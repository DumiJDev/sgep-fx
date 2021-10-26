package org.github.dumijdev.sgepfx.model;

import javax.persistence.*;

@Table(name = "tb_propina")
@Entity
public class Propina {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "propina")
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @OneToOne(cascade = CascadeType.ALL, optional = false, orphanRemoval = true)
    @JoinColumn(name = "pagamento_id", nullable = false)
    private Pagamento pagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "mes", nullable = false)
    private Mes mes;

    @Column(name = "classe")
    private Integer classe;

    public Propina() {
    }

    public Integer getClasse() {
        return classe;
    }

    public void setClasse(Integer classe) {
        this.classe = classe;
    }

    public Mes getMes() {
        return mes;
    }

    public void setMes(Mes mes) {
        this.mes = mes;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}