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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "TB_UNIDADE", schema = "leilao")
@SequenceGenerator(schema = "leilao", name = "seq_unidade", sequenceName = "seq_unidade", allocationSize = 1)
public class Unidade implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_unidade")
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "TX_NOME", nullable = false, length = 128)
    private String nome;

    @Column(name = "dt_created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "DT_UPDATED_AT", nullable = false)
    private LocalDateTime updatedAt;

}

