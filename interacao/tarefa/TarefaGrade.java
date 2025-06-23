/*     */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.ObjetoTatico;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.componentes.dominio.contexto.ContextoTatico;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.Grade;
/*     */ import ipqm.gsd.componentes.dominio.teatro_operacao.TeatroOperacao;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*     */ import ipqm.gsd.hidra.ihm.projetos.geral.cenas.controladores.ControladorCenarioTatico;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javafx.scene.Cursor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TarefaGrade
/*     */   extends Tarefa1P<CanvasCenarioTatico>
/*     */ {
/*     */   private Cursor cursor;
/*     */   
/*     */   public TarefaGrade(CanvasCenarioTatico canvas) {
/*  30 */     super(canvas);
/*     */   }
/*     */ 
/*     */   
/*     */   public void inicializa() {
/*  35 */     this.cursor = getCanvasEspacial().getCursor();
/*  36 */     getCanvasEspacial().setCursor(Cursor.CROSSHAIR);
/*     */   }
/*     */ 
/*     */   
/*     */   public void finaliza() {
/*  41 */     getCanvasEspacial().setCursor(this.cursor);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  46 */     super.botao1Pressionado(p, e);
/*  47 */     getCanvasEspacial().setRedesenhoAutomatico(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/*  52 */     super.botao1Solto(p, e);
/*  53 */     getCanvasEspacial().setRedesenhoAutomatico(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void desenha() {
/*  58 */     CanvasCenarioTatico canvasCT = getCanvasEspacial();
/*  59 */     canvasCT.setRedesenhoAutomatico(false);
/*     */     
/*  61 */     canvasCT.getCamadaInfo().tornaTransparente();
/*  62 */     Graphics2D g2d = canvasCT.getCamadaInfo().getImagem().createGraphics();
/*     */     
/*  64 */     if (this.p != null) {
/*  65 */       CoordenadaRaster pR = canvasCT.projeta(this.p);
/*  66 */       g2d.setStroke(new BasicStroke(3.0F));
/*  67 */       g2d.setColor(Color.white);
/*  68 */       g2d.drawLine(0, pR.getY(), canvasCT.getLargura() - 1, pR.getY());
/*  69 */       g2d.drawLine(pR.getX(), 0, pR.getX(), canvasCT.getAltura() - 1);
/*     */     } 
/*     */     
/*  72 */     g2d.dispose();
/*  73 */     canvasCT.setRedesenhoAutomatico(true);
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
/*     */   public void criaObjeto(CoordenadaCartesiana p) {
/*  87 */     ((ControladorCenarioTatico)getCanvasEspacial().getControlador()).exibirPainelFormulario("projetos/geral/formularios/GradeForm.fxml", "Editar Grade", null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void habilitarComandos() {
/*  98 */     setHabilitarComando(Comando.EDITAR_GRADE, true);
/*  99 */     setHabilitarComando(Comando.REDEFINIR_GRADE, true);
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
/*     */   public boolean isHabilitarComando(Comando comando) {
/* 115 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void executarComando(Comando comando, CoordenadaCartesiana posicao) {
/* 121 */     if (isHabilitarComando(comando)) {
/* 122 */       TeatroOperacao teatro; Grade grade = ((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getGrade();
/*     */       
/* 124 */       switch (comando) {
/*     */         case EDITAR_GRADE:
/* 126 */           ((ControladorCenarioTatico)getCanvasEspacial().getControlador()).exibirPainelFormulario("projetos/geral/formularios/GradeForm.fxml", "Editar Grade", (ObjetoTatico)grade);
/*     */           break;
/*     */         case REDEFINIR_GRADE:
/* 129 */           grade.setCoordenadaCartesiana(new CoordenadaCartesiana());
/* 130 */           grade.salvarMemoria(false);
/*     */           break;
/*     */         case GRADE_CENTRO_EXERCICIO:
/* 133 */           teatro = ((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getTeatroOperacao();
/* 134 */           grade.setCoordenadaCartesiana(teatro.getPosicaoTeatroOperacao().getCoordenadaCartesiana());
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
/*     */   public Map<Comando, Boolean> obterComandosHabilitados() {
/* 163 */     Map<Comando, Boolean> comandosHabilitados = new HashMap<>();
/*     */     
/* 165 */     comandosHabilitados.put(Comando.EDITAR_GRADE, Boolean.valueOf(isHabilitarComando(Comando.EDITAR_GRADE)));
/* 166 */     comandosHabilitados.put(Comando.REDEFINIR_GRADE, Boolean.valueOf(isHabilitarComando(Comando.REDEFINIR_GRADE)));
/* 167 */     comandosHabilitados.put(Comando.GRADE_CENTRO_EXERCICIO, Boolean.valueOf(isHabilitarComando(Comando.GRADE_CENTRO_EXERCICIO)));
/*     */     
/* 169 */     return comandosHabilitados;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaGrade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */