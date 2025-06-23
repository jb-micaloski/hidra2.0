/*     */ package ipqm.gsd.hidra.ihm.camadas;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.objeto_visual.Camada;
/*     */ import ipqm.gsd.componentes.nucleo.objeto_visual.Filtro;
/*     */ import java.awt.Color;
/*     */ import java.nio.ByteBuffer;
/*     */ import java.util.Arrays;
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
/*     */ public class CamadaBGRA<F extends Filtro>
/*     */   extends Camada<F>
/*     */ {
/*     */   protected static final int BLUE = 0;
/*     */   protected static final int GREEN = 1;
/*     */   protected static final int RED = 2;
/*     */   protected static final int ALPHA = 3;
/*     */   protected static final int BYTES_PIXEL = 4;
/*     */   protected byte[] bgra;
/*     */   private ByteBuffer buffer;
/*     */   
/*     */   public CamadaBGRA(F filtro, int largura, int altura) {
/*  33 */     super((Filtro)filtro, largura, altura);
/*  34 */     this.bgra = new byte[largura * altura * 4];
/*  35 */     this.buffer = ByteBuffer.wrap(this.bgra);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void redimensiona(int largura, int altura) {
/*  45 */     super.redimensiona(largura, altura);
/*  46 */     this.bgra = new byte[largura * altura * 4];
/*  47 */     this.buffer = ByteBuffer.wrap(this.bgra);
/*  48 */     tornaTransparente();
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
/*     */   void setPixel(int x, int y, byte b, byte g, byte r, byte a) {
/*  61 */     if (x < 0 || y < 0 || x >= getLargura() || y >= getAltura()) {
/*     */       return;
/*     */     }
/*  64 */     int pos = (y * getLargura() + x) * 4;
/*     */     
/*  66 */     this.bgra[pos + 0] = b;
/*  67 */     this.bgra[pos + 1] = g;
/*  68 */     this.bgra[pos + 2] = r;
/*  69 */     this.bgra[pos + 3] = a;
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
/*     */   public void setMarca(int x, int y, byte b, byte g, byte r, byte a) {
/*  84 */     y = getAltura() - y - 1;
/*  85 */     if (x < 0 || y < 0 || x >= getLargura() || y >= getAltura()) {
/*     */       return;
/*     */     }
/*     */     
/*  89 */     int pos = ((y - 1) * getLargura() + x) * 4;
/*  90 */     if (pos >= 0 && pos < this.bgra.length) {
/*  91 */       this.bgra[pos + 0] = b;
/*  92 */       this.bgra[pos + 1] = g;
/*  93 */       this.bgra[pos + 2] = r;
/*  94 */       this.bgra[pos + 3] = a;
/*     */     } 
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
/* 110 */     pos += getLargura() * 4;
/* 111 */     if (pos >= 0 && pos < this.bgra.length) {
/* 112 */       this.bgra[pos + 0] = b;
/* 113 */       this.bgra[pos + 1] = g;
/* 114 */       this.bgra[pos + 2] = r;
/* 115 */       this.bgra[pos + 3] = a;
/*     */     } 
/* 117 */     if (pos >= 4 && pos < this.bgra.length) {
/* 118 */       this.bgra[pos - 4 + 0] = b;
/* 119 */       this.bgra[pos - 4 + 1] = g;
/* 120 */       this.bgra[pos - 4 + 2] = r;
/* 121 */       this.bgra[pos - 4 + 3] = a;
/*     */     } 
/* 123 */     if (pos > 0 && pos < this.bgra.length - 4) {
/* 124 */       this.bgra[pos + 4 + 0] = b;
/* 125 */       this.bgra[pos + 4 + 1] = g;
/* 126 */       this.bgra[pos + 4 + 2] = r;
/* 127 */       this.bgra[pos + 4 + 3] = a;
/*     */     } 
/*     */ 
/*     */     
/* 131 */     pos += getLargura() * 4;
/* 132 */     if (pos >= 0 && pos < this.bgra.length) {
/* 133 */       this.bgra[pos + 0] = b;
/* 134 */       this.bgra[pos + 1] = g;
/* 135 */       this.bgra[pos + 2] = r;
/* 136 */       this.bgra[pos + 3] = a;
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
/*     */   public void setPixel(int x, int y, Color c) {
/* 159 */     setPixel(x, y, (byte)c.getRed(), (byte)c.getGreen(), (byte)c.getBlue(), (byte)c.getAlpha());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMarca(int x, int y, Color c) {
/* 169 */     setMarca(x, y, (byte)c.getBlue(), (byte)c.getGreen(), (byte)c.getRed(), (byte)c.getAlpha());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMarca(int x, int y, Color c, byte opacidade) {
/* 180 */     setMarca(x, y, (byte)c.getBlue(), (byte)c.getGreen(), (byte)c.getRed(), opacidade);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getPixel(int x, int y) {
/* 190 */     int pos = (y * getLargura() + x) * 4;
/* 191 */     return (pos < this.bgra.length) ? new Color(this.bgra[pos + 0], this.bgra[pos + 1], this.bgra[pos + 2], this.bgra[pos + 3]) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tornaTransparente() {
/* 200 */     Arrays.fill(this.bgra, (byte)0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void limpa(Color cor) {
/* 209 */     for (int i = 0; i < this.bgra.length; i += 4) {
/* 210 */       this.bgra[i + 0] = (byte)cor.getBlue();
/* 211 */       this.bgra[i + 1] = (byte)cor.getGreen();
/* 212 */       this.bgra[i + 2] = (byte)cor.getRed();
/* 213 */       this.bgra[i + 3] = (byte)cor.getAlpha();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ByteBuffer getBuffer() {
/* 222 */     return this.buffer;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/camadas/CamadaBGRA.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */