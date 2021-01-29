package com.ivc.talonsystem.DTO;

import java.io.Serializable;

public class AbstractCompanyDTO implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -1803407655365312280L;


	private Integer id;
	private String callname;
	public AbstractCompanyDTO(Integer id, String callname) {
		this.id=id;
		this.callname=callname;
	}
	public AbstractCompanyDTO() {
		
	}
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
	
	

}
