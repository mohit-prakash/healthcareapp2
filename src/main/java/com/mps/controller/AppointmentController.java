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

import com.mps.entity.Appointment;
import com.mps.exception.AppointmentNotFoundException;
import com.mps.service.IAppointmentService;
import com.mps.service.IDoctorService;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {
	
	@Autowired
	private IAppointmentService service;
	@Autowired
	private IDoctorService dService;
	
	private void createDynamicUI(Model model)
	{
		model.addAttribute("doctors", dService.getDoctorIdAndNames());
	}
	@GetMapping("/register")
	public String registerAppointment(@RequestParam(name="message", required = false) String msg,Model model)
	{
		createDynamicUI(model);
		model.addAttribute("message", msg);
		return "AppointmentRegister";
	}
	@PostMapping("/save")
	public String saveAppointment(@ModelAttribute Appointment appointment, RedirectAttributes attributes)
	{
		Long id = service.addAppointment(appointment);
		attributes.addAttribute("message","Appointment "+id+" added successfully");
		return "redirect:register";
	}
	@GetMapping("/all")
	public String showAppointments(Model model,@RequestParam(value="message",required = false) String msg)
	{
		List<Appointment> list = service.getAllAppointment();
		model.addAttribute("list",list);
		model.addAttribute("message",msg);
		return "AppointmentData";
	}
	@GetMapping("/edit")
	public String editAppointment(@RequestParam("id") Long id,Model model,RedirectAttributes att)
	{
		try {
		createDynamicUI(model);
		Appointment appointment = service.getOneAppointment(id);
		model.addAttribute("appointment",appointment);
		return "AppointmentEdit";
		}
		catch(AppointmentNotFoundException anfe)
		{
			att.addAttribute("message", anfe.getMessage());
			return "redirect:all";
		}
		
	}
	@PostMapping("/update")
	public String updateAppointment(@ModelAttribute Appointment appointment,RedirectAttributes attributes)
	{
		Long id = service.updateAppointment(appointment);
		attributes.addAttribute("message","Appointment "+id+" updated successfully!!");
		return "redirect:all";
	}
	@GetMapping("/delete")
	public String deleteAppointment(@RequestParam("id") Long id,RedirectAttributes attribute)
	{
		try {
		service.removeAppointment(id);
		attribute.addAttribute("message", "Appointment "+id+" removed successfully!!");
		return "redirect:all";
		}
		catch(AppointmentNotFoundException anfe)
		{
			attribute.addAttribute("message", anfe.getMessage());
			return "redirect:all";
		}
	}
}
