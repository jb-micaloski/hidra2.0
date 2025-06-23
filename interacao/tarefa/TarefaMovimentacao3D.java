/*     */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*     */ import ipqm.gsd.hidra.ihm.interacao.GrupoCamera;
/*     */ import ipqm.gsd.hidra.ihm.projetos.geral.cenas.controladores.ControladorCenarioTatico;
/*     */ import javafx.scene.Camera;
/*     */ import javafx.scene.Cursor;
/*     */ import javafx.scene.input.KeyCode;
/*     */ import javafx.scene.input.KeyEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TarefaMovimentacao3D
/*     */   extends Tarefa<CanvasCenarioTatico<? extends ControladorCenarioTatico>>
/*     */ {
/*  21 */   private final double AJUSTE_ZOOM = 2.0D;
/*  22 */   private final double AJUSTE_ARRASTO = 0.2D;
/*     */   private double mousePosX;
/*     */   private double mousePosY;
/*     */   private double mousePosAntigaX;
/*     */   private double mousePosAntigaY;
/*     */   private double mouseDeltaX;
/*     */   private double mouseDeltaY;
/*     */   private GrupoCamera grupoCamera;
/*     */   private Camera camera;
/*     */   
/*     */   public TarefaMovimentacao3D(CanvasCenarioTatico<? extends ControladorCenarioTatico> canvasEspacial) {
/*  33 */     super(canvasEspacial);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void inicializa() {
/*  39 */     getCanvasEspacial().setCursor(Cursor.OPEN_HAND);
/*  40 */     this.grupoCamera = getCanvasEspacial().getGrupoCanvas();
/*  41 */     this.camera = getCanvasEspacial().getCamera();
/*  42 */     getCanvasEspacial().show3DCanvas();
/*     */   }
/*     */ 
/*     */   
/*     */   public void finaliza() {
/*  47 */     resetarZoom();
/*  48 */     getCanvasEspacial().show2DCanvas();
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao3Arrastado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  53 */     CoordenadaRaster cR = getCanvasEspacial().projeta(p);
/*  54 */     this.mousePosAntigaX = this.mousePosX;
/*  55 */     this.mousePosAntigaY = this.mousePosY;
/*  56 */     this.mousePosX = cR.getX();
/*  57 */     this.mousePosY = cR.getY();
/*  58 */     this.mouseDeltaX = this.mousePosX - this.mousePosAntigaX;
/*  59 */     this.mouseDeltaY = this.mousePosY - this.mousePosAntigaY;
/*     */     
/*  61 */     double z = this.camera.getTranslateZ();
/*  62 */     double newZ = z + this.mouseDeltaX * 2.0D;
/*  63 */     if (z > 1300.0D) {
/*  64 */       this.camera.setTranslateZ(1300.0D);
/*  65 */     } else if (z < -1000.0D) {
/*  66 */       this.camera.setTranslateZ(-1000.0D);
/*     */     } else {
/*  68 */       this.camera.setTranslateZ(newZ);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao3Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  74 */     ajustaCoordenadas(p);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Arrastado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  79 */     getCanvasEspacial().setCursor(Cursor.MOVE);
/*  80 */     CoordenadaRaster cR = getCanvasEspacial().projeta(p);
/*  81 */     this.mousePosAntigaX = this.mousePosX;
/*  82 */     this.mousePosAntigaY = this.mousePosY;
/*  83 */     this.mousePosX = cR.getX();
/*  84 */     this.mousePosY = cR.getY();
/*  85 */     this.mouseDeltaX = this.mousePosX - this.mousePosAntigaX;
/*  86 */     this.mouseDeltaY = this.mousePosY - this.mousePosAntigaY;
/*     */     
/*  88 */     double ry = this.grupoCamera.getRy().getAngle() - this.mouseDeltaX * 0.2D;
/*  89 */     double rx = this.grupoCamera.getRx().getAngle() + this.mouseDeltaY * 0.2D;
/*     */     
/*  91 */     if (ry <= -80.0D) {
/*  92 */       ry = -80.0D;
/*     */     }
/*  94 */     if (ry >= 80.0D) {
/*  95 */       ry = 80.0D;
/*     */     }
/*  97 */     if (rx <= -80.0D) {
/*  98 */       rx = -80.0D;
/*     */     }
/* 100 */     if (rx >= 80.0D) {
/* 101 */       rx = 80.0D;
/*     */     }
/*     */     
/* 104 */     this.grupoCamera.getRy().setAngle(ry);
/* 105 */     this.grupoCamera.getRx().setAngle(rx);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/* 110 */     ajustaCoordenadas(p);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/* 115 */     super.botao1Solto(p, e);
/* 116 */     getCanvasEspacial().setCursor(Cursor.OPEN_HAND);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao2Arrastado(CoordenadaCartesiana p, EstadoTeclado e) {
/* 121 */     CoordenadaRaster cR = getCanvasEspacial().projeta(p);
/* 122 */     this.mousePosAntigaX = this.mousePosX;
/* 123 */     this.mousePosAntigaY = this.mousePosY;
/* 124 */     this.mousePosX = cR.getX();
/* 125 */     this.mousePosY = cR.getY();
/* 126 */     this.mouseDeltaX = this.mousePosX - this.mousePosAntigaX;
/* 127 */     this.mouseDeltaY = this.mousePosY - this.mousePosAntigaY;
/*     */     
/* 129 */     this.grupoCamera.getT().setX(this.grupoCamera.getT().getTx() + this.mouseDeltaX * 0.2D * 4.0D);
/* 130 */     this.grupoCamera.getT().setY(this.grupoCamera.getT().getTy() + this.mouseDeltaY * 0.2D * 4.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao2Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/* 135 */     ajustaCoordenadas(p);
/*     */   }
/*     */ 
/*     */   
/*     */   public void duploClique(CoordenadaCartesiana p, EstadoTeclado e) {
/* 140 */     CanvasCenarioTatico<? extends ControladorCenarioTatico> ct = getCanvasEspacial();
/* 141 */     ct.set3DCanvasWireFrameMode(!ct.get3DCanvasWireFrameMode());
/*     */   }
/*     */   
/*     */   private void ajustaCoordenadas(CoordenadaCartesiana p) {
/* 145 */     CoordenadaRaster cR = getCanvasEspacial().projeta(p);
/* 146 */     this.mousePosX = this.mousePosAntigaX = cR.getX();
/* 147 */     this.mousePosY = this.mousePosAntigaY = cR.getY();
/*     */   }
/*     */   
/*     */   private void resetarZoom() {
/* 151 */     this.grupoCamera.getRx().setAngle(0.0D);
/* 152 */     this.grupoCamera.getRy().setAngle(0.0D);
/* 153 */     this.grupoCamera.getT().setX(0.0D);
/* 154 */     this.grupoCamera.getT().setY(0.0D);
/* 155 */     this.grupoCamera.getT().setZ(0.0D);
/* 156 */     this.camera.setTranslateZ(0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void teclaPressionada(KeyEvent event) {
/* 161 */     if (event.getCode() == KeyCode.R) {
/* 162 */       resetarZoom();
/*     */       return;
/*     */     } 
/*     */   }
/*     */   
/*     */   public void cancelarTarefa() {}
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaMovimentacao3D.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */