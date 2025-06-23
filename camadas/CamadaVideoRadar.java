/*     */ package ipqm.gsd.hidra.ihm.camadas;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.componentes.dominio.entidades.Veiculo;
/*     */ import ipqm.gsd.componentes.nucleo.driver.extrator_nmea.Disparo;
/*     */ import ipqm.gsd.componentes.nucleo.objeto_visual.MapaDisparos;
/*     */ import ipqm.gsd.hidra.ihm.camadas.filtros.FiltroVideoRadar;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CamadaVideoRadar
/*     */   extends CamadaBGRA<FiltroVideoRadar>
/*     */ {
/*     */   private static final int NUM_MAX_REPETICAO_DISPAROS = 30;
/*     */   private final MapaDisparos mapaDisparos;
/*     */   private double escalaMN;
/*     */   private Disparo disparoAnterior2;
/*     */   private Disparo disparoAnterior1;
/*     */   private boolean simulacao;
/*     */   
/*     */   public CamadaVideoRadar(FiltroVideoRadar filtro, int largura, int altura, boolean simulacao) {
/*  26 */     super(filtro, largura, altura);
/*  27 */     this.simulacao = simulacao;
/*  28 */     this.mapaDisparos = new MapaDisparos();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void redimensiona(int largura, int altura) {
/*  34 */     super.redimensiona(largura, altura);
/*  35 */     this.mapaDisparos.setDimensao(largura, altura);
/*  36 */     this.mapaDisparos.atualiza();
/*     */   }
/*     */   
/*     */   public boolean isSimulacao() {
/*  40 */     return this.simulacao;
/*     */   }
/*     */   
/*     */   public void setSimulacao(boolean simulacao) {
/*  44 */     this.simulacao = simulacao;
/*     */   }
/*     */   
/*     */   public double getEscalaMN() {
/*  48 */     return this.escalaMN;
/*     */   }
/*     */   
/*     */   public void setEscalaMN(double escalaMN) {
/*  52 */     this.escalaMN = escalaMN;
/*  53 */     this.mapaDisparos.setDimensao(getLargura(), getAltura());
/*  54 */     this.mapaDisparos.setEscalaMN(escalaMN);
/*  55 */     this.mapaDisparos.atualiza();
/*     */   }
/*     */   
/*     */   public int getTamanho() {
/*  59 */     return getLargura();
/*     */   }
/*     */ 
/*     */   
/*     */   private void desvanecer(int valorB, int valorG, int valorR, int valorA) {
/*  64 */     for (int i = 0; i < this.bgra.length; i += 4) {
/*  65 */       if (valorB > 0) {
/*  66 */         int valorOriginal = this.bgra[i + 0] & 0xFF;
/*  67 */         this.bgra[i + 0] = (valorOriginal >= valorB) ? (byte)(this.bgra[i + 0] - valorB) : 0;
/*     */       } 
/*  69 */       if (valorG > 0) {
/*  70 */         int valorOriginal = this.bgra[i + 1] & 0xFF;
/*  71 */         this.bgra[i + 1] = (valorOriginal >= valorG) ? (byte)(this.bgra[i + 1] - valorG) : 0;
/*     */       } 
/*  73 */       if (valorR > 0) {
/*  74 */         int valorOriginal = this.bgra[i + 2] & 0xFF;
/*  75 */         this.bgra[i + 2] = (valorOriginal >= valorR) ? (byte)(this.bgra[i + 2] - valorR) : 0;
/*     */       } 
/*  77 */       if (valorA > 0) {
/*  78 */         int valorOriginal = this.bgra[i + 3] & 0xFF;
/*  79 */         this.bgra[i + 3] = (valorOriginal >= valorA) ? (byte)(this.bgra[i + 3] - valorA) : 0;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void desvanecer(int desvanecimento) {
/*  85 */     if (this.simulacao) {
/*  86 */       desvanecer(desvanecimento, desvanecimento, desvanecimento, desvanecimento);
/*     */     } else {
/*  88 */       desvanecer(0, 0, 0, desvanecimento);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void desvanecer() {
/*  94 */     desvanecer(((FiltroVideoRadar)getFiltro()).getDesvanecimento());
/*     */   }
/*     */   
/*     */   public void desenha(List<Disparo> disparos, CoordenadaRaster ppn) {
/*  98 */     for (Disparo disparo : disparos) {
/*  99 */       desenhaDisparo(disparo, ppn);
/*     */     }
/*     */   }
/*     */   
/*     */   private void desenhaDisparo(Disparo disparo, CoordenadaRaster ppn) {
/* 104 */     FiltroVideoRadar filtroRadar = (FiltroVideoRadar)getFiltro();
/* 105 */     if (this.disparoAnterior1 != null) {
/* 106 */       int marcacao = this.disparoAnterior1.getMarcacao();
/* 107 */       if (!filtroRadar.isIgnorarGiro()) {
/* 108 */         double rumoSuperficie = Veiculo.getVeiculoReferencial().getRumo().getRumoSuperficie().getRumo();
/* 109 */         int deltaMarcacao = (int)(0.5D + rumoSuperficie * this.mapaDisparos.getMaxMarcacao() / 360.0D);
/* 110 */         marcacao += deltaMarcacao;
/*     */       } 
/*     */ 
/*     */       
/* 114 */       marcacao += filtroRadar.getOffsetMarcacao();
/*     */       
/* 116 */       if (marcacao > this.mapaDisparos.getMaxMarcacao() - 1) {
/* 117 */         marcacao -= this.mapaDisparos.getMaxMarcacao();
/* 118 */       } else if (marcacao < 0) {
/* 119 */         marcacao += this.mapaDisparos.getMaxMarcacao();
/*     */       } 
/*     */ 
/*     */       
/* 123 */       while (!this.mapaDisparos.getV(marcacao)) {
/* 124 */         if (++marcacao >= this.mapaDisparos.getMaxMarcacao()) {
/* 125 */           marcacao = 0;
/*     */         }
/*     */       } 
/* 128 */       this.disparoAnterior1.setMarcacao(marcacao);
/*     */ 
/*     */       
/* 131 */       int ponto = 0;
/* 132 */       for (int i = 0; i < (this.disparoAnterior1.getAmostras()).length; i++) {
/*     */         
/* 134 */         int valor = this.disparoAnterior1.getAmostras()[i] & 0xFF;
/* 135 */         if (filtroRadar.getEliminarArtefatos() > 0 && this.disparoAnterior2 != null && i < (this.disparoAnterior2
/* 136 */           .getAmostras()).length && i < (disparo.getAmostras()).length && (this.disparoAnterior2
/* 137 */           .getAmostras()[i] & 0xFF) + (disparo
/* 138 */           .getAmostras()[i] & 0xFF) - valor + 128 < filtroRadar.getEliminarArtefatos()) {
/* 139 */           valor = 0;
/*     */         }
/* 141 */         if (valor < filtroRadar.getLimiar()) {
/* 142 */           valor = 0;
/*     */         }
/* 144 */         else if (filtroRadar.isLimiarCrisp()) {
/* 145 */           valor = -1;
/*     */         } else {
/* 147 */           if (filtroRadar.getGanho() != 1.0F) {
/* 148 */             valor = (int)(valor * filtroRadar.getGanho());
/*     */           }
/* 150 */           valor += filtroRadar.getBrilho();
/* 151 */           if (valor > 255) {
/* 152 */             valor = 255;
/*     */           }
/* 154 */           else if (valor < 0) {
/* 155 */             valor = 0;
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 162 */         int distancia = i * this.disparoAnterior1.getDistanciaAmostras() + filtroRadar.getOffsetDistancia();
/* 163 */         while (this.mapaDisparos.getDistancia(marcacao, ponto) <= distancia) {
/* 164 */           int x = ppn.getX() + this.mapaDisparos.getX(marcacao, ponto);
/* 165 */           int y = getAltura() - ppn.getY() + this.mapaDisparos.getY(marcacao, ponto);
/*     */ 
/*     */           
/* 168 */           if (x >= 0 && x < getLargura() && y >= 0 && y < getAltura()) {
/* 169 */             if (this.simulacao) {
/* 170 */               setMarca(x, y, (byte)0, (byte)-1, (byte)-1, (byte)valor);
/*     */             } else {
/* 172 */               setMarca(x, y, filtroRadar.getModoVisualizacao().getColor(), (byte)valor);
/*     */             } 
/*     */ 
/*     */             
/* 176 */             if (this.disparoAnterior2 != null) {
/* 177 */               int ma = marcacao - 1;
/* 178 */               for (; ma > this.disparoAnterior2.getMarcacao() && marcacao - ma < 30; ma--) {
/* 179 */                 int xa = ppn.getX() + this.mapaDisparos.getX(ma, ponto);
/* 180 */                 int ya = getAltura() - ppn.getY() + this.mapaDisparos.getY(ma, ponto);
/* 181 */                 if (this.simulacao) {
/* 182 */                   setMarca(xa, ya, (byte)0, (byte)-1, (byte)-1, (byte)valor);
/*     */                 } else {
/* 184 */                   setMarca(xa, ya, filtroRadar.getModoVisualizacao().getColor(), (byte)valor);
/*     */                 } 
/*     */               } 
/*     */             } 
/*     */           } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 194 */           ponto++;
/*     */         } 
/*     */       } 
/*     */     } 
/* 198 */     this.disparoAnterior2 = this.disparoAnterior1;
/* 199 */     this.disparoAnterior1 = disparo;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/camadas/CamadaVideoRadar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */