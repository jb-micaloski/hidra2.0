/*     */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaPolar;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.componentes.dominio.entidades.Veiculo;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.radar.IPL;
/*     */ import ipqm.gsd.componentes.nucleo.objeto_visual.ObjetoVisual;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*     */ import ipqm.gsd.hidra.ihm.objetos_visuais.objetos_graficos.ObjetoGraficoIPL;
/*     */ import javafx.application.Platform;
/*     */ import javafx.scene.control.TextField;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TarefaIPL<E extends CanvasCenarioTatico>
/*     */   extends Tarefa<E>
/*     */ {
/*     */   private IPL iplSelecionado;
/*     */   private final E canvas;
/*     */   private ObjetoGraficoIPL.Selecao selecao;
/*     */   private TextField iplMarcacao;
/*     */   private TextField iplDistancia;
/*     */   private TextField iplDirecao;
/*     */   
/*     */   public TarefaIPL(E canvas) {
/*  31 */     this.canvas = canvas;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void inicializa() {}
/*     */ 
/*     */   
/*     */   public void finaliza() {}
/*     */ 
/*     */   
/*     */   public void botao1Arrastado(CoordenadaCartesiana p, EstadoTeclado e) {
/*     */     CoordenadaPolar cpAtual, cpCentro;
/*     */     CoordenadaCartesiana ccCentro;
/*     */     CoordenadaPolar cpDirecao;
/*  46 */     if (this.iplSelecionado == null) {
/*     */       return;
/*     */     }
/*     */     
/*  50 */     CoordenadaCartesiana ccPPN = Veiculo.getVeiculoReferencial().getCondicaoAssociada().getCinematicaSimulada().getPosicao().getCoordenadaCartesiana();
/*     */     
/*  52 */     switch (this.selecao) {
/*     */       case CENTRO:
/*  54 */         cpAtual = CoordenadaPolar.converterCoordenadaCartesiana(ccPPN, p);
/*  55 */         this.iplSelecionado.setMarcacaoCentro(cpAtual.getMarcacao());
/*  56 */         this.iplSelecionado.setDistanciaCentro(cpAtual.getDistancia());
/*     */         
/*  58 */         Platform.runLater(() -> {
/*     */               this.iplMarcacao.setText(String.format("%03.0f", new Object[] { Double.valueOf(this.iplSelecionado.getMarcacaoCentro()) }));
/*     */               this.iplDistancia.setText(String.format("%03.2f", new Object[] { Double.valueOf(this.iplSelecionado.getDistanciaCentro()) }));
/*     */             });
/*     */         break;
/*     */       
/*     */       case BORDA:
/*  65 */         cpCentro = new CoordenadaPolar(this.iplSelecionado.getMarcacaoCentro(), this.iplSelecionado.getDistanciaCentro());
/*  66 */         ccCentro = CoordenadaCartesiana.converterCoordenadaPolar(ccPPN, cpCentro);
/*     */         
/*  68 */         cpDirecao = CoordenadaPolar.converterCoordenadaCartesiana(ccCentro, p);
/*  69 */         this.iplSelecionado.setDirecao((int)cpDirecao.getMarcacao());
/*  70 */         Platform.runLater(() -> {
/*     */               this.iplMarcacao.setText(String.format("%03.0f", new Object[] { Double.valueOf(this.iplSelecionado.getMarcacaoCentro()) }));
/*     */               this.iplDistancia.setText(String.format("%03.2f", new Object[] { Double.valueOf(this.iplSelecionado.getDistanciaCentro()) }));
/*     */               if (this.iplDirecao != null) {
/*     */                 this.iplDirecao.setText(String.format("%03d", new Object[] { Integer.valueOf((int)this.iplSelecionado.getDirecao()) }));
/*     */               }
/*     */             });
/*     */         break;
/*     */     } 
/*     */     
/*  80 */     this.canvas.atualizarCamadaTatica();
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  85 */     this.selecao = testaSelecao(this.canvas.projeta(p), 16.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Solto(CoordenadaCartesiana p, EstadoTeclado e) {}
/*     */ 
/*     */   
/*     */   public ObjetoGraficoIPL.Selecao testaSelecao(CoordenadaRaster coordenada, double tolerancia) {
/*  93 */     if (this.iplSelecionado == null) {
/*  94 */       return ObjetoGraficoIPL.Selecao.NAO;
/*     */     }
/*  96 */     ObjetoGraficoIPL grafico = (ObjetoGraficoIPL)this.iplSelecionado.getObjetosVisuais().get(ObjetoVisual.Tipo.GRAFICO);
/*  97 */     return grafico.testaSelecao(coordenada, tolerancia);
/*     */   }
/*     */   
/*     */   public void setIplSelecionado(IPL iplSelecionado, TextField iplMarcacao, TextField iplDistancia, TextField iplDirecao) {
/* 101 */     this.iplSelecionado = iplSelecionado;
/* 102 */     this.iplMarcacao = iplMarcacao;
/* 103 */     this.iplDistancia = iplDistancia;
/* 104 */     this.iplDirecao = iplDirecao;
/*     */   }
/*     */   
/*     */   public double calculaDistanciaLinha(CoordenadaPolar cpLinha, CoordenadaPolar cp) {
/* 108 */     double valor = 0.0D;
/* 109 */     return valor;
/*     */   }
/*     */   
/*     */   public void cancelarTarefa() {}
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaIPL.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */