package com.myapp.finalproject.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="itemtable")
public class Item {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="itemid", nullable = false)
	private long id;
	
	@Column(name="title", unique=true)
	private String title;
	
	@JoinColumn(name="categoryid")
	private long category;
	
	@Transient
	private String category_name;
	
	@Column(name="color")
	private String color;
	
	@Column(name="size")
	private String size;
	
	@Column(name="price")
	private double price;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="details")
	private String details;
	
	@Column(name="photoName")
	private String photoName;
	
	@Transient
    private MultipartFile photo;
	
	@ManyToMany(mappedBy="items")
	private Set<Order> orders = new HashSet<Order>();
	
//	@OneToMany(fetch=FetchType.LAZY, mappedBy="item")
//	private Set<Review> reiviews = new HashSet<Review>();	

//	@JoinColumn(name="wishListid")
//	private long wishList;
	
//	@JoinColumn(name="orderid")
//	private long order;
	
	public Item(String title,long category_id,String categoryName) {
        this.title = title;
        this.category = category_id;
        this.category_name=categoryName;
    }
	Item() {
		
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getCategory() {
		return category;
	}

	public void setCategory(long category) {
		this.category = category;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getQuantity() {
		return quantity;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

//	public Set<Review> getReiviews() {
//		return reiviews;
//	}
//
//	public void setReiviews(Set<Review> reiviews) {
//		this.reiviews = reiviews;
//	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public MultipartFile getPhoto() {
		return photo;
	}

	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
//	@Override
//	  public int hashCode() {
//	    final int prime = 31;
//	    int result = 1;
//	    result = prime * result + ((type == null) ? 0 : type.hashCode());
//	    return result;
//	  }
//
	  @Override
	  public boolean equals(Object obj) {
	    if (this == obj)
	      return true;
	    if (obj == null)
	      return false;
	    if (!(obj instanceof Item))
	      return false;
	    Item other = (Item) obj;
	    return this.id == other.id;
	  }
//	
}
