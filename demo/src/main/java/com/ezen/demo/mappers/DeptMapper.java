package com.ezen.demo.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.demo.vo.Dept;

@Mapper
public interface DeptMapper 
{
	List<Dept> getList();
}
