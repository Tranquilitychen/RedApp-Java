import com.kalic.redapple.mapper.SalesMapper;
import com.kalic.redapple.pojo.Salesman;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Kalic Li
 * @ClassName Login
 * @Package PACKAGE_NAME
 * @Description TODO
 * @date 2020/2/29 14:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-dao.xml", "classpath:spring-service.xml",
        "classpath:spring-config-annotation.xml"})
public class Login {
    @Autowired
    private SalesMapper salesMapper;

    @Test
    public void selSales(){
        Salesman salesman = salesMapper.selcheckSales("admin", "123123");
        System.out.println(salesman);
        Salesman salesman1 = salesMapper.selcheckSales("sales003", "123123");
        System.out.println(salesman1);
        Salesman salesman2 = salesMapper.selcheckSales("sales003", "123123122312");
        System.out.println(salesman2);
        System.out.println(salesman != null);
        System.out.println(salesman1 != null);
    }

    @Test
    public void testLamabe(){
        List<String> stringList = new ArrayList<>();
        stringList.add("xixi");
        stringList.add("haha");
        stringList.add("kaka");
        stringList.add("xixi");
        stringList.add("haha");
        List<String> collectString = stringList.stream()
                .filter(str -> str == "meimei")
                .collect(Collectors.toList());
        System.out.println(collectString);

    }
}
