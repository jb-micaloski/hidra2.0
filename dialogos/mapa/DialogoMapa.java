/*     */ package ipqm.gsd.hidra.ihm.dialogos.mapa;
/*     */ 
/*     */ import ipqm.gsd.hidra.ihm.controle.Aplicacao;
/*     */ import ipqm.gsd.hidra.ihm.controle.Controlador;
/*     */ import ipqm.gsd.hidra.ihm.controle.Tela;
/*     */ import ipqm.gsd.hidra.ihm.dialogos.Dialogo;
/*     */ import ipqm.gsd.hidra.ihm.util.UtilDesempenho;
/*     */ import ipqm.gsd.hidra.ihm.widgets.Widget;
/*     */ import ipqm.gsd.hidra.ihm.widgets.fundo_preto.WidgetFundoPreto;
/*     */ import java.util.Iterator;
/*     */ import javafx.animation.Animation;
/*     */ import javafx.animation.FadeTransition;
/*     */ import javafx.animation.ParallelTransition;
/*     */ import javafx.animation.ScaleTransition;
/*     */ import javafx.application.Platform;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.scene.Node;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class DialogoMapa
/*     */   extends Dialogo
/*     */ {
/*     */   private final ControladorDialogoMapa controladorDialogoMapa;
/*     */   
/*     */   public ControladorDialogoMapa getControladorDialogoMapa() {
/*  29 */     return this.controladorDialogoMapa;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public DialogoMapa(String titulo, String descricao, Controlador controladorPai) {
/*  35 */     super(titulo, descricao, controladorPai, "mapa/Mapa.fxml");
/*  36 */     this.controladorDialogoMapa = (ControladorDialogoMapa)this.fXMLLoader.getController();
/*  37 */     this.controladorDialogoMapa.setDialogo(this);
/*  38 */     exibir();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void exibir() {
/*  44 */     getControladorDialogoMapa().setTitulo(this.titulo);
/*  45 */     getControladorDialogoMapa().setDescricao(this.descricao);
/*  46 */     exibirFundoPreto();
/*  47 */     exibirConfirma();
/*     */     
/*  49 */     Iterator<Tela> it = Aplicacao.getInstancia().getTelas().values().iterator();
/*  50 */     while (it.hasNext()) {
/*  51 */       Tela tela = it.next();
/*  52 */       if (tela.getNumTela() != getControladorPai().getTela().getNumTela()) {
/*  53 */         WidgetFundoPreto fundo = (WidgetFundoPreto)tela.getWidget(Widget.Tipo.FUNDO_PRETO);
/*  54 */         if (fundo != null) {
/*  55 */           fundo.exibir(tela.getCenaAtual());
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void ocultar() {
/*  64 */     getControladorDialogoMapa().setTitulo(null);
/*  65 */     getControladorDialogoMapa().setDescricao(null);
/*     */     
/*  67 */     ocultarFundoPreto();
/*  68 */     ocultarConfirma();
/*     */     
/*  70 */     Iterator<Tela> it = Aplicacao.getInstancia().getTelas().values().iterator();
/*  71 */     while (it.hasNext()) {
/*  72 */       Tela tela = it.next();
/*  73 */       if (tela.getNumTela() != getControladorPai().getTela().getNumTela()) {
/*  74 */         WidgetFundoPreto fundo = (WidgetFundoPreto)tela.getWidget(Widget.Tipo.FUNDO_PRETO);
/*  75 */         if (fundo != null) {
/*  76 */           fundo.ocultar(tela.getCenaAtual());
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void exibirConfirma() {
/*  86 */     Platform.runLater(() -> {
/*     */           this.controladorPai.getAnchorPanePai().getChildren().add(getControladorDialogoMapa().getAnchorPaneDialogo());
/*     */           
/*     */           getControladorDialogoMapa().getFundoMapa().setVisible(true);
/*     */         });
/*  91 */     ScaleTransition st = new ScaleTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)getControladorDialogoMapa().getFundoMapa());
/*  92 */     st.setFromX(1.2D);
/*  93 */     st.setFromY(1.2D);
/*  94 */     st.setToX(1.0D);
/*  95 */     st.setToY(1.0D);
/*     */     
/*  97 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)getControladorDialogoMapa().getFundoMapa());
/*  98 */     fadeTransition.setFromValue(0.0D);
/*  99 */     fadeTransition.setToValue(1.0D);
/*     */     
/* 101 */     ParallelTransition pt = new ParallelTransition(new Animation[] { (Animation)st, (Animation)fadeTransition });
/* 102 */     pt.play();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void ocultarConfirma() {
/* 110 */     if (getControladorDialogoMapa().getFundoMapa().isVisible()) {
/* 111 */       FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)getControladorDialogoMapa().getFundoMapa());
/* 112 */       fadeTransition.setFromValue(1.0D);
/* 113 */       fadeTransition.setToValue(0.0D);
/* 114 */       fadeTransition.setOnFinished(arg0 -> {
/*     */             getControladorDialogoMapa().getFundoMapa().setVisible(false);
/*     */             this.controladorPai.getAnchorPanePai().getChildren().remove(getControladorDialogoMapa().getAnchorPaneDialogo());
/*     */           });
/* 118 */       fadeTransition.play();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void exibirFundoPreto() {
/* 126 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)getControladorDialogoMapa().getFundoPreto());
/* 127 */     fadeTransition.setFromValue(0.0D);
/* 128 */     fadeTransition.setToValue(0.9D);
/* 129 */     getControladorDialogoMapa().getFundoPreto().setVisible(true);
/* 130 */     fadeTransition.play();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void ocultarFundoPreto() {
/* 137 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)getControladorDialogoMapa().getFundoPreto());
/* 138 */     fadeTransition.setFromValue(0.9D);
/* 139 */     fadeTransition.setToValue(0.0D);
/* 140 */     fadeTransition.setOnFinished(arg0 -> getControladorDialogoMapa().getFundoPreto().setVisible(false));
/*     */ 
/*     */     
/* 143 */     fadeTransition.play();
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/dialogos/mapa/DialogoMapa.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */