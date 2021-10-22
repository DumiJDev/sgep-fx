package org.github.dumijdev.sgepfx.model;

public enum Acesso {
    ADMIN("admin"), USER("user");
    private final String acesso;

    Acesso(String acesso) {
        this.acesso = acesso;
    }

    public String getAcesso() {
        return acesso;
    }
}
