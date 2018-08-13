package com.mmt.service;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mmt.dao.entity.TravelBlogs;
import com.mmt.dao.repository.BlogRepo;
import com.mmt.dto.TravelBlogsDto;

@Service("blogService")
public class BlogServiceImpl implements IBlogService{
	
	@Autowired
	private BlogRepo blogRepo;
	
	Logger logger;

	@Override
	public void addNewBlogs(TravelBlogsDto blogDto) {
		
		TravelBlogs blogEnt = new TravelBlogs();
		blogEnt.setBlogName(blogDto.getBlogName());
		blogEnt.setImgURL(blogDto.getImgURL());
		blogEnt.setBlogURL(blogDto.getBlogURL());
		
		blogRepo.save(blogEnt);
		
	}

	@Override
	public TravelBlogsDto readBlogs(long blogId) {
		TravelBlogs blogEnt;
		blogEnt = blogRepo.findById(blogId);
		TravelBlogsDto blogDto = new TravelBlogsDto();
		blogDto.setBlogName(blogEnt.getBlogName());
		blogDto.setImgURL(blogEnt.getImgURL());
		blogDto.setBlogURL(blogEnt.getBlogURL());
		return blogDto;
	}

	@Override
	public void removeBlogs(long blogId) {
		blogRepo.deleteById(blogId);
	}

}
