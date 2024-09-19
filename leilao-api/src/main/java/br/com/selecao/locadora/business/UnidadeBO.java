package br.com.selecao.locadora.business;

import br.com.selecao.locadora.business.view.UnidadeVO;
import br.com.selecao.locadora.entity.Unidade;
import br.com.selecao.locadora.repository.UnidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UnidadeBO {

    private final UnidadeRepository unidadeRepository;

    public List<Unidade> buscarTodos() {
        return unidadeRepository.findAll();
    }

    public Unidade buscarUnidade(final Long id) {
        return unidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada"));
    }

    @Transactional
    public void inserirUnidade(final UnidadeVO unidade) {
        final Unidade novo = new Unidade();
        BeanUtils.copyProperties(unidade, novo);
        novo.setCreatedAt(LocalDateTime.now());
        novo.setUpdatedAt(LocalDateTime.now());
        unidadeRepository.save(novo);
    }

    @Transactional
    public void alterarUnidade(final Long id, final UnidadeVO unidade) {
        final Unidade salvo = buscarUnidade(id);
        BeanUtils.copyProperties(unidade, salvo);
        salvo.setUpdatedAt(LocalDateTime.now());
        unidadeRepository.save(salvo);
    }

    @Transactional
    public void deletarUnidade(final Long id) {
        final Unidade salvo = buscarUnidade(id);
        unidadeRepository.delete(salvo);
    }

}
