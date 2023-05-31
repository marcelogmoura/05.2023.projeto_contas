package br.com.cotiinformatica.entities;

import java.util.List;

import br.com.cotiinformatica.enums.TipoCategoria;
import lombok.Data;

@Data
public class Categoria {

	private Integer idCategoria;
	private String nome;
	private Integer idUsuario;
	private TipoCategoria tipo;
	
	private Usuario usuario;
	private List<Conta> conta;

}
