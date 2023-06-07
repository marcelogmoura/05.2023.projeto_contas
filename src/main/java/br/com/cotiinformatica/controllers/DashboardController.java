package br.com.cotiinformatica.controllers;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.dtos.DashboardDto;
import br.com.cotiinformatica.entities.Conta;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.enums.TipoCategoria;
import br.com.cotiinformatica.repositories.ContaRepository;

@Controller
public class DashboardController {
	
	@Autowired
	private ContaRepository contaRepository;
	
	@RequestMapping(value = "/admin/dashboard")
	public ModelAndView dashboard(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("admin/dashboard");
		
		try {
			LocalDate primeiroDiaDoMes = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
			LocalDate ultimoDiaDoMes = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
			
			Usuario usuario = (Usuario) request.getSession().getAttribute("auth_usuario");
			
			Date dataInicio = Date.from(primeiroDiaDoMes.atStartOfDay(ZoneId.systemDefault()).toInstant());
			Date dataFim = Date.from(ultimoDiaDoMes.atStartOfDay(ZoneId.systemDefault()).toInstant());
			
			List<Conta> contas = contaRepository.findAll(dataInicio , dataFim, usuario.getIdUsuario());
			
			Double totalReceitas = 0.0;
			Double totalDespesas = 0.0;
			
			for(Conta item : contas) {
				if(item.getCategoria().getTipo() == TipoCategoria.RECEITAS)
					totalReceitas += item.getValor();
				else if(item.getCategoria().getTipo() == TipoCategoria.DESPESAS)
					totalDespesas += item.getValor();
			}
			
			List<DashboardDto> somatorioContas = new ArrayList<DashboardDto>();
			somatorioContas.add(new DashboardDto("Total de Receitas" , totalReceitas));
			somatorioContas.add(new DashboardDto("Total de Despesas" , totalDespesas));
			modelAndView.addObject("somatorioContas" , somatorioContas);
			
			modelAndView.addObject("dataInicio" , dataInicio);
			modelAndView.addObject("dataFim" , dataFim);
			
			
		}catch (Exception e) {
			modelAndView.addObject("mensagem" , "Falha ao gerar dashboard:" + e.getMessage());
		}
		
		return modelAndView;
	}

	
}
