package org.github.dumijdev.sgepfx.controller;

import com.jfoenix.controls.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.github.dumijdev.sgepfx.SGESpringFX;
import org.github.dumijdev.sgepfx.model.TipoDePagamento;
import org.github.dumijdev.sgepfx.model.builder.MatriculaBuilder;
import org.github.dumijdev.sgepfx.model.builder.PagamentoBuilder;
import org.github.dumijdev.sgepfx.model.property.AlunoProperty;
import org.github.dumijdev.sgepfx.model.property.ClasseProperty;
import org.github.dumijdev.sgepfx.model.property.MatriculaProperty;
import org.github.dumijdev.sgepfx.model.property.TipoDePagamentoProperty;
import org.github.dumijdev.sgepfx.repository.MatriculaRepository;
import org.github.dumijdev.sgepfx.util.Converte;
import org.github.dumijdev.sgepfx.util.Dialog;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.github.dumijdev.sgepfx.util.Converte.converte;

@Component
public class MatriculaController implements Initializable {

    //Minhas variáveis
    private static MatriculaRepository mr;
    private static TableView<MatriculaProperty> tvMatriculasGlobal;
    private static Stage st;
    private static boolean aberto;

    @FXML // fx:id="jtfPesquisa"
    private JFXTextField jtfPesquisa; // Value injected by FXMLLoader
    @FXML // fx:id="jbtNovo"
    private JFXButton jbtNovo; // Value injected by FXMLLoader
    @FXML // fx:id="jrbNome"
    private JFXRadioButton jrbNome; // Value injected by FXMLLoader
    @FXML // fx:id="pesquisa"
    private ToggleGroup pesquisa; // Value injected by FXMLLoader
    @FXML // fx:id="jrbClasse"
    private JFXRadioButton jrbClasse; // Value injected by FXMLLoader
    @FXML // fx:id="jcbData"
    private JFXRadioButton jrbAno; // Value injected by FXMLLoader
    @FXML // fx:id="tvAlunos"
    private TableView<MatriculaProperty> tvMatriculas; // Value injected by FXMLLoader
    @FXML // fx:id="tcNome"
    private TableColumn<MatriculaProperty, String> tcNome; // Value injected by FXMLLoader
    @FXML // fx:id="tcClasse"
    private TableColumn<MatriculaProperty, String> tcClasse; // Value injected by FXMLLoader
    @FXML // fx:id="tcPagamento"
    private TableColumn<MatriculaProperty, String> tcPagamento; // Value injected by FXMLLoader
    @FXML // fx:id="tcDataDaMatricula"
    private TableColumn<MatriculaProperty, String> tcDataDaMatricula; // Value injected by FXMLLoader
    //Nova-matricula
    @FXML
    private JFXComboBox<AlunoProperty> jcbAluno;
    @FXML
    private JFXTextField jtfValor;
    @FXML
    private JFXComboBox<TipoDePagamentoProperty> jcbTipoDePagamento;
    @FXML
    private JFXComboBox<ClasseProperty> jcbClasse;
    @FXML
    private JFXButton jbtRegistrar;

    @FXML
    void handleRegistrar(ActionEvent event) {
        final var m = mr.save(MatriculaBuilder.novaInstancia()
                .comAluno(converte(jcbAluno.getSelectionModel().getSelectedItem()))
                .comClasse(jcbClasse.getSelectionModel().getSelectedItem().getValor())
                .comData(LocalDate.now())
                .comPagamento(
                        PagamentoBuilder.novaInstancia()
                                .comAluno(converte(jcbAluno.getSelectionModel().getSelectedItem()))
                                .comDataDePagamento(LocalDateTime.now())
                                .comTipoDePagamento(TipoDePagamento.MATRICULA)
                                .comValorPago(new BigDecimal((jtfValor.getText()).trim()))
                                .constroi()
                )
                .constroi());

        Dialog.informacao("Matricula",
                String.format("Matricula do aluno %s para %dª classe feita com sucesso!",
                        m.getAluno().getNomeProprio(), m.getClasse()));
        st.close();
        aberto = false;

        atualizaTabela();
    }

    @FXML
    void handleNovo(ActionEvent event) {
        try {
            if (!aberto) {
                Parent p = FXMLLoader.load(getClass().getResource("/fxml/nova-matricula.fxml"));
                Scene s = new Scene(p);
                st = new Stage();
                st.setTitle("Nova matricula");
                st.setResizable(false);
                st.setOnCloseRequest(event1 -> aberto = false);
                st.setScene(s);
                st.show();
                aberto = true;
            }
        } catch (IOException e) {
            Dialog.erro("Erro", e.getMessage());
        }
    }

    @FXML
    void handlePesquisa(ActionEvent event) {

    }

    @FXML
    void handlePesquisaPorTexto(KeyEvent event) {
        if (jtfPesquisa.getText().isEmpty()) {
                atualizaTabela();
        } else if (jrbNome.isSelected()) {
            var aux = mr.buscaPeloNomeProprio(jtfPesquisa.getText());

            var resultados = aux.stream()
                    .map(Converte::converte).collect(Collectors.toList());

            tvMatriculasGlobal.getItems().clear();
            tvMatriculasGlobal.getItems().addAll(resultados);
        } else if (jrbClasse.isSelected()){
            var aux = mr.buscaPelaClasse(Integer.parseInt(jtfPesquisa.getText()));

            var resultados = aux.stream()
                    .map(Converte::converte).collect(Collectors.toList());

            tvMatriculasGlobal.getItems().clear();
            tvMatriculasGlobal.getItems().addAll(resultados);
        } else if (jrbAno.isSelected()) {
            var aux = mr.buscaEntre(LocalDate.of(Integer.parseInt(jtfPesquisa.getText()), 1, 1),
                    LocalDate.of(Integer.parseInt(jtfPesquisa.getText()), 12, 31));

            var resultados = aux.stream()
                    .map(Converte::converte).collect(Collectors.toList());

            tvMatriculasGlobal.getItems().clear();
            tvMatriculasGlobal.getItems().addAll(resultados);
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
        mr = SGESpringFX.getMR();
        if (tvMatriculas != null) {
            tvMatriculasGlobal = tvMatriculas;

            tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            tcClasse.setCellValueFactory(new PropertyValueFactory<>("classe"));
            tcPagamento.setCellValueFactory(new PropertyValueFactory<>("valorDoPagamento"));
            tcDataDaMatricula.setCellValueFactory(new PropertyValueFactory<>("dataDePagamento"));

            aberto = false;
            atualizaTabela();
        }

        if (jcbTipoDePagamento != null) {
            SGESpringFX.getAR().findAll().forEach(aluno -> jcbAluno.getItems().add(converte(aluno)));
            jcbAluno.getSelectionModel().selectFirst();
            jcbTipoDePagamento.getItems().add(new TipoDePagamentoProperty(TipoDePagamento.MATRICULA));
            jcbTipoDePagamento.getSelectionModel().selectFirst();
            IntStream.rangeClosed(1, 9)
                    .forEach(value -> jcbClasse.getItems().add(new ClasseProperty(value)));
        }
    }

    private void atualizaTabela() {
        tvMatriculasGlobal.getItems().clear();
        mr.buscaTodos().forEach(m -> tvMatriculasGlobal.getItems().add(converte(m)));
        tvMatriculasGlobal.getItems().sort(Comparator.comparing(MatriculaProperty::getNome));
    }
}
