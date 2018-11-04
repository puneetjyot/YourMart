package com.nagarro.yourmartapi.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name = "productdetails")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	
	
	@Column(name = "productcode", unique = false, nullable = false, length = 1000)
	private String productcode;
	
	@Column(name = "productname", unique = false, nullable = false, length = 1000)
	private String productname;
	
	@Column(name = "shortdiscription", unique = false, nullable = false, length = 1000)
	private String shortdiscription;
	
	@Column(name = "longdescription", unique = false, nullable = false, length = 1000)
	private String longdescription;
	
	@Column(name = "dimensions", unique = false, nullable = false, length = 1000)
	private String dimensions;
	
	@Column(name = "mrp", unique = false, nullable = false, length = 1000)
	private float mrp;
	
	@Column(name = "ssp", unique = false, nullable = false, length = 1000)
	private float ssp;
	
	@Column(name = "ymp", unique = false, nullable = false, length = 1000)
	private float ymp;
	
	@Column(name = "primaryimage", unique = false, nullable = false, length = 1000)
	private String primaryimage;
	
	@Column(name = "usageinstructions", unique = false, nullable = false, length = 1000)
	private String usageinstructions;
	
	@Column(name = "createdat", unique = false, nullable = false, length = 1000)
	private String createdat;
	
	@Column(name = "updatedat", unique = false, nullable = false, length = 1000)
	private String updatedat;
	
	@Column(name = "comments", unique = false, nullable = false, length = 1000)
	private String comments;
	
	@Column(name = "productattribute", unique = false, nullable = false, length = 1000)
	private String productattribute;
	
	@Column(name = "status", unique = false, nullable = false, length = 1000)
	private String status;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@ManyToOne(optional=false)
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

	public String getProductcode() {
		return productcode;
	}

	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getShortdiscription() {
		return shortdiscription;
	}

	public void setShortdiscription(String shortdiscription) {
		this.shortdiscription = shortdiscription;
	}

	public String getLongdescription() {
		return longdescription;
	}

	public void setLongdescription(String longdescription) {
		this.longdescription = longdescription;
	}

	public String getDimensions() {
		return dimensions;
	}

	public void setDimensions(String dimensions) {
		this.dimensions = dimensions;
	}

	public float getMrp() {
		return mrp;
	}

	public void setMrp(float mrp) {
		this.mrp = mrp;
	}

	public float getSsp() {
		return ssp;
	}

	public void setSsp(float ssp) {
		this.ssp = ssp;
	}

	public float getYmp() {
		return ymp;
	}

	public void setYmp(float ymp) {
		this.ymp = ymp;
	}

	public String getPrimaryimage() {
		return primaryimage;
	}

	public void setPrimaryimage(String primaryimage) {
		this.primaryimage = primaryimage;
	}

	public String getUsageinstructions() {
		return usageinstructions;
	}

	public void setUsageinstructions(String usageinstructions) {
		this.usageinstructions = usageinstructions;
	}

	public String getCreatedat() {
		return createdat;
	}

	public void setCreatedat(String createdat) {
		this.createdat = createdat;
	}

	public String getUpdatedat() {
		return updatedat;
	}

	public void setUpdatedat(String updatedat) {
		this.updatedat = updatedat;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getProductattribute() {
		return productattribute;
	}

	public void setProductattribute(String productattribute) {
		this.productattribute = productattribute;
	}

	
}
