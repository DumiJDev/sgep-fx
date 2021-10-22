package org.github.dumijdev.sgepfx.model;

import javax.persistence.*;
import java.time.LocalDate;

@Table(name = "tb_matricula")
@Entity
public class Matricula {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "matricula")
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @Column(name = "classe", nullable = false)
    private Integer classe;

    @Column(name = "data", nullable = false)
    private LocalDate data;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "pagamento_id", nullable = false)
    private Pagamento pagamento;

    public Matricula() {
    }

    public Long getId() {
        return id;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Integer getClasse() {
        return classe;
    }

    public void setClasse(Integer classe) {
        this.classe = classe;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}