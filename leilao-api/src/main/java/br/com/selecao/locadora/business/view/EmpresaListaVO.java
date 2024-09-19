package br.com.selecao.locadora.business.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaListaVO {

    private Long id;
    private String cnpj;
    private String razaoSocial;
    private String telefone;
    private String email;

}
