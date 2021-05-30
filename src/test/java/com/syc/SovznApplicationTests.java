package com.syc;
import com.syc.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
class SovznApplicationTests {
    @Autowired
    DataSource dataSource;
    UserService userService;
    @Test
    void contextLoads() throws SQLException {
        //测试数据库连接
        System.out.println("++++++++++++++++++++++++++++"+dataSource.getClass());
        System.out.println("++++++++++++++++++++++++++++"+dataSource.getConnection());
        /*List<User1> list=userMapper.selAllUser();
        System.out.println("测试查询"+list);
        System.out.println("测试查询+++++++++"+userService.selUser1Byname("师耀昌"));
        System.out.println("测试查询+++++++++"+adminService.selAdminByname("sovzn"));*/
       String u=new String("2014-1");
       int age=Integer.parseInt(u.substring(0,4));
        System.out.println(age);
    }

}

