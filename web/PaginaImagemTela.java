/*    */ package ipqm.gsd.hidra.ihm.web;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.log.Log;
/*    */ import ipqm.gsd.componentes.nucleo.web.Pagina;
/*    */ import ipqm.gsd.componentes.nucleo.web.ServidorHttp;
/*    */ import java.awt.AWTException;
/*    */ import java.awt.GraphicsEnvironment;
/*    */ import java.awt.Rectangle;
/*    */ import java.awt.Robot;
/*    */ import java.awt.image.BufferedImage;
/*    */ import java.io.ByteArrayOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import java.util.Map;
/*    */ import javax.imageio.ImageIO;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PaginaImagemTela
/*    */   extends Pagina
/*    */ {
/*    */   public PaginaImagemTela(ServidorHttp servidorHttp) {
/* 28 */     super(servidorHttp, "Tela");
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] contruir(Map<String, String> params) throws UnsupportedEncodingException {
/* 33 */     byte[] imageBytes = null;
/*    */     try {
/*    */       Rectangle rectangle;
/* 36 */       Robot robot = new Robot();
/* 37 */       GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
/*    */ 
/*    */       
/*    */       try {
/* 41 */         rectangle = new Rectangle(ge.getScreenDevices()[0].getDisplayMode().getWidth(), ge.getScreenDevices()[0].getDisplayMode().getHeight());
/* 42 */       } catch (Exception ex) {
/* 43 */         Log.gravarLogExcecao("Erro ao gerar retangulo para a captura de tela", this, ex);
/* 44 */         rectangle = ge.getMaximumWindowBounds();
/*    */       } 
/*    */       
/* 47 */       if (rectangle != null) {
/* 48 */         BufferedImage captura = robot.createScreenCapture(rectangle);
/* 49 */         try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
/* 50 */           ImageIO.write(captura, "png", bos);
/* 51 */           imageBytes = bos.toByteArray();
/* 52 */         } catch (IOException e) {
/* 53 */           Log.gravarLogExcecao("Erro ao converter imagem", this, e);
/*    */         } 
/*    */       } 
/* 56 */     } catch (AWTException ex) {
/* 57 */       Log.gravarLogExcecao("Erro ao capturar imagem", this, ex);
/*    */     } 
/*    */     
/* 60 */     return imageBytes;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getConteudo(Map<String, String> params) {
/* 65 */     throw new UnsupportedOperationException("Not supported yet.");
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/web/PaginaImagemTela.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */