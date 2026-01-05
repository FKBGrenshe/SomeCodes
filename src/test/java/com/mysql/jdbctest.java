package com.mysql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @Author: Bingyu Chen
 * @CreateTime: 2025-11-18
 * @Description: 数据库连接测试
 */
@SpringBootTest
public class jdbctest {

    // 数据源
    @Autowired
    DataSource dataSource;

    // 用于访问数据库的组件
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    void printDbInfo() throws SQLException{
        System.out.println("默认数据源为：" + dataSource.getClass());
        System.out.println("数据库连接实例：" + dataSource.getConnection());
        //访问数据库user表，查询user表的数据量
        Integer i = jdbcTemplate.queryForObject("SELECT count(*) from try.`student`", Integer.class);
        System.out.println("user 表中共有" + i + "条数据。");
    }






}
