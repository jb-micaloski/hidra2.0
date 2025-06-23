/*    */ package ipqm.gsd.hidra.ihm.camadas.filtros;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum EnumFiltroPontosHistoricos
/*    */ {
/*  8 */   SEMPRE("Sempre", true), NUNCA("Nunca", false), AO_SELECIONAR("Ao Selecionar", false);
/*    */   
/*    */   private boolean exibir;
/*    */   private String descricao;
/*    */   
/*    */   EnumFiltroPontosHistoricos(String descricao, boolean exibir) {
/* 14 */     this.descricao = descricao;
/* 15 */     this.exibir = exibir;
/*    */   }
/*    */   
/*    */   public String getDescricao() {
/* 19 */     return this.descricao;
/*    */   }
/*    */   
/*    */   public boolean isExibir() {
/* 23 */     return this.exibir;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 28 */     return getDescricao();
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/camadas/filtros/EnumFiltroPontosHistoricos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */