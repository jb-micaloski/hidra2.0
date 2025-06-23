/*     */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.Posicao;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.JanelaGeografica;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*     */ import ipqm.gsd.hidra.ihm.projetos.geral.cenas.controladores.ControladorCenarioTatico;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import javafx.scene.Cursor;
/*     */ import javafx.scene.ImageCursor;
/*     */ import javafx.scene.image.Image;
/*     */ 
/*     */ public class TarefaZoom
/*     */   extends Tarefa<CanvasCenarioTatico<? extends ControladorCenarioTatico>> {
/*     */   private Cursor cursor;
/*     */   private EstadoInteracao estadoInteracao;
/*     */   protected CoordenadaCartesiana p0;
/*     */   protected CoordenadaCartesiana p1;
/*     */   protected boolean transformando;
/*     */   private CoordenadaGeografica centroAnterior;
/*     */   private double escalaAnterior;
/*     */   
/*     */   public enum EstadoInteracao {
/*  29 */     BOTAO_PRESSIONADO,
/*  30 */     BOTAO_ARRASTADO,
/*  31 */     BOTAO_SOLTO;
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
/*     */   public TarefaZoom(CanvasCenarioTatico<? extends ControladorCenarioTatico> canvasEspacial) {
/*  43 */     super(canvasEspacial);
/*     */   }
/*     */ 
/*     */   
/*     */   public void inicializa() {
/*  48 */     this.cursor = getCanvasEspacial().getCursor();
/*  49 */     Image image = new Image("ipqm/gsd/hidra/ihm/imagens/ponteiros_mouse/zoom.png");
/*  50 */     getCanvasEspacial().setCursor((Cursor)new ImageCursor(image));
/*     */   }
/*     */ 
/*     */   
/*     */   public void finaliza() {
/*  55 */     getCanvasEspacial().setCursor(this.cursor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void botao3Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/*     */     CoordenadaGeografica centro;
/*     */     double escala;
/*  68 */     CanvasCenarioTatico<? extends ControladorCenarioTatico> canvasCT = getCanvasEspacial();
/*  69 */     canvasCT.setModoNavegacao(CanvasCenarioTatico.ModoNavegacao.LIVRE);
/*  70 */     canvasCT.setEnquadramento(false);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  75 */     if (this.centroAnterior != null && this.escalaAnterior != 0.0D) {
/*  76 */       centro = this.centroAnterior;
/*  77 */       escala = this.escalaAnterior;
/*     */       
/*  79 */       this.centroAnterior = null;
/*  80 */       this.escalaAnterior = 0.0D;
/*     */     } else {
/*  82 */       centro = canvasCT.getJanelaGeografica().getCentro();
/*  83 */       escala = canvasCT.getEscalaMN() * 1.5D;
/*     */     } 
/*     */     
/*  86 */     canvasCT.setCentroJanela(centro);
/*  87 */     canvasCT.setEscalaMN(escala);
/*     */     
/*  89 */     canvasCT.atualizarCamadas();
/*     */     
/*  91 */     if (getCanvasEspacial() instanceof CanvasCenarioTatico) {
/*  92 */       getCanvasEspacial().atualizarCamadaTatica();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  98 */     setEstadoInteracao(EstadoInteracao.BOTAO_PRESSIONADO);
/*  99 */     this.p0 = this.p1 = p;
/* 100 */     this.transformando = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void botao1Arrastado(CoordenadaCartesiana p, EstadoTeclado e) {
/* 106 */     setEstadoInteracao(EstadoInteracao.BOTAO_ARRASTADO);
/*     */     
/* 108 */     this.p1 = p;
/*     */     
/* 110 */     desenha();
/* 111 */     if (getCanvasEspacial() instanceof CanvasCenarioTatico) {
/* 112 */       getCanvasEspacial().atualizarCamadaTatica();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void botao1Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/* 119 */     if (this.transformando) {
/* 120 */       if (this.estadoInteracao == EstadoInteracao.BOTAO_ARRASTADO) {
/* 121 */         finalizaTransformacao(this.p0, this.p1);
/* 122 */       } else if (this.estadoInteracao == EstadoInteracao.BOTAO_PRESSIONADO) {
/* 123 */         finalizaTransformacao(this.p0);
/*     */       } 
/* 125 */       this.transformando = false;
/*     */     } 
/*     */     
/* 128 */     setEstadoInteracao(EstadoInteracao.BOTAO_SOLTO);
/*     */     
/* 130 */     this.p0 = this.p1 = null;
/* 131 */     if (getCanvasEspacial() instanceof CanvasCenarioTatico) {
/* 132 */       getCanvasEspacial().atualizarCamadaTatica();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void finalizaTransformacao(CoordenadaCartesiana cc) {
/* 142 */     if (cc == null) {
/*     */       return;
/*     */     }
/*     */     
/* 146 */     CanvasCenarioTatico<? extends ControladorCenarioTatico> canvasCT = getCanvasEspacial();
/* 147 */     canvasCT.setModoNavegacao(CanvasCenarioTatico.ModoNavegacao.LIVRE);
/* 148 */     canvasCT.setEnquadramento(false);
/*     */     
/* 150 */     CoordenadaGeografica centro = CoordenadaGeografica.converterCoordenadaCartesiana(cc, 0.0D);
/* 151 */     double escala = canvasCT.getEscalaMN() / 2.0D;
/*     */     
/* 153 */     canvasCT.setCentroJanela(centro);
/* 154 */     canvasCT.setEscalaMN(escala);
/*     */     
/* 156 */     canvasCT.atualizarCamadas();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void finalizaTransformacao(CoordenadaCartesiana cc1, CoordenadaCartesiana cc2) {
/* 166 */     if (cc1 == null || cc2 == null) {
/*     */       return;
/*     */     }
/*     */     
/* 170 */     CanvasCenarioTatico<? extends ControladorCenarioTatico> canvasCT = getCanvasEspacial();
/* 171 */     canvasCT.setModoNavegacao(CanvasCenarioTatico.ModoNavegacao.LIVRE);
/* 172 */     canvasCT.setEnquadramento(false);
/*     */     
/* 174 */     this.centroAnterior = canvasCT.getJanelaGeografica().getCentro();
/* 175 */     this.escalaAnterior = canvasCT.getEscalaMN();
/*     */     
/* 177 */     JanelaGeografica janela = getJanela(cc1, cc2);
/* 178 */     CoordenadaGeografica centro = canvasCT.defineCentroJanela(new Posicao(janela.getCentro(), null, CoordenadaCartesiana.converterCoordenadaGeografica(janela.getCentro(), 0.0D)));
/* 179 */     double escala = Math.min(janela.getAltura(), janela.getLargura()) / 2.0D;
/*     */     
/* 181 */     canvasCT.setCentroJanela(centro);
/* 182 */     canvasCT.setEscalaMN(escala);
/*     */     
/* 184 */     canvasCT.atualizarCamadas();
/*     */   }
/*     */ 
/*     */   
/*     */   public void desenha() {
/* 189 */     CanvasCenarioTatico<? extends ControladorCenarioTatico> canvasCT = getCanvasEspacial();
/* 190 */     canvasCT.getCamadaInfo().tornaTransparente();
/* 191 */     Graphics2D g2d = canvasCT.getCamadaInfo().getImagem().createGraphics();
/* 192 */     String msg = null;
/*     */     
/* 194 */     if (this.p0 != null && this.p1 != null) {
/* 195 */       CoordenadaRaster p0Raster = canvasCT.projeta(this.p0);
/* 196 */       CoordenadaRaster p1Raster = canvasCT.projeta(this.p1);
/* 197 */       CoordenadaRaster pMinRaster = new CoordenadaRaster(Math.min(p0Raster.getX(), p1Raster.getX()), Math.min(p0Raster.getY(), p1Raster.getY()));
/* 198 */       CoordenadaRaster pMaxRaster = new CoordenadaRaster(Math.max(p0Raster.getX(), p1Raster.getX()), Math.max(p0Raster.getY(), p1Raster.getY()));
/*     */       
/* 200 */       int largura = pMaxRaster.getX() - pMinRaster.getX() + 1;
/* 201 */       int altura = pMaxRaster.getY() - pMinRaster.getY() + 1;
/*     */       
/* 203 */       if (this.p0 != null && this.p1 != null) {
/* 204 */         JanelaGeografica janela = getJanela(this.p0, this.p1);
/* 205 */         msg = String.format("Centralizar mapa em %s x %.1f MN x %.1f MN", new Object[] { janela.toString(), Double.valueOf(janela.getLargura()), Double.valueOf(janela.getAltura()) });
/*     */       } 
/*     */       
/* 208 */       if (largura > 1 || altura > 1) {
/* 209 */         float[] dash1 = { 10.0F };
/* 210 */         g2d.setStroke(new BasicStroke(1.0F, 0, 0, 10.0F, dash1, 0.0F));
/*     */ 
/*     */ 
/*     */         
/* 214 */         g2d.setColor(Color.white);
/* 215 */         g2d.drawRect(pMinRaster.getX(), pMinRaster.getY(), largura, altura);
/*     */       } 
/*     */     } 
/* 218 */     if (getEstadoInteracao() == EstadoInteracao.BOTAO_SOLTO) {
/* 219 */       canvasCT.exibirStatus(g2d, null);
/*     */     } else {
/* 221 */       canvasCT.exibirStatus(g2d, msg);
/*     */     } 
/*     */     
/* 224 */     g2d.dispose();
/* 225 */     canvasCT.getCamadaInfo().geraImagemFX();
/*     */   }
/*     */   
/*     */   private JanelaGeografica getJanela(CoordenadaCartesiana p0, CoordenadaCartesiana p1) {
/* 229 */     CoordenadaCartesiana pMin = new CoordenadaCartesiana(Math.min(p0.getX(), p1.getX()), Math.min(p0.getY(), p1.getY()));
/* 230 */     CoordenadaCartesiana pMax = new CoordenadaCartesiana(Math.max(p0.getX(), p1.getX()), Math.max(p0.getY(), p1.getY()));
/* 231 */     CoordenadaGeografica pMinGeo = CoordenadaGeografica.converterCoordenadaCartesiana(pMin, 0.0D);
/* 232 */     CoordenadaGeografica pMaxGeo = CoordenadaGeografica.converterCoordenadaCartesiana(pMax, 0.0D);
/* 233 */     return new JanelaGeografica(pMinGeo, pMaxGeo);
/*     */   }
/*     */   
/*     */   private void setEstadoInteracao(EstadoInteracao estadoInteracao) {
/* 237 */     this.estadoInteracao = estadoInteracao;
/*     */   }
/*     */   
/*     */   private EstadoInteracao getEstadoInteracao() {
/* 241 */     return this.estadoInteracao;
/*     */   }
/*     */   
/*     */   public void cancelarTarefa() {}
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaZoom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */