package com.pe.matricula.service;

import java.util.List;

import com.pe.matricula.entities.Section;

public interface ISectionService {
	public boolean insert(Section idsection);

	List<Section> list();

	Section listarId(long idsection);
	
	List<Section> fetchCourseByName(String sectionname);

	public void delSAbyId(long idsection);
}
