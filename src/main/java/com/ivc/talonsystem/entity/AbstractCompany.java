package com.ivc.talonsystem.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class AbstractCompany implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7548007057600066744L;


	@Id
	@GenericGenerator(name = "abstrcomp_id", strategy = "com.ivc.talonsystem.util.keygens.CompanyIdGenerator")
    @GeneratedValue(generator = "abstrcomp_id")  
	@Column(name = "id")
	private Integer id;
	
	
	@NotEmpty
    @Column(name = "callname")
    private String callname;
	
//	@LazyCollection(LazyCollectionOption.FALSE)
//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
//	private Collection<User> userCollection;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
	private Collection<Violation> violationCollection;

	public Collection<Violation> getViolationCollection() {
		return violationCollection;
	}

	public void setViolationCollection(Collection<Violation> violationCollection) {
		this.violationCollection = violationCollection;
	}

//	public Collection<User> getUserCollection() {
//		return userCollection;
//	}
//
//	public void setUserCollection(Collection<User> userCollection) {
//		this.userCollection = userCollection;
//	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCallname() {
		return callname;
	}

	public void setCallname(String callname) {
		this.callname = callname;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		AbstractCompany other = (AbstractCompany) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
