package com.ivc.talonsystem.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name="jobpost")
public class PostJob implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9209702627857778024L;
	
	@Id
	@GenericGenerator(name = "postjob_id", strategy = "com.ivc.talonsystem.util.keygens.PostIdGenerator")
    @GeneratedValue(generator = "postjob_id")  
	@Column(name = "id")
	private Integer id;
	
	@NotEmpty
    @Column(name = "postname")
	private String postname;
	
    @Column(name = "category")
	private String category;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPostname() {
		return postname;
	}

	public void setPostname(String postname) {
		this.postname = postname;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((postname == null) ? 0 : postname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PostJob other = (PostJob) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (postname == null) {
			if (other.postname != null)
				return false;
		} else if (!postname.equals(other.postname))
			return false;
		return true;
	}
	
	
	
}
