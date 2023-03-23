package com.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
public class User {


    private Integer id;
    private String name;
    private String job;
    private Date createdAt;
    private Date updatedAt;


    public User(String name, String job) {
            this.name = name;
            this.job = job;
        }

    public User(Integer id, String name, String job, Date createdAt, Date updatedAt) {
        this.id = id;
        this.name = name;
        this.job = job;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }


    public User() {
    }


}
