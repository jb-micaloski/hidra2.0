/*    */ package ipqm.gsd.hidra.ihm.controle;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.Ambiente;
/*    */ import ipqm.gsd.componentes.nucleo.NucleoAmbiente;
/*    */ import java.io.File;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Fonia
/*    */ {
/*    */   public enum Tipo
/*    */   {
/* 16 */     SSTT,
/* 17 */     SIMNAV,
/* 18 */     CISNE;
/*    */   }
/*    */   
/* 21 */   private static String internaEstereo = "";
/* 22 */   private static String toque = "";
/*    */   private static String tipoFonia;
/* 24 */   private static double balance = 0.0D;
/*    */ 
/*    */   
/*    */   public static String getWidgetFonia() {
/* 28 */     String t = Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.TIPO_FONIA);
/* 29 */     tipoFonia = t.toUpperCase().trim();
/* 30 */     Tipo tipo = Tipo.valueOf(tipoFonia);
/*    */     
/* 32 */     String widget = "";
/*    */     
/* 34 */     if (tipo != null)
/* 35 */     { switch (tipo)
/*    */       { case SSTT:
/* 37 */           widget = "fonia.WidgetFonia";
/* 38 */           toque = "fonia" + File.separator + "Beep.m4a";
/* 39 */           balance = -1.0D;
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
/* 56 */           return widget;case SIMNAV: widget = "fonia_vhf.WidgetFoniaVHF"; toque = "fonia" + File.separator + "foniaChamandoIPhone.wav"; return widget;case CISNE: widget = "fonia_cisne.WidgetFoniaCisne"; internaEstereo = "--estereo"; toque = "fonia" + File.separator + "foniaChamandoIPhone.wav"; return widget; }  toque = "fonia" + File.separator + "foniaChamandoIPhone.wav"; }  return widget;
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean isFoniaHabilitada() {
/* 61 */     return Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.FONIA_HABILITADA).equals("SIM");
/*    */   }
/*    */   
/*    */   public static String getInternaEstereo() {
/* 65 */     return internaEstereo;
/*    */   }
/*    */   
/*    */   public static String getTipoFonia() {
/* 69 */     return tipoFonia;
/*    */   }
/*    */   
/*    */   public static String getToque() {
/* 73 */     return toque;
/*    */   }
/*    */   
/*    */   public static double getBalance() {
/* 77 */     return balance;
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/controle/Fonia.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */