
package com.revature.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EmployeeMgrId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8140069880550268797L;
	
	@Column(name="managerId")
	private Integer managerId;
	@Column(name="employeeId")
	private Integer employeeId;
	public EmployeeMgrId() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeMgrId(Integer managerId, Integer employeeId) {
		super();
		this.managerId = managerId;
		this.employeeId = employeeId;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((managerId == null) ? 0 : managerId.hashCode());
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
		EmployeeMgrId other = (EmployeeMgrId) obj;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		if (managerId == null) {
			if (other.managerId != null)
				return false;
		} else if (!managerId.equals(other.managerId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "EmployeeMgrId [managerId=" + managerId + ", employeeId=" + employeeId + "]";
	}
		
}
