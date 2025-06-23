/*     */ package ipqm.gsd.hidra.ihm.objetos_graficos.barra_ferramentas;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import javafx.geometry.Orientation;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.ButtonBase;
/*     */ import javafx.scene.control.Separator;
/*     */ import javafx.scene.control.ToggleButton;
/*     */ import javafx.scene.control.ToggleGroup;
/*     */ import javafx.scene.image.Image;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ import javafx.scene.layout.Background;
/*     */ import javafx.scene.layout.BackgroundImage;
/*     */ import javafx.scene.layout.BackgroundPosition;
/*     */ import javafx.scene.layout.BackgroundRepeat;
/*     */ import javafx.scene.layout.BackgroundSize;
/*     */ import javafx.scene.layout.Pane;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BarraDeFerramentas
/*     */ {
/*  28 */   private final Map<AcaoBarraFerramentas.Acao, BotaoBarraFerramenta> botoes = new HashMap<>();
/*  29 */   private final Map<String, ToggleGroup> grupos = new HashMap<>();
/*     */   private final AcaoBarraFerramentas barraFerramentas;
/*  31 */   private final Map<String, GrupoBotoesBarraFerramentaAgrupados> gruposBotoesAgrupados = new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final AnchorPane pai;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BarraDeFerramentas(AcaoBarraFerramentas barraFerramentas, AnchorPane pai) {
/*  44 */     this.barraFerramentas = barraFerramentas;
/*  45 */     this.pai = pai;
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
/*     */   
/*     */   public void addBotaoSimples(Pane pane, String nome, String fxid, AcaoBarraFerramentas.Acao acao) {
/*  58 */     BotaoBarraFerramenta b = new BotaoBarraFerramenta(nome, fxid, this.barraFerramentas, acao, BotaoBarraFerramenta.Botao.SIMPLES, this);
/*     */     
/*  60 */     this.botoes.put(acao, b);
/*  61 */     pane.getChildren().add(b.getBotao());
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
/*     */   
/*     */   public void addBotaoAlternar(Pane pane, String nome, String fxid, AcaoBarraFerramentas.Acao acao) {
/*  74 */     addBotaoAlternar(pane, nome, fxid, acao, null, false);
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
/*     */ 
/*     */   
/*     */   public void addBotaoAlternar(Pane pane, String nome, String fxid, AcaoBarraFerramentas.Acao acao, String grupo) {
/*  88 */     addBotaoAlternar(pane, nome, fxid, acao, grupo, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addBotaoAlternarAgrupado(Pane pane, String nome, String fxid, AcaoBarraFerramentas.Acao acao, String grupo, String grupoAgrupado) {
/* 104 */     if (grupoAgrupado == null) {
/*     */       return;
/*     */     }
/*     */     
/* 108 */     BotaoBarraFerramenta b = new BotaoBarraFerramenta(nome, fxid, this.barraFerramentas, acao, BotaoBarraFerramenta.Botao.ALTERNAR, this);
/*     */     
/* 110 */     this.botoes.put(acao, b);
/* 111 */     if (grupo != null) {
/* 112 */       if (!this.grupos.containsKey(grupo)) {
/* 113 */         this.grupos.put(grupo, new ToggleGroup());
/*     */       }
/* 115 */       ((ToggleButton)b.getBotao()).setToggleGroup(this.grupos.get(grupo));
/*     */     } 
/* 117 */     if (!this.gruposBotoesAgrupados.containsKey(grupoAgrupado)) {
/* 118 */       this.gruposBotoesAgrupados.put(grupoAgrupado, new GrupoBotoesBarraFerramentaAgrupados(this));
/* 119 */       Pane paneBG = new Pane();
/* 120 */       BackgroundImage myBI = new BackgroundImage(new Image("/ipqm/gsd/hidra/ihm/imagens/icones/agrupado.png", 32.0D, 32.0D, false, true), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
/*     */       
/* 122 */       paneBG.setBackground(new Background(new BackgroundImage[] { myBI }));
/* 123 */       paneBG.getChildren().add(((GrupoBotoesBarraFerramentaAgrupados)this.gruposBotoesAgrupados.get(grupoAgrupado)).getBotao());
/* 124 */       pane.getChildren().add(paneBG);
/*     */     } 
/* 126 */     ((GrupoBotoesBarraFerramentaAgrupados)this.gruposBotoesAgrupados.get(grupoAgrupado)).addBotao(acao, b);
/* 127 */     b.setGrupoBotoesBarraFerramentaAgrupados(this.gruposBotoesAgrupados.get(grupoAgrupado));
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void addBotaoAlternar(Pane pane, String nome, String fxid, AcaoBarraFerramentas.Acao acao, String grupo, boolean selecionado) {
/* 142 */     BotaoBarraFerramenta b = new BotaoBarraFerramenta(nome, fxid, this.barraFerramentas, acao, BotaoBarraFerramenta.Botao.ALTERNAR, this);
/*     */     
/* 144 */     this.botoes.put(acao, b);
/* 145 */     if (grupo != null) {
/* 146 */       if (!this.grupos.containsKey(grupo)) {
/* 147 */         this.grupos.put(grupo, new ToggleGroup());
/*     */       }
/* 149 */       ((ToggleButton)b.getBotao()).setToggleGroup(this.grupos.get(grupo));
/*     */     } 
/* 151 */     ((ToggleButton)b.getBotao()).setSelected(selecionado);
/* 152 */     pane.getChildren().add(b.getBotao());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addSeparador(Pane pane, Orientation orientation) {
/* 162 */     Separator separator = new Separator(orientation);
/* 163 */     pane.getChildren().add(separator);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BotaoBarraFerramenta getBotao(AcaoBarraFerramentas.Acao id) {
/* 173 */     return this.botoes.get(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Button getBotaoSimples(AcaoBarraFerramentas.Acao id) {
/* 183 */     if (this.botoes.containsKey(id)) {
/* 184 */       ButtonBase base = ((BotaoBarraFerramenta)this.botoes.get(id)).getBotao();
/* 185 */       if (base instanceof Button) {
/* 186 */         return (Button)base;
/*     */       }
/*     */     } 
/* 189 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ToggleButton getBotaoAlternar(AcaoBarraFerramentas.Acao id) {
/* 199 */     if (this.botoes.containsKey(id)) {
/* 200 */       ButtonBase base = ((BotaoBarraFerramenta)this.botoes.get(id)).getBotao();
/* 201 */       if (base instanceof ToggleButton) {
/* 202 */         return (ToggleButton)base;
/*     */       }
/*     */     } 
/* 205 */     return null;
/*     */   }
/*     */   
/*     */   public boolean contemBotao(AcaoBarraFerramentas.Acao id) {
/* 209 */     return this.botoes.containsKey(id);
/*     */   }
/*     */   
/*     */   public Map<String, ToggleGroup> getGrupos() {
/* 213 */     return this.grupos;
/*     */   }
/*     */   
/*     */   public AnchorPane getPai() {
/* 217 */     return this.pai;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void acao(AcaoBarraFerramentas.Acao acao) {
/* 228 */     Iterator<GrupoBotoesBarraFerramentaAgrupados> it = this.gruposBotoesAgrupados.values().iterator();
/* 229 */     while (it.hasNext()) {
/* 230 */       GrupoBotoesBarraFerramentaAgrupados grupo = it.next();
/* 231 */       ((ToggleButton)grupo.getBotao()).setSelected(grupo.isSelecionado());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/objetos_graficos/barra_ferramentas/BarraDeFerramentas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */