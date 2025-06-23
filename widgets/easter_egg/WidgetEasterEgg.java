/*    */ package ipqm.gsd.hidra.ihm.widgets.easter_egg;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.Maquina;
/*    */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*    */ import ipqm.gsd.componentes.nucleo.comando_remoto.ComandoRemoto;
/*    */ import ipqm.gsd.componentes.nucleo.comando_remoto.GestorComandoRemoto;
/*    */ import ipqm.gsd.componentes.nucleo.comando_remoto.comandos.EasterEgg;
/*    */ import ipqm.gsd.componentes.nucleo.log.Log;
/*    */ import ipqm.gsd.componentes.nucleo.util.depuracao.Debug;
/*    */ import ipqm.gsd.hidra.ihm.controle.Tela;
/*    */ import ipqm.gsd.hidra.ihm.util.Tocador;
/*    */ import ipqm.gsd.hidra.ihm.visao.Cena;
/*    */ import ipqm.gsd.hidra.ihm.widgets.Widget;
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import javafx.fxml.FXMLLoader;
/*    */ import javafx.scene.input.KeyCode;
/*    */ import javafx.scene.input.KeyEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WidgetEasterEgg
/*    */   extends Widget
/*    */ {
/*    */   private ControladorEasterEgg controladorEasterEgg;
/*    */   private List<Maquina> maquinasExistentes;
/*    */   
/*    */   public WidgetEasterEgg(Tela tela) {
/* 30 */     super(Widget.Tipo.EASTER_EGG, tela);
/*    */   }
/*    */ 
/*    */   
/*    */   public void construir() {
/* 35 */     Log.gravarLogInstrucao("Construindo widget easter egg", this);
/*    */     
/* 37 */     FXMLLoader fxmlLoader = new FXMLLoader(Widget.class.getResource("easter_egg/EasterEgg.fxml"));
/*    */     
/*    */     try {
/* 40 */       fxmlLoader.load();
/* 41 */     } catch (IOException ex) {
/* 42 */       Log.gravarLogExcecao("Erro ao carregar FXML do widget", this, ex);
/*    */     } 
/*    */     
/* 45 */     this.controladorEasterEgg = (ControladorEasterEgg)fxmlLoader.getController();
/* 46 */     this.controladorEasterEgg.setWidgetEasterEgg(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void exibir(Cena cena) {
/* 52 */     if (cena.getControlador().getAnchorPanePai() != null) {
/* 53 */       (new Tocador("outros" + File.separator + "oque.mp3", 0.8D)).reproduzirUmaVez();
/* 54 */       if (!this.controladorEasterEgg.getAnchorPaneEasterEgg().isVisible()) {
/* 55 */         cena.getControlador().getAnchorPanePai().getChildren().add(this.controladorEasterEgg.getAnchorPaneEasterEgg());
/* 56 */         this.controladorEasterEgg.getAnchorPaneEasterEgg().setVisible(true);
/* 57 */         this.controladorEasterEgg.oque();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void ocultar(Cena cena) {
/* 64 */     if (cena.getControlador().getAnchorPanePai() != null) {
/* 65 */       cena.getControlador().getAnchorPanePai().getChildren().remove(this.controladorEasterEgg.getAnchorPaneEasterEgg());
/* 66 */       this.controladorEasterEgg.getAnchorPaneEasterEgg().setVisible(false);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isVisivel() {
/* 72 */     return this.controladorEasterEgg.getAnchorPaneEasterEgg().isVisible();
/*    */   }
/*    */ 
/*    */   
/*    */   public void teclaPressionada(KeyEvent event) {
/* 77 */     super.teclaPressionada(event);
/* 78 */     if (!Debug.isDebug()) {
/*    */       return;
/*    */     }
/* 81 */     switch (event.getCode()) {
/*    */       case J:
/* 83 */         if (event.isControlDown()) {
/* 84 */           if (event.isShiftDown()) {
/* 85 */             if (this.maquinasExistentes == null) {
/*    */               try {
/* 87 */                 this.maquinasExistentes = (new Maquina()).listarTodos();
/* 88 */               } catch (Exception ex) {
/* 89 */                 Log.gravarLogExcecao("Erro ao listar maquinas", this, ex);
/*    */               } 
/*    */             }
/*    */             
/* 93 */             for (Maquina maquina : this.maquinasExistentes) {
/* 94 */               EasterEgg easterEgg = new EasterEgg(maquina.getHostname());
/* 95 */               GestorComandoRemoto.getInstancia().enviar((ComandoRemoto)easterEgg);
/*    */             } 
/*    */             break;
/*    */           } 
/* 99 */           exibir(getTela().getCenaAtual());
/*    */         } 
/*    */         break;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void desenharObjetos(List<ObjetoDOMINIO> listaObjetos) {}
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/easter_egg/WidgetEasterEgg.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */