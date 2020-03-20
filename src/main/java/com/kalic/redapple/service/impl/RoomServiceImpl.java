package com.kalic.redapple.service.impl;

import com.alibaba.fastjson.JSON;
import com.kalic.redapple.mapper.FloorMapper;
import com.kalic.redapple.mapper.RoomMapper;
import com.kalic.redapple.mapper.RoomlbMapper;
import com.kalic.redapple.pojo.Floor;
import com.kalic.redapple.pojo.Room;
import com.kalic.redapple.pojo.Roomlb;
import com.kalic.redapple.service.RoomService;
import com.kalic.redapple.utils.ResultDto;
import com.kalic.redapple.vo.VoRoom;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomMapper roomMapper;
    @Autowired
    private RoomlbMapper roomlbMapper;
    @Autowired
    private FloorMapper floorMapper;

    /**
     *  获取 房间的所有信息 （包含有房间分类的一些信息，展示房间的包装类）
     * @return 房间信息包装类
     */
    @Override
    public List<VoRoom> getListRooms() {
        System.out.println(roomMapper);
        List<Room> rooms = roomMapper.getAllRoom();
        List<Roomlb> roomlbs = roomlbMapper.getAllRoomlb();
        List<Floor> floors = floorMapper.getAllFloor();

        List<VoRoom> voRooms = new ArrayList<>();
        for (Room room: rooms) {
            VoRoom voRoom = new VoRoom();
            BeanUtils.copyProperties(room, voRoom);
            Roomlb roomlb = getRoomlbForRoomlbno(voRoom.getRoomlbno(),roomlbs);
            voRoom.setRoomlbname(roomlb.getRoomlbname());
            voRoom.setSubname(roomlb.getSubname());
            Floor floor = getFloorForFloorno(voRoom.getFloorno(), floors);
            voRoom.setFloorname(floor.getFloorname());
            voRooms.add(voRoom);
        }
        System.out.println(voRooms);
        // 向Room 中添加 一些字段
        return voRooms;
    }

    @Override
    public List<VoRoom> getEmptyRooms() {
        System.out.println(roomMapper);
        List<Room> rooms = roomMapper.getEmptyRooms();
        List<Roomlb> roomlbs = roomlbMapper.getAllRoomlb();
        List<Floor> floors = floorMapper.getAllFloor();

        List<VoRoom> voRooms = new ArrayList<>();
        for (Room room: rooms) {
            VoRoom voRoom = new VoRoom();
            BeanUtils.copyProperties(room, voRoom);
            Roomlb roomlb = getRoomlbForRoomlbno(voRoom.getRoomlbno(),roomlbs);
            voRoom.setRoomlbname(roomlb.getRoomlbname());
            voRoom.setSubname(roomlb.getSubname());
            voRoom.setPrice(roomlb.getPrice());
            Floor floor = getFloorForFloorno(voRoom.getFloorno(), floors);
            voRoom.setFloorname(floor.getFloorname());
            voRooms.add(voRoom);
        }
        System.out.println(voRooms);
        // 向Room 中添加 一些字段
        return voRooms;
    }

    /**
     * 插入 单个房间 信息
     * @param jsonRoom
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto<Integer> insertSingleRoom(String jsonRoom) {
        if (jsonRoom == "" || jsonRoom.isEmpty()){
            return new ResultDto<>(400, "参数错误");
        }
        Room room = JSON.parseObject(jsonRoom, Room.class);
        room.setRoomname(room.getRoomno());
        room.setRstatus("0");
/*        room.setIsditry("N");
        room.setIsown("N");
        room.setStopflag("N");*/
        System.out.println(room);
        int resultIndex = 0;
        try {
            resultIndex = roomMapper.insertRoom(room);
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        if (resultIndex == 0){
            return new ResultDto<>(406, "插入失败");
        }else {
            return new ResultDto<>(200, "获取成功", resultIndex);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDto<Integer> insertBatchRoom(String batchJsonRoom) {
        if (batchJsonRoom == "" || batchJsonRoom.isEmpty()){
            return new ResultDto<>(400, "参数错误");
        }
        List<Room> rooms = JSON.parseArray(batchJsonRoom, Room.class);
        for (Room room : rooms) {
            room.setRoomname(room.getRoomno());
            room.setRstatus("0");
            System.out.println(room);
        }
        int resultIndex;
        try {
            resultIndex = roomMapper.insertBatchRooms(rooms);
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultDto<>(406, "插入失败");
        }
        if (resultIndex == 0){
            return new ResultDto<>(406, "插入失败");
        }else {
            return new ResultDto<>(200, "获取成功", resultIndex);
        }
    }

    @Override
    public List<Room> selAllDirtyRooms() {
        return roomMapper.selAllDirtyRooms();
    }

    @Override
    public ResultDto<Integer> updCleanRoom(String roomJson) {
        if (roomJson == "" || roomJson.isEmpty()){
            return new ResultDto<>(400, "参数错误");
        }
        List<Room> rooms = JSON.parseArray(roomJson, Room.class);
        int index = 0;
        try{
            for (Room room: rooms){
                index +=roomMapper.updateRstatus("0", room.getRoomno());
            }
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return new ResultDto<>(406, "修改失败");
        }
        if (index == 0){
            return new ResultDto<>(406, "修改失败");
        }else {
            return new ResultDto<>(200, "修改成功", index);
        }
    }

    /**
     *  根据 房间分类no 获取房间信息
     * @param roomlbno
     * @param roomlbs
     * @return 房间分类的class
     */
    private Roomlb getRoomlbForRoomlbno(String roomlbno, List<Roomlb> roomlbs){
        List<Roomlb> result = roomlbs.stream()
                .filter(item -> item.getRoomlbno().equals(roomlbno))
                .collect(Collectors.toList());
        return result.get(0);
    }

    /**
     *  根据 floorno 楼层id 获取楼层信息
     * @param floorno
     * @param floors
     * @return
     */
    private Floor getFloorForFloorno(String floorno, List<Floor> floors){
        List<Floor> result = floors.stream()
                .filter(item -> item.getFloorno().equals(floorno))
                .collect(Collectors.toList());
        return result.get(0);
    }
}
