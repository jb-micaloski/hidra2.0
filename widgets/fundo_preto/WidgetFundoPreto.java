/*    */ package ipqm.gsd.hidra.ihm.widgets.fundo_preto;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*    */ import ipqm.gsd.hidra.ihm.controle.Tela;
/*    */ import ipqm.gsd.hidra.ihm.util.UtilDesempenho;
/*    */ import ipqm.gsd.hidra.ihm.visao.Cena;
/*    */ import ipqm.gsd.hidra.ihm.widgets.Widget;
/*    */ import java.util.List;
/*    */ import javafx.animation.FadeTransition;
/*    */ import javafx.application.Platform;
/*    */ import javafx.event.ActionEvent;
/*    */ import javafx.scene.Node;
/*    */ import javafx.scene.layout.AnchorPane;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WidgetFundoPreto
/*    */   extends Widget
/*    */ {
/*    */   private AnchorPane anchorPane;
/*    */   
/*    */   public WidgetFundoPreto(Tela tela) {
/* 24 */     super(Widget.Tipo.FUNDO_PRETO, tela);
/* 25 */     setExibirApenasTelaPrincipal(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void construir() {
/* 30 */     this.anchorPane = new AnchorPane();
/* 31 */     this.anchorPane.setLayoutX(0.0D);
/* 32 */     this.anchorPane.setLayoutY(0.0D);
/* 33 */     this.anchorPane.setPrefHeight(1080.0D);
/* 34 */     this.anchorPane.setPrefWidth(1920.0D);
/* 35 */     this.anchorPane.setId("fundoPreto");
/* 36 */     this.anchorPane.setVisible(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void exibir(Cena cena) {
/* 41 */     if (cena.getControlador().getAnchorPanePai() != null) {
/* 42 */       Platform.runLater(() -> {
/*    */             if (!cena.getControlador().getAnchorPanePai().getChildren().contains(this.anchorPane)) {
/*    */               cena.getControlador().getAnchorPanePai().getChildren().add(this.anchorPane);
/*    */             }
/*    */             this.anchorPane.setVisible(true);
/*    */           });
/* 48 */       FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.anchorPane);
/* 49 */       fadeTransition.setFromValue(0.0D);
/* 50 */       fadeTransition.setToValue(0.9D);
/*    */       
/* 52 */       fadeTransition.play();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void ocultar(Cena cena) {
/* 58 */     if (cena.getControlador().getAnchorPanePai() != null) {
/* 59 */       FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.anchorPane);
/* 60 */       fadeTransition.setFromValue(0.9D);
/* 61 */       fadeTransition.setToValue(0.0D);
/* 62 */       fadeTransition.setOnFinished(arg0 -> {
/*    */             this.anchorPane.setVisible(false);
/*    */             cena.getControlador().getAnchorPanePai().getChildren().remove(this.anchorPane);
/*    */           });
/* 66 */       fadeTransition.play();
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isVisivel() {
/* 72 */     return this.anchorPane.isVisible();
/*    */   }
/*    */   
/*    */   public void desenharObjetos(List<ObjetoDOMINIO> listaObjetos) {}
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/fundo_preto/WidgetFundoPreto.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */