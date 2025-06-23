/*     */ package ipqm.gsd.hidra.ihm.camadas;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.navegacao.JanelaGeografica;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.configuracao.Diretorios;
/*     */ import ipqm.gsd.componentes.nucleo.configuracao.Proxy;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.hidra.ihm.camadas.filtros.FiltroMapaOnline;
/*     */ import ipqm.gsd.smaps.view.MapsParameters;
/*     */ import ipqm.gsd.smaps.view.raster.ExceptionHandler;
/*     */ import ipqm.gsd.smaps.view.raster.GoogleMapsViewer;
/*     */ import ipqm.gsd.smaps.view.raster.MapQuestViewer;
/*     */ import ipqm.gsd.smaps.view.raster.OnlineTileMaps;
/*     */ import ipqm.gsd.smaps.view.raster.OpenSeaMapsViewer;
/*     */ import ipqm.gsd.smaps.view.raster.OpenStreetMapsViewer;
/*     */ import ipqm.gsd.smaps.view.raster.TileMapsViewer;
/*     */ import java.awt.image.BufferedImage;
/*     */ import javafx.embed.swing.SwingFXUtils;
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
/*     */ public class CamadaMapaOnline
/*     */   extends CamadaBI<FiltroMapaOnline>
/*     */ {
/*     */   private TileMapsViewer servidorMapas;
/*     */   private final MapsParameters mapsParameters;
/*     */   private OnlineTileMaps servico;
/*     */   private final ExceptionHandler handler;
/*     */   
/*     */   public CamadaMapaOnline(FiltroMapaOnline filtro, int largura, int altura, OnlineTileMaps servico) {
/*  39 */     super(filtro, largura, altura);
/*  40 */     this.handler = ((mensagem, obj, ex) -> Log.gravarLogExcecao(mensagem, obj, ex));
/*     */ 
/*     */     
/*  43 */     setServico(servico);
/*  44 */     this.mapsParameters = null;
/*     */   }
/*     */ 
/*     */   
/*     */   private TileMapsViewer obtemServidorCartas(OnlineTileMaps servico) {
/*  49 */     switch (servico) {
/*     */       case GOOGLE:
/*  51 */         return (TileMapsViewer)new GoogleMapsViewer(this.handler);
/*     */       case MAP_QUEST:
/*  53 */         return (TileMapsViewer)new MapQuestViewer(this.handler);
/*     */       case OPEN_SEA_MAPS:
/*  55 */         return (TileMapsViewer)new OpenSeaMapsViewer(this.handler);
/*     */       case OPEN_STREET_MAPS:
/*  57 */         return (TileMapsViewer)new OpenStreetMapsViewer(this.handler);
/*     */     } 
/*  59 */     return null;
/*     */   }
/*     */   
/*     */   public OnlineTileMaps getServico() {
/*  63 */     return this.servico;
/*     */   }
/*     */   
/*     */   public final void setServico(OnlineTileMaps servico) {
/*  67 */     if (servico != null) {
/*  68 */       if (this.servidorMapas != null) {
/*  69 */         this.servidorMapas.done();
/*     */       }
/*  71 */       this.servico = servico;
/*  72 */       this.servidorMapas = obtemServidorCartas(servico);
/*     */       
/*  74 */       boolean modoOnlineHabilitado = Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoIHM().isMapasOnlineHabilitado();
/*  75 */       this.servidorMapas.setOnlineMode(modoOnlineHabilitado);
/*  76 */       this.servidorMapas.setCacheFolder(Diretorios.getDiretorioPadrao(Diretorios.Diretorio.TILES));
/*  77 */       Proxy proxy = Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoRede().getProxy();
/*  78 */       if (proxy != null) {
/*  79 */         setProxy(proxy);
/*     */       }
/*  81 */       this.servidorMapas.init();
/*     */     } 
/*     */   }
/*     */   
/*     */   public final void setProxy(Proxy proxy) {
/*  86 */     this.servidorMapas.setProxy(proxy.getHost(), 
/*  87 */         String.valueOf(proxy.getPorta()), proxy
/*  88 */         .getExcecoes(), proxy
/*  89 */         .getUsuario(), proxy
/*  90 */         .getSenha(true));
/*  91 */     this.servidorMapas.setProxyEnabled(proxy.isHabilitado());
/*     */   }
/*     */   
/*     */   public BufferedImage requestTile(int tileX, int tileY, int levelOfDetail) {
/*  95 */     return this.servidorMapas.requestTile(tileX, tileY, levelOfDetail);
/*     */   }
/*     */   
/*     */   public void desenha(JanelaGeografica janela) {
/*  99 */     if (this.servidorMapas != null) {
/*     */       
/* 101 */       double latMin = janela.getMin().getLatitude();
/* 102 */       double latMax = janela.getMax().getLatitude();
/* 103 */       double longMin = janela.getMin().getLongitude();
/* 104 */       double longMax = janela.getMax().getLongitude();
/*     */       
/* 106 */       this.imagem.flush();
/* 107 */       this.imagem = this.servidorMapas.draw(longMin, latMin, longMax, latMax, getLargura(), getAltura(), this.mapsParameters);
/* 108 */       this.imagemFX = SwingFXUtils.toFXImage(this.imagem, this.imagemFX);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void geraImagemFX() {}
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/camadas/CamadaMapaOnline.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */