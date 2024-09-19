package br.com.selecao.locadora.repository;

import br.com.selecao.locadora.business.view.FiltroLeiloesVO;
import br.com.selecao.locadora.business.view.LeilaoListaVO;
import br.com.selecao.locadora.entity.Leilao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeilaoRepository extends JpaRepository<Leilao, Long> {

    @Query("SELECT new br.com.selecao.locadora.business.view.LeilaoListaVO( "
            + " leilao.id, v.razaoSocial, leilao.inicioPrevisto, sum(lote.quantidade * lote.valorInicial)) "
            + " FROM Leilao leilao LEFT JOIN Lote lote ON lote.leilao.id = leilao.id LEFT JOIN leilao.vendedor v "
            + " WHERE ((cast(:#{#filtro.razaoSocial} as string) IS NULL) "
            + "   OR (v.razaoSocial LIKE ('%' || cast(:#{#filtro.razaoSocial} as string) || '%'))) "
            + " AND ((coalesce(:#{#filtro.inicioPrevisto}, NULL) IS NULL) OR "
            + "   (leilao.inicioPrevisto BETWEEN :#{#filtro.inicioPrevistaInicio} AND :#{#filtro.inicioPrevistaFim})) "
            + " GROUP BY leilao.id, v.razaoSocial, leilao.inicioPrevisto")
    List<LeilaoListaVO> buscarPorFiltro(FiltroLeiloesVO filtro);

}
