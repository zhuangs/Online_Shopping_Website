package com.myapp.finalproject.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myapp.finalproject.dao.MessageDAO;
import com.myapp.finalproject.dao.UserDAO;
import com.myapp.finalproject.exception.AdException;
import com.myapp.finalproject.pojo.Message;
import com.myapp.finalproject.pojo.User;

/**
 * Servlet implementation class AdminManageMessageController
 */
@Controller
@RequestMapping("/adminManageMessage.htm")
public class AdminManageMessageController{
	@RequestMapping(method=RequestMethod.GET)
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MessageDAO messageDao = new MessageDAO();
		List<Message> list = messageDao.getAllMessages();
		ModelAndView mv = new ModelAndView();
		mv.addObject( "messageList", list);
		mv.setViewName("Admin/manageMessage");
		return mv;
	}
	@RequestMapping(method = RequestMethod.POST)
	protected ModelAndView doSubmitAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = null;
		try {
			String name = request.getParameter("username");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			System.out.println(title);
			MessageDAO messageDao = new MessageDAO();
			UserDAO userDao = new UserDAO();
			User user = userDao.retrieve(name);
			System.out.println("username is"+user.getName());
			Message message = messageDao.create(title, content, user);
			message.setSender("Admin");
			message.setReceiver(user.getName());
			messageDao.save(message);
			user.getMessages().add(message);
			userDao.save(user);
			List<Message> adminMessage = messageDao.getAllMessagesByAdmin();
			System.out.println("Admin message"+adminMessage.size());
			HttpSession session = request.getSession();
			session.setAttribute("adminMessage", adminMessage);
			List<Message> list = messageDao.getAllMessages();
			
			mv = new ModelAndView("Admin/manageMessage", "adminMessage", adminMessage);
			mv.addObject( "messageList", list);
		}
		catch (AdException e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return mv;
	}
}
