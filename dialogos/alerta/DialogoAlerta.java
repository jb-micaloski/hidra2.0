/*     */ package ipqm.gsd.hidra.ihm.dialogos.alerta;
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
/*     */ import javafx.scene.control.TextArea;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class DialogoAlerta
/*     */   extends Dialogo
/*     */ {
/*     */   private final ControladorDialogoAlerta controladorDialogoAlerta;
/*     */   
/*     */   public DialogoAlerta(String titulo, String descricao, Controlador controladorPai) {
/*  30 */     super(titulo, descricao, controladorPai, "alerta/Alerta.fxml");
/*  31 */     this.controladorDialogoAlerta = (ControladorDialogoAlerta)this.fXMLLoader.getController();
/*  32 */     this.controladorDialogoAlerta.setDialogo(this);
/*  33 */     exibir();
/*     */   }
/*     */   
/*     */   public DialogoAlerta(String titulo, String descricao, Controlador controladorPai, String descricaoLonga) {
/*  37 */     super(titulo, descricao, controladorPai, "alerta/Alerta.fxml");
/*  38 */     this.controladorDialogoAlerta = (ControladorDialogoAlerta)this.fXMLLoader.getController();
/*  39 */     this.controladorDialogoAlerta.setDialogo(this);
/*     */     
/*  41 */     TextArea textArea = new TextArea(descricaoLonga);
/*  42 */     textArea.setLayoutX(this.controladorDialogoAlerta.getAlerta().getLayoutX());
/*  43 */     textArea.setPrefWidth(this.controladorDialogoAlerta.getAlerta().getPrefWidth() + 3.0D);
/*  44 */     textArea.setLayoutY(650.0D);
/*  45 */     textArea.setEditable(false);
/*  46 */     textArea.setWrapText(true);
/*  47 */     this.controladorDialogoAlerta.getAnchorPaneDialogo().getChildren().add(textArea);
/*  48 */     exibir();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void exibir() {
/*  54 */     this.controladorDialogoAlerta.setTituloAlerta(this.titulo);
/*  55 */     this.controladorDialogoAlerta.setDescricaoAlerta(this.descricao);
/*     */     
/*  57 */     exibirFundoPreto();
/*  58 */     exibirAlerta();
/*     */     
/*  60 */     Iterator<Tela> it = Aplicacao.getInstancia().getTelas().values().iterator();
/*  61 */     while (it.hasNext()) {
/*  62 */       Tela tela = it.next();
/*  63 */       if (tela.getNumTela() != getControladorPai().getTela().getNumTela()) {
/*  64 */         WidgetFundoPreto fundo = (WidgetFundoPreto)tela.getWidget(Widget.Tipo.FUNDO_PRETO);
/*  65 */         if (fundo != null) {
/*  66 */           fundo.exibir(tela.getCenaAtual());
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void ocultar() {
/*  75 */     this.controladorDialogoAlerta.setTituloAlerta(null);
/*  76 */     this.controladorDialogoAlerta.setDescricaoAlerta(null);
/*     */     
/*  78 */     ocultarFundoPreto();
/*  79 */     ocultarAlerta();
/*     */     
/*  81 */     Iterator<Tela> it = Aplicacao.getInstancia().getTelas().values().iterator();
/*  82 */     while (it.hasNext()) {
/*  83 */       Tela tela = it.next();
/*  84 */       if (tela.getNumTela() != getControladorPai().getTela().getNumTela()) {
/*  85 */         WidgetFundoPreto fundo = (WidgetFundoPreto)tela.getWidget(Widget.Tipo.FUNDO_PRETO);
/*  86 */         if (fundo != null) {
/*  87 */           fundo.ocultar(tela.getCenaAtual());
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void exibirAlerta() {
/*  97 */     Platform.runLater(() -> {
/*     */           this.controladorPai.getAnchorPanePai().getChildren().add(this.controladorDialogoAlerta.getAnchorPaneDialogo());
/*     */           
/*     */           this.controladorDialogoAlerta.getAlerta().setVisible(true);
/*     */         });
/* 102 */     ScaleTransition st = new ScaleTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.controladorDialogoAlerta.getAlerta());
/* 103 */     st.setFromX(1.2D);
/* 104 */     st.setFromY(1.2D);
/* 105 */     st.setToX(1.0D);
/* 106 */     st.setToY(1.0D);
/*     */     
/* 108 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.controladorDialogoAlerta.getAlerta());
/* 109 */     fadeTransition.setFromValue(0.0D);
/* 110 */     fadeTransition.setToValue(1.0D);
/*     */     
/* 112 */     ParallelTransition pt = new ParallelTransition(new Animation[] { (Animation)st, (Animation)fadeTransition });
/* 113 */     pt.play();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void ocultarAlerta() {
/* 121 */     if (this.controladorDialogoAlerta.getAlerta().isVisible()) {
/* 122 */       FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.controladorDialogoAlerta.getAlerta());
/* 123 */       fadeTransition.setFromValue(1.0D);
/* 124 */       fadeTransition.setToValue(0.0D);
/* 125 */       fadeTransition.setOnFinished(arg0 -> {
/*     */             this.controladorDialogoAlerta.getAlerta().setVisible(false);
/*     */             this.controladorPai.getAnchorPanePai().getChildren().remove(this.controladorDialogoAlerta.getAnchorPaneDialogo());
/*     */           });
/* 129 */       fadeTransition.play();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void exibirFundoPreto() {
/* 137 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.controladorDialogoAlerta.getFundoPreto());
/* 138 */     fadeTransition.setFromValue(0.0D);
/* 139 */     fadeTransition.setToValue(0.9D);
/* 140 */     this.controladorDialogoAlerta.getFundoPreto().setVisible(true);
/* 141 */     fadeTransition.play();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void ocultarFundoPreto() {
/* 148 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.controladorDialogoAlerta.getFundoPreto());
/* 149 */     fadeTransition.setFromValue(0.9D);
/* 150 */     fadeTransition.setToValue(0.0D);
/* 151 */     fadeTransition.setOnFinished(arg0 -> this.controladorDialogoAlerta.getFundoPreto().setVisible(false));
/*     */ 
/*     */     
/* 154 */     fadeTransition.play();
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/dialogos/alerta/DialogoAlerta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */