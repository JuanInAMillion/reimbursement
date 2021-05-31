
package com.revature.repository;

import java.util.List;

import com.revature.model.ReimbReqStatus;

public interface ReimbReqStatusDAO {
	public List<ReimbReqStatus> findAllStatuses();
	public ReimbReqStatus findReimbReqStatusById(int statusId);		
	public boolean addStatus(ReimbReqStatus status);
	public boolean updateStatus(ReimbReqStatus status);
	public boolean deleteStatusById(int statusId);	
}
