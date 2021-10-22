package org.github.dumijdev.sgepfx.model;

import java.util.Arrays;

public enum Mes {
    JANEIRO("Jan", 1), FEVEREIRO("Fev", 2), MARCO("Mar", 3),
    ABRIL("Abr", 4), MAIO("Mai", 5), JUNHO("Jun", 6),
    JULHO("Jul", 7), AGOSTO("Ago", 8), SETEMBRO("Set", 9),
    OUTUBRO("Out", 10), NOVEMBRO("Nov", 11), DEZEMBRO("Dez", 12);
    private final String mes;

    private final Integer mesInt;

    public String getMes() {
        return mes;
    }

    public Integer getMesInt() {
        return mesInt;
    }

    Mes(String mes, Integer mesInt) {
        this.mes = mes;
        this.mesInt = mesInt;
    }

    public static Mes parseOf(String mes) {
        return Arrays.stream(values()).filter(mes1 -> mes1.mes.equals(mes)).findAny().get();
    }
}
