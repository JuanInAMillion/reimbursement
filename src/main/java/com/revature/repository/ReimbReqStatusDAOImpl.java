
package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.revature.model.ReimbReqStatus;
import com.revature.util.HibernateSessionFactory;

public class ReimbReqStatusDAOImpl implements ReimbReqStatusDAO {
	private static final Logger LOGGER = LogManager.getFormatterLogger(ReimbReqStatusDAO.class);
	private Session currentSession;

	public ReimbReqStatusDAOImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<ReimbReqStatus> findAllStatuses() {
		List<ReimbReqStatus> statusList = new ArrayList<ReimbReqStatus>();
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();	        	        
	        statusList = currentSession.createQuery("From ReimbReqStatus").getResultList();
	        currentSession.getTransaction().commit();	        
		}catch(HibernateException e) {
			currentSession.getTransaction().rollback();				
			LOGGER.error("Error at finding all employeeroles.",e);
			e.printStackTrace();
			statusList = null;
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return statusList;
	}

	public ReimbReqStatus findReimbReqStatusById(int statusId) {
		ReimbReqStatus status = null;
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();	        	        
	        String hql = "Select * From reimburse.reimb_req_status as reimbStatus where reimbStatus.statusId=:statusId";
			Query<ReimbReqStatus> query = currentSession.createNativeQuery(hql,ReimbReqStatus.class);
			query.setParameter("statusId", statusId);
			status = query.uniqueResult();
	        currentSession.getTransaction().commit();	        
		}catch(HibernateException e) {
			currentSession.getTransaction().rollback();				
			LOGGER.error("Error at finding role by role ID.",e);
			e.printStackTrace();
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return status;
	}

	public boolean addStatus(ReimbReqStatus status) {
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();
	        currentSession.save(status);
	        currentSession.getTransaction().commit();
			LOGGER.info("Successfully added a status.");
			return true;
		}catch(HibernateException e) {
			if(currentSession.getTransaction() != null) {
				LOGGER.error("Error at adding a status, transaction is Being Rolled Back.",e);
				currentSession.getTransaction().rollback();				
			}
			e.printStackTrace();
			return false;
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
	}

	public boolean updateStatus(ReimbReqStatus status) {
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();
	        currentSession.update(status);
	        currentSession.getTransaction().commit();
			LOGGER.info("Successfully updating a status.");
			return true;
		}catch(HibernateException e) {
			if(currentSession.getTransaction() != null) {
				LOGGER.error("Error at updating a status, transaction is Being Rolled Back.",e);
				currentSession.getTransaction().rollback();				
			}
			e.printStackTrace();
			return false;
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
	}

	public boolean deleteStatusById(int statusId) {
		try {
	        ReimbReqStatus status = findReimbReqStatusById(statusId);
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();	        
	        if(status !=null) {
		        currentSession.delete(status);
		        currentSession.getTransaction().commit();
				LOGGER.info("Successfully deleting a status.");	        	
				return true;
	        }else {
				LOGGER.info("This status Id doesn't exsit.");
				return false;	        	
	        }
		}catch(HibernateException e) {
			if(currentSession.getTransaction() != null) {
				LOGGER.error("Error at deleting a status, transaction is Being Rolled Back.",e);
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
