package com.myapp.finalproject.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myapp.finalproject.dao.ItemDAO;
import com.myapp.finalproject.pojo.Item;

/**
 * Servlet implementation class EditController
 */
@Controller
@RequestMapping("/edit.htm")
public class EditController{
	@RequestMapping(method=RequestMethod.POST)
    protected ModelAndView doSubmitAction(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String action = request.getParameter("action");
		String edit = null;
		ModelAndView mv = null;
		if(action.equals("editItem")){
			String itemId = request.getParameter("itemId");
			ItemDAO itemDao = new ItemDAO();
			Item item = itemDao.get((Long.parseLong(itemId)));
			String title = request.getParameter("title");
			String price = request.getParameter("price");
			String size = request.getParameter("size");
			String color = request.getParameter("color");
			String quantity = request.getParameter("quantity");
			String details = request.getParameter("details");
			item.setColor(color);
			item.setDetails(details);
			item.setPrice(Double.parseDouble(price));
			item.setQuantity(Integer.parseInt(quantity));
			item.setTitle(title);
			item.setSize(size);
			itemDao.update(item);
			mv = new ModelAndView("Admin/editItemSuccessful");
		}
		return mv;
	}
}
