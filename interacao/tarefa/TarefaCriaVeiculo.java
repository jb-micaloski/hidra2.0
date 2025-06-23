/*     */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.componentes.dominio.entidades.Veiculo;
/*     */ import ipqm.gsd.componentes.dominio.simuladores.GeradorVeiculos;
/*     */ import ipqm.gsd.componentes.nucleo.util.depuracao.Debug;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*     */ import ipqm.gsd.hidra.ihm.projetos.geral.cenas.controladores.ControladorCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.projetos.geral.formularios.controladores.ControladorVeiculoSecundarioForm;
/*     */ import ipqm.gsd.hidra.ihm.util.desenho.UtilJava2D;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javafx.scene.Cursor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TarefaCriaVeiculo
/*     */   extends Tarefa2P<CanvasCenarioTatico<ControladorCenarioTatico>>
/*     */ {
/*     */   private Cursor cursor;
/*     */   private EstadoTeclado estadoTeclado;
/*     */   
/*     */   public TarefaCriaVeiculo(CanvasCenarioTatico<ControladorCenarioTatico> canvasCT) {
/*  34 */     super(canvasCT);
/*     */   }
/*     */ 
/*     */   
/*     */   public void inicializa() {
/*  39 */     this.cursor = getCanvasEspacial().getCursor();
/*  40 */     getCanvasEspacial().setCursor(Cursor.CROSSHAIR);
/*     */   }
/*     */ 
/*     */   
/*     */   public void finaliza() {
/*  45 */     getCanvasEspacial().setCursor(this.cursor);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  50 */     super.botao1Pressionado(p, e);
/*  51 */     getCanvasEspacial().setRedesenhoAutomatico(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/*  56 */     this.estadoTeclado = e;
/*  57 */     super.botao1Solto(p, e);
/*  58 */     getCanvasEspacial().setRedesenhoAutomatico(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p) {
/*  63 */     criaVeiculo(p, 0.0D, 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p0, CoordenadaCartesiana p1) {
/*  68 */     criaVeiculo(p0, getRumo(p0, p1), getVelocidade(p0, p1));
/*     */   }
/*     */   
/*     */   private void criaVeiculo(CoordenadaCartesiana posicao, double rumo, double velocidade) {
/*  72 */     CoordenadaGeografica cg = CoordenadaGeografica.converterCoordenadaCartesiana(posicao, 0.0D);
/*  73 */     if (velocidade > 9999.0D) {
/*  74 */       velocidade = 1.0E-5D;
/*     */     }
/*     */     
/*  77 */     Veiculo veiculoAleatorio = null;
/*     */     
/*  79 */     if (Debug.isDebug() && this.estadoTeclado.isShiftDown())
/*     */     {
/*  81 */       veiculoAleatorio = GeradorVeiculos.gerarVeiculoAleatorio(posicao, rumo, velocidade);
/*     */     }
/*     */     
/*  84 */     if (veiculoAleatorio != null) {
/*  85 */       veiculoAleatorio.salvarVeiculoComSensores(false);
/*     */     } else {
/*  87 */       ControladorVeiculoSecundarioForm.setLatitude(cg.getLatitude());
/*  88 */       ControladorVeiculoSecundarioForm.setLongitude(cg.getLongitude());
/*  89 */       ControladorVeiculoSecundarioForm.setRumo(rumo);
/*  90 */       ControladorVeiculoSecundarioForm.setVelocidade(velocidade);
/*     */       
/*  92 */       ControladorCenarioTatico controlador = (ControladorCenarioTatico)getCanvasEspacial().getControlador();
/*  93 */       controlador.exibirPainelFormulario("projetos/geral/formularios/VeiculoSecundarioForm.fxml", "Veículo Secundário", null);
/*     */     } 
/*  95 */     getCanvasEspacial().getCamadaInfo().tornaTransparente();
/*  96 */     getCanvasEspacial().getCamadaInfo().geraImagemFX();
/*     */   }
/*     */   
/*     */   private double getRumo(CoordenadaCartesiana p0, CoordenadaCartesiana p1) {
/* 100 */     double rumo = 90.0D - Math.toDegrees(Math.atan2(p1.getY() - p0.getY(), p1.getX() - p0.getX()));
/* 101 */     if (rumo < 0.0D) {
/* 102 */       rumo += 360.0D;
/*     */     }
/* 104 */     return rumo;
/*     */   }
/*     */   
/*     */   private double getVelocidade(CoordenadaCartesiana p0, CoordenadaCartesiana p1) {
/* 108 */     int tamanhoVetorVeloc = getCanvasEspacial().getFiltroObjetoTatico().getTamanhoVetorVeloc();
/* 109 */     return CoordenadaCartesiana.calcularDistancia(p0, p1) / tamanhoVetorVeloc / 60.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void desenha() {
/* 114 */     if (this.p1 != null && this.p2 != null) {
/* 115 */       CanvasCenarioTatico<ControladorCenarioTatico> canvasCT = getCanvasEspacial();
/* 116 */       canvasCT.getCamadaInfo().tornaTransparente();
/* 117 */       Graphics2D g2d = canvasCT.getCamadaInfo().getImagem().createGraphics();
/*     */       
/* 119 */       g2d.setStroke(new BasicStroke(3.0F));
/* 120 */       g2d.setColor(Color.yellow);
/* 121 */       CoordenadaRaster p1Raster = canvasCT.projeta(this.p1);
/* 122 */       CoordenadaRaster p2Raster = canvasCT.projeta(this.p2);
/* 123 */       g2d.drawRect(p1Raster.getX() - 10, p1Raster.getY() - 10, 20, 20);
/* 124 */       UtilJava2D.desenhaTracejado(g2d, p1Raster.getX(), p1Raster.getY(), p2Raster.getX(), p2Raster.getY());
/* 125 */       String info1 = String.format("%3.0f°", new Object[] { Double.valueOf(getRumo(this.p1, this.p2)) });
/* 126 */       String info2 = String.format("%-5.1f nós", new Object[] { Double.valueOf(getVelocidade(this.p1, this.p2)) });
/* 127 */       Font font = new Font("Arial", 1, 10);
/* 128 */       FontMetrics fm = g2d.getFontMetrics(font);
/* 129 */       g2d.setFont(font);
/* 130 */       g2d.drawString(info1, p2Raster.getX() + 15, p2Raster.getY() - 2);
/* 131 */       g2d.drawString(info2, p2Raster.getX() + 15, p2Raster.getY() + 5 + fm.getHeight() / 2);
/* 132 */       g2d.dispose();
/* 133 */       canvasCT.getCamadaInfo().geraImagemFX();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void executarComando(Comando comando, CoordenadaCartesiana posicao) {
/* 140 */     if (isHabilitarComando(comando)) {
/*     */       CoordenadaGeografica posicaoAtual;
/* 142 */       switch (comando) {
/*     */         case CRIA_VEICULO:
/* 144 */           posicaoAtual = CoordenadaGeografica.converterCoordenadaCartesiana(posicao, 0.0D);
/* 145 */           ControladorVeiculoSecundarioForm.setLatitude(posicaoAtual.getLatitude());
/* 146 */           ControladorVeiculoSecundarioForm.setLongitude(posicaoAtual.getLongitude());
/*     */           break;
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
/*     */   public void habilitarComandos() {
/* 163 */     setHabilitarComando(Comando.CRIA_VEICULO, true);
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
/*     */   public Map<Comando, Boolean> obterComandosHabilitados() {
/* 187 */     Map<Comando, Boolean> comandosHabilitados = new HashMap<>();
/*     */     
/* 189 */     comandosHabilitados.put(Comando.CRIA_VEICULO, Boolean.valueOf(isHabilitarComando(Comando.CRIA_VEICULO)));
/*     */     
/* 191 */     return comandosHabilitados;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaCriaVeiculo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */