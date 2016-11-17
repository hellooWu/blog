package com.wujialong.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wujialong.mapper.BlogTypeMapper;

@Service
public class BlogTypeService {
	
	@Autowired
	BlogTypeMapper blogTypeMapper;
	//取得博客类型表中的全部类型(不重复)
	public List<String> getAllTypes(){
		return blogTypeMapper.getAllTypes();
	}
}
