/*     */ package ipqm.gsd.hidra.ihm.util.desenho;
/*     */ 
/*     */ import com.sun.javafx.tk.FontMetrics;
/*     */ import com.sun.javafx.tk.Toolkit;
/*     */ import ipqm.gsd.componentes.dominio.ObjetoTatico;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.ObjetoCinematica;
/*     */ import ipqm.gsd.componentes.dominio.historico_navegacao.HistoricoNavegacao;
/*     */ import ipqm.gsd.componentes.nucleo.util.ConversorUnidades;
/*     */ import ipqm.gsd.hidra.ihm.camadas.CamadaBI;
/*     */ import java.util.List;
/*     */ import javafx.geometry.Point3D;
/*     */ import javafx.scene.Group;
/*     */ import javafx.scene.canvas.GraphicsContext;
/*     */ import javafx.scene.paint.Color;
/*     */ import javafx.scene.paint.Paint;
/*     */ import javafx.scene.shape.Cylinder;
/*     */ import javafx.scene.shape.StrokeLineCap;
/*     */ import javafx.scene.shape.StrokeLineJoin;
/*     */ import javafx.scene.text.FontSmoothingType;
/*     */ import javafx.scene.transform.Rotate;
/*     */ import javafx.scene.transform.Transform;
/*     */ import javafx.scene.transform.Translate;
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
/*     */ public class UtilJavaFX
/*     */ {
/*     */   public static void desenhaTriangulo(GraphicsContext g2d, int x0, int y0, int altura, int base, double angulo, String texto, Color corFundo, Color corContorno, boolean preencheTriangulo) {
/*  39 */     int x1 = x0 + (int)(altura / 1.0D * Math.cos(angulo));
/*  40 */     int y1 = y0 - (int)(altura / 1.0D * Math.sin(angulo));
/*  41 */     int x2 = x0 + (int)(base / 2.0D * Math.cos(angulo + 1.5707963267948966D));
/*  42 */     int y2 = y0 - (int)(base / 2.0D * Math.sin(angulo + 1.5707963267948966D));
/*  43 */     int x3 = x0 + (int)(base / 2.0D * Math.cos(angulo + 4.71238898038469D));
/*  44 */     int y3 = y0 - (int)(base / 2.0D * Math.sin(angulo + 4.71238898038469D));
/*  45 */     int dx = (int)(altura / 2.0D * Math.cos(angulo));
/*  46 */     int dy = (int)(altura / 2.0D * Math.sin(angulo));
/*  47 */     double[] xPoints = { (x1 - dx), (x2 - dx), (x3 - dx) };
/*  48 */     double[] yPoints = { (y1 + dy), (y2 + dy), (y3 + dy) };
/*  49 */     g2d.setFill((Paint)corFundo);
/*  50 */     if (preencheTriangulo) {
/*  51 */       g2d.fillPolygon(xPoints, yPoints, 3);
/*     */     }
/*  53 */     g2d.setStroke((Paint)corContorno);
/*     */     
/*  55 */     g2d.strokePolygon(xPoints, yPoints, 3);
/*     */     
/*  57 */     if (!texto.isEmpty()) {
/*  58 */       FontMetrics fm = Toolkit.getToolkit().getFontLoader().getFontMetrics(g2d.getFont());
/*  59 */       float larguraTexto = fm.computeStringWidth(texto);
/*  60 */       float alturaTexto = fm.getAscent();
/*  61 */       g2d.strokeText(texto, (x0 - larguraTexto / 2.0F - (dx / 3)), (y0 + alturaTexto / 2.0F + (dy / 3)));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void desenhaIndicadorMarcacao(int ordemMarcacao, GraphicsContext g2d, int x, int y, int largura, int altura, Color preenchimento, Color contorno) {
/*  67 */     if (ordemMarcacao > 0) {
/*  68 */       g2d.save();
/*  69 */       g2d.setGlobalAlpha(0.5D);
/*  70 */       if (preenchimento != null) {
/*  71 */         g2d.setFill((ordemMarcacao == 1) ? (Paint)preenchimento : (Paint)preenchimento);
/*  72 */         g2d.setLineWidth(1.0D);
/*  73 */         g2d.fillRect((x - largura / 2 - 2), (y - altura / 2 - 2), (largura + 4), (altura + 4));
/*     */       } 
/*     */       
/*  76 */       if (ordemMarcacao == 3) {
/*  77 */         g2d.setStroke((Paint)contorno);
/*  78 */         g2d.setLineWidth(2.0D);
/*  79 */         g2d.strokeRect((x - largura / 2 - 2), (y - altura / 2 - 2), (largura + 4), (altura + 4));
/*     */       } 
/*  81 */       g2d.restore();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void desenhaVetorVelocidade(ObjetoCinematica obj, CoordenadaCartesiana posicao, CamadaBI camada, int minutosFrente, GraphicsContext g2d, float espessura) {
/*  88 */     CoordenadaCartesiana posicaoFinal = obj.getPosicaoDadoTempo(minutosFrente);
/*     */     
/*  90 */     if (posicaoFinal != null) {
/*  91 */       CoordenadaRaster posicaoRaster = camada.converterCoordenadaCartesianaParaRaster(posicao);
/*  92 */       CoordenadaRaster posicaoFinalRaster = camada.converterCoordenadaCartesianaParaRaster(posicaoFinal);
/*  93 */       g2d.setLineWidth(espessura);
/*  94 */       desenhaSeta(g2d, posicaoRaster.getX(), posicaoRaster.getY(), posicaoFinalRaster.getX(), posicaoFinalRaster.getY());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void desenhaSeta(GraphicsContext g2d, int x1, int y1, int x2, int y2) {
/*  99 */     double d = Math.sqrt(((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)));
/* 100 */     if (d == 0.0D) {
/*     */       return;
/*     */     }
/* 103 */     double d1 = -(x2 - x1) / (y2 - y1);
/* 104 */     double d2 = 6.0D / Math.sqrt(1.0D + d1 * d1);
/* 105 */     double d3 = 6.0D / Math.sqrt(1.0D + 1.0D / d1 / d1);
/* 106 */     if (d1 < 0.0D) {
/* 107 */       d3 = -d3;
/*     */     }
/* 109 */     double d4 = x2 - (10 * (x2 - x1)) / d;
/* 110 */     double d5 = y2 - (10 * (y2 - y1)) / d;
/* 111 */     g2d.strokeLine(x1, y1, d4, d5);
/* 112 */     double[] ai = { d4 - d2, x2, d4 + d2 };
/* 113 */     double[] ai1 = { d5 - d3, y2, d5 + d3 };
/* 114 */     g2d.fillPolygon(ai, ai1, 3);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void escreveLegendasDireita(String linha1, String linha2, String linha3, int x, int y, Color corTexto, Color corFundo, GraphicsContext g2d) {
/* 119 */     if (linha1 != null) {
/* 120 */       FontMetrics fm = Toolkit.getToolkit().getFontLoader().getFontMetrics(g2d.getFont());
/* 121 */       float alturaTexto = fm.getAscent();
/* 122 */       escreveTexto(linha1, x, y - alturaTexto, corTexto, corFundo, g2d);
/*     */     } 
/* 124 */     if (linha2 != null) {
/* 125 */       escreveTexto(linha2, x, y, corTexto, corFundo, g2d);
/*     */     }
/* 127 */     if (linha3 != null) {
/* 128 */       FontMetrics fm = Toolkit.getToolkit().getFontLoader().getFontMetrics(g2d.getFont());
/* 129 */       float alturaTexto = fm.getAscent();
/* 130 */       escreveTexto(linha3, x, y + alturaTexto, corTexto, corFundo, g2d);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void escreveLegendasEsquerda(String linha1, String linha2, String linha3, int x, int y, Color corTexto, Color corFundo, GraphicsContext g2d) {
/* 136 */     if (linha1 != null) {
/* 137 */       FontMetrics fm = Toolkit.getToolkit().getFontLoader().getFontMetrics(g2d.getFont());
/* 138 */       float larguraTexto = fm.computeStringWidth(linha1);
/* 139 */       float alturaTexto = fm.getAscent();
/* 140 */       escreveTexto(linha1, x - larguraTexto, y - alturaTexto, corTexto, corFundo, g2d);
/*     */     } 
/* 142 */     if (linha2 != null) {
/* 143 */       FontMetrics fm = Toolkit.getToolkit().getFontLoader().getFontMetrics(g2d.getFont());
/* 144 */       float larguraTexto = fm.computeStringWidth(linha2);
/* 145 */       escreveTexto(linha2, x - larguraTexto, y, corTexto, corFundo, g2d);
/*     */     } 
/* 147 */     if (linha3 != null) {
/* 148 */       FontMetrics fm = Toolkit.getToolkit().getFontLoader().getFontMetrics(g2d.getFont());
/* 149 */       float larguraTexto = fm.computeStringWidth(linha3);
/* 150 */       float alturaTexto = fm.getAscent();
/* 151 */       escreveTexto(linha3, x - larguraTexto, y + alturaTexto, corTexto, corFundo, g2d);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void escreveTexto(String texto, float x, float y, Color corTexto, Color corFundo, GraphicsContext g2d) {
/* 157 */     if (texto == null) {
/*     */       return;
/*     */     }
/* 160 */     FontMetrics fm = Toolkit.getToolkit().getFontLoader().getFontMetrics(g2d.getFont());
/* 161 */     float larguraTexto = fm.computeStringWidth(texto);
/* 162 */     float alturaTexto = fm.getAscent();
/* 163 */     g2d.setFill((Paint)corFundo);
/* 164 */     g2d.setGlobalAlpha(0.5D);
/* 165 */     g2d.fillRect(x, (y - alturaTexto / 2.0F), larguraTexto, alturaTexto);
/* 166 */     g2d.setFill((Paint)corTexto);
/* 167 */     g2d.setGlobalAlpha(1.0D);
/* 168 */     g2d.setFontSmoothingType(FontSmoothingType.GRAY);
/* 169 */     g2d.fillText(texto, x, (y + alturaTexto / 2.0F));
/*     */   }
/*     */   
/*     */   public static Group desenhaRastro(ObjetoTatico obj, CamadaBI camadaBI, GraphicsContext g2d, Color corObjeto, boolean exibirPontosHistoricosEstendido) {
/* 173 */     int raioRaster = 3;
/* 174 */     CoordenadaCartesiana cc0 = obj.getCinematica().getPosicao().getCoordenadaCartesiana();
/* 175 */     CoordenadaRaster cr0 = camadaBI.converterCoordenadaCartesianaParaRaster(cc0);
/* 176 */     g2d.save();
/* 177 */     g2d.beginPath();
/* 178 */     g2d.moveTo(cr0.getX(), cr0.getY());
/* 179 */     g2d.setFill((Paint)corObjeto);
/* 180 */     g2d.setStroke((Paint)corObjeto);
/* 181 */     g2d.setLineWidth(1.0D);
/* 182 */     g2d.setLineCap(StrokeLineCap.BUTT);
/* 183 */     g2d.setLineJoin(StrokeLineJoin.BEVEL);
/* 184 */     g2d.setMiterLimit(0.0D);
/* 185 */     g2d.setLineDashes(new double[] { 2.0D, 4.0D });
/* 186 */     g2d.setLineDashOffset(0.0D);
/* 187 */     List<HistoricoNavegacao> rastro = obj.getCinematica().getPontosHistoricos().getRastro();
/* 188 */     Group grupo = new Group();
/* 189 */     for (HistoricoNavegacao ponto : rastro) {
/* 190 */       CoordenadaGeografica cg = new CoordenadaGeografica(ponto.getLatitude().doubleValue(), ponto.getLongitude().doubleValue());
/* 191 */       CoordenadaCartesiana cc = CoordenadaCartesiana.converterCoordenadaGeografica(cg, 0.0D);
/* 192 */       CoordenadaRaster posicaoRaster = camadaBI.converterCoordenadaCartesianaParaRaster(cc);
/* 193 */       int altitude = camadaBI.converterDistanciaCartesianaParaRaster(ConversorUnidades.feetToMN(ponto.getAltitude().doubleValue()));
/* 194 */       grupo.getChildren().add(criarConexao(new Point3D(posicaoRaster.getX(), posicaoRaster.getY(), 0.0D), new Point3D(posicaoRaster
/* 195 */               .getX(), posicaoRaster.getY(), -altitude)));
/* 196 */       g2d.fillOval((posicaoRaster.getX() - raioRaster), (posicaoRaster.getY() - raioRaster), (raioRaster * 2), (raioRaster * 2));
/* 197 */       g2d.lineTo(posicaoRaster.getX(), posicaoRaster.getY());
/*     */     } 
/* 199 */     g2d.closePath();
/* 200 */     g2d.stroke();
/* 201 */     g2d.restore();
/*     */     
/* 203 */     return grupo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static Cylinder criarConexao(Point3D origem, Point3D alvo) {
/* 213 */     Point3D yAxis = new Point3D(0.0D, 1.0D, 0.0D);
/* 214 */     Point3D diff = alvo.subtract(origem);
/* 215 */     double height = diff.magnitude();
/*     */     
/* 217 */     Point3D mid = alvo.midpoint(origem);
/* 218 */     Translate moveToMidpoint = new Translate(mid.getX(), mid.getY(), mid.getZ());
/*     */     
/* 220 */     Point3D axisOfRotation = diff.crossProduct(yAxis);
/* 221 */     double angle = Math.acos(diff.normalize().dotProduct(yAxis));
/* 222 */     Rotate rotateAroundCenter = new Rotate(-Math.toDegrees(angle), axisOfRotation);
/* 223 */     Cylinder cilindro = new Cylinder(1.0D, height);
/* 224 */     cilindro.getTransforms().addAll((Object[])new Transform[] { (Transform)moveToMidpoint, (Transform)rotateAroundCenter });
/* 225 */     return cilindro;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/util/desenho/UtilJavaFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */