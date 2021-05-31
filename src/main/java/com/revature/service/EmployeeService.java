
package com.revature.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.model.Employee;
import com.revature.repository.EmployeeDAO;
import com.revature.repository.EmployeeDAOImpl;

public class EmployeeService {
	private static final Logger LOGGER = LogManager.getFormatterLogger(EmployeeService.class);
	private EmployeeDAO emplDAO = null;
	
	public EmployeeService() {
		emplDAO = new EmployeeDAOImpl();
	}
	
	public List<Employee> findAllEmployees(){
		return this.emplDAO.findAllEmployees();
	}
	public List<Employee> findEmployeesByUsername(String username){
		return this.emplDAO.findEmployeesByUsername(username);
	}
	
	public Employee findEmployeeById(int employeeId) {
		return this.emplDAO.findEmployeeById(employeeId);
	}
	public Employee findEmployeeByUsername(String username) {
		return this.emplDAO.findEmployeeByUsername(username);
	}
	public Employee findEmployeeByEmail(String email) {
		return this.emplDAO.findEmployeeByEmail(email);
	}
		
	public List findAllEmployeeWithMgrId() {
		return this.emplDAO.findAllEmployeeWithMgrId();
	}
	
	public List findEmployeesWithMgrByEmplRoleId(int employeeId, int roleId) {
		return this.emplDAO.findEmployeesWithMgrByEmplRoleId(employeeId, roleId);
	}
	
	public int addEmployee(Employee employee) {
		return this.emplDAO.addEmployee(employee);
	}
	
	public boolean updateEmployee(Employee employee) {
		return this.emplDAO.updateEmployee(employee);
	}
	
	public boolean deleteEmployeeById(int employeeId) {
		return this.emplDAO.deleteEmployeeById(employeeId);
	}
	
	public boolean deleteEmployeeByUsername(String username) {
		return this.emplDAO.deleteEmployeeByUsername(username);
	}
	
	public Employee login(String username, String password) {
		 Employee empl = findEmployeeByUsername(username);		
		 LOGGER.info("username "+username +" tries to login");
		 if(empl != null ) {
			 boolean flag = false;
			 try {
				 if(password.equals(empl.getPassword()))
					flag = true;
			 }catch(Exception e) {
				 e.printStackTrace();
				 LOGGER.error("Failed to validate user info.");
			 }
			 
			 if(flag) {
				 LOGGER.info("username "+username+" login successfully.");
				 return empl;				 
			 }else {
				 LOGGER.info("username "+username+" failed to login.");				 
				 return null;
			 }
		 }else {
			 LOGGER.info("no username "+username+" in system.");				 
			 return null;
		 }
	 }
}
