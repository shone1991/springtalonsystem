package com.ivc.talonsystem.dao;

import java.util.List;

import com.ivc.talonsystem.entity.PostJob;

public interface PostJobDao {
	PostJob findById(int id);

	PostJob findByPostName(String postname);

	void save(PostJob postjob);

	void edit(PostJob postjob);

	void delete(String postname);

	List<PostJob> findAllPostJobs();
	
	List<PostJob> findAllPostJobs(Integer offset, Integer maxResult);
}
