package com.xxx.yyy.util;

public final class NormalizedUtils {
	private NormalizedUtils() {
	}

	public static String normalizedText(String str) {
		if (str == null)
			return "";

		str = str.toLowerCase();

		str = str.replaceAll("[*`.]", "") // remove ".", "*"
				.replaceAll("(?<=\\d),|:(?=\\d)", ""); // remove "," ":" in
														// numbers
		// TODO implement normalized text
		return str;
	}

	public static String normalizedText(String str, String regex,
			String replacement) {
		if (str == null)
			return "";

		char[] repArr = null;
		if (replacement != null)
			repArr = replacement.toCharArray();

		char[] regArr = null;
		if (regex != null)
			regArr = regex.toCharArray();

		char[] array = str.toCharArray();
		int len = array.length;
		for (int i = 0; i < len; i++) {
			if (checkCondition(array[i], regex))
				changeArray(array, replacement);
		}

		return null;
	}

	private static void changeArray(char[] array, String replacement) {
		// TODO implementation change array

	}

	private static boolean checkCondition(char c, String regex) {
		// TODO implementation check condition  
		return false;
	}

	public static void main(String[] args) {
		System.out.println(normalizedText(null));
		System.out.println(normalizedText("h2&u*a*	.ma,anh"));
		System.out.println(normalizedText("2:0:0: 1,2,3,4,5,"));
	}

}
