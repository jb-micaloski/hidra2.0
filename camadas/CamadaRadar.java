/*     */ package ipqm.gsd.hidra.ihm.camadas;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.ObjetoTatico;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaPolar;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.componentes.dominio.entidades.Veiculo;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.Janela;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.JanelaCartesiana;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*     */ import ipqm.gsd.componentes.nucleo.driver.Sensor;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.logon.perfis.PerfilUsuario;
/*     */ import ipqm.gsd.hidra.ihm.camadas.filtros.FiltroRadar;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasEspacial;
/*     */ import ipqm.gsd.hidra.ihm.interacao.tarefa.TarefaSelecao;
/*     */ import ipqm.gsd.radar.view.RadarImageParameters;
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CamadaRadar
/*     */   extends CamadaTatica<FiltroRadar>
/*     */ {
/*     */   private boolean aneisDistancia;
/*     */   private RadarImageParameters radarImageParameters;
/*     */   private final CanvasCenarioTatico canvasRadarGenerico;
/*     */   
/*     */   public CamadaRadar(FiltroRadar filtro, int largura, int altura, CanvasCenarioTatico canvasRadarGenerico) {
/*  39 */     super(filtro, largura, altura);
/*  40 */     this.canvasRadarGenerico = canvasRadarGenerico;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void desenha(Janela janela, List<ObjetoTatico> listaObjetos, TarefaSelecao<? extends CanvasEspacial> tarefaSelecao, float opacidade) {
/*  47 */     tornaTransparente();
/*  48 */     setJanelaCartesiana((JanelaCartesiana)janela);
/*  49 */     if (this.aneisDistancia) {
/*  50 */       if (this.escalaMN == 0.125D || this.escalaMN == 0.25D || this.escalaMN == 0.5D) {
/*  51 */         desenhaAneis(5);
/*  52 */       } else if (this.escalaMN == 0.75D) {
/*  53 */         desenhaAneis(3);
/*     */       } else {
/*  55 */         desenhaAneis(6);
/*     */       } 
/*     */     }
/*     */     
/*  59 */     if (listaObjetos == null) {
/*     */       return;
/*     */     }
/*     */     
/*  63 */     this.tarefaSelecao = tarefaSelecao;
/*     */     
/*  65 */     desenhaGiro(Veiculo.getVeiculoReferencial(), this, this.imagem.createGraphics());
/*     */     
/*  67 */     Iterator<ObjetoTatico> iterator = listaObjetos.iterator();
/*     */     
/*  69 */     while (iterator.hasNext()) {
/*  70 */       ObjetoTatico objetoTatico = iterator.next();
/*     */       try {
/*  72 */         if (!(objetoTatico instanceof Veiculo)) {
/*  73 */           Mediador.getInstancia().getGestor()
/*  74 */             .getGestorObjetos().desenharObjeto((ObjetoDOMINIO)objetoTatico, this);
/*     */         }
/*  76 */       } catch (Exception e) {
/*  77 */         Log.gravarLogExcecao("Erro ao desenhar objetos táticos", this, e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void desenhaGiro(Veiculo veiculo, CamadaBI camada, Graphics2D g2d) {
/*  84 */     if (this.radarImageParameters == null) {
/*     */       return;
/*     */     }
/*     */     
/*  88 */     Sensor giro = null;
/*  89 */     if (Veiculo.getVeiculoReferencial().getMapaSensores().get(Sensor.Tipo.GIRO) != null) {
/*  90 */       giro = ((List<Sensor>)Veiculo.getVeiculoReferencial().getMapaSensores().get(Sensor.Tipo.GIRO)).get(0);
/*     */     } else {
/*     */       return;
/*     */     } 
/*     */     
/*  95 */     double rumo = 0.0D;
/*  96 */     if (giro.isAvariado() || !giro.isHabilitado()) {
/*  97 */       if (!(PerfilUsuario.getPerfilUsuarioAtual() instanceof ipqm.gsd.componentes.nucleo.logon.perfis.AlunoSSTT)) {
/*  98 */         this.radarImageParameters.setOrientation(RadarImageParameters.Orientation.HEAD_UP);
/*     */       }
/* 100 */     } else if (this.radarImageParameters.getOrientation() != RadarImageParameters.Orientation.HEAD_UP) {
/* 101 */       rumo = veiculo.getCinematicaSimulada().getRumo().getRumoSuperficie().getRumo();
/* 102 */       if (this.radarImageParameters.getOrientation() == RadarImageParameters.Orientation.COURSE_UP) {
/* 103 */         rumo = veiculo.getCinematicaSimulada().getRumo().getRumoSuperficie().getRumo() - this.radarImageParameters.getReferenceCourse();
/*     */       }
/*     */     } 
/*     */     
/* 107 */     double alcance = Math.min(camada.getJanelaCartesiana().getLargura(), camada.getJanelaCartesiana().getAltura()) / 2.0D;
/* 108 */     CoordenadaCartesiana posCart0 = veiculo.getPosicao().getCoordenadaCartesiana();
/* 109 */     CoordenadaPolar cp = new CoordenadaPolar(rumo, 2.0D * alcance);
/* 110 */     CoordenadaCartesiana posCart1 = CoordenadaCartesiana.converterCoordenadaPolar(posCart0, cp);
/*     */     
/* 112 */     CoordenadaRaster pos0 = camada.converterCoordenadaCartesianaParaRaster(posCart0);
/* 113 */     CoordenadaRaster pos1 = camada.converterCoordenadaCartesianaParaRaster(posCart1);
/*     */     
/* 115 */     Color corObjeto = Color.RED;
/* 116 */     g2d.setComposite(AlphaComposite.getInstance(3, 0.5F));
/* 117 */     g2d.setStroke(new BasicStroke(2.0F));
/* 118 */     g2d.setColor(corObjeto);
/* 119 */     g2d.drawLine(pos0.getX(), pos0.getY(), pos1.getX(), pos1.getY());
/* 120 */     g2d.setStroke(new BasicStroke(1.0F));
/* 121 */     g2d.dispose();
/*     */   }
/*     */   
/*     */   private void desenhaAneis(int n) {
/* 125 */     int quantAneis = n * 2;
/* 126 */     double raio = this.escalaMN / n;
/*     */     
/* 128 */     CoordenadaCartesiana ccPPN = Veiculo.getVeiculoReferencial().getCinematicaSimulada().getPosicao().getCoordenadaCartesiana();
/* 129 */     CoordenadaRaster crPPN = converterCoordenadaCartesianaParaRaster(Veiculo.getVeiculoReferencial()
/* 130 */         .getCinematicaSimulada().getPosicao().getCoordenadaCartesiana());
/*     */     
/* 132 */     Graphics2D g2d = this.imagem.createGraphics();
/* 133 */     g2d.setColor(Color.GREEN);
/* 134 */     g2d.setStroke(new BasicStroke(1.0F));
/*     */     
/* 136 */     for (int i = 1; i <= quantAneis; i++) {
/* 137 */       CoordenadaPolar cp = new CoordenadaPolar(0.0D, raio * i);
/* 138 */       CoordenadaCartesiana cc = CoordenadaCartesiana.converterCoordenadaPolar(ccPPN, cp);
/* 139 */       int raioAtual = converterDistanciaCartesianaParaRaster(CoordenadaCartesiana.calcularDistancia(ccPPN, cc));
/* 140 */       g2d.drawOval(crPPN.getX() - raioAtual, crPPN.getY() - raioAtual, raioAtual * 2, raioAtual * 2);
/*     */     } 
/* 142 */     g2d.dispose();
/*     */   }
/*     */   
/*     */   public boolean isAneisDistancia() {
/* 146 */     return this.aneisDistancia;
/*     */   }
/*     */   
/*     */   public void setAneisDistancia(boolean aneisDistancia) {
/* 150 */     this.aneisDistancia = aneisDistancia;
/*     */   }
/*     */   
/*     */   public RadarImageParameters getRadarImageParameters() {
/* 154 */     return this.radarImageParameters;
/*     */   }
/*     */   
/*     */   public void setRadarImageParameters(RadarImageParameters radarImageParameters) {
/* 158 */     this.radarImageParameters = radarImageParameters;
/*     */   }
/*     */   
/*     */   public CanvasCenarioTatico getCanvasRadarGenerico() {
/* 162 */     return this.canvasRadarGenerico;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/camadas/CamadaRadar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */