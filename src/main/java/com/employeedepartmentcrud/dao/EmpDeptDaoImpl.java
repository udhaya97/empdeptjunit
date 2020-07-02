package com.employeedepartmentcrud.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.employeedepartmentcrud.model.Department;
import com.employeedepartmentcrud.model.Employee;

public class EmpDeptDaoImpl implements EmpDeptDao {
	
	static Connection con;
	//connection with mysql
	static void getConnectionMySql()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
		}  
		try {
			con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/studept","root","root");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}  
	}
	public Department createDept(Department dept) {
		getConnectionMySql();
		String query ="insert into department values (?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, dept.getDept_id());
			ps.setString(2, dept.getDept_name());
			ps.executeUpdate();
			System.out.println("Department created");
			
		} catch (SQLException e) {
			System.err.println("Duplicate primary key ! change dept_id ");
		}
		

	return dept;}

	public Department updateDept(int deptId , Department dpt) {
		getConnectionMySql();
		String query ="update department set dept_name=? where dept_id=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, dpt.getDept_name());
			ps.setInt(2, deptId);
			ps.executeUpdate();
			System.out.println("Department Updated");
			
		} catch (SQLException e) {
		
			System.err.println("data not found");
			
		}

	return dpt;}

	public int readAllDept() {
		
		getConnectionMySql();
		String query ="select * from department";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+" "+rs.getString(2));
			}
			System.out.println("All Data From Department");
			
		} catch (SQLException e) {
			System.err.println("Data may not present");		}

	return 0;}

	public int delteDept(int deptId) {
		
		getConnectionMySql();
		String query ="delete from department where dept_id=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, deptId);
			//ps.executeUpdate();
			
			System.out.println("Data deleted from department"+ps.executeUpdate());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("data may not present");
		}
	return 0;}

	public Employee createEmp(Employee employee,int deptId) {
		
		getConnectionMySql();
		String query ="insert into employee values (?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, employee.getEmp_id());
			ps.setString(2, employee.getEmp_name());
			ps.setString(3, employee.getMailId());
			ps.setInt(4, employee.getAge());
			ps.setInt(5, deptId);
			ps.executeUpdate();
			System.out.println("Employee created");
			
		} catch (SQLException e) {
			System.err.println("dept_id may not be available in data base ");
		}
	return employee;}

	public Employee updateEmp(Employee employee, int empId, int deptId) {
		getConnectionMySql();
		String query ="update employee set emp_name=? where emp_id=? and dept_emp_fk=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString(1, employee.getEmp_name());
			ps.setInt(2, empId);
			ps.setInt(3, deptId);
			ps.executeUpdate();
			System.out.println("Employee table updated");
			
		} catch (SQLException e) {
		
			System.err.println("Data not present");
		}

	return employee;}

	public int readEmpFromDept(int deptId) {
		getConnectionMySql();
		String query ="select * from employee where dept_emp_fk=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, deptId);
			ResultSet rs = ps.executeQuery();
			
					//System.out.println("Values from employee table "+rs.next());
					
						if(rs.next()==false)
						{
							System.out.println("Data not available in table for searched key");
						}else
						{
							while(rs.next())
							{
						System.out.println(rs.getInt(1)+" "+rs.getString(2));
						}
					}
				
				
			
			
		} catch (SQLException e) {
			System.err.println("data not available");
		}
	return 0;}

	public int deleteEmpFromDept(int deptId,int empId) {
		getConnectionMySql();
		String query ="delete from employee where dept_emp_fk=? and emp_id=?";
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setInt(1, deptId);
			ps.setInt(2, empId);
			ps.executeUpdate();
			System.out.println("Employee Deleted");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("No value found");
		}

	return 0;}
	
	
	
	

}
