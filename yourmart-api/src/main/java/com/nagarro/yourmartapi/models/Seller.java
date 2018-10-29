package com.nagarro.yourmartapi.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "seller")
public class Seller {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
		private int id;
	
	@Column(name = "sellername", unique = false, nullable = false, length = 1000)
	private String sellername;
	
	@Column(name = "sellerpassword", unique = false, nullable = false, length = 1000)
	private String sellerpassword;
	
	@Column(name = "sellerstatus", unique = false, nullable = false, length = 1000)
	private String sellerstatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSellername() {
		return sellername;
	}

	public void setSellername(String sellername) {
		this.sellername = sellername;
	}

	public String getSellerpassword() {
		return sellerpassword;
	}

	public void setSellerpassword(String sellerpassword) {
		this.sellerpassword = sellerpassword;
	}

	public String getSellerstatus() {
		return sellerstatus;
	}

	public void setSellerstatus(String sellerstatus) {
		this.sellerstatus = sellerstatus;
	}


	
	

}
