/*     */ package ipqm.gsd.hidra.ihm.widgets.fonia;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.comunicacao_fonia.ComunicacaoFoniaTatica;
/*     */ import ipqm.gsd.componentes.nucleo.Ambiente;
/*     */ import ipqm.gsd.componentes.nucleo.Maquina;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.NucleoAmbiente;
/*     */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*     */ import ipqm.gsd.componentes.nucleo.comunicacao_fonia.ComponenteFoniaIHM;
/*     */ import ipqm.gsd.componentes.nucleo.comunicacao_fonia.ComunicacaoFonia;
/*     */ import ipqm.gsd.componentes.nucleo.comunicacao_fonia.IHMFonia;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.notificador.Notificacao;
/*     */ import ipqm.gsd.componentes.nucleo.notificador.Notificador;
/*     */ import ipqm.gsd.componentes.nucleo.relogio_simulado.RelogioSimulado;
/*     */ import ipqm.gsd.componentes.nucleo.util.ComandosSO;
/*     */ import ipqm.gsd.hidra.ihm.controle.Fonia;
/*     */ import ipqm.gsd.hidra.ihm.controle.Tela;
/*     */ import ipqm.gsd.hidra.ihm.dispositivos.controladores_jogo.GestorDispositivos;
/*     */ import ipqm.gsd.hidra.ihm.dispositivos.controladores_jogo.pedaleira_fonia.PedaleiraFoniaUser;
/*     */ import ipqm.gsd.hidra.ihm.util.Tocador;
/*     */ import ipqm.gsd.hidra.ihm.util.UtilDesempenho;
/*     */ import ipqm.gsd.hidra.ihm.visao.Cena;
/*     */ import ipqm.gsd.hidra.ihm.widgets.Widget;
/*     */ import ipqm.gsd.hidra.ihm.widgets.WidgetMinimizavel;
/*     */ import ipqm.gsd.hidra.ihm.widgets.system_tray.IconeSystemTray;
/*     */ import java.awt.Color;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.Calendar;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javafx.animation.TranslateTransition;
/*     */ import javafx.application.Platform;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.fxml.FXMLLoader;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.control.TitledPane;
/*     */ import javafx.scene.control.ToggleButton;
/*     */ import javafx.scene.input.KeyCode;
/*     */ import javafx.scene.input.KeyEvent;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ import javafx.scene.layout.Region;
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
/*     */ public class WidgetFonia
/*     */   extends Widget
/*     */   implements IHMFonia, WidgetMinimizavel, PedaleiraFoniaUser
/*     */ {
/*     */   private ControladorFonia controladorFonia;
/*     */   private AnchorPane root;
/*     */   private TitledPane titledPane;
/*     */   private ComunicacaoFonia commFonia;
/*     */   private Map<String, ComponenteFoniaIHM> componentesFoniaIHM;
/*     */   private boolean pttCalcado;
/*  90 */   private ComunicacaoFonia.EstadosCliente estado = ComunicacaoFonia.EstadosCliente.INATIVO;
/*     */   
/*     */   private IconeSystemTray iconeSystemTray;
/*     */   
/*     */   private boolean primeiraConexaoChat = true;
/*     */   
/*     */   private boolean piscaIcone;
/*     */   
/*     */   private Tocador tocadorTeste;
/*     */   
/*     */   private Tocador tocadorFoniaChamando;
/*     */   private int tocarFoniaChamando;
/*     */   
/*     */   public WidgetFonia(Tela tela) {
/* 104 */     super(Widget.Tipo.FONIA, tela);
/* 105 */     setSempreVisivel(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void construir() {
/* 114 */     String params = Maquina.getMaquinaLocal().getHostname() + " " + Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.IP_SERVIDOR_FONIA) + " " + Fonia.getTipoFonia() + " " + Fonia.getInternaEstereo();
/* 115 */     ComandosSO.enviarComando(ComandosSO.Comando.FONIA, params);
/*     */     
/* 117 */     Log.gravarLogInstrucao("Construindo widget fonia", this);
/*     */     
/* 119 */     FXMLLoader fxmlLoader = new FXMLLoader(Widget.class.getResource("fonia/Fonia.fxml"));
/*     */     
/*     */     try {
/* 122 */       this.root = (AnchorPane)fxmlLoader.load();
/* 123 */     } catch (IOException ex) {
/* 124 */       Log.gravarLogExcecao("Erro ao carregar FXML do widget", this, ex);
/*     */     } 
/*     */     
/* 127 */     setControladorFonia((ControladorFonia)fxmlLoader.getController());
/*     */     
/* 129 */     this.titledPane = new TitledPane();
/* 130 */     this.titledPane.setCollapsible(false);
/* 131 */     this.titledPane.setId("titledPaneWidget");
/* 132 */     this.titledPane.setLayoutX(1540.0D);
/* 133 */     this.titledPane.setLayoutY(780.0D);
/* 134 */     this.titledPane.setPrefWidth(380.0D);
/* 135 */     this.titledPane.setPrefHeight(245.0D);
/* 136 */     this.titledPane.setText("Fonia (" + getControladorFonia().getNomeEstacao() + ")");
/*     */     
/* 138 */     Platform.runLater(() -> {
/*     */           this.titledPane.getStylesheets().clear();
/*     */           
/*     */           this.titledPane.getStylesheets().setAll((Object[])new String[] { "ipqm/gsd/hidra/ihm/css/widgets/fonia.css", "ipqm/gsd/hidra/ihm/css/widgets/widgets.css" });
/*     */         });
/* 143 */     this.root.setId("painelFonia");
/* 144 */     this.titledPane.setContent((Node)this.root);
/* 145 */     this.titledPane.setVisible(false);
/*     */     
/* 147 */     this.componentesFoniaIHM = getControladorFonia().getMapComponentesFoniaIHM();
/* 148 */     this.commFonia = (ComunicacaoFonia)new ComunicacaoFoniaTatica(this, this.componentesFoniaIHM, Fonia.getTipoFonia());
/*     */     
/* 150 */     getControladorFonia().setWidgetFonia(this);
/* 151 */     this.tocadorTeste = new Tocador("fonia" + File.separator + "testeFonia.wav");
/* 152 */     this.tocadorFoniaChamando = new Tocador(Fonia.getToque());
/* 153 */     this.tocadorFoniaChamando.getMediaPlayer().setBalance(Fonia.getBalance());
/*     */     
/* 155 */     GestorDispositivos.getInstancia().setWidgetFonia(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void exibir(Cena cena) {
/* 160 */     if (cena.getControlador().getAnchorPanePai() != null) {
/* 161 */       cena.getControlador().getAnchorPanePai().getChildren().add(this.titledPane);
/* 162 */       if (cena.getControlador().getTela().getWidgets().containsKey(Widget.Tipo.PAINEL_PERMANENTE)) {
/* 163 */         this.titledPane.setLayoutY(780.0D);
/*     */       } else {
/* 165 */         this.titledPane.setLayoutY(830.0D);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void ocultar(Cena cena) {
/* 172 */     if (cena.getControlador().getAnchorPanePai() != null) {
/* 173 */       cena.getControlador().getAnchorPanePai().getChildren().remove(this.titledPane);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisivel() {
/* 179 */     return this.titledPane.isVisible(); } public void notificaIHM(ComponenteFoniaIHM componenteFoniaIHM) { int indiceHashMap; ToggleButton tb; List<BotaoCmd> listaBtComando;
/*     */     BotaoCmd bt;
/*     */     String cor;
/*     */     Map<String, String> mapaLinhas;
/*     */     Region indCanalExt;
/*     */     BotaoCmd botaoCmd1;
/*     */     Region region1;
/* 186 */     BotaoCmd cmdAcionado = null;
/*     */     
/* 188 */     int tipoCanal = componenteFoniaIHM.getTipoCanal();
/*     */ 
/*     */ 
/*     */     
/* 192 */     switch (tipoCanal) {
/*     */       case 17:
/* 194 */         getControladorFonia().processarRequisicaoCanaisAdicionais(componenteFoniaIHM);
/*     */         return;
/*     */       case 13:
/* 197 */         if (componenteFoniaIHM.getEstadoCanal() == 8)
/*     */         {
/* 199 */           getControladorFonia().ativarFoniaBloqueada("GERAL DE CUBÍCULO");
/*     */         }
/* 201 */         if (componenteFoniaIHM.getEstadoCanal() == 9)
/*     */         {
/* 203 */           getControladorFonia().desativarFoniaBloqueada("GERAL DE CUBÍCULO");
/*     */         }
/*     */         return;
/*     */       case 12:
/* 207 */         if (componenteFoniaIHM.getEstadoCanal() == 7) {
/* 208 */           Platform.runLater(() -> {
/*     */                 getControladorFonia().getTextAreaReceberChat().clear();
/*     */                 getControladorFonia().getClientesConversando().setText("");
/*     */                 getControladorFonia().getTextFildEnviarChat().setDisable(true);
/*     */               });
/*     */           return;
/*     */         } 
/* 215 */         if (!this.controladorFonia.getPainelChat().isVisible()) {
/* 216 */           getControladorFonia().getBtIniciarChat().setTimerAtivado(true);
/*     */         }
/* 218 */         if (isMinimizado()) {
/* 219 */           this.piscaIcone = true;
/*     */         }
/* 221 */         Platform.runLater(() -> getControladorFonia().getTextAreaReceberChat().appendText(componenteFoniaIHM.getNomeCanal() + "\n"));
/*     */         return;
/*     */ 
/*     */ 
/*     */       
/*     */       case 7:
/*     */         return;
/*     */ 
/*     */ 
/*     */       
/*     */       case 1:
/* 232 */         for (BotaoCmd c : getControladorFonia().getListaBtCmdLinhasExternas()) {
/* 233 */           if (c.GetTextoForeground().equalsIgnoreCase(componenteFoniaIHM.getNomeCanal())) {
/* 234 */             cmdAcionado = c;
/*     */ 
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */         
/* 241 */         if (cmdAcionado == null) {
/*     */           return;
/*     */         }
/* 244 */         indiceHashMap = cmdAcionado.GetIndHashMap();
/*     */         
/* 246 */         if (componenteFoniaIHM.getEstadoCanal() != 0) {
/* 247 */           getControladorFonia().setStatusObjLinExterna(indiceHashMap, true); break;
/*     */         } 
/* 249 */         getControladorFonia().setStatusObjLinExterna(indiceHashMap, false);
/*     */         break;
/*     */ 
/*     */       
/*     */       case 2:
/*     */       case 18:
/* 255 */         if (getControladorFonia().getPainelChat().isVisible()) {
/* 256 */           getControladorFonia().getBtFecharChat().setTimerAtivado(true);
/*     */         }
/* 258 */         if (isMinimizado()) {
/* 259 */           this.piscaIcone = true;
/*     */         }
/*     */ 
/*     */         
/* 263 */         mapaLinhas = new HashMap<>();
/*     */         
/* 265 */         if (tipoCanal == 18) {
/*     */           
/* 267 */           listaBtComando = getControladorFonia().getListaBtCmdLinhasVigia();
/*     */           
/* 269 */           for (Map<String, String> mapaTemp : getControladorFonia().getMapaLinhasVigia().values()) {
/* 270 */             mapaLinhas.putAll(mapaTemp);
/*     */           }
/*     */         } else {
/*     */           
/* 274 */           listaBtComando = getControladorFonia().getListaBtCmdLinhasInternas();
/* 275 */           mapaLinhas.putAll(getControladorFonia().getMapaLinhasInternas());
/*     */         } 
/*     */         
/* 278 */         for (BotaoCmd c : listaBtComando) {
/* 279 */           if (c.getHostName().equalsIgnoreCase(componenteFoniaIHM.getNomeCanal())) {
/* 280 */             cmdAcionado = c;
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/* 285 */         for (String hostname : mapaLinhas.keySet()) {
/* 286 */           if (hostname.equalsIgnoreCase(componenteFoniaIHM.getNomeCanal())) {
/* 287 */             if (componenteFoniaIHM.getEstadoCanal() == 2) {
/*     */               
/* 289 */               Long horaMilisegundos = Long.valueOf(Mediador.getRelogio().getHorario());
/* 290 */               Calendar c = Calendar.getInstance();
/* 291 */               c.setTimeInMillis(horaMilisegundos.longValue());
/*     */               
/* 293 */               String canalSaiu = RelogioSimulado.converteCalendar2Hora(c).substring(0, 5) + " " + (String)mapaLinhas.get(hostname) + " entrou na conversa...\n";
/* 294 */               String clintesAntigos = getControladorFonia().getClientesConversando().getText() + (String)mapaLinhas.get(hostname) + " ";
/*     */               
/* 296 */               Platform.runLater(() -> {
/*     */                     getControladorFonia().getTextAreaReceberChat().appendText(canalSaiu);
/*     */                     getControladorFonia().getClientesConversando().setText(clintesAntigos);
/*     */                     getControladorFonia().getTextFildEnviarChat().setDisable(false);
/*     */                     getControladorFonia().getTextFildEnviarChat().requestFocus();
/*     */                   });
/* 302 */               setTocarFoniaChamando(getTocarFoniaChamando() - 1);
/*     */               
/* 304 */               setTocarFoniaChamando(getTocarFoniaChamando() - 1);
/*     */             } 
/* 306 */             if (componenteFoniaIHM.getEstadoCanal() == 14 || componenteFoniaIHM.getEstadoCanal() == 13) {
/*     */               
/* 308 */               Long horaMilisegundos = Long.valueOf(Mediador.getRelogio().getHorario());
/* 309 */               Calendar c = Calendar.getInstance();
/* 310 */               c.setTimeInMillis(horaMilisegundos.longValue());
/*     */               
/* 312 */               String canalSaiu = RelogioSimulado.converteCalendar2Hora(c).substring(0, 5) + " " + (String)mapaLinhas.get(hostname) + " saiu da conversa...\n";
/* 313 */               String clintesAntigos = getControladorFonia().getClientesConversando().getText().replace((String)mapaLinhas.get(hostname) + " ", "");
/*     */               
/* 315 */               Platform.runLater(() -> {
/*     */                     if (clintesAntigos.equalsIgnoreCase("")) {
/*     */                       getControladorFonia().getTextFildEnviarChat().setDisable(true);
/*     */                     }
/*     */                     
/*     */                     if (this.primeiraConexaoChat) {
/*     */                       getControladorFonia().getClientesConversando().setText(clintesAntigos);
/*     */                       this.primeiraConexaoChat = false;
/*     */                     } else {
/*     */                       getControladorFonia().getTextAreaReceberChat().appendText(canalSaiu);
/*     */                       getControladorFonia().getClientesConversando().setText(clintesAntigos);
/*     */                     } 
/*     */                   });
/* 328 */               setTocarFoniaChamando(getTocarFoniaChamando() + 1);
/*     */             } 
/*     */             
/* 331 */             if ((componenteFoniaIHM.getEstadoCanal() == 14 || componenteFoniaIHM.getEstadoCanal() == 13) && (cmdAcionado == null || 
/* 332 */               getControladorFonia().getPainelChat().isVisible())) {
/* 333 */               Notificacao n = new Notificacao("Fonia", Notificacao.Tipo.ALERTA);
/* 334 */               n.setDescricao("O canal " + (String)mapaLinhas.get(hostname) + " solicita a sua atenção.");
/* 335 */               n.setPermanente(true);
/* 336 */               n.setExibirBotaoFechar(true);
/* 337 */               Notificador.getInstancia().addNotificacao(n);
/*     */               
/* 339 */               setTocarFoniaChamando(getTocarFoniaChamando() + 1);
/*     */             } 
/*     */             
/* 342 */             if (componenteFoniaIHM.getEstadoCanal() == 3) {
/* 343 */               Long horaMilisegundos = Long.valueOf(Mediador.getRelogio().getHorario());
/* 344 */               Calendar c = Calendar.getInstance();
/* 345 */               c.setTimeInMillis(horaMilisegundos.longValue());
/*     */               
/* 347 */               String canalSaiu = RelogioSimulado.converteCalendar2Hora(c).substring(0, 5) + " " + (String)mapaLinhas.get(hostname) + " saiu da conversa...\n";
/* 348 */               String clintesAntigos = getControladorFonia().getClientesConversando().getText().replace((String)mapaLinhas.get(hostname) + " ", "");
/*     */               
/* 350 */               Platform.runLater(() -> {
/*     */                     if (clintesAntigos.equalsIgnoreCase("")) {
/*     */                       getControladorFonia().getTextFildEnviarChat().setDisable(true);
/*     */                     }
/*     */                     
/*     */                     if (this.primeiraConexaoChat) {
/*     */                       getControladorFonia().getClientesConversando().setText(clintesAntigos);
/*     */                       this.primeiraConexaoChat = false;
/*     */                     } else {
/*     */                       getControladorFonia().getTextAreaReceberChat().appendText(canalSaiu);
/*     */                       getControladorFonia().getClientesConversando().setText(clintesAntigos);
/*     */                     } 
/*     */                   });
/* 363 */               setTocarFoniaChamando(getTocarFoniaChamando() - 1);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 374 */         if (cmdAcionado == null) {
/*     */           return;
/*     */         }
/* 377 */         indiceHashMap = cmdAcionado.GetIndHashMap();
/*     */         
/* 379 */         if (tipoCanal == 18) {
/* 380 */           if (componenteFoniaIHM.getEstadoCanal() == 3) {
/* 381 */             getControladorFonia().setStatusObjLinVigia(indiceHashMap, false);
/*     */           } else {
/* 383 */             getControladorFonia().setStatusObjLinVigia(indiceHashMap, true);
/*     */           } 
/*     */           
/* 386 */           if (componenteFoniaIHM.getEstadoCanal() == 14 || componenteFoniaIHM.getEstadoCanal() == 13) {
/* 387 */             getControladorFonia().setStatusObjTimerLinVigia(indiceHashMap, true);
/*     */           } else {
/* 389 */             getControladorFonia().setStatusObjTimerLinVigia(indiceHashMap, false);
/*     */           } 
/*     */         } else {
/* 392 */           if (componenteFoniaIHM.getEstadoCanal() == 3) {
/* 393 */             getControladorFonia().setStatusObjLinInterna(indiceHashMap, false);
/*     */           } else {
/* 395 */             getControladorFonia().setStatusObjLinInterna(indiceHashMap, true);
/*     */           } 
/*     */           
/* 398 */           if (componenteFoniaIHM.getEstadoCanal() == 14 || componenteFoniaIHM.getEstadoCanal() == 13) {
/* 399 */             getControladorFonia().setStatusObjTimerLinInterna(indiceHashMap, true);
/*     */           } else {
/* 401 */             getControladorFonia().setStatusObjTimerLinInterna(indiceHashMap, false);
/*     */           } 
/*     */         } 
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
/* 415 */         if (componenteFoniaIHM.getEstadoCanal() == 13) {
/* 416 */           getTocadorFoniaChamando().reproduzir();
/*     */         }
/*     */         break;
/*     */       
/*     */       case 3:
/* 421 */         cmdAcionado = getControladorFonia().getBtHandsFree();
/*     */         break;
/*     */       
/*     */       case 6:
/* 425 */         if (getControladorFonia().getPainelChat().isVisible()) {
/* 426 */           getControladorFonia().getBtFecharChat().setTimerAtivado(true);
/*     */         }
/* 428 */         if (isMinimizado()) {
/* 429 */           this.piscaIcone = true;
/*     */         }
/*     */         break;
/*     */       
/*     */       case 4:
/* 434 */         cmdAcionado = getControladorFonia().getBtExtComms();
/*     */         break;
/*     */       
/*     */       case 5:
/* 438 */         cmdAcionado = getControladorFonia().getBtIntComms();
/*     */         break;
/*     */       case 16:
/* 441 */         cmdAcionado = getControladorFonia().getBtTesteMicrofone();
/*     */         break;
/*     */     } 
/*     */     
/* 445 */     switch (componenteFoniaIHM.getEstadoCanal()) {
/*     */       
/*     */       case 5:
/* 448 */         this.estado = componenteFoniaIHM.getClienteAtivo();
/* 449 */         if (this.estado == ComunicacaoFonia.EstadosCliente.INATIVO)
/*     */         {
/*     */           
/* 452 */           if (getIconeSystemTray() != null) {
/* 453 */             getIconeSystemTray().setNome("Fonia Inativa");
/* 454 */             getIconeSystemTray().setImagem("ipqm/gsd/hidra/ihm/imagens/icones/iconeFoniaInativa.png");
/* 455 */             getIcone().atualiza();
/*     */           } 
/*     */         }
/* 458 */         if (this.estado == ComunicacaoFonia.EstadosCliente.ATIVO) {
/*     */           
/* 460 */           getControladorFonia().desativarFoniaBloqueada("COMUNICAÇÃO ATIVA");
/* 461 */           if (getIconeSystemTray() != null) {
/* 462 */             getIconeSystemTray().setNome("Fonia Ativa");
/* 463 */             getIconeSystemTray().setImagem("ipqm/gsd/hidra/ihm/imagens/icones/iconeFonia.png");
/* 464 */             getIcone().atualiza();
/*     */           } 
/*     */         } 
/* 467 */         if (this.estado == ComunicacaoFonia.EstadosCliente.FALHA) {
/*     */           
/* 469 */           getControladorFonia().desativarFoniaBloqueada("COMUNICAÇÃO INSTÁVEL");
/* 470 */           if (getIconeSystemTray() != null) {
/* 471 */             getIconeSystemTray().setNome("Fonia Instável");
/* 472 */             getIconeSystemTray().setImagem("ipqm/gsd/hidra/ihm/imagens/icones/iconeFoniaAlerta.png");
/* 473 */             getIcone().atualiza();
/*     */           } 
/*     */         } 
/*     */         break;
/*     */       case 0:
/* 478 */         switch (tipoCanal) {
/*     */           case 6:
/* 480 */             bt = getControladorFonia().buscaLinhaExtSelecionadaAtual();
/* 481 */             if (bt == null) {
/*     */               break;
/*     */             }
/* 484 */             indCanalExt = bt.getRegiaoLuzIndicativa();
/* 485 */             if (indCanalExt != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 492 */               bt.setTimerAtivado(false);
/* 493 */               Platform.runLater(() -> indCanalExt.setStyle("-fx-background-color:darkgray;"));
/*     */             } 
/*     */             break;
/*     */ 
/*     */           
/*     */           case 18:
/* 499 */             tb = cmdAcionado.getIdBtn();
/* 500 */             Platform.runLater(() -> tb.setStyle("-fx-background-color:orange;"));
/*     */             break;
/*     */         } 
/*     */ 
/*     */         
/* 505 */         tb = cmdAcionado.getIdBtn();
/* 506 */         Platform.runLater(() -> tb.setStyle("-fx-background-color:darkgray;"));
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 2:
/* 518 */         switch (tipoCanal) {
/*     */           case 18:
/* 520 */             cor = "-fx-background-color:tomato;";
/*     */             break;
/*     */           default:
/* 523 */             cor = "-fx-background-color:#F3CA1D;";
/*     */             break;
/*     */         } 
/*     */         
/* 527 */         tb = cmdAcionado.getIdBtn();
/* 528 */         Platform.runLater(() -> tb.setStyle(cor));
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/* 534 */         switch (tipoCanal) {
/*     */           case 6:
/* 536 */             botaoCmd1 = getControladorFonia().buscaLinhaExtSelecionadaAtual();
/* 537 */             region1 = botaoCmd1.getRegiaoLuzIndicativa();
/* 538 */             if (region1 != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 545 */               botaoCmd1.setTimerAtivado(true);
/* 546 */               Platform.runLater(() -> indCanalExt.setStyle("-fx-background-color:#FAFAFA;"));
/*     */             } 
/*     */             break;
/*     */ 
/*     */           
/*     */           case 18:
/* 552 */             tb = cmdAcionado.getIdBtn();
/* 553 */             Platform.runLater(() -> tb.setStyle("-fx-background-color:orange;"));
/*     */             break;
/*     */         } 
/*     */ 
/*     */         
/* 558 */         tb = cmdAcionado.getIdBtn();
/* 559 */         Platform.runLater(() -> tb.setStyle("-fx-background-color:#FAFAFA;"));
/*     */         break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 568 */         if (tipoCanal == 6) {
/* 569 */           botaoCmd1 = getControladorFonia().buscaLinhaExtSelecionadaAtual();
/* 570 */           region1 = botaoCmd1.getRegiaoLuzIndicativa();
/* 571 */           if (region1 != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 578 */             botaoCmd1.setTimerAtivado(false);
/* 579 */             Platform.runLater(() -> indCanalExt.setStyle("-fx-background-color:#20b920;"));
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 584 */         if (tipoCanal == 16) {
/* 585 */           tb = cmdAcionado.getIdBtn();
/* 586 */           Platform.runLater(() -> tb.setStyle("-fx-background-color:#20b920;"));
/*     */         } 
/*     */         break;
/*     */     }  }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ComunicacaoFonia getCommFonia() {
/* 595 */     return this.commFonia;
/*     */   }
/*     */   
/*     */   public void pttCalcado() {
/* 599 */     if (!this.pttCalcado) {
/* 600 */       this.pttCalcado = true;
/* 601 */       getCommFonia().tratarComandoIHM("PTT FISICO");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void pttDescalcado() {
/* 606 */     this.pttCalcado = false;
/* 607 */     getCommFonia().tratarComandoIHM("PTT FISICO");
/*     */   }
/*     */ 
/*     */   
/*     */   public void teclaSolta(KeyEvent event) {
/* 612 */     super.teclaSolta(event);
/* 613 */     if (this.estado == ComunicacaoFonia.EstadosCliente.INATIVO) {
/*     */       return;
/*     */     }
/* 616 */     switch (event.getCode()) {
/*     */       case PAUSE:
/* 618 */         pttDescalcado();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void teclaPressionada(KeyEvent event) {
/* 625 */     super.teclaPressionada(event);
/* 626 */     if (this.estado == ComunicacaoFonia.EstadosCliente.INATIVO) {
/*     */       return;
/*     */     }
/* 629 */     switch (event.getCode()) {
/*     */       case PAUSE:
/* 631 */         pttCalcado();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void desenharObjetos(List<ObjetoDOMINIO> listaObjetos) {
/* 638 */     if (this.estado != ComunicacaoFonia.EstadosCliente.INATIVO || !isMinimizado());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IconeSystemTray getIcone() {
/* 647 */     if (getIconeSystemTray() == null) {
/* 648 */       setIconeSystemTray(new IconeSystemTray("Fonia", "ipqm/gsd/hidra/ihm/imagens/icones/iconeFonia.png"));
/*     */     }
/* 650 */     getIconeSystemTray().setAtivo(true);
/* 651 */     getIconeSystemTray().setPisca(this.piscaIcone);
/* 652 */     getIconeSystemTray().setCorPisca(Color.ORANGE);
/*     */     
/* 654 */     return getIconeSystemTray();
/*     */   }
/*     */   
/*     */   public void setPiscaIcone(boolean piscaIcone) {
/* 658 */     this.piscaIcone = piscaIcone;
/*     */   }
/*     */ 
/*     */   
/*     */   public void minimizar() {
/* 663 */     TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(500.0D), (Node)this.titledPane);
/* 664 */     tt.setFromY(0.0D);
/* 665 */     tt.setToY(240.0D);
/* 666 */     tt.setAutoReverse(false);
/* 667 */     tt.setOnFinished(arg0 -> this.titledPane.setVisible(false));
/*     */ 
/*     */     
/* 670 */     tt.play();
/*     */   }
/*     */ 
/*     */   
/*     */   public void maximizar() {
/* 675 */     this.piscaIcone = false;
/* 676 */     this.titledPane.setVisible(true);
/* 677 */     TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(500.0D), (Node)this.titledPane);
/* 678 */     tt.setFromY(240.0D);
/* 679 */     tt.setToY(0.0D);
/* 680 */     tt.setAutoReverse(false);
/* 681 */     tt.play();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMinimizado() {
/* 686 */     return !this.titledPane.isVisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public void pedalPressionado() {
/* 691 */     pttCalcado();
/*     */   }
/*     */ 
/*     */   
/*     */   public void pedalSolto() {
/* 696 */     pttDescalcado();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isAgrupado() {
/* 701 */     return true;
/*     */   }
/*     */   
/*     */   public Tocador getTocadorTeste() {
/* 705 */     return this.tocadorTeste;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public IconeSystemTray getIconeSystemTray() {
/* 712 */     return this.iconeSystemTray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIconeSystemTray(IconeSystemTray iconeSystemTray) {
/* 719 */     this.iconeSystemTray = iconeSystemTray;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ControladorFonia getControladorFonia() {
/* 726 */     return this.controladorFonia;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setControladorFonia(ControladorFonia controladorFonia) {
/* 733 */     this.controladorFonia = controladorFonia;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tocador getTocadorFoniaChamando() {
/* 740 */     return this.tocadorFoniaChamando;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTocarFoniaChamando() {
/* 747 */     return this.tocarFoniaChamando;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTocarFoniaChamando(int tocarFoniaChamando) {
/* 754 */     this.tocarFoniaChamando = tocarFoniaChamando;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/fonia/WidgetFonia.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */