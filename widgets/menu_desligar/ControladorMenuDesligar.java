/*    */ package ipqm.gsd.hidra.ihm.widgets.menu_desligar;
/*    */ 
/*    */ import ipqm.gsd.hidra.ihm.controle.Aplicacao;
/*    */ import ipqm.gsd.hidra.ihm.controle.Controlador;
/*    */ import ipqm.gsd.hidra.ihm.dialogos.AcaoDialogo;
/*    */ import ipqm.gsd.hidra.ihm.dialogos.confirma.DialogoConfirma;
/*    */ import ipqm.gsd.hidra.ihm.widgets.Widget;
/*    */ import java.net.URL;
/*    */ import java.util.ResourceBundle;
/*    */ import javafx.event.ActionEvent;
/*    */ import javafx.fxml.FXML;
/*    */ import javafx.fxml.Initializable;
/*    */ import javafx.scene.control.Button;
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
/*    */ 
/*    */ 
/*    */ public class ControladorMenuDesligar
/*    */   implements Initializable
/*    */ {
/*    */   @FXML
/*    */   private AnchorPane anchorPaneMenuDesligar;
/*    */   @FXML
/*    */   private AnchorPane fundoPreto;
/*    */   @FXML
/*    */   private AnchorPane menuDesligar;
/*    */   @FXML
/*    */   private Button botaoDesligar;
/*    */   @FXML
/*    */   private Button botaoReiniciar;
/*    */   @FXML
/*    */   private Button botaoLogoff;
/*    */   @FXML
/*    */   private Button botaoCancelar;
/*    */   private WidgetMenuDesligar widgetMenuDesligar;
/*    */   
/*    */   public void initialize(URL url, ResourceBundle rb) {}
/*    */   
/*    */   @FXML
/*    */   private void acaoDesligar(ActionEvent event) {
/* 49 */     new DialogoConfirma("Tem certeza que deseja Desligar?", "O sistema será encerrado", this.widgetMenuDesligar.getTela().getCenaAtual().getControlador())
/*    */       {
/*    */         public void acao(AcaoDialogo acao) {
/* 52 */           if (acao == AcaoDialogo.SIM) {
/* 53 */             Aplicacao.getInstancia().desligar();
/*    */           }
/*    */         }
/*    */       };
/*    */   }
/*    */ 
/*    */   
/*    */   @FXML
/*    */   private void acaoReiniciar(ActionEvent event) {
/* 62 */     new DialogoConfirma("Tem certeza que deseja Reiniciar?", "O sistema será encerrado", this.widgetMenuDesligar.getTela().getCenaAtual().getControlador())
/*    */       {
/*    */         public void acao(AcaoDialogo acao) {
/* 65 */           if (acao == AcaoDialogo.SIM) {
/* 66 */             Aplicacao.getInstancia().reiniciar();
/*    */           }
/*    */         }
/*    */       };
/*    */   }
/*    */   
/*    */   @FXML
/*    */   private void acaoLogoff(ActionEvent event) {
/* 74 */     Aplicacao.getInstancia().logoff(null);
/*    */   }
/*    */   
/*    */   @FXML
/*    */   private void acaoCancelar(ActionEvent event) {
/* 79 */     Widget w = this.widgetMenuDesligar.getTela().getWidget(Widget.Tipo.MENU_DESLIGAR);
/* 80 */     if (w != null) {
/* 81 */       w.ocultar(this.widgetMenuDesligar.getTela().getCenaAtual());
/*    */     }
/*    */   }
/*    */   
/*    */   public AnchorPane getAnchorPane() {
/* 86 */     return this.anchorPaneMenuDesligar;
/*    */   }
/*    */   
/*    */   public AnchorPane getFundoPreto() {
/* 90 */     return this.fundoPreto;
/*    */   }
/*    */   
/*    */   public AnchorPane getMenuDesligar() {
/* 94 */     return this.menuDesligar;
/*    */   }
/*    */   
/*    */   public void setWidgetMenuDesligar(WidgetMenuDesligar widgetMenuDesligar) {
/* 98 */     this.widgetMenuDesligar = widgetMenuDesligar;
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/menu_desligar/ControladorMenuDesligar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */