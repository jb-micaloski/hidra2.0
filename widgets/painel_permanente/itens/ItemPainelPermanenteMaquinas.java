/*    */ package ipqm.gsd.hidra.ihm.widgets.painel_permanente.itens;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*    */ import ipqm.gsd.hidra.ihm.projetos.administracao.VariaveisInterfaceAdministracao;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemPainelPermanenteMaquinas
/*    */   extends ItemPainelPermanente
/*    */ {
/*    */   public ItemPainelPermanenteMaquinas() {
/* 14 */     super("Máquinas", 12);
/*    */   }
/*    */ 
/*    */   
/*    */   public String geraValor(List<ObjetoDOMINIO> listaObjetos) {
/* 19 */     String retorno = "";
/* 20 */     retorno = VariaveisInterfaceAdministracao.totalMaq;
/* 21 */     return retorno;
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/painel_permanente/itens/ItemPainelPermanenteMaquinas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */