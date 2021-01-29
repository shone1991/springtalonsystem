package com.ivc.talonsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ivc.talonsystem.dao.PostJobDao;
import com.ivc.talonsystem.entity.PostJob;
import com.ivc.talonsystem.util.StringUtil;

@Service("postjobService")
@Transactional
public class PostJobServiceImpl implements PostJobService {

	@Autowired
	private PostJobDao postjobDao;
	
	@Override
	public PostJob findById(int id) {
		return postjobDao.findById(id);
	}

	@Override
	public PostJob findByPostName(String postname) {
		return postjobDao.findByPostName(postname);
	}

	@Override
	public void savePostJob(PostJob job) {
		postjobDao.save(job);

	}

	@Override
	public void updatePostJob(PostJob job) {
		postjobDao.edit(job);

	}

	@Override
	public List<PostJob> findAllPostJobs() {
		return postjobDao.findAllPostJobs();
	}

	@Override
	public boolean isPostJobNameUnique(String postname) {
		PostJob job=postjobDao.findByPostName(postname);
		return job==null;
	}

	@Override
	public List<PostJob> findAllPostJobs(Integer offset, Integer maxResult) {
		return postjobDao.findAllPostJobs(offset, maxResult);
	}

	@Override
	public void delete(String postname) {
		postjobDao.delete(postname);
		
	}

	@Override
	public List<PostJob> findAllPostJobsWhereNameLike(String namelike) {
		List<PostJob> postjoblist=findAllPostJobs()
				.stream().filter(p -> StringUtil.containsIgnoreCase(p.getPostname(),namelike.trim()))
				.collect(Collectors.toList());
		return postjoblist;
	}

}
