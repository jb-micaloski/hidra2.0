/*     */ package ipqm.gsd.hidra.ihm.util.desenho;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.ObjetoTatico;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.ObjetoCinematica;
/*     */ import ipqm.gsd.componentes.dominio.historico_navegacao.HistoricoNavegacao;
/*     */ import ipqm.gsd.componentes.nucleo.objeto_visual.Camada;
/*     */ import ipqm.gsd.hidra.ihm.camadas.CamadaBI;
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Polygon;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Rectangle2D;
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
/*     */ public class UtilJava2D
/*     */ {
/*     */   public static void escreveTexto(String texto, int x, int y, Color corTexto, Color corFundo, Graphics2D g2d) {
/*  44 */     if (texto == null) {
/*     */       return;
/*     */     }
/*  47 */     Rectangle2D rect = g2d.getFontMetrics().getStringBounds(texto, g2d);
/*  48 */     int larguraTexto = (int)rect.getWidth();
/*  49 */     int alturaTexto = (int)rect.getHeight();
/*  50 */     g2d.setColor(corFundo);
/*  51 */     g2d.setComposite(AlphaComposite.getInstance(3, 0.5F));
/*  52 */     g2d.fillRect(x, y - alturaTexto / 2, larguraTexto, alturaTexto);
/*  53 */     g2d.setColor(corTexto);
/*  54 */     g2d.setComposite(AlphaComposite.getInstance(3, 1.0F));
/*  55 */     g2d.drawString(texto, x, y + alturaTexto / 2);
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
/*     */   public static void escreveTextoCentralizado(String texto, int x, int y, Color corTexto, Color corFundo, Graphics2D g2d) {
/*  70 */     if (texto == null) {
/*     */       return;
/*     */     }
/*  73 */     Rectangle2D rect = g2d.getFontMetrics().getStringBounds(texto, g2d);
/*  74 */     int larguraTexto = (int)rect.getWidth();
/*  75 */     int alturaTexto = (int)rect.getHeight();
/*  76 */     g2d.setColor(corFundo);
/*  77 */     g2d.setComposite(AlphaComposite.getInstance(3, 0.5F));
/*  78 */     g2d.fillRect(x - larguraTexto / 2, y - alturaTexto / 2, larguraTexto, alturaTexto);
/*  79 */     g2d.setColor(corTexto);
/*  80 */     g2d.setComposite(AlphaComposite.getInstance(3, 1.0F));
/*  81 */     g2d.drawString(texto, x - larguraTexto / 2, y + alturaTexto / 2);
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
/*     */   public static void escreveLegendasEsquerda(String linha1, String linha2, String linha3, int x, int y, Color corTexto, Color corFundo, Graphics2D g2d) {
/*  98 */     if (linha1 != null) {
/*  99 */       Rectangle2D rect = g2d.getFontMetrics().getStringBounds(linha1, g2d);
/* 100 */       int larguraTexto = (int)rect.getWidth();
/* 101 */       int alturaTexto = (int)rect.getHeight();
/* 102 */       escreveTexto(linha1, x - larguraTexto, y - alturaTexto, corTexto, corFundo, g2d);
/*     */     } 
/*     */ 
/*     */     
/* 106 */     if (linha2 != null) {
/* 107 */       Rectangle2D rect = g2d.getFontMetrics().getStringBounds(linha2, g2d);
/* 108 */       int larguraTexto = (int)rect.getWidth();
/* 109 */       escreveTexto(linha2, x - larguraTexto, y, corTexto, corFundo, g2d);
/*     */     } 
/*     */ 
/*     */     
/* 113 */     if (linha3 != null) {
/* 114 */       Rectangle2D rect = g2d.getFontMetrics().getStringBounds(linha3, g2d);
/* 115 */       int larguraTexto = (int)rect.getWidth();
/* 116 */       int alturaTexto = (int)rect.getHeight();
/* 117 */       escreveTexto(linha3, x - larguraTexto, y + alturaTexto, corTexto, corFundo, g2d);
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
/*     */   public static void escreveLegendasEsquerda(String linha1, String linha2, int x, int y, Color corTexto, Color corFundo, Graphics2D g2d) {
/* 136 */     if (linha1 != null) {
/* 137 */       Rectangle2D rect = g2d.getFontMetrics().getStringBounds(linha1, g2d);
/* 138 */       int larguraTexto = (int)rect.getWidth();
/* 139 */       int alturaTexto = (int)rect.getHeight();
/* 140 */       escreveTexto(linha1, x - larguraTexto, y - alturaTexto / 2 - 1, corTexto, corFundo, g2d);
/*     */     } 
/*     */ 
/*     */     
/* 144 */     if (linha2 != null) {
/* 145 */       Rectangle2D rect = g2d.getFontMetrics().getStringBounds(linha2, g2d);
/* 146 */       int larguraTexto = (int)rect.getWidth();
/* 147 */       int alturaTexto = (int)rect.getHeight();
/* 148 */       escreveTexto(linha2, x - larguraTexto, y + alturaTexto / 2, corTexto, corFundo, g2d);
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
/*     */   public static void escreveLegendasDireita(String linha1, String linha2, String linha3, int x, int y, Color corTexto, Color corFundo, Graphics2D g2d) {
/* 169 */     if (linha1 != null) {
/* 170 */       Rectangle2D rect = g2d.getFontMetrics().getStringBounds(linha1, g2d);
/* 171 */       int alturaTexto = (int)rect.getHeight();
/* 172 */       escreveTexto(linha1, x, y - alturaTexto, corTexto, corFundo, g2d);
/*     */     } 
/* 174 */     if (linha2 != null) {
/* 175 */       escreveTexto(linha2, x, y, corTexto, corFundo, g2d);
/*     */     }
/* 177 */     if (linha3 != null) {
/* 178 */       Rectangle2D rect = g2d.getFontMetrics().getStringBounds(linha3, g2d);
/* 179 */       int alturaTexto = (int)rect.getHeight();
/* 180 */       escreveTexto(linha3, x, y + alturaTexto, corTexto, corFundo, g2d);
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
/*     */   public static void escreveLegendasDireita(String linha1, String linha2, int x, int y, Color corTexto, Color corFundo, Graphics2D g2d) {
/* 198 */     if (linha1 != null) {
/* 199 */       Rectangle2D rect = g2d.getFontMetrics().getStringBounds(linha1, g2d);
/* 200 */       int alturaTexto = (int)rect.getHeight();
/* 201 */       escreveTexto(linha1, x, y - alturaTexto / 2 - 1, corTexto, corFundo, g2d);
/*     */     } 
/* 203 */     if (linha2 != null) {
/* 204 */       Rectangle2D rect = g2d.getFontMetrics().getStringBounds(linha2, g2d);
/* 205 */       int alturaTexto = (int)rect.getHeight();
/* 206 */       escreveTexto(linha2, x, y + alturaTexto / 2, corTexto, corFundo, g2d);
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
/*     */   public static void desenhaTriangulo(Graphics2D g2d, int x0, int y0, int altura, int base, double angulo, String texto, Color corFundo, Color corContorno, boolean preencheTriangulo) {
/* 227 */     int x1 = x0 + (int)(altura / 1.0D * Math.cos(angulo));
/* 228 */     int y1 = y0 - (int)(altura / 1.0D * Math.sin(angulo));
/* 229 */     int x2 = x0 + (int)(base / 2.0D * Math.cos(angulo + 1.5707963267948966D));
/* 230 */     int y2 = y0 - (int)(base / 2.0D * Math.sin(angulo + 1.5707963267948966D));
/* 231 */     int x3 = x0 + (int)(base / 2.0D * Math.cos(angulo + 4.71238898038469D));
/* 232 */     int y3 = y0 - (int)(base / 2.0D * Math.sin(angulo + 4.71238898038469D));
/* 233 */     int dx = (int)(altura / 2.0D * Math.cos(angulo));
/* 234 */     int dy = (int)(altura / 2.0D * Math.sin(angulo));
/* 235 */     int[] xPoints = { x1 - dx, x2 - dx, x3 - dx };
/* 236 */     int[] yPoints = { y1 + dy, y2 + dy, y3 + dy };
/* 237 */     g2d.setColor(corFundo);
/* 238 */     if (preencheTriangulo) {
/* 239 */       g2d.fillPolygon(xPoints, yPoints, 3);
/*     */     }
/* 241 */     g2d.setColor(corContorno);
/*     */     
/* 243 */     g2d.drawPolygon(xPoints, yPoints, 3);
/*     */     
/* 245 */     if (!texto.isEmpty()) {
/* 246 */       Rectangle2D rect = g2d.getFontMetrics().getStringBounds(texto, g2d);
/* 247 */       int larguraTexto = (int)rect.getWidth();
/* 248 */       int alturaTexto = (int)rect.getHeight();
/* 249 */       g2d.drawString(texto, x0 - larguraTexto / 2 - dx / 3, y0 + alturaTexto / 2 + dy / 3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void desenhaLosango(Graphics2D g2d, int x, int y, int tamanho, Color corFundo, Color corContorno, boolean preencheLosangulo) {
/* 256 */     int d = tamanho / 2;
/* 257 */     int[] xPoints = { x - d, x, x + d, x };
/* 258 */     int[] yPoints = { y, y + d, y, y - d };
/*     */     
/* 260 */     g2d.setColor(corFundo);
/* 261 */     if (preencheLosangulo) {
/* 262 */       g2d.fillPolygon(xPoints, yPoints, 4);
/*     */     }
/* 264 */     g2d.setColor(corContorno);
/*     */     
/* 266 */     g2d.drawPolygon(xPoints, yPoints, 4);
/*     */   }
/*     */   
/*     */   public static void desenhaGravata(Graphics2D g2d, int x0, int y0, int largura, int altura, Color corFundo, Color corContorno) {
/* 270 */     int dx = largura / 2;
/* 271 */     int dy = altura / 2;
/* 272 */     int x1 = x0 - dx;
/* 273 */     int y1 = y0 - dy;
/* 274 */     int x2 = x1;
/* 275 */     int y2 = y0 + dy;
/* 276 */     int x3 = x0 + dx;
/* 277 */     int y3 = y1;
/* 278 */     int x4 = x3;
/* 279 */     int y4 = y2;
/*     */     
/* 281 */     int[] xPoints = { x1, x2, x3, x4 };
/* 282 */     int[] yPoints = { y1, y2, y3, y4 };
/* 283 */     g2d.setColor(corFundo);
/* 284 */     g2d.fillPolygon(xPoints, yPoints, 4);
/* 285 */     g2d.setColor(corContorno);
/* 286 */     g2d.drawPolygon(xPoints, yPoints, 4);
/*     */   }
/*     */   
/*     */   public static void desenhaSeta(Graphics2D g2d, int x1, int y1, int x2, int y2) {
/* 290 */     double d = Math.sqrt(((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)));
/* 291 */     if (d == 0.0D) {
/*     */       return;
/*     */     }
/* 294 */     double d1 = -(x2 - x1) / (y2 - y1);
/* 295 */     double d2 = 6.0D / Math.sqrt(1.0D + d1 * d1);
/* 296 */     double d3 = 6.0D / Math.sqrt(1.0D + 1.0D / d1 / d1);
/* 297 */     if (d1 < 0.0D) {
/* 298 */       d3 = -d3;
/*     */     }
/* 300 */     double d4 = x2 - (10 * (x2 - x1)) / d;
/* 301 */     double d5 = y2 - (10 * (y2 - y1)) / d;
/* 302 */     g2d.drawLine(x1, y1, (int)d4, (int)d5);
/* 303 */     int[] ai = { (int)(d4 - d2), x2, (int)(d4 + d2) };
/* 304 */     int[] ai1 = { (int)(d5 - d3), y2, (int)(d5 + d3) };
/* 305 */     g2d.fillPolygon(ai, ai1, 3);
/*     */   }
/*     */   
/*     */   public static void desenhaTracejado(Graphics2D g2d, int x1, int y1, int x2, int y2) {
/* 309 */     Graphics2D g3 = (Graphics2D)g2d.create();
/* 310 */     Graphics2D g4 = (Graphics2D)g2d.create();
/*     */     
/* 312 */     double d = Math.sqrt(((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)));
/* 313 */     if (d == 0.0D) {
/*     */       return;
/*     */     }
/* 316 */     double d1 = -(x2 - x1) / (y2 - y1);
/* 317 */     double d2 = 6.0D / Math.sqrt(1.0D + d1 * d1);
/* 318 */     double d3 = 6.0D / Math.sqrt(1.0D + 1.0D / d1 / d1);
/* 319 */     if (d1 < 0.0D) {
/* 320 */       d3 = -d3;
/*     */     }
/* 322 */     double d4 = x2 - (10 * (x2 - x1)) / d + 1.0D;
/* 323 */     double d5 = y2 - (10 * (y2 - y1)) / d + 1.0D;
/*     */     
/* 325 */     RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */ 
/*     */     
/* 328 */     g2d.setRenderingHints(rh);
/* 329 */     g3.setRenderingHints(rh);
/* 330 */     g4.setRenderingHints(rh);
/*     */     
/* 332 */     float[] dash1 = { 19.0F, 12.0F };
/* 333 */     BasicStroke stroke = new BasicStroke(3.0F, 0, 0, 10.0F, dash1, 0.0F);
/* 334 */     float[] dash2 = { 18.0F, 13.0F };
/* 335 */     BasicStroke bs = new BasicStroke(1.0F, 0, 0, 10.0F, dash2, 1.0F);
/*     */     
/* 337 */     g3.setColor(Color.black);
/* 338 */     g3.setStroke(stroke);
/*     */     
/* 340 */     Line2D linha = new Line2D.Double(x1, y1, (int)d4, (int)d5);
/*     */     
/* 342 */     g3.draw(linha);
/*     */     
/* 344 */     g2d.setStroke(bs);
/* 345 */     Line2D linha2 = new Line2D.Double(x1, y1, (int)d4, (int)d5);
/* 346 */     g2d.draw(linha2);
/*     */     
/* 348 */     Line2D linha3 = new Line2D.Double(x1, y1, (int)d4, (int)d5);
/* 349 */     float[] dash3 = { 1.0F, 30.0F };
/* 350 */     g4.setColor(Color.black);
/* 351 */     g4.setStroke(new BasicStroke(3.0F, 0, 0, 10.0F, dash3, 1.0F));
/* 352 */     g4.draw(linha3);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void desenhaIndicadorMarcacao(int ordemMarcacao, Graphics2D g2d, int x, int y, int largura, int altura, Color preenchimento, Color contorno) {
/* 357 */     if (ordemMarcacao > 0) {
/* 358 */       g2d.setComposite(AlphaComposite.getInstance(3, 0.5F));
/* 359 */       if (preenchimento != null) {
/* 360 */         g2d.setColor((ordemMarcacao == 1) ? preenchimento : preenchimento);
/* 361 */         g2d.setStroke(new BasicStroke(1.0F));
/* 362 */         g2d.fillRect(x - largura / 2 - 2, y - altura / 2 - 2, largura + 4, altura + 4);
/*     */       } 
/*     */       
/* 365 */       if (ordemMarcacao == 3) {
/* 366 */         g2d.setColor(contorno);
/* 367 */         g2d.setStroke(new BasicStroke(2.0F));
/* 368 */         g2d.drawRect(x - largura / 2 - 2, y - altura / 2 - 2, largura + 4, altura + 4);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void desenhaVetorVelocidadeTracejado(ObjetoCinematica obj, CoordenadaCartesiana posicao, CamadaBI camada, int minutosFrente, Graphics2D g2d, float espessura) {
/* 376 */     CoordenadaCartesiana posicaoFinal = CoordenadaCartesiana.converterCoordenadaCartesianaDeclinacaoMagnetica(obj.getPosicaoDadoTempo(minutosFrente), true);
/*     */     
/* 378 */     if (posicaoFinal != null) {
/* 379 */       CoordenadaRaster posicaoRaster = camada.converterCoordenadaCartesianaParaRaster(posicao);
/* 380 */       CoordenadaRaster posicaoFinalRaster = camada.converterCoordenadaCartesianaParaRaster(posicaoFinal);
/* 381 */       g2d.setStroke(new BasicStroke(espessura));
/* 382 */       desenhaTracejado(g2d, posicaoRaster.getX(), posicaoRaster.getY(), posicaoFinalRaster.getX(), posicaoFinalRaster.getY());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void desenhaVetorVelocidadeSeta(ObjetoCinematica obj, CoordenadaCartesiana posicao, CamadaBI camada, int minutosFrente, Graphics2D g2d, float espessura) {
/* 389 */     CoordenadaCartesiana posicaoFinal = CoordenadaCartesiana.converterCoordenadaCartesianaDeclinacaoMagnetica(obj.getPosicaoDadoTempo(minutosFrente), true);
/*     */     
/* 391 */     if (posicaoFinal != null) {
/* 392 */       CoordenadaRaster posicaoRaster = camada.converterCoordenadaCartesianaParaRaster(posicao);
/* 393 */       CoordenadaRaster posicaoFinalRaster = camada.converterCoordenadaCartesianaParaRaster(posicaoFinal);
/* 394 */       g2d.setStroke(new BasicStroke(espessura));
/* 395 */       desenhaSeta(g2d, posicaoRaster.getX(), posicaoRaster.getY(), posicaoFinalRaster.getX(), posicaoFinalRaster.getY());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void desenhaRastro(ObjetoTatico obj, Camada camada, Color corObjeto, boolean exibirPontosHistoricosEstendido) {
/* 400 */     CamadaBI camadaBI = (CamadaBI)camada;
/* 401 */     Graphics2D g2d = camadaBI.getImagem().createGraphics();
/* 402 */     g2d.setColor(corObjeto);
/* 403 */     g2d.setStroke(new BasicStroke(1.0F));
/* 404 */     int raioRaster = 3;
/* 405 */     GeneralPath caminho = new GeneralPath();
/* 406 */     CoordenadaCartesiana cc0 = CoordenadaCartesiana.converterCoordenadaCartesianaDeclinacaoMagnetica(obj.getCinematica().getPosicao().getCoordenadaCartesiana(), true);
/* 407 */     CoordenadaRaster cr0 = camadaBI.converterCoordenadaCartesianaParaRaster(cc0);
/* 408 */     caminho.moveTo(cr0.getX(), cr0.getY());
/* 409 */     List<HistoricoNavegacao> rastro = obj.getCinematica().getPontosHistoricos().getRastro();
/* 410 */     for (HistoricoNavegacao ponto : rastro) {
/* 411 */       CoordenadaGeografica cg = new CoordenadaGeografica(ponto.getLatitude().doubleValue(), ponto.getLongitude().doubleValue());
/* 412 */       CoordenadaCartesiana cc = CoordenadaCartesiana.converterCoordenadaCartesianaDeclinacaoMagnetica(CoordenadaCartesiana.converterCoordenadaGeografica(cg, 0.0D), true);
/* 413 */       CoordenadaRaster posicaoRaster = camadaBI.converterCoordenadaCartesianaParaRaster(cc);
/* 414 */       g2d.fillOval(posicaoRaster.getX() - raioRaster, posicaoRaster.getY() - raioRaster, raioRaster * 2, raioRaster * 2);
/* 415 */       caminho.lineTo(posicaoRaster.getX(), posicaoRaster.getY());
/*     */     } 
/*     */     
/* 418 */     g2d.setStroke(new BasicStroke(1.0F, 0, 2, 0.0F, new float[] { 2.0F, 4.0F }, 0.0F));
/* 419 */     g2d.draw(caminho);
/*     */   }
/*     */   
/*     */   public static void desenhaRastroHistorico(ObjetoTatico obj, Camada camada, Color corObjeto, boolean exibirPontosHistoricosEstendido) {
/* 423 */     CamadaBI camadaBI = (CamadaBI)camada;
/* 424 */     Graphics2D g2d = camadaBI.getImagem().createGraphics();
/* 425 */     g2d.setColor(corObjeto);
/* 426 */     g2d.setStroke(new BasicStroke(1.0F));
/* 427 */     int largura = 10;
/* 428 */     GeneralPath caminho = new GeneralPath();
/* 429 */     CoordenadaCartesiana cc0 = CoordenadaCartesiana.converterCoordenadaCartesianaDeclinacaoMagnetica(obj.getCinematica().getPosicao().getCoordenadaCartesiana(), true);
/* 430 */     CoordenadaRaster cr0 = camadaBI.converterCoordenadaCartesianaParaRaster(cc0);
/* 431 */     caminho.moveTo(cr0.getX(), cr0.getY());
/* 432 */     List<HistoricoNavegacao> rastro = obj.getCinematica().getPontosHistoricos().getRastro();
/*     */     
/* 434 */     for (HistoricoNavegacao ponto : rastro) {
/* 435 */       CoordenadaGeografica cg = new CoordenadaGeografica(ponto.getLatitude().doubleValue(), ponto.getLongitude().doubleValue());
/* 436 */       CoordenadaCartesiana cc = CoordenadaCartesiana.converterCoordenadaCartesianaDeclinacaoMagnetica(CoordenadaCartesiana.converterCoordenadaGeografica(cg, 0.0D), true);
/* 437 */       CoordenadaRaster posicaoRaster = camadaBI.converterCoordenadaCartesianaParaRaster(cc);
/* 438 */       g2d.setStroke(new BasicStroke(1.0F));
/* 439 */       if (ponto.getRumoFundo() != null && ponto.getVelocidade() != null && ponto.getVelocidade().doubleValue() > 0.0D) {
/* 440 */         double angulo = 1.5707963267948966D - Math.toRadians(ponto.getRumoFundo().doubleValue());
/* 441 */         desenhaTriangulo(g2d, posicaoRaster.getX(), posicaoRaster.getY(), largura, (int)(2.0D * largura / 3.0D), angulo, "", corObjeto, corObjeto, true);
/*     */       } else {
/* 443 */         int raioRaster = 3;
/* 444 */         g2d.fillOval(posicaoRaster.getX() - raioRaster, posicaoRaster.getY() - raioRaster, raioRaster * 2, raioRaster * 2);
/*     */       } 
/* 446 */       caminho.lineTo(posicaoRaster.getX(), posicaoRaster.getY());
/*     */     } 
/*     */     
/* 449 */     g2d.setStroke(new BasicStroke(1.0F, 0, 2, 0.0F, new float[] { 2.0F, 4.0F }, 0.0F));
/* 450 */     g2d.draw(caminho);
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
/*     */   public static void desenhaEctagonoReal(Graphics2D g2, int x, int y, double ang, double comprimento, double largura, double altMN, Color corFundo, Color corContorno, boolean preenche) {
/* 469 */     int x0 = x;
/* 470 */     int y0 = y;
/*     */     
/* 472 */     int larguraX = (int)largura / 2;
/* 473 */     int comprimentoY = (int)comprimento;
/*     */     
/* 475 */     int inicioX = x0;
/* 476 */     int inicioY = y0 + comprimentoY / 2;
/*     */     
/* 478 */     int x1 = inicioX - larguraX;
/* 479 */     int y1 = inicioY;
/*     */     
/* 481 */     int x2 = x1;
/* 482 */     int y2 = y1 - comprimentoY + comprimentoY / 4;
/*     */     
/* 484 */     int x3 = x0 - larguraX / 3;
/* 485 */     int y3 = y1 - comprimentoY;
/*     */     
/* 487 */     int x4 = x0 + larguraX / 3;
/* 488 */     int y4 = y1 - comprimentoY;
/*     */     
/* 490 */     int x5 = x0 + larguraX;
/* 491 */     int y5 = y2;
/*     */     
/* 493 */     int x6 = x5;
/* 494 */     int y6 = inicioY;
/*     */     
/* 496 */     int x1Final = (int)(x0 + (x1 - x0) * Math.cos(ang) - (y1 - y0) * Math.sin(ang));
/* 497 */     int y1Final = (int)(y0 + (x1 - x0) * Math.sin(ang) + (y1 - y0) * Math.cos(ang));
/*     */     
/* 499 */     int x2Final = (int)(x0 + (x2 - x0) * Math.cos(ang) - (y2 - y0) * Math.sin(ang));
/* 500 */     int y2Final = (int)(y0 + (x2 - x0) * Math.sin(ang) + (y2 - y0) * Math.cos(ang));
/*     */     
/* 502 */     int x3Final = (int)(x0 + (x3 - x0) * Math.cos(ang) - (y3 - y0) * Math.sin(ang));
/* 503 */     int y3Final = (int)(y0 + (x3 - x0) * Math.sin(ang) + (y3 - y0) * Math.cos(ang));
/*     */     
/* 505 */     int x4Final = (int)(x0 + (x4 - x0) * Math.cos(ang) - (y4 - y0) * Math.sin(ang));
/* 506 */     int y4Final = (int)(y0 + (x4 - x0) * Math.sin(ang) + (y4 - y0) * Math.cos(ang));
/*     */     
/* 508 */     int x5Final = (int)(x0 + (x5 - x0) * Math.cos(ang) - (y5 - y0) * Math.sin(ang));
/* 509 */     int y5Final = (int)(y0 + (x5 - x0) * Math.sin(ang) + (y5 - y0) * Math.cos(ang));
/*     */     
/* 511 */     int x6Final = (int)(x0 + (x6 - x0) * Math.cos(ang) - (y6 - y0) * Math.sin(ang));
/* 512 */     int y6Final = (int)(y0 + (x6 - x0) * Math.sin(ang) + (y6 - y0) * Math.cos(ang));
/*     */     
/* 514 */     int[] pox = { x1Final, x2Final, x3Final, x4Final, x5Final, x6Final };
/* 515 */     int[] poy = { y1Final, y2Final, y3Final, y4Final, y5Final, y6Final };
/*     */     
/* 517 */     Polygon p = new Polygon(pox, poy, 6);
/*     */     
/* 519 */     g2.setColor(corFundo);
/* 520 */     if (preenche) {
/* 521 */       g2.fillPolygon(p);
/*     */     }
/* 523 */     g2.setColor(corContorno);
/*     */     
/* 525 */     g2.draw(p);
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/util/desenho/UtilJava2D.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */