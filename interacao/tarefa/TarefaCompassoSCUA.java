/*    */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*    */ 
/*    */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*    */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaPolar;
/*    */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*    */ import ipqm.gsd.componentes.nucleo.Mediador;
/*    */ import ipqm.gsd.componentes.nucleo.configuracao.ConfiguracaoIHM;
/*    */ import ipqm.gsd.componentes.nucleo.logon.perfis.PerfilUsuario;
/*    */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*    */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*    */ import ipqm.gsd.hidra.ihm.util.desenho.UtilJava2D;
/*    */ import java.awt.AlphaComposite;
/*    */ import java.awt.BasicStroke;
/*    */ import java.awt.Color;
/*    */ import java.awt.Font;
/*    */ import java.awt.FontMetrics;
/*    */ import java.awt.Graphics2D;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TarefaCompassoSCUA
/*    */   extends TarefaCompasso
/*    */ {
/*    */   public TarefaCompassoSCUA(CanvasCenarioTatico canvas) {
/* 26 */     super(canvas);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void mouseMovido(CoordenadaCartesiana p, EstadoTeclado e) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void desenha() {
/* 36 */     if (this.p1 == null && this.p2 == null) {
/*    */       return;
/*    */     }
/*    */     
/* 40 */     CanvasCenarioTatico canvasCT = getCanvasEspacial();
/* 41 */     canvasCT.getCamadaInfo().tornaTransparente();
/* 42 */     Graphics2D g2d = canvasCT.getCamadaInfo().getImagem().createGraphics();
/*    */     
/* 44 */     if (this.p1 != null && this.p2 != null) {
/*    */       
/* 46 */       g2d.setColor(Color.RED);
/* 47 */       CoordenadaRaster p1Raster = canvasCT.projeta(this.p1);
/* 48 */       CoordenadaRaster p2Raster = canvasCT.projeta(this.p2);
/* 49 */       int largura = Math.abs(p2Raster.getX() - p1Raster.getX());
/* 50 */       int altura = Math.abs(p2Raster.getY() - p1Raster.getY());
/* 51 */       int raio = (int)Math.sqrt((largura * largura + altura * altura));
/*    */       
/* 53 */       g2d.setStroke(new BasicStroke(2.0F));
/* 54 */       g2d.setComposite(AlphaComposite.getInstance(3, 1.0F));
/* 55 */       g2d.drawOval(p1Raster.getX() - raio, p1Raster.getY() - raio, 2 * raio, 2 * raio);
/*    */       
/* 57 */       g2d.setStroke(new BasicStroke(1.0F));
/* 58 */       g2d.setComposite(AlphaComposite.getInstance(3, 1.0F));
/* 59 */       g2d.drawLine(p1Raster.getX(), p1Raster.getY(), p2Raster.getX(), p2Raster.getY());
/*    */       
/* 61 */       CoordenadaPolar cp = CoordenadaPolar.converterCoordenadaCartesiana(this.p1, this.p2);
/* 62 */       if (cp == null) {
/*    */         return;
/*    */       }
/*    */       
/* 66 */       double marcacao = cp.getMarcacao();
/*    */ 
/*    */ 
/*    */       
/* 70 */       ConfiguracaoIHM.Unidades unidadeDistancia = Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoIHM().getUnidadeDistanciaPadrao();
/*    */       
/* 72 */       ConfiguracaoIHM.Unidades unidadeDistanciaPrecisa = Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoIHM().getUnidadeDistPrecisaPadrao();
/*    */       
/* 74 */       String info2 = String.format("%.2f", new Object[] { Double.valueOf(cp.getDistancia(unidadeDistancia)) }) + " " + unidadeDistancia.getUnidade();
/* 75 */       String info3 = String.format("%.2f", new Object[] { Double.valueOf(cp.getDistancia(unidadeDistanciaPrecisa)) }) + " " + unidadeDistanciaPrecisa.getUnidade();
/*    */       
/* 77 */       String info1 = String.format("%03d°", new Object[] { Integer.valueOf((int)marcacao) });
/* 78 */       Font font = new Font("Arial", 1, 12);
/* 79 */       FontMetrics fm = g2d.getFontMetrics(font);
/* 80 */       g2d.setFont(font);
/* 81 */       g2d.setColor(Color.black);
/*    */       
/* 83 */       UtilJava2D.escreveLegendasDireita(info1, info2, null, p2Raster.getX() + 15, p2Raster.getY() + 10 + fm.getHeight() / 2, 
/* 84 */           PerfilUsuario.getPerfilUsuarioAtual().getServidorCartas().getTextoObjetoGrafico(), 
/* 85 */           PerfilUsuario.getPerfilUsuarioAtual().getServidorCartas().getFundoObjetoGrafico(), g2d);
/*    */       
/* 87 */       String infoStatus = "Marcação: " + info1 + ", distância " + info2 + " (" + info3 + ")";
/* 88 */       canvasCT.exibirStatus(infoStatus);
/*    */     } 
/*    */     
/* 91 */     g2d.dispose();
/* 92 */     canvasCT.getCamadaInfo().geraImagemFX();
/* 93 */     canvasCT.atualizarCamadaTatica();
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaCompassoSCUA.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */