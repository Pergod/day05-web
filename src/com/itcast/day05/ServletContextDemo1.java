package com.itcast.day05;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletContextDemo1 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		ServletContext context=this.getServletContext();
		/*
		 * 转发时，数据最好不要通过Context域携带
		 */
//		context.setAttribute("data", "servlet context value");
//		context.getRequestDispatcher("/index.jsp").forward(req, resp);;
		
		req.setAttribute("data", "request value");
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
