/*    */ package ipqm.gsd.hidra.ihm.widgets.painel_permanente.itens;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*    */ import ipqm.gsd.hidra.ihm.projetos.administracao.VariaveisInterfaceAdministracao;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemPainelPermanenteFuncionalidades
/*    */   extends ItemPainelPermanente
/*    */ {
/*    */   public ItemPainelPermanenteFuncionalidades() {
/* 14 */     super("Funcionalidades", 11);
/*    */   }
/*    */ 
/*    */   
/*    */   public String geraValor(List<ObjetoDOMINIO> listaObjetos) {
/* 19 */     String retorno = "";
/* 20 */     retorno = VariaveisInterfaceAdministracao.totalFunc;
/* 21 */     return retorno;
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/painel_permanente/itens/ItemPainelPermanenteFuncionalidades.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */