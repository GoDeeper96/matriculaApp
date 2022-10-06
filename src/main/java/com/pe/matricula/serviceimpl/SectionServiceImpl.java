package com.pe.matricula.serviceimpl;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.matricula.entities.Section;
import com.pe.matricula.repositories.ISectionRepository;
import com.pe.matricula.service.ISectionService;
@Service
public class SectionServiceImpl implements ISectionService{
	
	@Autowired
	private ISectionRepository cl;

	@Override
	public boolean insert(Section idsection) {
		Section objCoursesList = cl.save(idsection);
		if(objCoursesList==null)
		{
			return false;
		}
		else
		{
			return true;
		}
	
	}

	@Override
	public List<Section> list() {
		// TODO Auto-generated method stub
		return cl.findAll();
	}

	@Override
	@Transactional(readOnly=true)
	public Section listarId(long idsection) {
		// TODO Auto-generated method stub
		Optional<Section> cls = cl.findById(idsection);
		return cls.isPresent()? cls.get(): new Section();
	}

	@Override
	public void delSAbyId(long idsection) {
		cl.deleteById(idsection);
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Section> fetchCourseByName(String sectionname) {
		// TODO Auto-generated method stub
		return cl.fetchCourseByName(sectionname);
	}
	
	
}
