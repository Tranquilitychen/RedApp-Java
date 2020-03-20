import com.alibaba.fastjson.JSON;
import com.kalic.redapple.pojo.Reg;
import com.kalic.redapple.pojo.Room;
import com.kalic.redapple.pojo.Roomlb;
import com.kalic.redapple.service.RegService;
import com.kalic.redapple.service.RoomService;
import com.kalic.redapple.service.RoomlbService;
import com.kalic.redapple.vo.VoRoom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @ClassName RegData
 * @Package PACKAGE_NAME
 * @Description TODO
 * @author Kalic Li
 * @date 2020/2/10 10:18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-dao.xml", "classpath:spring-service.xml",
        "classpath:spring-config-annotation.xml" })
@Transactional
public class RegData {
    @Autowired
    private RegService regServiceImpl;
    @Autowired
    private RoomService roomService;
    @Autowired
    private RoomlbService roomlbServiceImpl;

    @Test
    public void resultReg(){
        System.out.println("==============================");
        // 1. 初始需要的信息
        List<VoRoom> roomList = roomService.getListRooms();
        VoRoom room1 = roomList.get(0);
        VoRoom room2 = roomList.get(6);
        List<Roomlb> roomlbList = roomlbServiceImpl.getAllRoomlb();
        // 2. 对信息进行 增删改查e
        Reg reg = new Reg();
        reg.setRoomno(room1.getRoomno());
        reg.setRoomlbno(room1.getRoomlbno());
        reg.setIsgroup("0");

        // 顾客信息
        reg.setGuestname("张三");
        reg.setGuesttype("");
        reg.setSecrecyflag("1");
        reg.setGuestno(0000000001);

        Reg reg2 = new Reg();
        reg2.setRoomno(room2.getRoomno());
        reg2.setRoomlbno(room2.getRoomlbno());
        reg2.setIsgroup("0");

        // 顾客信息
        reg2.setGuestname("张三");
        reg2.setGuesttype("");
        reg2.setSecrecyflag("1");
        reg2.setGuestno(0000000002);


        // -------------生成 regno----------------------
        String preflag = reg.getIsgroup() == "0" ? "G" : "T";
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String nowDate = dateFormat.format(now);
        // 最后的六位  区分账单
        System.out.println(preflag + nowDate + "000001");
        reg.setRegno(preflag + nowDate + "000001");
        reg2.setRegno(preflag + nowDate + "000002");
        reg.setLinkno(preflag + nowDate + "000001");
        reg2.setLinkno(preflag + nowDate + "000001");
        reg.setSureflag("1");
        reg2.setSureflag("1");

        // ---------------------------------------

/*        System.out.println(regServiceImpl.insRegData(reg));*/
        System.out.println("==============================");
        System.out.println("==========str => List==============");
        List<Reg> regs = JSON.parseArray("json", Reg.class);
        System.out.println(regs);
        System.out.println("========================");
        List<String> regStr = JSON.parseArray("json", String.class);
        System.out.println(regStr);
        System.out.println("=========str => List============");
        List<Reg> regList = JSON.parseArray("json").toJavaList(Reg.class);
        System.out.println(regList);
    }



}
