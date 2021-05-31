
package com.revature.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="ReimbReqStatus")
@Table(name="reimb_req_status" ,schema="reimburse")
public class ReimbReqStatus implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1567701960595371949L;
	@Id
	private Integer statusId;
	@Column(name="status")
	private String status;
	public ReimbReqStatus() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ReimbReqStatus(Integer statusId, String status) {
		super();
		this.statusId = statusId;
		this.status = status;
	}
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((statusId == null) ? 0 : statusId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimbReqStatus other = (ReimbReqStatus) obj;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (statusId == null) {
			if (other.statusId != null)
				return false;
		} else if (!statusId.equals(other.statusId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "ReimbReqStatus [statusId=" + statusId + ", status=" + status + "]";
	}

	
}
