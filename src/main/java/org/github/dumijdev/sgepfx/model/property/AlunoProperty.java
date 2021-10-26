package org.github.dumijdev.sgepfx.model.property;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class AlunoProperty {
    private final SimpleLongProperty id;
    private final SimpleStringProperty nome;
    private final SimpleIntegerProperty idade;
    private final SimpleStringProperty pagamento;
    private final SimpleStringProperty dataDeInscricao;

    public AlunoProperty(Long id, String nome, Integer idade, String pagamento, String dataDeInscricao) {
        this.id = new SimpleLongProperty(id);
        this.nome = new SimpleStringProperty(nome);
        this.idade = new SimpleIntegerProperty(idade);
        this.pagamento = new SimpleStringProperty(pagamento);
        this.dataDeInscricao = new SimpleStringProperty(dataDeInscricao);
    }

    public long getId() {
        return id.get();
    }

    public void setId(long id) {
        this.id.set(id);
    }

    public SimpleLongProperty idProperty() {
        return id;
    }

    public String getNome() {
        return nome.get();
    }

    public void setNome(String nome) {
        this.nome.set(nome);
    }

    public SimpleStringProperty nomeProperty() {
        return nome;
    }

    public int getIdade() {
        return idade.get();
    }

    public void setIdade(int idade) {
        this.idade.set(idade);
    }

    public SimpleIntegerProperty idadeProperty() {
        return idade;
    }

    public String getPagamento() {
        return pagamento.get();
    }

    public void setPagamento(String pagamento) {
        this.pagamento.set(pagamento);
    }

    public SimpleStringProperty pagamentoProperty() {
        return pagamento;
    }

    public String getDataDeInscricao() {
        return dataDeInscricao.get();
    }

    public void setDataDeInscricao(String dataDeInscricao) {
        this.dataDeInscricao.set(dataDeInscricao);
    }

    public SimpleStringProperty dataDeInscricaoProperty() {
        return dataDeInscricao;
    }

    @Override
    public String toString() {
        return String.format("%s (%d anos)", nome.get(), idade.get());
    }
}
