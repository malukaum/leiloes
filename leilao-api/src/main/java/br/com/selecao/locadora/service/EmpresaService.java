package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.EmpresaBO;
import br.com.selecao.locadora.business.view.EmpresaListaVO;
import br.com.selecao.locadora.business.view.EmpresaVO;
import br.com.selecao.locadora.business.view.FiltroEmpresasVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/empresas")
public class EmpresaService {

    private final EmpresaBO empresaBO;

    @GetMapping
    public ResponseEntity<Object> buscarTodos() {
        return new ResponseEntity<>(empresaBO.buscarTodos(), HttpStatus.OK);
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<EmpresaListaVO>> buscarPorFiltro(final FiltroEmpresasVO filtro) {
        return new ResponseEntity<>(empresaBO.buscarPorFiltro(filtro), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarEmpresa(@PathVariable("id") final Long id) {
        return new ResponseEntity<>(empresaBO.buscarEmpresa(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> inserirEmpresa(@RequestBody final EmpresaVO unidade) {
        empresaBO.inserirEmpresa(unidade);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> alterarEmpresa(@PathVariable("id") final Long id,
                                               @RequestBody final EmpresaVO unidade) {
        empresaBO.alterarEmpresa(id, unidade);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmpresa(@PathVariable("id") final Long id) {
        empresaBO.deletarEmpresa(id);
        return ResponseEntity.ok().build();
    }

}
