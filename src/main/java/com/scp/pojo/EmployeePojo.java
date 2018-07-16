package com.scp.pojo;

public class EmployeePojo {
	private int empid;
	private String empname;
	private String password;
	private String isAvtive;
	public EmployeePojo(int empid, String empname, String password, String isAvtive) {
		super();
		this.empid = empid;
		this.empname = empname;
		this.password = password;
		this.isAvtive = isAvtive;
	}
	public int getEmpid() {
		return empid;
	}
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	public String getEmpname() {
		return empname;
	}
	public void setEmpname(String empname) {
		this.empname = empname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String isAvtive() {
		return isAvtive;
	}
	public void setAvtive(String isAvtive) {
		this.isAvtive = isAvtive;
	}
	public EmployeePojo() {
		super();
	}
	@Override
	public String toString() {
		return "EmployeePojo [empid=" + empid + ", empname=" + empname + ", password=" + password + ", isAvtive="
				+ isAvtive + "]";
	}	

}
