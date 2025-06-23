/*    */ package ipqm.gsd.hidra.ihm.widgets.painel_permanente.itens;
/*    */ 
/*    */ import ipqm.gsd.componentes.dominio.acompanhamentos.Acompanhamento;
/*    */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemPainelPermanenteNumAcompanhamentosClassificados
/*    */   extends ItemPainelPermanente
/*    */ {
/*    */   public ItemPainelPermanenteNumAcompanhamentosClassificados() {
/* 16 */     super("Acompanhamentos Classificados", 18);
/* 17 */     setFrequencia(10);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String geraValor(List<ObjetoDOMINIO> listaObjetos) {
/* 23 */     if (listaObjetos == null) {
/* 24 */       return "";
/*    */     }
/*    */     
/* 27 */     Iterator<ObjetoDOMINIO> iterator = listaObjetos.iterator();
/* 28 */     int numAcompanhamentos = 0;
/*    */     
/* 30 */     while (iterator.hasNext()) {
/* 31 */       ObjetoDOMINIO objetoDOMINIO = iterator.next();
/* 32 */       if (objetoDOMINIO instanceof Acompanhamento) {
/* 33 */         Acompanhamento a = (Acompanhamento)objetoDOMINIO;
/* 34 */         if (!(objetoDOMINIO instanceof ipqm.gsd.componentes.dominio.acompanhamentos.AcompanhamentoFundido) && 
/* 35 */           a.getClassificacaoAcompanhamento() != null && a.getClassificacaoAcompanhamento().getInformacaoVeiculo() != null) {
/* 36 */           numAcompanhamentos++;
/*    */         }
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 43 */     return String.valueOf(numAcompanhamentos);
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/painel_permanente/itens/ItemPainelPermanenteNumAcompanhamentosClassificados.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */