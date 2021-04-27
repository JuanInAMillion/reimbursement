
package com.revature.repository;

import java.util.List;

import com.revature.model.ReimbRequest;

public interface ReimbRequestDAO {
	public List<ReimbRequest> findAllReimbRequests();	
	public List<ReimbRequest> findReimbRequestsByManagerId(int managerId);
	public List<ReimbRequest> findReimbRequestsByEmployeeId(int employeeId);
	public List<ReimbRequest> findReimbRequestsByStatusId(int statusId);
	public List<ReimbRequest> findReimbRequestsByEmplStatusId(int employeeId, int statusId);
	public List<ReimbRequest> findReimbRequestsByManagerAndStatusId(int managerId, int statusId);
	public ReimbRequest findReimbRequestByRequestId(int requestId);
	public int addReimbRequest(ReimbRequest reimbRequest);
	public boolean updateReimbRequest(ReimbRequest reimbRequest);
	public boolean deleteReimbRequestByRequestId(int requestId);
}
