package com.pe.matricula.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pe.matricula.entities.Role;
public interface IRoleRepository extends JpaRepository<Role, Long> {
	@Query(value = "Select r.rol, count(u.edad_user)" + 
            " from Users u join roles r on r.user_id= u.id" + 
            " Where CAST(u.edad_user AS NUMERIC) > 25" + 
            " group by r.rol", nativeQuery=true)
public List<String[]> rolXedad();
}
