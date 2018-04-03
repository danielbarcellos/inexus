package br.com.claro.inexus.service;

import java.math.BigDecimal;

public interface UsuarioService<T> {

	void createUsuario(T usuario);

	void createUsuario(String nome, BigDecimal idGrupoUsuario, BigDecimal idUsuarioSenha, boolean ativo, boolean trace);
	
	T buscarUsuarioPeloId(Long id);
}