/*    */ package ipqm.gsd.hidra.ihm.util;
/*    */ 
/*    */ import ipqm.gsd.hidra.ihm.objetos_graficos.PainelLateralCT;
/*    */ import javafx.animation.FadeTransition;
/*    */ import javafx.animation.TranslateTransition;
/*    */ import javafx.event.ActionEvent;
/*    */ import javafx.scene.Node;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AnimadorObjetos
/*    */ {
/*    */   public static void exibirComponenteTranslateTransition(Node componente, Integer xOrigem, Integer yOrigem, Integer xDestino, Integer yDestino, Integer numeroRepeticoes, Boolean autoReverse) {
/* 20 */     TranslateTransition translateTransition = new TranslateTransition(UtilDesempenho.duracaoAnimacao(500.0D), componente);
/* 21 */     PainelLateralCT.setExibindo(true);
/* 22 */     if (xOrigem != null) {
/* 23 */       translateTransition.setFromX(xOrigem.intValue());
/*    */     }
/* 25 */     if (yOrigem != null) {
/* 26 */       translateTransition.setFromY(yOrigem.intValue());
/*    */     }
/*    */     
/* 29 */     if (xDestino != null) {
/* 30 */       translateTransition.setToX(xDestino.intValue());
/*    */     }
/* 32 */     if (yDestino != null) {
/* 33 */       translateTransition.setToY(yDestino.intValue());
/*    */     }
/* 35 */     translateTransition.setCycleCount(numeroRepeticoes.intValue());
/* 36 */     translateTransition.setAutoReverse(autoReverse.booleanValue());
/*    */     
/* 38 */     translateTransition.play();
/*    */   }
/*    */ 
/*    */   
/*    */   public static void ocultarComponenteTranslateTransition(Node componente, Integer xOrigem, Integer yOrigem, Integer xDestino, Integer yDestino, Integer numeroRepeticoes, Boolean autoReverse) {
/* 43 */     TranslateTransition translateTransition = new TranslateTransition(UtilDesempenho.duracaoAnimacao(500.0D), componente);
/* 44 */     PainelLateralCT.setExibindo(false);
/*    */     
/* 46 */     if (xOrigem != null) {
/* 47 */       translateTransition.setFromX(xOrigem.intValue());
/*    */     }
/* 49 */     if (yOrigem != null) {
/* 50 */       translateTransition.setFromY(yOrigem.intValue());
/*    */     }
/*    */     
/* 53 */     if (xDestino != null) {
/* 54 */       translateTransition.setToX(xDestino.intValue());
/*    */     }
/* 56 */     if (yDestino != null) {
/* 57 */       translateTransition.setToY(yDestino.intValue());
/*    */     }
/*    */     
/* 60 */     translateTransition.setCycleCount(numeroRepeticoes.intValue());
/* 61 */     translateTransition.setAutoReverse(autoReverse.booleanValue());
/*    */     
/* 63 */     translateTransition.play();
/*    */   }
/*    */ 
/*    */   
/*    */   public static void exibirComponenteFadeTransition(Node componente, double x, double y, Integer numeroRepeticoes, Boolean autoReverse, double opacidade) {
/* 68 */     componente.setLayoutX(x);
/* 69 */     componente.setLayoutY(y);
/*    */     
/* 71 */     componente.setOpacity(0.0D);
/* 72 */     componente.setVisible(true);
/* 73 */     componente.setRotate(0.0D);
/*    */     
/* 75 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(700.0D), componente);
/* 76 */     fadeTransition.setFromValue(0.0D);
/* 77 */     fadeTransition.setToValue(opacidade);
/* 78 */     fadeTransition.setCycleCount(numeroRepeticoes.intValue());
/* 79 */     fadeTransition.setAutoReverse(autoReverse.booleanValue());
/* 80 */     fadeTransition.play();
/*    */   }
/*    */ 
/*    */   
/*    */   public static void exibirComponenteFadeTransition(Node componente, double x, double y, Integer numeroRepeticoes, Boolean autoReverse) {
/* 85 */     exibirComponenteFadeTransition(componente, x, y, numeroRepeticoes, autoReverse, 1.0D);
/*    */   }
/*    */   
/*    */   public static void ocultarComponenteFadeTransition(Node componente, Integer numeroRepeticoes, Boolean autoReverse) {
/* 89 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), componente);
/* 90 */     fadeTransition.setFromValue(1.0D);
/* 91 */     fadeTransition.setToValue(0.0D);
/* 92 */     fadeTransition.setCycleCount(numeroRepeticoes.intValue());
/* 93 */     fadeTransition.setAutoReverse(autoReverse.booleanValue());
/* 94 */     fadeTransition.setOnFinished(arg0 -> componente.setVisible(false));
/*    */ 
/*    */     
/* 97 */     fadeTransition.play();
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/util/AnimadorObjetos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */