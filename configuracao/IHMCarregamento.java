/*    */ package ipqm.gsd.hidra.ihm.configuracao;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.IHM;
/*    */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface IHMCarregamento
/*    */   extends IHM
/*    */ {
/*    */   void iniciar();
/*    */   
/*    */   void exibirIHM();
/*    */   
/*    */   void ocultarIHM();
/*    */   
/*    */   void finalizaCarregamento();
/*    */   
/*    */   void erro(Exception paramException);
/*    */   
/*    */   void notificarCarregamento(int paramInt, String paramString);
/*    */   
/*    */   default void configurar() {
/* 30 */     throw new UnsupportedOperationException("Not supported yet.");
/*    */   }
/*    */ 
/*    */   
/*    */   default void configurarObjetos() {
/* 35 */     throw new UnsupportedOperationException("Not supported yet.");
/*    */   }
/*    */ 
/*    */   
/*    */   default void configurarComportamentoJanela() {
/* 40 */     throw new UnsupportedOperationException("Not supported yet.");
/*    */   }
/*    */ 
/*    */   
/*    */   default void configurarEstadoPadrao() {
/* 45 */     throw new UnsupportedOperationException("Not supported yet.");
/*    */   }
/*    */ 
/*    */   
/*    */   default void configurarTopicoAjuda() {
/* 50 */     throw new UnsupportedOperationException("Not supported yet.");
/*    */   }
/*    */ 
/*    */   
/*    */   default void desenharObjetos(List<ObjetoDOMINIO> listaObjetos) {
/* 55 */     throw new UnsupportedOperationException("Not supported yet.");
/*    */   }
/*    */ 
/*    */   
/*    */   default void notificaEstadoSincronismo(boolean estadoSincronismo) {
/* 60 */     throw new UnsupportedOperationException("Not supported yet.");
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/configuracao/IHMCarregamento.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */