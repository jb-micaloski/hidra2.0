/*     */ package ipqm.gsd.hidra.ihm.widgets.fonia_vhf;
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
/*     */ import ipqm.gsd.hidra.ihm.dispositivos.controladores_jogo.pedaleira_fonia.PedaleiraFoniaController;
/*     */ import ipqm.gsd.hidra.ihm.dispositivos.controladores_jogo.pedaleira_fonia.PedaleiraFoniaUser;
/*     */ import ipqm.gsd.hidra.ihm.util.UtilDesempenho;
/*     */ import ipqm.gsd.hidra.ihm.visao.Cena;
/*     */ import ipqm.gsd.hidra.ihm.widgets.Widget;
/*     */ import ipqm.gsd.hidra.ihm.widgets.WidgetMinimizavel;
/*     */ import ipqm.gsd.hidra.ihm.widgets.system_tray.IconeSystemTray;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javafx.animation.TranslateTransition;
/*     */ import javafx.application.Platform;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.fxml.FXMLLoader;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.control.TitledPane;
/*     */ import javafx.scene.input.KeyCode;
/*     */ import javafx.scene.input.KeyEvent;
/*     */ import javafx.scene.layout.AnchorPane;
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
/*     */ public class WidgetFoniaVHF
/*     */   extends Widget
/*     */   implements IHMFonia, WidgetMinimizavel, PedaleiraFoniaUser
/*     */ {
/*     */   public static final String PRETO_FOSCO = "-fx-background-color:#212121";
/*     */   public static final String PRETO = "-fx-background-color:#000000";
/*     */   public static final String TEXT_PRETO = "-fx-text-fill:#000000";
/*     */   public static final String TEXT_PRETO_FOSCO = "-fx-text-fill:#212121";
/*     */   public static final String VERDE_SOLIDO = "-fx-background-color:yellowgreen;";
/*     */   public static final String BRANCO_SOLIDO = "-fx-background-color:white;";
/*     */   public static final String TEXT_VERMELHO = "-fx-text-fill:#cd3a3e;";
/*     */   public static final String TEXT_VERMELHO_OPACO = "-fx-text-fill:#3c0206;";
/*     */   public static final String CINZA_CLARO = "-fx-background-color:#68676a;";
/*     */   private ControladorFoniaVHF controladorFoniaVHF;
/*     */   private ComunicacaoFonia commFonia;
/*     */   private Map<String, ComponenteFoniaIHM> componentesFoniaIHM;
/*     */   private boolean pttCalcado;
/*     */   private Map<Integer, String> coresBotoes;
/*     */   private AnchorPane root;
/*     */   private TitledPane titledPane;
/*     */   private boolean primeiraVez = true;
/*     */   private double xInicialMouseArrastar;
/*     */   private IconeSystemTray iconeSystemTray;
/*  77 */   private ComunicacaoFonia.EstadosCliente estado = ComunicacaoFonia.EstadosCliente.INATIVO;
/*     */   
/*     */   public WidgetFoniaVHF(Tela tela) {
/*  80 */     super(Widget.Tipo.FONIA, tela);
/*  81 */     setSempreVisivel(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void construir() {
/*  90 */     String params = Maquina.getMaquinaLocal().getHostname() + " " + Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.IP_SERVIDOR_FONIA) + " " + Fonia.getTipoFonia() + " " + Fonia.getInternaEstereo();
/*  91 */     ComandosSO.enviarComando(ComandosSO.Comando.FONIA, params);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  97 */     this.coresBotoes = new HashMap<>();
/*  98 */     this.coresBotoes.put(Integer.valueOf(0), "-fx-background-color:#000000");
/*  99 */     this.coresBotoes.put(Integer.valueOf(3), "-fx-background-color:#68676a;");
/*     */     
/* 101 */     Log.gravarLogInstrucao("Construindo widget fonia", this);
/*     */     
/* 103 */     FXMLLoader fxmlLoader = new FXMLLoader(Widget.class.getResource("fonia_vhf/FoniaVHF.fxml"));
/*     */     
/*     */     try {
/* 106 */       this.root = (AnchorPane)fxmlLoader.load();
/* 107 */     } catch (IOException ex) {
/* 108 */       Log.gravarLogExcecao("Erro ao carregar FXML do widget", this, ex);
/*     */     } 
/*     */     
/* 111 */     this.controladorFoniaVHF = (ControladorFoniaVHF)fxmlLoader.getController();
/*     */     
/* 113 */     this.componentesFoniaIHM = this.controladorFoniaVHF.getComponentesFoniaVhf();
/* 114 */     this.commFonia = (ComunicacaoFonia)new ComunicacaoFoniaTatica(this, this.componentesFoniaIHM, Fonia.getTipoFonia());
/*     */     
/* 116 */     this.commFonia.instanciarThreadVerificaAlcanceRadioVHF();
/*     */     
/* 118 */     this.titledPane = new TitledPane();
/* 119 */     this.titledPane.setCollapsible(false);
/* 120 */     this.titledPane.setId("titledPaneWidget");
/* 121 */     this.titledPane.setLayoutX(1540.0D);
/* 122 */     this.titledPane.setLayoutY(780.0D);
/* 123 */     this.titledPane.setPrefWidth(380.0D);
/* 124 */     this.titledPane.setPrefHeight(245.0D);
/* 125 */     this.titledPane.setText("Fonia");
/*     */     
/* 127 */     Platform.runLater(() -> {
/*     */           this.titledPane.getStylesheets().clear();
/*     */           
/*     */           this.titledPane.getStylesheets().setAll((Object[])new String[] { "ipqm/gsd/hidra/ihm/css/widgets/foniaVHF.css", "ipqm/gsd/hidra/ihm/css/widgets/widgets.css" });
/*     */         });
/* 132 */     this.titledPane.setContent((Node)this.root);
/* 133 */     this.titledPane.setVisible(false);
/*     */     
/* 135 */     this.controladorFoniaVHF.setWidgetFoniaVHF(this);
/* 136 */     this.controladorFoniaVHF.setAnchorPaneFonia(this.root);
/*     */     
/* 138 */     this.controladorFoniaVHF.getLabelTx().setStyle("-fx-text-fill:#3c0206;");
/* 139 */     this.controladorFoniaVHF.getLabelRx().setStyle("-fx-text-fill:#3c0206;");
/*     */     try {
/* 141 */       this.controladorFoniaVHF.contruir();
/* 142 */     } catch (InterruptedException ex) {
/* 143 */       Log.gravarLogExcecao("Erro ao construir controlador.", this, ex);
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/* 148 */       new PedaleiraFoniaController(Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.PEDALEIRA_FONIA), this);
/* 149 */     } catch (Exception ex) {
/* 150 */       Log.gravarLogInstrucao("Não foi possível conectar o dispositivo da pedaleira da fonia: " + ex, this);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void exibir(Cena cena) {
/* 156 */     if (cena.getControlador().getAnchorPanePai() != null) {
/*     */       
/* 158 */       cena.getControlador().getAnchorPanePai().getChildren().add(this.titledPane);
/*     */       
/* 160 */       if (this.primeiraVez) {
/* 161 */         this.primeiraVez = false;
/* 162 */         TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(500.0D), (Node)this.titledPane);
/* 163 */         tt.setFromX(-250.0D);
/* 164 */         tt.setToX(0.0D);
/* 165 */         tt.setAutoReverse(false);
/* 166 */         tt.play();
/*     */         
/* 168 */         if (getIconeSystemTray() != null) {
/* 169 */           getIconeSystemTray().setNome("Fonia Inativa");
/* 170 */           getIconeSystemTray().setImagem("ipqm/gsd/hidra/ihm/imagens/icones/iconeFoniaInativa.png");
/* 171 */           getIcone().atualiza();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void ocultar(Cena cena) {
/* 179 */     if (cena.getControlador().getAnchorPanePai() != null) {
/* 180 */       cena.getControlador().getAnchorPanePai().getChildren().remove(this.titledPane);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisivel() {
/* 186 */     return this.titledPane.isVisible();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void desenharObjetos(List<ObjetoDOMINIO> listaObjetos) {}
/*     */ 
/*     */   
/*     */   public void pttCalcado() {
/* 195 */     if (!this.pttCalcado) {
/* 196 */       this.pttCalcado = true;
/* 197 */       getCommFonia().tratarComandoIHM("PTT FISICO");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void pttDescalcado() {
/* 202 */     this.pttCalcado = false;
/* 203 */     getCommFonia().tratarComandoIHM("PTT FISICO");
/*     */   }
/*     */ 
/*     */   
/*     */   public void teclaSolta(KeyEvent event) {
/* 208 */     super.teclaSolta(event);
/* 209 */     switch (event.getCode()) {
/*     */       case PAUSE:
/* 211 */         pttDescalcado();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void teclaPressionada(KeyEvent event) {
/* 218 */     super.teclaPressionada(event);
/* 219 */     switch (event.getCode()) {
/*     */       case PAUSE:
/* 221 */         pttCalcado();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void notificaIHM(ComponenteFoniaIHM componenteFoniaIHM) {
/* 228 */     int tipoCanal = componenteFoniaIHM.getTipoCanal();
/*     */     
/* 230 */     switch (tipoCanal) {
/*     */       case 17:
/* 232 */         this.controladorFoniaVHF.processarRequisicaoCanaisAdicionais(componenteFoniaIHM);
/*     */         break;
/*     */       case 3:
/* 235 */         Platform.runLater(() -> {
/*     */               this.controladorFoniaVHF.getBotaoPPT().setStyle(this.coresBotoes.get(Integer.valueOf(componenteFoniaIHM.getEstadoCanal())));
/*     */               if (((String)this.coresBotoes.get(Integer.valueOf(componenteFoniaIHM.getEstadoCanal()))).equalsIgnoreCase("-fx-background-color:#68676a;")) {
/*     */                 this.controladorFoniaVHF.getLabelTx().setStyle("-fx-text-fill:#cd3a3e;");
/*     */               } else {
/*     */                 this.controladorFoniaVHF.getLabelTx().setStyle("-fx-text-fill:#3c0206;");
/*     */               } 
/*     */             });
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 14:
/* 250 */         Platform.runLater(() -> {
/*     */               if (componenteFoniaIHM.getEstadoCanal() == 11) {
/*     */                 this.controladorFoniaVHF.getLabelRx().setStyle("-fx-text-fill:#cd3a3e;");
/*     */               } else if (componenteFoniaIHM.getEstadoCanal() == 0) {
/*     */                 this.controladorFoniaVHF.getLabelRx().setStyle("-fx-text-fill:#3c0206;");
/*     */               } else if (componenteFoniaIHM.getEstadoCanal() == 0) {
/*     */                 this.controladorFoniaVHF.getLabelRx().setStyle("-fx-text-fill:#3c0206;");
/*     */               } 
/*     */             });
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 8:
/* 264 */         if (componenteFoniaIHM.getEstadoCanal() == 5) {
/* 265 */           this.estado = componenteFoniaIHM.getClienteAtivo();
/*     */           
/* 267 */           if (this.estado == ComunicacaoFonia.EstadosCliente.ATIVO) {
/* 268 */             Platform.runLater(() -> {
/*     */                   this.controladorFoniaVHF.getFoniaVHFBloqueada().setVisible(false);
/*     */                   
/*     */                   this.controladorFoniaVHF.getFoniaVHFBloqueada().toBack();
/*     */                 });
/* 273 */             if (getIconeSystemTray() != null) {
/* 274 */               getIconeSystemTray().setNome("Fonia Ativa");
/* 275 */               getIconeSystemTray().setImagem("ipqm/gsd/hidra/ihm/imagens/icones/iconeFonia.png");
/* 276 */               getIcone().atualiza();
/*     */             } 
/*     */           } 
/* 279 */           if (this.estado == ComunicacaoFonia.EstadosCliente.INATIVO) {
/* 280 */             Platform.runLater(() -> {
/*     */                   this.controladorFoniaVHF.getFoniaVHFBloqueada().setVisible(true);
/*     */                   
/*     */                   this.controladorFoniaVHF.getFoniaVHFBloqueada().toFront();
/*     */                 });
/* 285 */             if (getIconeSystemTray() != null) {
/* 286 */               getIconeSystemTray().setNome("Fonia Inativa");
/* 287 */               getIconeSystemTray().setImagem("ipqm/gsd/hidra/ihm/imagens/icones/iconeFoniaInativa.png");
/* 288 */               getIcone().atualiza();
/*     */             } 
/*     */           } 
/* 291 */           if (this.estado == ComunicacaoFonia.EstadosCliente.FALHA && 
/* 292 */             getIconeSystemTray() != null) {
/* 293 */             getIconeSystemTray().setNome("Fonia Instável");
/* 294 */             getIconeSystemTray().setImagem("ipqm/gsd/hidra/ihm/imagens/icones/iconeFoniaAlerta.png");
/* 295 */             getIcone().atualiza();
/*     */           } 
/*     */           
/*     */           break;
/*     */         } 
/* 300 */         if (componenteFoniaIHM.getEstadoCanal() == 10) {
/* 301 */           while (getEstado() != ComunicacaoFonia.EstadosCliente.ATIVO) {
/*     */             try {
/* 303 */               Thread.sleep(1000L);
/* 304 */             } catch (InterruptedException ex) {
/* 305 */               Log.gravarLogExcecao("Erro no sleep.", this, ex);
/*     */             } 
/*     */           } 
/* 308 */           Platform.runLater(() -> {
/*     */                 this.controladorFoniaVHF.acaoMais(null);
/*     */                 this.controladorFoniaVHF.acaoCanal16(null);
/*     */               });
/*     */         } 
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ComunicacaoFonia getCommFonia() {
/* 320 */     return this.commFonia;
/*     */   }
/*     */ 
/*     */   
/*     */   public IconeSystemTray getIcone() {
/* 325 */     if (getIconeSystemTray() == null) {
/* 326 */       setIconeSystemTray(new IconeSystemTray("Fonia (CTRL+F)", "ipqm/gsd/hidra/ihm/imagens/icones/iconeFonia.png"));
/*     */     }
/*     */     
/* 329 */     return getIconeSystemTray();
/*     */   }
/*     */ 
/*     */   
/*     */   public void minimizar() {
/* 334 */     TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(500.0D), (Node)this.titledPane);
/* 335 */     tt.setFromY(0.0D);
/* 336 */     tt.setToY(240.0D);
/* 337 */     tt.setAutoReverse(false);
/* 338 */     tt.setOnFinished(arg0 -> this.titledPane.setVisible(false));
/*     */ 
/*     */     
/* 341 */     tt.play();
/*     */   }
/*     */ 
/*     */   
/*     */   public void maximizar() {
/* 346 */     this.titledPane.setVisible(true);
/* 347 */     TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(500.0D), (Node)this.titledPane);
/* 348 */     tt.setFromY(240.0D);
/* 349 */     tt.setToY(0.0D);
/* 350 */     tt.setAutoReverse(false);
/* 351 */     tt.play();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMinimizado() {
/* 356 */     return !isVisivel();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAgrupado() {
/* 361 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComunicacaoFonia.EstadosCliente getEstado() {
/* 368 */     return this.estado;
/*     */   }
/*     */ 
/*     */   
/*     */   public void pedalPressionado() {
/* 373 */     pttCalcado();
/*     */   }
/*     */ 
/*     */   
/*     */   public void pedalSolto() {
/* 378 */     pttDescalcado();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IconeSystemTray getIconeSystemTray() {
/* 385 */     return this.iconeSystemTray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIconeSystemTray(IconeSystemTray iconeSystemTray) {
/* 392 */     this.iconeSystemTray = iconeSystemTray;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/fonia_vhf/WidgetFoniaVHF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */