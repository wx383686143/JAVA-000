package org.geekbang.ecommerce.dao.impl;

import org.geekbang.ecommerce.common.JdbcUtils;
import org.geekbang.ecommerce.dao.IOrderDao;
import org.geekbang.ecommerce.vo.OrderVO;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDaoImpl implements IOrderDao {

    private Connection conn;
    private PreparedStatement statement;
    private ResultSet rs;

    private DataSource dataSource;

    @Override
    public OrderVO findOrderById(Long orderId) {
        OrderVO orderVO = new OrderVO();
        try {
            String sql = "select pk,order_no,count,price,status,created_time,update_time from orders o where o.pk = ?";
            dataSource = JdbcUtils.getDateSource();
            conn = dataSource.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setLong(1, orderId);
            rs = statement.executeQuery();
            while (rs.next()) {
                orderVO.setPk(rs.getLong(1));
                orderVO.setOrderNo(rs.getString(2));
                orderVO.setCount(rs.getBigDecimal(3));
                orderVO.setPrice(rs.getBigDecimal(4));
                orderVO.setStatus(rs.getInt(5));
                orderVO.setCreatedTime(rs.getDate(6));
                orderVO.setUpdateTime(rs.getDate(7));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(conn, statement, rs);
        }
        return orderVO;
    }

    @Override
    public List<OrderVO> findOrderList(OrderVO orderVO) {
        List<OrderVO> list = new ArrayList<>();
        try {
            dataSource = JdbcUtils.getDateSource();
            String sql = "select pk,order_no,count,price,status,created_time,update_time from orders o where o.pk = ?";
            conn = dataSource.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setLong(1, orderVO.getPk());
            rs = statement.executeQuery();
            OrderVO order;
            while (rs.next()) {
                order = new OrderVO();
                order.setPk(rs.getLong(1));
                order.setOrderNo(rs.getString(2));
                order.setCount(rs.getBigDecimal(3));
                order.setPrice(rs.getBigDecimal(4));
                order.setStatus(rs.getInt(5));
                order.setCreatedTime(rs.getDate(6));
                order.setUpdateTime(rs.getDate(7));
                list.add(order);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(conn, statement, rs);
        }
        return list;
    }

    @Override
    public void add(OrderVO orderVO) {
        try {
            dataSource = JdbcUtils.getDateSource();
            conn = dataSource.getConnection();
            String sql = "insert into orders(order_no,count,price,status,created_time,update_time)values(?,?,?,?,sysdate(),sysdate())";
            statement = conn.prepareStatement(sql);
            statement.setString(1, orderVO.getOrderNo());
            statement.setBigDecimal(2, orderVO.getCount());
            statement.setBigDecimal(3, orderVO.getPrice());
            statement.setInt(4, orderVO.getStatus());
            statement.execute();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(conn, statement, rs);
        }
    }
}
