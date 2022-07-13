package com.ezen.demo.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ezen.demo.model.AttachVO;
import com.ezen.demo.model.UploadVO;

@Mapper
public interface UploadMapper 
{
	int insertUpload(UploadVO vo);
	
//	int insertAttach(Map<String, Object> map);
	int insertAttach(AttachVO vo);
	
	int insertMultiAttach(List<AttachVO> list);
	
	int updateUpload(UploadVO vo);
	
	int updateAttach(Map<String, Object> map);
	
	int deleteUpload(int num);
	int deleteAttach(int num);
	int deleteAttInfo(int pnum);
	String getFname(int num);
	
	List<Map<String, Object>> getList();
	
	List<Map<String, Object>> getDetail(int num);
	
	List<String> getAttachByPnum(int pnum);
	
}
