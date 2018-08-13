package com.mmt.service;

import com.mmt.dto.TravelBlogsDto;

public interface IBlogService {
	
	void addNewBlogs(TravelBlogsDto blogDto);
	TravelBlogsDto readBlogs(long blogId);
	void removeBlogs(long blogId);
	
}
