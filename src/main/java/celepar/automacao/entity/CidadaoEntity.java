package celepar.automacao.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    // Getters e Setters
    public Long getIdCidadao() {
        return idCidadao;
    }

    public void setIdCidadao(Long idCidadao) {
        this.idCidadao = idCidadao;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public LocalDate getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(LocalDate dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public Integer getCdMunicipioNaturalidade() {
        return cdMunicipioNaturalidade;
    }

    public void setCdMunicipioNaturalidade(Integer cdMunicipioNaturalidade) {
        this.cdMunicipioNaturalidade = cdMunicipioNaturalidade;
    }

    public String getNmPai() {
        return nmPai;
    }

    public void setNmPai(String nmPai) {
        this.nmPai = nmPai;
    }

    public String getNmMae() {
        return nmMae;
    }

    public void setNmMae(String nmMae) {
        this.nmMae = nmMae;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDtUltimaAtualizacaoRfb() {
        return dtUltimaAtualizacaoRfb;
    }

    public void setDtUltimaAtualizacaoRfb(LocalDate dtUltimaAtualizacaoRfb) {
        this.dtUltimaAtualizacaoRfb = dtUltimaAtualizacaoRfb;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getUfRg() {
        return ufRg;
    }

    public void setUfRg(String ufRg) {
        this.ufRg = ufRg;
    }

    public LocalDate getDtEmissaoRg() {
        return dtEmissaoRg;
    }

    public void setDtEmissaoRg(LocalDate dtEmissaoRg) {
        this.dtEmissaoRg = dtEmissaoRg;
    }

    public Long getCelular() {
        return celular;
    }

    public void setCelular(Long celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTituloEleitor() {
        return tituloEleitor;
    }

    public void setTituloEleitor(Long tituloEleitor) {
        this.tituloEleitor = tituloEleitor;
    }

    public Long getCnh() {
        return cnh;
    }

    public void setCnh(Long cnh) {
        this.cnh = cnh;
    }

    public LocalDate getDtValidadeCnh() {
        return dtValidadeCnh;
    }

    public void setDtValidadeCnh(LocalDate dtValidadeCnh) {
        this.dtValidadeCnh = dtValidadeCnh;
    }

    public String getPassaporte() {
        return passaporte;
    }

    public void setPassaporte(String passaporte) {
        this.passaporte = passaporte;
    }

    public Integer getCdPaisPassaporte() {
        return cdPaisPassaporte;
    }

    public void setCdPaisPassaporte(Integer cdPaisPassaporte) {
        this.cdPaisPassaporte = cdPaisPassaporte;
    }

    public LocalDate getDtValidadePassaporte() {
        return dtValidadePassaporte;
    }

    public void setDtValidadePassaporte(LocalDate dtValidadePassaporte) {
        this.dtValidadePassaporte = dtValidadePassaporte;
    }

    public Long getIdBiometrico() {
        return idBiometrico;
    }

    public void setIdBiometrico(Long idBiometrico) {
        this.idBiometrico = idBiometrico;
    }

    public String getNmLogradouroContato() {
        return nmLogradouroContato;
    }

    public void setNmLogradouroContato(String nmLogradouroContato) {
        this.nmLogradouroContato = nmLogradouroContato;
    }

    public String getNrLogradouroContato() {
        return nrLogradouroContato;
    }

    public void setNrLogradouroContato(String nrLogradouroContato) {
        this.nrLogradouroContato = nrLogradouroContato;
    }

    public String getNmBairroContato() {
        return nmBairroContato;
    }

    public void setNmBairroContato(String nmBairroContato) {
        this.nmBairroContato = nmBairroContato;
    }

    public String getComplementoLogradouroContato() {
        return complementoLogradouroContato;
    }

    public void setComplementoLogradouroContato(String complementoLogradouroContato) {
        this.complementoLogradouroContato = complementoLogradouroContato;
    }

    public Integer getCepLogradouroContato() {
        return cepLogradouroContato;
    }

    public void setCepLogradouroContato(Integer cepLogradouroContato) {
        this.cepLogradouroContato = cepLogradouroContato;
    }

    public Integer getCdMunicipioLogradouroContato() {
        return cdMunicipioLogradouroContato;
    }

    public void setCdMunicipioLogradouroContato(Integer cdMunicipioLogradouroContato) {
        this.cdMunicipioLogradouroContato = cdMunicipioLogradouroContato;
    }

    public Long getTelefoneContato() {
        return telefoneContato;
    }

    public void setTelefoneContato(Long telefoneContato) {
        this.telefoneContato = telefoneContato;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

    public Integer getNivelConfiabilidadeCadastro() {
        return nivelConfiabilidadeCadastro;
    }

    public void setNivelConfiabilidadeCadastro(Integer nivelConfiabilidadeCadastro) {
        this.nivelConfiabilidadeCadastro = nivelConfiabilidadeCadastro;
    }

    public String getNmCidadeContato() {
        return nmCidadeContato;
    }

    public void setNmCidadeContato(String nmCidadeContato) {
        this.nmCidadeContato = nmCidadeContato;
    }

    public LocalDateTime getDtRegistro() {
        return dtRegistro;
    }

    public void setDtRegistro(LocalDateTime dtRegistro) {
        this.dtRegistro = dtRegistro;
    }

    public String getUfContato() {
        return ufContato;
    }

    public void setUfContato(String ufContato) {
        this.ufContato = ufContato;
    }

    public LocalDateTime getDtAtualizacao() {
        return dtAtualizacao;
    }

    public void setDtAtualizacao(LocalDateTime dtAtualizacao) {
        this.dtAtualizacao = dtAtualizacao;
    }

    public LocalDateTime getDtAtualizacaoSenha() {
        return dtAtualizacaoSenha;
    }

    public void setDtAtualizacaoSenha(LocalDateTime dtAtualizacaoSenha) {
        this.dtAtualizacaoSenha = dtAtualizacaoSenha;
    }

    public Integer getNumProtocolo() {
        return numProtocolo;
    }

    public void setNumProtocolo(Integer numProtocolo) {
        this.numProtocolo = numProtocolo;
    }

    public LocalDateTime getDtRecadastramento() {
        return dtRecadastramento;
    }

    public void setDtRecadastramento(LocalDateTime dtRecadastramento) {
        this.dtRecadastramento = dtRecadastramento;
    }
}
