package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import helper.DBConnection;

public class BookItem extends Book {

	private enum BookStatus {
		AVAILABLE, REVERSED, LOANED, LOST
	}

	private enum BookFormat {
		HARDCOVER, PAPERBACK, AUDIOBOOK, EBOOK, NEWSPAPER, MAGAZINE, JOURNAL
	}

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
		DBConnection conn = new DBConnection();
		Connection con = conn.connDb();
		Statement st = null;
		ResultSet rs = null;

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
		} finally {
			st.close();
			rs.close();
			con.close();
		}

		return list;
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

}
