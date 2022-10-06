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

import com.mps.entity.Patient;
import com.mps.exception.PatientNotFoundException;
import com.mps.service.IPatientService;

@Controller
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	private IPatientService service;

	@GetMapping("/register")
	public String registerPatient(@RequestParam(name = "message",required = false) String msg,Model model)
	{
		model.addAttribute("message",msg);
		return "PatientRegister";
	}
	
	@PostMapping("/save")
	public String savePatient(@ModelAttribute Patient patient, RedirectAttributes attributes)
	{
		Long id = service.addPatient(patient);
		String message="Patient "+id+" created successfully!!";
		attributes.addAttribute("message",message);
		return "redirect:register";
	}
	
	@GetMapping("/all")
	public String allPatient(Model model,@RequestParam(name="message",required = false) String msg)
	{
		List<Patient> allPatient = service.getAllPatient();
		model.addAttribute("list",allPatient);
		model.addAttribute("message",msg);
		return "PatientData";
	}
	
	@GetMapping("/edit")
	public String editPatient(@RequestParam(name="id") Long id,Model model,RedirectAttributes attributes)
	{
		String page=null;
		try {
				Patient patient = service.getOnePatient(id);
				model.addAttribute("Patient",patient);
				page="PatientEdit";
			}
		catch(PatientNotFoundException pnfe)
		{
			attributes.addAttribute("message",pnfe.getMessage());
			page="redirect:all";
		}
		return page;
	}
	
	@PostMapping("/update")
	public String updatePatient(@ModelAttribute Patient patient,RedirectAttributes attributes)
	{
		Long id = service.addPatient(patient);
		String message="Patient "+id+" updated successfully!!";
		attributes.addAttribute("message",message);
		return "redirect:all";
	}
	
	@GetMapping("/delete")
	public String deletePatient(@RequestParam("id") Long id,RedirectAttributes attribute)
	{
		try {
				service.removePatient(id);
				attribute.addAttribute("message","Patient "+id+" removed successfully!!");
		}
		catch(PatientNotFoundException pnfe)
		{
			attribute.addAttribute("message",pnfe.getMessage());
		}
		return "redirect:all";
	}
	
}
