package org.github.dumijdev.sgepfx.repository;

import org.github.dumijdev.sgepfx.model.Aluno;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Long> {
    @Query("select a from Aluno a where a.id = ?1")
    Aluno busca(Long id);

    @Query("select a from Aluno a where upper(a.nome) like upper(concat('%', ?1, '%')) order by a.nome")
    List<Aluno> buscaPeloNomeContendo(@NonNull String nome);

    @Query("select a from Aluno a where upper(a.bi) like upper(concat('%', ?1, '%')) order by a.nome")
    List<Aluno> buscaPeloBIContendo(@NonNull String bi);

    @Query("select a from Aluno a order by a.nome")
    List<Aluno> buscaTodos();


}