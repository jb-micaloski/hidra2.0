/*     */ package ipqm.gsd.hidra.ihm.ihm_camera;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.camera.CameraFixa;
/*     */ import ipqm.gsd.componentes.dominio.camera.controle.ControleCameraGenerica;
/*     */ import javafx.event.Event;
/*     */ import javafx.event.EventHandler;
/*     */ import javafx.geometry.Pos;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.input.MouseEvent;
/*     */ import javafx.scene.layout.HBox;
/*     */ import javafx.scene.layout.Pane;
/*     */ import javafx.scene.layout.VBox;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CameraControleIHM
/*     */ {
/*     */   private Button btCima;
/*     */   private Button btBaixo;
/*     */   private Button btDireita;
/*     */   private Button btEsquerda;
/*     */   private Pane painelControle;
/*     */   private final CameraFixa cameraFixa;
/*     */   private final ControleCameraGenerica controleCamera;
/*     */   
/*     */   public CameraControleIHM(CameraFixa camera) {
/*  29 */     this.cameraFixa = camera;
/*     */     
/*  31 */     this.controleCamera = new ControleCameraGenerica(camera);
/*     */     
/*  33 */     this.painelControle = new Pane();
/*     */   }
/*     */   
/*     */   public Pane getPainelControle() {
/*  37 */     return this.painelControle;
/*     */   }
/*     */   
/*     */   public CameraFixa getCameraFixa() {
/*  41 */     return this.cameraFixa;
/*     */   }
/*     */ 
/*     */   
/*     */   public void iniciaComponentes() {
/*  46 */     this.painelControle.resize(420.0D, 75.0D);
/*     */     
/*  48 */     this.btCima = new Button("");
/*  49 */     this.btDireita = new Button("");
/*  50 */     this.btEsquerda = new Button("");
/*  51 */     this.btBaixo = new Button("");
/*     */     
/*  53 */     this.btCima.setId("botaoControleCamera");
/*  54 */     this.btDireita.setId("botaoControleCamera");
/*  55 */     this.btEsquerda.setId("botaoControleCamera");
/*  56 */     this.btBaixo.setId("botaoControleCamera");
/*     */     
/*  58 */     VBox vbox = new VBox();
/*     */     
/*  60 */     HBox hboxBTCima = new HBox();
/*  61 */     HBox hboxBTEsqBaixoDir = new HBox();
/*     */     
/*  63 */     hboxBTCima.getChildren().add(this.btCima);
/*  64 */     hboxBTEsqBaixoDir.getChildren().addAll((Object[])new Node[] { (Node)this.btEsquerda, (Node)this.btBaixo, (Node)this.btDireita });
/*     */     
/*  66 */     hboxBTCima.setAlignment(Pos.CENTER);
/*  67 */     hboxBTEsqBaixoDir.setAlignment(Pos.CENTER);
/*     */     
/*  69 */     vbox.getChildren().addAll((Object[])new Node[] { (Node)hboxBTCima, (Node)hboxBTEsqBaixoDir });
/*     */     
/*  71 */     vbox.setPrefWidth(420.0D);
/*     */     
/*  73 */     this.painelControle.getChildren().add(vbox);
/*     */     
/*  75 */     this.btCima.setOnMouseReleased(new EventHandler<MouseEvent>()
/*     */         {
/*     */           public void handle(MouseEvent t) {
/*  78 */             CameraControleIHM.this.controleCamera.moverCima();
/*     */           }
/*     */         });
/*  81 */     this.btCima.setOnMouseReleased(new EventHandler<MouseEvent>()
/*     */         {
/*     */           public void handle(MouseEvent t) {
/*  84 */             CameraControleIHM.this.controleCamera.parar();
/*     */           }
/*     */         });
/*     */     
/*  88 */     this.btEsquerda.setOnMouseReleased(new EventHandler<MouseEvent>()
/*     */         {
/*     */           public void handle(MouseEvent t) {
/*  91 */             CameraControleIHM.this.controleCamera.moverEsquerda();
/*     */           }
/*     */         });
/*  94 */     this.btEsquerda.setOnMouseReleased(new EventHandler<MouseEvent>()
/*     */         {
/*     */           public void handle(MouseEvent t) {
/*  97 */             CameraControleIHM.this.controleCamera.parar();
/*     */           }
/*     */         });
/*     */     
/* 101 */     this.btDireita.setOnMouseReleased(new EventHandler<MouseEvent>()
/*     */         {
/*     */           public void handle(MouseEvent t) {
/* 104 */             CameraControleIHM.this.controleCamera.moverDireita();
/*     */           }
/*     */         });
/* 107 */     this.btDireita.setOnMouseReleased(new EventHandler<MouseEvent>()
/*     */         {
/*     */           public void handle(MouseEvent t)
/*     */           {
/* 111 */             CameraControleIHM.this.controleCamera.parar();
/*     */           }
/*     */         });
/*     */     
/* 115 */     this.btBaixo.setOnMouseReleased(new EventHandler<MouseEvent>()
/*     */         {
/*     */           public void handle(MouseEvent t) {
/* 118 */             CameraControleIHM.this.controleCamera.moverBaixo();
/*     */           }
/*     */         });
/* 121 */     this.btBaixo.setOnMouseReleased(new EventHandler<MouseEvent>()
/*     */         {
/*     */           public void handle(MouseEvent t) {
/* 124 */             CameraControleIHM.this.controleCamera.parar();
/*     */           }
/*     */         });
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/ihm_camera/CameraControleIHM.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */