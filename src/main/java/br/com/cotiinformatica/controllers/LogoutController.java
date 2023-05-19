package br.com.cotiinformatica.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutController {
	
	@RequestMapping(value = "/logout")
	public ModelAndView Logout(HttpServletRequest request) {		
		request.getSession().removeAttribute("auth_usuario");
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/");
		return modelAndView;
	}

}
