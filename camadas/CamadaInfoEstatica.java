/*     */ package ipqm.gsd.hidra.ihm.camadas;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaPolar;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.componentes.dominio.contexto.ContextoTatico;
/*     */ import ipqm.gsd.componentes.dominio.entidades.DadosAmbientais;
/*     */ import ipqm.gsd.componentes.dominio.entidades.IReferencial;
/*     */ import ipqm.gsd.componentes.dominio.entidades.Veiculo;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.JanelaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.JanelaGeografica;
/*     */ import ipqm.gsd.componentes.dominio.util.UTM;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.driver.Sensor;
/*     */ import ipqm.gsd.componentes.nucleo.logon.perfis.PerfilUsuario;
/*     */ import ipqm.gsd.hidra.ihm.camadas.filtros.FiltroInfoEstatica;
/*     */ import ipqm.gsd.hidra.ihm.util.InformacoesExibicaoUtil;
/*     */ import ipqm.gsd.hidra.ihm.util.desenho.UtilJava2D;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CamadaInfoEstatica
/*     */   extends CamadaBI<FiltroInfoEstatica>
/*     */   implements Serializable
/*     */ {
/*     */   private double escala;
/*     */   private CoordenadaCartesiana posicaoCursor;
/*     */   private boolean exibirEscala = true;
/*     */   private boolean exibirReferencial = true;
/*     */   public static CoordenadaPolar VENTO_REAL;
/*     */   public static boolean VENTO_AUTO = false;
/*  43 */   private String utm = null;
/*  44 */   private static Color COR_UTM = new Color(150, 50, 150, 150);
/*     */   
/*     */   public enum Orientacao
/*     */   {
/*  48 */     NORTE("Orientado no Norte"), PROA("Orientado na Proa");
/*     */     
/*     */     private final String nome;
/*     */     
/*     */     Orientacao(String nome) {
/*  53 */       this.nome = nome;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/*  58 */       return this.nome;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public CamadaInfoEstatica(FiltroInfoEstatica filtro, int largura, int altura) {
/*  64 */     super(filtro, largura, altura);
/*  65 */     UTM.preencherLista();
/*     */   }
/*     */   
/*     */   public void desenha() {
/*  69 */     tornaTransparente();
/*     */     
/*  71 */     Graphics2D g2d = getImagem().createGraphics();
/*  72 */     RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*  73 */     g2d.setRenderingHints(rh);
/*     */     
/*  75 */     if (this.posicaoCursor != null) {
/*  76 */       UtilJava2D.escreveLegendasEsquerda(null, 
/*  77 */           InformacoesExibicaoUtil.obterInformacaoPosicaoCursor(this.posicaoCursor, InformacoesExibicaoUtil.COORDENADA_GEOGRAFICA) + " | " + InformacoesExibicaoUtil.obterInformacaoPosicaoCursor(this.posicaoCursor, InformacoesExibicaoUtil.COORDENADA_GRADE), null, 
/*  78 */           getLargura(), 6, Color.white, Color.black, g2d);
/*     */     }
/*     */     
/*  81 */     if (((FiltroInfoEstatica)getFiltro()).isExibirUTM()) {
/*  82 */       desenharUTM(g2d);
/*     */     }
/*     */     
/*  85 */     if (this.exibirEscala) {
/*  86 */       UtilJava2D.escreveLegendasDireita(null, "Escala: " + String.format("%.2f", new Object[] { Double.valueOf(this.escala) }) + "MN", null, 0, 6, Color.white, Color.black, g2d);
/*     */     }
/*     */     
/*  89 */     if (this.exibirReferencial) {
/*  90 */       IReferencial ref = ((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getTeatroOperacao().getIReferencial();
/*  91 */       if (ref != null) {
/*  92 */         UtilJava2D.escreveLegendasEsquerda(null, "Referencial: " + ref.getNaReferencial(), null, getLargura(), 6, Color.white, Color.black, g2d);
/*     */       }
/*     */     } 
/*     */     
/*  96 */     if (((FiltroInfoEstatica)getFiltro()).isExibirAneis()) {
/*  97 */       desenhaAneis(g2d, ((FiltroInfoEstatica)getFiltro()).getQuantidadeAneis());
/*     */     }
/*     */     
/* 100 */     if (((FiltroInfoEstatica)getFiltro()).isExibirGradeado()) {
/* 101 */       int n = ((FiltroInfoEstatica)getFiltro()).getQuantidadeGradeados();
/* 102 */       desenhaGrade(g2d, n, n);
/*     */     } 
/*     */     
/* 105 */     if (((FiltroInfoEstatica)getFiltro()).isExibirRosaDeManobras()) {
/* 106 */       if (Veiculo.getVeiculoReferencial() == null) {
/* 107 */         desenhaRosaDeManobra(g2d, 0.0D);
/* 108 */         desenhaDirecaoVento(g2d);
/*     */       } else {
/* 110 */         double rumo; switch (((FiltroInfoEstatica)getFiltro()).getOrientacao()) {
/*     */           case NORTE:
/* 112 */             desenhaRosaDeManobra(g2d, 0.0D);
/* 113 */             desenhaDirecaoVento(g2d);
/*     */             break;
/*     */           case PROA:
/* 116 */             rumo = Veiculo.getVeiculoReferencial().getCondicaoAssociada().getCinematicaAutomatica().getRumoSuperficie();
/* 117 */             desenhaRosaDeManobra(g2d, rumo);
/* 118 */             desenhaDirecaoVento(g2d);
/*     */             break;
/*     */         } 
/*     */       
/*     */       } 
/*     */     }
/* 124 */     g2d.dispose();
/*     */   }
/*     */ 
/*     */   
/*     */   public void desenha(JanelaGeografica janela, double escala) {
/* 129 */     JanelaGeografica janelaGeo = janela;
/* 130 */     this.escala = escala;
/* 131 */     setJanelaCartesiana(JanelaCartesiana.converterJanelaGeografica(janelaGeo, janelaGeo.getCentro().getLatitude()));
/* 132 */     desenha();
/*     */   }
/*     */   
/*     */   public void setPosicaoCursor(CoordenadaCartesiana posicaoCursor) {
/* 136 */     this.posicaoCursor = posicaoCursor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void desenhaRosaDeManobra(Graphics2D g2d, double offset) {
/* 146 */     g2d.setColor(Color.white);
/* 147 */     g2d.setStroke(new BasicStroke(4.0F));
/*     */     
/* 149 */     int centroX = getLargura() / 2;
/* 150 */     int centroY = getAltura() / 2;
/* 151 */     int raio = Math.min(getLargura(), getAltura()) / 2;
/*     */     
/* 153 */     int raioCirculo = raio - 55;
/* 154 */     int raioTexto = raioCirculo + 30;
/* 155 */     int raioLinhaMenor = raioCirculo + 10;
/* 156 */     int raioLinhaMaior = raioCirculo + 15;
/*     */     
/* 158 */     g2d.drawOval(centroX - raioCirculo, centroY - raioCirculo, 2 * raioCirculo, 2 * raioCirculo);
/*     */     
/* 160 */     for (int i = 0; i < 360; i++) {
/*     */       
/* 162 */       double posFimX, posFimY, ang = i + offset;
/*     */       
/* 164 */       double anguloRad = Math.toRadians(ang - 90.0D);
/*     */       
/* 166 */       double posInicioX = centroX + raioCirculo * Math.cos(anguloRad);
/* 167 */       double posInicioY = centroY + raioCirculo * Math.sin(anguloRad);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 172 */       if (i % 10 == 0) {
/* 173 */         posFimX = centroX + raioLinhaMaior * Math.cos(anguloRad);
/* 174 */         posFimY = centroY + raioLinhaMaior * Math.sin(anguloRad);
/* 175 */         g2d.setStroke(new BasicStroke(2.5F));
/*     */         
/* 177 */         double posTextoX = centroX + raioTexto * Math.cos(anguloRad);
/* 178 */         double posTextoY = centroY + raioTexto * Math.sin(anguloRad);
/*     */         
/* 180 */         UtilJava2D.escreveTextoCentralizado(String.format("%03d", new Object[] { Integer.valueOf(i) }), (int)posTextoX, (int)posTextoY, Color.white, Color.black, g2d);
/*     */       } else {
/* 182 */         posFimX = centroX + raioLinhaMenor * Math.cos(anguloRad);
/* 183 */         posFimY = centroY + raioLinhaMenor * Math.sin(anguloRad);
/* 184 */         g2d.setStroke(new BasicStroke(2.0F));
/*     */       } 
/*     */       
/* 187 */       g2d.setColor(Color.WHITE);
/* 188 */       g2d.drawLine((int)posInicioX, (int)posInicioY, (int)posFimX, (int)posFimY);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void desenhaDirecaoVento(Graphics2D g2d) {
/* 198 */     DadosAmbientais dadosAmbientais = null;
/* 199 */     double direcaoVento = 0.0D;
/* 200 */     double velocidadeVento = 0.0D;
/* 201 */     if (PerfilUsuario.getPerfilUsuarioAtual().isUsuarioInstrutor()) {
/* 202 */       dadosAmbientais = ((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getTeatroOperacao().getAmbienteTeatroOperacao();
/* 203 */     } else if (VENTO_AUTO) {
/* 204 */       if (Veiculo.getVeiculoReferencial().getSensor(Sensor.Tipo.ANEMOMETRO) != null) {
/* 205 */         if (Veiculo.getVeiculoReferencial().getSensor(Sensor.Tipo.ANEMOMETRO).isAvariado() || 
/* 206 */           !Veiculo.getVeiculoReferencial().getSensor(Sensor.Tipo.ANEMOMETRO).isHabilitado()) {
/*     */           return;
/*     */         }
/*     */       } else {
/*     */         return;
/*     */       } 
/*     */       
/* 213 */       if (Veiculo.getVeiculoReferencial().getSensor(Sensor.Tipo.GPS) != null) {
/* 214 */         if (Veiculo.getVeiculoReferencial().getSensor(Sensor.Tipo.GPS).isAvariado() || 
/* 215 */           !Veiculo.getVeiculoReferencial().getSensor(Sensor.Tipo.GPS).isHabilitado()) {
/*     */           return;
/*     */         }
/*     */       } else {
/*     */         return;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 224 */       dadosAmbientais = ((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getTeatroOperacao().getAmbienteTeatroOperacao();
/*     */     } 
/*     */     
/* 227 */     if (dadosAmbientais != null) {
/* 228 */       direcaoVento = dadosAmbientais.getDirecaoVento();
/* 229 */       velocidadeVento = dadosAmbientais.getVelocidadeVento();
/*     */     } else {
/* 231 */       if (VENTO_REAL == null) {
/*     */         return;
/*     */       }
/* 234 */       velocidadeVento = VENTO_REAL.getDistancia();
/* 235 */       direcaoVento = VENTO_REAL.getMarcacao();
/*     */     } 
/*     */     
/* 238 */     g2d.setStroke(new BasicStroke(4.0F));
/* 239 */     g2d.setColor(Color.WHITE);
/*     */     
/* 241 */     int centroX = getLargura() / 2;
/* 242 */     int centroY = getAltura() / 2;
/* 243 */     int raio = Math.min(getLargura(), getAltura()) / 2;
/* 244 */     int raioCirculo = raio - 55;
/* 245 */     int raioLinhaMaior = raioCirculo - 20;
/*     */     
/* 247 */     double ang = direcaoVento;
/* 248 */     double anguloRad = Math.toRadians(ang - 90.0D);
/* 249 */     double posInicioX = centroX + raioCirculo * Math.cos(anguloRad);
/* 250 */     double posInicioY = centroY + raioCirculo * Math.sin(anguloRad);
/* 251 */     double posFimX = centroX + raioLinhaMaior * Math.cos(anguloRad);
/* 252 */     double posFimY = centroY + raioLinhaMaior * Math.sin(anguloRad);
/*     */     
/* 254 */     UtilJava2D.desenhaSeta(g2d, (int)posInicioX, (int)posInicioY, (int)posFimX, (int)posFimY);
/*     */ 
/*     */ 
/*     */     
/* 258 */     String veloc = String.valueOf((int)velocidadeVento);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 265 */     if (direcaoVento < 180.0D) {
/* 266 */       UtilJava2D.escreveLegendasEsquerda(" " + veloc, null, (int)posFimX, (int)posFimY, Color.white, Color.black, g2d);
/*     */     } else {
/* 268 */       UtilJava2D.escreveLegendasDireita(veloc + " ", null, (int)posFimX, (int)posFimY, Color.white, Color.black, g2d);
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
/*     */   private void desenhaGrade(Graphics2D g2d, int nX, int nY) {
/* 281 */     g2d.setColor(Color.darkGray);
/* 282 */     g2d.setStroke(new BasicStroke(1.0F));
/*     */     
/*     */     int i;
/* 285 */     for (i = 1; i <= nX; i++) {
/* 286 */       int p = (int)(getLargura() / (nX + 1.0D) * i);
/* 287 */       g2d.drawLine(p, 0, p, getAltura());
/*     */     } 
/*     */ 
/*     */     
/* 291 */     for (i = 1; i <= nY; i++) {
/* 292 */       int p = (int)(getAltura() / (nY + 1.0D) * i);
/* 293 */       g2d.drawLine(0, p, getLargura(), p);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void desenhaAneis(Graphics2D g2d, int n) {
/* 303 */     int raio = Math.min(getLargura(), getAltura()) / 2;
/* 304 */     int centroX = getLargura() / 2;
/* 305 */     int centroY = getAltura() / 2;
/*     */     
/* 307 */     g2d.setColor(Color.darkGray);
/* 308 */     g2d.setStroke(new BasicStroke(1.0F));
/*     */     
/* 310 */     for (int i = 1; i <= n; i++) {
/* 311 */       int r = raio / n * i;
/* 312 */       g2d.drawOval(centroX - r, centroY - r, 2 * r, 2 * r);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setUTM(String utm) {
/* 317 */     this.utm = utm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isExibirEscala() {
/* 324 */     return this.exibirEscala;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExibirEscala(boolean exibirEscala) {
/* 331 */     this.exibirEscala = exibirEscala;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isExibirReferencial() {
/* 338 */     return this.exibirReferencial;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExibirReferencial(boolean exibirReferencial) {
/* 345 */     this.exibirReferencial = exibirReferencial;
/*     */   }
/*     */   
/*     */   private void desenharUTM(Graphics2D g2d) {
/* 349 */     g2d.setColor(Color.darkGray);
/* 350 */     g2d.setStroke(new BasicStroke(1.0F));
/*     */     
/* 352 */     for (CoordenadaCartesiana cart : UTM.LISTA_CARTS) {
/* 353 */       CoordenadaRaster pRaster = converterCoordenadaCartesianaParaRaster(cart);
/*     */       
/* 355 */       g2d.drawLine(pRaster.getX(), 0, pRaster.getX(), getAltura() - 1);
/* 356 */       g2d.drawLine(0, pRaster.getY(), getLargura() - 1, pRaster.getY());
/*     */     } 
/*     */     
/* 359 */     g2d.setColor(COR_UTM);
/*     */     
/* 361 */     if (this.escala < 12.01D) {
/*     */       
/* 363 */       CoordenadaGeografica cGInicio = CoordenadaGeografica.converterCoordenadaCartesiana(this.janelaCart.getMin(), 0.0D);
/*     */       
/* 365 */       UTM.definirUTM(this.janelaCart.getMin());
/* 366 */       UTM.setEasting(((int)UTM.getEasting() / 1000 * 1000));
/* 367 */       UTM.setNorthing(((int)(UTM.getNorthing() / 1000.0D) * 1000));
/*     */       
/* 369 */       cGInicio = UTM.obterCoordenadaGeograficaPorUTM();
/* 370 */       CoordenadaGeografica cGFim = CoordenadaGeografica.converterCoordenadaCartesiana(this.janelaCart.getMax(), 0.0D);
/*     */       
/*     */       do {
/* 373 */         CoordenadaCartesiana cC = CoordenadaCartesiana.converterCoordenadaGeografica(cGInicio, 0.0D);
/* 374 */         CoordenadaRaster pRaster = converterCoordenadaCartesianaParaRaster(cC);
/*     */         
/* 376 */         if (pRaster.getX() > 0 || pRaster.getY() > 0) {
/* 377 */           g2d.drawLine(pRaster.getX(), 0, pRaster.getX(), getAltura() - 1);
/* 378 */           g2d.drawLine(0, pRaster.getY(), getLargura() - 1, pRaster.getY());
/*     */         } 
/*     */         
/* 381 */         UTM.definirUTM(cC);
/* 382 */         UTM.setEasting(UTM.getEasting() + 1000.0D);
/* 383 */         UTM.setNorthing(UTM.getNorthing() + 1000.0D);
/*     */         
/* 385 */         cGInicio = UTM.obterCoordenadaGeograficaPorUTM();
/*     */       }
/* 387 */       while (cGInicio.getLongitude() < cGFim.getLongitude());
/*     */     } 
/* 389 */     if (this.utm != null)
/* 390 */       UtilJava2D.escreveLegendasDireita(null, "UTM: " + this.utm, null, 0, getAltura() - 7, Color.white, Color.black, g2d); 
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/camadas/CamadaInfoEstatica.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */