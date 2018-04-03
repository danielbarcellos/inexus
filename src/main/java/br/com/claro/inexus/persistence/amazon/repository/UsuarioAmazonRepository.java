package br.com.claro.inexus.persistence.amazon.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.claro.inexus.persistence.amazon.Usuario;

@Repository
@Qualifier("usuarioAmazonRepository")
public interface UsuarioAmazonRepository extends JpaRepository<Usuario, Long>{

}
