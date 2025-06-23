/*     */ package ipqm.gsd.hidra.ihm.util;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.Ambiente;
/*     */ import ipqm.gsd.componentes.nucleo.NucleoAmbiente;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import javafx.util.Duration;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UtilDesempenho
/*     */ {
/*     */   private static VelocidadeAnimacao velocidadeAnimacao;
/*     */   private static FrequenciaAtualizacaoCenarioTatico frequenciaAtualizacaoCenarioTatico;
/*     */   
/*     */   public enum VelocidadeAnimacao
/*     */   {
/*  18 */     LENTA("Lenta", 2.0D),
/*  19 */     PADRAO("Padrao", 1.0D),
/*  20 */     RAPIDA("Rápida", 0.5D),
/*  21 */     DESABILITADA("Desabilitada", 0.0D);
/*     */     
/*     */     private final String descricao;
/*     */     private final double fator;
/*     */     
/*     */     VelocidadeAnimacao(String descricao, double fator) {
/*  27 */       this.descricao = descricao;
/*  28 */       this.fator = fator;
/*     */     }
/*     */     
/*     */     public String getDescricao() {
/*  32 */       return this.descricao;
/*     */     }
/*     */     
/*     */     public double getFator() {
/*  36 */       return this.fator;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum FrequenciaAtualizacaoCenarioTatico
/*     */   {
/*  42 */     PADRAO(50L, 1000L, 100L),
/*  43 */     LENTA(1000L, 1000L, 250L);
/*     */     
/*     */     private final long frequenciaDesenho;
/*     */     private final long frequenciaAtualizacaoCamadas;
/*     */     private final long tempoMinimoAtualizacaoCamadas;
/*     */     
/*     */     FrequenciaAtualizacaoCenarioTatico(long frequenciaDesenho, long frequenciaAtualizacaoCamadas, long tempoMinimoAtualizacaoCamadas) {
/*  50 */       this.frequenciaDesenho = frequenciaDesenho;
/*  51 */       this.frequenciaAtualizacaoCamadas = frequenciaAtualizacaoCamadas;
/*  52 */       this.tempoMinimoAtualizacaoCamadas = tempoMinimoAtualizacaoCamadas;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public long getFrequenciaDesenho() {
/*  61 */       return this.frequenciaDesenho;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public long getFrequenciaAtualizacaoCamadas() {
/*  70 */       return this.frequenciaAtualizacaoCamadas;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public long getTempoMinimoAtualizacaoCamadas() {
/*  81 */       return this.tempoMinimoAtualizacaoCamadas;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  86 */       return "Freq. de desenho: " + getFrequenciaDesenho() + "ms" + " Freq. de atualização de camadas: " + 
/*  87 */         getTempoMinimoAtualizacaoCamadas() + "/" + getFrequenciaAtualizacaoCamadas() + "ms.";
/*     */     }
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
/*     */   public static Duration duracaoAnimacao(double d) {
/* 103 */     return (getVelocidadeAnimacao() == VelocidadeAnimacao.DESABILITADA) ? Duration.ONE : Duration.millis(d * (getVelocidadeAnimacao()).fator);
/*     */   }
/*     */   
/*     */   public static VelocidadeAnimacao getVelocidadeAnimacao() {
/* 107 */     if (velocidadeAnimacao == null) {
/*     */       try {
/* 109 */         String veloc = Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.VELOCIDADE_ANIMACAO);
/* 110 */         velocidadeAnimacao = VelocidadeAnimacao.valueOf(veloc.trim().toUpperCase());
/* 111 */       } catch (NullPointerException|IllegalArgumentException ex) {
/* 112 */         velocidadeAnimacao = VelocidadeAnimacao.PADRAO;
/*     */       } 
/*     */     }
/* 115 */     return velocidadeAnimacao;
/*     */   }
/*     */   
/*     */   public static FrequenciaAtualizacaoCenarioTatico getFrequenciaAtualizacaoCenarioTatico() {
/* 119 */     if (frequenciaAtualizacaoCenarioTatico == null) {
/*     */       try {
/* 121 */         String freq = Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.FREQUENCIA_ATUALIZACAO_CENARIO_TATICO);
/* 122 */         frequenciaAtualizacaoCenarioTatico = FrequenciaAtualizacaoCenarioTatico.valueOf(freq.trim().toUpperCase());
/* 123 */       } catch (NullPointerException|IllegalArgumentException ex) {
/* 124 */         frequenciaAtualizacaoCenarioTatico = FrequenciaAtualizacaoCenarioTatico.PADRAO;
/*     */       } 
/* 126 */       Log.gravarLogInstrucao("Frequencia de atualização do cenátio tático definida: " + frequenciaAtualizacaoCenarioTatico.name() + " (" + frequenciaAtualizacaoCenarioTatico + ")", frequenciaAtualizacaoCenarioTatico);
/*     */     } 
/*     */ 
/*     */     
/* 130 */     return frequenciaAtualizacaoCenarioTatico;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/util/UtilDesempenho.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */