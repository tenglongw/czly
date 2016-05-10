package com.czly;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import junit.framework.TestCase;

import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;

public class TestWordImport extends TestCase {
	public static final String url = "jdbc:mysql://localhost:3306/weboffice";
    public static final String user = "root";
    public static final String password = "root";
    public static Connection conn;
    
	public static void init() throws Exception {
		DruidDataSource src = initDruid();
		conn = src.getConnection();
	}

	@Test
	public void testExportWord() {
		FileOutputStream fos = null;
        InputStream is = null;
        byte[] Buffer = new byte[4096];
        ResultSet rs = null;
        File file = null;
        
        try {
        	TestWordImport.init();
        	rs = conn.prepareStatement("select DocContent from doc where id=2").executeQuery();
            if (rs.next()) {
                // 把数据库的文件显示到该目录下
                file = new File("E:/test2.docx");
                if (!file.exists()) {
                    file.createNewFile(); // 如果文件不存在，则创建
                }
                fos = new FileOutputStream(file);
                is = rs.getBinaryStream("DocContent");
                int size = 0;
                //从数据库中一段一段的读出数据，-1表示读到了文件末
                while ((size = is.read(Buffer)) != -1) {
                    fos.write(Buffer, 0, size);
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
        } finally {
            // 关闭用到的资源
            try {
				fos.close();
				rs.close();
	            conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
	}
	@Test
    public void testBlobInsert() throws Exception {
        FileInputStream fis = null;
        PreparedStatement ps = null;
        String infile = "E:/test.docx";
        try {
        	TestWordImport.init();
            String sql = "insert into doc (DocContent) values (?)";
            File f = new File(infile);
            fis = new FileInputStream(f);
            ps = conn.prepareStatement(sql);
            ps.setBinaryStream(1,fis,fis.available());  //第二个参数为文件的内容
            int resl = ps.executeUpdate();
            System.out.println(resl);
        } catch (Exception ex) {
        	ex.printStackTrace();
        } finally {
            ps.close();
            fis.close();
            conn.close();
        }
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
//			try {
//			    result.setFilters("stat");// for mysql
//			} catch (SQLException e) {
//			    e.printStackTrace();
//			}
		result.setPoolPreparedStatements(false);
		return result;
	}
	 @Test
	 public void testSub(){
		 String st = "meinv.png";
		 System.out.println(st.substring(st.indexOf(".")));
	 }
}
