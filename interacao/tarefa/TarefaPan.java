/*     */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.Posicao;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javafx.scene.Cursor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TarefaPan
/*     */   extends Tarefa2P<CanvasCenarioTatico>
/*     */ {
/*     */   private Cursor cursor;
/*     */   
/*     */   public TarefaPan(CanvasCenarioTatico canvasCT) {
/*  23 */     super(canvasCT);
/*     */   }
/*     */ 
/*     */   
/*     */   public void inicializa() {
/*  28 */     this.cursor = getCanvasEspacial().getCursor();
/*  29 */     getCanvasEspacial().setCursor(Cursor.HAND);
/*  30 */     getCanvasEspacial().show2DCanvas();
/*     */   }
/*     */ 
/*     */   
/*     */   public void finaliza() {
/*  35 */     getCanvasEspacial().setCursor(this.cursor);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Pressionado(CoordenadaCartesiana cart, EstadoTeclado e) {
/*  40 */     super.botao1Pressionado(cart, e);
/*  41 */     getCanvasEspacial().setRedesenhoAutomatico(false);
/*  42 */     getCanvasEspacial().setModoNavegacao(CanvasCenarioTatico.ModoNavegacao.LIVRE);
/*  43 */     getCanvasEspacial().setCursor(Cursor.MOVE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Arrastado(CoordenadaCartesiana cart, EstadoTeclado e) {
/*  48 */     super.botao1Arrastado(cart, e);
/*  49 */     CanvasCenarioTatico canvasCT = getCanvasEspacial();
/*  50 */     canvasCT.setEnquadramento(false);
/*  51 */     CoordenadaGeografica p1Geo = CoordenadaGeografica.converterCoordenadaCartesiana(this.p1, 0.0D);
/*  52 */     CoordenadaGeografica p2Geo = CoordenadaGeografica.converterCoordenadaCartesiana(this.p2, 0.0D);
/*  53 */     CoordenadaGeografica centro = canvasCT.getJanelaGeografica().getCentro();
/*  54 */     centro.setLongitude(centro.getLongitude() - p2Geo.getLongitude() + p1Geo.getLongitude());
/*  55 */     centro.setLatitude(centro.getLatitude() - p2Geo.getLatitude() + p1Geo.getLatitude());
/*  56 */     canvasCT.setCentroJanela(centro);
/*  57 */     canvasCT.atualizarCamadas();
/*  58 */     canvasCT.getCamadaInfo().tornaTransparente();
/*  59 */     canvasCT.getCamadaInfo().geraImagemFX();
/*  60 */     canvasCT.getCamadaRadarImage().clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Solto(CoordenadaCartesiana cart, EstadoTeclado e) {
/*  65 */     super.botao1Solto(cart, e);
/*  66 */     getCanvasEspacial().setCursor(Cursor.HAND);
/*  67 */     CanvasCenarioTatico canvasCT = getCanvasEspacial();
/*  68 */     canvasCT.atualizarCamadas();
/*  69 */     canvasCT.getCamadaRadarImage().clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void duploClique(CoordenadaCartesiana p, EstadoTeclado e) {
/*  74 */     CanvasCenarioTatico canvas = getCanvasEspacial();
/*  75 */     canvas.setCentroJanela(CoordenadaGeografica.converterCoordenadaCartesiana(p, 0.0D));
/*  76 */     canvas.atualizarCamadas();
/*     */   }
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p) {
/*  81 */     CanvasCenarioTatico canvas = getCanvasEspacial();
/*  82 */     canvas.setRedesenhoAutomatico(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p1, CoordenadaCartesiana p2) {
/*  87 */     CanvasCenarioTatico canvas = getCanvasEspacial();
/*  88 */     canvas.atualizarCamadas();
/*  89 */     canvas.setRedesenhoAutomatico(true);
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
/*     */   public void executarComando(Comando comando, CoordenadaCartesiana posicao) {
/* 110 */     if (isHabilitarComando(comando)) {
/*     */       CanvasCenarioTatico canvasCT;
/* 112 */       switch (comando) {
/*     */         
/*     */         case CENTRAR_MAPA:
/* 115 */           canvasCT = getCanvasEspacial();
/* 116 */           canvasCT.setModoNavegacao(CanvasCenarioTatico.ModoNavegacao.LIVRE);
/* 117 */           canvasCT.setEnquadramento(false);
/* 118 */           canvasCT.setCentroJanela(canvasCT.defineCentroJanela(new Posicao(CoordenadaGeografica.converterCoordenadaCartesiana(posicao, 0.0D), null, posicao)));
/* 119 */           canvasCT.atualizarCamadas();
/*     */           break;
/*     */         
/*     */         case IR_PARA:
/* 123 */           getCanvasEspacial().getControlador().exibirPainelFormulario("projetos/geral/formularios/IrPara.fxml", "Ir Para Uma Coordenada", null);
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
/* 141 */     setHabilitarComando(Comando.CENTRAR_MAPA, true);
/* 142 */     setHabilitarComando(Comando.IR_PARA, true);
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
/* 167 */     Map<Comando, Boolean> comandosHabilitados = new HashMap<>();
/*     */     
/* 169 */     comandosHabilitados.put(Comando.CENTRAR_MAPA, Boolean.valueOf(isHabilitarComando(Comando.CENTRAR_MAPA)));
/* 170 */     comandosHabilitados.put(Comando.IR_PARA, Boolean.valueOf(isHabilitarComando(Comando.IR_PARA)));
/*     */     
/* 172 */     return comandosHabilitados;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaPan.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */