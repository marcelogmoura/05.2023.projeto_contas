package br.com.cotiinformatica.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.dtos.AutenticarDto;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Controller
public class AutenticarController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@RequestMapping(value = "/")
	public ModelAndView autenticar() {
		ModelAndView modelAndView = new ModelAndView("autenticar");
		modelAndView.addObject("dto" , new AutenticarDto());
		return modelAndView;
	}
	
	@RequestMapping(value = "/autenticar-post" , method = RequestMethod.POST)
	public ModelAndView autenticarPost(AutenticarDto dto, HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView("autenticar");
		
		try {
			
			Usuario usuario = usuarioRepository.findByEmailAndSenha(dto.getEmail(), dto.getSenha());
			
			if(usuario != null) {
				
				request.getSession().setAttribute("auth_usuario", usuario);
				
				modelAndView.setViewName("redirect:/admin/dashboard");
			}else {
				
				modelAndView.addObject("mensagem" , "Acesso negado, dados inválidos");
				
			}
			
		}catch (Exception e) {
			modelAndView.addObject("mensagem" , e.getMessage());
		}
		
		modelAndView.addObject("dto" , dto);
		return modelAndView;
		
	}

}
