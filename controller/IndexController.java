package com.example.jdbc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.*;

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

    @Resource
    private DataSource dataSource;

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

    /**
     *
     * @return
     */
    @RequestMapping("update")
    public String up(){
        final String sql = "insert into temp ( id, name) values (? , ?)";

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1,"23");
                ps.setString(2,"name");
                return ps;
            }
        });
        return "1111111111111";
    }

    /**
     * 获取主键自增
     */
    @RequestMapping("updateKeys/{id}")
    public String updateKeys(@PathVariable String id) throws SQLException {

        final String sql = "insert into temp ( id, name) values ( "+id +", '我是中国人')";
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate(sql,Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = statement.getGeneratedKeys();
        if ( rs.next()){
            int anInt = rs.getInt("1");
            System.out.println(anInt);
        }
        return "我获取成功了";
    }

    /**
     * 测试
     * @return
     */
    @RequestMapping("ue/{str}")
    public String ups(@PathVariable String str){

        KeyHolder keyHolde = new GeneratedKeyHolder(); //创建一个主键的持有者

        final String sql = "insert into temp ( id, name) values (? , ?)";

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                PreparedStatement ps = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,str);
                ps.setString(2,"name");
                return ps;
            }
        },keyHolde);

        System.out.println(keyHolde.getKey());
        return "1111111111111";
    }


}
