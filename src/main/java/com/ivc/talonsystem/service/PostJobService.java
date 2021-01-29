package com.ivc.talonsystem.service;

import java.util.List;

import com.ivc.talonsystem.entity.PostJob;

public interface PostJobService {
	PostJob findById(int id);

	PostJob findByPostName(String postname);

	void savePostJob(PostJob job);

	void updatePostJob(PostJob job);

	void delete(String postname);

	List<PostJob> findAllPostJobs();
	
	List<PostJob> findAllPostJobs(Integer offset, Integer maxResult);
	
	List<PostJob> findAllPostJobsWhereNameLike(String namelike);
	
	boolean isPostJobNameUnique(String postname);
}
