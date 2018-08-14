package com.mmt.service;

import java.util.ArrayList;
import java.util.List;
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
		blogDto.setId(blogEnt.getId());
		blogDto.setBlogName(blogEnt.getBlogName());
		blogDto.setImgURL(blogEnt.getImgURL());
		blogDto.setBlogURL(blogEnt.getBlogURL());
		return blogDto;
	}

	@Override
	public void removeBlogs(long blogId) {
		blogRepo.deleteById(blogId);
	}

	@Override
	public List<TravelBlogsDto> allBlogInfo() {
		List<TravelBlogs> blogEnt;
		List<TravelBlogsDto> blogDto = new ArrayList<>();
		blogEnt =  blogRepo.findAll();
		for (TravelBlogs travelBlogsEnt : blogEnt) {
			blogDto.add(assemblerBlog(travelBlogsEnt));
		}
		return blogDto;
	}
	
	TravelBlogsDto assemblerBlog(TravelBlogs tBlogEnt) {
		TravelBlogsDto tBlogDto = new TravelBlogsDto();
		tBlogDto.setId(tBlogEnt.getId());
		tBlogDto.setBlogName(tBlogEnt.getBlogName());
		tBlogDto.setImgURL(tBlogEnt.getImgURL());
		tBlogDto.setBlogURL(tBlogEnt.getBlogURL());
		return tBlogDto;
	}

}
