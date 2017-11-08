package com.myapp.finalproject.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.myapp.finalproject.exception.AdException;
import com.myapp.finalproject.pojo.Email;
import com.myapp.finalproject.pojo.Message;
import com.myapp.finalproject.pojo.Payment;
import com.myapp.finalproject.pojo.User;
import com.myapp.finalproject.pojo.WishList;

public class MessageDAO extends DAO{
	public Message create(String title, String content, User user) throws AdException{
		try {
            begin();
            System.out.println("inside DAO");
            Message message = new Message(user);
            Date date = new Date();
            message.setDate(date);
            message.setContent(content);
            message.setTitle(title);
            message.setReceiver("Admin");
            getSession().save(message);
            commit();
            close();
            return message;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            throw new AdException("Exception while creating message: " + e.getMessage());
        }
	}
	
	public List<Message> getAllMessages() throws AdException{
    	try{
    		begin();
    		Query q = getSession().createQuery("from Message message where message.receiver = 'admin'");
    		List list = q.list();
    		commit();
    		close();
    		return list;
    	}catch (HibernateException e) {
            rollback();
            throw new AdException("Could not list the messages", e);
        }
    }
	
	public List<Message> getAllMessagesByAdmin() throws AdException{
		try{
			begin();
			Query q = getSession().createQuery("from Message message where message.sender = 'admin'");
    		List list = q.list();
    		commit();
    		close();
    		return list;
    	}catch (HibernateException e) {
            rollback();
            throw new AdException("Could not list the messages", e);
    	}
	}
	public List<Message> getAllMessagesByUser(String username) throws AdException{
		try{
			begin();
			Query q = getSession().createQuery("from Message message where message.receiver = :username");
			q.setString("username", username);
    		List list = q.list();
//    		System.out.println(list.size());
    		commit();
    		close();
    		return list;
    	}catch (HibernateException e) {
            rollback();
            throw new AdException("Could not list the messages", e);
    	}
	}
	public List<Message> getAllMessagesSendByUser(String username) throws AdException{
		try{
			begin();
			Query q = getSession().createQuery("from Message message where message.sender = :username");
			q.setString("username", username);
    		List list = q.list();
    		System.out.println(list.size());
    		commit();
    		close();
    		return list;
    	}catch (HibernateException e) {
            rollback();
            throw new AdException("Could not list the messages", e);
    	}
	}
	public void save(Message message) throws AdException {
        try {
            begin();
            getSession().update(message);
            commit();
            close();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not save the message", e);
        }
    }
}
