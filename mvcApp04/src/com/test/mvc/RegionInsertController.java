package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class RegionInsertController implements Controller
{
	private IRegionDAO dao;
		
	public void setDao(IRegionDAO dao)
	{
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		Region region = new Region();
		int result = -1;
		
		//데이터 수신 
		String regionName = request.getParameter("regionName");
		System.out.println(regionName);
		
		
		region.setRegionName(regionName);
		System.out.println("여기 왔니?");
		
		try
		{			
			result = dao.add(region);
			
			mav.addObject("result",result);
			mav.setViewName("redirect:regionlist.action");
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		
		return mav;
	}

}
