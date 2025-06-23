/*     */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.componentes.dominio.constante_sstt.TipoPontoReferencia;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*     */ import ipqm.gsd.hidra.ihm.projetos.geral.formularios.controladores.ControladorPontoReferenciaForm;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javafx.scene.Cursor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TarefaCriaPontoReferencia
/*     */   extends Tarefa2P<CanvasCenarioTatico>
/*     */ {
/*     */   private Cursor cursor;
/*     */   private TipoPontoReferencia tipoPonto;
/*  24 */   private double velocidade = 0.0D;
/*     */   
/*     */   public TarefaCriaPontoReferencia(CanvasCenarioTatico canvas) {
/*  27 */     super(canvas);
/*     */   }
/*     */   
/*     */   public TarefaCriaPontoReferencia(CanvasCenarioTatico canvas, TipoPontoReferencia tipoPonto) {
/*  31 */     super(canvas);
/*  32 */     this.tipoPonto = tipoPonto;
/*     */   }
/*     */ 
/*     */   
/*     */   public void inicializa() {
/*  37 */     this.cursor = getCanvasEspacial().getCursor();
/*  38 */     getCanvasEspacial().setCursor(Cursor.CROSSHAIR);
/*     */   }
/*     */ 
/*     */   
/*     */   public void finaliza() {
/*  43 */     getCanvasEspacial().setCursor(this.cursor);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  48 */     super.botao1Pressionado(p, e);
/*  49 */     getCanvasEspacial().setRedesenhoAutomatico(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/*  54 */     super.botao1Solto(p, e);
/*  55 */     getCanvasEspacial().setRedesenhoAutomatico(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void habilitarComandos() {
/*  60 */     setHabilitarComando(Comando.CRIA_PONTOREFERENCIA, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<Comando, Boolean> obterComandosHabilitados() {
/*  65 */     Map<Comando, Boolean> comandosHabilitados = new HashMap<>();
/*     */     
/*  67 */     comandosHabilitados.put(Comando.CRIA_PONTOREFERENCIA, Boolean.valueOf(isHabilitarComando(Comando.CRIA_PONTOREFERENCIA)));
/*     */     
/*  69 */     return comandosHabilitados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TipoPontoReferencia getTipoPonto() {
/*  76 */     return this.tipoPonto;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTipoPonto(TipoPontoReferencia tipoPonto) {
/*  83 */     this.tipoPonto = tipoPonto;
/*     */   }
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p1, CoordenadaCartesiana p2) {
/*  88 */     int tamanhoVetorVeloc = getCanvasEspacial().getFiltroObjetoTatico().getTamanhoVetorVeloc();
/*  89 */     this.velocidade = CoordenadaCartesiana.calcularDistancia(p1, p2) / tamanhoVetorVeloc / 60.0D;
/*  90 */     criaObjeto(p1);
/*     */   }
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p) {
/*     */     try {
/*  96 */       String titulo = "";
/*  97 */       String fxml = "";
/*     */       
/*  99 */       switch (getTipoPonto()) {
/*     */         case MAGE:
/* 101 */           titulo = "Dados do Fixo MAGE";
/* 102 */           fxml = "projetos/geral/formularios/PontoReferenciaMageForm.fxml";
/*     */           break;
/*     */         case MANUAL:
/* 105 */           titulo = "Dados do Ponto Referência MANUAL";
/* 106 */           fxml = "projetos/geral/formularios/PontoReferenciaForm.fxml";
/*     */           break;
/*     */         case DATUM:
/* 109 */           titulo = "Dados do DATUM";
/* 110 */           fxml = "projetos/geral/formularios/PontoReferenciaDatumForm.fxml";
/*     */           break;
/*     */         case SPLASH:
/* 113 */           titulo = "Dados do SPLASH";
/* 114 */           fxml = "projetos/geral/formularios/PontoReferenciaSplashForm.fxml";
/*     */           break;
/*     */       } 
/*     */       
/* 118 */       CoordenadaGeografica coordGeo = CoordenadaGeografica.converterCoordenadaCartesiana(p, 0.0D);
/* 119 */       ControladorPontoReferenciaForm.setLatitude(coordGeo.getLatitude());
/* 120 */       ControladorPontoReferenciaForm.setLongitude(coordGeo.getLongitude());
/*     */       
/* 122 */       ControladorPontoReferenciaForm.setTipoPonto(this.tipoPonto);
/*     */       
/* 124 */       if (this.velocidade > 0.0D) {
/* 125 */         ControladorPontoReferenciaForm.setVelocidade(this.velocidade);
/*     */       }
/*     */       
/* 128 */       getCanvasEspacial().getControlador().exibirPainelFormulario(fxml, titulo, null);
/*     */       
/* 130 */       this.velocidade = 0.0D;
/* 131 */     } catch (Exception e) {
/* 132 */       Log.gravarLogExcecao("Erro ao criar ponto de referência", this, e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaCriaPontoReferencia.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */