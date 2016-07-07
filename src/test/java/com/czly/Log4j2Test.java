package com.czly;


import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import com.czly.common.util.HttpUtil;

public class Log4j2Test {
	
	Logger logger = LogManager.getLogger(Log4j2Test.class);

	@Test
	public void test() {
		
		try {
			//String geturl = "http://tangyidbweb.chinacloudsites.cn/API/API.asmx/Login?LoginName=www1@qq.com&LoginPassword=123456";
			String posturl = "http://tangyidbweb.chinacloudsites.cn/API/API.asmx/Login";
			//System.out.println("GET------"+HttpUtil.get(geturl));
			Map<String,String> params = new HashMap<String,String>();
			params.put("LoginName", "30593234@qq.com");
			params.put("LoginPassword", "cain1005");
			String response = HttpUtil.post(posturl, params);
			System.out.println("POST------"+response);
			//创建SAXReader对象  
	        SAXReader reader = new SAXReader();
	        Document document = reader.read(new ByteArrayInputStream(response.getBytes("UTF-8")));
	        listNodes(document.getRootElement());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void test1(){
		String st = "I have a dream, I want to learn Java, efforts to make money";
		char[] stArray = st.toCharArray();
		for(int i=stArray.length-1;i>0;i--){
			System.out.println(stArray[i]);
		}
	}
	
	 //遍历当前节点下的所有节点  
    public void listNodes(Element node){  
        System.out.println("当前节点的名称：" + node.getName());  
        //如果当前节点内容不为空，则输出  
        if(!(node.getTextTrim().equals(""))){  
             System.out.println( node.getName() + "：" + node.getText());    
        }  
        //同时迭代当前节点下面的所有子节点  
        //使用递归  
        Iterator<Element> iterator = node.elementIterator();  
        while(iterator.hasNext()){  
            Element e = iterator.next();  
            listNodes(e);  
        }  
    }  

}
