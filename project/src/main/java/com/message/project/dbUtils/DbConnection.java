package com.message.project.dbUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

private static	Connection connection=null;


//DB connection 
public static Connection getConnection() throws SQLException
{
	if(connection !=null)
	{
		return connection;
	}
	else
	{
//		String driver= "com.mysql.cj.jdbc.Driver";
		String url= "jdbc:mysql://localhost:3306/messagedb";
		String user="root";         //Mysql Username
		String password="Vivek@2002";    //Mysql password
		
		try {
//			Class.forName(driver);
			connection=DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	return connection;
}


}
