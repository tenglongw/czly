package com.czly;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.alibaba.druid.pool.DruidDataSource;

public class Test {
	public static final String url = "jdbc:mysql://localhost:3306/czly";
    public static final String user = "root";
    public static final String password = "root";
    
    public static void main(String[] args) throws SQLException, InterruptedException {
	DruidDataSource src = initDruid();
	Connection conn = src.getConnection();
	ResultSet rs = query(conn, "SELECT * FROM t_user");
	while (rs.next()) {  
	    String uid = rs.getString(1);  
            String ufname = rs.getString(2);  
            System.out.println(uid + "\t" + ufname + "\t");  
        }
        rs.close();
        conn.close();
    	
    }
    
    public static ResultSet query(Connection conn, String sql) {
	ResultSet result = null;
	try {
	    result = conn.prepareStatement(sql).executeQuery();
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return result;
    }
    
    public static DruidDataSource initDruid() {
	DruidDataSource result = new DruidDataSource();
	result.setDriverClassName("com.mysql.jdbc.Driver");
	result.setUsername(user);
	result.setPassword(password);
	result.setUrl(url);
	
	result.setInitialSize(1);
	result.setMinIdle(1);
	result.setMaxActive(2);
	result.setTestWhileIdle(false);
	// 启用监控统计功能
//	try {
//	    result.setFilters("stat");// for mysql
//	} catch (SQLException e) {
//	    e.printStackTrace();
//	}
	result.setPoolPreparedStatements(false);
	return result;
    }
}
