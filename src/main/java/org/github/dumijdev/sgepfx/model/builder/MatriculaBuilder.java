package org.github.dumijdev.sgepfx.model.builder;

import org.github.dumijdev.sgepfx.model.Aluno;
import org.github.dumijdev.sgepfx.model.Matricula;
import org.github.dumijdev.sgepfx.model.Pagamento;

import java.time.LocalDate;

public class MatriculaBuilder {
    private Aluno aluno;
    private Integer classe;
    private LocalDate data;
    private Pagamento pagamento;

    private MatriculaBuilder() {
    }

    public static MatriculaBuilder novaInstancia() {
        return new MatriculaBuilder();
    }

    public MatriculaBuilder comAluno(Aluno aluno) {
        this.aluno = aluno;
        return this;
    }

    public MatriculaBuilder comClasse(Integer classe) {
        this.classe = classe;
        return this;
    }

    public MatriculaBuilder comData(LocalDate data) {
        this.data = data;
        return this;
    }

    public MatriculaBuilder comPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
        return this;
    }

    public Matricula constroi() {
        final var m = new Matricula();

        m.setAluno(aluno);
        m.setClasse(classe);
        m.setData(data);
        m.setPagamento(pagamento);

        return m;
    }
}
