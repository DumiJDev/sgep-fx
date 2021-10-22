package org.github.dumijdev.sgepfx.repository;

import org.github.dumijdev.sgepfx.model.Pagamento;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends PagingAndSortingRepository<Pagamento, Long>, JpaSpecificationExecutor<Pagamento> {
    @Query("select p from Pagamento p order by p.tipoDePagamento, p.dataDePagamento")
    List<Pagamento> buscaTodos();

}