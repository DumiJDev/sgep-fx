package org.github.dumijdev.sgepfx.model.property;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class MatriculaProperty {
    private final SimpleLongProperty id;
    private final SimpleStringProperty nome;
    private final SimpleStringProperty classe;
    private final SimpleStringProperty valorDoPagamento;
    private final SimpleStringProperty dataDePagamento;

    public MatriculaProperty(Long id, String nome, String classe, String valorDoPagamento, String dataDePagamento) {
        this.id = new SimpleLongProperty(id);
        this.nome = new SimpleStringProperty(nome);
        this.classe = new SimpleStringProperty(classe + "ª");
        this.valorDoPagamento = new SimpleStringProperty(valorDoPagamento + " Akz");
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

    public String getClasse() {
        return classe.get();
    }

    public SimpleStringProperty classeProperty() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe.set(classe);
    }

    public String getValorDoPagamento() {
        return valorDoPagamento.get();
    }

    public SimpleStringProperty valorDoPagamentoProperty() {
        return valorDoPagamento;
    }

    public void setValorDoPagamento(String valorDoPagamento) {
        this.valorDoPagamento.set(valorDoPagamento);
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
