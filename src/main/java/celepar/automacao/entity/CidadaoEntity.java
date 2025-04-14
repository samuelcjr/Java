package celepar.automacao.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "TB_CIDADAO", schema = "CIDADAO")
public class CidadaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDCIDADAO", nullable = false)
    private Long idCidadao;

    @Column(name = "LOGIN", length = 32, unique = true)
    private String login;

    @Column(name = "NOME", length = 128, nullable = false)
    private String nome;

    @Column(name = "GENERO", length = 1)
    private String genero;

    @Column(name = "DTNASCIMENTO")
    private LocalDate dtNascimento;

    @Column(name = "CDMUNICIPIONATURALIDADE")
    private Integer cdMunicipioNaturalidade;

    @Column(name = "NMPAI", length = 128)
    private String nmPai;

    @Column(name = "NMMAE", length = 128)
    private String nmMae;

    @Column(name = "CPF", nullable = false, unique = true)
    private Long cpf;

    @Column(name = "DTULTIMAATUALIZACAORFB")
    private LocalDate dtUltimaAtualizacaoRfb;

    @Column(name = "RG", length = 15, unique = true)
    private String rg;

    @Column(name = "UFRG", length = 2)
    private String ufRg;

    @Column(name = "DTEMISSAORG")
    private LocalDate dtEmissaoRg;

    @Column(name = "CELULAR", unique = true)
    private Long celular;

    @Column(name = "EMAIL", length = 128, unique = true)
    private String email;

    @Column(name = "TITULOELEITOR", unique = true)
    private Long tituloEleitor;

    @Column(name = "CNH", unique = true)
    private Long cnh;

    @Column(name = "DTVALIDADECNH")
    private LocalDate dtValidadeCnh;

    @Column(name = "PASSAPORTE", length = 10, unique = true)
    private String passaporte;

    @Column(name = "CDPAISPASSAPORTE")
    private Integer cdPaisPassaporte;

    @Column(name = "DTVALIDADEPASSAPORTE")
    private LocalDate dtValidadePassaporte;

    @Column(name = "IDBIOMETRICO", unique = true)
    private Long idBiometrico;

    @Column(name = "NMLOGRADOUROCONTATO", length = 128)
    private String nmLogradouroContato;

    @Column(name = "NRLOGRADOUROCONTATO", length = 10)
    private String nrLogradouroContato;

    @Column(name = "NMBAIRROCONTATO", length = 40)
    private String nmBairroContato;

    @Column(name = "COMPLEMENTOLOGRADOUROCONTATO", length = 64)
    private String complementoLogradouroContato;

    @Column(name = "CEPLOGRADOUROCONTATO", nullable = false)
    private Integer cepLogradouroContato;

    @Column(name = "CDMUNICIPIOLOGRADOUROCONTATO", nullable = false)
    private Integer cdMunicipioLogradouroContato;

    @Column(name = "TELEFONECONTATO")
    private Long telefoneContato;

    @Column(name = "SITUACAO", nullable = false)
    private Integer situacao;

    @Column(name = "NIVELCONFIABILIDADECADASTRO", nullable = false)
    private Integer nivelConfiabilidadeCadastro;

    @Column(name = "NMCIDADECONTATO", length = 128, nullable = false)
    private String nmCidadeContato;

    @Column(name = "DTREGISTRO")
    private LocalDateTime dtRegistro;

    @Column(name = "UFCONTATO", length = 2, nullable = false)
    private String ufContato;

    @Column(name = "DTATUALIZACAO")
    private LocalDateTime dtAtualizacao;

    @Column(name = "DTATUALIZACAOSENHA")
    private LocalDateTime dtAtualizacaoSenha;

    @Column(name = "NUMPROTOCOLO")
    private Integer numProtocolo;

    @Column(name = "DTRECADASTRAMENTO")
    private LocalDateTime dtRecadastramento;

}
