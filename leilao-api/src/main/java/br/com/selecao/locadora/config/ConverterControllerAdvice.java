package br.com.selecao.locadora.config;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.apache.logging.log4j.util.Strings.isNotBlank;

@ControllerAdvice
public class ConverterControllerAdvice {

    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String dateText) throws IllegalArgumentException {
                if (isNotBlank(dateText)) {
                    setValue(LocalDate.parse(dateText, DateTimeFormatter.ISO_LOCAL_DATE));
                }
            }
        });

        webDataBinder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String dateText) throws IllegalArgumentException {
                if (isNotBlank(dateText)) {
                    setValue(LocalDateTime.parse(dateText, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                }
            }
        });
    }

}
