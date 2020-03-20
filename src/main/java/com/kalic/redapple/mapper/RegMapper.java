package com.kalic.redapple.mapper;

import com.kalic.redapple.pojo.Reg;
import com.kalic.redapple.vo.LinkRoomInfo;
import com.kalic.redapple.vo.Roomqty;
import com.kalic.redapple.vo.SwapRoomInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RegMapper {
    // 1. 获取所有 已开房间 的 消费信息
    List<Reg> getAllReg();
    // 1.2 根据 regno 获取账单信息
    Reg selRegForRegno(@Param("regno") String regno);
    // 2. 插入账单信息
    int insRegInfo(@Param("regs") List<Reg> regs);
    // 3. 获取账单信息中最大的 regno
    String selMaxLikeRegno(@Param("linkno") String linkno);
    // 4. 获取账单信息中最大的 linkid 联房号
    int selMaxLinkidReg();
    // 4. 获取账单信息中最大的 groupid
    int selMaxGroupidReg();
    // 5. 账单续房 ， 修改账单的 离开日期 以及 入住天数
    int updRegRoomqty(@Param("roomqty") Roomqty roomqty);
    // 6. 退房
    int updRegExitRoom(@Param("regno") String regno, @Param("checkroomflag") String checkroomflag, @Param("nullify") String nullify);
    // 7. 房间联房
    int updRegLinkRoom(@Param("item") LinkRoomInfo linkRoomInfo);
    // 8. 房间拆房
    int updRegUnpackRoom(@Param("item") LinkRoomInfo linkRoomInfo);
    // 9. 换房
    int updRegSwapRoom(SwapRoomInfo swapRoomInfo);
}
