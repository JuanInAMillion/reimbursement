
package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.model.Employee;
import com.revature.model.EmployeeMgr;
import com.revature.model.EmployeeMgrId;
import com.revature.util.HibernateSessionFactory;

public class EmployeeMgrDAOImpl implements EmployeeMgrDAO {
	private static final Logger LOGGER = LogManager.getFormatterLogger(EmployeeDAOImpl.class);
	private Session currentSession;

	public EmployeeMgrDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Employee> findManagersByEmplId(int emplId) {
		List<Employee> mgrs = new ArrayList<Employee>();
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();	        
			String hql  = "select * from reimburse.employee as empl "+
					"left join reimburse.empl_mgr as e_mgr "+
					"ON empl.employeeId = e_mgr.managerId where e_mgr.employeeId =?1";		        	        
			Query<Employee> query = currentSession.createNativeQuery(hql,Employee.class);
			query.setParameter(1, emplId);
			mgrs = query.getResultList();
	        currentSession.getTransaction().commit();			
		}catch(HibernateException e) {
			LOGGER.error("Error at finding managers by employee ID.",e);
			e.printStackTrace();
			currentSession.getTransaction().rollback();				
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return mgrs;
	}

	public List<Employee> findEmployeesByMgrId(int mgrId) {
		// TODO Auto-generated method stub
		List<Employee> empls = new ArrayList<Employee>();
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();	        
			String hql  = "select * from reimburse.employee as empl "+
					"left join reimburse.empl_mgr as e_mgr "+
					"on empl.employeeId = e_mgr.employeeId where e_mgr.managerId =?1";		        	        
			Query<Employee> query = currentSession.createNativeQuery(hql,Employee.class);
			query.setParameter(1, mgrId);
			empls = query.getResultList();
	        currentSession.getTransaction().commit();			
		}catch(HibernateException e) {
			currentSession.getTransaction().rollback();				
			LOGGER.error("Error at finding employees by manager ID.",e);
			e.printStackTrace();
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return empls;
	}

	public boolean addEmployeeMgr(int mgrId, int emplId) {
		try {
			EmployeeMgrId emId = new EmployeeMgrId(mgrId, emplId);
			EmployeeMgr e_mgr = new EmployeeMgr(emId);
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();
	        currentSession.save(e_mgr);
	        currentSession.getTransaction().commit();
			LOGGER.info("Successfully adding a Employee_Manager.");
			return true;
		}catch(HibernateException e) {
			if(currentSession.getTransaction() != null) {
				LOGGER.error("Error at adding a Employee_Manager, transaction is Being Rolled Back.",e);
				currentSession.getTransaction().rollback();				
			}
			e.printStackTrace();
			return false;
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
	}

	public boolean deleteEmployeeMgr(int mgrId, int emplId) {
		try {
			EmployeeMgrId emId = new EmployeeMgrId(mgrId, emplId);
			EmployeeMgr e_mgr = new EmployeeMgr(emId);
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();
	        currentSession.delete(e_mgr);
	        currentSession.getTransaction().commit();
			LOGGER.info("Successfully deleting an Employee_Manager.");
			return true;
		}catch(HibernateException e) {
			if(currentSession.getTransaction() != null) {
				LOGGER.error("Error at deleting an Employee_Manager, transaction is Being Rolled Back.",e);
				currentSession.getTransaction().rollback();				
			}
			e.printStackTrace();
			return false;
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
	}

}
