package com.myapp.finalproject.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.myapp.finalproject.pojo.Message;
import com.myapp.finalproject.pojo.User;

public class MessageValidator implements Validator {
	public boolean supports(Class aClass)
    {
        return aClass.equals(User.class);
    }

    public void validate(Object obj, Errors errors)
    {
        Message message = (Message) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.invalid.message", "Title Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "error.invalid.message", "Content number Required");
    }
}
