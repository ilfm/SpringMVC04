/*-----------------------------------------------
  #16. EmployeeInsertFormController.java
  - 사용자 정의 컨트롤러 클래스
  - 직원 데이터 입력 폼 요청에 대한 액션 처리 
  - DAO 객체에 대한 의존성 주입(DI)를 위한 준비
    → 인터페이스 자료형, setter 메소드 정의.
  -----------------------------------------------  
 * */

package com.test.mvc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class EmployeeInsertFormController implements Controller
{

	private	IEmployeeDAO dao;
	

	public void setDao(IEmployeeDAO dao)
	{
		this.dao = dao;
	}
	//모두 텍스트 박스이면 리스트가(셀렉트 박스,) 있기 때문에 
	@Override
	public ModelAndView handleRequest(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception
	{
		
		ModelAndView mav = new ModelAndView();
		ArrayList<Region> regionList = new ArrayList<Region>();
		ArrayList<Department> departmentList = new ArrayList<Department>();
		ArrayList<Position> positionList = new ArrayList<Position>();
		
		try
		{
			regionList = dao.regionList();
			departmentList = dao.departmentList();
			positionList = dao.positionList();
			
			//객체 추가하기
			mav.addObject("regionList", regionList);
			mav.addObject("departmentList", departmentList);
			mav.addObject("positionList", positionList);
			
			mav.setViewName("EmployeeInserForm");
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
			
		}
		
		
		
		return mav;
	}

}
