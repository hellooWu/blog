package com.wujialong.mapper;


import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.wujialong.pojo.Blog;

public class BlogMapperTest {
	ApplicationContext applicationContext;
	@Before
	public void setUp() throws Exception {
		applicationContext=new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
	}

	@Test
	public void testGetManagerInformationByType() throws Exception {
		BlogMapper bm=(BlogMapper)applicationContext.getBean("blogMapper");
		HashMap<String, Comparable> map=new HashMap<String, Comparable>();
		map.put("formerBlogID", 0);
		map.put("type", "测试");
		List<Blog> list=bm.getManageInformationByType(map);
		System.out.println(list.get(0).getType());
	}

}
