package br.com.cotiinformatica.repositories;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import br.com.cotiinformatica.entities.Usuario;

public class UsuarioRepository {
	
	private JdbcTemplate jdbcTemplate;
	
	public UsuarioRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public void create(Usuario usuario) throws Exception{
		
		String query = "insert into usuario(nome, email, senha)values(?, ?, md5(?))";
		Object[] params = { usuario.getNome(), usuario.getEmail(), usuario.getSenha() };
		
		jdbcTemplate.update(query, params);
	}

}
