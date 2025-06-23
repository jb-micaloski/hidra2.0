/*     */ package ipqm.gsd.hidra.ihm.interacao;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.JanelaGeografica;
/*     */ import ipqm.gsd.componentes.nucleo.gestao.GestorCartasGenerico;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.smaps.model.coord.BBox;
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
/*     */ public class SelecaoObjetoCartaNautica
/*     */ {
/*     */   private static final byte RAIO = 8;
/*     */   
/*     */   private BBox obterCoordenadasRegiaoSelecionada(CoordenadaCartesiana pontoClicado, CanvasCenarioTatico canvas) {
/*  67 */     CoordenadaRaster pontoClicadoRaster = canvas.projeta(pontoClicado);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  73 */     byte raio = 8;
/*     */ 
/*     */     
/*  76 */     CoordenadaRaster pontoBoundingBoxMinRaster = new CoordenadaRaster(pontoClicadoRaster.getX() - raio, pontoClicadoRaster.getY() + raio);
/*  77 */     CoordenadaRaster pontoBoundingboxMaxRaster = new CoordenadaRaster(pontoClicadoRaster.getX() + raio, pontoClicadoRaster.getY() - raio);
/*     */ 
/*     */     
/*  80 */     CoordenadaCartesiana pontoBoundignBoxMinCart = canvas.projetaInv(pontoBoundingBoxMinRaster);
/*  81 */     CoordenadaCartesiana pontoBoundingBoxMaxCart = canvas.projetaInv(pontoBoundingboxMaxRaster);
/*     */ 
/*     */     
/*  84 */     CoordenadaGeografica pontoBoundingBoxMinGeo = CoordenadaGeografica.converterCoordenadaCartesiana(pontoBoundignBoxMinCart, 0.0D);
/*  85 */     CoordenadaGeografica pontoBoundingBoxMaxGeo = CoordenadaGeografica.converterCoordenadaCartesiana(pontoBoundingBoxMaxCart, 0.0D);
/*     */     
/*  87 */     double latMin = pontoBoundingBoxMinGeo.getLatitude();
/*  88 */     double latMax = pontoBoundingBoxMaxGeo.getLatitude();
/*  89 */     double longMin = pontoBoundingBoxMinGeo.getLongitude();
/*  90 */     double longMax = pontoBoundingBoxMaxGeo.getLongitude();
/*     */     
/*  92 */     BBox bbox = new BBox(longMin, latMin, longMax, latMax);
/*     */     
/*  94 */     return bbox;
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
/*     */   private String fazerRequisicaoServidorMapas(GestorCartasGenerico servidorMapas, BBox pickBox, BBox janelaVisualizacao) throws Exception {
/* 122 */     String dadosCamadas = servidorMapas.getPickInfo(pickBox, janelaVisualizacao);
/* 123 */     return dadosCamadas;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String tratarSelecaoObjetoCartaNautica(CoordenadaCartesiana pontoClicado, CanvasCenarioTatico canvas, GestorCartasGenerico servidorMapas) {
/* 165 */     String dadosCamadas = "";
/*     */     
/*     */     try {
/* 168 */       BBox pickBox = obterCoordenadasRegiaoSelecionada(pontoClicado, canvas);
/* 169 */       JanelaGeografica janelaGeo = canvas.getJanelaGeografica();
/* 170 */       BBox janelaVisualizacao = new BBox(janelaGeo.getMin().getLongitude(), janelaGeo.getMin().getLatitude(), janelaGeo.getMax().getLongitude(), janelaGeo.getMax().getLatitude());
/* 171 */       if (pickBox != null) {
/* 172 */         dadosCamadas = fazerRequisicaoServidorMapas(servidorMapas, pickBox, janelaVisualizacao);
/*     */       }
/*     */     }
/* 175 */     catch (Exception ex) {
/* 176 */       Log.gravarLogExcecao("Erro. Não foi possível fazer requisição ao servidor de mapas S52. ", this, ex);
/* 177 */       dadosCamadas = null;
/*     */     } 
/*     */     
/* 180 */     return dadosCamadas;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/SelecaoObjetoCartaNautica.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */