package com.pe.matricula.service;

import java.util.List;

import com.pe.matricula.entities.Course;

public interface ICourseService {
	public boolean insert(Course course);
	
	List<Course> list();
	
	Course listarId(long ccourse);
	
	public void delete(long ccourse);
	
	List<Course> fetchCourseByName(String nameProduct);
	
	
}
