/*    */ package ipqm.gsd.hidra.ihm.util;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.ThreadPool;
/*    */ import ipqm.gsd.componentes.nucleo.log.Log;
/*    */ import java.io.BufferedReader;
/*    */ import java.io.File;
/*    */ import java.io.FileNotFoundException;
/*    */ import java.io.FileReader;
/*    */ import java.io.IOException;
/*    */ import java.util.concurrent.TimeUnit;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class Tail
/*    */ {
/*    */   private BufferedReader input;
/* 21 */   private long sleepTime = 100L;
/*    */   private String currentLine;
/*    */   
/*    */   public Tail(File arquivo) {
/*    */     try {
/* 26 */       this.input = new BufferedReader(new FileReader(arquivo));
/* 27 */       ThreadPool.agendarExecucaoPeriodica(new Atualizador(), this.sleepTime, TimeUnit.MILLISECONDS, "Tail de arquivo");
/* 28 */     } catch (FileNotFoundException ex) {
/* 29 */       Log.gravarLogExcecao("Erro ao abrir tail do arquivo " + arquivo, Tail.class, ex);
/*    */     } 
/*    */   }
/*    */   
/*    */   public abstract void novaLinha(String paramString);
/*    */   
/*    */   private class Atualizador implements Runnable {
/*    */     private Atualizador() {}
/*    */     
/*    */     public void run() {
/*    */       try {
/* 40 */         if ((Tail.this.currentLine = Tail.this.input.readLine()) != null) {
/* 41 */           Tail.this.novaLinha(Tail.this.currentLine);
/*    */         }
/* 43 */       } catch (IOException ex) {
/* 44 */         Log.gravarLogExcecao("Erro ao abrir tail do arquivo", this, ex);
/*    */         try {
/* 46 */           Tail.this.input.close();
/* 47 */         } catch (IOException ex1) {
/* 48 */           Log.gravarLogExcecao("Erro ao abrir tail do arquivo", this, ex);
/*    */         } 
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/util/Tail.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */