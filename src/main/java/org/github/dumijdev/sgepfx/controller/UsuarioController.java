package org.github.dumijdev.sgepfx.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.github.dumijdev.sgepfx.SGESpringFX;
import org.github.dumijdev.sgepfx.exceprion.DadoInvalidoException;
import org.github.dumijdev.sgepfx.model.Acesso;
import org.github.dumijdev.sgepfx.model.Usuario;
import org.github.dumijdev.sgepfx.model.builder.FuncionarioBuilder;
import org.github.dumijdev.sgepfx.model.builder.UsuarioBuilder;
import org.github.dumijdev.sgepfx.model.property.AcessoProperty;
import org.github.dumijdev.sgepfx.model.property.UsuarioProperty;
import org.github.dumijdev.sgepfx.repository.UsuarioRepository;
import org.github.dumijdev.sgepfx.util.cripto.DPCripto;
import org.github.dumijdev.sgepfx.util.javafx.Dialog;
import org.github.dumijdev.sgepfx.util.javafx.Render;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Component
public class UsuarioController implements Initializable {

    //Minhas variáveis
    private static UsuarioRepository ur;
    private static Usuario u;
    private static TableView<UsuarioProperty> tvUsuario;
    @FXML // fx:id="ivFoto"
    private ImageView ivFoto; // Value injected by FXMLLoader
    @FXML // fx:id="lbCaminho"
    private Label lbCaminho; // Value injected by FXMLLoader
    @FXML // fx:id="jbtEscolheFoto"
    private JFXButton jbtEscolheFoto; // Value injected by FXMLLoader
    @FXML // fx:id="jtfUsername"
    private JFXTextField jtfUsername; // Value injected by FXMLLoade
    @FXML // fx:id="lbUsername"
    private Label lbUsername; // Value injected by FXMLLoader
    @FXML // fx:id="lbUserPhone"
    private Label lbUserPhone; // Value injected by FXMLLoader
    @FXML // fx:id="lbAcesso"
    private Label lbAcesso; // Value injected by FXMLLoader
    @FXML // fx:id="jbtNovo"
    private JFXButton jbtNovo; // Value injected by FXMLLoader
    @FXML // fx:id="jtfNome"
    private JFXTextField jtfNome; // Value injected by FXMLLoader
    @FXML // fx:id="jtfNomeDoPai"
    private JFXTextField jtfNomeDoPai; // Value injected by FXMLLoader
    @FXML // fx:id="jtfNomeMae"
    private JFXTextField jtfNomeMae; // Value injected by FXMLLoader
    @FXML // fx:id="jtfTelefone"
    private JFXTextField jtfTelefone; // Value injected by FXMLLoader
    @FXML // fx:id="jdpNascimento"
    private JFXDatePicker jdpNascimento; // Value injected by FXMLLoader
    @FXML // fx:id="jtfBI"
    private JFXTextField jtfBI; // Value injected by FXMLLoader
    @FXML // fx:id="jcbAcesso"
    private JFXComboBox<AcessoProperty> jcbAcesso; // Value injected by FXMLLoader
    @FXML // fx:id="jtfPassword"
    private JFXTextField jtfPassword; // Value injected by FXMLLoader
    @FXML // fx:id="jbtEditar"
    private JFXButton jbtEditar; // Value injected by FXMLLoader
    @FXML // fx:id="jbtSalvar"
    private JFXButton jbtSalvar; // Value injected by FXMLLoader
    @FXML // fx:id="tvFuncionarios"
    private TableView<UsuarioProperty> tvFuncionarios; // Value injected by FXMLLoader
    @FXML // fx:id="tcUserName"
    private TableColumn<UsuarioProperty, String> tcUserName; // Value injected by FXMLLoader
    @FXML // fx:id="tcNome"
    private TableColumn<UsuarioProperty, String> tcNome; // Value injected by FXMLLoader
    @FXML // fx:id="tcTelefone"
    private TableColumn<UsuarioProperty, String> tcTelefone; // Value injected by FXMLLoader
    @FXML // fx:id="tcAcesso"
    private TableColumn<UsuarioProperty, String> tcAcesso; // Value injected by FXMLLoader
    @FXML // fx:id="jbtSalvarNovo"
    private JFXButton jbtSalvarNovo; // Value injected by FXMLLoader
    private boolean editando;
    private String imagem;

    public static void setU(Usuario u) {
        UsuarioController.u = u;
    }

    @FXML
    void handleEscolheFoto(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("Imagem", "png", "jpg"));
        var imagem = fc.showOpenDialog(null);
        if (lbCaminho != null)
        lbCaminho.setText(imagem.getAbsolutePath());
        try {
            ivFoto.setImage(new Image(new FileInputStream(imagem.getAbsolutePath())));
            this.imagem = imagem.getAbsolutePath();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleSalvarNovo(ActionEvent event) {
        try {
            validaAtualizacao();
            ur.salva(
                    UsuarioBuilder
                            .novaInstancia()
                            .comAcesso(
                                    Arrays.stream(Acesso.values())
                                            .filter(a -> a.getAcesso()
                                                    .equalsIgnoreCase(
                                                            jcbAcesso
                                                                    .getSelectionModel()
                                                                    .getSelectedItem()
                                                                    .getValor()))
                                            .findFirst().orElse(null))
                            .comFuncionario(
                                    FuncionarioBuilder.novaInstancia()
                                            .comBi(jtfBI.getText())
                                            .comDataDeNascimento(jdpNascimento.getValue())
                                            .comNome(jtfNome.getText())
                                            .comNomeDaMae(jtfNomeMae.getText())
                                            .comNomeDoPai(jtfNomeDoPai.getText())
                                            .comTelefone(jtfTelefone.getText())
                                            .comImagem(imagem)
                                            .constroi()
                            )
                            .comLogin(jtfUsername.getText())
                            .comSenha(jtfPassword.getText())
                            .constroi()
            );
            estado(false);

        } catch (DadoInvalidoException ex) {
            Dialog.erro("Erro", ex.getMessage());
        }
        atualizaTabela();
    }

    @FXML
    void handleEditar(ActionEvent event) {
        if (editando) {
            editando = false;
            estado(false);
        } else {
            editando = true;
            estado(true);
        }
    }

    @FXML
    void handleNovo(ActionEvent event) {
        Render.setP("/fxml/novo-funcionario.fxml");
        Render.setS1(new Stage());
        Render.setTitle("Novo funcionário");
        Render.setResizable(false);
        estado(true);
        Render.renderFX();
    }

    @FXML
    void handleSalvar(ActionEvent event) {
        try {
            validaAtualizacao();
            System.out.println(u.getSenha());
            if (!(jtfPassword.getText().isEmpty() || jtfPassword.getText().isBlank()))
                u.setSenha(DPCripto.codifica(u.getLogin(), jtfPassword.getText()));
            System.out.println(u.getSenha());
            u.getFuncionario().setBi(jtfBI.getText());
            u.getFuncionario().setNome(jtfNome.getText());
            u.getFuncionario().setTelefone(jtfTelefone.getText());
            u.getFuncionario().setNomeDaMae(jtfNomeMae.getText());
            u.getFuncionario().setNomeDoPai(jtfNomeDoPai.getText());
            if (!jdpNascimento.getEditor().getText().isEmpty())
                u.getFuncionario().setDataDeNascimento(jdpNascimento.getValue());
            if (imagem != null)
            u.getFuncionario().setImagem(imagem);
            Dialog.informacao("Atualização de dados",
                    format("A pedido de %s.\nDados alterados com sucesso!",
                            ur.save(u).getFuncionario().getNome()));
            estado(false);
            atualizaTabela();
        } catch (DadoInvalidoException ex) {
            Dialog.erro("Erro", ex.getMessage());
        }
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
        if (tvFuncionarios != null) {
            ur = SGESpringFX.getUr();
            editando = false;
            tvUsuario = tvFuncionarios;
            tcUserName.setCellValueFactory(new PropertyValueFactory<>("username"));
            tcTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
            tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            tcAcesso.setCellValueFactory(new PropertyValueFactory<>("acesso"));

            atualizaTabela();
        }

        if (lbAcesso != null) {
            lbAcesso.setText(u.getAcesso().getAcesso());
            lbUsername.setText(u.getLogin());
            lbUserPhone.setText(u.getFuncionario().getTelefone());
            try {
                if (!u.getFuncionario().getImagem().isEmpty()) {
                    Path p = Paths.get(u.getFuncionario().getImagem());
                    if (Files.exists(p))
                        ivFoto.setImage(new Image(new FileInputStream(u.getFuncionario().getImagem())));
                }
            } catch (FileNotFoundException e) {
                Dialog.erro("Erro", e.getMessage());
                e.printStackTrace();
            }

            jtfBI.setText(u.getFuncionario().getBi());
            jtfNome.setText(u.getFuncionario().getNome());
            jtfNomeDoPai.setText(u.getFuncionario().getNomeDoPai());
            jtfNomeMae.setText(u.getFuncionario().getNomeDaMae());
            jtfTelefone.setText(u.getFuncionario().getTelefone());
        }

        if (jcbAcesso != null) {
            Arrays.stream(Acesso.values()).map(AcessoProperty::new).forEach(jcbAcesso.getItems()::add);
            estado(true);
        }
    }

    private void atualizaTabela() {
        tvUsuario.getItems().clear();
        ur.buscaTodos()
                .stream()
                //.filter(usuario -> !usuario.equals(u))
                .map(usuario -> new UsuarioProperty(
                        usuario.getId(),
                        usuario.getFuncionario().getTelefone(),
                        usuario.getFuncionario().getNome(),
                        usuario.getLogin(),
                        usuario.getAcesso().getAcesso()))
                .collect(Collectors.toList())
                .forEach(tvUsuario.getItems()::add);
    }

    private void validaAtualizacao() throws DadoInvalidoException {
        if (!jtfBI.getText().matches("\\d{9}[A-Za-z]{2}\\d{3}") ||
                jtfNome.getText().isEmpty() ||
                !jtfTelefone.getText().matches("\\d{9}"))
            throw new DadoInvalidoException("Dados inválidos");
        else if (jtfUsername != null)
            if (jtfUsername.getText().isEmpty())
                throw new DadoInvalidoException("Dados inválidos");
    }

    private void estado(boolean e) {
        if (jtfUsername != null)
            jtfUsername.setEditable(e);
        jtfBI.setEditable(e);
        jtfTelefone.setEditable(e);
        jtfNome.setEditable(e);
        jtfNomeDoPai.setEditable(e);
        jtfNomeMae.setEditable(e);
        jtfPassword.setEditable(e);
        jbtEscolheFoto.setDisable(!e);
        jdpNascimento.setDisable(!e);
    }
}
