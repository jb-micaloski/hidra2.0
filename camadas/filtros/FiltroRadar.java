/*    */ package ipqm.gsd.hidra.ihm.camadas.filtros;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.logon.perfis.PerfilUsuario;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FiltroRadar
/*    */   extends FiltroObjetoTatico
/*    */ {
/*    */   public FiltroRadar() {
/* 13 */     this.exibirPlotRadar = true;
/* 14 */     setExibirPlotMAD(false);
/* 15 */     setTipoExibicaoPontosHistoricos(EnumFiltroPontosHistoricos.NUNCA);
/* 16 */     setExibirPontosHistoricos(false);
/* 17 */     setExibirPosicaoGPS(false);
/* 18 */     setExibirGiro(false);
/* 19 */     setExibirApenasLiberado(false);
/*    */     
/* 21 */     setAcompanhamentos(PerfilUsuario.ExibirAcompanhamento.EXIBIR_TODOS_ACOMPANHAMENTOS, false);
/* 22 */     setAcompanhamentos(PerfilUsuario.ExibirAcompanhamento.EXIBIR_ACOMPANHAMENTO_RADAR, true);
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/camadas/filtros/FiltroRadar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */