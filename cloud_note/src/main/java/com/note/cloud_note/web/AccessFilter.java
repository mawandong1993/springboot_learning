package com.note.cloud_note.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.note.cloud_note.vo.User;

/**
 * Servlet Filter implementation class AccessFilter
 */
public class AccessFilter implements Filter {

    public AccessFilter() {
    }
	public void destroy() {
	}
	
	private String login="/log_in.html";
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		HttpSession session=req.getSession();
		//放过login_in.html
		String path=req.getRequestURI();
//		System.out.println("access:"+path);
		if(path.endsWith(login)){
			chain.doFilter(request, response);
			return;
		}
		//放过alert_error.html
		if(path.endsWith("alert_error.html")){
			chain.doFilter(request, response);
			return;
		}
		//检查用户是否登录，如果登录就放过，没有登录就重定向到登录页
		User user=(User) session.getAttribute("loginUser");
		//如果登录就放过
		if(user==null){
			//重定向到登录页,使用绝对路径
			res.sendRedirect(req.getContextPath()+login);
			return;
		}
		
		chain.doFilter(request, response);
	}
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
