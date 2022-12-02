package helper;

import java.security.SecureRandom;

public class PasswordOps {
	
	public PasswordOps() {}
	
	private static final String ALPHA_CAPS ="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
	private static final String NUMERIC ="0123456789";
	private static final String SPECIAL_CHARS = "!@#$%^&*_=+-/\"";
	
	
	private static final SecureRandom random = new SecureRandom();
	private static final char[] dic = (ALPHA_CAPS + ALPHA + NUMERIC + SPECIAL_CHARS).toCharArray();
	
	
	

	public static String passwordGenerator(int len) {
		
	
		String result ="";
		for (int i = 0; i < len; i++) {
            int index = random.nextInt(dic.length);
            result += dic[index];
        }
		
		return result;
	}

}
