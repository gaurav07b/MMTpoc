/*package com.mmt.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="offers")
public class OfferDetails {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="head")
	private String header;
	
	@Column(name="couponcode")
	@Size(min=6, max=20)
	private String code;
	
	@Column(name="condition")
	private String  conditionStmt;
	
	@Column(name="imageURL")
	private String path;
	
	

}
*/