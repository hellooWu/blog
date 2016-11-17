package com.wujialong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wujialong.mapper.BloggerMapper;


@Service
public class BloggerService {
	
	@Autowired
	private BloggerMapper bloggerMapper;
	
	public boolean ifMatchUserName(String username,String password) throws Exception{
		String s=bloggerMapper.getPasswordByUserName(username);
		return password.equals(s);
	}
	
	
	
	
}
