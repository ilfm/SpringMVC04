/*-----------------------------------------------
 	#24.LoginFormController.java
	- 사용자 정의 컨트롤러 클래스
	- 로그인 폼 액션 수행  및 해당 액션 수행 이후
	  → 아마도.. 사용자 최초 요청 페이지.
	- 단순히 로그인 폼이 구성된 페이지(LoginForm.jsp)를
	  뷰(view)로 제시하는 페이지
-----------------------------------------------  
* */
package com.test.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class LoginFormController implements Controller
{
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ModelAndView mav = new ModelAndView();
				
		mav.setViewName("LoginForm");	
		
		return mav;
	}

}
