package br.com.selecao.locadora.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "TB_EMPRESA", schema = "leilao")
@SequenceGenerator(schema = "leilao", name = "seq_empresa", sequenceName = "seq_empresa", allocationSize = 1)
public class Empresa {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_empresa")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "TX_RAZAO_SOCIAL", nullable = false, length = 64)
    private String razaoSocial;

    @Column(name = "TX_CNPJ", nullable = false, length = 32)
    private String cnpj;

    @Column(name = "TX_LOGRADOURO", length = 64)
    private String logradouro;

    @Column(name = "TX_MUNICIPIO", length = 64)
    private String municipio;

    @Column(name = "TX_COMPLEMENTO", length = 64)
    private String complemento;

    @Column(name = "TX_BAIRRO", length = 64)
    private String bairro;

    @Column(name = "TX_CEP", length = 16)
    private String cep;

    @Column(name = "TX_TELEFONE", length = 32)
    private String telefone;

    @Column(name = "TX_EMAIL", nullable = false, length = 254)
    private String email;

    @Column(name = "TX_SITE", length = 254)
    private String site;

    @Column(name = "TX_USUARIO", nullable = false, length = 20)
    private String usuario;

    @JsonIgnore
    @Column(name = "TX_SENHA", length = 128)
    private String senha;

    @Column(name = "DT_CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "DT_UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;

    @Column(name = "TX_NUMERO", length = 10)
    private String numero;

}
