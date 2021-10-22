package org.github.dumijdev.sgepfx.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@MappedSuperclass
public abstract class Pessoa {
    @Column(name = "nome_proprio", nullable = false)
    protected String nomeProprio;
    @Column(name = "nome_de_familia", nullable = false)
    protected String nomeDeFamilia;
    @Column(name = "data_de_nascimento", nullable = false)
    protected LocalDate dataDeNascimento;
    @Column(name = "nome_do_pai")
    protected String nomeDoPai;
    @Column(name = "nome_da_mae")
    protected String nomeDaMae;
    @Column(name = "bi", nullable = false)
    protected String bi;

    public String getNomeProprio() {
        return nomeProprio;
    }

    public void setNomeProprio(String nomeProprio) {
        this.nomeProprio = nomeProprio;
    }

    public String getNomeDeFamilia() {
        return nomeDeFamilia;
    }

    public void setNomeDeFamilia(String nomeDeFamilia) {
        this.nomeDeFamilia = nomeDeFamilia;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(LocalDate dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getNomeDoPai() {
        return nomeDoPai;
    }

    public void setNomeDoPai(String nomeDoPai) {
        this.nomeDoPai = nomeDoPai;
    }

    public String getNomeDaMae() {
        return nomeDaMae;
    }

    public void setNomeDaMae(String nomeDaMae) {
        this.nomeDaMae = nomeDaMae;
    }

    public String getBi() {
        return bi;
    }

    public void setBi(String bi) {
        this.bi = bi;
    }

    public String nomeCompleto() {
        return nomeProprio + ' ' + nomeDeFamilia;
    }
}
