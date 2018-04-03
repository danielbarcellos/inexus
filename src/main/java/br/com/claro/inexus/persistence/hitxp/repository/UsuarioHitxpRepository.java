package br.com.claro.inexus.persistence.hitxp.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.claro.inexus.persistence.hitxp.Usuario;

@Repository
@Qualifier("usuarioHitxpRepository")
public interface UsuarioHitxpRepository extends JpaRepository<Usuario, Long>{

}
