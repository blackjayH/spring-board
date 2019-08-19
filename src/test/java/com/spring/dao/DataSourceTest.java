package com.spring.dao;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.vo.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class DataSourceTest {

	
	@Test
	public void testTime() throws Exception {
		//System.out.println(dao.getTime());
		System.out.println("성공");
	}

	@Test
	public void testInsertUser() throws Exception {
		UserVO uservo = new UserVO();
		uservo.setId("aaa");
		uservo.setPw("111");
		//dao.insertUser(uservo);
	}

}
