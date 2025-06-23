/*    */ package ipqm.gsd.hidra.ihm.web;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.log.Log;
/*    */ import ipqm.gsd.componentes.nucleo.web.Pagina;
/*    */ import ipqm.gsd.componentes.nucleo.web.ServidorHttp;
/*    */ import ipqm.gsd.hidra.ihm.controle.Aplicacao;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PaginaControle
/*    */   extends Pagina
/*    */ {
/*    */   public PaginaControle(ServidorHttp servidorHttp) {
/* 19 */     super(servidorHttp, "Controle");
/*    */   }
/*    */ 
/*    */   
/*    */   public String getConteudo(Map<String, String> params) {
/* 24 */     StringBuilder out = new StringBuilder();
/*    */     
/* 26 */     out.append("<h2>Controle</h2>");
/*    */     
/* 28 */     if (params.containsKey("acao")) {
/* 29 */       Log.gravarLogInstrucao("Ação '" + (String)params.get("acao") + "' requisitado pelo operação na pagina web", this);
/* 30 */       switch ((String)params.get("acao")) {
/*    */         case "reiniciar":
/* 32 */           Aplicacao.getInstancia().reiniciar();
/* 33 */           out.append("Reiniciando...");
/*    */           break;
/*    */         case "desligar":
/* 36 */           Aplicacao.getInstancia().desligar();
/* 37 */           out.append("Desligando...");
/*    */           break;
/*    */         case "logoff":
/* 40 */           Aplicacao.getInstancia().logoff(null);
/* 41 */           out.append("Logoff...");
/*    */           break;
/*    */         case "reiniciar_app":
/* 44 */           Aplicacao.getInstancia().reiniciarAplicacao();
/* 45 */           out.append("Reiniciando a aplicação...");
/*    */           break;
/*    */       } 
/*    */ 
/*    */     
/*    */     } else {
/* 51 */       out.append("<a onclick=\"return confirm('Tem certeza que deseja realizar logoff?')\"  href='?p=ipqm.gsd.hidra.ihm.web.PaginaControle&acao=logoff'>Logoff</a><br/>");
/* 52 */       out.append("<a onclick=\"return confirm('Tem certeza que deseja reiniciar a aplicação?')\"  href='?p=ipqm.gsd.hidra.ihm.web.PaginaControle&acao=reiniciar_app'>Reiniciar a aplicação</a><br/><br/>");
/*    */       
/* 54 */       out.append("<a onclick=\"return confirm('Tem certeza que deseja desligar?')\"  href='?p=ipqm.gsd.hidra.ihm.web.PaginaControle&acao=desligar'>Desligar</a><br/>");
/* 55 */       out.append("<a onclick=\"return confirm('Tem certeza que deseja reiniciar?')\"  href='?p=ipqm.gsd.hidra.ihm.web.PaginaControle&acao=reiniciar'>Reiniciar</a><br/><br/>");
/*    */     } 
/*    */ 
/*    */     
/* 59 */     return out.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/web/PaginaControle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */