package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import helper.DBConnection;

public class User {

	private int id, addressId, libraryCardId, totalBooksCheckedout;
	private String name, password, userType, email, phone, dateOfMembership, status;

	public User() {

	}

	public User(int id, int addressId, int libraryCardId, int totalBooksCheckedout, String name, String password,
			String userType, String email, String phone, String dateOfMembership, String status) {
		super();
		this.id = id;
		this.addressId = addressId;
		this.libraryCardId = libraryCardId;
		this.totalBooksCheckedout = totalBooksCheckedout;
		this.name = name;
		this.password = password;
		this.userType = userType;
		this.email = email;
		this.phone = phone;
		this.dateOfMembership = dateOfMembership;
		this.status = status;
	}

	public ArrayList<User> getUserList() throws SQLException {
		DBConnection conn = new DBConnection();
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;

		ArrayList<User> list = new ArrayList<>();
		User obj;

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM user");
			while (rs.next()) {
				obj = new User(rs.getInt("id"), rs.getInt("address_id"), rs.getInt("library_card_id"),
						rs.getInt("total_books_checkedout"), rs.getString("name"), rs.getString("password"),
						rs.getString("user_type"), rs.getString("email"), rs.getString("phone"),
						rs.getString("date_of_membership"), rs.getString("status"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
		}

		return list;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public int getLibraryCardId() {
		return libraryCardId;
	}

	public void setLibraryCardId(int libraryCardId) {
		this.libraryCardId = libraryCardId;
	}

	public int getTotalBooksCheckedout() {
		return totalBooksCheckedout;
	}

	public void setTotalBooksCheckedout(int totalBooksCheckedout) {
		this.totalBooksCheckedout = totalBooksCheckedout;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDateOfMembership() {
		return dateOfMembership;
	}

	public void setDateOfMembership(String dateOfMembership) {
		this.dateOfMembership = dateOfMembership;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
