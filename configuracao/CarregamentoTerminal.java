/*    */ package ipqm.gsd.hidra.ihm.configuracao;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.log.Log;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CarregamentoTerminal
/*    */   implements IHMCarregamento
/*    */ {
/*    */   private int passoAtual;
/*    */   
/*    */   public void exibirIHM() {}
/*    */   
/*    */   public void ocultarIHM() {}
/*    */   
/*    */   public void finalizaCarregamento() {}
/*    */   
/*    */   public void erro(Exception ex) {
/* 29 */     System.out.println("Ocorreu algum erro ao iniciar a aplicação: " + ex.getMessage());
/*    */   }
/*    */ 
/*    */   
/*    */   public void notificarCarregamento(int passoAtual, String descricaoPasso) {
/* 34 */     if (passoAtual == 0) {
/* 35 */       String texto = this.passoAtual + " - " + descricaoPasso;
/* 36 */       Log.gravarLogInterface("Carregamento: " + texto, this);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void iniciar() {}
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/configuracao/CarregamentoTerminal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */