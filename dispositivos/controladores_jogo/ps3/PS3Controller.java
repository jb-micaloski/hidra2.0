/*    */ package ipqm.gsd.hidra.ihm.dispositivos.controladores_jogo.ps3;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.dispositivos.controladores_jogo.ps3.PS3ControllerUser;
/*    */ import ipqm.gsd.hidra.ihm.dispositivos.controladores_jogo.ControladorPadrao;
/*    */ import net.java.games.input.Event;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PS3Controller
/*    */   extends ControladorPadrao
/*    */ {
/*    */   private final PS3ControllerUser user;
/*    */   
/*    */   public PS3Controller(PS3ControllerUser user) throws Exception {
/* 15 */     super(true);
/* 16 */     this.user = user;
/*    */   }
/*    */ 
/*    */   
/*    */   public void notificar(Event event) {
/* 21 */     System.out.println(event.toString());
/* 22 */     if (event.getComponent().isAnalog()) {
/* 23 */       if (event.getComponent().getName().equals("x") || event.getComponent().getName().equals("y")) {
/* 24 */         this.user.leftStick(event.getComponent().getName(), event.getValue());
/*    */       }
/* 26 */       if (event.getComponent().getName().equals("rx") || event.getComponent().getName().equals("ry")) {
/* 27 */         this.user.leftStick(event.getComponent().getName(), event.getValue());
/*    */       
/*    */       }
/*    */     }
/* 31 */     else if (event.getValue() == 0.0D) {
/* 32 */       switch (event.getComponent().getName()) {
/*    */         case "Dead":
/* 34 */           this.user.square();
/*    */           break;
/*    */         case "Trigger":
/* 37 */           this.user.select();
/*    */           break;
/*    */         case "Unknown":
/* 40 */           this.user.triangle();
/*    */           break;
/*    */         case "Top":
/* 43 */           this.user.start();
/*    */           break;
/*    */         case "Base 5":
/* 46 */           this.user.l1();
/*    */           break;
/*    */         case "Base 6":
/* 49 */           this.user.r1();
/*    */           break;
/*    */         case "Base 4":
/* 52 */           this.user.r2();
/*    */           break;
/*    */         case "Base 3":
/* 55 */           this.user.l2();
/*    */           break;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/dispositivos/controladores_jogo/ps3/PS3Controller.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */