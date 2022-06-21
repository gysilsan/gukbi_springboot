package com.ezen.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ezen.demo.dao.JdbcEmpDao;
import com.ezen.demo.model.Emp;

@Controller
@RequestMapping("/jdbc/emp")
public class JdbcEmpController {
	
	@Autowired
	private JdbcEmpDao dao;
	
	@GetMapping("/list")
	public String getEmpList(Model model) {
		List<Emp> list = dao.getListAll();
		model.addAttribute("list", list);
		return "/emp/empList";
	}
	
	@GetMapping("/dept")
	public String getListByDeptNo() {
		List<Emp> list = dao.getListByDeptNo(20);
		return list.toString();
	}
	
	@GetMapping("/add")
	public String addEmp() {
		//Emp 객체생성
		Emp emp = new Emp();
		emp.setEmpno(8888);
		emp.setHiredate(java.sql.Date.valueOf("2022-06-20"));
		emp.setEname("Test");
		emp.setSal(40000);
		boolean added = dao.add(emp);
		return "added="+added;
	}
	
	@GetMapping("/addkey")
	public String addAndGetKey() {
		//Emp 객체생성
		Emp emp = new Emp();
		emp.setEmpno(9999);
		emp.setHiredate(java.sql.Date.valueOf("2022-06-20"));
		emp.setEname("TestKey");
		emp.setSal(40001);
		int key = dao.addAndGetKey(emp);
		return "key="+key;
	}
	
	@GetMapping("/one/{empno}")
	public String detail(@PathVariable("empno") int empno, Model model) {
		Emp emp = dao.getEmpById(empno);
		model.addAttribute("emp", emp);
		return "/emp/detail";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam(name ="empno") int empno, Model model) {
		Emp emp = dao.getEmpById(empno);
		model.addAttribute("emp", emp);
		return "/emp/edit";
	}
	
	@PostMapping("/update")
	@ResponseBody
	public String update(Emp emp) {
		boolean updated = dao.update(emp);
		return "{\"updated\":"+updated+"}";
	}
	
//	@GetMapping("/update/{empno}/{deptno}/{sal}")
//	public String update(@PathVariable("empno") int empno,
//						 @PathVariable("deptno") int deptno,
//						 @PathVariable("sal") int sal) {
//		//Emp 객체생성
//		Emp emp = new Emp();
//		emp.setEmpno(empno);
//		emp.setDeptno(deptno);
//		emp.setSal(sal);
//		boolean updated = dao.update(emp);
//		return "updated="+updated;
//	}
	
	@GetMapping("/delete") /* delete?empno=7369 */
	public String delete(@RequestParam(name ="empno") int empno) {
		boolean deleted = dao.delete(empno);
		return "deleted="+deleted;
	}
}
