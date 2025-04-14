package celepar.automacao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "TB_TERMOACEITESITUACAO", schema = "CIDADAO")
public class TermoAceiteEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDTERMOACEITESITUACAO", nullable = false)
    private Long idTermoAceiteSituacao;

    @Column(name = "IDCIDADAO", nullable = false)
    private Long idCidadao;

    @Column(name = "DTTERMOACEITESITUACAO", nullable = false, columnDefinition = "TIMESTAMP DEFAULT SYSTIMESTAMP")
    private LocalDateTime dtTermoAceiteSituacao;

    @Column(name = "FLGTERMOACEITE", nullable = false)
    private Integer flgTermoAceite;

}
