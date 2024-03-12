package com.java.validation;

public class StringValidations{
	public static Boolean validateString(String str) {
		if (str == null)
			return false;
		if(str.isBlank() || str.isEmpty())
			return false;
		else
			return true;
	}
}
