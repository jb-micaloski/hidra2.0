/*     */ package ipqm.gsd.hidra.ihm.controle;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.hidra.ihm.util.UtilDesempenho;
/*     */ import ipqm.gsd.hidra.ihm.visao.Cena;
/*     */ import ipqm.gsd.hidra.ihm.visao.CenaInterna;
/*     */ import ipqm.gsd.hidra.ihm.widgets.pesquisa.itens.ItemPesquisa;
/*     */ import ipqm.gsd.hidra.ihm.widgets.pesquisa.itens.ItemPesquisaFuncionalidadeInterna;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javafx.animation.Animation;
/*     */ import javafx.animation.FadeTransition;
/*     */ import javafx.animation.ParallelTransition;
/*     */ import javafx.animation.TranslateTransition;
/*     */ import javafx.application.Platform;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.Label;
/*     */ import javafx.scene.control.ScrollPane;
/*     */ import javafx.scene.control.ToggleButton;
/*     */ import javafx.scene.control.ToggleGroup;
/*     */ import javafx.scene.control.Tooltip;
/*     */ import javafx.scene.input.KeyCode;
/*     */ import javafx.scene.input.KeyEvent;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ import javafx.scene.layout.HBox;
/*     */ import javafx.scene.layout.Pane;
/*     */ import javafx.scene.layout.VBox;
/*     */ 
/*     */ public abstract class ControladorFuncionalidadesInternas
/*     */   extends Controlador
/*     */ {
/*  37 */   private final Map<String, CenaInterna> listaCenasInternas = new HashMap<>();
/*  38 */   private final Map<Integer, ToggleButton> listaBotoes = new HashMap<>();
/*  39 */   private final Map<Integer, CenaInterna> listaAtalho = new HashMap<>();
/*     */   
/*     */   private Pane paneTitulo;
/*     */   
/*     */   private ScrollPane menuLateral;
/*     */   private VBox vBox;
/*     */   private AnchorPane areaExibicao;
/*     */   private CenaInterna cenaAtual;
/*     */   private CenaInterna cenaAnterior;
/*  48 */   private final ToggleGroup grupo = new ToggleGroup();
/*     */   
/*     */   private HBox grupoBotoesControleScrollbar;
/*     */   
/*     */   private Button botaoMaisScrollbar;
/*     */   private Button botaoMenosScrollbar;
/*     */   
/*     */   public void teclaPressionada(KeyEvent event) {
/*  56 */     super.teclaPressionada(event);
/*     */     
/*  58 */     switch (event.getCode()) {
/*     */       case DIGIT1:
/*     */       case DIGIT2:
/*     */       case DIGIT3:
/*     */       case DIGIT4:
/*     */       case DIGIT5:
/*     */       case DIGIT6:
/*     */       case DIGIT7:
/*     */       case DIGIT8:
/*     */       case DIGIT9:
/*  68 */         if (event.isControlDown()) {
/*  69 */           exibirFuncionalidadeInterna(Integer.valueOf(event.getCode().getName()).intValue());
/*     */         }
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void exibirIHM() {
/*  78 */     super.exibirIHM();
/*     */     
/*  80 */     TranslateTransition ttMenu = new TranslateTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.menuLateral);
/*  81 */     ttMenu.setFromX(-450.0D);
/*  82 */     ttMenu.setToX(0.0D);
/*  83 */     ttMenu.setAutoReverse(false);
/*     */     
/*  85 */     TranslateTransition ttLabel = new TranslateTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.paneTitulo);
/*  86 */     ttLabel.setFromX(-450.0D);
/*  87 */     ttLabel.setToX(0.0D);
/*  88 */     ttLabel.setAutoReverse(false);
/*     */     
/*  90 */     FadeTransition ftMenu = new FadeTransition(UtilDesempenho.duracaoAnimacao(200.0D), (Node)this.menuLateral);
/*  91 */     ftMenu.setFromValue(0.0D);
/*  92 */     ftMenu.setToValue(1.0D);
/*     */     
/*  94 */     ParallelTransition pt = new ParallelTransition(new Animation[] { (Animation)ttMenu, (Animation)ftMenu, (Animation)ttLabel });
/*  95 */     pt.setOnFinished(arg0 -> {
/*     */           if (this.cenaAtual != null) {
/*     */             exibirFuncionalidadeInterna(this.cenaAtual);
/*     */           }
/*     */         });
/* 100 */     pt.play();
/*     */   }
/*     */ 
/*     */   
/*     */   public void ocultarIHM() {
/* 105 */     this.areaExibicao.getChildren().clear();
/* 106 */     super.ocultarIHM();
/*     */   }
/*     */ 
/*     */   
/*     */   public void configurarObjetos() {
/* 111 */     super.configurarObjetos();
/*     */     
/* 113 */     this.areaExibicao = new AnchorPane();
/* 114 */     this.areaExibicao.setLayoutX(351.0D);
/* 115 */     this.areaExibicao.setLayoutY(0.0D);
/* 116 */     this.areaExibicao.setPrefHeight(1080.0D);
/* 117 */     this.areaExibicao.setPrefWidth(1470.0D);
/*     */     
/* 119 */     this.vBox = new VBox();
/*     */     
/* 121 */     this.menuLateral = new ScrollPane((Node)this.vBox);
/* 122 */     this.menuLateral.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
/* 123 */     this.menuLateral.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
/*     */     
/* 125 */     this.menuLateral.setLayoutX(0.0D);
/* 126 */     this.menuLateral.setLayoutY(90.0D);
/* 127 */     this.menuLateral.setPrefHeight(940.0D);
/* 128 */     this.menuLateral.setPrefWidth(350.0D);
/* 129 */     this.menuLateral.setId("barraMenuFuncionalidadesInterna");
/*     */     
/* 131 */     this.paneTitulo = (Pane)new AnchorPane();
/*     */     
/* 133 */     Label labelTitulo = new Label(getCena().getNome());
/* 134 */     labelTitulo.setPrefWidth(350.0D);
/* 135 */     labelTitulo.setId("tituloFuncionalidadeInterna");
/*     */     
/* 137 */     Platform.runLater(() -> {
/*     */           this.paneTitulo.getChildren().add(labelTitulo);
/*     */           
/*     */           getAnchorPanePai().getChildren().add(this.areaExibicao);
/*     */           getAnchorPanePai().getChildren().add(this.menuLateral);
/*     */           getAnchorPanePai().getChildren().add(this.paneTitulo);
/*     */         });
/* 144 */     int cont = 1;
/* 145 */     for (Iterator<CenaInterna> iterator = criarCenasInternas().iterator(); iterator.hasNext(); ) { CenaInterna cena = iterator.next();
/*     */       
/* 147 */       if (cena == null) {
/* 148 */         Pane separator = new Pane();
/* 149 */         separator.setPrefSize(350.0D, 30.0D);
/* 150 */         Platform.runLater(() -> this.vBox.getChildren().add(separator));
/*     */         
/*     */         continue;
/*     */       } 
/* 154 */       this.listaCenasInternas.put(cena.getFxml(), cena);
/* 155 */       cena.getControlador().setPai(this);
/*     */       
/* 157 */       if (cena.isExibirMenu()) {
/*     */         
/* 159 */         Aplicacao.getInstancia().addItemPesquisa((ItemPesquisa)new ItemPesquisaFuncionalidadeInterna(getCena().getFxml(), cena, getCena().getNome() + " > " + cena.getNome()));
/*     */         
/* 161 */         if (cont == 1) {
/* 162 */           this.cenaAtual = cena;
/*     */         }
/*     */         
/* 165 */         ToggleButton button = new ToggleButton(cena.getNome());
/* 166 */         button.setToggleGroup(this.grupo);
/* 167 */         button.setPrefSize(351.0D, 70.0D);
/* 168 */         button.setId("botaoLateral");
/*     */         
/* 170 */         this.listaBotoes.put(Integer.valueOf(cena.hashCode()), button);
/* 171 */         this.listaAtalho.put(Integer.valueOf(cont), cena);
/*     */         
/* 173 */         int contTemp = cont;
/* 174 */         Platform.runLater(() -> {
/*     */               if (contTemp <= 9) {
/*     */                 button.setTooltip(new Tooltip(cena.getNome() + " (CTRL+" + contTemp + ")"));
/*     */               } else {
/*     */                 button.setTooltip(new Tooltip(cena.getNome()));
/*     */               } 
/*     */               
/*     */               this.vBox.getChildren().add(button);
/*     */             });
/* 183 */         button.setOnAction(e -> exibirFuncionalidadeInterna(cena));
/*     */ 
/*     */ 
/*     */         
/* 187 */         Log.gravarLogInstrucao("Funcionalidade interna adicionada em " + getCena().getNome() + ": " + cena.getNome() + " na posição " + cont + " do menu", this);
/*     */         
/* 189 */         cont++; continue;
/*     */       } 
/* 191 */       Log.gravarLogInstrucao("Funcionalidade interna adicionada em " + getCena().getNome() + ": " + cena.getNome(), this); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 198 */     if (cont > 13) {
/* 199 */       this.botaoMaisScrollbar = new Button();
/* 200 */       this.botaoMaisScrollbar.setId("botaoFontAwesome");
/* 201 */       this.botaoMaisScrollbar.setText("");
/* 202 */       this.botaoMenosScrollbar = new Button();
/* 203 */       this.botaoMenosScrollbar.setId("botaoFontAwesome");
/* 204 */       this.botaoMenosScrollbar.setText("");
/* 205 */       this.grupoBotoesControleScrollbar = new HBox(new Node[] { (Node)this.botaoMenosScrollbar, (Node)this.botaoMaisScrollbar });
/* 206 */       this.grupoBotoesControleScrollbar.setLayoutX(295.0D);
/* 207 */       this.grupoBotoesControleScrollbar.setLayoutY(65.0D);
/*     */       
/* 209 */       Platform.runLater(() -> this.paneTitulo.getChildren().add(this.grupoBotoesControleScrollbar));
/*     */ 
/*     */ 
/*     */       
/* 213 */       this.botaoMaisScrollbar.setOnAction(e -> this.menuLateral.setVvalue(this.menuLateral.getVvalue() + 0.3D));
/*     */ 
/*     */       
/* 216 */       this.botaoMenosScrollbar.setOnAction(e -> this.menuLateral.setVvalue(this.menuLateral.getVvalue() - 0.3D));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, CenaInterna> getListaCenasInternas() {
/* 224 */     return this.listaCenasInternas;
/*     */   }
/*     */   
/*     */   public ControladorFuncionalidadesInternas getControladorFuncionalidadesInternas() {
/* 228 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void exibirFuncionalidadeInterna(int num) {
/* 237 */     if (this.listaAtalho.containsKey(Integer.valueOf(num))) {
/* 238 */       exibirFuncionalidadeInterna(this.listaAtalho.get(Integer.valueOf(num)));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCenaAtual(CenaInterna cenaInterna) {
/* 248 */     if (this.listaCenasInternas.containsValue(cenaInterna)) {
/* 249 */       this.cenaAtual = cenaInterna;
/*     */     }
/*     */   }
/*     */   
/*     */   public CenaInterna getCenaAtual() {
/* 254 */     return this.cenaAtual;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void exibirFuncionalidadeInterna(CenaInterna cena) {
/* 264 */     if (!cena.getControlador().verificaCondicoesExibicao((Cena)cena)) {
/* 265 */       ((ToggleButton)this.listaBotoes.get(Integer.valueOf(this.cenaAtual.hashCode()))).setSelected(true);
/*     */       
/*     */       return;
/*     */     } 
/* 269 */     if (this.cenaAtual != null) {
/* 270 */       this.cenaAtual.getControlador().ocultarIHM();
/*     */     }
/*     */     
/* 273 */     this.cenaAtual = cena;
/*     */     
/* 275 */     Log.gravarLogInterface("Exibindo funcionalidade interna '" + cena.getNome() + "' de '" + getCena().getNome() + "' em tela " + (getTela().getNumTela() + 1), this);
/*     */     
/* 277 */     this.areaExibicao.getChildren().clear();
/* 278 */     this.areaExibicao.getChildren().add(cena.getControlador().getAnchorPaneAnimar());
/* 279 */     cena.getControlador().getAnchorPaneAnimar().setVisible(true);
/*     */     
/* 281 */     TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(200.0D), (Node)this.areaExibicao);
/* 282 */     tt.setFromX(-50.0D);
/* 283 */     tt.setToX(0.0D);
/* 284 */     tt.setAutoReverse(false);
/*     */     
/* 286 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.areaExibicao);
/* 287 */     fadeTransition.setFromValue(0.0D);
/* 288 */     fadeTransition.setToValue(1.0D);
/*     */     
/* 290 */     ParallelTransition pt = new ParallelTransition(new Animation[] { (Animation)tt, (Animation)fadeTransition });
/* 291 */     pt.play();
/*     */     
/* 293 */     cena.getControlador().exibirIHM();
/* 294 */     if (this.listaBotoes.get(Integer.valueOf(cena.hashCode())) != null) {
/* 295 */       ((ToggleButton)this.listaBotoes.get(Integer.valueOf(cena.hashCode()))).setSelected(true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Object> getParametrosCenaInterna(String fxml) {
/* 306 */     if (fxml != null && 
/* 307 */       this.listaCenasInternas.containsKey(fxml)) {
/* 308 */       return ((CenaInterna)this.listaCenasInternas.get(fxml)).getParametros();
/*     */     }
/*     */     
/* 311 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void exibirFormulario(String fxml, Object objeto) {
/* 322 */     CenaInterna cena = null;
/*     */     
/* 324 */     if (this.listaCenasInternas.containsKey(fxml)) {
/* 325 */       cena = this.listaCenasInternas.get(fxml);
/*     */     }
/*     */     
/* 328 */     if (cena == null) {
/*     */       return;
/*     */     }
/*     */     
/* 332 */     if (this.cenaAtual != null) {
/* 333 */       this.cenaAtual.getControlador().ocultarIHM();
/*     */     }
/*     */     
/* 336 */     Log.gravarLogInterface("Exibindo formulário interno '" + cena.getNome() + "' de '" + 
/* 337 */         getCena().getNome() + "' em tela " + (getTela().getNumTela() + 1) + "; Objeto: " + objeto, this);
/*     */     
/* 339 */     this.areaExibicao.getChildren().clear();
/* 340 */     this.areaExibicao.getChildren().add(cena.getControlador().getAnchorPaneAnimar());
/* 341 */     cena.getControlador().getAnchorPaneAnimar().setVisible(true);
/*     */     
/* 343 */     TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(200.0D), (Node)this.areaExibicao);
/* 344 */     tt.setFromX(-50.0D);
/* 345 */     tt.setToX(0.0D);
/* 346 */     tt.setAutoReverse(false);
/*     */     
/* 348 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.areaExibicao);
/* 349 */     fadeTransition.setFromValue(0.0D);
/* 350 */     fadeTransition.setToValue(1.0D);
/*     */     
/* 352 */     ParallelTransition pt = new ParallelTransition(new Animation[] { (Animation)tt, (Animation)fadeTransition });
/* 353 */     pt.play();
/*     */     
/* 355 */     this.cenaAnterior = getCenaAtual();
/* 356 */     setCenaAtual(cena);
/*     */     
/* 358 */     cena.getControlador().exibirObjeto(this, objeto);
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
/*     */   public void exibirFormulario(String fxml, Controlador pai, Object objeto) {
/* 370 */     CenaInterna cena = null;
/*     */     
/* 372 */     if (this.listaCenasInternas.containsKey(fxml)) {
/* 373 */       cena = this.listaCenasInternas.get(fxml);
/*     */     }
/*     */     
/* 376 */     if (cena == null) {
/*     */       return;
/*     */     }
/*     */     
/* 380 */     if (this.cenaAtual != null) {
/* 381 */       this.cenaAtual.getControlador().ocultarIHM();
/*     */     }
/*     */     
/* 384 */     Log.gravarLogInterface("Exibindo formulário interno '" + cena.getNome() + "' de '" + 
/* 385 */         getCena().getNome() + "' em tela " + (getTela().getNumTela() + 1) + "; Objeto: " + objeto, this);
/*     */     
/* 387 */     this.areaExibicao.getChildren().clear();
/* 388 */     this.areaExibicao.getChildren().add(cena.getControlador().getAnchorPaneAnimar());
/* 389 */     cena.getControlador().getAnchorPaneAnimar().setVisible(true);
/*     */     
/* 391 */     TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(200.0D), (Node)this.areaExibicao);
/* 392 */     tt.setFromX(-50.0D);
/* 393 */     tt.setToX(0.0D);
/* 394 */     tt.setAutoReverse(false);
/*     */     
/* 396 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.areaExibicao);
/* 397 */     fadeTransition.setFromValue(0.0D);
/* 398 */     fadeTransition.setToValue(1.0D);
/*     */     
/* 400 */     ParallelTransition pt = new ParallelTransition(new Animation[] { (Animation)tt, (Animation)fadeTransition });
/* 401 */     pt.play();
/*     */     
/* 403 */     cena.getControlador().exibirObjeto(pai, objeto);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void voltarFormulario() {
/* 411 */     this.cenaAtual = this.cenaAnterior;
/*     */     
/* 413 */     exibirFuncionalidadeInterna(this.cenaAtual);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void voltarFormulario(CenaInterna cena) {
/* 423 */     if (this.cenaAtual != null) {
/* 424 */       this.cenaAtual.getControlador().ocultarIHM();
/*     */     }
/*     */     
/* 427 */     Log.gravarLogInterface("Exibindo funcionalidade interna '" + cena.getNome() + "' de '" + getCena().getNome() + "' em tela " + (getTela().getNumTela() + 1), this);
/*     */     
/* 429 */     this.areaExibicao.getChildren().clear();
/* 430 */     this.areaExibicao.getChildren().add(cena.getControlador().getAnchorPaneAnimar());
/* 431 */     cena.getControlador().getAnchorPaneAnimar().setVisible(true);
/*     */     
/* 433 */     TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(200.0D), (Node)this.areaExibicao);
/* 434 */     tt.setFromX(-50.0D);
/* 435 */     tt.setToX(0.0D);
/* 436 */     tt.setAutoReverse(false);
/*     */     
/* 438 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)this.areaExibicao);
/* 439 */     fadeTransition.setFromValue(0.0D);
/* 440 */     fadeTransition.setToValue(1.0D);
/*     */     
/* 442 */     ParallelTransition pt = new ParallelTransition(new Animation[] { (Animation)tt, (Animation)fadeTransition });
/* 443 */     pt.play();
/*     */     
/* 445 */     cena.getControlador().exibirIHM();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void desenharObjetos(List<ObjetoDOMINIO> listaObjetos) {
/* 451 */     if (this.cenaAtual != null)
/* 452 */       this.cenaAtual.getControlador().desenharObjetos(listaObjetos); 
/*     */   }
/*     */   
/*     */   protected abstract List<CenaInterna> criarCenasInternas();
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/controle/ControladorFuncionalidadesInternas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */