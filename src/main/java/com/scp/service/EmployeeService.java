package com.scp.service;

import java.util.HashMap;
import java.util.List;

import com.scp.constants.AppLevelConstants;
import com.scp.entity.EmployeeEntity;
import com.scp.pojo.EmployeePojo;

public interface EmployeeService {

	public boolean addEmployee(EmployeePojo p);
	public boolean deleteEmployee(int id);
	public EmployeePojo getEmployee(int id);
	public boolean updateEmployee(EmployeePojo e);
	public EmployeeEntity getById(int id);
	public List<EmployeePojo>getAllEmployee();
	public EmployeePojo getByIdPojo(int id);
	public List<EmployeePojo>searchEmployee(HashMap<AppLevelConstants.EmpConstants, String>searchElements);
}
