package org.github.dumijdev.sgepfx.model.builder;

import org.github.dumijdev.sgepfx.model.Acesso;
import org.github.dumijdev.sgepfx.model.Funcionario;
import org.github.dumijdev.sgepfx.model.Usuario;


public class UsuarioBuilder {
    private String login;
    private String senha;
    private Acesso acesso;
    private Funcionario funcionario;

    private UsuarioBuilder() {
    }

    public static UsuarioBuilder novaInstancia() {
        return new UsuarioBuilder();
    }

    public UsuarioBuilder comLogin(String login) {
        this.login = login;
        return this;
    }

    public UsuarioBuilder comSenha(String senha) {
        this.senha = senha;
        return this;
    }

    public UsuarioBuilder comAcesso(Acesso acesso) {
        this.acesso = acesso;
        return this;
    }

    public UsuarioBuilder comFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
        return this;
    }

    public Usuario constroi() {
        var u = new Usuario();

        u.setLogin(login);
        u.setSenha(senha);
        u.setAcesso(acesso);
        u.setFuncionario(funcionario);

        return u;
    }
}
