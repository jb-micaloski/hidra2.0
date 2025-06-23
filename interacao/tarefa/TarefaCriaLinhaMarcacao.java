/*     */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.acompanhamentos.Acompanhamento;
/*     */ import ipqm.gsd.componentes.dominio.acompanhamentos.AcompanhamentoLinhaMarcacaoManual;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaPolar;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.Posicao;
/*     */ import ipqm.gsd.componentes.dominio.contexto.ContextoTatico;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*     */ import ipqm.gsd.hidra.ihm.util.desenho.UtilJava2D;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javafx.scene.Cursor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TarefaCriaLinhaMarcacao
/*     */   extends Tarefa2P<CanvasCenarioTatico>
/*     */ {
/*     */   private Cursor cursor;
/*     */   private GeneralPath caminho;
/*  35 */   int cont = 1;
/*     */   private Acompanhamento.Hostilidade corAcompanhamento;
/*     */   private CoordenadaCartesiana posicaoFinal;
/*     */   private CoordenadaCartesiana posicaoInicio;
/*     */   
/*     */   public TarefaCriaLinhaMarcacao(CanvasCenarioTatico canvas) {
/*  41 */     super(canvas);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseMovido(CoordenadaCartesiana p, EstadoTeclado e) {
/*  47 */     super.mouseMovido(p, e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void inicializa() {
/*  53 */     this.cursor = getCanvasEspacial().getCursor();
/*  54 */     getCanvasEspacial().setCursor(Cursor.CROSSHAIR);
/*     */   }
/*     */ 
/*     */   
/*     */   public void finaliza() {
/*  59 */     getCanvasEspacial().setCursor(this.cursor);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  64 */     super.botao1Pressionado(p, e);
/*  65 */     getCanvasEspacial().setRedesenhoAutomatico(false);
/*  66 */     this.posicaoInicio = p;
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/*  71 */     super.botao1Solto(p, e);
/*  72 */     CanvasCenarioTatico canvasCT = getCanvasEspacial();
/*  73 */     canvasCT.getCamadaInfo().tornaTransparente();
/*  74 */     canvasCT.getCamadaInfo().geraImagemFX();
/*  75 */     canvasCT.atualizarCamadaTatica();
/*  76 */     canvasCT.setRedesenhoAutomatico(true);
/*  77 */     this.posicaoFinal = p;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void desenha() {
/*  83 */     if (this.p1 == null && this.p2 == null) {
/*     */       return;
/*     */     }
/*     */     
/*  87 */     CanvasCenarioTatico canvasCT = getCanvasEspacial();
/*  88 */     canvasCT.getCamadaInfo().tornaTransparente();
/*  89 */     Graphics2D g2d = canvasCT.getCamadaInfo().getImagem().createGraphics();
/*  90 */     this.caminho = new GeneralPath();
/*     */     
/*  92 */     if (this.p1 != null && this.p2 != null) {
/*  93 */       g2d.setStroke(new BasicStroke(2.0F));
/*  94 */       CoordenadaRaster p1R = canvasCT.projeta(this.p1);
/*  95 */       CoordenadaRaster p2R = canvasCT.projeta(this.p2);
/*     */       
/*  97 */       UtilJava2D.desenhaTracejado(g2d, p1R.getX(), p1R.getY(), p2R.getX(), p2R.getY());
/*  98 */       CoordenadaPolar cp = CoordenadaPolar.converterCoordenadaCartesiana(this.p1, this.p2);
/*  99 */       if (cp != null) {
/* 100 */         double marcacao = cp.getMarcacao();
/*     */         
/* 102 */         String info1 = String.format("%03d°", new Object[] { Integer.valueOf((int)marcacao) });
/* 103 */         Font font = new Font("Arial", 1, 12);
/* 104 */         g2d.setFont(font);
/* 105 */         g2d.setColor(Color.black);
/* 106 */         UtilJava2D.escreveTexto(info1, p2R.getX() + 15, p2R.getY() - 2, Color.black, Color.white, g2d);
/*     */         
/* 108 */         g2d.drawLine(p1R.getX(), p1R.getY(), p2R.getX(), p2R.getY());
/* 109 */         g2d.dispose();
/* 110 */         canvasCT.getCamadaInfo().geraImagemFX();
/*     */         
/* 112 */         canvasCT.setRedesenhoAutomatico(true);
/* 113 */         canvasCT.atualizarCamadaTatica();
/*     */       } 
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
/*     */   public void habilitarComandos() {
/* 127 */     setHabilitarComando(Comando.CRIA_LINHA_ACOMPANHAMENTO, true);
/* 128 */     setHabilitarComando(Comando.CANCELA_LINHA_ACOMPANHAMENTO, true);
/* 129 */     setHabilitarComando(Comando.PLOTAR_LINHAS_MARCACAO, true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void executarComando(Comando comando, CoordenadaCartesiana posicao) {
/* 135 */     if (isHabilitarComando(comando)) {
/* 136 */       CanvasCenarioTatico canvasCT; switch (comando) {
/*     */         case CRIA_LINHA_ACOMPANHAMENTO:
/* 138 */           canvasCT = getCanvasEspacial();
/* 139 */           this.posicaoInicio = this.p1;
/* 140 */           this.posicaoFinal = this.p2;
/* 141 */           canvasCT.atualizarCamadaTatica();
/*     */           break;
/*     */       } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Comando, Boolean> obterComandosHabilitados() {
/* 171 */     Map<Comando, Boolean> comandosHabilitados = new HashMap<>();
/*     */     
/* 173 */     comandosHabilitados.put(Comando.CRIA_LINHA_ACOMPANHAMENTO, Boolean.valueOf(isHabilitarComando(Comando.CRIA_LINHA_ACOMPANHAMENTO)));
/* 174 */     comandosHabilitados.put(Comando.CANCELA_LINHA_ACOMPANHAMENTO, Boolean.valueOf(isHabilitarComando(Comando.CANCELA_LINHA_ACOMPANHAMENTO)));
/* 175 */     comandosHabilitados.put(Comando.PLOTAR_LINHAS_MARCACAO, Boolean.valueOf(isHabilitarComando(Comando.PLOTAR_LINHAS_MARCACAO)));
/*     */     
/* 177 */     return comandosHabilitados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p1, CoordenadaCartesiana p2) {
/* 184 */     CoordenadaGeografica cg = CoordenadaGeografica.converterCoordenadaCartesiana(p1, 0.0D);
/* 185 */     CoordenadaPolar cp = CoordenadaPolar.converterCoordenadaCartesiana(p1, p2);
/*     */     
/*     */     try {
/* 188 */       CoordenadaPolar coordPolar = CoordenadaPolar.converterCoordenadaCartesiana(p1, p2);
/* 189 */       double marcacao = coordPolar.getMarcacao();
/* 190 */       Posicao posRef = ((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getTeatroOperacao().getPosicaoTeatroOperacao();
/*     */       
/* 192 */       AcompanhamentoLinhaMarcacaoManual acomp = AcompanhamentoLinhaMarcacaoManual.fabricar(cg.getLatitude(), cg.getLongitude(), marcacao, posRef, null);
/* 193 */       acomp.salvarMemoria(true);
/*     */     }
/* 195 */     catch (Exception ex) {
/* 196 */       Log.gravarLogExcecao("Erro ao criar acompanhamento", this, ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p) {}
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaCriaLinhaMarcacao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */