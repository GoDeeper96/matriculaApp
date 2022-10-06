package com.pe.matricula.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pe.matricula.entities.Course;

@Repository
public interface ICourseRepository extends JpaRepository<Course, Long> {
	@Query("select count(c.coursename) from Course c where c.coursename=:coursename")
	public int findCourseByName(@Param("coursename") String coursename);
	
	@Query("select co from Course co where co.coursename like %?1%")
	List<Course> fetchCoursesByName(String nombre);

}
