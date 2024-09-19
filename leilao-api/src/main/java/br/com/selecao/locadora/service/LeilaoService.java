package br.com.selecao.locadora.service;

import br.com.selecao.locadora.business.LeilaoBO;
import br.com.selecao.locadora.business.view.FiltroLeiloesVO;
import br.com.selecao.locadora.business.view.LeilaoListaVO;
import br.com.selecao.locadora.business.view.LeilaoVO;
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
@RequestMapping(value = "/leiloes")
public class LeilaoService {

    private final LeilaoBO leilaoBO;

    @GetMapping
    public ResponseEntity<Object> buscarTodos() {
        return new ResponseEntity<>(leilaoBO.buscarTodos(), HttpStatus.OK);
    }

    @GetMapping("/filtro")
    public ResponseEntity<List<LeilaoListaVO>> buscarPorFiltro(final FiltroLeiloesVO filtro) {
        return new ResponseEntity<>(leilaoBO.buscarPorFiltro(filtro), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarLeilao(@PathVariable("id") final Long id) {
        return new ResponseEntity<>(leilaoBO.buscarLeilao(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> inserirLeilao(@RequestBody final LeilaoVO unidade) {
        leilaoBO.inserirLeilao(unidade);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> alterarLeilao(@PathVariable("id") final Long id, @RequestBody final LeilaoVO unidade) {
        leilaoBO.alterarLeilao(id, unidade);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLeilao(@PathVariable("id") final Long id) {
        leilaoBO.deletarLeilao(id);
        return ResponseEntity.ok().build();
    }

}
