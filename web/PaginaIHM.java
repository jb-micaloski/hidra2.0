/*    */ package ipqm.gsd.hidra.ihm.web;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.util.ConversorTipos;
/*    */ import ipqm.gsd.componentes.nucleo.web.Pagina;
/*    */ import ipqm.gsd.componentes.nucleo.web.ServidorHttp;
/*    */ import ipqm.gsd.hidra.ihm.Hidra;
/*    */ import ipqm.gsd.hidra.ihm.HidraFX;
/*    */ import ipqm.gsd.hidra.ihm.controle.Aplicacao;
/*    */ import ipqm.gsd.hidra.ihm.controle.Tela;
/*    */ import ipqm.gsd.hidra.ihm.util.UtilDesempenho;
/*    */ import ipqm.gsd.hidra.ihm.widgets.Widget;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.Map;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PaginaIHM
/*    */   extends Pagina
/*    */ {
/*    */   public PaginaIHM(ServidorHttp servidorHttp) {
/* 25 */     super(servidorHttp, "IHM", 30);
/*    */   }
/*    */ 
/*    */   
/*    */   public String getConteudo(Map<String, String> params) {
/* 30 */     StringBuilder out = new StringBuilder();
/*    */ 
/*    */     
/* 33 */     out.append("<h2>IHM</h2>");
/*    */ 
/*    */     
/* 36 */     out.append("Tempo de execucão: ").append(ConversorTipos.milisegundosParaString(System.currentTimeMillis() - Hidra.tempoIniciado));
/* 37 */     out.append("<br><br>");
/*    */     
/* 39 */     if (Hidra.DATA_COMPILACAO != -1L) {
/* 40 */       String cor = "black";
/* 41 */       if (HidraFX.aplicacaoDesatualizada) {
/* 42 */         cor = "red";
/*    */       }
/* 44 */       out.append("Compilado em:  <b><font color=").append(cor).append(">").append((new SimpleDateFormat("dd.MM.yy HH:mm")).format(Long.valueOf(Hidra.DATA_COMPILACAO))).append("</font></b>");
/* 45 */       out.append("<br>");
/*    */     } 
/*    */     
/* 48 */     out.append("<a target=_blank href=?p=ipqm.gsd.hidra.ihm.web.PaginaImagemTela><img width='700px' src=?p=ipqm.gsd.hidra.ihm.web.PaginaImagemTela></a>");
/* 49 */     out.append("<br><br>");
/* 50 */     out.append("Veloc. animação: ").append(UtilDesempenho.getVelocidadeAnimacao().name()).append("<br/>");
/* 51 */     out.append("Freq. atualização CT: ").append(UtilDesempenho.getFrequenciaAtualizacaoCenarioTatico().name()).append("<br/>");
/* 52 */     out.append("<br>");
/*    */     
/* 54 */     for (Tela tela : Aplicacao.getInstancia().getTelas().values()) {
/*    */       
/* 56 */       out.append("<h3>Tela ").append(tela.toString()).append("</h3>");
/*    */       
/* 58 */       out.append("Cena atual: ").append(tela.getCenaAtual()).append("<br/>");
/* 59 */       out.append("&nbsp;&nbsp;Controlador: ").append(tela.getCenaAtual().getControlador().getClass()).append("<br/>");
/* 60 */       out.append("&nbsp;&nbsp;FXML: ").append(tela.getCenaAtual().getFxml()).append("<br/>");
/*    */       
/* 62 */       out.append("<br/>");
/*    */       
/* 64 */       out.append("FPS: ").append(tela.getFPS()).append("<br/>");
/*    */       
/* 66 */       out.append("<br/>");
/*    */       
/* 68 */       out.append("Widgets: ").append("<br/>");
/* 69 */       for (Widget w : tela.getWidgets().values()) {
/* 70 */         out.append("&nbsp;&nbsp;").append(w.getTipo()).append(" ");
/* 71 */         out.append("visivel: ").append(w.isVisivel()).append(" ");
/* 72 */         out.append("<br/>");
/*    */       } 
/*    */       
/* 75 */       out.append("<hr/>");
/*    */     } 
/*    */     
/* 78 */     return out.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/web/PaginaIHM.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */