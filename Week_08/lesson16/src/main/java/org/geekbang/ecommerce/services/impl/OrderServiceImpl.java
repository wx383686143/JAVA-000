package org.geekbang.ecommerce.services.impl;

import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.geekbang.ecommerce.dao.IOrderDao;
import org.geekbang.ecommerce.services.IOrderService;
import org.geekbang.ecommerce.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderDao orderDao;

    @Override
    public OrderVO findOrderById(Long orderId) {
        return orderDao.findOrderById(orderId);
    }

    @Override
    public List<OrderVO> findOrderList(OrderVO orderVO) {
        return orderDao.findOrderList(orderVO);
    }

    @Override
    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    public void add(OrderVO orderVO) {
        orderDao.add(orderVO);
    }

    @Override
    public void batchAdd(List<OrderVO> list) {
        orderDao.batchAdd(list);
    }

    @Override
    public void createTable() {
        orderDao.createTable();
    }

    @Override
    public void deleteTable() {
        orderDao.deleteTable();
    }
}
