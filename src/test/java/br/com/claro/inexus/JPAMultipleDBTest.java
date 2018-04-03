package br.com.claro.inexus;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.claro.inexus.config.AmazonJpaConfig;
import br.com.claro.inexus.config.AppConfig;
import br.com.claro.inexus.config.HitxpJpaConfig;
import br.com.claro.inexus.service.UsuarioAmazonService;
import br.com.claro.inexus.service.UsuarioHitxpService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, HitxpJpaConfig.class, AmazonJpaConfig.class })
public class JPAMultipleDBTest {

	@Autowired
	private UsuarioAmazonService usuarioAmazonService;

	@Autowired
	private UsuarioHitxpService usuarioHitxpService;
	
	@Test
	public void quandoCriarUsuario_entaoCriaUsandoConexaoAmazon() {
		br.com.claro.inexus.persistence.amazon.Usuario usuario = new br.com.claro.inexus.persistence.amazon.Usuario();
		
		usuario.setNmUsuario("Daniel Barcellos - AMAZON");
		usuario.setDsEmail("danielbarcellos@domain.com");
		usuario.setFcAtivo("S");
		usuario.setFcTrace("N");
		usuario.setIdGrupoUsuario(BigDecimal.valueOf(19.0));
		usuario.setIdUsuarioSenha(BigDecimal.valueOf(463));
		
		this.usuarioAmazonService.createUsuario(usuario);
		
		assertNotNull(this.usuarioAmazonService.buscarUsuarioPeloId(usuario.getIdUsuario()));
	}

	@Test
	public void quandoCriarUsuario_entaoCriaUsandoJdbcTemplateAmazon() {
		br.com.claro.inexus.persistence.amazon.Usuario usuario = new br.com.claro.inexus.persistence.amazon.Usuario();
		
		usuario.setNmUsuario("Daniel Barcellos - AMAZON (JDBC TEMPLATE)");
		usuario.setDsEmail("danielbarcellos@domain.com");
		usuario.setFcAtivo("S");
		usuario.setFcTrace("N");
		usuario.setIdGrupoUsuario(BigDecimal.valueOf(19.0));
		usuario.setIdUsuarioSenha(BigDecimal.valueOf(463));
		
		this.usuarioAmazonService.createUsuario(usuario.getNmUsuario(), usuario.getIdGrupoUsuario(), usuario.getIdUsuarioSenha(), true, false);
		
	}
	
	@Test
	public void quandoCriarUsuario_entaoCriaUsandoConexaoHitxp() {
		br.com.claro.inexus.persistence.hitxp.Usuario usuario = new br.com.claro.inexus.persistence.hitxp.Usuario();
		
		usuario.setNmUsuario("Daniel Barcellos - HITXP");
		usuario.setDsEmail("danielbarcellos@domain.com");
		usuario.setFcAtivo("S");
		usuario.setFcTrace("N");
		usuario.setIdGrupoUsuario(BigDecimal.valueOf(19.0));
		usuario.setIdUsuarioSenha(BigDecimal.valueOf(463));
		
		this.usuarioHitxpService.createUsuario(usuario);
		
		assertNotNull(this.usuarioHitxpService.buscarUsuarioPeloId(usuario.getIdUsuario()));
	}

	@Test
	public void quandoCriarUsuario_entaoCriaUsandoJdbcTemplateHitxp() {
		br.com.claro.inexus.persistence.hitxp.Usuario usuario = new br.com.claro.inexus.persistence.hitxp.Usuario();
		
		usuario.setNmUsuario("Daniel Barcellos - HITXP (JDBC TEMPLATE)");
		usuario.setDsEmail("danielbarcellos@domain.com");
		usuario.setFcAtivo("S");
		usuario.setFcTrace("N");
		usuario.setIdGrupoUsuario(BigDecimal.valueOf(19.0));
		usuario.setIdUsuarioSenha(BigDecimal.valueOf(463));
		
		this.usuarioHitxpService.createUsuario(usuario.getNmUsuario(), usuario.getIdGrupoUsuario(), usuario.getIdUsuarioSenha(), true, false);
		
	}
}