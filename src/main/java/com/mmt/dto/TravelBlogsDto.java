package com.mmt.dto;

public class TravelBlogsDto {

		private long id;
		
		private String blogName;
		
		private String imgURL;
		
		private String blogURL;

		public TravelBlogsDto() {
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
