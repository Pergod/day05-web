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
		 * ��Servlet�����ȡ�ļ�����ò�Ҫ����ServletContext�������ᵼ�²����֮ǰ�Ĵ�����ҡ�
		 * ʹ��������� -- ClassLoader
		 */
		new userDao().update();
		
	}

	private void mixWay(HttpServletResponse resp) throws FileNotFoundException, IOException {
		ServletContext context=this.getServletContext();
		/*
		 * ��ȡ�ļ��ľ���·��
		 */
		String path=context.getRealPath("/WEB-INF/classes/connect.properties");
		/*
		 * ��ȡ�ļ���
		 */
		String filename=path.substring(path.lastIndexOf("\\")+1);
		System.out.println(filename);
		FileInputStream in=new FileInputStream(new File(path));
		
		Properties properties=new Properties();//��map������ʽ����
		properties.load(in);
		String url=properties.getProperty("url");
		String username=properties.getProperty("username");
		String password=properties.getProperty("password");
		resp.getWriter().write("url:"+url+"username:"+username+"password"+password);
	}

	private void tradtionWay(HttpServletResponse resp) throws FileNotFoundException, IOException {
		FileInputStream in=new FileInputStream("�˴����ļ�Ŀ¼�������java�������Ŀ¼");
		Properties properties=new Properties();//��map������ʽ����
		properties.load(in);
		String url=properties.getProperty("url");
		String username=properties.getProperty("username");
		String password=properties.getProperty("password");
		resp.getWriter().write("url:"+url+"username:"+username+"password"+password);
	}
	
	/*
	 * �˷���ֻ�����ڶ�ȡ�ļ����޷���ȡ�ļ���
	 */
	private void getProperties(HttpServletResponse resp) throws IOException {
		ServletContext context=this.getServletContext();
		InputStream in= context.getResourceAsStream("/WEB-INF/classes/connect.properties");
		Properties properties=new Properties();//��map������ʽ����
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
