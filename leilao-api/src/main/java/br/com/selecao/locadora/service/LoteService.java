package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.LoteBO;
import br.com.selecao.locadora.business.view.LoteVO;
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

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/lotes")
public class LoteService {

    private final LoteBO loteBO;

    @GetMapping
    public ResponseEntity<Object> buscarTodos() {
        return new ResponseEntity<>(loteBO.buscarTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarLote(@PathVariable("id") final Long id) {
        return new ResponseEntity<>(loteBO.buscarLote(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> inserirLote(@RequestBody final LoteVO unidade) {
        loteBO.inserirLote(unidade);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> alterarLote(@PathVariable("id") final Long id, @RequestBody final LoteVO unidade) {
        loteBO.alterarLote(id, unidade);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLote(@PathVariable("id") final Long id) {
        loteBO.deletarLote(id);
        return ResponseEntity.ok().build();
    }

}
