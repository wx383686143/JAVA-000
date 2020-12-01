package org.geekbang.ecommerce.dao.impl;

import org.geekbang.ecommerce.Application;
import org.geekbang.ecommerce.dao.IUserDao;
import org.geekbang.ecommerce.vo.UserVO;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/** 
 * UserDaoImpl Tester. 
 * 
 * @author <Authors name> 
 * @since
 * @version 1.0 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserDaoImplTest {

    @Autowired
    private IUserDao userDao;

    @Before
    public void before() throws Exception { 
    } 
    
    @After
    public void after() throws Exception { 
    } 

    /** 
     * 
     * Method: batchAdd(List<UserVO> list) 
     * 
     */ 
    @Test
    public void testBatchAdd() throws Exception { 
        List<UserVO> list = new ArrayList<>();
        UserVO userVO = new UserVO();
        userVO.setUserName("sansan");
        userVO.setRealName("张三");
        userVO.setAge(25);
        userVO.setPhone(17795737381l);
        userVO.setSex("1");
        userVO.setAddress("西安市");
        list.add(userVO);
        userDao.batchAdd(list);
    }
    

} 
