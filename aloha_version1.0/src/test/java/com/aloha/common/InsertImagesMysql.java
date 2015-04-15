package com.aloha.common;
import java.sql.*;
import java.io.*;
public class InsertImagesMysql{
	public static void main(String[] args){
		System.out.println("Insert Image Example!");
		String driverName = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "testdb";
		String userName = "root";
		String password = "root";
		Connection con = null;
		try{
		   Class.forName(driverName);
		   con = DriverManager.getConnection(url+dbName,userName,password);
		   Statement st = con.createStatement();
		   File imgfile = new File("E://iu acad//pic.jpg");
		  
		  FileInputStream fin = new FileInputStream(imgfile);
		 
		   PreparedStatement pre =
		   con.prepareStatement("insert into images values(?, ?,?,?)");
		 
		   pre.setInt(1,1);
		   pre.setInt(2,1);
		   pre.setBinaryStream(3,(InputStream)fin,(int)imgfile.length());
		   pre.setInt(4,3);
		   pre.executeUpdate();
		   System.out.println("Successfully inserted the file into the database!");

		   pre.close();
		   con.close(); 
		}catch (Exception e1){
			System.out.println(e1.getMessage());
		}
	}
}