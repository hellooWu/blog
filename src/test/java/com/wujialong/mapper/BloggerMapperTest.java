package com.wujialong.mapper;



import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BloggerMapperTest {
	ApplicationContext applicationContext;
	@Before
	public void setUp() throws Exception {
		applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
	}

	@Test
	public void testGetPasswordByUserName() throws Exception {
		BloggerMapper bm=(BloggerMapper)applicationContext.getBean("bloggerMapper");
		String s=bm.getPasswordByUserName("wujialong");
		System.out.println(s);
	}

}
