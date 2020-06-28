/*=================================================================================================================
 *  #06.EmployeeDAO.java
 *  - 데이터 베이스 액션 처리 클래스
 *  - 직원 데이터 입출력 및 수정 삭제
 *  - Connection 객체에 대한 의존성 주입 준비
 *  (인터페이스 자료형 / setter 구성)
 * ================================================================================================================
 * */
package com.test.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;





public class EmployeeDAO implements IEmployeeDAO
{
	//인터페이스 자료형 구성
	private DataSource dataSource;
	


	//setter 구성
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	
	//인터페이스에서 선언된 메소드 재정의
	@Override
	public ArrayList<Employee> list() throws SQLException
	{
		ArrayList<Employee> result = new ArrayList<Employee>();
		
		//데이터 베이스 연결
		Connection conn = dataSource.getConnection();
		
		String sql="SELECT EMPLOYEEID, NAME, SSN, BIRTHDAY, LUNAR, LUNARNAME, TELEPHONE, DEPARTMENTID, DEPARTMENTNAME, POSITIONID, POSITIONNAME, REGIONID, REGIONNAME, BASICPAY, EXTRAPAY, PAY, GRADE"
				+ " FROM EMPLOYEEVIEW"
				+ " ORDER BY EMPLOYEEID";
		// 뷰를 부여하고 있다.
		
		//System.out.println(sql);
		
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) 
		{
			Employee employee = new Employee();
			employee.setEmployeeId(rs.getString("EMPLOYEEID"));
			employee.setName(rs.getString("NAME"));
			employee.setSsn(rs.getString("SSN"));
			employee.setBirthday(rs.getString("BIRTHDAY"));
			employee.setLunar(Integer.parseInt(rs.getString("LUNAR")));
			employee.setLunarName(rs.getString("LUNARNAME"));
			employee.setTelephone(rs.getString("TELEPHONE"));
			employee.setDepartmentId(rs.getString("DEPARTMENTID"));
			employee.setDepartmentName(rs.getString("DEPARTMENTNAME"));
			employee.setPositionId((rs.getString("POSITIONID")));
			employee.setPositionName(rs.getString("POSITIONNAME"));
			employee.setRegionId(rs.getString("REGIONID"));			
			employee.setRegionName(rs.getString("REGIONNAME"));
			employee.setBasicPay(Integer.parseInt(rs.getString("BASICPAY")));
			employee.setExtraPay(Integer.parseInt(rs.getString("EXTRAPAY")));
			employee.setPay(Integer.parseInt(rs.getString("PAY")));
			employee.setGrade(Integer.parseInt(rs.getString("GRADE")));
			
			result.add(employee);
			
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		
		return result;
	}

	@Override
	public ArrayList<Region> regionList() throws SQLException
	{
		ArrayList<Region> result = new ArrayList<Region>();
	
		
		String sql ="SELECT * FROM REGIONVIEW";
		
		Connection conn = dataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) 
		{
			Region region = new Region();
			region.setRegionId(rs.getString("REGIONID"));
			region.setRegionName(rs.getString("REGIONNAME"));
			region.setDelCheck(rs.getInt("DELCHECK"));
			
			result.add(region);
			
		}
		
		//리소스 반납
		rs.close();
		pstmt.close();
		conn.close();
			
		
		return result;
	}

	@Override
	public ArrayList<Department> departmentList() throws SQLException
	{
		ArrayList<Department> result = new ArrayList<Department>();
		
		
	
		String sql ="SELECT * FROM DEPARTMENTVIEW";
		
		Connection conn = dataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) 
		{
			Department department = new Department();
			department.setDepartmentId(rs.getString("DEPARTMENTID"));
			department.setDepartmentName(rs.getString("DEPARTMENTNAME"));
			department.setDelcheck(rs.getInt("DELCHECK"));
			
			result.add(department);
			
		}
		
		//리소스 반납
		rs.close();
		pstmt.close();
		conn.close();
			
		
		
		
		
		
		return result;
	}

	@Override
	public ArrayList<Position> positionList() throws SQLException
	{
		ArrayList<Position> result = new ArrayList<Position>();
		
		
		
		String sql ="SELECT * FROM POSITIONVIEW";
		
		Connection conn = dataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) 
		{
			
			Position position = new Position();
			position.setPositionId(rs.getString("POSITIONID"));
			position.setPositionName(rs.getString("POSITIONNAME"));
			position.setDelCheck(rs.getInt("DELCHECK"));
			
			result.add(position);
			
		}
		
		//리소스 반납
		rs.close();
		pstmt.close();
		conn.close();
			
		
		
		return result;
	}

	@Override
	public int getMinBasicPay(String positionId) throws SQLException
	{
		int result =0;
		
		String sql="SELECT MINBASICPAY FROM POSITION WHERE POSITIONID=?";
		Connection conn = dataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, positionId);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) 
		{
			result = rs.getInt("MINBASICPAY");
			
		}	
		
		//리소스 반납
		rs.close();
		pstmt.close();
		conn.close();
		
		return result;
	}

	@Override
	public int employeeAdd(Employee employee) throws SQLException
	{
		
		if(employee.getBasicPay() < getMinBasicPay(employee.getPositionId())) 
		{
			System.out.println("");
		}	
		
		int result=0;
		
		String sql="INSERT INTO EMPLOYEE(EMPLOYEEID,NAME,SSN1,SSN2,BIRTHDAY,LUNAR,TELEPHONE,DEPARTMENTID,POSITIONID,REGIONID,BASICPAY,EXTRAPAY)"
				+ " VALUES(EMPLOYEESEQ.NEXTVAL,?,?,CRYPTPACK.ENCRYPT(?,?),TO_DATE(?,'YYYY-MM-DD'),?,?,?,?,?,?,?)";
		
		Connection conn = dataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, employee.getName());
		pstmt.setString(2, employee.getSsn1());
		//두번 넣어야 한다. 체크
		pstmt.setString(3, employee.getSsn2());
		pstmt.setString(4, employee.getSsn2());
		pstmt.setString(5, employee.getBirthday());
		pstmt.setInt(6, employee.getLunar());
		pstmt.setString(7, employee.getTelephone());
		pstmt.setString(8, employee.getDepartmentId());
		pstmt.setString(9, employee.getPositionId());
		pstmt.setString(10, employee.getRegionId());
		pstmt.setInt(11, employee.getBasicPay());
		pstmt.setInt(12, employee.getExtraPay());
		
		result = pstmt.executeUpdate();
				
		
		return result;
	}

	@Override
	public int remove(String employeeId) throws SQLException
	{
		int result=0;
		
		String sql="DELETE FROM EMPLOYEE WHERE EMPLOYEEID=?";
		Connection conn = dataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,employeeId);
		
		result = pstmt.executeUpdate();
		
		return result;
	}

	//모두 수정하게 되어 있다. id빼고 모두 수정하게 암호화 해서 수정하게 SSN2 2번 넣어야한다.
   @Override
   public int modify(Employee employee) throws SQLException
   {
      int result = 0;

      Connection conn=dataSource.getConnection();
      
      String sql="UPDATE EMPLOYEE"
      		+ " SET NAME=?, BIRTHDAY=TO_DATE(?, 'YYYY-MM-DD')"
      		+ ", LUNAR=?, TELEPHONE=?, DEPARTMENTID=?, POSITIONID=?, REGIONID=?,"
      		+ " BASICPAY=?, EXTRAPAY=?, SSN1=?, SSN2=CRYPTPACK.ENCRYPT(?,?)"
      		+ " WHERE EMPLOYEEID = ?";
      PreparedStatement pstmt = conn.prepareStatement(sql);
     
      
      System.out.println(employee.getBirthday());
      
      
      pstmt.setString(1, employee.getName());
      pstmt.setString(2, employee.getBirthday());
      pstmt.setInt(3, employee.getLunar());
      pstmt.setString(4, employee.getTelephone());
      pstmt.setString(5, employee.getDepartmentId());
      pstmt.setString(6, employee.getPositionId());
      pstmt.setString(7, employee.getRegionId());
      pstmt.setInt(8, employee.getBasicPay());
      pstmt.setInt(9, employee.getExtraPay());
      pstmt.setString(10, employee.getSsn1());
      pstmt.setString(11, employee.getSsn2());
      pstmt.setString(12, employee.getSsn2());
      pstmt.setString(13, employee.getEmployeeId());
      
      result = pstmt.executeUpdate();

      pstmt.close();
      conn.close();
      
      return result;
   }

	//직원검색 view 가아니라 그냥 임폴로이 테이블에서 
	@Override
	public Employee searchId(String employeeId) throws SQLException
	{
		Employee result=  new Employee();
		// TO_CHAR(BIRTHDAY, 'YYYY-MM-DD') 입력은 yyyy-mm-dd 인데 보여주는건  yyyy-mm-dd hh-ss이여서 
		String sql="SELECT EMPLOYEEID, NAME, TO_CHAR(BIRTHDAY, 'YYYY-MM-DD') AS BIRTHDAY"
				+ ", LUNAR, TELEPHONE"
				+ ", DEPARTMENTID, POSITIONID, REGIONID"
				+ ", BASICPAY, EXTRAPAY, SSN1, SSN2, GRADE"
				+ " FROM EMPLOYEE"
				+ " WHERE EMPLOYEEID=?";
		
		Connection conn = dataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,employeeId);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) 
		{
			result.setEmployeeId(rs.getString("EMPLOYEEID"));
			result.setName(rs.getString("NAME"));
			result.setBirthday(rs.getString("BIRTHDAY"));
			result.setLunar(rs.getInt("LUNAR"));
			result.setTelephone(rs.getString("TELEPHONE"));
			result.setDepartmentId(rs.getString("DEPARTMENTID"));
			result.setPositionId(rs.getString("POSITIONID"));
			result.setRegionId(rs.getString("REGIONID"));
			result.setBasicPay(rs.getInt("BASICPAY"));
			result.setExtraPay(rs.getInt("EXTRAPAY"));
			result.setSsn1(rs.getString("SSN1"));
			result.setSsn2(rs.getString("SSN2"));
			result.setGrade(rs.getInt("GRADE"));
		}
		                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
		//리소스 반납
	      pstmt.close();
	      conn.close();
	      rs.close();
	      
		
		
		return result;
	}// end searchId


	// 로그인 기능과 관련한 메소드 추가 ~! --------------------------------------------------------------------------------------------
	
	//일반 직원 로그인
	@Override
	public String login(String id, String pw) throws SQLException
	{
		String result = null;
		
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT NAME"
				+ " FROM EMPLOYEE"
				+ " WHERE EMPLOYEEID=?"
				+ " AND SSN2=CRYPTPACK.ENCRYPT(?, ?)";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(id));
		pstmt.setString(2, pw);
		pstmt.setString(3, pw);
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) 
		{
			result = rs.getString("NAME");			
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return result;
	}

	//관리자 로그인
	@Override
	public String loginAdmin(String id, String pw) throws SQLException
	{
		String result = null;
		
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT NAME"
				+ " FROM EMPLOYEE"
				+ " WHERE EMPLOYEEID=?"
				+ " AND SSN2=CRYPTPACK.ENCRYPT(?, ?)"
				+ " AND GRADE=0";

		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(id));
		pstmt.setString(2, pw);
		pstmt.setString(3, pw);
		
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) 
		{
			result = rs.getString("NAME");			
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		
		return result;
	}
	
	//-------------------------------------------------------- 로그인 기능과 관련한 메소드 추가 ~!!!
	
	// 일반 직원이 조회하는 직원 전체 리스트 출력 (추가)
	public ArrayList<Employee> empList() throws SQLException
	{
		ArrayList<Employee> result = new ArrayList<Employee>();
		
		//데이터 베이스 연결
		Connection conn = dataSource.getConnection();
		
		String sql="SELECT EMPLOYEEID, NAME, SSN, BIRTHDAY"
				+ ", LUNARNAME"
				+ ", TELEPHONE, DEPARTMENTNAME, POSITIONNAME, REGIONNAME"
				+ " FROM EMPLOYEEVIEW"
				+ " ORDER BY EMPLOYEEID";
		// 뷰를 부여하고 있다.
		
		//System.out.println(sql);
		
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) 
		{
			Employee employee = new Employee();
			employee.setEmployeeId(rs.getString("EMPLOYEEID"));
			employee.setName(rs.getString("NAME"));
			employee.setSsn(rs.getString("SSN"));
			employee.setBirthday(rs.getString("BIRTHDAY"));
			employee.setLunarName(rs.getString("LUNARNAME"));
			employee.setTelephone(rs.getString("TELEPHONE"));
			employee.setDepartmentName(rs.getString("DEPARTMENTNAME"));
			employee.setPositionName(rs.getString("POSITIONNAME"));	
			employee.setRegionName(rs.getString("REGIONNAME"));

			result.add(employee);
			
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		
		return result;
	}

	
	
	
	

	
	
	

}
