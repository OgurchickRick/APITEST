package com.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class User {


    public String name;
    public String job;


    public User(String name, String job) {
            this.name = name;
            this.job = job;
        }

        public User() {
        }
}
