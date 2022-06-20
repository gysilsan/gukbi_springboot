package com.ezen.demo.model;

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

   @Override
   public String toString() {
      return String.format("%d\t%s\t%s\t%f\t%d", empno,ename,hiredate,sal, deptno);
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
	
}