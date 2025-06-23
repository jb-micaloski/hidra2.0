/*     */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.componentes.dominio.correcao_manual.Symbols;
/*     */ import ipqm.gsd.componentes.dominio.entidades.Veiculo;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javafx.scene.Cursor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TarefaCriaSymbols
/*     */   extends Tarefa1P<CanvasCenarioTatico>
/*     */ {
/*     */   private Cursor cursor;
/*     */   private Symbols simbolo;
/*     */   
/*     */   public TarefaCriaSymbols(CanvasCenarioTatico canvas) {
/*  28 */     super(canvas);
/*     */   }
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p) {
/*     */     try {
/*  34 */       CoordenadaGeografica cg = CoordenadaGeografica.converterCoordenadaCartesiana(p, 0.0D);
/*  35 */       this.simbolo = Symbols.fabricar(getCanvasEspacial().getCamadaCarta().getSimboloAtual(), cg.getLatitude(), cg.getLongitude(), Veiculo.getVeiculoReferencial().getPosicao());
/*  36 */       dataCriacao();
/*  37 */       this.simbolo.salvarBDMemoria(true);
/*  38 */       getCanvasEspacial().atualizarCamadaTatica();
/*  39 */     } catch (Exception e) {
/*  40 */       Log.gravarLogExcecao("Erro ao criar simbolo na carta", this, e);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void dataCriacao() throws ParseException {
/*  45 */     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY-HH:mm");
/*  46 */     String st = sdf.format(Long.valueOf(Mediador.getRelogio().getHorario()));
/*  47 */     stringToMilliseconds(st);
/*  48 */     this.simbolo.setDataCriacao(stringToMilliseconds(st));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String millisecondsToString(long dataHora) {
/*  59 */     if (dataHora == 0L) {
/*  60 */       return null;
/*     */     }
/*  62 */     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy-hh:mm");
/*  63 */     Date date = new Date(dataHora);
/*  64 */     return sdf.format(Long.valueOf(date.getTime()));
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
/*     */   public long stringToMilliseconds(String dataHora) throws ParseException {
/*  77 */     if (dataHora == null || dataHora.equals("")) {
/*  78 */       return 0L;
/*     */     }
/*  80 */     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy-hh:mm");
/*  81 */     String dateInString = dataHora;
/*  82 */     Date date = sdf.parse(dateInString);
/*     */     
/*  84 */     return date.getTime();
/*     */   }
/*     */ 
/*     */   
/*     */   public void inicializa() {
/*  89 */     this.cursor = getCanvasEspacial().getCursor();
/*  90 */     getCanvasEspacial().setCursor(Cursor.CROSSHAIR);
/*     */   }
/*     */ 
/*     */   
/*     */   public void finaliza() {
/*  95 */     getCanvasEspacial().setCursor(this.cursor);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/* 100 */     super.botao1Pressionado(p, e);
/* 101 */     getCanvasEspacial().setRedesenhoAutomatico(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/* 106 */     super.botao1Solto(p, e);
/* 107 */     getCanvasEspacial().setRedesenhoAutomatico(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void habilitarComandos() {
/* 112 */     setHabilitarComando(Comando.CRIA_SIMBOLOS, true);
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
/* 123 */     Map<Comando, Boolean> comandosHabilitados = new HashMap<>();
/*     */     
/* 125 */     comandosHabilitados.put(Comando.CRIA_SIMBOLOS, Boolean.valueOf(isHabilitarComando(Comando.CRIA_SIMBOLOS)));
/*     */     
/* 127 */     return comandosHabilitados;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaCriaSymbols.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */