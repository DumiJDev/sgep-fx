package org.github.dumijdev.sgepfx.model.property;

import javafx.beans.property.SimpleStringProperty;
import org.github.dumijdev.sgepfx.model.TipoDePagamento;

import java.util.Objects;

public class TipoDePagamentoProperty {
    private final SimpleStringProperty tipo;

    public TipoDePagamentoProperty(TipoDePagamento tipo) {
        this.tipo = new SimpleStringProperty(tipo.getS());
    }

    public String getTipo() {
        return tipo.get();
    }

    public void setTipo(String tipo) {
        this.tipo.set(tipo);
    }

    public SimpleStringProperty tipoProperty() {
        return tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TipoDePagamentoProperty)) return false;
        TipoDePagamentoProperty that = (TipoDePagamentoProperty) o;
        return getTipo().equals(that.getTipo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTipo());
    }

    @Override
    public String toString() {
        return tipo.get();
    }
}
