/*     */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*     */ import ipqm.gsd.hidra.ihm.projetos.geral.cenas.controladores.ControladorCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.projetos.geral.formularios.controladores.ControladorElementoNavegacaoForm;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javafx.scene.Cursor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TarefaCriaElementoNavegacao
/*     */   extends Tarefa2P<CanvasCenarioTatico<ControladorCenarioTatico>>
/*     */ {
/*     */   private Cursor cursor;
/*     */   
/*     */   public TarefaCriaElementoNavegacao(CanvasCenarioTatico<ControladorCenarioTatico> canvas) {
/*  23 */     super(canvas);
/*     */   }
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p) {
/*  28 */     criaElementoNavegacao(p, 0.0D, 0.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p1, CoordenadaCartesiana p2) {
/*  33 */     criaElementoNavegacao(p1, getRumo(p1, p2), getVelocidade(p1, p2));
/*     */   }
/*     */   
/*     */   private void criaElementoNavegacao(CoordenadaCartesiana posicao, double rumo, double velocidade) {
/*  37 */     CoordenadaGeografica cg = CoordenadaGeografica.converterCoordenadaCartesiana(posicao, 0.0D);
/*     */     
/*  39 */     ControladorElementoNavegacaoForm.setLatitude(cg.getLatitude());
/*  40 */     ControladorElementoNavegacaoForm.setLongitude(cg.getLongitude());
/*     */ 
/*     */     
/*  43 */     ControladorCenarioTatico controlador = (ControladorCenarioTatico)getCanvasEspacial().getControlador();
/*  44 */     controlador.exibirPainelFormulario("projetos/geral/formularios/ElementoNavegacaoForm.fxml", "Elemento Navegação", null);
/*     */     
/*  46 */     getCanvasEspacial().getCamadaInfo().tornaTransparente();
/*  47 */     getCanvasEspacial().getCamadaInfo().geraImagemFX();
/*     */   }
/*     */ 
/*     */   
/*     */   public void inicializa() {
/*  52 */     this.cursor = getCanvasEspacial().getCursor();
/*  53 */     getCanvasEspacial().setCursor(Cursor.CROSSHAIR);
/*     */   }
/*     */ 
/*     */   
/*     */   public void finaliza() {
/*  58 */     getCanvasEspacial().setCursor(this.cursor);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  63 */     super.botao1Pressionado(p, e);
/*  64 */     getCanvasEspacial().setRedesenhoAutomatico(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void botao1Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/*  70 */     super.botao1Solto(p, e);
/*  71 */     getCanvasEspacial().setRedesenhoAutomatico(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void executarComando(Comando comando, CoordenadaCartesiana posicao) {
/*  77 */     if (isHabilitarComando(comando)) {
/*     */       CoordenadaGeografica posicaoAtual;
/*  79 */       switch (comando) {
/*     */         case ELEMENTO_NAVEGACAO:
/*  81 */           posicaoAtual = CoordenadaGeografica.converterCoordenadaCartesiana(posicao, 0.0D);
/*  82 */           ControladorElementoNavegacaoForm.setLatitude(posicaoAtual.getLatitude());
/*  83 */           ControladorElementoNavegacaoForm.setLongitude(posicaoAtual.getLongitude());
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
/* 101 */     setHabilitarComando(Comando.ELEMENTO_NAVEGACAO, true);
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
/* 126 */     Map<Comando, Boolean> comandosHabilitados = new HashMap<>();
/*     */     
/* 128 */     comandosHabilitados.put(Comando.CRIA_VEICULO, Boolean.valueOf(isHabilitarComando(Comando.CRIA_VEICULO)));
/*     */     
/* 130 */     return comandosHabilitados;
/*     */   }
/*     */ 
/*     */   
/*     */   private double getRumo(CoordenadaCartesiana p0, CoordenadaCartesiana p1) {
/* 135 */     double rumo = 90.0D - Math.toDegrees(Math.atan2(p1.getY() - p0.getY(), p1.getX() - p0.getX()));
/* 136 */     if (rumo < 0.0D) {
/* 137 */       rumo += 360.0D;
/*     */     }
/* 139 */     return rumo;
/*     */   }
/*     */   
/*     */   private double getVelocidade(CoordenadaCartesiana p0, CoordenadaCartesiana p1) {
/* 143 */     int tamanhoVetorVeloc = getCanvasEspacial().getFiltroObjetoTatico().getTamanhoVetorVeloc();
/* 144 */     return CoordenadaCartesiana.calcularDistancia(p0, p1) / tamanhoVetorVeloc / 60.0D;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaCriaElementoNavegacao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */