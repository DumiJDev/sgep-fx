package org.github.dumijdev.sgepfx.model.property;

import javafx.beans.property.SimpleIntegerProperty;

import java.util.Objects;

public class ClasseProperty {
    private final SimpleIntegerProperty valor;

    public ClasseProperty(int valor) {
        this.valor = new SimpleIntegerProperty(valor);
    }

    public int getValor() {
        return valor.get();
    }

    public void setValor(int valor) {
        this.valor.set(valor);
    }

    public SimpleIntegerProperty valorProperty() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClasseProperty)) return false;
        ClasseProperty that = (ClasseProperty) o;
        return Objects.equals(getValor(), that.getValor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValor());
    }

    @Override
    public String toString() {
        return String.format("%dª classe", valor.get());
    }
}
