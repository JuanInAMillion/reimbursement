
package com.revature.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name="EmployeeMgr")
@Table(name="empl_mgr" ,schema="reimburse")
public class EmployeeMgr implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private EmployeeMgrId empl_mgr_id = new EmployeeMgrId();
	public EmployeeMgr() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EmployeeMgr(EmployeeMgrId empl_mgr_id) {
		super();
		this.empl_mgr_id = empl_mgr_id;
	}
	public EmployeeMgrId getEmpl_mgr_id() {
		return empl_mgr_id;
	}
	public void setEmpl_mgr_id(EmployeeMgrId empl_mgr_id) {
		this.empl_mgr_id = empl_mgr_id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((empl_mgr_id == null) ? 0 : empl_mgr_id.hashCode());
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
		EmployeeMgr other = (EmployeeMgr) obj;
		if (empl_mgr_id == null) {
			if (other.empl_mgr_id != null)
				return false;
		} else if (!empl_mgr_id.equals(other.empl_mgr_id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "EmployeeMgr [empl_mgr_id=" + empl_mgr_id + "]";
	}
	
}
