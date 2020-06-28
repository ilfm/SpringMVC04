package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class DepartmentInsertController implements Controller
{
	private IDepartmentDAO dao;
		
	public void setDao(IDepartmentDAO dao)
	{
		this.dao = dao;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();
		Department department = new Department();
		int result = -1;
		
		//데이터 수신 
		String departmentName = request.getParameter("departmentName");
		System.out.println(departmentName);
		
		
		department.setDepartmentName(departmentName);
		System.out.println("여기 왔니?");
		
		try
		{			
			result = dao.add(department);
			
			mav.addObject("result",result);
			mav.setViewName("redirect:departmentlist.action");
			
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		
		
		return mav;
	}

}
