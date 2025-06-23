/*     */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.acompanhamentos.Acompanhamento;
/*     */ import ipqm.gsd.componentes.dominio.acompanhamentos.AcompanhamentoManual;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.componentes.dominio.contexto.ContextoTatico;
/*     */ import ipqm.gsd.componentes.dominio.plot.PlotSonar;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.hidra.ihm.camadas.CamadaInfo;
/*     */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*     */ import ipqm.gsd.hidra.ihm.projetos.sonar.interacao.CanvasSonar;
/*     */ import ipqm.gsd.hidra.ihm.util.desenho.UtilJava2D;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javafx.scene.Cursor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TarefaCriaObjetoSonar
/*     */   extends Tarefa2P<CanvasSonar>
/*     */ {
/*     */   public static final String AVISO_MAX_ACOMP_AUTO = "Limite máximo de acompanhamentos automáticos atingido!";
/*     */   public static final String AVISO_MAX_ACOMP_MANUAL = "Limite máximo de acompanhamentos manuais atingido!";
/*     */   private boolean criarAcompAutomatico = true;
/*     */   private Cursor cursor;
/*     */   
/*     */   public TarefaCriaObjetoSonar(CanvasSonar canvas) {
/*  39 */     super(canvas);
/*     */   }
/*     */ 
/*     */   
/*     */   public void inicializa() {
/*  44 */     this.cursor = getCanvasEspacial().getCursor();
/*  45 */     getCanvasEspacial().setCursor(Cursor.CROSSHAIR);
/*     */   }
/*     */ 
/*     */   
/*     */   public void finaliza() {
/*  50 */     getCanvasEspacial().setCursor(this.cursor);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  55 */     super.botao1Pressionado(p, e);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/*  60 */     super.botao1Solto(p, e);
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseEntrou(CoordenadaCartesiana p) {
/*  65 */     this.p1 = p;
/*  66 */     desenha();
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseMovido(CoordenadaCartesiana p, EstadoTeclado e) {
/*  71 */     this.p1 = p;
/*  72 */     desenha();
/*     */   }
/*     */   
/*     */   public void mouseSaiu(CoordenadaCartesiana p, EstadoTeclado e) {
/*  76 */     CanvasSonar canvasSonar = getCanvasEspacial();
/*  77 */     canvasSonar.getCamadaInfo().tornaTransparente();
/*     */   }
/*     */ 
/*     */   
/*     */   public void desenha() {
/*  82 */     CanvasSonar canvasSonar = getCanvasEspacial();
/*  83 */     if (!((TarefaCriaObjetoSonar)canvasSonar.getTarefa()).isCriarAcompAutomatico()) {
/*  84 */       canvasSonar.getCamadaInfo().tornaTransparente();
/*  85 */       CamadaInfo camadaInfo = canvasSonar.getCamadaInfo();
/*  86 */       if (this.p1 != null && this.p2 != null) {
/*  87 */         Graphics2D g2d = camadaInfo.getImagem().createGraphics();
/*  88 */         g2d.setStroke(new BasicStroke(3.0F));
/*  89 */         g2d.setColor(new Color(177, 71, 15));
/*  90 */         CoordenadaRaster p1Raster = canvasSonar.projeta(this.p1);
/*  91 */         CoordenadaRaster p2Raster = canvasSonar.projeta(this.p2);
/*  92 */         g2d.drawRect(p1Raster.getX() - 10, p1Raster.getY() - 10, 20, 20);
/*  93 */         UtilJava2D.desenhaTracejado(g2d, p1Raster.getX(), p1Raster.getY(), p2Raster.getX(), p2Raster.getY());
/*  94 */         String info1 = String.format("%3.0f°", new Object[] { Double.valueOf(getRumo(this.p1, this.p2)) });
/*  95 */         String info2 = String.format("%-5.1f nós", new Object[] { Double.valueOf(getVelocidade(this.p1, this.p2)) });
/*  96 */         Font font = new Font("Arial", 1, 10);
/*  97 */         FontMetrics fm = g2d.getFontMetrics(font);
/*  98 */         g2d.setFont(font);
/*  99 */         g2d.drawString(info1, p2Raster.getX() + 15, p2Raster.getY() - 2);
/* 100 */         g2d.drawString(info2, p2Raster.getX() + 15, p2Raster.getY() + 5 + fm.getHeight() / 2);
/* 101 */         g2d.dispose();
/*     */       } 
/* 103 */       camadaInfo.geraImagemFX();
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
/*     */   private double getRumo(CoordenadaCartesiana p0, CoordenadaCartesiana p1) {
/* 143 */     double rumo = 90.0D - Math.toDegrees(Math.atan2(p1.getY() - p0.getY(), p1.getX() - p0.getX()));
/* 144 */     if (rumo < 0.0D) {
/* 145 */       rumo += 360.0D;
/*     */     }
/* 147 */     return rumo;
/*     */   }
/*     */   
/*     */   private double getVelocidade(CoordenadaCartesiana p0, CoordenadaCartesiana p1) {
/* 151 */     int tamanhoVetorVeloc = 4;
/* 152 */     return CoordenadaCartesiana.calcularDistancia(p0, p1) / tamanhoVetorVeloc / 60.0D;
/*     */   }
/*     */   
/*     */   private void criaAcompanhamentoSonar(CoordenadaCartesiana p, boolean automatico, double rumo, double velocidade) {
/* 156 */     CanvasSonar canvasSonar = getCanvasEspacial();
/*     */ 
/*     */     
/* 159 */     if (automatico) {
/*     */       
/* 161 */       Collection<ObjetoDOMINIO> listaObjetos = Mediador.getInstancia().getContextualizador().getContexto().getObjetos().values();
/*     */       
/* 163 */       for (ObjetoDOMINIO objeto : listaObjetos) {
/* 164 */         if (objeto instanceof PlotSonar) {
/* 165 */           PlotSonar plot = (PlotSonar)objeto;
/* 166 */           if (plot.isMarcado()) {
/*     */             continue;
/*     */           }
/*     */           
/* 170 */           if (plot.isDesenhando()) {
/* 171 */             CoordenadaCartesiana posicaoCart = plot.getObjetoTaticoAssociado().getCinematica().getPosicao().getCoordenadaCartesiana();
/* 172 */             CoordenadaCartesiana posicaoCartRef = p;
/*     */             
/* 174 */             double dx = Math.pow(posicaoCartRef.getX() - posicaoCart.getX(), 2.0D);
/* 175 */             double dy = Math.pow(posicaoCartRef.getY() - posicaoCart.getY(), 2.0D);
/*     */             
/* 177 */             double distancia = Math.sqrt(dx + dy);
/*     */             
/* 179 */             if (distancia <= 0.3D) {
/* 180 */               plot.setMarcado(true);
/* 181 */               plot.salvarMemoria(false);
/*     */             }
/*     */           
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       
/* 189 */       canvasSonar.getControlador().getControladorPainelSitrep().adicionarAcompanhamentoAoSitrep(fabricarAcompanhamentoManual(p, rumo, velocidade));
/*     */     } 
/*     */   }
/*     */   
/*     */   private Acompanhamento fabricarAcompanhamentoManual(CoordenadaCartesiana posicao, double rumo, double velocidade) {
/*     */     try {
/* 195 */       CoordenadaGeografica cg = CoordenadaGeografica.converterCoordenadaCartesiana(posicao, 0.0D);
/* 196 */       if (velocidade > 9999.0D) {
/* 197 */         velocidade = 1.0E-5D;
/*     */       }
/* 199 */       AcompanhamentoManual a = AcompanhamentoManual.fabricar(cg.getLatitude(), cg.getLongitude(), rumo, velocidade, (
/* 200 */           (ContextoTatico)Mediador.getInstancia().getContextualizador()
/* 201 */           .getContexto()).getVeiculoReferencial().getPosicao(), null);
/* 202 */       a.salvarMemoria(true);
/* 203 */       getCanvasEspacial().getCamadaInfo().tornaTransparente();
/* 204 */       getCanvasEspacial().getCamadaInfo().geraImagemFX();
/* 205 */       return (Acompanhamento)a;
/* 206 */     } catch (Exception e) {
/* 207 */       Log.gravarLogExcecao("Erro ao fabricar acompanhamento manual no sonar", this, e);
/*     */ 
/*     */       
/* 210 */       return null;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void executarComando(Comando comando, CoordenadaCartesiana posicao) {
/* 231 */     if (isHabilitarComando(comando))
/*     */     {
/* 233 */       switch (comando) {
/*     */       
/*     */       } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void habilitarComandos() {
/* 258 */     setHabilitarComando(Comando.CRIAR_ACOMPANHAMENTO, true);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Comando, Boolean> obterComandosHabilitados() {
/* 283 */     Map<Comando, Boolean> comandosHabilitados = new HashMap<>();
/*     */     
/* 285 */     comandosHabilitados.put(Comando.CRIAR_ACOMPANHAMENTO, Boolean.valueOf(isHabilitarComando(Comando.CRIAR_ACOMPANHAMENTO)));
/*     */     
/* 287 */     return comandosHabilitados;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p) {
/* 293 */     criaAcompanhamentoSonar(p, this.criarAcompAutomatico, 0.0D, 0.0D);
/*     */   }
/*     */   
/*     */   public boolean isCriarAcompAutomatico() {
/* 297 */     return this.criarAcompAutomatico;
/*     */   }
/*     */   
/*     */   public void setCriarAcompAutomatico(boolean criarAcompAutomatico) {
/* 301 */     this.criarAcompAutomatico = criarAcompAutomatico;
/*     */   }
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p1, CoordenadaCartesiana p2) {
/* 306 */     criaAcompanhamentoSonar(p1, this.criarAcompAutomatico, getRumo(p1, p2), getVelocidade(p1, p2));
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaCriaObjetoSonar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */