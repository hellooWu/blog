package com.wujialong.mapper;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BlogTypeMapperTest {
	ApplicationContext applicationContext;
	@Before
	public void setUp() throws Exception {
		applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
	}	

	@Test
	public void testGetAllTypes() {
		BlogTypeMapper bm=(BlogTypeMapper)applicationContext.getBean("blogTypeMapper");
		System.out.println(bm.getAllTypes());
	}

}
