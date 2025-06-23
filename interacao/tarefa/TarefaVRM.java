/*     */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.componentes.dominio.entidades.Veiculo;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.radar.VRM;
/*     */ import ipqm.gsd.componentes.nucleo.logon.perfis.PerfilUsuario;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import javafx.application.Platform;
/*     */ import javafx.scene.control.TextField;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TarefaVRM
/*     */   extends Tarefa2P<CanvasCenarioTatico>
/*     */ {
/*     */   private final VRM vrm1;
/*     */   private final VRM vrm2;
/*     */   private VRM vrmAtivo;
/*     */   private TextField txtVrm;
/*  25 */   private static final float[] vrm1Dash = new float[] { 16.0F, 20.0F }, vrm2Dash = new float[] { 10.0F, 12.0F };
/*     */   
/*     */   public TarefaVRM(CanvasCenarioTatico canvas, VRM vrm1, VRM vrm2) {
/*  28 */     super(canvas);
/*  29 */     this.vrm1 = vrm1;
/*  30 */     this.vrm2 = vrm2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void desenha() {
/*  35 */     super.desenha();
/*  36 */     if (this.p2 == null) {
/*     */       return;
/*     */     }
/*     */     
/*  40 */     CanvasCenarioTatico canvasCT = getCanvasEspacial();
/*  41 */     canvasCT.getCamadaInfo().tornaTransparente();
/*  42 */     Graphics2D g2d = canvasCT.getCamadaInfo().getImagem().createGraphics();
/*  43 */     CoordenadaCartesiana cc = Veiculo.getVeiculoReferencial().getCinematicaSimulada().getPosicao().getCoordenadaCartesiana();
/*  44 */     this.vrmAtivo.setDistancia(CoordenadaCartesiana.calcularDistancia(cc, this.p2));
/*  45 */     this.vrmAtivo.setCoordenada(this.p2);
/*     */     
/*  47 */     float[] dash = vrm1Dash;
/*  48 */     switch (this.vrmAtivo.getTipo()) {
/*     */       case VRM1:
/*  50 */         dash = vrm1Dash;
/*     */         break;
/*     */       case VRM2:
/*  53 */         dash = vrm2Dash;
/*     */         break;
/*     */     } 
/*  56 */     if (this.p2 != null) {
/*  57 */       g2d.setStroke(new BasicStroke(4.0F, 2, 0, 10.0F, dash, 0.0F));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  64 */       if (PerfilUsuario.getPerfilUsuarioAtual().isSistemaNavegacao()) {
/*  65 */         g2d.setColor(Color.GREEN);
/*     */       } else {
/*  67 */         g2d.setColor(this.vrmAtivo.isAtivo() ? new Color(255, 51, 0) : new Color(255, 255, 102));
/*     */       } 
/*     */       
/*  70 */       CoordenadaRaster p1Raster = canvasCT.projeta(this.p2);
/*  71 */       CoordenadaRaster p2Raster = canvasCT.projeta(cc);
/*  72 */       int largura = Math.abs(p2Raster.getX() - p1Raster.getX());
/*  73 */       int altura = Math.abs(p2Raster.getY() - p1Raster.getY());
/*  74 */       int raio = (int)Math.sqrt((largura * largura + altura * altura));
/*  75 */       g2d.drawOval(p2Raster.getX() - raio, p2Raster.getY() - raio, 2 * raio, 2 * raio);
/*  76 */       g2d.dispose();
/*     */     } 
/*  78 */     canvasCT.getCamadaInfo().geraImagemFX();
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  83 */     super.botao1Pressionado(p, e);
/*  84 */     getCanvasEspacial().setRedesenhoAutomatico(false);
/*  85 */     this.vrmAtivo.setDesenhar(false);
/*  86 */     getCanvasEspacial().atualizarCamadaTatica();
/*  87 */     desenha();
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Arrastado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  92 */     super.botao1Arrastado(p, e);
/*     */     
/*  94 */     Platform.runLater(() -> this.txtVrm.setText(String.format("%03.2f", new Object[] { Double.valueOf(this.vrmAtivo.getDistancia()) })));
/*     */ 
/*     */ 
/*     */     
/*  98 */     getCanvasEspacial().atualizarCamadaTatica();
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/* 103 */     this.vrmAtivo.setDesenhar(true);
/* 104 */     super.botao1Solto(p, e);
/* 105 */     CanvasCenarioTatico canvasCT = getCanvasEspacial();
/* 106 */     canvasCT.getCamadaInfo().tornaTransparente();
/* 107 */     canvasCT.getCamadaInfo().geraImagemFX();
/* 108 */     canvasCT.atualizarCamadas();
/* 109 */     canvasCT.setRedesenhoAutomatico(true);
/* 110 */     this.vrmAtivo.salvarMemoria(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p) {
/* 115 */     CanvasCenarioTatico canvasCT = (CanvasCenarioTatico)getCanvasEspacial();
/* 116 */     canvasCT.exibirStatus("");
/* 117 */     canvasCT.getCamadaInfo().tornaTransparente();
/*     */   }
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p1, CoordenadaCartesiana p2) {
/* 122 */     CanvasCenarioTatico canvasCT = (CanvasCenarioTatico)getCanvasEspacial();
/* 123 */     canvasCT.exibirStatus("");
/* 124 */     canvasCT.getCamadaInfo().tornaTransparente();
/* 125 */     this.vrmAtivo.salvarMemoria(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void inicializa() {
/* 130 */     this.vrmAtivo = this.vrm1.isAtivo() ? this.vrm1 : this.vrm2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void finaliza() {
/* 135 */     CanvasCenarioTatico canvasCT = (CanvasCenarioTatico)getCanvasEspacial();
/* 136 */     canvasCT.exibirStatus("");
/* 137 */     canvasCT.getCamadaInfo().tornaTransparente();
/*     */   }
/*     */   
/*     */   public void setVrmSelecionado(VRM vrmAtivo, TextField txtVrm) {
/* 141 */     this.vrmAtivo = vrmAtivo;
/* 142 */     this.txtVrm = txtVrm;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaVRM.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */