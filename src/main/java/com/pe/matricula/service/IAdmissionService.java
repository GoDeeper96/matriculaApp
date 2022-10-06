package com.pe.matricula.service;

import java.util.List;


import com.pe.matricula.entities.Admission;

public interface IAdmissionService {
	public boolean insert(Admission idAdmission);
	List<Admission> list();

	Admission listarId(long idAdmission);

	public void delSAbyId(long idAdmission);
	
	public boolean userHasAdmission(String username);
	
	public void delByUsername(long idadmission);
}
