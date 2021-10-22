package org.github.dumijdev.sgepfx.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Table(name = "tb_pagamento")
@Entity
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "pagamento")
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private TipoDePagamento tipoDePagamento;

    @Column(name = "data_de_pagamento")
    private LocalDateTime dataDePagamento;

    @Column(name = "valor_pago", nullable = false, precision = 19, scale = 3)
    private BigDecimal valorPago;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    public Long getId() {
        return id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public void setValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
    }

    public LocalDateTime getDataDePagamento() {
        return dataDePagamento;
    }

    public void setDataDePagamento(LocalDateTime dataDePagamento) {
        this.dataDePagamento = dataDePagamento;
    }

    public TipoDePagamento getTipoDePagamento() {
        return tipoDePagamento;
    }

    public void setTipoDePagamento(TipoDePagamento tipoDePagamento) {
        this.tipoDePagamento = tipoDePagamento;
    }
}