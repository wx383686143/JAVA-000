package org.geekbang.ecommerce.services;

import org.geekbang.ecommerce.vo.OrderVO;

import java.util.List;

public interface IOrderService {
    OrderVO findOrderById(Long orderId);

    List<OrderVO> findOrderList(OrderVO orderVO);

    void add(OrderVO orderVO);

    void batchAdd(List<OrderVO> list);

    void createTable();

    void deleteTable();
}
