/*     */ package ipqm.gsd.hidra.ihm.widgets.ajuda_contexto;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.notificador.Notificador;
/*     */ import ipqm.gsd.hidra.ihm.controle.ControladorFuncionalidadesInternas;
/*     */ import ipqm.gsd.hidra.ihm.controle.Tela;
/*     */ import ipqm.gsd.hidra.ihm.util.UtilDesempenho;
/*     */ import ipqm.gsd.hidra.ihm.visao.Cena;
/*     */ import ipqm.gsd.hidra.ihm.widgets.Widget;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import javafx.animation.FadeTransition;
/*     */ import javafx.animation.TranslateTransition;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.fxml.FXMLLoader;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.input.KeyCode;
/*     */ import javafx.scene.input.KeyEvent;
/*     */ import javafx.scene.web.WebEngine;
/*     */ import javafx.scene.web.WebView;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WidgetAjuda
/*     */   extends Widget
/*     */ {
/*     */   private ControladorAjudaContexto controladorAjudaContexto;
/*     */   
/*     */   public WidgetAjuda(Tela tela) {
/*  32 */     super(Widget.Tipo.AJUDA, tela);
/*  33 */     setExibirApenasTelaPrincipal(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void construir() {
/*  38 */     Log.gravarLogInstrucao("Construindo widget ajuda de contexto", this);
/*     */     
/*  40 */     FXMLLoader fxmlLoader = new FXMLLoader(Widget.class.getResource("ajuda_contexto/AjudaContexto.fxml"));
/*     */     
/*     */     try {
/*  43 */       fxmlLoader.load();
/*  44 */     } catch (IOException ex) {
/*  45 */       Log.gravarLogExcecao("Erro ao carregar FXML do widget", this, ex);
/*     */     } 
/*     */     
/*  48 */     this.controladorAjudaContexto = (ControladorAjudaContexto)fxmlLoader.getController();
/*  49 */     this.controladorAjudaContexto.setWidgetAjuda(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void exibir(Cena cena) {
/*  54 */     TopicoAjuda topicoAjuda = cena.getControlador().getTopicoAjuda();
/*  55 */     exibirTopico(cena.getControlador().getTela().getCenaAtual(), topicoAjuda);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void exibirTopico(Cena cena, TopicoAjuda topicoAjuda) {
/*  65 */     if (topicoAjuda != null) {
/*  66 */       cena.getControlador().getAnchorPanePai().getChildren().add(this.controladorAjudaContexto.getAnchorPaneAjuda());
/*  67 */       exibirFundoPreto();
/*  68 */       exibirAjuda(topicoAjuda);
/*     */     } else {
/*  70 */       cena.getControlador().getNome();
/*  71 */       Notificador.informacao("Ajuda não disponível", "Esta funcionalidade não possui um tópico de ajuda associado");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void ocultar(Cena cena) {
/*  77 */     ocultarFundoPreto();
/*  78 */     ocultarAjuda(cena);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void exibirFundoPreto() {
/*  85 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.controladorAjudaContexto.getFundoPreto());
/*  86 */     fadeTransition.setFromValue(0.0D);
/*  87 */     fadeTransition.setToValue(0.9D);
/*  88 */     this.controladorAjudaContexto.getFundoPreto().setVisible(true);
/*  89 */     fadeTransition.play();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void ocultarFundoPreto() {
/*  96 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.controladorAjudaContexto.getFundoPreto());
/*  97 */     fadeTransition.setFromValue(0.9D);
/*  98 */     fadeTransition.setToValue(0.0D);
/*  99 */     fadeTransition.setOnFinished(arg0 -> this.controladorAjudaContexto.getFundoPreto().setVisible(false));
/*     */ 
/*     */     
/* 102 */     fadeTransition.play();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void exibirAjuda(TopicoAjuda ajuda) {
/* 112 */     WebView browser = new WebView();
/* 113 */     WebEngine webEngine = browser.getEngine();
/* 114 */     webEngine.load(ajuda.getUrl());
/*     */     
/* 116 */     this.controladorAjudaContexto.getFundoAjuda().getChildren().add(browser);
/*     */     
/* 118 */     this.controladorAjudaContexto.getFundoAjuda().setVisible(true);
/*     */     
/* 120 */     TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(500.0D), (Node)this.controladorAjudaContexto.getFundoAjuda());
/* 121 */     tt.setFromY(1000.0D);
/* 122 */     tt.setToY(0.0D);
/* 123 */     tt.setAutoReverse(false);
/* 124 */     tt.play();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void ocultarAjuda(Cena cena) {
/* 132 */     TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(500.0D), (Node)this.controladorAjudaContexto.getFundoAjuda());
/* 133 */     tt.setFromY(0.0D);
/* 134 */     tt.setToY(1000.0D);
/* 135 */     tt.setAutoReverse(false);
/* 136 */     tt.setOnFinished(arg0 -> {
/*     */           this.controladorAjudaContexto.getFundoAjuda().setVisible(false);
/*     */           cena.getControlador().getAnchorPanePai().getChildren().remove(this.controladorAjudaContexto.getAnchorPaneAjuda());
/*     */         });
/* 140 */     tt.play();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisivel() {
/* 145 */     return this.controladorAjudaContexto.getFundoAjuda().isVisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public void teclaPressionada(KeyEvent event) {
/* 150 */     super.teclaPressionada(event);
/* 151 */     switch (event.getCode()) {
/*     */       case ESCAPE:
/* 153 */         if (isVisivel()) {
/* 154 */           ocultar(getTela().getCenaAtual());
/*     */         }
/*     */         break;
/*     */       case F1:
/* 158 */         if (!isVisivel()) {
/* 159 */           if (getTela().getCenaAtual().getControlador() instanceof ControladorFuncionalidadesInternas || 
/* 160 */             getTela().getCenaAtual().getControlador() instanceof ipqm.gsd.hidra.ihm.projetos.geral.formularios.controladores.ControladorFormularioPadrao) {
/* 161 */             exibir((Cena)((ControladorFuncionalidadesInternas)getTela().getCenaAtual().getControlador()).getCenaAtual()); break;
/*     */           } 
/* 163 */           exibir(getTela().getCenaAtual());
/*     */           break;
/*     */         } 
/* 166 */         ocultar(getTela().getCenaAtual());
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void desenharObjetos(List<ObjetoDOMINIO> listaObjetos) {}
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/ajuda_contexto/WidgetAjuda.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */