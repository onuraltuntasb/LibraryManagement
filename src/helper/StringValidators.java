package helper;

import java.util.regex.Pattern;

public class StringValidators {
	
	public static final String emailReg = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
	public static final String usernameReg = "^[A-Za-z]\\w{5,29}$";
	public static final String passwordReg = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
	
	
	public static boolean patternMatches (String s, String regexPattern) {
	    return Pattern.compile(regexPattern)
	      .matcher(s)
	      .matches();
	}
	
}
