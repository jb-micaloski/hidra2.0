/*      */ package ipqm.gsd.hidra.ihm.widgets.fonia;
/*      */ 
/*      */ import ipqm.gsd.componentes.dominio.configuracao.fonia.ComunicacaoPontoPonto;
/*      */ import ipqm.gsd.componentes.dominio.configuracao.fonia.ConfiguracaoFonia;
/*      */ import ipqm.gsd.componentes.dominio.configuracao.fonia.LinhaExterna;
/*      */ import ipqm.gsd.componentes.dominio.configuracao.fonia.LinhaInterna;
/*      */ import ipqm.gsd.componentes.nucleo.Maquina;
/*      */ import ipqm.gsd.componentes.nucleo.Mediador;
/*      */ import ipqm.gsd.componentes.nucleo.ThreadPool;
/*      */ import ipqm.gsd.componentes.nucleo.anotacao_interface.EnumFiltros;
/*      */ import ipqm.gsd.componentes.nucleo.comunicacao_fonia.ComponenteFoniaIHM;
/*      */ import ipqm.gsd.componentes.nucleo.log.Log;
/*      */ import ipqm.gsd.componentes.nucleo.notificador.Notificacao;
/*      */ import ipqm.gsd.componentes.nucleo.notificador.Notificador;
/*      */ import ipqm.gsd.componentes.nucleo.relogio_simulado.RelogioSimulado;
/*      */ import ipqm.gsd.componentes.nucleo.util.GenericComparator;
/*      */ import ipqm.gsd.componentes.nucleo.util.TipoOrdenacao;
/*      */ import ipqm.gsd.hidra.ihm.util.FiltroTexto;
/*      */ import ipqm.gsd.hidra.ihm.util.UtilDesempenho;
/*      */ import java.net.URL;
/*      */ import java.sql.SQLException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.ResourceBundle;
/*      */ import java.util.concurrent.ConcurrentHashMap;
/*      */ import java.util.concurrent.ScheduledFuture;
/*      */ import java.util.concurrent.TimeUnit;
/*      */ import java.util.function.Supplier;
/*      */ import java.util.stream.Collectors;
/*      */ import javafx.animation.TranslateTransition;
/*      */ import javafx.application.Platform;
/*      */ import javafx.event.ActionEvent;
/*      */ import javafx.event.Event;
/*      */ import javafx.event.EventHandler;
/*      */ import javafx.fxml.FXML;
/*      */ import javafx.fxml.Initializable;
/*      */ import javafx.scene.Node;
/*      */ import javafx.scene.control.ContextMenu;
/*      */ import javafx.scene.control.Label;
/*      */ import javafx.scene.control.Menu;
/*      */ import javafx.scene.control.MenuItem;
/*      */ import javafx.scene.control.Slider;
/*      */ import javafx.scene.control.TextArea;
/*      */ import javafx.scene.control.TextField;
/*      */ import javafx.scene.control.ToggleButton;
/*      */ import javafx.scene.control.Tooltip;
/*      */ import javafx.scene.input.KeyCode;
/*      */ import javafx.scene.input.KeyEvent;
/*      */ import javafx.scene.input.MouseButton;
/*      */ import javafx.scene.input.MouseEvent;
/*      */ import javafx.scene.layout.AnchorPane;
/*      */ import javafx.scene.layout.Region;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ControladorFonia
/*      */   implements Initializable
/*      */ {
/*      */   @FXML
/*      */   private AnchorPane anchorPaneFonia;
/*      */   @FXML
/*      */   private AnchorPane FoniaCompleta;
/*      */   private WidgetFonia widgetFonia;
/*      */   @FXML
/*      */   private ContextMenu SubMenuLinExt1;
/*      */   @FXML
/*      */   private ContextMenu SubMenuLinExt2;
/*      */   @FXML
/*      */   private ContextMenu SubMenuLinExt3;
/*      */   @FXML
/*      */   private ContextMenu SubMenuLinExt4;
/*      */   @FXML
/*      */   private ContextMenu SubMenuLinExt5;
/*      */   @FXML
/*      */   private ContextMenu SubMenuLinInt1;
/*      */   @FXML
/*      */   private ContextMenu SubMenuLinInt2;
/*      */   @FXML
/*      */   private ContextMenu SubMenuLinInt3;
/*      */   @FXML
/*      */   private ContextMenu SubMenuLinInt4;
/*      */   @FXML
/*      */   private ContextMenu SubMenuLinInt5;
/*      */   @FXML
/*      */   private ContextMenu SubMenuLinInt6;
/*      */   @FXML
/*      */   private ContextMenu SubMenuLinInt7;
/*      */   @FXML
/*      */   private ContextMenu SubMenuLinInt8;
/*      */   @FXML
/*      */   private ContextMenu SubMenuLinInt9;
/*      */   @FXML
/*      */   private ContextMenu SubMenuLinInt10;
/*      */   @FXML
/*      */   private ContextMenu SubMenuLinVigia;
/*      */   public boolean linIntGrupSAltoF2Selec;
/*      */   public boolean linIntGrupSAltoF3Selec;
/*      */   public boolean inicColor = true;
/*      */   public boolean inicColor1 = true;
/*      */   public boolean timerAtivo = false;
/*      */   private boolean consoleInstrutor = false;
/*      */   private ScheduledFuture timer;
/*  150 */   private Map<String, String> mapaLinhasInternas = new HashMap<>();
/*  151 */   private Map<String, Map<String, String>> mapaLinhasVigia = new HashMap<>();
/*      */   
/*  153 */   private final List<String> listaLinhasExternas = new ArrayList<>();
/*      */   
/*  155 */   private final List<ToggleButton> listaBotoesLinhasInternas = new ArrayList<>();
/*  156 */   private final List<ToggleButton> listaBotoesLinhasExternas = new ArrayList<>();
/*  157 */   private final List<ToggleButton> listaBotoesLinhasVigia = new ArrayList<>();
/*  158 */   private final List<Region> listaIndicativoLinhasExternas = new ArrayList<>();
/*      */   
/*  160 */   private final List<BotaoCmd> listaBtCmdLinhasExternas = new ArrayList<>();
/*  161 */   private final List<BotaoCmd> listaBtCmdLinhasInternas = new ArrayList<>();
/*  162 */   private final List<BotaoCmd> listaBtCmdLinhasVigia = new ArrayList<>();
/*      */ 
/*      */   
/*  165 */   private final List<ContextMenu> listaContextMenuLinExternas = new ArrayList<>();
/*  166 */   private final List<ContextMenu> listaContextMenuLinInternas = new ArrayList<>();
/*  167 */   private final List<ContextMenu> listaContextMenuLinVigia = new ArrayList<>();
/*      */ 
/*      */   
/*  170 */   private final Map<String, ComponenteFoniaIHM> componentesFoniaIHM = new ConcurrentHashMap<>();
/*      */   
/*  172 */   private final BotaoCmd btTesteMicrofone = new BotaoCmd();
/*  173 */   private final BotaoCmd btCommGeral = new BotaoCmd();
/*  174 */   private final BotaoCmd btAcenderLuzes = new BotaoCmd();
/*  175 */   private final BotaoCmd btHandsFree = new BotaoCmd();
/*  176 */   private final BotaoCmd btExtComms = new BotaoCmd();
/*  177 */   private final BotaoCmd btIntComms = new BotaoCmd();
/*  178 */   private final BotaoCmd btCommVigia = new BotaoCmd();
/*  179 */   private BotaoCmd btIniciarChat = new BotaoCmd();
/*  180 */   private BotaoCmd btFecharChat = new BotaoCmd();
/*      */   
/*      */   private String nomeEstacao;
/*      */   
/*      */   private ToggleButton cmdAcenderLuzes;
/*      */   
/*      */   private int volumeAtual;
/*      */   
/*      */   public static final int VOLUME_MAXIMO_EM_DB = 5;
/*      */   
/*      */   public static final int VOLUME_MINIMO_EM_DB = -30;
/*      */   
/*      */   public static final int MAX_SIZE_MSG = 10;
/*      */   
/*      */   @FXML
/*      */   private Region IndicativoLinhaExtSelec1;
/*      */   @FXML
/*      */   private Region IndicativoLinhaExtSelec2;
/*      */   @FXML
/*      */   private Region IndicativoLinhaExtSelec3;
/*      */   @FXML
/*      */   private Region IndicativoLinhaExtSelec4;
/*      */   @FXML
/*      */   private Region IndicativoLinhaExtSelec5;
/*      */   @FXML
/*      */   private ToggleButton btnCmdComunGeral;
/*      */   @FXML
/*      */   private ToggleButton CmdEstadoMicrofone;
/*      */   @FXML
/*      */   private ToggleButton CmdHandsFree;
/*      */   @FXML
/*      */   private ToggleButton btnCmdVigia;
/*      */   @FXML
/*      */   private ToggleButton btnGrupada2;
/*      */   @FXML
/*      */   private Slider VolumeSlider;
/*      */   @FXML
/*      */   private ToggleButton CmdVolume;
/*      */   @FXML
/*      */   private TextField textFildEnviarChat;
/*      */   @FXML
/*      */   private TextArea textAreaReceberChat;
/*      */   @FXML
/*      */   private AnchorPane painelChat;
/*      */   @FXML
/*      */   private ToggleButton fecharChat;
/*      */   @FXML
/*      */   private ToggleButton iniciarChat;
/*      */   @FXML
/*      */   private Label ClientesConversando;
/*      */   @FXML
/*      */   private AnchorPane foniaBloqueada;
/*      */   @FXML
/*      */   private Label IndicacaoGeralCubiculo;
/*      */   @FXML
/*      */   private ToggleButton btnCmdLinhaExt1;
/*      */   @FXML
/*      */   private ToggleButton btnCmdLinhaExt2;
/*      */   @FXML
/*      */   private ToggleButton btnCmdLinhaExt3;
/*      */   @FXML
/*      */   private ToggleButton btnCmdLinhaExt4;
/*      */   @FXML
/*      */   private ToggleButton btnCmdLinhaExt5;
/*      */   @FXML
/*      */   private ToggleButton btnCmdLinhaInt1;
/*      */   @FXML
/*      */   private ToggleButton btnCmdLinhaInt2;
/*      */   @FXML
/*      */   private ToggleButton btnCmdLinhaInt3;
/*      */   @FXML
/*      */   private ToggleButton btnCmdLinhaInt4;
/*      */   @FXML
/*      */   private ToggleButton btnCmdLinhaInt5;
/*      */   @FXML
/*      */   private ToggleButton btnCmdLinhaInt6;
/*      */   @FXML
/*      */   private ToggleButton btnCmdLinhaInt7;
/*      */   @FXML
/*      */   private ToggleButton btnCmdLinhaInt8;
/*      */   @FXML
/*      */   private ToggleButton btnCmdLinhaInt9;
/*      */   @FXML
/*      */   private ToggleButton btnCmdLinhaInt10;
/*      */   
/*      */   @FXML
/*      */   private void ComandarLinExt1(MouseEvent event) {
/*  267 */     tratarSelecaoLinhaExterna(0, event);
/*      */   }
/*      */   
/*      */   @FXML
/*      */   private void ComandarLinExt2(MouseEvent event) {
/*  272 */     tratarSelecaoLinhaExterna(1, event);
/*      */   }
/*      */   
/*      */   @FXML
/*      */   private void ComandarLinExt3(MouseEvent event) {
/*  277 */     tratarSelecaoLinhaExterna(2, event);
/*      */   }
/*      */   
/*      */   @FXML
/*      */   private void ComandarLinExt4(MouseEvent event) {
/*  282 */     tratarSelecaoLinhaExterna(3, event);
/*      */   }
/*      */   
/*      */   @FXML
/*      */   private void ComandarLinExt5(MouseEvent event) {
/*  287 */     tratarSelecaoLinhaExterna(4, event);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void AtivarTimerApresLuzCmds() {
/*  295 */     this.timer = ThreadPool.agendarExecucaoPeriodica(() -> { String corLinhaInterna; String corLinhaExterna; String corLinhaVigia; String corIniciarChat; String corFecharChat; if (this.inicColor) { corLinhaExterna = "-fx-background-color:#FAFAFA;"; corLinhaInterna = "-fx-background-color:#FAFAFA;"; corLinhaVigia = "-fx-background-color:orange;"; corIniciarChat = "-fx-background-color:darkgray;"; corFecharChat = "fecharChat"; this.inicColor = false; } else { corLinhaExterna = "-fx-background-color:darkgray;"; corLinhaInterna = "-fx-background-color:#F3CA1D;"; corLinhaVigia = "-fx-background-color:tomato;"; corIniciarChat = "-fx-background-color:#F3CA1D;"; corFecharChat = "fecharChatAmarelo"; this.inicColor = true; }  BotaoCmd bt = buscaLinhaExtSelecionadaAtual(); if (bt != null && bt.GetTimerAtivado()) { Region indCanalExt = bt.getRegiaoLuzIndicativa(); if (indCanalExt != null) Platform.runLater(());  }  Platform.runLater(()); if (this.listaBtCmdLinhasVigia != null && !this.listaBtCmdLinhasVigia.isEmpty()) Platform.runLater(());  if (this.btIniciarChat.GetTimerAtivado()) Platform.runLater(());  if (this.btFecharChat.GetTimerAtivado()) Platform.runLater(());  }1L, TimeUnit.SECONDS, "Piscar luzes da fonia");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @FXML
/*      */   private void ComandarLinInt1(MouseEvent event) {
/*  366 */     tratarSelecaoLinhaInterna(0, event);
/*      */   }
/*      */   
/*      */   @FXML
/*      */   private void ComandarLinInt2(MouseEvent event) {
/*  371 */     tratarSelecaoLinhaInterna(1, event);
/*      */   }
/*      */   
/*      */   @FXML
/*      */   private void ComandarLinInt3(MouseEvent event) {
/*  376 */     tratarSelecaoLinhaInterna(2, event);
/*      */   }
/*      */   
/*      */   @FXML
/*      */   private void ComandarLinInt4(MouseEvent event) {
/*  381 */     tratarSelecaoLinhaInterna(3, event);
/*      */   }
/*      */   
/*      */   @FXML
/*      */   private void ComandarLinInt5(MouseEvent event) {
/*  386 */     tratarSelecaoLinhaInterna(4, event);
/*      */   }
/*      */   
/*      */   @FXML
/*      */   private void ComandarLinInt6(MouseEvent event) {
/*  391 */     tratarSelecaoLinhaInterna(5, event);
/*      */   }
/*      */   
/*      */   @FXML
/*      */   private void ComandarLinInt7(MouseEvent event) {
/*  396 */     tratarSelecaoLinhaInterna(6, event);
/*      */   }
/*      */   
/*      */   @FXML
/*      */   private void ComandarLinInt8(MouseEvent event) {
/*  401 */     tratarSelecaoLinhaInterna(7, event);
/*      */   }
/*      */   
/*      */   @FXML
/*      */   private void ComandarLinInt9(MouseEvent event) {
/*  406 */     tratarSelecaoLinhaInterna(8, event);
/*      */   }
/*      */   
/*      */   @FXML
/*      */   private void ComandarLinInt10(MouseEvent event) {
/*  411 */     tratarSelecaoLinhaInterna(9, event);
/*      */   }
/*      */   
/*      */   @FXML
/*      */   private void ComandarComunGeral(MouseEvent event) {
/*  416 */     if (event.getEventType() == MouseEvent.MOUSE_PRESSED || event.getEventType() == MouseEvent.MOUSE_RELEASED) {
/*  417 */       this.widgetFonia.getCommFonia().tratarComandoIHM(this.btnCmdComunGeral.getText());
/*      */     }
/*      */   }
/*      */   
/*      */   @FXML
/*      */   private void ComandarHandsFree(MouseEvent event) {
/*  423 */     this.widgetFonia.getCommFonia().tratarComandoIHM(this.CmdHandsFree.getText());
/*      */   }
/*      */   
/*      */   @FXML
/*      */   private void ComandarGrupoVigia(MouseEvent event) {
/*  428 */     if (this.listaBtCmdLinhasVigia != null && !this.listaBtCmdLinhasVigia.isEmpty() && (
/*  429 */       (BotaoCmd)this.listaBtCmdLinhasVigia.get(0)).GetStatusBtnConfig() && 
/*  430 */       event.getButton() == MouseButton.PRIMARY) {
/*  431 */       this.widgetFonia.getCommFonia().tratarComandoIHM(((BotaoCmd)this.listaBtCmdLinhasVigia.get(0)).getHostName());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   @FXML
/*      */   private void HabilitarTesteMicrofone(MouseEvent event) {
/*  439 */     this.widgetFonia.getCommFonia().tratarComandoIHM(this.CmdEstadoMicrofone.getText());
/*      */   }
/*      */ 
/*      */   
/*      */   private void carregaAcao(MenuItem itemSelecionadoSubMenu, BotaoCmd btComandoLinha, ContextMenu contextMenu, List<ContextMenu> listaContextMenu, int IdentificaçãoComando, List<BotaoCmd> listaObjsLinhasNaIHM) {
/*  444 */     MenuItem itemIHM = new MenuItem();
/*      */     
/*  446 */     if (!btComandoLinha.GetStatusBtn()) {
/*      */       
/*  448 */       String labelItemSelecionadoSubMenu = itemSelecionadoSubMenu.getText();
/*      */       
/*  450 */       itemIHM.setText(btComandoLinha.GetTextoForeground());
/*  451 */       itemIHM.setId(btComandoLinha.GetTextoForeground());
/*      */       
/*  453 */       int ind = contextMenu.getItems().indexOf(itemSelecionadoSubMenu);
/*  454 */       itemSelecionadoSubMenu.setText(btComandoLinha.GetTextoForeground());
/*  455 */       btComandoLinha.getListaSubOpcoes().set(ind, itemIHM);
/*      */ 
/*      */       
/*  458 */       for (int k = 0; k < listaContextMenu.size(); k++) {
/*      */         
/*  460 */         if (((BotaoCmd)listaObjsLinhasNaIHM.get(k)).GetStatusBtnConfig() && 
/*  461 */           k != IdentificaçãoComando) {
/*      */ 
/*      */           
/*  464 */           BotaoCmd botaoCmd = listaObjsLinhasNaIHM.get(k);
/*  465 */           botaoCmd.getListaSubOpcoes().clear();
/*  466 */           botaoCmd.getListaSubOpcoes().addAll(btComandoLinha.getListaSubOpcoes());
/*      */           
/*  468 */           ContextMenu TabelaSubOpcoes = listaContextMenu.get(k);
/*      */           
/*  470 */           for (int i = 0; i < btComandoLinha.getListaSubOpcoes().size(); i++) {
/*  471 */             ((MenuItem)TabelaSubOpcoes.getItems().get(i)).setText(((MenuItem)contextMenu.getItems().get(i)).getText());
/*      */           }
/*      */         } 
/*      */       } 
/*      */ 
/*      */       
/*  477 */       String hostNameBotaoSubMenu = null;
/*  478 */       if (btComandoLinha.GetCodTipoLinha() == 2)
/*      */       {
/*  480 */         for (String hostName : this.mapaLinhasInternas.keySet()) {
/*  481 */           if (((String)this.mapaLinhasInternas.get(hostName)).equalsIgnoreCase(labelItemSelecionadoSubMenu)) {
/*  482 */             hostNameBotaoSubMenu = hostName;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */       }
/*  488 */       ((BotaoCmd)listaObjsLinhasNaIHM.get(IdentificaçãoComando)).SetTextoForeground(labelItemSelecionadoSubMenu);
/*      */       
/*  490 */       btComandoLinha.SetTextoForeground(labelItemSelecionadoSubMenu);
/*  491 */       if (btComandoLinha.GetCodTipoLinha() == 2) {
/*  492 */         btComandoLinha.setHostName(hostNameBotaoSubMenu);
/*      */       }
/*      */       
/*  495 */       btComandoLinha.getIdBtn().setText(labelItemSelecionadoSubMenu);
/*      */ 
/*      */       
/*  498 */       Platform.runLater(() -> {
/*      */             switch (btComandoLinha.GetCodTipoLinha()) {
/*      */               case 2:
/*      */                 btComandoLinha.getIdBtn().setTooltip(new Tooltip("LINHA INTERNA " + btComandoLinha.getIdBtn().getText()));
/*      */                 this.widgetFonia.getCommFonia().tratarComandoIHM("LINHA_INTERNA#" + btComandoLinha.getHostName());
/*      */                 break;
/*      */               case 1:
/*      */                 btComandoLinha.getIdBtn().setTooltip(new Tooltip("LINHA EXTERNA " + btComandoLinha.getIdBtn().getText()));
/*      */                 break;
/*      */             } 
/*      */           });
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void apresentaEGerenciaSubItensComandos(List<String> ListaLabelsLinhasTotais, final List<BotaoCmd> ListaObjsLinhasNaIHM, final BotaoCmd BtComandoLinha, final ContextMenu SubItensLinhaComunicacao, final List<ContextMenu> ListaContextMenu, final int IdentificaçãoComando) {
/*  543 */     boolean jaExisteNaIhm = false;
/*      */     int i;
/*  545 */     for (i = 0; i < ListaLabelsLinhasTotais.size(); i++) {
/*      */ 
/*      */ 
/*      */       
/*  549 */       String labelBD = ListaLabelsLinhasTotais.get(i);
/*      */       
/*  551 */       if (!labelBD.equals(BtComandoLinha.GetTextoForeground())) {
/*      */         
/*  553 */         MenuItem itemSelecionado = BtComandoLinha.getListaSubOpcoes().get(i);
/*      */ 
/*      */         
/*  556 */         for (int k = 0; k < ListaObjsLinhasNaIHM.size(); k++) {
/*  557 */           String LabelIHM = ((BotaoCmd)ListaObjsLinhasNaIHM.get(k)).GetTextoForeground();
/*      */           
/*  559 */           if (labelBD.equals(LabelIHM)) {
/*  560 */             jaExisteNaIhm = true;
/*      */ 
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */         
/*  567 */         if (!jaExisteNaIhm) {
/*  568 */           SubItensLinhaComunicacao.getItems().add(itemSelecionado);
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*  573 */         jaExisteNaIhm = false;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  578 */     BtComandoLinha.getListaSubOpcoes().clear();
/*      */     
/*  580 */     for (i = 0; i < SubItensLinhaComunicacao.getItems().size(); i++) {
/*      */       
/*  582 */       final MenuItem subItem = (MenuItem)SubItensLinhaComunicacao.getItems().get(i);
/*  583 */       BtComandoLinha.getListaSubOpcoes().add(subItem);
/*      */       
/*  585 */       ((MenuItem)BtComandoLinha.getListaSubOpcoes().get(i)).setOnAction(new EventHandler<ActionEvent>()
/*      */           {
/*      */             public void handle(ActionEvent event) {
/*  588 */               ControladorFonia.this.carregaAcao(subItem, BtComandoLinha, SubItensLinhaComunicacao, ListaContextMenu, IdentificaçãoComando, ListaObjsLinhasNaIHM);
/*      */             }
/*      */           });
/*      */     } 
/*      */   }
/*      */   
/*      */   private void apresentaGerenciaVigia(final BotaoCmd BtComandoLinha, ContextMenu SubItensLinhaComunicacao) {
/*  595 */     SubItensLinhaComunicacao.getItems().clear();
/*      */     
/*  597 */     for (String hostNameGrupo : this.mapaLinhasVigia.keySet()) {
/*  598 */       final Map<String, String> mapaLinhas = this.mapaLinhasVigia.get(hostNameGrupo);
/*  599 */       Menu menu = new Menu(hostNameGrupo);
/*      */       
/*  601 */       for (String hostnameLinha : mapaLinhas.keySet()) {
/*  602 */         MenuItem item = new MenuItem();
/*  603 */         item.setText(mapaLinhas.get(hostnameLinha));
/*  604 */         item.setId(mapaLinhas.get(hostnameLinha));
/*  605 */         item.setOnAction(new EventHandler<ActionEvent>()
/*      */             {
/*      */               public void handle(ActionEvent event) {
/*  608 */                 for (ComponenteFoniaIHM componenteFoniaIHM : ControladorFonia.this.componentesFoniaIHM.values()) {
/*      */                   
/*  610 */                   if (BtComandoLinha.getHostName().equals(componenteFoniaIHM.getNomeCanal()) && (componenteFoniaIHM
/*  611 */                     .getEstadoCanal() == 2 || componenteFoniaIHM.getEstadoCanal() == 14 || componenteFoniaIHM
/*  612 */                     .getEstadoCanal() == 13)) {
/*  613 */                     Notificacao n = new Notificacao("Fonia", Notificacao.Tipo.ALERTA);
/*  614 */                     n.setDescricao("Favor desconectar da linha " + BtComandoLinha.GetTextoForeground() + " para estabelecer nova conexão.");
/*  615 */                     n.setPermanente(true);
/*  616 */                     n.setExibirBotaoFechar(true);
/*  617 */                     Notificador.getInstancia().addNotificacao(n);
/*      */                     return;
/*      */                   } 
/*      */                 } 
/*  621 */                 BtComandoLinha.SetTextoForeground((String)mapaLinhas.get(hostnameLinha));
/*  622 */                 BtComandoLinha.setHostName(hostnameLinha);
/*  623 */                 BtComandoLinha.getIdBtn().setText((String)mapaLinhas.get(hostnameLinha));
/*  624 */                 ControladorFonia.this.widgetFonia.getCommFonia().tratarComandoIHM("LINHA_INTERNA#" + hostnameLinha);
/*      */               }
/*      */             });
/*  627 */         menu.getItems().add(item);
/*      */       } 
/*  629 */       SubItensLinhaComunicacao.getItems().add(menu);
/*      */     } 
/*  631 */     BtComandoLinha.getListaSubOpcoes().clear();
/*  632 */     BtComandoLinha.getListaSubOpcoes().addAll((Collection<? extends MenuItem>)SubItensLinhaComunicacao.getItems());
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void escreverLabelCmdLinhaExt() {
/*  638 */     for (int i = 0; i < this.listaBtCmdLinhasExternas.size(); i++) {
/*      */       
/*  640 */       BotaoCmd botaoCmd = this.listaBtCmdLinhasExternas.get(i);
/*  641 */       if (botaoCmd.GetStatusBtnConfig()) {
/*  642 */         botaoCmd.getIdBtn().setText(botaoCmd.GetTextoForeground());
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void escreverLabelCmdLinhaInt() {
/*  650 */     for (int i = 0; i < this.listaBtCmdLinhasInternas.size(); i++) {
/*      */       
/*  652 */       BotaoCmd linInterna = this.listaBtCmdLinhasInternas.get(i);
/*  653 */       if (linInterna.GetStatusBtnConfig()) {
/*  654 */         linInterna.getIdBtn().setText(linInterna.GetTextoForeground());
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private void escreverLabelCmdLinhaVigia() {
/*  660 */     for (int i = 0; i < this.listaBtCmdLinhasVigia.size(); i++) {
/*      */       
/*  662 */       BotaoCmd linVigia = this.listaBtCmdLinhasVigia.get(i);
/*  663 */       if (linVigia.GetStatusBtnConfig()) {
/*  664 */         linVigia.getIdBtn().setText(linVigia.GetTextoForeground());
/*      */       }
/*      */     } 
/*      */   }
/*      */   
/*      */   private void carregaIdsIndicativoLinhasExternas() {
/*  670 */     this.listaIndicativoLinhasExternas.add(this.IndicativoLinhaExtSelec1);
/*  671 */     this.listaIndicativoLinhasExternas.add(this.IndicativoLinhaExtSelec2);
/*  672 */     this.listaIndicativoLinhasExternas.add(this.IndicativoLinhaExtSelec3);
/*  673 */     this.listaIndicativoLinhasExternas.add(this.IndicativoLinhaExtSelec4);
/*  674 */     this.listaIndicativoLinhasExternas.add(this.IndicativoLinhaExtSelec5);
/*      */   }
/*      */   
/*      */   private void carregaBotoesLinhasExternas() {
/*  678 */     this.listaBotoesLinhasExternas.add(this.btnCmdLinhaExt1);
/*  679 */     this.listaBotoesLinhasExternas.add(this.btnCmdLinhaExt2);
/*  680 */     this.listaBotoesLinhasExternas.add(this.btnCmdLinhaExt3);
/*  681 */     this.listaBotoesLinhasExternas.add(this.btnCmdLinhaExt4);
/*  682 */     this.listaBotoesLinhasExternas.add(this.btnCmdLinhaExt5);
/*      */   }
/*      */   
/*      */   private void carregaBotoesLinhasInternas() {
/*  686 */     this.listaBotoesLinhasInternas.add(this.btnCmdLinhaInt1);
/*  687 */     this.listaBotoesLinhasInternas.add(this.btnCmdLinhaInt2);
/*  688 */     this.listaBotoesLinhasInternas.add(this.btnCmdLinhaInt3);
/*  689 */     this.listaBotoesLinhasInternas.add(this.btnCmdLinhaInt4);
/*  690 */     this.listaBotoesLinhasInternas.add(this.btnCmdLinhaInt5);
/*  691 */     this.listaBotoesLinhasInternas.add(this.btnCmdLinhaInt6);
/*  692 */     this.listaBotoesLinhasInternas.add(this.btnCmdLinhaInt7);
/*  693 */     this.listaBotoesLinhasInternas.add(this.btnCmdLinhaInt8);
/*  694 */     this.listaBotoesLinhasInternas.add(this.btnCmdLinhaInt9);
/*  695 */     this.listaBotoesLinhasInternas.add(this.btnCmdLinhaInt10);
/*      */   }
/*      */   
/*      */   private void carregaBotaoLinhaVigia() {
/*  699 */     this.listaBotoesLinhasVigia.add(this.btnCmdVigia);
/*      */   }
/*      */   
/*      */   private void carregaBotaoCmdAcenderLuzes() {
/*  703 */     this.btAcenderLuzes.SetIdBtn(this.cmdAcenderLuzes);
/*      */   }
/*      */   
/*      */   private void carregaObjCmdCommGeral() {
/*  707 */     if (this.consoleInstrutor) {
/*  708 */       this.btnCmdComunGeral.setId("CmdComunGeral");
/*  709 */       this.btCommGeral.SetIdBtn(this.btnCmdComunGeral);
/*  710 */       this.btnCmdComunGeral.setDisable(false);
/*      */     } else {
/*  712 */       this.btnCmdComunGeral.setStyle("-fx-background-color:black;");
/*  713 */       this.btnCmdComunGeral.setDisable(true);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void carregaObjCmdHandsFree() {
/*  718 */     this.btHandsFree.SetIdBtn(this.CmdHandsFree);
/*      */   }
/*      */   
/*      */   private void carregaObjCmdTesteMicrofone() {
/*  722 */     this.btTesteMicrofone.SetIdBtn(this.CmdEstadoMicrofone);
/*      */   }
/*      */ 
/*      */   
/*      */   private void carregaObjsLinhasExternas() {
/*  727 */     for (String nome : this.listaLinhasExternas) {
/*  728 */       ComponenteFoniaIHM componenteFoniaIHM = new ComponenteFoniaIHM(nome, 1);
/*  729 */       this.componentesFoniaIHM.put(nome, componenteFoniaIHM);
/*      */       
/*  731 */       if (this.listaBtCmdLinhasExternas.size() < 5) {
/*      */         
/*  733 */         BotaoCmd botaoCmd = new BotaoCmd();
/*  734 */         botaoCmd.SetCodTipoLinha(1);
/*  735 */         botaoCmd.SetTextoForeground(nome);
/*  736 */         botaoCmd.SetStatusBtnConfig(Boolean.valueOf(true));
/*      */         
/*  738 */         for (String nomeSubItem : this.listaLinhasExternas) {
/*  739 */           MenuItem item = new MenuItem();
/*  740 */           item.setText(nomeSubItem);
/*  741 */           item.setId(nomeSubItem);
/*  742 */           botaoCmd.getListaSubOpcoes().add(item);
/*      */         } 
/*      */         
/*  745 */         this.listaBtCmdLinhasExternas.add(botaoCmd);
/*  746 */         int indice = this.listaBtCmdLinhasExternas.indexOf(botaoCmd);
/*  747 */         botaoCmd.setRegiaoLuzIndicativa(this.listaIndicativoLinhasExternas.get(indice));
/*  748 */         botaoCmd.SetIdBtn(this.listaBotoesLinhasExternas.get(indice));
/*  749 */         botaoCmd.SetIndHashMap(indice);
/*      */       } 
/*      */     } 
/*      */     
/*  753 */     if (this.listaBtCmdLinhasExternas.size() < 5) {
/*  754 */       for (int i = this.listaBtCmdLinhasExternas.size(); i < 5; i++) {
/*  755 */         BotaoCmd botaoCmd = new BotaoCmd();
/*  756 */         botaoCmd.SetIdBtn(this.listaBotoesLinhasExternas.get(i));
/*  757 */         this.listaBtCmdLinhasExternas.add(botaoCmd);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private void carregaObjsLinhasInternas() {
/*  763 */     for (String hostName : this.mapaLinhasInternas.keySet()) {
/*  764 */       ComponenteFoniaIHM componenteFoniaIHM = new ComponenteFoniaIHM(hostName, 2);
/*  765 */       this.componentesFoniaIHM.put(componenteFoniaIHM.getNomeCanal(), componenteFoniaIHM);
/*      */       
/*  767 */       if (this.listaBtCmdLinhasInternas.size() < 10) {
/*      */         
/*  769 */         BotaoCmd botaoCmd = new BotaoCmd();
/*  770 */         botaoCmd.SetCodTipoLinha(2);
/*  771 */         botaoCmd.SetTextoForeground(this.mapaLinhasInternas.get(hostName));
/*  772 */         botaoCmd.setHostName(hostName);
/*  773 */         botaoCmd.SetStatusBtnConfig(Boolean.valueOf(true));
/*      */         
/*  775 */         for (String nomeSubItem : this.mapaLinhasInternas.values()) {
/*  776 */           MenuItem item = new MenuItem();
/*  777 */           item.setText(nomeSubItem);
/*  778 */           item.setId(nomeSubItem);
/*  779 */           botaoCmd.getListaSubOpcoes().add(item);
/*      */         } 
/*      */         
/*  782 */         this.listaBtCmdLinhasInternas.add(botaoCmd);
/*  783 */         int indice = this.listaBtCmdLinhasInternas.indexOf(botaoCmd);
/*  784 */         botaoCmd.SetIdBtn(this.listaBotoesLinhasInternas.get(indice));
/*  785 */         botaoCmd.SetIndHashMap(this.listaBtCmdLinhasInternas.indexOf(botaoCmd));
/*      */       } 
/*      */     } 
/*      */     
/*  789 */     if (this.listaBtCmdLinhasInternas.size() < 10) {
/*  790 */       for (int i = this.listaBtCmdLinhasInternas.size(); i < 10; i++) {
/*  791 */         BotaoCmd botaoCmd = new BotaoCmd();
/*  792 */         botaoCmd.SetCodTipoLinha(2);
/*  793 */         botaoCmd.SetIdBtn(this.listaBotoesLinhasInternas.get(i));
/*  794 */         this.listaBtCmdLinhasInternas.add(botaoCmd);
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   private void carregaDemaisBotoesRelevantesComunicacaoFonia() {
/*  800 */     this.componentesFoniaIHM.put(this.CmdEstadoMicrofone.getText(), new ComponenteFoniaIHM(this.CmdEstadoMicrofone.getText(), 16));
/*  801 */     this.componentesFoniaIHM.put(this.CmdHandsFree.getText(), new ComponenteFoniaIHM(this.CmdHandsFree.getText(), 3));
/*  802 */     this.componentesFoniaIHM.put("IND CANAL EXT", new ComponenteFoniaIHM("IND CANAL EXT", 6));
/*  803 */     this.componentesFoniaIHM.put("PTT FISICO", new ComponenteFoniaIHM("PTT FISICO", 7));
/*  804 */     this.componentesFoniaIHM.put("CHAT", new ComponenteFoniaIHM("CHAT", 12));
/*  805 */     this.componentesFoniaIHM.put(this.btnCmdComunGeral.getText(), new ComponenteFoniaIHM(this.btnCmdComunGeral.getText(), 13));
/*      */ 
/*      */     
/*  808 */     this.componentesFoniaIHM.put("CANAIS ADICIONAIS", new ComponenteFoniaIHM("CANAIS ADICIONAIS", 17));
/*      */   }
/*      */   
/*      */   public void setStatusObjLinExterna(int ind, boolean sts) {
/*  812 */     ((BotaoCmd)this.listaBtCmdLinhasExternas.get(ind)).SetStatusBtn(Boolean.valueOf(sts));
/*      */   }
/*      */   
/*      */   public void setStatusObjLinInterna(int ind, boolean sts) {
/*  816 */     ((BotaoCmd)this.listaBtCmdLinhasInternas.get(ind)).SetStatusBtn(Boolean.valueOf(sts));
/*      */   }
/*      */   
/*      */   public void setStatusObjLinVigia(int ind, boolean sts) {
/*  820 */     ((BotaoCmd)this.listaBtCmdLinhasVigia.get(ind)).SetStatusBtn(Boolean.valueOf(sts));
/*      */   }
/*      */   
/*      */   public void setStatusObjTimerLinInterna(int ind, boolean sts) {
/*  824 */     ((BotaoCmd)this.listaBtCmdLinhasInternas.get(ind)).setTimerAtivado(sts);
/*      */   }
/*      */   
/*      */   public void setStatusObjTimerLinVigia(int ind, boolean sts) {
/*  828 */     ((BotaoCmd)this.listaBtCmdLinhasVigia.get(ind)).setTimerAtivado(sts);
/*      */   }
/*      */   
/*      */   public BotaoCmd buscaLinhaExtSelecionadaAtual() {
/*  832 */     for (int i = 0; i < this.listaBtCmdLinhasExternas.size(); i++) {
/*  833 */       if (((BotaoCmd)this.listaBtCmdLinhasExternas.get(i)).GetStatusBtn()) {
/*  834 */         return this.listaBtCmdLinhasExternas.get(i);
/*      */       }
/*      */     } 
/*  837 */     return null;
/*      */   }
/*      */   
/*      */   public Map<String, ComponenteFoniaIHM> getMapComponentesFoniaIHM() {
/*  841 */     return this.componentesFoniaIHM;
/*      */   }
/*      */   
/*      */   public BotaoCmd getBtHandsFree() {
/*  845 */     return this.btHandsFree;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void initialize(URL url, ResourceBundle rb) {
/*  854 */     this.textAreaReceberChat.setEditable(false);
/*      */     
/*  856 */     FiltroTexto.filtrarTexto(this.textFildEnviarChat, EnumFiltros.TipoCampo.TEXTO_QUINHENTOS);
/*  857 */     this.painelChat.setVisible(false);
/*  858 */     this.textFildEnviarChat.setDisable(true);
/*  859 */     this.btIniciarChat.setTimerAtivado(false);
/*  860 */     this.btFecharChat.setTimerAtivado(false);
/*      */     
/*  862 */     this.VolumeSlider.setMax(5.0D);
/*  863 */     this.VolumeSlider.setMin(-30.0D);
/*  864 */     this.VolumeSlider.setBlockIncrement(1.0D);
/*  865 */     this.VolumeSlider.setVisible(false);
/*  866 */     this.volumeAtual = 0;
/*      */     
/*  868 */     AtivarTimerApresLuzCmds();
/*      */     
/*  870 */     this.FoniaCompleta.setVisible(true);
/*  871 */     this.painelChat.setVisible(false);
/*  872 */     this.foniaBloqueada.setVisible(false);
/*      */     
/*  874 */     carregaIdsIndicativoLinhasExternas();
/*  875 */     carregaBotoesLinhasExternas();
/*  876 */     carregaBotoesLinhasInternas();
/*  877 */     carregaBotaoLinhaVigia();
/*  878 */     carregaDemaisBotoesRelevantesComunicacaoFonia();
/*      */     
/*  880 */     List<ConfiguracaoFonia> listaConfigFonia = new ArrayList<>();
/*      */     try {
/*  882 */       listaConfigFonia = (new ConfiguracaoFonia()).listarTodos();
/*  883 */     } catch (Exception ex) {
/*  884 */       Log.gravarLogExcecao("Erro ao carregar configurações de fonia", this, ex);
/*      */     } 
/*  886 */     ConfiguracaoFonia configPadrao = null;
/*  887 */     for (ConfiguracaoFonia obj : listaConfigFonia) {
/*  888 */       if (obj.isConfiguracaoPadrao()) {
/*  889 */         configPadrao = obj;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*  894 */     if (configPadrao == null) {
/*  895 */       Log.gravarLogExcecao("Erro ao carregar config padrão da fonia!", this, new Exception("Nenhuma configração está configurada como padrão"));
/*      */       
/*      */       return;
/*      */     } 
/*  899 */     Iterator<LinhaExterna> iteratorExt = configPadrao.getLinhasExternas().iterator();
/*  900 */     while (iteratorExt.hasNext()) {
/*  901 */       LinhaExterna lExt = iteratorExt.next();
/*  902 */       this.listaLinhasExternas.add(lExt.getNome());
/*      */     } 
/*      */     
/*  905 */     Collections.sort(this.listaLinhasExternas);
/*      */     
/*  907 */     List<ComunicacaoPontoPonto> listaComPP = new ArrayList<>();
/*      */     try {
/*  909 */       listaComPP = configPadrao.obterComunicacaoPontoPontoByConfiguracaoFonia();
/*  910 */     } catch (Exception ex) {
/*  911 */       Log.gravarLogExcecao("Erro ao recuperar dados do banco.", this, ex);
/*      */     } 
/*      */ 
/*      */     
/*  915 */     Iterator<LinhaInterna> iteratorInt = configPadrao.getLinhasInternas().iterator();
/*  916 */     while (iteratorInt.hasNext()) {
/*  917 */       LinhaInterna lint = iteratorInt.next();
/*  918 */       if (lint.getHostName().equalsIgnoreCase(Maquina.getMaquinaLocal().getHostname())) {
/*  919 */         this.nomeEstacao = lint.getNome();
/*  920 */         this.consoleInstrutor = lint.isInstrutor();
/*  921 */         List<LinhaInterna> lista = new ArrayList<>(configPadrao.getLinhasInternas());
/*  922 */         GenericComparator.ordenarLista(lista, "nome", TipoOrdenacao.ASC);
/*  923 */         Iterator<LinhaInterna> iteratorInt2 = lista.iterator();
/*  924 */         while (iteratorInt2.hasNext()) {
/*  925 */           LinhaInterna lInt2 = iteratorInt2.next();
/*  926 */           if (lInt2.getCubiculo() == lint.getCubiculo() && !lInt2.getHostName().equalsIgnoreCase(lint.getHostName())) {
/*  927 */             this.mapaLinhasInternas.put(lInt2.getHostName(), lInt2.getNome());
/*      */           }
/*      */         } 
/*      */ 
/*      */         
/*  932 */         Iterator<ComunicacaoPontoPonto> iteratorLinIntComPP2 = listaComPP.iterator();
/*  933 */         while (iteratorLinIntComPP2.hasNext()) {
/*  934 */           ComunicacaoPontoPonto comPP = iteratorLinIntComPP2.next();
/*  935 */           LinhaInterna linhaOrigem = comPP.getLinhaInternaOrigem();
/*  936 */           LinhaInterna linhaDestino = comPP.getLinhaInternaDestino();
/*      */           
/*  938 */           if (this.consoleInstrutor && linhaOrigem.getHostName().equalsIgnoreCase(lint.getHostName())) {
/*  939 */             String nomeFantasia = linhaDestino.getHostName().substring(linhaDestino.getHostName().length() - 3);
/*  940 */             if (this.mapaLinhasVigia.containsKey("CUB" + linhaDestino.getCubiculo())) {
/*  941 */               ((Map<String, String>)this.mapaLinhasVigia.get("CUB" + linhaDestino.getCubiculo())).put(linhaDestino.getHostName(), nomeFantasia); continue;
/*      */             } 
/*  943 */             Map<Object, Object> mapaTemp = new HashMap<>();
/*  944 */             mapaTemp.put(linhaDestino.getHostName(), nomeFantasia);
/*  945 */             this.mapaLinhasVigia.put("CUB" + linhaDestino.getCubiculo(), mapaTemp); continue;
/*      */           } 
/*  947 */           if (linhaDestino.getHostName().equalsIgnoreCase(lint.getHostName())) {
/*  948 */             String nomeFantasia = "VIGIA" + linhaOrigem.getHostName().substring(linhaOrigem.getHostName().length() - 1);
/*      */             
/*  950 */             if (this.mapaLinhasVigia.containsKey("VIGIA")) {
/*  951 */               ((Map<String, String>)this.mapaLinhasVigia.get("VIGIA")).put(linhaOrigem.getHostName(), nomeFantasia); continue;
/*      */             } 
/*  953 */             Map<Object, Object> mapaTemp = new HashMap<>();
/*  954 */             mapaTemp.put(linhaOrigem.getHostName(), nomeFantasia);
/*  955 */             this.mapaLinhasVigia.put("VIGIA", mapaTemp);
/*      */           } 
/*      */         } 
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*      */     
/*  963 */     this
/*      */       
/*  965 */       .mapaLinhasInternas = (Map<String, String>)this.mapaLinhasInternas.entrySet().stream().sorted((e1, e2) -> ((String)e1.getKey()).compareTo((String)e2.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, java.util.LinkedHashMap::new));
/*      */     
/*  967 */     this
/*      */       
/*  969 */       .mapaLinhasVigia = (Map<String, Map<String, String>>)this.mapaLinhasVigia.entrySet().stream().sorted((e1, e2) -> ((String)e1.getKey()).compareTo((String)e2.getKey())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, java.util.LinkedHashMap::new));
/*      */     
/*  971 */     carregaBotaoCmdAcenderLuzes();
/*  972 */     carregaObjCmdCommGeral();
/*  973 */     carregaObjCmdHandsFree();
/*      */     
/*  975 */     carregaObjsLinhasExternas();
/*  976 */     carregaObjsLinhasInternas();
/*  977 */     carregaObjsLinhasVigia();
/*      */     
/*  979 */     carregaObjCmdTesteMicrofone();
/*      */     
/*  981 */     escreverLabelCmdLinhaExt();
/*  982 */     escreverLabelCmdLinhaInt();
/*  983 */     escreverLabelCmdLinhaVigia();
/*      */     
/*  985 */     List<String> listaLinhasInternas = new ArrayList<>(this.mapaLinhasInternas.values());
/*      */     
/*  987 */     Platform.runLater(() -> {
/*      */           int i = 0;
/*      */           
/*      */           while (i < this.listaLinhasExternas.size() && i < 5) {
/*      */             ToggleButton IdFxComando = this.listaBotoesLinhasExternas.get(i);
/*      */             
/*      */             IdFxComando.setTooltip(new Tooltip("LINHA EXTERNA " + (String)this.listaLinhasExternas.get(i)));
/*      */             i++;
/*      */           } 
/*      */           i = 0;
/*      */           while (i < listaLinhasInternas.size() && i < 10) {
/*      */             ToggleButton IdFxComando = this.listaBotoesLinhasInternas.get(i);
/*      */             IdFxComando.setTooltip(new Tooltip("LINHA INTERNA " + (String)listaLinhasInternas.get(i)));
/*      */             i++;
/*      */           } 
/*      */           this.CmdEstadoMicrofone.setTooltip(new Tooltip("CINZA->teste desligado | VERDE->transmitindo | BRANCO->mudo"));
/*      */           this.btnCmdComunGeral.setTooltip(new Tooltip("Mantenha pressionado para falar"));
/*      */         });
/* 1005 */     this.listaContextMenuLinExternas.clear();
/* 1006 */     this.listaContextMenuLinExternas.add(this.SubMenuLinExt1);
/* 1007 */     this.listaContextMenuLinExternas.add(this.SubMenuLinExt2);
/* 1008 */     this.listaContextMenuLinExternas.add(this.SubMenuLinExt3);
/* 1009 */     this.listaContextMenuLinExternas.add(this.SubMenuLinExt4);
/* 1010 */     this.listaContextMenuLinExternas.add(this.SubMenuLinExt5);
/*      */     int i;
/* 1012 */     for (i = 0; i < this.listaContextMenuLinExternas.size(); i++) {
/* 1013 */       if (((BotaoCmd)this.listaBtCmdLinhasExternas.get(i)).GetStatusBtnConfig()) {
/* 1014 */         apresentaEGerenciaSubItensComandos(this.listaLinhasExternas, this.listaBtCmdLinhasExternas, this.listaBtCmdLinhasExternas.get(i), this.listaContextMenuLinExternas.get(i), this.listaContextMenuLinExternas, i);
/*      */       }
/*      */     } 
/*      */     
/* 1018 */     this.listaContextMenuLinInternas.clear();
/* 1019 */     this.listaContextMenuLinInternas.add(this.SubMenuLinInt1);
/* 1020 */     this.listaContextMenuLinInternas.add(this.SubMenuLinInt2);
/* 1021 */     this.listaContextMenuLinInternas.add(this.SubMenuLinInt3);
/* 1022 */     this.listaContextMenuLinInternas.add(this.SubMenuLinInt4);
/* 1023 */     this.listaContextMenuLinInternas.add(this.SubMenuLinInt5);
/* 1024 */     this.listaContextMenuLinInternas.add(this.SubMenuLinInt6);
/* 1025 */     this.listaContextMenuLinInternas.add(this.SubMenuLinInt7);
/* 1026 */     this.listaContextMenuLinInternas.add(this.SubMenuLinInt8);
/* 1027 */     this.listaContextMenuLinInternas.add(this.SubMenuLinInt9);
/* 1028 */     this.listaContextMenuLinInternas.add(this.SubMenuLinInt10);
/*      */     
/* 1030 */     for (i = 0; i < this.listaContextMenuLinInternas.size(); i++) {
/* 1031 */       if (((BotaoCmd)this.listaBtCmdLinhasInternas.get(i)).GetStatusBtnConfig()) {
/* 1032 */         apresentaEGerenciaSubItensComandos(listaLinhasInternas, this.listaBtCmdLinhasInternas, this.listaBtCmdLinhasInternas.get(i), this.listaContextMenuLinInternas.get(i), this.listaContextMenuLinInternas, i);
/*      */       }
/*      */     } 
/*      */     
/* 1036 */     this.listaContextMenuLinVigia.clear();
/* 1037 */     this.listaContextMenuLinVigia.add(this.SubMenuLinVigia);
/*      */     
/* 1039 */     if (this.listaBtCmdLinhasVigia != null && !this.listaBtCmdLinhasVigia.isEmpty()) {
/* 1040 */       for (i = 0; i < this.listaContextMenuLinVigia.size(); i++) {
/* 1041 */         if (((BotaoCmd)this.listaBtCmdLinhasVigia.get(i)).GetStatusBtnConfig()) {
/* 1042 */           apresentaGerenciaVigia(this.listaBtCmdLinhasVigia.get(i), this.listaContextMenuLinVigia.get(i));
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */   
/*      */   public void setWidgetFonia(WidgetFonia widgetFonia) {
/* 1049 */     this.widgetFonia = widgetFonia;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNomeEstacao() {
/* 1056 */     return this.nomeEstacao;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BotaoCmd getBtExtComms() {
/* 1063 */     return this.btExtComms;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BotaoCmd getBtIntComms() {
/* 1070 */     return this.btIntComms;
/*      */   }
/*      */   
/*      */   @FXML
/*      */   private void HabilitarSlider(MouseEvent event) {
/* 1075 */     if (this.VolumeSlider.isVisible()) {
/* 1076 */       this.VolumeSlider.setVisible(false);
/*      */     } else {
/* 1078 */       Platform.runLater(() -> this.VolumeSlider.setValue(this.volumeAtual));
/*      */ 
/*      */       
/* 1081 */       this.VolumeSlider.setVisible(true);
/*      */     } 
/*      */   }
/*      */   
/*      */   @FXML
/*      */   private void SetarVolume(MouseEvent event) {
/* 1087 */     this.volumeAtual = (int)this.VolumeSlider.getValue();
/* 1088 */     this.widgetFonia.getCommFonia().tratarComandoIHM(this.CmdVolume.getText() + "#" + this.volumeAtual);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<BotaoCmd> getListaBtCmdLinhasInternas() {
/* 1095 */     return this.listaBtCmdLinhasInternas;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<BotaoCmd> getListaBtCmdLinhasExternas() {
/* 1102 */     return this.listaBtCmdLinhasExternas;
/*      */   }
/*      */   
/*      */   public List<BotaoCmd> getListaBtCmdLinhasVigia() {
/* 1106 */     return this.listaBtCmdLinhasVigia;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TextArea getTextAreaReceberChat() {
/* 1113 */     return this.textAreaReceberChat;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public TextField getTextFildEnviarChat() {
/* 1120 */     return this.textFildEnviarChat;
/*      */   }
/*      */ 
/*      */   
/*      */   @FXML
/*      */   private void IniciarChat(MouseEvent event) {
/* 1126 */     this.painelChat.setVisible(true);
/* 1127 */     this.painelChat.toFront();
/* 1128 */     TranslateTransition tt2 = new TranslateTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.painelChat);
/* 1129 */     tt2.setFromX(400.0D);
/* 1130 */     tt2.setToX(0.0D);
/* 1131 */     tt2.setAutoReverse(false);
/* 1132 */     tt2.play();
/*      */     
/* 1134 */     this.textFildEnviarChat.requestFocus();
/* 1135 */     this.btIniciarChat.setTimerAtivado(false);
/* 1136 */     this.iniciarChat.setStyle("-fx-background-color:darkgray;");
/*      */   }
/*      */   
/*      */   @FXML
/*      */   private void FecharChat(MouseEvent event) {
/* 1141 */     TranslateTransition tt2 = new TranslateTransition(UtilDesempenho.duracaoAnimacao(500.0D), (Node)this.painelChat);
/* 1142 */     tt2.setFromX(0.0D);
/* 1143 */     tt2.setToX(400.0D);
/* 1144 */     tt2.setAutoReverse(false);
/* 1145 */     tt2.setOnFinished(arg0 -> {
/*      */           this.painelChat.setVisible(false);
/*      */           this.btFecharChat.setTimerAtivado(false);
/*      */           this.fecharChat.setId("fecharChat");
/*      */           this.painelChat.toBack();
/*      */         });
/* 1151 */     tt2.play();
/*      */   }
/*      */   
/*      */   private void EnviarChat() {
/* 1155 */     String textoEnviar = this.textFildEnviarChat.getText();
/* 1156 */     if (textoEnviar.length() > 0) {
/* 1157 */       this.widgetFonia.getCommFonia().tratarComandoIHM("CHAT#" + this.nomeEstacao + ": " + textoEnviar);
/* 1158 */       this.textFildEnviarChat.setText("");
/*      */       
/* 1160 */       Long horaMilisegundos = Long.valueOf(Mediador.getRelogio().getHorario());
/* 1161 */       Calendar c = Calendar.getInstance();
/* 1162 */       c.setTimeInMillis(horaMilisegundos.longValue());
/* 1163 */       this.textAreaReceberChat.appendText(RelogioSimulado.converteCalendar2Hora(c).substring(0, 5) + " " + this.nomeEstacao + " (Eu)" + ": " + textoEnviar + "\n");
/*      */     } 
/* 1165 */     this.textFildEnviarChat.requestFocus();
/*      */   }
/*      */   
/*      */   @FXML
/*      */   private void EnviarMsgChat(MouseEvent event) {
/* 1170 */     EnviarChat();
/*      */   }
/*      */   
/*      */   @FXML
/*      */   private void EnviarMsgChatEnter(KeyEvent event) {
/* 1175 */     if (event.getCode().equals(KeyCode.ENTER)) {
/* 1176 */       EnviarChat();
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public AnchorPane getPainelChat() {
/* 1184 */     return this.painelChat;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Label getClientesConversando() {
/* 1191 */     return this.ClientesConversando;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BotaoCmd getBtIniciarChat() {
/* 1198 */     return this.btIniciarChat;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BotaoCmd getBtFecharChat() {
/* 1205 */     return this.btFecharChat;
/*      */   }
/*      */   
/*      */   public void ativarFoniaBloqueada(String mensagem) {
/* 1209 */     Platform.runLater(() -> {
/*      */           this.foniaBloqueada.setVisible(true);
/*      */           this.foniaBloqueada.toFront();
/*      */           this.IndicacaoGeralCubiculo.setText(mensagem);
/*      */         });
/*      */   }
/*      */   
/*      */   public void desativarFoniaBloqueada(String mensagem) {
/* 1217 */     Platform.runLater(() -> {
/*      */           this.foniaBloqueada.setVisible(false);
/*      */           this.foniaBloqueada.toBack();
/*      */           this.IndicacaoGeralCubiculo.setText(mensagem);
/*      */         });
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BotaoCmd getBtTesteMicrofone() {
/* 1228 */     return this.btTesteMicrofone;
/*      */   }
/*      */   
/*      */   @FXML
/*      */   private void TestarFoneOuvido(MouseEvent event) {
/* 1233 */     this.widgetFonia.getTocadorTeste().reproduzir();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void processarRequisicaoCanaisAdicionais(ComponenteFoniaIHM componenteFoniaIHM) {
/* 1241 */     List<String> lista = null;
/* 1242 */     LinhaExterna linhaE = new LinhaExterna();
/* 1243 */     String canaisAdicionais = "";
/*      */     
/*      */     try {
/* 1246 */       lista = linhaE.obterListaNomesLinhasExternasConfiguracaoPadraoOrdenadoPorID();
/* 1247 */     } catch (SQLException ex) {
/* 1248 */       Log.gravarLogExcecao("Erro ao carregar as Linhas Externas da configuração padrão da fonia do Banco Dados", this, ex);
/*      */     } 
/* 1250 */     if (lista == null || lista.isEmpty()) {
/*      */       return;
/*      */     }
/*      */     
/* 1254 */     for (String canal : lista) {
/* 1255 */       canaisAdicionais = canaisAdicionais.concat(canal + "#");
/*      */     }
/* 1257 */     componenteFoniaIHM.setNomeApresentacao(canaisAdicionais);
/* 1258 */     this.widgetFonia.getCommFonia().tratarComandoIHM(componenteFoniaIHM.getNomeCanal());
/*      */   }
/*      */   
/*      */   private void tratarSelecaoLinhaExterna(int identificador, MouseEvent event) {
/* 1262 */     if (((BotaoCmd)this.listaBtCmdLinhasExternas.get(identificador)).GetStatusBtnConfig() && 
/* 1263 */       event.getButton() == MouseButton.PRIMARY) {
/* 1264 */       this.widgetFonia.getCommFonia().tratarComandoIHM(((BotaoCmd)this.listaBtCmdLinhasExternas.get(identificador)).getIdBtn().getText());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private void tratarSelecaoLinhaInterna(int identificador, MouseEvent event) {
/* 1270 */     if (((BotaoCmd)this.listaBtCmdLinhasInternas.get(identificador)).GetStatusBtnConfig() && 
/* 1271 */       event.getButton() == MouseButton.PRIMARY) {
/* 1272 */       this.widgetFonia.getCommFonia().tratarComandoIHM(((BotaoCmd)this.listaBtCmdLinhasInternas.get(identificador)).getHostName());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void carregaObjsLinhasVigia() {
/* 1279 */     for (String hostNameGrupo : this.mapaLinhasVigia.keySet()) {
/*      */       
/* 1281 */       if (this.listaBtCmdLinhasVigia.size() < 1) {
/*      */         
/* 1283 */         BotaoCmd botaoCmd = new BotaoCmd();
/* 1284 */         botaoCmd.SetCodTipoLinha(18);
/* 1285 */         botaoCmd.SetTextoForeground("VIGIA");
/* 1286 */         botaoCmd.setHostName("VIGIA");
/*      */ 
/*      */         
/* 1289 */         botaoCmd.SetStatusBtnConfig(Boolean.valueOf(true));
/* 1290 */         this.listaBtCmdLinhasVigia.add(botaoCmd);
/* 1291 */         int indice = this.listaBtCmdLinhasVigia.indexOf(botaoCmd);
/* 1292 */         botaoCmd.SetIdBtn(this.listaBotoesLinhasVigia.get(indice));
/* 1293 */         botaoCmd.SetIndHashMap(this.listaBtCmdLinhasVigia.indexOf(botaoCmd));
/*      */       } 
/* 1295 */       Map<String, String> mapaLinhas = this.mapaLinhasVigia.get(hostNameGrupo);
/*      */       
/* 1297 */       for (String hostnameSubItem : mapaLinhas.keySet()) {
/* 1298 */         ComponenteFoniaIHM componenteFoniaIHM = new ComponenteFoniaIHM(hostnameSubItem, 18);
/* 1299 */         this.componentesFoniaIHM.put(componenteFoniaIHM.getNomeCanal(), componenteFoniaIHM);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public Map<String, String> getMapaLinhasInternas() {
/* 1306 */     return this.mapaLinhasInternas;
/*      */   }
/*      */   
/*      */   public Map<String, Map<String, String>> getMapaLinhasVigia() {
/* 1310 */     return this.mapaLinhasVigia;
/*      */   }
/*      */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/fonia/ControladorFonia.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */