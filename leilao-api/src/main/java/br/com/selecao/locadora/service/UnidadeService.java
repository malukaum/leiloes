package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.UnidadeBO;
import br.com.selecao.locadora.business.view.UnidadeVO;
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
@RequestMapping(value = "/unidades")
public class UnidadeService {

    private final UnidadeBO unidadeBO;

    @GetMapping
    public ResponseEntity<Object> buscarTodos() {
        return new ResponseEntity<>(unidadeBO.buscarTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarUnidade(@PathVariable("id") final Long id) {
        return new ResponseEntity<>(unidadeBO.buscarUnidade(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> inserirUnidade(@RequestBody final UnidadeVO unidade) {
        unidadeBO.inserirUnidade(unidade);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> alterarUnidade(@PathVariable("id") final Long id,
                                               @RequestBody final UnidadeVO unidade) {
        unidadeBO.alterarUnidade(id, unidade);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUnidade(@PathVariable("id") final Long id) {
        unidadeBO.deletarUnidade(id);
        return ResponseEntity.ok().build();
    }

}
