/*    */ package ipqm.gsd.hidra.ihm.dialogos.alerta;
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
/*    */ 
/*    */ public class ControladorDialogoAlerta
/*    */   implements Initializable
/*    */ {
/*    */   @FXML
/*    */   private AnchorPane anchorPaneDialogo;
/*    */   @FXML
/*    */   private AnchorPane fundoPreto;
/*    */   @FXML
/*    */   private AnchorPane alerta;
/*    */   @FXML
/*    */   private Label tituloAlerta;
/*    */   @FXML
/*    */   private Label descricaoAlerta;
/*    */   private DialogoAlerta dialogo;
/*    */   
/*    */   public void initialize(URL url, ResourceBundle rb) {}
/*    */   
/*    */   public AnchorPane getAnchorPaneDialogo() {
/* 39 */     return this.anchorPaneDialogo;
/*    */   }
/*    */   
/*    */   public AnchorPane getFundoPreto() {
/* 43 */     return this.fundoPreto;
/*    */   }
/*    */   
/*    */   public AnchorPane getAlerta() {
/* 47 */     return this.alerta;
/*    */   }
/*    */   
/*    */   public void setTituloAlerta(String tituloAlerta) {
/* 51 */     this.tituloAlerta.setText(tituloAlerta);
/*    */   }
/*    */   
/*    */   public void setDescricaoAlerta(String descricaoAlerta) {
/* 55 */     this.descricaoAlerta.setText(descricaoAlerta);
/*    */   }
/*    */   
/*    */   @FXML
/*    */   private void acaoBotaoOk(ActionEvent event) {
/* 60 */     ThreadPool.executar(() -> this.dialogo.acao(AcaoDialogo.OK));
/*    */ 
/*    */     
/* 63 */     this.dialogo.ocultar();
/*    */   }
/*    */   
/*    */   public void setDialogo(DialogoAlerta dialogo) {
/* 67 */     this.dialogo = dialogo;
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/dialogos/alerta/ControladorDialogoAlerta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */