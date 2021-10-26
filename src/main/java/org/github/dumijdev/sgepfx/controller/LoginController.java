package org.github.dumijdev.sgepfx.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import org.github.dumijdev.sgepfx.LoginApp;
import org.github.dumijdev.sgepfx.SGEPFXAPP;
import org.github.dumijdev.sgepfx.SGESpringFX;
import org.github.dumijdev.sgepfx.model.Acesso;
import org.github.dumijdev.sgepfx.model.builder.FuncionarioBuilder;
import org.github.dumijdev.sgepfx.model.builder.UsuarioBuilder;
import org.github.dumijdev.sgepfx.repository.UsuarioRepository;
import org.github.dumijdev.sgepfx.util.javafx.Dialog;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Component
public class LoginController implements Initializable {

    //Minhas variáveis
    private static UsuarioRepository ur;
    @FXML // fx:id="tfLoginUsuario"
    private JFXTextField tfLoginUsuario; // Value injected by FXMLLoader
    @FXML // fx:id="cbManterAtivo"
    private JFXCheckBox cbManterAtivo; // Value injected by FXMLLoader
    @FXML // fx:id="btLogin"
    private JFXButton btLogin; // Value injected by FXMLLoader
    @FXML // fx:id="btEsqueceu"
    private JFXButton btEsqueceu; // Value injected by FXMLLoader
    @FXML // fx:id="pfPalavraPasseUsuario"
    private JFXPasswordField pfPalavraPasseUsuario; // Value injected by FXMLLoader

    @FXML
    void handleEsqueceu(ActionEvent event) {

    }

    @FXML
    void handleLogin(ActionEvent event) {
        final var u = ur.login(tfLoginUsuario.getText(), pfPalavraPasseUsuario.getText());
        System.out.println(u);

        if (u != null) {
            tfLoginUsuario.setText(cbManterAtivo.isSelected() ? tfLoginUsuario.getText() : "");
            pfPalavraPasseUsuario.setText(cbManterAtivo.isSelected() ? pfPalavraPasseUsuario.getText() : "");
            UsuarioController.setU(u);
            MainController.setU(u);
            try {
                new SGEPFXAPP().start(new Stage());
                LoginApp.getLocalStage().hide();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else Dialog.erro("Login", "Usuário não encontrado!");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        ur = SGESpringFX.getUr();
        if (ur.buscaTodos().size() == 0) {
            var u1 = ur.salva(
                    UsuarioBuilder.novaInstancia()
                            .comAcesso(Acesso.ADMIN)
                            .comLogin("dumij")
                            .comSenha("dumij")
                            .comFuncionario(
                                    FuncionarioBuilder.novaInstancia()
                                            .comBi("123456789LA123")
                                            .comDataDeNascimento(LocalDate.now())
                                            .comNomeDaMae("")
                                            .comNomeDoPai("")
                                            .comTelefone("")
                                            .comNome("admin")
                                            .comImagem("")
                                            .constroi()
                            )
                            .constroi()
            );

            System.err.printf("login: %s\npass: %s%n", u1.getLogin(), u1.getSenha());
        }
    }

}
