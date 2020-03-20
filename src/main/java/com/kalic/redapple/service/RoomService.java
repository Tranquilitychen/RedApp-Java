package com.kalic.redapple.service;

import com.kalic.redapple.pojo.Room;
import com.kalic.redapple.utils.ResultDto;
import com.kalic.redapple.vo.VoRoom;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

public interface RoomService {
    // 1. 获取所有的房间信息
    List<VoRoom> getListRooms();
    // 1.1 获取所有的空房间
    List<VoRoom> getEmptyRooms();
    // 2. 新增单个房间信息
    ResultDto<Integer> insertSingleRoom(String jsonRoom);
    // 3. 新增 批量 房间信息
    ResultDto<Integer> insertBatchRoom(String batchJsonRoom);
    // 4. 获取脏房间的信息
    List<Room> selAllDirtyRooms();
    // 5. 清理房间
    ResultDto<Integer> updCleanRoom(String roomJson);
}
