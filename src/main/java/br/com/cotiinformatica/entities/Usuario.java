package br.com.cotiinformatica.entities;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Usuario {
	
	private Integer idUsuario;
	private String nome;
	private String email;
	private String senha;
	
	private List<Categoria> categorias;
	private List<Conta> contas;

}
