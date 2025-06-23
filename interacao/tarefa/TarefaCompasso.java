/*     */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaPolar;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.componentes.dominio.entidades.Veiculo;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.configuracao.ConfiguracaoIHM;
/*     */ import ipqm.gsd.componentes.nucleo.logon.perfis.PerfilUsuario;
/*     */ import ipqm.gsd.componentes.nucleo.util.ConversorTipos;
/*     */ import ipqm.gsd.componentes.nucleo.util.ConversorUnidades;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*     */ import ipqm.gsd.hidra.ihm.util.desenho.UtilJava2D;
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics2D;
/*     */ import javafx.scene.Cursor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TarefaCompasso
/*     */   extends Tarefa2P<CanvasCenarioTatico>
/*     */ {
/*     */   private Cursor cursor;
/*     */   
/*     */   public TarefaCompasso(CanvasCenarioTatico canvas) {
/*  33 */     super(canvas);
/*     */   }
/*     */ 
/*     */   
/*     */   public void inicializa() {
/*  38 */     this.cursor = getCanvasEspacial().getCursor();
/*  39 */     getCanvasEspacial().setCursor(Cursor.CROSSHAIR);
/*     */   }
/*     */ 
/*     */   
/*     */   public void finaliza() {
/*  44 */     getCanvasEspacial().setCursor(this.cursor);
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseMovido(CoordenadaCartesiana p, EstadoTeclado e) {
/*  49 */     super.mouseMovido(p, e);
/*     */     
/*  51 */     if (Veiculo.getVeiculoReferencial() != null) {
/*     */       
/*  53 */       Veiculo veiculo = Veiculo.getVeiculoReferencial();
/*  54 */       CoordenadaCartesiana posVeiculo = veiculo.getPosicao().getCoordenadaCartesiana();
/*  55 */       CoordenadaCartesiana posCursor = p;
/*  56 */       double distancia = CoordenadaCartesiana.calcularDistancia(posVeiculo, posCursor);
/*  57 */       double velocidade = veiculo.getVelocidade().getVelocidadeFundo().getVelocidade();
/*     */       
/*  59 */       if (velocidade >= 0.1D) {
/*  60 */         double tempo = distancia / velocidade;
/*  61 */         CanvasCenarioTatico canvasCT = (CanvasCenarioTatico)getCanvasEspacial();
/*  62 */         canvasCT.exibirStatus("Time To Go: " + ConversorTipos.doubleParaStringHMS(tempo));
/*     */       } else {
/*  64 */         CanvasCenarioTatico canvasCT = (CanvasCenarioTatico)getCanvasEspacial();
/*  65 */         canvasCT.exibirStatus("Time To Go: [INDEFINIDO]");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  73 */     super.botao1Pressionado(p, e);
/*  74 */     getCanvasEspacial().setRedesenhoAutomatico(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/*  79 */     super.botao1Solto(p, e);
/*  80 */     CanvasCenarioTatico canvasCT = getCanvasEspacial();
/*  81 */     canvasCT.getCamadaInfo().tornaTransparente();
/*  82 */     canvasCT.getCamadaInfo().geraImagemFX();
/*  83 */     canvasCT.atualizarCamadaTatica();
/*  84 */     canvasCT.setRedesenhoAutomatico(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void desenha() {
/*  89 */     if (this.p1 == null && this.p2 == null) {
/*     */       return;
/*     */     }
/*     */     
/*  93 */     CanvasCenarioTatico canvasCT = getCanvasEspacial();
/*  94 */     canvasCT.getCamadaInfo().tornaTransparente();
/*  95 */     Graphics2D g2d = canvasCT.getCamadaInfo().getImagem().createGraphics();
/*     */     
/*  97 */     if (this.p1 != null && this.p2 != null) {
/*  98 */       g2d.setStroke(new BasicStroke(2.0F));
/*  99 */       g2d.setColor(Color.green);
/* 100 */       CoordenadaRaster p1Raster = canvasCT.projeta(this.p1);
/* 101 */       CoordenadaRaster p2Raster = canvasCT.projeta(this.p2);
/* 102 */       int largura = Math.abs(p2Raster.getX() - p1Raster.getX());
/* 103 */       int altura = Math.abs(p2Raster.getY() - p1Raster.getY());
/* 104 */       int raio = (int)Math.sqrt((largura * largura + altura * altura));
/*     */       
/* 106 */       g2d.setComposite(AlphaComposite.getInstance(3, 0.3F));
/* 107 */       g2d.drawOval(p1Raster.getX() - raio, p1Raster.getY() - raio, 2 * raio, 2 * raio);
/*     */       
/* 109 */       g2d.setComposite(AlphaComposite.getInstance(3, 1.0F));
/* 110 */       UtilJava2D.desenhaTracejado(g2d, p1Raster.getX(), p1Raster.getY(), p2Raster.getX(), p2Raster.getY());
/*     */       
/* 112 */       CoordenadaPolar cp = CoordenadaPolar.converterCoordenadaCartesiana(this.p1, this.p2);
/* 113 */       if (cp == null) {
/*     */         return;
/*     */       }
/* 116 */       double marcacao = cp.getMarcacao();
/* 117 */       double distanciaTextoCompasso = 0.0D;
/* 118 */       double distanciaPrecisa = 0.0D;
/* 119 */       String info2 = null;
/* 120 */       String info3 = null;
/*     */ 
/*     */ 
/*     */       
/* 124 */       ConfiguracaoIHM.Unidades unidadeDistancia = Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoIHM().getUnidadeDistanciaPadrao();
/*     */       
/* 126 */       ConfiguracaoIHM.Unidades unidadeDistanciaPrecisa = Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoIHM().getUnidadeDistPrecisaPadrao();
/*     */       
/* 128 */       distanciaTextoCompasso = cp.getDistancia(unidadeDistancia);
/*     */ 
/*     */       
/* 131 */       switch (unidadeDistancia) {
/*     */         case MN:
/* 133 */           info2 = String.format("%-6.2f MN", new Object[] { Double.valueOf(distanciaTextoCompasso) });
/*     */           
/* 135 */           if (unidadeDistanciaPrecisa.equals(ConfiguracaoIHM.Unidades.JD)) {
/* 136 */             distanciaPrecisa = ConversorUnidades.milhasNauticasParaJardas(distanciaTextoCompasso);
/* 137 */             info3 = String.format("%-5.1f JD", new Object[] { Double.valueOf(distanciaPrecisa) }); break;
/*     */           } 
/* 139 */           distanciaPrecisa = ConversorUnidades.milhasNauticasParaMetros(distanciaTextoCompasso);
/* 140 */           info3 = String.format("%-5.1f M", new Object[] { Double.valueOf(distanciaPrecisa) });
/*     */           break;
/*     */         
/*     */         case KM:
/* 144 */           info2 = String.format("%-6.2f KM", new Object[] { Double.valueOf(distanciaTextoCompasso) });
/*     */           
/* 146 */           if (unidadeDistanciaPrecisa.equals(ConfiguracaoIHM.Unidades.JD)) {
/* 147 */             distanciaPrecisa = ConversorUnidades.quilometrosParaJardas(distanciaTextoCompasso);
/* 148 */             info3 = String.format("%-5.1f JD", new Object[] { Double.valueOf(distanciaPrecisa) }); break;
/*     */           } 
/* 150 */           distanciaPrecisa = distanciaTextoCompasso * 1000.0D;
/* 151 */           info3 = String.format("%-5.1f M", new Object[] { Double.valueOf(distanciaPrecisa) });
/*     */           break;
/*     */         
/*     */         default:
/* 155 */           info2 = String.format("%-6.2f JD", new Object[] { Double.valueOf(distanciaTextoCompasso) });
/*     */           
/* 157 */           if (unidadeDistanciaPrecisa.equals(ConfiguracaoIHM.Unidades.JD)) {
/* 158 */             distanciaPrecisa = distanciaTextoCompasso;
/* 159 */             info3 = String.format("%-5.1f JD", new Object[] { Double.valueOf(distanciaPrecisa) }); break;
/*     */           } 
/* 161 */           distanciaPrecisa = ConversorUnidades.jardasParaMetros(distanciaTextoCompasso);
/* 162 */           info3 = String.format("%-5.1f M", new Object[] { Double.valueOf(distanciaPrecisa) });
/*     */           break;
/*     */       } 
/*     */ 
/*     */       
/* 167 */       String info1 = String.format("%03d°", new Object[] { Integer.valueOf((int)marcacao) });
/* 168 */       Font font = new Font("Arial", 1, 12);
/* 169 */       FontMetrics fm = g2d.getFontMetrics(font);
/* 170 */       g2d.setFont(font);
/* 171 */       g2d.setColor(Color.black);
/*     */       
/* 173 */       UtilJava2D.escreveLegendasDireita(info1, info2, null, p2Raster.getX() + 15, p2Raster.getY() + 10 + fm.getHeight() / 2, 
/* 174 */           PerfilUsuario.getPerfilUsuarioAtual().getServidorCartas().getTextoObjetoGrafico(), 
/* 175 */           PerfilUsuario.getPerfilUsuarioAtual().getServidorCartas().getFundoObjetoGrafico(), g2d);
/*     */       
/* 177 */       String infoStatus = "Marcacao: " + info1 + ", distancia " + info2 + " (" + info3 + ")";
/* 178 */       canvasCT.exibirStatus(infoStatus);
/*     */     } 
/*     */     
/* 181 */     g2d.dispose();
/*     */     
/* 183 */     canvasCT.getCamadaInfo()
/* 184 */       .geraImagemFX();
/* 185 */     canvasCT.atualizarCamadaTatica();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p) {
/* 191 */     CanvasCenarioTatico canvasCT = (CanvasCenarioTatico)getCanvasEspacial();
/* 192 */     canvasCT.exibirStatus("");
/* 193 */     canvasCT.getCamadaInfo().tornaTransparente();
/* 194 */     getCanvasEspacial().getCamadaInfo().geraImagemFX();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p0, CoordenadaCartesiana p1) {
/* 201 */     CanvasCenarioTatico canvasCT = (CanvasCenarioTatico)getCanvasEspacial();
/* 202 */     canvasCT.exibirStatus("");
/* 203 */     canvasCT.getCamadaInfo().tornaTransparente();
/* 204 */     getCanvasEspacial().getCamadaInfo().geraImagemFX();
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaCompasso.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */