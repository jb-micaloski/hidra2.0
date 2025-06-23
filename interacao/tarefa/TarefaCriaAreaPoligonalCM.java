/*     */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.banco_dados.DAOAreaPoligonalCorrecaoManual;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.componentes.dominio.correcao_manual.AreaPoligonalCorrecaoManual;
/*     */ import ipqm.gsd.componentes.dominio.correcao_manual.VerticeCorrecaoManual;
/*     */ import ipqm.gsd.componentes.nucleo.Maquina;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.util.GenericComparator;
/*     */ import ipqm.gsd.componentes.nucleo.util.TipoOrdenacao;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*     */ import ipqm.gsd.hidra.ihm.projetos.geral.cenas.controladores.ControladorCenarioTatico;
/*     */ import ipqm.gsd.javafx.S52Color;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.GeneralPath;
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
/*     */ public class TarefaCriaAreaPoligonalCM
/*     */   extends TarefaNP<CanvasCenarioTatico>
/*     */ {
/*     */   private GeneralPath caminho;
/*     */   private Cursor cursor;
/*     */   private boolean botaoAberta = false;
/*     */   private AreaPoligonalCorrecaoManual area;
/*     */   
/*     */   public TarefaCriaAreaPoligonalCM(CanvasCenarioTatico canvas) {
/*  46 */     super(canvas);
/*     */   }
/*     */ 
/*     */   
/*     */   public void inicializa() {
/*  51 */     this.cursor = getCanvasEspacial().getCursor();
/*  52 */     getCanvasEspacial().setCursor(Cursor.CROSSHAIR);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void finaliza() {
/*  58 */     getCanvasEspacial().setCursor(this.cursor);
/*  59 */     getCanvasEspacial().getCamadaInfo().tornaTransparente();
/*  60 */     getCanvasEspacial().getCamadaInfo().geraImagemFX();
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  65 */     super.botao1Pressionado(p, e);
/*  66 */     getCanvasEspacial().setRedesenhoAutomatico(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/*  71 */     super.botao1Solto(p, e);
/*  72 */     getCanvasEspacial().setRedesenhoAutomatico(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void desenha() {
/*  77 */     CanvasCenarioTatico canvas = getCanvasEspacial();
/*  78 */     synchronized (canvas.getCamadaInfo()) {
/*  79 */       canvas.getCamadaInfo().tornaTransparente();
/*     */       
/*  81 */       if (this.vertices.isEmpty()) {
/*  82 */         getCanvasEspacial().getCamadaInfo().geraImagemFX();
/*     */         return;
/*     */       } 
/*  85 */       List<CoordenadaCartesiana> listaVertices = new ArrayList<>();
/*     */       
/*  87 */       for (CoordenadaCartesiana coordenada : this.vertices) {
/*     */         
/*     */         try {
/*  90 */           CoordenadaCartesiana coordenadaAux = (CoordenadaCartesiana)coordenada.clone();
/*  91 */           listaVertices.add(coordenadaAux);
/*  92 */         } catch (CloneNotSupportedException ex) {
/*  93 */           Log.gravarLogExcecao("Erro ao tentar desenhar linha poligonal. ", this, ex);
/*     */         } 
/*     */       } 
/*     */       
/*  97 */       canvas.setRedesenhoAutomatico(false);
/*     */       
/*  99 */       Graphics2D g2d = canvas.getCamadaInfo().getImagem().createGraphics();
/* 100 */       Color cor = S52Color.getAWT(S52Color.ColorType.S52_BLACK);
/* 101 */       this.caminho = new GeneralPath();
/*     */       
/* 103 */       CoordenadaRaster inicio = canvas.projeta(listaVertices.get(0));
/*     */       
/* 105 */       this.caminho.moveTo(inicio.getX(), inicio.getY());
/*     */       
/* 107 */       for (int i = 1; i < listaVertices.size(); i++) {
/* 108 */         CoordenadaRaster p = canvas.projeta(listaVertices.get(i));
/* 109 */         this.caminho.lineTo(p.getX(), p.getY());
/*     */       } 
/*     */ 
/*     */       
/* 113 */       CoordenadaRaster fim = (this.p != null) ? canvas.projeta(this.p) : null;
/*     */       
/* 115 */       if (fim != null) {
/* 116 */         this.caminho.lineTo(fim.getX(), fim.getY());
/*     */       }
/*     */ 
/*     */       
/* 120 */       g2d.setColor(cor);
/* 121 */       g2d.setStroke(new BasicStroke(3.0F));
/* 122 */       g2d.draw(this.caminho);
/*     */       
/* 124 */       g2d.dispose();
/* 125 */       canvas.getCamadaInfo().geraImagemFX();
/* 126 */       canvas.setRedesenhoAutomatico(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public GeneralPath getCaminho() {
/* 132 */     return this.caminho;
/*     */   }
/*     */   
/*     */   public boolean isFirstSameLast() {
/* 136 */     if (this.vertices.isEmpty()) {
/* 137 */       return false;
/*     */     }
/*     */     
/* 140 */     return ((CoordenadaCartesiana)this.vertices.get(0)).equals(this.vertices.get(this.vertices.size() - 1));
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
/*     */   public void executarComando(Comando comando, CoordenadaCartesiana posicao) {
/* 161 */     if (isHabilitarComando(comando)) {
/* 162 */       switch (comando) {
/*     */         case CRIA_AREA:
/* 164 */           this.botaoAberta = true;
/* 165 */           criaObjeto(this.vertices);
/* 166 */           this.vertices.clear();
/*     */           break;
/*     */         case CRIA_AREA_FECHADA:
/* 169 */           this.botaoAberta = false;
/* 170 */           criaObjeto(this.vertices);
/* 171 */           this.vertices.clear();
/*     */         case CANCELA_AREA:
/* 173 */           this.vertices.clear();
/*     */           break;
/*     */         case CRIAR_AREA_EXERCICIO:
/* 176 */           ((ControladorCenarioTatico)getCanvasEspacial().getControlador()).exibirPainelFormulario("projetos/geral/formularios/EditaAreaPoligonalForm.fxml", null, "Criar Área Poligonal");
/*     */           break;
/*     */       } 
/*     */       
/* 180 */       desenha();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void criaObjeto(List<CoordenadaCartesiana> coordVertices) {
/* 187 */     int numeroVertice = 1;
/*     */     
/* 189 */     this.area = new AreaPoligonalCorrecaoManual(Maquina.getMaquinaLocal(), gerarNome());
/*     */     
/* 191 */     for (CoordenadaCartesiana coordenada : coordVertices) {
/* 192 */       VerticeCorrecaoManual vertice = new VerticeCorrecaoManual(CoordenadaGeografica.converterCoordenadaCartesiana(coordenada, 0.0D), numeroVertice++, this.area);
/* 193 */       this.area.getListaVertices().add(vertice);
/*     */     } 
/*     */     
/* 196 */     GenericComparator.ordenarLista(new ArrayList(this.area.getListaVertices()), "ordem", TipoOrdenacao.ASC);
/*     */     
/*     */     try {
/* 199 */       this.area.setAberto(this.botaoAberta);
/* 200 */       dataCriacao();
/* 201 */       this.area.salvarBDMemoria(true);
/*     */       
/* 203 */       this.botaoAberta = false;
/* 204 */     } catch (ParseException ex) {
/* 205 */       Log.gravarLogExcecao("Erro ao tentar criar a área. ", this, ex);
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
/*     */   public void habilitarComandos() {
/* 219 */     if (this.vertices.size() == 2) {
/* 220 */       setHabilitarComando(Comando.CRIA_AREA, true);
/* 221 */       setHabilitarComando(Comando.CRIA_AREA_FECHADA, false);
/* 222 */       setHabilitarComando(Comando.CANCELA_AREA, true);
/* 223 */       setHabilitarComando(Comando.CRIAR_AREA_EXERCICIO, false);
/* 224 */     } else if (this.vertices.size() > 2) {
/*     */       
/* 226 */       setHabilitarComando(Comando.CRIA_AREA, true);
/* 227 */       setHabilitarComando(Comando.CRIA_AREA_FECHADA, true);
/* 228 */       setHabilitarComando(Comando.CANCELA_AREA, true);
/* 229 */       setHabilitarComando(Comando.CRIAR_AREA_EXERCICIO, false);
/*     */     } else {
/* 231 */       setHabilitarComando(Comando.CRIA_AREA, false);
/* 232 */       setHabilitarComando(Comando.CRIA_AREA_FECHADA, false);
/* 233 */       setHabilitarComando(Comando.CANCELA_AREA, false);
/* 234 */       if (this.vertices.isEmpty()) {
/* 235 */         setHabilitarComando(Comando.CRIAR_AREA_EXERCICIO, true);
/*     */       } else {
/* 237 */         setHabilitarComando(Comando.CRIAR_AREA_EXERCICIO, false);
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
/*     */   public Map<Comando, Boolean> obterComandosHabilitados() {
/* 264 */     Map<Comando, Boolean> comandosHabilitados = new HashMap<>();
/*     */     
/* 266 */     comandosHabilitados.put(Comando.CRIA_AREA, Boolean.valueOf(isHabilitarComando(Comando.CRIA_AREA)));
/* 267 */     comandosHabilitados.put(Comando.CRIA_AREA_FECHADA, Boolean.valueOf(isHabilitarComando(Comando.CRIA_AREA_FECHADA)));
/* 268 */     comandosHabilitados.put(Comando.CANCELA_AREA, Boolean.valueOf(isHabilitarComando(Comando.CANCELA_AREA)));
/* 269 */     comandosHabilitados.put(Comando.CRIAR_AREA_EXERCICIO, Boolean.valueOf(isHabilitarComando(Comando.CRIAR_AREA_EXERCICIO)));
/*     */     
/* 271 */     return comandosHabilitados;
/*     */   }
/*     */ 
/*     */   
/*     */   private String gerarNome() {
/* 276 */     int cont = 1;
/* 277 */     String nomeTemp = "Área Poligonal " + String.valueOf(cont);
/* 278 */     Set<String> listaNomes = new HashSet<>();
/* 279 */     List<AreaPoligonalCorrecaoManual> lista = new ArrayList<>((new DAOAreaPoligonalCorrecaoManual()).listarAreaPoligonalCM());
/* 280 */     for (AreaPoligonalCorrecaoManual item : lista) {
/* 281 */       listaNomes.add(item.getNome());
/*     */     }
/* 283 */     while (listaNomes.contains(nomeTemp)) {
/* 284 */       cont++;
/* 285 */       nomeTemp = "Área Poligonal " + String.valueOf(cont);
/*     */     } 
/* 287 */     return nomeTemp;
/*     */   }
/*     */   
/*     */   private void dataCriacao() throws ParseException {
/* 291 */     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY-HH:mm");
/* 292 */     String st = sdf.format(Long.valueOf(Mediador.getRelogio().getHorario()));
/* 293 */     stringToMilliseconds(st);
/* 294 */     this.area.setDataCriacao(stringToMilliseconds(st));
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
/* 305 */     if (dataHora == 0L) {
/* 306 */       return null;
/*     */     }
/* 308 */     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy-hh:mm");
/* 309 */     Date date = new Date(dataHora);
/* 310 */     return sdf.format(Long.valueOf(date.getTime()));
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
/* 324 */     if (dataHora == null || dataHora.equals("")) {
/* 325 */       return 0L;
/*     */     }
/* 327 */     SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy-hh:mm");
/* 328 */     String dateInString = dataHora;
/* 329 */     Date date = sdf.parse(dateInString);
/*     */     
/* 331 */     return date.getTime();
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaCriaAreaPoligonalCM.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */