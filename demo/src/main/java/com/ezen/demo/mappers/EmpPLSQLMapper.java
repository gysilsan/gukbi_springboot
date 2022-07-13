package com.ezen.demo.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.demo.model.Emp;

@Mapper
public interface EmpPLSQLMapper {
	
	void getEnameByEmpno(Map<String, Object> map);
	void getEmpByEmpno(Map<String, Object> map);
	void getVoByEmpno(Emp emp);
	void getEmpByDeptno(Map<String, Object> map);
	void getVoByDeptno(Emp emp);
}
