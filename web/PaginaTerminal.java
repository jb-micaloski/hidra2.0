/*    */ package ipqm.gsd.hidra.ihm.web;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.util.ConversorTipos;
/*    */ import ipqm.gsd.componentes.nucleo.web.Pagina;
/*    */ import ipqm.gsd.componentes.nucleo.web.ServidorHttp;
/*    */ import ipqm.gsd.hidra.ihm.Hidra;
/*    */ import ipqm.gsd.hidra.ihm.HidraTerminal;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PaginaTerminal
/*    */   extends Pagina
/*    */ {
/*    */   public PaginaTerminal(ServidorHttp servidorHttp) {
/* 21 */     super(servidorHttp, "Terminal", 30);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getConteudo(Map<String, String> params) {
/* 26 */     StringBuilder out = new StringBuilder();
/*    */     
/* 28 */     out.append("<h2>Terminal</h2>");
/*    */     
/* 30 */     if (params.containsKey("acao")) {
/*    */       
/* 32 */       switch ((String)params.get("acao")) {
/*    */         case "reiniciar_app":
/* 34 */           HidraTerminal.getInstancia().reiniciarAplicacao();
/* 35 */           out.append("Reiniciando a aplicação...");
/*    */           break;
/*    */       } 
/*    */ 
/*    */     
/*    */     } else {
/* 41 */       out.append("<a onclick=\"return confirm('Tem certeza que deseja reiniciar a aplicação?')\"  href='?p=ipqm.gsd.hidra.ihm.web.PaginaTerminal&acao=reiniciar_app'>Reiniciar a aplicação</a><br/><br/>");
/*    */       
/* 43 */       out.append("Tempo de execucão: ").append(ConversorTipos.milisegundosParaString(System.currentTimeMillis() - Hidra.tempoIniciado));
/* 44 */       out.append("<br><br>");
/*    */       
/* 46 */       if (Hidra.DATA_COMPILACAO != -1L) {
/* 47 */         String cor = "black";
/* 48 */         out.append("Compilado em:  <b><font color=").append(cor).append(">").append((new SimpleDateFormat("dd.MM.yy HH:mm")).format(Long.valueOf(Hidra.DATA_COMPILACAO))).append("</font></b>");
/* 49 */         out.append("<br>");
/*    */       } 
/*    */     } 
/*    */     
/* 53 */     return out.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/web/PaginaTerminal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */