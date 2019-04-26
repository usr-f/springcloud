package com.qif.cloud.mybatis.old.model;

import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/4/24 0024.
 */
@Repository
public class Girl_info implements Serializable {

    private Integer id;
    private String name;
    private String age;

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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
