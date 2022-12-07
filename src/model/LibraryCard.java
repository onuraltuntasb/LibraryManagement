package model;

import java.util.Random;

public class LibraryCard {

	private int id;
	private String cardNumber;
	private String bardcode;
	private String issuedAt;
	private boolean active;

	public LibraryCard() {
	}

	

	public LibraryCard(int id, String cardNumber, String bardcode, String issuedAt, boolean active) {
		super();
		this.id = id;
		this.cardNumber = cardNumber;
		this.bardcode = bardcode;
		this.issuedAt = issuedAt;
		this.active = active;
	}



	public static  String createBarcode() {

		// FEATURE barcode could be change with real barcode image
		// its just for demo string
		// givenUsingJava8_whenGeneratingRandomAlphanumericString

		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 10;
		Random random = new Random();

		String generatedString = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();

		System.out.println(generatedString);

		return generatedString;
	}
	


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getBardcode() {
		return bardcode;
	}

	public void setBardcode(String bardcode) {
		this.bardcode = bardcode;
	}

	public String getIssuedAt() {
		return issuedAt;
	}

	public void setIssuedAt(String issuedAt) {
		this.issuedAt = issuedAt;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
