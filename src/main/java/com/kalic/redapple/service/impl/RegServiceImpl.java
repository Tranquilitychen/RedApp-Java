package com.kalic.redapple.service.impl;

import com.alibaba.fastjson.JSON;
import com.kalic.redapple.mapper.*;
import com.kalic.redapple.pojo.*;
import com.kalic.redapple.service.RegService;
import com.kalic.redapple.utils.Buildno;
import com.kalic.redapple.utils.ResultDto;
import com.kalic.redapple.vo.LinkRoomInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional(rollbackFor = {Exception.class})
@Service
public class RegServiceImpl implements RegService {
    @Autowired
    private RegMapper regMapper;
    @Autowired
    private GuestMapper guestMapper;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private RegBillMapper regBillMapper;
    @Autowired
    private PayDetailedMapper payDetailedMapper;
    @Autowired
    private RoomLogMapper roomLogMapper;

    @Override
    public List<Reg> getAllReg() {
        System.out.println(regMapper.getAllReg());
        return regMapper.getAllReg();
    }

    /**
     * 入住订单需要的操作
     * 1. 客人信息录入
     * 2. 订单填写完成
     * 3. 房间状态变更
     * 4. 订单消费 录入 房租（消费项目）
     * 5. 账单明细表 录入
     * 6. 日志录入
     * 以上哪一条出现错误都不算成功，所以需要事务回滚
     * @param jsonReg
     * @param linkRoomno
     * @return
     */
    @Override
    public ResultDto<Integer> insRegData(String jsonReg, String linkRoomno) {
        if (jsonReg == "" || jsonReg.isEmpty()){
            return new ResultDto<>(400, "参数错误");
        }
        List<Reg> regs = JSON.parseArray(jsonReg, Reg.class);
        // 订单信息
        List<Reg> insRegs = new ArrayList<>();
        // 账单信息
        List<RegBill> indRegBills = new ArrayList<>();
        List<PayDetailed> insPayDetaileds = new ArrayList<>();
        int index = 0;
        // 事务回滚
        try {
            // 1. 插入 新房客
            // 取出其中一个来进行插入操作
            Reg otherReg = regs.get(0);
            System.out.println("======= 模板账单 =========");
            System.out.println(otherReg);
            Guest guest = insGuest(otherReg);
            index += guestMapper.insGuestInfo(guest);
            long guestno = guest.getGuestno();
            System.out.println("======== 数据库返回插入客人信息的did =========");
            System.out.println(guestno);
            // 1.1 查询出当日的最大账单号
            // 1.1.1 获取最大的Regno
            String linkno = Buildno.buildRegnoPref(otherReg.getIsgroup());
            System.out.println("linkNo;" + linkno);
            String maxRegnoToday = regMapper.selMaxLikeRegno(linkno);
            System.out.println("当日最大的订单编号：" + maxRegnoToday);
            int maxno= 0;
            if (maxRegnoToday != null) {
                maxno = Integer.parseInt(maxRegnoToday.substring(9));
            }
            System.out.println("最大号：" + maxno);
            System.out.println("=======================================");
            int maxLinkid = 0;
            int maxGroupid = 0;
            if (otherReg.getIsgroup().equals("0")){
                maxLinkid = regMapper.selMaxLinkidReg();
                maxLinkid++;
                System.out.println("新的联房号：" + maxLinkid);
            }else{
                maxGroupid = regMapper.selMaxGroupidReg();
                maxGroupid++;
                System.out.println("新的团体房号：" + maxGroupid);
            }
            System.out.println("=======================================");
            // 以下开始进行订单创建以及账单信息录入
            for (Reg reg : regs){
                Reg buildReg;
                // 如果 linkRoomno 为 null 则 没有联房，联房号为账单号，联房id 为 0
                if (reg.getIsgroup().equals("0")) {
                    // 说明是一个房间(前台已经判断过了)
                    if (regs.size() == 0) {
                        linkRoomno = "";
                    }
                    maxno++;
                    buildReg = guestRegBuilder(reg, linkRoomno, guestno, maxno, maxLinkid);
                }else{
                    // 如果 linkRoomno 不能为 null，团体房号为账单号，联房id 为 1
                    maxno++;
                    buildReg = teamRegBuilder(reg, linkRoomno, guestno, maxno, maxGroupid);
                }
                System.out.println("======== buildReg ==========");
                System.out.println(buildReg);
                // 2. 修改房间状态 "0" 表示入住
                index += roomMapper.updateRoomStatus("1", buildReg.getRegno(), buildReg.getRoomno());
                // 3. 插入房间账单
                RegBill buildRegBill = insRegBill(buildReg);
                System.out.println("客房入住的房租账单：" + buildRegBill);
                // 4. 插入账单明细 对象
                if (buildReg.getSecurityReal() > 0){
                    // 添加了 dtIndate 与 dtOutdate
                    PayDetailed payDetailed = buildPayDetailed(buildReg);
                    insPayDetaileds.add(payDetailed);
                    buildReg.setPaywayno(payDetailed.getPayno());
                }
                indRegBills.add(buildRegBill);
                insRegs.add(buildReg);
            }
            System.out.println("======== 插入账单 ==========");
            System.out.println(insRegs);
            // 5. 插入账单信息 List
            index += regMapper.insRegInfo(insRegs);
            // 6. 插入 账单消费表
            index += regBillMapper.insRegBillFormReg(indRegBills);
            // 7. 插入 账单明细表
            if (insPayDetaileds.size() != 0){
                index += payDetailedMapper.insBatchPayDetaileds(insPayDetaileds);
            }
            System.out.println(index);
            System.out.println("==========================");
            System.out.println(insRegs.get(0).getDtIndate());
        }catch(Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultDto<>(400, "账单插入失败");
        }
        System.out.println(index);
        if (index == 0){
            return new ResultDto<>(400, "账单插入，出现未知错误");
        }else{
            return new ResultDto<>(200, "账单插入成功");
        }
    }

    /**
     *  房间 --- 联房
     * @param jsonLink
     * @return
     */
    @Override
    public ResultDto<Integer> updRegLinkData(String jsonLink) {
        if(jsonLink == "" || jsonLink.isEmpty()){
            return new ResultDto<>(400, "参数错误");
        }
        List<LinkRoomInfo> linkRoomInfos = JSON.parseArray(jsonLink, LinkRoomInfo.class);
        int index = 0;
        try{
            LinkRoomInfo roomInfo = linkRoomInfos.get(0);
            int maxLinkid = 0;
            if (roomInfo.getLinkid() == -1){
                // 获取最大的联号
               maxLinkid = regMapper.selMaxLinkidReg();
                for (LinkRoomInfo linkRoomInfo: linkRoomInfos){
                    linkRoomInfo.setLinkid(maxLinkid + 1);
                    index = regMapper.updRegLinkRoom(linkRoomInfo);
                }
            }else{
                for (LinkRoomInfo linkRoomInfo: linkRoomInfos){
                    System.out.println(linkRoomInfo);
                    index = regMapper.updRegLinkRoom(linkRoomInfo);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultDto<>(400, "联房失败");
        }
        if (index == 0){
            return new ResultDto<>(400, "联房失败，出现未知错误");
        }else{
            return new ResultDto<>(200, "联房成功");
        }
    }

    /**
     * 房间拆房
     * @param jsonUnpack
     * @return
     */
    @Override
    public ResultDto<Integer> updRegUnpackData(String jsonUnpack) {
        if(jsonUnpack == "" || jsonUnpack.isEmpty()){
            return new ResultDto<>(400, "参数错误");
        }
        List<LinkRoomInfo> linkRoomInfos = JSON.parseArray(jsonUnpack, LinkRoomInfo.class);
        int index = 0;
        try{
            for (LinkRoomInfo linkRoomInfo: linkRoomInfos){
                index = regMapper.updRegUnpackRoom(linkRoomInfo);
            }
        }catch(Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultDto<>(400, "拆房失败");
        }
        if (index == 0){
            return new ResultDto<>(400, "拆房失败，出现未知错误");
        }else{
            return new ResultDto<>(200, "拆房成功");
        }
    }

    /**
     *  顾客账单初始化，填充相关信息
     * @param reg 未初始化的账单信息
     * @return
     */
    private Reg guestRegBuilder(Reg reg, String linkRoomno, long guestno, int maxno, int maxLinkid){
        String regno = Buildno.buildRegnoPref(reg.getIsgroup()) + Buildno.zeroRegno(maxno);
        reg.setRegno(regno);
        // 判断是单个房间 还是 需要联房
        if (linkRoomno == ""){
            reg.setLinkno("");
            reg.setLinkid(0);
        }else{
            // 需要联房
            reg.setLinkno(linkRoomno);
            reg.setLinkid(maxLinkid);
        }
        reg.setGuestno(guestno);
        reg.setSureflag("Y");
        reg.setNullify("Y");
        return reg;
    }
    /**
     *  团队账单初始化，填充相关信息
     * @param reg 未初始化的账单信息
     * @param linkRoomno 使用团体房的房间号来判断主房间
     * @return
     */
    private Reg teamRegBuilder(Reg reg, String linkRoomno, long guestno, int maxno, int maxGroupid){
        String regno = Buildno.buildRegnoPref(reg.getIsgroup()) + Buildno.zeroRegno(maxno);
        reg.setRegno(regno);
        reg.setGroupid(maxGroupid);
        reg.setGuestno(guestno);
        reg.setGroupno(linkRoomno);
        reg.setSureflag("Y");
        reg.setNullify("Y");
        return reg;
    }

    /**
     * 完善 散客 信息
     * @param reg 账单信息
     * @return
     */
    private Guest insGuest(Reg reg){
        Guest guest = new Guest();
        if (reg.getIsgroup().equals("0")){
            guest.setGuestname(reg.getGuestname());
            guest.setIdtypeno(reg.getIdtypeno());
            guest.setIdno(reg.getIdno());
            guest.setSex(reg.getSex());
            guest.setDtBirthday(reg.getDtBirthday());
            guest.setCountryno(reg.getCountryno());
            guest.setAreano(reg.getAreano());
            guest.setAddress(reg.getAddress());
            guest.setTel(reg.getTel());
            guest.setMeno(reg.getMeno());
            guest.setGroupflag("N");
            return guest;
        }else{
            guest.setGuestname(reg.getGroupname());
            guest.setGroupflag("Y");
            guest.setTel(reg.getTel());
            return guest;
        }

    }

    /**
     *  消费账单
     * @param reg
     * @return
     */
    private RegBill insRegBill(Reg reg){
        double totalprice = reg.getPrice() * reg.getDays();
        RegBill regBill = new RegBill();
        regBill.setRoomno(reg.getRoomno());
        regBill.setRegno(reg.getRegno());
        regBill.setItemno("001");
        regBill.setItemname("房租");
        regBill.setNum(reg.getDays());
        regBill.setPrice(reg.getPrice());
        regBill.setDiscount(reg.getDiscount());
        regBill.setTotalprice(totalprice);
        regBill.setDtIndate(reg.getDtIndate());
        regBill.setDtOutdate(reg.getDtOutdate());
        regBill.setDtOper(reg.getDtIndate());
        regBill.setOperid(reg.getOperid());
        regBill.setNullify("Y");
        return regBill;
    }

    /**
     * 账单明细 信息
     */
    private PayDetailed buildPayDetailed (Reg reg){
        PayDetailed payDetailed = new PayDetailed();
        payDetailed.setPayno("P" + reg.getRegno());
        payDetailed.setRoomno(reg.getRoomno());
        payDetailed.setRegno(reg.getRegno());
        payDetailed.setGuestno(reg.getGuestno());
        // 此处的押金 为 入住交的押金 或者 没有交押金而已
        payDetailed.setMoney(reg.getSecurityReal());
        payDetailed.setIsReceipts("Y");
        payDetailed.setSourcetype(reg.getSecuritytype());
        payDetailed.setPaytype("002");
        payDetailed.setDtOper(reg.getDtIndate());
        payDetailed.setOperid(reg.getOperid());
        payDetailed.setMeno("入住交押金");
        return payDetailed;
    }
}