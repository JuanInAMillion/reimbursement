
package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.hibernate.HibernateException;
import org.hibernate.Session;
//import org.hibernate.Transaction;

import com.revature.model.Employee;
import com.revature.util.HibernateSessionFactory;

public class EmployeeDAOImpl implements EmployeeDAO {
	private static final Logger LOGGER = LogManager.getFormatterLogger(EmployeeDAOImpl.class);
	private Session currentSession;
	
	public EmployeeDAOImpl() {
		// TODO Auto-generated constructor stub
	}
	
	public List<Employee> findAllEmployees() {
		List<Employee> emplList = new ArrayList<Employee>();
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();	        	        	        
	        emplList = currentSession.createQuery("From Employee").getResultList();
	        currentSession.getTransaction().commit();
		}catch(HibernateException e) {
			if(currentSession.getTransaction() != null) {
				currentSession.getTransaction().rollback();				
			}				
			LOGGER.error("Error at finding all employees.",e);
			e.printStackTrace();
			emplList = null;
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return emplList;
	}

	public List<Employee> findEmployeesByUsername(String username) {
		List<Employee> emplList = new ArrayList<Employee>();
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();	        	        	        	        
	        String searchCriteria = "username";
	        String hql = "From Employee as empl where empl."+searchCriteria+" like :searchField";
			Query<Employee> query = currentSession.createQuery(hql);
			query.setParameter("searchField", "%"+username+"%");
	        emplList = query.getResultList();
	        currentSession.getTransaction().commit();
		}catch(HibernateException e) {
			currentSession.getTransaction().rollback();				
			LOGGER.error("Error at finding employees by user name.",e);
			e.printStackTrace();
			emplList = null;
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return emplList;
	}

	public Employee findEmployeeById(int employeeId) {
		Employee employee = null;
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();	        	        	        	        
			employee = currentSession.get(Employee.class, employeeId);
	        currentSession.getTransaction().commit();
		}catch(HibernateException e) {
			currentSession.getTransaction().rollback();				
			LOGGER.error("Error at finding employee by employee ID.",e);
			e.printStackTrace();
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return employee;
	}

	public Employee findEmployeeByUsername(String username) {
		// TODO Auto-generated method stub
		Employee employee = null;
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();	        	        	        	        
	        String hql = "Select * From reimburse.employee as empl where empl.username=:username";
			Query<Employee> query = currentSession.createNativeQuery(hql,Employee.class);
			query.setParameter("username", username);
			employee = (Employee)(query.uniqueResult());
	        currentSession.getTransaction().commit();
		}catch(HibernateException e) {
			currentSession.getTransaction().rollback();				
			LOGGER.error("Error at finding employee by user name.",e);
			e.printStackTrace();
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return employee;
	}

	public Employee findEmployeeByEmail(String email) {
		// TODO Auto-generated method stub
		Employee employee = null;
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();	        	        	        	        
	        String hql = "Select * From reimburse.employee as empl where empl.email=:email";
			Query<Employee> query = currentSession.createNativeQuery(hql,Employee.class);
			query.setParameter("email", email);
			employee = (Employee)query.uniqueResult();
	        currentSession.getTransaction().commit();
		}catch(HibernateException e) {
			currentSession.getTransaction().rollback();				
			LOGGER.error("Error at finding employee by Email.",e);
			e.printStackTrace();
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return employee;
	}

	public int addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		int id = 0;
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();
	        LOGGER.debug(currentSession.isConnected());
	        System.out.println("currentSession connected:"+currentSession.isConnected());
	        currentSession.save(employee);
	        currentSession.getTransaction().commit();
			id = employee.getEmployeeId(); 
			LOGGER.info("Successfully adding an employee.");
		}catch(HibernateException e) {
			if(currentSession.getTransaction() != null) {
				LOGGER.error("Error at adding an employee, transaction is Being Rolled Back.",e);
				currentSession.getTransaction().rollback();				
			}
			e.printStackTrace();
		}finally {
				currentSession.close();			
		}
		return id;
	}

	public boolean updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();
	        currentSession.update(employee);
	        currentSession.getTransaction().commit();
			LOGGER.info("Successfully updating an employee.");
			return true;
		}catch(HibernateException e) {
			if(currentSession.getTransaction() != null) {
				LOGGER.error("Error at updating an employee, transaction is Being Rolled Back.",e);
				currentSession.getTransaction().rollback();				
			}
			e.printStackTrace();
			return false;
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
	}

	public boolean deleteEmployeeById(int employeeId) {
		try {
	        Employee employee = findEmployeeById(employeeId);
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();
	        if(employee !=null) {
		        currentSession.delete(employee);
		        currentSession.getTransaction().commit();
				LOGGER.info("Successfully deleting an employee.");	        	
				return true;
	        }else {
				LOGGER.info("This emplyee Id doesn't exsit.");
				return false;	        	
	        }
		}catch(HibernateException e) {
			if(currentSession.getTransaction() != null) {
				LOGGER.error("Error deleting an employee, transaction is Being Rolled Back.",e);
				currentSession.getTransaction().rollback();				
			}
			e.printStackTrace();
			return false;
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
	}

	public boolean deleteEmployeeByUsername(String username) {
		// TODO Auto-generated method stub
		try {
	        Employee employee = findEmployeeByUsername(username);
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();
	        if(employee !=null) {
		        currentSession.delete(employee);
		        currentSession.getTransaction().commit();
				LOGGER.info("Successfully deleting an employee.");	        	
				return true;
	        }else {
				LOGGER.info("This username of employee doesn't exsit.");
				return false;	        	
	        }
		}catch(HibernateException e) {
			if(currentSession.getTransaction() != null) {
				LOGGER.error("Error at deleting an employee, transaction is Being Rolled Back.",e);
				currentSession.getTransaction().rollback();				
			}
			e.printStackTrace();
			return false;
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
	}

	@Override
	public List findAllEmployeeWithMgrId() {
		List emplMgrs = null;
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();	 
	        String nql = "Select empl.employeeId,empl.username, empl.firstname,empl.lastname,"
	        		+" empl.email, empl.workyears, emgr.managerId "
	        		+" From reimburse.employee as empl left join reimburse.empl_mgr as emgr "
	        		+" on empl.employeeId = emgr.employeeId order by emgr.managerId";
			Query query = currentSession.createNativeQuery(nql);
			emplMgrs = query.getResultList();
	        currentSession.getTransaction().commit();
		}catch(HibernateException e) {
			currentSession.getTransaction().rollback();				
			LOGGER.error("Error at finding employee by user name.",e);
			e.printStackTrace();
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return emplMgrs;
	}

	@Override
	public List findEmployeesWithMgrByEmplRoleId(int employeeId, int roleId) {
		List emplMgrs = null;
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();
	        String nql = "";
	        Query query = null;
	        if(employeeId > 0) {
	        	if(roleId == 1) {
	    	        nql = "Select empl.employeeId,empl.username, empl.firstname,empl.lastname,"
	    	        		+" empl.email, empl.workyears, emgr.managerId "
	    	        		+" From reimburse.employee as empl left join reimburse.empl_mgr as emgr "
	    	        		+" on empl.employeeId = emgr.employeeId where empl.employeeId=:employeeId";
	    			query = currentSession.createNativeQuery(nql);
	    			query.setParameter("employeeId", employeeId);
	        	}else if(roleId ==2) {
	    	        nql = "Select empl.employeeId,empl.username,empl.firstname,empl.lastname,"
	    	        		+" empl.email, empl.workyears, emgr.managerId "
	    	        		+" From reimburse.employee as empl left join reimburse.empl_mgr as emgr "
	    	        		+" on empl.employeeId = emgr.employeeId where emgr.managerId =:employeeId"
	    	        		+" or empl.employeeId =:employeeId";
	    			query = currentSession.createNativeQuery(nql);
	    			query.setParameter("employeeId", employeeId);
	        	}	        		
	        }else {
	        	if(roleId == 1) {
	    	        nql = "Select empl.employeeId,empl.username, empl.firstname,empl.lastname,"
	    	        		+" empl.email, empl.workyears, emgr.managerId "
	    	        		+" From reimburse.employee as empl left join reimburse.empl_mgr as emgr "
	    	        		+" on empl.employeeId = emgr.employeeId where empl.roleId=1";
	    			query = currentSession.createNativeQuery(nql);
	        	}else if(roleId ==2) {
	    	        nql = "Select empl.employeeId,empl.username,empl.firstname,empl.lastname,"
	    	        		+" empl.email, empl.workyears, emgr.managerId "
	    	        		+" From reimburse.employee as empl left join reimburse.empl_mgr as emgr "
	    	        		+" on empl.employeeId = emgr.employeeId where empl.roleId =2";
	    			query = currentSession.createNativeQuery(nql);
	        	}	        			        	
	        }	        
			emplMgrs = query.getResultList();
	        currentSession.getTransaction().commit();
		}catch(HibernateException e) {
			currentSession.getTransaction().rollback();				
			LOGGER.error("Error at finding employee by user name.",e);
			e.printStackTrace();
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return emplMgrs;
	}

}
