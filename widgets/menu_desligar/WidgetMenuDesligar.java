/*     */ package ipqm.gsd.hidra.ihm.widgets.menu_desligar;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.hidra.ihm.controle.Aplicacao;
/*     */ import ipqm.gsd.hidra.ihm.controle.Tela;
/*     */ import ipqm.gsd.hidra.ihm.util.UtilDesempenho;
/*     */ import ipqm.gsd.hidra.ihm.visao.Cena;
/*     */ import ipqm.gsd.hidra.ihm.widgets.Widget;
/*     */ import ipqm.gsd.hidra.ihm.widgets.fundo_preto.WidgetFundoPreto;
/*     */ import java.io.IOException;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javafx.animation.FadeTransition;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.fxml.FXMLLoader;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.input.KeyCode;
/*     */ import javafx.scene.input.KeyEvent;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WidgetMenuDesligar
/*     */   extends Widget
/*     */ {
/*     */   private ControladorMenuDesligar controladorMenuDesligar;
/*     */   
/*     */   public WidgetMenuDesligar(Tela tela) {
/*  31 */     super(Widget.Tipo.MENU_DESLIGAR, tela);
/*  32 */     setExibirApenasTelaPrincipal(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void construir() {
/*  38 */     Log.gravarLogInstrucao("Construindo widget menu desligar", this);
/*     */     
/*  40 */     FXMLLoader fxmlLoader = new FXMLLoader(Widget.class.getResource("menu_desligar/MenuDesligar.fxml"));
/*     */     
/*     */     try {
/*  43 */       fxmlLoader.load();
/*  44 */     } catch (IOException ex) {
/*  45 */       Log.gravarLogExcecao("Erro ao carregar FXML do widget", this, ex);
/*     */     } 
/*     */     
/*  48 */     this.controladorMenuDesligar = (ControladorMenuDesligar)fxmlLoader.getController();
/*  49 */     this.controladorMenuDesligar.setWidgetMenuDesligar(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void exibir(Cena cena) {
/*  54 */     if (cena.getControlador().getAnchorPanePai() != null) {
/*  55 */       cena.getControlador().getAnchorPanePai().getChildren().add(this.controladorMenuDesligar.getAnchorPane());
/*  56 */       exibirFundoPreto();
/*  57 */       exibirMenuDesligar();
/*     */       
/*  59 */       Iterator<Tela> it = Aplicacao.getInstancia().getTelas().values().iterator();
/*  60 */       while (it.hasNext()) {
/*  61 */         Tela tela = it.next();
/*  62 */         if (!tela.isTelaPrincipal()) {
/*  63 */           WidgetFundoPreto fundo = (WidgetFundoPreto)tela.getWidget(Widget.Tipo.FUNDO_PRETO);
/*  64 */           if (fundo != null) {
/*  65 */             fundo.exibir(tela.getCenaAtual());
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void ocultar(Cena cena) {
/*  74 */     if (cena.getControlador().getAnchorPanePai() != null) {
/*  75 */       ocultarFundoPreto();
/*  76 */       ocultarMenuDesligar(cena.getControlador().getAnchorPanePai());
/*     */       
/*  78 */       Iterator<Tela> it = Aplicacao.getInstancia().getTelas().values().iterator();
/*  79 */       while (it.hasNext()) {
/*  80 */         Tela tela = it.next();
/*  81 */         if (!tela.isTelaPrincipal()) {
/*  82 */           WidgetFundoPreto fundo = (WidgetFundoPreto)tela.getWidget(Widget.Tipo.FUNDO_PRETO);
/*  83 */           if (fundo != null) {
/*  84 */             fundo.ocultar(tela.getCenaAtual());
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void exibirFundoPreto() {
/*  95 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.controladorMenuDesligar.getFundoPreto());
/*  96 */     fadeTransition.setFromValue(0.0D);
/*  97 */     fadeTransition.setToValue(0.9D);
/*  98 */     this.controladorMenuDesligar.getFundoPreto().setVisible(true);
/*  99 */     fadeTransition.play();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void ocultarFundoPreto() {
/* 106 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.controladorMenuDesligar.getFundoPreto());
/* 107 */     fadeTransition.setFromValue(0.9D);
/* 108 */     fadeTransition.setToValue(0.0D);
/* 109 */     fadeTransition.setOnFinished(arg0 -> this.controladorMenuDesligar.getFundoPreto().setVisible(false));
/*     */ 
/*     */     
/* 112 */     fadeTransition.play();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void exibirMenuDesligar() {
/* 119 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.controladorMenuDesligar.getMenuDesligar());
/* 120 */     fadeTransition.setFromValue(0.0D);
/* 121 */     fadeTransition.setToValue(1.0D);
/* 122 */     this.controladorMenuDesligar.getMenuDesligar().setVisible(true);
/* 123 */     fadeTransition.play();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ocultarMenuDesligar(AnchorPane anchorPane) {
/* 130 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.controladorMenuDesligar.getMenuDesligar());
/* 131 */     fadeTransition.setFromValue(1.0D);
/* 132 */     fadeTransition.setToValue(0.0D);
/* 133 */     fadeTransition.setOnFinished(arg0 -> {
/*     */           this.controladorMenuDesligar.getMenuDesligar().setVisible(false);
/*     */           anchorPane.getChildren().remove(this.controladorMenuDesligar.getAnchorPane());
/*     */         });
/* 137 */     fadeTransition.play();
/* 138 */     ocultarFundoPreto();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisivel() {
/* 143 */     return this.controladorMenuDesligar.getMenuDesligar().isVisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public void teclaPressionada(KeyEvent event) {
/* 148 */     super.teclaPressionada(event);
/* 149 */     switch (event.getCode()) {
/*     */       case L:
/* 151 */         if (event.isControlDown() && !event.isShiftDown()) {
/* 152 */           Aplicacao.getInstancia().logoff(null); break;
/* 153 */         }  if (event.isControlDown() && event.isShiftDown()) {
/* 154 */           Aplicacao.getInstancia().reiniciarAplicacao();
/*     */         }
/*     */         break;
/*     */       case ESCAPE:
/* 158 */         if (isVisivel()) {
/* 159 */           ocultar(getTela().getCenaAtual());
/*     */         }
/*     */         break;
/*     */       case C:
/* 163 */         if (event.isControlDown() && event.isShiftDown())
/* 164 */           Aplicacao.getInstancia().logoff("config"); 
/*     */         break;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void desenharObjetos(List<ObjetoDOMINIO> listaObjetos) {}
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/menu_desligar/WidgetMenuDesligar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */