/*    */ package ipqm.gsd.hidra.ihm.camadas;
/*    */ 
/*    */ import ipqm.gsd.hidra.ihm.camadas.filtros.FiltroObjetoTatico;
/*    */ import java.io.Serializable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CamadaInfo
/*    */   extends CamadaBI<FiltroObjetoTatico>
/*    */   implements Serializable
/*    */ {
/*    */   public CamadaInfo(FiltroObjetoTatico filtro, int largura, int altura) {
/* 15 */     super(filtro, largura, altura);
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/camadas/CamadaInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */