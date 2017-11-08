package com.myapp.finalproject.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.myapp.finalproject.exception.AdException;
import com.myapp.finalproject.pojo.Item;
import com.myapp.finalproject.pojo.Order;
import com.myapp.finalproject.pojo.User;


public class OrderDAO extends DAO{
	public Order get(long id) throws AdException {
        try {
            begin();
            Query q=getSession().createQuery("from Order where id= :id");
            q.setLong("id",id);
            Order order=(Order)q.uniqueResult();
            commit();
            close();
            return order;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not obtain the named order " + id + " " + e.getMessage());
        }
    }
	public void save(Order order) throws AdException {
        try {
            begin();
            getSession().update(order);
            commit();
            close();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not save the order", e);
        }
    }
	public Order create(String status, Set<Item> items, User user, Date createDate)
            throws AdException {
        try {
            begin();
            Order order = new Order(user, items);
//            user.addOrder(order);
            order.setCreateDate(createDate);
            order.setStatus(status);
            getSession().save(order);
            commit();
            close();
            return order;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create advert", e);
            throw new AdException("Exception while creating order: " + e.getMessage());
        }
    }

    public void delete(Order order) throws AdException {
        try {
            begin();
            getSession().delete(order);
            commit();
            close();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not delete the category", e);
        }
    }
    
    public List<Order> getAllOrderByUser(User user) throws AdException{
    	try{
    		begin();
    		Query q = getSession().createQuery("from Order where user = :userId");
    		q.setLong("userId", user.getPersonID());
    		List list = q.list();
    		commit();
    		close();
    		return list;
    	}catch (HibernateException e) {
            rollback();
            throw new AdException("Could not list the orders", e);
        }
    }
}
