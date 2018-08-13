//package com.mmt.dao.entity;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.validation.constraints.Size;
//
//@Entity
//@Table(name="offers")
//public class OfferDetails {
//	
//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	private long id;
//	
//	@Column(name="head")
//	private String header;
//	
//	@Column(name="couponcode")
//	@Size(min=6, max=20)
//	private String code;
//	
//	@Column(name="condition")
//	private String  conditionStmt;
//	
//	@Column(name="imageURL")
//	private String path;
//
//	public OfferDetails() {
//		super();
//	}
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public String getHeader() {
//		return header;
//	}
//
//	public void setHeader(String header) {
//		this.header = header;
//	}
//
//	public String getCode() {
//		return code;
//	}
//
//	public void setCode(String code) {
//		this.code = code;
//	}
//
//	public String getConditionStmt() {
//		return conditionStmt;
//	}
//
//	public void setConditionStmt(String conditionStmt) {
//		this.conditionStmt = conditionStmt;
//	}
//
//	public String getPath() {
//		return path;
//	}
//
//	public void setPath(String path) {
//		this.path = path;
//	}
//	
//}
