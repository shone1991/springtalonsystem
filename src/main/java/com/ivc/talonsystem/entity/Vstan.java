package com.ivc.talonsystem.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "v_stan")
public class Vstan extends AbstractCompany implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2553645994120939975L;

	@Transient
	private Integer kod;
	@NotEmpty
    @Column(name = "NAME")
    private String name;
    @JoinColumn(name = "OTD", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Rju otd;
    
    
    
 	public Integer getKod() {
		return kod;
	}
	public void setKod(Integer kod) {
		this.kod = kod;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Rju getOtd() {
		return otd;
	}
	public void setOtd(Rju otd) {
		this.otd = otd;
	}

	
	
	
    
}
