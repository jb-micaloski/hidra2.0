/*     */ package ipqm.gsd.hidra.ihm.util.carta;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.navegacao.JanelaGeografica;
/*     */ import ipqm.gsd.componentes.nucleo.ThreadPool;
/*     */ import ipqm.gsd.componentes.nucleo.configuracao.Diretorios;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.notificador.Notificacao;
/*     */ import ipqm.gsd.componentes.nucleo.notificador.Notificador;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*     */ import ipqm.gsd.smaps.util.MapTiles;
/*     */ import ipqm.gsd.smaps.view.raster.OnlineTileMaps;
/*     */ import java.awt.Point;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.IOException;
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
/*     */ public class BuscadorTiles
/*     */ {
/*     */   private Notificacao notificacao;
/*     */   private int minTileX;
/*     */   private int maxTileX;
/*     */   private int minTileY;
/*     */   private int maxTileY;
/*     */   private long tempoTotal;
/*     */   private int numTile;
/*     */   private int totalTiles;
/*     */   private int totalTilesBuscados;
/*     */   private final String dirCache;
/*     */   private final CanvasCenarioTatico cenarioTatico;
/*     */   private boolean buscando;
/*     */   private boolean pausado;
/*     */   private boolean pararBusca;
/*     */   private final OnlineTileMaps servico;
/*     */   
/*     */   public BuscadorTiles(CanvasCenarioTatico cenarioTatico, OnlineTileMaps servico) {
/*  46 */     this.cenarioTatico = cenarioTatico;
/*  47 */     this.dirCache = Diretorios.getDiretorioPadrao(Diretorios.Diretorio.TILES) + servico.getDiretorio();
/*  48 */     this.servico = servico;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void iniciar(final JanelaGeografica janela, final double escala) {
/*  58 */     if (!this.buscando) {
/*  59 */       this.notificacao = new Notificacao(String.format("Buscando %s da escala  %.2f MN", new Object[] { this.servico
/*  60 */               .toString(), Double.valueOf(escala) }), Notificacao.Tipo.CARREGANDO);
/*  61 */       this.notificacao.setPermanente(true);
/*  62 */       this.notificacao.setExibirBotaoFechar(false);
/*  63 */       Notificador.getInstancia().addNotificacao(this.notificacao);
/*     */       
/*  65 */       ThreadPool.executar(new Runnable()
/*     */           {
/*     */             public void run() {
/*  68 */               BuscadorTiles.this.buscando = true;
/*  69 */               BuscadorTiles.this.pararBusca = false;
/*  70 */               BuscadorTiles.this.buscaTiles(janela, escala);
/*  71 */               BuscadorTiles.this.buscando = false;
/*  72 */               if (BuscadorTiles.this.cenarioTatico != null) {
/*  73 */                 String msg = BuscadorTiles.this.pararBusca ? "Busca interrompida" : "Busca finalizada";
/*  74 */                 msg = String.format("%s: %d tiles do %s da escala %.2f MN efetivamente buscados, %ds", new Object[] { msg, 
/*  75 */                       Integer.valueOf(BuscadorTiles.access$400(this.this$0)), BuscadorTiles.access$500(this.this$0).toString(), Double.valueOf(this.val$escala), Integer.valueOf((int)(BuscadorTiles.access$600(this.this$0) / 1000.0D)) });
/*     */ 
/*     */                 
/*  78 */                 BuscadorTiles.this.notificacao.setDescricao(msg);
/*  79 */                 BuscadorTiles.this.notificacao.setDuracaoNotificacao(5);
/*  80 */                 BuscadorTiles.this.notificacao.setPermanente(false);
/*     */               } 
/*     */             }
/*     */           }"Buscador de tiles");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parar() {
/*  91 */     if (isBuscando()) {
/*  92 */       this.pararBusca = true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pausar() {
/* 100 */     this.pausado = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void continuar() {
/* 107 */     this.pausado = false;
/* 108 */     notify();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isBuscando() {
/* 115 */     return this.buscando;
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
/*     */   private void buscaTiles(JanelaGeografica janela, double escala) {
/* 127 */     if (this.cenarioTatico == null) {
/*     */       return;
/*     */     }
/* 130 */     int levelOfDetail = this.cenarioTatico.getLevelOfDetail(escala);
/* 131 */     Point p1 = MapTiles.latLongToPixelXY(janela.getMax().getLatitude(), janela.getMin().getLongitude(), levelOfDetail);
/* 132 */     Point p2 = MapTiles.latLongToPixelXY(janela.getMin().getLatitude(), janela.getMax().getLongitude(), levelOfDetail);
/*     */ 
/*     */     
/* 135 */     Point t1 = MapTiles.pixelXYToTileXY(p1.x, p1.y);
/* 136 */     Point t2 = MapTiles.pixelXYToTileXY(p2.x, p2.y);
/* 137 */     this.minTileX = t1.x;
/* 138 */     this.maxTileX = t2.x;
/* 139 */     this.minTileY = t1.y;
/* 140 */     this.maxTileY = t2.y;
/* 141 */     this.totalTiles = (this.maxTileX - this.minTileX + 1) * (this.maxTileY - this.minTileY + 1);
/* 142 */     this.totalTilesBuscados = 0;
/* 143 */     this.tempoTotal = 0L;
/* 144 */     this.numTile = 0;
/*     */     
/* 146 */     String msg = String.format("Iniciando busca de %d tiles do %s da escala %.2f MN.", new Object[] { Integer.valueOf(this.totalTiles), this.servico.toString(), Double.valueOf(escala) });
/* 147 */     this.notificacao.setDescricao(msg);
/*     */ 
/*     */     
/* 150 */     int x1 = (this.minTileX + this.maxTileX) / 2;
/* 151 */     int y1 = (this.minTileY + this.maxTileY) / 2;
/* 152 */     int x2 = x1;
/* 153 */     int y2 = y1;
/* 154 */     int num = 0;
/*     */     
/* 156 */     while (x1 >= this.minTileX || y1 >= this.minTileY || x2 <= this.maxTileX || y2 <= this.maxTileY) {
/* 157 */       for (int k = 0; k <= x2 - x1; k++) {
/* 158 */         if (!verificaBusca(x1 + k, y1, levelOfDetail, escala)) {
/*     */           return;
/*     */         }
/* 161 */         if (x1 != x2) {
/*     */ 
/*     */           
/* 164 */           if (!verificaBusca(x1 + k, y2, levelOfDetail, escala)) {
/*     */             return;
/*     */           }
/* 167 */           if (k != 0 && k != x2 - x1) {
/* 168 */             if (!verificaBusca(x1, y1 + k, levelOfDetail, escala)) {
/*     */               return;
/*     */             }
/* 171 */             if (!verificaBusca(x2, y1 + k, levelOfDetail, escala))
/*     */               return; 
/*     */           } 
/*     */         } 
/*     */       } 
/* 176 */       x1--;
/* 177 */       y1--;
/* 178 */       x2++;
/* 179 */       y2++;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean verificaBusca(int tileX, int tileY, int levelOfDetail, double escala) {
/* 188 */     if (tileX >= this.minTileX && tileX <= this.maxTileX && tileY >= this.minTileY && tileY <= this.maxTileY) {
/* 189 */       this.numTile++;
/*     */       
/* 191 */       if (!MapTiles.isTile(this.dirCache, tileX, tileY, levelOfDetail)) {
/* 192 */         BufferedImage img; int num; long t = System.currentTimeMillis();
/*     */ 
/*     */         
/* 195 */         switch (this.servico) {
/*     */           case GOOGLE:
/* 197 */             img = this.cenarioTatico.getCamadaSatelite().requestTile(tileX, tileY, levelOfDetail);
/*     */             break;
/*     */           default:
/* 200 */             img = this.cenarioTatico.getCamadaMapaAlternativo().requestTile(tileX, tileY, levelOfDetail);
/*     */             break;
/*     */         } 
/* 203 */         if (img != null) {
/*     */           try {
/* 205 */             MapTiles.writeTile(this.dirCache, tileX, tileY, levelOfDetail, img);
/* 206 */           } catch (IOException ex) {
/* 207 */             Log.gravarLogExcecao("Erro ao escrever tile no disco!", this, ex);
/*     */           } 
/*     */         }
/* 210 */         long dt = System.currentTimeMillis() - t;
/* 211 */         this.tempoTotal += dt;
/* 212 */         this.totalTilesBuscados++;
/*     */         
/* 214 */         switch ((int)escala) {
/*     */           case 48:
/* 216 */             num = 3;
/*     */             break;
/*     */           case 24:
/* 219 */             num = 5;
/*     */             break;
/*     */           case 12:
/* 222 */             num = 7;
/*     */             break;
/*     */           case 6:
/* 225 */             num = 11;
/*     */             break;
/*     */           case 3:
/* 228 */             num = 13;
/*     */             break;
/*     */           default:
/* 231 */             num = 1;
/*     */             break;
/*     */         } 
/* 234 */         if (this.totalTilesBuscados % num == 0 && 
/* 235 */           this.cenarioTatico != null) {
/*     */           
/* 237 */           String msg = String.format("Buscados %d / %d tiles (%d efetivos), %ds...", new Object[] { Integer.valueOf(this.numTile), Integer.valueOf(this.totalTiles), Integer.valueOf(this.totalTilesBuscados), Integer.valueOf((int)(this.tempoTotal / 1000.0D)) });
/*     */           
/* 239 */           this.notificacao.setDescricao(msg);
/*     */         } 
/*     */         
/* 242 */         synchronized (this) {
/* 243 */           while (this.pausado) {
/*     */             try {
/* 245 */               wait();
/* 246 */             } catch (InterruptedException interruptedException) {}
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 252 */     return !this.pararBusca;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/util/carta/BuscadorTiles.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */