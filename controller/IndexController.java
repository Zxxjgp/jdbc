package com.example.jdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: jdbc
 * @Package: com.example.jdbc.controller
 * @ClassName: IndexController
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/10/17 15:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/10/17 15:55
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("index")
    public String getList(){
        jdbcTemplate.execute("create table temp(id int primary key,name varchar(32))");
        return "22";
    }

    @RequestMapping("insert")
    public String insert(){
        Object[] objects= new Object[]{1,"3"};
        int update = jdbcTemplate.update("insert into temp ( id, name) values (? , ?)", objects);
        if (update == 0){
            return "失败他妈";
        }
        return "成功他老母";
    }
}
