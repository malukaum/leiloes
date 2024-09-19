package br.com.selecao.locadora.business.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeilaoListaVO {

    private Long id;
    private String razaoSocial;
    private LocalDateTime inicioPrevisto;
    private BigDecimal total;

}
