/*     */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.area_exercicio.AreaExercicio;
/*     */ import ipqm.gsd.componentes.dominio.area_exercicio.AreaPoligonal;
/*     */ import ipqm.gsd.componentes.dominio.area_exercicio.Vertice;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.PlanoNavegacao;
/*     */ import ipqm.gsd.componentes.dominio.teatro_operacao.CondicaoInicial;
/*     */ import ipqm.gsd.componentes.dominio.teatro_operacao.CondicaoInicialAreaExercicio;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.util.GenericComparator;
/*     */ import ipqm.gsd.componentes.nucleo.util.TipoOrdenacao;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.util.ArrayList;
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
/*     */ public class TarefaCriaLinhaPoligonal
/*     */   extends TarefaNP<CanvasCenarioTatico>
/*     */ {
/*     */   private GeneralPath caminho;
/*     */   private Cursor cursor;
/*     */   private boolean botaoAberta = false;
/*     */   
/*     */   public TarefaCriaLinhaPoligonal(CanvasCenarioTatico canvas) {
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
/*     */   
/*     */   public void finaliza() {
/*  53 */     getCanvasEspacial().setCursor(this.cursor);
/*  54 */     getCanvasEspacial().getCamadaInfo().tornaTransparente();
/*  55 */     getCanvasEspacial().getCamadaInfo().geraImagemFX();
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  60 */     super.botao1Pressionado(p, e);
/*  61 */     getCanvasEspacial().setRedesenhoAutomatico(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/*  66 */     super.botao1Solto(p, e);
/*  67 */     getCanvasEspacial().setRedesenhoAutomatico(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void desenha() {
/*  72 */     CanvasCenarioTatico canvas = getCanvasEspacial();
/*  73 */     synchronized (canvas.getCamadaInfo()) {
/*  74 */       canvas.getCamadaInfo().tornaTransparente();
/*     */       
/*  76 */       if (this.vertices.isEmpty()) {
/*  77 */         getCanvasEspacial().getCamadaInfo().geraImagemFX();
/*     */         return;
/*     */       } 
/*  80 */       List<CoordenadaCartesiana> listaVertices = new ArrayList<>();
/*     */       
/*  82 */       for (CoordenadaCartesiana coordenada : this.vertices) {
/*     */         
/*     */         try {
/*  85 */           CoordenadaCartesiana coordenadaAux = (CoordenadaCartesiana)coordenada.clone();
/*  86 */           listaVertices.add(coordenadaAux);
/*  87 */         } catch (CloneNotSupportedException ex) {
/*  88 */           Log.gravarLogExcecao("Erro ao tentar desenhar linha poligonal. ", this, ex);
/*     */         } 
/*     */       } 
/*     */       
/*  92 */       canvas.setRedesenhoAutomatico(false);
/*     */       
/*  94 */       Graphics2D g2d = canvas.getCamadaInfo().getImagem().createGraphics();
/*  95 */       Color cor = Color.white;
/*  96 */       this.caminho = new GeneralPath();
/*     */       
/*  98 */       CoordenadaRaster inicio = canvas.projeta(listaVertices.get(0));
/*     */       
/* 100 */       this.caminho.moveTo(inicio.getX(), inicio.getY());
/*     */       
/* 102 */       for (int i = 1; i < listaVertices.size(); i++) {
/* 103 */         CoordenadaRaster p = canvas.projeta(listaVertices.get(i));
/* 104 */         this.caminho.lineTo(p.getX(), p.getY());
/*     */       } 
/*     */ 
/*     */       
/* 108 */       CoordenadaRaster fim = (this.p != null) ? canvas.projeta(this.p) : null;
/*     */       
/* 110 */       if (fim != null) {
/* 111 */         this.caminho.lineTo(fim.getX(), fim.getY());
/*     */       }
/*     */ 
/*     */       
/* 115 */       g2d.setColor(cor);
/* 116 */       g2d.setStroke(new BasicStroke(3.0F));
/* 117 */       g2d.draw(this.caminho);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 127 */       g2d.dispose();
/* 128 */       canvas.getCamadaInfo().geraImagemFX();
/* 129 */       canvas.setRedesenhoAutomatico(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public GeneralPath getCaminho() {
/* 135 */     return this.caminho;
/*     */   }
/*     */   
/*     */   public boolean isFirstSameLast() {
/* 139 */     if (this.vertices.isEmpty()) {
/* 140 */       return false;
/*     */     }
/*     */     
/* 143 */     return ((CoordenadaCartesiana)this.vertices.get(0)).equals(this.vertices.get(this.vertices.size() - 1));
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
/* 164 */     if (isHabilitarComando(comando)) {
/*     */       
/* 166 */       switch (comando) {
/*     */         case CRIA_AREA:
/* 168 */           this.botaoAberta = true;
/*     */           
/* 170 */           criaObjeto(this.vertices);
/* 171 */           this.vertices.clear();
/*     */           break;
/*     */         case CRIA_AREA_FECHADA:
/* 174 */           this.botaoAberta = false;
/*     */           
/* 176 */           criaObjeto(this.vertices);
/* 177 */           this.vertices.clear();
/*     */         case CANCELA_AREA:
/* 179 */           this.vertices.clear();
/*     */           break;
/*     */       } 
/*     */ 
/*     */       
/* 184 */       desenha();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void criaObjeto(List<CoordenadaCartesiana> coordVertices) {
/* 191 */     int numeroVertice = 1;
/*     */     
/* 193 */     AreaPoligonal area = new AreaPoligonal(null, gerarNome());
/*     */     
/* 195 */     for (CoordenadaCartesiana coordenada : coordVertices) {
/* 196 */       Vertice vertice = new Vertice(CoordenadaGeografica.converterCoordenadaCartesiana(coordenada, 0.0D), numeroVertice++, area);
/* 197 */       area.getListaVertices().add(vertice);
/*     */     } 
/*     */     
/* 200 */     GenericComparator.ordenarLista(new ArrayList(area.getListaVertices()), "ordem", TipoOrdenacao.ASC);
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
/* 228 */       area.setAberto(this.botaoAberta);
/*     */       
/* 230 */       if (area.getCondicaoInicialArea() == null) {
/* 231 */         area.setCondicaoInicialArea(new HashSet());
/* 232 */         CondicaoInicialAreaExercicio condicaoArea = new CondicaoInicialAreaExercicio();
/* 233 */         area.getCondicaoInicialArea().add(condicaoArea);
/*     */       } 
/* 235 */       area.getCondicaoInicialArea().stream().forEach(condicaoArea -> {
/*     */             condicaoArea.setAreaExercicio((AreaExercicio)area);
/*     */             
/*     */             condicaoArea.setCondicaoInicial(CondicaoInicial.getCondicaoInicialLocal());
/*     */           });
/* 240 */       area.salvarBDMemoria(true);
/*     */       
/* 242 */       PlanoNavegacao pn = PlanoNavegacao.obterPlanoCorrente();
/* 243 */       pn.getListaAreaExercicio().add(area);
/* 244 */       pn.atualizarBD(true);
/* 245 */       pn.salvarMemoria(true);
/*     */       
/* 247 */       this.botaoAberta = false;
/* 248 */     } catch (Exception ex) {
/* 249 */       Log.gravarLogExcecao("Erro ao tentar criar a área. ", this, ex);
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
/*     */   public void habilitarComandos() {
/* 277 */     if (this.vertices.size() == 2) {
/*     */       
/* 279 */       setHabilitarComando(Comando.CRIA_AREA, true);
/* 280 */       setHabilitarComando(Comando.CRIA_AREA_FECHADA, false);
/* 281 */       setHabilitarComando(Comando.CANCELA_AREA, true);
/* 282 */       setHabilitarComando(Comando.CRIAR_AREA_EXERCICIO, false);
/* 283 */     } else if (this.vertices.size() > 2) {
/*     */       
/* 285 */       setHabilitarComando(Comando.CRIA_AREA, true);
/* 286 */       setHabilitarComando(Comando.CRIA_AREA_FECHADA, true);
/* 287 */       setHabilitarComando(Comando.CANCELA_AREA, true);
/* 288 */       setHabilitarComando(Comando.CRIAR_AREA_EXERCICIO, false);
/*     */     } else {
/* 290 */       setHabilitarComando(Comando.CRIA_AREA, false);
/* 291 */       setHabilitarComando(Comando.CRIA_AREA_FECHADA, false);
/* 292 */       setHabilitarComando(Comando.CANCELA_AREA, false);
/* 293 */       if (this.vertices.isEmpty()) {
/* 294 */         setHabilitarComando(Comando.CRIAR_AREA_EXERCICIO, true);
/*     */       } else {
/* 296 */         setHabilitarComando(Comando.CRIAR_AREA_EXERCICIO, false);
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
/* 323 */     Map<Comando, Boolean> comandosHabilitados = new HashMap<>();
/*     */     
/* 325 */     comandosHabilitados.put(Comando.CRIA_AREA, Boolean.valueOf(isHabilitarComando(Comando.CRIA_AREA)));
/* 326 */     comandosHabilitados.put(Comando.CRIA_AREA_FECHADA, Boolean.valueOf(isHabilitarComando(Comando.CRIA_AREA_FECHADA)));
/* 327 */     comandosHabilitados.put(Comando.CANCELA_AREA, Boolean.valueOf(isHabilitarComando(Comando.CANCELA_AREA)));
/*     */     
/* 329 */     comandosHabilitados.put(Comando.CRIAR_AREA_EXERCICIO, Boolean.valueOf(isHabilitarComando(Comando.CRIAR_AREA_EXERCICIO)));
/*     */     
/* 331 */     return comandosHabilitados;
/*     */   }
/*     */ 
/*     */   
/*     */   private String gerarNome() {
/* 336 */     int cont = 1;
/* 337 */     String nomeTemp = "Área Poligonal " + String.valueOf(cont);
/*     */     
/* 339 */     Set<String> listaNomes = new HashSet<>();
/*     */     
/* 341 */     List<AreaPoligonal> lista = CondicaoInicial.getCondicaoInicialLocal().listarAreaPoligonalAssociada();
/*     */     
/* 343 */     for (AreaPoligonal item : lista) {
/* 344 */       listaNomes.add(item.getNome());
/*     */     }
/*     */     
/* 347 */     while (listaNomes.contains(nomeTemp)) {
/* 348 */       cont++;
/* 349 */       nomeTemp = "Área Poligonal " + String.valueOf(cont);
/*     */     } 
/*     */     
/* 352 */     return nomeTemp;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaCriaLinhaPoligonal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */