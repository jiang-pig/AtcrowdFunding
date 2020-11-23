package com.atguigu.crowd.test;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.util.CrowdUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml","classpath:spring-persist-tx.xml"})
public class CrowdTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
    @Test
    public void testTx(){

        Admin admin = new Admin(null, "gkx", "123", "段利将", "2573213555@qq.com", null);
        adminService.saveAdmin(admin);

        System.out.println(CrowdUtil.md5("123"));

    }
    @Test
    public void testLogger(){

        //获取log对象
        Logger logger = LoggerFactory.getLogger(CrowdTest.class);

        //根据不同的日志级别来打印日志
        logger.debug("debug level");
        logger.info("info level");
        logger.warn("warn level");
        logger.error("error level");
    }
    @Test
    public void testInsertAdmin(){
        Admin admin = new Admin(null,"hhh","123456","哈哈哈","123456789@qq.com",null);
        int count = adminMapper.insertSelective(admin);
        System.out.println("受影响的行数：" + count);
    }
    @Test
    public void test(){
        for (int i = 107; i < 206; i++) {
            adminMapper.deleteByPrimaryKey(i++);
        }
    }

    @Test
    public void test01(){
        System.out.println("测试");
    }



}
