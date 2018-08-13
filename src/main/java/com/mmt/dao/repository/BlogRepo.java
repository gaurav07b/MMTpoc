package com.mmt.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mmt.dao.entity.TravelBlogs;

@Repository("travelBlogRepo")
public interface BlogRepo extends JpaRepository<TravelBlogs, Long> {
	
	TravelBlogs findById(long id);
	
}
