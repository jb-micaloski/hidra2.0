/*    */ package ipqm.gsd.hidra.ihm.camadas;
/*    */ 
/*    */ import ipqm.gsd.componentes.dominio.ObjetoTatico;
/*    */ import ipqm.gsd.componentes.dominio.acompanhamentos.AcompanhamentoMage;
/*    */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*    */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*    */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaPolar;
/*    */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*    */ import ipqm.gsd.componentes.dominio.entidades.Veiculo;
/*    */ import ipqm.gsd.componentes.dominio.navegacao.JanelaCartesiana;
/*    */ import ipqm.gsd.componentes.nucleo.Mediador;
/*    */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*    */ import ipqm.gsd.componentes.nucleo.log.Log;
/*    */ import ipqm.gsd.hidra.ihm.camadas.filtros.FiltroMage;
/*    */ import ipqm.gsd.hidra.ihm.gestao.GestorObjetosGraficosFX;
/*    */ import ipqm.gsd.hidra.ihm.interacao.CanvasEspacial;
/*    */ import ipqm.gsd.hidra.ihm.interacao.tarefa.TarefaSelecao;
/*    */ import ipqm.gsd.hidra.ihm.projetos.mage.cenas.controladores.ControladorMage;
/*    */ import java.awt.BasicStroke;
/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics2D;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CamadaMage
/*    */   extends CamadaTatica<FiltroMage>
/*    */ {
/*    */   private ControladorMage controladorMage;
/*    */   
/*    */   public CamadaMage(FiltroMage filtro, int largura, int altura) {
/* 36 */     super(filtro, largura, altura);
/*    */   }
/*    */ 
/*    */   
/*    */   public synchronized void desenha(JanelaCartesiana janela, List<ObjetoTatico> listaObjetos, TarefaSelecao<? extends CanvasEspacial> tarefaSelecao, float opacidade, ControladorMage controlador) {
/* 41 */     tornaTransparente();
/* 42 */     if (getControladorMage() == null) {
/* 43 */       setControladorMage(controlador);
/*    */     }
/*    */     
/* 46 */     setJanelaCartesiana(janela);
/* 47 */     if (listaObjetos == null) {
/*    */       return;
/*    */     }
/*    */     
/* 51 */     this.tarefaSelecao = tarefaSelecao;
/* 52 */     Veiculo veiculo = Veiculo.getVeiculoReferencial();
/* 53 */     desenharRumo(veiculo, this);
/*    */     try {
/* 55 */       listaObjetos.stream().forEach(obj -> {
/*    */             ((AcompanhamentoMage)obj).getCinematica().getPosicao().getCoordenadaPolar().setDistancia(((AcompanhamentoMage)obj).getDistanciaMage());
/*    */             
/*    */             obj.getCinematica().getPosicao().getCoordenadaPolar().setMarcacao(obj.getCinematica().getPosicao().getCoordenadaPolar().getMarcacao());
/*    */             
/*    */             obj.getCinematica().getPosicao().setCoordenadaCartesiana(CoordenadaCartesiana.converterCoordenadaPolar(veiculo.getPosicao().getCoordenadaCartesiana(), obj.getCinematica().getPosicao().getCoordenadaPolar()));
/*    */             obj.getCinematica().getPosicao().setCoordenadaGeografica(CoordenadaGeografica.converterCoordenadaCartesiana(obj.getCinematica().getPosicao().getCoordenadaCartesiana(), 0.0D));
/*    */             ((GestorObjetosGraficosFX)Mediador.getInstancia().getGestor().getGestorObjetos()).desenharObjetoAlternativo((ObjetoDOMINIO)obj, this);
/*    */           });
/* 64 */     } catch (Exception e) {
/* 65 */       Log.gravarLogExcecao("Erro ao tentar desenhar objetos graficos no Mage", this, e);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public ControladorMage getControladorMage() {
/* 71 */     return this.controladorMage;
/*    */   }
/*    */   
/*    */   public void setControladorMage(ControladorMage controladorMage) {
/* 75 */     this.controladorMage = controladorMage;
/*    */   }
/*    */   
/*    */   private void desenharRumo(Veiculo veiculo, CamadaBI camada) {
/* 79 */     Graphics2D g2d = camada.getImagem().createGraphics();
/* 80 */     double alcance = Math.min(camada.getJanelaCartesiana().getLargura(), camada.getJanelaCartesiana().getAltura()) / 2.0D;
/* 81 */     CoordenadaCartesiana posCart0 = veiculo.getPosicao().getCoordenadaCartesiana();
/*    */     
/* 83 */     Double rumoSonar = Double.valueOf(veiculo.getRumo().getRumoSuperficie().getRumo());
/* 84 */     CoordenadaPolar cp = new CoordenadaPolar(rumoSonar.doubleValue(), 2.0D * alcance);
/* 85 */     CoordenadaCartesiana posCart1 = CoordenadaCartesiana.converterCoordenadaPolar(posCart0, cp);
/*    */     
/* 87 */     CoordenadaRaster pos0 = camada.converterCoordenadaCartesianaParaRaster(posCart0);
/* 88 */     CoordenadaRaster pos1 = camada.converterCoordenadaCartesianaParaRaster(posCart1);
/*    */     
/* 90 */     g2d = camada.getImagem().createGraphics();
/* 91 */     float[] dashPattern = { 5.0F, 5.0F };
/* 92 */     g2d.setStroke(new BasicStroke(2.0F, 0, 0, 10.0F, dashPattern, 0.0F));
/* 93 */     g2d.setColor(new Color(255, 255, 0));
/* 94 */     g2d.drawLine(pos0.getX(), pos0.getY(), pos1.getX(), pos1.getY());
/* 95 */     g2d.dispose();
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/camadas/CamadaMage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */