package br.com.selecao.locadora.business;

import br.com.selecao.locadora.business.view.FiltroLeiloesVO;
import br.com.selecao.locadora.business.view.LeilaoListaVO;
import br.com.selecao.locadora.business.view.LeilaoVO;
import br.com.selecao.locadora.entity.Leilao;
import br.com.selecao.locadora.repository.LeilaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LeilaoBO {

    private final LeilaoRepository leilaoRepository;

    public List<Leilao> buscarTodos() {
        return leilaoRepository.findAll();
    }

    public List<LeilaoListaVO> buscarPorFiltro(final FiltroLeiloesVO filtro) {
        return leilaoRepository.buscarPorFiltro(filtro);
    }

    public Leilao buscarLeilao(final Long id) {
        return leilaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leilão não encontrado"));
    }

    @Transactional
    public void inserirLeilao(final LeilaoVO unidade) {
        final Leilao novo = new Leilao();
        BeanUtils.copyProperties(unidade, novo);
        novo.setCreatedAt(LocalDateTime.now());
        novo.setUpdatedAt(LocalDateTime.now());
        leilaoRepository.save(novo);
    }

    @Transactional
    public void alterarLeilao(final Long id, final LeilaoVO unidade) {
        final Leilao salvo = buscarLeilao(id);
        BeanUtils.copyProperties(unidade, salvo);
        salvo.setUpdatedAt(LocalDateTime.now());
        leilaoRepository.save(salvo);
    }

    @Transactional
    public void deletarLeilao(final Long id) {
        final Leilao salvo = buscarLeilao(id);
        leilaoRepository.delete(salvo);
    }

}
