package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class RegionUpdateController implements Controller
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
		
		String regionId = request.getParameter("regionId");
		String regionName = request.getParameter("regionName");
		
		System.out.println("1233456788");
		try
		{
			Region region = new Region();
			region.setRegionId(regionId);
			region.setRegionName(regionName);
			
			int result = dao.modify(region);
			
			mav.setViewName("redirect:regionlist.action");
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		
		
		
		return mav;
	}

}
