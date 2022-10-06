package com.pe.matricula.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pe.matricula.entities.Role;
import com.pe.matricula.repositories.IRoleRepository;
import com.pe.matricula.service.IRoleService;

@Service
public class RoleServiceImpl implements IRoleService{
	@Autowired
	IRoleRepository rR;

	@Override
	public void insert(Role rol) {
		// TODO Auto-generated method stub
		rR.save(rol);
	}

	@Override
	public List<Role> list() {
		// TODO Auto-generated method stub
		return rR.findAll();
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		rR.deleteById(id);
		
	}

	@Override
	public Optional<Role> listarId(long id) {
		// TODO Auto-generated method stub
		return rR.findById(id);
	}
}
