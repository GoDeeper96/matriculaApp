package com.pe.matricula.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pe.matricula.entities.Section;


@Repository
public interface ISectionRepository extends JpaRepository<Section, Long>{
	@Query("select count(sa.idSection) from Section sa where sa.idSection=:name")
	public long buscarSeccion(@Param("name") String coursename);
	
	@Query("select sa from Section sa where sa.course.coursename like %?1%")
	List<Section> fetchCourseByName(String coursename);
}
