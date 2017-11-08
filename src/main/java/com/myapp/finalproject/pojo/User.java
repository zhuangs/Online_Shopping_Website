package com.myapp.finalproject.pojo;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
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


@Entity
@Table(name="userTable")
@PrimaryKeyJoinColumn(name="personID")
public class User extends Person{
	
	@Column(name = "name", unique=true)
	private String name;
	
	@Column(name = "password")
	private String password;
	
	
	@OneToOne(fetch=FetchType.EAGER, mappedBy="user", cascade=CascadeType.ALL)
	private Email email;
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="user")
	private Set<Order> orders = new HashSet<Order>();
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy="user")
	private Set<Message> messages = new HashSet<Message>();
	
	@OneToOne(fetch=FetchType.EAGER, mappedBy="user", cascade=CascadeType.ALL)
	private WishList wishList;
	
	@Column(name = "address")
	private String address;
	
	@OneToOne(fetch=FetchType.EAGER, mappedBy="user", cascade=CascadeType.ALL)
	private Payment payment;
	
	@Column(name = "phoneNumber")
	private String phoneNumber;
	
	
	public User(String name, String password) {
        this.name = name;
        this.password = password;
//        wishList = new WishList();
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Email getEmail() {
		return email;
		
	}

	public void setEmail(Email email) {
		this.email = email;
	}

	public Set<Order> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public void addOrder(Order order) {
        getOrders().add(order);
    }
	
	
	public Set<Message> getMessages() {
		return messages;
	}

	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}

	public void addMessage(Message message) {
        getMessages().add(message);
    }
	
	public WishList getWishList() {
		return wishList;
	}

	public void setWishList(WishList wishList) {
		this.wishList = wishList;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
