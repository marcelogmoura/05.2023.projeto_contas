package br.com.cotiinformatica.entities;

import java.util.List;

import br.com.cotiinformatica.enums.TipoCategoria;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Categoria {

	private Integer idCategoria;
	private String nome;
	private Integer idUsuario;
	private TipoCategoria tipo;
	
	private Usuario usuario;
	private List<Conta> conta;

}
