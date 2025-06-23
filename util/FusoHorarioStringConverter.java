/*     */ package ipqm.gsd.hidra.ihm.util;
/*     */ 
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FusoHorarioStringConverter
/*     */ {
/*     */   public static String toString(Long diferencaGMT) {
/*  53 */     long horas = diferencaGMT.longValue() / 3600000L;
/*  54 */     long minutos = diferencaGMT.longValue() % 3600000L / 60000L;
/*     */     
/*  56 */     DecimalFormat horasFormat = new DecimalFormat("+#00;-#00");
/*  57 */     DecimalFormat minutosFormat = new DecimalFormat("#00");
/*     */     
/*  59 */     String horasString = horasFormat.format(horas);
/*  60 */     String minutosString = minutosFormat.format(minutos);
/*     */     
/*  62 */     String fusoHorarioString = String.format("GMT%s:%s", new Object[] { horasString, minutosString });
/*     */ 
/*     */     
/*  65 */     return fusoHorarioString;
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
/*     */   public static Long fromString(String fusoHorarioString) {
/*  98 */     Long fusoHorarioMilissegundos = Long.valueOf(0L);
/*     */     
/* 100 */     Matcher fusoHorarioMatcher = Pattern.compile("([gG][mM][tT]|[uU][tT][cC])?([\\+-]?)(\\d{1,2})([:\\s\\._,\\|/\\\\](\\d{1,2}))?").matcher(fusoHorarioString.trim());
/*     */     
/* 102 */     if (fusoHorarioMatcher.matches()) {
/*     */       
/* 104 */       String sinalString = fusoHorarioMatcher.group(2);
/* 105 */       String horaString = fusoHorarioMatcher.group(3);
/* 106 */       String minutoString = fusoHorarioMatcher.group(5);
/*     */       
/* 108 */       long hora = 0L;
/* 109 */       long minuto = 0L;
/*     */ 
/*     */       
/*     */       try {
/* 113 */         hora = Long.parseLong(horaString);
/* 114 */         minuto = Long.parseLong(minutoString);
/*     */       }
/* 116 */       catch (NumberFormatException ex) {
/*     */         
/* 118 */         if (sinalString == null) {
/*     */           
/* 120 */           sinalString = "+";
/*     */         }
/* 122 */         else if (horaString == null) {
/*     */           
/* 124 */           hora = 0L;
/*     */         }
/* 126 */         else if (minutoString == null) {
/*     */           
/* 128 */           minuto = 0L;
/*     */         }
/*     */         else {
/*     */           
/* 132 */           throw ex;
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 138 */       fusoHorarioMilissegundos = Long.valueOf(hora * 3600000L + minuto * 60000L);
/*     */       
/* 140 */       if (sinalString.equals("-"))
/*     */       {
/* 142 */         fusoHorarioMilissegundos = Long.valueOf(fusoHorarioMilissegundos.longValue() * -1L);
/*     */       
/*     */       }
/*     */     }
/*     */     else {
/*     */       
/* 148 */       fusoHorarioMilissegundos = Long.valueOf(0L);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 153 */     return fusoHorarioMilissegundos;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/util/FusoHorarioStringConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */