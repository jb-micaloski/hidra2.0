/*    */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*    */ 
/*    */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*    */ import ipqm.gsd.componentes.nucleo.log.Log;
/*    */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*    */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*    */ import ipqm.gsd.hidra.ihm.projetos.geral.cenas.controladores.ControladorCenarioTatico;
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import javafx.scene.Cursor;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TarefaCriaProfundidades
/*    */   extends Tarefa1P<CanvasCenarioTatico>
/*    */ {
/*    */   private Cursor cursor;
/*    */   
/*    */   public TarefaCriaProfundidades(CanvasCenarioTatico canvas) {
/* 22 */     super(canvas);
/*    */   }
/*    */ 
/*    */   
/*    */   public void criaObjeto(CoordenadaCartesiana p) {
/*    */     try {
/* 28 */       ((ControladorCenarioTatico)getCanvasEspacial().getControlador()).exibirPainelFormulario("projetos/geral/formularios/ProfundidadesForm.fxml", "Cria Profundidades", null);
/*    */     }
/* 30 */     catch (Exception e) {
/* 31 */       Log.gravarLogExcecao("Erro ao criar profundidade na carta", this, e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void inicializa() {
/* 37 */     this.cursor = getCanvasEspacial().getCursor();
/* 38 */     getCanvasEspacial().setCursor(Cursor.CROSSHAIR);
/*    */   }
/*    */ 
/*    */   
/*    */   public void finaliza() {
/* 43 */     getCanvasEspacial().setCursor(this.cursor);
/*    */   }
/*    */ 
/*    */   
/*    */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/* 48 */     super.botao1Pressionado(p, e);
/* 49 */     getCanvasEspacial().setRedesenhoAutomatico(false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void botao1Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/* 54 */     super.botao1Solto(p, e);
/* 55 */     getCanvasEspacial().setRedesenhoAutomatico(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public void habilitarComandos() {
/* 60 */     setHabilitarComando(Comando.CRIA_PROFUNDIDADE, true);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void executarComando(Comando comando, CoordenadaCartesiana posicao) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public Map<Comando, Boolean> obterComandosHabilitados() {
/* 71 */     Map<Comando, Boolean> comandosHabilitados = new HashMap<>();
/*    */     
/* 73 */     comandosHabilitados.put(Comando.CRIA_PROFUNDIDADE, Boolean.valueOf(isHabilitarComando(Comando.CRIA_PROFUNDIDADE)));
/*    */     
/* 75 */     return comandosHabilitados;
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaCriaProfundidades.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */