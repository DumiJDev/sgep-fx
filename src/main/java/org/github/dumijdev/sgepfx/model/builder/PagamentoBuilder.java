package org.github.dumijdev.sgepfx.model.builder;

import org.github.dumijdev.sgepfx.model.Aluno;
import org.github.dumijdev.sgepfx.model.Pagamento;
import org.github.dumijdev.sgepfx.model.TipoDePagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PagamentoBuilder {
    private TipoDePagamento tipoDePagamento;
    private LocalDateTime dataDePagamento;
    private BigDecimal valorPago;
    private Aluno aluno;

    private PagamentoBuilder() {

    }

    public static PagamentoBuilder novaInstancia() {
        return new PagamentoBuilder();
    }

    public PagamentoBuilder comTipoDePagamento(TipoDePagamento tipoDePagamento) {
        this.tipoDePagamento = tipoDePagamento;
        return this;
    }

    public PagamentoBuilder comDataDePagamento(LocalDateTime dataDePagamento) {
        this.dataDePagamento = dataDePagamento;
        return this;
    }

    public PagamentoBuilder comValorPago(BigDecimal valorPago) {
        this.valorPago = valorPago;
        return this;
    }

    public PagamentoBuilder comAluno(Aluno aluno) {
        this.aluno = aluno;
        return this;
    }

    public Pagamento constroi() {
        final var p = new Pagamento();

        p.setAluno(aluno);
        p.setDataDePagamento(dataDePagamento);
        p.setTipoDePagamento(tipoDePagamento);
        p.setValorPago(valorPago);

        return p;
    }
}
