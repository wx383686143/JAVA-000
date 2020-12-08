package org.geekbang.ecommerce.dao.impl; 

import org.geekbang.ecommerce.Application;
import org.geekbang.ecommerce.services.IOrderService;
import org.geekbang.ecommerce.vo.OrderVO;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    private IOrderService orderService;

    @Test
    public void testFindOrderById() throws Exception {
        OrderVO orderVO = orderService.findOrderById(1l);
        System.out.println(orderVO.getOrderNo());
    }
    
    @Test
    public void testFindOrderList() throws Exception { 
    }
    
    @Test
    public void testAdd() throws Exception {
        OrderVO orderVO = new OrderVO();
        orderVO.setPk(2l);
        orderVO.setUserFK(2l);
        orderVO.setOrderNo("S000000001");
        orderVO.setCount(new BigDecimal(1));
        orderVO.setPrice(new BigDecimal(200.00));
        orderService.add(orderVO);
    }

    @Test
    public void testBatchAdd() throws Exception {
        List<OrderVO> list = new ArrayList<>();
        list.add(new OrderVO(1l, "S0000001", new BigDecimal(1), new BigDecimal(2000.00), null, null, 1l, null, null));
        list.add(new OrderVO(2l, "S0000002", new BigDecimal(1), new BigDecimal(2000.00), null, null, 2l, null, null));
        list.add(new OrderVO(3l, "S0000003", new BigDecimal(1), new BigDecimal(2000.00), null, null, 2l, null, null));
        list.add(new OrderVO(4l, "S0000004", new BigDecimal(1), new BigDecimal(2000.00), null, null, 3l, null, null));
        list.add(new OrderVO(5l, "S0000005", new BigDecimal(1), new BigDecimal(2000.00), null, null, 3l, null, null));
        list.add(new OrderVO(6l, "S0000006", new BigDecimal(1), new BigDecimal(2000.00), null, null, 4l, null, null));
        list.add(new OrderVO(7l, "S0000007", new BigDecimal(1), new BigDecimal(2000.00), null, null, 4l, null, null));
        list.add(new OrderVO(8l, "S0000008", new BigDecimal(1), new BigDecimal(2000.00), null, null, 5l, null, null));
        orderService.batchAdd(list);
    }

    @Test
    public void testCreateTable() throws Exception {
        orderService.createTable();
    }

    @Test
    public void testDeleteTable() throws Exception {
        orderService.deleteTable();
    }

} 
