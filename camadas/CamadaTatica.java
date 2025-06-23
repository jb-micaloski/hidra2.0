/*     */ package ipqm.gsd.hidra.ihm.camadas;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.ObjetoTatico;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.Janela;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.JanelaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.JanelaGeografica;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.objeto_visual.ObjetoVisual;
/*     */ import ipqm.gsd.hidra.ihm.camadas.filtros.FiltroObjetoTatico;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasEspacial;
/*     */ import ipqm.gsd.hidra.ihm.interacao.GrupoCamera;
/*     */ import ipqm.gsd.hidra.ihm.interacao.tarefa.TarefaSelecao;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CamadaTatica<F extends FiltroObjetoTatico>
/*     */   extends CamadaBI<F>
/*     */ {
/*     */   protected TarefaSelecao<? extends CanvasEspacial> tarefaSelecao;
/*     */   private GrupoCamera grupoCamera;
/*     */   protected double escalaMN;
/*     */   
/*     */   public CamadaTatica(F filtro, int largura, int altura) {
/*  30 */     super(filtro, largura, altura);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void desenha(Janela janela, List<ObjetoTatico> listaObjetos, TarefaSelecao<? extends CanvasEspacial> tarefaSelecao, float opacidade) {
/*  36 */     tornaTransparente();
/*     */     
/*  38 */     if (listaObjetos == null) {
/*     */       return;
/*     */     }
/*     */     
/*  42 */     this.tarefaSelecao = tarefaSelecao;
/*  43 */     JanelaGeografica janelaGeo = (JanelaGeografica)janela;
/*  44 */     setJanelaCartesiana(JanelaCartesiana.converterJanelaGeografica(janelaGeo, janelaGeo.getCentro().getLatitude()));
/*     */     
/*  46 */     Iterator<ObjetoTatico> iterator = listaObjetos.iterator();
/*     */     
/*  48 */     while (iterator.hasNext()) {
/*  49 */       ObjetoTatico objetoTatico = iterator.next();
/*     */       try {
/*  51 */         Mediador.getInstancia().getGestor().getGestorObjetos().desenharObjeto((ObjetoDOMINIO)objetoTatico, this);
/*  52 */       } catch (Exception e) {
/*  53 */         Log.gravarLogExcecao("Erro ao desenhar objetos táticos", this, e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int ordemMarcacao(ObjetoVisual objetoVisual) {
/*  59 */     if (this.tarefaSelecao == null) {
/*  60 */       return 0;
/*     */     }
/*     */     
/*  63 */     if (this.tarefaSelecao.getListaObjetosMarcados() == null) {
/*  64 */       return 0;
/*     */     }
/*     */     
/*  67 */     if (this.tarefaSelecao.getListaObjetosMarcados().contains(objetoVisual)) {
/*  68 */       if (this.tarefaSelecao.getListaObjetosMarcados().get(0) == objetoVisual) {
/*  69 */         return 3;
/*     */       }
/*  71 */       return 1;
/*     */     } 
/*     */     
/*  74 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public GrupoCamera getGrupoCamera() {
/*  79 */     return this.grupoCamera;
/*     */   }
/*     */   
/*     */   public void setGrupoCamera(GrupoCamera grupoCamera) {
/*  83 */     this.grupoCamera = grupoCamera;
/*     */   }
/*     */   
/*     */   public double getEscalaMN() {
/*  87 */     return this.escalaMN;
/*     */   }
/*     */   
/*     */   public void setEscalaMN(double escalaMN) {
/*  91 */     this.escalaMN = escalaMN;
/*     */   }
/*     */   
/*     */   public int getEscalaReal() {
/*  95 */     double H = this.escalaMN;
/*  96 */     double h = 1920.0D;
/*  97 */     int polegada = 0;
/*     */     
/*  99 */     if (polegada == 0) {
/* 100 */       polegada = 20;
/*     */     }
/*     */ 
/*     */     
/* 104 */     double PS_MM = 0.01153D * polegada;
/*     */     
/* 106 */     int escala = (int)(H * 1852.0D / PS_MM * h / 1000.0D + 0.5D);
/*     */     
/* 108 */     return escala;
/*     */   }
/*     */   public TarefaSelecao<? extends CanvasEspacial> getTarefaSelecao() {
/* 111 */     return this.tarefaSelecao;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/camadas/CamadaTatica.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */