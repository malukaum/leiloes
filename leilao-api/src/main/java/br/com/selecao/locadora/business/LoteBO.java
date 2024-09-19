package br.com.selecao.locadora.business;

import br.com.selecao.locadora.business.view.LoteVO;
import br.com.selecao.locadora.entity.Lote;
import br.com.selecao.locadora.repository.LoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoteBO {

    private final LoteRepository loteRepository;

    public List<Lote> buscarTodos() {
        return loteRepository.findAll();
    }

    public Lote buscarLote(final Long id) {
        return loteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leilão não encontrada"));
    }

    @Transactional
    public void inserirLote(final LoteVO unidade) {
        final Lote novo = new Lote();
        BeanUtils.copyProperties(unidade, novo);
        novo.setCreatedAt(LocalDateTime.now());
        novo.setUpdatedAt(LocalDateTime.now());
        loteRepository.save(novo);
    }

    @Transactional
    public void alterarLote(final Long id, final LoteVO unidade) {
        final Lote salvo = buscarLote(id);
        BeanUtils.copyProperties(unidade, salvo);
        salvo.setUpdatedAt(LocalDateTime.now());
        loteRepository.save(salvo);
    }

    @Transactional
    public void deletarLote(final Long id) {
        final Lote salvo = buscarLote(id);
        loteRepository.delete(salvo);
    }

}
