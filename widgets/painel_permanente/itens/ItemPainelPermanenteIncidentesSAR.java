/*    */ package ipqm.gsd.hidra.ihm.widgets.painel_permanente.itens;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemPainelPermanenteIncidentesSAR
/*    */   extends ItemPainelPermanente
/*    */ {
/*    */   public ItemPainelPermanenteIncidentesSAR() {
/* 15 */     super("Número de Incidentes ", 20);
/*    */   }
/*    */ 
/*    */   
/*    */   public String geraValor(List<ObjetoDOMINIO> listaObjetos) {
/* 20 */     if (listaObjetos == null) {
/* 21 */       return "";
/*    */     }
/*    */     
/* 24 */     Iterator<ObjetoDOMINIO> iterator = listaObjetos.iterator();
/* 25 */     int numAcompanhamentos = 0;
/*    */     
/* 27 */     while (iterator.hasNext()) {
/* 28 */       ObjetoDOMINIO objetoDOMINIO = iterator.next();
/* 29 */       if (objetoDOMINIO instanceof ipqm.gsd.componentes.dominio.sar.IncidenteSAR) {
/* 30 */         numAcompanhamentos++;
/*    */       }
/*    */     } 
/* 33 */     return String.valueOf(numAcompanhamentos);
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/painel_permanente/itens/ItemPainelPermanenteIncidentesSAR.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */