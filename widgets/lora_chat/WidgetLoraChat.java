/*     */ package ipqm.gsd.hidra.ihm.widgets.lora_chat;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*     */ import ipqm.gsd.componentes.nucleo.comunicacao_fonia.ComponenteFoniaIHM;
/*     */ import ipqm.gsd.componentes.nucleo.comunicacao_fonia.IHMFonia;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.hidra.ihm.controle.Tela;
/*     */ import ipqm.gsd.hidra.ihm.util.UtilDesempenho;
/*     */ import ipqm.gsd.hidra.ihm.visao.Cena;
/*     */ import ipqm.gsd.hidra.ihm.widgets.Widget;
/*     */ import ipqm.gsd.hidra.ihm.widgets.WidgetMinimizavel;
/*     */ import ipqm.gsd.hidra.ihm.widgets.system_tray.IconeSystemTray;
/*     */ import java.awt.Color;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import javafx.animation.TranslateTransition;
/*     */ import javafx.application.Platform;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.fxml.FXMLLoader;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.control.TitledPane;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WidgetLoraChat
/*     */   extends Widget
/*     */   implements IHMFonia, WidgetMinimizavel
/*     */ {
/*     */   private ControladorLoraChat controladorLoraChat;
/*     */   private AnchorPane root;
/*     */   private TitledPane titledPane;
/*     */   private IconeSystemTray iconeSystemTray;
/*     */   private boolean piscaIcone;
/*     */   
/*     */   public WidgetLoraChat(Tela tela) {
/*  41 */     super(Widget.Tipo.LORA_CHAT, tela);
/*  42 */     setSempreVisivel(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void construir() {
/*  48 */     Log.gravarLogInstrucao("Construindo widget Lora Chat", this);
/*     */     
/*  50 */     FXMLLoader fxmlLoader = new FXMLLoader(Widget.class.getResource("lora_chat/LoraChat.fxml"));
/*     */     
/*     */     try {
/*  53 */       this.root = (AnchorPane)fxmlLoader.load();
/*  54 */     } catch (IOException ex) {
/*  55 */       Log.gravarLogExcecao("Erro ao carregar FXML do widget", this, ex);
/*     */     } 
/*     */     
/*  58 */     this.controladorLoraChat = (ControladorLoraChat)fxmlLoader.getController();
/*     */     
/*  60 */     this.titledPane = new TitledPane();
/*  61 */     this.titledPane.setCollapsible(false);
/*  62 */     this.titledPane.setId("titledPaneWidget");
/*  63 */     this.titledPane.setLayoutX(1540.0D);
/*  64 */     this.titledPane.setLayoutY(780.0D);
/*  65 */     this.titledPane.setPrefWidth(380.0D);
/*  66 */     this.titledPane.setPrefHeight(245.0D);
/*  67 */     this.titledPane.setText("Lora (Chat)");
/*     */     
/*  69 */     Platform.runLater(() -> {
/*     */           this.titledPane.getStylesheets().clear();
/*     */           
/*     */           this.titledPane.getStylesheets().setAll((Object[])new String[] { "ipqm/gsd/hidra/ihm/css/widgets/lora_chat.css", "ipqm/gsd/hidra/ihm/css/widgets/widgets.css" });
/*     */         });
/*  74 */     this.titledPane.setContent((Node)this.root);
/*  75 */     this.titledPane.setVisible(false);
/*     */     
/*  77 */     this.controladorLoraChat.setWidgetRTD(this);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void exibir(Cena cena) {
/*  83 */     if (cena.getControlador().getAnchorPanePai() != null) {
/*  84 */       cena.getControlador().getAnchorPanePai().getChildren().add(this.titledPane);
/*  85 */       if (cena.getControlador().getTela().getWidgets().containsKey(Widget.Tipo.PAINEL_PERMANENTE)) {
/*  86 */         this.titledPane.setLayoutY(780.0D);
/*     */       } else {
/*  88 */         this.titledPane.setLayoutY(830.0D);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void ocultar(Cena cena) {
/*  95 */     if (cena.getControlador().getAnchorPanePai() != null) {
/*  96 */       cena.getControlador().getAnchorPanePai().getChildren().remove(this.titledPane);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisivel() {
/* 102 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void desenharObjetos(List<ObjetoDOMINIO> listaObjetos) {
/* 107 */     boolean novo = this.controladorLoraChat.atualizaChat();
/*     */     
/* 109 */     if (isMinimizado() && novo) {
/* 110 */       this.piscaIcone = true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void notificaIHM(ComponenteFoniaIHM componenteFoniaIHM) {}
/*     */ 
/*     */   
/*     */   public IconeSystemTray getIcone() {
/* 120 */     if (getIconeSystemTray() == null) {
/* 121 */       setIconeSystemTray(new IconeSystemTray("Chat Lora", "ipqm/gsd/hidra/ihm/imagens/icones/iconeLORA.png"));
/*     */     }
/* 123 */     getIconeSystemTray().setAtivo(true);
/* 124 */     getIconeSystemTray().setPisca(this.piscaIcone);
/* 125 */     getIconeSystemTray().setCorPisca(Color.ORANGE);
/*     */     
/* 127 */     return getIconeSystemTray();
/*     */   }
/*     */ 
/*     */   
/*     */   public void minimizar() {
/* 132 */     TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(500.0D), (Node)this.titledPane);
/* 133 */     tt.setFromY(0.0D);
/* 134 */     tt.setToY(240.0D);
/* 135 */     tt.setAutoReverse(false);
/* 136 */     tt.setOnFinished(arg0 -> this.titledPane.setVisible(false));
/*     */ 
/*     */     
/* 139 */     tt.play();
/*     */   }
/*     */ 
/*     */   
/*     */   public void maximizar() {
/* 144 */     this.piscaIcone = false;
/* 145 */     this.titledPane.setVisible(true);
/* 146 */     TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(500.0D), (Node)this.titledPane);
/* 147 */     tt.setFromY(240.0D);
/* 148 */     tt.setToY(0.0D);
/* 149 */     tt.setAutoReverse(false);
/* 150 */     tt.play();
/* 151 */     this.controladorLoraChat.focaChat();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMinimizado() {
/* 156 */     return !this.titledPane.isVisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAgrupado() {
/* 161 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IconeSystemTray getIconeSystemTray() {
/* 168 */     return this.iconeSystemTray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIconeSystemTray(IconeSystemTray iconeSystemTray) {
/* 175 */     this.iconeSystemTray = iconeSystemTray;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/lora_chat/WidgetLoraChat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */