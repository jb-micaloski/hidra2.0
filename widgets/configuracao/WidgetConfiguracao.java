/*     */ package ipqm.gsd.hidra.ihm.widgets.configuracao;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.hidra.ihm.controle.Tela;
/*     */ import ipqm.gsd.hidra.ihm.util.UtilDesempenho;
/*     */ import ipqm.gsd.hidra.ihm.visao.Cena;
/*     */ import ipqm.gsd.hidra.ihm.widgets.Widget;
/*     */ import ipqm.gsd.hidra.ihm.widgets.WidgetMinimizavel;
/*     */ import ipqm.gsd.hidra.ihm.widgets.system_tray.IconeSystemTray;
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import javafx.animation.Animation;
/*     */ import javafx.animation.FadeTransition;
/*     */ import javafx.animation.ParallelTransition;
/*     */ import javafx.animation.TranslateTransition;
/*     */ import javafx.application.Platform;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.fxml.FXMLLoader;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ 
/*     */ public class WidgetConfiguracao
/*     */   extends Widget
/*     */   implements WidgetMinimizavel {
/*     */   private AnchorPane root;
/*     */   private AnchorPane anchorPane;
/*     */   private ControladorConfiguracao controladorConfiguracao;
/*     */   private IconeSystemTray iconeSystemTray;
/*     */   
/*     */   public WidgetConfiguracao(Tela tela) {
/*  33 */     super(Widget.Tipo.CONFIGURACAO, tela);
/*     */   }
/*     */ 
/*     */   
/*     */   public void construir() {
/*  38 */     Log.gravarLogInstrucao("Construindo widget configuracao", this);
/*     */     
/*  40 */     FXMLLoader fxmlLoader = new FXMLLoader(Widget.class.getResource("configuracao/Configuracao.fxml"));
/*     */     
/*     */     try {
/*  43 */       this.root = (AnchorPane)fxmlLoader.load();
/*  44 */     } catch (IOException ex) {
/*  45 */       Log.gravarLogExcecao("Erro ao carregar FXML do widget", this, ex);
/*     */     } 
/*     */     
/*  48 */     this.controladorConfiguracao = (ControladorConfiguracao)fxmlLoader.getController();
/*  49 */     this.controladorConfiguracao.setWidgetConfiguracao(this);
/*     */     
/*  51 */     this.anchorPane = new AnchorPane();
/*  52 */     this.anchorPane.setLayoutX(0.0D);
/*  53 */     this.anchorPane.setLayoutY(0.0D);
/*  54 */     this.anchorPane.setPrefHeight(0.0D);
/*  55 */     this.anchorPane.setPrefWidth(0.0D);
/*     */     
/*  57 */     Platform.runLater(() -> {
/*     */           this.anchorPane.getStylesheets().clear();
/*     */           
/*     */           this.anchorPane.getStylesheets().setAll((Object[])new String[] { "ipqm/gsd/hidra/ihm/css/widgets/configuracao.css" });
/*     */         });
/*  62 */     this.anchorPane.getChildren().addAll((Collection)this.root.getChildren());
/*  63 */     this.anchorPane.setVisible(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void exibir(Cena cena) {
/*  69 */     this.anchorPane.setVisible(true);
/*  70 */     if (cena.getControlador().getAnchorPanePai() != null) {
/*  71 */       cena.getControlador().getAnchorPanePai().getChildren().add(this.anchorPane);
/*     */     }
/*  73 */     this.controladorConfiguracao.exibirIHM();
/*     */   }
/*     */ 
/*     */   
/*     */   public void ocultar(Cena cena) {
/*  78 */     this.anchorPane.setVisible(false);
/*  79 */     if (cena.getControlador().getAnchorPanePai() != null) {
/*  80 */       cena.getControlador().getAnchorPanePai().getChildren().remove(this.anchorPane);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisivel() {
/*  86 */     return this.anchorPane.isVisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public void desenharObjetos(List<ObjetoDOMINIO> listaObjetos) {
/*  91 */     this.controladorConfiguracao.desenharObjetos();
/*     */   }
/*     */ 
/*     */   
/*     */   public IconeSystemTray getIcone() {
/*  96 */     if (this.iconeSystemTray == null) {
/*  97 */       this.iconeSystemTray = new IconeSystemTray("Configurações", "ipqm/gsd/hidra/ihm/imagens/icones/configuracoes.png");
/*     */     }
/*  99 */     this.iconeSystemTray.setAtivo(true);
/* 100 */     this.iconeSystemTray.setPisca(false);
/*     */     
/* 102 */     return this.iconeSystemTray;
/*     */   }
/*     */ 
/*     */   
/*     */   public void minimizar() {
/* 107 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.controladorConfiguracao.getFundoPretoModal());
/* 108 */     fadeTransition.setFromValue(0.4D);
/* 109 */     fadeTransition.setToValue(0.0D);
/*     */     
/* 111 */     TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(500.0D), (Node)this.controladorConfiguracao.getPainelConfiguracao());
/* 112 */     tt.setFromX(0.0D);
/* 113 */     tt.setToX(500.0D);
/* 114 */     tt.setAutoReverse(false);
/*     */     
/* 116 */     ParallelTransition pt = new ParallelTransition(new Animation[] { (Animation)fadeTransition, (Animation)tt });
/* 117 */     pt.setOnFinished(arg0 -> {
/*     */           this.anchorPane.setVisible(false);
/*     */           ocultar(getTela().getCenaAtual().getControlador().getCena());
/*     */         });
/* 121 */     pt.play();
/*     */   }
/*     */ 
/*     */   
/*     */   public void maximizar() {
/* 126 */     exibir(getTela().getCenaAtual().getControlador().getCena());
/* 127 */     this.anchorPane.setVisible(true);
/*     */     
/* 129 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.controladorConfiguracao.getFundoPretoModal());
/* 130 */     fadeTransition.setFromValue(0.0D);
/* 131 */     fadeTransition.setToValue(0.4D);
/*     */     
/* 133 */     TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(500.0D), (Node)this.controladorConfiguracao.getPainelConfiguracao());
/* 134 */     tt.setFromX(500.0D);
/* 135 */     tt.setToX(0.0D);
/* 136 */     tt.setAutoReverse(false);
/*     */     
/* 138 */     ParallelTransition pt = new ParallelTransition(new Animation[] { (Animation)fadeTransition, (Animation)tt });
/* 139 */     pt.play();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMinimizado() {
/* 144 */     return !this.anchorPane.isVisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAgrupado() {
/* 149 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/configuracao/WidgetConfiguracao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */