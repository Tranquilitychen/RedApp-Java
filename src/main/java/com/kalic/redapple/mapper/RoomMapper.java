package com.kalic.redapple.mapper;
import com.kalic.redapple.pojo.Room;
import com.kalic.redapple.vo.BSTimer;
import com.kalic.redapple.vo.SwapRoomInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 房间（ra_gs_room）　SQL　
 */
public interface RoomMapper {
	// 1.查询所有的房间
    List<Room> getAllRoom();
    // 1.1.1 查询 空房 房间
    List<Room> getEmptyRooms();
    // 1.1.1 查询所有脏房间
    List<Room> selAllDirtyRooms();
    // 1.2 根据 房间id 查询房间信息
    Room selRoomForRoomno(@Param("roomno") String roomno);
    // 2.插入单个房间信息
    int insertRoom(Room room);
    // 3.批量插入房间信息
    int insertBatchRooms(@Param("rooms") List<Room> rooms);
    // 4.删除房间信息
    int deleteRoom(char roomno);
    // 5.更新 入住 房间 状态信息
    int updateRoomStatus(@Param("rstatus") String rstatus,@Param("regno") String regno, @Param("roomno")String roomno);
    // 6.更新 预定 房间 订单信息 bookingno
    int updateBookingBookingno(@Param("bookingno") String bookingno, @Param("roomno")String roomno);
    // 7.更新 房间 状态信息 rstatus = '2'
    int updateRstatus(@Param("rstatus") String rstatus, @Param("roomno") String roomno);
    // 8.批量更新 房间 状态 信息 rstatus
    int updateBatchRstatus(@Param("rstatus") String rstatus, @Param("bsTimers") List<BSTimer> bsTimers);
    // 9. 取消房间的预定信息
    int updCalcelBookingRoom(@Param("bookingno") String bookingno, @Param("rstatus") String rstatus, @Param("roomno")String roomno);
    // 10.退房
    int updExitRoom(@Param("roomno")String roomno, @Param("regno") String regno,@Param("rstatus") String rstatus);
    // 11. 换房
    int updSwapRoom(@Param("roomno")String roomno, @Param("regno") String regno,@Param("rstatus") String rstatus);
}
