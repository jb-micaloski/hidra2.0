/*     */ package ipqm.gsd.hidra.ihm.interacao;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.Rumo;
/*     */ import ipqm.gsd.componentes.dominio.entidades.Veiculo;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.ThreadPool;
/*     */ import ipqm.gsd.componentes.nucleo.driver.Sensor;
/*     */ import ipqm.gsd.componentes.nucleo.gestao.GestorCartasGenerico;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.logon.perfis.PerfilUsuario;
/*     */ import ipqm.gsd.hidra.ihm.gestao.GestorSinaisFX;
/*     */ import ipqm.gsd.hidra.ihm.gestao.OuvinteVideoRadar;
/*     */ import ipqm.gsd.hidra.ihm.projetos.geral.cenas.controladores.ControladorCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.util.UtilDesempenho;
/*     */ import ipqm.gsd.radar.model.RadarBeam;
/*     */ import ipqm.gsd.radar.view.RadarImage;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.ScheduledFuture;
/*     */ import javafx.animation.AnimationTimer;
/*     */ import javafx.scene.Cursor;
/*     */ import javafx.scene.canvas.GraphicsContext;
/*     */ import javafx.scene.image.Image;
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
/*     */ public abstract class CanvasCenarioTaticoFX<E extends ControladorCenarioTatico>
/*     */   extends CanvasCenarioTatico<E>
/*     */   implements OuvinteVideoRadar, Serializable
/*     */ {
/*     */   private AnimationTimer animadorFX;
/*     */   private ScheduledFuture atualizadorAutomaticoCamadas;
/*     */   
/*     */   public void inicializa(double larguraCanvas, double alturaCanvas, double layoutX, double layoutY) {
/*  44 */     super.inicializa(larguraCanvas, alturaCanvas, layoutX, layoutY);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  49 */     this.animadorFX = new AnimationTimer()
/*     */       {
/*     */         public void handle(long now)
/*     */         {
/*  53 */           if (System.currentTimeMillis() - CanvasCenarioTaticoFX.this.ultimoDesenho > UtilDesempenho.getFrequenciaAtualizacaoCenarioTatico().getFrequenciaDesenho()) {
/*     */             try {
/*  55 */               CanvasCenarioTaticoFX.this.desenharCamadas();
/*  56 */             } catch (Exception e) {
/*  57 */               Log.gravarLogExcecao("Ocorreu algum erro ao desenhar as camadas do CT", this, e);
/*     */             } 
/*     */           }
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected synchronized void desenharCamadas() {
/*     */     Color cor;
/*  70 */     this.ultimoDesenho = System.currentTimeMillis();
/*  71 */     GraphicsContext gcMapa = this.mapaCanvas.getGraphicsContext2D();
/*     */     
/*  73 */     if (this.filtroMapaAlternativo.isExibir()) {
/*  74 */       float red = 0.5647059F;
/*  75 */       float green = 0.8F;
/*  76 */       float blue = 0.79607844F;
/*  77 */       cor = new Color(red, green, blue, 0.0D);
/*     */     } else {
/*  79 */       cor = new Color(0.0D, 0.0D, 0.0D, 0.0D);
/*     */     } 
/*  81 */     gcMapa.clearRect(0.0D, 0.0D, getLargura(), getAltura());
/*  82 */     gcMapa.setFill((Paint)cor);
/*  83 */     gcMapa.fillRect(0.0D, 0.0D, getLargura(), getAltura());
/*     */     
/*  85 */     GestorCartasGenerico.ServidorCartas servidor = PerfilUsuario.getPerfilUsuarioAtual().getServidorCartas();
/*  86 */     boolean desenhaVideoBruto = true;
/*  87 */     if (Veiculo.getVeiculoReferencial() != null && 
/*  88 */       Veiculo.getVeiculoReferencial().getSensor(Sensor.Tipo.RADAR_BUSCA, getIdRadarSelecionado()) != null) {
/*  89 */       Sensor sensor = Veiculo.getVeiculoReferencial().getSensor(Sensor.Tipo.RADAR_BUSCA, getIdRadarSelecionado());
/*  90 */       desenhaVideoBruto = (sensor.isHabilitado() && !sensor.isAvariado());
/*     */     } 
/*     */ 
/*     */     
/*  94 */     if (servidor != GestorCartasGenerico.ServidorCartas.IPQM) {
/*  95 */       if (this.filtroSatelite.isExibir()) {
/*  96 */         gcMapa.setGlobalAlpha(this.filtroSatelite.getOpacidade());
/*  97 */         gcMapa.drawImage((Image)this.camadaSatelite.getImagemFX(), 0.0D, 0.0D);
/*     */       } 
/*  99 */       if (this.filtroMapaAlternativo.isExibir()) {
/* 100 */         gcMapa.setGlobalAlpha(this.filtroMapaAlternativo.getOpacidade());
/* 101 */         gcMapa.drawImage((Image)this.camadaMapaAlternativo.getImagemFX(), 0.0D, 0.0D);
/*     */       } 
/* 103 */       if (this.filtroCartaNautica.isExibir()) {
/* 104 */         gcMapa.setGlobalAlpha(this.filtroCartaNautica.getOpacidade());
/* 105 */         gcMapa.drawImage((Image)this.camadaCarta.getImagemFX(), 0.0D, 0.0D);
/*     */       } 
/* 107 */       if (this.filtroVideoRadar.isExibir()) {
/* 108 */         gcMapa.setGlobalAlpha(this.filtroVideoRadar.getOpacidade());
/* 109 */         if (desenhaVideoBruto) {
/* 110 */           gcMapa.drawImage((Image)this.camadaRadarImage.getImage(), 0.0D, 0.0D);
/*     */         }
/*     */       } 
/* 113 */       if (this.filtroObjetoTatico.isExibir()) {
/* 114 */         gcMapa.setGlobalAlpha(this.filtroObjetoTatico.getOpacidade());
/* 115 */         gcMapa.drawImage((Image)this.camadaTatica.getImagemFX(), 0.0D, 0.0D);
/*     */       } 
/* 117 */       if (this.camadaInfo != null) {
/* 118 */         gcMapa.setGlobalAlpha(1.0D);
/* 119 */         gcMapa.drawImage((Image)this.camadaInfo.getImagemFX(), 0.0D, 0.0D);
/*     */       } 
/* 121 */       if (this.camadaInfoEstatica != null) {
/* 122 */         gcMapa.setGlobalAlpha(1.0D);
/* 123 */         gcMapa.drawImage((Image)this.camadaInfoEstatica.getImagemFX(), 0.0D, 0.0D);
/*     */       } 
/*     */     } else {
/* 126 */       if (this.filtroVideoRadar.isExibir()) {
/* 127 */         if (this.filtroSatelite.isExibir()) {
/* 128 */           if (this.filtroCartaNautica.isExibir()) {
/* 129 */             gcMapa.setGlobalAlpha(this.filtroCartaNautica.getOpacidade());
/* 130 */             gcMapa.drawImage((Image)this.camadaCartaBase.getImagemFX(), 0.0D, 0.0D);
/*     */           } 
/*     */           
/* 133 */           gcMapa.setGlobalAlpha(this.filtroSatelite.getOpacidade());
/* 134 */           gcMapa.drawImage((Image)this.camadaSatelite.getImagemFX(), 0.0D, 0.0D);
/*     */           
/* 136 */           if (this.filtroMapaAlternativo.isExibir()) {
/* 137 */             gcMapa.setGlobalAlpha(this.filtroMapaAlternativo.getOpacidade());
/* 138 */             gcMapa.drawImage((Image)this.camadaMapaAlternativo.getImagemFX(), 0.0D, 0.0D);
/*     */           } 
/*     */           
/* 141 */           if (this.filtroCartaNautica.isExibir()) {
/* 142 */             gcMapa.setGlobalAlpha(this.filtroCartaNautica.getOpacidade());
/* 143 */             gcMapa.drawImage((Image)this.camadaCarta.getImagemFX(), 0.0D, 0.0D);
/*     */           } 
/*     */           
/* 146 */           gcMapa.setGlobalAlpha(this.filtroVideoRadar.getOpacidade());
/* 147 */           if (desenhaVideoBruto) {
/* 148 */             gcMapa.drawImage((Image)this.camadaRadarImage.getImage(), 0.0D, 0.0D);
/*     */           }
/*     */           
/* 151 */           if (this.filtroCartaNautica.isExibir()) {
/* 152 */             gcMapa.setGlobalAlpha(1.0D);
/* 153 */             gcMapa.drawImage((Image)this.camadaCartaTopo.getImagemFX(), 0.0D, 0.0D);
/*     */           } 
/*     */         } else {
/* 156 */           if (this.filtroCartaNautica.isExibir()) {
/* 157 */             gcMapa.setGlobalAlpha(this.filtroCartaNautica.getOpacidade());
/* 158 */             gcMapa.drawImage((Image)this.camadaCartaBase.getImagemFX(), 0.0D, 0.0D);
/*     */           } 
/*     */           
/* 161 */           gcMapa.setGlobalAlpha(this.filtroVideoRadar.getOpacidade());
/* 162 */           if (desenhaVideoBruto) {
/* 163 */             gcMapa.drawImage((Image)this.camadaRadarImage.getImage(), 0.0D, 0.0D);
/*     */           }
/*     */           
/* 166 */           if (this.filtroCartaNautica.isExibir()) {
/* 167 */             gcMapa.setGlobalAlpha(1.0D);
/* 168 */             gcMapa.drawImage((Image)this.camadaCartaTopo.getImagemFX(), 0.0D, 0.0D);
/*     */           } 
/*     */         } 
/* 171 */       } else if (this.filtroSatelite.isExibir()) {
/* 172 */         if (this.filtroCartaNautica.isExibir()) {
/* 173 */           gcMapa.setGlobalAlpha(this.filtroCartaNautica.getOpacidade());
/* 174 */           gcMapa.drawImage((Image)this.camadaCartaBase.getImagemFX(), 0.0D, 0.0D);
/*     */         } 
/*     */         
/* 177 */         gcMapa.setGlobalAlpha(this.filtroSatelite.getOpacidade());
/* 178 */         gcMapa.drawImage((Image)this.camadaSatelite.getImagemFX(), 0.0D, 0.0D);
/*     */         
/* 180 */         if (this.filtroMapaAlternativo.isExibir()) {
/* 181 */           gcMapa.setGlobalAlpha(this.filtroMapaAlternativo.getOpacidade());
/* 182 */           gcMapa.drawImage((Image)this.camadaMapaAlternativo.getImagemFX(), 0.0D, 0.0D);
/*     */         } 
/*     */         
/* 185 */         if (this.filtroCartaNautica.isExibir()) {
/* 186 */           gcMapa.setGlobalAlpha(this.filtroCartaNautica.getOpacidade());
/* 187 */           gcMapa.drawImage((Image)this.camadaCartaTopo.getImagemFX(), 0.0D, 0.0D);
/*     */         } 
/*     */       } else {
/* 190 */         if (this.filtroCartaNautica.isExibir()) {
/* 191 */           gcMapa.setGlobalAlpha(this.filtroCartaNautica.getOpacidade());
/* 192 */           gcMapa.drawImage((Image)this.camadaCarta.getImagemFX(), 0.0D, 0.0D);
/*     */         } 
/*     */         
/* 195 */         if (this.filtroMapaAlternativo.isExibir()) {
/* 196 */           gcMapa.setGlobalAlpha(this.filtroMapaAlternativo.getOpacidade());
/* 197 */           gcMapa.drawImage((Image)this.camadaMapaAlternativo.getImagemFX(), 0.0D, 0.0D);
/*     */         } 
/*     */       } 
/*     */       
/* 201 */       if (this.filtroObjetoTatico.isExibir()) {
/* 202 */         gcMapa.setGlobalAlpha(this.filtroObjetoTatico.getOpacidade());
/* 203 */         gcMapa.drawImage((Image)this.camadaTatica.getImagemFX(), 0.0D, 0.0D);
/*     */       } 
/* 205 */       if (this.camadaInfo != null) {
/* 206 */         gcMapa.setGlobalAlpha(1.0D);
/* 207 */         gcMapa.drawImage((Image)this.camadaInfo.getImagemFX(), 0.0D, 0.0D);
/*     */       } 
/* 209 */       if (this.camadaInfoEstatica != null) {
/* 210 */         gcMapa.setGlobalAlpha(1.0D);
/* 211 */         gcMapa.drawImage((Image)this.camadaInfoEstatica.getImagemFX(), 0.0D, 0.0D);
/*     */       } 
/*     */     } 
/* 214 */     if (getIdTarefa() == CanvasEspacial.Tarefas.MOVIMENTACAO_3D) {
/* 215 */       update3DCanvas();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCursor(Cursor cursor) {
/* 221 */     this.cenaCanvas3D.setCursor(cursor);
/*     */   }
/*     */ 
/*     */   
/*     */   public Cursor getCursor() {
/* 226 */     return this.cenaCanvas3D.getCursor();
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void setEscalaMN(double alcance) {
/* 231 */     super.setEscalaMN(alcance);
/* 232 */     if (this.camadaRadarImage != null) {
/* 233 */       this.camadaRadarImage.setRange(alcance);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void alterarTamanhoCamadas(int largura, int altura) {
/* 244 */     super.alterarTamanhoCamadas(largura, altura);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getLargura() {
/* 249 */     return (int)this.mapaCanvas.getWidth();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getAltura() {
/* 254 */     return (int)this.mapaCanvas.getHeight();
/*     */   }
/*     */ 
/*     */   
/*     */   public void atualizarCamadaRadar(List<RadarBeam> disparos) {
/* 259 */     if (this.filtroVideoRadar.isExibir()) {
/* 260 */       Veiculo veiculo = Veiculo.getVeiculoReferencial();
/* 261 */       double rumo = Rumo.calculaRumoDeclinacaoMagnetica(veiculo.getCinematicaSimulada().getRumo().getRumoSuperficie().getRumo(), true);
/*     */       try {
/* 263 */         disparos.forEach(disparo -> this.camadaRadarImage.draw(disparo, this.posReferencialRaster.getX(), this.posReferencialRaster.getY(), rumo, this.filtroVideoRadar.getRadarImageParameters()));
/*     */       
/*     */       }
/* 266 */       catch (Exception ex) {
/* 267 */         this.camadaRadarImage = new RadarImage(1040, 1025, getEscalaMN());
/* 268 */         Log.gravarLogExcecao("Erro ao desenhar disparos do radar.", this, ex);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void iniciarAnimacao() {
/* 279 */     ((GestorSinaisFX)Mediador.getInstancia().getGestor().getGestorSinais()).adicionaOuvinteVideoRadar(this);
/* 280 */     setRedesenhoAutomatico(true);
/* 281 */     this.animadorFX.start();
/* 282 */     this.atualizadorAutomaticoCamadas = ThreadPool.agendarExecucaoPeriodica(() -> {
/*     */           if (System.currentTimeMillis() - this.ultimaAtualizacaoCamadas >= UtilDesempenho.getFrequenciaAtualizacaoCenarioTatico().getFrequenciaAtualizacaoCamadas()) {
/*     */             try {
/*     */               atualizarCamadas();
/* 286 */             } catch (Exception e) {
/*     */               Log.gravarLogExcecao("Ocorreu algum erro ao atualizar as camadas do CT", this, e);
/*     */             } 
/*     */           }
/* 290 */         }500L, "Atualizador de camadas automático: " + UtilDesempenho.getFrequenciaAtualizacaoCenarioTatico());
/*     */     
/* 292 */     show2DCanvas();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pararAnimacao() {
/* 301 */     ((GestorSinaisFX)Mediador.getInstancia().getGestor().getGestorSinais()).removeOuvinteVideoRadar(this);
/* 302 */     this.animadorFX.stop();
/* 303 */     this.atualizadorAutomaticoCamadas.cancel(false);
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/CanvasCenarioTaticoFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */