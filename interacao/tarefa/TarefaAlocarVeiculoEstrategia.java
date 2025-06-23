/*     */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.componentes.dominio.constante_sstt.GrupoTatico;
/*     */ import ipqm.gsd.componentes.dominio.entidades.Veiculo;
/*     */ import ipqm.gsd.componentes.dominio.jogo.estrategias.JogoEstrategia;
/*     */ import ipqm.gsd.componentes.nucleo.notificador.Notificador;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*     */ import ipqm.gsd.hidra.ihm.projetos.geral.cenas.controladores.ControladorCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.projetos.geral.formularios.controladores.ControladorVeiculoForm;
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
/*     */ public class TarefaAlocarVeiculoEstrategia
/*     */   extends Tarefa2P<CanvasCenarioTatico<ControladorCenarioTatico>>
/*     */ {
/*     */   private Cursor cursor;
/*     */   
/*     */   public TarefaAlocarVeiculoEstrategia(CanvasCenarioTatico<ControladorCenarioTatico> canvasCT) {
/*  35 */     super(canvasCT);
/*     */   }
/*     */ 
/*     */   
/*     */   public void inicializa() {
/*  40 */     this.cursor = getCanvasEspacial().getCursor();
/*  41 */     getCanvasEspacial().setCursor(Cursor.CROSSHAIR);
/*     */   }
/*     */ 
/*     */   
/*     */   public void finaliza() {
/*  46 */     getCanvasEspacial().setCursor(this.cursor);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  51 */     super.botao1Pressionado(p, e);
/*  52 */     getCanvasEspacial().setRedesenhoAutomatico(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/*  57 */     super.botao1Solto(p, e);
/*  58 */     getCanvasEspacial().setRedesenhoAutomatico(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p) {
/*  63 */     alocarVeiculo(p, 0.0D, 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p0, CoordenadaCartesiana p1) {
/*  68 */     alocarVeiculo(p0, getRumo(p0, p1), getVelocidade(p0, p1));
/*     */   }
/*     */   
/*     */   private void alocarVeiculo(CoordenadaCartesiana cc, double rumo, double velocidade) {
/*  72 */     CoordenadaGeografica cg = CoordenadaGeografica.converterCoordenadaCartesiana(cc, 0.0D);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  77 */     int vazios = 0;
/*  78 */     for (Veiculo v : JogoEstrategia.getInstancia().getListaVeiculos()) {
/*     */       
/*  80 */       if (v.getCondicaoAssociada().getGrupo().equals(GrupoTatico.A) && 
/*  81 */         v.getDono() == null) {
/*  82 */         vazios++;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  87 */     if (vazios > 0) {
/*     */       
/*  89 */       CoordenadaCartesiana ccCirculo = JogoEstrategia.getInstancia().getCirculoOperacao().getCoordenadaCentro();
/*     */       
/*  91 */       double distancia = CoordenadaCartesiana.calcularDistancia(cc, ccCirculo);
/*     */       
/*  93 */       double raio = JogoEstrategia.getInstancia().getCirculoOperacao().getRaio();
/*     */ 
/*     */       
/*  96 */       if (distancia <= raio) {
/*     */ 
/*     */         
/*  99 */         ControladorVeiculoForm.setLatitude(cg.getLatitude());
/* 100 */         ControladorVeiculoForm.setLongitude(cg.getLongitude());
/* 101 */         ControladorVeiculoForm.setRumo(rumo);
/*     */         
/* 103 */         ControladorCenarioTatico controlador = (ControladorCenarioTatico)getCanvasEspacial().getControlador();
/* 104 */         controlador.exibirPainelFormulario("projetos/geral/formularios/VeiculoForm.fxml", "Inserir Veículo", null);
/*     */       } else {
/*     */         
/* 107 */         Notificador.alerta("Veiculo fora do Teatro de Operacão:", "A alocação de veículos só pode ocorrer dentro do cículo do Teatro.");
/*     */       } 
/*     */     } else {
/*     */       
/* 111 */       Notificador.alerta("Não é possivél alocar veículos", "Todos os veículos disponíveis já estão alocados.");
/*     */     } 
/*     */     
/* 114 */     getCanvasEspacial().getCamadaInfo().tornaTransparente();
/* 115 */     getCanvasEspacial().getCamadaInfo().geraImagemFX();
/*     */   }
/*     */ 
/*     */   
/*     */   private double getRumo(CoordenadaCartesiana p0, CoordenadaCartesiana p1) {
/* 120 */     double rumo = 90.0D - Math.toDegrees(Math.atan2(p1.getY() - p0.getY(), p1.getX() - p0.getX()));
/* 121 */     if (rumo < 0.0D) {
/* 122 */       rumo += 360.0D;
/*     */     }
/* 124 */     return rumo;
/*     */   }
/*     */   
/*     */   private double getVelocidade(CoordenadaCartesiana p0, CoordenadaCartesiana p1) {
/* 128 */     int tamanhoVetorVeloc = getCanvasEspacial().getFiltroObjetoTatico().getTamanhoVetorVeloc();
/* 129 */     return CoordenadaCartesiana.calcularDistancia(p0, p1) / tamanhoVetorVeloc / 60.0D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void desenha() {
/* 134 */     if (this.p1 != null && this.p2 != null) {
/* 135 */       CanvasCenarioTatico<ControladorCenarioTatico> canvasCT = getCanvasEspacial();
/* 136 */       canvasCT.getCamadaInfo().tornaTransparente();
/* 137 */       Graphics2D g2d = canvasCT.getCamadaInfo().getImagem().createGraphics();
/*     */       
/* 139 */       g2d.setStroke(new BasicStroke(3.0F));
/* 140 */       g2d.setColor(Color.yellow);
/* 141 */       CoordenadaRaster p1Raster = canvasCT.projeta(this.p1);
/* 142 */       CoordenadaRaster p2Raster = canvasCT.projeta(this.p2);
/* 143 */       g2d.drawRect(p1Raster.getX() - 10, p1Raster.getY() - 10, 20, 20);
/* 144 */       UtilJava2D.desenhaTracejado(g2d, p1Raster.getX(), p1Raster.getY(), p2Raster.getX(), p2Raster.getY());
/* 145 */       String info1 = String.format("%3.0f°", new Object[] { Double.valueOf(getRumo(this.p1, this.p2)) });
/*     */       
/* 147 */       Font font = new Font("Arial", 1, 10);
/* 148 */       FontMetrics fm = g2d.getFontMetrics(font);
/* 149 */       g2d.setFont(font);
/* 150 */       String info2 = String.format("%-5.1f nós", new Object[] { Double.valueOf(getVelocidade(this.p1, this.p2)) });
/* 151 */       g2d.drawString(info1, p2Raster.getX() + 15, p2Raster.getY() - 2);
/*     */       
/* 153 */       g2d.dispose();
/* 154 */       canvasCT.getCamadaInfo().geraImagemFX();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void executarComando(Comando comando, CoordenadaCartesiana posicao) {
/* 161 */     if (isHabilitarComando(comando)) {
/*     */       CoordenadaGeografica posicaoAtual;
/* 163 */       switch (comando) {
/*     */         case ALOCAR_VEICULO:
/* 165 */           posicaoAtual = CoordenadaGeografica.converterCoordenadaCartesiana(posicao, 0.0D);
/* 166 */           ControladorVeiculoForm.setLatitude(posicaoAtual.getLatitude());
/* 167 */           ControladorVeiculoForm.setLongitude(posicaoAtual.getLongitude());
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
/*     */   
/*     */   public void habilitarComandos() {
/* 185 */     setHabilitarComando(Comando.ALOCAR_VEICULO, true);
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
/* 210 */     Map<Comando, Boolean> comandosHabilitados = new HashMap<>();
/*     */     
/* 212 */     comandosHabilitados.put(Comando.ALOCAR_VEICULO, Boolean.valueOf(isHabilitarComando(Comando.ALOCAR_VEICULO)));
/*     */     
/* 214 */     return comandosHabilitados;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaAlocarVeiculoEstrategia.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */