package br.com.claro.inexus.persistence.amazon;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the USUARIO database table.
 * 
 */
@Entity
@Table(schema = "nethitz")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
@SequenceGenerator(name = "SQ_USUARIO", initialValue = 1, allocationSize = 1, sequenceName = "SQ_USUARIO", schema = "nethitz")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_USUARIO")
	@Column(name="ID_USUARIO")
	private Long idUsuario;

	@Column(name="CD_CHAMADO")
	private String cdChamado;

	@Column(name="CD_PA_ACESSO")
	private String cdPaAcesso;

	@Temporal(TemporalType.DATE)
	@Column(name="DH_DELECAO")
	private Date dhDelecao;

	@Temporal(TemporalType.DATE)
	@Column(name="DH_INCLUSAO")
	private Date dhInclusao;

	@Temporal(TemporalType.DATE)
	@Column(name="DH_ULTIMO_ACESSO")
	private Date dhUltimoAcesso;

	@Column(name="DS_EMAIL")
	private String dsEmail;

	@Column(name="FC_ATIVO")
	private String fcAtivo;

	@Column(name="FC_TRACE")
	private String fcTrace;

	@Column(name="ID_GRUPO_USUARIO")
	private java.math.BigDecimal idGrupoUsuario;

	@Column(name="ID_USUARIO_SENHA")
	private java.math.BigDecimal idUsuarioSenha;

	@Column(name="NM_USUARIO")
	private String nmUsuario;

	public Usuario() {
	}

	public Long getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCdChamado() {
		return this.cdChamado;
	}

	public void setCdChamado(String cdChamado) {
		this.cdChamado = cdChamado;
	}

	public String getCdPaAcesso() {
		return this.cdPaAcesso;
	}

	public void setCdPaAcesso(String cdPaAcesso) {
		this.cdPaAcesso = cdPaAcesso;
	}

	public Date getDhDelecao() {
		return this.dhDelecao;
	}

	public void setDhDelecao(Date dhDelecao) {
		this.dhDelecao = dhDelecao;
	}

	public Date getDhInclusao() {
		return this.dhInclusao;
	}

	public void setDhInclusao(Date dhInclusao) {
		this.dhInclusao = dhInclusao;
	}

	public Date getDhUltimoAcesso() {
		return this.dhUltimoAcesso;
	}

	public void setDhUltimoAcesso(Date dhUltimoAcesso) {
		this.dhUltimoAcesso = dhUltimoAcesso;
	}

	public String getDsEmail() {
		return this.dsEmail;
	}

	public void setDsEmail(String dsEmail) {
		this.dsEmail = dsEmail;
	}

	public String getFcAtivo() {
		return this.fcAtivo;
	}

	public void setFcAtivo(String fcAtivo) {
		this.fcAtivo = fcAtivo;
	}

	public String getFcTrace() {
		return this.fcTrace;
	}

	public void setFcTrace(String fcTrace) {
		this.fcTrace = fcTrace;
	}

	public java.math.BigDecimal getIdGrupoUsuario() {
		return this.idGrupoUsuario;
	}

	public void setIdGrupoUsuario(java.math.BigDecimal idGrupoUsuario) {
		this.idGrupoUsuario = idGrupoUsuario;
	}

	public java.math.BigDecimal getIdUsuarioSenha() {
		return this.idUsuarioSenha;
	}

	public void setIdUsuarioSenha(java.math.BigDecimal idUsuarioSenha) {
		this.idUsuarioSenha = idUsuarioSenha;
	}

	public String getNmUsuario() {
		return this.nmUsuario;
	}

	public void setNmUsuario(String nmUsuario) {
		this.nmUsuario = nmUsuario;
	}

}