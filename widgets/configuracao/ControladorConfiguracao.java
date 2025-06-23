/*     */ package ipqm.gsd.hidra.ihm.widgets.configuracao;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.Maquina;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.configuracao.ConfiguracaoIHM;
/*     */ import ipqm.gsd.componentes.nucleo.notificador.Notificacao;
/*     */ import ipqm.gsd.componentes.nucleo.notificador.Notificador;
/*     */ import ipqm.gsd.hidra.ihm.dispositivos.controladores_jogo.GestorDispositivos;
/*     */ import ipqm.gsd.hidra.ihm.dispositivos.controladores_jogo.ship_console.ShipConsoleController;
/*     */ import ipqm.gsd.hidra.ihm.util.UtilDesempenho;
/*     */ import ipqm.gsd.hidra.ihm.widgets.Widget;
/*     */ import java.net.URL;
/*     */ import java.util.ResourceBundle;
/*     */ import javafx.animation.TranslateTransition;
/*     */ import javafx.application.Platform;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.fxml.FXML;
/*     */ import javafx.fxml.Initializable;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.Label;
/*     */ import javafx.scene.control.ListCell;
/*     */ import javafx.scene.control.ListView;
/*     */ import javafx.scene.control.RadioButton;
/*     */ import javafx.scene.control.TitledPane;
/*     */ import javafx.scene.image.ImageView;
/*     */ import javafx.scene.input.MouseEvent;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ import javafx.stage.Modality;
/*     */ import javafx.stage.Stage;
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
/*     */ public class ControladorConfiguracao
/*     */   implements Initializable
/*     */ {
/*     */   @FXML
/*     */   private AnchorPane painelConfiguracao;
/*     */   private WidgetConfiguracao widgetConfiguracao;
/*     */   @FXML
/*     */   private ListView<Notificacao> listViewNotificacoes;
/*     */   @FXML
/*     */   private AnchorPane fundoPretoModal;
/*     */   @FXML
/*     */   private Label labelMaquina;
/*     */   @FXML
/*     */   private Button botaoMaisAjuda;
/*     */   @FXML
/*     */   private Button botaoCalibrarShipConsole;
/*     */   @FXML
/*     */   private TitledPane titledPaneConfigDistancia;
/*     */   @FXML
/*     */   private TitledPane titledPaneConfigCoordGeo;
/*     */   @FXML
/*     */   private TitledPane titledPaneConfigVelocidade;
/*     */   @FXML
/*     */   private TitledPane titledPaneConfigVelocidadeVento;
/*     */   @FXML
/*     */   private TitledPane titledPaneConfigDistanciaPrecisa;
/*     */   @FXML
/*     */   private TitledPane titledPaneConfigProfundidadeAltitude;
/*     */   @FXML
/*     */   private TitledPane titledPaneConfigTemperatura;
/*     */   @FXML
/*     */   private RadioButton unidadeMN;
/*     */   @FXML
/*     */   private RadioButton unidadeJD;
/*     */   @FXML
/*     */   private RadioButton unidadeKM;
/*     */   @FXML
/*     */   private RadioButton exibicaoCoordGeoGMS;
/*     */   @FXML
/*     */   private RadioButton exibicaoCoordGeoGDM;
/*     */   @FXML
/*     */   private RadioButton unidadeKNVento;
/*     */   @FXML
/*     */   private RadioButton unidadeKMHVento;
/*     */   @FXML
/*     */   private RadioButton unidadeMSVento;
/*     */   @FXML
/*     */   private RadioButton unidadeYDDistPrecisa;
/*     */   @FXML
/*     */   private RadioButton unidadeMDistPrecisa;
/*     */   @FXML
/*     */   private RadioButton unidadeKNVeloc;
/*     */   @FXML
/*     */   private RadioButton unidadeKMHVeloc;
/*     */   @FXML
/*     */   private RadioButton unidadeFTProfAlt;
/*     */   @FXML
/*     */   private RadioButton unidadeMProfAlt;
/*     */   @FXML
/*     */   private RadioButton unidadeFTemperatura;
/*     */   @FXML
/*     */   private RadioButton unidadeCTemperatura;
/*     */   
/*     */   public void initialize(URL url, ResourceBundle rb) {
/* 105 */     this.listViewNotificacoes.setCellFactory(param -> new IconListCell());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public WidgetConfiguracao getWidgetConfiguracao() {
/* 111 */     return this.widgetConfiguracao;
/*     */   }
/*     */   
/*     */   public void setWidgetConfiguracao(WidgetConfiguracao widgetConfiguracao) {
/* 115 */     this.widgetConfiguracao = widgetConfiguracao;
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void acaoFechar(ActionEvent event) {
/* 120 */     ShipConsoleController.setCALIBRAR(false);
/* 121 */     this.widgetConfiguracao.minimizar();
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void acaoTrocarExibicaoCoordGeo(ActionEvent event) {
/* 126 */     ConfiguracaoIHM.FormatoExibicaoCoordenadaGeografica formatoExibicaoCoordenadaGeografica = ConfiguracaoIHM.FormatoExibicaoCoordenadaGeografica.GDM;
/* 127 */     if (this.exibicaoCoordGeoGDM.isSelected()) {
/* 128 */       formatoExibicaoCoordenadaGeografica = ConfiguracaoIHM.FormatoExibicaoCoordenadaGeografica.GDM;
/* 129 */     } else if (this.exibicaoCoordGeoGMS.isSelected()) {
/* 130 */       formatoExibicaoCoordenadaGeografica = ConfiguracaoIHM.FormatoExibicaoCoordenadaGeografica.GMS;
/*     */     } 
/* 132 */     Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoIHM().setFormatoExibicaoCoordGeoPadrao(formatoExibicaoCoordenadaGeografica);
/*     */   }
/*     */   
/*     */   public void desenharObjetos() {
/* 136 */     if (this.listViewNotificacoes.getItems().size() != Notificador.getInstancia().getNotificacoesAntigas().size()) {
/* 137 */       Platform.runLater(() -> {
/*     */             this.listViewNotificacoes.getItems().clear();
/*     */             this.listViewNotificacoes.getItems().addAll(Notificador.getInstancia().getNotificacoesAntigas());
/*     */           });
/*     */     }
/*     */   }
/*     */   
/*     */   public void exibirIHM() {
/* 145 */     this.labelMaquina.setText(Maquina.getMaquinaLocal().toString());
/*     */     
/* 147 */     if (Mediador.isSistemaIniciado()) {
/* 148 */       this.botaoMaisAjuda.setDisable(false);
/* 149 */       this.titledPaneConfigDistancia.setDisable(false);
/* 150 */       this.titledPaneConfigCoordGeo.setDisable(false);
/* 151 */       this.titledPaneConfigDistanciaPrecisa.setDisable(false);
/* 152 */       this.titledPaneConfigVelocidade.setDisable(false);
/* 153 */       this.titledPaneConfigVelocidadeVento.setDisable(false);
/* 154 */       this.titledPaneConfigProfundidadeAltitude.setDisable(false);
/* 155 */       this.titledPaneConfigTemperatura.setDisable(false);
/*     */       
/* 157 */       switch (Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoIHM().getUnidadeDistanciaPadrao()) {
/*     */         case AJUDA:
/* 159 */           this.unidadeJD.setSelected(true);
/*     */           break;
/*     */         case ERRO:
/* 162 */           this.unidadeMN.setSelected(true);
/*     */           break;
/*     */         case INFORMACAO:
/* 165 */           this.unidadeKM.setSelected(true);
/*     */           break;
/*     */       } 
/*     */       
/* 169 */       switch (Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoIHM().getFormatoExibicaoCoordGeoPadrao()) {
/*     */         case AJUDA:
/* 171 */           this.exibicaoCoordGeoGDM.setSelected(true);
/*     */           break;
/*     */         case ERRO:
/* 174 */           this.exibicaoCoordGeoGMS.setSelected(true);
/*     */           break;
/*     */       } 
/*     */       
/* 178 */       switch (Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoIHM().getUnidadeDistPrecisaPadrao()) {
/*     */         case ALERTA:
/* 180 */           this.unidadeMDistPrecisa.setSelected(true);
/*     */           break;
/*     */         case AJUDA:
/* 183 */           this.unidadeYDDistPrecisa.setSelected(true);
/*     */           break;
/*     */       } 
/*     */       
/* 187 */       switch (Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoIHM().getUnidadeVelocidadePadrao()) {
/*     */         case CARREGANDO:
/* 189 */           this.unidadeKMHVeloc.setSelected(true);
/*     */           break;
/*     */         case null:
/* 192 */           this.unidadeKNVeloc.setSelected(true);
/*     */           break;
/*     */       } 
/*     */       
/* 196 */       switch (Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoIHM().getUnidadeVelocVentoPadrao()) {
/*     */         case CARREGANDO:
/* 198 */           this.unidadeKMHVento.setSelected(true);
/*     */           break;
/*     */         case null:
/* 201 */           this.unidadeKNVento.setSelected(true);
/*     */           break;
/*     */         case null:
/* 204 */           this.unidadeMSVento.setSelected(true);
/*     */           break;
/*     */       } 
/*     */       
/* 208 */       switch (Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoIHM().getUnidadeProfAltPadrao()) {
/*     */         case null:
/* 210 */           this.unidadeFTProfAlt.setSelected(true);
/*     */           break;
/*     */         case ALERTA:
/* 213 */           this.unidadeMProfAlt.setSelected(true);
/*     */           break;
/*     */       } 
/*     */       
/* 217 */       switch (Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoIHM().getUnidadeTemperaturaPadrao()) {
/*     */         case null:
/* 219 */           this.unidadeFTemperatura.setSelected(true);
/*     */           break;
/*     */         case null:
/* 222 */           this.unidadeCTemperatura.setSelected(true);
/*     */           break;
/*     */       } 
/*     */       
/* 226 */       if (GestorDispositivos.isConectadoShipConsole()) {
/* 227 */         this.botaoCalibrarShipConsole.setVisible(true);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @FXML
/*     */   private void acaoCliqueFundoModal(MouseEvent event) {
/* 235 */     if (event.getClickCount() == 2) {
/* 236 */       this.widgetConfiguracao.minimizar();
/*     */     } else {
/* 238 */       int inicio = 0;
/* 239 */       int fim = 26;
/*     */       
/* 241 */       TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(150.0D), (Node)this.painelConfiguracao);
/* 242 */       tt.setFromX(inicio);
/* 243 */       tt.setToX(fim);
/* 244 */       tt.setOnFinished(arg0 -> {
/*     */             TranslateTransition tt2 = new TranslateTransition(UtilDesempenho.duracaoAnimacao(250.0D), (Node)this.painelConfiguracao);
/*     */             tt2.setFromX(fim);
/*     */             tt2.setToX(inicio);
/*     */             tt2.play();
/*     */           });
/* 250 */       tt.play();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public AnchorPane getPainelConfiguracao() {
/* 256 */     return this.painelConfiguracao;
/*     */   }
/*     */   
/*     */   public AnchorPane getFundoPretoModal() {
/* 260 */     return this.fundoPretoModal;
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void acaoMaisAjuda(ActionEvent event) {
/* 265 */     Widget w = this.widgetConfiguracao.getTela().getWidget(Widget.Tipo.CONFIGURACAO);
/* 266 */     if (w != null) {
/* 267 */       w.ocultar(this.widgetConfiguracao.getTela().getCenaAtual());
/*     */     }
/* 269 */     this.widgetConfiguracao.getTela().trocar("projetos/geral/cenas/Ajuda.fxml");
/*     */   }
/*     */   
/*     */   @FXML
/*     */   public void acaoBotaoCalibrarShipConsole(ActionEvent acao) {
/* 274 */     ShipConsoleController.setCALIBRAR(true);
/*     */     
/* 276 */     Stage stage = new Stage();
/* 277 */     stage.initModality(Modality.WINDOW_MODAL);
/* 278 */     CalibradorShipConsole calibrador = new CalibradorShipConsole();
/* 279 */     calibrador.start(stage);
/*     */   }
/*     */   
/*     */   private class IconListCell extends ListCell<Notificacao> {
/*     */     private IconListCell() {}
/*     */     
/*     */     public void updateItem(Notificacao notificacao, boolean empty) {
/* 286 */       super.updateItem(notificacao, empty);
/* 287 */       if (empty) {
/* 288 */         setText(null);
/* 289 */         setGraphic(null);
/*     */       } else {
/*     */         
/* 292 */         String titulo = notificacao.getTitulo();
/* 293 */         String descricao = notificacao.getDescricao();
/* 294 */         String tituloSubstituto = "";
/* 295 */         String imagem = null;
/*     */         
/* 297 */         switch (notificacao.getTipo()) {
/*     */           case AJUDA:
/* 299 */             tituloSubstituto = "Ajuda";
/* 300 */             imagem = "ipqm/gsd/hidra/ihm/imagens/notificacoes/ajuda.png";
/*     */             break;
/*     */           case ERRO:
/* 303 */             tituloSubstituto = "Erro";
/* 304 */             imagem = "ipqm/gsd/hidra/ihm/imagens/notificacoes/erro.png";
/*     */             break;
/*     */           case INFORMACAO:
/* 307 */             tituloSubstituto = "Informação";
/* 308 */             imagem = "ipqm/gsd/hidra/ihm/imagens/notificacoes/info.png";
/*     */             break;
/*     */           case ALERTA:
/* 311 */             tituloSubstituto = "Alerta";
/* 312 */             imagem = "ipqm/gsd/hidra/ihm/imagens/notificacoes/alerta.png";
/*     */             break;
/*     */           case CARREGANDO:
/* 315 */             tituloSubstituto = "Carregando";
/* 316 */             imagem = "ipqm/gsd/hidra/ihm/imagens/notificacoes/carregando.gif";
/*     */             break;
/*     */         } 
/*     */         
/* 320 */         if (titulo != null && descricao == null) {
/* 321 */           descricao = titulo;
/* 322 */           titulo = tituloSubstituto;
/* 323 */         } else if (titulo == null && descricao != null) {
/* 324 */           titulo = tituloSubstituto;
/*     */         } 
/*     */         
/* 327 */         if (notificacao.getContador() > 1) {
/* 328 */           titulo = titulo + " [+" + notificacao.getContador() + "]";
/*     */         }
/*     */         
/* 331 */         setText(titulo + "\n" + descricao);
/* 332 */         if (imagem != null) {
/* 333 */           ImageView iv = new ImageView(imagem);
/* 334 */           setGraphic((Node)iv);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void acaoTrocarUnidadeDistancia(ActionEvent event) {
/* 342 */     ConfiguracaoIHM.Unidades unidade = ConfiguracaoIHM.Unidades.KM;
/* 343 */     if (this.unidadeJD.isSelected()) {
/* 344 */       unidade = ConfiguracaoIHM.Unidades.JD;
/* 345 */     } else if (this.unidadeMN.isSelected()) {
/* 346 */       unidade = ConfiguracaoIHM.Unidades.MN;
/*     */     } 
/*     */     
/* 349 */     Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoIHM().setUnidadeDistanciaPadrao(unidade);
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void acaoTrocarUnidadeVelocidadeVento(ActionEvent event) {
/* 354 */     ConfiguracaoIHM.Unidades unidade = ConfiguracaoIHM.Unidades.KMH;
/*     */     
/* 356 */     if (this.unidadeKNVento.isSelected()) {
/* 357 */       unidade = ConfiguracaoIHM.Unidades.KN;
/* 358 */     } else if (this.unidadeMSVento.isSelected()) {
/* 359 */       unidade = ConfiguracaoIHM.Unidades.MTS;
/*     */     } 
/*     */     
/* 362 */     Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoIHM().setUnidadeVelocVentoPadrao(unidade);
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void acaoTrocarUnidadeDistanciaPrecisa(ActionEvent event) {
/* 367 */     ConfiguracaoIHM.Unidades unidade = ConfiguracaoIHM.Unidades.MT;
/*     */     
/* 369 */     if (this.unidadeYDDistPrecisa.isSelected()) {
/* 370 */       unidade = ConfiguracaoIHM.Unidades.JD;
/*     */     }
/*     */     
/* 373 */     Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoIHM().setUnidadeDistPrecisaPadrao(unidade);
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void acaoTrocarUnidadeVelocidade(ActionEvent event) {
/* 378 */     ConfiguracaoIHM.Unidades unidade = ConfiguracaoIHM.Unidades.KMH;
/*     */     
/* 380 */     if (this.unidadeKNVeloc.isSelected()) {
/* 381 */       unidade = ConfiguracaoIHM.Unidades.KN;
/*     */     }
/*     */     
/* 384 */     Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoIHM().setUnidadeVelocidadePadrao(unidade);
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void acaoTrocarUnidadeProfundidadeAltitude(ActionEvent event) {
/* 389 */     ConfiguracaoIHM.Unidades unidade = ConfiguracaoIHM.Unidades.FT;
/*     */     
/* 391 */     if (this.unidadeMProfAlt.isSelected()) {
/* 392 */       unidade = ConfiguracaoIHM.Unidades.MT;
/*     */     }
/*     */     
/* 395 */     Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoIHM().setUnidadeProfAltPadrao(unidade);
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void acaoTrocarUnidadeTemperatura(ActionEvent event) {
/* 400 */     ConfiguracaoIHM.Unidades unidade = ConfiguracaoIHM.Unidades.TPC;
/*     */     
/* 402 */     if (this.unidadeFTemperatura.isSelected()) {
/* 403 */       unidade = ConfiguracaoIHM.Unidades.TPF;
/*     */     }
/*     */     
/* 406 */     Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoIHM().setUnidadeTemperaturaPadrao(unidade);
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/configuracao/ControladorConfiguracao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */