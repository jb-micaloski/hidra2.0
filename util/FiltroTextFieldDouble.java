/*    */ package ipqm.gsd.hidra.ihm.util;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.anotacao_interface.EnumFiltros;
/*    */ import javafx.beans.value.ObservableValue;
/*    */ import javafx.event.ActionEvent;
/*    */ import javafx.scene.Node;
/*    */ import javafx.scene.control.TableCell;
/*    */ import javafx.scene.control.TextField;
/*    */ 
/*    */ public class FiltroTextFieldDouble<S>
/*    */   extends TableCell<S, Double>
/*    */ {
/*    */   private TextField textField;
/*    */   
/*    */   public FiltroTextFieldDouble(EnumFiltros.TipoCampo tipo, EnumFiltros.Valores valor) {
/* 16 */     this.textField = new TextField();
/* 17 */     FiltroTexto.filtrarTexto(this.textField, tipo);
/* 18 */     this.textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
/*    */           if (!isNowFocused.booleanValue()) {
/*    */             processEdit();
/*    */           }
/*    */         });
/* 23 */     this.textField.setOnAction(event -> processEdit());
/*    */   }
/*    */   
/*    */   private void processEdit() {
/* 27 */     String text = this.textField.getText();
/* 28 */     commitEdit(Double.valueOf(Double.parseDouble(text)));
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateItem(Double value, boolean empty) {
/* 33 */     String valortemp = String.valueOf(value);
/* 34 */     super.updateItem(value, empty);
/* 35 */     if (empty) {
/* 36 */       setText(null);
/* 37 */       setGraphic(null);
/* 38 */     } else if (isEditing()) {
/* 39 */       setText(null);
/* 40 */       this.textField.setText(valortemp);
/* 41 */       setGraphic((Node)this.textField);
/*    */     } else {
/* 43 */       setText(valortemp);
/* 44 */       setGraphic(null);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void startEdit() {
/* 50 */     super.startEdit();
/* 51 */     Double value = (Double)getItem();
/* 52 */     if (value != null) {
/* 53 */       String valor = String.valueOf(value);
/* 54 */       this.textField.setText(valor);
/* 55 */       setGraphic((Node)this.textField);
/* 56 */       setText(null);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void cancelEdit() {
/* 62 */     super.cancelEdit();
/* 63 */     setText(String.valueOf(getItem()));
/* 64 */     setGraphic(null);
/*    */   }
/*    */ 
/*    */   
/*    */   public void commitEdit(Double value) {
/* 69 */     super.commitEdit(value);
/* 70 */     setText(String.valueOf(value));
/* 71 */     setGraphic(null);
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/util/FiltroTextFieldDouble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */