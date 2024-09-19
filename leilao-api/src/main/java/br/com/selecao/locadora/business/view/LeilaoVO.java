package br.com.selecao.locadora.business.view;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LeilaoVO {

    private Integer codigo;
    private String descricao;
    private Integer vendedor;
    private LocalDateTime inicioPrevisto;

}
