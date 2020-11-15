package com.springfx.annotation;

import com.springfx.vo.Student;
import com.springfx.xml.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    public void add(Student student) {
        studentDao.add(student);
    }
}
