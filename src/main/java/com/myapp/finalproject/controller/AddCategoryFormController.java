package com.myapp.finalproject.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.myapp.finalproject.dao.CategoryDAO;
import com.myapp.finalproject.dao.UserDAO;
import com.myapp.finalproject.exception.AdException;
import com.myapp.finalproject.pojo.Category;
import com.myapp.finalproject.pojo.User;

/**
 * Servlet implementation class AddCategoryFormController
 */
@Controller
@RequestMapping("/addcategory.htm")
public class AddCategoryFormController{
	@Autowired
	@Qualifier("categoryValidator")
	CategoryValidator categoryValidator;
    
	@InitBinder
	private void initBinder(WebDataBinder binder)
	{
		binder.setValidator(categoryValidator);
	}
	@RequestMapping(method=RequestMethod.POST)
    protected String doSubmitAction(@ModelAttribute("category")Category category,BindingResult result) throws Exception
    {
		categoryValidator.validate(category, result);
		if(result.hasErrors())
		{
			return "Admin/AddNewCatagory";
		}
        
        try
        {
            CategoryDAO categoryDAO = new CategoryDAO();
            categoryDAO.create(category.getTitle());
            //DAO.close();
        }
        catch (AdException e)
        {
            System.out.println(e.getMessage());
        }
        return "Admin/addedCategory";
    }
	@RequestMapping(method = RequestMethod.GET)
	public String initializeForm(@ModelAttribute("category") Category category, BindingResult result) {

		return "Admin/AddNewCatagory";
	}
	
//	@RequestMapping(method=RequestMethod.GET)
//	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		CategoryDAO categoryDao = null;
//		List categoryList = new ArrayList();
//		try{
//			categoryDao = new CategoryDAO();
//			categoryList = categoryDao.getAllCategory();
//		} catch (AdException e) {
//            System.out.println(e.getMessage());
//        }
//		ModelAndView mv = new ModelAndView("Admin/AddNewCatagory", "categoryList", categoryList);
//        return mv;
//	}
//	@RequestMapping(value = "/delete.html", method = RequestMethod.GET)    
//    public String delete(@RequestParam(value="delTitle",required=true) String title) throws AdException {    
//		CategoryDAO c = new CategoryDAO();
//		Category category = c.get(title);
//		c.delete(category);
//        return "deleteSuccess"; // this line assumes you have a view resolver that knows how to map this return to a valid view.        
//    }
}
