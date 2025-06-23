/*     */ package ipqm.gsd.hidra.ihm.dialogos.latlong;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.hidra.ihm.controle.Aplicacao;
/*     */ import ipqm.gsd.hidra.ihm.controle.Controlador;
/*     */ import ipqm.gsd.hidra.ihm.controle.Tela;
/*     */ import ipqm.gsd.hidra.ihm.dialogos.AcaoDialogo;
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
/*     */ public abstract class DialogoLatLong
/*     */   extends Dialogo
/*     */ {
/*     */   private final ControladorDialogoLatLong controladorDialogoLatLong;
/*     */   
/*     */   public DialogoLatLong(String titulo, String descricao, Controlador controladorPai) {
/*  31 */     super(titulo, descricao, controladorPai, "latlong/LatLong.fxml");
/*  32 */     this.controladorDialogoLatLong = (ControladorDialogoLatLong)this.fXMLLoader.getController();
/*  33 */     this.controladorDialogoLatLong.setDialogo(this);
/*  34 */     exibir();
/*     */   }
/*     */ 
/*     */   
/*     */   public void acao(AcaoDialogo acao) {
/*  39 */     if (acao == AcaoDialogo.OK) {
/*  40 */       if (this.controladorDialogoLatLong.validarFormulario()) {
/*  41 */         acao(this.controladorDialogoLatLong.getCoordenadaGeografica());
/*  42 */         Platform.runLater(() -> ocultar());
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/*  47 */       Platform.runLater(() -> ocultar());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final void exibir() {
/*  58 */     this.controladorDialogoLatLong.setTituloConfirma(this.titulo);
/*  59 */     this.controladorDialogoLatLong.setDescricaoConfirma(this.descricao);
/*  60 */     exibirFundoPreto();
/*  61 */     exibirDialogo();
/*     */     
/*  63 */     Iterator<Tela> it = Aplicacao.getInstancia().getTelas().values().iterator();
/*  64 */     while (it.hasNext()) {
/*  65 */       Tela tela = it.next();
/*  66 */       if (tela.getNumTela() != getControladorPai().getTela().getNumTela()) {
/*  67 */         WidgetFundoPreto fundo = (WidgetFundoPreto)tela.getWidget(Widget.Tipo.FUNDO_PRETO);
/*  68 */         if (fundo != null) {
/*  69 */           fundo.exibir(tela.getCenaAtual());
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void ocultar() {
/*  78 */     this.controladorDialogoLatLong.setTituloConfirma(null);
/*  79 */     this.controladorDialogoLatLong.setDescricaoConfirma(null);
/*     */     
/*  81 */     ocultarFundoPreto();
/*  82 */     ocultarConfirma();
/*     */     
/*  84 */     Iterator<Tela> it = Aplicacao.getInstancia().getTelas().values().iterator();
/*  85 */     while (it.hasNext()) {
/*  86 */       Tela tela = it.next();
/*  87 */       if (tela.getNumTela() != getControladorPai().getTela().getNumTela()) {
/*  88 */         WidgetFundoPreto fundo = (WidgetFundoPreto)tela.getWidget(Widget.Tipo.FUNDO_PRETO);
/*  89 */         if (fundo != null) {
/*  90 */           fundo.ocultar(tela.getCenaAtual());
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void exibirDialogo() {
/* 100 */     Platform.runLater(() -> {
/*     */           this.controladorPai.getAnchorPanePai().getChildren().add(this.controladorDialogoLatLong.getAnchorPaneDialogo());
/*     */           
/*     */           this.controladorDialogoLatLong.getDialogo().setVisible(true);
/*     */           this.controladorDialogoLatLong.exibirIHM();
/*     */         });
/* 106 */     ScaleTransition st = new ScaleTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.controladorDialogoLatLong.getDialogo());
/* 107 */     st.setFromX(1.2D);
/* 108 */     st.setFromY(1.2D);
/* 109 */     st.setToX(1.0D);
/* 110 */     st.setToY(1.0D);
/*     */     
/* 112 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.controladorDialogoLatLong.getDialogo());
/* 113 */     fadeTransition.setFromValue(0.0D);
/* 114 */     fadeTransition.setToValue(1.0D);
/*     */     
/* 116 */     ParallelTransition pt = new ParallelTransition(new Animation[] { (Animation)st, (Animation)fadeTransition });
/* 117 */     pt.play();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void ocultarConfirma() {
/* 125 */     if (this.controladorDialogoLatLong.getDialogo().isVisible()) {
/* 126 */       FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.controladorDialogoLatLong.getDialogo());
/* 127 */       fadeTransition.setFromValue(1.0D);
/* 128 */       fadeTransition.setToValue(0.0D);
/* 129 */       fadeTransition.setOnFinished(arg0 -> {
/*     */             this.controladorDialogoLatLong.getDialogo().setVisible(false);
/*     */             this.controladorPai.getAnchorPanePai().getChildren().remove(this.controladorDialogoLatLong.getAnchorPaneDialogo());
/*     */           });
/* 133 */       fadeTransition.play();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void exibirFundoPreto() {
/* 141 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.controladorDialogoLatLong.getFundoPreto());
/* 142 */     fadeTransition.setFromValue(0.0D);
/* 143 */     fadeTransition.setToValue(0.9D);
/* 144 */     this.controladorDialogoLatLong.getFundoPreto().setVisible(true);
/* 145 */     fadeTransition.play();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void ocultarFundoPreto() {
/* 152 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.controladorDialogoLatLong.getFundoPreto());
/* 153 */     fadeTransition.setFromValue(0.9D);
/* 154 */     fadeTransition.setToValue(0.0D);
/* 155 */     fadeTransition.setOnFinished(arg0 -> this.controladorDialogoLatLong.getFundoPreto().setVisible(false));
/*     */ 
/*     */     
/* 158 */     fadeTransition.play();
/*     */   }
/*     */   
/*     */   public abstract void acao(CoordenadaGeografica paramCoordenadaGeografica);
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/dialogos/latlong/DialogoLatLong.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */