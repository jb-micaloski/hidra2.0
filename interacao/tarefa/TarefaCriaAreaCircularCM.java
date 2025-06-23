/*     */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.banco_dados.DAOAreaCircularCorrecaoManual;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaPolar;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.componentes.dominio.correcao_manual.AreaCircularCorrecaoManual;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*     */ import ipqm.gsd.hidra.ihm.projetos.geral.cenas.controladores.ControladorCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.util.desenho.UtilJava2D;
/*     */ import ipqm.gsd.javafx.S52Color;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
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
/*     */ public class TarefaCriaAreaCircularCM
/*     */   extends Tarefa2P<CanvasCenarioTatico>
/*     */ {
/*     */   private Cursor cursor;
/*     */   private AreaCircularCorrecaoManual area;
/*     */   
/*     */   public TarefaCriaAreaCircularCM(CanvasCenarioTatico canvas) {
/*  41 */     super(canvas);
/*     */   }
/*     */ 
/*     */   
/*     */   public void inicializa() {
/*  46 */     this.cursor = getCanvasEspacial().getCursor();
/*  47 */     getCanvasEspacial().setCursor(Cursor.CROSSHAIR);
/*     */   }
/*     */ 
/*     */   
/*     */   public void finaliza() {
/*  52 */     getCanvasEspacial().setCursor(this.cursor);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  57 */     super.botao1Pressionado(p, e);
/*  58 */     getCanvasEspacial().setRedesenhoAutomatico(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/*  63 */     super.botao1Solto(p, e);
/*  64 */     getCanvasEspacial().getCamadaInfo().tornaTransparente();
/*  65 */     getCanvasEspacial().getCamadaInfo().geraImagemFX();
/*  66 */     getCanvasEspacial().atualizarCamadaTatica();
/*  67 */     getCanvasEspacial().setRedesenhoAutomatico(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void habilitarComandos() {
/*  72 */     setHabilitarComando(Comando.CRIAR_AREA_EXERCICIO, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<Comando, Boolean> obterComandosHabilitados() {
/*  77 */     Map<Comando, Boolean> comandosHabilitados = new HashMap<>();
/*     */     
/*  79 */     comandosHabilitados.put(Comando.CRIAR_AREA_EXERCICIO, Boolean.valueOf(isHabilitarComando(Comando.CRIAR_AREA_EXERCICIO)));
/*     */     
/*  81 */     return comandosHabilitados;
/*     */   }
/*     */ 
/*     */   
/*     */   public void desenha() {
/*  86 */     if (this.p1 == null && this.p2 == null) {
/*     */       return;
/*     */     }
/*     */     
/*  90 */     CanvasCenarioTatico canvasCT = getCanvasEspacial();
/*  91 */     canvasCT.getCamadaInfo().tornaTransparente();
/*  92 */     Graphics2D g2d = canvasCT.getCamadaInfo().getImagem().createGraphics();
/*     */     
/*  94 */     if (this.p1 != null && this.p2 != null) {
/*  95 */       g2d.setStroke(new BasicStroke(3.0F));
/*  96 */       g2d.setColor(S52Color.getAWT(S52Color.ColorType.S52_BLACK));
/*  97 */       CoordenadaRaster p1Raster = canvasCT.projeta(this.p1);
/*  98 */       CoordenadaRaster p2Raster = canvasCT.projeta(this.p2);
/*  99 */       int largura = Math.abs(p2Raster.getX() - p1Raster.getX());
/* 100 */       int altura = Math.abs(p2Raster.getY() - p1Raster.getY());
/* 101 */       int raio = (int)Math.sqrt((largura * largura + altura * altura));
/*     */       
/* 103 */       g2d.drawOval(p1Raster.getX() - raio, p1Raster.getY() - raio, 2 * raio, 2 * raio);
/*     */ 
/*     */       
/* 106 */       CoordenadaPolar cp = CoordenadaPolar.converterCoordenadaCartesiana(this.p1, this.p2);
/*     */       
/* 108 */       double distanciaMN = cp.getDistancia();
/* 109 */       double distanciaJd = distanciaMN * 2025.3718285214347D;
/*     */       
/* 111 */       String info1 = String.format("%-6.2f MN", new Object[] { Double.valueOf(distanciaMN) });
/* 112 */       Font font = new Font("Arial", 1, 12);
/* 113 */       FontMetrics fm = g2d.getFontMetrics(font);
/* 114 */       g2d.setFont(font);
/* 115 */       g2d.setColor(S52Color.getAWT(S52Color.ColorType.S52_BLACK));
/* 116 */       UtilJava2D.escreveTexto(info1, p2Raster.getX() + 15, p2Raster.getY() - 2, S52Color.getAWT(S52Color.ColorType.S52_BLACK), S52Color.getAWT(S52Color.ColorType.S52_WHITE), g2d);
/*     */       
/* 118 */       String info3 = String.format("%-5.1f jardas", new Object[] { Double.valueOf(distanciaJd) });
/* 119 */       String infoStatus = "Raio: " + info1 + " (" + info3 + ")";
/* 120 */       canvasCT.exibirStatus(infoStatus);
/*     */     } 
/*     */ 
/*     */     
/* 124 */     g2d.dispose();
/* 125 */     canvasCT.getCamadaInfo().geraImagemFX();
/* 126 */     canvasCT.atualizarCamadaTatica();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p) {}
/*     */ 
/*     */   
/*     */   public void criaObjeto(CoordenadaCartesiana p1, CoordenadaCartesiana p2) {
/* 135 */     CoordenadaCartesiana centro = null;
/* 136 */     CoordenadaCartesiana pontosRaio = null;
/*     */     try {
/* 138 */       centro = (CoordenadaCartesiana)p1.clone();
/* 139 */       pontosRaio = (CoordenadaCartesiana)p2.clone();
/* 140 */     } catch (CloneNotSupportedException ex) {
/* 141 */       Log.gravarLogExcecao("Erro ao tentar criar a área. ", this, ex);
/*     */     } 
/*     */     
/* 144 */     double raio = CoordenadaCartesiana.calcularDistancia(centro, pontosRaio);
/*     */     
/*     */     try {
/* 147 */       this.area = AreaCircularCorrecaoManual.fabricar(centro, raio, gerarNome());
/* 148 */       dataCriacao();
/* 149 */       this.area.salvarBDMemoria(true);
/* 150 */     } catch (Exception ex) {
/* 151 */       Log.gravarLogExcecao("Erro ao criar área na carta", this, ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private String gerarNome() {
/* 156 */     int cont = 1;
/* 157 */     String nomeTemp = "Área Circular " + String.valueOf(cont);
/* 158 */     Set<String> listaNomes = new HashSet<>();
/* 159 */     List<AreaCircularCorrecaoManual> lista = new ArrayList<>((new DAOAreaCircularCorrecaoManual()).listarAreaCircularCM());
/* 160 */     for (AreaCircularCorrecaoManual item : lista) {
/* 161 */       listaNomes.add(item.getNome());
/*     */     }
/* 163 */     while (listaNomes.contains(nomeTemp)) {
/* 164 */       cont++;
/* 165 */       nomeTemp = "Área Circular " + String.valueOf(cont);
/*     */     } 
/* 167 */     return nomeTemp;
/*     */   }
/*     */   
/*     */   private void dataCriacao() throws ParseException {
/* 171 */     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY-HH:mm");
/* 172 */     String st = sdf.format(Long.valueOf(Mediador.getRelogio().getHorario()));
/* 173 */     stringToMilliseconds(st);
/* 174 */     this.area.setDataCriacao(stringToMilliseconds(st));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String millisecondsToString(long dataHora) {
/* 185 */     if (dataHora == 0L) {
/* 186 */       return null;
/*     */     }
/* 188 */     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy-hh:mm");
/* 189 */     Date date = new Date(dataHora);
/* 190 */     return sdf.format(Long.valueOf(date.getTime()));
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
/*     */   public long stringToMilliseconds(String dataHora) throws ParseException {
/* 204 */     if (dataHora == null || dataHora.equals("")) {
/* 205 */       return 0L;
/*     */     }
/* 207 */     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy-hh:mm");
/* 208 */     String dateInString = dataHora;
/* 209 */     Date date = sdf.parse(dateInString);
/*     */     
/* 211 */     return date.getTime();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void executarComando(Comando comando, CoordenadaCartesiana posicao) {
/* 217 */     if (isHabilitarComando(comando)) {
/* 218 */       switch (comando) {
/*     */         case CRIAR_AREA_EXERCICIO:
/* 220 */           ((ControladorCenarioTatico)getCanvasEspacial().getControlador()).exibirPainelFormulario("projetos/geral/formularios/EditaAreaCircularForm.fxml", null, "Criar Área Circular");
/*     */           break;
/*     */       } 
/*     */       
/* 224 */       desenha();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaCriaAreaCircularCM.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */