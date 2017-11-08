package com.myapp.finalproject.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="categorytable")
public class Category {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="categoryid", unique = true, nullable = false)
	private long id;
	@Column(name="title", unique=true)
	private String title;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="category")
	private Set<Item> items = new HashSet<Item>();
	
	public Category(String title) {
        this.title = title;
        this.items = new HashSet<Item>();
    }
	public Category() {
    }

    public Set<Item> getItems() {
        return items;
    }

    void setItems(Set<Item> items) {
        this.items = items;
    }

    public void addItem(Item item) {
        getItems().add(item);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
