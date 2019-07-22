package com.spring.website;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.dao.UserDAO;
import com.spring.vo.UserVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/*.xml" })
public class DataSourceTest {
	/*
	 * @Inject private DataSource ds;
	 * 
	 * @Test public void testConnection() throws Exception { try (Connection conn =
	 * (Connection) ds.getConnection()) { System.out.println(conn);
	 * 
	 * } catch (Exception e) { System.out.println("안돔"); e.printStackTrace(); } }
	 * 
	 */

	@Inject
	private UserDAO dao;

	@Test
	public void testTime() throws Exception {
		System.out.println("완료");
		System.out.println(((UserDAO) dao).getTime());	
	}

	@Test
	public void testInsertUser() throws Exception {
		UserVO uservo = new UserVO();
		uservo.setId("xx");
		uservo.setPw("xx");
		dao.insertUser(uservo);
	}

}
