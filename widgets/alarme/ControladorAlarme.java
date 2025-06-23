/*     */ package ipqm.gsd.hidra.ihm.widgets.alarme;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.alarmes.ConfiguracaoAlarme;
/*     */ import ipqm.gsd.componentes.nucleo.util.depuracao.Debug;
/*     */ import ipqm.gsd.hidra.ihm.objetos_visuais.objetos_tabulares.ObjetoTabularAlarme;
/*     */ import ipqm.gsd.hidra.ihm.util.UtilDesempenho;
/*     */ import java.net.URL;
/*     */ import java.util.Iterator;
/*     */ import java.util.ResourceBundle;
/*     */ import javafx.animation.TranslateTransition;
/*     */ import javafx.beans.value.ObservableValue;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.event.Event;
/*     */ import javafx.event.EventHandler;
/*     */ import javafx.fxml.FXML;
/*     */ import javafx.fxml.Initializable;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.CheckBox;
/*     */ import javafx.scene.control.TableColumn;
/*     */ import javafx.scene.control.TableView;
/*     */ import javafx.scene.control.cell.PropertyValueFactory;
/*     */ import javafx.scene.input.MouseButton;
/*     */ import javafx.scene.input.MouseEvent;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ import javafx.scene.layout.VBox;
/*     */ import javafx.util.Callback;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ControladorAlarme
/*     */   implements Initializable
/*     */ {
/*     */   @FXML
/*     */   private AnchorPane anchorPaneAlarme;
/*     */   private WidgetAlarme widgetAlarme;
/*     */   @FXML
/*     */   private TableView<ObjetoTabularAlarme> tableViewAlarmes;
/*     */   @FXML
/*     */   private TableColumn<ObjetoTabularAlarme, Button> colunaLed;
/*     */   @FXML
/*     */   private TableColumn<ObjetoTabularAlarme, String> colunaDescricao;
/*     */   @FXML
/*     */   private AnchorPane anchorPaneConfig;
/*     */   @FXML
/*     */   private VBox hboxConfig;
/*     */   
/*     */   public void initialize(URL url, ResourceBundle rb) {
/*  55 */     this.colunaDescricao.setCellValueFactory(cellData -> ((ObjetoTabularAlarme)cellData.getValue()).getDescricao());
/*  56 */     this.colunaLed.setCellValueFactory((Callback)new PropertyValueFactory("led"));
/*     */     
/*  58 */     this.tableViewAlarmes.setOnMouseClicked(new EventHandler<MouseEvent>()
/*     */         {
/*     */           public void handle(MouseEvent mouseEvent) {
/*  61 */             if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
/*  62 */               ObjetoTabularAlarme ota = (ObjetoTabularAlarme)ControladorAlarme.this.tableViewAlarmes.getSelectionModel().getSelectedItem();
/*  63 */               if (ota != null) {
/*  64 */                 ota.getAlarme().reconhecer();
/*     */               }
/*     */             } 
/*     */           }
/*     */         });
/*  69 */     AtualizarListaConfigAlarme();
/*     */   }
/*     */   
/*     */   public WidgetAlarme getWidgetAlarme() {
/*  73 */     return this.widgetAlarme;
/*     */   }
/*     */   
/*     */   public void setWidgetAlarme(WidgetAlarme widgetAlarme) {
/*  77 */     this.widgetAlarme = widgetAlarme;
/*  78 */     this.tableViewAlarmes.setItems(widgetAlarme.getListaAlarmes());
/*     */   }
/*     */   
/*     */   public AnchorPane getAnchorPaneAlarme() {
/*  82 */     return this.anchorPaneAlarme;
/*     */   }
/*     */   
/*     */   public void setTabelViewVisible(boolean v) {
/*  86 */     this.tableViewAlarmes.setVisible(v);
/*     */   }
/*     */   
/*     */   public void reconheceTodos() {
/*  90 */     if (Debug.isDebug()) {
/*  91 */       for (ObjetoTabularAlarme ota : this.widgetAlarme.getListaAlarmes()) {
/*  92 */         ota.getAlarme().reconhecer();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void acaoBotaoConfig(ActionEvent event) {
/*  99 */     AtualizarListaConfigAlarme();
/*     */     
/* 101 */     if (this.anchorPaneConfig.isVisible()) {
/* 102 */       TranslateTransition tt2 = new TranslateTransition(UtilDesempenho.duracaoAnimacao(500.0D), (Node)this.anchorPaneConfig);
/* 103 */       tt2.setFromX(0.0D);
/* 104 */       tt2.setToX(400.0D);
/* 105 */       tt2.setAutoReverse(false);
/* 106 */       tt2.setOnFinished(arg0 -> this.anchorPaneConfig.setVisible(false));
/*     */ 
/*     */       
/* 109 */       tt2.play();
/*     */     } else {
/* 111 */       this.anchorPaneConfig.setVisible(true);
/* 112 */       TranslateTransition tt2 = new TranslateTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.anchorPaneConfig);
/* 113 */       tt2.setFromX(400.0D);
/* 114 */       tt2.setToX(0.0D);
/* 115 */       tt2.setAutoReverse(false);
/* 116 */       tt2.play();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void AtualizarListaConfigAlarme() {
/* 121 */     this.hboxConfig.getChildren().clear();
/*     */     
/* 123 */     Iterator<ConfiguracaoAlarme> it = Mediador.getInstancia().getContextualizador().getContexto().getConfiguracoesAlarmes().values().iterator();
/* 124 */     while (it.hasNext()) {
/* 125 */       ConfiguracaoAlarme ca = it.next();
/*     */       
/* 127 */       if (ca.isPermiteDesabilitar()) {
/* 128 */         CheckBox cb = new CheckBox(ca.getNome());
/* 129 */         cb.setSelected(ca.isHabilitado());
/* 130 */         this.hboxConfig.getChildren().add(cb);
/*     */         
/* 132 */         cb.setOnAction(e -> ca.setHabilitado(cb.isSelected()));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/alarme/ControladorAlarme.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */