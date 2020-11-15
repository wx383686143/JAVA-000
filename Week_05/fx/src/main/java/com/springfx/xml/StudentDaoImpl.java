package com.springfx.xml;

import com.springfx.vo.Student;

public class StudentDaoImpl implements StudentDao {

    public void add(Student student) {
        System.out.println("student:" + student.getId() + student.getName());
    }

}
