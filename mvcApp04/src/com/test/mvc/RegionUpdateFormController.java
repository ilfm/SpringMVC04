package com.test.mvc;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class RegionUpdateFormController implements Controller
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
		
		int regionId =Integer.parseInt(request.getParameter("regionId"));
		region = dao.search(regionId);

		mav.addObject("region",region);
		mav.setViewName("RegionUpdateForm");
		
		
		return mav;
	}

}
