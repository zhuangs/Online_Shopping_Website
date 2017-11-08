package com.myapp.finalproject.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myapp.finalproject.dao.MessageDAO;
import com.myapp.finalproject.dao.OrderDAO;
import com.myapp.finalproject.dao.UserDAO;
import com.myapp.finalproject.pojo.Email;
import com.myapp.finalproject.pojo.Item;
import com.myapp.finalproject.pojo.Message;
import com.myapp.finalproject.pojo.Order;
import com.myapp.finalproject.pojo.User;

/**
 * Servlet implementation class MyAccountController
 */
@Controller
@RequestMapping("/myAccount.htm")
public class MyAccountController {
	@RequestMapping(method=RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		if(action.equals("myAccount")){
			User user = (User)session.getAttribute("user");
//			UserDAO userDao = new UserDAO();
//			User user = userDao.retrieve(username);
//			System.out.println(username);
			if(user==null){
				String username = (String)session.getAttribute("userName");
				UserDAO userDao = new UserDAO();
				user = userDao.retrieve(username);
			}
			mv.addObject("user", user);
			System.out.println(session.getAttribute("userName"));
			OrderDAO orderDao = new OrderDAO();
			List<Order> orderList  = orderDao.getAllOrderByUser(user);
			mv.addObject("orderList", orderList);
			MessageDAO messageDao = new MessageDAO();
			List<Message> remessageList = messageDao.getAllMessagesByUser(user.getName());
			mv.addObject("remessageList", remessageList);
//			System.out.println("re"+remessageList.size());
			List<Message> semessageList = messageDao.getAllMessagesSendByUser(user.getName());
			mv.addObject("semessageList", semessageList);
//			System.out.println("se"+semessageList.size());
			mv.setViewName("User/myAccount");
		}
		if(action.equals("viewDetail")){
			long id = Long.parseLong(request.getParameter("orderId"));
//			 System.out.println(id);
			 OrderDAO orderDao = new OrderDAO();
			 Order order = orderDao.get(id);
			 Set<Item> items = order.getItems();
			 System.out.println(items.size());
			 mv = new ModelAndView("User/detail", "items", items);
		}
		if(action.equals("payment")){
			mv.setViewName("User/payment");
		}
		if(action.equals("cancel")){
			OrderDAO orderDao = new OrderDAO();
			
			Long orderId = Long.parseLong(request.getParameter("order"));
			Order order = orderDao.get(orderId);
			order.setStatus("Canceled");
			orderDao.save(order);
			JSONObject obj = new JSONObject();
	        obj.put("cancel", "Canceled");
		}
		return mv;
	}
	@RequestMapping(method=RequestMethod.POST)
	protected void doSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String name = request.getParameter("name");
//		System.out.println(name);
		String email = request.getParameter("email");
		String address = request.getParameter(("address"));
		String phoneNumber = request.getParameter("phoneNumber");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("userName");
		UserDAO userDao = new UserDAO();
		User user = userDao.retrieve(username);
		user.setAddress(address);
		Email e = user.getEmail();
		e.setEmailId(email);
		user.setEmail(e);
		user.setName(name);
		user.setPassword(password);
		user.setPhoneNumber(phoneNumber);
		userDao.save(user);
		session.setAttribute("user", user);
		session.setAttribute("userName", user.getName());
		JSONObject obj = new JSONObject();
		obj.put("flag", 1);
		PrintWriter out = response.getWriter();
		out.print(obj);
	}
}
