package com.ivc.talonsystem.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="underrju")
public class Underrju extends AbstractCompany implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2026147847398903848L;


    @Column(name = "name")
    private String name;

    @NotNull
    @Valid
    @JoinColumn(name = "id_rju", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Rju rju;
    

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Rju getRju() {
		return rju;
	}

	public void setRju(Rju rju) {
		this.rju = rju;
	}

	
    
}
