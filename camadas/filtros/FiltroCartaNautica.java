/*    */ package ipqm.gsd.hidra.ihm.camadas.filtros;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.Ambiente;
/*    */ import ipqm.gsd.componentes.nucleo.VariaveisEstado;
/*    */ import ipqm.gsd.componentes.nucleo.contexto.GravarEstado;
/*    */ import ipqm.gsd.componentes.nucleo.objeto_visual.Filtro;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FiltroCartaNautica
/*    */   extends Filtro
/*    */   implements Serializable, GravarEstado
/*    */ {
/*    */   public FiltroCartaNautica() {
/* 16 */     obterEstado();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void todosFiltros(boolean habilitar) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void gravarEstado() {
/* 26 */     if (verificarCondicoesGravacao()) {
/* 27 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_ENCS, String.valueOf(isExibir()));
/* 28 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.OPACIDADE_ENCS, String.valueOf(getOpacidade()));
/* 29 */       Ambiente.getInstance().persisteVariavelAmbiente("estado.properties");
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void obterEstado() {
/* 35 */     if (GravarEstado.verificarRecuperacao()) {
/* 36 */       String exibirENC = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_ENCS);
/* 37 */       if (!exibirENC.equals("")) {
/* 38 */         setExibir(Boolean.valueOf(exibirENC).booleanValue());
/*    */       }
/*    */       
/* 41 */       String opac = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.OPACIDADE_ENCS);
/* 42 */       if (!opac.equals(""))
/* 43 */         setOpacidade(Float.valueOf(opac).floatValue()); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/camadas/filtros/FiltroCartaNautica.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */