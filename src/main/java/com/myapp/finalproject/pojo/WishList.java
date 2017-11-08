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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
@Entity
@Table(name = "wishlisttable")
public class WishList {
	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "user"))
	@Id
	@GeneratedValue(generator = "generator", strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private long id;
	
//	@OneToMany(fetch=FetchType.LAZY, mappedBy="wishList")
	@OneToMany(fetch=FetchType.LAZY)
	private Set<Item> items = new HashSet<Item>();
	
	@Column(name = "kkk")
	private String kkk;
	
	@OneToOne(fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn(name="id")
	private User user;
	
	public WishList(){
		this.items = new HashSet<Item>();
	}
	public Set<Item> getItems() {
		return items;
	}
	public void setItems(Set<Item> items) {
		this.items = items;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKkk() {
		return kkk;
	}
	public void setKkk(String kkk) {
		this.kkk = kkk;
	}
	
	
}
