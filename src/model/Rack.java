package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import helper.DBConnection;

public class Rack {
	private int id;
	private String tag;
	private String locationIdentifer;
	
	DBConnection conn = new DBConnection();
	Connection con = conn.connDb();
	Statement st = null;
	ResultSet rs = null;
	
	public Rack(int id, String tag, String locationIdentifer) {
		super();
		this.id = id;
		this.tag = tag;
		this.locationIdentifer = locationIdentifer;
	}
	
	public Rack() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Rack> getRackIdentifiers() throws SQLException {


		ArrayList<Rack> list = new ArrayList<>();
		Rack obj;

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM rack");
			while (rs.next()) {
				obj = new Rack(rs.getInt("id"),rs.getString("tag"),rs.getString("location_identifier"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return list;
	}


	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}


	public String getLocationIdentifer() {
		return locationIdentifer;
	}

	public void setLocationIdentifer(String locationIdentifer) {
		this.locationIdentifer = locationIdentifer;
	}

}
