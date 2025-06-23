/*     */ package ipqm.gsd.hidra.ihm.widgets.fonia_vhf;
/*     */ 
/*     */ import eu.hansolo.enzo.sevensegment.SevenSegment;
/*     */ import eu.hansolo.enzo.sevensegment.SevenSegmentBuilder;
/*     */ import ipqm.gsd.componentes.dominio.configuracao.fonia.LinhaVhf;
/*     */ import ipqm.gsd.componentes.nucleo.comunicacao_fonia.ComponenteFoniaIHM;
/*     */ import ipqm.gsd.componentes.nucleo.comunicacao_fonia.ComunicacaoFonia;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import java.net.URL;
/*     */ import java.sql.SQLException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.TreeMap;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.fxml.FXML;
/*     */ import javafx.fxml.Initializable;
/*     */ import javafx.geometry.Insets;
/*     */ import javafx.geometry.Pos;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.Label;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ import javafx.scene.layout.Background;
/*     */ import javafx.scene.layout.BackgroundFill;
/*     */ import javafx.scene.layout.CornerRadii;
/*     */ import javafx.scene.layout.HBox;
/*     */ import javafx.scene.paint.Color;
/*     */ import javafx.scene.paint.Paint;
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
/*     */ public class ControladorFoniaVHF
/*     */   implements Initializable
/*     */ {
/*     */   @FXML
/*     */   private AnchorPane anchorPaneFonia;
/*     */   private WidgetFoniaVHF widgetFoniaVHF;
/*     */   @FXML
/*     */   private Button botaoPPT;
/*  53 */   private int canalSelecionado = -1;
/*     */   
/*  55 */   private Map<String, ComponenteFoniaIHM> componentesFoniaVhf = new ConcurrentHashMap<>();
/*     */   
/*     */   @FXML
/*     */   private Button botaoMais;
/*     */   
/*     */   @FXML
/*     */   private Button botaoMenos;
/*     */   
/*     */   List<String> chaves;
/*     */   
/*     */   @FXML
/*     */   private Button botaoCanal16;
/*     */   
/*     */   @FXML
/*     */   private Label labelTx;
/*     */   
/*     */   @FXML
/*     */   private Label labelRx;
/*     */   
/*     */   private SevenSegment seg0;
/*     */   private SevenSegment seg1;
/*     */   @FXML
/*     */   private AnchorPane foniaVHFBloqueada;
/*     */   
/*     */   public void initialize(URL url, ResourceBundle rb) {
/*  80 */     this
/*     */ 
/*     */ 
/*     */       
/*  84 */       .seg0 = SevenSegmentBuilder.create().prefSize(24.0D, 29.0D).character(0).segmentStyle(SevenSegment.SegmentStyle.RED).build();
/*  85 */     this
/*     */ 
/*     */ 
/*     */       
/*  89 */       .seg1 = SevenSegmentBuilder.create().prefSize(24.0D, 29.0D).character(0).segmentStyle(SevenSegment.SegmentStyle.RED).build();
/*     */     
/*  91 */     HBox lcd = new HBox();
/*  92 */     lcd.setPadding(new Insets(7.0D, 16.0D, 7.0D, 16.0D));
/*  93 */     lcd.setBackground(new Background(new BackgroundFill[] { new BackgroundFill((Paint)Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY) }));
/*  94 */     lcd.setSpacing(10.0D);
/*  95 */     lcd.setAlignment(Pos.CENTER);
/*  96 */     lcd.setFillHeight(false);
/*     */     
/*  98 */     lcd.getChildren().addAll((Object[])new Node[] { (Node)this.seg0, (Node)this.seg1 });
/*     */     
/* 100 */     this.anchorPaneFonia.getChildren().add(lcd);
/* 101 */     lcd.setLayoutX(this.botaoMenos.getLayoutX() - 1.0D);
/* 102 */     lcd.setLayoutY(this.botaoCanal16.getLayoutY() - 1.0D);
/*     */     
/* 104 */     getFoniaVHFBloqueada().setVisible(true);
/* 105 */     getFoniaVHFBloqueada().toFront();
/*     */ 
/*     */     
/* 108 */     ComponenteFoniaIHM componenteFoniaIHM = null;
/* 109 */     Iterator<LinhaVhf> iteratorExt = obterLinhasVHFBancoDados().iterator();
/* 110 */     while (iteratorExt.hasNext()) {
/* 111 */       LinhaVhf lVhf = iteratorExt.next();
/* 112 */       if (lVhf.getTipoVhf().equalsIgnoreCase("VHF_FULL")) {
/* 113 */         componenteFoniaIHM = new ComponenteFoniaIHM(lVhf.getNome(), 10);
/* 114 */         getComponentesFoniaVhf().put(lVhf.getNome(), componenteFoniaIHM);
/*     */       } 
/* 116 */       if (lVhf.getTipoVhf().equalsIgnoreCase("VHF_HALF")) {
/* 117 */         componenteFoniaIHM = new ComponenteFoniaIHM(lVhf.getNome(), 11);
/* 118 */         getComponentesFoniaVhf().put(lVhf.getNome(), componenteFoniaIHM);
/*     */       } 
/*     */     } 
/*     */     
/* 122 */     this.componentesFoniaVhf = new TreeMap<>(getComponentesFoniaVhf());
/*     */ 
/*     */     
/* 125 */     this.chaves = new ArrayList<>(this.componentesFoniaVhf.keySet());
/*     */ 
/*     */     
/* 128 */     this.componentesFoniaVhf.put("HANDS FREE", new ComponenteFoniaIHM("HANDS FREE", 3));
/* 129 */     this.componentesFoniaVhf.put("PTT FISICO", new ComponenteFoniaIHM("PTT FISICO", 7));
/* 130 */     this.componentesFoniaVhf.put("LABEL RX", new ComponenteFoniaIHM("LABEL RX", 14));
/* 131 */     this.componentesFoniaVhf.put("LABEL TX", new ComponenteFoniaIHM("LABEL TX", 15));
/*     */ 
/*     */     
/* 134 */     this.componentesFoniaVhf.put("CANAIS ADICIONAIS", new ComponenteFoniaIHM("CANAIS ADICIONAIS", 17));
/*     */   }
/*     */   
/*     */   private List<LinhaVhf> obterLinhasVHFBancoDados() {
/* 138 */     List<LinhaVhf> linhasVHF = new ArrayList<>();
/*     */     try {
/* 140 */       linhasVHF = (new LinhaVhf()).obterListaLinhasVHFParaConfigPadrao();
/* 141 */     } catch (SQLException ex) {
/* 142 */       Log.gravarLogExcecao("Erro ao carregar as Linhas VHF da configuração padrão da fonia, do Banco Dados", this, ex);
/*     */     } 
/* 144 */     return linhasVHF;
/*     */   }
/*     */   
/*     */   public WidgetFoniaVHF getWidgetFoniaVHF() {
/* 148 */     return this.widgetFoniaVHF;
/*     */   }
/*     */   
/*     */   public void setWidgetFoniaVHF(WidgetFoniaVHF widgetFoniaVHF) {
/* 152 */     this.widgetFoniaVHF = widgetFoniaVHF;
/*     */   }
/*     */   
/*     */   public AnchorPane getAnchorPaneFonia() {
/* 156 */     return this.anchorPaneFonia;
/*     */   }
/*     */   
/*     */   public void setAnchorPaneFonia(AnchorPane anchorPaneFonia) {
/* 160 */     this.anchorPaneFonia = anchorPaneFonia;
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void acaoPPT(ActionEvent event) {
/* 165 */     this.widgetFoniaVHF.getCommFonia().tratarComandoIHM("HANDS FREE");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @FXML
/*     */   public void acaoMais(ActionEvent event) {
/* 175 */     setCanalSelecionado(getCanalSelecionado() + 1);
/* 176 */     if (getCanalSelecionado() >= this.chaves.size()) {
/* 177 */       setCanalSelecionado(0);
/*     */     }
/* 179 */     this.seg0.setCharacter(String.valueOf(this.chaves.get(getCanalSelecionado())).substring(0, 1));
/* 180 */     this.seg1.setCharacter(String.valueOf(this.chaves.get(getCanalSelecionado())).substring(1, 2));
/* 181 */     this.widgetFoniaVHF.getCommFonia().tratarComandoIHM(this.chaves.get(getCanalSelecionado()));
/*     */   }
/*     */   
/*     */   @FXML
/*     */   public void acaoMenos(ActionEvent event) {
/* 186 */     setCanalSelecionado(getCanalSelecionado() - 1);
/* 187 */     if (getCanalSelecionado() < 0) {
/* 188 */       setCanalSelecionado(this.chaves.size() - 1);
/*     */     }
/* 190 */     this.seg0.setCharacter(String.valueOf(this.chaves.get(getCanalSelecionado())).substring(0, 1));
/* 191 */     this.seg1.setCharacter(String.valueOf(this.chaves.get(getCanalSelecionado())).substring(1, 2));
/* 192 */     this.widgetFoniaVHF.getCommFonia().tratarComandoIHM(this.chaves.get(getCanalSelecionado()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnchorPane getFoniaVHFBloqueada() {
/* 199 */     return this.foniaVHFBloqueada;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getCanalSelecionado() {
/* 206 */     return this.canalSelecionado;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCanalSelecionado(int canalSelecionado) {
/* 213 */     this.canalSelecionado = canalSelecionado;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void processarRequisicaoCanaisAdicionais(ComponenteFoniaIHM componenteFoniaIHM) {
/* 221 */     List<String> lista = null;
/* 222 */     LinhaVhf linhaV = new LinhaVhf();
/* 223 */     String canaisAdicionais = "";
/*     */     
/*     */     try {
/* 226 */       lista = linhaV.obterListaNomesLinhasVHFConfiguracaoPadraoOrdenadoPorID();
/* 227 */     } catch (SQLException ex) {
/* 228 */       Log.gravarLogExcecao("Erro ao carregar as Linhas VHF da configuração padrão da fonia do Banco Dados", this, ex);
/*     */     } 
/* 230 */     if (lista == null || lista.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/* 234 */     for (String canal : lista) {
/* 235 */       canaisAdicionais = canaisAdicionais.concat(canal + "#");
/*     */     }
/* 237 */     componenteFoniaIHM.setNomeApresentacao(canaisAdicionais);
/* 238 */     this.widgetFoniaVHF.getCommFonia().tratarComandoIHM(componenteFoniaIHM.getNomeCanal());
/*     */   }
/*     */   
/*     */   private class acaoCanal16Inicial implements Runnable {
/*     */     private acaoCanal16Inicial() {}
/*     */     
/*     */     public void run() {
/* 245 */       while (ControladorFoniaVHF.this.widgetFoniaVHF.getEstado() != ComunicacaoFonia.EstadosCliente.ATIVO) {
/*     */         try {
/* 247 */           Thread.sleep(1000L);
/* 248 */         } catch (InterruptedException ex) {
/* 249 */           Log.gravarLogExcecao("Erro no sleep.", this, ex);
/*     */         } 
/*     */       } 
/*     */       
/* 253 */       ControladorFoniaVHF.this.acaoCanal16(null);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void contruir() throws InterruptedException {
/* 260 */     Thread t = new Thread(new acaoCanal16Inicial());
/* 261 */     t.start();
/*     */   }
/*     */   
/*     */   public Button getBotaoPPT() {
/* 265 */     return this.botaoPPT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, ComponenteFoniaIHM> getComponentesFoniaVhf() {
/* 272 */     return this.componentesFoniaVhf;
/*     */   }
/*     */   
/*     */   @FXML
/*     */   public void acaoCanal16(ActionEvent event) {
/* 277 */     int canalSelecionadoAux = 0;
/* 278 */     while (!((String)this.chaves.get(canalSelecionadoAux)).equalsIgnoreCase("16")) {
/* 279 */       canalSelecionadoAux++;
/*     */     }
/* 281 */     if (getCanalSelecionado() == canalSelecionadoAux) {
/*     */       return;
/*     */     }
/* 284 */     setCanalSelecionado(canalSelecionadoAux);
/*     */     
/* 286 */     this.seg0.setCharacter(String.valueOf(this.chaves.get(getCanalSelecionado())).substring(0, 1));
/* 287 */     this.seg1.setCharacter(String.valueOf(this.chaves.get(getCanalSelecionado())).substring(1, 2));
/* 288 */     this.widgetFoniaVHF.getCommFonia().tratarComandoIHM(this.chaves.get(getCanalSelecionado()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Label getLabelTx() {
/* 295 */     return this.labelTx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Label getLabelRx() {
/* 302 */     return this.labelRx;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/fonia_vhf/ControladorFoniaVHF.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */