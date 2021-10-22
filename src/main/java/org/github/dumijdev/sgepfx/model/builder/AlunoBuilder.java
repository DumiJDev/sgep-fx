package org.github.dumijdev.sgepfx.model.builder;

import org.github.dumijdev.sgepfx.model.Aluno;

import java.time.LocalDate;

public class AlunoBuilder {
    private String nomeProprio;
    private String nomeDeFamilia;
    private LocalDate dataDeNascimento;
    private String nomeDoPai;
    private String nomeDaMae;
    private String bi;
    private LocalDate dataDaInscricao;

    private AlunoBuilder() {
    }

    public static AlunoBuilder novaInstancia() {
        return new AlunoBuilder();
    }

    public AlunoBuilder comAnoDaInscricao(LocalDate anoDaInscricao) {
        this.dataDaInscricao = anoDaInscricao;
        return this;
    }

    public AlunoBuilder comNomeProprio(String nomeProprio) {
        this.nomeProprio = nomeProprio;
        return this;
    }

    public AlunoBuilder comNomeDeFamilia(String nomeDeFamilia) {
        this.nomeDeFamilia = nomeDeFamilia;
        return this;
    }

    public AlunoBuilder comDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
        return this;
    }

    public AlunoBuilder comNomeDoPai(String nomeDoPai) {
        this.nomeDoPai = nomeDoPai;
        return this;
    }

    public AlunoBuilder comNomeDaMae(String nomeDaMae) {
        this.nomeDaMae = nomeDaMae;
        return this;
    }

    public AlunoBuilder comBi(String bi) {
        this.bi = bi;
        return this;
    }

    public Aluno constroi() {
        final var aluno = new Aluno();
        aluno.setDataDaInscricao(dataDaInscricao);
        aluno.setBi(bi);
        aluno.setDataDeNascimento(dataDeNascimento);
        aluno.setNomeDaMae(nomeDaMae);
        aluno.setNomeDeFamilia(nomeDeFamilia);
        aluno.setNomeDoPai(nomeDoPai);
        aluno.setNomeProprio(nomeProprio);

        return aluno;
    }
}
