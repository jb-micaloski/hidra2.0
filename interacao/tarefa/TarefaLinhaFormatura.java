/*     */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*     */ import ipqm.gsd.hidra.ihm.projetos.estrategia.cenas.controladores.ControladorCenarioEstrategia;
/*     */ import ipqm.gsd.hidra.ihm.projetos.geral.formularios.controladores.ControladorLinhaFormaturaForm;
/*     */ import ipqm.gsd.hidra.ihm.projetos.geral.formularios.controladores.ControladorVeiculoForm;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javafx.scene.Cursor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TarefaLinhaFormatura
/*     */   extends Tarefa1P<CanvasCenarioTatico<ControladorCenarioEstrategia>>
/*     */ {
/*     */   private Cursor cursor;
/*     */   
/*     */   public TarefaLinhaFormatura(CanvasCenarioTatico<ControladorCenarioEstrategia> canvas) {
/*  25 */     super(canvas);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void inicializa() {
/*  31 */     this.cursor = getCanvasEspacial().getCursor();
/*  32 */     getCanvasEspacial().setCursor(Cursor.CROSSHAIR);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void finaliza() {
/*  38 */     getCanvasEspacial().setCursor(this.cursor);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  43 */     super.botao1Pressionado(p, e);
/*  44 */     getCanvasEspacial().setRedesenhoAutomatico(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/*  49 */     super.botao1Solto(p, e);
/*  50 */     getCanvasEspacial().setRedesenhoAutomatico(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void desenha() {
/*  61 */     criaObjeto(this.p);
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
/*     */   public void criaObjeto(CoordenadaCartesiana p) {
/*  86 */     CoordenadaGeografica cg = CoordenadaGeografica.converterCoordenadaCartesiana(p, 0.0D);
/*  87 */     ControladorLinhaFormaturaForm.setLatitude(cg.getLatitude());
/*  88 */     ControladorLinhaFormaturaForm.setLongitude(cg.getLongitude());
/*     */     
/*  90 */     ControladorCenarioEstrategia controlador = (ControladorCenarioEstrategia)getCanvasEspacial().getControlador();
/*  91 */     controlador.exibirPainelFormulario("projetos/geral/formularios/LinhaFormaturaForm.fxml", "Linha de Formatura", null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void executarComando(Comando comando, CoordenadaCartesiana posicao) {
/*  97 */     if (isHabilitarComando(comando)) {
/*     */       CoordenadaGeografica posicaoAtual;
/*  99 */       switch (comando) {
/*     */         case LINHA_FORMATURA:
/* 101 */           posicaoAtual = CoordenadaGeografica.converterCoordenadaCartesiana(posicao, 0.0D);
/* 102 */           ControladorVeiculoForm.setLatitude(posicaoAtual.getLatitude());
/* 103 */           ControladorVeiculoForm.setLongitude(posicaoAtual.getLongitude());
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
/* 121 */     setHabilitarComando(Comando.LINHA_FORMATURA, true);
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
/* 146 */     Map<Comando, Boolean> comandosHabilitados = new HashMap<>();
/*     */     
/* 148 */     comandosHabilitados.put(Comando.LINHA_FORMATURA, Boolean.valueOf(isHabilitarComando(Comando.LINHA_FORMATURA)));
/*     */     
/* 150 */     return comandosHabilitados;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaLinhaFormatura.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */