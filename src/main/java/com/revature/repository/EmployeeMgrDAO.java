package com.revature.repository;

import java.util.List;

import com.revature.model.Employee;

public interface EmployeeMgrDAO {
	public List<Employee> findManagersByEmplId(int emplId);
	public List<Employee> findEmployeesByMgrId(int mgrId);	
	public boolean addEmployeeMgr(int mgrId, int emplId);
	public boolean deleteEmployeeMgr(int mgrId, int emplId);
}
