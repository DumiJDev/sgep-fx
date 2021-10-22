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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.github.dumijdev.sgepfx.SGESpringFX;
import org.github.dumijdev.sgepfx.model.Aluno;
import org.github.dumijdev.sgepfx.model.Mes;
import org.github.dumijdev.sgepfx.model.Pagamento;
import org.github.dumijdev.sgepfx.model.TipoDePagamento;
import org.github.dumijdev.sgepfx.model.builder.PagamentoBuilder;
import org.github.dumijdev.sgepfx.model.builder.PropinaBuilder;
import org.github.dumijdev.sgepfx.model.property.AlunoProperty;
import org.github.dumijdev.sgepfx.model.property.ClasseProperty;
import org.github.dumijdev.sgepfx.model.property.MesProperty;
import org.github.dumijdev.sgepfx.model.property.PagamentoProperty;
import org.github.dumijdev.sgepfx.repository.PagamentoRepository;
import org.github.dumijdev.sgepfx.repository.PropinaRepository;
import org.github.dumijdev.sgepfx.util.Converte;
import org.github.dumijdev.sgepfx.util.Dialog;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.github.dumijdev.sgepfx.util.Converte.converte;
import static org.github.dumijdev.sgepfx.util.Converte.converteTodos;

@Component
public class PagamentoController implements Initializable {

    //Minhas variáveis
    private static AnchorPane apContainerGlobal;

    @FXML // fx:id="apContainer"
    private AnchorPane apContainer; // Value injected by FXMLLoader

    @FXML // fx:id="hbMain"
    private HBox hbMain; // Value injected by FXMLLoader

    @FXML // fx:id="jbtMatricula"
    private JFXButton jbtMatricula; // Value injected by FXMLLoader

    @FXML // fx:id="jbtOutro"
    private JFXButton jbtOutro; // Value injected by FXMLLoader

    @FXML // fx:id="jbtPropina"
    private JFXButton jbtPropina; // Value injected by FXMLLoader

    @FXML // fx:id="jbtUniforme"
    private JFXButton jbtUniforme; // Value injected by FXMLLoader

    @FXML // fx:id="jbtVoltar"
    private JFXButton jbtVoltar; // Value injected by FXMLLoader

    //Pagamentos
    @FXML // fx:id="jtfPesquisa"
    private JFXTextField jtfPesquisa; // Value injected by FXMLLoader

    @FXML // fx:id="jbtNovo"
    private JFXButton jbtNovo; // Value injected by FXMLLoader

    @FXML // fx:id="jcbCertificado"
    private JFXCheckBox jcbCertificado; // Value injected by FXMLLoader

    @FXML // fx:id="jcbPropina"
    private JFXCheckBox jcbPropina; // Value injected by FXMLLoader

    @FXML // fx:id="jcbUniforme"
    private JFXCheckBox jcbUniforme; // Value injected by FXMLLoader

    @FXML // fx:id="tvPagamento"
    private TableView<PagamentoProperty> tvPagamento; // Value injected by FXMLLoader

    @FXML // fx:id="tcNome"
    private TableColumn<PagamentoProperty, String> tcNome; // Value injected by FXMLLoader

    @FXML // fx:id="tcTipoDePagamento"
    private TableColumn<PagamentoProperty, String> tcTipoDePagamento; // Value injected by FXMLLoader

    @FXML // fx:id="tcValor"
    private TableColumn<PagamentoProperty, String> tcValor; // Value injected by FXMLLoader

    @FXML // fx:id="tcDataDePagamento"
    private TableColumn<PagamentoProperty, String> tcDataDePagamento; // Value injected by FXMLLoader
    private static PagamentoRepository pr;
    private static TableView<PagamentoProperty> tvPagamentoGlobal;

    @FXML
    void handleNovo(ActionEvent event) {
        try {
            var p = (Parent) FXMLLoader.load(getClass().getResource("/fxml/pagamento.fxml"));
            telaPrincipal(p);
        } catch (IOException e) {
            Dialog.erro("Erro", e.getMessage());
        }
    }

    @FXML
    void handleCertificados(ActionEvent event) {

    }

    @FXML
    void handlePesquisa(KeyEvent event) {

    }

    @FXML
    void handlePropinas(ActionEvent event) {

    }

    @FXML
    void handleUniformes(ActionEvent event) {

    }
    //Pagamentos fim

    //Propina
    @FXML // fx:id="jcbAlunoPropina"
    private JFXComboBox<AlunoProperty> jcbAlunoPropina; // Value injected by FXMLLoader

    @FXML // fx:id="jcbClassePropina"
    private JFXComboBox<ClasseProperty> jcbClassePropina; // Value injected by FXMLLoader

    @FXML // fx:id="jcbMesPropina"
    private JFXComboBox<MesProperty> jcbMesPropina; // Value injected by FXMLLoader

    @FXML // fx:id="jtfValorPropina"
    private JFXTextField jtfValorPropina; // Value injected by FXMLLoader

    @FXML // fx:id="jbtRegistrarPropina"
    private JFXButton jbtRegistrarPropina; // Value injected by FXMLLoader
    private static Stage s1;
    private static boolean aberto;
    private static PropinaRepository pr1;

    @FXML
    void handleRegistrarPropina(ActionEvent event) {
        var p = pr1.save(PropinaBuilder.novaInstancia()
                .comAluno(converte(jcbAlunoPropina.getSelectionModel().getSelectedItem()))
                .comClasse(jcbClassePropina.getSelectionModel().getSelectedItem().getValor())
                .comMes(Mes.parseOf(jcbMesPropina.getSelectionModel().getSelectedItem().getValor()))
                .comPagamento(
                        PagamentoBuilder.novaInstancia()
                                .comAluno(converte(jcbAlunoPropina.getSelectionModel().getSelectedItem()))
                                .comDataDePagamento(LocalDateTime.now())
                                .comTipoDePagamento(TipoDePagamento.PROPINA)
                                .comValorPago(new BigDecimal(jtfValorPropina.getText()))
                                .constroi()
                ).constroi());
        Dialog.informacao("Propina",
                String.format("Propina do(a) %s para %dª classe feita com sucesso!",
                        p.getAluno().getNomeProprio(), p.getClasse()));
        s1.close();
        aberto = false;
    }
    //Propina fim

    @FXML
    void handleMatricula(ActionEvent event) {

    }

    @FXML
    void handleOutro(ActionEvent event) {

    }

    @FXML
    void handlePropina(ActionEvent event) {
        try {
            Parent p = FXMLLoader.load(getClass().getResource("/fxml/pagamento-propina.fxml"));
            Scene s = new Scene(p);
            s1 = new Stage();
            s1.setTitle("Propina");
            s1.setResizable(false);
            s1.setOnCloseRequest(event1 -> aberto = false);
            s1.setScene(s);
            s1.show();
        } catch (IOException e) {
            Dialog.erro("Erro", e.getMessage());
        }
    }

    @FXML
    void handleUniforme(ActionEvent event) {

    }

    @FXML
    void handleVoltar(ActionEvent event) {
        try {
            telaPrincipal(FXMLLoader.load(getClass().getResource("/fxml/pagamentos.fxml")));
        } catch (IOException e) {
            Dialog.erro("Erro", e.getMessage());
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
        if (apContainer != null) {
            apContainerGlobal = apContainer;
            aberto = false;
            try {
                telaPrincipal(FXMLLoader.load(getClass().getResource("/fxml/pagamentos.fxml")));
            } catch (IOException e) {
                Dialog.erro("Erro", e.getMessage());
            }
        }

        if (jcbAlunoPropina != null) {
            var ar = SGESpringFX.getAR();
            jcbAlunoPropina.getItems().addAll(converteTodos(ar.buscaTodos().toArray(Aluno[]::new)));
            jcbClassePropina.getItems()
                    .addAll(IntStream.rangeClosed(1, 9)
                            .mapToObj(ClasseProperty::new).collect(Collectors.toList()));
            jcbMesPropina.getItems()
                    .addAll(Arrays.stream(Mes.values())
                            .map(MesProperty::new).collect(Collectors.toList()));
        }

        if (jcbCertificado != null) {
            pr1 = SGESpringFX.getPr1();
            pr = SGESpringFX.getPr();
            tvPagamentoGlobal = tvPagamento;
            tcNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
            tcDataDePagamento.setCellValueFactory(new PropertyValueFactory<>("dataDePagamento"));
            tcTipoDePagamento.setCellValueFactory(new PropertyValueFactory<>("tipoDePagamento"));
            tcValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
            atualizaTabela();
        }
    }

    private void telaPrincipal(Parent p) {
        AnchorPane.setTopAnchor(p, 0.0);
        AnchorPane.setBottomAnchor(p, 0.0);
        AnchorPane.setLeftAnchor(p, 0.0);
        AnchorPane.setRightAnchor(p, 0.0);

        apContainerGlobal.getChildren().clear();
        apContainerGlobal.getChildren().add(p);
    }

    private void atualizaTabela() {
        tvPagamentoGlobal.getItems().clear();
        tvPagamentoGlobal.getItems()
                .addAll(Converte.converteTodos(pr.buscaTodos().toArray(Pagamento[]::new)));
    }
}
