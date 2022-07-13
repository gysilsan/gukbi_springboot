package com.ezen.demo.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.demo.vo.Dept;

@Mapper
public interface DeptMapper 
{
	List<Dept> getList();	// 전체 목록
	List<Dept> getListByDeptno(int deptno);
	Dept getInfoByDeptno(int deptno);
	
	int add(Dept dept);
	int update(Dept dept);
	int delete(int deptno);
	
	int addAndGetKey(Dept dept);
	
	List<Dept> getListLike(String key);
	List<Map> getListMap();
	
	List<Dept> getListByMultiDeptnos(List<Integer> list);
}
