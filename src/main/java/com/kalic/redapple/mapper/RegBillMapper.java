package com.kalic.redapple.mapper;

import com.kalic.redapple.pojo.RegBill;
import com.kalic.redapple.vo.RegBillInfo;
import com.kalic.redapple.vo.SwapRoomInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 我将 所有账单语句中的 and roomno = #{oldRoomno} 取消了，原因： 换房时 001 房费问题
 * 去掉之后 对于修改的时候可能就会出现多改，改的地方不对的问题
 */
public interface RegBillMapper {
    // 1. 获取所有 已开房间 的 消费信息
    List<RegBill> getAllRegBill();
    // 2. 根据 regno 和 roomno 获取账单信息
    List<RegBill> getRegBillForRegnoAndRoomno(@Param("regno") String regno, @Param("roomno")String roomno);
    // 3. 插入账单信息(前端)
    int insRegBill(@Param("regBillInfoList") List<RegBillInfo> regBillInfoList);
    // 3,1 插入账单信息（后端）
    int insRegBillFormReg(@Param("regBills") List<RegBill> regBills);
    // 4. 删除账单信息
    int delRegBill(@Param("regno") String regno, @Param("roomno")String roomno);
    // 6. 修改账单项目数量
    int updRegBillNum(RegBillInfo regBillInfo);
    // 7. 换房, 除房费以外
    int updRegBillSwapRoom(SwapRoomInfo swapRoomInfo);
    // 7.1 换房 房类 数量
    int updRegBillRoomBillSwapRoom(@Param("swapRoom") SwapRoomInfo swapRoomInfo, @Param("itemno") String itemno,@Param("meno") String meno);
    // 7.1.1 换房 只更换房间编号 roomno 以及 房间的离开时间
    int updRegBillRoomno(@Param("swapRoom") SwapRoomInfo swapRoomInfo, @Param("itemno") String itemno, @Param("meno") String meno);
    // 7.2 换房新增账单
    int insSwapRoomBill(@Param("regBill") RegBill regBill);
    // 续房
    int updRegBillQty(RegBill regBill);
    // 5. 完成账单信息
    int updRegBillNullify(@Param("regno") String regno, @Param("flowid")Integer flowid);
}
