/*    */ package ipqm.gsd.hidra.ihm.widgets.painel_permanente.itens;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.Maquina;
/*    */ import ipqm.gsd.componentes.nucleo.Mediador;
/*    */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemPainelPermanenteMaquinasConectadas
/*    */   extends ItemPainelPermanente
/*    */ {
/*    */   public ItemPainelPermanenteMaquinasConectadas() {
/* 15 */     super("Máquinas conectadas", 13);
/* 16 */     setFrequencia(5);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String geraValor(List<ObjetoDOMINIO> listaObjetos) {
/* 22 */     if (listaObjetos == null) {
/* 23 */       return "";
/*    */     }
/*    */     
/* 26 */     int maquinasConectadas = 0;
/*    */     
/* 28 */     if (Mediador.isSistemaIniciado()) {
/* 29 */       for (Maquina maquina : Mediador.getInstancia().getMapaMaquinasExistentes().values()) {
/* 30 */         if (maquina.getIdMaquina() != Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoRede().getMaquinaLocal().getIdMaquina() && 
/* 31 */           maquina.inferirEstadosConexao(Maquina.EnumEstadosConexao.CONECTADO)) {
/* 32 */           maquinasConectadas++;
/*    */         }
/*    */       } 
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 39 */     return maquinasConectadas + "/" + (Mediador.getInstancia().getMapaMaquinasExistentes().values().size() - 1);
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/painel_permanente/itens/ItemPainelPermanenteMaquinasConectadas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */