package org.geekbang.ecommerce.dao.impl;

import org.geekbang.ecommerce.common.DynamicDataSource;
import org.geekbang.ecommerce.dao.IGoodsDao;
import org.geekbang.ecommerce.common.JdbcUtils;
import org.geekbang.ecommerce.vo.GoodsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class GoodsDaoImpl implements IGoodsDao {

    private Connection conn;
    private PreparedStatement statement;
    private ResultSet rs;

    @Autowired
    private DynamicDataSource dynamicDataSource;

    @Override
    public void add(GoodsVO goodsVO) {
        try {
            conn = dynamicDataSource.getConnection();
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

    @Override
    public GoodsVO get(Long pk) {
        GoodsVO goodsVO = new GoodsVO();
        try {
            String sql = "select pk,goods_no,goods_name,goods_type,quantity,price,created_time,update_time from goods where pk = ?";
            conn = dynamicDataSource.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setLong(1, pk);
            rs = statement.executeQuery();
            while (rs.next()) {
                goodsVO.setPk(rs.getLong(1));
                goodsVO.setGoodsNo(rs.getString(2));
                goodsVO.setGoodsName(rs.getString(3));
                goodsVO.setGoodsType(rs.getString(4));
                goodsVO.setQuantity(rs.getInt(5));
                goodsVO.setPrice(rs.getBigDecimal(6));
                goodsVO.setCreatedTime(rs.getDate(7));
                goodsVO.setUpdateTime(rs.getDate(8));
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            JdbcUtils.close(conn, statement, rs);
        }
        return goodsVO;
    }

}
