package com.myapp.finalproject.controller;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.myapp.finalproject.pojo.User;

import org.springframework.validation.ValidationUtils;

public class UserValidator implements Validator {

    public boolean supports(Class aClass)
    {
        return aClass.equals(User.class);
    }

    public void validate(Object obj, Errors errors)
    {
        User user = (User) obj;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address", "error.invalid.user", "Address Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phoneNumber", "error.invalid.user", "Phone number Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.invalid.user", "User Name Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "payment.cardNumber", "error.invalid.payment.cardNumber", "Card Number Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email.emailId", "error.invalid.email.emailId", "Email Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "payment.expiredDate", "error.invalid.payment.expiredDate", "Expire Date Required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "payment.nameOnCard", "error.invalid.payment.nameOnCard", "Name On Card Required");
    }
}
