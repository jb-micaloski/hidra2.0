/*     */ package ipqm.gsd.hidra.ihm.dialogos.selecaoMultipla;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.ThreadPool;
/*     */ import ipqm.gsd.hidra.ihm.dialogos.AcaoDialogo;
/*     */ import java.net.URL;
/*     */ import java.util.List;
/*     */ import java.util.ResourceBundle;
/*     */ import javafx.collections.FXCollections;
/*     */ import javafx.collections.ObservableList;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.fxml.FXML;
/*     */ import javafx.fxml.Initializable;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.Label;
/*     */ import javafx.scene.control.ListView;
/*     */ import javafx.scene.input.MouseButton;
/*     */ import javafx.scene.input.MouseEvent;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ControladorDialogoSelecaoMultipla
/*     */   implements Initializable
/*     */ {
/*     */   private DialogoSelecaoMultipla dialogo;
/*     */   @FXML
/*     */   private AnchorPane fundoPreto;
/*     */   @FXML
/*     */   private AnchorPane selecao;
/*     */   @FXML
/*     */   private Label tituloSelecao;
/*     */   @FXML
/*     */   private Label descricaoSelecao;
/*     */   @FXML
/*     */   private AnchorPane anchorPaneSelecao;
/*     */   @FXML
/*     */   private ListView<Object> listaItens;
/*     */   private ObservableList<Object> lista;
/*     */   @FXML
/*     */   private Button botaoOk;
/*     */   
/*     */   public void initialize(URL url, ResourceBundle rb) {}
/*     */   
/*     */   public void setDialogo(DialogoSelecaoMultipla dialogoSelecaoMultipla) {
/*  54 */     this.dialogo = dialogoSelecaoMultipla;
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void acaoBotaoOk(ActionEvent event) {
/*  59 */     if (getListaItens().getSelectionModel().getSelectedItems().size() > 0) {
/*  60 */       this.dialogo.setItemSelecionado(getListaItens().getSelectionModel().getSelectedItems().get(0));
/*  61 */       ThreadPool.executar(() -> this.dialogo.acao(AcaoDialogo.OK));
/*     */ 
/*     */       
/*  64 */       this.dialogo.ocultar();
/*     */     } 
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void acaoBotaoCancelar(ActionEvent event) {
/*  70 */     ThreadPool.executar(() -> this.dialogo.acao(AcaoDialogo.CANCELAR));
/*     */ 
/*     */     
/*  73 */     this.dialogo.ocultar();
/*     */   }
/*     */   
/*     */   public void setTituloSelecao(String tituloSelecao) {
/*  77 */     this.tituloSelecao.setText(tituloSelecao);
/*     */   }
/*     */   
/*     */   public void setDescricaoSelecao(String descricaoSelecao) {
/*  81 */     this.descricaoSelecao.setText(descricaoSelecao);
/*     */   }
/*     */   
/*     */   public AnchorPane getFundoPreto() {
/*  85 */     return this.fundoPreto;
/*     */   }
/*     */   
/*     */   public AnchorPane getSelecao() {
/*  89 */     return this.selecao;
/*     */   }
/*     */   
/*     */   public AnchorPane getAnchorPaneSelecao() {
/*  93 */     return this.anchorPaneSelecao;
/*     */   }
/*     */   
/*     */   public void setListaItens(List<Object> lista) {
/*  97 */     this.lista = FXCollections.observableArrayList();
/*  98 */     this.lista.addAll(lista);
/*  99 */     getListaItens().setItems(this.lista);
/* 100 */     this.botaoOk.setDisable(true);
/*     */   }
/*     */   
/*     */   public void limparLista() {
/* 104 */     this.lista.clear();
/* 105 */     getListaItens().getItems().clear();
/*     */   }
/*     */ 
/*     */   
/*     */   @FXML
/*     */   private void acaoCliqueMouse(MouseEvent event) {
/* 111 */     if (event.getButton().equals(MouseButton.PRIMARY)) {
/* 112 */       this.botaoOk.setDisable(false);
/* 113 */       if (event.getClickCount() == 2 && 
/* 114 */         getListaItens().getSelectionModel().getSelectedItems().size() > 0) {
/* 115 */         this.dialogo.setItemSelecionado(getListaItens().getSelectionModel().getSelectedItems().get(0));
/* 116 */         ThreadPool.executar(() -> this.dialogo.acao(AcaoDialogo.OK));
/*     */ 
/*     */         
/* 119 */         this.dialogo.ocultar();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ListView<Object> getListaItens() {
/* 130 */     return this.listaItens;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setListaItens(ListView<Object> listaItens) {
/* 137 */     this.listaItens = listaItens;
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void ocultarCliqueFora(MouseEvent event) {
/* 142 */     this.dialogo.ocultar();
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/dialogos/selecaoMultipla/ControladorDialogoSelecaoMultipla.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */