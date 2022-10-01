package com.mps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mps.entity.Doctor;
import com.mps.exception.DoctorNotFoundException;
import com.mps.service.IDoctorService;
import com.mps.service.ISpecializationService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
	
	@Autowired
	private IDoctorService service;
	
	@Autowired
	private ISpecializationService specializationService;
	
	private void createDynamicUi(Model model)
	{
		model.addAttribute("specializations",specializationService.getSpecIdAndName());
	}
	
	@GetMapping("/register")
	public String docRegister(@RequestParam(value = "message",required = false) String message,Model model) 
	{
		model.addAttribute("message",message);
		createDynamicUi(model);
		return "DoctorRegister";
	}
	
	@PostMapping("/save")
	public String saveDoctor(@ModelAttribute Doctor doctor,RedirectAttributes attribute)
	{
		Long id = service.addDoctor(doctor);
		attribute.addAttribute("message", "Doctor "+id+" registered successfully!!");
		return "redirect:register";
	}
	
	@GetMapping("/all")
	public String showDoctors(Model model,@RequestParam(value="message",required = false) String msg) 
	{
		List<Doctor> allDoctor = service.getAllDoctor();
		model.addAttribute("doctors", allDoctor);
		model.addAttribute("message", msg);
		return "DoctorData";
	}
	
	@GetMapping("/delete")
	public String deleteDoctor(@RequestParam("id")Long id, RedirectAttributes attribute)
	{
		try {
			service.removeDoctor(id);
			attribute.addAttribute("message","Doctor "+id+" deleted successfully!!");
			return "redirect:all";
		} catch (DoctorNotFoundException dnfe) {
			attribute.addAttribute("message","Doctor Not Found");
			return "redirect:all";
		}
	}
	
	@GetMapping("/edit")
	public String editDoctor(@RequestParam("id") Long id,Model model,RedirectAttributes attribute)
	{
		try {
			Doctor doctor = service.getOneDoctor(id);
			model.addAttribute("doctor",doctor);
			createDynamicUi(model);
			return "DoctorEdit";
		} catch (DoctorNotFoundException dnfe) {
			attribute.addAttribute("message",dnfe.getMessage());
			return "redirect:all";
		}
	}
	
	@PostMapping("/update")
	public String updateDoctor(@ModelAttribute Doctor doctor, RedirectAttributes attribute)
	{
		Long id = service.addDoctor(doctor);
		attribute.addAttribute("message","Doctor "+id+" updated successfully!!");
		return "redirect:all";
		
	}
	
	
}
