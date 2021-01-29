package com.ivc.talonsystem.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="unitdepart")
public class UnitDepart extends AbstractCompany implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6755135810656178204L;
	

	@Column(name = "NAMEUNIT")
    private String nameUnit;

	
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "unitdep")
    private Collection<Underunitdep> underunitdepCollection;
    
    
   
	public String getNameUnit() {
		return nameUnit;
	}

	public void setNameUnit(String nameUnit) {
		this.nameUnit = nameUnit;
	}

	public Collection<Underunitdep> getUnderunitdepCollection() {
		return underunitdepCollection;
	}

	public void setUnderunitdepCollection(Collection<Underunitdep> underunitdepCollection) {
		this.underunitdepCollection = underunitdepCollection;
	}

	
	
    
}
