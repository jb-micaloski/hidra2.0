/*     */ package ipqm.gsd.hidra.ihm.camadas.filtros;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.Ambiente;
/*     */ import ipqm.gsd.componentes.nucleo.VariaveisEstado;
/*     */ import ipqm.gsd.componentes.nucleo.contexto.GravarEstado;
/*     */ import ipqm.gsd.componentes.nucleo.objeto_visual.Filtro;
/*     */ import ipqm.gsd.radar.view.RadarImageParameters;
/*     */ import java.awt.Color;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ public class FiltroVideoRadar extends Filtro implements Serializable, GravarEstado {
/*     */   private ModoVisualizacao modoVisualizacao;
/*     */   private float ganho;
/*     */   private int brilho;
/*     */   private int desvanecimento;
/*     */   private int offsetMarcacao;
/*     */   
/*     */   public void gravarEstado() {
/*  19 */     if (verificarCondicoesGravacao()) {
/*  20 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_RADAR_FR, String.valueOf(isExibir()));
/*  21 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.GANHO_FR, String.valueOf(String.valueOf(getGanho())));
/*  22 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.BRILHO_FR, String.valueOf(String.valueOf(getBrilho())));
/*  23 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.OPACIDADE_FR, String.valueOf(getOpacidade()));
/*  24 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.PERSISTENCIA_FR, String.valueOf(getDesvanecimento()));
/*  25 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.FTC_FR, String.valueOf(getLimiar()));
/*  26 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.STC_FR, String.valueOf(getEliminarArtefatos()));
/*  27 */       Ambiente.getInstance().persisteVariavelAmbiente("estado.properties");
/*     */     } 
/*     */   }
/*     */   private int offsetDistancia; private int limiar; private boolean limiarCrisp; private int eliminarArtefatos; private boolean ignorarGiro; private RadarImageParameters radarImageParameters;
/*     */   
/*     */   public void obterEstado() {
/*  33 */     if (GravarEstado.verificarRecuperacao()) {
/*  34 */       String exibirRadar = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_RADAR_FR);
/*  35 */       if (!exibirRadar.equals("")) {
/*  36 */         setExibir(Boolean.valueOf(exibirRadar).booleanValue());
/*  37 */         setExibirVideoSecundario(Boolean.valueOf(exibirRadar).booleanValue());
/*     */       } 
/*     */       
/*  40 */       String ganho = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.GANHO_FR);
/*  41 */       if (!ganho.equals("")) {
/*  42 */         setGanho(Float.valueOf(ganho).floatValue());
/*     */       }
/*     */       
/*  45 */       String brilho = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.BRILHO_FR);
/*  46 */       if (!brilho.equals("")) {
/*  47 */         setBrilho(Integer.valueOf(brilho).intValue());
/*     */       }
/*     */       
/*  50 */       String opacidade = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.OPACIDADE_FR);
/*  51 */       if (!opacidade.equals("")) {
/*  52 */         setOpacidade(Float.valueOf(opacidade).floatValue());
/*     */       }
/*     */       
/*  55 */       String persistencia = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.PERSISTENCIA_FR);
/*  56 */       if (!persistencia.equals("")) {
/*  57 */         setDesvanecimento(Integer.valueOf(persistencia).intValue());
/*     */       }
/*     */       
/*  60 */       String limiar = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.FTC_FR);
/*  61 */       if (!limiar.equals("")) {
/*  62 */         setLimiar(Integer.valueOf(limiar).intValue());
/*     */       }
/*     */       
/*  65 */       String artefatos = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.STC_FR);
/*  66 */       if (!artefatos.equals("")) {
/*  67 */         setEliminarArtefatos(Integer.valueOf(artefatos).intValue());
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public enum ModoVisualizacao
/*     */   {
/*  74 */     Diurno(104, 228, 86),
/*  75 */     Entardercer(35, 76, 29),
/*  76 */     Noturno(22, 34, 7);
/*     */     
/*     */     private final Color color;
/*     */     
/*     */     ModoVisualizacao(int r, int g, int b) {
/*  81 */       this.color = new Color(r, g, b);
/*     */     }
/*     */     
/*     */     public Color getColor() {
/*  85 */       return this.color;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean exibirVideoSecundario = true;
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
/*     */   public FiltroVideoRadar() {
/* 158 */     setExibir(false);
/* 159 */     this.radarImageParameters = new RadarImageParameters();
/* 160 */     this.modoVisualizacao = ModoVisualizacao.Diurno;
/* 161 */     this.desvanecimento = 3;
/* 162 */     this.ganho = 1.0F;
/* 163 */     setOpacidade(1.0F);
/* 164 */     obterEstado();
/*     */   }
/*     */   
/*     */   public RadarImageParameters getRadarImageParameters() {
/* 168 */     return this.radarImageParameters;
/*     */   }
/*     */   
/*     */   public void setRadarImageParameters(RadarImageParameters radarImageParameters) {
/* 172 */     this.radarImageParameters = radarImageParameters;
/*     */   }
/*     */   
/*     */   public ModoVisualizacao getModoVisualizacao() {
/* 176 */     return this.modoVisualizacao;
/*     */   }
/*     */   
/*     */   public void setModoVisualizacao(ModoVisualizacao modoVisualizacao) {
/* 180 */     this.modoVisualizacao = modoVisualizacao;
/*     */   }
/*     */   
/*     */   public float getGanho() {
/* 184 */     return this.radarImageParameters.getGain();
/*     */   }
/*     */   
/*     */   public void setGanho(float ganho) {
/* 188 */     this.radarImageParameters.setGain(ganho);
/* 189 */     this.ganho = this.radarImageParameters.getGain();
/*     */   }
/*     */   
/*     */   public int getBrilho() {
/* 193 */     return this.radarImageParameters.getBrightness();
/*     */   }
/*     */   
/*     */   public void setBrilho(int brilho) {
/* 197 */     this.radarImageParameters.setBrightness(brilho);
/* 198 */     this.brilho = this.radarImageParameters.getBrightness();
/*     */   }
/*     */   
/*     */   public int getDesvanecimento() {
/* 202 */     return this.radarImageParameters.getFadingLevel();
/*     */   }
/*     */   
/*     */   public void setDesvanecimento(int desvanecimento) {
/* 206 */     this.radarImageParameters.setFadingLevel(desvanecimento);
/* 207 */     this.desvanecimento = this.radarImageParameters.getFadingLevel();
/*     */   }
/*     */   
/*     */   public int getOffsetMarcacao() {
/* 211 */     return this.radarImageParameters.getHeadingOffset();
/*     */   }
/*     */   
/*     */   public double getOffsetMarcacaoGraus() {
/* 215 */     return this.radarImageParameters.getHeadingOffsetDegrees() / 4096.0D * 360.0D;
/*     */   }
/*     */   
/*     */   public void setOffsetMarcacao(int offsetMarcacao) {
/* 219 */     this.radarImageParameters.setHeadingOffset(offsetMarcacao);
/* 220 */     this.offsetMarcacao = this.radarImageParameters.getHeadingOffset();
/*     */   }
/*     */   
/*     */   public void setOffsetMarcacao(double offsetMarcacaoGraus) {
/* 224 */     this.radarImageParameters.setHeadingOffsetDegrees((int)(0.5D + offsetMarcacaoGraus * 4096.0D / 360.0D));
/* 225 */     this.offsetMarcacao = (int)this.radarImageParameters.getHeadingOffsetDegrees();
/*     */   }
/*     */   
/*     */   public int getOffsetDistancia() {
/* 229 */     return this.radarImageParameters.getDistanceOffset();
/*     */   }
/*     */   
/*     */   public void setOffsetDistancia(int offsetDistancia) {
/* 233 */     this.radarImageParameters.setDistanceOffset(offsetDistancia);
/* 234 */     this.offsetDistancia = this.radarImageParameters.getDistanceOffset();
/*     */   }
/*     */   
/*     */   public int getLimiar() {
/* 238 */     return this.radarImageParameters.getSignalThreshold();
/*     */   }
/*     */   
/*     */   public void setLimiar(int limiar) {
/* 242 */     this.radarImageParameters.setSignalThreshold(limiar);
/* 243 */     this.limiar = this.radarImageParameters.getSignalThreshold();
/*     */   }
/*     */   
/*     */   public boolean isLimiarCrisp() {
/* 247 */     return this.limiarCrisp;
/*     */   }
/*     */   
/*     */   public void setLimiarCrisp(boolean limiarCrisp) {
/* 251 */     this.limiarCrisp = limiarCrisp;
/*     */   }
/*     */   
/*     */   public int getEliminarArtefatos() {
/* 255 */     return this.radarImageParameters.getAntiClutterSeaLevel();
/*     */   }
/*     */   
/*     */   public void setEliminarArtefatos(int eliminarDefeitos) {
/* 259 */     this.radarImageParameters.setAntiClutterSeaLevel(eliminarDefeitos);
/* 260 */     this.eliminarArtefatos = this.radarImageParameters.getAntiClutterSeaLevel();
/*     */   }
/*     */   
/*     */   public boolean isIgnorarGiro() {
/* 264 */     return this.ignorarGiro;
/*     */   }
/*     */   
/*     */   public void setIgnorarGiro(boolean ignoraGiro) {
/* 268 */     this.ignorarGiro = ignoraGiro;
/*     */   }
/*     */   
/*     */   public boolean isExibirVideoSecundario() {
/* 272 */     return this.exibirVideoSecundario;
/*     */   }
/*     */   
/*     */   public void setExibirVideoSecundario(boolean exibirVideoSecundario) {
/* 276 */     this.exibirVideoSecundario = exibirVideoSecundario;
/*     */   }
/*     */   
/*     */   public void todosFiltros(boolean habilitar) {}
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/camadas/filtros/FiltroVideoRadar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */