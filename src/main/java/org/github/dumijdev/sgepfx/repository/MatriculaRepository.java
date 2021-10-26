package org.github.dumijdev.sgepfx.repository;

import org.github.dumijdev.sgepfx.model.Aluno;
import org.github.dumijdev.sgepfx.model.Matricula;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MatriculaRepository extends PagingAndSortingRepository<Matricula, Long> {
    @Query("select m from Matricula m where m.classe = ?1 order by m.aluno.nome")
    List<Matricula> buscaPelaClasse(@NonNull Integer classe);

    @Query("select m from Matricula m where upper(m.aluno.nome) like upper(concat('%', ?1, '%')) order by m.aluno.nome")
    List<Matricula> buscaPeloNome(@NonNull String nome);

    @Query("select m from Matricula m where upper(m.aluno.nome) = upper(?1) and m.data between ?2 and ?3 order by m.aluno.nome")
    List<Matricula> buscaPeloNomeEntre(@NonNull String nome, @NonNull LocalDate dataStart, @NonNull LocalDate dataEnd);

    @Query("select m from Matricula m where m.classe = ?1 and m.data between ?2 and ?3 order by m.aluno.nome, m.data")
    List<Matricula> buscaPelaClasseEntre(@NonNull Integer classe, @NonNull LocalDate dataStart, @NonNull LocalDate dataEnd);

    @Query("select m from Matricula m where m.data between ?1 and ?2 order by m.aluno.nome")
    List<Matricula> buscaEntre(@NonNull LocalDate dataStart, @NonNull LocalDate dataEnd);

    @Query("select m from Matricula m order by m.classe, m.data")
    List<Matricula> buscaTodos();

    @Query("select m from Matricula m where m.aluno = ?1 order by m.aluno.nome")
    List<Matricula> buscaPeloAluno(@NonNull Aluno aluno);


}