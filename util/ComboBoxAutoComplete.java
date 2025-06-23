/*    */ package ipqm.gsd.hidra.ihm.util;
/*    */ 
/*    */ import java.text.Normalizer;
/*    */ import java.util.Collection;
/*    */ import java.util.regex.Pattern;
/*    */ import java.util.stream.Stream;
/*    */ import javafx.collections.FXCollections;
/*    */ import javafx.collections.ObservableList;
/*    */ import javafx.event.Event;
/*    */ import javafx.scene.control.ComboBox;
/*    */ import javafx.scene.control.Tooltip;
/*    */ import javafx.scene.input.KeyCode;
/*    */ import javafx.scene.input.KeyEvent;
/*    */ import javafx.stage.Window;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ComboBoxAutoComplete<T>
/*    */ {
/*    */   private final ComboBox<T> cmb;
/* 35 */   String filter = "";
/*    */   private final ObservableList<T> originalItems;
/*    */   
/*    */   public ComboBoxAutoComplete(ComboBox<T> cmb) {
/* 39 */     this.cmb = cmb;
/* 40 */     this.originalItems = FXCollections.observableArrayList((Collection)cmb.getItems());
/* 41 */     cmb.setTooltip(new Tooltip());
/* 42 */     cmb.setOnKeyPressed(this::handleOnKeyPressed);
/* 43 */     cmb.setOnHidden(this::handleOnHiding);
/*    */   }
/*    */   
/*    */   public void handleOnKeyPressed(KeyEvent e) {
/* 47 */     ObservableList<T> filteredList = FXCollections.observableArrayList();
/* 48 */     KeyCode code = e.getCode();
/*    */     
/* 50 */     if (code.isLetterKey()) {
/* 51 */       this.filter += e.getText();
/*    */     }
/* 53 */     if (code == KeyCode.BACK_SPACE && this.filter.length() > 0) {
/* 54 */       this.filter = this.filter.substring(0, this.filter.length() - 1);
/* 55 */       this.cmb.getItems().setAll((Collection)this.originalItems);
/*    */     } 
/* 57 */     if (code == KeyCode.ESCAPE) {
/* 58 */       this.filter = "";
/*    */     }
/* 60 */     if (this.filter.length() == 0) {
/* 61 */       filteredList = this.originalItems;
/* 62 */       this.cmb.getTooltip().hide();
/*    */     } else {
/* 64 */       Stream<T> itens = this.cmb.getItems().stream();
/* 65 */       String txtUsr = unaccent(this.filter.toString().toLowerCase());
/* 66 */       itens.filter(el -> unaccent(el.toString().toLowerCase()).contains(txtUsr)).forEach(filteredList::add);
/* 67 */       this.cmb.getTooltip().setText(txtUsr);
/* 68 */       Window stage = this.cmb.getScene().getWindow();
/* 69 */       double posX = stage.getX() + this.cmb.getBoundsInParent().getMinX();
/* 70 */       double posY = stage.getY() + this.cmb.getBoundsInParent().getMinY();
/* 71 */       this.cmb.getTooltip().show(stage, posX, posY);
/* 72 */       this.cmb.show();
/*    */     } 
/* 74 */     this.cmb.getItems().setAll((Collection)filteredList);
/*    */   }
/*    */   
/*    */   public void handleOnHiding(Event e) {
/* 78 */     this.filter = "";
/* 79 */     this.cmb.getTooltip().hide();
/* 80 */     T s = (T)this.cmb.getSelectionModel().getSelectedItem();
/* 81 */     this.cmb.getItems().setAll((Collection)this.originalItems);
/* 82 */     this.cmb.getSelectionModel().select(s);
/*    */   }
/*    */   
/*    */   private String unaccent(String s) {
/* 86 */     String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
/* 87 */     Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
/* 88 */     return pattern.matcher(temp).replaceAll("");
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/util/ComboBoxAutoComplete.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */