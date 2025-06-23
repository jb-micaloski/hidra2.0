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
/*     */ public class LongitudeDoubleDGStringGDMConverter
/*     */   extends DoubleStringConverter
/*     */ {
/*     */   public Double fromString(String longitudeGDM) {
/*     */     String minutosStr, decimosMinutoStr;
/*  58 */     int posicaoGrau = 0;
/*  59 */     int posicaoPontoDecimal = 0;
/*  60 */     int posicaoMinuto = 0;
/*  61 */     int sinal = -1;
/*     */     
/*  63 */     for (int i = 0; i < longitudeGDM.length(); i++) {
/*     */       
/*  65 */       switch (longitudeGDM.charAt(i)) {
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
/*     */         case 'E':
/*     */         case 'L':
/*     */         case 'e':
/*     */         case 'l':
/* 102 */           sinal = 1;
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 'O':
/*     */         case 'W':
/*     */         case 'o':
/*     */         case 'w':
/* 111 */           sinal = -1;
/*     */           break;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     } 
/* 119 */     int posicaoInicio = 0;
/* 120 */     int posicaoFim = posicaoGrau;
/* 121 */     String grausStr = longitudeGDM.substring(posicaoInicio, posicaoFim);
/*     */ 
/*     */ 
/*     */     
/* 125 */     if (posicaoPontoDecimal != 0) {
/*     */       
/* 127 */       posicaoInicio = posicaoGrau + 1;
/* 128 */       posicaoFim = posicaoPontoDecimal;
/* 129 */       minutosStr = longitudeGDM.substring(posicaoInicio, posicaoFim);
/*     */       
/* 131 */       posicaoInicio = posicaoPontoDecimal + 1;
/* 132 */       posicaoFim = posicaoMinuto;
/* 133 */       decimosMinutoStr = longitudeGDM.substring(posicaoInicio, posicaoFim);
/*     */     }
/* 135 */     else if (posicaoMinuto != 0) {
/*     */       
/* 137 */       posicaoInicio = posicaoGrau + 1;
/* 138 */       posicaoFim = posicaoMinuto;
/* 139 */       minutosStr = longitudeGDM.substring(posicaoInicio, posicaoFim);
/*     */       
/* 141 */       decimosMinutoStr = "0";
/*     */     }
/*     */     else {
/*     */       
/* 145 */       minutosStr = "0";
/*     */       
/* 147 */       decimosMinutoStr = "0";
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 155 */     Double graus = Double.valueOf(Double.parseDouble(grausStr));
/* 156 */     Double minutos = Double.valueOf(Double.parseDouble(minutosStr + "." + decimosMinutoStr));
/*     */ 
/*     */     
/* 159 */     return Double.valueOf((graus.doubleValue() + minutos.doubleValue() / 60.0D) * sinal);
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
/*     */   public String toString(Double longitudeDG) {
/* 179 */     return CoordenadaGeografica.longitudeToString(longitudeDG.doubleValue());
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/util/LongitudeDoubleDGStringGDMConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */