package org.geekbang.ecommerce.dao.impl;

import org.aspectj.weaver.ast.Or;
import org.geekbang.ecommerce.dao.IOrderDao;
import org.geekbang.ecommerce.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDaoImpl implements IOrderDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public OrderVO findOrderById(Long orderId) {
        String sql = "select pk,order_no,count,price,status,created_time,update_time from order_t o where o.pk = ?";
        OrderVO orderVO = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(OrderVO.class), orderId);
        return orderVO;
    }

    @Override
    public List<OrderVO> findOrderList(OrderVO orderVO) {
        String sql = "select pk,order_no,count,price,status,created_time,update_time from order_t";
        List<OrderVO> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OrderVO.class));
        return list;
    }

    @Override
    public void add(OrderVO orderVO) {
        String sql = "insert into order_t(pk,order_no,count,price,user_fk,status,created_time,update_time)values(?,?,?,?,?,1,now(),now())";
        jdbcTemplate.update(sql, new Object[]{orderVO.getPk(), orderVO.getOrderNo(), orderVO.getCount(), orderVO.getPrice(), orderVO.getUserFK()});
    }

    @Override
    public void batchAdd(List<OrderVO> list) {
        String sql = "insert into order_t(pk,order_no,count,price,user_fk,status,created_time,update_time)values(?,?,?,?,?,1,now(),now())";
        List<Object[]> objects = new ArrayList<>();
        Object[] object;
        for(OrderVO orderVO: list) {
            object = new Object[]{orderVO.getPk(), orderVO.getOrderNo(), orderVO.getCount(), orderVO.getPrice(), orderVO.getUserFK()};
            objects.add(object);
        }
        jdbcTemplate.batchUpdate(sql, objects);
    }

    @Override
    public void createTable() {
        String sql = "CREATE TABLE `order_t` (\n" +
                "  `pk` bigint(20) DEFAULT NULL,\n" +
                "  `order_no` varchar(50) DEFAULT NULL,\n" +
                "  `count` int(11) DEFAULT NULL,\n" +
                "  `price` decimal(10,0) DEFAULT NULL,\n" +
                "  `status` tinyint(4) DEFAULT NULL,\n" +
                "  `goods_fk` bigint(20) DEFAULT NULL,\n" +
                "  `user_fk` bigint(20) DEFAULT NULL,\n" +
                "  `created_time` datetime DEFAULT NULL,\n" +
                "  `update_time` datetime DEFAULT NULL\n" +
                ")";
        jdbcTemplate.execute(sql);
    }

    @Override
    public void deleteTable() {
        String sql = "drop table order_t";
        jdbcTemplate.execute(sql);
    }
}
