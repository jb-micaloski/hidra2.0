/*     */ package ipqm.gsd.hidra.ihm.interacao;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.ObjetoTatico;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.JanelaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.JanelaGeografica;
/*     */ import ipqm.gsd.hidra.ihm.camadas.CamadaInfo;
/*     */ import ipqm.gsd.hidra.ihm.controle.Controlador;
/*     */ import ipqm.gsd.hidra.ihm.interacao.tarefa.Comando;
/*     */ import ipqm.gsd.hidra.ihm.interacao.tarefa.Tarefa;
/*     */ import ipqm.gsd.hidra.ihm.interacao.tarefa.TarefaSelecao;
/*     */ import java.util.EnumMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javafx.scene.Cursor;
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class CanvasEspacial<E extends Controlador>
/*     */   implements ModeloCanvas, ModeloEventos
/*     */ {
/*     */   protected TarefaSelecao<? extends CanvasEspacial> tarefaSelecaoObjetos;
/*     */   
/*     */   public enum Tarefas
/*     */   {
/*  28 */     NENHUMA,
/*  29 */     SELECAO_OBJETOS,
/*  30 */     MOVIMENTACAO_3D,
/*  31 */     PAN,
/*  32 */     CRIA_OBJETOS,
/*  33 */     GRADE,
/*  34 */     CRIA_ROTAS,
/*  35 */     COMPASSO,
/*  36 */     ZOOM,
/*     */     
/*  38 */     LINHA_MARCACAO,
/*  39 */     CRIA_PONTOS,
/*  40 */     CRIA_AREA_CIRCULAR,
/*  41 */     CRIA_FIGURA_GEOMETRICA,
/*  42 */     CRIA_VEICULO,
/*  43 */     DESENHO,
/*  44 */     ALOCAR_VEICULO,
/*  45 */     LINHA_FORMATURA,
/*  46 */     EBL,
/*  47 */     VRM,
/*  48 */     IPL,
/*  49 */     GZ,
/*  50 */     CRIA_PONTOSMANUAL,
/*  51 */     CRIA_PONTOSDATUM,
/*  52 */     CRIA_PONTOSPLASH,
/*  53 */     CRIA_SIMBOLOS,
/*  54 */     CRIA_TEXTOS,
/*  55 */     CRIA_PROFUNDIDADES,
/*  56 */     CRIA_AREA_POLIGONAL_CM,
/*  57 */     CRIA_AREA_CIRCULAR_CM,
/*  58 */     CRIA_PONTOSMAGE,
/*  59 */     ENQUADRAR_ROTA,
/*  60 */     CRIA_ELEMENTO_NAVEGACAO,
/*  61 */     MODO_VIEW;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  66 */   protected final Map<Tarefas, Tarefa> mapaTarefas = new EnumMap<>(Tarefas.class);
/*     */   protected Tarefas idTarefa;
/*     */   protected JanelaCartesiana janelaCart;
/*     */   protected JanelaGeografica janelaGeo;
/*     */   private double escalaMN;
/*     */   private Tarefa tarefa;
/*     */   protected CoordenadaCartesiana posicaoCursorUltimoClique;
/*     */   
/*     */   public abstract E getControlador();
/*     */   
/*     */   public void setTarefaSelecaoObjetos(TarefaSelecao<? extends CanvasEspacial> tarefa) {
/*  77 */     this.mapaTarefas.put(Tarefas.SELECAO_OBJETOS, tarefa);
/*  78 */     this.tarefaSelecaoObjetos = tarefa;
/*     */   }
/*     */   
/*     */   public void setIdTarefa(Tarefas tarefa) {
/*  82 */     setTarefa(this.mapaTarefas.get(tarefa));
/*  83 */     this.idTarefa = tarefa;
/*     */   }
/*     */   
/*     */   public Tarefas getIdTarefa() {
/*  87 */     return (this.idTarefa == null) ? Tarefas.NENHUMA : this.idTarefa;
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
/*     */   public Tarefa getTarefa(Tarefas idTtarefa) {
/* 103 */     return this.mapaTarefas.get(idTtarefa);
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
/*     */   public Map<Comando, Boolean> obterComandosHabilitados(Tarefas idTarefa) {
/* 130 */     Tarefa tarefa = getTarefa(idTarefa);
/* 131 */     Map<Comando, Boolean> comandosHabilitados = tarefa.obterComandosHabilitados();
/*     */     
/* 133 */     return comandosHabilitados;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void executarComandoTarefa(Tarefas idTarefa, Comando idComando) {
/* 144 */     CoordenadaCartesiana posicaoCursor = getPosicaoCursorUltimoClique();
/*     */ 
/*     */     
/* 147 */     Tarefa tarefa = getTarefa(idTarefa);
/*     */ 
/*     */     
/* 150 */     tarefa.executarComando(idComando, posicaoCursor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setJanela(CoordenadaGeografica centro, double escalaMN) {
/* 161 */     if (centro != null) {
/*     */       
/* 163 */       double dX, dY, aspecto = getLargura() / getAltura();
/* 164 */       CoordenadaCartesiana centroCart = CoordenadaCartesiana.converterCoordenadaGeografica(centro, 0.0D);
/*     */       
/* 166 */       if (aspecto > 1.0D) {
/* 167 */         dX = escalaMN * aspecto;
/* 168 */         dY = escalaMN;
/*     */       } else {
/* 170 */         dX = escalaMN;
/* 171 */         dY = escalaMN / aspecto;
/*     */       } 
/*     */       
/* 174 */       CoordenadaCartesiana p1Cart = CoordenadaCartesiana.converterDistanciaXY(centroCart, -dX, -dY);
/* 175 */       CoordenadaCartesiana p2Cart = CoordenadaCartesiana.converterDistanciaXY(centroCart, dX, dY);
/*     */       
/* 177 */       this.janelaGeo = new JanelaGeografica(CoordenadaGeografica.converterCoordenadaCartesiana(p1Cart, 0.0D), CoordenadaGeografica.converterCoordenadaCartesiana(p2Cart, 0.0D));
/* 178 */       this.janelaCart = JanelaCartesiana.converterJanelaGeografica(this.janelaGeo, 0.0D);
/* 179 */       this.escalaMN = escalaMN;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCentroJanela(CoordenadaGeografica centro) {
/* 189 */     setJanela(centro, this.escalaMN);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEscalaMN(double escalaMN) {
/* 198 */     setJanela(this.janelaGeo.getCentro(), escalaMN);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double getEscalaMN() {
/* 207 */     return this.escalaMN;
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
/*     */   public void atualizaJanela() {
/* 220 */     setJanela(this.janelaGeo.getCentro(), this.escalaMN);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JanelaGeografica getJanelaGeografica() {
/* 229 */     return this.janelaGeo;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JanelaCartesiana getJanelaCartesiana() {
/* 238 */     return this.janelaCart;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CoordenadaRaster projeta(CoordenadaCartesiana p) {
/* 248 */     double iX = (p.getX() - this.janelaCart.getMin().getX()) / this.janelaCart.getLargura();
/* 249 */     double iY = (this.janelaCart.getMax().getY() - p.getY()) / this.janelaCart.getAltura();
/* 250 */     return new CoordenadaRaster((int)(getLargura() * iX), (int)(getAltura() * iY));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CoordenadaCartesiana projetaInv(CoordenadaRaster p) {
/* 261 */     double cartesianoX = this.janelaCart.getMin().getX() + p.getX() / (getLargura() - 1.0D) * this.janelaCart.getLargura();
/* 262 */     double cartesianoY = this.janelaCart.getMax().getY() - p.getY() / (getAltura() - 1.0D) * this.janelaCart.getAltura();
/* 263 */     return new CoordenadaCartesiana(cartesianoX, cartesianoY);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int distancia(double distanciaCartesiana) {
/* 273 */     return (int)(distanciaCartesiana / this.janelaCart.getLargura() * getLargura() + 0.5D);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public double distanciaInv(int distanciaRaster) {
/* 283 */     return distanciaRaster / getLargura() * this.janelaCart.getLargura();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTarefa(Tarefa t) {
/* 292 */     if (this.tarefa != t) {
/* 293 */       if (this.tarefa != null) {
/* 294 */         this.tarefa.finaliza();
/*     */       }
/* 296 */       this.tarefa = t;
/* 297 */       if (this.tarefa != null) {
/* 298 */         this.tarefa.inicializa();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tarefa getTarefa() {
/* 309 */     return this.tarefa;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void setCursor(Cursor paramCursor);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract Cursor getCursor();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract List<ObjetoTatico> getObjetosTaticos();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CoordenadaCartesiana getPosicaoCursorUltimoClique() {
/* 331 */     return this.posicaoCursorUltimoClique;
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
/*     */   protected void setPosicaoCursorUltimoClique(CoordenadaCartesiana posicaoUltimoClique) {
/* 350 */     this.posicaoCursorUltimoClique = posicaoUltimoClique;
/*     */   }
/*     */   
/*     */   public abstract CamadaInfo getCamadaInfo();
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/CanvasEspacial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */