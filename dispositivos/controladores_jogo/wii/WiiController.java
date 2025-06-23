/*    */ package ipqm.gsd.hidra.ihm.dispositivos.controladores_jogo.wii;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.ThreadPool;
/*    */ import ipqm.gsd.componentes.nucleo.log.Log;
/*    */ import wiiusej.WiiUseApiManager;
/*    */ import wiiusej.Wiimote;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WiiController
/*    */ {
/*    */   private final WiiControllerUser user;
/*    */   private final Wiimote wiimote;
/*    */   
/*    */   public WiiController(WiiControllerUser user, int id) {
/* 18 */     this.user = user;
/* 19 */     Wiimote[] wiimotes = WiiUseApiManager.getWiimotes(1, true);
/* 20 */     this.wiimote = wiimotes[0];
/* 21 */     configLeds(id);
/* 22 */     this.wiimote.activateIRTRacking();
/* 23 */     this.wiimote.activateMotionSensing();
/* 24 */     this.wiimote.addWiiMoteEventListeners(user);
/*    */   }
/*    */   
/*    */   public void activateRumble(int time) {
/* 28 */     ThreadPool.executar(new Rumble(time), "Rumble Wiimote");
/*    */   }
/*    */   
/*    */   public Wiimote getWiimote() {
/* 32 */     return this.wiimote;
/*    */   }
/*    */   
/*    */   private class Rumble
/*    */     implements Runnable {
/*    */     private final int time;
/*    */     
/*    */     public Rumble(int time) {
/* 40 */       this.time = time;
/*    */     }
/*    */ 
/*    */     
/*    */     public void run() {
/* 45 */       WiiController.this.getWiimote().activateRumble();
/*    */       try {
/* 47 */         Thread.sleep(this.time);
/* 48 */       } catch (InterruptedException ex) {
/* 49 */         Log.gravarLogExcecao("Thread interrompida, ocorreu algum erro no sleep", this, ex);
/*    */       } 
/* 51 */       WiiController.this.getWiimote().deactivateRumble();
/*    */     }
/*    */   }
/*    */   
/*    */   private void configLeds(int id) {
/* 56 */     switch (id) {
/*    */       case 1:
/* 58 */         this.wiimote.setLeds(true, false, false, false);
/*    */         break;
/*    */       case 2:
/* 61 */         this.wiimote.setLeds(false, true, false, false);
/*    */         break;
/*    */       case 3:
/* 64 */         this.wiimote.setLeds(false, false, true, false);
/*    */         break;
/*    */       case 4:
/* 67 */         this.wiimote.setLeds(false, false, false, true);
/*    */         break;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/dispositivos/controladores_jogo/wii/WiiController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */