/*     */ package ipqm.gsd.hidra.ihm.widgets.notificador;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.notificador.Notificacao;
/*     */ import ipqm.gsd.hidra.ihm.util.UtilDesempenho;
/*     */ import ipqm.gsd.hidra.ihm.visao.Cena;
/*     */ import javafx.animation.Animation;
/*     */ import javafx.animation.FadeTransition;
/*     */ import javafx.animation.ParallelTransition;
/*     */ import javafx.animation.TranslateTransition;
/*     */ import javafx.application.Platform;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.event.Event;
/*     */ import javafx.event.EventHandler;
/*     */ import javafx.scene.Cursor;
/*     */ import javafx.scene.ImageCursor;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.control.Label;
/*     */ import javafx.scene.image.Image;
/*     */ import javafx.scene.image.ImageView;
/*     */ import javafx.scene.input.MouseEvent;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NotificacaoVisual
/*     */ {
/*     */   private static final int ALTURA = 48;
/*     */   private static final int LARGURA_MINIMA = 300;
/*     */   private static final int LARGURA_MAXIMA = 600;
/*     */   private AnchorPane anchorPane;
/*     */   private Cena cena;
/*     */   private final Notificacao notificacao;
/*     */   private double y;
/*     */   private boolean mouseOver;
/*     */   private double larguraTela;
/*     */   private Label labelTitulo;
/*     */   private Label labelDescricao;
/*  43 */   private String tituloSubstituto = "";
/*     */   
/*     */   public NotificacaoVisual(Notificacao notificacao) {
/*  46 */     this.notificacao = notificacao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void exibir(Cena cena, int pos) {
/*  57 */     if (cena == null || cena.getControlador() == null || cena.getControlador().getTela() == null) {
/*     */       return;
/*     */     }
/*     */     
/*  61 */     String classeAnchorPane = "notificacao";
/*     */     
/*  63 */     this.larguraTela = cena.getControlador().getTela().getStage().getWidth();
/*     */     
/*  65 */     this.cena = cena;
/*  66 */     this.y = calculaY(pos);
/*     */     
/*  68 */     this.labelTitulo = new Label();
/*  69 */     this.labelTitulo.setLayoutX(5.0D);
/*  70 */     this.labelTitulo.setLayoutY(5.0D);
/*  71 */     this.labelTitulo.setId("labelTituloNotificacao");
/*     */     
/*  73 */     this.labelDescricao = new Label();
/*  74 */     this.labelDescricao.setLayoutX(5.0D);
/*  75 */     this.labelDescricao.setLayoutY(25.0D);
/*  76 */     this.labelDescricao.setId("labelDescricaoNotificacao");
/*     */     
/*  78 */     Label labelImagem = new Label();
/*     */     
/*  80 */     if (this.notificacao.getImagem() == null) {
/*  81 */       switch (this.notificacao.getTipo()) {
/*     */         case AJUDA:
/*  83 */           this.tituloSubstituto = "Ajuda";
/*  84 */           this.notificacao.setImagem("ipqm/gsd/hidra/ihm/imagens/notificacoes/ajuda.png");
/*     */           break;
/*     */         case ERRO:
/*  87 */           this.tituloSubstituto = "Erro";
/*  88 */           this.notificacao.setImagem("ipqm/gsd/hidra/ihm/imagens/notificacoes/erro.png");
/*     */           break;
/*     */         case INFORMACAO:
/*  91 */           this.tituloSubstituto = "Informação";
/*  92 */           this.notificacao.setImagem("ipqm/gsd/hidra/ihm/imagens/notificacoes/info.png");
/*     */           break;
/*     */         case ALERTA:
/*  95 */           this.tituloSubstituto = "Alerta";
/*  96 */           this.notificacao.setImagem("ipqm/gsd/hidra/ihm/imagens/notificacoes/alerta.png");
/*     */           break;
/*     */         case CARREGANDO:
/*  99 */           this.tituloSubstituto = "Carregando";
/* 100 */           this.notificacao.setImagem("ipqm/gsd/hidra/ihm/imagens/notificacoes/carregando.gif");
/*     */           break;
/*     */         case EXCECAO:
/* 103 */           this.tituloSubstituto = "Exceção!";
/* 104 */           this.notificacao.setImagem("ipqm/gsd/hidra/ihm/imagens/notificacoes/excecao.png");
/* 105 */           classeAnchorPane = "excecao";
/*     */           break;
/*     */       } 
/*     */       
/* 109 */       labelImagem.setLayoutX(5.0D);
/* 110 */       labelImagem.setLayoutY(12.0D);
/* 111 */       this.labelTitulo.setLayoutX(37.0D);
/* 112 */       this.labelDescricao.setLayoutX(37.0D);
/*     */     } else {
/* 114 */       labelImagem.setLayoutX(5.0D);
/* 115 */       labelImagem.setLayoutY(5.0D);
/* 116 */       labelImagem.setMaxHeight(0.0D);
/* 117 */       labelImagem.setMaxWidth(0.0D);
/*     */     } 
/*     */     
/* 120 */     if (this.notificacao.getImagem() != null) {
/* 121 */       ImageView iv = new ImageView(this.notificacao.getImagem());
/* 122 */       labelImagem.setGraphic((Node)iv);
/* 123 */       labelImagem.setMaxWidth(iv.getFitWidth());
/*     */     } 
/*     */     
/* 126 */     atualiza();
/*     */     
/* 128 */     this.anchorPane = new AnchorPane();
/* 129 */     this.anchorPane.setMaxHeight(48.0D);
/* 130 */     this.anchorPane.setMinWidth(300.0D);
/* 131 */     this.anchorPane.setMaxWidth(600.0D);
/* 132 */     this.anchorPane.setMinHeight(48.0D);
/*     */     
/* 134 */     Platform.runLater(() -> {
/*     */           this.anchorPane.getStylesheets().clear();
/*     */           
/*     */           this.anchorPane.getStylesheets().setAll((Object[])new String[] { "ipqm/gsd/hidra/ihm/css/widgets/notificacao.css" });
/*     */         });
/* 139 */     this.anchorPane.setId(classeAnchorPane);
/*     */     
/* 141 */     Platform.runLater(() -> {
/*     */           this.anchorPane.getChildren().addAll((Object[])new Node[] { (Node)this.labelTitulo, (Node)this.labelDescricao, (Node)labelImagem });
/*     */           
/*     */           cena.getControlador().getAnchorPanePai().getChildren().add(this.anchorPane);
/*     */           if (getNotificacao().isExibirBotaoFechar()) {
/*     */             Image image = new Image("ipqm/gsd/hidra/ihm/imagens/ponteiros_mouse/fechar.png");
/*     */             this.anchorPane.setCursor((Cursor)new ImageCursor(image));
/*     */           } 
/*     */         });
/* 150 */     this.anchorPane.setVisible(true);
/*     */     
/* 152 */     double x = this.larguraTela / 2.0D - 150.0D;
/* 153 */     this.anchorPane.setLayoutX(x);
/*     */     
/* 155 */     TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(200.0D), (Node)this.anchorPane);
/* 156 */     tt.setFromY(-48.0D);
/* 157 */     tt.setToY(3.0D);
/* 158 */     tt.setAutoReverse(false);
/*     */     
/* 160 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(200.0D), (Node)this.anchorPane);
/* 161 */     fadeTransition.setFromValue(0.0D);
/* 162 */     fadeTransition.setToValue(0.9D);
/*     */     
/* 164 */     ParallelTransition pt = new ParallelTransition(new Animation[] { (Animation)fadeTransition, (Animation)tt });
/* 165 */     pt.setOnFinished(arg0 -> acertaLayoutX());
/*     */ 
/*     */ 
/*     */     
/* 169 */     pt.play();
/*     */     
/* 171 */     this.anchorPane.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>()
/*     */         {
/*     */           
/*     */           public void handle(MouseEvent mouseEvent)
/*     */           {
/* 176 */             NotificacaoVisual.this.mouseOver = true;
/*     */           }
/*     */         });
/* 179 */     this.anchorPane.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>()
/*     */         {
/*     */           
/*     */           public void handle(MouseEvent mouseEvent)
/*     */           {
/* 184 */             NotificacaoVisual.this.mouseOver = false;
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public void acertaLayoutX() {
/* 190 */     double x = this.larguraTela / 2.0D - this.anchorPane.getWidth() / 2.0D;
/* 191 */     if (this.anchorPane.getLayoutX() != x) {
/* 192 */       TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(100.0D), (Node)this.anchorPane);
/* 193 */       tt.setToX(x - this.anchorPane.getLayoutX());
/* 194 */       tt.setAutoReverse(false);
/* 195 */       tt.play();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ocultar() {
/* 203 */     if (this.cena.getControlador().getAnchorPanePai() == null) {
/*     */       return;
/*     */     }
/* 206 */     Platform.runLater(() -> {
/*     */           if (this.cena.getControlador().getAnchorPanePai().getChildren().contains(this.anchorPane)) {
/*     */             TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(400.0D), (Node)this.anchorPane);
/*     */             tt.setToY(-48.0D);
/*     */             tt.setAutoReverse(false);
/*     */             tt.setDelay(UtilDesempenho.duracaoAnimacao(100.0D));
/*     */             ParallelTransition pt = new ParallelTransition(new Animation[] { (Animation)tt });
/*     */             pt.setOnFinished(());
/*     */             pt.play();
/*     */           } 
/*     */         });
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
/*     */   
/*     */   public void mostrar(Cena cena) {
/* 233 */     this.cena = cena;
/* 234 */     if (this.anchorPane == null || cena.getControlador().getAnchorPanePai() == null) {
/*     */       return;
/*     */     }
/* 237 */     Platform.runLater(() -> cena.getControlador().getAnchorPanePai().getChildren().add(this.anchorPane));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mover(int pos) {
/* 248 */     if (this.anchorPane == null) {
/*     */       return;
/*     */     }
/* 251 */     double novoY = calculaY(pos);
/* 252 */     if (novoY != this.y) {
/* 253 */       Platform.runLater(() -> {
/*     */             TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.anchorPane);
/*     */             tt.setToY(novoY - this.anchorPane.getLayoutY());
/*     */             tt.setAutoReverse(false);
/*     */             tt.setOnFinished(());
/*     */             tt.play();
/*     */           });
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
/*     */   private double calculaY(int pos) {
/* 272 */     double y = (3 + pos * 48);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 278 */     if (y < 0.0D) {
/* 279 */       y = 0.0D;
/*     */     }
/* 281 */     return y;
/*     */   }
/*     */   
/*     */   public Notificacao getNotificacao() {
/* 285 */     return this.notificacao;
/*     */   }
/*     */   
/*     */   public AnchorPane getAnchorPane() {
/* 289 */     return this.anchorPane;
/*     */   }
/*     */   
/*     */   public boolean isMouseOver() {
/* 293 */     return this.mouseOver;
/*     */   }
/*     */   
/*     */   public void atualiza() {
/* 297 */     Platform.runLater(() -> {
/*     */           String titulo = this.notificacao.getTitulo();
/*     */           String descricao = this.notificacao.getDescricao();
/*     */           if (titulo != null && descricao == null) {
/*     */             descricao = titulo;
/*     */             titulo = this.tituloSubstituto;
/*     */           } else if (titulo == null && descricao != null) {
/*     */             titulo = this.tituloSubstituto;
/*     */           } 
/*     */           if (this.notificacao.getContador() > 1)
/*     */             titulo = titulo + " [+" + this.notificacao.getContador() + "]"; 
/*     */           this.labelTitulo.setText(titulo);
/*     */           this.labelDescricao.setText(descricao);
/*     */         });
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/notificador/NotificacaoVisual.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */