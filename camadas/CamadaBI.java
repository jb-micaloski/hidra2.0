/*     */ package ipqm.gsd.hidra.ihm.camadas;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.JanelaCartesiana;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.objeto_visual.Camada;
/*     */ import ipqm.gsd.componentes.nucleo.objeto_visual.Filtro;
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Point;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ImageObserver;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import javafx.embed.swing.SwingFXUtils;
/*     */ import javafx.scene.image.WritableImage;
/*     */ import javax.imageio.ImageIO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CamadaBI<F extends Filtro>
/*     */   extends Camada<F>
/*     */ {
/*  29 */   protected BufferedImage imagem = null;
/*  30 */   protected JanelaCartesiana janelaCart = null;
/*  31 */   protected WritableImage imagemFX = null;
/*     */   
/*     */   public CamadaBI(F filtro, int largura, int altura) {
/*  34 */     super((Filtro)filtro, largura, altura);
/*  35 */     this.imagem = new BufferedImage(largura, altura, 3);
/*  36 */     this.imagemFX = new WritableImage(largura, altura);
/*     */   }
/*     */ 
/*     */   
/*     */   public void redimensiona(int largura, int altura) {
/*  41 */     super.redimensiona(largura, altura);
/*  42 */     this.imagem = new BufferedImage(largura, altura, 3);
/*  43 */     tornaTransparente();
/*     */   }
/*     */ 
/*     */   
/*     */   public void tornaTransparente() {
/*  48 */     Graphics2D g2d = this.imagem.createGraphics();
/*  49 */     g2d.setComposite(AlphaComposite.Src);
/*  50 */     g2d.setColor(new Color(0, 0, 0, 0));
/*  51 */     g2d.fillRect(0, 0, getLargura(), getAltura());
/*  52 */     g2d.setComposite(AlphaComposite.SrcOver);
/*  53 */     g2d.dispose();
/*     */   }
/*     */   
/*     */   public void limpa(Color cor) {
/*  57 */     Graphics2D g2d = this.imagem.createGraphics();
/*  58 */     g2d.clearRect(0, 0, getLargura(), getAltura());
/*  59 */     g2d.setBackground(cor);
/*  60 */     g2d.dispose();
/*     */   }
/*     */   
/*     */   public void limpa() {
/*  64 */     limpa(Color.black);
/*     */   }
/*     */   
/*     */   public BufferedImage getImagem() {
/*  68 */     return this.imagem;
/*     */   }
/*     */   
/*     */   public synchronized void combina(CamadaBI superior) {
/*  72 */     combina(superior, superior.getFiltro().getOpacidade(), 0, 0);
/*     */   }
/*     */   
/*     */   public synchronized void combina(CamadaBI superior, float opacidade) {
/*  76 */     combina(superior, opacidade, 0, 0);
/*     */   }
/*     */   
/*     */   public synchronized void combina(CamadaBI superior, float opacidade, int x, int y) {
/*  80 */     if (superior != null) {
/*  81 */       BufferedImage img_superior = superior.getImagem();
/*  82 */       if (img_superior != null) {
/*  83 */         Graphics2D g2d = this.imagem.createGraphics();
/*  84 */         g2d.setComposite(AlphaComposite.getInstance(3, opacidade));
/*  85 */         g2d.drawImage(img_superior, x, y, (ImageObserver)null);
/*  86 */         g2d.setComposite(AlphaComposite.getInstance(3, 1.0F));
/*  87 */         g2d.dispose();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setJanelaCartesiana(JanelaCartesiana janela) {
/*  93 */     this.janelaCart = janela;
/*     */   }
/*     */   
/*     */   public JanelaCartesiana getJanelaCartesiana() {
/*  97 */     return this.janelaCart;
/*     */   }
/*     */   
/*     */   public CoordenadaRaster converterCoordenadaCartesianaParaRaster(CoordenadaCartesiana p) {
/* 101 */     if (this.janelaCart == null) {
/* 102 */       return null;
/*     */     }
/* 104 */     double iX = (p.getX() - this.janelaCart.getMin().getX()) / this.janelaCart.getLargura();
/* 105 */     double iY = (this.janelaCart.getMax().getY() - p.getY()) / this.janelaCart.getAltura();
/* 106 */     return new CoordenadaRaster((int)(getLargura() * iX), (int)(getAltura() * iY));
/*     */   }
/*     */ 
/*     */   
/*     */   public CoordenadaCartesiana converterCoordenadaRasterParaCartesiana(CoordenadaRaster p) {
/* 111 */     double cartesianoX = this.janelaCart.getMin().getX() + p.getX() / (getLargura() - 1.0D) * this.janelaCart.getLargura();
/*     */     
/* 113 */     double cartesianoY = this.janelaCart.getMax().getY() - p.getY() / (getAltura() - 1.0D) * this.janelaCart.getAltura();
/* 114 */     return new CoordenadaCartesiana(cartesianoX, cartesianoY);
/*     */   }
/*     */   
/*     */   public CoordenadaCartesiana converterCoordenaRasterParaCartesiana(Point p) {
/* 118 */     return converterCoordenadaRasterParaCartesiana(new CoordenadaRaster(p.x, p.y));
/*     */   }
/*     */   
/*     */   public int converterDistanciaCartesianaParaRaster(double distanciaCartesiana) {
/* 122 */     CoordenadaCartesiana p1 = CoordenadaCartesiana.converterDistanciaXY(getJanelaCartesiana().getCentro(), distanciaCartesiana, 0.0D);
/* 123 */     double dx = Math.abs(p1.getX() - getJanelaCartesiana().getCentro().getX());
/* 124 */     return (int)(dx / this.janelaCart.getLargura() * getLargura() + 0.5D);
/*     */   }
/*     */   
/*     */   public double converterDistanciaRasterParaCartesiana(int distanciaRaster) {
/* 128 */     double dx = distanciaRaster / getLargura() * this.janelaCart.getLargura();
/* 129 */     CoordenadaCartesiana p1 = new CoordenadaCartesiana(this.janelaCart.getCentro().getX() + dx, this.janelaCart.getCentro().getY());
/* 130 */     return CoordenadaCartesiana.calcularDistancia(this.janelaCart.getCentro(), p1);
/*     */   }
/*     */   
/*     */   public byte[] getPNG() {
/* 134 */     byte[] resultado = null;
/* 135 */     try (ByteArrayOutputStream baos = new ByteArrayOutputStream(1000)) {
/* 136 */       ImageIO.write(this.imagem, "png", baos);
/* 137 */       baos.flush();
/* 138 */       resultado = baos.toByteArray();
/* 139 */     } catch (IOException ex) {
/* 140 */       Log.gravarLogExcecao("Erro de entrada/saída de dados ", this, ex);
/*     */     } 
/* 142 */     return resultado;
/*     */   }
/*     */ 
/*     */   
/*     */   public void salvaEmArquivo(byte[] arrayBytes, String nomeArquivo) {
/* 147 */     try (FileOutputStream fos = new FileOutputStream(nomeArquivo)) {
/* 148 */       fos.write(arrayBytes);
/* 149 */     } catch (FileNotFoundException ex) {
/* 150 */       Log.gravarLogExcecao("Erro. Arquivo não encontrado. ", this, ex);
/* 151 */     } catch (IOException ex) {
/* 152 */       Log.gravarLogExcecao("Erro de entrada/saída de dados ", this, ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void salvaEmArquivo(String nomeArquivo) {
/* 157 */     try (FileOutputStream fos = new FileOutputStream(nomeArquivo)) {
/* 158 */       fos.write(getPNG());
/* 159 */     } catch (FileNotFoundException ex) {
/* 160 */       Log.gravarLogExcecao("Erro. Arquivo não encontrado. ", this, ex);
/* 161 */     } catch (IOException ex) {
/* 162 */       Log.gravarLogExcecao("Erro de entrada/saída de dados", this, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void geraImagemFX() {
/* 169 */     if (this.imagem == null || this.imagem.getRaster() == null) {
/*     */       return;
/*     */     }
/* 172 */     this.imagemFX = SwingFXUtils.toFXImage(this.imagem, this.imagemFX);
/*     */   }
/*     */   
/*     */   public WritableImage getImagemFX() {
/* 176 */     return this.imagemFX;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/camadas/CamadaBI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */