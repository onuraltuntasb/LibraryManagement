package helper;

import java.sql.*;

public class DBConnection {
	Connection c = null;
	
	public DBConnection() {}
	
	public Connection connDb() {
		try {
			this.c = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management","root","root");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return c;
		
	}
	
	
}
