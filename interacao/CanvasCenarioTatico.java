/*      */ package ipqm.gsd.hidra.ihm.interacao;
/*      */ import ipqm.gsd.componentes.dominio.ObjetoTatico;
/*      */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*      */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*      */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*      */ import ipqm.gsd.componentes.dominio.cinematica.Posicao;
/*      */ import ipqm.gsd.componentes.dominio.constante_sstt.TipoPontoReferencia;
/*      */ import ipqm.gsd.componentes.dominio.contexto.ContextoTatico;
/*      */ import ipqm.gsd.componentes.dominio.entidades.Veiculo;
/*      */ import ipqm.gsd.componentes.dominio.navegacao.Janela;
/*      */ import ipqm.gsd.componentes.dominio.navegacao.JanelaGeografica;
/*      */ import ipqm.gsd.componentes.dominio.sensores.RadarBusca;
/*      */ import ipqm.gsd.componentes.dominio.util.UTM;
/*      */ import ipqm.gsd.componentes.nucleo.Ambiente;
/*      */ import ipqm.gsd.componentes.nucleo.Mediador;
/*      */ import ipqm.gsd.componentes.nucleo.ThreadPool;
/*      */ import ipqm.gsd.componentes.nucleo.VariaveisEstado;
/*      */ import ipqm.gsd.componentes.nucleo.configuracao.Diretorios;
/*      */ import ipqm.gsd.componentes.nucleo.driver.Sensor;
/*      */ import ipqm.gsd.componentes.nucleo.gestao.GestorCartasGenerico;
/*      */ import ipqm.gsd.componentes.nucleo.log.Log;
/*      */ import ipqm.gsd.componentes.nucleo.logon.perfis.PerfilUsuario;
/*      */ import ipqm.gsd.componentes.nucleo.objeto_visual.ObjetoVisual;
/*      */ import ipqm.gsd.hidra.ihm.camadas.CamadaCarta;
/*      */ import ipqm.gsd.hidra.ihm.camadas.CamadaInfo;
/*      */ import ipqm.gsd.hidra.ihm.camadas.CamadaInfoEstatica;
/*      */ import ipqm.gsd.hidra.ihm.camadas.CamadaMapaOnline;
/*      */ import ipqm.gsd.hidra.ihm.camadas.CamadaRadar;
/*      */ import ipqm.gsd.hidra.ihm.camadas.CamadaTatica;
/*      */ import ipqm.gsd.hidra.ihm.camadas.filtros.FiltroCartaNautica;
/*      */ import ipqm.gsd.hidra.ihm.camadas.filtros.FiltroInfoEstatica;
/*      */ import ipqm.gsd.hidra.ihm.camadas.filtros.FiltroMapaOnline;
/*      */ import ipqm.gsd.hidra.ihm.camadas.filtros.FiltroObjetoTatico;
/*      */ import ipqm.gsd.hidra.ihm.camadas.filtros.FiltroRadar;
/*      */ import ipqm.gsd.hidra.ihm.camadas.filtros.FiltroVideoRadar;
/*      */ import ipqm.gsd.hidra.ihm.gestao.GestorSimulacaoFX;
/*      */ import ipqm.gsd.hidra.ihm.interacao.tarefa.TarefaCompasso;
/*      */ import ipqm.gsd.hidra.ihm.interacao.tarefa.TarefaCriaAreaCircular;
/*      */ import ipqm.gsd.hidra.ihm.interacao.tarefa.TarefaCriaAreaCircularCM;
/*      */ import ipqm.gsd.hidra.ihm.interacao.tarefa.TarefaCriaElementoNavegacao;
/*      */ import ipqm.gsd.hidra.ihm.interacao.tarefa.TarefaCriaLinhaMarcacao;
/*      */ import ipqm.gsd.hidra.ihm.interacao.tarefa.TarefaCriaLinhaPoligonal;
/*      */ import ipqm.gsd.hidra.ihm.interacao.tarefa.TarefaCriaObjetos;
/*      */ import ipqm.gsd.hidra.ihm.interacao.tarefa.TarefaCriaPontoReferencia;
/*      */ import ipqm.gsd.hidra.ihm.interacao.tarefa.TarefaCriaPontos;
/*      */ import ipqm.gsd.hidra.ihm.interacao.tarefa.TarefaCriaRotas;
/*      */ import ipqm.gsd.hidra.ihm.interacao.tarefa.TarefaCriaSymbols;
/*      */ import ipqm.gsd.hidra.ihm.interacao.tarefa.TarefaCriaVeiculo;
/*      */ import ipqm.gsd.hidra.ihm.interacao.tarefa.TarefaDesenho;
/*      */ import ipqm.gsd.hidra.ihm.interacao.tarefa.TarefaGrade;
/*      */ import ipqm.gsd.hidra.ihm.interacao.tarefa.TarefaMovimentacao3D;
/*      */ import ipqm.gsd.hidra.ihm.interacao.tarefa.TarefaPan;
/*      */ import ipqm.gsd.hidra.ihm.interacao.tarefa.TarefaSelecao;
/*      */ import ipqm.gsd.hidra.ihm.interacao.tarefa.TarefaZoom;
/*      */ import ipqm.gsd.hidra.ihm.projetos.geral.cenas.controladores.ControladorCenarioTatico;
/*      */ import ipqm.gsd.hidra.ihm.simuladores.SimuladorVideoBrutoFX;
/*      */ import ipqm.gsd.hidra.ihm.util.UtilDesempenho;
/*      */ import ipqm.gsd.hidra.ihm.util.desenho.UtilJava2D;
/*      */ import ipqm.gsd.radar.model.Range;
/*      */ import ipqm.gsd.radar.view.RadarImage;
/*      */ import ipqm.gsd.smaps.model.coord.BBox;
/*      */ import ipqm.gsd.smaps.model.dtm.DTMFile;
/*      */ import ipqm.gsd.smaps.model.dtm.Model3D;
/*      */ import ipqm.gsd.smaps.util.MapTiles;
/*      */ import ipqm.gsd.smaps.view.raster.OnlineTileMaps;
/*      */ import ipqm.gsd.smaps.view.vector.ENCParameters;
/*      */ import java.awt.Color;
/*      */ import java.awt.Graphics2D;
/*      */ import java.io.File;
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import java.util.concurrent.locks.Condition;
/*      */ import java.util.concurrent.locks.Lock;
/*      */ import java.util.concurrent.locks.ReentrantLock;
/*      */ import javafx.application.Platform;
/*      */ import javafx.scene.Camera;
/*      */ import javafx.scene.Node;
/*      */ import javafx.scene.Parent;
/*      */ import javafx.scene.SceneAntialiasing;
/*      */ import javafx.scene.SubScene;
/*      */ import javafx.scene.canvas.Canvas;
/*      */ import javafx.scene.image.Image;
/*      */ import javafx.scene.image.WritableImage;
/*      */ import javafx.scene.input.KeyCode;
/*      */ import javafx.scene.input.KeyEvent;
/*      */ import javafx.scene.input.MouseButton;
/*      */ import javafx.scene.input.MouseEvent;
/*      */ import javafx.scene.input.ScrollEvent;
/*      */ import javafx.scene.input.TouchEvent;
/*      */ import javafx.scene.paint.Material;
/*      */ import javafx.scene.paint.PhongMaterial;
/*      */ import javafx.scene.shape.DrawMode;
/*      */ import javafx.scene.shape.MeshView;
/*      */ import javafx.scene.shape.TriangleMesh;
/*      */ 
/*      */ public abstract class CanvasCenarioTatico<E extends ControladorCenarioTatico> extends CanvasEspacial<E> implements Serializable {
/*      */   public static final int MIN_LEVEL_OF_DETAIL = 4;
/*      */   public static final int MAX_LEVEL_OF_DETAIL = 20;
/*      */   public static final int LARGURA_TELA = 1920;
/*      */   public static final int ALTURA_TELA = 1080;
/*      */   public static final int LARGURA_CANVAS = 1040;
/*      */   public static final int ALTURA_CANVAS = 1025;
/*      */   public static final int ALTURA_RADAR = 926;
/*      */   public static final int LARGURA_RADAR = 926;
/*      */   public static final int LARGURA_CANVAS_EXTENDIDO = 1480;
/*      */   public static final int LARGURA_CANVAS_INSTRUTOR = 1425;
/*      */   public static final int NUM_PIXELS_RECUO_VIEW = 130;
/*      */   
/*      */   public enum ModoNavegacao {
/*  111 */     LIVRE,
/*  112 */     CENTRADO,
/*  113 */     LOOK_AHEAD;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  135 */   protected CanvasEspacial.Tarefas idTarefaAnterior = CanvasEspacial.Tarefas.NENHUMA;
/*      */   
/*      */   protected boolean arrastandoBotao1;
/*      */   
/*      */   protected boolean arrastandoBotao2;
/*      */   protected boolean arrastandoBotao3;
/*      */   protected boolean exibirMenuContexto;
/*      */   protected Canvas mapaCanvas;
/*      */   private double layoutXCanvas;
/*      */   private double layoutYCanvas;
/*      */   protected SubScene cenaCanvas3D;
/*      */   private GrupoCamera grupoCanvas;
/*      */   private Camera camera;
/*      */   protected FiltroObjetoTatico filtroObjetoTatico;
/*      */   protected FiltroCartaNautica filtroCartaNautica;
/*      */   protected FiltroMapaOnline filtroSatelite;
/*      */   protected FiltroMapaOnline filtroMapaAlternativo;
/*      */   protected FiltroVideoRadar filtroVideoRadar;
/*      */   protected FiltroInfoEstatica filtroInfoEstatica;
/*      */   protected CamadaMapaOnline camadaMapaAlternativo;
/*      */   protected CamadaMapaOnline camadaSatelite;
/*      */   protected CamadaCarta camadaCarta;
/*      */   protected CamadaCarta camadaCartaTopo;
/*      */   protected CamadaCarta camadaCartaBase;
/*      */   protected CamadaTatica<FiltroObjetoTatico> camadaTatica;
/*      */   protected CamadaInfo camadaInfo;
/*      */   protected CamadaInfoEstatica camadaInfoEstatica;
/*      */   protected RadarImage camadaRadarImage;
/*      */   private boolean redesenhoAutomatico;
/*      */   private ModoNavegacao modoNavegacao;
/*      */   protected CoordenadaCartesiana posicaoMouse;
/*      */   protected CoordenadaRaster posReferencialRaster;
/*      */   protected int larguraOriginal;
/*      */   protected int alturaOriginal;
/*  169 */   private final Lock lock = new ReentrantLock();
/*  170 */   private final Condition atualizar = this.lock.newCondition();
/*      */   
/*      */   private boolean sinalAtualizarCamadas;
/*      */   
/*      */   private boolean visuDisponivel;
/*      */   private boolean simulacaoRadarIniciada;
/*      */   protected CamadaRadar camadaRadar;
/*      */   protected final FiltroRadar filtroRadar;
/*      */   private boolean enquadrado = true;
/*  179 */   private List<DTMFile> dtmFiles = null;
/*  180 */   private MeshView terrainMesh = null;
/*      */   
/*      */   protected long ultimaAtualizacaoCamadas;
/*      */   
/*      */   protected long ultimoDesenho;
/*      */   
/*      */   private int idRadarSelecionado;
/*      */   private int displayScale;
/*      */   
/*      */   public CanvasCenarioTatico() {
/*  190 */     this.ultimaAtualizacaoCamadas = this.ultimoDesenho = System.currentTimeMillis();
/*      */ 
/*      */     
/*  193 */     this.filtroObjetoTatico = new FiltroObjetoTatico();
/*  194 */     this.filtroCartaNautica = new FiltroCartaNautica();
/*  195 */     this.filtroSatelite = new FiltroMapaOnline();
/*  196 */     this.filtroVideoRadar = new FiltroVideoRadar();
/*  197 */     this.filtroMapaAlternativo = new FiltroMapaOnline();
/*  198 */     this.filtroInfoEstatica = new FiltroInfoEstatica();
/*      */     
/*  200 */     this.redesenhoAutomatico = true;
/*  201 */     this.mapaTarefas.put(CanvasEspacial.Tarefas.MOVIMENTACAO_3D, new TarefaMovimentacao3D(this));
/*  202 */     this.mapaTarefas.put(CanvasEspacial.Tarefas.PAN, new TarefaPan(this));
/*  203 */     this.mapaTarefas.put(CanvasEspacial.Tarefas.DESENHO, new TarefaDesenho(this));
/*  204 */     this.mapaTarefas.put(CanvasEspacial.Tarefas.CRIA_OBJETOS, new TarefaCriaObjetos(this));
/*  205 */     this.mapaTarefas.put(CanvasEspacial.Tarefas.CRIA_VEICULO, new TarefaCriaVeiculo(this));
/*  206 */     this.mapaTarefas.put(CanvasEspacial.Tarefas.GRADE, new TarefaGrade(this));
/*  207 */     this.mapaTarefas.put(CanvasEspacial.Tarefas.COMPASSO, new TarefaCompasso(this));
/*  208 */     this.mapaTarefas.put(CanvasEspacial.Tarefas.LINHA_MARCACAO, new TarefaCriaLinhaMarcacao(this));
/*  209 */     this.mapaTarefas.put(CanvasEspacial.Tarefas.ZOOM, new TarefaZoom(this));
/*  210 */     this.mapaTarefas.put(CanvasEspacial.Tarefas.CRIA_PONTOS, new TarefaCriaPontos(this));
/*  211 */     this.mapaTarefas.put(CanvasEspacial.Tarefas.CRIA_PONTOSMANUAL, new TarefaCriaPontoReferencia(this, TipoPontoReferencia.MANUAL));
/*  212 */     this.mapaTarefas.put(CanvasEspacial.Tarefas.CRIA_PONTOSMAGE, new TarefaCriaPontoReferencia(this, TipoPontoReferencia.MAGE));
/*  213 */     this.mapaTarefas.put(CanvasEspacial.Tarefas.CRIA_PONTOSPLASH, new TarefaCriaPontoReferencia(this, TipoPontoReferencia.SPLASH));
/*  214 */     this.mapaTarefas.put(CanvasEspacial.Tarefas.CRIA_PONTOSDATUM, new TarefaCriaPontoReferencia(this, TipoPontoReferencia.DATUM));
/*  215 */     this.mapaTarefas.put(CanvasEspacial.Tarefas.CRIA_ELEMENTO_NAVEGACAO, new TarefaCriaElementoNavegacao(this));
/*  216 */     this.mapaTarefas.put(CanvasEspacial.Tarefas.CRIA_FIGURA_GEOMETRICA, new TarefaCriaLinhaPoligonal(this));
/*  217 */     this.mapaTarefas.put(CanvasEspacial.Tarefas.CRIA_AREA_CIRCULAR, new TarefaCriaAreaCircular(this));
/*  218 */     this.mapaTarefas.put(CanvasEspacial.Tarefas.ALOCAR_VEICULO, new TarefaAlocarVeiculoEstrategia(this));
/*  219 */     this.mapaTarefas.put(CanvasEspacial.Tarefas.LINHA_FORMATURA, new TarefaLinhaFormatura(this));
/*  220 */     this.mapaTarefas.put(CanvasEspacial.Tarefas.CRIA_ROTAS, new TarefaCriaRotas(this));
/*  221 */     this.mapaTarefas.put(CanvasEspacial.Tarefas.CRIA_SIMBOLOS, new TarefaCriaSymbols(this));
/*  222 */     this.mapaTarefas.put(CanvasEspacial.Tarefas.CRIA_TEXTOS, new TarefaCriaTexts(this));
/*  223 */     this.mapaTarefas.put(CanvasEspacial.Tarefas.CRIA_PROFUNDIDADES, new TarefaCriaProfundidades(this));
/*  224 */     this.mapaTarefas.put(CanvasEspacial.Tarefas.CRIA_AREA_CIRCULAR_CM, new TarefaCriaAreaCircularCM(this));
/*  225 */     this.mapaTarefas.put(CanvasEspacial.Tarefas.CRIA_AREA_POLIGONAL_CM, new TarefaCriaAreaPoligonalCM(this));
/*      */     
/*  227 */     this.modoNavegacao = ModoNavegacao.CENTRADO;
/*  228 */     this.filtroRadar = new FiltroRadar();
/*      */   }
/*      */ 
/*      */   
/*      */   public void configuraCanvas(double largura, double altura, double layoutX, double layoutY) {
/*  233 */     this.layoutXCanvas = layoutX;
/*  234 */     this.layoutYCanvas = layoutY;
/*  235 */     this.mapaCanvas = new Canvas(largura, altura);
/*  236 */     this.grupoCanvas = new GrupoCamera(largura, altura);
/*  237 */     this.grupoCanvas.getChildren().addAll((Object[])new Node[] { (Node)this.mapaCanvas });
/*  238 */     this.cenaCanvas3D = new SubScene((Parent)this.grupoCanvas, 1920.0D, 1080.0D, true, SceneAntialiasing.BALANCED);
/*  239 */     adicionarEventos(new Node[] { (Node)this.cenaCanvas3D });
/*  240 */     this.grupoCanvas.setLayoutX(layoutX);
/*  241 */     this.grupoCanvas.setLayoutY(layoutY);
/*  242 */     this.camera = (Camera)new PerspectiveCamera();
/*  243 */     this.camera.setNearClip(0.1D);
/*  244 */     this.camera.setFarClip(10000.0D);
/*  245 */     this.cenaCanvas3D.setCamera(this.camera);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void alterarTamanhoCanvas(double largura, double altura, double layoutX, double layoutY) {
/*  258 */     this.layoutXCanvas = layoutX;
/*  259 */     this.layoutYCanvas = layoutY;
/*      */     
/*  261 */     this.mapaCanvas.setWidth(largura);
/*  262 */     this.mapaCanvas.setHeight(altura);
/*      */     
/*  264 */     this.grupoCanvas.setLayoutX(layoutX);
/*  265 */     this.grupoCanvas.setLayoutY(layoutY);
/*      */     
/*  267 */     alterarTamanhoCamadas((int)largura, (int)altura);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void exibirStatus(String mensagem) {
/*  277 */     Graphics2D g2d = getCamadaInfo().getImagem().createGraphics();
/*  278 */     exibirStatus(g2d, mensagem);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void exibirStatus(Graphics2D g2d, String mensagem) {
/*  289 */     UtilJava2D.escreveLegendasEsquerda(null, mensagem, null, getCamadaInfo().getLargura(), getCamadaInfo().getAltura() - 7, Color.white, Color.black, g2d);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSimulacaoRadarIniciada() {
/*  297 */     return this.simulacaoRadarIniciada;
/*      */   }
/*      */   
/*      */   public void setSimulacaoRadarIniciada(boolean simulacaoRadarIniciada) {
/*  301 */     this.simulacaoRadarIniciada = simulacaoRadarIniciada;
/*      */   }
/*      */   
/*      */   public void setRedesenhoAutomatico(boolean redesenhoAutomatico) {
/*  305 */     this.redesenhoAutomatico = redesenhoAutomatico;
/*      */   }
/*      */   
/*      */   public boolean isRedesenhoAutomatico() {
/*  309 */     return this.redesenhoAutomatico;
/*      */   }
/*      */   
/*      */   public CamadaCarta getCamadaCarta() {
/*  313 */     return this.camadaCarta;
/*      */   }
/*      */   
/*      */   public CamadaCarta getCamadaCartaTopo() {
/*  317 */     return this.camadaCartaTopo;
/*      */   }
/*      */   
/*      */   public CamadaCarta getCamadaCartaBase() {
/*  321 */     return this.camadaCartaBase;
/*      */   }
/*      */   
/*      */   public CamadaTatica<FiltroObjetoTatico> getCamadaTatica() {
/*  325 */     return this.camadaTatica;
/*      */   }
/*      */ 
/*      */   
/*      */   public CamadaInfo getCamadaInfo() {
/*  330 */     return this.camadaInfo;
/*      */   }
/*      */   
/*      */   public CamadaInfoEstatica getCamadaInfoEstatica() {
/*  334 */     return this.camadaInfoEstatica;
/*      */   }
/*      */   
/*      */   public RadarImage getCamadaRadarImage() {
/*  338 */     return this.camadaRadarImage;
/*      */   }
/*      */   
/*      */   public CamadaMapaOnline getCamadaMapaAlternativo() {
/*  342 */     return this.camadaMapaAlternativo;
/*      */   }
/*      */   
/*      */   public CamadaMapaOnline getCamadaSatelite() {
/*  346 */     return this.camadaSatelite;
/*      */   }
/*      */   
/*      */   public CamadaRadar getCamadaRadar() {
/*  350 */     return this.camadaRadar;
/*      */   }
/*      */   
/*      */   public void setCamadaRadar(CamadaRadar camadaRadar) {
/*  354 */     this.camadaRadar = camadaRadar;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void atualizarCamadas() {
/*  364 */     if (System.currentTimeMillis() - this.ultimaAtualizacaoCamadas >= UtilDesempenho.getFrequenciaAtualizacaoCenarioTatico().getTempoMinimoAtualizacaoCamadas()) {
/*  365 */       this.ultimaAtualizacaoCamadas = System.currentTimeMillis();
/*  366 */       if (this.camadaTatica != null) {
/*  367 */         this.camadaTatica.setEscalaMN(getEscalaMN());
/*      */       }
/*      */       
/*  370 */       this.displayScale = (int)(this.janelaGeo.getAltura() * 1852.0D / 0.25625D + 0.5D);
/*      */       
/*      */       try {
/*  373 */         this.lock.lock();
/*  374 */         this.sinalAtualizarCamadas = true;
/*  375 */         this.atualizar.signal();
/*      */       } finally {
/*  377 */         this.lock.unlock();
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public synchronized void atualizarCamadaTatica() {
/*  385 */     if (this.filtroObjetoTatico.isExibir()) {
/*  386 */       JanelaGeografica janelaGeografica = getJanelaGeografica();
/*  387 */       List<ObjetoTatico> listaObjetos = getObjetosTaticos();
/*  388 */       float opacidade = 1.0F;
/*  389 */       if (getTarefa(CanvasEspacial.Tarefas.DESENHO) != null) {
/*  390 */         listaObjetos.addAll(((TarefaDesenho)getTarefa(CanvasEspacial.Tarefas.DESENHO)).getListaDesenhos());
/*      */       }
/*      */       
/*  393 */       this.camadaTatica.desenha((Janela)janelaGeografica, listaObjetos, this.tarefaSelecaoObjetos, opacidade);
/*  394 */       this.camadaTatica.geraImagemFX();
/*  395 */       this.camadaTatica.setEscalaMN(getEscalaMN());
/*      */       
/*  397 */       ContextoTatico contexto = (ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto();
/*      */       
/*  399 */       centralizaVeiculo(contexto.getTeatroOperacao().getPosicaoReferencial());
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void alterarTamanhoCamadas(int largura, int altura) {
/*  404 */     this.larguraOriginal = largura;
/*  405 */     this.alturaOriginal = altura;
/*      */     
/*  407 */     if (this.camadaCarta != null) {
/*  408 */       synchronized (this.camadaCarta) {
/*  409 */         this.camadaCarta.redimensiona(largura, altura);
/*      */       } 
/*      */     }
/*  412 */     if (this.camadaCartaTopo != null) {
/*  413 */       synchronized (this.camadaCartaTopo) {
/*  414 */         this.camadaCartaTopo.redimensiona(largura, altura);
/*      */       } 
/*      */     }
/*  417 */     if (this.camadaCartaBase != null) {
/*  418 */       synchronized (this.camadaCartaBase) {
/*  419 */         this.camadaCartaBase.redimensiona(largura, altura);
/*      */       } 
/*      */     }
/*  422 */     if (this.camadaSatelite != null) {
/*  423 */       synchronized (this.camadaSatelite) {
/*  424 */         this.camadaSatelite.redimensiona(largura, altura);
/*      */       } 
/*      */     }
/*  427 */     if (this.camadaMapaAlternativo != null) {
/*  428 */       synchronized (this.camadaMapaAlternativo) {
/*  429 */         this.camadaMapaAlternativo.redimensiona(largura, altura);
/*      */       } 
/*      */     }
/*  432 */     if (this.camadaTatica != null) {
/*  433 */       synchronized (this.camadaTatica) {
/*  434 */         this.camadaTatica.redimensiona(largura, altura);
/*      */       } 
/*      */     }
/*  437 */     if (this.camadaInfo != null) {
/*  438 */       synchronized (this.camadaInfo) {
/*  439 */         this.camadaInfo.redimensiona(largura, altura);
/*      */       } 
/*      */     }
/*  442 */     if (this.camadaInfoEstatica != null) {
/*  443 */       synchronized (this.camadaInfoEstatica) {
/*  444 */         this.camadaInfoEstatica.redimensiona(largura, altura);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public void setEnquadramento(boolean enquadra) {
/*  450 */     this.enquadrado = enquadra;
/*      */   }
/*      */   
/*      */   public int getLevelOfDetail() {
/*  454 */     return getLevelOfDetail(getJanelaGeografica());
/*      */   }
/*      */   
/*      */   public int getLevelOfDetail(JanelaGeografica janela) {
/*  458 */     double larguraMN = janela.getLargura();
/*  459 */     double groundResolution = larguraMN * 1852.0D / getLargura();
/*  460 */     return MapTiles.findBestLevelOfDetail(groundResolution, janela.getCentro().getLatitude());
/*      */   }
/*      */   
/*      */   public int getLevelOfDetail(double alcance) {
/*  464 */     double larguraMN = 2.0D * alcance;
/*  465 */     double aspecto = getLargura() / getAltura();
/*      */     
/*  467 */     if (aspecto > 1.0D) {
/*  468 */       larguraMN *= aspecto;
/*      */     }
/*      */     
/*  471 */     double groundResolution = larguraMN * 1852.0D / getLargura();
/*  472 */     return MapTiles.findBestLevelOfDetail(groundResolution, getJanelaGeografica().getCentro().getLatitude());
/*      */   }
/*      */ 
/*      */   
/*      */   public void setModoNavegacao(ModoNavegacao modo) {
/*  477 */     this.modoNavegacao = modo;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public ModoNavegacao getModoNavegacao() {
/*  483 */     return this.modoNavegacao;
/*      */   }
/*      */ 
/*      */   
/*      */   public FiltroObjetoTatico getFiltroObjetoTatico() {
/*  488 */     return this.filtroObjetoTatico;
/*      */   }
/*      */   
/*      */   public FiltroInfoEstatica getFiltroInfoEstatica() {
/*  492 */     return this.filtroInfoEstatica;
/*      */   }
/*      */   
/*      */   public FiltroCartaNautica getFiltroCartaNautica() {
/*  496 */     return this.filtroCartaNautica;
/*      */   }
/*      */   
/*      */   public FiltroMapaOnline getFiltroSatelite() {
/*  500 */     return this.filtroSatelite;
/*      */   }
/*      */   
/*      */   public FiltroVideoRadar getFiltroVideoRadar() {
/*  504 */     return this.filtroVideoRadar;
/*      */   }
/*      */   
/*      */   public FiltroMapaOnline getFiltroMapaAlternativo() {
/*  508 */     return this.filtroMapaAlternativo;
/*      */   }
/*      */   
/*      */   public void inicializa(double larguraCanvas, double alturaCanvas, double layoutX, double layoutY) {
/*  512 */     this.larguraOriginal = (int)larguraCanvas;
/*  513 */     this.alturaOriginal = (int)alturaCanvas;
/*  514 */     configuraCanvas(larguraCanvas, alturaCanvas, layoutX, layoutY);
/*      */ 
/*      */     
/*      */     try {
/*  518 */       Posicao posRef = ((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getTeatroOperacao().getPosicaoReferencial();
/*      */       
/*  520 */       double latitudeInicial = posRef.getCoordenadaGeografica().getLatitude();
/*  521 */       double longitudeInicial = posRef.getCoordenadaGeografica().getLongitude();
/*      */       
/*  523 */       CoordenadaGeografica centro = new CoordenadaGeografica(latitudeInicial, longitudeInicial);
/*  524 */       setJanela(centro, 3.0D);
/*      */     }
/*  526 */     catch (NumberFormatException e) {
/*  527 */       Log.gravarLogExcecao("Erro ao inicializar o canvas cenário tático", this, e);
/*      */     } 
/*      */     
/*  530 */     setIdTarefa(CanvasEspacial.Tarefas.SELECAO_OBJETOS);
/*  531 */     this.posReferencialRaster = new CoordenadaRaster(this.larguraOriginal / 2, this.alturaOriginal / 2);
/*      */     
/*  533 */     GestorCartasGenerico.ServidorCartas servidor = PerfilUsuario.getPerfilUsuarioAtual().getServidorCartas();
/*      */     
/*  535 */     this.camadaCarta = new CamadaCarta(this.filtroCartaNautica, this.larguraOriginal, this.alturaOriginal, servidor);
/*  536 */     this.camadaCartaTopo = new CamadaCarta(this.filtroCartaNautica, this.larguraOriginal, this.alturaOriginal, servidor);
/*  537 */     this.camadaCartaBase = new CamadaCarta(this.filtroCartaNautica, this.larguraOriginal, this.alturaOriginal, servidor);
/*  538 */     this.camadaTatica = new CamadaTatica(this.filtroObjetoTatico, this.larguraOriginal, this.alturaOriginal);
/*  539 */     this.camadaTatica.setGrupoCamera(this.grupoCanvas);
/*  540 */     this.camadaInfo = new CamadaInfo(this.filtroObjetoTatico, this.larguraOriginal, this.alturaOriginal);
/*  541 */     this.camadaInfoEstatica = new CamadaInfoEstatica(this.filtroInfoEstatica, this.larguraOriginal, this.alturaOriginal);
/*  542 */     this.camadaSatelite = new CamadaMapaOnline(this.filtroSatelite, this.larguraOriginal, this.alturaOriginal, OnlineTileMaps.GOOGLE);
/*  543 */     this.camadaMapaAlternativo = new CamadaMapaOnline(this.filtroMapaAlternativo, this.larguraOriginal, this.alturaOriginal, OnlineTileMaps.OPEN_SEA_MAPS);
/*  544 */     this.camadaRadarImage = new RadarImage(926, 926, getEscalaMN(), 1.5D);
/*  545 */     this.camadaRadar = new CamadaRadar(this.filtroRadar, this.larguraOriginal, this.alturaOriginal, this);
/*  546 */     this.camadaRadar.setEscalaMN(getEscalaMN());
/*  547 */     iniciaAtualizadorCamadas();
/*      */     
/*  549 */     this.visuDisponivel = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setEscalaMN(double alcance) {
/*  554 */     if (alcance < 1536.0D) {
/*  555 */       super.setEscalaMN(alcance);
/*  556 */       if (getIdTarefa() == CanvasEspacial.Tarefas.MOVIMENTACAO_3D) {
/*  557 */         show3DCanvas();
/*      */       }
/*      */     } 
/*      */     
/*  561 */     if (alcance > 0.0234375D) {
/*  562 */       this.visuDisponivel = true;
/*      */     }
/*      */   }
/*      */   
/*      */   public synchronized void setEscalaRadarMN(double alcance) {
/*  567 */     this.camadaRadarImage.setRange(getEscalaMN());
/*      */     
/*  569 */     SimuladorVideoBrutoFX simulador = (SimuladorVideoBrutoFX)GestorSimulacaoFX.MAPA_SIMULADORES.get(Integer.valueOf(getIdRadarSelecionado()));
/*  570 */     if (simulador != null) {
/*  571 */       this.filtroVideoRadar.setIgnorarGiro(true);
/*  572 */       simulador.setDimensao(getAltura(), getLargura());
/*  573 */       simulador.setCanvasCenarioTatico(this);
/*  574 */       corrigirRangeSimuladorRadar(alcance);
/*  575 */       simulador.getSimuladorRadar().setRange(getFiltroVideoRadar().getRadarImageParameters().getRange());
/*      */     } 
/*      */     
/*  578 */     atualizarCamadas();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setJanela(CoordenadaGeografica centro, double alcanceMN) {
/*  584 */     double dX, dY, aspecto = getLargura() / getAltura();
/*  585 */     CoordenadaCartesiana centroCart = CoordenadaCartesiana.converterCoordenadaGeografica(centro, 0.0D);
/*  586 */     if (aspecto > 1.0D) {
/*  587 */       dX = alcanceMN * aspecto;
/*  588 */       dY = alcanceMN;
/*      */     } else {
/*  590 */       dX = alcanceMN;
/*  591 */       dY = alcanceMN / aspecto;
/*      */     } 
/*  593 */     CoordenadaCartesiana p1Cart = CoordenadaCartesiana.converterDistanciaXY(centroCart, -dX, -dY);
/*  594 */     CoordenadaCartesiana p2Cart = CoordenadaCartesiana.converterDistanciaXY(centroCart, dX, dY);
/*      */     
/*  596 */     JanelaGeografica janelaGeo = new JanelaGeografica(CoordenadaGeografica.converterCoordenadaCartesiana(p1Cart, 0.0D), CoordenadaGeografica.converterCoordenadaCartesiana(p2Cart, 0.0D));
/*  597 */     int novoLevelOfDetail = getLevelOfDetail(janelaGeo);
/*  598 */     if (novoLevelOfDetail >= 4 && novoLevelOfDetail <= 20) {
/*  599 */       super.setJanela(centro, alcanceMN);
/*      */     
/*      */     }
/*  602 */     else if (this.visuDisponivel) {
/*      */       
/*  604 */       this.visuDisponivel = false;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void iniciaAtualizadorCamadas() {
/*  609 */     ThreadPool.executar(new AtualizadorCamadas(), "Atualizador de camadas");
/*      */   }
/*      */   
/*      */   public CoordenadaGeografica defineCentroJanela(Posicao pos) {
/*  613 */     return pos.getCoordenadaGeografica();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public class AtualizadorCamadas
/*      */     implements Runnable
/*      */   {
/*      */     public void run() {
/*  622 */       ContextoTatico contexto = (ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto();
/*      */       while (true) {
/*      */         try {
/*  625 */           CanvasCenarioTatico.this.lock.lock();
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  630 */           while (!CanvasCenarioTatico.this.sinalAtualizarCamadas) {
/*  631 */             CanvasCenarioTatico.this.atualizar.awaitUninterruptibly();
/*      */           }
/*  633 */           CanvasCenarioTatico.this.sinalAtualizarCamadas = false;
/*      */         } finally {
/*  635 */           CanvasCenarioTatico.this.lock.unlock();
/*      */         } 
/*      */         
/*      */         try {
/*  639 */           Posicao posicaoReferencial = contexto.getTeatroOperacao().getPosicaoReferencial();
/*      */ 
/*      */           
/*  642 */           if (CanvasCenarioTatico.this.getModoNavegacao() == CanvasCenarioTatico.ModoNavegacao.CENTRADO || CanvasCenarioTatico.this.getModoNavegacao() == CanvasCenarioTatico.ModoNavegacao.LOOK_AHEAD) {
/*  643 */             CanvasCenarioTatico.this.setCentroJanela(CanvasCenarioTatico.this.defineCentroJanela(posicaoReferencial));
/*      */           }
/*      */           
/*  646 */           CanvasCenarioTatico.this.posReferencialRaster = CanvasCenarioTatico.this.projeta(posicaoReferencial.getCoordenadaCartesiana());
/*      */ 
/*      */           
/*  649 */           if (CanvasCenarioTatico.this.filtroCartaNautica.isExibir()) {
/*  650 */             GestorCartasGenerico.ServidorCartas servidor = PerfilUsuario.getPerfilUsuarioAtual().getServidorCartas();
/*  651 */             if (servidor == GestorCartasGenerico.ServidorCartas.IPQM) {
/*  652 */               if (CanvasCenarioTatico.this.filtroVideoRadar.isExibir()) {
/*  653 */                 CanvasCenarioTatico.this.camadaCartaBase.desenha(CanvasCenarioTatico.this.janelaGeo, CamadaCarta.ModoDesenho.RADAR_BASE);
/*  654 */                 CanvasCenarioTatico.this.camadaCartaTopo.desenha(CanvasCenarioTatico.this.janelaGeo, CamadaCarta.ModoDesenho.RADAR_TOPO);
/*  655 */                 if (((FiltroMapaOnline)CanvasCenarioTatico.this.camadaSatelite.getFiltro()).isExibir()) {
/*  656 */                   CanvasCenarioTatico.this.camadaCarta.desenha(CanvasCenarioTatico.this.janelaGeo, CamadaCarta.ModoDesenho.SATELITE_TOPO);
/*      */                 }
/*  658 */               } else if (CanvasCenarioTatico.this.filtroSatelite.isExibir()) {
/*  659 */                 CanvasCenarioTatico.this.camadaCartaBase.desenha(CanvasCenarioTatico.this.janelaGeo, CamadaCarta.ModoDesenho.SATELITE_BASE);
/*  660 */                 CanvasCenarioTatico.this.camadaCartaTopo.desenha(CanvasCenarioTatico.this.janelaGeo, CamadaCarta.ModoDesenho.SATELITE_TOPO);
/*      */               } else {
/*  662 */                 CanvasCenarioTatico.this.camadaCarta.desenha(CanvasCenarioTatico.this.janelaGeo);
/*      */               } 
/*      */             } else {
/*      */               
/*  666 */               CanvasCenarioTatico.this.camadaCarta.desenha(CanvasCenarioTatico.this.getJanelaGeografica());
/*      */             } 
/*      */           } 
/*      */ 
/*      */           
/*  671 */           CanvasCenarioTatico.this.camadaInfoEstatica.desenha(CanvasCenarioTatico.this.getJanelaGeografica(), CanvasCenarioTatico.this.getEscalaMN());
/*  672 */           CanvasCenarioTatico.this.camadaInfoEstatica.geraImagemFX();
/*      */ 
/*      */           
/*  675 */           CanvasCenarioTatico.this.atualizarCamadaTatica();
/*      */ 
/*      */           
/*  678 */           if (CanvasCenarioTatico.this.filtroSatelite.isExibir()) {
/*  679 */             CanvasCenarioTatico.this.camadaSatelite.desenha(CanvasCenarioTatico.this.getJanelaGeografica());
/*      */           }
/*      */ 
/*      */           
/*  683 */           if (CanvasCenarioTatico.this.filtroMapaAlternativo.isExibir()) {
/*  684 */             CanvasCenarioTatico.this.camadaMapaAlternativo.desenha(CanvasCenarioTatico.this.getJanelaGeografica());
/*      */           }
/*      */           
/*  687 */           CanvasCenarioTatico.this.centralizaVeiculo(posicaoReferencial);
/*      */           
/*  689 */           if (CanvasCenarioTatico.this.filtroVideoRadar.isExibir() && CanvasCenarioTatico.this.camadaRadarImage != null && (
/*  690 */             CanvasCenarioTatico.this.camadaRadarImage.getWidth() != CanvasCenarioTatico.this.larguraOriginal || CanvasCenarioTatico.this.camadaRadarImage.getHeight() != CanvasCenarioTatico.this.alturaOriginal)) {
/*  691 */             CanvasCenarioTatico.this.camadaRadarImage.clear();
/*  692 */             synchronized (CanvasCenarioTatico.this.camadaRadarImage) {
/*  693 */               CanvasCenarioTatico.this.camadaRadarImage = new RadarImage(CanvasCenarioTatico.this.larguraOriginal, CanvasCenarioTatico.this.alturaOriginal, CanvasCenarioTatico.this.camadaRadarImage.getRange(), 1.5D);
/*  694 */               CanvasCenarioTatico.this.camadaRadarImage.clear();
/*      */             }
/*      */           
/*      */           } 
/*  698 */         } catch (Exception ex) {
/*  699 */           Log.gravarLogExcecao("Erro ao atualizar Camadas", this, ex);
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private void centralizaVeiculo(Posicao posicaoReferencial) {
/*  706 */     if (this.camadaTatica == null) {
/*      */       return;
/*      */     }
/*      */ 
/*      */     
/*  711 */     CoordenadaCartesiana posCart = (Veiculo.getVeiculoReferencial() != null) ? Veiculo.getVeiculoReferencial().getPosicao().getCoordenadaCartesiana() : ((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getTeatroOperacao().getPosicaoTeatroOperacao().getCoordenadaCartesiana();
/*  712 */     CoordenadaRaster posRaster = this.camadaTatica.converterCoordenadaCartesianaParaRaster(posCart);
/*  713 */     if (posRaster == null) {
/*      */       return;
/*      */     }
/*  716 */     if (this.enquadrado && (
/*  717 */       posRaster.getX() > 1000 || posRaster.getY() > 1000 || posRaster.getX() < 10 || posRaster.getY() < 10)) {
/*  718 */       setCentroJanela(defineCentroJanela(posicaoReferencial));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public SubScene getCenaCanvas3D() {
/*  724 */     return this.cenaCanvas3D;
/*      */   }
/*      */   
/*      */   public GrupoCamera getGrupoCanvas() {
/*  728 */     return this.grupoCanvas;
/*      */   }
/*      */   
/*      */   public Camera getCamera() {
/*  732 */     return this.camera;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void adicionarEventos(Node... nodes) {
/*  741 */     for (Node node : nodes) {
/*  742 */       node.setOnKeyPressed(event -> ((ControladorCenarioTatico)getControlador()).tratarKeyPressedCanvas(event));
/*      */ 
/*      */       
/*  745 */       node.setOnKeyReleased(event -> ((ControladorCenarioTatico)getControlador()).tratarKeyReleasedCanvas(event));
/*      */ 
/*      */       
/*  748 */       node.setOnMouseClicked(event -> ((ControladorCenarioTatico)getControlador()).tratarMouseClickedCanvas(event));
/*      */ 
/*      */       
/*  751 */       node.setOnMouseDragged(event -> ((ControladorCenarioTatico)getControlador()).tratarMouseDraggedCanvas(event));
/*      */ 
/*      */       
/*  754 */       node.setOnMouseEntered(event -> ((ControladorCenarioTatico)getControlador()).tratarMouseEnteredCanvas(event));
/*      */ 
/*      */       
/*  757 */       node.setOnMouseExited(event -> ((ControladorCenarioTatico)getControlador()).tratarMouseExitedCanvas(event));
/*      */ 
/*      */       
/*  760 */       node.setOnMouseMoved(event -> ((ControladorCenarioTatico)getControlador()).tratarMouseMovedCanvas(event));
/*      */ 
/*      */       
/*  763 */       node.setOnMousePressed(event -> ((ControladorCenarioTatico)getControlador()).tratarMousePressedCanvas(event));
/*      */ 
/*      */       
/*  766 */       node.setOnMouseReleased(event -> ((ControladorCenarioTatico)getControlador()).tratarMouseReleasedCanvas(event));
/*      */ 
/*      */       
/*  769 */       node.setOnScroll(event -> ((ControladorCenarioTatico)getControlador()).tratarScrollCanvas(event));
/*      */ 
/*      */       
/*  772 */       node.setOnTouchMoved(event -> ((ControladorCenarioTatico)getControlador()).tratarEventoTouchMoved(event));
/*      */ 
/*      */       
/*  775 */       node.setOnTouchPressed(event -> ((ControladorCenarioTatico)getControlador()).tratarEventoTouchPressed(event));
/*      */ 
/*      */       
/*  778 */       node.setOnTouchReleased(event -> ((ControladorCenarioTatico)getControlador()).tratarEventoTouchReleased(event));
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<ObjetoVisual> obterTarefaSelecaoObjetosListaObjetosMarcados() {
/*  792 */     List<ObjetoVisual> listaMarcados = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  798 */     if (getIdTarefa() == CanvasEspacial.Tarefas.SELECAO_OBJETOS)
/*      */     {
/*  800 */       listaMarcados = ((TarefaSelecao)getTarefa(CanvasEspacial.Tarefas.SELECAO_OBJETOS)).getListaObjetosMarcados();
/*      */     }
/*      */ 
/*      */     
/*  804 */     return listaMarcados;
/*      */   }
/*      */ 
/*      */   
/*      */   public void mouseClicked(MouseEvent event) {
/*  809 */     if (event.getClickCount() == 2 && event.getButton() == MouseButton.PRIMARY) {
/*      */       
/*  811 */       int x = (int)(event.getSceneX() - this.layoutXCanvas);
/*  812 */       int y = (int)(event.getSceneY() - this.layoutYCanvas);
/*  813 */       CoordenadaRaster coordRaster = new CoordenadaRaster(x, y);
/*  814 */       CoordenadaCartesiana p = projetaInv(coordRaster);
/*  815 */       EstadoTeclado et = estadoTeclado(event);
/*      */       
/*  817 */       duploClique(p, et);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void mousePressed(MouseEvent event) {
/*  824 */     int x = (int)(event.getSceneX() - this.layoutXCanvas);
/*  825 */     int y = (int)(event.getSceneY() - this.layoutYCanvas);
/*  826 */     CoordenadaRaster coordRaster = new CoordenadaRaster(x, y);
/*  827 */     CoordenadaCartesiana p = projetaInv(coordRaster);
/*  828 */     EstadoTeclado et = estadoTeclado(event);
/*      */     
/*  830 */     switch (event.getButton()) {
/*      */       case UP:
/*  832 */         botao1Pressionado(p, et);
/*      */         break;
/*      */       case DOWN:
/*  835 */         botao2Pressionado(p, et);
/*      */         break;
/*      */       case RIGHT:
/*  838 */         botao3Pressionado(p, et);
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void mouseDragged(MouseEvent event) {
/*  845 */     if (event != null) {
/*  846 */       int x = (int)(event.getSceneX() - this.layoutXCanvas);
/*  847 */       int y = (int)(event.getSceneY() - this.layoutYCanvas);
/*  848 */       CoordenadaRaster coordRaster = new CoordenadaRaster(x, y);
/*  849 */       CoordenadaCartesiana p = projetaInv(coordRaster);
/*  850 */       EstadoTeclado et = estadoTeclado(event);
/*      */       
/*  852 */       if (this.arrastandoBotao1) {
/*      */         
/*  854 */         botao1Arrastado(p, et);
/*      */       }
/*  856 */       else if (this.arrastandoBotao2) {
/*      */         
/*  858 */         botao2Arrastado(p, et);
/*      */       }
/*  860 */       else if (this.arrastandoBotao3) {
/*      */         
/*  862 */         botao3Arrastado(p, et);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void mouseReleased(MouseEvent event) {
/*  870 */     int x = (int)(event.getSceneX() - this.layoutXCanvas);
/*  871 */     int y = (int)(event.getSceneY() - this.layoutYCanvas);
/*  872 */     CoordenadaRaster coordRaster = new CoordenadaRaster(x, y);
/*  873 */     CoordenadaCartesiana p = projetaInv(coordRaster);
/*  874 */     EstadoTeclado et = estadoTeclado(event);
/*      */     
/*  876 */     if (this.arrastandoBotao1) {
/*  877 */       botao1Solto(p, et);
/*  878 */     } else if (this.arrastandoBotao2) {
/*  879 */       botao2Solto(p, et);
/*  880 */     } else if (this.arrastandoBotao3) {
/*  881 */       botao3Solto(p, et);
/*      */     } 
/*      */     
/*  884 */     getCamadaInfo().tornaTransparente();
/*  885 */     getCamadaInfo().geraImagemFX();
/*      */   }
/*      */ 
/*      */   
/*      */   public void mouseMoved(MouseEvent event) {
/*  890 */     int x = (int)(event.getSceneX() - this.layoutXCanvas);
/*  891 */     int y = (int)(event.getSceneY() - this.layoutYCanvas);
/*  892 */     CoordenadaRaster coordRaster = new CoordenadaRaster(x, y);
/*  893 */     CoordenadaCartesiana p = projetaInv(coordRaster);
/*  894 */     EstadoTeclado et = estadoTeclado(event);
/*      */     
/*  896 */     mouseMovido(p, et);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void mouseExited(MouseEvent event) {
/*  902 */     int x = (int)(event.getSceneX() - this.layoutXCanvas);
/*  903 */     int y = (int)(event.getSceneY() - this.layoutYCanvas);
/*  904 */     CoordenadaRaster coordRaster = new CoordenadaRaster(x, y);
/*  905 */     CoordenadaCartesiana p = projetaInv(coordRaster);
/*      */ 
/*      */     
/*  908 */     mouseSaiu(p);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void mouseEntered(MouseEvent event) {
/*  914 */     int x = (int)(event.getSceneX() - this.layoutXCanvas);
/*  915 */     int y = (int)(event.getSceneY() - this.layoutYCanvas);
/*  916 */     CoordenadaRaster coordRaster = new CoordenadaRaster(x, y);
/*  917 */     CoordenadaCartesiana p = projetaInv(coordRaster);
/*      */ 
/*      */     
/*  920 */     mouseEntrou(p);
/*      */   }
/*      */   
/*      */   public void mouseScrolled(ScrollEvent event) {
/*  924 */     mouseRodou((int)event.getDeltaY());
/*      */   }
/*      */ 
/*      */   
/*      */   public void keyPressed(KeyEvent event) {
/*  929 */     if (getTarefa() != null) {
/*  930 */       getTarefa().teclaPressionada(event);
/*      */     }
/*  932 */     teclaPressionada(event);
/*  933 */     if (!event.isShiftDown()) {
/*  934 */       if (event.isAltDown()) {
/*  935 */         this.idTarefaAnterior = getIdTarefa();
/*  936 */         setIdTarefa(CanvasEspacial.Tarefas.PAN);
/*  937 */       } else if (event.isControlDown()) {
/*  938 */         this.idTarefaAnterior = getIdTarefa();
/*  939 */         setIdTarefa(CanvasEspacial.Tarefas.ZOOM);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public void keyReleased(KeyEvent event) {
/*  945 */     if (this.idTarefaAnterior != CanvasEspacial.Tarefas.NENHUMA) {
/*  946 */       setIdTarefa(this.idTarefaAnterior);
/*  947 */       this.idTarefaAnterior = CanvasEspacial.Tarefas.NENHUMA;
/*      */     } 
/*      */   }
/*      */   
/*      */   public void keyTyped(KeyEvent event) {
/*  952 */     if (getTarefa() != null) {
/*  953 */       getTarefa().teclaPressionada(event);
/*      */     }
/*  955 */     teclaPressionada(event);
/*      */   }
/*      */ 
/*      */   
/*      */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  960 */     this.posicaoMouse = p;
/*      */     
/*  962 */     setExibirMenuContexto(false);
/*      */     
/*  964 */     if (getTarefa() != null) {
/*  965 */       getTarefa().botao1Pressionado(p, e);
/*      */     }
/*  967 */     this.arrastandoBotao1 = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void botao1Arrastado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  972 */     if (getTarefa() != null) {
/*  973 */       getTarefa().botao1Arrastado(p, e);
/*      */     }
/*  975 */     atualizaPosicaoCursor(p);
/*      */   }
/*      */ 
/*      */   
/*      */   public void botao1Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/*  980 */     if (getTarefa() != null) {
/*  981 */       getTarefa().botao1Solto(p, e);
/*  982 */       setPosicaoCursorUltimoClique(p);
/*      */     } 
/*      */     
/*  985 */     this.arrastandoBotao1 = false;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void botao2Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  991 */     setExibirMenuContexto(false);
/*      */     
/*  993 */     if (getTarefa() != null) {
/*  994 */       getTarefa().botao2Pressionado(p, e);
/*      */     }
/*      */     
/*  997 */     this.arrastandoBotao2 = true;
/*      */   }
/*      */ 
/*      */   
/*      */   public void botao2Arrastado(CoordenadaCartesiana p, EstadoTeclado e) {
/* 1002 */     if (getTarefa() != null) {
/* 1003 */       getTarefa().botao2Arrastado(p, e);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void botao2Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/* 1009 */     if (getTarefa() != null) {
/* 1010 */       getTarefa().botao2Solto(p, e);
/*      */     }
/*      */     
/* 1013 */     this.arrastandoBotao2 = false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void botao3Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/* 1018 */     p = CoordenadaCartesiana.converterCoordenadaCartesianaDeclinacaoMagnetica(p, false);
/* 1019 */     setPosicaoCursorUltimoClique(p);
/* 1020 */     if (getTarefa() != null) {
/* 1021 */       getTarefa().botao3Pressionado(p, e);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       try {
/* 1039 */         getTarefa().habilitarComandos();
/* 1040 */         setExibirMenuContexto(getTarefa().obterComandosHabilitados().values().stream().anyMatch(item -> (((Boolean)item).booleanValue() == true)));
/*      */       }
/* 1042 */       catch (UnsupportedOperationException ex) {
/* 1043 */         setExibirMenuContexto(false);
/*      */       } 
/*      */     } 
/* 1046 */     this.arrastandoBotao3 = true;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void botao3Arrastado(CoordenadaCartesiana p, EstadoTeclado e) {
/* 1052 */     if (getTarefa() != null) {
/* 1053 */       getTarefa().botao3Arrastado(p, e);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void botao3Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/* 1059 */     if (getTarefa() != null) {
/* 1060 */       getTarefa().botao3Solto(p, e);
/*      */     }
/*      */     
/* 1063 */     this.arrastandoBotao3 = false;
/*      */   }
/*      */ 
/*      */   
/*      */   public void duploClique(CoordenadaCartesiana p, EstadoTeclado e) {
/* 1068 */     if (getTarefa() != null) {
/* 1069 */       getTarefa().duploClique(p, e);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void mouseMovido(CoordenadaCartesiana p, EstadoTeclado e) {
/* 1075 */     if (getTarefa() != null) {
/* 1076 */       getTarefa().mouseMovido(p, e);
/*      */     }
/*      */     
/* 1079 */     atualizaPosicaoCursor(p);
/*      */   }
/*      */ 
/*      */   
/*      */   public void mouseSaiu(CoordenadaCartesiana p) {
/* 1084 */     if (getTarefa() != null) {
/* 1085 */       getTarefa().mouseSaiu(p);
/*      */     }
/*      */     
/* 1088 */     atualizaPosicaoCursor(p);
/*      */   }
/*      */ 
/*      */   
/*      */   public void mouseEntrou(CoordenadaCartesiana p) {
/* 1093 */     if (getTarefa() != null) {
/* 1094 */       getTarefa().mouseEntrou(p);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void mouseRodou(int rotacao) {
/* 1100 */     if (getTarefa() != null) {
/* 1101 */       getTarefa().mouseRodou(rotacao);
/*      */     }
/* 1103 */     if (rotacao > 0) {
/* 1104 */       setEscalaMN(getEscalaMN() * 0.75D);
/*      */     } else {
/* 1106 */       setEscalaMN(getEscalaMN() * 1.5D);
/*      */     } 
/* 1108 */     atualizarCamadas();
/*      */   }
/*      */ 
/*      */   
/*      */   public void teclaPressionada(KeyEvent event) {
/* 1113 */     setRedesenhoAutomatico(false);
/*      */     
/* 1115 */     switch (event.getCode()) {
/*      */       
/*      */       case ESCAPE:
/* 1118 */         getTarefa().cancelarTarefa();
/*      */         break;
/*      */       case UP:
/*      */       case DOWN:
/*      */       case RIGHT:
/*      */       case LEFT:
/* 1124 */         setModoNavegacao(ModoNavegacao.LIVRE);
/* 1125 */         switch (event.getCode()) {
/*      */           case UP:
/* 1127 */             moveCentroJanela(0.0D, getEscalaMN() / 2.0D);
/*      */             break;
/*      */           case DOWN:
/* 1130 */             moveCentroJanela(0.0D, -getEscalaMN() / 2.0D);
/*      */             break;
/*      */           case RIGHT:
/* 1133 */             moveCentroJanela(getEscalaMN() / 2.0D, 0.0D);
/*      */             break;
/*      */           case LEFT:
/* 1136 */             moveCentroJanela(-getEscalaMN() / 2.0D, 0.0D);
/*      */             break;
/*      */         } 
/* 1139 */         atualizarCamadas();
/* 1140 */         ((ControladorCenarioTatico)getControlador()).desligaPPN();
/* 1141 */         setEnquadramento(false);
/* 1142 */         if (getCamadaRadarImage() != null) {
/* 1143 */           getCamadaRadarImage().clear();
/*      */         }
/*      */         break;
/*      */       case HOME:
/* 1147 */         setModoNavegacao(ModoNavegacao.CENTRADO);
/* 1148 */         atualizarCamadas();
/*      */         break;
/*      */       case ADD:
/* 1151 */         setEscalaMN(getEscalaMN() * 0.5D);
/* 1152 */         atualizarCamadas();
/*      */         break;
/*      */       case SUBTRACT:
/* 1155 */         setEscalaMN(getEscalaMN() * 2.0D);
/* 1156 */         atualizarCamadas();
/*      */         break;
/*      */       case M:
/* 1159 */         setIdTarefa(CanvasEspacial.Tarefas.CRIA_OBJETOS);
/*      */         break;
/*      */     } 
/*      */     
/* 1163 */     setRedesenhoAutomatico(true);
/*      */   }
/*      */   
/*      */   private EstadoTeclado estadoTeclado(MouseEvent e) {
/* 1167 */     EstadoTeclado et = new EstadoTeclado();
/* 1168 */     et.setAltDown(e.isAltDown());
/* 1169 */     et.setControlDown(e.isControlDown());
/* 1170 */     et.setShiftDown(e.isShiftDown());
/* 1171 */     return et;
/*      */   }
/*      */   
/*      */   public void moveCentroJanela(double distanciaX, double distanciaY) {
/* 1175 */     CoordenadaCartesiana centroCart = getJanelaCartesiana().getCentro();
/* 1176 */     centroCart.setX(centroCart.getX() + distanciaX);
/* 1177 */     centroCart.setY(centroCart.getY() + distanciaY);
/* 1178 */     setCentroJanela(CoordenadaGeografica.converterCoordenadaCartesiana(centroCart, 0.0D));
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<ObjetoTatico> getObjetosTaticos() {
/* 1184 */     List<ObjetoTatico> objetosTaticos = ((ControladorCenarioTatico)getControlador()).getObjetosTaticos();
/*      */     
/* 1186 */     return objetosTaticos;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean isExibirMenuContexto() {
/* 1191 */     return this.exibirMenuContexto;
/*      */   }
/*      */   
/*      */   public void setExibirMenuContexto(boolean exibirMenuContexto) {
/* 1195 */     this.exibirMenuContexto = exibirMenuContexto;
/*      */   }
/*      */   
/*      */   public void show2DCanvas() {
/* 1199 */     Log.gravarLogInstrucao("Alterando canvas para o Canvas 2D", this);
/* 1200 */     Platform.runLater(() -> {
/*      */           getGrupoCanvas().getChildren().clear();
/*      */           getGrupoCanvas().getChildren().addAll((Object[])new Node[] { (Node)this.mapaCanvas });
/*      */         });
/*      */   }
/*      */   
/*      */   public void show3DCanvas() {
/* 1207 */     Log.gravarLogInstrucao("Alterando canvas para o Canvas 3D", this);
/* 1208 */     ThreadPool.executar(() -> {
/*      */           JanelaGeografica geo = getJanelaGeografica();
/*      */           BBox bbox = new BBox(geo.getMin().getLongitude(), geo.getMin().getLatitude(), geo.getMax().getLongitude(), geo.getMax().getLatitude());
/*      */           Platform.runLater(());
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private TriangleMesh build3DModel(BBox bbox) {
/* 1248 */     float gridSpacing = 3.0F;
/* 1249 */     float d = gridSpacing / 3600.0F;
/* 1250 */     int numX = (int)(0.5D + (bbox.getMax().getLongitude() - bbox.getMin().getLongitude()) / d);
/* 1251 */     int numY = (int)(0.5D + (bbox.getMax().getLatitude() - bbox.getMin().getLatitude()) / d);
/* 1252 */     float[][] zData = new float[numY][numX];
/* 1253 */     if (this.dtmFiles != null) {
/* 1254 */       for (DTMFile f : this.dtmFiles) {
/* 1255 */         if (f.getBBox().intersects(bbox)) {
/*      */           try {
/* 1257 */             f.read();
/* 1258 */             double lat = bbox.getMin().getLatitude();
/* 1259 */             for (int iy = 0; iy < numY; iy++) {
/* 1260 */               double lon = bbox.getMin().getLongitude();
/* 1261 */               for (int ix = 0; ix < numX; ix++) {
/*      */                 try {
/* 1263 */                   float z = f.getZValue(lon, lat);
/* 1264 */                   zData[iy][ix] = z;
/* 1265 */                 } catch (Exception exception) {}
/*      */                 
/* 1267 */                 lon += d;
/*      */               } 
/* 1269 */               lat += d;
/*      */             } 
/* 1271 */           } catch (Exception exception) {}
/*      */         }
/*      */       } 
/*      */     }
/*      */     
/* 1276 */     return Model3D.buildMesh(zData, bbox, 3.0F, 30.0F);
/*      */   }
/*      */   
/*      */   public void update3DCanvas() {
/* 1280 */     if (this.terrainMesh != null) {
/* 1281 */       Platform.runLater(() -> {
/*      */             WritableImage wi = this.mapaCanvas.snapshot(null, null);
/*      */             PhongMaterial mat = new PhongMaterial();
/*      */             mat.setDiffuseMap((Image)wi);
/*      */             this.terrainMesh.setMaterial((Material)mat);
/*      */           });
/*      */     }
/*      */   }
/*      */   
/*      */   public void set3DCanvasWireFrameMode(boolean wireFrameMode) {
/* 1291 */     this.terrainMesh.setDrawMode(wireFrameMode ? DrawMode.LINE : DrawMode.FILL);
/*      */   }
/*      */   
/*      */   public boolean get3DCanvasWireFrameMode() {
/* 1295 */     return (this.terrainMesh.getDrawMode() == DrawMode.LINE);
/*      */   }
/*      */   
/*      */   private void init3DCanvas() {
/* 1299 */     this.dtmFiles = new ArrayList<>();
/* 1300 */     File dir = new File(Diretorios.getDiretorioPadrao(Diretorios.Diretorio.DTM));
/* 1301 */     if (dir.isDirectory()) {
/* 1302 */       for (File file : dir.listFiles()) {
/* 1303 */         if (file.isFile()) {
/* 1304 */           DTMFile dtm = new DTMFile(file.getAbsolutePath());
/* 1305 */           this.dtmFiles.add(dtm);
/*      */         } 
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   @Deprecated
/*      */   public void exibirDadosFeicoes(String dadosFeicoes) {
/* 1313 */     ControladorCenarioTatico controlador = (ControladorCenarioTatico)getControlador();
/* 1314 */     controlador.exibirDadosFeicoes(dadosFeicoes);
/*      */   }
/*      */   
/*      */   private void atualizaPosicaoCursor(CoordenadaCartesiana posicaoCursor) {
/* 1318 */     ((ControladorCenarioTatico)getControlador()).exibirPosicaoCursor(posicaoCursor);
/*      */     
/* 1320 */     if (this.filtroInfoEstatica.isExibirUTM()) {
/* 1321 */       this.camadaInfoEstatica.setUTM(UTM.obterTextoUTM(posicaoCursor));
/*      */     }
/*      */   }
/*      */   
/*      */   public void corrigirRangeSimuladorRadar(double alcance) {
/* 1326 */     Range range = null;
/* 1327 */     if (alcance <= 0.25D) {
/* 1328 */       range = Range.RANGE_0_25NM;
/* 1329 */     } else if (alcance <= 0.5D) {
/* 1330 */       range = Range.RANGE_0_5NM;
/* 1331 */     } else if (alcance <= 0.75D) {
/* 1332 */       range = Range.RANGE_0_75NM;
/* 1333 */     } else if (alcance <= 1.5D) {
/* 1334 */       range = Range.RANGE_1_5NM;
/* 1335 */     } else if (alcance <= 3.0D) {
/* 1336 */       range = Range.RANGE_3NM;
/* 1337 */     } else if (alcance <= 6.0D) {
/* 1338 */       range = Range.RANGE_6NM;
/* 1339 */     } else if (alcance <= 12.0D) {
/* 1340 */       range = Range.RANGE_12NM;
/* 1341 */     } else if (alcance <= 24.0D) {
/* 1342 */       range = Range.RANGE_24NM;
/* 1343 */     } else if (alcance <= 48.0D) {
/* 1344 */       range = Range.RANGE_48NM;
/* 1345 */     } else if (alcance <= 96.0D) {
/* 1346 */       range = Range.RANGE_96NM;
/*      */     } else {
/* 1348 */       range = Range.RANGE_192NM;
/*      */     } 
/*      */     
/* 1351 */     getFiltroVideoRadar().getRadarImageParameters().setRange(range);
/*      */   }
/*      */   
/*      */   public int getDisplayScale() {
/* 1355 */     return this.displayScale;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getIdRadarSelecionado() {
/* 1362 */     return this.idRadarSelecionado;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setIdRadarSelecionado(int idRadarSelecionado) {
/* 1369 */     this.idRadarSelecionado = idRadarSelecionado;
/*      */   }
/*      */   
/*      */   public void recuperarEstado() {
/* 1373 */     if (GravarEstado.verificarRecuperacao()) {
/* 1374 */       String escala = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.ESCALA_VISUALIZACAO);
/* 1375 */       if (escala != null && !escala.equals("")) {
/* 1376 */         setEscalaMN(Double.parseDouble(escala));
/* 1377 */         ((ControladorCenarioTatico)getControlador()).acertaEscalaBarraFerramentas();
/*      */       } 
/*      */       
/* 1380 */       if (Veiculo.getVeiculoReferencial() != null && Veiculo.getVeiculoReferencial().getMapaSensores().get(Sensor.Tipo.RADAR_BUSCA) != null) {
/* 1381 */         String radar = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.RADAR_SELECIONADO_FR);
/* 1382 */         if (radar != null) {
/* 1383 */           RadarBusca radarSelecionado = null;
/* 1384 */           for (Sensor sensor : Veiculo.getVeiculoReferencial().getMapaSensores().get(Sensor.Tipo.RADAR_BUSCA)) {
/* 1385 */             if (radar.equals(sensor.getNome())) {
/* 1386 */               radarSelecionado = (RadarBusca)sensor;
/*      */               break;
/*      */             } 
/*      */           } 
/* 1390 */           if (radarSelecionado != null) {
/* 1391 */             ((GestorSimulacaoFX)Mediador.getInstancia().getGestor().getGestorSimulacao()).getSimuladorVideoRadar();
/* 1392 */             this.idRadarSelecionado = radarSelecionado.getId();
/* 1393 */             SimuladorVideoBrutoFX.setIdRadarAtual(this.idRadarSelecionado);
/* 1394 */             setIdRadarSelecionado(this.idRadarSelecionado);
/* 1395 */             if (GestorSimulacaoFX.MAPA_SIMULADORES.get(Integer.valueOf(this.idRadarSelecionado)) != null) {
/* 1396 */               ((SimuladorVideoBrutoFX)GestorSimulacaoFX.MAPA_SIMULADORES.get(Integer.valueOf(this.idRadarSelecionado))).setCanvasCenarioTatico(this);
/* 1397 */               ((SimuladorVideoBrutoFX)GestorSimulacaoFX.MAPA_SIMULADORES.get(Integer.valueOf(this.idRadarSelecionado))).setExibir(true);
/*      */             } 
/*      */             
/* 1400 */             setEscalaRadarMN(getEscalaMN());
/* 1401 */             ControladorRadarGenerico.setRadarSelecionado(radarSelecionado);
/*      */           } 
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/* 1407 */       getFiltroObjetoTatico().obterEstado();
/* 1408 */       getFiltroInfoEstatica().obterEstado();
/*      */       
/* 1410 */       ENCParameters parameters = Mediador.getInstancia().getGestor().getGestorCartas().getParameters();
/*      */       
/* 1412 */       String charts = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.CHARTS);
/* 1413 */       if (!charts.equals("")) {
/* 1414 */         switch (charts) {
/*      */           case "Todas":
/* 1416 */             if (parameters instanceof ENCParameters) {
/* 1417 */               parameters.setCharts();
/*      */             }
/*      */             return;
/*      */         } 
/*      */         try {
/* 1422 */           BBox bbox = Mediador.getInstancia().getGestor().getGestorCartas().getChartBBox(charts);
/* 1423 */           double latMin = bbox.getMin().getLatitude();
/* 1424 */           double latMax = bbox.getMax().getLatitude();
/* 1425 */           double longMin = bbox.getMin().getLongitude();
/* 1426 */           double longMax = bbox.getMax().getLongitude();
/* 1427 */           JanelaGeografica janela = new JanelaGeografica(longMin, latMin, longMax, latMax);
/* 1428 */           setModoNavegacao(ModoNavegacao.LIVRE);
/* 1429 */           setEnquadramento(false);
/* 1430 */           setCentroJanela(janela.getCentro());
/* 1431 */           ((ControladorCenarioTatico)getControlador()).tratarMouseDraggedCanvas(null);
/* 1432 */           List<String> cartas = new ArrayList<>();
/* 1433 */           cartas.add(charts);
/* 1434 */           Mediador.getInstancia().getGestor().getGestorCartas().getParameters().setCharts(cartas);
/* 1435 */           if (parameters instanceof ENCParameters) {
/* 1436 */             parameters.setCharts(cartas);
/*      */           }
/*      */         }
/* 1439 */         catch (NullPointerException ex) {
/* 1440 */           Log.gravarLogExcecao("Erro ao carregar carta selecionada.", this, ex);
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   public abstract void iniciarAnimacao();
/*      */   
/*      */   public abstract void pararAnimacao();
/*      */   
/*      */   protected abstract void desenharCamadas();
/*      */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/CanvasCenarioTatico.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */