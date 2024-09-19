package br.com.selecao.locadora.entity;

import br.com.selecao.locadora.entity.pk.CompradorPK;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Entity
@Table(name = "TB_COMPRADOR", schema = "leilao")
public class Comprador {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private CompradorPK id;

}
