package com.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Unknown {

    private Integer id;
    private String name;
    private Integer year;
    private String color;
    private String pantone_value;

    public Unknown(Integer id, String name, Integer year, String color, String pantone_value) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.color = color;
        this.pantone_value = pantone_value;
    }

    public Unknown() {
    }
}
