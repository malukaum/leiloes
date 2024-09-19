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
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "TB_LEILAO", schema = "leilao")
@SequenceGenerator(schema = "leilao", name = "seq_leilao", sequenceName = "seq_leilao", allocationSize = 1)
public class Leilao {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_leilao")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "NU_CODIGO")
    private Integer codigo;

    @Column(name = "TX_DESCRICAO", nullable = false, length = 60)
    private String descricao;

    @Column(name = "DT_INICIO_PREVISTO", nullable = false)
    private LocalDateTime inicioPrevisto;

    @Column(name = "DT_CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "DT_UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "VENDEDOR_ID")
    private Empresa vendedor;

}
