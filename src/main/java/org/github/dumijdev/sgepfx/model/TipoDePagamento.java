package org.github.dumijdev.sgepfx.model;

public enum TipoDePagamento {
   MATRICULA("Matricula"), PROPINA("Propina"), UNIFORME("Uniforme");

    private final String s;

    public String getS() {
        return s;
    }

    TipoDePagamento(String s) {
        this.s = s;
    }
}