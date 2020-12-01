package org.geekbang.ecommerce.dao;

import org.geekbang.ecommerce.vo.UserVO;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserDaoImpl implements IUserDao {

    private PreparedStatement statement;
    private ResultSet rs;

    @Override
    public void batchAdd(List<UserVO> list) {
        Connection con = JdbcUtils.getCon();
        try {
            con.setAutoCommit(false);
            String sql = "insert into users(user_name,real_name,sex,age,phone,address,created_time,update_time)values(?,?,?,?,?,?,sysdate(),sysdate())";
            statement = con.prepareStatement(sql);
            for(int i=1; i<=list.size(); i++) {
                statement.setString(1, list.get(i-1).getUserName());
                statement.setString(2, list.get(i-1).getRealName());
                statement.setString(3, list.get(i-1).getSex());
                statement.setInt(4, list.get(i-1).getAge());
                statement.setLong(5, list.get(i-1).getPhone());
                statement.setString(6, list.get(i-1).getAddress());
                statement.addBatch();
            }
            statement.executeBatch();
            con.commit();
        }catch (SQLException e) {
            e.printStackTrace();
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }finally {
            JdbcUtils.close(con, statement, null);
        }
    }
}
