package com.demo.vo;

public class StudentVO {

    private int id;
    private String name;

    public StudentVO() {
    }

    public StudentVO(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
