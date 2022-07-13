package com.ezen.demo.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.demo.model.Board;

@Mapper
public interface MybatisBoardMapper {

	List<Board> getList();
	Board getBoardByNum(int num);
	List<Board> search(Map<String,String> map);
	
	int add(Board board);
	int delete(int num);
	int update(Board board);
}
