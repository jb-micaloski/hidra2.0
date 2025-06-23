/*    */ package ipqm.gsd.hidra.ihm.simuladores;
/*    */ 
/*    */ import ipqm.gsd.componentes.dominio.dispositivos.controladores_jogo.ship_console.ControleTelegrafo;
/*    */ import ipqm.gsd.hidra.ihm.dispositivos.controladores_jogo.GestorDispositivos;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Governo
/*    */ {
/*    */   public static Governo instancia;
/*    */   
/*    */   private Governo() {
/* 15 */     ControleTelegrafo controleTelegrafo = new ControleTelegrafo();
/* 16 */     controleTelegrafo.iniciaVerificacaoRelogio();
/* 17 */     GestorDispositivos.getInstancia().setControleTelegrafo(controleTelegrafo);
/*    */   }
/*    */   
/*    */   public ControleTelegrafo getControleTelegrafo() {
/* 21 */     return GestorDispositivos.getInstancia().getControleTelegrafo();
/*    */   }
/*    */   
/*    */   public static Governo getInstancia() {
/* 25 */     if (instancia == null) {
/* 26 */       instancia = new Governo();
/*    */     }
/* 28 */     return instancia;
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/simuladores/Governo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */