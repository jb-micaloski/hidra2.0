/*    */ package ipqm.gsd.hidra.ihm.dialogos.confirma;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.ThreadPool;
/*    */ import ipqm.gsd.hidra.ihm.dialogos.AcaoDialogo;
/*    */ import java.net.URL;
/*    */ import java.util.ResourceBundle;
/*    */ import javafx.event.ActionEvent;
/*    */ import javafx.fxml.FXML;
/*    */ import javafx.fxml.Initializable;
/*    */ import javafx.scene.control.Label;
/*    */ import javafx.scene.layout.AnchorPane;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ControladorDialogoConfirma
/*    */   implements Initializable
/*    */ {
/*    */   @FXML
/*    */   private AnchorPane anchorPaneDialogo;
/*    */   @FXML
/*    */   private AnchorPane fundoPreto;
/*    */   @FXML
/*    */   private AnchorPane confirma;
/*    */   @FXML
/*    */   private Label tituloConfirma;
/*    */   @FXML
/*    */   private Label descricaoConfirma;
/*    */   private DialogoConfirma dialogo;
/*    */   
/*    */   public void initialize(URL url, ResourceBundle rb) {}
/*    */   
/*    */   public AnchorPane getAnchorPaneDialogo() {
/* 38 */     return this.anchorPaneDialogo;
/*    */   }
/*    */   
/*    */   public AnchorPane getFundoPreto() {
/* 42 */     return this.fundoPreto;
/*    */   }
/*    */   
/*    */   public AnchorPane getConfirma() {
/* 46 */     return this.confirma;
/*    */   }
/*    */   
/*    */   public void setTituloConfirma(String tituloConfirma) {
/* 50 */     this.tituloConfirma.setText(tituloConfirma);
/*    */   }
/*    */   
/*    */   public void setDescricaoConfirma(String descricaoConfirma) {
/* 54 */     this.descricaoConfirma.setText(descricaoConfirma);
/*    */   }
/*    */   
/*    */   @FXML
/*    */   private void acaoBotaoSim(ActionEvent event) {
/* 59 */     ThreadPool.executar(() -> this.dialogo.acao(AcaoDialogo.SIM));
/*    */ 
/*    */     
/* 62 */     this.dialogo.ocultar();
/*    */   }
/*    */   
/*    */   @FXML
/*    */   private void acaoBotaoNao(ActionEvent event) {
/* 67 */     ThreadPool.executar(() -> this.dialogo.acao(AcaoDialogo.NAO));
/*    */ 
/*    */     
/* 70 */     this.dialogo.ocultar();
/*    */   }
/*    */   
/*    */   public void setDialogo(DialogoConfirma dialogo) {
/* 74 */     this.dialogo = dialogo;
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/dialogos/confirma/ControladorDialogoConfirma.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */