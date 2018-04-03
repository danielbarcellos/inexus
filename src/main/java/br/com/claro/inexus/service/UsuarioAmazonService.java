package br.com.claro.inexus.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.claro.inexus.persistence.amazon.Usuario;
import br.com.claro.inexus.persistence.amazon.repository.UsuarioAmazonRepository;

@Service
@Transactional("amazonTransactionManager")
public class UsuarioAmazonService implements UsuarioService<Usuario> {

	@Autowired
	UsuarioAmazonRepository usuarioAmazonRepository;
	
	@Autowired
	JdbcTemplate amanzonJdbcTemplate;
	
	@Override
	public void createUsuario(Usuario usuario) {
		this.usuarioAmazonRepository.save(usuario);
	}

	@Override
	public Usuario buscarUsuarioPeloId(Long id) {
		Optional<Usuario> optional = this.usuarioAmazonRepository.findById(id);
		return optional.get();
	}

	@Override
	public void createUsuario(String nome, BigDecimal idGrupoUsuario, BigDecimal idUsuarioSenha, boolean ativo,
			boolean trace) {
		final String INSERT = "INSERT INTO NETHITZ.USUARIO(ID_USUARIO, NM_USUARIO, ID_GRUPO_USUARIO, ID_USUARIO_SENHA, FC_ATIVO, FC_TRACE) VALUES (NETHITZ.SQ_USUARIO.NEXTVAL, ?, ?, ?, ?, ?)";
		this.amanzonJdbcTemplate.update(INSERT, nome, idGrupoUsuario, idUsuarioSenha, ativo ? "S" : "N", trace ? "S" : "N");
	}

}