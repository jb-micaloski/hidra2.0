/*     */ package ipqm.gsd.hidra.ihm.objetos_graficos.barra_ferramentas;
/*     */ 
/*     */ import ipqm.gsd.hidra.ihm.util.UtilDesempenho;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import javafx.animation.PauseTransition;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.event.Event;
/*     */ import javafx.event.EventHandler;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.control.ButtonBase;
/*     */ import javafx.scene.control.ToggleButton;
/*     */ import javafx.scene.input.MouseEvent;
/*     */ import javafx.scene.layout.VBox;
/*     */ import javafx.util.Duration;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GrupoBotoesBarraFerramentaAgrupados
/*     */ {
/*     */   private final Map<AcaoBarraFerramentas.Acao, BotaoBarraFerramenta> botoes;
/*     */   private AcaoBarraFerramentas.Acao acaoSelecionada;
/*     */   private final ButtonBase botao;
/*     */   private VBox vBox;
/*     */   private final BarraDeFerramentas barraDeFerramentas;
/*     */   
/*     */   public GrupoBotoesBarraFerramentaAgrupados(BarraDeFerramentas barraDeFerramentas) {
/*  34 */     this.barraDeFerramentas = barraDeFerramentas;
/*  35 */     this.botoes = new HashMap<>();
/*  36 */     this.botao = (ButtonBase)new ToggleButton();
/*     */     
/*  38 */     addPressAndHoldHandler((Node)this.botao, UtilDesempenho.duracaoAnimacao(300.0D), event -> exibeSubMenu(event));
/*     */ 
/*     */ 
/*     */     
/*  42 */     this.botao.setOnAction(e -> getBotaoAtual().acao());
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
/*     */   private void exibeSubMenu(MouseEvent event) {
/*  54 */     if (this.vBox == null) {
/*  55 */       this.vBox = new VBox();
/*  56 */       this.vBox.setId("painelBotoes");
/*     */       
/*  58 */       Iterator<AcaoBarraFerramentas.Acao> it = this.botoes.keySet().iterator();
/*     */       
/*  60 */       while (it.hasNext()) {
/*  61 */         AcaoBarraFerramentas.Acao acao = it.next();
/*  62 */         BotaoBarraFerramenta b = this.botoes.get(acao);
/*  63 */         this.vBox.getChildren().add(b.getBotao());
/*     */       } 
/*     */     } 
/*     */     
/*  67 */     double mouseX = event.getSceneX();
/*  68 */     double mouseY = event.getSceneY();
/*  69 */     double correcaoY = 0.0D;
/*     */     
/*  71 */     if (mouseY + this.vBox.getHeight() > 1080.0D) {
/*  72 */       correcaoY = this.vBox.getHeight();
/*     */     }
/*     */     
/*  75 */     this.vBox.setLayoutX(mouseX - 50.0D);
/*  76 */     this.vBox.setLayoutY(mouseY - correcaoY);
/*  77 */     getBarraDeFerramentas().getPai().getChildren().add(this.vBox);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void acao(AcaoBarraFerramentas.Acao acao) {
/*  87 */     getBarraDeFerramentas().getPai().getChildren().remove(this.vBox);
/*  88 */     if (this.acaoSelecionada != acao) {
/*  89 */       setAcaoSelecionada(acao);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addBotao(AcaoBarraFerramentas.Acao acao, BotaoBarraFerramenta botao) {
/* 100 */     if (this.botoes.isEmpty()) {
/* 101 */       this.botoes.put(acao, botao);
/* 102 */       setAcaoSelecionada(acao);
/*     */     } else {
/* 104 */       this.botoes.put(acao, botao);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BotaoBarraFerramenta getBotao(AcaoBarraFerramentas.Acao a) {
/* 115 */     return this.botoes.get(a);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BotaoBarraFerramenta getBotaoAtual() {
/* 124 */     return this.botoes.get(this.acaoSelecionada);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAcaoSelecionada(AcaoBarraFerramentas.Acao acaoSelecionada) {
/* 133 */     this.acaoSelecionada = acaoSelecionada;
/* 134 */     getBotao().setId(((BotaoBarraFerramenta)this.botoes.get(acaoSelecionada)).getBotao().getId());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ButtonBase getBotao() {
/* 143 */     return this.botao;
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
/*     */   private void addPressAndHoldHandler(Node node, Duration holdTime, EventHandler<MouseEvent> handler) {
/*     */     class Wrapper<T>
/*     */     {
/*     */       T content;
/*     */     };
/* 160 */     Wrapper<MouseEvent> eventWrapper = new Wrapper<>();
/*     */     
/* 162 */     PauseTransition holdTimer = new PauseTransition(holdTime);
/* 163 */     holdTimer.setOnFinished(event -> handler.handle((Event)eventWrapper.content));
/*     */     
/* 165 */     node.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
/*     */           eventWrapper.content = (T)event;
/*     */           holdTimer.playFromStart();
/*     */         });
/* 169 */     node.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> holdTimer.stop());
/* 170 */     node.addEventHandler(MouseEvent.DRAG_DETECTED, event -> holdTimer.stop());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BarraDeFerramentas getBarraDeFerramentas() {
/* 179 */     return this.barraDeFerramentas;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSelecionado() {
/* 188 */     Iterator<AcaoBarraFerramentas.Acao> it = this.botoes.keySet().iterator();
/*     */     
/* 190 */     while (it.hasNext()) {
/* 191 */       AcaoBarraFerramentas.Acao acao = it.next();
/* 192 */       BotaoBarraFerramenta b = this.botoes.get(acao);
/* 193 */       if (((ToggleButton)b.getBotao()).isSelected()) {
/* 194 */         return true;
/*     */       }
/*     */     } 
/* 197 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/objetos_graficos/barra_ferramentas/GrupoBotoesBarraFerramentaAgrupados.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */