package com.employeedepartmentcrud;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.employeedepartmentcrud.dao.EmpDeptDao;
import com.employeedepartmentcrud.dao.EmpDeptDaoImpl;
import com.employeedepartmentcrud.model.Department;
import com.employeedepartmentcrud.model.Employee;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AppTest 
   
{
	 Department dep = new Department(1,"training");
 	Department dep1 = new Department(2,"development");
 	Department dep2 = new Department(3,"support");
 	Employee emp = new Employee(1,"Udhay","udhay@gmail.com",23);
 	Employee emp1 = new Employee(10,"rakesh","rake@gmail.com",26);
 	Employee emp2 = new Employee(12,"kishore","ksh@gmail.com",27);
     Employee emp4 = new Employee (15,"Kumar","kum@gmail.com",16);
   EmpDeptDao empd = new EmpDeptDaoImpl();
    Connection con;
    //connection with mysql
   @Test
   public  void a_connectionCheck()
   {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		
			System.err.println("Driver Error");
		}  
		try {
			con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/studept","root","root");
			System.out.println("Connection successfull");
			assertNotNull("Connection success full", con);
		} catch (SQLException e) {
			
			System.err.println("Connection invalid");
		}  
   }
   //create department
   @Test
   public void b_createDept()
   {
	  a_connectionCheck();
	 
	  
	  assertNotNull("Department Created",empd.createDept(dep));
	  assertNotNull("Department Created",empd.createDept(dep2));
	  assertNotNull("Department Created",empd.createDept(dep1));
	  try {
		con.close();
		System.out.println("Connection closed");
	} catch (SQLException e) {
		
		System.err.println("Connection issue");
	}
   }
   //create employee
   @Test
   public void c_createEmployee()
   {
	   a_connectionCheck();
	  assertNotNull("Creating employee", empd.createEmp(emp, dep2.getDept_id()));
	  assertNotNull("Creating employee", empd.createEmp(emp1, dep1.getDept_id()));
	  assertNotNull("Creating employee", empd.createEmp(emp2, dep.getDept_id()));
	  assertNotNull("Creating employee", empd.createEmp(emp, dep.getDept_id()));
	  assertNotNull("Creating employee", empd.createEmp(emp1, dep2.getDept_id()));
	  assertNotNull("Creating employee",empd.createEmp(emp2, dep1.getDept_id()));
	   
	  try {
			con.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			
			System.err.println("Connection issue");
		}
   }
   //Get All Department Values
   @Test
   public void d_readDept()
   {
	   a_connectionCheck();
	  assertNotNull("Showing Department values" ,empd.readAllDept());
	  try {
			con.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			
			System.err.println("Connection issue");
		}
   }
   //update employee in department
   @Test
   public void e_updateEmp()
   {
	   a_connectionCheck();
	   Employee emp = empd.updateEmp(emp4, emp2.getEmp_id(), dep.getDept_id());
	   assertNotNull("Employee Updated", emp);
	   try {
			con.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			
			System.err.println("Connection issue");
		}
   }
   //update department
   @Test
   public void f_updateDept()
   {
	   a_connectionCheck();
	  
	   assertNotNull("Department Updated", empd.updateDept(dep.getDept_id(), dep1));
	   try {
			con.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			
			System.err.println("Connection issue");
		}
	  
   }
   
  
   //get employees from department
   @Test
   public void g_readEmp()
   {
	   a_connectionCheck();
	   assertNotNull("Showing Employee",empd.readEmpFromDept(dep2.getDept_id()));
	   try {
			con.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			
			System.err.println("Connection issue");
		}
   }
   //delete employee in department
   @Test
   public void h_deleteEmp()
   {
	   a_connectionCheck();
	   assertNotNull("deleted Employee",empd.deleteEmpFromDept(dep.getDept_id(), emp.getEmp_id()));
	   try {
			con.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			
			System.err.println("Connection issue");
		}
   }
   //delete department
   @Test
   public void i_deleteDept()
   {
	   a_connectionCheck();
	   assertNotNull("deleting Department",empd.delteDept(dep2.getDept_id()));
	   try {
			con.close();
			System.out.println("Connection closed");
		} catch (SQLException e) {
			
			System.err.println("Connection issue");
		}
	  
   }


}
