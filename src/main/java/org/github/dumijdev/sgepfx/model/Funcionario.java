package org.github.dumijdev.sgepfx.model;

import javax.persistence.*;

@Table(name = "tb_funcionario")
@Entity
public class Funcionario extends Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "funcionario")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "telefone", nullable = false)
    private String telefone;

    @Column(name = "imagem")
    private String imagem;

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

}