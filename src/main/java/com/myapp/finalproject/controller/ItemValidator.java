package com.myapp.finalproject.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.myapp.finalproject.pojo.Item;


public class ItemValidator implements Validator{
	public boolean supports(Class aClass)
    {
        return aClass.equals(Item.class);
    }
	public void validate(Object obj, Errors errors)
    {
        Item item = (Item) obj;
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.invalid.item", "Title Required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "color", "error.invalid.item", "Color Required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "size", "error.invalid.item", "Size Required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "quantity", "error.invalid.item", "Quantity Required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "details", "error.invalid.item", "Details Required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "error.invalid.item", "Price Required");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "photo", "error.invalid.item", "Photo Required");
    }
}
