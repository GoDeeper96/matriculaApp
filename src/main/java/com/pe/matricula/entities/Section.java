package com.pe.matricula.entities;

import java.util.ArrayList;
import java.util.List;
//import java.util.List;
//import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.pe.matricula.entities.Section;

@Entity
@Table(name = "Section")
public class Section {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idSection;

	@Column(name = "horario", nullable = false)
	private String horario;

	@Column(name = "fecha", nullable = false)
	private String fecha;

	@ManyToOne
	@JoinColumn(name = "iduser", nullable = false)
	private Users users;

	@ManyToOne
	@JoinColumn(name = "idcourse", nullable = false)
	private Course course;

	@ManyToMany(mappedBy="sections")
	private List<Admission> admissions=new ArrayList<>();;

	public Section() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Section(long idSection, String horario, String fecha, Users users, Course course) {
		super();
		this.idSection = idSection;
		this.horario = horario;
		this.fecha = fecha;
		this.users = users;
		this.course = course;
	}

	public long getIdSection() {
		return idSection;
	}

	public void setIdSection(long idSection) {
		this.idSection = idSection;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<Admission> getAdmissions() {
		return admissions;
	}

	public void setAdmissions(List<Admission> admissions) {
		this.admissions = admissions;
	}


	
	

}
