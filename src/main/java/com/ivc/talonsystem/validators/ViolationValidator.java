package com.ivc.talonsystem.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.ivc.talonsystem.entity.Violation;

@Component
public class ViolationValidator implements Validator {

//	public ViolationValidator(){}

	@Override
	public boolean supports(final Class<?> clazz) {
		return Violation.class.equals(clazz);
	}

	@Override
	public void validate(final Object object, final Errors errors) {
		final Violation violation=(Violation)object;
		if(violation.getCompany()==null) {
			errors.rejectValue("company.callname", "company.callname");
		}

	}

}
