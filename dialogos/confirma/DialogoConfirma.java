/*     */ package ipqm.gsd.hidra.ihm.dialogos.confirma;
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
/*     */ public abstract class DialogoConfirma
/*     */   extends Dialogo
/*     */ {
/*     */   private final ControladorDialogoConfirma controladorDialogoConfirma;
/*     */   
/*     */   public DialogoConfirma(String titulo, String descricao, Controlador controladorPai) {
/*  29 */     super(titulo, descricao, controladorPai, "confirma/Confirma.fxml");
/*  30 */     this.controladorDialogoConfirma = (ControladorDialogoConfirma)this.fXMLLoader.getController();
/*  31 */     this.controladorDialogoConfirma.setDialogo(this);
/*  32 */     exibir();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void exibir() {
/*  38 */     this.controladorDialogoConfirma.setTituloConfirma(this.titulo);
/*  39 */     this.controladorDialogoConfirma.setDescricaoConfirma(this.descricao);
/*  40 */     exibirFundoPreto();
/*  41 */     exibirConfirma();
/*     */     
/*  43 */     Iterator<Tela> it = Aplicacao.getInstancia().getTelas().values().iterator();
/*  44 */     while (it.hasNext()) {
/*  45 */       Tela tela = it.next();
/*  46 */       if (tela.getNumTela() != getControladorPai().getTela().getNumTela()) {
/*  47 */         WidgetFundoPreto fundo = (WidgetFundoPreto)tela.getWidget(Widget.Tipo.FUNDO_PRETO);
/*  48 */         if (fundo != null) {
/*  49 */           fundo.exibir(tela.getCenaAtual());
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void ocultar() {
/*  58 */     this.controladorDialogoConfirma.setTituloConfirma(null);
/*  59 */     this.controladorDialogoConfirma.setDescricaoConfirma(null);
/*     */     
/*  61 */     ocultarFundoPreto();
/*  62 */     ocultarConfirma();
/*     */     
/*  64 */     Iterator<Tela> it = Aplicacao.getInstancia().getTelas().values().iterator();
/*  65 */     while (it.hasNext()) {
/*  66 */       Tela tela = it.next();
/*  67 */       if (tela.getNumTela() != getControladorPai().getTela().getNumTela()) {
/*  68 */         WidgetFundoPreto fundo = (WidgetFundoPreto)tela.getWidget(Widget.Tipo.FUNDO_PRETO);
/*  69 */         if (fundo != null) {
/*  70 */           fundo.ocultar(tela.getCenaAtual());
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void exibirConfirma() {
/*  80 */     Platform.runLater(() -> {
/*     */           this.controladorPai.getAnchorPanePai().getChildren().add(this.controladorDialogoConfirma.getAnchorPaneDialogo());
/*     */           
/*     */           this.controladorDialogoConfirma.getConfirma().setVisible(true);
/*     */         });
/*  85 */     ScaleTransition st = new ScaleTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.controladorDialogoConfirma.getConfirma());
/*  86 */     st.setFromX(1.2D);
/*  87 */     st.setFromY(1.2D);
/*  88 */     st.setToX(1.0D);
/*  89 */     st.setToY(1.0D);
/*     */     
/*  91 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.controladorDialogoConfirma.getConfirma());
/*  92 */     fadeTransition.setFromValue(0.0D);
/*  93 */     fadeTransition.setToValue(1.0D);
/*     */     
/*  95 */     ParallelTransition pt = new ParallelTransition(new Animation[] { (Animation)st, (Animation)fadeTransition });
/*  96 */     pt.play();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void ocultarConfirma() {
/* 104 */     if (this.controladorDialogoConfirma.getConfirma().isVisible()) {
/* 105 */       FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.controladorDialogoConfirma.getConfirma());
/* 106 */       fadeTransition.setFromValue(1.0D);
/* 107 */       fadeTransition.setToValue(0.0D);
/* 108 */       fadeTransition.setOnFinished(arg0 -> {
/*     */             this.controladorDialogoConfirma.getConfirma().setVisible(false);
/*     */             this.controladorPai.getAnchorPanePai().getChildren().remove(this.controladorDialogoConfirma.getAnchorPaneDialogo());
/*     */           });
/* 112 */       fadeTransition.play();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void exibirFundoPreto() {
/* 120 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.controladorDialogoConfirma.getFundoPreto());
/* 121 */     fadeTransition.setFromValue(0.0D);
/* 122 */     fadeTransition.setToValue(0.9D);
/* 123 */     this.controladorDialogoConfirma.getFundoPreto().setVisible(true);
/* 124 */     fadeTransition.play();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void ocultarFundoPreto() {
/* 131 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.controladorDialogoConfirma.getFundoPreto());
/* 132 */     fadeTransition.setFromValue(0.9D);
/* 133 */     fadeTransition.setToValue(0.0D);
/* 134 */     fadeTransition.setOnFinished(arg0 -> this.controladorDialogoConfirma.getFundoPreto().setVisible(false));
/*     */ 
/*     */     
/* 137 */     fadeTransition.play();
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/dialogos/confirma/DialogoConfirma.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */