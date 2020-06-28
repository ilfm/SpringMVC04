/*================================
 *  #10. DepartmentDAO.java
 * 	- 데이터 베이스 액션 처리 클래스
 *  - 지역 데이터 입출력 및 수정 삭제
 *  - Connection 객체에 대한 의존성 주입 준비.
 *    (인터페이스 자료형 / setter 구성)
 * ==============================
 */


package com.test.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class DepartmentDAO implements IDepartmentDAO
{
	//인터페이스 자료형을 속성으로 구성
	private DataSource dataSource;
	
	//setter 구성
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	
	//IDepartmentDAO 인터페이스 메소드 재정의 -------------------------------------------
	
	//부서 전체 리스트 출력
	@Override
	public ArrayList<Department> list() throws SQLException
	{
		ArrayList<Department> result = new ArrayList<Department>();
		
		//DB연결
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT DEPARTMENTID, DEPARTMENTNAME , DELCHECK FROM DEPARTMENTVIEW";		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) 
		{
			Department dto = new Department();
			
			dto.setDepartmentId(rs.getString("DEPARTMENTID"));
			dto.setDepartmentName(rs.getString("DEPARTMENTNAME"));
			dto.setDelcheck(rs.getInt("DELCHECK"));
			
			result.add(dto);			
		}	
		
		
		conn.close();
		pstmt.close();
		rs.close();
		
		return result;
	}

	//부서 데이터 등록(입력 , 추가)
	@Override
	public int add(Department department) throws SQLException
	{
		int result =0;
		
		String sql="INSERT INTO DEPARTMENT(DEPARTMENTID,DEPARTMENTNAME) VALUES(DEPARTMENTSEQ.NEXTVAL,?)";
	    
		//DB연결
		Connection conn = dataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,department.getDepartmentName());
		
		result = pstmt.executeUpdate();
		
		
		
		conn.close();
		pstmt.close();
				
		return result;
	}
	

	// 부서 데이터 제거(삭제)
	@Override
	public int remove(String departmentId) throws SQLException//들어오는게 스트링
	{
		int result =0;
		
		String sql ="DELETE FROM DEPARTMENT WHERE DEPARTMENTID=?"; 
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setInt(1, Integer.parseInt(departmentId));//믿지마..
		
		result = pstmt.executeUpdate();
		
		conn.close();
		pstmt.close();		
		
		
		return result;
	}

	// 부서 데이터 수정(변경)
	@Override
	public int modify(Department department) throws SQLException
	{
	
		int result =0;
		
		String sql ="UPDATE DEPARTMENT SET DEPARTMENTNAME =? WHERE DEPARTMENTID=? ";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, department.getDepartmentName());
		pstmt.setInt(2, Integer.parseInt(department.getDepartmentId()));
		
		
		result = pstmt.executeUpdate();
		
		conn.close();
		pstmt.close();
		
		
		
		return result;
	}
	
	//--------------------------------------------- IDepartmentDAO 인터페이스 메소드 재정의
	
	
	
}
