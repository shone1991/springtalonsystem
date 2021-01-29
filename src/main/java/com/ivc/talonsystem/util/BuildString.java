package com.ivc.talonsystem.util;

public class BuildString {
	
	public static StringBuilder stringbuilder;
	public static String buildstring(String[] strs) {
		stringbuilder=new StringBuilder();
		for(int i=0; i<strs.length; i++) {
			stringbuilder.append(strs[i]);
			stringbuilder.append(" ");
		}
		return stringbuilder.toString();
	}
	public static void main(String [] args) {
		String[] strs= new String []{"a","b"};
		BuildString.buildstring(strs);
		System.out.println(BuildString.buildstring(strs));
	}
}
