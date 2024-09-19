package br.com.selecao.locadora.repository;

import br.com.selecao.locadora.business.view.EmpresaListaVO;
import br.com.selecao.locadora.business.view.FiltroEmpresasVO;
import br.com.selecao.locadora.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

    @Query("SELECT new br.com.selecao.locadora.business.view.EmpresaListaVO( "
            + "e.id, e.cnpj, e.razaoSocial, e.telefone, e.email) FROM Empresa e "
            + " WHERE ((cast(:#{#filtro.cnpj} as string) IS NULL) OR (cast(:#{#filtro.cnpj} as string) = e.cnpj)) "
            + "  AND ((cast(:#{#filtro.telefone} as string) IS NULL) "
            + "   OR (cast(:#{#filtro.telefone} as string) = e.telefone)) "
            + "  AND ((cast(:#{#filtro.email} as string) IS NULL) "
            + "   OR (e.email LIKE ('%' || cast(:#{#filtro.email} as string) || '%'))) "
            + "  AND ((cast(:#{#filtro.razaoSocial} as string) IS NULL) "
            + "   OR (e.razaoSocial LIKE ('%' || cast(:#{#filtro.razaoSocial} as string) || '%'))) ")
    List<EmpresaListaVO> buscarPorFiltro(FiltroEmpresasVO filtro);

}
