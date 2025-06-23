/*    */ package ipqm.gsd.hidra.ihm.util;
/*    */ 
/*    */ import java.text.DecimalFormat;
/*    */ import javafx.util.converter.DoubleStringConverter;
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
/*    */ 
/*    */ 
/*    */ public class DoubleFormattedStringConverter
/*    */   extends DoubleStringConverter
/*    */ {
/* 44 */   private String formato = null;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public DoubleFormattedStringConverter() {
/* 52 */     this.formato = "0.0";
/*    */   }
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
/*    */   public DoubleFormattedStringConverter(String formato) {
/* 67 */     this.formato = formato;
/*    */   }
/*    */   
/*    */   public String getFormato() {
/* 71 */     return this.formato;
/*    */   }
/*    */   
/*    */   public void setFormato(String formato) {
/* 75 */     this.formato = formato;
/*    */   }
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
/*    */   public String toString(Double value) {
/* 93 */     DecimalFormat numberFormat = new DecimalFormat(getFormato());
/* 94 */     String stringValue = numberFormat.format(value);
/*    */ 
/*    */     
/* 97 */     return stringValue;
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/util/DoubleFormattedStringConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */