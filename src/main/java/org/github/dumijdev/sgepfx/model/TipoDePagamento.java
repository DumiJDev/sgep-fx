package org.github.dumijdev.sgepfx.model;

public enum TipoDePagamento {
    CERTIFICADO("Certificado"), MATRICULA("Matricula"), PROPINA("Propina"), UNIFORME("Uniforme");

    private final String s;

    TipoDePagamento(String s) {
        this.s = s;
    }

    public String getS() {
        return s;
    }
}