package com.example.jdbc.controller;

import com.example.jdbc.entity.Index;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
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
}
