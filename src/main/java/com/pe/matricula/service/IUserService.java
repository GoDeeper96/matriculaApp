package com.pe.matricula.service;

import java.util.List;

import com.pe.matricula.entities.Users;

public interface IUserService {
	public Integer insert(Users user);

	List<Users> list();

	public void delete(long id);

	Users listarId(long idUsers);
	
	public boolean insertboo(Users user);
	
	List<Users> findByNameUsers(String name);
	
	List<Users> findByNameLikeIgnoreCase(String name);
	
	Users listName(String name);
	
}
