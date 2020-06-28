/*=================================================================================================================
 *  #05.IEmployeeDAO.java
 *  - 인터페이스  → 의존성 주입을 위해 만드는 것 
 * ================================================================================================================
 * */
package com.test.mvc;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IEmployeeDAO
{

	// 추후 EmployeeDAO 에서 정의 할 것으로 예상되는 메소드에 대한 선언
	
	public ArrayList<Employee> list() throws SQLException;//나중에 Exception 던지는 것이 불가능하다.
	public ArrayList<Region> regionList() throws SQLException;
	public ArrayList<Department> departmentList() throws SQLException;
	public ArrayList<Position> positionList() throws SQLException;
	//이게 없다면 다 주입받아야한다.
	//편의성을 위해 
	
	// 최소급 알기 위한 메소드
	public int getMinBasicPay(String positionId) throws SQLException;
	public int employeeAdd(Employee employee) throws SQLException;
	public int remove(String employeeId) throws SQLException;
	public int modify(Employee employee) throws SQLException;
	
	// 어떤 직원을 수정이나 제거하려면 어떤 직원인지 알아야 한다.
	public Employee searchId(String employeeId) throws SQLException;
	
	// 로그인 기능과 관련한 메소드 추가 ~!!-------------------------------------------------------------------------------
	public String login(String id , String pw) throws SQLException;
	public String loginAdmin(String id , String pw) throws SQLException;
	
	// 일반 직원이 조회하는 직원 전체 리스트 출력(추가)
	public ArrayList<Employee> empList() throws SQLException;
	
	
	
	
	
}
