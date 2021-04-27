
package com.revature.repository;

import java.util.List;

import com.revature.model.EmployeeRole;

public interface EmployeeRoleDAO {
	public List<EmployeeRole> findAllRoles();
	public EmployeeRole findEmployeeRoleById(int roleid);		
	public boolean addRole(EmployeeRole role);
	public boolean updateRole(EmployeeRole role);
	public boolean deleteRoleById(int roleid);	
}
