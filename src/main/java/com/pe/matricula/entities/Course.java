package com.pe.matricula.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Course")
public class Course {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long ccourse;
	
	@Column(name="coursename",nullable=false,length= 30)
	private String coursename;
	
	@Column(name="coursegroup",nullable=false,length=30)
	private String coursegroup;

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Course(Long ccourse, String coursename, String coursegroup) {
		super();
		this.ccourse = ccourse;
		this.coursename = coursename;
		this.coursegroup = coursegroup;
	}

	@Override
	public int hashCode() {
		return Objects.hash(ccourse, coursegroup, coursename);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(ccourse, other.ccourse) && Objects.equals(coursegroup, other.coursegroup)
				&& Objects.equals(coursename, other.coursename);
	}

	public Long getCcourse() {
		return ccourse;
	}

	public void setCcourse(Long ccourse) {
		this.ccourse = ccourse;
	}

	public String getCoursename() {
		return coursename;
	}

	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}

	public String getCoursegroup() {
		return coursegroup;
	}

	public void setCoursegroup(String coursegroup) {
		this.coursegroup = coursegroup;
	}
	
}
