/*     */ package ipqm.gsd.hidra.ihm.camadas;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.ObjetoTatico;
/*     */ import ipqm.gsd.componentes.dominio.acompanhamentos.Acompanhamento;
/*     */ import ipqm.gsd.componentes.dominio.acompanhamentos.AcompanhamentoSonar;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaPolar;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.componentes.dominio.deteccaoVisual.Pointer;
/*     */ import ipqm.gsd.componentes.dominio.entidades.Veiculo;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.JanelaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.plot.PlotSonar;
/*     */ import ipqm.gsd.componentes.dominio.plot.RuidoSonar;
/*     */ import ipqm.gsd.componentes.dominio.sensores.Sonar;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*     */ import ipqm.gsd.componentes.nucleo.configuracao.Diretorios;
/*     */ import ipqm.gsd.componentes.nucleo.driver.Sensor;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.objeto_visual.Filtro;
/*     */ import ipqm.gsd.componentes.nucleo.objeto_visual.ObjetoVisual;
/*     */ import ipqm.gsd.componentes.nucleo.util.ConversorUnidades;
/*     */ import ipqm.gsd.hidra.ihm.camadas.filtros.FiltroSonar;
/*     */ import ipqm.gsd.hidra.ihm.controle.Controlador;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasEspacial;
/*     */ import ipqm.gsd.hidra.ihm.interacao.tarefa.TarefaSelecao;
/*     */ import ipqm.gsd.hidra.ihm.objetos_visuais.objetos_graficos.ObjetoGraficoAcompanhamento;
/*     */ import ipqm.gsd.hidra.ihm.projetos.sonar.cenas.controladores.ControladorSonar;
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Composite;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Arc2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.stream.Collectors;
/*     */ import javafx.scene.shape.Circle;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CamadaSonar
/*     */   extends CamadaTatica<FiltroSonar>
/*     */ {
/*     */   public static final double MULTIPLICADOR_X = 3.38D;
/*  53 */   public static final Color COR_PREENCHIMENTO = new Color(255, 200, 0, 100);
/*  54 */   public static Font FONTE = new Font("Roboto", 0, 9);
/*  55 */   private final Map<Integer, Map<Integer, RuidoSonar>> MAPA_RUIDOS = new ConcurrentHashMap<>();
/*  56 */   private final Map<PlotSonar, List<RuidoSonar>> MAPA_RUIDOS_PLOTS = new ConcurrentHashMap<>();
/*  57 */   private final Map<PlotSonar, List<RuidoSonar>> MAPA_RUIDOS_PLOT_GRAFICO = new ConcurrentHashMap<>();
/*  58 */   public static final Color COR_RUIDO_VERDE = new Color(0, 255, 0, 150);
/*  59 */   public static final Color COR_RUIDO_VERMELHO = new Color(255, 0, 0, 150);
/*     */   public static final int RAIO_MINIMO = 10;
/*     */   public static final int RAIO_MAXIMO = 480;
/*     */   public static final int VELOCIDADE_SONAR = 820;
/*  63 */   private int CICLO_DESENHO = 0;
/*     */   
/*  65 */   private final Random random = new Random();
/*  66 */   private float indicePersistencia = 1.0F;
/*     */   
/*  68 */   private Arc2D.Double arcoIgnorarSinalProa = null;
/*  69 */   private Arc2D.Double arcoIgnorarSinalPopa = null;
/*     */   
/*  71 */   private ControladorSonar controladorSonar = null;
/*     */   
/*     */   private final Circle rosaDeManobra;
/*     */   private boolean camadaPrincipal;
/*  75 */   private BufferedImage mancha = null;
/*  76 */   public CoordenadaRaster rasterCentroTela = new CoordenadaRaster(960, 540);
/*     */   private final CamadaBI camadaSonarAtivo;
/*     */   private boolean visualizacaoProa;
/*  79 */   private double rumoAnteriorVeiculo = 0.0D;
/*     */   
/*     */   public CamadaSonar(FiltroSonar filtro, Circle rosaDeManobra, int largura, int altura) {
/*  82 */     super(filtro, largura, altura);
/*  83 */     this.rosaDeManobra = rosaDeManobra;
/*     */     try {
/*  85 */       String path = Diretorios.getDiretorioPadrao(Diretorios.Diretorio.AJUDA) + "fontes/Roboto-Thin.ttf";
/*  86 */       FONTE = Font.createFont(0, new File(path)).deriveFont(0, 10.0F);
/*  87 */     } catch (Exception ex) {
/*  88 */       Log.gravarLogExcecao("Erro ao carregar fonte", this, ex);
/*     */     } 
/*  90 */     this.camadaSonarAtivo = new CamadaBI<>(new FiltroSonar(), 1220, 813);
/*     */   }
/*     */ 
/*     */   
/*     */   public void desenha(JanelaCartesiana janela, List<ObjetoTatico> listaObjetos, TarefaSelecao<? extends CanvasEspacial> tarefaSelecao, float opacidade, Controlador controlador) {
/*  95 */     if (this.controladorSonar == null) {
/*  96 */       this.controladorSonar = (ControladorSonar)controlador;
/*     */     }
/*  98 */     setJanelaCartesiana(janela);
/*     */     
/* 100 */     if (listaObjetos == null) {
/*     */       return;
/*     */     }
/*     */     
/* 104 */     this.tarefaSelecao = tarefaSelecao;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 112 */       if (this.controladorSonar.getPainelSonarAtivo().isVisible()) {
/*     */         
/* 114 */         tornaTransparente();
/* 115 */         listaObjetos.forEach(obj -> Mediador.getInstancia().getGestor().getGestorObjetos().desenharObjeto((ObjetoDOMINIO)obj, this));
/*     */ 
/*     */         
/* 118 */         desenhaManchas();
/* 119 */         if (this.controladorSonar.getPaneSonarGrafico().isVisible()) {
/* 120 */           desenhaCanvasGrafico(listaObjetos);
/*     */         } else {
/* 122 */           desenharRumoSonar(Veiculo.getVeiculoReferencial(), this);
/*     */         } 
/*     */       } 
/* 125 */     } catch (Exception e) {
/* 126 */       Log.gravarLogExcecao("Erro ao tentar desenhar objetos graficos no Sonar", this, e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void desenharRumoSonar(Veiculo veiculo, CamadaBI camada) {
/* 131 */     Graphics2D g2d = camada.getImagem().createGraphics();
/* 132 */     double alcance = getEscalaMN() / 1000.0D;
/* 133 */     CoordenadaCartesiana posCart0 = veiculo.getCinematicaSimulada().getPosicao().getCoordenadaCartesiana();
/*     */     
/* 135 */     Double rumoSonar = Double.valueOf(veiculo.getRumo().getRumoSuperficie().getRumo());
/* 136 */     CoordenadaPolar cp = new CoordenadaPolar(rumoSonar.doubleValue(), 2.0D * alcance);
/* 137 */     CoordenadaCartesiana posCart1 = CoordenadaCartesiana.converterCoordenadaPolar(posCart0, cp);
/*     */     
/* 139 */     this.rasterCentroTela = camada.converterCoordenadaCartesianaParaRaster(posCart0);
/* 140 */     CoordenadaRaster pos1 = camada.converterCoordenadaCartesianaParaRaster(posCart1);
/*     */     
/* 142 */     if (veiculo.getVelocidade().getVelocidadeSuperficie().getVelocidade() > 0.0D && 
/* 143 */       recuperarSensorSonar().getTipoSensor() == Sensor.Tipo.SONAR_PRS) {
/*     */       
/* 145 */       double arcoCego = 40.0D;
/*     */       
/* 147 */       double anguloMaxPopa = converteMarcacaoParaAngulo(rumoSonar.doubleValue() - arcoCego);
/* 148 */       double anguloMinPopa = converteMarcacaoParaAngulo(rumoSonar.doubleValue() + arcoCego);
/*     */       
/* 150 */       double anguloMaxProa = converteMarcacaoParaAngulo(veiculo.getRumo().getRumoSuperficie().getRumo() - arcoCego);
/* 151 */       double anguloMinProa = converteMarcacaoParaAngulo(veiculo.getRumo().getRumoSuperficie().getRumo() + arcoCego);
/*     */       
/* 153 */       this.arcoIgnorarSinalPopa = new Arc2D.Double(this.rasterCentroTela.getX(), this.rasterCentroTela.getY(), 900.0D, 900.0D, anguloMinPopa, anguloMaxPopa - anguloMinPopa, 2);
/* 154 */       this.arcoIgnorarSinalProa = new Arc2D.Double(this.rasterCentroTela.getX(), this.rasterCentroTela.getY(), 900.0D, 900.0D, anguloMinProa, anguloMaxProa - anguloMinProa, 2);
/* 155 */       Composite alpha = AlphaComposite.getInstance(3, 0.2F);
/* 156 */       g2d.setComposite(alpha);
/* 157 */       g2d.setColor(Color.WHITE);
/* 158 */       g2d.fill(this.arcoIgnorarSinalProa);
/* 159 */       g2d.fill(this.arcoIgnorarSinalPopa);
/* 160 */       g2d.dispose();
/*     */     } 
/*     */     
/* 163 */     g2d = camada.getImagem().createGraphics();
/* 164 */     float[] dashPattern = { 5.0F, 5.0F };
/* 165 */     g2d.setStroke(new BasicStroke(2.0F, 0, 0, 10.0F, dashPattern, 0.0F));
/* 166 */     g2d.setColor(Color.WHITE);
/* 167 */     g2d.drawLine(this.rasterCentroTela.getX(), this.rasterCentroTela.getY(), pos1.getX(), pos1.getY());
/* 168 */     g2d.dispose();
/*     */   }
/*     */   
/*     */   private void desenharCursorMarcacao(Veiculo veiculo, CamadaBI camada) {
/* 172 */     Graphics2D g2d = camada.getImagem().createGraphics();
/* 173 */     double alcance = Math.min(camada.getJanelaCartesiana().getLargura(), camada.getJanelaCartesiana().getAltura()) / 2.0D;
/* 174 */     CoordenadaCartesiana posCart0 = veiculo.getPosicao().getCoordenadaCartesiana();
/*     */     
/* 176 */     CoordenadaPolar cp = new CoordenadaPolar(recuperarSensorSonar().getCursorMarcacao(), 2.0D * alcance);
/* 177 */     CoordenadaCartesiana posCart1 = CoordenadaCartesiana.converterCoordenadaPolar(posCart0, cp);
/*     */     
/* 179 */     CoordenadaRaster pos0 = camada.converterCoordenadaCartesianaParaRaster(posCart0);
/* 180 */     CoordenadaRaster pos1 = camada.converterCoordenadaCartesianaParaRaster(posCart1);
/*     */     
/* 182 */     g2d.setColor(Color.white);
/* 183 */     g2d.drawLine(pos0.getX(), pos0.getY(), pos1.getX(), pos1.getY());
/* 184 */     g2d.dispose();
/*     */   }
/*     */   
/*     */   public Sonar recuperarSensorSonar() {
/* 188 */     return getControladorSonar().getSensorSonar();
/*     */   }
/*     */   
/*     */   public double converteMarcacaoParaAngulo(double marcacao) {
/* 192 */     return 90.0D - marcacao;
/*     */   }
/*     */   
/*     */   public void setIndicePersistencia(float indicePersistencia) {
/* 196 */     this.indicePersistencia = indicePersistencia;
/*     */   }
/*     */   
/*     */   public float getIndicePersistencia() {
/* 200 */     return this.indicePersistencia;
/*     */   }
/*     */   
/*     */   public ControladorSonar getControladorSonar() {
/* 204 */     return this.controladorSonar;
/*     */   }
/*     */   
/*     */   public boolean ignorarPlotSinal(double x, double y) {
/* 208 */     if (this.arcoIgnorarSinalProa != null && this.arcoIgnorarSinalPopa != null) {
/* 209 */       return (this.arcoIgnorarSinalProa.contains(x, y) || this.arcoIgnorarSinalPopa.contains(x, y));
/*     */     }
/* 211 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public double getRaio() {
/* 216 */     if (getControladorSonar().getPaneSonarGrafico().isVisible()) {
/* 217 */       return -getControladorSonar().getPaneSonarGraficoController().getRetangulo().getY();
/*     */     }
/* 219 */     return this.rosaDeManobra.getRadius();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isCamadaPrincipal() {
/* 226 */     return this.camadaPrincipal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCamadaPrincipal(boolean camadaPrincipal) {
/* 233 */     this.camadaPrincipal = camadaPrincipal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void desenhaManchas() {
/* 242 */     if (this.mancha == null) {
/* 243 */       this.mancha = getImagem();
/* 244 */       popularManchas(this.CICLO_DESENHO);
/*     */     } else {
/* 246 */       atualizaManchas();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void atualizaManchas() {
/* 252 */     for (Map<Integer, RuidoSonar> map : this.MAPA_RUIDOS.values()) {
/* 253 */       for (RuidoSonar ruido : map.values()) {
/*     */         double distanciaRaster;
/*     */         
/* 256 */         if (getControladorSonar().getPaneSonarGrafico().isVisible()) {
/* 257 */           distanciaRaster = this.camadaSonarAtivo.getAltura() - converterDistanciaEmPosY(ruido.getDistancia());
/*     */         } else {
/* 259 */           double dx = Math.pow((this.rasterCentroTela.getX() - ruido.getX()), 2.0D);
/* 260 */           double dy = Math.pow((this.rasterCentroTela.getY() - ruido.getY()), 2.0D);
/* 261 */           distanciaRaster = Math.sqrt(dx + dy);
/*     */         } 
/*     */         
/* 264 */         double dif = Math.abs(getRaio() - distanciaRaster);
/* 265 */         float opacidade = 0.0F;
/* 266 */         double fatorOpacidade = dif / distanciaRaster;
/*     */         
/* 268 */         if (distanciaRaster < getRaio()) {
/* 269 */           opacidade = (float)((1.0D - fatorOpacidade) * getIndicePersistencia());
/* 270 */           opacidade += 0.1F;
/* 271 */           opacidade = (opacidade > 1.0F) ? 1.0F : ((opacidade < 0.0F) ? 0.0F : opacidade);
/* 272 */           if (opacidade >= 0.1F && 
/* 273 */             ruido.getOpacidade() >= 0.1D) {
/* 274 */             ruido.setOpacidade(opacidade);
/* 275 */             ruido.setDesenhando(true);
/*     */           } 
/*     */         } else {
/*     */           
/* 279 */           opacidade = (float)(fatorOpacidade * getIndicePersistencia());
/* 280 */           opacidade += 0.1F;
/* 281 */           opacidade = (opacidade > 1.0F) ? 1.0F : ((opacidade < 0.0F) ? 0.0F : opacidade);
/* 282 */           ruido.setOpacidade(opacidade * ruido.getOpacidade());
/*     */         } 
/*     */         
/* 285 */         if (ruido.isDesenhando() && 
/* 286 */           ruido.getOpacidade() > 0.1F) {
/* 287 */           Graphics2D g2d = this.mancha.createGraphics();
/* 288 */           g2d.setColor(ruido.getCor());
/* 289 */           Composite alpha = AlphaComposite.getInstance(3, ruido.getOpacidade() * this.indicePersistencia);
/* 290 */           g2d.setComposite(alpha);
/* 291 */           g2d.fillOval(ruido.getX(), ruido.getY(), 5, 2);
/* 292 */           g2d.dispose();
/* 293 */           desenharManchas(ruido);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 299 */     for (List<RuidoSonar> listaRuidos : this.MAPA_RUIDOS_PLOTS.values()) {
/* 300 */       Iterator<RuidoSonar> it = listaRuidos.iterator();
/* 301 */       while (it.hasNext()) {
/* 302 */         RuidoSonar ruido = it.next();
/*     */         
/* 304 */         double dx = Math.pow((this.rasterCentroTela.getX() - ruido.getX()), 2.0D);
/* 305 */         double dy = Math.pow((this.rasterCentroTela.getY() - ruido.getY()), 2.0D);
/* 306 */         double distanciaRaster = Math.sqrt(dx + dy);
/* 307 */         double diferenca = Math.abs(getRaio() - distanciaRaster);
/* 308 */         float opacidadeRuido = 0.0F;
/* 309 */         double fatorOpacidade = diferenca / distanciaRaster;
/* 310 */         if (distanciaRaster < getRaio()) {
/* 311 */           opacidadeRuido = (float)((1.0D - fatorOpacidade) * getIndicePersistencia());
/* 312 */           opacidadeRuido += 0.1F;
/* 313 */           opacidadeRuido = (opacidadeRuido > 1.0F) ? 1.0F : ((opacidadeRuido < 0.0F) ? 0.0F : opacidadeRuido);
/* 314 */           if (opacidadeRuido >= 0.1F) {
/* 315 */             ruido.setOpacidade(opacidadeRuido);
/* 316 */             ruido.setDesenhando(true);
/*     */           } 
/*     */         } else {
/* 319 */           opacidadeRuido = (float)(fatorOpacidade * getIndicePersistencia());
/* 320 */           opacidadeRuido += 0.1F;
/* 321 */           opacidadeRuido = (opacidadeRuido > 1.0F) ? 1.0F : ((opacidadeRuido < 0.0F) ? 0.0F : opacidadeRuido);
/* 322 */           ruido.setOpacidade(opacidadeRuido * ruido.getOpacidade());
/*     */         } 
/* 324 */         if (ruido.getOpacidade() > 0.1F) {
/* 325 */           if (ruido.isDesenhando()) {
/* 326 */             Graphics2D g2d = this.mancha.createGraphics();
/* 327 */             g2d.setColor(ruido.getCor());
/* 328 */             Composite alpha = AlphaComposite.getInstance(3, ruido.getOpacidade() * getIndicePersistencia());
/* 329 */             g2d.setComposite(alpha);
/* 330 */             g2d.fillOval(ruido.getX(), ruido.getY(), 4, 3);
/* 331 */             g2d.dispose();
/*     */           }  continue;
/*     */         } 
/* 334 */         it.remove();
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 339 */     for (List<RuidoSonar> listaRuidos : this.MAPA_RUIDOS_PLOT_GRAFICO.values()) {
/* 340 */       Iterator<RuidoSonar> it = listaRuidos.iterator();
/* 341 */       while (it.hasNext()) {
/* 342 */         RuidoSonar ruido = it.next();
/*     */         
/* 344 */         double dx = Math.pow((this.rasterCentroTela.getX() - ruido.getX()), 2.0D);
/* 345 */         double dy = Math.pow((this.rasterCentroTela.getY() - ruido.getY()), 2.0D);
/* 346 */         double distanciaRaster = Math.sqrt(dx + dy);
/* 347 */         double diferenca = Math.abs(getRaio() - distanciaRaster);
/* 348 */         float opacidadeRuido = 0.0F;
/* 349 */         double fatorOpacidade = diferenca / distanciaRaster;
/* 350 */         if (distanciaRaster < getRaio()) {
/* 351 */           opacidadeRuido = (float)((1.0D - fatorOpacidade) * getIndicePersistencia());
/* 352 */           opacidadeRuido += 0.3F;
/* 353 */           opacidadeRuido = (opacidadeRuido > 1.0F) ? 1.0F : ((opacidadeRuido < 0.0F) ? 0.0F : opacidadeRuido);
/* 354 */           if (opacidadeRuido >= 0.1F) {
/* 355 */             ruido.setOpacidade(opacidadeRuido);
/* 356 */             ruido.setDesenhando(true);
/*     */           } 
/*     */         } else {
/* 359 */           opacidadeRuido = (float)(fatorOpacidade * getIndicePersistencia());
/* 360 */           opacidadeRuido += 0.1F;
/* 361 */           opacidadeRuido = (opacidadeRuido > 1.0F) ? 1.0F : ((opacidadeRuido < 0.0F) ? 0.0F : opacidadeRuido);
/* 362 */           ruido.setOpacidade(opacidadeRuido * ruido.getOpacidade());
/*     */         } 
/* 364 */         if (ruido.getOpacidade() > 0.1F) {
/* 365 */           if (ruido.isDesenhando())
/* 366 */             desenharPlots(ruido); 
/*     */           continue;
/*     */         } 
/* 369 */         it.remove();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void limparManchas() {
/* 376 */     if (this.mancha != null) {
/* 377 */       Graphics2D g2d = this.mancha.createGraphics();
/* 378 */       g2d.setComposite(AlphaComposite.Src);
/* 379 */       g2d.setColor(new Color(0, 0, 0, 0));
/* 380 */       g2d.fillRect(0, 0, getLargura(), getAltura());
/* 381 */       g2d.setComposite(AlphaComposite.SrcOver);
/* 382 */       g2d.dispose();
/* 383 */       this.MAPA_RUIDOS.clear();
/* 384 */       this.MAPA_RUIDOS_PLOTS.clear();
/* 385 */       this.MAPA_RUIDOS_PLOT_GRAFICO.clear();
/* 386 */       this.CICLO_DESENHO = 0;
/* 387 */       incrementaCicloDesenho();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void popularManchas(int cicloDesenho) {
/* 392 */     this.rumoAnteriorVeiculo = Veiculo.getVeiculoReferencial().getCinematicaSimulada().getRumoSuperficie();
/* 393 */     this.rasterCentroTela = converterCoordenadaCartesianaParaRaster(Veiculo.getVeiculoReferencial().getCinematicaSimulada().getPosicao().getCoordenadaCartesiana());
/*     */     
/* 395 */     int distancia = (int)getEscalaMN();
/* 396 */     int marcacao = 359;
/* 397 */     CoordenadaPolar cp = null;
/* 398 */     CoordenadaRaster cR = null;
/* 399 */     Map<Integer, RuidoSonar> mapa = this.MAPA_RUIDOS.get(Integer.valueOf(cicloDesenho));
/*     */     
/* 401 */     if (mapa == null) {
/* 402 */       mapa = new ConcurrentHashMap<>();
/* 403 */       for (int i = 0; i <= 600; i++) {
/*     */         do {
/* 405 */           cp = new CoordenadaPolar(this.random.nextInt(marcacao), ConversorUnidades.jardasParaMilhasNauticas(this.random.nextInt(distancia)));
/* 406 */         } while (this.controladorSonar.getSensorSonar().isArcoCego(cp.getMarcacao()));
/*     */ 
/*     */ 
/*     */         
/* 410 */         cR = converterCoordenadaCartesianaParaRaster(CoordenadaCartesiana.converterCoordenadaPolar(
/* 411 */               Veiculo.getVeiculoReferencial().getCinematicaSimulada().getPosicao().getCoordenadaCartesiana(), cp));
/* 412 */         mapa.put(Integer.valueOf(i), new RuidoSonar(cR.getX(), cR.getY(), cp.getMarcacao(), cp.getDistancia(), 
/* 413 */               Veiculo.getVeiculoReferencial().getCinematicaSimulada().getRumoSuperficie(), 
/* 414 */               (this.random.nextBoolean() == true) ? COR_RUIDO_VERDE : COR_RUIDO_VERMELHO, 2));
/*     */       } 
/* 416 */       this.MAPA_RUIDOS.put(Integer.valueOf(cicloDesenho), mapa);
/*     */     } else {
/* 418 */       for (int i = 0; i <= 600; i++) {
/* 419 */         double marcacaoRuido = 0.0D;
/* 420 */         double distanciaRuido = ConversorUnidades.jardasParaMilhasNauticas(this.random.nextInt(distancia));
/*     */         do {
/* 422 */           marcacaoRuido = this.random.nextInt(marcacao);
/* 423 */         } while (this.controladorSonar.getSensorSonar().isArcoCego(marcacaoRuido));
/*     */ 
/*     */ 
/*     */         
/* 427 */         alteraPosicaoRuido(mapa.get(Integer.valueOf(i)), marcacaoRuido, distanciaRuido);
/*     */       } 
/*     */     } 
/*     */     
/* 431 */     this.CICLO_DESENHO++;
/*     */   }
/*     */ 
/*     */   
/*     */   public void alteraPosicaoRuido(RuidoSonar ruido, double marcacaoRuido, double distanciaRuido) {
/* 436 */     CoordenadaRaster cR = converterCoordenadaCartesianaParaRaster(CoordenadaCartesiana.converterCoordenadaPolar(
/* 437 */           Veiculo.getVeiculoReferencial().getCinematicaSimulada().getPosicao().getCoordenadaCartesiana(), new CoordenadaPolar(marcacaoRuido, distanciaRuido)));
/*     */     
/* 439 */     ruido.setX(cR.getX());
/* 440 */     ruido.setY(cR.getY());
/* 441 */     ruido.setMarcacao(marcacaoRuido);
/* 442 */     ruido.setDistancia(distanciaRuido);
/* 443 */     ruido.setRumoVeiculo(Veiculo.getVeiculoReferencial().getCinematicaSimulada().getRumoSuperficie());
/* 444 */     ruido.setCor((this.random.nextBoolean() == true) ? COR_RUIDO_VERDE : COR_RUIDO_VERMELHO);
/* 445 */     ruido.setDesenhando(false);
/*     */   }
/*     */   
/*     */   public void incrementaCicloDesenho() {
/* 449 */     if (this.CICLO_DESENHO > 2) {
/* 450 */       this.CICLO_DESENHO = 0;
/*     */     }
/* 452 */     for (Map<Integer, RuidoSonar> mapa : this.MAPA_RUIDOS.values()) {
/* 453 */       for (RuidoSonar ruido : mapa.values()) {
/* 454 */         ruido.decrementaContador();
/*     */       }
/*     */     } 
/*     */     
/* 458 */     this.MAPA_RUIDOS_PLOTS.values().forEach(lista -> lista.forEach(()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 464 */     this.MAPA_RUIDOS_PLOT_GRAFICO.values().forEach(lista -> lista.forEach(()));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 469 */     popularManchas(this.CICLO_DESENHO);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Integer, Map<Integer, RuidoSonar>> getMAPA_RUIDOS() {
/* 476 */     return this.MAPA_RUIDOS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<PlotSonar, List<RuidoSonar>> getMAPA_RUIDOS_PLOTS() {
/* 483 */     return this.MAPA_RUIDOS_PLOTS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<PlotSonar, List<RuidoSonar>> getMAPA_RUIDOS_PLOT_GRAFICO() {
/* 490 */     return this.MAPA_RUIDOS_PLOT_GRAFICO;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void desenhaCanvasGrafico(List<ObjetoTatico> listaObjetos) {
/* 496 */     List<Acompanhamento> listaAcompanhamentos = (List<Acompanhamento>)listaObjetos.stream().filter(obj -> obj instanceof Acompanhamento).map(Acompanhamento.class::cast).collect(Collectors.toList());
/*     */     
/* 498 */     List<Pointer> listaPointer = (List<Pointer>)listaObjetos.stream().filter(obj -> obj instanceof Pointer).map(Pointer.class::cast).collect(Collectors.toList());
/*     */     
/* 500 */     listaAcompanhamentos.forEach(acomp -> {
/*     */           if (acomp.getObjetosVisuais().get(ObjetoVisual.Tipo.GRAFICO) != null && ((ObjetoVisual)acomp.getObjetosVisuais().get(ObjetoVisual.Tipo.GRAFICO)).efetuarPoliticasDesenho((Filtro)this.controladorSonar.getCanvasSonar().getFiltroSonar())) {
/*     */             desenharAcompanhamento(acomp);
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 507 */     desenharPointer(listaPointer);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void desenharManchas(RuidoSonar ruido) {
/*     */     double marcacao;
/* 514 */     if (isVisualizacaoProa()) {
/* 515 */       marcacao = calculaMarcacao(ruido.getMarcacao() - ruido.getRumoVeiculo());
/*     */     } else {
/* 517 */       marcacao = calculaMarcacao(ruido.getMarcacao());
/*     */     } 
/*     */     
/* 520 */     Graphics2D g2D = this.camadaSonarAtivo.getImagem().createGraphics();
/* 521 */     g2D.setColor(ruido.getCor());
/* 522 */     double distancia = converterDistanciaEmPosY(ruido.getDistancia());
/* 523 */     Composite alpha = AlphaComposite.getInstance(3, ruido.getOpacidade() * getIndicePersistencia());
/* 524 */     g2D.setComposite(alpha);
/* 525 */     g2D.fillOval((int)(marcacao * 3.38D), (int)distancia, 5, 2);
/* 526 */     g2D.dispose();
/*     */   }
/*     */   
/*     */   public void desenharPlots(RuidoSonar ruido) {
/*     */     double marcacao;
/* 531 */     if (isVisualizacaoProa()) {
/* 532 */       marcacao = calculaMarcacao(ruido.getMarcacao() - ruido.getRumoVeiculo());
/*     */     } else {
/* 534 */       marcacao = calculaMarcacao(ruido.getMarcacao());
/*     */     } 
/* 536 */     Graphics2D g2D = this.camadaSonarAtivo.getImagem().createGraphics();
/* 537 */     g2D.setColor(ruido.getCor());
/* 538 */     double distancia = converterDistanciaEmPosY(ruido.getDistancia());
/* 539 */     Composite alpha = AlphaComposite.getInstance(3, ruido.getOpacidade() * getIndicePersistencia());
/* 540 */     g2D.setComposite(alpha);
/* 541 */     g2D.fillOval((int)(marcacao * 3.38D), (int)distancia, 4, 3);
/* 542 */     g2D.dispose();
/*     */   }
/*     */ 
/*     */   
/*     */   public void desenharAcompanhamento(Acompanhamento acomp) {
/* 547 */     double distancia = converterDistanciaEmPosY(acomp.getPosicao().getCoordenadaPolar().getDistancia());
/* 548 */     double marcacao = acomp.getPosicao().getCoordenadaPolar().getMarcacao();
/* 549 */     if (isVisualizacaoProa()) {
/* 550 */       if (acomp instanceof AcompanhamentoSonar) {
/* 551 */         AcompanhamentoSonar ac = (AcompanhamentoSonar)acomp;
/* 552 */         marcacao = calculaMarcacao(marcacao - ac.getRumoVeiculoReferencial());
/*     */       } else {
/* 554 */         marcacao = calculaMarcacao(marcacao - Veiculo.getVeiculoReferencial().getCinematicaSimulada().getRumoSuperficie());
/*     */       } 
/*     */     } else {
/* 557 */       marcacao = calculaMarcacao(marcacao);
/*     */     } 
/* 559 */     int largura = 32;
/* 560 */     int altura = 32;
/* 561 */     Color cor = acomp.isPerdido() ? ObjetoGraficoAcompanhamento.Cor.PERDIDO.getAwt() : (acomp.isLiberadoSistemaTatico() ? Color.YELLOW : Color.WHITE);
/*     */     
/* 563 */     int x0 = (int)(marcacao * 3.38D - (largura / 2));
/* 564 */     int y0 = (int)(distancia - (altura / 2));
/* 565 */     int ordemMarcacao = ordemMarcacao((ObjetoVisual)acomp.getObjetosVisuais().get(ObjetoVisual.Tipo.GRAFICO));
/* 566 */     Graphics2D g2D = this.camadaSonarAtivo.getImagem().createGraphics();
/* 567 */     g2D.setColor(cor);
/* 568 */     g2D.setStroke(new BasicStroke(1.0F));
/* 569 */     g2D.drawRect(x0, y0, largura, altura);
/* 570 */     g2D.setFont(FONTE);
/*     */     
/* 572 */     if (ordemMarcacao > 0) {
/* 573 */       g2D.setColor(COR_PREENCHIMENTO);
/* 574 */       g2D.setStroke(new BasicStroke(1.0F));
/* 575 */       g2D.fillRect((int)(marcacao * 3.38D - (largura / 2) - 2.0D), (int)(distancia - (altura / 2) - 2.0D), largura + 4, altura + 4);
/*     */       
/* 577 */       if (ordemMarcacao == 3) {
/* 578 */         g2D.setColor(Color.red);
/* 579 */         g2D.setStroke(new BasicStroke(2.0F));
/* 580 */         g2D.drawRect((int)(marcacao * 3.38D - (largura / 2) - 2.0D), (int)(distancia - (altura / 2) - 2.0D), largura + 4, altura + 4);
/*     */       } 
/*     */     } 
/*     */     
/* 584 */     String texto = acomp.isPerdido() ? "+".concat(acomp.getNa()) : acomp.getNa();
/*     */     
/* 586 */     if (texto == null) {
/*     */       return;
/*     */     }
/* 589 */     g2D.drawString(texto, x0, y0 - 4);
/*     */   }
/*     */   
/*     */   public void desenharPointer(List<Pointer> listaPointer) {
/* 593 */     Graphics2D g2D = this.camadaSonarAtivo.getImagem().createGraphics();
/* 594 */     for (Pointer pointer : listaPointer) {
/* 595 */       CoordenadaPolar cp = CoordenadaPolar.converterCoordenadaCartesiana(Veiculo.getVeiculoReferencial().getPosicao().getCoordenadaCartesiana(), pointer.getCoordenaCartesiana());
/* 596 */       double y = converterDistanciaEmPosY(cp.getDistancia());
/* 597 */       double x = cp.getMarcacao();
/*     */       
/* 599 */       if (isVisualizacaoProa()) {
/* 600 */         x = calculaMarcacao(x - pointer.getRumoVeiculoReferencial());
/*     */       } else {
/* 602 */         x = calculaMarcacao(x);
/*     */       } 
/*     */       
/* 605 */       x *= 3.38D;
/* 606 */       int largura = 16;
/* 607 */       int altura = 16;
/* 608 */       Color cor = Color.WHITE;
/* 609 */       int x0 = (int)(x - (largura / 2));
/* 610 */       int y0 = (int)(y - (altura / 2));
/*     */       
/* 612 */       g2D.setColor(cor);
/* 613 */       g2D.drawOval(x0, y0, altura, altura);
/*     */     } 
/* 615 */     g2D.dispose();
/*     */   }
/*     */   
/*     */   public double converterDistanciaEmPosY(double distancia) {
/* 619 */     double altura = this.camadaSonarAtivo.getAltura();
/* 620 */     double distanciaLimite = ConversorUnidades.jardasParaMilhasNauticas((this.controladorSonar.getSensorSonar().getEscala() * 1000));
/*     */     
/* 622 */     return altura - distancia * altura / distanciaLimite;
/*     */   }
/*     */   
/*     */   public double converterPosYEmDistancia(double posY) {
/* 626 */     double distancia = (this.camadaSonarAtivo.getAltura() - posY) / this.camadaSonarAtivo.getAltura() * this.controladorSonar.getSensorSonar().getEscala() * 1000.0D;
/* 627 */     return ConversorUnidades.jardasParaMilhasNauticas(distancia);
/*     */   }
/*     */   
/*     */   public double converterPosXEmMarcacao(double posicao) {
/* 631 */     double marcacao = posicao / 3.38D;
/* 632 */     marcacao += 180.0D;
/* 633 */     if (isVisualizacaoProa()) {
/* 634 */       marcacao += Veiculo.getVeiculoReferencial().getCinematicaSimulada().getRumoSuperficie();
/*     */     }
/*     */     
/* 637 */     if (marcacao > 360.0D) {
/* 638 */       marcacao -= 360.0D;
/*     */     }
/* 640 */     return marcacao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double calculaMarcacao(double marcacao) {
/* 651 */     marcacao += 180.0D;
/* 652 */     return (marcacao >= 360.0D) ? (marcacao - 360.0D) : ((marcacao < 0.0D) ? (marcacao + 360.0D) : marcacao);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVisualizacaoProa() {
/* 659 */     return this.visualizacaoProa;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVisualizacaoProa(boolean visualizacaoProa) {
/* 666 */     this.visualizacaoProa = visualizacaoProa;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CamadaBI getCamadaSonarAtivo() {
/* 673 */     return this.camadaSonarAtivo;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void geraImagemFX() {
/* 678 */     if (this.controladorSonar.getPaneSonarGrafico().isVisible()) {
/* 679 */       this.camadaSonarAtivo.geraImagemFX();
/*     */     } else {
/* 681 */       super.geraImagemFX();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void tornaTransparente() {
/* 687 */     if (this.controladorSonar.getPaneSonarGrafico().isVisible()) {
/* 688 */       this.camadaSonarAtivo.tornaTransparente();
/*     */     } else {
/* 690 */       super.tornaTransparente();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getRumoAnteriorVeiculo() {
/* 698 */     return this.rumoAnteriorVeiculo;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/camadas/CamadaSonar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */