package com.myapp.finalproject.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myapp.finalproject.dao.OrderDAO;
import com.myapp.finalproject.dao.UserDAO;
import com.myapp.finalproject.exception.AdException;
import com.myapp.finalproject.pojo.Item;
import com.myapp.finalproject.pojo.Order;
import com.myapp.finalproject.pojo.User;

@Controller
@RequestMapping("/manageUserAccount.htm")
public class ManageUserAccountController {
	//@Autowired
	
	@RequestMapping(method=RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = null;
		String action = request.getParameter("action");
		
		if(action.equals("enter")){
			UserDAO userDao = null;
			List userList = new ArrayList();
			try{
				userDao = new UserDAO();
				userList = userDao.getAllUsers();
			} catch (AdException e) {
	            System.out.println(e.getMessage());
	        }
			mv = new ModelAndView("Admin/manageUserAccount", "userList", userList);
		}
		if(action.equals("viewDetails")){
			 long id = Long.parseLong(request.getParameter("orderId"));
//			 System.out.println(id);
			 OrderDAO orderDao = new OrderDAO();
			 Order order = orderDao.get(id);
			 Set<Item> items = order.getItems();
			 System.out.println(items.size());
			 mv = new ModelAndView("Admin/orderDetail", "items", items);
		}
//		if(action.equals("ship")){
//			long id = Long.parseLong(request.getParameter("orderId"));
//			OrderDAO orderDao = new OrderDAO();
//			Order order = orderDao.get(id);
//			order.setStatus("Shipped");
//			orderDao.save(order);
//			mv = new ModelAndView("Admin/manageUserAccount");
//		}
//		if(action.equals("cancel")){
//			long id = Long.parseLong(request.getParameter("orderId"));
//			OrderDAO orderDao = new OrderDAO();
//			Order order = orderDao.get(id);
//			order.setStatus("Canceled");
//			mv = new ModelAndView("Admin/manageUserAccount");
//		}
		if(action.equals("delete")){
			String username = request.getParameter("username");
			UserDAO userDao = new UserDAO();
			User user = userDao.retrieve(username);
			System.out.println(username);
			userDao.delete(user);
			List<User> userList = userDao.getAllUsers();
			mv = new ModelAndView("Admin/manageUserAccount","userList",userList);
		}
		if(action.equals("cancel")){
			OrderDAO orderDao = new OrderDAO();
//			System.out.println(request.getParameter("order"));
			Long orderId = Long.parseLong(request.getParameter("order"));
			Order order = orderDao.get(orderId);
			order.setStatus("Canceled");
			orderDao.save(order);
			JSONObject obj = new JSONObject();
	        obj.put("cancel", "Canceled");
		}
		if(action.equals("ship")){
			OrderDAO orderDao = new OrderDAO();
			System.out.println(request.getParameter("ship"));
			Long orderId = Long.parseLong(request.getParameter("ship"));
			Order order = orderDao.get(orderId);
			order.setStatus("Shipped");
			orderDao.save(order);
			JSONObject obj = new JSONObject();
	        obj.put("ship", "Shipped");
		}
        return mv;
	}

}
