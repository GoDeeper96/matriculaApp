package com.pe.matricula.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.pe.matricula.entities.Admission;

public interface IAdmissionRepository extends JpaRepository<Admission, Long>{
	@Query("select count(a.idadmission) from Admission a where a.idadmission=:coursename")
	public int findCourseByName(@Param("coursename") String coursename);
	
	@Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Admission a WHERE a.users.lastName=:name")
	public boolean findAdmissionByUsername(@Param("name")String name);
	
//	@Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Admission a WHERE a.users.lastName=:name ")
	
	@Modifying
	@Query("DELETE FROM Admission a WHERE a.idadmission=?1")
	public void delByName(long idadmission);
}
