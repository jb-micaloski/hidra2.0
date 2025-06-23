/*    */ package ipqm.gsd.hidra.ihm.dispositivos.controladores_jogo;
/*    */ 
/*    */ import java.io.BufferedReader;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStreamReader;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GestorDispositivosUsb
/*    */ {
/*    */   public void cadastrarDispositivoUsbInteresse() {}
/*    */   
/*    */   public String listarUSB() throws IOException, InterruptedException {
/* 66 */     StringBuilder output = new StringBuilder();
/*    */ 
/*    */     
/* 69 */     Process p = Runtime.getRuntime().exec("lsusb");
/* 70 */     p.waitFor();
/* 71 */     try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
/*    */       String line;
/* 73 */       while ((line = reader.readLine()) != null) {
/* 74 */         output.append(line.substring(33)).append("\n");
/*    */       }
/*    */     } 
/* 77 */     p.destroy();
/* 78 */     return output.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/dispositivos/controladores_jogo/GestorDispositivosUsb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */