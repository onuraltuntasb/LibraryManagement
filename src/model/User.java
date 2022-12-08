package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import helper.DBConnection;

public class User {

	private int id, addressId, libraryCardId, totalBooksCheckedout;
	private String name, password, userType, email, phone, dateOfMembership, status;
	
	private static String[] userTypes = new String[] { "MEMBER", "LIBRARIAN", "ADMIN" };

	private DBConnection conn = new DBConnection();
	private Statement st = null;
	private ResultSet rs = null;

	private Connection con = conn.connDb();
	private PreparedStatement preparedStatement = null;

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

	public boolean addUser(String street, String city, String state, String zipcode, String country, String password,
			String cbStaus, String name, String email, String phone, String cbUserType, Date createdAt, String barcode,
			Date libraryCardCreatedAt, int active) {

		int fkAddressId = -1;
		int fkLibraryCardId = -1;

		try {
			fkAddressId = (int) addUserAddress(street, city, state, zipcode, country);

		} catch (Exception e) {
			System.out.println(fkAddressId);
		}

		try {
			fkLibraryCardId = (int) addUserLibraryCard(barcode, libraryCardCreatedAt, active);
		} catch (Exception e) {
			System.out.println(fkLibraryCardId);
		}

		try {
			String query = "INSERT INTO user (password,status,name,email,phone,user_type,address_id,date_of_membership,total_books_checkedout,library_card_id) VALUES (?,?,?,?,?,?,?,?,?,?)";
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);

			preparedStatement.setString(1, password);
			preparedStatement.setString(2, cbStaus);
			preparedStatement.setString(3, name);
			preparedStatement.setString(4, email);
			preparedStatement.setString(5, phone);
			preparedStatement.setString(6, cbUserType);
			preparedStatement.setInt(7, fkAddressId);
			preparedStatement.setDate(8, new java.sql.Date(createdAt.getTime()));
			preparedStatement.setInt(9, 0);
			preparedStatement.setInt(10, fkLibraryCardId);

			preparedStatement.executeUpdate();

			JOptionPane.showMessageDialog(new JFrame(), " user added!", "Dialog", JOptionPane.INFORMATION_MESSAGE);
			return true;

		} catch (SQLException e1) {

			e1.printStackTrace();
			return false;
		}

	}

	public long addUserAddress(String streetAdress, String city, String state, String zipcode, String country) {

		long rValue = -1;

		try {
			String query = "INSERT INTO address (street_address,city,state,zipcode,country) VALUES (?,?,?,?,?)";

			st = con.createStatement();
			preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, streetAdress);
			preparedStatement.setString(2, city);
			preparedStatement.setString(3, state);
			preparedStatement.setString(4, zipcode);
			preparedStatement.setString(5, country);

			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating address failed, no rows affected.");
			}

			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					rValue = generatedKeys.getLong(1);
				} else {
					throw new SQLException("Creating address failed, no ID obtained.");
				}
			}

			JOptionPane.showMessageDialog(new JFrame(), " address added!", "Dialog", JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		return rValue;
	}

	public long addUserLibraryCard(String barcode, Date createdDate, int active) {

		long rValue = -1;

		try {
			String query = "INSERT INTO library_card (barcode,issuedat,active) VALUES (?,?,?)";

			st = con.createStatement();
			preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, barcode);
			preparedStatement.setDate(2, new java.sql.Date(createdDate.getTime()));
			preparedStatement.setInt(3, active);

			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating library_card failed, no rows affected.");
			}

			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					rValue = generatedKeys.getLong(1);
				} else {
					throw new SQLException("Creating library_card failed, no ID obtained.");
				}
			}

			JOptionPane.showMessageDialog(new JFrame(), "library_card added!", "Dialog",
					JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException e1) {

			e1.printStackTrace();
		}

		return rValue;

	}

	public boolean deleteUser(int id, int delUserAddressId, int delUserLibraryCardId) {

		try {
			String query = "DELETE FROM user WHERE id=?";
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);

			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

			JOptionPane.showMessageDialog(new JFrame(), " user deleted!", "Dialog", JOptionPane.INFORMATION_MESSAGE);

			// TODO can be added del or keep address feature

			deleteAddress(delUserAddressId);

			deleteLibraryCard(delUserLibraryCardId);
			return true;

		} catch (SQLException e1) {

			e1.printStackTrace();
			return false;
		}

	}

	public boolean updateUser(String password, String status, String name, String email, String phone, String userType,
			int tbc, int id) {

		// except address, library_card and dom because no need to update so much data
		// at admin panel. Its job of librarian

		try {
			String query = "UPDATE user SET password=?,status=?,name=?,email=?,phone=?,user_type=?,total_books_checkedout=? WHERE id=?";
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);

			preparedStatement.setString(1, password);
			preparedStatement.setString(2, status);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, phone);
			preparedStatement.setString(5, userType);
			preparedStatement.setInt(6, tbc);
			preparedStatement.setInt(7, id);

			preparedStatement.executeUpdate();

			JOptionPane.showMessageDialog(new JFrame(), " user updated!", "Dialog", JOptionPane.INFORMATION_MESSAGE);

			return true;

		} catch (SQLException e1) {

			e1.printStackTrace();
			return false;
		}
	}

	public boolean deleteAddress(int addressId) {

		try {
			String query = "DELETE FROM address WHERE id=?";
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);

			preparedStatement.setInt(1, addressId);
			preparedStatement.executeUpdate();

			JOptionPane.showMessageDialog(new JFrame(), " address deleted!", "Dialog", JOptionPane.INFORMATION_MESSAGE);

			return true;

		} catch (SQLException e1) {

			e1.printStackTrace();
			return false;
		}

	}

	public boolean deleteLibraryCard(int libraryCardId) {

		try {
			String query = "DELETE FROM library_card WHERE id=?";
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);

			preparedStatement.setInt(1, libraryCardId);
			preparedStatement.executeUpdate();

			JOptionPane.showMessageDialog(new JFrame(), " library card deleted!", "Dialog",
					JOptionPane.INFORMATION_MESSAGE);

			return true;

		} catch (SQLException e1) {

			e1.printStackTrace();
			return false;
		}

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

	public static String[] getUserTypes() {
		return userTypes;
	}

	public static void setUserTypes(String[] userTypes) {
		User.userTypes = userTypes;
	}

}
