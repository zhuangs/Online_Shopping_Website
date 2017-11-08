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

import com.myapp.finalproject.dao.CategoryDAO;
import com.myapp.finalproject.dao.ItemDAO;
import com.myapp.finalproject.exception.AdException;
import com.myapp.finalproject.pojo.Category;



/**
 * Servlet implementation class CategoryContentController
 */
@Controller
@RequestMapping("/categoryContent.htm")
public class CategoryContentController{

	@RequestMapping(method = RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String category = request.getParameter("action");
		System.out.println(category);
		CategoryDAO categories = null;
		ItemDAO items = new ItemDAO();
//        List categoryList = null;
        List itemList = new ArrayList();
		try{
			categories = new CategoryDAO();
			if(category.equals("newArrival")){
				Category c = categories.get("New Arrivals");
				long id = c.getId();
				itemList = items.getAllItemByCategory(id);
				System.out.println(itemList.size());
			}
			if(category.equals("newArrival")){
				Category c = categories.get("New Arrivals");
				long id = c.getId();
				itemList = items.getAllItemByCategory(id);
				System.out.println(itemList.size());
			}
			if(category.equals("denim")){
				Category c = categories.get("denim");
				long id = c.getId();
				itemList = items.getAllItemByCategory(id);
				System.out.println(itemList.size());
			}
			if(category.equals("tees")){
				Category c = categories.get("tees");
				long id = c.getId();
				itemList = items.getAllItemByCategory(id);
				System.out.println(itemList.size());
			}
			if(category.equals("dresses")){
				Category c = categories.get("dresses");
				long id = c.getId();
				itemList = items.getAllItemByCategory(id);
				System.out.println(itemList.size());
			}
			if(category.equals("shoes")){
				Category c = categories.get("shoes");
				long id = c.getId();
				itemList = items.getAllItemByCategory(id);
				System.out.println(itemList.size());
			}
			if(category.equals("bag")){
				Category c = categories.get("bad");
				long id = c.getId();
				itemList = items.getAllItemByCategory(id);
				System.out.println(itemList.size());
			}
			if(category.equals("sale")){
				Category c = categories.get("sale");
				long id = c.getId();
				itemList = items.getAllItemByCategory(id);
				System.out.println(itemList.size());
			}
		} catch (AdException e) {
            System.out.println(e.getMessage());
        }
		ModelAndView mv = new ModelAndView("../../index", "itemList", itemList);
        return mv;
	}

}
