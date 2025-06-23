/*     */ package ipqm.gsd.hidra.ihm.widgets.bloqueio_tela;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*     */ import ipqm.gsd.hidra.ihm.controle.Tela;
/*     */ import ipqm.gsd.hidra.ihm.util.UtilDesempenho;
/*     */ import ipqm.gsd.hidra.ihm.visao.Cena;
/*     */ import ipqm.gsd.hidra.ihm.widgets.Widget;
/*     */ import java.util.List;
/*     */ import javafx.animation.FadeTransition;
/*     */ import javafx.application.Platform;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.control.Label;
/*     */ import javafx.scene.input.KeyEvent;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WidgetBloqueioTela
/*     */   extends Widget
/*     */ {
/*     */   private AnchorPane anchorPane;
/*     */   private Label label;
/*     */   private boolean ativo;
/*     */   private boolean pisca;
/*     */   
/*     */   public WidgetBloqueioTela(Tela tela) {
/*  32 */     super(Widget.Tipo.BLOQUEIO_TELA, tela);
/*  33 */     setExibirApenasTelaPrincipal(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void construir() {
/*  38 */     this.anchorPane = new AnchorPane();
/*  39 */     this.anchorPane.setLayoutX(0.0D);
/*  40 */     this.anchorPane.setLayoutY(0.0D);
/*  41 */     this.anchorPane.setPrefHeight(1080.0D);
/*  42 */     this.anchorPane.setPrefWidth(1920.0D);
/*  43 */     this.anchorPane.setId("fundoVermelho");
/*  44 */     this.anchorPane.setVisible(false);
/*  45 */     this.label = new Label();
/*  46 */     this.label.setStyle("-fx-font-size: 150; -fx-text-fill: white; -fx-alignment: CENTER;");
/*  47 */     this.label.setLayoutY(400.0D);
/*  48 */     this.label.setPrefWidth(1920.0D);
/*  49 */     this.anchorPane.getChildren().add(this.label);
/*     */   }
/*     */ 
/*     */   
/*     */   public void exibir(Cena cena) {
/*  54 */     if (cena.getControlador().getAnchorPanePai() != null) {
/*  55 */       Platform.runLater(() -> cena.getControlador().getAnchorPanePai().getChildren().add(this.anchorPane));
/*     */ 
/*     */       
/*  58 */       FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.anchorPane);
/*  59 */       fadeTransition.setFromValue(0.0D);
/*  60 */       fadeTransition.setToValue(0.9D);
/*  61 */       this.anchorPane.setVisible(true);
/*  62 */       fadeTransition.play();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void ocultar(Cena cena) {
/*  68 */     if (cena.getControlador().getAnchorPanePai() != null) {
/*  69 */       FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.anchorPane);
/*  70 */       fadeTransition.setFromValue(0.9D);
/*  71 */       fadeTransition.setToValue(0.0D);
/*  72 */       fadeTransition.setOnFinished(arg0 -> {
/*     */             this.anchorPane.setVisible(false);
/*     */             
/*     */             Platform.runLater(());
/*     */           });
/*     */       
/*  78 */       fadeTransition.play();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisivel() {
/*  84 */     return this.anchorPane.isVisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public void desenharObjetos(List<ObjetoDOMINIO> listaObjetos) {
/*  89 */     this.label.setVisible(this.pisca);
/*  90 */     this.pisca = !this.pisca;
/*     */   }
/*     */ 
/*     */   
/*     */   public void teclaPressionada(KeyEvent event) {
/*  95 */     if (this.ativo) {
/*  96 */       event.consume();
/*     */     } else {
/*  98 */       super.teclaPressionada(event);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void teclaSolta(KeyEvent event) {
/* 104 */     if (this.ativo) {
/* 105 */       event.consume();
/*     */     } else {
/* 107 */       super.teclaSolta(event);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isAtivo() {
/* 112 */     return this.ativo;
/*     */   }
/*     */   
/*     */   public void setAtivo(boolean ativo) {
/* 116 */     this.ativo = ativo;
/* 117 */     if (ativo && !isVisivel()) {
/* 118 */       exibir(getTela().getCenaAtual());
/*     */     }
/* 120 */     if (!ativo && isVisivel()) {
/* 121 */       ocultar(getTela().getCenaAtual());
/*     */     }
/*     */   }
/*     */   
/*     */   public void setTexto(String texto) {
/* 126 */     Platform.runLater(() -> this.label.setText(texto));
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/bloqueio_tela/WidgetBloqueioTela.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */