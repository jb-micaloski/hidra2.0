/*     */ package ipqm.gsd.hidra.ihm.util;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
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
/*     */ public class DataHoraStringConverter
/*     */ {
/*     */   public static Long fromString(String dataString) {
/*     */     Long dataMilissegundos;
/*  77 */     String[] dataHora = dataString.trim().split("-|\\t|\\s|:|;|\\.|,|_|/|\\\\");
/*  78 */     dataString = String.join("-", (CharSequence[])dataHora);
/*     */ 
/*     */     
/*  81 */     Date dataDate = null;
/*     */ 
/*     */     
/*  84 */     if (!dataString.equals("00-00-0000-00-00")) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  90 */       Date dataAgora = new Date(Mediador.getRelogio().getHorario());
/*     */       
/*     */       try {
/*     */         SimpleDateFormat formatadorData;
/*     */         
/*  95 */         switch (dataHora.length) {
/*     */           
/*     */           case 4:
/*  98 */             dataString = dataString + "-00";
/*  99 */             formatadorData = new SimpleDateFormat("dd-MM-yyyy-HH-mm");
/* 100 */             dataDate = formatadorData.parse(dataString);
/*     */             break;
/*     */           
/*     */           case 1:
/* 104 */             dataString = dataString + "-00";
/*     */           
/*     */           case 2:
/* 107 */             formatadorData = new SimpleDateFormat("dd-MM-yyyy");
/* 108 */             dataString = formatadorData.format(dataAgora) + "-" + dataString;
/*     */           
/*     */           default:
/* 111 */             formatadorData = new SimpleDateFormat("dd-MM-yyyy-HH-mm");
/* 112 */             dataDate = formatadorData.parse(dataString);
/*     */             break;
/*     */         } 
/*     */ 
/*     */ 
/*     */       
/* 118 */       } catch (ParseException ex) {
/*     */ 
/*     */ 
/*     */         
/* 122 */         Log.gravarLogExcecao("Data/Hora invalida. Erro ao converter data e hora string para long.", DataHoraStringConverter.class, ex);
/*     */       } 
/*     */ 
/*     */       
/* 126 */       dataMilissegundos = Long.valueOf(dataDate.getTime());
/*     */     }
/*     */     else {
/*     */       
/* 130 */       dataMilissegundos = Long.valueOf(0L);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 135 */     return dataMilissegundos;
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
/*     */   public static String toString(Long dataMilissegundos) {
/*     */     String dataString;
/* 167 */     if (dataMilissegundos.longValue() == 0L) {
/*     */       
/* 169 */       dataString = "00/00/0000 00:00";
/*     */     }
/*     */     else {
/*     */       
/* 173 */       Date dataDate = new Date(dataMilissegundos.longValue());
/* 174 */       SimpleDateFormat formatadorData = new SimpleDateFormat("dd/MM/yyyy HH:mm");
/* 175 */       dataString = formatadorData.format(dataDate);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 180 */     return dataString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String toStringHHmm(Long dataMilissegundos) {
/*     */     String dataString;
/* 188 */     if (dataMilissegundos.longValue() == 0L) {
/*     */       
/* 190 */       dataString = "00:00";
/*     */     }
/*     */     else {
/*     */       
/* 194 */       Date dataDate = new Date(dataMilissegundos.longValue());
/* 195 */       SimpleDateFormat formatadorData = new SimpleDateFormat("HH:mm");
/* 196 */       dataString = formatadorData.format(dataDate);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 201 */     return dataString;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/util/DataHoraStringConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */