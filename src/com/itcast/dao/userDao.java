package com.itcast.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class userDao {
	//ֻ����һ��.�޷���ȡ���º�����ݡ� 
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
	 * ����ȡ�ļ��ĳ�����Servlet�Ļ�����ͨ����װ������ȡ
	 */
	public void update() throws IOException {
//		ClassLoader loader=userDao.class.getClassLoader();
//		
//		/*
//		 * �����ķ�ʽ��ȡ�Ļ����ļ����˹�����װ����װ���ļ�ֻװ��һ�Σ��޷���ȡ���º�����ݡ�
//		 */
//		InputStream inputStream=loader.getResourceAsStream("connect.properties");
//		
//		Properties properties=new Properties();//��map������ʽ����
//		properties.load(inputStream);
		String url=dbconfig.getProperty("url");
		String username=dbconfig.getProperty("username");
		String password=dbconfig.getProperty("password");
		System.out.println("url:"+url+"username:"+username+"password"+password);
	}
	
	public void find() throws IOException {
		ClassLoader loader=userDao.class.getClassLoader();
		/*
		 * ͨ����װ�صķ�ʽ��ȡ�ļ�λ��
		 */
		String path=loader.getResource("connect.properties").getPath();
		/*
		 * ���Դ�ͳ��ʽ��ȡ�ļ�
		 */
		FileInputStream inputStream=new FileInputStream(path);
		
		Properties properties=new Properties();//��map������ʽ����
		properties.load(inputStream);
		String url=dbconfig.getProperty("url");
		String username=dbconfig.getProperty("username");
		String password=dbconfig.getProperty("password");
		System.out.println("url:"+url+"username:"+username+"password"+password);
	}
}
