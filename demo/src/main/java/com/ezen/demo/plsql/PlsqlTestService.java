package com.ezen.demo.plsql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.CallableStatementCreator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import com.ezen.demo.mappers.EmpPLSQLMapper;
import com.ezen.demo.model.Emp;

@Service
public class PlsqlTestService {
	
	@Autowired
	private EmpPLSQLMapper dao;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	private ResultSet rs;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Map<String, Object> getEnameByEmpno(int empno) {
		
		List<SqlParameter> param = Arrays.asList(
				new SqlParameter(Types.INTEGER),
				new SqlOutParameter("ename", Types.VARCHAR)
		);
		
		Map<String, Object> resultMap = jdbcTemplate.call(new CallableStatementCreator() {
			@Override
			public CallableStatement createCallableStatement(Connection con) throws SQLException {
				CallableStatement cs = con.prepareCall("{ call ename_by_empno(?, ?) }");
				cs.setInt(1, empno);
				cs.registerOutParameter(2, Types.VARCHAR);
				return cs;
			}
			
		}, param);
		
		logger.info("service ename={}", resultMap.get("ename"));
		
		return resultMap;
	}
	
	public Map<String, Object> getEnameByEmpno2(int empno) {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("ename_by_empno");
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("p_empno", empno);
		SqlParameterSource in = new MapSqlParameterSource(paramMap);
		
		Map<String, Object> resultMap = simpleJdbcCall.execute(in);
		logger.debug("ename={}", resultMap.get("P_ENAME").toString());
		return resultMap;
	}
	
	/* SimpleJdbcCall을 사용하여 오라클로부터 커서를 받아오는 예 */
	public List<Emp> getEmpByDeptno(int deptno) {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("emp_by_deptno")
				.declareParameters(
						new SqlParameter("p_deptno", Types.INTEGER),
						new SqlOutParameter("refcur", Types.REF_CURSOR)
				)
				.returningResultSet("refcur", BeanPropertyRowMapper.newInstance(Emp.class));
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("p_deptno", deptno);
		SqlParameterSource in = new MapSqlParameterSource(paramMap);
		
		Map<String, Object> resultMap = simpleJdbcCall.execute(in);
//		logger.info("결과맵={}", resultMap.toString());
		List<Emp> list = (List<Emp>) resultMap.get("refcur");
		
		return list;
	};
	
	public List<Emp> getEmpByHireyear(String hireyear) {
		SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
				.withProcedureName("emp_by_hireyear")
				.declareParameters(
						new SqlParameter("p_hireyear", Types.VARCHAR),
						new SqlOutParameter("refcur", Types.REF_CURSOR)
				)
				.returningResultSet("refcur", BeanPropertyRowMapper.newInstance(Emp.class));
		
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("p_hireyear", hireyear);
		SqlParameterSource in = new MapSqlParameterSource(paramMap);
		
		Map<String, Object> resultMap = simpleJdbcCall.execute(in);
		List<Emp> list = (List<Emp>) resultMap.get("refcur");
		return list;
	}
	
	public String getEnameByEmpno3(int empno) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("empno", empno);
		dao.getEnameByEmpno(paramMap);
		
		String ename = (String)paramMap.get("ename");
		logger.info("paramMap={}", paramMap.toString());
		return ename;
	}
	
	public String getEmpByEmpno(int empno) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("empno", empno);
		dao.getEmpByEmpno(paramMap);
		System.out.println(paramMap);
		
		String ename = (String)paramMap.get("ename");
		Integer deptno = (Integer)paramMap.get("deptno");
		Date hiredate = (Date)paramMap.get("hiredate");
		logger.info("paramMap={}", paramMap.toString());
		return String.format("%d\t%s\t%d\t%s", empno, ename, deptno, hiredate);
	}
	
	public Emp getVoByEmpno(int empno) {
		Emp emp = new Emp();
		emp.setEmpno(empno);
		
		dao.getVoByEmpno(emp);
		
		logger.info("emp={}", emp.toString());
		return emp;
	}
	
	public String getEmpByDeptno2(int deptno) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("deptno", deptno);
		dao.getEmpByDeptno(paramMap);
		System.out.println(paramMap);
		return paramMap.get("resultList").toString();
	}
	
	public String getVoByDeptno(int deptno) {
		Emp emp = new Emp();
		emp.setDeptno(deptno);
		dao.getVoByDeptno(emp);
		return emp.getList().toString();
	}
	
}
