/*     */ package ipqm.gsd.hidra.ihm.camadas;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.navegacao.JanelaGeografica;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.gestao.GestorCartasGenerico;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.hidra.ihm.camadas.filtros.FiltroCartaNautica;
/*     */ import ipqm.gsd.smaps.view.MapsParameters;
/*     */ import ipqm.gsd.smaps.view.vector.ENCParameters;
/*     */ import ipqm.gsd.smaps.view.vector.ENCViewer;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.Serializable;
/*     */ import javafx.embed.swing.SwingFXUtils;
/*     */ 
/*     */ public class CamadaCarta extends CamadaBI<FiltroCartaNautica> implements Serializable {
/*     */   private ENCParameters parameters;
/*     */   private GestorCartasGenerico.ServidorCartas servidor;
/*     */   public String simboloAtual;
/*     */   public String textoAtual;
/*     */   
/*     */   public enum ModoDesenho {
/*  22 */     RADAR_BASE,
/*  23 */     RADAR_TOPO,
/*  24 */     SATELITE_BASE,
/*  25 */     SATELITE_TOPO,
/*  26 */     TUDO;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CamadaCarta(FiltroCartaNautica filtro, int largura, int altura, GestorCartasGenerico.ServidorCartas servidor) {
/*  35 */     super(filtro, largura, altura);
/*  36 */     this.servidor = servidor;
/*  37 */     switch (servidor) {
/*     */       case RADAR_BASE:
/*     */       case RADAR_TOPO:
/*  40 */         this.parameters = new ENCParameters();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ENCParameters getParameters() {
/*  51 */     return this.parameters;
/*     */   }
/*     */   
/*     */   public void setParameters(ENCParameters parameters) {
/*  55 */     this.parameters = parameters;
/*     */   }
/*     */ 
/*     */   
/*     */   public void desenha(JanelaGeografica janela, ModoDesenho modoDesenho) {
/*  60 */     GestorCartasGenerico gestor = Mediador.getInstancia().getGestor().getGestorCartas();
/*     */     
/*  62 */     gestor.getParameters().setSafetyContour(gestor.getParameters().getSafetyContour());
/*  63 */     gestor.getParameters().setSafetyDepth(gestor.getParameters().getSafetyDepth());
/*  64 */     gestor.getParameters().setDeepContour(gestor.getParameters().getDeepContour());
/*  65 */     gestor.getParameters().setShallowContour(gestor.getParameters().getShallowContour());
/*     */     
/*  67 */     double latMin = janela.getMin().getLatitude();
/*  68 */     double latMax = janela.getMax().getLatitude();
/*  69 */     double longMin = janela.getMin().getLongitude();
/*  70 */     double longMax = janela.getMax().getLongitude();
/*     */     
/*     */     try {
/*     */       BufferedImage img;
/*     */       
/*  75 */       if (this.servidor == GestorCartasGenerico.ServidorCartas.IPQM) {
/*  76 */         switch (modoDesenho) {
/*     */           case RADAR_BASE:
/*  78 */             img = gestor.draw(longMin, latMin, longMax, latMax, getLargura(), getAltura(), ENCViewer.OverlayMode.RADAR_SUPRESSED, (MapsParameters)gestor
/*  79 */                 .getParameters(), this.servidor);
/*     */             break;
/*     */           case RADAR_TOPO:
/*  82 */             img = gestor.draw(longMin, latMin, longMax, latMax, getLargura(), getAltura(), ENCViewer.OverlayMode.RADAR_ONTOP, (MapsParameters)gestor
/*  83 */                 .getParameters(), this.servidor);
/*     */             break;
/*     */           case SATELITE_BASE:
/*  86 */             img = gestor.draw(longMin, latMin, longMax, latMax, getLargura(), getAltura(), ENCViewer.OverlayMode.SATELLITE_SUPRESSED, (MapsParameters)gestor
/*  87 */                 .getParameters(), this.servidor);
/*     */             break;
/*     */           case SATELITE_TOPO:
/*  90 */             img = gestor.draw(longMin, latMin, longMax, latMax, getLargura(), getAltura(), ENCViewer.OverlayMode.SATELLITE_ONTOP, (MapsParameters)gestor
/*  91 */                 .getParameters(), this.servidor);
/*     */             break;
/*     */           default:
/*  94 */             img = gestor.draw(longMin, latMin, longMax, latMax, getLargura(), getAltura(), ENCViewer.OverlayMode.ALL, (MapsParameters)gestor
/*  95 */                 .getParameters(), this.servidor); break;
/*     */         } 
/*     */       } else {
/*  98 */         img = gestor.draw(longMin, latMin, longMax, latMax, getLargura(), getAltura(), ENCViewer.OverlayMode.ALL, (MapsParameters)gestor
/*  99 */             .getParameters(), this.servidor);
/*     */       } 
/* 101 */       this.imagemFX = SwingFXUtils.toFXImage(img, this.imagemFX);
/* 102 */     } catch (Exception ex) {
/* 103 */       Log.gravarLogExcecao("Erro ao desenhar mapa!", this, ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void desenha(JanelaGeografica janela) {
/* 108 */     desenha(janela, ModoDesenho.TUDO);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void geraImagemFX() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSimboloAtual() {
/* 119 */     return this.simboloAtual;
/*     */   }
/*     */   
/*     */   public void setSimboloAtual(String simboloAtual) {
/* 123 */     this.simboloAtual = simboloAtual;
/*     */   }
/*     */   
/*     */   public String getTextoAtual() {
/* 127 */     return this.textoAtual;
/*     */   }
/*     */   
/*     */   public void setTextoAtual(String textoAtual) {
/* 131 */     this.textoAtual = textoAtual;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/camadas/CamadaCarta.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */