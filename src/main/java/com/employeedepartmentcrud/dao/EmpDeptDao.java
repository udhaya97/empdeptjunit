package com.employeedepartmentcrud.dao;

import com.employeedepartmentcrud.model.Department;
import com.employeedepartmentcrud.model.Employee;

public interface EmpDeptDao {
	
	//create a department
	Department createDept(Department dept);
	//update a department
	Department updateDept(int deptId,Department dept);
	//Get All Department
    int readAllDept();
    //delete department with all associated employees
    int delteDept(int deptId);
    //Create Employee With Associate Department id
    Employee createEmp(Employee employee,int deptId);
    //Update employee details with in department
    Employee updateEmp(Employee employee,int empId,int deptId);
    // Get all employees based on department id
    int readEmpFromDept(int empId);
    //delete employee in department
    int deleteEmpFromDept(int deptId,int empId);

  
    
    

}
