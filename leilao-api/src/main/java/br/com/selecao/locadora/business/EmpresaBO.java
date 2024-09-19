package br.com.selecao.locadora.business;

import br.com.selecao.locadora.business.view.EmpresaListaVO;
import br.com.selecao.locadora.business.view.EmpresaVO;
import br.com.selecao.locadora.business.view.FiltroEmpresasVO;
import br.com.selecao.locadora.entity.Empresa;
import br.com.selecao.locadora.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isNotEmpty;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmpresaBO {

    private final EmpresaRepository empresaRepository;

    public List<Empresa> buscarTodos() {
        return empresaRepository.findAll();
    }

    public List<EmpresaListaVO> buscarPorFiltro(final FiltroEmpresasVO filtro) {
        return empresaRepository.buscarPorFiltro(filtro);
    }


    public Empresa buscarEmpresa(final Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leilão não encontrada"));
    }

    @Transactional
    public void inserirEmpresa(final EmpresaVO empresa) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        final Empresa novo = new Empresa();
        BeanUtils.copyProperties(empresa, novo);
        novo.setCreatedAt(LocalDateTime.now());
        novo.setUpdatedAt(LocalDateTime.now());
        novo.setSenha(encoder.encode(novo.getSenha()));
        empresaRepository.save(novo);
    }

    @Transactional
    public void alterarEmpresa(final Long id, final EmpresaVO empresa) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        final Empresa salvo = buscarEmpresa(id);
        BeanUtils.copyProperties(empresa, salvo, "senha");
        salvo.setUpdatedAt(LocalDateTime.now());
        if (isNotEmpty(empresa.getSenha())) {
            salvo.setSenha(encoder.encode(empresa.getSenha()));
        }
        empresaRepository.save(salvo);
    }

    @Transactional
    public void deletarEmpresa(final Long id) {
        final Empresa salvo = buscarEmpresa(id);
        empresaRepository.delete(salvo);
    }

}
