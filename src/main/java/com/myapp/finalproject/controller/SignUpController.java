package com.myapp.finalproject.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myapp.finalproject.dao.UserDAO;
import com.myapp.finalproject.exception.AdException;
import com.myapp.finalproject.pojo.User;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/signup.htm")

public class SignUpController {
	@Autowired
	@Qualifier("userValidator")
	UserValidator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	protected String doSubmitAction(@ModelAttribute("user") User user, BindingResult result) throws Exception {
		validator.validate(user, result);
		if(result.hasErrors()) {
			return "signup";
		}
		
		try {
			UserDAO userDao = new UserDAO();
			userDao.create(user.getName(), user.getPassword(), user.getEmail().getEmailId(), user.getAddress(), user.getPhoneNumber(), user.getPayment().getCardNumber(), user.getPayment().getExpiredDate(), user.getPayment().getNameOnCard());
			
			System.out.println(user.getName());
		}
		catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return "addedUser";
	}
	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("user") User user, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter("action");
		if(action!=null){
			return "login";
		}
		else return "signup";
	}
}
