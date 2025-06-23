/*     */ package ipqm.gsd.hidra.ihm.controle;
/*     */ 
/*     */ import com.sun.javafx.perf.PerformanceTracker;
/*     */ import ipqm.gsd.componentes.nucleo.IHM;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.ThreadPool;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.logon.Funcionalidade;
/*     */ import ipqm.gsd.componentes.nucleo.notificador.Notificador;
/*     */ import ipqm.gsd.componentes.nucleo.util.depuracao.Debug;
/*     */ import ipqm.gsd.hidra.ihm.projetos.geral.cenas.controladores.ControladorPrincipal;
/*     */ import ipqm.gsd.hidra.ihm.visao.Cena;
/*     */ import ipqm.gsd.hidra.ihm.widgets.Widget;
/*     */ import ipqm.gsd.hidra.ihm.widgets.WidgetMinimizavel;
/*     */ import ipqm.gsd.hidra.ihm.widgets.ajuda_contexto.WidgetAjuda;
/*     */ import ipqm.gsd.hidra.ihm.widgets.menu_desligar.WidgetMenuDesligar;
/*     */ import ipqm.gsd.hidra.ihm.widgets.painel_permanente.WidgetPainelPermanente;
/*     */ import ipqm.gsd.hidra.ihm.widgets.pesquisa.WidgetPesquisa;
/*     */ import ipqm.gsd.hidra.ihm.widgets.system_tray.WidgetSystemTray;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ import java.util.concurrent.ScheduledFuture;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import javafx.application.Platform;
/*     */ import javafx.scene.Cursor;
/*     */ import javafx.scene.image.Image;
/*     */ import javafx.scene.input.KeyCode;
/*     */ import javafx.scene.input.KeyEvent;
/*     */ import javafx.stage.Screen;
/*     */ import javafx.stage.Stage;
/*     */ import javafx.stage.StageStyle;
/*     */ import javafx.stage.WindowEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Tela
/*     */   implements Runnable
/*     */ {
/*     */   private PerformanceTracker tracker;
/*     */   private final Stage stage;
/*     */   private Cena cenaAtual;
/*     */   private String titulo;
/*  54 */   private final Map<String, Cena> cenas = new HashMap<>();
/*  55 */   private final Map<Integer, Cena> cenasDestaque = new HashMap<>();
/*  56 */   private final Map<Widget.Tipo, Widget> widgets = new HashMap<>();
/*     */ 
/*     */ 
/*     */   
/*     */   private final int numTela;
/*     */ 
/*     */ 
/*     */   
/*     */   private ScheduledFuture scheduledFuture;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tela(Stage stage, Screen screen, int numTela) {
/*  70 */     this.stage = stage;
/*  71 */     this.numTela = numTela;
/*  72 */     construir(screen);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tela(Screen screen, int numTela) {
/*  82 */     this(new Stage(), screen, numTela);
/*     */   }
/*     */ 
/*     */   
/*     */   private void construir(Screen screen) {
/*  87 */     double x = screen.getBounds().getMinX();
/*  88 */     double y = screen.getBounds().getMinY();
/*  89 */     double w = 1920.0D;
/*  90 */     double h = 1080.0D;
/*     */     
/*  92 */     this.stage.setX(x);
/*  93 */     this.stage.setY(y);
/*  94 */     this.stage.setWidth(w);
/*  95 */     this.stage.setHeight(h);
/*     */     
/*  97 */     this.stage.initStyle(StageStyle.TRANSPARENT);
/*  98 */     this.stage.setResizable(false);
/*  99 */     this.stage.getIcons().add(new Image("ipqm/gsd/hidra/ihm/imagens/iconeHIDRA.png"));
/* 100 */     this.stage.setFullScreen(true);
/*     */     
/* 102 */     Log.gravarLogInstrucao("Tela " + (this.numTela + 1) + " construida. Posição (x,y): " + x + "," + y + "; Largura: " + w + "; Altura: " + h, this);
/*     */     
/* 104 */     this.stage.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
/*     */           if (event.getCode() == KeyCode.F4 && event.isAltDown()) {
/*     */             event.consume();
/*     */           } else {
/*     */             teclaPressionada(event);
/*     */           } 
/*     */         });
/*     */     
/* 112 */     this.stage.addEventFilter(KeyEvent.KEY_RELEASED, event -> teclaSolta(event));
/*     */ 
/*     */ 
/*     */     
/* 116 */     this.stage.setOnCloseRequest(event -> {
/*     */           exibeOcultaMenuDesligar(Aplicacao.getInstancia().getCenaAtual());
/*     */           event.consume();
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Widget getWidget(Widget.Tipo tipo) {
/* 129 */     if (this.widgets.containsKey(tipo)) {
/* 130 */       return this.widgets.get(tipo);
/*     */     }
/* 132 */     return null;
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
/*     */   public void exibirWidgetsPermanentes(Cena cena) {
/* 144 */     Map<Widget.Tipo, Widget> treeMap = new TreeMap<>(this.widgets);
/*     */     
/* 146 */     Iterator<Widget> iterator = treeMap.values().iterator();
/* 147 */     while (iterator.hasNext()) {
/* 148 */       Widget w = iterator.next();
/* 149 */       if (w.isSempreVisivel()) {
/* 150 */         w.exibir(cena);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ocultaTodosWidgets(Cena cena) {
/* 161 */     Iterator<Widget> iterator = this.widgets.values().iterator();
/* 162 */     while (iterator.hasNext()) {
/* 163 */       Widget w = iterator.next();
/* 164 */       if (w.isVisivel()) {
/* 165 */         w.ocultar(cena);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTelaPrincipal() {
/* 176 */     return (this.numTela == 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void teclaSolta(KeyEvent event) {
/* 186 */     if (Debug.isDebug() && event.isControlDown() && event.isShiftDown() && event.getCode() == KeyCode.PAUSE) {
/* 187 */       if (this.scheduledFuture == null || this.scheduledFuture.isDone()) {
/* 188 */         iniciaAnimacao(1.0D);
/* 189 */         Notificador.informacao("PAUSE", "Animação restaurada");
/*     */       } else {
/* 191 */         this.scheduledFuture.cancel(false);
/* 192 */         Notificador.informacao("PAUSE", "Animação interrompida");
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 197 */     Map<Widget.Tipo, Widget> treeMap = new TreeMap<>(this.widgets);
/*     */ 
/*     */     
/* 200 */     Iterator<Widget> iterator = treeMap.values().iterator();
/* 201 */     while (iterator.hasNext()) {
/* 202 */       Widget w = iterator.next();
/* 203 */       if (!event.isConsumed()) {
/* 204 */         w.teclaSolta(event);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 209 */     if (getCenaAtual() != null && 
/* 210 */       !event.isConsumed()) {
/* 211 */       getCenaAtual().getControlador().teclaSolta(event);
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
/*     */   public void teclaPressionada(KeyEvent event) {
/* 224 */     Map<Widget.Tipo, Widget> treeMap = new TreeMap<>(this.widgets);
/*     */ 
/*     */     
/* 227 */     Iterator<Widget> iterator = treeMap.values().iterator();
/* 228 */     while (iterator.hasNext()) {
/* 229 */       Widget w = iterator.next();
/* 230 */       if (!event.isConsumed()) {
/* 231 */         w.teclaPressionada(event);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 236 */     if (getCenaAtual() != null && 
/* 237 */       !event.isConsumed()) {
/* 238 */       getCenaAtual().getControlador().teclaPressionada(event);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Cena getCenaAtual() {
/* 244 */     return this.cenaAtual;
/*     */   }
/*     */   
/*     */   public void setTitulo(String titulo) {
/* 248 */     this.titulo = titulo;
/*     */   }
/*     */   
/*     */   public Stage getStage() {
/* 252 */     return this.stage;
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
/*     */   public boolean trocar(Cena cena) {
/* 264 */     if (cena == null) {
/* 265 */       return false;
/*     */     }
/*     */     
/* 268 */     if (this.cenaAtual != null && !this.cenaAtual.getControlador().verificaCondicoesOcultacao(cena)) {
/* 269 */       return false;
/*     */     }
/*     */     
/* 272 */     if (!cena.getControlador().verificaCondicoesExibicao(cena)) {
/* 273 */       return false;
/*     */     }
/*     */     
/* 276 */     Platform.runLater(() -> {
/*     */           if (!this.stage.isShowing()) {
/*     */             this.stage.show();
/*     */           }
/*     */           
/*     */           if (this.cenaAtual != null) {
/*     */             if (this.cenaAtual.getFxml().equals(cena.getFxml())) {
/*     */               return;
/*     */             }
/*     */             
/*     */             this.cenaAtual.getControlador().ocultarIHM();
/*     */             
/*     */             this.cenaAtual.getControlador().getTela().ocultaTodosWidgets(this.cenaAtual);
/*     */           } 
/*     */           
/*     */           this.stage.setScene(cena.getScene());
/*     */           
/*     */           cena.getControlador().exibirIHM();
/*     */           cena.getControlador().getTela().exibirWidgetsPermanentes(cena);
/*     */           cena.getScene().setCursor(Cursor.DEFAULT);
/*     */           this.cenaAtual = cena;
/*     */           this.stage.setTitle(toString());
/*     */           if (Debug.isDebug()) {
/*     */             this.tracker = PerformanceTracker.getSceneTracker(cena.getScene());
/*     */           }
/*     */         });
/* 302 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean trocar(String cenaNome) {
/* 313 */     Cena cena = getCena(cenaNome);
/* 314 */     return trocar(cena);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean trocar(int posicao) {
/* 325 */     if (getCenasDestaque().containsKey(Integer.valueOf(posicao))) {
/* 326 */       return trocar(getCenasDestaque().get(Integer.valueOf(posicao)));
/*     */     }
/* 328 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFPS() {
/* 337 */     if (this.tracker == null) {
/* 338 */       return 0.0F;
/*     */     }
/* 340 */     float fps = this.tracker.getAverageFPS();
/* 341 */     this.tracker.resetAverageFPS();
/* 342 */     return fps;
/*     */   }
/*     */   
/*     */   public boolean isVisivel() {
/* 346 */     return this.stage.isShowing();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Cena getCena(String fxml) {
/* 356 */     return this.cenas.get(fxml);
/*     */   }
/*     */   
/*     */   public Map<String, Cena> getCenas() {
/* 360 */     return this.cenas;
/*     */   }
/*     */ 
/*     */   
/*     */   public void construir(List<Funcionalidade> listaFuncionalidades, List<String> listaWidgets, List<String> itensPainelPermanente, IHM ihm) {
/* 365 */     construirWidgets(listaWidgets);
/*     */ 
/*     */     
/* 368 */     WidgetPainelPermanente painelPermanente = (WidgetPainelPermanente)getWidget(Widget.Tipo.PAINEL_PERMANENTE);
/* 369 */     if (painelPermanente != null) {
/* 370 */       painelPermanente.fabricar(itensPainelPermanente);
/*     */     }
/*     */ 
/*     */     
/* 374 */     Iterator<Funcionalidade> iteratorListaFuncionalidade = listaFuncionalidades.iterator();
/* 375 */     while (iteratorListaFuncionalidade.hasNext()) {
/* 376 */       Funcionalidade funcionalidade = iteratorListaFuncionalidade.next();
/* 377 */       String nomeTela = "";
/* 378 */       if (this.numTela > 0) {
/* 379 */         nomeTela = " [Tela " + (this.numTela + 1) + "]";
/*     */       }
/* 381 */       ihm.notificarCarregamento(0, "Carregando funcionalidade: " + funcionalidade.getNome() + nomeTela);
/* 382 */       Cena cena = fabricarCena(funcionalidade, ihm);
/* 383 */       Log.gravarLogInstrucao("Criando cena: " + funcionalidade.getNome() + " (" + funcionalidade.getCaminhoProjeto() + ")" + nomeTela, this);
/*     */ 
/*     */       
/* 386 */       if (funcionalidade.getPosicao() > 0 && funcionalidade.getPosicao() <= 9) {
/* 387 */         addCenaDestaque(funcionalidade.getPosicao(), cena);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void construirWidgets(List<String> listaWidgets) {
/* 396 */     listaWidgets.stream().forEach(item -> {
/*     */           try {
/*     */             Class<?> c = Class.forName("ipqm.gsd.hidra.ihm.widgets." + item);
/*     */             
/*     */             Constructor<?> cons = c.getConstructor(new Class[] { Tela.class });
/*     */             
/*     */             Widget widget = (Widget)cons.newInstance(new Object[] { this });
/*     */             
/*     */             boolean construir = false;
/*     */             
/*     */             if (widget.isExibirApenasTelaPrincipal()) {
/*     */               if (isTelaPrincipal()) {
/*     */                 construir = true;
/*     */               }
/*     */             } else {
/*     */               construir = true;
/*     */             } 
/*     */             
/*     */             if (this.widgets.containsKey(widget.getTipo())) {
/*     */               construir = false;
/*     */             }
/*     */             
/*     */             if (construir) {
/*     */               try {
/*     */                 widget.construir();
/*     */                 this.widgets.put(widget.getTipo(), widget);
/*     */                 Log.gravarLogInstrucao("Widget adicionado a tela " + (this.numTela + 1), widget);
/*     */                 if (widget instanceof WidgetMinimizavel) {
/*     */                   WidgetSystemTray widgetSystemTray = (WidgetSystemTray)getWidget(Widget.Tipo.SYSTEM_TRAY);
/*     */                   if (widgetSystemTray != null) {
/*     */                     widgetSystemTray.addWidget((WidgetMinimizavel)widget);
/*     */                   }
/*     */                 } 
/* 429 */               } catch (Exception ex) {
/*     */                 Log.gravarLogExcecao("Erro ao construir o widget " + item, this, ex);
/*     */               } 
/*     */             }
/* 433 */           } catch (ClassNotFoundException|InstantiationException|IllegalAccessException|NoSuchMethodException|SecurityException|IllegalArgumentException|java.lang.reflect.InvocationTargetException ex) {
/*     */             Log.gravarLogExcecao("Erro ao construir um widget", this, ex);
/*     */           } 
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean removeWidget(Widget.Tipo tipo) {
/* 447 */     if (this.widgets.containsKey(tipo)) {
/* 448 */       Widget w = this.widgets.remove(tipo);
/* 449 */       Log.gravarLogInstrucao("Widget " + w + " removido da tela " + getNumTela(), this);
/* 450 */       return true;
/*     */     } 
/* 452 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Widget.Tipo, Widget> getWidgets() {
/* 462 */     return this.widgets;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addCenaDestaque(int posicao, Cena cena) {
/* 472 */     Log.gravarLogInstrucao("Adicionando cena em destaque: " + cena.getNome() + ", posição: " + posicao + ", tela: " + getNumTela(), this);
/* 473 */     this.cenasDestaque.put(Integer.valueOf(posicao), cena);
/* 474 */     ControladorPrincipal controladorPrincipal = (ControladorPrincipal)getCena("projetos/geral/cenas/TelaPrincipal.fxml").getControlador();
/* 475 */     Platform.runLater(() -> controladorPrincipal.addCenaDestaque(posicao, cena));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void aplicarCss() {
/* 481 */     Iterator<Cena> itCena = getCenas().values().iterator();
/* 482 */     while (itCena.hasNext()) {
/* 483 */       Cena c = itCena.next();
/* 484 */       c.getControlador().aplicarCss();
/*     */     } 
/*     */   }
/*     */   
/*     */   public Map<Integer, Cena> getCenasDestaque() {
/* 489 */     return this.cenasDestaque;
/*     */   }
/*     */   
/*     */   public Cena fabricarCena(Funcionalidade funcionalidade, IHM ihm) {
/* 493 */     Cena cena = new Cena(funcionalidade.getNome(), funcionalidade.getCaminhoProjeto(), ihm, this);
/* 494 */     cena.setDescricao(funcionalidade.getDescricao());
/* 495 */     cena.setImagem(funcionalidade.getImagemFunc());
/* 496 */     getCenas().put(funcionalidade.getCaminhoProjeto(), cena);
/* 497 */     return cena;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void exibeOcultaMenuDesligar(Cena cena) {
/* 506 */     WidgetMenuDesligar menuDesligar = (WidgetMenuDesligar)Aplicacao.getInstancia().getTelaPrincipal().getWidget(Widget.Tipo.MENU_DESLIGAR);
/* 507 */     if (menuDesligar != null) {
/* 508 */       if (menuDesligar.isVisivel()) {
/* 509 */         menuDesligar.ocultar(cena);
/*     */       } else {
/* 511 */         menuDesligar.exibir(cena);
/*     */       } 
/*     */     } else {
/* 514 */       Notificador.informacao("Funcionalidade não disponível", "Você não possui permissão para acessar o menu de desligar");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void exibeOcultaAjuda(Cena cena) {
/* 524 */     WidgetAjuda ajuda = (WidgetAjuda)getWidget(Widget.Tipo.AJUDA);
/* 525 */     if (ajuda != null) {
/* 526 */       if (ajuda.isVisivel()) {
/* 527 */         ajuda.ocultar(cena);
/*     */       } else {
/* 529 */         ajuda.exibir(cena);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void exibeOcultaPesquisa() {
/* 540 */     WidgetPesquisa pesquisa = (WidgetPesquisa)getWidget(Widget.Tipo.PESQUISA);
/* 541 */     if (pesquisa != null) {
/* 542 */       if (pesquisa.isVisivel()) {
/* 543 */         pesquisa.ocultar(getCenaAtual());
/*     */       } else {
/* 545 */         pesquisa.exibir(getCenaAtual());
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public int getNumTela() {
/* 551 */     return this.numTela;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 556 */     String tituloAtual = this.titulo + " - " + ((getCenaAtual() != null) ? getCenaAtual().getNome() : "");
/*     */     
/* 558 */     if (Aplicacao.getInstancia().getTelas().size() > 1) {
/* 559 */       tituloAtual = tituloAtual + " [" + (this.numTela + 1) + "]";
/*     */     }
/* 561 */     return tituloAtual;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void iniciaAnimacao(double fps) {
/* 570 */     this.scheduledFuture = ThreadPool.agendarExecucaoPeriodica(this, 1L, TimeUnit.SECONDS, "Animador da tela " + (getNumTela() + 1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/* 577 */     if (getCenaAtual() != null && getCenaAtual().getControlador() != null) {
/*     */       try {
/* 579 */         if (Mediador.isSistemaIniciado() && Mediador.getInstancia().getGestor().getGestorObjetos() != null) {
/* 580 */           getCenaAtual().getControlador().desenharObjetos(Collections.unmodifiableList(Mediador.getInstancia().getGestor().getGestorObjetos().getObjetos()));
/*     */         } else {
/* 582 */           getCenaAtual().getControlador().desenharObjetos(null);
/*     */         } 
/* 584 */       } catch (Exception e) {
/* 585 */         Log.gravarLogExcecao("Erro ao executar a animação da cena atual: " + getCenaAtual(), this, e);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 590 */     if (this.widgets != null) {
/* 591 */       Iterator<Widget> iterator = Collections.<Widget>unmodifiableList(new ArrayList<>(this.widgets.values())).iterator();
/* 592 */       while (iterator.hasNext()) {
/* 593 */         Widget w = iterator.next();
/* 594 */         if (w.isVisivel())
/*     */           try {
/* 596 */             if (Mediador.isSistemaIniciado() && Mediador.getInstancia().getGestor().getGestorObjetos() != null) {
/* 597 */               w.desenharObjetos(Collections.unmodifiableList(Mediador.getInstancia().getGestor().getGestorObjetos().getObjetos())); continue;
/*     */             } 
/* 599 */             w.desenharObjetos(null);
/*     */           }
/* 601 */           catch (Exception e) {
/* 602 */             Log.gravarLogExcecao("Erro ao executar animação do widget " + w.getTipo().toString(), this, e);
/*     */           }  
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/controle/Tela.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */