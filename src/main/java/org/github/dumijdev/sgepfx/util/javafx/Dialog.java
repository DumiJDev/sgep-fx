package org.github.dumijdev.sgepfx.util.javafx;

import javafx.scene.control.Alert;

import java.util.Objects;

public class Dialog {
    private static final String cab = "Mensagem: ";

    public static boolean aviso(String titulo, String mensagem) {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText("AVISO!!!");
        alert.setContentText(mensagem);
        return Objects.requireNonNull(alert.showAndWait().orElse(null)).getButtonData().isDefaultButton();
    }

    public static void erro(String titulo, String mensagem) {
        var alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText("ERRO!!!");
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static boolean informacao(String titulo, String mensagem) {
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText("Informação");
        alert.setContentText(mensagem);

        return Objects.requireNonNull(alert.showAndWait().orElse(null)).getButtonData().isDefaultButton();
    }
}
