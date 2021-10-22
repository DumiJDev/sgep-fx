package org.github.dumijdev.sgepfx.util;

import javafx.scene.control.Alert;

public class Dialog {
    private static String cab = "Mensagem: ";

    public static boolean aviso(String titulo, String mensagem) {
        var alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText("AVISO!!!");
        alert.setContentText(mensagem);
        return alert.showAndWait().get().getButtonData().isDefaultButton();
    }

    public static boolean erro(String titulo, String mensagem) {
        var alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText("ERRO!!!");
        alert.setContentText(mensagem);
        return alert.showAndWait().get().getButtonData().isDefaultButton();
    }

    public static boolean informacao(String titulo, String mensagem) {
        var alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText("Informação");
        alert.setContentText(mensagem);

        return alert.showAndWait().get().getButtonData().isDefaultButton();
    }
}
