/*    */ package ipqm.gsd.hidra.ihm.web;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.notificador.Notificacao;
/*    */ import ipqm.gsd.componentes.nucleo.notificador.Notificador;
/*    */ import ipqm.gsd.componentes.nucleo.web.Pagina;
/*    */ import ipqm.gsd.componentes.nucleo.web.ServidorHttp;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PaginaNotificacoes
/*    */   extends Pagina
/*    */ {
/*    */   public PaginaNotificacoes(ServidorHttp servidorHttp) {
/* 19 */     super(servidorHttp, "Notificações", 30);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getConteudo(Map<String, String> params) {
/* 24 */     StringBuilder out = new StringBuilder();
/*    */     
/* 26 */     for (Notificacao notificacao : Notificador.getInstancia().getNotificacoesAntigas()) {
/*    */       
/* 28 */       String titulo = notificacao.getTitulo();
/* 29 */       String descricao = notificacao.getDescricao();
/*    */       
/* 31 */       if (titulo != null && descricao == null) {
/* 32 */         descricao = titulo;
/*    */       }
/*    */       
/* 35 */       if (notificacao.getContador() > 1) {
/* 36 */         titulo = titulo + " [+" + notificacao.getContador() + "]";
/*    */       }
/*    */       
/* 39 */       out.append(notificacao.getTipo()).append(": ").append(titulo).append("<br/>").append(descricao).append("<br/>");
/*    */       
/* 41 */       out.append("<hr/>");
/*    */     } 
/*    */     
/* 44 */     return out.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/web/PaginaNotificacoes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */