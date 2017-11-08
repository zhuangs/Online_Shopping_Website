package com.myapp.finalproject.dao;

import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.myapp.finalproject.exception.AdException;
import com.myapp.finalproject.pojo.Category;
import com.myapp.finalproject.pojo.Item;
import com.myapp.finalproject.pojo.User;

public class ItemDAO extends DAO{
	public Item get(long id) throws AdException {
        try {
            begin();
            Query q=getSession().createQuery("from Item where id= :id");
            q.setLong("id",id);
            Item item=(Item)q.uniqueResult();
            commit();
            close();
            return item;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not obtain the named category " + id + " " + e.getMessage());
        }
    }
	public Item getByTitle(String title) throws AdException {
        try {
            begin();
            Query q=getSession().createQuery("from Item item where item.title= :title");
            q.setString("title",title);
            Item item=(Item)q.uniqueResult();
            commit();
            close();
            return item;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not obtain the named category " + title + " " + e.getMessage());
        }
    }
	public List getByColor(String color) throws AdException {
        try {
            begin();
            Query q=getSession().createQuery("from Item item where item.color= :color");
            q.setString("color",color);
            List list = q.list();
            commit();
            close();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not obtain the named category " + color + " " + e.getMessage());
        }
    }
	public Item create(String title, long categoryId, String color, String size, int quantity, String details, String categoryName,double price)throws AdException {
		try{
			begin();
			Item item = new Item(title, categoryId, categoryName);
			item.setColor(color);
			item.setDetails(details);
//			item.setPhotoName(photoName);
			item.setQuantity(quantity);
			item.setSize(size);
			item.setPrice(price);
			getSession().save(item);
			commit();
			close();
			return item;
		} catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create advert", e);
            throw new AdException("Exception while creating advert: " + e.getMessage());
        }
	}
	public void delete (Item item) throws AdException {
		try {
			begin();
			getSession().delete(item);
			commit();
			close();
		} catch (HibernateException e) {
            rollback();
            throw new AdException("Could not delete advert", e);
        }
	}
	public List<Item> getAllItem() throws AdException{
		try{
			begin();
			Query q = getSession().createQuery("from Item");
			List list = q.list();
			commit();
			close();
			return list;
		}catch (HibernateException e) {
	        rollback();
	        throw new AdException("Could not list the users", e);
	    }
	}
	public List<Item> getAllItemByCategory(long categoryid) throws AdException{
		try{
			begin();
			Query q = getSession().createQuery("from Item i where i.category = :id");
			q.setLong("id",categoryid);
			List list = q.list();
			commit();
			close();
			return list;
		}catch (HibernateException e) {
            rollback();
            throw new AdException("Could not list the items", e);
        }
	}
	public void save(Item item) throws AdException {
        try {
            begin();
            getSession().update(item);
            commit();
            close();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not save the item", e);
        }
    }
	public void update(Item item) throws AdException {
        try {
            begin();
            getSession().update(item);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not update the item", e);
        }
    }
}
