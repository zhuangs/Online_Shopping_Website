package com.myapp.finalproject.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myapp.finalproject.dao.ItemDAO;
import com.myapp.finalproject.dao.UserDAO;
import com.myapp.finalproject.pojo.Item;
import com.myapp.finalproject.pojo.User;

/**
 * Servlet implementation class SearchUserController
 */
@Controller
@RequestMapping("/search.htm")
public class SearchController{
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView doSubmitAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");
		ModelAndView mv = null;
		String searchKeyWord = request.getParameter("searchKeyWord");
		if(action.equals("searchuser")){
			String searchKey = request.getParameter("searchkey");
			System.out.println(searchKey);
			if(searchKey.equals("name")){
				UserDAO userDao = new UserDAO();
				User user = userDao.retrieve(searchKeyWord);
				mv = new ModelAndView("Admin/searchUserResult","user",user);
			}
			if(searchKey.equals("email")){
				UserDAO userDao = new UserDAO();
				User user = userDao.searchByEmail(searchKeyWord);
				mv = new ModelAndView("Admin/searchUserResult","user",user);
			}
		}
		if(action.equals("searchItem")){
			String searchKey = request.getParameter("searchkey");
			ItemDAO itemDao = new ItemDAO();
			if(searchKey.equals("title")){
				Item item = itemDao.getByTitle(searchKeyWord);
				mv = new ModelAndView("Admin/searchItemResult","titleItem",item);
			}
			if(searchKey.equals("id")){
				Item item = itemDao.get(Long.parseLong(searchKeyWord));
				mv = new ModelAndView("Admin/searchItemResult","item",item);
			}
			if(searchKey.equals("category")){
				List<Item> list = itemDao.getAllItemByCategory(Long.parseLong(searchKeyWord));
				System.out.println(list.size());
				mv = new ModelAndView("Admin/searchItemResult","catelist",list);
			}
			if(searchKey.equals("color")){
				List<Item> list = itemDao.getByColor(searchKeyWord);
				mv = new ModelAndView("Admin/searchItemResult","colorlist",list);
			}
		}
		return mv;
	}

}
