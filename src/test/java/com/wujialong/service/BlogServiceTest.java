package com.wujialong.service;


import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wujialong.mapper.BlogMapper;
import com.wujialong.pojo.Blog;

public class BlogServiceTest {

	ApplicationContext applicationContext;
	@Before
	public void setUp() throws Exception {
		applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
	}

	@Test
	public void testGetPasswordByUserName() throws Exception {
		BlogMapper bm=(BlogMapper)applicationContext.getBean("blogMapper");
		List<Blog> list=bm.getLatestBlog();
		System.out.println(list);
	}

}
