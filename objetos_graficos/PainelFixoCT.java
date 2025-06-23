/*     */ package ipqm.gsd.hidra.ihm.objetos_graficos;
/*     */ 
/*     */ import ipqm.gsd.hidra.ihm.util.UtilDesempenho;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javafx.animation.FadeTransition;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.control.ScrollPane;
/*     */ import javafx.scene.control.TitledPane;
/*     */ import javafx.scene.layout.Pane;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PainelFixoCT
/*     */ {
/*     */   private final int width;
/*     */   private final int height;
/*     */   private final List<TitledPane> paineis;
/*     */   private final Pane pai;
/*     */   
/*     */   public PainelFixoCT(Pane pai, int width, int height) {
/*  29 */     this.pai = pai;
/*  30 */     this.width = width;
/*  31 */     this.height = height;
/*  32 */     this.paineis = new ArrayList<>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void constroi(int... alturas) {
/*  48 */     int y = 0;
/*  49 */     for (int h : alturas) {
/*  50 */       TitledPane titledPane = new TitledPane();
/*  51 */       titledPane.setCollapsible(false);
/*  52 */       titledPane.setId("titledPaneCenario");
/*  53 */       titledPane.setText(".");
/*  54 */       titledPane.setLayoutX(0.0D);
/*  55 */       titledPane.setLayoutY(y);
/*  56 */       titledPane.setMaxWidth(this.width);
/*  57 */       titledPane.setMinWidth(this.width);
/*  58 */       titledPane.setPrefWidth(this.width);
/*  59 */       titledPane.setMaxHeight(h);
/*  60 */       titledPane.setMinHeight(h);
/*  61 */       titledPane.setPrefHeight(h);
/*  62 */       titledPane.setVisible(false);
/*     */       
/*  64 */       y += h + 1;
/*  65 */       this.paineis.add(titledPane);
/*     */       
/*  67 */       this.pai.getChildren().add(titledPane);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(int posicao, String titulo, Pane conteudo) {
/*  81 */     TitledPane tp = this.paineis.get(posicao);
/*  82 */     if (tp == null) {
/*     */       return;
/*     */     }
/*  85 */     if (tp.getText() != null && tp.getText().equals(titulo)) {
/*     */       return;
/*     */     }
/*  88 */     ScrollPane scroll = new ScrollPane((Node)conteudo);
/*  89 */     scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
/*  90 */     scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
/*  91 */     scroll.fitToHeightProperty();
/*  92 */     scroll.fitToWidthProperty();
/*  93 */     tp.setVisible(true);
/*  94 */     tp.setText(titulo);
/*  95 */     tp.setContent((Node)scroll);
/*     */     
/*  97 */     FadeTransition ft = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)tp);
/*  98 */     ft.setFromValue(0.0D);
/*  99 */     ft.setToValue(1.0D);
/* 100 */     ft.play();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove(int posicao) {
/* 110 */     TitledPane tp = this.paineis.get(posicao);
/* 111 */     if (tp == null) {
/*     */       return;
/*     */     }
/* 114 */     if (tp.isVisible()) {
/* 115 */       FadeTransition ft = new FadeTransition(UtilDesempenho.duracaoAnimacao(500.0D), (Node)tp);
/* 116 */       ft.setFromValue(1.0D);
/* 117 */       ft.setToValue(0.0D);
/* 118 */       ft.setOnFinished(arg0 -> {
/*     */             tp.setText(".");
/*     */             tp.setContent(null);
/*     */             tp.setVisible(false);
/*     */           });
/* 123 */       ft.play();
/*     */     } else {
/* 125 */       tp.setText(".");
/* 126 */       tp.setContent(null);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getTitle(int posicao) {
/* 138 */     TitledPane tp = this.paineis.get(posicao);
/* 139 */     if (tp == null) {
/* 140 */       return null;
/*     */     }
/* 142 */     return tp.getText();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTitle(int posicao, String texto) {
/* 153 */     TitledPane tp = this.paineis.get(posicao);
/* 154 */     if (tp != null)
/* 155 */       tp.setText(texto); 
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/objetos_graficos/PainelFixoCT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */