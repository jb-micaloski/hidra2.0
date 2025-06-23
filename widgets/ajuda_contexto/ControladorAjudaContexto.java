/*    */ package ipqm.gsd.hidra.ihm.widgets.ajuda_contexto;
/*    */ 
/*    */ import ipqm.gsd.hidra.ihm.widgets.Widget;
/*    */ import java.net.URL;
/*    */ import java.util.ResourceBundle;
/*    */ import javafx.event.ActionEvent;
/*    */ import javafx.fxml.FXML;
/*    */ import javafx.fxml.Initializable;
/*    */ import javafx.scene.layout.AnchorPane;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ControladorAjudaContexto
/*    */   implements Initializable
/*    */ {
/*    */   @FXML
/*    */   private AnchorPane fundoPreto;
/*    */   @FXML
/*    */   private AnchorPane fundoAjuda;
/*    */   @FXML
/*    */   private AnchorPane anchorPaneAjuda;
/*    */   private WidgetAjuda widgetAjuda;
/*    */   
/*    */   public void initialize(URL url, ResourceBundle rb) {}
/*    */   
/*    */   public void setWidgetAjuda(WidgetAjuda widgetAjuda) {
/* 32 */     this.widgetAjuda = widgetAjuda;
/*    */   }
/*    */   
/*    */   @FXML
/*    */   private void acaoFecharAjuda(ActionEvent event) {
/* 37 */     Widget w = this.widgetAjuda.getTela().getWidget(Widget.Tipo.AJUDA);
/* 38 */     if (w != null) {
/* 39 */       w.ocultar(this.widgetAjuda.getTela().getCenaAtual());
/*    */     }
/*    */   }
/*    */   
/*    */   public AnchorPane getFundoPreto() {
/* 44 */     return this.fundoPreto;
/*    */   }
/*    */   
/*    */   public AnchorPane getFundoAjuda() {
/* 48 */     return this.fundoAjuda;
/*    */   }
/*    */   
/*    */   public AnchorPane getAnchorPaneAjuda() {
/* 52 */     return this.anchorPaneAjuda;
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/ajuda_contexto/ControladorAjudaContexto.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */