package com.myapp.finalproject.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myapp.finalproject.dao.MessageDAO;
import com.myapp.finalproject.dao.UserDAO;
import com.myapp.finalproject.exception.AdException;
import com.myapp.finalproject.pojo.Message;
import com.myapp.finalproject.pojo.User;

/**
 * Servlet implementation class ManageMessageController
 */
@Controller
@RequestMapping("/message.htm")
public class ManageMessageController {
	@Autowired
	@Qualifier("messageValidator")
	MessageValidator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}
	@RequestMapping(method = RequestMethod.POST)
	protected String doSubmitAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		System.out.println("Message id:"+message.getId());
//		validator.validate(message, result);
//		if(result.hasErrors()) {
//			return "myAccount";
//		}
		try {
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			System.out.println(title);
			MessageDAO messageDao = new MessageDAO();
			UserDAO userDao = new UserDAO();
			HttpSession ss = request.getSession();
			String username = (String)ss.getAttribute("userName");
			User user = userDao.retrieve(username);
//			System.out.println("username is"+user.getName());
			Message message = messageDao.create(title, content, user);
			message.setSender(user.getName());
			messageDao.save(message);
			user.getMessages().add(message);
			userDao.save(user);
//			System.out.println(message.getTitle());
		}
		catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return "User/messageSend";
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("message")Message message, BindingResult result) {

		return "myAccount";
	}
}
