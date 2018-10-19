package com.example.jdbc.controller;

import com.example.jdbc.entity.Index;
import org.springframework.jdbc.core.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: jdbc
 * @Package: com.example.jdbc.controller
 * @ClassName: QuerryController
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/10/17 17:51
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/10/17 17:51
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@RestController
@RequestMapping("query")
public class QuerryController {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("list")
    public String list(){

        return null;
    }

    @RequestMapping("dange/{id}")
    public Index dange(@PathVariable String id){
        String sql = "select id, name from temp where name = ? ";
        Index index = new Index();

        jdbcTemplate.query(sql, new Object[] { id } ,new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                index.setId(Integer.valueOf(id));
                index.setName(resultSet.getString("name"));
            }
        });

        return index;
    }

    @RequestMapping("count")
    public Integer count(){
        String sql  = "select count(*) from temp";
        Integer integer = jdbcTemplate.queryForObject(sql, Integer.class);

        return integer;

    }

    /**
     * 第一张处理list集合（RowMapper）
     * @param name
     * @return
     */
    @RequestMapping("listIndex/{name}")
    public List<Index> index(@PathVariable String name){

        String sql = "select id, name from temp where name = ? ";

       return jdbcTemplate.query(sql, new Object[]{name}, new RowMapper<Index>() {
            @Override
            public Index mapRow(ResultSet rrs, int i) throws SQLException {
                Index index = new Index();
                index.setId(rrs.getInt("id"));
                index.setName(rrs.getString("name"));
                return index;
            }
        });
    }

    /**
     * 处理list集合（RowCallbackHandler）
     * @param id
     * @return
     */
    @RequestMapping("danges/{id}")
    public List<Index> danges(@PathVariable String id){
        String sql = "select id, name from temp where name = ? ";
        List<Index> list = new ArrayList<>(16);

        jdbcTemplate.query(sql, new Object[] { id } ,new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                Index index = new Index();
                index.setId(Integer.valueOf(id));
                index.setName(resultSet.getString("name"));
                list.add(index);
            }
        });

        return list;
    }

    /**
     * 更新一条记录
     */
    @RequestMapping("indexUpdate")
    public String indexUpdate(){
        String sql = "update temp set name = '我是性感美女' where name = ? ";

        final int update = jdbcTemplate.update(sql, "255");

        final int updates = jdbcTemplate.update(sql,new Object[]{"我是性感美女1"});

        return "我更新成功了";
    }

    //删除
    public  void delete(int id) {
        String sql = "delete from temp where id = ?";
        Object args[] = new Object[]{id};
        int temp = jdbcTemplate.update(sql,args);
        if (temp > 0) {
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }


    /**
     * 批量更新
     */

    /**
     * 查询单个数据
     */
    @RequestMapping("getIndex")
    public  Index getIndex(){
        String sql = "select  * from temp where id = 633 ";



        Index index = jdbcTemplate.queryForObject(sql, new RowMapper<Index>() {


            @Override
            public Index mapRow(ResultSet resultSet, int i) throws SQLException {
                Index index = new Index();
                index.setId(resultSet.getInt("id"));
                index.setName(resultSet.getString("name"));

                return index;
            }
        });
        return index;


    }

    @RequestMapping("one")
    public Index one(){
        String sql = "select  * from temp where id = ? ";
        String sql2 = "select  * from temp where id = 633 ";
        jdbcTemplate.queryForObject( sql2 , new BeanPropertyRowMapper<Index>(Index.class));

        Index index = jdbcTemplate.queryForObject(sql,new Object[]{633} ,new BeanPropertyRowMapper<Index>(Index.class));
        Index indexc = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Index>(Index.class));
        return  index;
    }
}
