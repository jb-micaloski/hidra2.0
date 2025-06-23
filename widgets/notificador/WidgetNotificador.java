/*     */ package ipqm.gsd.hidra.ihm.widgets.notificador;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*     */ import ipqm.gsd.componentes.nucleo.ThreadPool;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.notificador.INotificar;
/*     */ import ipqm.gsd.componentes.nucleo.notificador.Notificacao;
/*     */ import ipqm.gsd.componentes.nucleo.notificador.Notificador;
/*     */ import ipqm.gsd.hidra.ihm.controle.Tela;
/*     */ import ipqm.gsd.hidra.ihm.util.Tocador;
/*     */ import ipqm.gsd.hidra.ihm.visao.Cena;
/*     */ import ipqm.gsd.hidra.ihm.widgets.Widget;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import javafx.event.Event;
/*     */ import javafx.event.EventHandler;
/*     */ import javafx.scene.input.KeyCode;
/*     */ import javafx.scene.input.KeyEvent;
/*     */ import javafx.scene.input.MouseEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WidgetNotificador
/*     */   extends Widget
/*     */   implements INotificar
/*     */ {
/*  29 */   private static int MAXIMO_NOTIFICACOES = 1;
/*     */   
/*     */   private Notificador notificador;
/*     */   private boolean visivel;
/*     */   private Cena cenaReferencia;
/*     */   private ArrayList<NotificacaoVisual> lista;
/*     */   private Tocador tocadorNotificacao;
/*     */   
/*     */   public WidgetNotificador(Tela tela) {
/*  38 */     super(Widget.Tipo.NOTIFICADOR, tela);
/*  39 */     setSempreVisivel(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void construir() {
/*  44 */     this.lista = new ArrayList<>();
/*  45 */     this.tocadorNotificacao = new Tocador("notificacoes" + File.separator + "notificacao.wav");
/*  46 */     this.notificador = Notificador.getInstancia();
/*  47 */     this.notificador.setiNotificar(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void exibir(Cena cena) {
/*  52 */     this.cenaReferencia = cena;
/*  53 */     this.visivel = true;
/*  54 */     this.lista.stream().forEach(n -> n.mostrar(cena));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ocultar(Cena cena) {
/*  61 */     this.cenaReferencia = null;
/*  62 */     this.visivel = false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisivel() {
/*  67 */     return this.visivel;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean exibirNotificacao(final Notificacao notificacao) {
/*  73 */     if (this.cenaReferencia == null || this.cenaReferencia.getControlador().getAnchorPanePai() == null) {
/*  74 */       return false;
/*     */     }
/*     */     
/*  77 */     MAXIMO_NOTIFICACOES = Notificador.getInstancia().getQuantidadeNotificacoesPermanentes() + 1;
/*     */     
/*  79 */     if (this.lista != null && this.lista.size() >= MAXIMO_NOTIFICACOES) {
/*  80 */       return false;
/*     */     }
/*     */     
/*  83 */     if (this.tocadorNotificacao != null) {
/*  84 */       this.tocadorNotificacao.reproduzir();
/*     */     }
/*     */     
/*  87 */     NotificacaoVisual n = new NotificacaoVisual(notificacao);
/*     */     
/*  89 */     this.lista.add(0, n);
/*  90 */     ordenar();
/*  91 */     n.exibir(this.cenaReferencia, 0);
/*     */     
/*  93 */     if (notificacao.isExibirBotaoFechar() && n.getAnchorPane() != null) {
/*  94 */       n.getAnchorPane().addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>()
/*     */           {
/*     */             
/*     */             public void handle(MouseEvent mouseEvent)
/*     */             {
/*  99 */               Log.gravarLogInterface("Notificação removida pelo usuário: " + notificacao.getTitulo(), notificacao);
/* 100 */               WidgetNotificador.this.ocultarNotificacao(notificacao);
/*     */             }
/*     */           });
/*     */     }
/*     */     
/* 105 */     if (!notificacao.isPermanente()) {
/* 106 */       ThreadPool.agendarExecucao(new RemoverNotificacao(n), n.getNotificacao().getDuracaoNotificacao(), TimeUnit.SECONDS, "Remover notificação");
/*     */     }
/*     */     
/* 109 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean ocultarNotificacao(Notificacao notificacao) {
/* 114 */     NotificacaoVisual notificacaoVisual = null;
/*     */     
/* 116 */     for (NotificacaoVisual nv : this.lista) {
/* 117 */       if (nv.getNotificacao() == notificacao) {
/* 118 */         notificacaoVisual = nv;
/*     */       }
/*     */     } 
/*     */     
/* 122 */     if (notificacaoVisual != null) {
/* 123 */       notificacaoVisual.ocultar();
/* 124 */       this.lista.remove(notificacaoVisual);
/* 125 */       ordenar();
/* 126 */       return true;
/*     */     } 
/* 128 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void ordenar() {
/* 135 */     if (this.lista.isEmpty()) {
/*     */       return;
/*     */     }
/* 138 */     for (int i = 0; i < this.lista.size(); i++) {
/* 139 */       ((NotificacaoVisual)this.lista.get(i)).mover(i);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void desenharObjetos(List<ObjetoDOMINIO> listaObjetos) {
/* 145 */     if (this.lista != null) {
/* 146 */       this.lista.stream().forEach(n -> {
/*     */             if (n.getNotificacao().isPermanente()) {
/*     */               n.atualiza();
/*     */             }
/*     */           });
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void teclaPressionada(KeyEvent event) {
/* 156 */     super.teclaPressionada(event);
/*     */     
/* 158 */     if (event.isControlDown() && event.isShiftDown() && event.getCode() == KeyCode.N) {
/* 159 */       limparNotificacoes(true);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void limparNotificacoes(boolean all) {
/* 165 */     if (all) {
/* 166 */       Notificador.getInstancia().limpaNotificacoes();
/*     */     }
/*     */     
/* 169 */     for (NotificacaoVisual nv : this.lista) {
/* 170 */       nv.ocultar();
/*     */     }
/*     */     
/* 173 */     this.lista.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   private class RemoverNotificacao
/*     */     implements Runnable
/*     */   {
/*     */     private final NotificacaoVisual notificacaoVisual;
/*     */ 
/*     */     
/*     */     public RemoverNotificacao(NotificacaoVisual notificacaoVisual) {
/* 184 */       this.notificacaoVisual = notificacaoVisual;
/*     */     }
/*     */ 
/*     */     
/*     */     public void run() {
/* 189 */       while (this.notificacaoVisual.isMouseOver()) {
/*     */         try {
/* 191 */           Thread.sleep(100L);
/* 192 */         } catch (InterruptedException ex) {
/* 193 */           Log.gravarLogExcecao("Erro na thread de notificação visual", this, ex);
/*     */         } 
/*     */       } 
/* 196 */       WidgetNotificador.this.ocultarNotificacao(this.notificacaoVisual.getNotificacao());
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 201 */       return this.notificacaoVisual.getNotificacao().getTitulo();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/notificador/WidgetNotificador.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */