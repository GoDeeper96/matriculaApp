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
//import org.springframework.util.SerializationUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.pe.matricula.entities.Admission;
import com.pe.matricula.entities.Section;
import com.pe.matricula.entities.Users;
import com.pe.matricula.service.IAdmissionService;
import com.pe.matricula.service.IRoleService;
import com.pe.matricula.service.ISectionService;
import com.pe.matricula.service.IUserService;

@Controller
@RequestMapping("/admissions")
public class AdmissionController {
	@Autowired
	private ISectionService seService;
	@Autowired
	private IUserService usService;
	@Autowired
	private IAdmissionService adService;
	@Autowired
	private IRoleService uRService;

	@GetMapping("/new")
	public String newAdmission(Model model) {
		model.addAttribute("admission", new Admission());
		model.addAttribute("listaSecciones", seService.list());
		model.addAttribute("listaUsuarios", getAllStudents());
		model.addAttribute("admission", new Admission());
		return "admission/admission";
	}

	@GetMapping("/list")
	public String listAdmissions(Model model) {
		try {
			model.addAttribute("admission", new Admission());
			model.addAttribute("listaAdmisiones", adService.list());
		} catch (Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "admission/listAdmissions";
	}

	public List<Users> getAllStudents() {
		List<Users> listUsers = new ArrayList<Users>();
		for (int i = 0; i < uRService.list().size(); i++) {
			if (uRService.list().get(i).getRol().equals("ROLE_ESTUDIANTE")) {
				listUsers.add(uRService.list().get(i).getUser());
			}

		}
		return listUsers;

	}

	public Users getCurrentUserObject() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String lastname = "";
		if (principal instanceof UserDetails) {
			lastname = ((UserDetails) principal).getUsername();
//			System.out.println("nombre :" + lastname);
		} else {
			lastname = principal.toString();
		}
		int savedPosition = 0;
		for (int i = 0; i < usService.list().size(); i++) {
//			System.out.println("nombre :" + usService.list().get(i).getLastName());
			if (usService.list().get(i).getLastName().equals(lastname)) {
				savedPosition = i;
//					return usService.list().get(i);

			}
		}
//		System.out.println("este nombre se eligio: " +usService.list().get(savedPosition).getLastName());
		return usService.list().get(savedPosition);

	}

	@RequestMapping("/save")
	public String save(@RequestParam(value = "something", required = false) long[] something, Model model)
			throws ParseException {
		List<Section> listSections = new ArrayList<Section>();
//		System.out.println("que esta pasando:"+ something[0]);
//		System.out.println("que esta pasando:"+ something[1]);
//		System.out.println("que esta pasando:"+ something[2]);
		System.out.println(seService.list().size());
		int tamaño = seService.list().size();
		int tamañoArray = something.length;
		for (int i = 0; i < tamaño; i++) {
			for(int k=0; k < tamañoArray; k++) {
				if (seService.list().get(i).getIdSection() == something[k]) {

//					System.out.println("CURSOS: " + listSections.get(i).getCourse().getCoursename());
					listSections.add(seService.list().get(i));
//					System.out.println("CURSOS: " + listSections.get(i).getCourse().getCoursename());
				}
			}
			
			
		}
		String lastname = getCurrentUserObject().getLastName();
		Admission adm = new Admission();
		adm.setUsers(getCurrentUserObject());
		adm.setSections(listSections);
		if (adService.userHasAdmission(lastname)) 
		{
				long savedPosition = 0;
//				String converted= "";
				System.out.println("llegue perras");
				for (int i = 0; i < adService.list().size(); i++) {
					if (adService.list().get(i).getUsers().getLastName().equals(lastname)) {

						savedPosition=adService.list().get(i).getIdadmission();
//						System.out.println("Posicion: " + savedPosition + "Nombre fue?: " + adService.list().get(i).getUsers().getLastName() + "es igual a "+ lastname);
					}
				}
//				converted=Integer.toString(savedPosition);
//				System.out.println("ID: " + savedPosition);
				adService.delByUsername(savedPosition);
//				model.addAttribute("mensaje", "Se elimino los records antiguos");
//				System.out.println(adm.getSections().get(0).getCourse().getCoursename());
				boolean flag = adService.insert(adm);
				if (flag) {
					return "redirect:/sections/list";
				} else {
					model.addAttribute("mensaje", "ocurrio un error");
					return "redirect:/sections/list";
				}
			
		} else {
//			System.out.println(adm.getSections().get(0).getCourse().getCoursename());
			boolean flag = adService.insert(adm);
			if (flag) {
				return "redirect:/sections/list";
			} else {
				model.addAttribute("mensaje", "ocurrio un error");
				return "redirect:/sections/list";
			}
		}
	}
//		@RequestMapping("/save")
//		public String saveAdmissions(@RequestParam(value = "something" , required = false) int[] something, Model model)
//				throws ParseException {
//			
//			List<Section> listSections = new ArrayList<Section>();
//			for(int i=0; i<something.length; i++) {
//			if(seService.list().get(i).getIdSection()==something[i])
//				{
//					
//					listSections.add(seService.list().get(i));
//				}
//			}
//			
//			Admission adm = new Admission();
//			adm.setSections(listSections);
//			adm.setUser(getCurrentUserObject());
//			boolean flag = adService.insert(adm);
//			if(flag) {
//				return "redirect:/admissions/list";
//			}
//			else
//			{
//				model.addAttribute("mensaje", "Ocurrió un error");
//				return "redirect:/sections/list";
//			}

//			for(int i=0; i<something.length; i++) {
//				if(seService.list().get(i).getIdSection()==something[i])
//				{
//					
//					listAdmissions.add(seService.list().get(i));
//				}
//			}
//			model.addAttribute("listaUsuarios",getCurrentUserObject());
//			model.addAttribute("listaSecciones", listAdmissions);
//			if(binRes.hasErrors()) {
//				model.addAttribute("listaUsuarios",getCurrentUserObject());
//				model.addAttribute("listaSecciones", listAdmissions);
//				return "sections/list";            
//			}else
//			{
//				boolean flag = adService.insert(adm);
//				if (flag) {
//					return "redirect:/admissions/list";
//				} else {
//					model.addAttribute("mensaje", "Ocurrió un error");
//					return "redirect:/sections/list";
//				}
//			}

//		}
	@RequestMapping("/saveByAdmin")
	public String saveAdmissionsByAdmin(@ModelAttribute @Valid Admission objAdm, BindingResult binRes, Model model)
			throws ParseException {

		if (binRes.hasErrors()) {
			model.addAttribute("listaSecciones", seService.list());
			model.addAttribute("listaUsuarios", getAllStudents());
			return "admission/admission";
		} else {
			boolean flag = adService.insert(objAdm);
			if (flag) {
				return "redirect:/admissions/list";
			} else {
				model.addAttribute("mensaje", "Ocurrió un error");
				return "redirect:/admissions/new";
			}
		}
	}

	@RequestMapping("/list")
	public String listAdmissions(Map<String, Object> model) {
		model.put("listaAdmisiones", adService.list());
		return "section/listAdmissions";

	}

	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Admission adm) {
		adService.listarId(adm.getIdadmission());
		return "admission/listAdmissions";

	}

	@RequestMapping("/update/{id}")
	public String update(@PathVariable long id, Model model, RedirectAttributes objRedir) {

		Admission objAdm = adService.listarId(id);
		if (objAdm == null) {
			objRedir.addFlashAttribute("mensaje", "OcurriÃ³ un error");
			return "redirect:/savingaccounts/list";
		} else {
			model.addAttribute("listaSecciones", seService.list());
			model.addAttribute("listaUsuarios", usService.list());
			model.addAttribute("admission", objAdm);
			return "admission/admission";
		}
	}

}
