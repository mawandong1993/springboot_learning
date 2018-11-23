package com.note.cloud_note.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.note.cloud_note.vo.User;
import com.note.cloud_note.util.JsonResult;
@Component
public class AccessInterceptor implements HandlerInterceptor {

	public AccessInterceptor() {
	}
	
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String path=request.getRequestURI();
//		System.out.println("Interceptor:"+path);
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("loginUser");
		//如果没有登录就返回错误的json消息
		if(user==null){
			JsonResult result=new JsonResult("需要重新登陆！");
			//利用response对象反馈结果
			response.setContentType("application/json;charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			ObjectMapper mapper=new ObjectMapper();
			String json=mapper.writeValueAsString(result);
			response.getWriter().println(json);
			response.flushBuffer();
			return false;
		}
		//如果登陆了就放过请求
		return true;//放过请求
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
