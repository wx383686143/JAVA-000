package org.geekbang.ecommerce.dao;

import org.aspectj.weaver.ast.Or;
import org.geekbang.ecommerce.vo.OrderVO;

import java.util.List;

public interface IOrderDao {

    OrderVO findOrderById(Long orderId);

    List<OrderVO> findOrderList(OrderVO orderVO);

    void add(OrderVO orderVO);

    void batchAdd(List<OrderVO> list);

    void createTable();

    void deleteTable();
}
