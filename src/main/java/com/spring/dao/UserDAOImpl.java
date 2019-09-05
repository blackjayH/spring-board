package com.spring.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.spring.vo.User;
import com.spring.vo.UserVO;

@Repository
public class UserDAOImpl implements UserDAO {

	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("userdb");
	// 엔티티 매니저

	EntityManager em = emf.createEntityManager();
	// 트랜잭션 획득
	EntityTransaction tx = em.getTransaction();

	@Inject
	private SqlSession sqlSession;

	private static final String namespace = "com.spring.website.userMapper";

	// 00. 시간 입력
	@Override
	public String getTime() {
		return sqlSession.selectOne(namespace + ".getTime");
	}

	// 01. 전체 회원 목록 조회
	@Override
	public List<UserVO> listAll() {
		return sqlSession.selectList(namespace + ".listAll");
	}

	// 02. 회원 가입
	@Override
	public void insertUser(UserVO uservo) {
		sqlSession.insert(namespace + ".insertUser", uservo);
	}

	// 03. 회원 정보 상세 조회
	@Override
	public UserVO viewUser(String id) {
		return sqlSession.selectOne(namespace + ".viewUser", id);
	}

	// 04. 회원 정보 수정
	@Override
	public void updateUser(UserVO uservo) {
		sqlSession.update(namespace + ".updateUser", uservo);
	}

	// 05. 회원 정보 삭제
	@Override
	public void deleteUser(UserVO uservo) {
		sqlSession.delete(namespace + ".deleteUser", uservo);
	}

	// 06. 회원 정보 확인(로그인)
	@Override
	public boolean checkPw(String id, String pw) {
		boolean result = false;
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);
		int count = sqlSession.selectOne(namespace + ".checkPw", map);
		if (count == 1)
			result = true;
		return result;
	}

	// 07. 아이디 중복 체크(회원가입)
	@Override
	public boolean checkId(String id) {
		boolean result = false;
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		int count = sqlSession.selectOne(namespace + ".checkId", map);
		if (count == 1)
			result = true;
		return result;
	}

	// jpa 회원 가입
	@Override
	public void insertjpaUser(User user) {
		try {
			tx.begin();
			em.persist(user);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		emf.close();
	}

	// jpa 회원 정보 상세 조회
	@Override
	public User viewjpaUser(User user) {
		try {
			// tx.begin();
			List<User> users = em.createQuery("SELECT u FROM User u", User.class).getResultList();
			// tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		emf.close();
		return user;
	}

	// jpa 회원 정보 수정
	@Override
	public void updatejpaUser(User user) {
		try {
			// tx.begin();
			em.find(User.class, user.getId()).setPw(user.getPw());
			// tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		emf.close();
	}

	// jpa 회원 정보 삭제
	@Override
	public void deletejpaUser(User user) {
		try {
			// tx.begin();
			em.remove(em.find(User.class, user.getId()));
			// tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		emf.close();
	}
}
