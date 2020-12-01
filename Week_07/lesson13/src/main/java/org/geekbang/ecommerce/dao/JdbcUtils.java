package org.geekbang.ecommerce.dao;

import java.sql.*;

public class JdbcUtils {
    private static String url = "jdbc:mysql://localhost:3306/ecommerce?serverTimezone=UTC";
    private static String userName = "root";
    private static String pwd = "root";

    public static Connection getCon() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, pwd);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void close(Connection con, PreparedStatement ps, ResultSet rs) {
        if(null != rs){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(null != ps) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(null != con) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
