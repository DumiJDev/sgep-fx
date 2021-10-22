package org.github.dumijdev.sgepfx.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub

    }

}
