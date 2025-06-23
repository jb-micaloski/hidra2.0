/*    */ package ipqm.gsd.hidra.ihm.camadas.filtros;
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
/*    */ public class FiltroSonar
/*    */   extends FiltroObjetoTatico
/*    */ {
/*    */   private boolean exibirPlotSonar = true;
/*    */   
/*    */   public FiltroSonar() {
/* 18 */     setExibirApenasLiberado(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isExibirPlotSonar() {
/* 23 */     return this.exibirPlotSonar;
/*    */   }
/*    */   
/*    */   public void setExibirPlotSonar(boolean exibirPlotSonar) {
/* 27 */     this.exibirPlotSonar = exibirPlotSonar;
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/camadas/filtros/FiltroSonar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */