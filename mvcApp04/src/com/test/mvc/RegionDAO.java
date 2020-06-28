/*================================
 *  #10. RegionDAO.java
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

public class RegionDAO implements IRegionDAO
{

	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	
	//리스트 출력 메소드
	@Override
	public ArrayList<Region> list() throws SQLException
	{
		
		ArrayList<Region> result = new ArrayList<Region>();
		
		String sql = "SELECT REGIONID,REGIONNAME,DELCHECK FROM REGIONVIEW";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) 
		{
			
			Region dto = new Region();
			
			dto.setRegionId(rs.getString("REGIONID"));// 이 부분 String 이여도 상관없겠지?
			dto.setRegionName(rs.getString("REGIONNAME"));
			dto.setDelCheck(rs.getInt("DELCHECK"));
			//System.out.println("이거 :"+ rs.getInt("DELCHECK"));
			
			result.add(dto);			
		}
		
		conn.close();
		pstmt.close();
		rs.close();
		
		
		return result;
	}

	//지역 정보 추가 메소드
	@Override
	public int add(Region region) throws SQLException // 왜 그냥 지역명만 받으면 안되지?
	{
		System.out.println("여기왓니?ㄴㄴㄴ");
		int result = 0;
		
		String sql ="INSERT INTO REGION(REGIONID, REGIONNAME) VALUES(REGIONSEQ.NEXTVAL ,?)";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		//System.out.println(region.getRegionName());
		pstmt.setString(1, region.getRegionName());
		result = pstmt.executeUpdate();		
		
		conn.close();
		pstmt.close();
		
		System.out.println("여기왓니?ㄴㄴㄴ");
		return result;
	}

	//지역 삭제 메소드
	@Override
	public int remove(String regionId) throws SQLException
	{
		int result =0;
		
		String sql="DELETE FROM REGION WHERE REGIONID=?";
		
		//DB연결
		Connection conn = dataSource.getConnection();
			
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, Integer.parseInt(regionId));
		
		result =pstmt.executeUpdate();
		
		
		conn.close();
		pstmt.close();
		
		return result;
	}

	//지역 수정 메소드
	@Override
	public int modify(Region region) throws SQLException
	{
		
		int result=0;
		
		String sql = "UPDATE REGION SET REGIONNAME=? WHERE REGIONID = ?";
		
		//DB연결
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1,region.getRegionName());
		pstmt.setInt(2,Integer.parseInt(region.getRegionId()));	
		
		System.out.println(region.getRegionName());
		result = pstmt.executeUpdate();
		
		conn.close();
		pstmt.close();
		
		
		return result;
		
	}
	//지역 찾기 메소드
	@Override
	public Region search(int regionId) throws SQLException
	{
		
		Region result = new Region();
		
		String sql = "SELECT REGIONID,REGIONNAME,DELCHECK FROM REGIONVIEW WHERE REGIONID = ?";
		
		Connection conn = dataSource.getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, regionId);
		
		ResultSet rs = pstmt.executeQuery();
		
		while(rs.next()) 
		{
			
			result.setRegionId(rs.getString("REGIONID"));// 이 부분 String 이여도 상관없겠지?
			result.setRegionName(rs.getString("REGIONNAME"));
			result.setDelCheck(rs.getInt("DELCHECK"));
			//System.out.println("이거 :"+ rs.getInt("DELCHECK"));
		
		}
		
		conn.close();
		pstmt.close();
		rs.close();
		
		
		return result;
	}
	
	

}
