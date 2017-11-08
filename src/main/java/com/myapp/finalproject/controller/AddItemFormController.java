package com.myapp.finalproject.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myapp.finalproject.dao.CategoryDAO;
import com.myapp.finalproject.dao.ItemDAO;
import com.myapp.finalproject.exception.AdException;
import com.myapp.finalproject.pojo.Category;
import com.myapp.finalproject.pojo.Item;

@Controller
@RequestMapping("/addItems.htm")
public class AddItemFormController {
	@Autowired
	ServletContext context; 
	@Qualifier("itemValidator")
	ItemValidator itemValidator;
	
	@InitBinder
	private void initBinder(WebDataBinder binder){
		binder.setValidator(itemValidator);
	}
	
	@RequestMapping(method=RequestMethod.POST)
    protected String doSubmitAction(@ModelAttribute("item")Item item,BindingResult result) throws Exception{
		if(item!=null){
			System.out.println(item.getCategory_name());
		}
//		itemValidator.validate(item, result);
//		if(result.hasErrors())
//		{
//			return "Admin/AddNewItem";
//		}
		String title = item.getTitle();
		String categorytitle = item.getCategory_name();
		System.out.println(item.getColor());
		Double price = item.getPrice();
		String color = item.getColor();
		String details = item.getDetails();
		String size = item.getSize();
		int quantity = item.getQuantity();
//		File file;
//		String check = File.separator;
//		String path = null;
//		System.out.println(context.getRealPath(""));
//		if(check.equalsIgnoreCase("\\")) {
//	        path = context.getRealPath("").replace("build\\",""); 
//	    }
//		if(check.equalsIgnoreCase("/")) {
//		       path = context.getRealPath("").replace("build/","");
//		       path += "/resources/"; 
//		}
//		String fileNameWithExt = null;
//		String con = null;
//		if(item.getPhoto()!=null){
//			fileNameWithExt = System.currentTimeMillis() + item.getPhoto().getOriginalFilename();
//	        file = new File("/Users/Shan/Documents/workspace-sts-3.7.3.RELEASE/FinalPorject/src/main/resources/"+fileNameWithExt);
//	        System.out.println(file);
//	        con = context.getContextPath();
//	        item.getPhoto().transferTo(file);
//		}
		try{
            CategoryDAO categories = new CategoryDAO();
            ItemDAO itemDao = new ItemDAO();
            Category category = categories.get(categorytitle);
            if(category!=null){
            	long categoryid = category.getId();
            	item.setCategory(categoryid);
            }
            else{
            	System.out.println("wrong");
            }   
//            System.out.println(price);
            Item i = itemDao.create(title, category.getId(), color, size, quantity, details, categorytitle, price);
//            i.setPhotoName(con +"/resources/" + fileNameWithExt);
//            i.setPhotoName("/Users/Shan/Documents/workspace-sts-3.7.3.RELEASE/FinalPorject/src/main/resources/"+fileNameWithExt);
            i.setPhotoName(item.getPhoto().getOriginalFilename());
            System.out.println(item.getPhoto().getOriginalFilename());
            byte[] bytes = item.getPhoto().getBytes();
            BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(new File("/Users/Shan/Documents/workspace-sts-3.7.3.RELEASE/Final Porject.24/src/main/webapp/resources/image/"+i.getPhotoName())));
            buffStream.write(bytes);
            buffStream.close();
            itemDao.save(i);
            category.addItem(i);
            categories.save(category);
		}catch (AdException e) {
            System.out.println(e.getMessage());
        }
		return "Admin/AddedItem";
	}
	@RequestMapping(method=RequestMethod.GET)
    public String initializeForm(@ModelAttribute("item")Item item, BindingResult result) { 
   
        return "Admin/AddNewItem"; 
    }
}
