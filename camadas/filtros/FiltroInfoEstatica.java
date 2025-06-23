/*     */ package ipqm.gsd.hidra.ihm.camadas.filtros;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.Ambiente;
/*     */ import ipqm.gsd.componentes.nucleo.VariaveisEstado;
/*     */ import ipqm.gsd.componentes.nucleo.contexto.GravarEstado;
/*     */ import ipqm.gsd.componentes.nucleo.objeto_visual.Filtro;
/*     */ import ipqm.gsd.hidra.ihm.camadas.CamadaInfoEstatica;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FiltroInfoEstatica
/*     */   extends Filtro
/*     */   implements GravarEstado
/*     */ {
/*     */   private boolean exibirRosaDeManobras;
/*     */   private boolean exibirGradeado;
/*     */   private boolean exibirAneis;
/*     */   private boolean exibirUTM;
/*  22 */   private int quantidadeGradeados = 7;
/*  23 */   private int quantidadeAneis = 3;
/*  24 */   private CamadaInfoEstatica.Orientacao orientacao = CamadaInfoEstatica.Orientacao.NORTE;
/*     */ 
/*     */   
/*     */   public void todosFiltros(boolean habilitar) {
/*  28 */     setExibirRosaDeManobras(habilitar);
/*  29 */     setExibirGradeado(habilitar);
/*  30 */     setExibirAneis(habilitar);
/*     */   }
/*     */   
/*     */   public boolean isExibirGradeado() {
/*  34 */     return this.exibirGradeado;
/*     */   }
/*     */   
/*     */   public void setExibirGradeado(boolean exibirGradeado) {
/*  38 */     this.exibirGradeado = exibirGradeado;
/*     */   }
/*     */   
/*     */   public boolean isExibirAneis() {
/*  42 */     return this.exibirAneis;
/*     */   }
/*     */   
/*     */   public void setExibirAneis(boolean exibirAneis) {
/*  46 */     this.exibirAneis = exibirAneis;
/*     */   }
/*     */   
/*     */   public int getQuantidadeAneis() {
/*  50 */     return this.quantidadeAneis;
/*     */   }
/*     */   
/*     */   public void setQuantidadeAneis(int quantidadeAneis) {
/*  54 */     this.quantidadeAneis = quantidadeAneis;
/*     */   }
/*     */   
/*     */   public int getQuantidadeGradeados() {
/*  58 */     return this.quantidadeGradeados;
/*     */   }
/*     */   
/*     */   public void setQuantidadeGradeados(int quantidadeGradeados) {
/*  62 */     this.quantidadeGradeados = quantidadeGradeados;
/*     */   }
/*     */   
/*     */   public boolean isExibirRosaDeManobras() {
/*  66 */     return this.exibirRosaDeManobras;
/*     */   }
/*     */   
/*     */   public void setExibirRosaDeManobras(boolean exibirRosaDeManobras) {
/*  70 */     this.exibirRosaDeManobras = exibirRosaDeManobras;
/*     */   }
/*     */   
/*     */   public CamadaInfoEstatica.Orientacao getOrientacao() {
/*  74 */     return this.orientacao;
/*     */   }
/*     */   
/*     */   public void setOrientacao(CamadaInfoEstatica.Orientacao orientacao) {
/*  78 */     this.orientacao = orientacao;
/*     */   }
/*     */   
/*     */   public boolean isExibirUTM() {
/*  82 */     return this.exibirUTM;
/*     */   }
/*     */   
/*     */   public void setExibirUTM(boolean exibirUTM) {
/*  86 */     this.exibirUTM = exibirUTM;
/*     */   }
/*     */ 
/*     */   
/*     */   public void gravarEstado() {
/*  91 */     if (verificarCondicoesGravacao()) {
/*  92 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_ROSA_DE_MANOBRA_FT, String.valueOf(isExibirRosaDeManobras()));
/*  93 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.ORIENTACAO_ROSA_FT, String.valueOf(getOrientacao()));
/*  94 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_ANEIS_FT, String.valueOf(isExibirAneis()));
/*  95 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.QUANTIDADE_ANEIS_FT, String.valueOf(getQuantidadeAneis()));
/*  96 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_GRADIADO_FT, String.valueOf(isExibirGradeado()));
/*  97 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.QUANTIDADE_GRADIADO_FT, String.valueOf(getQuantidadeGradeados()));
/*  98 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_UTM, String.valueOf(isExibirUTM()));
/*     */       
/* 100 */       Ambiente.getInstance().persisteVariavelAmbiente("estado.properties");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void obterEstado() {
/* 106 */     if (GravarEstado.verificarRecuperacao()) {
/* 107 */       String rosaManobra = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_ROSA_DE_MANOBRA_FT);
/* 108 */       if (!rosaManobra.equals("")) {
/* 109 */         setExibirRosaDeManobras(Boolean.parseBoolean(rosaManobra));
/*     */       }
/*     */       
/* 112 */       String rosaManobraOrientacao = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.ORIENTACAO_ROSA_FT);
/* 113 */       if (!rosaManobraOrientacao.equals("")) {
/* 114 */         if (CamadaInfoEstatica.Orientacao.NORTE.toString().equals(rosaManobraOrientacao)) {
/* 115 */           setOrientacao(CamadaInfoEstatica.Orientacao.NORTE);
/*     */         } else {
/* 117 */           setOrientacao(CamadaInfoEstatica.Orientacao.PROA);
/*     */         } 
/*     */       }
/*     */       
/* 121 */       String utm = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_UTM);
/* 122 */       if (!utm.equals("")) {
/* 123 */         setExibirUTM(Boolean.parseBoolean(utm));
/*     */       }
/*     */       
/* 126 */       String aneis = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_ANEIS_FT);
/* 127 */       if (!aneis.equals("")) {
/* 128 */         setExibirAneis(Boolean.parseBoolean(aneis));
/*     */       }
/*     */       
/* 131 */       String aneisQuantidade = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.QUANTIDADE_ANEIS_FT);
/* 132 */       if (!aneisQuantidade.equals("")) {
/* 133 */         setQuantidadeAneis(Integer.parseInt(aneisQuantidade));
/*     */       }
/*     */       
/* 136 */       String gradiado = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_GRADIADO_FT);
/* 137 */       if (!gradiado.equals("")) {
/* 138 */         setExibirGradeado(Boolean.parseBoolean(gradiado));
/*     */       }
/* 140 */       String gradiadoQuantidade = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.QUANTIDADE_GRADIADO_FT);
/* 141 */       if (!gradiadoQuantidade.equals(""))
/* 142 */         setQuantidadeGradeados(Integer.parseInt(gradiadoQuantidade)); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/camadas/filtros/FiltroInfoEstatica.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */