/*    */ package ipqm.gsd.hidra.ihm.widgets.easter_egg;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.ThreadPool;
/*    */ import ipqm.gsd.hidra.ihm.util.UtilDesempenho;
/*    */ import java.net.URL;
/*    */ import java.util.ResourceBundle;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ import javafx.animation.TranslateTransition;
/*    */ import javafx.application.Platform;
/*    */ import javafx.event.ActionEvent;
/*    */ import javafx.fxml.FXML;
/*    */ import javafx.fxml.Initializable;
/*    */ import javafx.scene.Node;
/*    */ import javafx.scene.control.Button;
/*    */ import javafx.scene.image.ImageView;
/*    */ import javafx.scene.layout.AnchorPane;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ControladorEasterEgg
/*    */   implements Initializable
/*    */ {
/*    */   @FXML
/*    */   private AnchorPane anchorPaneEasterEgg;
/*    */   @FXML
/*    */   private Button oque;
/*    */   private WidgetEasterEgg widgetEasterEgg;
/*    */   
/*    */   public void initialize(URL url, ResourceBundle rb) {
/* 36 */     Platform.runLater(() -> {
/*    */           this.oque.setVisible(false);
/*    */           this.anchorPaneEasterEgg.setVisible(false);
/*    */         });
/*    */   }
/*    */   
/*    */   public AnchorPane getAnchorPaneEasterEgg() {
/* 43 */     return this.anchorPaneEasterEgg;
/*    */   }
/*    */   
/*    */   public void setWidgetEasterEgg(WidgetEasterEgg widgetEasterEgg) {
/* 47 */     this.widgetEasterEgg = widgetEasterEgg;
/*    */   }
/*    */   
/*    */   public void oque() {
/* 51 */     this.oque.setGraphic((Node)new ImageView("ipqm/gsd/hidra/ihm/imagens/jonata.png"));
/* 52 */     this.oque.setVisible(true);
/*    */     
/* 54 */     TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(500.0D), (Node)this.oque);
/* 55 */     tt.setFromY(500.0D);
/* 56 */     tt.setToY(0.0D);
/* 57 */     tt.setAutoReverse(false);
/* 58 */     tt.play();
/*    */     
/* 60 */     ThreadPool.agendarExecucao(new VoltaOQue(), 1L, TimeUnit.SECONDS, "Volta easter egg");
/*    */   }
/*    */   
/*    */   private class VoltaOQue
/*    */     implements Runnable {
/*    */     private VoltaOQue() {}
/*    */     
/*    */     public void run() {
/* 68 */       TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(500.0D), (Node)ControladorEasterEgg.this.oque);
/* 69 */       tt.setFromY(0.0D);
/* 70 */       tt.setToY(500.0D);
/* 71 */       tt.setAutoReverse(false);
/* 72 */       tt.setOnFinished(arg0 -> {
/*    */             ControladorEasterEgg.this.oque.setVisible(false);
/*    */             ControladorEasterEgg.this.widgetEasterEgg.ocultar(ControladorEasterEgg.this.widgetEasterEgg.getTela().getCenaAtual());
/*    */           });
/* 76 */       tt.play();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/easter_egg/ControladorEasterEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */