/*    */ package ipqm.gsd.hidra.ihm.dialogos.mapa;
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
/*    */ public class ControladorDialogoMapa
/*    */   implements Initializable
/*    */ {
/*    */   @FXML
/*    */   private AnchorPane anchorPaneDialogo;
/*    */   @FXML
/*    */   private AnchorPane fundoPreto;
/*    */   private DialogoMapa dialogo;
/*    */   @FXML
/*    */   private AnchorPane fundoMapa;
/*    */   @FXML
/*    */   private Label descricaoTexto;
/*    */   @FXML
/*    */   private Label tituloTexto;
/*    */   @FXML
/*    */   private AnchorPane container;
/*    */   
/*    */   public void initialize(URL url, ResourceBundle rb) {}
/*    */   
/*    */   public AnchorPane getAnchorPaneDialogo() {
/* 41 */     return this.anchorPaneDialogo;
/*    */   }
/*    */   
/*    */   public AnchorPane getFundoPreto() {
/* 45 */     return this.fundoPreto;
/*    */   }
/*    */   
/*    */   public AnchorPane getFundoMapa() {
/* 49 */     return this.fundoMapa;
/*    */   }
/*    */   
/*    */   private void acaoBotaoSim(ActionEvent event) {
/* 53 */     ThreadPool.executar(() -> this.dialogo.acao(AcaoDialogo.SIM));
/*    */ 
/*    */     
/* 56 */     this.dialogo.ocultar();
/*    */   }
/*    */   
/*    */   public void setDialogo(DialogoMapa dialogo) {
/* 60 */     this.dialogo = dialogo;
/*    */   }
/*    */   
/*    */   @FXML
/*    */   private void acaoOK(ActionEvent event) {
/* 65 */     ThreadPool.executar(() -> this.dialogo.acao(AcaoDialogo.OK));
/*    */ 
/*    */     
/* 68 */     this.dialogo.ocultar();
/*    */   }
/*    */   
/*    */   @FXML
/*    */   private void acaoCancelar(ActionEvent event) {
/* 73 */     ThreadPool.executar(() -> this.dialogo.acao(AcaoDialogo.CANCELAR));
/*    */ 
/*    */     
/* 76 */     this.dialogo.ocultar();
/*    */   }
/*    */   
/*    */   public void setDescricao(String descricao) {
/* 80 */     this.descricaoTexto.setText(descricao);
/*    */   }
/*    */   
/*    */   public void setTitulo(String titulo) {
/* 84 */     this.tituloTexto.setText(titulo);
/*    */   }
/*    */   
/*    */   public AnchorPane getContainer() {
/* 88 */     return this.container;
/*    */   }
/*    */   
/*    */   public void setContainer(AnchorPane container) {
/* 92 */     this.container = container;
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/dialogos/mapa/ControladorDialogoMapa.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */