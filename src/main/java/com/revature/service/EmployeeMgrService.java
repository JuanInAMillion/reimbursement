
package com.revature.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.model.Employee;
import com.revature.repository.EmployeeMgrDAO;
import com.revature.repository.EmployeeMgrDAOImpl;

public class EmployeeMgrService {
	private static final Logger LOGGER = LogManager.getFormatterLogger(EmployeeMgrService.class);
	private EmployeeMgrDAO emplMgrDAO = null;
	
	
	public EmployeeMgrService() {
		super();
		// TODO Auto-generated constructor stub
		emplMgrDAO = new EmployeeMgrDAOImpl();
	}
	
	public List<Employee> findManagersByEmplId(int emplId){
		return this.emplMgrDAO.findManagersByEmplId(emplId);
		
	}
	public List<Employee> findEmployeesByMgrId(int mgrId){
		return this.emplMgrDAO.findEmployeesByMgrId(mgrId);
	}
		
	public boolean addEmployeeMgr(int mgrId, int emplId) {
		return this.emplMgrDAO.addEmployeeMgr(mgrId, emplId);
	}
	
	public boolean deleteEmployeeMgr(int mgrId, int emplId) {
		return this.emplMgrDAO.deleteEmployeeMgr(mgrId, emplId);
	}

}
