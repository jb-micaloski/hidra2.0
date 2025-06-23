/*     */ package ipqm.gsd.hidra.ihm.dispositivos.controladores_jogo;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.ThreadPool;
/*     */ import ipqm.gsd.componentes.nucleo.dispositivos.controladores_jogo.ControladorJogo;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.util.concurrent.ScheduledFuture;
/*     */ import net.java.games.input.Component;
/*     */ import net.java.games.input.Controller;
/*     */ import net.java.games.input.ControllerEnvironment;
/*     */ import net.java.games.input.Event;
/*     */ import net.java.games.input.EventQueue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ControladorPadrao
/*     */   implements ControladorJogo
/*     */ {
/*     */   private static final String CAMINHO_AMBIENTE_CONTROLE = "net.java.games.input.DefaultControllerEnvironment";
/*     */   protected Controller controller;
/*     */   protected ScheduledFuture polling;
/*     */   
/*     */   public ControladorPadrao(boolean showDevice) throws Exception {
/*  26 */     ControllerEnvironment ce = criarAmbienteControlesPadrao();
/*  27 */     this.controller = null;
/*  28 */     for (Controller ca : ce.getControllers()) {
/*  29 */       System.out.println(ca.getType());
/*  30 */       if (ca.getType() == Controller.Type.STICK) {
/*  31 */         this.controller = ca;
/*     */       }
/*     */     } 
/*     */     
/*  35 */     if (this.controller == null) {
/*  36 */       throw new Exception("Found no controllers.");
/*     */     }
/*     */     
/*  39 */     Component[] components = this.controller.getComponents();
/*  40 */     if (showDevice) {
/*     */       
/*  42 */       System.out.println(this.controller.getName());
/*  43 */       System.out.println("Type: " + this.controller.getType().toString());
/*     */       
/*  45 */       System.out.println("Component Count: " + components.length);
/*  46 */       for (int j = 0; j < components.length; j++) {
/*     */         
/*  48 */         System.out.println("Component " + j + ": " + components[j].getName());
/*  49 */         System.out.println("    Identifier: " + components[j].getIdentifier().getName());
/*  50 */         System.out.print("    ComponentType: ");
/*  51 */         if (components[j].isRelative()) {
/*  52 */           System.out.print("Relative");
/*     */         } else {
/*  54 */           System.out.print("Absolute");
/*     */         } 
/*  56 */         if (components[j].isAnalog()) {
/*  57 */           System.out.print(" Analog");
/*     */         } else {
/*  59 */           System.out.print(" Digital");
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  64 */     this.polling = ThreadPool.agendarExecucaoPeriodica(new Polling(), 50L, "Polling do controlador de jogo");
/*     */   }
/*     */   
/*     */   public ControladorPadrao(String nomeDispositivo) throws Exception {
/*  68 */     ControllerEnvironment ce = criarAmbienteControlesPadrao();
/*  69 */     this.controller = null;
/*  70 */     for (Controller ca : ce.getControllers()) {
/*     */       
/*  72 */       if (ca.getName().trim().equalsIgnoreCase(nomeDispositivo.trim()) && ca.getType() == Controller.Type.STICK) {
/*  73 */         this.controller = ca;
/*     */       }
/*     */     } 
/*     */     
/*  77 */     if (this.controller == null) {
/*  78 */       throw new Exception("Nenhum dispositivo encontrado com o nome " + nomeDispositivo);
/*     */     }
/*  80 */     this.polling = ThreadPool.agendarExecucaoPeriodica(new Polling(), 50L, "Polling do controlador de jogo");
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract void notificar(Event paramEvent);
/*     */   
/*     */   public Controller getController() {
/*  87 */     return this.controller;
/*     */   }
/*     */ 
/*     */   
/*     */   private static ControllerEnvironment criarAmbienteControlesPadrao() throws ReflectiveOperationException {
/*  92 */     Constructor<?> constructor = Class.forName("net.java.games.input.DefaultControllerEnvironment").getDeclaredConstructors()[0];
/*  93 */     constructor.setAccessible(true);
/*  94 */     return (ControllerEnvironment)constructor.newInstance(new Object[0]);
/*     */   }
/*     */   
/*     */   private class Polling implements Runnable {
/*     */     private Polling() {}
/*     */     
/*     */     public void run() {
/* 101 */       if (ControladorPadrao.this.controller.poll()) {
/* 102 */         EventQueue queue = ControladorPadrao.this.controller.getEventQueue();
/* 103 */         Event event = new Event();
/* 104 */         while (queue.getNextEvent(event)) {
/* 105 */           ControladorPadrao.this.notificar(event);
/*     */         }
/*     */       } else {
/*     */         
/* 109 */         ControladorPadrao.this.polling.cancel(true);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/dispositivos/controladores_jogo/ControladorPadrao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */