package com.czly.base;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations={"/spring/applicationContext-datasource.xml","/spring/applicationContext-tx.xml",
									"/config/applicationContext-property.xml"}) 
public abstract class BaseSpringContextTest{
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
	@Before
	public void before(){
//		// create a SqlSessionFactory
//		SqlSession session = sqlSessionFactory.openSession();
//		Connection conn = session.getConnection();
//		Reader reader = null;
//		try {
//			reader = Resources.getResourceAsReader("config/hqldb.sql");
//			ScriptRunner runner = new ScriptRunner(conn);
//			runner.setLogWriter(null);
//			runner.runScript(reader);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			if (reader != null){
//				try {
//					reader.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//			session.close();
//		}
	}
}
