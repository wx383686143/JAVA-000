package com.demo;

import com.demo.dao.StudentDao;
import com.demo.starter.clazz.Klass;
import com.demo.starter.clazz.School;
import com.demo.starter.clazz.Student;
import com.demo.vo.StudentVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTests {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private Student student;

    @Autowired
    private Klass klass;

    @Autowired
    private School school;

    @Test
    public void testAdd() {
        StudentVO studentVO = new StudentVO();
        studentVO.setId(1);
        studentVO.setName("test");
        studentDao.add(studentVO);
    }

    @Test
    public void testModify() {
        StudentVO studentVO = new StudentVO();
        studentVO.setId(1);
        studentVO.setName("test1");
        studentDao.modify(studentVO);
    }

    @Test
    public void testDelete() {
        studentDao.delete(1);
    }

    @Test
    public void testGet() {
        StudentVO studentVO = studentDao.get(1);
        System.out.println("studentVO.name" + studentVO.getName());
    }

    @Test
    public void testGetList() {
        List<StudentVO> list = studentDao.getList();
        System.out.println("studentVO.name" + list.get(0).getName());
    }

    @Test
    public void testBatchAdd() {
        List<StudentVO> list = new ArrayList<>();
        list.add(new StudentVO("aaaa"));
        list.add(new StudentVO("bbbb"));
        list.add(new StudentVO("cccc"));
        list.add(new StudentVO("dddd"));
        studentDao.batchAdd(list);
    }

    @Test
    public void testAddX() {
        StudentVO studentVO = new StudentVO();
        studentVO.setName("test123");
        studentDao.addX(studentVO);
    }

    @Test
    public void testStarter() {
        student.init();

        klass.dong();

        school.ding();

    }
}
