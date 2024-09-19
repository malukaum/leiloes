package br.com.selecao.locadora.business.view;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static java.util.Objects.nonNull;

@Getter
@Setter
public class FiltroLeiloesVO {

    private String razaoSocial;
    private LocalDate inicioPrevisto;

    public LocalDateTime getInicioPrevistaInicio() {
        if (nonNull(inicioPrevisto)) {
            return inicioPrevisto.atStartOfDay();
        }
        else return null;
    }

    public LocalDateTime getInicioPrevistaFim() {
        if (nonNull(inicioPrevisto)) {
            return inicioPrevisto.atTime(LocalTime.MAX);
        }
        else return null;
    }

}
