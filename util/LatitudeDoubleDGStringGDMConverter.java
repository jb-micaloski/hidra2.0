/*     */ package ipqm.gsd.hidra.ihm.util;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import javafx.util.converter.DoubleStringConverter;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LatitudeDoubleDGStringGDMConverter
/*     */   extends DoubleStringConverter
/*     */ {
/*     */   public Double fromString(String latitudeGDM) {
/*     */     String minutosStr, decimosMinutoStr;
/*  58 */     int posicaoGrau = 0;
/*  59 */     int posicaoPontoDecimal = 0;
/*  60 */     int posicaoMinuto = 0;
/*  61 */     int sinal = -1;
/*     */     
/*  63 */     for (int i = 0; i < latitudeGDM.length(); i++) {
/*     */       
/*  65 */       switch (latitudeGDM.charAt(i)) {
/*     */ 
/*     */         
/*     */         case '\t':
/*     */         case ' ':
/*     */         case '#':
/*     */         case '*':
/*     */         case '-':
/*     */         case ':':
/*     */         case '@':
/*     */         case '_':
/*     */         case '|':
/*     */         case '°':
/*  78 */           posicaoGrau = i;
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case ',':
/*     */         case '.':
/*  85 */           posicaoPontoDecimal = i;
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case '\'':
/*     */         case '`':
/*     */         case '´':
/*  93 */           posicaoMinuto = i;
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 'N':
/*     */         case 'n':
/* 100 */           sinal = 1;
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 'S':
/*     */         case 's':
/* 107 */           sinal = -1;
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     } 
/* 115 */     int posicaoInicio = 0;
/* 116 */     int posicaoFim = posicaoGrau;
/* 117 */     String grausStr = latitudeGDM.substring(posicaoInicio, posicaoFim);
/*     */ 
/*     */ 
/*     */     
/* 121 */     if (posicaoPontoDecimal != 0) {
/*     */       
/* 123 */       posicaoInicio = posicaoGrau + 1;
/* 124 */       posicaoFim = posicaoPontoDecimal;
/* 125 */       minutosStr = latitudeGDM.substring(posicaoInicio, posicaoFim);
/*     */       
/* 127 */       posicaoInicio = posicaoPontoDecimal + 1;
/* 128 */       posicaoFim = posicaoMinuto;
/* 129 */       decimosMinutoStr = latitudeGDM.substring(posicaoInicio, posicaoFim);
/*     */     }
/* 131 */     else if (posicaoMinuto != 0) {
/*     */       
/* 133 */       posicaoInicio = posicaoGrau + 1;
/* 134 */       posicaoFim = posicaoMinuto;
/* 135 */       minutosStr = latitudeGDM.substring(posicaoInicio, posicaoFim);
/*     */       
/* 137 */       decimosMinutoStr = "0";
/*     */     }
/*     */     else {
/*     */       
/* 141 */       minutosStr = "0";
/*     */       
/* 143 */       decimosMinutoStr = "0";
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 151 */     Double graus = Double.valueOf(Double.parseDouble(grausStr));
/* 152 */     Double minutos = Double.valueOf(Double.parseDouble(minutosStr + "." + decimosMinutoStr));
/*     */ 
/*     */     
/* 155 */     return Double.valueOf((graus.doubleValue() + minutos.doubleValue() / 60.0D) * sinal);
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
/*     */   public String toString(Double latitudeDG) {
/* 175 */     return CoordenadaGeografica.latitudeToString(latitudeDG.doubleValue());
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/util/LatitudeDoubleDGStringGDMConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */