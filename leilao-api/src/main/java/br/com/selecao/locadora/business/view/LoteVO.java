package br.com.selecao.locadora.business.view;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class LoteVO {

    private Integer numeroLote;
    private BigDecimal quantidade;
    private BigDecimal valorInicial;
    private Long idUnidade;
    private Long idLeilao;
    private String descricao;

}
