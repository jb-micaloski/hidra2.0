/*     */ package ipqm.gsd.hidra.ihm.widgets.painel_permanente.itens;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*     */ import ipqm.gsd.hidra.ihm.widgets.painel_permanente.WidgetPainelPermanente;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ItemPainelPermanente
/*     */ {
/*     */   public static final int RUMO_FUNDO = 1;
/*     */   public static final int RUMO_SUPERFICIE = 2;
/*     */   public static final int VELOCIDADE_FUNDO = 3;
/*     */   public static final int VELOCIDADE_SUPERFICIE = 4;
/*     */   public static final int POSICAO_GEOGRAFICA = 5;
/*     */   public static final int POSICAO_CARTESIANA = 6;
/*     */   public static final int HORARIO = 7;
/*     */   public static final int NUMERO_ACOMPANHAMENTOS = 8;
/*     */   public static final int DIR_VELOC_VENTO = 9;
/*     */   public static final int NUMERO_USUARIOS = 10;
/*     */   public static final int NUMERO_FUNCIONALIDADES = 11;
/*     */   public static final int NUMERO_MAQUINAS = 12;
/*     */   public static final int MAQUINAS_CONECTADAS = 13;
/*     */   public static final int NUMERO_VEICULOS = 14;
/*     */   public static final int CENTRO_EXERCICIO = 15;
/*     */   public static final int TEMPO_REPRODUCAO = 16;
/*     */   public static final int TEMPO_TOTAL_REPRODUCAO = 17;
/*     */   public static final int NUMERO_ACOMPANHAMENTOS_CLASSIFICADOS = 18;
/*     */   public static final int NUMERO_ROTAS_MONITORADAS = 19;
/*     */   public static final int NUMERO_INCIDENTES = 20;
/*     */   public static final int NUMERO_SRU_DISPONIVEIS = 21;
/*     */   public static final int ERROS_OCORRIDOS = 22;
/*     */   public static final int HORARIO_REAL = 23;
/*     */   public static final int NUMERO_ACOMPANHAMENTOS_FUNDIDOS = 24;
/*     */   public static final int NUMERO_ACOMPANHAMENTOS_PERDIDOS = 25;
/*     */   public static final int MAQUINAS_EXERCICIO = 26;
/*     */   public static final int PROPRIA_MAQUINA = 27;
/*     */   public static final int DATA_HORA = 28;
/*     */   private final int tipo;
/*     */   private final String nome;
/*     */   private WidgetPainelPermanente painelPermanente;
/*  48 */   private int frequencia = 1;
/*  49 */   private int controleFrequencia = 0;
/*     */   
/*     */   public ItemPainelPermanente(String nome, int tipo) {
/*  52 */     this.nome = nome;
/*  53 */     this.tipo = tipo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getTipo() {
/*  62 */     return this.tipo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLabel(int posicao) {
/*  71 */     this.painelPermanente.getControladorPainelPermanente().setLabel(posicao, this.nome);
/*  72 */     this.painelPermanente.getControladorPainelPermanente().setValue(posicao, "...");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(int posicao, List<ObjetoDOMINIO> listaObjetos) {
/*  83 */     if (this.controleFrequencia == 0) {
/*  84 */       this.controleFrequencia = this.frequencia;
/*  85 */       String valor = geraValor(listaObjetos);
/*  86 */       this.painelPermanente.getControladorPainelPermanente().setValue(posicao, valor);
/*     */     } 
/*  88 */     this.controleFrequencia--;
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract String geraValor(List<ObjetoDOMINIO> paramList);
/*     */   
/*     */   public String toString() {
/*  95 */     return this.nome;
/*     */   }
/*     */   
/*     */   public void setPainelPermanente(WidgetPainelPermanente painelPermanente) {
/*  99 */     this.painelPermanente = painelPermanente;
/*     */   }
/*     */   
/*     */   public void setFrequencia(int frequencia) {
/* 103 */     this.frequencia = frequencia;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/painel_permanente/itens/ItemPainelPermanente.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */