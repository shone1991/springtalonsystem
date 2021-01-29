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
@Table(name="underunitdep")
public class Underunitdep extends AbstractCompany implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5301834652283678138L;

	
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Valid
    @JoinColumn(name = "ID_UNITDEP", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private UnitDepart unitdep;

  



	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public UnitDepart getUnitdep() {
		return unitdep;
	}


	public void setUnitdep(UnitDepart idUnitdep) {
		this.unitdep = idUnitdep;
	}


	
	
    
}
