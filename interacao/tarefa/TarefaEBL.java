/*     */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaPolar;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.componentes.dominio.entidades.Veiculo;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.radar.EBL;
/*     */ import ipqm.gsd.componentes.nucleo.logon.perfis.PerfilUsuario;
/*     */ import ipqm.gsd.hidra.ihm.camadas.CamadaInfo;
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
/*     */ public class TarefaEBL
/*     */   extends Tarefa2P<CanvasCenarioTatico>
/*     */ {
/*     */   private final EBL ebl1;
/*     */   private final EBL ebl2;
/*     */   private EBL eblAtivo;
/*     */   private TextField txtEbl;
/*     */   private double marcacao;
/*  28 */   private static final float[] ebl1Dash = new float[] { 16.0F, 20.0F }, ebl2Dash = new float[] { 10.0F, 12.0F };
/*     */   
/*     */   public TarefaEBL(CanvasCenarioTatico canvas, EBL ebl1, EBL ebl2) {
/*  31 */     super(canvas);
/*  32 */     this.ebl1 = ebl1;
/*  33 */     this.ebl2 = ebl2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  38 */     super.botao1Pressionado(p, e);
/*     */     
/*  40 */     this.eblAtivo.setDesenhar(false);
/*     */     
/*  42 */     getCanvasEspacial().setRedesenhoAutomatico(false);
/*  43 */     CanvasCenarioTatico canvasCT = getCanvasEspacial();
/*  44 */     canvasCT.atualizarCamadaTatica();
/*  45 */     desenha();
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Arrastado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  50 */     super.botao1Arrastado(p, e);
/*     */     
/*  52 */     Platform.runLater(() -> this.txtEbl.setText(String.format("%03.0f", new Object[] { Double.valueOf(this.eblAtivo.getMarcacao()) })));
/*     */ 
/*     */ 
/*     */     
/*  56 */     getCanvasEspacial().atualizarCamadaTatica();
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/*  61 */     this.eblAtivo.setDesenhar(true);
/*  62 */     super.botao1Solto(p, e);
/*  63 */     CanvasCenarioTatico canvasCT = getCanvasEspacial();
/*  64 */     canvasCT.getCamadaInfo().tornaTransparente();
/*  65 */     canvasCT.getCamadaInfo().geraImagemFX();
/*  66 */     canvasCT.atualizarCamadas();
/*  67 */     canvasCT.setRedesenhoAutomatico(true);
/*  68 */     this.eblAtivo.salvarMemoria(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p) {
/*  73 */     CanvasCenarioTatico canvasCT = (CanvasCenarioTatico)getCanvasEspacial();
/*  74 */     canvasCT.exibirStatus("");
/*  75 */     canvasCT.getCamadaInfo().tornaTransparente();
/*     */   }
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p1, CoordenadaCartesiana p2) {
/*  80 */     CanvasCenarioTatico canvasCT = (CanvasCenarioTatico)getCanvasEspacial();
/*  81 */     canvasCT.exibirStatus("");
/*  82 */     canvasCT.getCamadaInfo().tornaTransparente();
/*  83 */     this.eblAtivo.salvarMemoria(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void desenha() {
/*  88 */     if (this.p2 != null) {
/*  89 */       CanvasCenarioTatico canvasCT = getCanvasEspacial();
/*  90 */       CamadaInfo camadaInfo = canvasCT.getCamadaInfo();
/*  91 */       camadaInfo.tornaTransparente();
/*  92 */       CoordenadaCartesiana cc = Veiculo.getVeiculoReferencial().getCinematica().getPosicao().getCoordenadaCartesiana();
/*     */       
/*  94 */       double alcance = 6.0D;
/*     */       
/*  96 */       CoordenadaPolar cp = CoordenadaPolar.converterCoordenadaCartesiana(cc, this.p2);
/*  97 */       this.marcacao = cp.getMarcacao();
/*  98 */       this.eblAtivo.setMarcacao(this.marcacao);
/*  99 */       CoordenadaPolar cpLimiteTela = new CoordenadaPolar(this.marcacao, alcance);
/* 100 */       CoordenadaCartesiana ccLimiteTela = CoordenadaCartesiana.converterCoordenadaPolar(cc, cpLimiteTela);
/* 101 */       CoordenadaRaster crLimiteTela = canvasCT.projeta(ccLimiteTela);
/*     */       
/* 103 */       float[] dash = ebl1Dash;
/* 104 */       switch (this.eblAtivo.getTipo()) {
/*     */         case EBL1:
/* 106 */           dash = ebl1Dash;
/*     */           break;
/*     */         case EBL2:
/* 109 */           dash = ebl2Dash;
/*     */           break;
/*     */       } 
/* 112 */       Graphics2D g2d = camadaInfo.getImagem().createGraphics();
/* 113 */       g2d.setStroke(new BasicStroke(4.0F, 2, 0, 10.0F, dash, 0.0F));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 120 */       if (PerfilUsuario.getPerfilUsuarioAtual().isSistemaNavegacao()) {
/* 121 */         g2d.setColor(Color.GREEN);
/*     */       } else {
/* 123 */         g2d.setColor(this.eblAtivo.isAtivo() ? new Color(255, 51, 0) : new Color(255, 255, 102));
/*     */       } 
/*     */       
/* 126 */       CoordenadaRaster p2Raster = canvasCT.projeta(cc);
/* 127 */       g2d.drawLine(p2Raster.getX(), p2Raster.getY(), crLimiteTela.getX(), crLimiteTela.getY());
/* 128 */       g2d.dispose();
/* 129 */       camadaInfo.geraImagemFX();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void inicializa() {
/* 135 */     this.eblAtivo = this.ebl1.isAtivo() ? this.ebl1 : this.ebl2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void finaliza() {
/* 140 */     CanvasCenarioTatico canvasCT = (CanvasCenarioTatico)getCanvasEspacial();
/* 141 */     canvasCT.exibirStatus("");
/* 142 */     canvasCT.getCamadaInfo().tornaTransparente();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEblSelecionado(EBL eblAtivo, TextField txtEbl) {
/* 147 */     this.eblAtivo = eblAtivo;
/* 148 */     this.txtEbl = txtEbl;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaEBL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */