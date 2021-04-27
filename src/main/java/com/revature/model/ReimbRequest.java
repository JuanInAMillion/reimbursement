
package com.revature.model;

import java.io.Serializable;
import java.util.Arrays;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity(name="ReimbRequest")
@Table(name="reimb_request" ,schema="reimburse")
public class ReimbRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7873173225410985300L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="reimbReqId")
	private Integer reimbReqId;
	
	@Column(name="employeeId")	
	private Integer employeeId;
	
	@Column(name="location")
	private String location;
	
	@Column(name="requestDate")
	private Date requestDate;
	
	@Column(name="approvalDate")
	private Date approvalDate;
	
	@Column(name="cost", precision=10, scale=2)
	private Double cost;

	@Column(name="description")
	private String description;
	
	@Type(type="org.hibernate.type.BinaryType")
	@Column(name="receipt", columnDefinition="bytea")
	private byte[] receipt;	
	
	@Column(name="statusId")
	private Integer statusId;

	public ReimbRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReimbRequest(Integer employeeId, String location, Date requestDate, Date approvalDate, Double cost,
			String description, byte[] receipt, Integer statusId) {
		super();
		this.employeeId = employeeId;
		this.location = location;
		this.requestDate = requestDate;
		this.approvalDate = approvalDate;
		this.cost = cost;
		this.description = description;
		this.receipt = receipt;
		this.statusId = statusId;
	}

	public Integer getReimbReqId() {
		return reimbReqId;
	}

	public void setReimbReqId(Integer reimbReqId) {
		this.reimbReqId = reimbReqId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getReceipt() {
		return receipt;
	}

	public void setReceipt(byte[] receipt) {
		this.receipt = receipt;
	}

	public Integer getStatusId() {
		return statusId;
	}

	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((approvalDate == null) ? 0 : approvalDate.hashCode());
		result = prime * result + ((cost == null) ? 0 : cost.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((location == null) ? 0 : location.hashCode());
		result = prime * result + Arrays.hashCode(receipt);
		result = prime * result + ((reimbReqId == null) ? 0 : reimbReqId.hashCode());
		result = prime * result + ((requestDate == null) ? 0 : requestDate.hashCode());
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
		ReimbRequest other = (ReimbRequest) obj;
		if (approvalDate == null) {
			if (other.approvalDate != null)
				return false;
		} else if (!approvalDate.equals(other.approvalDate))
			return false;
		if (cost == null) {
			if (other.cost != null)
				return false;
		} else if (!cost.equals(other.cost))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		if (!Arrays.equals(receipt, other.receipt))
			return false;
		if (reimbReqId == null) {
			if (other.reimbReqId != null)
				return false;
		} else if (!reimbReqId.equals(other.reimbReqId))
			return false;
		if (requestDate == null) {
			if (other.requestDate != null)
				return false;
		} else if (!requestDate.equals(other.requestDate))
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
		return "ReimbRequest [reimbReqId=" + reimbReqId + ", employeeId=" + employeeId + ", location=" + location
				+ ", requestDate=" + requestDate + ", approvalDate=" + approvalDate + ", cost=" + cost
				+ ", description=" + description + ", receipt=" + Arrays.toString(receipt) + ", statusId=" + statusId
				+ "]";
	}


}
