package com.itcast.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class userDao {
	//只加载一次.无法获取更新后的数据。 
	private static Properties dbconfig=new Properties();
	static{
		ClassLoader loader=userDao.class.getClassLoader();
		InputStream inputStream=loader.getResourceAsStream("connect.properties");
		try {
			dbconfig.load(inputStream);
		} catch (IOException e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	/*
	 * 当读取文件的程序不是Servlet的话，需通过类装载器读取
	 */
	public void update() throws IOException {
//		ClassLoader loader=userDao.class.getClassLoader();
//		
//		/*
//		 * 以流的方式读取的话，文件不宜过大。类装载器装载文件只装载一次，无法获取更新后的数据。
//		 */
//		InputStream inputStream=loader.getResourceAsStream("connect.properties");
//		
//		Properties properties=new Properties();//以map集合形式保存
//		properties.load(inputStream);
		String url=dbconfig.getProperty("url");
		String username=dbconfig.getProperty("username");
		String password=dbconfig.getProperty("password");
		System.out.println("url:"+url+"username:"+username+"password"+password);
	}
	
	public void find() throws IOException {
		ClassLoader loader=userDao.class.getClassLoader();
		/*
		 * 通过类装载的方式获取文件位置
		 */
		String path=loader.getResource("connect.properties").getPath();
		/*
		 * 再以传统方式读取文件
		 */
		FileInputStream inputStream=new FileInputStream(path);
		
		Properties properties=new Properties();//以map集合形式保存
		properties.load(inputStream);
		String url=dbconfig.getProperty("url");
		String username=dbconfig.getProperty("username");
		String password=dbconfig.getProperty("password");
		System.out.println("url:"+url+"username:"+username+"password"+password);
	}
}
