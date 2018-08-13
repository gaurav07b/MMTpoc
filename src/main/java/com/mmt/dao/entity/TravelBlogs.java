package com.mmt.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TravelBlogs {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="blogid")
	private long id;
	
	@Column(name="blogtitle")
	private String blogName;
	
	@Column(name="blogimage")
	private String imgURL;
	
	@Column(name="blog")
	private String blogURL;

	public TravelBlogs() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBlogName() {
		return blogName;
	}

	public void setBlogName(String blogName) {
		this.blogName = blogName;
	}

	public String getImgURL() {
		return imgURL;
	}

	public void setImgURL(String imgURL) {
		this.imgURL = imgURL;
	}

	public String getBlogURL() {
		return blogURL;
	}

	public void setBlogURL(String blogURL) {
		this.blogURL = blogURL;
	}
		
}
