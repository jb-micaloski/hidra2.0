/*     */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.acompanhamentos.AcompanhamentoAIS;
/*     */ import ipqm.gsd.componentes.dominio.acompanhamentos.AcompanhamentoManual;
/*     */ import ipqm.gsd.componentes.dominio.acompanhamentos.AcompanhamentoRadar;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.Rumo;
/*     */ import ipqm.gsd.componentes.dominio.contexto.ContextoTatico;
/*     */ import ipqm.gsd.componentes.dominio.simuladores.GeradorAcompanhamentos;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.util.depuracao.Debug;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
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
/*     */ 
/*     */ public class TarefaCriaObjetos
/*     */   extends Tarefa2P<CanvasCenarioTatico>
/*     */ {
/*     */   private Cursor cursor;
/*     */   private EstadoTeclado estadoTeclado;
/*     */   
/*     */   public TarefaCriaObjetos(CanvasCenarioTatico canvasCT) {
/*  39 */     super(canvasCT);
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
/*  60 */     this.estadoTeclado = e;
/*  61 */     getCanvasEspacial().getCamadaInfo().tornaTransparente();
/*  62 */     getCanvasEspacial().getCamadaInfo().geraImagemFX();
/*  63 */     super.botao1Solto(p, e);
/*     */   }
/*     */ 
/*     */   
/*     */   public void desenha() {
/*  68 */     if (this.p1 != null && this.p2 != null) {
/*     */       
/*  70 */       CanvasCenarioTatico canvasCT = getCanvasEspacial();
/*  71 */       canvasCT.getCamadaInfo().tornaTransparente();
/*  72 */       Graphics2D g2d = canvasCT.getCamadaInfo().getImagem().createGraphics();
/*     */       
/*  74 */       g2d.setStroke(new BasicStroke(3.0F));
/*  75 */       g2d.setColor(Color.yellow);
/*  76 */       CoordenadaRaster p1Raster = canvasCT.projeta(this.p1);
/*  77 */       CoordenadaRaster p2Raster = canvasCT.projeta(this.p2);
/*  78 */       g2d.drawRect(p1Raster.getX() - 10, p1Raster.getY() - 10, 20, 20);
/*  79 */       UtilJava2D.desenhaTracejado(g2d, p1Raster.getX(), p1Raster.getY(), p2Raster.getX(), p2Raster.getY());
/*  80 */       String info1 = String.format("%3.0f°", new Object[] { Double.valueOf(getRumo(this.p1, this.p2)) });
/*  81 */       String info2 = String.format("%-5.1f nós", new Object[] { Double.valueOf(getVelocidade(this.p1, this.p2)) });
/*  82 */       Font font = new Font("Arial", 1, 10);
/*  83 */       FontMetrics fm = g2d.getFontMetrics(font);
/*  84 */       g2d.setFont(font);
/*  85 */       g2d.drawString(info1, p2Raster.getX() + 15, p2Raster.getY() - 2);
/*  86 */       g2d.drawString(info2, p2Raster.getX() + 15, p2Raster.getY() + 5 + fm.getHeight() / 2);
/*  87 */       g2d.dispose();
/*  88 */       canvasCT.getCamadaInfo().geraImagemFX();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void criaAcompanhamento(CoordenadaCartesiana posicao, double rumo, double velocidade) {
/*  93 */     CoordenadaGeografica cg = CoordenadaGeografica.converterCoordenadaCartesiana(posicao, 0.0D);
/*  94 */     if (velocidade > 9999.0D) {
/*  95 */       velocidade = 1.0E-5D;
/*     */     }
/*     */     
/*  98 */     if (Debug.isDebug() && this.estadoTeclado.isShiftDown() && !this.estadoTeclado.isControlDown()) {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/* 103 */         AcompanhamentoAIS acompanhamentoAIS = GeradorAcompanhamentos.gerarAcompanhamentoAIS(posicao, velocidade, rumo);
/* 104 */         acompanhamentoAIS.salvarMemoria(false);
/* 105 */       } catch (Exception ex) {
/* 106 */         Log.gravarLogExcecao("Erro ao gerar um acompanhamento ais aleatório", TarefaCriaObjetos.class, ex);
/*     */       }
/*     */     
/* 109 */     } else if (Debug.isDebug() && this.estadoTeclado.isControlDown() && this.estadoTeclado.isShiftDown()) {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/* 114 */         AcompanhamentoRadar acompanhamentoRadar = GeradorAcompanhamentos.gerarAcompanhamentoRadar(posicao, velocidade, rumo);
/* 115 */         acompanhamentoRadar.salvarMemoria(false);
/* 116 */       } catch (Exception ex) {
/* 117 */         Log.gravarLogExcecao("Erro ao gerar um acompanhamento radar aleatório", TarefaCriaObjetos.class, ex);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 122 */     if ((!Debug.isDebug() || !this.estadoTeclado.isShiftDown()) && (!Debug.isDebug() || !this.estadoTeclado.isControlDown())) {
/*     */       
/*     */       try {
/*     */         
/* 126 */         AcompanhamentoManual acompanhamentoManual = AcompanhamentoManual.fabricar(cg.getLatitude(), cg.getLongitude(), rumo, velocidade, (
/* 127 */             (ContextoTatico)Mediador.getInstancia().getContextualizador()
/* 128 */             .getContexto()).getVeiculoReferencial().getPosicao(), null);
/* 129 */         acompanhamentoManual.salvarMemoria(false);
/*     */       }
/* 131 */       catch (Exception e) {
/* 132 */         Log.gravarLogExcecao("Erro ao criar acompanhamento", this, e);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private double getRumo(CoordenadaCartesiana p0, CoordenadaCartesiana p1) {
/* 138 */     if (p0 == null || p1 == null) {
/* 139 */       return Double.NaN;
/*     */     }
/* 141 */     double rumo = 90.0D - Math.toDegrees(Math.atan2(p1.getY() - p0.getY(), p1.getX() - p0.getX()));
/* 142 */     if (rumo < 0.0D) {
/* 143 */       rumo += 360.0D;
/*     */     }
/* 145 */     return rumo;
/*     */   }
/*     */   
/*     */   private double getVelocidade(CoordenadaCartesiana p0, CoordenadaCartesiana p1) {
/* 149 */     int tamanhoVetorVeloc = getCanvasEspacial().getFiltroObjetoTatico().getTamanhoVetorVeloc();
/* 150 */     return CoordenadaCartesiana.calcularDistancia(p0, p1) / tamanhoVetorVeloc / 60.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p) {
/* 155 */     criaAcompanhamento(CoordenadaCartesiana.converterCoordenadaCartesianaDeclinacaoMagnetica(p, false), Rumo.calculaRumoDeclinacaoMagnetica(0.0D, false), 0.0D);
/*     */     
/* 157 */     getCanvasEspacial().getCamadaInfo().tornaTransparente();
/* 158 */     getCanvasEspacial().getCamadaInfo().geraImagemFX();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p0, CoordenadaCartesiana p1) {
/* 164 */     criaAcompanhamento(CoordenadaCartesiana.converterCoordenadaCartesianaDeclinacaoMagnetica(p0, false), 
/* 165 */         Rumo.calculaRumoDeclinacaoMagnetica(getRumo(p0, p1), false), getVelocidade(p0, p1));
/* 166 */     getCanvasEspacial().getCamadaInfo().tornaTransparente();
/* 167 */     getCanvasEspacial().getCamadaInfo().geraImagemFX();
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
/*     */   public void executarComando(Comando comando, CoordenadaCartesiana posicao) {
/* 190 */     if (isHabilitarComando(comando)) {
/*     */       CanvasCenarioTatico canvasCT;
/* 192 */       switch (comando) {
/*     */         case CRIAR_ACOMPANHAMENTO:
/* 194 */           canvasCT = getCanvasEspacial();
/* 195 */           canvasCT.atualizarCamadaTatica();
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
/* 212 */     setHabilitarComando(Comando.CRIAR_ACOMPANHAMENTO, true);
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
/* 237 */     Map<Comando, Boolean> comandosHabilitados = new HashMap<>();
/*     */     
/* 239 */     comandosHabilitados.put(Comando.CRIAR_ACOMPANHAMENTO, Boolean.valueOf(isHabilitarComando(Comando.CRIAR_ACOMPANHAMENTO)));
/*     */     
/* 241 */     return comandosHabilitados;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaCriaObjetos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */