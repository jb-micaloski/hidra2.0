/*    */ package ipqm.gsd.hidra.ihm.web;
/*    */ 
/*    */ import ipqm.gsd.componentes.dominio.web.PaginaResumo;
/*    */ import ipqm.gsd.componentes.nucleo.Maquina;
/*    */ import ipqm.gsd.componentes.nucleo.util.ConversorTipos;
/*    */ import ipqm.gsd.componentes.nucleo.web.ServidorHttp;
/*    */ import ipqm.gsd.hidra.ihm.Hidra;
/*    */ import ipqm.gsd.hidra.ihm.HidraFX;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PaginaResumoHidra
/*    */   extends PaginaResumo
/*    */ {
/*    */   public PaginaResumoHidra(ServidorHttp servidorHttp) {
/* 22 */     super(servidorHttp, "Resumo", 30);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getConteudo(Map<String, String> params) {
/* 27 */     StringBuilder out = new StringBuilder();
/*    */     
/* 29 */     String cor = "";
/*    */ 
/*    */     
/* 32 */     out.append(montaQuadro(Maquina.getMaquinaLocal().getHostname(), Maquina.getMaquinaLocal().getIp()));
/*    */ 
/*    */     
/* 35 */     SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
/* 36 */     out.append(montaQuadro(sdf.format(Long.valueOf(System.currentTimeMillis())), "hora local"));
/*    */ 
/*    */     
/* 39 */     out.append(montaQuadro(ConversorTipos.milisegundosParaString(System.currentTimeMillis() - Hidra.tempoIniciado), "tempo de execução"));
/*    */ 
/*    */     
/* 42 */     if (Hidra.DATA_COMPILACAO != -1L) {
/* 43 */       cor = "#FFFFFF";
/* 44 */       if (HidraFX.aplicacaoDesatualizada) {
/* 45 */         cor = "red";
/*    */       }
/* 47 */       out.append(montaQuadro((new SimpleDateFormat("dd.MM.yy HH:mm")).format(Long.valueOf(Hidra.DATA_COMPILACAO)), "versão qualidade_v1.1", "#000000", cor));
/*    */     } 
/*    */     
/* 50 */     out.append(super.getConteudo(params));
/*    */     
/* 52 */     return out.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/web/PaginaResumoHidra.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */