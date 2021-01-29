package com.ivc.talonsystem.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="violguide")
public class Violguide implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2144441453340628432L;
	
	@Id
	@GenericGenerator(name = "violguide_id", strategy = "com.ivc.talonsystem.util.keygens.ViolGuideIdGenerator")
    @GeneratedValue(generator = "violguide_id")  
	@Column(name = "id")
	private Integer id;
	@Lob
	@NotEmpty
	@Column(name = "contentviol")
	private String contentViol;
	
	@Column(name = "elements")
    private String elements;
	
	@NotNull
	@Valid
	@JoinColumn(name = "id_concl", referencedColumnName = "id")
    @ManyToOne(optional = false)
	private ConclusionViolGuide conclviolguide;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "violguide")
    private Collection<Violation> violationCollection;

	public Collection<Violation> getViolationCollection() {
		return violationCollection;
	}

	public void setViolationCollection(Collection<Violation> violationCollection) {
		this.violationCollection = violationCollection;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContentViol() {
		return contentViol;
	}

	public void setContentViol(String contentViol) {
		this.contentViol = contentViol;
	}

	public String getElements() {
		return elements;
	}

	public void setElements(String elements) {
		this.elements = elements;
	}

	public ConclusionViolGuide getConclviolguide() {
		return conclviolguide;
	}

	public void setConclviolguide(ConclusionViolGuide conclviolguide) {
		this.conclviolguide = conclviolguide;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conclviolguide == null) ? 0 : conclviolguide.hashCode());
		result = prime * result + ((contentViol == null) ? 0 : contentViol.hashCode());
		result = prime * result + ((elements == null) ? 0 : elements.hashCode());
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
		Violguide other = (Violguide) obj;
		if (conclviolguide == null) {
			if (other.conclviolguide != null)
				return false;
		} else if (!conclviolguide.equals(other.conclviolguide))
			return false;
		if (contentViol == null) {
			if (other.contentViol != null)
				return false;
		} else if (!contentViol.equals(other.contentViol))
			return false;
		if (elements == null) {
			if (other.elements != null)
				return false;
		} else if (!elements.equals(other.elements))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
