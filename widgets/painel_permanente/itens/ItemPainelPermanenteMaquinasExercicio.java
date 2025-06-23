/*    */ package ipqm.gsd.hidra.ihm.widgets.painel_permanente.itens;
/*    */ 
/*    */ import ipqm.gsd.componentes.dominio.contexto.ContextoTatico;
/*    */ import ipqm.gsd.componentes.dominio.teatro_operacao.TeatroOperacao;
/*    */ import ipqm.gsd.componentes.nucleo.Maquina;
/*    */ import ipqm.gsd.componentes.nucleo.Mediador;
/*    */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemPainelPermanenteMaquinasExercicio
/*    */   extends ItemPainelPermanente
/*    */ {
/*    */   public ItemPainelPermanenteMaquinasExercicio() {
/* 17 */     super("Máquinas no exercício", 26);
/* 18 */     setFrequencia(5);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String geraValor(List<ObjetoDOMINIO> listaObjetos) {
/* 24 */     if (listaObjetos == null) {
/* 25 */       return "";
/*    */     }
/*    */     
/* 28 */     int maquinasConectadas = 0;
/* 29 */     int maquinasTotal = 0;
/*    */     
/* 31 */     if (Mediador.isSistemaIniciado() && ((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getGrupoMaquinas() != null) {
/* 32 */       TeatroOperacao to = ((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getTeatroOperacao();
/*    */       
/* 34 */       maquinasTotal = to.getMapaMaquinaCondicao().keySet().size() - 1;
/*    */       
/* 36 */       for (Maquina maquina : to.getMapaMaquinaCondicao().keySet()) {
/* 37 */         if (maquina.getIdMaquina() != Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoRede().getMaquinaLocal().getIdMaquina() && 
/* 38 */           maquina.inferirEstadosConexao(Maquina.EnumEstadosConexao.EXECUTANDO)) {
/* 39 */           maquinasConectadas++;
/*    */         }
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 46 */     return maquinasConectadas + "/" + maquinasTotal;
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/painel_permanente/itens/ItemPainelPermanenteMaquinasExercicio.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */