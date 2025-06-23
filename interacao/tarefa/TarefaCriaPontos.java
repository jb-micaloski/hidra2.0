/*     */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.ObjetoTatico;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.componentes.dominio.entidades.Veiculo;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.PontoFixo;
/*     */ import ipqm.gsd.componentes.dominio.teatro_operacao.CondicaoInicial;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javafx.scene.Cursor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TarefaCriaPontos
/*     */   extends Tarefa1P<CanvasCenarioTatico>
/*     */ {
/*     */   private Cursor cursor;
/*     */   
/*     */   public TarefaCriaPontos(CanvasCenarioTatico canvas) {
/*  30 */     super(canvas);
/*     */   }
/*     */ 
/*     */   
/*     */   public void inicializa() {
/*  35 */     this.cursor = getCanvasEspacial().getCursor();
/*  36 */     getCanvasEspacial().setCursor(Cursor.CROSSHAIR);
/*     */   }
/*     */ 
/*     */   
/*     */   public void finaliza() {
/*  41 */     getCanvasEspacial().setCursor(this.cursor);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  46 */     super.botao1Pressionado(p, e);
/*  47 */     getCanvasEspacial().setRedesenhoAutomatico(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/*  52 */     super.botao1Solto(p, e);
/*  53 */     getCanvasEspacial().setRedesenhoAutomatico(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p) {
/*     */     try {
/*  61 */       CoordenadaGeografica cg = CoordenadaGeografica.converterCoordenadaCartesiana(p, 0.0D);
/*  62 */       PontoFixo pontoFixo = PontoFixo.fabricar(gerarNome(), cg.getLatitude(), cg.getLongitude(), Veiculo.getVeiculoReferencial().getPosicao());
/*     */       
/*  64 */       getCanvasEspacial().getControlador().exibirPainelFormulario("projetos/geral/formularios/PontoFixoForm.fxml", "Dados do Ponto Fixo", (ObjetoTatico)null);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*  71 */     catch (Exception e) {
/*  72 */       Log.gravarLogExcecao("Erro ao criar ponto", this, e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void habilitarComandos() {
/*  79 */     setHabilitarComando(Comando.CRIA_PONTO, true);
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
/*  90 */     Map<Comando, Boolean> comandosHabilitados = new HashMap<>();
/*     */     
/*  92 */     comandosHabilitados.put(Comando.CRIA_PONTO, Boolean.valueOf(isHabilitarComando(Comando.CRIA_PONTO)));
/*     */     
/*  94 */     return comandosHabilitados;
/*     */   }
/*     */ 
/*     */   
/*     */   private String gerarNome() {
/*  99 */     int cont = 1;
/* 100 */     String nomeTemp = "P" + String.valueOf(cont);
/*     */     
/* 102 */     List<PontoFixo> lista = new ArrayList<>();
/* 103 */     Set<String> listaNomes = new HashSet<>();
/*     */     
/*     */     try {
/* 106 */       lista.addAll(CondicaoInicial.getCondicaoInicialLocal().listarPontosFixosAssociados());
/* 107 */     } catch (Exception ex) {
/* 108 */       Log.gravarLogExcecao("Erro ao carregar pontos.", this, ex);
/*     */     } 
/*     */     
/* 111 */     for (PontoFixo item : lista) {
/* 112 */       listaNomes.add(item.getNome());
/*     */     }
/*     */     
/* 115 */     while (listaNomes.contains(nomeTemp)) {
/* 116 */       cont++;
/* 117 */       nomeTemp = "P" + String.valueOf(cont);
/*     */     } 
/*     */     
/* 120 */     return nomeTemp;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaCriaPontos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */