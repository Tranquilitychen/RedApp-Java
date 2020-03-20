package com.kalic.redapple.web.controller;

import com.kalic.redapple.pojo.Room;
import com.kalic.redapple.service.RoomService;
import com.kalic.redapple.utils.ResultDto;
import com.kalic.redapple.vo.VoRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoomController {
    @Autowired
    private RoomService roomServiceImpl;

    @RequestMapping("/room/getAllRoomData")
    public List<VoRoom> showAllRooms(){
        return roomServiceImpl.getListRooms();
    }

    @RequestMapping("/room/getEmptyRoomData")
    public List<VoRoom> showEmptyRooms(){
        return roomServiceImpl.getEmptyRooms();
    }

    @RequestMapping("/room/getDirtyRoomData")
    public List<Room> showDirtyRooms(){
        return roomServiceImpl.selAllDirtyRooms();
    }
    /**
     *  插入单个房间信息
     * @return
     */
    @RequestMapping("/room/insertRoomData")
    public ResultDto<Integer> insertRoom(String jsonRoom){
        return roomServiceImpl.insertSingleRoom(jsonRoom);
    }

    /**
     *  批量插入房间信息
     * @param batchJsonRoom
     * @return
     */
    @RequestMapping("/room/insertBatchRoom")
    public ResultDto<Integer> insertBatchRoom(String batchJsonRoom){
        return roomServiceImpl.insertBatchRoom(batchJsonRoom);
    }

    @RequestMapping("/room/updCleanRoom")
    public ResultDto<Integer> cleanRoom(String roomJson){
        return roomServiceImpl.updCleanRoom(roomJson);
    }
}
