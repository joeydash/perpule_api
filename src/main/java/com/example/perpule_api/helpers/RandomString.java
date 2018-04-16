package com.example.perpule_api.helpers;


import java.security.SecureRandom;

public class RandomString {
	private final static String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static SecureRandom rnd = new SecureRandom();

		public static String randomString( int len ){
		   StringBuilder sb = new StringBuilder( len );
		   for( int i = 0; i < len; i++ ) 
		      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
		   return sb.toString();
		}
		public static String jumble_password_with_random_string(String password,String random_string) {
			return password+random_string;
		}
		public static String hash_password(String password,String random_string) {
			return hashString(jumble_password_with_random_string(password,random_string));
		}
		public static String hashString(String string) {
			return String.valueOf(string.hashCode());
		}
}
