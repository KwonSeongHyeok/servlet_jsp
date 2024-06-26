package com.khmall.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.khmall.dto.Members;
import com.khmall.service.MembersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

	@Autowired
	private MembersService membersService;
	
	@GetMapping("/login")
	public String showLoginForm(Model model) {
		model.addAttribute("m", new Members());
		return "login";
	}
	
	@PostMapping("/login")
	public String getLogin(Model model,
					@RequestParam ("email") String email,
					@RequestParam ("password") String password,
					HttpSession session){
		Members members = membersService.getLogin(email, password);
		if(members != null) {
			session.setAttribute("loginSession", members);
			return "redirect:/";
		} else {
			model.addAttribute("error", "일치하지 않습니다.");
			model.addAttribute("m", new Members());
			return "login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
}
