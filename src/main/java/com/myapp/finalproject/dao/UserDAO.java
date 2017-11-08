package com.myapp.finalproject.dao;

import com.myapp.finalproject.exception.AdException;
import com.myapp.finalproject.pojo.Category;
import com.myapp.finalproject.pojo.Email;
import com.myapp.finalproject.pojo.Message;
import com.myapp.finalproject.pojo.Payment;
import com.myapp.finalproject.pojo.User;
import com.myapp.finalproject.pojo.WishList;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;

public class UserDAO extends DAO{
	public UserDAO() {	
	}
	public User retrieve(String username) throws AdException{
		try {
			begin();
		
			Query q = getSession().createQuery("from User where name = :username");
			q.setString("username", username);
//			q.setString("password", password);
			User user = (User) q.uniqueResult();
			commit();
			close();
			return user;
		}
		catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get user "+username, e);
		}
	}
	public User searchByEmail(String email) throws AdException{
		try {
			begin();
		
			Query q = getSession().createQuery("from User user where user.email.emailId = :email");
			q.setString("email", email);
//			q.setString("password", password);
			User user = (User) q.uniqueResult();
			commit();
			close();
			return user;
		}
		catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get user "+email, e);
		}
	}
	public User get(String username, String password) throws AdException{
		try {
			begin();
			Query q = getSession().createQuery("from User where name = :username and password = :password");
			q.setString("username", username);
			q.setString("password", password);
			User user = (User) q.uniqueResult();
			commit();
			close();
			return user;
		}
		catch (HibernateException e) {
			rollback();
			throw new AdException("Could not get user "+username, e);
		}
	}
	public void save(User user) throws AdException {
        try {
            begin();
            getSession().update(user);
            commit();
            close();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not save the user", e);
        }
    }
	public User create(String username, String password, String email, String address, String phoneNumber, String cardNumber, String expireDate, String nameOnCard)
            throws AdException {
        try {
            begin();
            System.out.println("inside DAO");
            
            
            
            Email email1=new Email(email);
            User user=new User(username,password);
//            Message message = new Message(user);
//            user.getMessages().add(message);
            Payment payment = new Payment(cardNumber, expireDate, nameOnCard);
            WishList wishList = new WishList();
            
            user.setAddress(address);
            user.setPhoneNumber(phoneNumber);
            user.setPayment(payment);
            user.setWishList(wishList);
            user.setEmail(email1);
            payment.setUser(user);
            email1.setUser(user);
            wishList.setUser(user);
            
            getSession().save(user);
            
            commit();
            close();
            return user;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create user " + username, e);
            throw new AdException("Exception while creating user: " + e.getMessage());
        }
    }

	public String checkAdmin(String username, String password) {
		if(username.equals("admin")&&password.equals("admin")){
			return "admin";
		}
		else return null;
	}
	
	
    public void delete(User user)
            throws AdException {
        try {
            begin();
            getSession().delete(user);
            commit();
            close();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not delete user " + user.getName(), e);
        }
    }
    
    public List<User> getAllUsers() throws AdException{
    	try{
    		begin();
    		Query q = getSession().createQuery("from User");
    		List list = q.list();
    		commit();
    		close();
    		return list;
    	}catch (HibernateException e) {
            rollback();
            throw new AdException("Could not list the users", e);
        }
    }
}
