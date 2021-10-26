package org.github.dumijdev.sgepfx.model.property;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;

public class UsuarioProperty {
    private final SimpleLongProperty idFuncionario;
    private final SimpleStringProperty telefone;
    private final SimpleStringProperty nome;
    private final SimpleStringProperty username;
    private final SimpleStringProperty acesso;

    public UsuarioProperty(Long idFuncionario, String telefone, String nome, String username, String acesso) {
        this.idFuncionario = new SimpleLongProperty(idFuncionario);
        this.telefone = new SimpleStringProperty(telefone);
        this.nome = new SimpleStringProperty(nome);
        this.username = new SimpleStringProperty(username);
        this.acesso = new SimpleStringProperty(acesso);
    }

    public long getIdFuncionario() {
        return idFuncionario.get();
    }

    public void setIdFuncionario(long idFuncionario) {
        this.idFuncionario.set(idFuncionario);
    }

    public SimpleLongProperty idFuncionarioProperty() {
        return idFuncionario;
    }

    public String getTelefone() {
        return telefone.get();
    }

    public void setTelefone(String telefone) {
        this.telefone.set(telefone);
    }

    public SimpleStringProperty telefoneProperty() {
        return telefone;
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

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public String getAcesso() {
        return acesso.get();
    }

    public void setAcesso(String acesso) {
        this.acesso.set(acesso);
    }

    public SimpleStringProperty acessoProperty() {
        return acesso;
    }
}
