/*     */ package ipqm.gsd.hidra.ihm.widgets.fonia;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javafx.scene.control.MenuItem;
/*     */ import javafx.scene.control.ToggleButton;
/*     */ import javafx.scene.layout.Region;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BotaoCmd
/*     */ {
/*     */   private ToggleButton IDBtn;
/*     */   private Region regiaoLuzIndicativa;
/*     */   private boolean BtnAcionado;
/*     */   private boolean BtnConfigurado;
/*     */   private boolean TimerAtivado;
/*     */   private int CodTipoLinha;
/*     */   private int IndiceNoHashMap;
/*     */   private String CorSelecionado;
/*     */   private String CorDeSelecionado;
/*     */   private String TextoForeground;
/*     */   private String HostName;
/*     */   private String CorLuzIndicaInicial;
/*     */   private String CorLuzIndicaBtnSelecionado;
/*     */   private int TamFonte;
/*  31 */   private final List<MenuItem> listaSubOpcoes = new ArrayList<>();
/*     */   
/*     */   public void setTimerAtivado(boolean flag) {
/*  34 */     this.TimerAtivado = flag;
/*     */   }
/*     */   
/*     */   public void SetIndHashMap(int ind) {
/*  38 */     this.IndiceNoHashMap = ind;
/*     */   }
/*     */   
/*     */   public void SetTamFonte(int TamFonte) {
/*  42 */     this.TamFonte = TamFonte;
/*     */   }
/*     */   
/*     */   public void SetIdBtn(ToggleButton IdBtn) {
/*  46 */     this.IDBtn = IdBtn;
/*     */   }
/*     */   
/*     */   public void setRegiaoLuzIndicativa(Region regiaoLuzIndicativa) {
/*  50 */     this.regiaoLuzIndicativa = regiaoLuzIndicativa;
/*     */   }
/*     */   
/*     */   public void SetStatusBtn(Boolean Sts) {
/*  54 */     this.BtnAcionado = Sts.booleanValue();
/*     */   }
/*     */   
/*     */   public void SetStatusBtnConfig(Boolean Sts) {
/*  58 */     this.BtnConfigurado = Sts.booleanValue();
/*     */   }
/*     */   
/*     */   public void SetCodTipoLinha(int Cod) {
/*  62 */     this.CodTipoLinha = Cod;
/*     */   }
/*     */   
/*     */   public void SetCorSelecionado(String Cor) {
/*  66 */     this.CorSelecionado = Cor;
/*     */   }
/*     */   
/*     */   public void SetCorDeSelecionado(String Cor) {
/*  70 */     this.CorDeSelecionado = Cor;
/*     */   }
/*     */   
/*     */   public void SetTextoForeground(String texto) {
/*  74 */     this.TextoForeground = texto;
/*     */   }
/*     */   
/*     */   public void SetCorLuzIndicaInicial(String Cor) {
/*  78 */     this.CorLuzIndicaInicial = Cor;
/*     */   }
/*     */   
/*     */   public void SetCorLuzIndicaBtnSelecionado(String Cor) {
/*  82 */     this.CorLuzIndicaBtnSelecionado = Cor;
/*     */   }
/*     */   
/*     */   public boolean GetTimerAtivado() {
/*  86 */     return this.TimerAtivado;
/*     */   }
/*     */   
/*     */   public int GetIndHashMap() {
/*  90 */     return this.IndiceNoHashMap;
/*     */   }
/*     */   
/*     */   public int GetTamFonte() {
/*  94 */     return this.TamFonte;
/*     */   }
/*     */   
/*     */   public List<MenuItem> getListaSubOpcoes() {
/*  98 */     return this.listaSubOpcoes;
/*     */   }
/*     */   
/*     */   public ToggleButton getIdBtn() {
/* 102 */     return this.IDBtn;
/*     */   }
/*     */   
/*     */   public Region getRegiaoLuzIndicativa() {
/* 106 */     return this.regiaoLuzIndicativa;
/*     */   }
/*     */   
/*     */   public boolean GetStatusBtn() {
/* 110 */     return this.BtnAcionado;
/*     */   }
/*     */   
/*     */   public boolean GetStatusBtnConfig() {
/* 114 */     return this.BtnConfigurado;
/*     */   }
/*     */   
/*     */   public int GetCodTipoLinha() {
/* 118 */     return this.CodTipoLinha;
/*     */   }
/*     */   
/*     */   public String GetCorSelecionado() {
/* 122 */     return this.CorSelecionado;
/*     */   }
/*     */   
/*     */   public String GetCorDeSelecionado() {
/* 126 */     return this.CorDeSelecionado;
/*     */   }
/*     */   
/*     */   public String GetTextoForeground() {
/* 130 */     return this.TextoForeground;
/*     */   }
/*     */   
/*     */   public String GetCorLuzIndicaInicial() {
/* 134 */     return this.CorLuzIndicaInicial;
/*     */   }
/*     */   
/*     */   public String GetCorLuzIndicaBtnSelecionado() {
/* 138 */     return this.CorLuzIndicaBtnSelecionado;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getHostName() {
/* 158 */     return this.HostName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHostName(String HostName) {
/* 165 */     this.HostName = HostName;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/fonia/BotaoCmd.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */