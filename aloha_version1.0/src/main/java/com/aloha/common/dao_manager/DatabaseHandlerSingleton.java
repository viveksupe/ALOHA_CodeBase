package com.aloha.common.dao_manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandlerSingleton {

	private static DatabaseHandlerSingleton db = null;
	private Connection con = null;
	private Statement stmt = null;

	private DatabaseHandlerSingleton() {
		try {
			// instantiate com.mysql.jdbc.Driver. This object registers itself
			// with the DriverManager
			Class.forName("com.mysql.jdbc.Driver");

			// ask the DriverManager for a connection to the schema mysql with
			// root privilleges
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/mysql", "root", "root");

			stmt = con.createStatement();

		} catch (SQLException ex) {
			System.out.println("cannot query the database");
		} catch (ClassNotFoundException ex) {
			System.out.println("cannot connect to MySQL database");
		}
	}

	public static DatabaseHandlerSingleton create() {
		if (db == null) {
			db = new DatabaseHandlerSingleton();
		}
		return db;
	}

	public ResultSet select(String sql) {
		try {
			return stmt.executeQuery(sql);
		} catch (SQLException ex) {
			return null;
		}
	}

	public void close() {
		if (con != null) {
			try {
				stmt.close();
				con.close();
			} catch (Exception e) {
			}
		}
	}

	public void finalize() {
		close();
	}

}

/*
 * import java.sql.*;
 * 
 * 
 * public class DatabaseHandler { private static String driverName =
 * "com.mysql.jdbc.Driver"; private static String url =
 * "jdbc:mysql://localhost:3306/"; private static String dbName = "testdb";
 * private static String userName = "root"; private static String password =
 * "root"; private static Connection connection = null; public void configure()
 * { driverName = "com.mysql.jdbc.Driver"; url = "jdbc:mysql://localhost:3306/";
 * dbName = "testdb"; userName = "root"; password = "root"; } public static
 * Connection getConnectionObject(){ while(connection==null) { try {
 * Class.forName(driverName); connection =
 * DriverManager.getConnection(url+dbName,userName,password); }
 * catch(ClassNotFoundException ex) {
 * System.out.println("ERROR: Unable to Connect to Database."); }catch
 * (SQLException ex) {
 * System.out.println("ERROR: Unable to Connect to Database."); } } try {
 * connection.setAutoCommit(true);
 * connection.setHoldability(ResultSet.CLOSE_CURSORS_AT_COMMIT); } catch
 * (SQLException ex) { ex.printStackTrace(); } return connection; }
 * 
 * 
 * }
 */
