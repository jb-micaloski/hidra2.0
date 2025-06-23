/*    */ package ipqm.gsd.hidra.ihm.widgets.painel_permanente.itens;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*    */ import ipqm.gsd.componentes.nucleo.comando_remoto.comandos.MensagemLog;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemPainelPermanenteErrosOcorridos
/*    */   extends ItemPainelPermanente
/*    */ {
/*    */   public ItemPainelPermanenteErrosOcorridos() {
/* 15 */     super("Erros ocorridos", 22);
/*    */   }
/*    */ 
/*    */   
/*    */   public String geraValor(List<ObjetoDOMINIO> listaObjetos) {
/* 20 */     return String.valueOf(MensagemLog.getErrosOcorridos());
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/painel_permanente/itens/ItemPainelPermanenteErrosOcorridos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */