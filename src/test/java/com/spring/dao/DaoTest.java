package com.spring.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.vo.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/*.xml" })
public class DaoTest {
	@Inject
	private UserDAO userdao;

	@Test
	public void testTime() throws Exception {
		System.out.println(userdao.getTime());
	}

	@Test
	public void testInsertUser() throws Exception {
		UserVO uservo = new UserVO();
		uservo.setId("aaa");
		uservo.setPw("111");
		userdao.insertUser(uservo);
	}

}
