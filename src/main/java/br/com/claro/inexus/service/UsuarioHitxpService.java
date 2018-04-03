package br.com.claro.inexus.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.claro.inexus.persistence.hitxp.Usuario;
import br.com.claro.inexus.persistence.hitxp.repository.UsuarioHitxpRepository;

@Service
@Transactional("hitxpTransactionManager")
public class UsuarioHitxpService implements UsuarioService<Usuario> {

	@Autowired
	UsuarioHitxpRepository usuarioHitxpRepository;
	
	@Autowired
	JdbcTemplate hitxpJdbcTemplate;
	
	@Override
	public void createUsuario(Usuario usuario) {
		this.usuarioHitxpRepository.save(usuario);
	}

	@Override
	public Usuario buscarUsuarioPeloId(Long id) {
		Optional<Usuario> optional = this.usuarioHitxpRepository.findById(id);
		return optional.get();
	}

	@Override
	public void createUsuario(String nome, BigDecimal idGrupoUsuario, BigDecimal idUsuarioSenha, boolean ativo,
			boolean trace) {
		final String INSERT = "INSERT INTO NETHITZ.USUARIO(ID_USUARIO, NM_USUARIO, ID_GRUPO_USUARIO, ID_USUARIO_SENHA, FC_ATIVO, FC_TRACE) VALUES (NETHITZ.SQ_USUARIO.NEXTVAL, ?, ?, ?, ?, ?)";
		this.hitxpJdbcTemplate.update(INSERT, nome, idGrupoUsuario, idUsuarioSenha, ativo ? "S" : "N", trace ? "S" : "N");
	}
}