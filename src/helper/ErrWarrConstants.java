package helper;

public class ErrWarrConstants {
	
	public static final String emailMatchingWarr = "Email pattern is not matching!\n(invalid mail)";
	public static final String emailConstraints = "It allows numeric values from 0 to 9.\n"
			+ "	Both uppercase and lowercase letters from a to z are allowed.\n"
			+ "	Allowed are underscore “_”, hyphen “-“, and dot “.”\n"
			+ "	Dot isn't allowed at the start and end of the local part.\n"
			+ "	Consecutive dots aren't allowed.\n"
			+ "	For the local part, a maximum of 64 characters are allowed.";

	public static final String userNameMatchingWarr = "username pattern is not matching!\n(invalid username)";
	public static final String usernameConstraints = "The username consists of 6 to 30 characters inclusive. If the username\n"
			+ "	consists of less than 6 or greater than 30 characters, then it is an invalid username.\n"
			+ "	The username can only contain alphanumeric characters and underscores (_). Alphanumeric characters describe the character set consisting of lowercase characters [a – z], uppercase characters [A – Z], and digits [0 – 9].\n"
			+ "	The first character of the username must be an alphabetic character, i.e., either lowercase character\n"
			+ "	[a – z] or uppercase character [A – Z].";
	
	public static final String passwordNameMatchingWarr = "username pattern is not matching!\n(invalid username)";
	public static final String passwordNameConstraints = "The username consists of 6 to 30 characters inclusive. If the username\n"
			+ "	consists of less than 6 or greater than 30 characters, then it is an invalid username.\n"
			+ "	The username can only contain alphanumeric characters and underscores (_). Alphanumeric characters describe the character set consisting of lowercase characters [a – z], uppercase characters [A – Z], and digits [0 – 9].\n"
			+ "	The first character of the username must be an alphabetic character, i.e., either lowercase character\n"
			+ "	[a – z] or uppercase character [A – Z].";


	public static final String passwordWarr = "password pattern is not matching!\n(invalid password)";
	public static final String passwordConstraints = "Password must contain at least one digit [0-9].\n"
			+ "Password must contain at least one lowercase Latin character [a-z].\n"
			+ "Password must contain at least one uppercase Latin character [A-Z].\n"
			+ "Password must contain at least one special character like ! @ # & ( ).\n"
			+ "Password must contain a length of at least 8 characters and a maximum of 20 characters.\n"
			+ "";
	
	
	

	
	
	
	
}
