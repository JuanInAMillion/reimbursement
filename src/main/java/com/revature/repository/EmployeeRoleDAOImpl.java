
package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.revature.model.EmployeeRole;
import com.revature.util.HibernateSessionFactory;

public class EmployeeRoleDAOImpl implements EmployeeRoleDAO {
	private static final Logger LOGGER = LogManager.getFormatterLogger(EmployeeRoleDAOImpl.class);
	private Session currentSession;

	
	public EmployeeRoleDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<EmployeeRole> findAllRoles() {
		// TODO Auto-generated method stub
		List<EmployeeRole> emplList = new ArrayList<EmployeeRole>();
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();	        
	        emplList = currentSession.createQuery("From EmployeeRole").getResultList();
	        currentSession.getTransaction().commit();
		}catch(HibernateException e) {
			if(currentSession.getTransaction() != null) {
				currentSession.getTransaction().rollback();				
			}			
			LOGGER.error("Error at finding all employeeroles.",e);
			e.printStackTrace();
			emplList = null;
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return emplList;
	}

	public EmployeeRole findEmployeeRoleById(int roleid) {
		// TODO Auto-generated method stub
		EmployeeRole role = null;
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();	        	        
	        String hql = "Select * From reimburse.empl_role as emplRole where emplRole.roleId=:roleId";
			Query<EmployeeRole> query = currentSession.createNativeQuery(hql,EmployeeRole.class);
			query.setParameter("roleId", roleid);
			role = query.uniqueResult();
	        currentSession.getTransaction().commit();			
		}catch(HibernateException e) {
			if(currentSession.getTransaction() != null) {
				currentSession.getTransaction().rollback();				
			}			
			LOGGER.error("Error at finding role by role ID.",e);
			e.printStackTrace();
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return role;
	}

	public boolean addRole(EmployeeRole role) {
		// TODO Auto-generated method stub
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();
	        LOGGER.debug(currentSession.isConnected());
	        System.out.println("currentSession connected:"+currentSession.isConnected());
	        currentSession.save(role);
	        currentSession.getTransaction().commit();
			LOGGER.info("Successfully adding a role.");
			return true;
		}catch(HibernateException e) {
			if(currentSession.getTransaction() != null) {
				LOGGER.error("Error at adding a role, transaction is Being Rolled Back.",e);
				currentSession.getTransaction().rollback();				
			}
			e.printStackTrace();
			return false;
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
	}

	public boolean updateRole(EmployeeRole role) {
		// TODO Auto-generated method stub
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();
	        currentSession.update(role);
	        currentSession.getTransaction().commit();
			LOGGER.info("Successfully updating a role.");
			return true;
		}catch(HibernateException e) {
			if(currentSession.getTransaction() != null) {
				LOGGER.error("Error at updating a role, transaction is Being Rolled Back.",e);
				currentSession.getTransaction().rollback();				
			}
			e.printStackTrace();
			return false;
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
	}

	public boolean deleteRoleById(int roleid) {
		try {
			
	        EmployeeRole role = findEmployeeRoleById(roleid);

	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();	        
	        System.out.println("DADADA: "+role.getRoleId() +" , "+role.getRole());
	        if(role !=null) {
		        currentSession.delete(role);
		        currentSession.getTransaction().commit();
				LOGGER.info("Successfully deleting a role.");	        	
				return true;
	        }else {
				LOGGER.info("This role Id doesn't exsit.");
				return false;	        	
	        }
		}catch(HibernateException e) {
			if(currentSession.getTransaction() != null) {
				LOGGER.error("Error at deleting a role, transaction is Being Rolled Back.",e);
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
