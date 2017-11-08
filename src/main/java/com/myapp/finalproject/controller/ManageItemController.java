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
import com.myapp.finalproject.pojo.Item;

/**
 * Servlet implementation class ManageItemController
 */
@Controller
@RequestMapping("/manageItem.htm")
public class ManageItemController{
	@RequestMapping(method=RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String action = request.getParameter("action");
//		System.out.println(action);
		ItemDAO itemDao = new ItemDAO();
		if(action!=null){
			if(action.equals("delete")){
				String itemId = request.getParameter("itemId");
				Item item = itemDao.get(Long.parseLong(itemId));
				itemDao.delete(item);
			}
		}
		List<Item> itemList = itemDao.getAllItem();
		System.out.println(itemList.size());
		ModelAndView mv = new ModelAndView("Admin/manageItem", "itemList", itemList);
		return mv;
	}

}
