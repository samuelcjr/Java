package celepar.automacao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import celepar.automacao.entity.CidadaoEntity;

@Repository
public interface UsuarioRepository extends JpaRepository<CidadaoEntity, Long> {

	// @Modifying
	// @Transactional
	@Query(value = "SELECT * FROM CIDADAO.TB_CIDADAO WHERE CPF = :cpf", nativeQuery = true)
	List<CidadaoEntity> buscarUsuarioRepository(String cpf);

}
