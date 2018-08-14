package com.mmt.service;

import java.util.List;

import com.mmt.dto.TravelBlogsDto;

public interface IBlogService {
	
	void addNewBlogs(TravelBlogsDto blogDto);
	TravelBlogsDto readBlogs(long blogId);
	void removeBlogs(long blogId);
	List<TravelBlogsDto> allBlogInfo();
	
}
