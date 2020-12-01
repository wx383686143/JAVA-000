package org.geekbang.ecommerce.dao.impl; 

import org.geekbang.ecommerce.Application;
import org.geekbang.ecommerce.dao.IOrderDao;
import org.geekbang.ecommerce.vo.OrderVO;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * OrderDaoImpl Tester.
 *
 * @author <Authors name>
 * @since
 * @version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class OrderDaoImplTest {

    @Autowired
    private IOrderDao orderDao;

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     *
     * Method: findOrderById(Long orderId)
     *
     */
    @Test
    public void testFindOrderById() throws Exception {
        OrderVO orderVO = orderDao.findOrderById(1l);
        System.out.println(orderVO.getOrderNo());
    }

    /**
     *
     * Method: findOrderList(OrderVO orderVO)
     *
     */
    @Test
    public void testFindOrderList() throws Exception {
        OrderVO orderVO = new OrderVO();
        orderVO.setPk(1l);
        List<OrderVO> orderVOS = orderDao.findOrderList(orderVO);
        for (OrderVO vo : orderVOS) {
            System.out.println(vo.getOrderNo());
        }
    }

    @Test
    public void testAdd() throws Exception {
        OrderVO orderVO = new OrderVO();
        orderVO.setOrderNo("S0000005");
        orderVO.setCount(new BigDecimal(2));
        orderVO.setPrice(new BigDecimal(200.00));
        orderVO.setStatus(1);
        orderDao.add(orderVO);
    }
    

} 
