package org.geekbang.ecommerce.dao.impl;

import org.geekbang.ecommerce.Application;
import org.geekbang.ecommerce.dao.IGoodsDao;
import org.geekbang.ecommerce.vo.GoodsVO;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/** 
 * GoodsDaoImpl Tester. 
 * 
 * @author <Authors name> 
 * @since
 * @version 1.0 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class GoodsDaoImplTest {

    @Autowired
    IGoodsDao goodsDao;

    @Before
    public void before() throws Exception { 
    } 
    
    @After
    public void after() throws Exception { 
    } 

    /** 
     * 
     * Method: add(GoodsVO goodsVO) 
     * 
     */ 
    @Test
    public void testAdd() throws Exception {
        GoodsVO goodsVO = new GoodsVO();
        goodsVO.setGoodsNo("G0000001");
        goodsVO.setGoodsName("惠普电脑");
        goodsVO.setGoodsType("电器");
        goodsVO.setQuantity(2);
        goodsVO.setPrice(new BigDecimal(3000.00));
        goodsDao.add(goodsVO);
    }
    

} 
