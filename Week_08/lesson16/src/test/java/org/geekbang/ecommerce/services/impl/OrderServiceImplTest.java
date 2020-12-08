package org.geekbang.ecommerce.services.impl; 

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

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class OrderServiceImplTest {

    @Autowired
    private IOrderService orderService;

    @Test
    public void testFindOrderById() throws Exception { 
    }
    
    @Test
    public void testFindOrderList() throws Exception { 
    }
    
    @Test
    public void testAdd() throws Exception {
        orderService.add(new OrderVO(9l, "S0000009", new BigDecimal(1), new BigDecimal(2000.00), null, null, 1l, null, null));
    }

    @Test
    public void testBatchAdd() throws Exception { 
    }
    
    @Test
    public void testCreateTable() throws Exception { 
    }
    
    @Test
    public void testDeleteTable() throws Exception { 
    }
    

} 
