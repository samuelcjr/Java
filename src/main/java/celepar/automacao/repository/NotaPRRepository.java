package celepar.automacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import celepar.automacao.entity.CidadaoEntity;
import jakarta.transaction.Transactional;

@Repository
public interface NotaPRRepository extends JpaRepository<CidadaoEntity, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE CIDADAO.TB_CIDADAO SET EMAIL = :emailNovo WHERE EMAIL = :email", nativeQuery = true)
    int atualizarEmail(String email, String emailNovo);
}
