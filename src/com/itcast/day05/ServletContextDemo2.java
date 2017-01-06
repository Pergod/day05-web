package com.itcast.day05;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itcast.dao.userDao;

public class ServletContextDemo2 extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		getProperties(resp);
//		tradtionWay(resp);
		mixWay(resp);
		
		/*
		 * 非Servlet程序读取文件，最好不要传递ServletContext，这样会导致层与层之前的代码混乱。
		 * 使用类加载器 -- ClassLoader
		 */
		new userDao().update();
		
	}

	private void mixWay(HttpServletResponse resp) throws FileNotFoundException, IOException {
		ServletContext context=this.getServletContext();
		/*
		 * 获取文件的绝对路径
		 */
		String path=context.getRealPath("/WEB-INF/classes/connect.properties");
		/*
		 * 获取文件名
		 */
		String filename=path.substring(path.lastIndexOf("\\")+1);
		System.out.println(filename);
		FileInputStream in=new FileInputStream(new File(path));
		
		Properties properties=new Properties();//以map集合形式保存
		properties.load(in);
		String url=properties.getProperty("url");
		String username=properties.getProperty("username");
		String password=properties.getProperty("password");
		resp.getWriter().write("url:"+url+"username:"+username+"password"+password);
	}

	private void tradtionWay(HttpServletResponse resp) throws FileNotFoundException, IOException {
		FileInputStream in=new FileInputStream("此处的文件目录是相对于java虚拟机的目录");
		Properties properties=new Properties();//以map集合形式保存
		properties.load(in);
		String url=properties.getProperty("url");
		String username=properties.getProperty("username");
		String password=properties.getProperty("password");
		resp.getWriter().write("url:"+url+"username:"+username+"password"+password);
	}
	
	/*
	 * 此方法只适用于读取文件，无法获取文件名
	 */
	private void getProperties(HttpServletResponse resp) throws IOException {
		ServletContext context=this.getServletContext();
		InputStream in= context.getResourceAsStream("/WEB-INF/classes/connect.properties");
		Properties properties=new Properties();//以map集合形式保存
		properties.load(in);
		String url=properties.getProperty("url");
		String username=properties.getProperty("username");
		String password=properties.getProperty("password");
		resp.getWriter().write("url:"+url+"username:"+username+"password"+password);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
