/*     */ package ipqm.gsd.hidra.ihm.dialogos.selecaoMultipla;
/*     */ 
/*     */ import ipqm.gsd.hidra.ihm.controle.Controlador;
/*     */ import ipqm.gsd.hidra.ihm.dialogos.AcaoDialogo;
/*     */ import ipqm.gsd.hidra.ihm.dialogos.Dialogo;
/*     */ import ipqm.gsd.hidra.ihm.util.UtilDesempenho;
/*     */ import java.util.List;
/*     */ import javafx.animation.FadeTransition;
/*     */ import javafx.animation.TranslateTransition;
/*     */ import javafx.application.Platform;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.control.SelectionMode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class DialogoSelecaoMultipla
/*     */   extends Dialogo
/*     */ {
/*     */   private final ControladorDialogoSelecaoMultipla controladorDialogoSelecaoMultipla;
/*     */   private final List<Object> itens;
/*     */   private Object itemSelecionado;
/*     */   
/*     */   public DialogoSelecaoMultipla(String titulo, String descricao, Controlador controladorPai, List<Object> itens, boolean selecaoMultipla) {
/*  30 */     super(titulo, descricao, controladorPai, "selecaoMultipla/SelecaoMultipla.fxml");
/*  31 */     this.controladorDialogoSelecaoMultipla = (ControladorDialogoSelecaoMultipla)this.fXMLLoader.getController();
/*  32 */     this.controladorDialogoSelecaoMultipla.setDialogo(this);
/*  33 */     if (selecaoMultipla == true) {
/*  34 */       this.controladorDialogoSelecaoMultipla.getListaItens().getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
/*     */     }
/*  36 */     this.itens = itens;
/*  37 */     exibir();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void exibir() {
/*  43 */     getControladorDialogoSelecaoMultipla().setTituloSelecao(this.titulo);
/*  44 */     getControladorDialogoSelecaoMultipla().setDescricaoSelecao(this.descricao);
/*  45 */     getControladorDialogoSelecaoMultipla().setListaItens(this.itens);
/*  46 */     exibirConfirma();
/*     */   }
/*     */ 
/*     */   
/*     */   public void ocultar() {
/*  51 */     ocultarConfirma();
/*     */   }
/*     */   
/*     */   public void setItemSelecionado(Object itemSelecionado) {
/*  55 */     this.itemSelecionado = itemSelecionado;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void exibirConfirma() {
/*  62 */     Platform.runLater(() -> {
/*     */           this.controladorPai.getAnchorPanePai().getChildren().add(getControladorDialogoSelecaoMultipla().getAnchorPaneSelecao());
/*     */           
/*     */           getControladorDialogoSelecaoMultipla().getSelecao().setVisible(true);
/*     */         });
/*  67 */     TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(500.0D), (Node)getControladorDialogoSelecaoMultipla().getSelecao());
/*  68 */     tt.setFromX(-680.0D);
/*  69 */     tt.setToX(0.0D);
/*  70 */     tt.setAutoReverse(false);
/*     */     
/*  72 */     tt.play();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void ocultarConfirma() {
/*  79 */     if (getControladorDialogoSelecaoMultipla().getSelecao().isVisible()) {
/*  80 */       TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)getControladorDialogoSelecaoMultipla().getSelecao());
/*  81 */       tt.setFromX(0.0D);
/*  82 */       tt.setToX(-680.0D);
/*  83 */       tt.setOnFinished(arg0 -> {
/*     */             getControladorDialogoSelecaoMultipla().getSelecao().setVisible(false);
/*     */             this.controladorPai.getAnchorPanePai().getChildren().remove(getControladorDialogoSelecaoMultipla().getAnchorPaneSelecao());
/*     */             getControladorDialogoSelecaoMultipla().setTituloSelecao(null);
/*     */             getControladorDialogoSelecaoMultipla().setDescricaoSelecao(null);
/*     */             getControladorDialogoSelecaoMultipla().limparLista();
/*     */           });
/*  90 */       tt.play();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void exibirFundoPreto() {
/*  98 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)getControladorDialogoSelecaoMultipla().getFundoPreto());
/*  99 */     fadeTransition.setFromValue(0.0D);
/* 100 */     fadeTransition.setToValue(0.9D);
/* 101 */     getControladorDialogoSelecaoMultipla().getFundoPreto().setVisible(true);
/* 102 */     fadeTransition.play();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void ocultarFundoPreto() {
/* 109 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)getControladorDialogoSelecaoMultipla().getFundoPreto());
/* 110 */     fadeTransition.setFromValue(0.9D);
/* 111 */     fadeTransition.setToValue(0.0D);
/* 112 */     fadeTransition.setOnFinished(arg0 -> getControladorDialogoSelecaoMultipla().getFundoPreto().setVisible(false));
/*     */ 
/*     */     
/* 115 */     fadeTransition.play();
/*     */   }
/*     */ 
/*     */   
/*     */   public void acao(AcaoDialogo acao) {
/* 120 */     acao(acao, this.itemSelecionado);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ControladorDialogoSelecaoMultipla getControladorDialogoSelecaoMultipla() {
/* 129 */     return this.controladorDialogoSelecaoMultipla;
/*     */   }
/*     */   
/*     */   public abstract void acao(AcaoDialogo paramAcaoDialogo, Object paramObject);
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/dialogos/selecaoMultipla/DialogoSelecaoMultipla.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */