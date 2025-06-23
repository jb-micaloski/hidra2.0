/*     */ package ipqm.gsd.hidra.ihm.widgets.fonia_cisne;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.configuracao.fonia.LinhaInterna;
/*     */ import ipqm.gsd.componentes.nucleo.Maquina;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.anotacao_interface.EnumFiltros;
/*     */ import ipqm.gsd.componentes.nucleo.comunicacao_fonia.ComponenteFoniaIHM;
/*     */ import ipqm.gsd.componentes.nucleo.comunicacao_fonia.ComunicacaoFonia;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.notificador.Notificador;
/*     */ import ipqm.gsd.componentes.nucleo.relogio_simulado.RelogioSimulado;
/*     */ import ipqm.gsd.hidra.ihm.util.FiltroTexto;
/*     */ import ipqm.gsd.hidra.ihm.util.UtilDesempenho;
/*     */ import java.net.URL;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import javafx.animation.TranslateTransition;
/*     */ import javafx.application.Platform;
/*     */ import javafx.beans.property.SimpleStringProperty;
/*     */ import javafx.beans.value.ObservableValue;
/*     */ import javafx.collections.FXCollections;
/*     */ import javafx.collections.ObservableList;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.event.Event;
/*     */ import javafx.event.EventHandler;
/*     */ import javafx.fxml.FXML;
/*     */ import javafx.fxml.Initializable;
/*     */ import javafx.geometry.Pos;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.Label;
/*     */ import javafx.scene.control.Slider;
/*     */ import javafx.scene.control.TableCell;
/*     */ import javafx.scene.control.TableColumn;
/*     */ import javafx.scene.control.TableView;
/*     */ import javafx.scene.control.TextArea;
/*     */ import javafx.scene.control.TextField;
/*     */ import javafx.scene.control.Tooltip;
/*     */ import javafx.scene.image.Image;
/*     */ import javafx.scene.image.ImageView;
/*     */ import javafx.scene.input.KeyCode;
/*     */ import javafx.scene.input.KeyEvent;
/*     */ import javafx.scene.input.MouseButton;
/*     */ import javafx.scene.input.MouseEvent;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ import javafx.scene.layout.VBox;
/*     */ import javafx.util.Callback;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ControladorFoniaCisne
/*     */   implements Initializable
/*     */ {
/*     */   @FXML
/*     */   private Slider VolumeSlider;
/*     */   @FXML
/*     */   private AnchorPane foniaBloqueada;
/*     */   @FXML
/*     */   private Label labelIndicacaoFoniaBloqueada;
/*     */   @FXML
/*     */   private Button iniciarChat;
/*     */   @FXML
/*     */   private AnchorPane painelChat;
/*     */   @FXML
/*     */   private Label ClientesConversando;
/*     */   @FXML
/*     */   private TextArea textAreaReceberChat;
/*     */   @FXML
/*     */   private TextField textFildEnviarChat;
/*     */   @FXML
/*     */   private Button chatEnviar;
/*     */   @FXML
/*     */   private AnchorPane FoniaCompleta;
/*     */   @FXML
/*     */   private Button fecharChat;
/*     */   @FXML
/*     */   private AnchorPane anchorPaneFoniaCisne;
/*     */   @FXML
/*     */   private Button botaoVolume;
/*     */   @FXML
/*     */   private Button botaoTesteFoneOuvido;
/*     */   @FXML
/*     */   private Button botaoTesteMicrofone;
/*     */   @FXML
/*     */   private TableView<ObjetoLinhaFonia> tableViewLinhas;
/*     */   @FXML
/*     */   private TableColumn<ObjetoLinhaFonia, String> colunaEstado;
/*     */   @FXML
/*     */   private TableColumn<ObjetoLinhaFonia, String> colunaLinha;
/*     */   private ObservableList<ObjetoLinhaFonia> listaLinhasInternas;
/*     */   private WidgetFoniaCisne widgetFoniaCisne;
/*     */   private boolean ConsoleInstrutor = false;
/* 104 */   private final Map<String, ComponenteFoniaIHM> componentesFoniaIHM = new ConcurrentHashMap<>();
/*     */   
/*     */   private int volumeAtual;
/*     */   
/*     */   public static final int VOLUME_MAXIMO_EM_DB = 30;
/*     */   
/*     */   public static final int VOLUME_MINIMO_EM_DB = -30;
/*     */   
/*     */   private String nomeApresentacaoMaquinaLocal;
/*     */   
/*     */   private boolean primeiraConexaoChat = true;
/*     */   
/*     */   private static final String DESLIGADO = "ipqm/gsd/hidra/ihm/imagens/icones/phoneVermelho.png";
/*     */   
/*     */   private static final String CHAMANDO = "ipqm/gsd/hidra/ihm/imagens/icones/phoneVermelhoChamando.gif";
/*     */   
/*     */   private static final String FALANDO = "ipqm/gsd/hidra/ihm/imagens/icones/phoneVerde.png";
/*     */   private static final String RECEBENDO = "ipqm/gsd/hidra/ihm/imagens/icones/phoneVermelhoRecebendo.gif";
/*     */   private int tocarFoniaChamando;
/*     */   
/*     */   public void initialize(URL url, ResourceBundle rb) {
/* 125 */     this.textAreaReceberChat.setEditable(false);
/*     */     
/* 127 */     FiltroTexto.filtrarTexto(this.textFildEnviarChat, EnumFiltros.TipoCampo.TEXTO_QUINHENTOS);
/* 128 */     this.painelChat.setVisible(false);
/* 129 */     this.textFildEnviarChat.setDisable(true);
/*     */     
/* 131 */     this.listaLinhasInternas = FXCollections.observableArrayList();
/* 132 */     this.colunaEstado.setCellValueFactory(cellData -> ((ObjetoLinhaFonia)cellData.getValue()).getEstado());
/* 133 */     this.colunaLinha.setCellValueFactory(cellData -> ((ObjetoLinhaFonia)cellData.getValue()).getLinha());
/*     */     
/* 135 */     this.VolumeSlider.setMax(30.0D);
/* 136 */     this.VolumeSlider.setMin(-30.0D);
/* 137 */     this.VolumeSlider.setBlockIncrement(1.0D);
/* 138 */     this.VolumeSlider.setVisible(false);
/* 139 */     setVolumeAtual(0);
/*     */     
/* 141 */     Maquina maquinaLocal = Maquina.getMaquinaLocal();
/* 142 */     setNomeApresentacaoMaquinaLocal(maquinaLocal.getTitulo());
/* 143 */     List<LinhaInterna> linhasInternas = obterLinhasInternasBancoDados(maquinaLocal);
/* 144 */     Iterator<LinhaInterna> it = linhasInternas.iterator();
/* 145 */     while (it.hasNext()) {
/* 146 */       LinhaInterna linhaInterna = it.next();
/* 147 */       ComponenteFoniaIHM componenteFoniaIHM = new ComponenteFoniaIHM(linhaInterna.getHostName(), 2);
/* 148 */       componenteFoniaIHM.setNomeApresentacao(linhaInterna.getNome());
/* 149 */       getComponentesFoniaIHM().put(componenteFoniaIHM.getNomeCanal(), componenteFoniaIHM);
/* 150 */       this.listaLinhasInternas.add(new ObjetoLinhaFonia(componenteFoniaIHM));
/*     */     } 
/*     */     
/* 153 */     getComponentesFoniaIHM().put("MIC TESTE", new ComponenteFoniaIHM(this.botaoTesteMicrofone.getText(), 16));
/* 154 */     this.botaoTesteMicrofone.setTooltip(new Tooltip("BRANCO->teste desligado | VERMELHO->mudo | VERDE->captando"));
/* 155 */     this.componentesFoniaIHM.put("CHAT", new ComponenteFoniaIHM("CHAT", 12));
/*     */     
/* 157 */     this.tableViewLinhas.getItems().addAll((Collection)this.listaLinhasInternas);
/*     */     
/* 159 */     this.tableViewLinhas.setOnMouseClicked(new EventHandler<MouseEvent>()
/*     */         {
/*     */           public void handle(MouseEvent mouseEvent) {
/* 162 */             if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
/* 163 */               ComponenteFoniaIHM componenteFoniaIHM = ((ControladorFoniaCisne.ObjetoLinhaFonia)ControladorFoniaCisne.this.tableViewLinhas.getSelectionModel().getSelectedItem()).getComponenteFoniaIHM();
/* 164 */               ControladorFoniaCisne.this.widgetFoniaCisne.getCommFonia().tratarComandoIHM(componenteFoniaIHM.getNomeCanal());
/*     */             } 
/*     */           }
/*     */         });
/* 168 */     atualizaTabela();
/*     */   }
/*     */   
/*     */   private List<LinhaInterna> obterLinhasInternasBancoDados(Maquina maquinaLocal) {
/* 172 */     List<LinhaInterna> linhasInternas = new ArrayList<>();
/* 173 */     LinhaInterna linha = new LinhaInterna();
/*     */     try {
/* 175 */       linhasInternas = linha.obterListaLinhasInternasDoGrupoMaquinasParaConfigPadraoPorMaquina(maquinaLocal);
/* 176 */     } catch (SQLException ex) {
/* 177 */       Log.gravarLogExcecao("Erro ao carregar as Linhas Internas da configuração padrão da fonia do Banco Dados", this, ex);
/*     */     } 
/* 179 */     return linhasInternas;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void notificaIHM(ComponenteFoniaIHM componenteFoniaIHM) {
/* 186 */     int tipoCanal = componenteFoniaIHM.getTipoCanal();
/*     */     
/* 188 */     switch (tipoCanal) {
/*     */       
/*     */       case 2:
/* 191 */         if (this.widgetFoniaCisne.isMinimizado());
/*     */ 
/*     */         
/* 194 */         if (getPainelChat().isVisible()) {
/* 195 */           Platform.runLater(() -> this.fecharChat.setId("fecharChatPisca"));
/*     */         }
/*     */ 
/*     */         
/* 199 */         Platform.runLater(() -> atualizaTabela());
/*     */ 
/*     */ 
/*     */         
/* 203 */         controlaComponentesGraficos(componenteFoniaIHM);
/*     */         break;
/*     */       
/*     */       case 16:
/* 207 */         switch (componenteFoniaIHM.getEstadoCanal()) {
/*     */           
/*     */           case 3:
/* 210 */             Platform.runLater(() -> this.botaoTesteMicrofone.setId("botaoTesteMicrofoneVermelho"));
/*     */             break;
/*     */ 
/*     */           
/*     */           case 4:
/* 215 */             Platform.runLater(() -> this.botaoTesteMicrofone.setId("botaoTesteMicrofoneVerde"));
/*     */             break;
/*     */ 
/*     */           
/*     */           case 0:
/* 220 */             Platform.runLater(() -> this.botaoTesteMicrofone.setId("botaoTesteMicrofoneBranco"));
/*     */             break;
/*     */         } 
/*     */ 
/*     */         
/*     */         break;
/*     */       
/*     */       case 8:
/* 228 */         switch (componenteFoniaIHM.getEstadoCanal()) {
/*     */           case 5:
/* 230 */             this.widgetFoniaCisne.setEstado(componenteFoniaIHM.getClienteAtivo());
/* 231 */             if (this.widgetFoniaCisne.getEstado() == ComunicacaoFonia.EstadosCliente.INATIVO) {
/*     */               
/* 233 */               ativarFoniaBloqueada("COMUNICAÇÃO INATIVA");
/* 234 */               if (this.widgetFoniaCisne.getIconeSystemTray() != null) {
/* 235 */                 this.widgetFoniaCisne.getIconeSystemTray().setNome("Fonia Inativa");
/* 236 */                 this.widgetFoniaCisne.getIconeSystemTray().setImagem("ipqm/gsd/hidra/ihm/imagens/icones/iconeFoniaInativa.png");
/* 237 */                 this.widgetFoniaCisne.getIcone().atualiza();
/*     */               } 
/*     */             } 
/* 240 */             if (this.widgetFoniaCisne.getEstado() == ComunicacaoFonia.EstadosCliente.ATIVO) {
/*     */               
/* 242 */               desativarFoniaBloqueada("COMUNICAÇÃO ATIVA");
/* 243 */               if (this.widgetFoniaCisne.getIconeSystemTray() != null) {
/* 244 */                 this.widgetFoniaCisne.getIconeSystemTray().setNome("Fonia Ativa");
/* 245 */                 this.widgetFoniaCisne.getIconeSystemTray().setImagem("ipqm/gsd/hidra/ihm/imagens/icones/iconeFonia.png");
/* 246 */                 this.widgetFoniaCisne.getIcone().atualiza();
/*     */               } 
/*     */             } 
/* 249 */             if (this.widgetFoniaCisne.getEstado() == ComunicacaoFonia.EstadosCliente.FALHA) {
/* 250 */               desativarFoniaBloqueada("COMUNICAÇÃO INSATÁVEL");
/* 251 */               if (this.widgetFoniaCisne.getIconeSystemTray() != null) {
/* 252 */                 this.widgetFoniaCisne.getIconeSystemTray().setNome("Fonia Instável");
/* 253 */                 this.widgetFoniaCisne.getIconeSystemTray().setImagem("ipqm/gsd/hidra/ihm/imagens/icones/iconeFoniaAlerta.png");
/* 254 */                 this.widgetFoniaCisne.getIcone().atualiza();
/*     */               } 
/*     */             } 
/*     */             break;
/*     */         } 
/*     */         
/*     */         break;
/*     */       case 12:
/* 262 */         if (componenteFoniaIHM.getEstadoCanal() == 7) {
/* 263 */           Platform.runLater(() -> {
/*     */                 getTextAreaReceberChat().clear();
/*     */                 getClientesConversando().setText("");
/*     */                 getTextFildEnviarChat().setDisable(true);
/*     */               });
/*     */           break;
/*     */         } 
/* 270 */         if (!getPainelChat().isVisible() || this.widgetFoniaCisne.isMinimizado()) {
/* 271 */           Notificador.informacao("CHAT", obterPreviewMensagemChat(componenteFoniaIHM.getNomeCanal()));
/* 272 */           Platform.runLater(() -> this.iniciarChat.setId("iniciarChatPisca"));
/*     */         } 
/*     */ 
/*     */         
/* 276 */         if (this.widgetFoniaCisne.isMinimizado());
/*     */ 
/*     */         
/* 279 */         Platform.runLater(() -> getTextAreaReceberChat().appendText(componenteFoniaIHM.getNomeCanal() + "\n"));
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String obterPreviewMensagemChat(String mensagemChat) {
/* 287 */     int tam = mensagemChat.length();
/* 288 */     if (tam > 60) {
/* 289 */       tam = 60;
/*     */     }
/* 291 */     return mensagemChat.substring(5, tam) + "...";
/*     */   }
/*     */ 
/*     */   
/*     */   private void controlaComponentesGraficos(ComponenteFoniaIHM componenteFoniaIHM) {
/*     */     String clintesAntigos;
/* 297 */     Long horaMilisegundos = Long.valueOf(Mediador.getRelogio().getHorario());
/* 298 */     Calendar c = Calendar.getInstance();
/* 299 */     c.setTimeInMillis(horaMilisegundos.longValue());
/*     */     
/* 301 */     switch (componenteFoniaIHM.getEstadoCanal()) {
/*     */       
/*     */       case 2:
/* 304 */         if (componenteFoniaIHM.getUltimaMensagem() != 13) {
/* 305 */           setTocarFoniaChamando(getTocarFoniaChamando() - 1);
/*     */         }
/* 307 */         if (componenteFoniaIHM.getUltimaMensagem() == 13 && (
/* 308 */           this.widgetFoniaCisne.isMinimizado() || getPainelChat().isVisible())) {
/* 309 */           Notificador.informacao("FONIA", componenteFoniaIHM.getNomeApresentacao() + " aceitou a chamada");
/*     */         }
/*     */ 
/*     */         
/* 313 */         clintesAntigos = getClientesConversando().getText() + componenteFoniaIHM.getNomeApresentacao() + " ";
/* 314 */         Platform.runLater(() -> {
/*     */               getClientesConversando().setText(clintesAntigos);
/*     */               getTextFildEnviarChat().setDisable(false);
/*     */               getTextFildEnviarChat().requestFocus();
/*     */             });
/*     */         break;
/*     */ 
/*     */       
/*     */       case 3:
/* 323 */         if (componenteFoniaIHM.getUltimaMensagem() == 16) {
/* 324 */           setTocarFoniaChamando(getTocarFoniaChamando() - 1);
/* 325 */           if (this.widgetFoniaCisne.isMinimizado() || getPainelChat().isVisible()) {
/* 326 */             Notificador.informacao("FONIA", componenteFoniaIHM.getNomeApresentacao() + " cancelou a chamada");
/*     */           }
/*     */         } 
/* 329 */         if (componenteFoniaIHM.getUltimaMensagem() == 18 && (
/* 330 */           this.widgetFoniaCisne.isMinimizado() || getPainelChat().isVisible())) {
/* 331 */           Notificador.informacao("FONIA", componenteFoniaIHM.getNomeApresentacao() + " desligou");
/*     */         }
/*     */ 
/*     */         
/* 335 */         clintesAntigos = getClientesConversando().getText().replace(componenteFoniaIHM.getNomeApresentacao() + " ", "");
/* 336 */         Platform.runLater(() -> {
/*     */               if (clintesAntigos.equalsIgnoreCase("")) {
/*     */                 getTextFildEnviarChat().setDisable(true);
/*     */               }
/*     */               if (this.primeiraConexaoChat) {
/*     */                 getClientesConversando().setText(clintesAntigos);
/*     */                 this.primeiraConexaoChat = false;
/*     */               } else {
/*     */                 getClientesConversando().setText(clintesAntigos);
/*     */               } 
/*     */             });
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 13:
/* 352 */         if (this.widgetFoniaCisne.isMinimizado() || getPainelChat().isVisible()) {
/* 353 */           Notificador.informacao("FONIA", componenteFoniaIHM.getNomeApresentacao() + " está chamando");
/*     */         }
/* 355 */         setTocarFoniaChamando(getTocarFoniaChamando() + 1);
/*     */         break;
/*     */     } 
/* 358 */     if (getTocarFoniaChamando() > 0) {
/*     */       
/* 360 */       if (!getWidgetFoniaCisne().getTocadorFoniaChamando().reproduzindo().booleanValue())
/*     */       {
/* 362 */         getWidgetFoniaCisne().getTocadorFoniaChamando().reproduzirIndefinidamente();
/*     */       }
/*     */     } else {
/*     */       
/* 366 */       getWidgetFoniaCisne().getTocadorFoniaChamando().pararReproducao();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isConsoleInstrutor() {
/* 375 */     return this.ConsoleInstrutor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setConsoleInstrutor(boolean ConsoleInstrutor) {
/* 382 */     this.ConsoleInstrutor = ConsoleInstrutor;
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void HabilitarTesteMicrofone(MouseEvent event) {
/* 387 */     getWidgetFoniaCisne().getCommFonia().tratarComandoIHM("MIC TESTE");
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void TestarFoneOuvido(MouseEvent event) {
/* 392 */     getWidgetFoniaCisne().getTocadorTesteFone().reproduzir();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WidgetFoniaCisne getWidgetFoniaCisne() {
/* 399 */     return this.widgetFoniaCisne;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWidgetFoniaCisne(WidgetFoniaCisne widgetFoniaCisne) {
/* 406 */     this.widgetFoniaCisne = widgetFoniaCisne;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, ComponenteFoniaIHM> getComponentesFoniaIHM() {
/* 413 */     return this.componentesFoniaIHM;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVolumeAtual() {
/* 420 */     return this.volumeAtual;
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void SetarVolume(MouseEvent event) {
/* 425 */     setVolumeAtual((int)this.VolumeSlider.getValue());
/* 426 */     this.widgetFoniaCisne.getCommFonia().tratarComandoIHM("VOLUME#" + getVolumeAtual());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVolumeAtual(int volumeAtual) {
/* 433 */     this.volumeAtual = volumeAtual;
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void HabilitarSlider(MouseEvent event) {
/* 438 */     if (this.VolumeSlider.isVisible()) {
/* 439 */       this.VolumeSlider.setVisible(false);
/*     */     } else {
/* 441 */       Platform.runLater(() -> this.VolumeSlider.setValue(getVolumeAtual()));
/*     */ 
/*     */       
/* 444 */       this.VolumeSlider.setVisible(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Label getLabelIndicacaoFoniaBloqueada() {
/* 452 */     return this.labelIndicacaoFoniaBloqueada;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLabelIndicacaoFoniaBloqueada(Label labelIndicacaoFoniaBloqueada) {
/* 460 */     this.labelIndicacaoFoniaBloqueada = labelIndicacaoFoniaBloqueada;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnchorPane getFoniaBloqueada() {
/* 467 */     return this.foniaBloqueada;
/*     */   }
/*     */   
/*     */   public void ativarFoniaBloqueada(String mensagem) {
/* 471 */     Platform.runLater(() -> {
/*     */           getFoniaBloqueada().setVisible(true);
/*     */           getFoniaBloqueada().toFront();
/*     */           getLabelIndicacaoFoniaBloqueada().setText(mensagem);
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public void desativarFoniaBloqueada(String mensagem) {
/* 480 */     Platform.runLater(() -> {
/*     */           getFoniaBloqueada().setVisible(false);
/*     */           getFoniaBloqueada().toBack();
/*     */           getLabelIndicacaoFoniaBloqueada().setText(mensagem);
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   @FXML
/*     */   private void IniciarChat(MouseEvent event) {
/* 490 */     getPainelChat().setVisible(true);
/* 491 */     getPainelChat().toFront();
/* 492 */     TranslateTransition tt2 = new TranslateTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)getPainelChat());
/* 493 */     tt2.setFromX(400.0D);
/* 494 */     tt2.setToX(0.0D);
/* 495 */     tt2.setAutoReverse(false);
/* 496 */     tt2.play();
/*     */     
/* 498 */     getTextFildEnviarChat().requestFocus();
/* 499 */     this.iniciarChat.setId("iniciarChat");
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void FecharChat(MouseEvent event) {
/* 504 */     TranslateTransition tt2 = new TranslateTransition(UtilDesempenho.duracaoAnimacao(500.0D), (Node)getPainelChat());
/* 505 */     tt2.setFromX(0.0D);
/* 506 */     tt2.setToX(400.0D);
/* 507 */     tt2.setAutoReverse(false);
/* 508 */     tt2.setOnFinished(arg0 -> {
/*     */           getPainelChat().setVisible(false);
/*     */           this.fecharChat.setId("fecharChat");
/*     */           getPainelChat().toBack();
/*     */         });
/* 513 */     tt2.play();
/*     */   }
/*     */   
/*     */   private void EnviarChat() {
/* 517 */     String textoEnviar = getTextFildEnviarChat().getText();
/* 518 */     if (textoEnviar.length() > 0) {
/* 519 */       this.widgetFoniaCisne.getCommFonia().tratarComandoIHM("CHAT#" + getNomeApresentacaoMaquinaLocal() + ": " + textoEnviar);
/* 520 */       getTextFildEnviarChat().setText("");
/*     */       
/* 522 */       Long horaMilisegundos = Long.valueOf(Mediador.getRelogio().getHorario());
/* 523 */       Calendar c = Calendar.getInstance();
/* 524 */       c.setTimeInMillis(horaMilisegundos.longValue());
/* 525 */       getTextAreaReceberChat().appendText(RelogioSimulado.converteCalendar2Hora(c).substring(0, 5) + " " + getNomeApresentacaoMaquinaLocal() + " (Eu)" + ": " + textoEnviar + "\n");
/*     */     } 
/* 527 */     getTextFildEnviarChat().requestFocus();
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void EnviarMsgChat(MouseEvent event) {
/* 532 */     EnviarChat();
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void EnviarMsgChatEnter(KeyEvent event) {
/* 537 */     if (event.getCode().equals(KeyCode.ENTER)) {
/* 538 */       EnviarChat();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnchorPane getPainelChat() {
/* 546 */     return this.painelChat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TextField getTextFildEnviarChat() {
/* 553 */     return this.textFildEnviarChat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNomeApresentacaoMaquinaLocal() {
/* 560 */     return this.nomeApresentacaoMaquinaLocal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNomeApresentacaoMaquinaLocal(String nomeApresentacaoMaquinaLocal) {
/* 568 */     this.nomeApresentacaoMaquinaLocal = nomeApresentacaoMaquinaLocal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TextArea getTextAreaReceberChat() {
/* 575 */     return this.textAreaReceberChat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Label getClientesConversando() {
/* 582 */     return this.ClientesConversando;
/*     */   }
/*     */   
/*     */   private void atualizaTabela() {
/* 586 */     this.colunaEstado.setCellFactory(new Callback<TableColumn<ObjetoLinhaFonia, String>, TableCell<ObjetoLinhaFonia, String>>()
/*     */         {
/*     */           public TableCell<ControladorFoniaCisne.ObjetoLinhaFonia, String> call(TableColumn<ControladorFoniaCisne.ObjetoLinhaFonia, String> param) {
/* 589 */             TableCell<ControladorFoniaCisne.ObjetoLinhaFonia, String> cell = new ControladorFoniaCisne.ColunaEstadoTableCell();
/* 590 */             return cell;
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTocarFoniaChamando() {
/* 600 */     return this.tocarFoniaChamando;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTocarFoniaChamando(int tocarFoniaChamando) {
/* 607 */     this.tocarFoniaChamando = tocarFoniaChamando;
/*     */   }
/*     */   
/*     */   private class ColunaEstadoTableCell
/*     */     extends TableCell<ObjetoLinhaFonia, String> {
/*     */     private final ImageView art;
/*     */     private final VBox vbox;
/*     */     
/*     */     public ColunaEstadoTableCell() {
/* 616 */       this.vbox = new VBox();
/* 617 */       this.vbox.setAlignment(Pos.CENTER);
/* 618 */       this.art = new ImageView();
/* 619 */       this.art.setFitHeight(24.0D);
/* 620 */       this.art.setFitWidth(24.0D);
/* 621 */       this.art.setVisible(true);
/* 622 */       this.vbox.setVisible(false);
/* 623 */       this.vbox.setVisible(true);
/* 624 */       this.vbox.getChildren().addAll((Object[])new Node[] { (Node)this.art });
/* 625 */       setGraphic((Node)this.vbox);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void updateItem(String t, boolean bln) {
/* 633 */       if (t != null) {
/*     */         String file; Image imagem;
/* 635 */         int estado = Integer.valueOf(t).intValue();
/*     */         
/* 637 */         switch (estado) {
/*     */           case 3:
/* 639 */             file = "ipqm/gsd/hidra/ihm/imagens/icones/phoneVermelho.png";
/* 640 */             imagem = new Image(file);
/* 641 */             this.art.setImage(imagem);
/*     */             break;
/*     */           case 13:
/* 644 */             file = "ipqm/gsd/hidra/ihm/imagens/icones/phoneVermelhoRecebendo.gif";
/* 645 */             imagem = new Image(file);
/* 646 */             this.art.setImage(imagem);
/*     */             break;
/*     */           case 14:
/* 649 */             file = "ipqm/gsd/hidra/ihm/imagens/icones/phoneVermelhoChamando.gif";
/* 650 */             imagem = new Image(file);
/* 651 */             this.art.setImage(imagem);
/*     */             break;
/*     */           case 2:
/* 654 */             file = "ipqm/gsd/hidra/ihm/imagens/icones/phoneVerde.png";
/* 655 */             imagem = new Image(file);
/* 656 */             this.art.setImage(imagem);
/*     */             break;
/*     */         } 
/*     */       } else {
/* 660 */         this.art.setImage(null);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public class ObjetoLinhaFonia
/*     */   {
/*     */     private final ComponenteFoniaIHM componenteFoniaIHM;
/*     */     private final SimpleStringProperty estado;
/*     */     private final SimpleStringProperty linha;
/*     */     
/*     */     public ObjetoLinhaFonia(ComponenteFoniaIHM componenteFoniaIHM) {
/* 673 */       this.componenteFoniaIHM = componenteFoniaIHM;
/* 674 */       this.estado = new SimpleStringProperty();
/* 675 */       this.linha = new SimpleStringProperty();
/*     */     }
/*     */     
/*     */     public SimpleStringProperty getEstado() {
/* 679 */       String e = String.valueOf(this.componenteFoniaIHM.getEstadoCanal());
/* 680 */       this.estado.set(e);
/* 681 */       return this.estado;
/*     */     }
/*     */     
/*     */     public SimpleStringProperty getLinha() {
/* 685 */       this.linha.setValue(this.componenteFoniaIHM.getNomeApresentacao());
/* 686 */       return this.linha;
/*     */     }
/*     */     
/*     */     public ComponenteFoniaIHM getComponenteFoniaIHM() {
/* 690 */       return this.componenteFoniaIHM;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/fonia_cisne/ControladorFoniaCisne.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */