/*     */ package ipqm.gsd.hidra.ihm.objetos_graficos;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.hidra.ihm.HidraFX;
/*     */ import ipqm.gsd.hidra.ihm.controle.Aplicacao;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.projetos.geral.cenas.controladores.ControladorPainelLateral;
/*     */ import ipqm.gsd.hidra.ihm.projetos.geral.formularios.controladores.ControladorFormularios;
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import javafx.application.Platform;
/*     */ import javafx.fxml.FXMLLoader;
/*     */ import javafx.scene.Parent;
/*     */ import javafx.scene.Scene;
/*     */ import javafx.scene.input.KeyCode;
/*     */ import javafx.scene.input.KeyEvent;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PainelLateralCT
/*     */ {
/*     */   private final ControladorPainelLateral painelLateralController;
/*     */   private AnchorPane root;
/*     */   private AnchorPane anchorPane;
/*     */   private static boolean exibindo;
/*     */   private static ControladorFormularios formulario;
/*     */   
/*     */   public PainelLateralCT(CanvasCenarioTatico canvasCenarioTatico, String caminhoPainel) {
/*  32 */     FXMLLoader fxmlLoader = new FXMLLoader(HidraFX.class.getResource(caminhoPainel));
/*     */     
/*     */     try {
/*  35 */       this.root = (AnchorPane)fxmlLoader.load();
/*  36 */     } catch (IOException ex) {
/*  37 */       Log.gravarLogExcecao("Erro ao carregar arquivo FXML. ", this, ex);
/*     */     } 
/*  39 */     this.painelLateralController = (ControladorPainelLateral)fxmlLoader.getController();
/*  40 */     this.painelLateralController.setCanvasCenarioTatico(canvasCenarioTatico);
/*     */     
/*  42 */     Platform.runLater(() -> {
/*     */           Scene scene = new Scene((Parent)this.root);
/*     */           
/*     */           scene.getStylesheets().addAll((Object[])new String[] { Aplicacao.getInstancia().getCssPadrao() });
/*     */         });
/*  47 */     this.anchorPane = new AnchorPane();
/*  48 */     this.anchorPane.setLayoutX(-205.0D);
/*  49 */     this.anchorPane.setLayoutY(0.0D);
/*  50 */     this.anchorPane.setPrefHeight(1025.0D);
/*  51 */     this.anchorPane.setPrefWidth(228.0D);
/*  52 */     this.anchorPane.setId("AnchorPane");
/*     */     
/*  54 */     Platform.runLater(() -> this.anchorPane.getChildren().addAll((Collection)this.root.getChildren()));
/*     */ 
/*     */ 
/*     */     
/*  58 */     canvasCenarioTatico.getControlador().getCena().getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
/*     */           if (exibindo) {
/*     */             if (event.getCode() == KeyCode.F4 && event.isAltDown()) {
/*     */               event.consume();
/*     */             } else if (getFormulario() != null) {
/*     */               getFormulario().acaoTeclado(event);
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ControladorPainelLateral getPainelLateralController() {
/*  73 */     return this.painelLateralController;
/*     */   }
/*     */   
/*     */   public AnchorPane getRoot() {
/*  77 */     return this.root;
/*     */   }
/*     */   
/*     */   public AnchorPane getAnchorPane() {
/*  81 */     return this.anchorPane;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ControladorFormularios getFormulario() {
/*  88 */     return formulario;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setFormulario(ControladorFormularios formulario) {
/*  95 */     PainelLateralCT.formulario = formulario;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isExibindo() {
/* 102 */     return exibindo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setExibindo(boolean exibindo) {
/* 109 */     PainelLateralCT.exibindo = exibindo;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/objetos_graficos/PainelLateralCT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */