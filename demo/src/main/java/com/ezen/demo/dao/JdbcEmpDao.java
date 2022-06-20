package com.ezen.demo.dao;

import java.sql.PreparedStatement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ezen.demo.model.Emp;

@Repository
public class JdbcEmpDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Emp> getListAll() {
		String sql = "SELECT * FROM emp2";
		List<Emp> list = jdbcTemplate.query(sql, (rs, i)-> {
			Emp emp = new Emp();
			emp.setEmpno(rs.getInt("EMPNO"));
			emp.setEname(rs.getString("ENAME"));
			emp.setJob(rs.getString("JOB"));
			emp.setMgr(rs.getInt("MGR"));
			emp.setHiredate(rs.getDate("HIREDATE"));
			emp.setSal(rs.getFloat("SAL"));
			emp.setComm(rs.getInt("COMM"));
			emp.setDeptno(rs.getInt("deptno"));
			return emp;
		});
		return list;
	}
	
	public List<Emp> getListByDeptNo(int deptno) {
		String sql = "SELECT * FROM emp2 WHERE deptno=?";
		List<Emp> list = jdbcTemplate.query(sql, (rs, i)-> {
			Emp emp = new Emp();
			emp.setEmpno(rs.getInt("EMPNO"));
			emp.setEname(rs.getString("ENAME"));
			emp.setJob(rs.getString("JOB"));
			emp.setMgr(rs.getInt("MGR"));
			emp.setHiredate(rs.getDate("HIREDATE"));
			emp.setSal(rs.getFloat("SAL"));
			emp.setComm(rs.getInt("COMM"));
			emp.setDeptno(rs.getInt("deptno"));
			return emp;
		}, deptno);
		return list;
	}
	
	public Emp getEmpById(int empno) {
		String sql = "SELECT * FROM emp2 WHERE empno=?";
		List<Emp> list = jdbcTemplate.query(sql, (rs, i)-> {
			Emp emp = new Emp();
			emp.setEmpno(rs.getInt("EMPNO"));
			emp.setEname(rs.getString("ENAME"));
			emp.setJob(rs.getString("JOB"));
			emp.setMgr(rs.getInt("MGR"));
			emp.setHiredate(rs.getDate("HIREDATE"));
			emp.setSal(rs.getFloat("SAL"));
			emp.setComm(rs.getInt("COMM"));
			emp.setDeptno(rs.getInt("deptno"));
			return emp;
		}, empno);
		return list.get(0);
	}
	
	public boolean add(Emp emp) {
		String sql = "INSERT INTO emp2(empno, ename, hiredate, sal) VALUES(?, ?, ?, ?)";
		int n = jdbcTemplate.update(sql,
				emp.getEmpno(), emp.getEname(), emp.getHiredate(), emp.getSal());
		return n>0;
	}
	
	public int addAndGetKey(Emp emp) {
		String sql = "INSERT INTO emp2(empno, ename, hiredate, sal) VALUES(?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		jdbcTemplate.update(conn->{
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(sql, new String[] {"empno"}); // PK 컬럼
			pstmt.setInt(1, emp.getEmpno());
			pstmt.setString(2, emp.getEname());
			pstmt.setDate(3, emp.getHiredate());
			pstmt.setFloat(4, emp.getSal());
			return pstmt;
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
//	public boolean update(Emp emp) {
//		String sql = "UPDATE emp2 SET deptno=?, sal=? WHERE empno=?";
//		int n = jdbcTemplate.update(sql, emp.getDeptno(), emp.getSal(), emp.getEmpno());
//		return n>0;
//	}
	
	public boolean update(Emp emp) {
		String sql = "UPDATE emp2 SET deptno=?, sal=? WHERE empno=?";
		int n = jdbcTemplate.update(sql, emp.getDeptno(), emp.getSal(), emp.getEmpno());
		return n>0;
	}
	
	public boolean delete(int empno) {
		String sql = "DELETE FROM emp2 WHERE empno=?";
		int n = jdbcTemplate.update(sql, empno);
		return n>0;
	}
}
