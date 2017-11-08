package com.myapp.finalproject.controller;


import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.servlet.ModelAndView;

import com.myapp.finalproject.dao.MessageDAO;
import com.myapp.finalproject.dao.OrderDAO;
import com.myapp.finalproject.dao.UserDAO;
import com.myapp.finalproject.exception.AdException;
import com.myapp.finalproject.pojo.Message;
import com.myapp.finalproject.pojo.Order;
import com.myapp.finalproject.pojo.User;

/**
 * Servlet implementation class LoginController
 */
@Controller
@RequestMapping("/login.htm")
public class LoginController{
	@Autowired
	@Qualifier("loginValidator")
	LoginValidator validator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("user") User user, BindingResult result, HttpServletRequest request, HttpServletResponse response) {
		String action = request.getParameter("action");
		if(action!=null){
			return "signup";
		}
		else return "login";
	}
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = null;
		String username = request.getParameter("userName");
		String password = request.getParameter("password");
		UserDAO userDao = new UserDAO();
		MessageDAO messageDao = new MessageDAO();
		String admin = userDao.checkAdmin(username, password);
		HttpSession session = request.getSession();
		if(admin!=null) {
			mv = new ModelAndView("Admin/adminIndex", "role", "admin");
			List<Message> adminMessage = messageDao.getAllMessagesByAdmin();
			session.setAttribute("adminMessage", adminMessage);
			session.setAttribute("role", "admin");
		}
		else{
			User user = (User) userDao.get(username, password);
			if(user==null){
				mv = new ModelAndView("loginFail");
			}
			else{
				List<Message> userMessage = messageDao.getAllMessagesByUser(username);
				session.setAttribute("userMessage", userMessage);
				session.setAttribute("user", user);
	//			System.out.println((session.getAttribute("user").);
				session.setAttribute("userName", user.getName());
				OrderDAO orderDao = new OrderDAO();
				List<Order> orders= new ArrayList<Order>();
				orders = orderDao.getAllOrderByUser(user);
				System.out.print("order size"+ orders.size());
				session.setAttribute("orderList", orders);
				mv = new ModelAndView("../../index","user",user);
			}
		}
		return mv;
	}
}
