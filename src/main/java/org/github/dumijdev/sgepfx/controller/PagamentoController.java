package org.github.dumijdev.sgepfx.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.github.dumijdev.sgepfx.SGESpringFX;
import org.github.dumijdev.sgepfx.model.*;
import org.github.dumijdev.sgepfx.model.builder.PagamentoBuilder;
import org.github.dumijdev.sgepfx.model.builder.PropinaBuilder;
import org.github.dumijdev.sgepfx.model.property.*;
import org.github.dumijdev.sgepfx.repository.PagamentoRepository;
import org.github.dumijdev.sgepfx.repository.PropinaRepository;
import org.github.dumijdev.sgepfx.util.javafx.Dialog;
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
    private static PagamentoRepository pr;
    private static TableView<PagamentoProperty> tvPagamentoGlobal;
    private static Stage s1;
    private static boolean aberto;
    private static PropinaRepository pr1;
    @FXML // fx:id="apContainer"
    private AnchorPane apContainer; // Value injected by FXMLLoader
    @FXML // fx:id="hbMain"
    private HBox hbMain; // Value injected by FXMLLoader
    @FXML // fx:id="jbtMatricula"
    private JFXButton jbtMatricula; // Value injected by FXMLLoader
    @FXML // fx:id="jbtGeral"
    private JFXButton jbtGeral; // Value injected by FXMLLoader
    @FXML // fx:id="jbtAtualizaTabela"
    private JFXButton jbtAtualizaTabela; // Value injected by FXMLLoader
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
    @FXML // fx:id="jcbMatricula"
    private JFXCheckBox jcbMatricula; // Value injected by FXMLLoader
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
    //Pagamento geral
    @FXML // fx:id="jcbAlunoGeral"
    private JFXComboBox<AlunoProperty> jcbAlunoGeral; // Value injected by FXMLLoader
    @FXML // fx:id="jcbTipoDePagamentoGeral"
    private JFXComboBox<TipoDePagamentoProperty> jcbTipoDePagamentoGeral; // Value injected by FXMLLoader
    @FXML // fx:id="jtfValorPropina"
    private JFXTextField jtfValorGeral; // Value injected by FXMLLoader
    @FXML // fx:id="jbtRegistrarGeral"
    private JFXButton jbtRegistrarGeral; // Value injected by FXMLLoader
    //Propina fim

    @FXML
    void handleAtualiza(ActionEvent event) {
        atualizaTabela();
    }

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
    void handlePesquisa(KeyEvent event) {
        tvPagamentoGlobal.getItems().clear();
        if (jcbCertificado.isSelected())
            atualizaTabela(jtfPesquisa.getText(), TipoDePagamento.CERTIFICADO);

        if (jcbMatricula.isSelected())
            atualizaTabela(jtfPesquisa.getText(), TipoDePagamento.MATRICULA);

        if (jcbPropina.isSelected())
            atualizaTabela(jtfPesquisa.getText(), TipoDePagamento.PROPINA);

        if (jcbUniforme.isSelected())
            atualizaTabela(jtfPesquisa.getText(), TipoDePagamento.UNIFORME);

        if (!(jcbCertificado.isSelected() || jcbMatricula.isSelected() || jcbPropina.isSelected() || jcbUniforme.isSelected()))
            atualizaTabela(jtfPesquisa.getText(), null);
    }

    @FXML
    void handleBusca(ActionEvent event) {
        tvPagamentoGlobal.getItems().clear();
        if (jcbCertificado.isSelected())
            atualizaTabela(jtfPesquisa.getText(), TipoDePagamento.CERTIFICADO);

        if (jcbMatricula.isSelected())
            atualizaTabela(jtfPesquisa.getText(), TipoDePagamento.MATRICULA);

        if (jcbPropina.isSelected())
            atualizaTabela(jtfPesquisa.getText(), TipoDePagamento.PROPINA);

        if (jcbUniforme.isSelected())
            atualizaTabela(jtfPesquisa.getText(), TipoDePagamento.UNIFORME);

        if (!(jcbCertificado.isSelected() || jcbMatricula.isSelected() || jcbPropina.isSelected() || jcbUniforme.isSelected()))
            atualizaTabela(jtfPesquisa.getText(), null);

    }

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
                        p.getAluno().getNome(), p.getClasse()));
        s1.close();
        aberto = false;
    }

    @FXML
    void handleRegistrarGeral(ActionEvent event) {
        var p = pr.save(
                PagamentoBuilder.novaInstancia()
                        .comAluno(converte(jcbAlunoGeral.getSelectionModel().getSelectedItem()))
                        .comDataDePagamento(LocalDateTime.now())
                        .comTipoDePagamento(Arrays.stream(TipoDePagamento.values()).filter(tipoDePagamento -> tipoDePagamento.getS().equalsIgnoreCase(jcbTipoDePagamentoGeral.getSelectionModel().getSelectedItem().getTipo())).findFirst().orElse(null))
                        .comValorPago(new BigDecimal(jtfValorGeral.getText()))
                        .constroi());
        Dialog.informacao("Propina",
                String.format("Pagamento do(a) %s para %s feita com sucesso!",
                        p.getAluno().getNome(), p.getTipoDePagamento().getS()));
        s1.close();
        aberto = false;
    }
    //Pagamento geral fim

    @FXML
    void handleGeral(ActionEvent event) {
        if (!aberto)
            try {
                Parent p = FXMLLoader.load(getClass().getResource("/fxml/novo-pagamento.fxml"));
                Scene s = new Scene(p);
                s1 = new Stage();
                s1.setTitle("Pagamento Geral");
                s1.setResizable(false);
                s1.setOnCloseRequest(event1 -> aberto = false);
                s1.setScene(s);
                s1.show();
            } catch (IOException e) {
                Dialog.erro("Erro", e.getMessage());
                e.printStackTrace();
            }
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
            jcbAlunoPropina.setOnMouseClicked(event -> {
             jcbClassePropina.getItems().clear();
             jcbClassePropina.getItems()
             .addAll(
             SGESpringFX.getMR()
             .buscaPeloNome(
             jcbAlunoPropina
             .getEditor()
             .getText())
             .stream().map(Matricula::getClasse)
             .collect(Collectors.toList())
             .stream().map(ClasseProperty::new)
             .collect(Collectors.toList()));
             });
            jcbAlunoPropina.setOnKeyReleased(event -> {
                jcbAlunoPropina.getItems().clear();
                jcbAlunoPropina.getItems()
                        .addAll(converteTodos(ar.buscaPeloNomeContendo(
                                jcbAlunoPropina
                                        .getEditor()
                                        .getText()).toArray(Aluno[]::new)));
            });
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

        if (jcbAlunoGeral != null) {
            var ar = SGESpringFX.getAR();
            jcbAlunoGeral.getItems().addAll(converteTodos(ar.buscaTodos().toArray(Aluno[]::new)));
            jcbAlunoGeral.setOnKeyReleased(event -> {
                jcbAlunoGeral.getItems().clear();
                jcbAlunoGeral.getItems()
                        .addAll(converteTodos(ar.buscaPeloNomeContendo(
                                jcbAlunoGeral
                                        .getEditor()
                                        .getText()).toArray(Aluno[]::new)));
            });
            jcbAlunoGeral.getSelectionModel().selectFirst();
            jcbTipoDePagamentoGeral.getItems()
                    .addAll(converteTodos(
                            new TipoDePagamento[]{TipoDePagamento.MATRICULA, TipoDePagamento.PROPINA},
                            TipoDePagamento.values()).toArray(TipoDePagamentoProperty[]::new));
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
                .addAll(converteTodos(pr.buscaTodos().toArray(Pagamento[]::new)));
    }

    private void atualizaTabela(String nome, TipoDePagamento tipo) {
        if (tipo == null)
            tvPagamentoGlobal.getItems()
                    .addAll(converteTodos(pr.buscaPeloNome(nome).toArray(Pagamento[]::new)));
        else
            tvPagamentoGlobal.getItems()
                    .addAll(converteTodos(pr.buscaPeloNomeETipoDePagamento(nome, tipo)
                            .toArray(Pagamento[]::new)));
    }
}
