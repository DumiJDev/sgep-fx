package org.github.dumijdev.sgepfx.model.property;

import javafx.beans.property.SimpleStringProperty;
import org.github.dumijdev.sgepfx.model.Acesso;

public class AcessoProperty {
    private final SimpleStringProperty valor;

    public AcessoProperty(Acesso acesso) {
        this.valor = new SimpleStringProperty(acesso.getAcesso());
    }

    public String getValor() {
        return valor.get();
    }

    public void setValor(String valor) {
        this.valor.set(valor);
    }

    public SimpleStringProperty valorProperty() {
        return valor;
    }

    @Override
    public String toString() {
        return valor.get();
    }
}
