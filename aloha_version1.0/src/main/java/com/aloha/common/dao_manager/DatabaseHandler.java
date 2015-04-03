package com.aloha.common.dao_manager;

import java.sql.*;


public class DatabaseHandler {
	private static String driverName = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/";
    private static String dbName = "testdb";
    private static String userName = "root";
    private static String password = "root";
    private static Connection connection = null;
	public void configure()
	{
		driverName = "com.mysql.jdbc.Driver";
        url = "jdbc:mysql://localhost:3306/";
        dbName = "testdb";
        userName = "root";
        password = "root";
	}
	 public static Connection getConnectionObject(){
	        while(connection==null) {
	            try
	            {
	                Class.forName(driverName);
	                connection = DriverManager.getConnection(url+dbName,userName,password);
	            }
	            catch(ClassNotFoundException ex)
	            {
	            	System.out.println("ERROR: Unable to Connect to Database.");
	            }catch (SQLException ex) {
	            	System.out.println("ERROR: Unable to Connect to Database.");
	            }
	        }
	        try {
	            connection.setAutoCommit(true);
	            connection.setHoldability(ResultSet.CLOSE_CURSORS_AT_COMMIT);
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        return connection;
	  }


}
