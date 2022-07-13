package com.ezen.demo.mappers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.demo.vo.Dept;

@Controller
@RequestMapping("/mybatis/dept")
public class DeptController 
{
	@Autowired
	private DeptMapper dao;   // 인터페이스 > 구현 클래스 > 인스턴스 참조
	
	@GetMapping("")
	public String showForm() {
		return "/dept/form";
	}
	
	@GetMapping("/list")
	public String getList()
	{
		return dao.getList().toString();
	}
	
	@GetMapping("/listByDeptno")
	public String getListByDeptno() {
		return dao.getListByDeptno(0).toString();
				
	}
	
	@GetMapping("/dept")
	public String getInfoByDeptno() {
		return dao.getInfoByDeptno(10).toString();
	}
	
	@GetMapping("/add")
	public String add() {
		Dept dept = new Dept();
		dept.setDeptno(50);
		dept.setDname("개발팀");
		dept.setLoc("신림");
		boolean added =  dao.add(dept)>0;
		return "added="+added;
	}
	
	@GetMapping("/addAndGetKey")
	public String addAndGetKey() {
		Dept dept = new Dept();
		dept.setDeptno(50);
		dept.setDname("key");
		dept.setLoc("test");
		boolean added =  dao.addAndGetKey(dept)>0;
		return "added="+added;
	}

	@GetMapping("/update")
	public String update() {
		Dept dept = new Dept();
		dept.setDeptno(50);
		dept.setDname("test~");
		dept.setLoc("test~");
		boolean updated = dao.update(dept)>0;
		return "updated="+updated;
	}
	
	@GetMapping("/delete")
	public String delete() {
		boolean deleted = dao.delete(50)>0;
		return "deleted="+deleted;
	}
	
	@GetMapping("/getListLike")
	public String getListLike() {
		return dao.getListLike("ES").toString();
	}
	
	@GetMapping("/getListMap")
	public String getListMap() {
		return dao.getListMap().toString();
	}
	
	@PostMapping("/multi_deptno")
	@ResponseBody
	public String getListByMultiDeptnos(HttpServletRequest request) {
		String[] sDeptno = request.getParameterValues("deptno");
		
		List<Integer> list = new ArrayList<>();
		//list.add(20); list.add(40);
		
		for(int i=0; i<sDeptno.length; i++) {
			list.add(Integer.valueOf(sDeptno[i]));
		}
		
		return dao.getListByMultiDeptnos(list).toString();
	}
}
