package com.springfx;

import com.springfx.annotation.StudentService;
import com.springfx.javaconfig.StudentBean;
import com.springfx.javaconfig.StudentConfig;
import com.springfx.vo.Student;
import com.springfx.xml.StudentDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ApplicationStart {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        Student student = new Student();
        student.setId(1);
        student.setName("test");
        // 使用xml配置方式
        StudentDao studentDao = (StudentDao) applicationContext.getBean("studentDaoImpl");
        studentDao.add(student);

        // 使用注解方式
        StudentService studentService = (StudentService) applicationContext.getBean("studentServiceImpl");
        studentService.add(student);

        // java config方式
        applicationContext = new AnnotationConfigApplicationContext(StudentConfig.class);
        StudentBean studentBean = (StudentBean) applicationContext.getBean("studentBean");
        studentBean.init();

    }
}
