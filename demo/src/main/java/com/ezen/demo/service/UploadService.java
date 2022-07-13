package com.ezen.demo.service;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ezen.demo.mappers.UploadMapper;
import com.ezen.demo.model.AttachVO;
import com.ezen.demo.model.UploadVO;

@Service
public class UploadService 
{
	@Autowired
	private UploadMapper dao;

//	public List<Map<String, Object>> getList() {
//		List<Map<String, Object>> list = dao.getList();
//		return list;
//	}
	
	public String getFname(int num) {
		return dao.getFname(num);
	}
	
	public List<UploadVO> getList() {
		List<Map<String, Object>> list = dao.getList();
		List<UploadVO> voList = new ArrayList<>();
		for(int i=0; i<list.size(); i++) {
			Map<String,Object> map = list.get(i);
			
			String fname = (String)map.get("FNAME");
//			String fpath = (String)map.get("FPATH");
			
			AttachVO aVo = null;
			
			if(fname != null) {
				aVo = new AttachVO();
				int fnum= ((BigDecimal)map.get("FNUM")).intValue();
				aVo.setNum(fnum);
				aVo.setFname(fname);
//				aVo.setFpath(fpath);
			}
			
			UploadVO vo = new UploadVO();
			int num = ((BigDecimal)map.get("NUM")).intValue();
			vo.setNum(num);
			
			if(voList.contains(vo)) {
				voList.get(voList.size()-1).getAttach().add(aVo);
			} else {
				String writer = (String)map.get("WRITER"); 
				Date udate = new Date(((Timestamp)map.get("UDATE")).getTime());
				String description = (String)map.get("DESCRIPTION");
			
				vo.setWriter(writer);
				vo.setUdate(udate);
				vo.setDescription(description);
	//			vo.getFnames().add(fname);
				vo.getAttach().add(aVo);
				
				voList.add(vo);
			}

		}
			return voList;
	}
	
	public UploadVO getDetail(int _num) {
		List<Map<String, Object>> list = dao.getDetail(_num);
		UploadVO vo = new UploadVO();
		for(int i=0; i<list.size(); i++) {
			Map<String, Object> map = list.get(i);
			
			int num = ((BigDecimal)map.get("NUM")).intValue();
			String writer = (String)map.get("WRITER");
			Date udate = new Date(((Timestamp)map.get("UDATE")).getTime());
			String description = (String)map.get("DESCRIPTION");
			
			vo.setNum(num);
			vo.setWriter(writer);
			vo.setUdate(udate);
			vo.setDescription(description);
			
			String fname = (String)map.get("FNAME");
			
			if(fname!=null) {
				AttachVO aVo = new AttachVO();
				int fnum = ((BigDecimal)map.get("FNUM")).intValue();
				aVo.setNum(fnum);
				aVo.setFname(fname);
				vo.getAttach().add(aVo);
			}
		}
		return vo;
	}
	
	public boolean insert(UploadVO vo) 
	{
		int pnum = 0;
		if(vo.getWriter() != null) { //파일 정보만 있는지, 글 정보도 있는지 확인
			dao.insertUpload(vo);    //upload_tb에 저장
			pnum = vo.getNum();
		}
		
		List<AttachVO> attList = vo.getAttach();
		
		if(pnum>0) {
			for(int i=0;i<attList.size();i++) {
				attList.get(i).setPnum(pnum);
			}
		}
		
		return dao.insertMultiAttach(attList)==attList.size();

//	      
//	      int insertedCnt = 0;
//	      for(int i=0;i<attList.size();i++) {
//	         insertedCnt += dao.insertAttach(attList.get(i));
//	      }
//	      return insertedCnt == attList.size();
//	      
	      

//		int totalSuccess = 0;
//		for(int i=0;i<attList.size();i++) {
//			AttachVO att = attList.get(i);
//			pnum = att.getPnum()>0 ? att.getPnum() : pnum; //pnum이 있으면 씀, 없으면 위에서 설정된 값 사용
//			att.setPnum(pnum);
//			att.setFname(attList.get(i).getFname());
//			att.setFpath(vo.getFpath());
//			totalSuccess += dao.insertAttach(att);
			
			//Map 사용 방식
//			Map<String,Object> fmap = new HashMap<>();
//			fmap.put("pnum", pnum);
//			fmap.put("fname", attList.get(i).getFname());
//			fmap.put("fpath", vo.getFpath());
//			totalSuccess += dao.insertAttach(fmap);   // 첨부파일 정보 저장
//		}
//		int insertedMulti = dao.insertMultiAttach(attList);
//		return insertedMulti == attList.size();
	}
	
	public boolean deleteAttach(HttpServletRequest request, int num) {
		String fname = dao.getFname(num);
		
		String path = request.getServletContext().getRealPath("WEB-INF/files/"+fname);
		System.out.println("fullPath= " + path);
		
		File f = new File(path);
		if(!f.exists()) {
			System.out.println("File Not Found");
			return false;
		}
		
		boolean fileDeleted = f.delete();
		boolean dbDeleted = dao.deleteAttach(num)>0;
		return fileDeleted && dbDeleted;
	}
	
	@Transactional(rollbackFor = {Exception.class})
	public boolean deleteUpload(HttpServletRequest request, int num) throws Exception {

		boolean attDeleted = dao.deleteAttInfo(num)>0;
		if(!attDeleted) throw new Exception("attach_tb rows delete fail");
		
		boolean uploadDeleted = dao.deleteUpload(num)>0;
		if(!uploadDeleted) throw new Exception("upload_tb rows delete fail");
		
		List<String> fnameList = dao.getAttachByPnum(num);
		String dir = request.getServletContext().getRealPath("WEB-INF/files/");
		int delCnt = 0;
		if(!(fnameList==null || fnameList.size()==0)) {
			for(int i=0; i<fnameList.size(); i++) {
				String path = dir+fnameList.get(i);
				File f = new File(path);
				if(!f.exists()) {
					System.out.println("File Not Found, '" + path + "'");
					continue;
				}
				delCnt += f.delete() ? 1 : 0;
			}
			if(delCnt==fnameList.size()) {
				System.out.println("Successfully deleted!");
			} else {
				System.out.println("Failed to delete the files");
				throw new Exception("file delete fail");
			}
		}
		return true;
	}


}
