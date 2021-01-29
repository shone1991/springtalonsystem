package com.ivc.talonsystem.entity;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="rju")
public class Rju extends AbstractCompany implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4294142403795421252L;
	
	
	//this field just duplicates @Column(name="callname") and serves for mapping to Rju fullname field
//	@NotEmpty
    @Column(name = "fullname")
    private String namerju;
	@NotEmpty
    @Column(name = "briefname")
    private String briefname;
	
	@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "otd")
    private Collection<Vstan> vStanCollection;
	
//	@LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "rju", orphanRemoval = true)
    private Collection<Underrju> underRjuCollection;
    
    

	public String getNamerju() {
		return namerju;
	}
	public void setNamerju(String namerju) {
		this.namerju = namerju;
	}
	public String getBriefname() {
		return briefname;
	}
	public void setBriefname(String briefname) {
		this.briefname = briefname;
	}
	public Collection<Vstan> getvStanCollection() {
		return vStanCollection;
	}
	public void setvStanCollection(Collection<Vstan> vStanCollection) {
		this.vStanCollection = vStanCollection;
	}
	
	
	public Collection<Underrju> getUnderRjuCollection() {
		return underRjuCollection;
	}
	public void setUnderRjuCollection(Collection<Underrju> underRjuCollection) {
		this.underRjuCollection = underRjuCollection;
	}

	

	
    
    
	
}
