package com.demo.dao.impl;

import com.demo.dao.StudentDao;
import com.demo.vo.StudentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDaoImpl implements StudentDao {

    private PreparedStatement statement;
    private ResultSet rs;

    @Autowired
    private DataSource dataSource;

    public void add(StudentVO studentVO) {
        Connection con = JdbcUtils.getCon();
        try {
            String sql = "insert into student(name)values(?)";
            statement = con.prepareStatement(sql);
            statement.setString(1, studentVO.getName());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(con, statement, null);
        }
    }

    public void addX(StudentVO studentVO) {
        try {
            Connection con = dataSource.getConnection();
            String sql = "insert into student(name)values(?)";
            statement = con.prepareStatement(sql);
            statement.setString(1, studentVO.getName());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(null, statement, null);
        }
    }

    public void modify(StudentVO studentVO) {
        Connection con = JdbcUtils.getCon();
        try {
            String sql = "update student set name=? where id=?";
            statement = con.prepareStatement(sql);
            statement.setString(1, studentVO.getName());
            statement.setInt(2, studentVO.getId());
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(con, statement, null);
        }
    }

    public void delete(int id) {
        Connection con = JdbcUtils.getCon();
        try {
            String sql = "delete from student where id=?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(con, statement, null);
        }
    }

    public StudentVO get(int id) {
        Connection con = JdbcUtils.getCon();
        StudentVO studentVO = new StudentVO();
        try {
            String sql = "select id, name from student where id=?";
            statement = con.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();

            while (rs.next()) {
                studentVO.setId(rs.getInt(1));
                studentVO.setName(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(con, statement, rs);
        }
        return studentVO;
    }

    public List<StudentVO> getList() {
        Connection con = JdbcUtils.getCon();
        List<StudentVO> list = new ArrayList<>();
        StudentVO studentVO = null;
        try {
            String sql = "select id, name from student";
            statement = con.prepareStatement(sql);
            rs = statement.executeQuery();
            while (rs.next()) {
                studentVO = new StudentVO();
                studentVO.setId(rs.getInt(1));
                studentVO.setName(rs.getString(2));
                list.add(studentVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtils.close(con, statement, rs);
        }
        return list;
    }

    public void batchAdd(List<StudentVO> list) {
        Connection con = JdbcUtils.getCon();
        try {
            con.setAutoCommit(false);
            String sql = "insert into student(name)values(?)";
            statement = con.prepareStatement(sql);
            for(int i=1; i<=list.size(); i++) {
                statement.setString(1, list.get(i-1).getName());
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
