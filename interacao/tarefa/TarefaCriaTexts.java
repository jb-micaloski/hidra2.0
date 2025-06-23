/*     */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.correcao_manual.Texts;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*     */ import ipqm.gsd.hidra.ihm.projetos.geral.cenas.controladores.ControladorCenarioTatico;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javafx.scene.Cursor;
/*     */ import javafx.scene.control.TextArea;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TarefaCriaTexts
/*     */   extends Tarefa1P<CanvasCenarioTatico>
/*     */ {
/*     */   private Cursor cursor;
/*     */   TextArea campo;
/*     */   private Texts texto;
/*     */   
/*     */   public TarefaCriaTexts(CanvasCenarioTatico canvas) {
/*  26 */     super(canvas);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p) {
/*     */     try {
/*  33 */       ((ControladorCenarioTatico)getCanvasEspacial().getControlador()).exibirPainelFormulario("projetos/geral/formularios/TextosForm.fxml", "Cria Textos", null);
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
/*     */     }
/*  57 */     catch (Exception e) {
/*  58 */       Log.gravarLogExcecao("Erro ao criar texto na carta", this, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void inicializa() {
/*  64 */     this.cursor = getCanvasEspacial().getCursor();
/*  65 */     getCanvasEspacial().setCursor(Cursor.CROSSHAIR);
/*     */   }
/*     */ 
/*     */   
/*     */   public void finaliza() {
/*  70 */     getCanvasEspacial().setCursor(this.cursor);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  75 */     super.botao1Pressionado(p, e);
/*  76 */     getCanvasEspacial().setRedesenhoAutomatico(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/*  81 */     super.botao1Solto(p, e);
/*  82 */     getCanvasEspacial().setRedesenhoAutomatico(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void habilitarComandos() {
/*  87 */     setHabilitarComando(Comando.CRIA_TEXTO, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void executarComando(Comando comando, CoordenadaCartesiana posicao) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Comando, Boolean> obterComandosHabilitados() {
/*  98 */     Map<Comando, Boolean> comandosHabilitados = new HashMap<>();
/*     */     
/* 100 */     comandosHabilitados.put(Comando.CRIA_TEXTO, Boolean.valueOf(isHabilitarComando(Comando.CRIA_TEXTO)));
/*     */     
/* 102 */     return comandosHabilitados;
/*     */   }
/*     */ 
/*     */   
/*     */   public TextArea getCampo() {
/* 107 */     return this.campo;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaCriaTexts.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */