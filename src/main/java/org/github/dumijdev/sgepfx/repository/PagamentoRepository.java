package org.github.dumijdev.sgepfx.repository;

import org.github.dumijdev.sgepfx.model.Pagamento;
import org.github.dumijdev.sgepfx.model.TipoDePagamento;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PagamentoRepository extends PagingAndSortingRepository<Pagamento, Long>, JpaSpecificationExecutor<Pagamento> {
    @Query("select p from Pagamento p order by p.tipoDePagamento, p.dataDePagamento")
    List<Pagamento> buscaTodos();

    @Query("select p from Pagamento p where upper(p.aluno.nome) like upper(concat('%', ?1, '%')) order by p.aluno.nome")
    List<Pagamento> buscaPeloNome(@NonNull String nome);

    @Query("select p from Pagamento p where upper(p.aluno.nome) like upper(concat('%', ?1, '%')) and p.tipoDePagamento = ?2 order by p.aluno.nome, p.tipoDePagamento")
    List<Pagamento> buscaPeloNomeETipoDePagamento(@NonNull String nome, @NonNull TipoDePagamento tipoDePagamento);


}