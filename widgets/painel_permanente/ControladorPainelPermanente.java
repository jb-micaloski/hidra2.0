/*     */ package ipqm.gsd.hidra.ihm.widgets.painel_permanente;
/*     */ 
/*     */ import java.net.URL;
/*     */ import java.util.ResourceBundle;
/*     */ import javafx.application.Platform;
/*     */ import javafx.fxml.FXML;
/*     */ import javafx.fxml.Initializable;
/*     */ import javafx.scene.control.Label;
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
/*     */ 
/*     */ 
/*     */ public class ControladorPainelPermanente
/*     */   implements Initializable
/*     */ {
/*     */   @FXML
/*     */   private AnchorPane anchorPanePainelPermanente;
/*     */   @FXML
/*     */   private Label label1;
/*     */   @FXML
/*     */   private Label label2;
/*     */   @FXML
/*     */   private Label label3;
/*     */   @FXML
/*     */   private Label label4;
/*     */   @FXML
/*     */   private Label value1;
/*     */   @FXML
/*     */   private Label value2;
/*     */   @FXML
/*     */   private Label value3;
/*     */   @FXML
/*     */   private Label value4;
/*     */   private WidgetPainelPermanente widgetPainelPermanente;
/*     */   
/*     */   public void initialize(URL url, ResourceBundle rb) {}
/*     */   
/*     */   public void setWidgetPainelPermanente(WidgetPainelPermanente widgetPainelPermanente) {
/*  51 */     this.widgetPainelPermanente = widgetPainelPermanente;
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void acaoLabel1(MouseEvent event) {
/*  56 */     this.widgetPainelPermanente.trocaProximoItem(1);
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void acaoLabel2(MouseEvent event) {
/*  61 */     this.widgetPainelPermanente.trocaProximoItem(2);
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void acaoLabel3(MouseEvent event) {
/*  66 */     this.widgetPainelPermanente.trocaProximoItem(3);
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void acaoLabel4(MouseEvent event) {
/*  71 */     this.widgetPainelPermanente.trocaProximoItem(4);
/*     */   }
/*     */   
/*     */   public AnchorPane getAnchorPanePainelPermanente() {
/*  75 */     return this.anchorPanePainelPermanente;
/*     */   }
/*     */   
/*     */   public void setValue(int pos, String value) {
/*  79 */     switch (pos) {
/*     */       case 1:
/*  81 */         Platform.runLater(() -> this.value1.setText(value));
/*     */         break;
/*     */ 
/*     */       
/*     */       case 2:
/*  86 */         Platform.runLater(() -> this.value2.setText(value));
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/*  91 */         Platform.runLater(() -> this.value3.setText(value));
/*     */         break;
/*     */ 
/*     */       
/*     */       case 4:
/*  96 */         Platform.runLater(() -> this.value4.setText(value));
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLabel(int pos, String value) {
/* 104 */     switch (pos) {
/*     */       case 1:
/* 106 */         this.label1.setText(value);
/*     */         break;
/*     */       case 2:
/* 109 */         this.label2.setText(value);
/*     */         break;
/*     */       case 3:
/* 112 */         this.label3.setText(value);
/*     */         break;
/*     */       case 4:
/* 115 */         this.label4.setText(value);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/painel_permanente/ControladorPainelPermanente.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */