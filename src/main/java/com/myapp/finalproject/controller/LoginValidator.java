package com.myapp.finalproject.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.myapp.finalproject.pojo.*;

public class LoginValidator implements Validator{
	public boolean supports(Class aClass)
    {
        return aClass.equals(User.class);
    }
	public void validate(Object obj, Errors errors)
    {
        User user = (User) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.invalid.user", "User Name Required");
    }
}
