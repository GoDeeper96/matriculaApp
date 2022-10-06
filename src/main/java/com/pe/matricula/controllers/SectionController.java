package com.pe.matricula.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pe.matricula.service.ISectionService;
//import com.pe.matricula.entities.Admission;
import com.pe.matricula.entities.Section;
import com.pe.matricula.entities.Users;
import com.pe.matricula.service.ICourseService;
import com.pe.matricula.service.IRoleService;
import com.pe.matricula.service.IUserService;

@Controller
@RequestMapping("/sections")
public class SectionController {
	@Autowired
	private ISectionService seService;
	
	@Autowired
	private ICourseService coService;
	
	@Autowired
	private IUserService usService;
	
	@Autowired
	private IRoleService uRService;
	
	@GetMapping("/new")
	public String newCourseList(Model model)
	{
		model.addAttribute("section",new Section());
		model.addAttribute("listaCursos",coService.list());
		model.addAttribute("listaUsuarios",byType());
		model.addAttribute("section",new Section());
		return "section/section";
	}
	@GetMapping("/list")
	public String listSections(Model model) {
		try {
			model.addAttribute("section", new Section());
			model.addAttribute("listaSecciones", seService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "section/listSections";
	}
	public Users getCurrentUserObject(){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username="";
		if (principal instanceof UserDetails) {
			  username = ((UserDetails)principal).getUsername();
			} else {
			  username = principal.toString();
			}
		Users listUsers = new Users();
		for (int i = 0; i < usService.list().size(); i++) {
			if (usService.list().get(i).getUsername().equals(username)) {
				listUsers = usService.list().get(i);
				
			}
		}
		return listUsers;
		
	}
	@RequestMapping("/save")
	public String insertSections(@ModelAttribute @Valid Section objSap, BindingResult binRes, Model model)
			throws ParseException {
		if (binRes.hasErrors()) {
			model.addAttribute("listaCursos", coService.list());
			model.addAttribute("listaUsuarios", usService.list());
			return "section/section";
		} else {
			boolean flag = seService.insert(objSap);
			if (flag) {
				return "redirect:/sections/list";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/sections/new";
			}
		}

	}
//	@RequestMapping("/saveAdmission")
//	public String insertSections(@RequestParam(value = "something" , required = false) int[] something, Model model)
//			throws ParseException {
//		
//		List<Section> listSections = new ArrayList<Section>();
//		for(int i=0; i<something.length; i++) {
//		if(seService.list().get(i).getIdSection()==something[i])
//			{
//				
//				listSections.add(seService.list().get(i));
//			}
//		}
//		
//		Admission adm = new Admission();
//		adm.setSections(listSections);
//		adm.setUser(getCurrentUserObject());
//		boolean flag = adService.insert(adm);
//		if(flag) {
//			return "redirect:/admissions/list";
//		}
//		else
//		{
//			model.addAttribute("mensaje", "Ocurrió un error");
//			return "redirect:/sections/list";
//		}
//
//	}
	@RequestMapping("/list")
	public String listSections(Map<String, Object> model) {
		model.put("listaSecciones", seService.list());
		return "section/listSections";

	}
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Section se) {
		seService.listarId(se.getIdSection());
		return "section/listSections";

	}
	@RequestMapping("/update/{id}")
	public String update(@PathVariable long id, Model model, RedirectAttributes objRedir) {

		Section objSe = seService.listarId(id);
		if (objSe == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
			return "redirect:/savingaccounts/list";
		} else {
			model.addAttribute("listaCursos", coService.list());
			model.addAttribute("listaUsuarios",usService.list());
			model.addAttribute("section", objSe);
			return "section/section";
		}
	}
	public List<Users> byType(){
		List<Users> listaProfesores = new ArrayList<Users>();
		for (int i =0; i<uRService.list().size();i++) {
			if(uRService.list().get(i).getRol().equals("ROLE_PROFESOR")) {
				listaProfesores.add(uRService.list().get(i).getUser());
			}
			
		}
		return listaProfesores;
	}
	@RequestMapping("/searchName")
	public String findByName(Map<String, Object> model, @ModelAttribute Section section)throws ParseException {

	List<Section> listSections;
	section.getCourse().setCoursename(section.getCourse().getCoursename());
	listSections = seService.fetchCourseByName(section.getCourse().getCoursename());
	model.put("section", new Section());
	if (listSections.isEmpty()) {
		model.put("mensaje", "No se encontro la seccion perteneciente a la lista de secciones actual");
	}
	model.put("listaSecciones", listSections);
	return "section/listSections";
		
	}
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable long id, Model model, RedirectAttributes objRedir) {

		Section objPro = seService.listarId(id);
		if (objPro == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
			return "redirect:/sections/list";
		} else {
			seService.delSAbyId(id);
			return "redirect:/sections/list";
		}
	}

}
