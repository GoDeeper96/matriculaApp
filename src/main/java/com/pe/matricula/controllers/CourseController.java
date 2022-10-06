//package com.pe.matricula.controllers;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//	
//import com.pe.matricula.entities.Course;
//import com.pe.matricula.service.ICourseService;
//
////@Secured("ROLE_USER")
//@RestController
//@RequestMapping("/courses")
//@CrossOrigin
//public class CourseController {
//	
//	@Autowired
//	private ICourseService cService;
//	
//	@PostMapping("/save")
//	public String saveCourse(@RequestBody Course course) {
//		cService.insert(course);
//		return "good";				
//	}
//	@GetMapping("/list")
//	public List<Course> list() {
//		return cService.list();
//		
//	}
//}
package com.pe.matricula.controllers;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pe.matricula.entities.Course;
import com.pe.matricula.service.ICourseService;


@Controller
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private ICourseService coService;
	

	@GetMapping("/new")
	public String newProduct(Model model) {
		model.addAttribute("course", new Course());
		return "course/course";
	}
	@GetMapping("/list")
	public String listCourses(Model model) {
		try {
			model.addAttribute("course", new Course());
			model.addAttribute("listaCursos", coService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "course/listCourses";
	}

	@RequestMapping("/save")
	public String insertCourse(@ModelAttribute @Valid Course objPro, BindingResult binRes, Model model)
			throws ParseException {
		if (binRes.hasErrors()) {
			return "user/user";
		} else {
			boolean flag = coService.insert(objPro);
			if (flag) {
				return "redirect:/courses/list";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/courses/new";
			}
		}

	}

	@RequestMapping("/list")
	public String listCourses(Map<String, Object> model) {
		model.put("listaCursos", coService.list());
		return "course/listCourses";

	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Course cor) {
		coService.listarId(cor.getCcourse());
		return "course/listCourses";

	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable long id, Model model, RedirectAttributes objRedir) {

		Course objPro = coService.listarId(id);
		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
			return "redirect:/products/list";
		} else {
			model.addAttribute("course", objPro);
			return "course/course";
		}
	}
	@RequestMapping("/searchbyName")
	public String findByName(Map<String, Object>model,@ModelAttribute Course course)throws ParseException {
		List<Course> listCourses;
		course.setCoursename(course.getCoursename());
		listCourses = coService.fetchCourseByName(course.getCoursename());
		model.put("course", new Course());
		if (listCourses.isEmpty()) {
			model.put("mensaje", "No se encontro el curso perteneciente a la lista de cursos actual");
		}
		model.put("listaCursos", listCourses);
		return "course/listCourses";
			

		
	}
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable long id, Model model, RedirectAttributes objRedir) {

		Course objPro = coService.listarId(id);
		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
			return "redirect:/courses/list";
		} else {
			coService.delete(id);
			return "redirect:/courses/list";
		}
	}

}

