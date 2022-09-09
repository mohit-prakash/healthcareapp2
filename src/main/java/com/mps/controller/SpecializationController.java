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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mps.entity.Specialization;
import com.mps.exception.SpecializationNotFoundException;
import com.mps.service.ISpecializationService;

@Controller
@RequestMapping("/spec")
public class SpecializationController {
	@Autowired
	private ISpecializationService service;
	
	@GetMapping("/reg")
	public String registerSpec(@RequestParam(value = "message",required = false) String msg,Model model)
	{
		model.addAttribute("message", msg);
		return "SpecializationRegister";
	}
	
	@PostMapping("/save")
	public String addSpec(@ModelAttribute Specialization s, RedirectAttributes attribute)
	{
		Long id = service.addSpecialization(s);
		String message="Specialization "+id+" added successfully!!";
		attribute.addAttribute("message", message);
		return "redirect:reg";
	}
	
	@GetMapping("/all")
	public String showAllSpecialization(Model model, @RequestParam(value="message",required=false) String msg1)
	{
		List<Specialization> allSpecialization = service.getAllSpecialization();
		model.addAttribute("specList", allSpecialization);
		model.addAttribute("message", msg1);
		return "SpecializationData";
	}
	@GetMapping("/delete")
	public String removeSpecialization(@RequestParam("id") Long id,RedirectAttributes attribute)
	{
		String msg="";
		try {
			service.removeSpecialization(id);
			msg="Specialization "+id+" is removed!!";
		}
		catch(SpecializationNotFoundException e)
		{
			msg=e.getMessage();
		}
		attribute.addAttribute("message", msg);
		return "redirect:all";
	}
	@GetMapping("/edit")
	public String editSpecialization(@RequestParam("id") Long id, Model model,RedirectAttributes attribute)
	{
		try {
				Specialization specialization = service.getOneSpecialization(id);
				model.addAttribute("specialization", specialization);
				return "SpecializationEdit";
		}
		catch(SpecializationNotFoundException e)
		{
			attribute.addAttribute("message", e.getMessage());
			return "redirect:all";
		}
	}
	
	@PostMapping("/update")
	public String updateSpecialization(@ModelAttribute Specialization s, RedirectAttributes attribute)
	{
		Long id = service.updateSpecialization(s);
		String msg="Specialization "+id+" updated successfully!!";
		attribute.addAttribute("message", msg);
		return "redirect:all";
	}
	
	@GetMapping("/checkcode")
	@ResponseBody
	public String validateSpecCode(@RequestParam String code)
	{
		String msg="";
		if(service.isSpecCodeExist(code))
		{
			msg=code+" already exist";
		}
		return msg;
	}
}
