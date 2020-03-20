import com.alibaba.fastjson.JSON;
import com.kalic.redapple.pojo.Room;
import com.kalic.redapple.pojo.Roomlb;
import com.kalic.redapple.service.RoomService;
import com.kalic.redapple.service.RoomlbService;
import com.kalic.redapple.utils.ResultDto;
import com.kalic.redapple.vo.VoRoom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-dao.xml", "classpath:spring-service.xml",
        "classpath:spring-config-annotation.xml" })
@Transactional
public class RoomlbData {
    @Autowired
    private RoomlbService roomlbServiceImpl;
    @Test
    public void getListRoomsService(){
        List<Roomlb> roomlbs = roomlbServiceImpl.getAllRoomlb();
        System.out.println(roomlbs);
    }

    @Test
    public void mybatisGetAllRoom(){
        Roomlb roomlb = new Roomlb();
        roomlb.setRoomlbno("05");
        roomlb.setRoomlbname("测释房");
        String roomlbJson = JSON.toJSONString(roomlb);
        System.out.println(roomlbJson);
        ResultDto<Integer> resultDto = roomlbServiceImpl.insRoomlb(roomlbJson);
        System.out.println(resultDto);
    }
    @Test
    public void mybatisGetAllRoom2(){
        Roomlb roomlb = new Roomlb();
        roomlb.setRoomlbno("05");
        roomlb.setRoomlbname("测试房123123");
        String roomlbJson = JSON.toJSONString(roomlb);
        System.out.println(roomlbJson);
        ResultDto<Integer> resultDto = roomlbServiceImpl.updRoomlbForRoomlbno(roomlbJson);
        System.out.println(resultDto);
    }


}
