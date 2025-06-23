/*    */ package ipqm.gsd.hidra.ihm.util;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.anotacao_interface.EnumFiltros;
/*    */ import javafx.beans.value.ObservableValue;
/*    */ import javafx.event.ActionEvent;
/*    */ import javafx.scene.Node;
/*    */ import javafx.scene.control.TableCell;
/*    */ import javafx.scene.control.TextField;
/*    */ 
/*    */ 
/*    */ public class MascaraTextField<S>
/*    */   extends TableCell<S, String>
/*    */ {
/*    */   private TextField textField;
/*    */   
/*    */   public MascaraTextField(EnumFiltros.Mascara mascara, EnumFiltros.Valores valor) {
/* 17 */     this.textField = new TextField();
/*    */     
/* 19 */     FiltroTexto.adicionarMascara(this.textField, mascara, valor);
/* 20 */     this.textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
/*    */           if (!isNowFocused.booleanValue()) {
/*    */             processEdit();
/*    */           }
/*    */         });
/* 25 */     this.textField.setOnAction(event -> processEdit());
/*    */   }
/*    */   
/*    */   private void processEdit() {
/* 29 */     String text = this.textField.getText();
/* 30 */     commitEdit(text);
/*    */   }
/*    */ 
/*    */   
/*    */   public void updateItem(String value, boolean empty) {
/* 35 */     super.updateItem(value, empty);
/* 36 */     if (empty) {
/* 37 */       setText(null);
/* 38 */       setGraphic(null);
/* 39 */     } else if (isEditing()) {
/* 40 */       setText(null);
/* 41 */       this.textField.setText(value);
/* 42 */       setGraphic((Node)this.textField);
/*    */     } else {
/* 44 */       setText(value);
/* 45 */       setGraphic(null);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void startEdit() {
/* 51 */     super.startEdit();
/* 52 */     String value = (String)getItem();
/* 53 */     if (value != null) {
/* 54 */       this.textField.setText(value);
/* 55 */       setGraphic((Node)this.textField);
/* 56 */       setText(null);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void cancelEdit() {
/* 62 */     super.cancelEdit();
/* 63 */     setText((String)getItem());
/* 64 */     setGraphic(null);
/*    */   }
/*    */ 
/*    */   
/*    */   public void commitEdit(String value) {
/* 69 */     super.commitEdit(value);
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/util/MascaraTextField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */