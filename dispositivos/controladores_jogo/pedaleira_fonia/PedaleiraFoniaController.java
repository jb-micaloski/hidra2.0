/*    */ package ipqm.gsd.hidra.ihm.dispositivos.controladores_jogo.pedaleira_fonia;
/*    */ 
/*    */ import ipqm.gsd.hidra.ihm.dispositivos.controladores_jogo.ControladorPadrao;
/*    */ import net.java.games.input.Event;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PedaleiraFoniaController
/*    */   extends ControladorPadrao
/*    */ {
/*    */   private final PedaleiraFoniaUser pedaleiraFoniaUser;
/* 15 */   private final String NOME_EVENTO = "Trigger";
/*    */   
/*    */   public PedaleiraFoniaController(String nomeDispositivo, PedaleiraFoniaUser pedaleiraFoniaUser) throws Exception {
/* 18 */     super(nomeDispositivo);
/* 19 */     this.pedaleiraFoniaUser = pedaleiraFoniaUser;
/*    */   }
/*    */ 
/*    */   
/*    */   public void notificar(Event event) {
/* 24 */     String nomeEvento = event.getComponent().getName();
/* 25 */     double valorEvento = event.getValue();
/*    */     
/* 27 */     if (nomeEvento.equals("Trigger"))
/* 28 */       if (valorEvento > 0.0D) {
/* 29 */         this.pedaleiraFoniaUser.pedalPressionado();
/*    */       } else {
/* 31 */         this.pedaleiraFoniaUser.pedalSolto();
/*    */       }  
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/dispositivos/controladores_jogo/pedaleira_fonia/PedaleiraFoniaController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */