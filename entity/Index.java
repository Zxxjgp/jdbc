package com.example.jdbc.entity;

import java.io.Serializable;

/**
 * @ProjectName: jdbc
 * @Package: com.example.jdbc.entity
 * @ClassName: Index
 * @Description: java类作用描述
 * @Author: 焦关平
 * @CreateDate: 2018/10/17 16:14
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/10/17 16:14
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */

public class Index implements Serializable {
    private Integer id;

    private String name;

    public Index(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Index() {
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
