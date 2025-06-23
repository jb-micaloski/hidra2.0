/*     */ package ipqm.gsd.hidra.ihm.configuracao;
/*     */ 
/*     */ import com.sun.javafx.application.PlatformImpl;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.util.ComandosSO;
/*     */ import ipqm.gsd.hidra.ihm.Hidra;
/*     */ import java.text.SimpleDateFormat;
/*     */ import javafx.application.Application;
/*     */ import javafx.application.Platform;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.event.Event;
/*     */ import javafx.event.EventHandler;
/*     */ import javafx.geometry.Insets;
/*     */ import javafx.geometry.Pos;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.Parent;
/*     */ import javafx.scene.Scene;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.Label;
/*     */ import javafx.scene.control.ProgressBar;
/*     */ import javafx.scene.control.TextArea;
/*     */ import javafx.scene.image.Image;
/*     */ import javafx.scene.image.ImageView;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ import javafx.scene.layout.HBox;
/*     */ import javafx.scene.layout.Pane;
/*     */ import javafx.scene.layout.VBox;
/*     */ import javafx.scene.paint.Paint;
/*     */ import javafx.stage.Stage;
/*     */ import javafx.stage.StageStyle;
/*     */ import javafx.stage.WindowEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CarregamentoFX
/*     */   extends Application
/*     */   implements IHMCarregamento
/*     */ {
/*     */   private static final int WIDTH = 700;
/*     */   private static final int HEIGHT = 420;
/*     */   private AnchorPane root;
/*     */   private TextArea textAreaLog;
/*     */   private Stage primaryStage;
/*     */   private ProgressBar progressBar;
/*     */   private Label carregamento;
/*     */   private TextArea licenca;
/*     */   private Label logo;
/*     */   private Label labelNotificacao;
/*     */   private VBox paneNotificacao;
/*     */   private Pane containerNotificacao;
/*     */   private int totalPassos;
/*     */   private int passoAtual;
/*     */   private String ultimaDescricao;
/*     */   
/*     */   public void iniciar() {
/*  57 */     PlatformImpl.startup(() -> {
/*     */           start(new Stage());
/*     */           exibirIHM();
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void start(Stage primaryStage) {
/*  66 */     this.primaryStage = primaryStage;
/*     */     
/*  68 */     this.totalPassos = 20;
/*  69 */     this.root = new AnchorPane();
/*     */     
/*  71 */     Node node = criaInterface();
/*  72 */     AnchorPane.setTopAnchor(node, Double.valueOf(0.0D));
/*  73 */     AnchorPane.setLeftAnchor(node, Double.valueOf(0.0D));
/*  74 */     AnchorPane.setRightAnchor(node, Double.valueOf(0.0D));
/*  75 */     AnchorPane.setBottomAnchor(node, Double.valueOf(0.0D));
/*     */     
/*  77 */     node.setStyle("-fx-background-image: url('ipqm/gsd/hidra/ihm/imagens/BGmodoDiurno.jpg'); -fx-background-repeat: round; -fx-background-size: cover; -fx-padding:50px 50px 0px 50px");
/*     */     
/*  79 */     this.root.getChildren().add(node);
/*  80 */     Scene scene = new Scene((Parent)this.root, 700.0D, 420.0D);
/*  81 */     scene.getStylesheets().add("ipqm/gsd/hidra/ihm/css/carregamento.css");
/*     */     
/*  83 */     this.primaryStage.initStyle(StageStyle.UNDECORATED);
/*  84 */     this.primaryStage.setTitle("Hidra qualidade_v1.1 - Carregando");
/*  85 */     this.primaryStage.setScene(scene);
/*  86 */     this.primaryStage.setResizable(false);
/*  87 */     this.primaryStage.getIcons().add(new Image("ipqm/gsd/hidra/ihm/imagens/iconeHIDRA.png"));
/*     */     
/*  89 */     primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
/*     */         {
/*     */           public void handle(WindowEvent t) {
/*  92 */             t.consume();
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Node criaInterface() {
/* 100 */     this.carregamento = new Label();
/* 101 */     this.carregamento.setPrefWidth(700.0D);
/* 102 */     this.carregamento.setId("texto");
/*     */     
/* 104 */     this.progressBar = new ProgressBar();
/* 105 */     this.progressBar.setPrefWidth(700.0D);
/* 106 */     this.progressBar.setPrefHeight(2.0D);
/*     */     
/* 108 */     this.textAreaLog = new TextArea();
/* 109 */     this.textAreaLog.setEditable(false);
/* 110 */     this.textAreaLog.setPrefHeight(50.0D);
/*     */     
/* 112 */     this.licenca = new TextArea();
/* 113 */     this.licenca.setEditable(false);
/* 114 */     this.licenca.setPrefWidth(700.0D);
/* 115 */     this.licenca.setId("licenca");
/* 116 */     this.licenca.setMinHeight(10.0D);
/*     */     
/* 118 */     this.licenca.setText("HIDRA é um produto do Grupo de Sistemas Digitais (GSD) e do Instituto de Pesquisas da Marinha (IPqM). \nMarinha do Brasil©" + ((Hidra.DATA_COMPILACAO == -1L) ? "" : (" " + (new SimpleDateFormat("yyyy")).format(Long.valueOf(Hidra.DATA_COMPILACAO)))) + " - Todos os direitos reservados. Versão: " + "qualidade_v1.1" + ((Hidra.DATA_COMPILACAO == -1L) ? "" : (" - Compilado em " + (new SimpleDateFormat("dd.MM.yy HH:mm")).format(Long.valueOf(Hidra.DATA_COMPILACAO)))) + ".");
/*     */     
/* 120 */     setImagem("ipqm/gsd/hidra/ihm/imagens/logoHIDRA.png");
/*     */     
/* 122 */     Button buttonReiniciarAplicacao = new Button("Reiniciar a aplicação");
/* 123 */     Button buttonReiniciar = new Button("Reiniciar a maquina");
/* 124 */     Button buttonConfig = new Button("Editar as configurações");
/* 125 */     Button buttonSair = new Button("Sair");
/*     */     
/* 127 */     buttonReiniciarAplicacao.setOnAction(new EventHandler<ActionEvent>()
/*     */         {
/*     */           public void handle(ActionEvent e) {
/* 130 */             ComandosSO.enviarComando(ComandosSO.Comando.REINICIAR_APLICACAO);
/*     */             try {
/* 132 */               Thread.sleep(100L);
/* 133 */             } catch (InterruptedException ex) {
/* 134 */               Log.gravarLogExcecao("Erro no sleep.", this, ex);
/*     */             } 
/* 136 */             System.exit(0);
/*     */           }
/*     */         });
/* 139 */     buttonReiniciar.setOnAction(new EventHandler<ActionEvent>()
/*     */         {
/*     */           public void handle(ActionEvent e) {
/* 142 */             ComandosSO.enviarComando(ComandosSO.Comando.REINICIAR);
/*     */             try {
/* 144 */               Thread.sleep(100L);
/* 145 */             } catch (InterruptedException ex) {
/* 146 */               Log.gravarLogExcecao("Erro no sleep.", this, ex);
/*     */             } 
/* 148 */             System.exit(0);
/*     */           }
/*     */         });
/* 151 */     buttonConfig.setOnAction(new EventHandler<ActionEvent>()
/*     */         {
/*     */           public void handle(ActionEvent e) {
/* 154 */             ComandosSO.enviarComando(ComandosSO.Comando.REINICIAR_APLICACAO, "config");
/*     */             try {
/* 156 */               Thread.sleep(100L);
/* 157 */             } catch (InterruptedException ex) {
/* 158 */               Log.gravarLogExcecao("Erro no sleep.", this, ex);
/*     */             } 
/* 160 */             System.exit(0);
/*     */           }
/*     */         });
/* 163 */     buttonSair.setOnAction(new EventHandler<ActionEvent>()
/*     */         {
/*     */           public void handle(ActionEvent e) {
/* 166 */             System.exit(0);
/*     */           }
/*     */         });
/*     */     
/* 170 */     HBox hBox = new HBox(new Node[] { (Node)buttonReiniciarAplicacao, (Node)buttonReiniciar, (Node)buttonConfig, (Node)buttonSair });
/* 171 */     hBox.setSpacing(10.0D);
/*     */     
/* 173 */     this.labelNotificacao = new Label();
/*     */     
/* 175 */     this.paneNotificacao = new VBox(new Node[] { (Node)this.labelNotificacao, (Node)hBox });
/* 176 */     this.paneNotificacao.setVisible(false);
/* 177 */     this.paneNotificacao.setId("erro");
/*     */     
/* 179 */     this.containerNotificacao = (Pane)new AnchorPane();
/*     */     
/* 181 */     VBox vbox = new VBox(new Node[] { (Node)this.logo, (Node)this.carregamento, (Node)this.progressBar, (Node)this.textAreaLog, (Node)this.containerNotificacao, (Node)this.licenca });
/* 182 */     return (Node)vbox;
/*     */   }
/*     */   
/*     */   private void setImagem(String imagem) {
/* 186 */     ImageView iv = new ImageView(imagem);
/* 187 */     iv.setFitHeight(150.0D);
/* 188 */     iv.setPreserveRatio(true);
/* 189 */     this.logo = new Label();
/* 190 */     this.logo.setPrefWidth(700.0D);
/* 191 */     this.logo.setPadding(new Insets(10.0D));
/* 192 */     this.logo.setAlignment(Pos.CENTER);
/* 193 */     this.logo.setGraphic((Node)iv);
/*     */   }
/*     */ 
/*     */   
/*     */   public void exibirIHM() {
/* 198 */     this.primaryStage.show();
/*     */   }
/*     */ 
/*     */   
/*     */   public void ocultarIHM() {
/* 203 */     Platform.runLater(() -> this.primaryStage.close());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void finalizaCarregamento() {
/* 210 */     while (this.passoAtual < this.totalPassos) {
/* 211 */       notificarCarregamento(0, "Finalizando carregamento");
/*     */       try {
/* 213 */         Thread.sleep(50L);
/* 214 */       } catch (InterruptedException ex) {
/* 215 */         Log.gravarLogExcecao("Erro no sleep.", this, ex);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void erro(Exception ex) {
/* 226 */     Platform.runLater(() -> {
/*     */           this.containerNotificacao.getChildren().add(this.paneNotificacao);
/*     */           this.primaryStage.setMinHeight(570.0D);
/*     */           this.labelNotificacao.setText("Ocorreu algum erro ao iniciar a aplicação:\n\n" + ex.getMessage() + "\n\nO que deseja fazer?\n\n");
/*     */           this.labelNotificacao.setTextFill(Paint.valueOf("#FF0000"));
/*     */           this.paneNotificacao.setVisible(true);
/*     */           this.textAreaLog.appendText("\n" + ex.getMessage() + "\n");
/*     */         });
/*     */   }
/*     */   
/*     */   public void notificarCarregamento(int passoAtual, String descricaoPasso) {
/*     */     // Byte code:
/*     */     //   0: aload_2
/*     */     //   1: ifnonnull -> 14
/*     */     //   4: aload_0
/*     */     //   5: getfield ultimaDescricao : Ljava/lang/String;
/*     */     //   8: ifnull -> 40
/*     */     //   11: goto -> 25
/*     */     //   14: aload_2
/*     */     //   15: aload_0
/*     */     //   16: getfield ultimaDescricao : Ljava/lang/String;
/*     */     //   19: invokevirtual equals : (Ljava/lang/Object;)Z
/*     */     //   22: ifne -> 40
/*     */     //   25: aload_0
/*     */     //   26: aload_2
/*     */     //   27: putfield ultimaDescricao : Ljava/lang/String;
/*     */     //   30: aload_0
/*     */     //   31: aload_2
/*     */     //   32: <illegal opcode> run : (Lipqm/gsd/hidra/ihm/configuracao/CarregamentoFX;Ljava/lang/String;)Ljava/lang/Runnable;
/*     */     //   37: invokestatic runLater : (Ljava/lang/Runnable;)V
/*     */     //   40: iload_1
/*     */     //   41: ifne -> 168
/*     */     //   44: aload_0
/*     */     //   45: dup
/*     */     //   46: getfield passoAtual : I
/*     */     //   49: iconst_1
/*     */     //   50: iadd
/*     */     //   51: putfield passoAtual : I
/*     */     //   54: aload_0
/*     */     //   55: getfield passoAtual : I
/*     */     //   58: i2d
/*     */     //   59: aload_0
/*     */     //   60: getfield totalPassos : I
/*     */     //   63: i2d
/*     */     //   64: ddiv
/*     */     //   65: ldc2_w 100.0
/*     */     //   68: dmul
/*     */     //   69: d2i
/*     */     //   70: istore_3
/*     */     //   71: new java/lang/StringBuilder
/*     */     //   74: dup
/*     */     //   75: invokespecial <init> : ()V
/*     */     //   78: aload_0
/*     */     //   79: getfield passoAtual : I
/*     */     //   82: invokevirtual append : (I)Ljava/lang/StringBuilder;
/*     */     //   85: ldc '/'
/*     */     //   87: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   90: aload_0
/*     */     //   91: getfield totalPassos : I
/*     */     //   94: invokevirtual append : (I)Ljava/lang/StringBuilder;
/*     */     //   97: ldc ' - '
/*     */     //   99: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   102: aload_2
/*     */     //   103: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   106: ldc ' ('
/*     */     //   108: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   111: iload_3
/*     */     //   112: invokevirtual append : (I)Ljava/lang/StringBuilder;
/*     */     //   115: ldc '%)'
/*     */     //   117: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   120: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   123: astore #4
/*     */     //   125: aload_0
/*     */     //   126: aload #4
/*     */     //   128: <illegal opcode> run : (Lipqm/gsd/hidra/ihm/configuracao/CarregamentoFX;Ljava/lang/String;)Ljava/lang/Runnable;
/*     */     //   133: invokestatic runLater : (Ljava/lang/Runnable;)V
/*     */     //   136: new java/lang/StringBuilder
/*     */     //   139: dup
/*     */     //   140: invokespecial <init> : ()V
/*     */     //   143: ldc 'Carregamento: ['
/*     */     //   145: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   148: aload #4
/*     */     //   150: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   153: ldc ']'
/*     */     //   155: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */     //   158: invokevirtual toString : ()Ljava/lang/String;
/*     */     //   161: aload_0
/*     */     //   162: invokestatic gravarLogInterface : (Ljava/lang/String;Ljava/lang/Object;)V
/*     */     //   165: goto -> 175
/*     */     //   168: getstatic java/lang/System.out : Ljava/io/PrintStream;
/*     */     //   171: aload_2
/*     */     //   172: invokevirtual println : (Ljava/lang/String;)V
/*     */     //   175: return
/*     */     // Line number table:
/*     */     //   Java source line number -> byte code offset
/*     */     //   #238	-> 0
/*     */     //   #239	-> 25
/*     */     //   #240	-> 30
/*     */     //   #245	-> 40
/*     */     //   #246	-> 44
/*     */     //   #247	-> 54
/*     */     //   #248	-> 71
/*     */     //   #249	-> 125
/*     */     //   #253	-> 136
/*     */     //   #254	-> 165
/*     */     //   #255	-> 168
/*     */     //   #257	-> 175
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	descriptor
/*     */     //   71	94	3	porcento	I
/*     */     //   125	40	4	texto	Ljava/lang/String;
/*     */     //   0	176	0	this	Lipqm/gsd/hidra/ihm/configuracao/CarregamentoFX;
/*     */     //   0	176	1	passoAtual	I
/*     */     //   0	176	2	descricaoPasso	Ljava/lang/String;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/configuracao/CarregamentoFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */