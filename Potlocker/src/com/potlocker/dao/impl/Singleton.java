package com.potlocker.dao.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Singleton {
	private static Connection con;
	
	static Properties props = new Properties();
	static String username;
	static String password;
	static String url;
	
	public static void setProps() throws FileNotFoundException, IOException {
		props.load(new FileInputStream("connection.properties"));

		username = props.getProperty("username");
		password = props.getProperty("password");
		url = props.getProperty("url");
	
	}


	private Singleton() {
	}
	
	public static synchronized Connection getConnection1() throws SQLException, FileNotFoundException, IOException {
		if(con == null) {
			setProps();
			con = DriverManager.getConnection(url, username, password);
		}
		return con;
	}
	
	public static Connection getConnection2() throws SQLException, FileNotFoundException, IOException {
		if(con == null) {
			setProps();
			con = DriverManager.getConnection(url, username, password);
		}
		return con;
	}
}
