package com.test.mvc;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PositionAjaxController implements Controller
{
	private IPositionDAO dao;
	
	
	public void setDao(IPositionDAO dao)
	{
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		
		//데이터 수신 (EmployeeInserForm.jsp 로 부터 positionId 수신)
		String positionName = request.getParameter("positionName");
		
		int check = -1;
		
		
		ArrayList<Position> result = new ArrayList<Position>();
		
		try
		{
			//기본급 반환
			result = dao.list();
			
			
			for(int i = 0; i < result.size(); i++) 
			{
				
				if(positionName.equals(result.get(i).getPositionName())) 
				{
					check = 1;
					break;
				}
		
				
			}
			System.out.println(result.get(1).getPositionId());
			
			
			mav.addObject("check",check);
			
			mav.setViewName("PositionAjax");
			
			
			
		} catch (Exception e)
		{
			
			System.out.println(e.toString());
		}

		
		return mav;
	}

}
