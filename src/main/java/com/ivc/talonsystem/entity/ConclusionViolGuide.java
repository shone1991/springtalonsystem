package com.ivc.talonsystem.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="conclusionviolguide")
public class ConclusionViolGuide implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9164883416499106136L;
	
	@Id
	@GenericGenerator(name = "concl_id", strategy = "com.ivc.talonsystem.util.keygens.ConclViolGuideIdGenerator")
    @GeneratedValue(generator = "concl_id")  
	@Column(name = "id")
	private Integer id;
	
	@Lob
	@NotEmpty
    @Column(name = "content")
	private String content;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "conclviolguide")
    private Collection<Violguide> violGuideCollection;
	
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

	public Collection<Violguide> getViolGuideCollection() {
		return violGuideCollection;
	}

	public void setViolGuideCollection(Collection<Violguide> violGuideCollection) {
		this.violGuideCollection = violGuideCollection;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
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
		ConclusionViolGuide other = (ConclusionViolGuide) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	


}
