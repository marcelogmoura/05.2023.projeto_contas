package br.com.cotiinformatica.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.dtos.ContasConsultaDto;
import br.com.cotiinformatica.entities.Conta;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.repositories.ContaRepository;

@Controller
public class ContasConsultaController {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@RequestMapping(value = "/admin/contas-consulta")
	public ModelAndView contasConsulta() {
		ModelAndView modelAndView = new ModelAndView("admin/contas-consulta");
		modelAndView.addObject("dto" , new ContasConsultaDto());
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/contas-consulta-post" , method = RequestMethod.POST)
	public ModelAndView contasConsultaPost(ContasConsultaDto dto, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/contas-consulta");
		
		try {
			Usuario usuario = (Usuario) request.getSession().getAttribute("auth_usuario");
			
			Date dataInicio = new SimpleDateFormat("yyyy-MM-dd").parse(dto.getDataInicio());
			Date dataFim = new SimpleDateFormat("yyyy-MM-dd").parse(dto.getDataFim());
			
			List<Conta> contas = contaRepository.findAll(dataInicio, dataFim, usuario.getIdUsuario());
			
			modelAndView.addObject("contas" , contas);
			
		}catch (Exception e) {
			modelAndView.addObject("mensagem" , e.getMessage());
		}
		
		modelAndView.addObject("dto" , dto);
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/excluir-conta")
	public ModelAndView excluirConta(Integer idConta , String dataInicio, String dataFim,  HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/contas-consulta");
		
		try {
			Usuario usuario = (Usuario) request.getSession().getAttribute("auth_usuario");
			
			Conta conta = contaRepository.findById(idConta, usuario.getIdUsuario());
			contaRepository.delete(conta);
			modelAndView.addObject("mensagem" , "Conta " + conta.getNome() + " excluída com sucesso.");
			
			Date dtInicio = new SimpleDateFormat("yyyy-MM-dd").parse(dataInicio);
			Date dtFim = new SimpleDateFormat("yyyy-MM-dd").parse(dataFim);
			List<Conta> contas = contaRepository.findAll(dtInicio, dtFim, usuario.getIdUsuario());
			modelAndView.addObject(contas);
			
		}catch (Exception e) {
			modelAndView.addObject("mensagem" , e.getMessage());
		}
		
		modelAndView.addObject("dto" , new ContasConsultaDto());
		return modelAndView;
	}
}




