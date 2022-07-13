package com.ezen.demo.plsql;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.demo.model.Emp;

@RestController
@RequestMapping("/plsql")
public class PlsqlTestController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	PlsqlTestService svc;
	
	@GetMapping("")
	public String test() {
		logger.info("{}. 여기는 {}", 1, "test()"); // trace, debug, info, warn, error
		return "PLSQL Test";
	}
	
	/* plsql/ename/7369 요청에 대한 응답 작성 > 웹브라우저에 표시하기 */
	@GetMapping("/ename/{empno}")
	public String getEnameByEmpno(@PathVariable("empno") int empno) {
		Map<String, Object> map = svc.getEnameByEmpno2(empno);
		
//		logger.info("controller ename={}", map.get("ename"));
//		return map.get("ename").toString();
		
		logger.debug("ename={}", (String)map.get("P_ENAME"));
		
		return map.get("P_ENAME").toString();
	}
	
	/* plsql/emp/20  */
	@GetMapping("/emp/{deptno}")
	public String getEmpByDeptno(@PathVariable("deptno") int deptno) {
		List<Emp> list = svc.getEmpByDeptno(deptno);
		return list.toString();
	}
	
	/* plsql/emp/hireyear/1980  */
	@GetMapping("/emp/hireyear/{hireyear}")
	public String getEmpByHireyear(@PathVariable("hireyear") String hireyear) {
		List<Emp> list = svc.getEmpByHireyear(hireyear);
		return list.toString();
	}
	
	@GetMapping("/ename2/{empno}")
	public String getEnameByEmpno3(@PathVariable("empno") int empno) {
		return svc.getEnameByEmpno3(empno);
	}
	
	@GetMapping("/emp/empno/{empno}")
	public String getEmpByEmpno(@PathVariable("empno") int empno) {
		return svc.getEmpByEmpno(empno);
	}
	
	@GetMapping("/emp/vo/empno/{empno}")
	public String getVoByEmpno(@PathVariable("empno") int empno) {
		return svc.getVoByEmpno(empno).toString();
	}
	
	@GetMapping("/emp/deptno2/{deptno}")
	public String getEmpByDeptno2(@PathVariable("deptno") int deptno) {
		return svc.getEmpByDeptno2(deptno);
	}
	
	@GetMapping("/emp/vo/deptno/{deptno}")
	public String getVoByDeptno(@PathVariable("deptno") int deptno) {
		return svc.getVoByDeptno(deptno);
	}
}
