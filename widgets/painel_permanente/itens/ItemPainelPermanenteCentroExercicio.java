/*    */ package ipqm.gsd.hidra.ihm.widgets.painel_permanente.itens;
/*    */ 
/*    */ import ipqm.gsd.componentes.dominio.contexto.ContextoTatico;
/*    */ import ipqm.gsd.componentes.dominio.teatro_operacao.TeatroOperacao;
/*    */ import ipqm.gsd.componentes.nucleo.Mediador;
/*    */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemPainelPermanenteCentroExercicio
/*    */   extends ItemPainelPermanente
/*    */ {
/*    */   public ItemPainelPermanenteCentroExercicio() {
/* 16 */     super("Centro do exercício", 15);
/*    */   }
/*    */ 
/*    */   
/*    */   public String geraValor(List<ObjetoDOMINIO> listaObjetos) {
/* 21 */     TeatroOperacao teatroReferencial = ((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getTeatroOperacao();
/* 22 */     if (teatroReferencial == null) {
/* 23 */       return " ";
/*    */     }
/* 25 */     return teatroReferencial.getPosicaoTeatroOperacao().getCoordenadaGeografica().toString();
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/painel_permanente/itens/ItemPainelPermanenteCentroExercicio.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */