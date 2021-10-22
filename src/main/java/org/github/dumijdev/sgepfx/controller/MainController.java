package org.github.dumijdev.sgepfx.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.github.dumijdev.sgepfx.util.Dialog;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class MainController implements Initializable {

    @FXML // fx:id="apMain"
    private AnchorPane apMain; // Value injected by FXMLLoader

    @FXML // fx:id="jbtAluno"
    private JFXButton jbtAluno; // Value injected by FXMLLoader

    @FXML // fx:id="jbtMatricula"
    private JFXButton jbtMatricula; // Value injected by FXMLLoader

    @FXML // fx:id="jbtPagamento"
    private JFXButton jbtPagamento; // Value injected by FXMLLoader

    @FXML // fx:id="jbtUsuario"
    private JFXButton jbtUsuario; // Value injected by FXMLLoader

    @FXML // fx:id="apContainer"
    private AnchorPane apContainer; // Value injected by FXMLLoader

    @FXML // fx:id="ivLogo"
    private ImageView ivLogo; // Value injected by FXMLLoader

    @FXML // fx:id="lbNomeDaInstituicao"
    private Label lbNomeDaInstituicao; // Value injected by FXMLLoader

    @FXML // fx:id="jbtDefinicoes"
    private JFXButton jbtDefinicoes; // Value injected by FXMLLoader

    @FXML // fx:id="jbtLogout"
    private JFXButton jbtLogout; // Value injected by FXMLLoader


    @FXML
    void handleLogout(ActionEvent event) {
        redesenhaMenu(jbtLogout, listaMenu(jbtLogout));
        Platform.exit();
    }

    @FXML
    void handleAluno(ActionEvent event) {
        redesenhaMenu(jbtAluno, listaMenu(jbtAluno));
        try {
            telaAtual(FXMLLoader.load(getClass().getResource("/fxml/alunos.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleDefinicoes(ActionEvent event) {
        redesenhaMenu(null, listaMenu(null));
    }

    @FXML
    void handleMatricula(ActionEvent event) {
        redesenhaMenu(jbtMatricula, listaMenu(jbtMatricula));
        try {
            telaAtual(FXMLLoader.load(getClass().getResource("/fxml/matricula.fxml")));
        } catch (IOException e) {
            Dialog.erro("Erro", e.getMessage());
        }
    }

    @FXML
    void handlePagamento(ActionEvent event) {
        redesenhaMenu(jbtPagamento, listaMenu(jbtPagamento));
        try {
            telaAtual(FXMLLoader.load(getClass().getResource("/fxml/pagamento-container.fxml")));
        } catch (IOException e) {
            Dialog.erro("Erro", e.getMessage());
        }
    }

    @FXML
    void handleUsuario(ActionEvent event) {
        redesenhaMenu(jbtUsuario, listaMenu(jbtUsuario));
    }

    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    private void redesenhaMenu(JFXButton clicado, List<JFXButton> outros) {
        ativaMenu(clicado);
        outros.forEach(this::desativaMenu);
    }

    private void desativaMenu(JFXButton menu) {
        if (menu != null) {
            menu.getStyleClass().remove("cor-secundaria");
            menu.getStyleClass().add("cor-principal");
        }
    }

    private void ativaMenu(JFXButton menu) {
        if (menu != null) {
            menu.getStyleClass().add("cor-secundaria");
            menu.getStyleClass().remove("cor-principal");
        }
    }

    private List<JFXButton> listaMenu(JFXButton ex) {
        final var menus = new ArrayList<JFXButton>();

        if (ex == null)
            ex = new JFXButton();
        if (ex != jbtAluno)
            menus.add(jbtAluno);
        if (ex != jbtDefinicoes)
            menus.add(jbtDefinicoes);
        if (ex != jbtMatricula)
            menus.add(jbtMatricula);
        if (ex != jbtPagamento)
            menus.add(jbtPagamento);
        if (ex != jbtUsuario)
            menus.add(jbtUsuario);
        if (ex != jbtLogout)
            menus.add(jbtLogout);

        return menus;
    }

    private void telaAtual(Parent parent) {
        AnchorPane.setBottomAnchor(parent, 0.0);
        AnchorPane.setLeftAnchor(parent, 0.0);
        AnchorPane.setRightAnchor(parent, 0.0);
        AnchorPane.setTopAnchor(parent, 0.0);

        apContainer.getChildren().clear();
        apContainer.getChildren().add(parent);
    }
}
