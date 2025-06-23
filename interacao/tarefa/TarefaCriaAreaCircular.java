/*     */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.area_exercicio.AreaCircular;
/*     */ import ipqm.gsd.componentes.dominio.area_exercicio.AreaExercicio;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaPolar;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.PlanoNavegacao;
/*     */ import ipqm.gsd.componentes.dominio.teatro_operacao.CondicaoInicial;
/*     */ import ipqm.gsd.componentes.dominio.teatro_operacao.CondicaoInicialAreaExercicio;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.configuracao.ConfiguracaoIHM;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*     */ import ipqm.gsd.hidra.ihm.projetos.geral.cenas.controladores.ControladorCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.util.desenho.UtilJava2D;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javafx.scene.Cursor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TarefaCriaAreaCircular
/*     */   extends Tarefa2P<CanvasCenarioTatico>
/*     */ {
/*     */   private Cursor cursor;
/*     */   
/*     */   public TarefaCriaAreaCircular(CanvasCenarioTatico canvas) {
/*  39 */     super(canvas);
/*     */   }
/*     */ 
/*     */   
/*     */   public void inicializa() {
/*  44 */     this.cursor = getCanvasEspacial().getCursor();
/*  45 */     getCanvasEspacial().setCursor(Cursor.CROSSHAIR);
/*     */   }
/*     */ 
/*     */   
/*     */   public void finaliza() {
/*  50 */     getCanvasEspacial().setCursor(this.cursor);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  55 */     super.botao1Pressionado(p, e);
/*  56 */     getCanvasEspacial().setRedesenhoAutomatico(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/*  61 */     super.botao1Solto(p, e);
/*  62 */     getCanvasEspacial().getCamadaInfo().tornaTransparente();
/*  63 */     getCanvasEspacial().getCamadaInfo().geraImagemFX();
/*  64 */     getCanvasEspacial().atualizarCamadaTatica();
/*  65 */     getCanvasEspacial().setRedesenhoAutomatico(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void habilitarComandos() {
/*  70 */     setHabilitarComando(Comando.CRIAR_AREA_EXERCICIO, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<Comando, Boolean> obterComandosHabilitados() {
/*  75 */     Map<Comando, Boolean> comandosHabilitados = new HashMap<>();
/*     */     
/*  77 */     comandosHabilitados.put(Comando.CRIAR_AREA_EXERCICIO, Boolean.valueOf(isHabilitarComando(Comando.CRIAR_AREA_EXERCICIO)));
/*     */     
/*  79 */     return comandosHabilitados;
/*     */   }
/*     */ 
/*     */   
/*     */   public void desenha() {
/*  84 */     if (this.p1 == null && this.p2 == null) {
/*     */       return;
/*     */     }
/*     */     
/*  88 */     CanvasCenarioTatico canvasCT = getCanvasEspacial();
/*  89 */     canvasCT.getCamadaInfo().tornaTransparente();
/*  90 */     Graphics2D g2d = canvasCT.getCamadaInfo().getImagem().createGraphics();
/*     */     
/*  92 */     if (this.p1 != null && this.p2 != null) {
/*  93 */       String info, infoStatus; g2d.setStroke(new BasicStroke(3.0F));
/*  94 */       g2d.setColor(Color.white);
/*  95 */       CoordenadaRaster p1Raster = canvasCT.projeta(this.p1);
/*  96 */       CoordenadaRaster p2Raster = canvasCT.projeta(this.p2);
/*  97 */       int largura = Math.abs(p2Raster.getX() - p1Raster.getX());
/*  98 */       int altura = Math.abs(p2Raster.getY() - p1Raster.getY());
/*  99 */       int raio = (int)Math.sqrt((largura * largura + altura * altura));
/*     */       
/* 101 */       g2d.drawOval(p1Raster.getX() - raio, p1Raster.getY() - raio, 2 * raio, 2 * raio);
/*     */       
/* 103 */       Font font = new Font("Arial", 1, 12);
/* 104 */       g2d.setFont(font);
/* 105 */       g2d.setColor(Color.black);
/*     */       
/* 107 */       CoordenadaPolar cp = CoordenadaPolar.converterCoordenadaCartesiana(this.p1, this.p2);
/*     */       
/* 109 */       ConfiguracaoIHM.Unidades unidadeDistancia = Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoIHM().getUnidadeDistanciaPadrao();
/*     */       
/* 111 */       double distancia = cp.getDistancia(unidadeDistancia);
/*     */ 
/*     */       
/* 114 */       switch (unidadeDistancia) {
/*     */         case CRIA_AREA:
/* 116 */           info = String.format("%-6.2f MN", new Object[] { Double.valueOf(distancia) });
/* 117 */           infoStatus = "Raio: " + info;
/*     */           break;
/*     */         case null:
/* 120 */           info = String.format("%-5.1f jd", new Object[] { Double.valueOf(distancia) });
/* 121 */           infoStatus = "Raio: " + String.format("%-6.2f MN", new Object[] { Double.valueOf(cp.getDistancia(ConfiguracaoIHM.Unidades.MN)) }) + " (" + info + ")";
/*     */           break;
/*     */         default:
/* 124 */           info = String.format("%-6.2f km", new Object[] { Double.valueOf(distancia) });
/* 125 */           infoStatus = "Raio: " + String.format("%-6.2f MN", new Object[] { Double.valueOf(cp.getDistancia(ConfiguracaoIHM.Unidades.MN)) }) + " (" + info + ")";
/*     */           break;
/*     */       } 
/*     */       
/* 129 */       canvasCT.exibirStatus(infoStatus);
/* 130 */       UtilJava2D.escreveTexto(info, p2Raster.getX() + 15, p2Raster.getY() - 2, Color.black, Color.white, g2d);
/*     */     } 
/*     */     
/* 133 */     g2d.dispose();
/* 134 */     canvasCT.getCamadaInfo().geraImagemFX();
/* 135 */     canvasCT.atualizarCamadaTatica();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p) {}
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p1, CoordenadaCartesiana p2) {
/* 144 */     CoordenadaCartesiana centro = null;
/* 145 */     CoordenadaCartesiana pontosRaio = null;
/*     */     
/*     */     try {
/* 148 */       centro = (CoordenadaCartesiana)p1.clone();
/* 149 */       pontosRaio = (CoordenadaCartesiana)p2.clone();
/* 150 */     } catch (CloneNotSupportedException ex) {
/* 151 */       Log.gravarLogExcecao("Erro ao tentar criar a área. ", this, ex);
/*     */     } 
/*     */     
/* 154 */     double raio = CoordenadaCartesiana.calcularDistancia(centro, pontosRaio);
/* 155 */     AreaCircular area = AreaCircular.fabricar(centro, raio, gerarNome());
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
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 184 */       if (area.getCondicaoInicialArea() == null) {
/* 185 */         area.setCondicaoInicialArea(new HashSet());
/* 186 */         CondicaoInicialAreaExercicio condicaoArea = new CondicaoInicialAreaExercicio();
/* 187 */         area.getCondicaoInicialArea().add(condicaoArea);
/*     */       } 
/* 189 */       area.getCondicaoInicialArea().stream().forEach(condicaoArea -> {
/*     */             condicaoArea.setAreaExercicio((AreaExercicio)area);
/*     */             
/*     */             condicaoArea.setCondicaoInicial(CondicaoInicial.getCondicaoInicialLocal());
/*     */           });
/* 194 */       area.salvarBDMemoria(true);
/*     */       
/* 196 */       PlanoNavegacao pn = PlanoNavegacao.obterPlanoCorrente();
/* 197 */       pn.getListaAreaExercicio().add(area);
/* 198 */       pn.atualizarBD(true);
/* 199 */       pn.salvarMemoria(true);
/* 200 */     } catch (Exception ex) {
/* 201 */       Log.gravarLogExcecao("Erro ao tentar persistir a área. ", this, ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private String gerarNome() {
/* 206 */     int cont = 1;
/* 207 */     String nomeTemp = "Área Circular " + String.valueOf(cont);
/*     */     
/* 209 */     Set<String> listaNomes = new HashSet<>();
/*     */     
/* 211 */     List<AreaCircular> lista = CondicaoInicial.getCondicaoInicialLocal().listarAreaCircularAssociada();
/*     */     
/* 213 */     for (AreaCircular item : lista) {
/* 214 */       listaNomes.add(item.getNome());
/*     */     }
/*     */     
/* 217 */     while (listaNomes.contains(nomeTemp)) {
/* 218 */       cont++;
/* 219 */       nomeTemp = "Área Circular " + String.valueOf(cont);
/*     */     } 
/*     */     
/* 222 */     return nomeTemp;
/*     */   }
/*     */ 
/*     */   
/*     */   public void executarComando(Comando comando, CoordenadaCartesiana posicao) {
/* 227 */     if (isHabilitarComando(comando))
/* 228 */       switch (comando) {
/*     */         
/*     */         case CRIA_AREA:
/* 231 */           ((ControladorCenarioTatico)getCanvasEspacial().getControlador()).exibirPainelFormulario("projetos/geral/formularios/AreaCircularForm.fxml", null, "Criar Área Circular");
/*     */           break;
/*     */       }  
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaCriaAreaCircular.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */