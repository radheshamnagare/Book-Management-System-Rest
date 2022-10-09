package com.bookmanagementsystem.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;


@Entity
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(nullable = false)
	private String authorName;
	@Column(nullable = false)
	private String authorGender;
    @Column(nullable = false)
    @Email(message = "invalid email")
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    @Column(nullable = false)
    private String address;
	@Column(nullable = false)
	@Size(min=10,max=12)
    private String contactNumber;
    
	@CreationTimestamp
	@Column(updatable = false)
	@Temporal(TemporalType.DATE)
    private Date createdDate;
    
	@CreationTimestamp
	@Column(insertable = false)
	@Temporal(TemporalType.DATE)
	private Date updatedDate;
	
	@OneToMany(targetEntity = Books.class,cascade = CascadeType.ALL)
	@JoinColumn(name="bookAuthor",referencedColumnName = "id")
	private List<Books> books;

	
	public Author() {
		
	}

	public Author(Integer id, String authorName, String authorGender, String email, String address,
			String contactNumber, List<Books> books, Date createdDate, Date updatedDate) {
		super();
		this.id = id;
		this.authorName = authorName;
		this.authorGender = authorGender;
		this.email = email;
		this.address = address;
		this.contactNumber = contactNumber;
		this.books = books;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorGender() {
		return authorGender;
	}

	public void setAuthorGender(String authorGender) {
		this.authorGender = authorGender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public List<Books> getBooks() {
		return books;
	}

	public void setBooks(List<Books> books) {
		this.books = books;
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
		return "Author [id=" + id + ", authorName=" + authorName + ", authorGender=" + authorGender + ", email=" + email
				+ ", address=" + address + ", contactNumber=" + contactNumber + ", books=" + books + ", createdDate="
				+ createdDate + ", updatedDate=" + updatedDate + "]";
	}

	
	
}
