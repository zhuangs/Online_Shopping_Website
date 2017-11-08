package com.myapp.finalproject.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myapp.finalproject.dao.UserDAO;
import com.myapp.finalproject.exception.AdException;
import com.myapp.finalproject.pojo.Category;

/**
 * Servlet implementation class BackToController
 */
@Controller
public class BackToController {
	@RequestMapping(value="/backTo.htm", method=RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");
		System.out.println(action);
		ModelAndView mv = null;
		if(action.equals("adminMenu")){
			mv = new ModelAndView("Admin/adminIndex");
		}
		if(action.equals("manageUserAccount")){
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
		if(action.equals("myAccount")){
			mv = new ModelAndView("User/myAccount");
		}
//		if(action.equals("createNewCatagory")){
//			System.out.println("aaaaaaaaaa");
//			mv = new ModelAndView("Admin/AddNewCatagory");
//		}
		return mv;
	}
	@RequestMapping(value="/createNewCatagory.htm", method=RequestMethod.GET)
	protected ModelAndView createNewCatagory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = null;
		Category catagory = new Category();
		mv = new ModelAndView("Admin/AddNewCatagory", "catagory", new Category());
		return mv;
	}
}
