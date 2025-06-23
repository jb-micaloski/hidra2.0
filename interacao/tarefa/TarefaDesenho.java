/*     */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*     */ import ipqm.gsd.hidra.ihm.projetos.geral.cenas.controladores.ControladorCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.projetos.reproducao.Desenho;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.Deque;
/*     */ import javafx.scene.Cursor;
/*     */ import javafx.scene.input.KeyCode;
/*     */ import javafx.scene.input.KeyEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TarefaDesenho
/*     */   extends Tarefa2P<CanvasCenarioTatico<? extends ControladorCenarioTatico>>
/*     */ {
/*     */   private Cursor cursor;
/*     */   private Graphics2D g2d;
/*     */   private Desenho desenho;
/*     */   private final Deque<Desenho> listaDesenhos;
/*     */   private static Color cor;
/*     */   
/*     */   public TarefaDesenho(CanvasCenarioTatico<? extends ControladorCenarioTatico> canvas) {
/*  34 */     super(canvas);
/*  35 */     this.listaDesenhos = new ArrayDeque<>();
/*  36 */     cor = Color.white;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void desenha() {
/*  42 */     if (this.p1 != null && this.p2 != null) {
/*  43 */       this.g2d = getCanvasEspacial().getCamadaInfo().getImagem().createGraphics();
/*  44 */       this.g2d.setStroke(new BasicStroke(3.0F));
/*  45 */       this.g2d.setColor(cor);
/*  46 */       CoordenadaRaster p1Raster = getCanvasEspacial().projeta(this.p1);
/*  47 */       CoordenadaRaster p2Raster = getCanvasEspacial().projeta(this.p2);
/*  48 */       this.g2d.drawLine(p1Raster.getX(), p1Raster.getY(), p2Raster.getX(), p2Raster.getY());
/*  49 */       this.g2d.dispose();
/*  50 */       getCanvasEspacial().getCamadaInfo().geraImagemFX();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  56 */     super.botao1Pressionado(p, e);
/*  57 */     this.desenho = new Desenho(p);
/*  58 */     this.desenho.setCor(cor);
/*  59 */     this.listaDesenhos.add(this.desenho);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Arrastado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  64 */     this.p2 = p;
/*  65 */     this.desenho.getForma().add(p);
/*  66 */     desenha();
/*  67 */     this.p1 = this.p2;
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/*  72 */     super.botao1Solto(p, e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void botao3Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/*  78 */     desfazer();
/*     */   }
/*     */ 
/*     */   
/*     */   public void inicializa() {
/*  83 */     this.cursor = getCanvasEspacial().getCursor();
/*  84 */     getCanvasEspacial().setCursor(Cursor.CROSSHAIR);
/*  85 */     CanvasCenarioTatico<? extends ControladorCenarioTatico> canvas = getCanvasEspacial();
/*  86 */     ((ControladorCenarioTatico)canvas.getControlador()).exibirPainelDesenho();
/*     */   }
/*     */ 
/*     */   
/*     */   public void finaliza() {
/*  91 */     getCanvasEspacial().setCursor(this.cursor);
/*  92 */     CanvasCenarioTatico<? extends ControladorCenarioTatico> canvas = getCanvasEspacial();
/*  93 */     ((ControladorCenarioTatico)canvas.getControlador()).exibirPainelDesenho();
/*     */   }
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p1, CoordenadaCartesiana p2) {
/*  98 */     Mediador.getInstancia().getGestor().getGestorObjetos().gerarId((ObjetoDOMINIO)this.desenho);
/*  99 */     this.desenho.setAcao((byte)0);
/* 100 */     Mediador.getInstancia().getGestor().getGestorObjetos().gerir((ObjetoDOMINIO)this.desenho);
/* 101 */     getCanvasEspacial().getCamadaInfo().tornaTransparente();
/* 102 */     getCanvasEspacial().getCamadaInfo().geraImagemFX();
/*     */   }
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p) {
/* 107 */     Mediador.getInstancia().getGestor().getGestorObjetos().gerarId((ObjetoDOMINIO)this.desenho);
/* 108 */     this.desenho.setAcao((byte)0);
/* 109 */     Mediador.getInstancia().getGestor().getGestorObjetos().gerir((ObjetoDOMINIO)this.desenho);
/* 110 */     getCanvasEspacial().getCamadaInfo().tornaTransparente();
/* 111 */     getCanvasEspacial().getCamadaInfo().geraImagemFX();
/*     */   }
/*     */   
/*     */   public Deque<Desenho> getListaDesenhos() {
/* 115 */     return this.listaDesenhos;
/*     */   }
/*     */   
/*     */   public void limparDesenhos() {
/* 119 */     this.listaDesenhos.stream().forEach(desenho -> desenho.excluir(true));
/*     */ 
/*     */     
/* 122 */     this.listaDesenhos.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void desfazer() {
/* 129 */     if (this.desenho != null) {
/* 130 */       this.desenho.excluir(true);
/* 131 */       this.listaDesenhos.remove(this.desenho);
/*     */     } 
/* 133 */     if (!this.listaDesenhos.isEmpty()) {
/* 134 */       this.desenho = this.listaDesenhos.getLast();
/*     */     } else {
/* 136 */       this.desenho = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Color getCor() {
/* 144 */     return cor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setCor(Color aCor) {
/* 151 */     cor = aCor;
/*     */   }
/*     */ 
/*     */   
/*     */   public void teclaPressionada(KeyEvent event) {
/* 156 */     super.teclaPressionada(event);
/* 157 */     if (event.isControlDown() && event.getCode() == KeyCode.Z)
/* 158 */       desfazer(); 
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaDesenho.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */