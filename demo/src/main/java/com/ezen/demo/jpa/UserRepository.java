package com.ezen.demo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	//JPA는 메소드 이름을 이용하여 SQL을 파생한다(Query Method)
	// User 클래스의 uname 속성을 이용하여 이름으로 검색되도록 메소드 작성
	List<User> findByUname(String uname); // select * from user_tb where name=uname;
	List<User> findByUnameAndEmail(String uname, String email);

	// num값이 5~10 사이에 있는 행을 추출하려고 한다
	@Query("SELECT u FROM User u WHERE u.num BETWEEN ?1 AND ?2")	// JPQL
	List<User> findAllNumBetJPQL(int start, int end);
	
	@Query("SELECT u FROM User u WHERE u.num BETWEEN :start AND :end")	// JPQL 이름 지정
	List<User> findAllNumBetJPQL2(@Param("start") int start, @Param("end") int end);
	
	@Query(value="SELECT * FROM user_tb u WHERE u.num BETWEEN ?1 AND ?2", nativeQuery = true)	// SQL
	List<User> findAllNumBetSQL(int start, int end);
}
