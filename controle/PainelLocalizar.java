/*    */ package ipqm.gsd.hidra.ihm.controle;
/*    */ 
/*    */ import javafx.collections.FXCollections;
/*    */ import javafx.collections.ObservableList;
/*    */ import javafx.scene.control.TableView;
/*    */ import javafx.scene.control.TextField;
/*    */ import javafx.scene.input.KeyEvent;
/*    */ import javafx.scene.layout.AnchorPane;
/*    */ import javafx.scene.layout.Pane;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PainelLocalizar
/*    */ {
/*    */   private final AnchorPane pai;
/*    */   private TextField campoTexto;
/*    */   
/*    */   public PainelLocalizar(AnchorPane pai) {
/* 22 */     this.pai = pai;
/*    */   }
/*    */   
/*    */   public void configurarPainel(Pane painel, double layoutX, double layoutY, ObservableList<InterfaceControladorTabela> lista, TableView<InterfaceControladorTabela> tabela) {
/* 26 */     setCampoTexto(new TextField());
/*    */     
/* 28 */     painel.getChildren().add(getCampoTexto());
/*    */     
/* 30 */     painel.setPrefHeight(38.0D);
/* 31 */     painel.setPrefWidth(300.0D);
/* 32 */     painel.setLayoutX(layoutX);
/* 33 */     painel.setLayoutY(layoutY);
/* 34 */     getCampoTexto().setPrefHeight(34.0D);
/* 35 */     getCampoTexto().setPrefWidth(280.0D);
/* 36 */     getCampoTexto().setLayoutX(14.0D);
/* 37 */     getCampoTexto().setLayoutY(26.0D);
/* 38 */     this.campoTexto.setId("campoLocalizar");
/*    */     
/* 40 */     this.campoTexto.setOnKeyReleased(t -> filtrarTabela(lista, tabela));
/*    */ 
/*    */     
/* 43 */     this.pai.getChildren().add(painel);
/*    */   }
/*    */   
/*    */   public AnchorPane getPai() {
/* 47 */     return this.pai;
/*    */   }
/*    */   
/*    */   public TextField getCampoTexto() {
/* 51 */     return this.campoTexto;
/*    */   }
/*    */   
/*    */   public void setCampoTexto(TextField campoTexto) {
/* 55 */     this.campoTexto = campoTexto;
/*    */   }
/*    */ 
/*    */   
/*    */   public void filtrarTabela(ObservableList<InterfaceControladorTabela> lista, TableView<InterfaceControladorTabela> tabela) {
/* 60 */     ObservableList<InterfaceControladorTabela> listaTemp = FXCollections.observableArrayList();
/* 61 */     if (!this.campoTexto.getText().isEmpty()) {
/* 62 */       for (InterfaceControladorTabela valor : lista) {
/* 63 */         if (valor.valorPequisa().toUpperCase().contains(this.campoTexto.getText().toUpperCase())) {
/* 64 */           listaTemp.add(valor);
/*    */         }
/*    */       } 
/*    */       
/* 68 */       tabela.setItems(listaTemp);
/*    */     } else {
/* 70 */       tabela.setItems(lista);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void limparCampo() {
/* 75 */     this.campoTexto.setText("");
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/controle/PainelLocalizar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */