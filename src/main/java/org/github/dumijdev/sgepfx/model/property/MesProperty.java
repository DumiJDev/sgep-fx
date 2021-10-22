package org.github.dumijdev.sgepfx.model.property;

import javafx.beans.property.SimpleStringProperty;
import org.github.dumijdev.sgepfx.model.Mes;

public class MesProperty {
    SimpleStringProperty valor;

    public MesProperty(String valor) {
        this.valor = new SimpleStringProperty(valor);
    }

    public MesProperty(Mes mes) {
        valor = new SimpleStringProperty(mes.getMes());
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

    @Override
    public String toString() {
        return valor.get();
    }
}
