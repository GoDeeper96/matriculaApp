package com.pe.matricula.service;
import java.util.List;
import java.util.Optional;

import com.pe.matricula.entities.Role;
public interface IRoleService {
	public void insert(Role rol);
	List <Role> list();
	public void delete(long id);
	
	Optional<Role> listarId(long id);
}
