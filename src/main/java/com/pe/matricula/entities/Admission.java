package com.pe.matricula.entities;
import java.util.ArrayList;
import java.util.List;
//import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Admission")
public class Admission {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique=true)
	private long idadmission;
	

	@ManyToOne
	@JoinColumn(name = "iduser", nullable = false)
	private Users users;
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "admission_section", 
	joinColumns = @JoinColumn(name="admissionid"),
	inverseJoinColumns = @JoinColumn(name="sectionid"))
	private List<Section> sections=new ArrayList<>();
	

	public Admission() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Admission(long idadmission, Users users) {
		super();
		this.idadmission = idadmission;
		this.users = users;
	}


	public long getIdadmission() {
		return idadmission;
	}


	public void setIdadmission(long idadmission) {
		this.idadmission = idadmission;
	}


	public Users getUsers() {
		return users;
	}


	public void setUsers(Users users) {
		this.users = users;
	}
	public List<Section> getSections() {
		return sections;
	}


	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

//	
//	@ManyToOne
//	@JoinColumn(name = "idsection", nullable = false)
//	private List<Section> section;
//	
//	@ManyToMany(cascade = CascadeType.ALL)
//	@JoinTable(name = "admission_course",
//			   joinColumns = { @JoinColumn(name="id_admission")},
//			   inverseJoinColumns= { @JoinColumn(name="id_section")})
//	private List<Section> sections = new ArrayList<Section>();
	

}
