/*     */ package ipqm.gsd.hidra.ihm.util;
/*     */ 
/*     */ import java.util.TimeZone;
/*     */ import javafx.util.StringConverter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TimeZoneStringConverter
/*     */   extends StringConverter<TimeZone>
/*     */ {
/*     */   public String toString(TimeZone timeZone) {
/*  50 */     String fusoHorario = null;
/*     */ 
/*     */     
/*     */     try {
/*  54 */       fusoHorario = timeZone.getDisplayName();
/*     */     }
/*  56 */     catch (NullPointerException ex) {
/*     */       
/*  58 */       if (timeZone == null) {
/*     */         
/*  60 */         TimeZone defaultTimeZone = TimeZone.getDefault();
/*     */         
/*  62 */         fusoHorario = defaultTimeZone.getDisplayName();
/*     */       }
/*     */       else {
/*     */         
/*  66 */         throw ex;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  73 */     return fusoHorario;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TimeZone fromString(String fusoHorario) {
/*  93 */     fusoHorario = fusoHorario.toUpperCase();
/*  94 */     fusoHorario = fusoHorario.replace("\\s", "");
/*  95 */     fusoHorario = fusoHorario.replace("UTC", "GMT");
/*     */     
/*  97 */     TimeZone timeZone = TimeZone.getTimeZone(fusoHorario);
/*     */ 
/*     */     
/* 100 */     return timeZone;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/util/TimeZoneStringConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */