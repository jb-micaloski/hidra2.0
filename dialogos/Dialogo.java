/*    */ package ipqm.gsd.hidra.ihm.dialogos;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.log.Log;
/*    */ import ipqm.gsd.hidra.ihm.controle.Controlador;
/*    */ import java.io.IOException;
/*    */ import javafx.fxml.FXMLLoader;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class Dialogo
/*    */ {
/*    */   protected String titulo;
/*    */   protected String descricao;
/*    */   protected Controlador controladorPai;
/*    */   protected FXMLLoader fXMLLoader;
/*    */   
/*    */   public Dialogo(String titulo, String descricao, Controlador controladorPai, String fxml) {
/* 21 */     this.titulo = titulo;
/* 22 */     this.descricao = descricao;
/* 23 */     this.controladorPai = controladorPai;
/*    */     
/* 25 */     Log.gravarLogInstrucao("Construindo dialogo: " + titulo + " / " + descricao, this);
/*    */     
/* 27 */     this.fXMLLoader = new FXMLLoader(Dialogo.class.getResource(fxml));
/*    */     
/*    */     try {
/* 30 */       this.fXMLLoader.load();
/* 31 */     } catch (IOException ex) {
/* 32 */       Log.gravarLogExcecao("Erro ao carregar FXML dialogo " + fxml, this, ex);
/*    */     } 
/*    */   }
/*    */   
/*    */   public Controlador getControladorPai() {
/* 37 */     return this.controladorPai;
/*    */   }
/*    */   
/*    */   public abstract void exibir();
/*    */   
/*    */   public abstract void ocultar();
/*    */   
/*    */   public abstract void acao(AcaoDialogo paramAcaoDialogo);
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/dialogos/Dialogo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */