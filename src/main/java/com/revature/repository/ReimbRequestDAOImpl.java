
package com.revature.repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.revature.model.Employee;
import com.revature.model.ReimbRequest;
import com.revature.util.HibernateSessionFactory;

public class ReimbRequestDAOImpl implements ReimbRequestDAO {
	private static final Logger LOGGER = LogManager.getFormatterLogger(ReimbRequestDAOImpl.class);
	private Session currentSession;

	public List<ReimbRequest> findAllReimbRequests() {
		List<ReimbRequest> reimbReqList = new ArrayList<ReimbRequest>();
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();	        	        	        
	        reimbReqList = currentSession.createQuery("From ReimbRequest").getResultList();
	        currentSession.getTransaction().commit();
		}catch(HibernateException e) {
			if(currentSession.getTransaction() != null) {
				currentSession.getTransaction().rollback();				
			}				
			LOGGER.error("Error at finding all ReimbRequest.",e);
			e.printStackTrace();
			reimbReqList = null;
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return reimbReqList;
	}

	public List<ReimbRequest> findReimbRequestsByManagerId(int managerId) {
		List<ReimbRequest> reimbReqList = new ArrayList<ReimbRequest>();
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();	   
	        String hql = "Select * From reimburse.reimb_request as reibmReq "+
	        				"left join reimburse.empl_mgr as emgr on reibmReq.employeeId = emgr.employeeId "+
	        				"where emgr.managerId =:managerId or reibmReq.employeeid =:employeeid order by reibmReq.employeeid";
			Query<ReimbRequest> query = currentSession.createNativeQuery(hql,ReimbRequest.class);
			query.setParameter("managerId", managerId);
			query.setParameter("employeeid", managerId);
			reimbReqList = query.getResultList();
	        currentSession.getTransaction().commit();
		}catch(HibernateException e) {
			currentSession.getTransaction().rollback();				
			LOGGER.error("Error at finding ReimbRequest by managerId.",e);
			e.printStackTrace();
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return reimbReqList;
	}

	public List<ReimbRequest> findReimbRequestsByEmployeeId(int employeeId) {
		List<ReimbRequest> reimbReqList = new ArrayList<ReimbRequest>();
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();	        	        	        	        
	        String hql = "Select * From reimburse.reimb_request as reibmReq "+
	        				"where reibmReq.employeeId =:employeeId";
			Query<ReimbRequest> query = currentSession.createNativeQuery(hql,ReimbRequest.class);
			query.setParameter("employeeId", employeeId);
			reimbReqList = query.getResultList();
	        currentSession.getTransaction().commit();
		}catch(HibernateException e) {
			currentSession.getTransaction().rollback();				
			LOGGER.error("Error at finding ReimbRequest by employee ID.",e);
			e.printStackTrace();
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return reimbReqList;
	}

	public List<ReimbRequest> findReimbRequestsByStatusId(int statusId) {
		List<ReimbRequest> reimbReqList = new ArrayList<ReimbRequest>();
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();	        	        	        	        
	        String hql = "Select * From reimburse.reimb_request as reibmReq "+
	        				"where reibmReq.statusId =:statusId";
			Query<ReimbRequest> query = currentSession.createNativeQuery(hql,ReimbRequest.class);
			query.setParameter("statusId", statusId);
			reimbReqList = query.getResultList();
	        currentSession.getTransaction().commit();
		}catch(HibernateException e) {
			currentSession.getTransaction().rollback();				
			LOGGER.error("Error at finding ReimbRequest by employee ID.",e);
			e.printStackTrace();
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return reimbReqList;
	}

	public ReimbRequest findReimbRequestByRequestId(int requestId) {
		ReimbRequest reimbReq = null;
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();	        	        	        	        
			reimbReq = currentSession.get(ReimbRequest.class, requestId);
	        currentSession.getTransaction().commit();
			
		}catch(HibernateException e) {
			currentSession.getTransaction().rollback();				
			LOGGER.error("Error at finding ReimbRequest by employee ID.",e);
			e.printStackTrace();
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return reimbReq;
	}

	public int addReimbRequest(ReimbRequest reimbRequest) {
		int id = 0;
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();
	        currentSession.save(reimbRequest);
	        currentSession.getTransaction().commit();
			id = reimbRequest.getReimbReqId(); 
			LOGGER.info("Successfully adding an ReimbRequest.");
		}catch(HibernateException e) {
			LOGGER.error("Error at adding an ReimbRequest, transaction is Being Rolled Back.",e);
			currentSession.getTransaction().rollback();				
			e.printStackTrace();
		}finally {
				currentSession.close();			
		}
		return id;
	}

	public boolean updateReimbRequest(ReimbRequest reimbRequest) {
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();
	        currentSession.update(reimbRequest);
	        currentSession.getTransaction().commit();
			LOGGER.info("Successfully updating a reimbRequest.");
			return true;
		}catch(HibernateException e) {
			if(currentSession.getTransaction() != null) {
				LOGGER.error("Error at updating an reimbRequest, transaction is Being Rolled Back.",e);
				currentSession.getTransaction().rollback();				
			}
			e.printStackTrace();
			return false;
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
	}

	public boolean deleteReimbRequestByRequestId(int requestId) {
		try {
			ReimbRequest reimbReq = findReimbRequestByRequestId(requestId);
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();
	        if(reimbReq !=null) {
		        currentSession.delete(reimbReq);
		        currentSession.getTransaction().commit();
				LOGGER.info("Successfully deleting an reimbReq.");	        	
				return true;
	        }else {
				LOGGER.info("This requestId doesn't exsit.");
				return false;	        	
	        }
		}catch(HibernateException e) {
			if(currentSession.getTransaction() != null) {
				LOGGER.error("Error at deleting an reimbReq, transaction is Being Rolled Back.",e);
				currentSession.getTransaction().rollback();				
			}
			e.printStackTrace();
			return false;
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
	}

	public List<ReimbRequest> findReimbRequestsByEmplStatusId(int employeeId, int statusId) {
		// TODO Auto-generated method stub
		List<ReimbRequest> reimbReqList = new ArrayList<ReimbRequest>();
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();	        	        	        	        
	        String hql = "Select * From reimburse.reimb_request as reibmReq "+
	        				"where reibmReq.employeeId =:employeeId AND reibmReq.statusId =:statusId";
			Query<ReimbRequest> query = currentSession.createNativeQuery(hql,ReimbRequest.class);
			query.setParameter("employeeId", employeeId);			
			query.setParameter("statusId", statusId);
			reimbReqList = query.getResultList();
	        currentSession.getTransaction().commit();
		}catch(HibernateException e) {
			currentSession.getTransaction().rollback();				
			LOGGER.error("Error at finding ReimbRequest by employee ID.",e);
			e.printStackTrace();
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return reimbReqList;
	}

	public List<ReimbRequest> findReimbRequestsByManagerAndStatusId(int managerId, int statusId) {
		// TODO Auto-generated method stub
		List<ReimbRequest> reimbReqList = new ArrayList<ReimbRequest>();
		try {
	        currentSession = HibernateSessionFactory.getSession();
	        currentSession.beginTransaction();	         
	        String hql = "Select * From reimburse.reimb_request as reibmReq"
	        			+ "	left join reimburse.empl_mgr as emgr on reibmReq.employeeId = emgr.employeeId"
	        			+ "	where (emgr.managerId =:managerId or reibmReq.employeeId =:employeeId)"
	        			+ "	and reibmReq.statusId =:statusId";	        
	        Query<ReimbRequest> query = currentSession.createNativeQuery(hql,ReimbRequest.class);
			query.setParameter("managerId", managerId);			
			query.setParameter("employeeId", managerId);			
			query.setParameter("statusId", statusId);
			reimbReqList = query.getResultList();
	        currentSession.getTransaction().commit();
		}catch(HibernateException e) {
			currentSession.getTransaction().rollback();				
			LOGGER.error("Error at finding ReimbRequest by employee ID.",e);
			e.printStackTrace();
		}finally {
			if(currentSession != null)
				currentSession.close();			
		}
		return reimbReqList;
	}
	
}
