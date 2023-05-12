package br.com.cotiinformatica.entities;

import java.sql.Date;

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
public class Conta {
	
	private Integer idConta;
	private String nome;
	private Double valor;
	private Date date;
	private String observacoes;
	
	private Usuario usuario;
	private Categoria categoria;
	

}
