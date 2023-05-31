package br.com.cotiinformatica.controllers;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.dtos.ContasCadastroDto;
import br.com.cotiinformatica.entities.Conta;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.CategoriaRepository;
import br.com.cotiinformatica.repositories.ContaRepository;

@Controller
public class ContasCadastroController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ContaRepository contaRepository;
	
	@RequestMapping(value = "/admin/contas-cadastro")
	public ModelAndView contasCadastro(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/contas-cadastro");
		
		try {
			Usuario usuario = (Usuario) request.getSession().getAttribute("auth_usuario");
			
			modelAndView.addObject("dto", new ContasCadastroDto()); 			
			modelAndView.addObject("categorias" , categoriaRepository.findAll(usuario.getIdUsuario()));
					
					
		}catch (Exception e) {
			modelAndView.addObject("mensagem", e.getMessage());
		}
		
		return modelAndView;
	}
	@RequestMapping(value ="admin/contas-cadastro-post" , method = RequestMethod.POST)
	public ModelAndView contasCadastroPost(ContasCadastroDto dto, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/contas-cadastro");
		
		try {
			Usuario usuario = (Usuario) request.getSession().getAttribute("auth_usuario");
			
			Conta conta = new Conta();
			conta.setNome(dto.getNome());
			conta.setValor(Double.parseDouble(dto.getValor()));
			conta.setData(new SimpleDateFormat("yyyy-MM-dd").parse(dto.getData()));
			conta.setObservacoes(dto.getObservacoes());
			conta.setIdCategoria(dto.getIdCategoria());
			conta.setIdUsuario(usuario.getIdUsuario());
			
			contaRepository.create(conta);
			modelAndView.addObject("mensagem" , "Conta cadastrada com sucesso.");
			modelAndView.addObject("dto" , new ContasCadastroDto());
			modelAndView.addObject("categorias" , categoriaRepository.findAll(usuario.getIdUsuario()));
			
			
		}catch (Exception e) {
			modelAndView.addObject("mensagem", e.getMessage());
			e.printStackTrace();
		}
		
		return modelAndView;
	}
}


