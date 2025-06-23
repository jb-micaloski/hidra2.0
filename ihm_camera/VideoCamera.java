/*     */ package ipqm.gsd.hidra.ihm.ihm_camera;
/*     */ 
/*     */ import com.sun.jna.Memory;
/*     */ import com.sun.jna.NativeLibrary;
/*     */ import ipqm.gsd.componentes.dominio.camera.CameraFixa;
/*     */ import ipqm.gsd.componentes.nucleo.configuracao.Diretorios;
/*     */ import java.nio.ByteBuffer;
/*     */ import javafx.application.Platform;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.canvas.Canvas;
/*     */ import javafx.scene.image.PixelFormat;
/*     */ import javafx.scene.image.PixelWriter;
/*     */ import javafx.scene.image.WritablePixelFormat;
/*     */ import javafx.scene.layout.Pane;
/*     */ import uk.co.caprica.vlcj.component.DirectMediaPlayerComponent;
/*     */ import uk.co.caprica.vlcj.player.direct.BufferFormat;
/*     */ import uk.co.caprica.vlcj.player.direct.BufferFormatCallback;
/*     */ import uk.co.caprica.vlcj.player.direct.DirectMediaPlayer;
/*     */ import uk.co.caprica.vlcj.player.direct.format.RV32BufferFormat;
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
/*     */ 
/*     */ public class VideoCamera
/*     */ {
/*     */   private boolean useSourceSize;
/*  38 */   private int width = 420;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   private int height = 320;
/*     */ 
/*     */ 
/*     */   
/*     */   private Canvas canvas;
/*     */ 
/*     */   
/*     */   private PixelWriter pixelWriter;
/*     */ 
/*     */   
/*     */   private WritablePixelFormat<ByteBuffer> pixelFormat;
/*     */ 
/*     */   
/*     */   private DirectMediaPlayerComponent mediaPlayerComponent;
/*     */ 
/*     */   
/*     */   private Pane painelCamera;
/*     */ 
/*     */   
/*     */   private final CameraFixa cameraFixa;
/*     */ 
/*     */ 
/*     */   
/*     */   public VideoCamera(CameraFixa cameraFixa) {
/*  67 */     this.cameraFixa = cameraFixa;
/*  68 */     this.useSourceSize = true;
/*     */     
/*  70 */     inicializar();
/*     */   }
/*     */   
/*     */   public VideoCamera(CameraFixa cameraFixa, int width, int height) {
/*  74 */     this.cameraFixa = cameraFixa;
/*  75 */     this.useSourceSize = false;
/*  76 */     this.width = width;
/*  77 */     this.height = height;
/*     */     
/*  79 */     inicializar();
/*     */   }
/*     */   
/*     */   private void inicializar() {
/*  83 */     this.canvas = new Canvas();
/*     */     
/*  85 */     NativeLibrary.addSearchPath("libvlc", Diretorios.getDiretorioPadrao(Diretorios.Diretorio.VLC));
/*  86 */     this.pixelWriter = this.canvas.getGraphicsContext2D().getPixelWriter();
/*  87 */     this.pixelFormat = PixelFormat.getByteBgraPreInstance();
/*     */     
/*  89 */     this.painelCamera = new Pane(new Node[] { (Node)this.canvas });
/*     */   }
/*     */   
/*     */   public Pane getPainelCamera() {
/*  93 */     return this.painelCamera;
/*     */   }
/*     */   
/*     */   public CameraFixa getCameraFixa() {
/*  97 */     return this.cameraFixa;
/*     */   }
/*     */   
/*     */   public boolean isReproduzindo() {
/* 101 */     if (this.mediaPlayerComponent != null) {
/* 102 */       return this.mediaPlayerComponent.getMediaPlayer().isPlaying();
/*     */     }
/* 104 */     return false;
/*     */   }
/*     */   
/*     */   public void continuar() {
/* 108 */     if (this.mediaPlayerComponent != null) {
/* 109 */       this.mediaPlayerComponent.getMediaPlayer().play();
/*     */     }
/*     */   }
/*     */   
/*     */   public void parar() {
/* 114 */     if (this.mediaPlayerComponent != null) {
/* 115 */       this.mediaPlayerComponent.getMediaPlayer().stop();
/*     */     }
/*     */   }
/*     */   
/*     */   public void inicializarVideo() {
/* 120 */     this.mediaPlayerComponent = new CameraMediaPlayerComponent();
/* 121 */     this.mediaPlayerComponent.getMediaPlayer().playMedia(this.cameraFixa.getStream(), new String[] { ":live-caching=10000", ":no-sout-rtp-sap", ":no-sout-standard-sap", ":sout-all", ":sout-keep" });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private class CameraMediaPlayerComponent
/*     */     extends DirectMediaPlayerComponent
/*     */   {
/*     */     public void display(DirectMediaPlayer mediaPlayer, Memory[] nativeBuffers, BufferFormat bufferFormat) {
/* 132 */       Memory nativeBuffer = nativeBuffers[0];
/* 133 */       ByteBuffer byteBuffer = nativeBuffer.getByteBuffer(0L, nativeBuffer.size());
/*     */       
/* 135 */       Platform.runLater(() -> VideoCamera.this.pixelWriter.setPixels(0, 0, bufferFormat.getWidth(), bufferFormat.getHeight(), (PixelFormat)VideoCamera.this.pixelFormat, byteBuffer, bufferFormat.getPitches()[0]));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public CameraMediaPlayerComponent() {
/* 142 */       super(new VideoCamera.CameraBufferFormatCallback(VideoCamera.this, null));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private class CameraBufferFormatCallback
/*     */     implements BufferFormatCallback
/*     */   {
/*     */     private CameraBufferFormatCallback() {}
/*     */ 
/*     */     
/*     */     public BufferFormat getBufferFormat(int sourceWidth, int sourceHeight) {
/* 155 */       if (VideoCamera.this.useSourceSize) {
/* 156 */         VideoCamera.this.width = sourceWidth;
/* 157 */         VideoCamera.this.height = sourceHeight;
/*     */       } 
/* 159 */       Platform.runLater(() -> {
/*     */             VideoCamera.this.canvas.setWidth(VideoCamera.this.width);
/*     */             
/*     */             VideoCamera.this.canvas.setHeight(VideoCamera.this.height);
/*     */             VideoCamera.this.canvas.toFront();
/*     */           });
/* 165 */       return (BufferFormat)new RV32BufferFormat(VideoCamera.this.width, VideoCamera.this.height);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/ihm_camera/VideoCamera.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */