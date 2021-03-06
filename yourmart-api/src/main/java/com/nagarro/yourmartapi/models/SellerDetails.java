package com.nagarro.yourmartapi.models;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


@XmlRootElement
@Entity
@Table(name = "sellerdetails")
public class SellerDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
		private int id;
	
	@Column(name = "companyname", unique = false, nullable = false, length = 1000)
	private String companyname;
	
	@Column(name = "ownername", unique = false, nullable = false, length = 1000)
	private String ownername;

	@Column(name = "address", unique = false, nullable = false, length = 100)
	private String address;

	@Column(name = "email", unique = false, nullable = false, length = 1000)
	private String email;

	@Column(name = "telephone", unique = false, nullable = false, length = 1000)
	private String telephone;

	@Column(name = "gstnumber", unique = false, nullable = false, length = 1000)
	private String gstnumber;

	@Column(name = "createdat", unique = false, nullable = false, length = 1000)
	private String createdat;
	
	
	
	public String getCreatedat() {
		return createdat;
	}

	public void setCreatedat(String createdat) {
		this.createdat = createdat;
	}

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="sellerid")
	private Seller seller;

	
	
	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getOwnername() {
		return ownername;
	}

	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getGstnumber() {
		return gstnumber;
	}

	public void setGstnumber(String gstnumber) {
		this.gstnumber = gstnumber;
	}

	

	

}
