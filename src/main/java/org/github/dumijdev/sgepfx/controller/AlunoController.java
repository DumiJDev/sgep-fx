package org.github.dumijdev.sgepfx.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.github.dumijdev.sgepfx.SGESpringFX;
import org.github.dumijdev.sgepfx.model.builder.AlunoBuilder;
import org.github.dumijdev.sgepfx.model.property.AlunoProperty;
import org.github.dumijdev.sgepfx.repository.AlunoRepository;
import org.github.dumijdev.sgepfx.util.Converte;
import org.github.dumijdev.sgepfx.util.javafx.Dialog;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
public class AlunoController implements Initializable {

    //Minhas variáveis
    private static AlunoRepository ar;
    private static boolean aberta;
    private static TableView<AlunoProperty> tvGlobal;
    private static Stage s1;

    @FXML // fx:id="jtfPesquisa"
    private JFXTextField jtfPesquisa; // Value injected by FXMLLoader
    @FXML // fx:id="jcbNome"
    private JFXCheckBox jcbNome; // Value injected by FXMLLoader
    @FXML // fx:id="jcbBI"
    private JFXCheckBox jcbBI; // Value injected by FXMLLoader
    @FXML // fx:id="jbtNovo"
    private JFXButton jbtNovo; // Value injected by FXMLLoader
    @FXML // fx:id="tvAlunos"
    private TableView<AlunoProperty> tvAlunos; // Value injected by FXMLLoader
    @FXML // fx:id="tcNome"
    private TableColumn<AlunoProperty, String> tcNome; // Value injected by FXMLLoader
    @FXML // fx:id="tcIdade"
    private TableColumn<AlunoProperty, Long> tcIdade; // Value injected by FXMLLoader
    @FXML // fx:id="tcPagamento"
    private TableColumn<AlunoProperty, String> tcPagamento; // Value injected by FXMLLoader
    @FXML // fx:id="tcDataDeInscricao"
    private TableColumn<AlunoProperty, String> tcDataDeInscricao; // Value injected by FXMLLoader
    @FXML // fx:id="jtfnome"
    private JFXTextField jtfnome; // Value injected by FXMLLoader
    @FXML // fx:id="jtfNomeFamilia"
    private JFXTextField jtfNomeFamilia; // Value injected by FXMLLoader
    @FXML // fx:id="jtfNomePai"
    private JFXTextField jtfNomePai; // Value injected by FXMLLoader
    @FXML // fx:id="jtfNomeMae"
    private JFXTextField jtfNomeMae; // Value injected by FXMLLoader
    @FXML // fx:id="jdpNasc"
    private JFXDatePicker jdpNasc; // Value injected by FXMLLoader
    @FXML // fx:id="jtfBI"
    private JFXTextField jtfBI; // Value injected by FXMLLoader
    @FXML // fx:id="jbtRegistrar"
    private JFXButton jbtRegistrar; // Value injected by FXMLLoader

    //Novo-aluno fxml
    @FXML
    void handleRegistrar(ActionEvent event) {
        var valido = !(jtfnome.getText().isEmpty() || jtfnome.getText().isBlank())
                && !(jtfNomeFamilia.getText().isEmpty() || jtfNomeFamilia.getText().isBlank())
                && !(jtfNomePai.getText().isEmpty() || jtfNomePai.getText().isBlank())
                && !(jtfNomeMae.getText().isEmpty() || jtfNomeMae.getText().isBlank())
                && !(jtfBI.getText().isEmpty() || jtfBI.getText().isBlank())
                && jtfBI.getText().matches("\\d{9}[A-Za-z]{2}\\d{3}")
                && jdpNasc.getValue().isBefore(LocalDate.now());

        if (valido) {
            var aluno = ar.save(AlunoBuilder
                    .novaInstancia()
                    .comAnoDaInscricao(LocalDate.now())
                    .comBi(jtfBI.getText())
                    .comDataDeNascimento(jdpNasc.getValue())
                    .comNome(jtfnome.getText())
                    .comNomeDoPai(jtfNomePai.getText())
                    .comNomeDaMae(jtfNomeMae.getText())
                    .constroi());

            var clicouOk = Dialog.informacao("Novo aluno",
                    String.format("Aluno %s adicionado com sucesso!",
                            aluno.getNome()));
            if (clicouOk) {
                s1.close();
                atualizaTabela();
                aberta = false;
            }

        } else
            Dialog.erro("Campo mal preenchido",
                    "Um ou mais campos não preenchidos corretamente");
    }

    @FXML
    void handleNovo(ActionEvent event) {
        try {
            if (!aberta) {
                Parent p = FXMLLoader.load(getClass().getResource("/fxml/novo-aluno.fxml"));
                Scene s = new Scene(p);
                s1 = new Stage();
                s1.setScene(s);
                s1.setResizable(false);
                s1.setTitle("Novo aluno");
                s1.setOnCloseRequest(e -> {
                    aberta = false;
                    atualizaTabela();
                });
                s1.show();
                aberta = true;
            } else s1.toFront();
        } catch (IOException e) {
            Dialog.erro("Erro", e.getMessage());
        }
    }

    @FXML
    void handlePesquisa(KeyEvent event) {
        if ("".equals(jtfPesquisa.getText()))
            atualizaTabela();
        else {
            tvAlunos.getItems().clear();
            var aux = jcbNome.isSelected() ?
                    ar.buscaPeloNomeContendo(jtfPesquisa.getText()) :
                    ar.buscaPeloBIContendo(jtfPesquisa.getText());

            var resultados = aux.stream().map(Converte::converte)
                    .collect(Collectors.toList());

            tvGlobal.getItems().addAll(resultados);
        }
    }

    @FXML
    void handleBI(ActionEvent event) {
        jcbNome.setSelected(false);
    }

    @FXML
    void handleNome(ActionEvent event) {
        jcbBI.setSelected(false);
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

        if (jcbBI != null) {
            //Escolha do campo a apresentar
            tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            tcIdade.setCellValueFactory(new PropertyValueFactory<>("idade"));
            tcPagamento.setCellValueFactory(new PropertyValueFactory<>("pagamento"));
            tcDataDeInscricao.setCellValueFactory(new PropertyValueFactory<>("dataDeInscricao"));
            tvGlobal = tvAlunos;
            aberta = false;
            //Rep
            ar = SGESpringFX.getAR();
            atualizaTabela();
        }

    }

    private void atualizaTabela() {
        tvGlobal.getItems().clear();
        ar.findAll().forEach(aluno -> tvGlobal.getItems().add(Converte.converte(aluno)));
    }
}
