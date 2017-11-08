package com.myapp.finalproject.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myapp.finalproject.dao.ItemDAO;
import com.myapp.finalproject.dao.OrderDAO;
import com.myapp.finalproject.dao.UserDAO;
import com.myapp.finalproject.pojo.Item;
import com.myapp.finalproject.pojo.Order;
import com.myapp.finalproject.pojo.User;

/**
 * Servlet implementation class AddToController
 */
@Controller
@RequestMapping("/addToss.htm")
public class AddToController{
//	@SuppressWarnings("unchecked")
	@RequestMapping(method=RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("why nothing shows");
		HttpSession session = request.getSession();
		String action = request.getParameter("action");
		System.out.println("xxxxxxxxx"+action);
		ModelAndView mv = new ModelAndView();
		ArrayList<Item> cart;
		if(session.getAttribute("cart") != null) {
			cart = (ArrayList<Item>) session.getAttribute("cart");
		}else {
            cart = new ArrayList<Item>();
        }
		if(action.equals("cart")) {
			long itemId = Long.parseLong(request.getParameter("itemId"));
			ItemDAO itemDao = new ItemDAO();
			Item item = itemDao.get(itemId);
			System.out.println("111"+cart.contains(item));
			if (!cart.contains(item)) {
                cart.add(item);
            }
			double total = 0;
			int count = 0;
            for (Item i : cart) {
                total = total + i.getPrice();
                count += 1;
            }
            session.setAttribute("cart", cart);
            mv.addObject("total", total);
            mv.addObject("count", count);
            mv.setViewName("User/viewCart");
		}
		if(action.equals("shoppingbag")) {
			System.out.println("why nothing shows");
            mv.setViewName("User/viewCart");
		}
		if(action.equals("shopmore")){
			mv.setViewName("index");
		}
		if(action.equals("checkout")){
			if(session.getAttribute("userName")==null){
				mv.setViewName("login");
			}
			else{
				UserDAO userDao = new UserDAO();
				User user = userDao.retrieve((String)session.getAttribute("userName"));
				System.out.println(user.getEmail());
//				Order order = new Order(user);
				Set<Item> items = new HashSet<Item>();
				for (Item i : cart) {
	                items.add(i);
	            }
	            Date date = new Date();
	            String status = "submitted";
				OrderDAO orderDao = new OrderDAO();
				Order order = orderDao.create(status, items, user, date);
				System.out.println(order.getId());
				order.setUser(user);
				user.addOrder(order);
				userDao.save(user);
				session.removeAttribute("cart");
				List<Order> orders= new ArrayList<Order>();
				orders = orderDao.getAllOrderByUser(user);
				session.setAttribute("orderList", orders);
				mv.setViewName("User/checkoutSuccessful");
			}
		}
		if(action.equals("remove")){
			if(action.equals("remove")){
				System.out.println("cart size is" + cart.size());
				Long itemId = Long.parseLong(request.getParameter("item"));
				ItemDAO itemDao= new ItemDAO();
				Item item = itemDao.get(itemId);
				cart.remove(item);
				System.out.println(cart.size());
			}
//			System.out.println("cart size is" + cart.size());
//			Long itemId = Long.parseLong(request.getParameter("item"));
//			ItemDAO itemDao= new ItemDAO();
//			Item item = itemDao.get(itemId);
//			int index = cart.indexOf(item);
//			Item removedItem = cart.remove(index);
//			System.out.println("We removed " + removedItem.getType() + " from the list.");
//			System.out.println(cart.contains(item));
//			session.setAttribute("cart", cart);
//			System.out.println(cart.size());
		}
		return mv;
	}
	@RequestMapping(method=RequestMethod.POST)
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse hsr1) throws Exception {
		String action = request.getParameter("action");
		System.out.println(action);
//		if(action.equals("remove")){
//			System.out.println("cart size is" + cart.size());
//			Long itemId = Long.parseLong(request.getParameter("item"));
//			ItemDAO itemDao= new ItemDAO();
//			Item item = itemDao.get(itemId);
//			cart.remove(item);
//			System.out.println(cart.size());
//		}
//		return mv;
		return new ModelAndView();
	}
}
