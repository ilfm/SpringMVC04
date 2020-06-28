package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class PositionInsertController implements Controller
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
		Position position = new Position();
		int result = -1;
		
		//데이터 수신 
		String positionName = request.getParameter("positionName");
		System.out.println(positionName);
		
		
		position.setPositionName(positionName);
		System.out.println("여기 왔니?");
		
		try
		{			
			result = dao.add(position);
			
			mav.addObject("result",result);
			mav.setViewName("redirect:positionlist.action");
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		
		return mav;
	}

}
