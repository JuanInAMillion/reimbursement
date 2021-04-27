
package com.revature.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.model.ReimbRequest;
import com.revature.repository.ReimbRequestDAO;
import com.revature.repository.ReimbRequestDAOImpl;

public class ReimbRequestService {
	private static final Logger LOGGER = LogManager.getFormatterLogger(ReimbRequestService.class);
	private ReimbRequestDAO reimbReqDAO = null;
	
	
	public ReimbRequestService() {
		super();
		// TODO Auto-generated constructor stub
		reimbReqDAO = new ReimbRequestDAOImpl();
	}
	public List<ReimbRequest> findAllReimbRequests(){
		return this.reimbReqDAO.findAllReimbRequests();
	}
	
	public List<ReimbRequest> findReimbRequestsByManagerId(int managerId){
		return this.reimbReqDAO.findReimbRequestsByManagerId(managerId);
	}
	
	public List<ReimbRequest> findReimbRequestsByEmployeeId(int employeeId){
		return this.reimbReqDAO.findReimbRequestsByEmployeeId(employeeId);
	}
	
	public List<ReimbRequest> findReimbRequestsByStatusId(int statusId){
		return this.reimbReqDAO.findReimbRequestsByStatusId(statusId);
	}

	public ReimbRequest findReimbRequestByRequestId(int requestId) {
		return this.reimbReqDAO.findReimbRequestByRequestId(requestId);
	}
	public List<ReimbRequest>findReimbRequestsByEmplStatusId(int employeeId, int statusId){
		return this.reimbReqDAO.findReimbRequestsByEmplStatusId(employeeId, statusId);
	}
	public List<ReimbRequest>findReimbRequestsByManagerAndStatusId(int managerId, int statusId){
		return this.reimbReqDAO.findReimbRequestsByManagerAndStatusId(managerId, statusId);
	}
		
	public int addReimbRequest(ReimbRequest reimbRequest) {
		return this.reimbReqDAO.addReimbRequest(reimbRequest);
	}
	
	public boolean updateReimbRequest(ReimbRequest reimbRequest) {
		return this.reimbReqDAO.updateReimbRequest(reimbRequest);
	}
	
	public boolean deleteReimbRequestByRequestId(int requestId) {
		return this.reimbReqDAO.deleteReimbRequestByRequestId(requestId);
	}
	
	
}
