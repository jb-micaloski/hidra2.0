/*     */ package ipqm.gsd.hidra.ihm.widgets.fonia_cisne;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.comunicacao_fonia.ComunicacaoFoniaTatica;
/*     */ import ipqm.gsd.componentes.nucleo.Ambiente;
/*     */ import ipqm.gsd.componentes.nucleo.Maquina;
/*     */ import ipqm.gsd.componentes.nucleo.NucleoAmbiente;
/*     */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*     */ import ipqm.gsd.componentes.nucleo.comunicacao_fonia.ComponenteFoniaIHM;
/*     */ import ipqm.gsd.componentes.nucleo.comunicacao_fonia.ComunicacaoFonia;
/*     */ import ipqm.gsd.componentes.nucleo.comunicacao_fonia.IHMFonia;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.util.ComandosSO;
/*     */ import ipqm.gsd.hidra.ihm.controle.Fonia;
/*     */ import ipqm.gsd.hidra.ihm.controle.Tela;
/*     */ import ipqm.gsd.hidra.ihm.util.Tocador;
/*     */ import ipqm.gsd.hidra.ihm.util.UtilDesempenho;
/*     */ import ipqm.gsd.hidra.ihm.visao.Cena;
/*     */ import ipqm.gsd.hidra.ihm.widgets.Widget;
/*     */ import ipqm.gsd.hidra.ihm.widgets.WidgetMinimizavel;
/*     */ import ipqm.gsd.hidra.ihm.widgets.system_tray.IconeSystemTray;
/*     */ import java.awt.Color;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javafx.animation.TranslateTransition;
/*     */ import javafx.application.Platform;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.fxml.FXMLLoader;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.control.TitledPane;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WidgetFoniaCisne
/*     */   extends Widget
/*     */   implements IHMFonia, WidgetMinimizavel
/*     */ {
/*     */   private ControladorFoniaCisne controladorFoniaCisne;
/*     */   private ComunicacaoFonia commFonia;
/*     */   private Map<String, ComponenteFoniaIHM> componentesFoniaIHM;
/*     */   private AnchorPane root;
/*     */   private TitledPane titledPane;
/*  46 */   private ComunicacaoFonia.EstadosCliente estado = ComunicacaoFonia.EstadosCliente.INATIVO;
/*     */   
/*     */   private Tocador tocadorTesteFone;
/*     */   
/*     */   private Tocador tocadorFoniaChamando;
/*     */   
/*     */   private IconeSystemTray iconeSystemTray;
/*     */ 
/*     */   
/*     */   public WidgetFoniaCisne(Tela tela) {
/*  56 */     super(Widget.Tipo.FONIA, tela);
/*  57 */     setSempreVisivel(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void construir() {
/*  65 */     String params = Maquina.getMaquinaLocal().getHostname() + " " + Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.IP_SERVIDOR_FONIA) + " " + Fonia.getTipoFonia() + " " + Fonia.getInternaEstereo();
/*  66 */     ComandosSO.enviarComando(ComandosSO.Comando.FONIA, params);
/*     */     
/*  68 */     Log.gravarLogInstrucao("Construindo widget fonia", this);
/*     */     
/*  70 */     FXMLLoader fxmlLoader = new FXMLLoader(Widget.class.getResource("fonia_cisne/FoniaCisne.fxml"));
/*     */     
/*     */     try {
/*  73 */       this.root = (AnchorPane)fxmlLoader.load();
/*  74 */     } catch (IOException ex) {
/*  75 */       Log.gravarLogExcecao("Erro ao carregar FXML do widget", this, ex);
/*     */     } 
/*     */     
/*  78 */     this.controladorFoniaCisne = (ControladorFoniaCisne)fxmlLoader.getController();
/*     */     
/*  80 */     this.componentesFoniaIHM = this.controladorFoniaCisne.getComponentesFoniaIHM();
/*  81 */     setCommFonia((ComunicacaoFonia)new ComunicacaoFoniaTatica(this, this.componentesFoniaIHM, Fonia.getTipoFonia()));
/*     */     
/*  83 */     this.titledPane = new TitledPane();
/*  84 */     this.titledPane.setCollapsible(false);
/*  85 */     this.titledPane.setId("titledPaneWidget");
/*  86 */     this.titledPane.setLayoutX(1540.0D);
/*  87 */     this.titledPane.setLayoutY(780.0D);
/*  88 */     this.titledPane.setPrefWidth(380.0D);
/*  89 */     this.titledPane.setPrefHeight(245.0D);
/*  90 */     this.titledPane.setText("Fonia (" + Maquina.getMaquinaLocal().getTitulo() + ")");
/*     */     
/*  92 */     Platform.runLater(() -> {
/*     */           this.titledPane.getStylesheets().clear();
/*     */           
/*     */           this.titledPane.getStylesheets().setAll((Object[])new String[] { "ipqm/gsd/hidra/ihm/css/widgets/foniaCisne.css", "ipqm/gsd/hidra/ihm/css/widgets/widgets.css" });
/*     */         });
/*  97 */     this.titledPane.setContent((Node)this.root);
/*  98 */     this.titledPane.setVisible(false);
/*     */     
/* 100 */     this.controladorFoniaCisne.setWidgetFoniaCisne(this);
/*     */     
/* 102 */     this.tocadorTesteFone = new Tocador("fonia" + File.separator + "testeFonia.wav");
/* 103 */     this.tocadorFoniaChamando = new Tocador("fonia" + File.separator + "foniaChamandoIPhone.wav");
/*     */   }
/*     */ 
/*     */   
/*     */   public void exibir(Cena cena) {
/* 108 */     if (cena.getControlador().getAnchorPanePai() != null) {
/* 109 */       cena.getControlador().getAnchorPanePai().getChildren().add(this.titledPane);
/* 110 */       if (cena.getControlador().getTela().getWidgets().containsKey(Widget.Tipo.PAINEL_PERMANENTE)) {
/* 111 */         this.titledPane.setLayoutY(780.0D);
/*     */       } else {
/* 113 */         this.titledPane.setLayoutY(830.0D);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void ocultar(Cena cena) {
/* 120 */     if (cena.getControlador().getAnchorPanePai() != null) {
/* 121 */       cena.getControlador().getAnchorPanePai().getChildren().remove(this.titledPane);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisivel() {
/* 127 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void desenharObjetos(List<ObjetoDOMINIO> listaObjetos) {
/* 132 */     if (getEstado() == ComunicacaoFonia.EstadosCliente.INATIVO && !isMinimizado())
/*     */     {
/*     */       
/* 135 */       this.controladorFoniaCisne.ativarFoniaBloqueada("COMUNICAÇÃO INATIVA");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void notificaIHM(ComponenteFoniaIHM componenteFoniaIHM) {
/* 142 */     this.controladorFoniaCisne.notificaIHM(componenteFoniaIHM);
/*     */   }
/*     */ 
/*     */   
/*     */   public IconeSystemTray getIcone() {
/* 147 */     if (getIconeSystemTray() == null) {
/* 148 */       setIconeSystemTray(new IconeSystemTray("Fonia", "ipqm/gsd/hidra/ihm/imagens/icones/iconeFonia.png"));
/*     */     }
/* 150 */     getIconeSystemTray().setAtivo(true);
/*     */     
/* 152 */     getIconeSystemTray().setCorPisca(Color.ORANGE);
/*     */     
/* 154 */     return getIconeSystemTray();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void minimizar() {
/* 162 */     TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(500.0D), (Node)this.titledPane);
/* 163 */     tt.setFromY(0.0D);
/* 164 */     tt.setToY(240.0D);
/* 165 */     tt.setAutoReverse(false);
/* 166 */     tt.setOnFinished(arg0 -> this.titledPane.setVisible(false));
/*     */ 
/*     */     
/* 169 */     tt.play();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void maximizar() {
/* 175 */     this.titledPane.setVisible(true);
/* 176 */     TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(500.0D), (Node)this.titledPane);
/* 177 */     tt.setFromY(240.0D);
/* 178 */     tt.setToY(0.0D);
/* 179 */     tt.setAutoReverse(false);
/* 180 */     tt.play();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMinimizado() {
/* 185 */     return !this.titledPane.isVisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAgrupado() {
/* 190 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComunicacaoFonia getCommFonia() {
/* 197 */     return this.commFonia;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCommFonia(ComunicacaoFonia commFonia) {
/* 204 */     this.commFonia = commFonia;
/*     */   }
/*     */   
/*     */   public Tocador getTocadorTesteFone() {
/* 208 */     return this.tocadorTesteFone;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComunicacaoFonia.EstadosCliente getEstado() {
/* 215 */     return this.estado;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEstado(ComunicacaoFonia.EstadosCliente estado) {
/* 222 */     this.estado = estado;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IconeSystemTray getIconeSystemTray() {
/* 229 */     return this.iconeSystemTray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIconeSystemTray(IconeSystemTray iconeSystemTray) {
/* 236 */     this.iconeSystemTray = iconeSystemTray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tocador getTocadorFoniaChamando() {
/* 243 */     return this.tocadorFoniaChamando;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/fonia_cisne/WidgetFoniaCisne.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */