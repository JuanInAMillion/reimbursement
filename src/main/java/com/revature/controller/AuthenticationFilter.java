package com.revature.controller;

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

/**
 * Servlet Filter implementation class FrontendAuthenticationFilter
 */

//@WebFilter("/*")
public class AuthenticationFilter implements Filter {
    private static final String[] resShared = {
            "images", "scripts", "styles"
    };

    /**
     * Default constructor. 
     */
    public AuthenticationFilter() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;		
		HttpSession session = httpRequest.getSession(false);
		
		final String URI = httpRequest.getRequestURI();
		final String RESOURCE = URI.replace("/reimbursement/", "");
		
		System.out.println("resource in filter :"+ RESOURCE);
		
		if(session.getAttribute("employee") != null && RESOURCE.startsWith("pages/employee")){
			chain.doFilter(request, response);
		}else if(session.getAttribute("manager") != null && RESOURCE.startsWith("pages/manager")) {
			chain.doFilter(request, response);			
		}else if(RESOURCE.equals("index.html")){
			httpResponse.sendRedirect("/reimbursement/index.html");
		}else {
			httpResponse.setStatus(404);
		}
	}
	
    private boolean isResShared(String res) {
        for (String share : resShared) {
    		System.out.println("resource: " +res+" in filter :"+ share);        	
        	if(res.startsWith(share))
        		return true;
        }
        return false;
    }
    
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
