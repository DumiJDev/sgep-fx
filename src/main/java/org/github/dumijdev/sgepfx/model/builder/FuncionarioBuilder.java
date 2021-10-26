package org.github.dumijdev.sgepfx.model.builder;

import org.github.dumijdev.sgepfx.model.Funcionario;

import java.time.LocalDate;

public class FuncionarioBuilder {
    private String nome;
    private LocalDate dataDeNascimento;
    private String nomeDoPai;
    private String nomeDaMae;
    private String bi;
    private String telefone;
    private String imagem;

    private FuncionarioBuilder() {
    }

    public static FuncionarioBuilder novaInstancia() {
        return new FuncionarioBuilder();
    }

    public FuncionarioBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public FuncionarioBuilder comDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
        return this;
    }

    public FuncionarioBuilder comNomeDoPai(String nomeDoPai) {
        this.nomeDoPai = nomeDoPai;
        return this;
    }

    public FuncionarioBuilder comNomeDaMae(String nomeDaMae) {
        this.nomeDaMae = nomeDaMae;
        return this;
    }

    public FuncionarioBuilder comBi(String bi) {
        this.bi = bi;
        return this;
    }

    public FuncionarioBuilder comTelefone(String telefone) {
        this.telefone = telefone;
        return this;
    }

    public FuncionarioBuilder comImagem(String imagem) {
        this.imagem = imagem;
        return this;
    }

    public Funcionario constroi() {
        var f = new Funcionario();

        f.setTelefone(telefone);
        f.setBi(bi);
        f.setDataDeNascimento(dataDeNascimento);
        f.setNomeDaMae(nomeDaMae);
        f.setNomeDoPai(nomeDoPai);
        f.setNome(nome);
        f.setImagem(imagem);

        return f;
    }
}
