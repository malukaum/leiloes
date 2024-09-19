package br.com.selecao.locadora.entity;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "TB_LOTE", schema = "leilao")
@SequenceGenerator(schema = "leilao", name = "seq_lote", sequenceName = "seq_lote", allocationSize = 1)
public class Lote {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_lote")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "NU_NUMERO_LOTE")
    private Integer numeroLote;

    @Column(name = "NU_QUANTIDADE", nullable = false)
    private BigDecimal quantidade;

    @Column(name = "NU_VALOR_INICIAL")
    private BigDecimal valorInicial;

    @Column(name = "TX_DESCRICAO", nullable = false, length = 60)
    private String descricao;

    @Column(name = "DT_CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "DT_UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "NU_UNIDADE", nullable = false)
    private Unidade unidade;

    @ManyToOne
    @JoinColumn(name = "LEILAO_ID")
    private Leilao leilao;
}
