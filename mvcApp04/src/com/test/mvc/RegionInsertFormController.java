package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class RegionInsertFormController implements Controller
{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse resonse) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("RegionInsertForm");	
		
		
		return mav;
	}

}
