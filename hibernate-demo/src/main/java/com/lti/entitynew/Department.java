package com.lti.entitynew;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_department")
public class Department {

	@Id
	@GeneratedValue
	private int deptno;

	private String name;
	private String location;

	@OneToMany(mappedBy = "department",cascade=CascadeType.MERGE)
	private List<EmployeeNew> employees;

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<EmployeeNew> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeNew> employees) {
		this.employees = employees;
	}

}
