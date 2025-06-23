/*     */ package ipqm.gsd.hidra.ihm.objetos_graficos.barra_ferramentas;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import javafx.application.Platform;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.ButtonBase;
/*     */ import javafx.scene.control.ToggleButton;
/*     */ import javafx.scene.control.Tooltip;
/*     */ 
/*     */ public class BotaoBarraFerramenta
/*     */ {
/*     */   private ButtonBase botao;
/*     */   private final String nome;
/*     */   private final AcaoBarraFerramentas acaoBarraFerramentas;
/*     */   private final AcaoBarraFerramentas.Acao acao;
/*     */   private GrupoBotoesBarraFerramentaAgrupados grupoBotoesBarraFerramentaAgrupados;
/*     */   private final BarraDeFerramentas barraDeFerramentas;
/*     */   
/*     */   public enum Botao {
/*  21 */     SIMPLES, ALTERNAR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BotaoBarraFerramenta(String nome, String fxid, AcaoBarraFerramentas acaoBarraFerramentas, AcaoBarraFerramentas.Acao acao, Botao tipoBotao, BarraDeFerramentas barraDeFerramentas) {
/*  32 */     this.nome = nome;
/*  33 */     this.acaoBarraFerramentas = acaoBarraFerramentas;
/*  34 */     this.acao = acao;
/*  35 */     this.barraDeFerramentas = barraDeFerramentas;
/*     */     
/*  37 */     switch (tipoBotao) {
/*     */       case SIMPLES:
/*  39 */         this.botao = (ButtonBase)new Button();
/*     */         break;
/*     */       case ALTERNAR:
/*  42 */         this.botao = (ButtonBase)new ToggleButton();
/*     */         break;
/*     */     } 
/*  45 */     Platform.runLater(() -> this.botao.setTooltip(new Tooltip(nome)));
/*     */ 
/*     */     
/*  48 */     this.botao.setId(fxid);
/*     */     
/*  50 */     this.botao.setOnAction(e -> acao());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void acao() {
/*  60 */     Log.gravarLogInterface("Botão da barra de ferramentas pressionado: " + getNome(), this);
/*     */ 
/*     */     
/*  63 */     if (this.botao instanceof ToggleButton && ((ToggleButton)this.botao).getToggleGroup() != null) {
/*  64 */       ((ToggleButton)this.botao).setSelected(true);
/*     */     }
/*     */     
/*  67 */     this.acaoBarraFerramentas.acaoBotaoBarraFerramentas(this.acao);
/*     */     
/*  69 */     if (this.grupoBotoesBarraFerramentaAgrupados != null) {
/*  70 */       this.grupoBotoesBarraFerramentaAgrupados.acao(this.acao);
/*     */     }
/*     */     
/*  73 */     this.barraDeFerramentas.acao(this.acao);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ButtonBase getBotao() {
/*  82 */     return this.botao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GrupoBotoesBarraFerramentaAgrupados getGrupoBotoesBarraFerramentaAgrupados() {
/*  92 */     return this.grupoBotoesBarraFerramentaAgrupados;
/*     */   }
/*     */   
/*     */   public void setGrupoBotoesBarraFerramentaAgrupados(GrupoBotoesBarraFerramentaAgrupados grupoBotoesBarraFerramentaAgrupados) {
/*  96 */     this.grupoBotoesBarraFerramentaAgrupados = grupoBotoesBarraFerramentaAgrupados;
/*     */   }
/*     */   
/*     */   public String getNome() {
/* 100 */     return this.nome;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/objetos_graficos/barra_ferramentas/BotaoBarraFerramenta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */