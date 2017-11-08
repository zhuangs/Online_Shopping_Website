package com.myapp.finalproject.pojo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "ordertable")
public class Order {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="orderid", unique = true, nullable = false)
	private long id;

//	@Column(name="orderNumber")
//	private String orderNumber;
	@Column(name="status")
	private String status;
	
//	@OneToMany(fetch=FetchType.LAZY, mappedBy="order")
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(name = "order_item", joinColumns = @JoinColumn(name = "orderId"), inverseJoinColumns = @JoinColumn(name = "itemId"))
	private Set<Item> items = new HashSet<Item>();
	
	@Column(name="createDate")
	private Date createDate;
	
	@ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	@JoinColumn(name="user")
    private User user;
	
	Order(){
		
	}
	public Order(User user, Set<Item> items){
		this.user = user;
		this.items = items;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

//	public String getOrderNumber() {
//		return orderNumber;
//	}
//
//	public void setOrderNumber(String orderNumber) {
//		this.orderNumber = orderNumber;
//	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}
	
	public void addItem(Item item) {
        getItems().add(item);
    }

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
