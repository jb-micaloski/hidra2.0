/*     */ package ipqm.gsd.hidra.ihm.widgets.alarme;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*     */ import ipqm.gsd.componentes.nucleo.alarmes.Alarme;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.objeto_visual.ObjetoVisual;
/*     */ import ipqm.gsd.componentes.nucleo.util.GenericComparator;
/*     */ import ipqm.gsd.componentes.nucleo.util.TipoOrdenacao;
/*     */ import ipqm.gsd.hidra.ihm.controle.Tela;
/*     */ import ipqm.gsd.hidra.ihm.objetos_visuais.objetos_tabulares.ObjetoTabularAlarme;
/*     */ import ipqm.gsd.hidra.ihm.util.Tocador;
/*     */ import ipqm.gsd.hidra.ihm.util.UtilDesempenho;
/*     */ import ipqm.gsd.hidra.ihm.visao.Cena;
/*     */ import ipqm.gsd.hidra.ihm.widgets.Widget;
/*     */ import ipqm.gsd.hidra.ihm.widgets.WidgetMinimizavel;
/*     */ import ipqm.gsd.hidra.ihm.widgets.system_tray.IconeSystemTray;
/*     */ import java.awt.Color;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javafx.animation.TranslateTransition;
/*     */ import javafx.application.Platform;
/*     */ import javafx.collections.FXCollections;
/*     */ import javafx.collections.ObservableList;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.fxml.FXMLLoader;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.control.ScrollPane;
/*     */ import javafx.scene.control.TitledPane;
/*     */ import javafx.scene.input.KeyCode;
/*     */ import javafx.scene.input.KeyEvent;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ 
/*     */ 
/*     */ public class WidgetAlarme
/*     */   extends Widget
/*     */   implements WidgetMinimizavel
/*     */ {
/*     */   private ObservableList<ObjetoTabularAlarme> listaAlarmes;
/*     */   private AnchorPane root;
/*     */   private TitledPane titledPane;
/*     */   private ControladorAlarme controladorAlarme;
/*  45 */   private int contadorAlarmesAtivos = 0;
/*     */   
/*     */   private Alarme.Nivel estadoPisca;
/*     */   
/*     */   private boolean primeiraExibicao = true;
/*     */   private IconeSystemTray icone;
/*     */   private Tocador tocador;
/*     */   
/*     */   public WidgetAlarme(Tela tela) {
/*  54 */     super(Widget.Tipo.ALARME, tela);
/*  55 */     setSempreVisivel(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void construir() {
/*  60 */     this.listaAlarmes = FXCollections.observableArrayList();
/*  61 */     this.tocador = new Tocador("alarmes" + File.separator + "alarme.wav");
/*     */     
/*  63 */     Log.gravarLogInstrucao("Construindo widget alarme", this);
/*     */     
/*  65 */     FXMLLoader fxmlLoader = new FXMLLoader(Widget.class.getResource("alarme/Alarme.fxml"));
/*     */     
/*     */     try {
/*  68 */       this.root = (AnchorPane)fxmlLoader.load();
/*  69 */     } catch (IOException ex) {
/*  70 */       Log.gravarLogExcecao("Erro ao carregar FXML do widget", this, ex);
/*     */     } 
/*     */     
/*  73 */     this.controladorAlarme = (ControladorAlarme)fxmlLoader.getController();
/*     */     
/*  75 */     this.titledPane = new TitledPane();
/*  76 */     this.titledPane.setCollapsible(false);
/*  77 */     this.titledPane.setId("titledPaneWidget");
/*  78 */     this.titledPane.setLayoutX(1540.0D);
/*  79 */     this.titledPane.setLayoutY(780.0D);
/*  80 */     this.titledPane.setPrefWidth(380.0D);
/*  81 */     this.titledPane.setPrefHeight(245.0D);
/*  82 */     this.titledPane.setText("Alarmes");
/*     */     
/*  84 */     Platform.runLater(() -> {
/*     */           this.titledPane.getStylesheets().clear();
/*     */           
/*     */           this.titledPane.getStylesheets().setAll((Object[])new String[] { "ipqm/gsd/hidra/ihm/css/widgets/alarme.css", "ipqm/gsd/hidra/ihm/css/widgets/widgets.css" });
/*     */         });
/*  89 */     this.root.setId("painelAlarmes");
/*  90 */     ScrollPane scroll = new ScrollPane((Node)this.root);
/*  91 */     scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
/*  92 */     scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
/*  93 */     scroll.fitToHeightProperty();
/*  94 */     scroll.fitToWidthProperty();
/*     */     
/*  96 */     this.titledPane.setContent((Node)scroll);
/*  97 */     this.titledPane.setVisible(false);
/*     */     
/*  99 */     this.controladorAlarme.setWidgetAlarme(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void exibir(Cena cena) {
/* 104 */     if (cena.getControlador().getAnchorPanePai() != null) {
/* 105 */       cena.getControlador().getAnchorPanePai().getChildren().add(this.titledPane);
/* 106 */       if (this.primeiraExibicao) {
/* 107 */         this.primeiraExibicao = false;
/* 108 */         TranslateTransition tt2 = new TranslateTransition(UtilDesempenho.duracaoAnimacao(500.0D), (Node)this.titledPane);
/* 109 */         tt2.setFromY(-40.0D);
/* 110 */         tt2.setToY(0.0D);
/* 111 */         tt2.setAutoReverse(false);
/* 112 */         tt2.play();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void ocultar(Cena cena) {
/* 119 */     if (cena.getControlador().getAnchorPanePai() != null) {
/* 120 */       cena.getControlador().getAnchorPanePai().getChildren().remove(this.titledPane);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisivel() {
/* 126 */     return true;
/*     */   }
/*     */   
/*     */   public TitledPane getTitledPane() {
/* 130 */     return this.titledPane;
/*     */   }
/*     */ 
/*     */   
/*     */   public void teclaPressionada(KeyEvent event) {
/* 135 */     super.teclaPressionada(event);
/* 136 */     switch (event.getCode()) {
/*     */       case A:
/* 138 */         if (event.isControlDown() && event.isShiftDown()) {
/* 139 */           this.controladorAlarme.reconheceTodos();
/*     */         }
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void desenharObjetos(List<ObjetoDOMINIO> listaObjetos) {
/* 147 */     int cont = 0;
/*     */     
/* 149 */     Alarme.Nivel nivelMaximo = null;
/*     */     
/* 151 */     List<ObjetoTabularAlarme> listaAlarmesTemp = new ArrayList<>();
/*     */     
/* 153 */     if (listaObjetos == null) {
/*     */       return;
/*     */     }
/*     */     
/* 157 */     for (ObjetoDOMINIO objetoDOMINIO : listaObjetos) {
/* 158 */       List<Alarme> temp = objetoDOMINIO.getAlarmes();
/* 159 */       GenericComparator.ordenarLista(temp, "nivel", TipoOrdenacao.DESC);
/* 160 */       for (Alarme alarme : temp) {
/* 161 */         ObjetoTabularAlarme objetoTabularAlarme = (ObjetoTabularAlarme)alarme.getObjetosVisuais().get(ObjetoVisual.Tipo.TABULAR);
/* 162 */         if (objetoTabularAlarme != null) {
/* 163 */           objetoTabularAlarme.atualiza();
/* 164 */           if (alarme.getEstado() == Alarme.Estado.ATIVO || alarme.getEstado() == Alarme.Estado.RECONHECIDO) {
/* 165 */             listaAlarmesTemp.add(objetoTabularAlarme);
/* 166 */             if (alarme.getEstado() == Alarme.Estado.ATIVO) {
/* 167 */               cont++;
/* 168 */               if (nivelMaximo == null) {
/* 169 */                 nivelMaximo = alarme.getNivel(); continue;
/*     */               } 
/* 171 */               if (alarme.getNivel() == Alarme.Nivel.VERMELHO) {
/* 172 */                 nivelMaximo = alarme.getNivel();
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 181 */     this.contadorAlarmesAtivos = cont;
/* 182 */     this.estadoPisca = nivelMaximo;
/*     */     
/* 184 */     Platform.runLater(() -> {
/*     */           this.listaAlarmes.clear();
/*     */           
/*     */           this.listaAlarmes.addAll(listaAlarmesTemp);
/*     */           this.controladorAlarme.setTabelViewVisible((this.listaAlarmes.size() != 0));
/*     */         });
/* 190 */     if (Mediador.getInstancia().getPerfilUsuario().isSistemaNavegacao() && this.tocador != null && nivelMaximo == Alarme.Nivel.VERMELHO) {
/* 191 */       this.tocador.reproduzir();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public IconeSystemTray getIcone() {
/* 197 */     if (this.icone == null) {
/* 198 */       this.icone = new IconeSystemTray("Alarmes", "ipqm/gsd/hidra/ihm/imagens/icones/iconeAlarme.png");
/*     */     }
/* 200 */     this.icone.setContador(this.contadorAlarmesAtivos);
/*     */     
/* 202 */     if (this.estadoPisca == Alarme.Nivel.AMARELO || this.estadoPisca == Alarme.Nivel.VERMELHO) {
/* 203 */       this.icone.setPisca(true);
/* 204 */       if (this.estadoPisca == Alarme.Nivel.AMARELO) {
/* 205 */         this.icone.setCorPisca(Color.ORANGE);
/* 206 */       } else if (this.estadoPisca == Alarme.Nivel.VERMELHO) {
/* 207 */         this.icone.setCorPisca(Color.RED);
/*     */       } 
/*     */     } else {
/* 210 */       this.icone.setPisca(false);
/*     */     } 
/*     */     
/* 213 */     return this.icone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void minimizar() {
/* 218 */     TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(500.0D), (Node)this.titledPane);
/* 219 */     tt.setFromY(0.0D);
/* 220 */     tt.setToY(240.0D);
/* 221 */     tt.setAutoReverse(false);
/* 222 */     tt.setOnFinished(arg0 -> this.titledPane.setVisible(false));
/*     */ 
/*     */     
/* 225 */     tt.play();
/*     */   }
/*     */ 
/*     */   
/*     */   public void maximizar() {
/* 230 */     this.titledPane.setVisible(true);
/* 231 */     TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(500.0D), (Node)this.titledPane);
/* 232 */     tt.setFromY(240.0D);
/* 233 */     tt.setToY(0.0D);
/* 234 */     tt.setAutoReverse(false);
/* 235 */     tt.play();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMinimizado() {
/* 240 */     return !this.titledPane.isVisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAgrupado() {
/* 245 */     return true;
/*     */   }
/*     */   
/*     */   public ObservableList<ObjetoTabularAlarme> getListaAlarmes() {
/* 249 */     return this.listaAlarmes;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/alarme/WidgetAlarme.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */