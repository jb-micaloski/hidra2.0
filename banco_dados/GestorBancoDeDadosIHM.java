/*    */ package ipqm.gsd.hidra.ihm.banco_dados;
/*    */ 
/*    */ import ipqm.gsd.componentes.dominio.banco_dados.GestorBancoDeDadosEscrita;
/*    */ import ipqm.gsd.componentes.dominio.banco_dados.GestorBancoDeDadosLeitura;
/*    */ import java.sql.SQLException;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GestorBancoDeDadosIHM
/*    */ {
/*    */   private final GestorBancoDeDadosLeitura gestorBancoDeDadosLeitura;
/*    */   private final GestorBancoDeDadosEscrita gestorBancoDeDadosEscrita;
/*    */   
/*    */   public GestorBancoDeDadosIHM(boolean exibirSQL) throws SQLException {
/* 17 */     this.gestorBancoDeDadosEscrita = new GestorBancoDeDadosEscrita(exibirSQL);
/* 18 */     this.gestorBancoDeDadosLeitura = new GestorBancoDeDadosLeitura(exibirSQL);
/*    */   }
/*    */   
/*    */   public String getSchema() {
/* 22 */     return GestorBancoDeDadosLeitura.getInstanciaLeitura().getSchema();
/*    */   }
/*    */   
/*    */   public GestorBancoDeDadosLeitura getGestorBancoDeDadosLeitura() {
/* 26 */     return this.gestorBancoDeDadosLeitura;
/*    */   }
/*    */   
/*    */   public GestorBancoDeDadosEscrita getGestorBancoDeDadosEscrita() {
/* 30 */     return this.gestorBancoDeDadosEscrita;
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/banco_dados/GestorBancoDeDadosIHM.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */