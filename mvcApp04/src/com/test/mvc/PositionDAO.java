/*================================
 *  #12. PositionDAO.java
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

public class PositionDAO implements IPositionDAO
{

	private DataSource dataSource;
	
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	//전체 출력 메소드
	@Override
	public ArrayList<Position> list() throws SQLException
	{
		ArrayList<Position> result = new ArrayList<Position>();
		
		Connection conn = dataSource.getConnection();
		
		String sql = "SELECT POSITIONID,POSITIONNAME,DELCHECK FROM POSITIONVIEW";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) 
		{
			Position dto = new Position();
			
			dto.setPositionId(rs.getString("POSITIONID"));
			dto.setPositionName(rs.getString("POSITIONNAME"));
			dto.setDelCheck(rs.getInt("DELCHECK"));
		
			result.add(dto);			
			
		}	
		
		//리소스 반납
		rs.close();
		conn.close();
		pstmt.close();
		
		return result;
	}

	//직위 정보 추가 메소드
	@Override
	public int add(Position position) throws SQLException
	{
		int result =0;
		
		String sql="INSERT INTO POSITION"
				+ "(POSITIONID,POSITIONNAME,MINBASICPAY)"
				+ " VALUES(POSITIONSEQ.NEXTVAL,?,?)";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, position.getPositionName());
		pstmt.setInt(2, position.getMinBasicPay());
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
				
		return result;
	}

	//직위정보 제거
	@Override
	public int remove(String positionId) throws SQLException
	{
		
		int result =0;
		
		String sql = "DELETE FROM POSITION WHERE POSITIONID=?";
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(positionId));
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		return result;
	}
	
	//직위 정보 수정
	@Override
	public int modify(Position position) throws SQLException
	{
		int result = 0;
		
		String sql ="UPDATE POSITION SET POSITIONNAME=? ,MINBASICPAY=? WHERE POSITIONID=?";
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,position.getPositionName());
		pstmt.setInt(2,position.getMinBasicPay());
		pstmt.setString(3,position.getPositionId());
		
		result = pstmt.executeUpdate();
		
		pstmt.close();
		conn.close();
		
		
		return result;
	}

}
