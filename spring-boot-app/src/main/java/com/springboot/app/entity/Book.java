package com.springboot.app.entity;



import java.io.Serializable;
import java.sql.Date;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.annotation.Nonnull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="book")
public class Book implements Serializable{
	private static final long serialVersionUID = -2731425678149216053L;
	
	@Id
	@GeneratedValue
	private long id;
	

	@Column(name = "bookName", length=100)
	@NotEmpty
	private String bookName;
	@Column(name = "bookAuthor", length=100)
	private String bookAuthor;
	@Column(name = "bookPublishar", length=100)
	private String bookPublishar;
	@Column(name = "bookPrice", length=100)
	private float bookPrice;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date created_dt;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date modify_dt;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="categoryId" ,referencedColumnName="id")
	private Category category;


	public Book() {
		super();
	}


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getBookAuthor() {
		return bookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
	public String getBookPublishar() {
		return bookPublishar;
	}
	public void setBookPublishar(String bookPublishar) {
		this.bookPublishar = bookPublishar;
	}
	public float getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(float bookPrice) {
		this.bookPrice = bookPrice;
	}
	public Date getCreated_dt() {
		return created_dt;
	}
	public void setCreated_dt(Date created_dt) {
		this.created_dt = created_dt;
	}
	public Date getModify_dt() {
		return modify_dt;
	}
	public void setModify_dt(Date modify_dt) {
		this.modify_dt = modify_dt;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	
}
