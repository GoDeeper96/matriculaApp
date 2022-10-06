package com.pe.matricula.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pe.matricula.entities.Course;
import com.pe.matricula.repositories.ICourseRepository;
import com.pe.matricula.service.ICourseService;

@Service
public class CourseServiceImpl implements ICourseService{
	
	@Autowired
	private ICourseRepository cR;
	
	@Override
	@Transactional
	public boolean insert(Course course) {
		// TODO Auto-generated method stub
		Course objCourse = cR.save(course);
		if (objCourse == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	@Transactional
	public void delete(long ccourse) {
		// TODO Auto-generated method stub
		cR.deleteById(ccourse);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Course> list() {
		// TODO Auto-generated method stub
		return cR.findAll();
	}


	@Override
	public Course listarId(long ccourse) {
		// TODO Auto-generated method stub
		Optional<Course> op = cR.findById(ccourse);
		return op.isPresent() ? op.get() : new Course();
	}

	@Override
	public List<Course> fetchCourseByName(String nameCourses) {
		// TODO Auto-generated method stub
		return cR.fetchCoursesByName(nameCourses);
	}
	
	
}
