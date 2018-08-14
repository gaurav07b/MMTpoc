package com.mmt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mmt.dto.TravelBlogsDto;
import com.mmt.response.ResponseData;
import com.mmt.service.BlogServiceImpl;

@RestController("travelBlog")
@RequestMapping("/mmt/blog")
public class BlogController {

	@Autowired
	private BlogServiceImpl blogImpl;

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/writeNew", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public void addBlog(@RequestBody TravelBlogsDto tBlogDto) {
		blogImpl.addNewBlogs(tBlogDto);
	}

	@RequestMapping(value = "/open_blog/{id}", method = RequestMethod.GET)
	public ResponseData showBlog(@PathVariable(value = "id") long id) {
		TravelBlogsDto blogData;
		blogData = blogImpl.readBlogs(id);
		ResponseData blogResponse = new ResponseData("200", "Opening Blog", blogData, null);
		return blogResponse;
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/moveToTrash/{id}", method = RequestMethod.DELETE)
	public void deleteBlog(@PathVariable(value = "id") long id) {
		blogImpl.removeBlogs(id);
	}

}
