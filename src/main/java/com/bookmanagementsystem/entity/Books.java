package com.bookmanagementsystem.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
public class Books {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
    @Column(nullable = false) 
	private String bookName;
	@Column(nullable = false)
	private String publication;
	@Column(nullable = false)
	private Double price;
	
	@CreationTimestamp
	@Column(updatable = false)
	@Temporal(TemporalType.DATE)
	private Date createdDate;
	
	@UpdateTimestamp
	@Column(insertable = false)
	@Temporal(TemporalType.DATE)
	private Date updatedDate;

	public Books() {
		//
	}
	public Books(Integer id, String bookName, String publication, Double price, Date createdDate, Date updatedDate) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.publication = publication;
		this.price = price;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getPublication() {
		return publication;
	}

	public void setPublication(String publication) {
		this.publication = publication;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String toString() {
		return "Books [id=" + id + ", bookName=" + bookName + ", publication=" + publication + ", price=" + price
				+ ", createdDate=" + createdDate + ", updatedDate=" + updatedDate + "]";
	}
	
	
}
