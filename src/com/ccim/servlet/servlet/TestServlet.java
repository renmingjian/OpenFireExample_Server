package com.ccim.servlet.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
public class TestServlet extends BaseHttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void handleGet(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(request.getParameter("type"));
		
	}
       
    

}
