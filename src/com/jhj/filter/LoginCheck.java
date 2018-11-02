package com.jhj.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jhj.member.MemberDTO;

/**
 * Servlet Filter implementation class LoginCheck
 */
@WebFilter("/LoginCheck")
public class LoginCheck implements Filter {

	/**
	 * Default constructor.
	 */
	public LoginCheck() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		MemberDTO memberDTO = (MemberDTO) session.getAttribute("member");
		if (memberDTO != null) {
			chain.doFilter(request, response);
		}else {
			((HttpServletResponse)response).sendRedirect("../index.jsp");
			//((HttpServletResponse)response).sendRedirect("../WEB-INF/view/member/memberLogin.jsp");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
