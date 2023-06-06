package br.com.cotiinformatica.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.github.javafaker.Faker;

import br.com.cotiinformatica.dtos.EmailMessageDto;
import br.com.cotiinformatica.dtos.RecuperarSenhaDto;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.helpers.EmailMessageHelper;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Controller
public class RecuperarSenhaController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@RequestMapping(value = "/recuperar-senha")
	public ModelAndView recuperarSenha() {
		ModelAndView modelAndView = new ModelAndView("recuperar-senha");
		modelAndView.addObject("dto" , new RecuperarSenhaDto());		
		
		return modelAndView;
	}

	@RequestMapping(value = "/recuperar-senha-post" , method = RequestMethod.POST)
	public ModelAndView recuperarSenhaPost(RecuperarSenhaDto dto) {
		ModelAndView modelAndView = new ModelAndView("recuperar-senha");
		
		try {
			
			Usuario usuario =  usuarioRepository.findByEmail(dto.getEmail());
			
			if(usuario != null) {
				Faker faker = new Faker();
				usuario.setSenha(faker.internet().password(5, 10, true, true, true));
				
				EmailMessageDto messageDto = new EmailMessageDto();
				messageDto.setEmailDestinatario(usuario.getEmail());
				messageDto.setAssunto("Recuperação de senha - Projeto Contas");
				messageDto.setConteudoMensagem("Olá " + usuario.getNome() +
						"Uma nova senha de acesso foi gerada para você." +
						"Acesso o sistema com a senha:" + usuario.getSenha() + "\n\n" +
						"Att\nEquipe Suporte - Projeto Contas");
				
				EmailMessageHelper.send(messageDto);
				
				usuarioRepository.update(usuario);

				modelAndView.addObject("mensagem" , "E-mail enviado para: " + dto.getEmail() );
				
				dto = new RecuperarSenhaDto();
				
			}else {
				modelAndView.addObject("mensagem" , "Usuário não encontrado. Verifique o email.");
			}
			
		}catch (Exception e) {
			
			modelAndView.addObject("mensagem" , "falha ao recuperar senha: " + e.getMessage());
		}
		
		modelAndView.addObject("dto", dto);
		return modelAndView;
		
	}
}



