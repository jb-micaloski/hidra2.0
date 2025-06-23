/*    */ package ipqm.gsd.hidra.ihm.widgets.painel_permanente.itens;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemPainelPermanenteHorarioReal
/*    */   extends ItemPainelPermanente
/*    */ {
/*    */   private final SimpleDateFormat sdf;
/*    */   
/*    */   public ItemPainelPermanenteHorarioReal() {
/* 16 */     super("Horário real", 23);
/* 17 */     this.sdf = new SimpleDateFormat("HH:mm:ss");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String geraValor(List<ObjetoDOMINIO> listaObjetos) {
/* 27 */     String horario = this.sdf.format(Long.valueOf(System.currentTimeMillis()));
/* 28 */     return horario;
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/painel_permanente/itens/ItemPainelPermanenteHorarioReal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */