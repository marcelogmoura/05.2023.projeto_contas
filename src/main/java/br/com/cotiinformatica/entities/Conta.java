package br.com.cotiinformatica.entities;



import java.util.Date;

import lombok.Data;

@Data
public class Conta {
	
	private Integer idConta;
	private String nome;
	private Double valor;
	private Date data;
	private String observacoes;
	private Integer idCategoria;
	private Integer idUsuario;
	
	private Usuario usuario;
	private Categoria categoria;
	

}
