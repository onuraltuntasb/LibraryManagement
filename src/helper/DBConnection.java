package helper;

import java.sql.*;

public class DBConnection {
	Connection c = null;
	
	public DBConnection() {}
	
	public Connection connDb() {
		try {
			this.c = DriverManager.getConnection("jdbc:mysql://localhost:3306/password_manager","root","123456789");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return c;
		
	}
	
	
}
