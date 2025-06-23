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
/*    */ public class ItemPainelPermanenteNumAcompanhamentos
/*    */   extends ItemPainelPermanente
/*    */ {
/*    */   public ItemPainelPermanenteNumAcompanhamentos() {
/* 15 */     super("Acompanhamentos", 8);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String geraValor(List<ObjetoDOMINIO> listaObjetos) {
/* 21 */     if (listaObjetos == null) {
/* 22 */       return "";
/*    */     }
/*    */     
/* 25 */     Iterator<ObjetoDOMINIO> iterator = listaObjetos.iterator();
/* 26 */     int numAcompanhamentos = 0;
/*    */     
/* 28 */     while (iterator.hasNext()) {
/* 29 */       ObjetoDOMINIO objetoDOMINIO = iterator.next();
/* 30 */       if (objetoDOMINIO instanceof ipqm.gsd.componentes.dominio.acompanhamentos.Acompanhamento) {
/* 31 */         numAcompanhamentos++;
/*    */       }
/*    */     } 
/*    */     
/* 35 */     return String.valueOf(numAcompanhamentos);
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/painel_permanente/itens/ItemPainelPermanenteNumAcompanhamentos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */