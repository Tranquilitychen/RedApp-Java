package com.kalic.redapple.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.kalic.redapple.mapper.*;
import com.kalic.redapple.pojo.*;
import com.kalic.redapple.service.BussinessCenterService;
import com.kalic.redapple.utils.ResultDto;
import com.kalic.redapple.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Kalic Li
 * @ClassName BussinessCenterServiceImpl
 * @Package com.kalic.redapple.service.impl
 * @Description TODO
 * @date 2020/2/15 13:46
 */
@Transactional(rollbackFor = {Exception.class})
@Service
public class BussinessCenterServiceImpl implements BussinessCenterService {
    @Autowired
    private FloorMapper floorMapper;
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private RoomlbMapper roomlbMapper;
    @Autowired
    private RegMapper regMapper;
    @Autowired
    private BookingMapper bookingMapper;
    @Autowired
    private GuestMapper guestMapper;
    @Autowired
    private RegBillMapper regBillMapper;
    @Autowired
    private PayDetailedMapper payDetailedMapper;
    @Autowired
    private RoomLogMapper roomLogMapper;

    /**
     * 营业中心的所需要的所有信息
     * @return
     */
    @Override
    public ResultDto<List<AllData>> getBCData() {
        // 1. 获取所有的房间信息
        List<Room> roomList = roomMapper.getAllRoom();
        //2 . 获取所有的房间分类信息
        List<Roomlb> roomlbList = roomlbMapper.getAllRoomlb();
        // 3. 订单信息
        List<Reg> regList = regMapper.getAllReg();
        // 4. 所有的预定信息
        List<Booking> bookingList = bookingMapper.selAllBookingInfo();
        // 5. 所有的顾客信息
        List<Guest> guestList = guestMapper.getAllGuest();
        // 6. 所有的账单信息
        List<RegBill> regBillList = regBillMapper.getAllRegBill();
        // 7. 所有楼层信息
        List<Floor> floorList = floorMapper.getAllFloor();
        // 8. 所有账单明细
        List<PayDetailed> payDetailedList = payDetailedMapper.selPayDetaileds();
        List<AllData> allDatas = new ArrayList<>();
        for (Room room: roomList) {
            // 1. 所有的房间分类信息。例如： roomno: 8089
            AllData allData = new AllData();
            // 1.1 添加房间信息
            allData.setRoom(room);
            // 1.2 根据房间信息检索房间分类信息
            List<Roomlb> collectRoomlb = roomlbList.stream()
                    .filter(roomlb -> roomlb.getRoomlbno().equals(room.getRoomlbno()))
                    .collect(Collectors.toList());
            allData.setRoomlb(collectRoomlb.get(0));
            // 1.3 根据房间信息检索楼层信息
            List<Floor> collectFloor = floorList.stream()
                    .filter(floor -> floor.getFloorno().equals(room.getFloorno()))
                    .collect(Collectors.toList());
            allData.setFloor(collectFloor.get(0));
            // 1.3 判断房间状态
            // 1.3.1 房间状态是 在住房间
            if(room.getRegno() != null && room.getRstatus().equals("1")){
                // 1.4.1 根据房间信息检索房间账单信息
                List<Reg> collectReg = regList.stream()
                        .filter(reg -> reg.getRoomno().equals(room.getRoomno()) && reg.getNullify().equals("Y"))
                        .collect(Collectors.toList());
                if (collectReg.size() != 0){
                    Reg reg = collectReg.get(0);
                    allData.setReg(reg);
                    // 1.5.1 当账单存在时，可以查询客人信息
                    List<Guest> collectGuest = guestList.stream()
                            .filter(guest -> guest.getGuestno() == reg.getGuestno())
                            .collect(Collectors.toList());
                    if (collectGuest.size() != 0){
                        Guest guest = collectGuest.get(0);
                        allData.setGuest(guest);
                    }
                    // 1.5.2 当账单存在时，可以查询当前账单 信息
                    List<RegBill> collectRegBill = regBillList.stream()
                            .filter(regBill ->  regBill.getRegno().equals(reg.getRegno()))
                            .collect(Collectors.toList());
                    System.out.println("collectRegBill:" + collectRegBill);
                    if (collectRegBill.size() != 0){
                        System.out.println(collectRegBill);
                        allData.setRegBill(collectRegBill);
                    }

                    // 1.5.3 当账单存在时，获取账单明细信息(入住交的押金没有 regno 怎么获取啊)
                    System.out.println(payDetailedList);
                    List<PayDetailed> collectPayDetailed = payDetailedList.stream()
                            .filter(payDetailed -> payDetailed.getRegno() != null && payDetailed.getRegno().equals(reg.getRegno()))
                            .collect(Collectors.toList());
                    System.out.println("collectPayDetailed:" + collectPayDetailed);
                    if (collectPayDetailed.size() != 0){
                        allData.setPayDetaileds(collectPayDetailed);
                    }

                    // 1.5.4 为 账单添加 预定账单信息
                    List<Booking> collectBooking = bookingList.stream()
                            .filter(booking -> booking.getBookingno().equals(reg.getBookingno()))
                            .collect(Collectors.toList());
                    if (collectBooking.size() != 0) {
                        Booking booking = collectBooking.get(0);
                        allData.setBooking(booking);
                        // 如果有预定信息，再查看下是否有 预定交押金
                        List<PayDetailed> bookingPayDetailed = payDetailedList.stream()
                                .filter(payDetailed -> payDetailed.getPaytype().equals("001") && payDetailed.getBookingno() != null && payDetailed.getBookingno().equals(booking.getBookingno()))
                                .collect(Collectors.toList());
                        System.out.println("Booking collectPayDetailed:" + bookingPayDetailed);
                        if (bookingPayDetailed.size() != 0){
                            // allData 已经 添加过一次 押金记录了，之后再来一次需要先取出来再添加
                            List<PayDetailed> payDetaileds = allData.getPayDetaileds();
                            // 合并两个 List
/*                            payDetaileds.stream().sequential().collect(Collectors.toCollection(() ->  collectPayDetailed));*/
                            payDetaileds.addAll(bookingPayDetailed);
                            System.out.println("Booking collectPayDetailed:" + payDetaileds);
                            allData.setPayDetaileds(payDetaileds);
                        }
                    }
                }
/*            }else if(!room.getBookingno().isEmpty()){
*               暂时修改以下，感觉不对，应该时房间状态的问题 && room.getBookingno() != null
* */
            }else if(room.getRstatus().equals("2")){
                // 1.3.2 房间状态是 预定房 （已经到了预定时间，颜色为紫色）
                List<Booking> collectBooking = bookingList.stream()
                        .filter(booking ->
                                booking.getBookingno().equals(room.getBookingno()) && booking.getDtCancel() == null)
                        .collect(Collectors.toList());
                if (collectBooking.size() != 0){
                    Booking booking = collectBooking.get(0);
                    allData.setBooking(booking);
                    // 1.3.2.1 添加预定人信息
                    List<Guest> collectGuest = guestList.stream()
                            .filter(guest -> guest.getGuestno() == booking.getGuestno())
                            .collect(Collectors.toList());
                    if (collectGuest.size() != 0) {
                        Guest guest = collectGuest.get(0);
                        allData.setGuest(guest);
                    }
                    // 1.3.2.2 添加 账单详情
                  List<PayDetailed> collectPayDetailed = payDetailedList.stream()
                            .filter(payDetailed -> payDetailed.getBookingno() != null && payDetailed.getBookingno().equals(booking.getBookingno()))
                            .collect(Collectors.toList());
                    if (collectPayDetailed.size() != 0){
                        allData.setPayDetaileds(collectPayDetailed);
                    }
                }
            } else if (room.getRstatus().equals("3")){
                // 1.3.3 房间状态是 脏房，房间基本信息
                System.out.println("该房间为脏房");

            }else if (!room.getOwnno().isEmpty() && room.getRstatus().equals("4")){
                // 1.3.4 房间状态是 自用房，添加自用房信息
                System.out.println("该房间为自用房");

            }else if (!room.getRstatus().isEmpty() && room.getRstatus().equals("5")){
                // 1.3.5 房间状态是 维修房，添加维修房信息
                System.out.println("该房间为维修房");
            }else {
                System.out.println("空房间,空房间存在预定信息");
                //  空房间添加预定信息
                List<Booking> collectBooking = bookingList.stream()
                        .filter(booking -> booking.getBookingno().equals(room.getBookingno()))
                        .collect(Collectors.toList());
                if (collectBooking.size() != 0){
                    // 目前只能有一个预定
                    allData.setBooking(collectBooking.get(0));

                    // 添加顾客信息（目前只能添加一个 顾客信息）
                    List<Guest> collectGuest = guestList.stream()
                            .filter(guest -> guest.getGuestno() == collectBooking.get(0).getGuestno())
                            .collect(Collectors.toList());
                    if (collectGuest.size() != 0) {
                        Guest guest = collectGuest.get(0);
                        allData.setGuest(guest);
                    }

                    // 1.3.2.2 添加 账单详情
                    List<PayDetailed> collectPayDetailed = payDetailedList.stream()
                            .filter(payDetailed -> payDetailed.getBookingno() != null && payDetailed.getBookingno().equals(collectBooking.get(0).getBookingno()))
                            .collect(Collectors.toList());
                    if (collectPayDetailed.size() != 0){
                        allData.setPayDetaileds(collectPayDetailed);
                    }
                }
                System.out.println(allData);
            }
            allDatas.add(allData);
        }
        for (AllData allData: allDatas) {
            System.out.println(allData);
        }
        System.out.println(allDatas);
        System.out.println(bookingList);
        return new ResultDto<List<AllData>>(200, "获取信息成功", allDatas);
    }

    /**
     * 批量修改 已经到了预定时间，但没有入住的房间为 预定状态,并且修改预定单
     * @param roomjson
     * @return
     */
    @Override
    public ResultDto<Integer> updRoomRstatus(String roomjson) {
        if (roomjson == "" || roomjson.isEmpty()){
            return new ResultDto<>(400, "参数错误");
        }
        int index = 0;
        List<BSTimer> bsTimers = JSON.parseArray(roomjson, BSTimer.class);
        System.out.println("bsTimers:" + bsTimers);
        try{
            index += roomMapper.updateBatchRstatus("2", bsTimers);
            for (BSTimer bsTimer : bsTimers){
                index += roomMapper.updateBookingBookingno(bsTimer.getBookingno(), bsTimer.getRoomno());
            }
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultDto<>(406, "修改失败");
        }
        if (index == 0){
            return new ResultDto<>(406, "修改失败");
        }else{
            return new ResultDto<>(200, "修改成功", index);
        }
    }

    /**
     * 批量取消预定信息
     * @param roomjson
     * @return
     */
    @Override
    public ResultDto<Integer> updCancelBooking(String roomjson) {
        if (roomjson == "" || roomjson.isEmpty()){
            return new ResultDto<>(400, "参数错误");
        }
        int index = 0;
        List<BSTimer> bsTimers = JSON.parseArray(roomjson, BSTimer.class);
        System.out.println(bsTimers);
        Timestamp nowDate = new Timestamp(new Date().getTime());
        System.out.println(nowDate);
        try{
            for (BSTimer bsTimer: bsTimers){
                // 1. 修改房间的 预定号 为"" 空
                index += roomMapper.updCalcelBookingRoom("", "0" , bsTimer.getRoomno());
                // 3. 修改 预定表中的 预定信息,填写 取消原因
                index += bookingMapper.updCancelBooking(bsTimer.getBookingno(), nowDate, "客人未在保留时间内入住，取消预定。");
            }
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultDto<>(406, "修改失败");
        }
        if (index == 0){
            return new ResultDto<>(406, "修改失败");
        }else{
            return new ResultDto<>(200, "修改成功", index);
        }
    }

    /**
     *  续房 需要修改订单中的离开时间，以及入住天数,填写续住日期（目前只续住了一次，再一次会覆盖上一次的续住日期 roomqty）
     * @param regjson
     * @return
     */
    @Override
    public ResultDto<Integer> updRoomqtyReg(String regjson, String resultjson) {
        if ((regjson == "" || regjson.isEmpty()) && (resultjson == "" || resultjson.isEmpty())){
            return new ResultDto<>(400, "参数错误");
        }
        Roomqty roomqty = JSON.parseObject(regjson, Roomqty.class);
        roomqty.setDays(Integer.parseInt(roomqty.getRoomqty()) + roomqty.getDays());
        System.out.println(roomqty);
        RegBill regBill = JSON.parseObject(resultjson, RegBill.class);
        int index = 0;
        try{
            // 1. 订单信息 入住天数 更新
            index += regMapper.updRegRoomqty(roomqty);
            // 2. 账单信息 入住天数更新
            System.out.println(regBill);
            index += regBillMapper.updRegBillQty(regBill);
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultDto<>(406, "修改失败");
        }
        if (index == 0){
            return new ResultDto<>(406, "修改失败");
        }else{
            return new ResultDto<>(200, "修改成功", index);
        }
    }

    /**
     * 换房
     * @param swapJson
     * @return
     */
    @Override
    public ResultDto<Integer> updSwapRoom(String swapJson) {
        if (swapJson == "" || swapJson.isEmpty()){
            return new ResultDto<>(400, "参数错误");
        }
        SwapRoomInfo swapRoomInfo = JSON.parseObject(swapJson, SwapRoomInfo.class);
        System.out.println(swapRoomInfo);
        int index = 0;
        try {
            // 1. 更换账单： 更换账单的 roomno 以及 price 房间状态. 修改房间账单号的 Roomno
            regMapper.updRegSwapRoom(swapRoomInfo);
            // 2. 更换RegBill 更换 roomno 为 新房间， regno不变
            // 更换房间账单时：先将原先的账单进行修改，但要注意 住房 天数 需要进行修改
            // 对房费账单的处理
            if (swapRoomInfo.getNewBillFlag()){
                /** 如果为真，说明需要新增房费账单
                 ** 1. 修改旧帐单的房费天数 以及 总价
                 *  2. 新增新账单
                 **/
                // 1. 修改旧帐单的房费天数 以及 总价 以及有效天数，房间的dtOutdate 为 dtIndate.
                index += regBillMapper.updRegBillRoomBillSwapRoom(swapRoomInfo, "001", "换房");
                // 2. 新增账单
                RegBill regBill = new RegBill();
                regBill.setFlowid(swapRoomInfo.getFlowid());
                regBill.setRoomno(swapRoomInfo.getNewRoomno());
                regBill.setRegno(swapRoomInfo.getRegno());
                regBill.setItemno("001");
                regBill.setItemname("房租");
                regBill.setNum(swapRoomInfo.getNewBillNum());
                regBill.setPrice(swapRoomInfo.getNewRegPrice());
                regBill.setDiscount(0.00);
                regBill.setTotalprice(swapRoomInfo.getInsBillTotalPrice());
                regBill.setDtIndate(swapRoomInfo.getDtIndate());
                regBill.setDtOutdate(swapRoomInfo.getDtOutdate());
                regBill.setDtOper(swapRoomInfo.getDtOper());
                regBill.setOperid(swapRoomInfo.getOperid());
                regBill.setMeno("换房");
                regBill.setNullify("Y");
                System.out.println(regBill);
                index +=  regBillMapper.insSwapRoomBill(regBill);
            }else{
                // 之更改账单的房间编号以及 dtOutdate
                index += regBillMapper.updRegBillRoomno(swapRoomInfo, "001", "换房");
            }
            // 对除房费之外的账单进行换房
            index += regBillMapper.updRegBillSwapRoom(swapRoomInfo);
            // 3. 更换房间信息： 旧房间rstatus 变更为3 脏房， regno变为"" ; 新房间rstatus 变更为1，regno变为 旧房间的 regno
            // 旧房间rstatus 变更为3 脏房， regno变为""
            index += roomMapper.updSwapRoom(swapRoomInfo.getOldRoomno(), "", "3");
            // 新房间rstatus 变更为1，regno变为 旧房间的 regno
            index += roomMapper.updSwapRoom(swapRoomInfo.getNewRoomno(), swapRoomInfo.getRegno(), "1");
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultDto<Integer>(406, "修改失败");
        }
        if (index == 0){
            return new ResultDto<Integer>(406, "修改失败");
        }else{
            return new ResultDto<Integer>(200, "修改成功", index);
        }
    }

    /**
     *  退房：
     *  1. 修改订单信息，nullify 变为 Y, checkroomflag 变未 true
     *  2. 修改房间信息，将房间信息 Regno 设置为 null
     *  3. 修改账单信息，nullify 变为 N, meno 设置为 账单完成
     * @param regjson
     * @return
     */
    @Override
    public ResultDto<Integer> updExitRoom(String regjson, String resultjson) {
        if ((regjson == "" || regjson.isEmpty()) && (resultjson == "" || resultjson.isEmpty())){
            return new ResultDto<Integer>(400, "参数错误");
        }
/*        FinishReg finishReg = JSON.parseObject(regjson, FinishReg.class);*/
        List<FinishRegno> finishRegnoList = JSON.parseArray(resultjson, FinishRegno.class);
        System.out.println(finishRegnoList);
        Timestamp now = new Timestamp(new Date().getTime());
        List<PayDetailed> payDetaileds = new ArrayList<>();
        int index = 0;
        try{
            for (FinishRegno finishRegno :finishRegnoList){
                System.out.println(finishRegno);
                if (finishRegno.getRegno().equals("") || finishRegno.getRoomno().equals("") || finishRegno.getFlowids().length == 0){
                    return new ResultDto<Integer>(400, "参数错误");
                }else{
                    // 1. 修改 账单信息
                    index += regMapper.updRegExitRoom(finishRegno.getRegno(), "Y", "N");
                    // 2. 修改房间信息
                    index += roomMapper.updExitRoom(finishRegno.getRoomno(), "","3");
                    // 3. 修改账单信息
                    Integer[] flows = finishRegno.getFlowids();
                    for (int i = 0; i < flows.length; i++){
                        index += regBillMapper.updRegBillNullify(finishRegno.getRegno(), flows[i]);
                    }
                    // 4. 填写 账单明细 -- 收到的钱 与 退还的押金
                    // 结账收银
                    PayDetailed recivePayDetailed = recivePayDetailed(finishRegno, now);
                    // 退还押金
                    PayDetailed backPayDetailed =  backPayDetailed(finishRegno, now);
                    payDetaileds.add(recivePayDetailed);
                    System.out.println("结账收银：" + recivePayDetailed);
                    payDetaileds.add(backPayDetailed);
                    System.out.println("退还押金："+ backPayDetailed);
                }
            }

            if(payDetaileds.size() != 0){
                index += payDetailedMapper.insBatchPayDetaileds(payDetaileds);
            }
           /* String[] regnoArr = finishReg.getRegno().split(",");
            String[] roomnoArr = finishReg.getRoomno().split(",");
            for (int i = 0; i < regnoArr.length; i++){
                String regno = regnoArr[i];
                String roomno = roomnoArr[i];
                if (regno.equals("") || roomno.equals("")){
                    return  new ResultDto<Integer>(400, "Param NullPoint");
                }else{
                    index += regMapper.updRegExitRoom(regno, "Y", "N");
                    index += roomMapper.updExitRoom(roomno, "","3");
                    // 3. 记录结账信息
                }
            }*/
            System.out.println("index:" + index);
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultDto<Integer>(406, "修改失败");
        }
        if (index == 0){
            return new ResultDto<Integer>(406, "修改失败");
        }else{
            return new ResultDto<Integer>(200, "修改成功", index);
        }
    }

    /**
     * 退房账单 明细表初始化
     * @return
     */
    private PayDetailed recivePayDetailed(FinishRegno finishRegno, Timestamp dtOper){
        PayDetailed payDetailed = new PayDetailed();
        payDetailed.setPayno("PO" + finishRegno.getRegno());
        payDetailed.setRegno(finishRegno.getRegno());
        payDetailed.setRoomno(finishRegno.getRoomno());
        payDetailed.setBookingno(finishRegno.getBookingno());
         payDetailed.setGuestno(finishRegno.getGuestno());
        // 此处的金额
        payDetailed.setMoney(finishRegno.getTotalRegBill());
        payDetailed.setIsReceipts("Y");
        // 暂时默认 退押金的来源为 收押金的来源 哇 保障
        payDetailed.setSourcetype(finishRegno.getSourcetype());
        payDetailed.setPaytype("004");
        payDetailed.setDtOper(dtOper);
        // 暂时线空着，不一样的
        payDetailed.setOperid(finishRegno.getOperid());
        payDetailed.setMeno("账单结算，结账收银");
        return payDetailed;
    }
    /**
     * 退房账单 明细表初始化 退还押金
     * @return
     */
    private PayDetailed backPayDetailed(FinishRegno finishRegno, Timestamp dtOper){
        PayDetailed payDetailed = new PayDetailed();
        payDetailed.setPayno("PS" + finishRegno.getRegno());
        payDetailed.setRegno(finishRegno.getRegno());
        payDetailed.setRoomno(finishRegno.getRoomno());
        payDetailed.setBookingno(finishRegno.getBookingno());
         payDetailed.setGuestno(finishRegno.getGuestno());
        // 此处的金额
        payDetailed.setMoney(finishRegno.getTotalSecurity());
        payDetailed.setIsReceipts("N");
        // 暂时默认 退押金的来源为 收押金的来源 哇 保障
        payDetailed.setSourcetype(finishRegno.getSourcetype());
        payDetailed.setPaytype("005");
        payDetailed.setDtOper(dtOper);
        // 暂时线空着，不一样的
        payDetailed.setOperid(finishRegno.getOperid());
        payDetailed.setMeno("账单结算，退还押金");
        return payDetailed;
    }


}
