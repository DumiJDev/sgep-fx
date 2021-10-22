package org.github.dumijdev.sgepfx.model.builder;

import org.github.dumijdev.sgepfx.model.Aluno;
import org.github.dumijdev.sgepfx.model.Mes;
import org.github.dumijdev.sgepfx.model.Pagamento;
import org.github.dumijdev.sgepfx.model.Propina;

public class PropinaBuilder {
    private Long id;
    private Aluno aluno;
    private Pagamento pagamento;
    private Mes mes;
    private Integer classe;

    private PropinaBuilder() {

    }

    public static PropinaBuilder novaInstancia() {
        return new PropinaBuilder();
    }

    public PropinaBuilder comId(Long id) {
        this.id = id;
        return this;
    }

    public PropinaBuilder comAluno(Aluno aluno) {
        this.aluno = aluno;
        return this;
    }

    public PropinaBuilder comPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
        return this;
    }

    public PropinaBuilder comMes(Mes mes) {
        this.mes = mes;
        return this;
    }

    public PropinaBuilder comClasse(Integer classe) {
        this.classe = classe;
        return this;
    }

    public Propina constroi() {
        var p = new Propina();

        p.setAluno(aluno);
        p.setClasse(classe);
        p.setId(id);
        p.setMes(mes);
        p.setPagamento(pagamento);

        return p;
    }
}
