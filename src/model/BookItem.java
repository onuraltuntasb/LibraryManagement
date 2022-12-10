package model;

import java.util.Date;

public class BookItem extends Book {

	private enum BookStatus {
		Available, Reversed, Loaned, Lost
	}

	private enum BookFormat {
		Hardcover, Paperback, Audiobook, Ebook, Newspaper, Magazine, Journal
	}

	private String barcode;
	private boolean isReferenceOnly;
	private Date borrowedAt;
	private Date dueDate;
	private Double price;
	private BookFormat format;
	private BookStatus status;
	private Date dateOfPurchase;
	private Date publicationDate;

	

	public BookItem(Long id, String iSBN, String title, String subject, String publisher, String language,
			int numberOfPages) {
		super(id, iSBN, title, subject, publisher, language, numberOfPages);
		// TODO Auto-generated constructor stub
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

	public boolean isReferenceOnly() {
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

	public BookFormat getFormat() {
		return format;
	}

	public void setFormat(BookFormat format) {
		this.format = format;
	}

	public BookStatus getStatus() {
		return status;
	}

	public void setStatus(BookStatus status) {
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

}
