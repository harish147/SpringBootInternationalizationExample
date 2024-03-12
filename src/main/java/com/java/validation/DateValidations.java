package com.java.validation;

import java.time.LocalDate;

public class DateValidations {
	public static Boolean dueDateValidation(LocalDate date) {
		if (date == null)
			return false;
		else
			return date.isAfter(LocalDate.now()) || date.isEqual(LocalDate.now()) ? true : false;
	}
}
