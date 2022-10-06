package com.pe.matricula.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.matricula.entities.Admission;
import com.pe.matricula.repositories.IAdmissionRepository;
import com.pe.matricula.service.IAdmissionService;

@Service
public class AdmissionServiceImpl implements IAdmissionService{

	@Autowired
	private IAdmissionRepository aR;
	@Override
	public boolean insert(Admission idAdmission) {
		// TODO Auto-generated method stub
		Admission objAdmission = aR.save(idAdmission);
		if (objAdmission == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional(readOnly = true)
	public List<Admission> list() {
		// TODO Auto-generated method stub
		return aR.findAll();
	}

	@Override
	public Admission listarId(long idAdmission) {
		// TODO Auto-generated method stub
		Optional<Admission> op = aR.findById(idAdmission);
		return op.isPresent() ? op.get() : new Admission();
	}

	@Override
	public void delSAbyId(long idAdmission) {
		// TODO Auto-generated method stub
		aR.deleteById(idAdmission);
	}

	@Override
	public boolean userHasAdmission(String username) {
		// TODO Auto-generated method stub
		return aR.findAdmissionByUsername(username);
//		if(aR.findAdmissionByUsername(username)) {
//			return
//		}
//		return ;
	}

	@Override
	@Transactional
	public void delByUsername(long idadmission) {
		// TODO Auto-generated method stub
		aR.delByName(idadmission);
	}
	
}
