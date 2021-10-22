package org.github.dumijdev.sgepfx.model.property;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class PagamentoProperty {
    private final SimpleLongProperty id;
    private final SimpleStringProperty nome;
    private final SimpleStringProperty tipoDePagamento;
    private final SimpleStringProperty valor;
    private final SimpleStringProperty dataDePagamento;

    public PagamentoProperty(Long id, String nome, String tipoDePagamento, String valor, String dataDePagamento) {
        this.id = new SimpleLongProperty(id);
        this.nome = new SimpleStringProperty(nome);
        this.tipoDePagamento = new SimpleStringProperty(tipoDePagamento);
        this.valor = new SimpleStringProperty(valor);
        this.dataDePagamento = new SimpleStringProperty(dataDePagamento);
    }

    public long getId() {
        return id.get();
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public String getNome() {
        return nome.get();
    }

    public SimpleStringProperty nomeProperty() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public String getTipoDePagamento() {
        return tipoDePagamento.get();
    }

    public SimpleStringProperty tipoDePagamentoProperty() {
        return tipoDePagamento;
    }

    public void setTipoDePagamento(String tipoDePagamento) {
        this.tipoDePagamento.set(tipoDePagamento);
    }

    public String getValor() {
        return valor.get();
    }

    public SimpleStringProperty valorProperty() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor.set(valor);
    }

    public String getDataDePagamento() {
        return dataDePagamento.get();
    }

    public SimpleStringProperty dataDePagamentoProperty() {
        return dataDePagamento;
    }

    public void setDataDePagamento(String dataDePagamento) {
        this.dataDePagamento.set(dataDePagamento);
    }
}
