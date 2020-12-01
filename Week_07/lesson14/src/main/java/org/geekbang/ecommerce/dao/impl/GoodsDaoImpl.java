package org.geekbang.ecommerce.dao.impl;

import org.geekbang.ecommerce.dao.IGoodsDao;
import org.geekbang.ecommerce.common.JdbcUtils;
import org.geekbang.ecommerce.vo.GoodsVO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class GoodsDaoImpl implements IGoodsDao {

    private Connection conn;
    private PreparedStatement statement;
    private ResultSet rs;

    @Resource(name = "slaveDataSource")
    private DataSource slaveDataSource;

    @Override
    public void add(GoodsVO goodsVO) {
        try {
            conn = slaveDataSource.getConnection();
            String sql = "insert into goods(goods_no,goods_name,goods_type,quantity,price,created_time,update_time)values(?,?,?,?,?,sysdate(),sysdate())";
            statement = conn.prepareStatement(sql);
            statement.setString(1, goodsVO.getGoodsNo());
            statement.setString(2, goodsVO.getGoodsName());
            statement.setString(3, goodsVO.getGoodsType());
            statement.setInt(4, goodsVO.getQuantity());
            statement.setBigDecimal(5, goodsVO.getPrice());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(conn, statement, rs);
        }
    }

}
