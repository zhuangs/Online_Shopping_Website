package com.myapp.finalproject.dao;

import com.myapp.finalproject.exception.AdException;
import com.myapp.finalproject.pojo.Category;
import com.myapp.finalproject.pojo.User;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;

public class CategoryDAO extends DAO{
	public Category get(String title) throws AdException {
        try {
            begin();
            Query q=getSession().createQuery("from Category where title= :title");
            q.setString("title",title);
            Category category=(Category)q.uniqueResult();
            commit();
            close();
            return category;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not obtain the named category " + title + " " + e.getMessage());
        }
    }

    public List list() throws AdException {
        try {
            begin();
            Query q = getSession().createQuery("from Category");
            List list = q.list();
            commit();
            close();
            return list;
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not list the categories", e);
        }
    }

    public Category create(String title) throws AdException {
        try {
            begin();
            Category cat = new Category(title);
            getSession().save(cat);
            commit();
            close();
            return null;
        } catch (HibernateException e) {
            rollback();
            //throw new AdException("Could not create the category", e);
            throw new AdException("Exception while creating category: " + e.getMessage());
        }
    }

    public void save(Category category) throws AdException {
        try {
            begin();
            getSession().update(category);
            commit();
            close();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not save the category", e);
        }
    }

    public void delete(Category category) throws AdException {
        try {
            begin();
            getSession().delete(category);
            close();
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new AdException("Could not delete the category", e);
        }
    }
    
    public List<Category> getAllCategory() throws AdException{
    	try{
    		begin();
    		Query q = getSession().createQuery("from Category");
    		List list = q.list();
    		commit();
    		close();
    		return list;
    	}catch (HibernateException e) {
            rollback();
            throw new AdException("Could not list the categories", e);
        }
    }
}
