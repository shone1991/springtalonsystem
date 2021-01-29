package com.ivc.talonsystem.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="violation")
public class Violation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5144260292214389455L;
	
	@Id
	@GenericGenerator(name = "viol_id", strategy = "com.ivc.talonsystem.util.keygens.ViolationIdGenerator")
    @GeneratedValue(generator = "viol_id")
	@Column(name = "id")
	private Integer id;

	@Column(name = "name_empl")
    private String nameEmpl;

    @NotEmpty
    @Column(name = "surname_empl")
    private String surnameEmpl;

    @Column(name = "lastname_empl")
    private String lastnameEmpl;
    
    @NotNull
    @Valid
    @JoinColumn(name = "id_company", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private AbstractCompany company;
    
    @JoinColumn(name = "id_post", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PostJob post;
    
    @NotNull
    @Column(name = "stub_num")
	private Integer stubnum;
    
    @NotNull
    @Valid
    @JoinColumn(name = "id_viol", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Violguide violguide;
    
    @Column(name = "seizedfrom")
    private String seizedFrom;
    
    @NotNull
    @Column(name = "seizedate")
    @Temporal(TemporalType.TIMESTAMP)
	private Date dateseize;
    
    @Column(name = "numorder")
    private String numorder;
    
    @Lob
    @Column(name = "measure")
    private String measure;
    
    @Column(name = "dateorder")
    @Temporal(TemporalType.TIMESTAMP)
	private Date dateorder;
    
    @Column(name = "stopmoney")
	private Integer stopmoney;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameEmpl() {
		return nameEmpl;
	}

	public void setNameEmpl(String nameEmpl) {
		this.nameEmpl = nameEmpl;
	}

	public String getSurnameEmpl() {
		return surnameEmpl;
	}

	public void setSurnameEmpl(String surnameEmpl) {
		this.surnameEmpl = surnameEmpl;
	}

	public String getLastnameEmpl() {
		return lastnameEmpl;
	}

	public void setLastnameEmpl(String lastnameEmpl) {
		this.lastnameEmpl = lastnameEmpl;
	}

	public AbstractCompany getCompany() {
		return company;
	}

	public void setCompany(AbstractCompany company) {
		this.company = company;
	}

	public PostJob getPost() {
		return post;
	}

	public void setPost(PostJob post) {
		this.post = post;
	}

	public Integer getStubnum() {
		return stubnum;
	}

	public void setStubnum(Integer stubnum) {
		this.stubnum = stubnum;
	}

	public Violguide getViolguide() {
		return violguide;
	}

	public void setViolguide(Violguide violguide) {
		this.violguide = violguide;
	}

	public String getSeizedFrom() {
		return seizedFrom;
	}

	public void setSeizedFrom(String seizedFrom) {
		this.seizedFrom = seizedFrom;
	}

	public Date getDateseize() {
		return dateseize;
	}

	public void setDateseize(Date dateseize) {
		this.dateseize = dateseize;
	}

	public String getNumorder() {
		return numorder;
	}

	public void setNumorder(String numorder) {
		this.numorder = numorder;
	}

	public String getMeasure() {
		return measure;
	}

	public void setMeasure(String measure) {
		this.measure = measure;
	}

	public Date getDateorder() {
		return dateorder;
	}

	public void setDateorder(Date dateorder) {
		this.dateorder = dateorder;
	}

	public Integer getStopmoney() {
		return stopmoney;
	}

	public void setStopmoney(Integer stopmoney) {
		this.stopmoney = stopmoney;
	}
    
    
    
    
    
	

}
