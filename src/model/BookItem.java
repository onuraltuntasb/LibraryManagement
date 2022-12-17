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

public class BookItem extends Book {
	



	private static String[] bookFormat = new String[] { "HARDCOVER","PAPERBACK", "AUDIOBOOK", "EBOOK", "NEWSPAPER", "MAGAZINE", "JOURNAL" };
	private static String[] bookStatus = new String[] { "AVAILABLE","REVERSED", "LOANED", "LOST"};

	private String barcode;
	private boolean isReferenceOnly;
	private Date borrowedAt;
	private Date dueDate;
	private Double price;
	private String format;
	private String status;
	private Date dateOfPurchase;
	private Date publicationDate;
	private long rackId;

	public BookItem() {}

	public BookItem(long id, String iSBN, String title, String subject, String publisher, String language,
			int numberOfPages, String barcode, boolean isReferenceOnly, java.sql.Date borrowedAt, Date dueDate,
			Double price, String format, String status, java.sql.Date dateOfPurchase, java.sql.Date publicationDate,
			long rackId) {
		super(id, iSBN, title, subject, publisher, language, numberOfPages);
		this.barcode = barcode;
		this.isReferenceOnly = isReferenceOnly;
		this.borrowedAt = borrowedAt;
		this.dueDate = dueDate;
		this.price = price;
		this.format = format;
		this.status = status;
		this.dateOfPurchase = dateOfPurchase;
		this.publicationDate = publicationDate;
		this.rackId = rackId;
	}

	public ArrayList<BookItem> getBookList() throws SQLException {
	

		ArrayList<BookItem> list = new ArrayList<>();
		BookItem obj;

		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM book");
			while (rs.next()) {
				obj = new BookItem(rs.getLong("id"), rs.getString("ISBN"), rs.getString("title"),
						rs.getString("subject"), rs.getString("publisher"), rs.getString("language"),
						rs.getInt("numberOfPages"), rs.getString("barcode"), rs.getBoolean("is_reference_only"),
						rs.getDate("borrowed_at"), rs.getDate("due_date"), rs.getDouble("price"),
						rs.getString("format"), rs.getString("status"), rs.getDate("date_of_purchase"),
						rs.getDate("publication_date"), rs.getLong("rack_id"));
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 

		return list;
	}
	
	
	
	
	public boolean addBookItem (String title, String subject, String publisher, String language,
	int numberOfPages, String barcode, boolean isReferenceOnly, java.sql.Date borrowedAt, java.sql.Date dueDate,
	double price, String format, String status, java.sql.Date dateOfPurchase, java.sql.Date publicationDate,
	String tag,String rackIdentifier,String libraryName) {

		int fkRackNumber = -1;

		try {
			

			if(isRackThere(rackIdentifier) != null) {
				fkRackNumber = (int) addRackNumber(tag,rackIdentifier);
			}else {
				fkRackNumber = isRackThere(rackIdentifier).getInt("id");
			}

		} catch (Exception e) {
			System.out.println(fkRackNumber);
		}

	

		try {
			String query = "INSERT INTO user (title,subject,publisher,language,numberOfPages,barcode,is_reference_only,borrowed_at,due_date,price,format,status,date_of_purchase,publication_date,rack_id,library_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			st = con.createStatement();
			preparedStatement = con.prepareStatement(query);

			preparedStatement.setString(1, title);
			preparedStatement.setString(2, subject);
			preparedStatement.setString(3, publisher);
			preparedStatement.setString(4, language);
			preparedStatement.setInt(5, numberOfPages);
			preparedStatement.setString(6, barcode);
			preparedStatement.setBoolean(7, isReferenceOnly);
			preparedStatement.setDate(8, new java.sql.Date(borrowedAt.getTime()));
			preparedStatement.setDate(9,new java.sql.Date(dueDate.getTime()));
			preparedStatement.setDouble(10, price);
			preparedStatement.setString(11, format);
			preparedStatement.setString(12, status);
			preparedStatement.setDate(13, new java.sql.Date(dateOfPurchase.getTime()));
			preparedStatement.setDate(14, new java.sql.Date(publicationDate.getTime()));
			preparedStatement.setLong(15, fkRackNumber);
			//TODO dynamic libraryId
			preparedStatement.setLong(16, 1);

			preparedStatement.executeUpdate();

			JOptionPane.showMessageDialog(new JFrame(), " user added!", "Dialog", JOptionPane.INFORMATION_MESSAGE);
			return true;

		} catch (SQLException e1) {

			e1.printStackTrace();
			return false;
		}

	}
	
	public ResultSet isRackThere(String rackIdentifier) throws SQLException {
		
		try {

			String query = "SELECT id FROM rack WHERE location_identifier=?";
			Connection con = conn.connDb();

			PreparedStatement preparedStatement = con.prepareStatement(query);

			preparedStatement.setString(1, rackIdentifier);
			rs = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();
			rs.close();
			con.close();
		}
		
		return rs;
	}
	
	public long addRackNumber(String tag, String rackIdentifier) {

		long rValue = -1;

		try {
			String query = "INSERT INTO rack (number,rackIdentifier VALUES (?,?)";

			st = con.createStatement();
			preparedStatement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, tag);
			preparedStatement.setString(2, rackIdentifier);
		

			int affectedRows = preparedStatement.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating rack failed, no rows affected.");
			}

			try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					rValue = generatedKeys.getLong(1);
				} else {
					throw new SQLException("Creating rack failed, no ID obtained.");
				}
			}

			JOptionPane.showMessageDialog(new JFrame(), " rack added!", "Dialog", JOptionPane.INFORMATION_MESSAGE);

		} catch (SQLException e1) {

			e1.printStackTrace();
		}
		

		return rValue;
	}
	
	

	public boolean checkout() {

		return true;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public boolean getIsReferenceOnly() {
		return isReferenceOnly;
	}

	public void setReferenceOnly(boolean isReferenceOnly) {
		this.isReferenceOnly = isReferenceOnly;
	}

	public Date getBorrowedAt() {
		return borrowedAt;
	}

	public void setBorrowedAt(Date borrowedAt) {
		this.borrowedAt = borrowedAt;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}

	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Long getRackId() {
		return rackId;
	}

	public void setRackId(Long rackId) {
		this.rackId = rackId;
	}

	

	public static String[] getBookFormat() {
		return bookFormat;
	}

	public static void setBookFormat(String[] bookFormat) {
		BookItem.bookFormat = bookFormat;
	}

	public static String[] getBookStatus() {
		return bookStatus;
	}

	public static void setBookStatus(String[] bookStatus) {
		BookItem.bookStatus = bookStatus;
	}

	public void setRackId(long rackId) {
		this.rackId = rackId;
	}



}
