package org.github.dumijdev.sgepfx.model;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "tb_aluno")
@Entity
public class Aluno extends Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "aluno")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "data_da_inscricao")
    private LocalDate dataDaInscricao;

    @Column(name = "matriculado")
    private Boolean matriculado;

    public Long getId() {
        return id;
    }

    public Boolean isMatriculado() {
        return matriculado;
    }

    public void setMatriculado(Boolean matriculado) {
        this.matriculado = matriculado;
    }

    public LocalDate getDataDaInscricao() {
        return dataDaInscricao;
    }

    public void setDataDaInscricao(LocalDate dataDaInscricao) {
        this.dataDaInscricao = dataDaInscricao;
    }
}