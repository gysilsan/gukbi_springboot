package com.ezen.demo.model;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Emp 
{
   private int empno;
   private String ename;
   private String job;
   private int mgr;
   private java.sql.Date hiredate;
   private float sal;
   private int comm;
   private int deptno;
   private String hireyear;
   
   private List<Emp> list;
	
   public List<Emp> getList() {
	   return list;
	}
	public void setList(List<Emp> list) {
		this.list = list;
	}
	@Override
	public String toString() {
//      return String.format("%d\t%s\t%s\t%f\t%d", empno,ename,hiredate,sal, deptno);
//	   return String.format("%d\t%s\t%s", empno, ename, hireyear);
//	   return String.format("%d\t%s\t%d\t%s", empno, ename, deptno, hiredate);
	   return String.format("%d\t%s\t%d", empno, ename, deptno);
   }
   public int getEmpno() {
      return empno;
   }
   public void setEmpno(int empno) {
      this.empno = empno;
   }
   public String getEname() {
      return ename;
   }
   public void setEname(String ename) {
      this.ename = ename;
   }
   public java.sql.Date getHiredate() {
      return hiredate;
   }
   public void setHiredate(java.sql.Date hiredate) {
      this.hiredate = hiredate;
   }
   public float getSal() {
      return sal;
   }
   public void setSal(float sal) {
      this.sal = sal;
   }
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public int getMgr() {
		return mgr;
	}
	public void setMgr(int mgr) {
		this.mgr = mgr;
	}
	public int getComm() {
		return comm;
	}
	public void setComm(int comm) {
		this.comm = comm;
	}
	public String getHireyear() {
		return hireyear;
	}
	public void setHireyear(String hireyear) {
		this.hireyear = hireyear;
	}
	
	
	
	
}