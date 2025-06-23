/*     */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.JanelaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.JanelaGeografica;
/*     */ import ipqm.gsd.componentes.nucleo.objeto_visual.ObjetoVisual;
/*     */ import ipqm.gsd.hidra.ihm.controle.Controlador;
/*     */ import ipqm.gsd.hidra.ihm.dialogos.AcaoDialogo;
/*     */ import ipqm.gsd.hidra.ihm.dialogos.selecaoMultipla.DialogoSelecaoMultipla;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasEspacial;
/*     */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*     */ import ipqm.gsd.hidra.ihm.objetos_visuais.objetos_graficos.ObjetoGrafico;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Point;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javafx.scene.Cursor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class TarefaSelecao<E extends CanvasEspacial>
/*     */   extends Tarefa<E>
/*     */ {
/*     */   public enum EstadoInteracao
/*     */   {
/*  34 */     NAO_INTERAGINDO,
/*  35 */     BOTAO_PRESSIONADO,
/*  36 */     BOTAO_ARRASTADO,
/*  37 */     BOTAO_SOLTO;
/*     */   }
/*  39 */   protected List<ObjetoVisual> selecao0 = new ArrayList<>(); protected boolean transformando;
/*     */   private EstadoInteracao estadoInteracao;
/*     */   protected CoordenadaCartesiana p0;
/*     */   protected CoordenadaCartesiana p1;
/*  43 */   private final List<ObjetoVisual> listaObjetosMarcados = new ArrayList<>();
/*     */   private Cursor cursor;
/*     */   
/*     */   public TarefaSelecao(E canvasEspacial) {
/*  47 */     super(canvasEspacial);
/*     */   }
/*     */ 
/*     */   
/*     */   public void inicializa() {
/*  52 */     this.cursor = getCanvasEspacial().getCursor();
/*  53 */     getCanvasEspacial().setCursor(Cursor.DEFAULT);
/*     */   }
/*     */ 
/*     */   
/*     */   public void finaliza() {
/*  58 */     getCanvasEspacial().setCursor(this.cursor);
/*     */   }
/*     */   
/*     */   public void setEstadoInteracao(EstadoInteracao estadoInteracao) {
/*  62 */     this.estadoInteracao = estadoInteracao;
/*     */   }
/*     */   
/*     */   public EstadoInteracao getEstadoInteracao() {
/*  66 */     return this.estadoInteracao;
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseMovido(CoordenadaCartesiana p, EstadoTeclado e) {
/*  71 */     setEstadoInteracao(EstadoInteracao.NAO_INTERAGINDO);
/*     */   }
/*     */   
/*     */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/*     */     double tolerancia;
/*  76 */     setEstadoInteracao(EstadoInteracao.BOTAO_PRESSIONADO);
/*  77 */     this.p0 = this.p1 = p;
/*  78 */     this.transformando = false;
/*     */ 
/*     */ 
/*     */     
/*  82 */     if (getCanvasEspacial().getEscalaMN() > 48.0D) {
/*  83 */       tolerancia = getCanvasEspacial().distanciaInv(20);
/*     */     } else {
/*  85 */       tolerancia = getCanvasEspacial().distanciaInv(16);
/*     */     } 
/*     */     
/*  88 */     this.selecao0 = testaSelecao(p, tolerancia);
/*     */     
/*  90 */     if (!this.listaObjetosMarcados.isEmpty() && 
/*  91 */       !e.isShiftDown()) {
/*  92 */       if (this.selecao0.stream().anyMatch(ov -> this.listaObjetosMarcados.contains(ov))) {
/*  93 */         Iterator<ObjetoVisual> it = this.selecao0.iterator();
/*     */         
/*  95 */         while (it.hasNext()) {
/*  96 */           ObjetoVisual next = it.next();
/*  97 */           if (!this.listaObjetosMarcados.contains(next)) {
/*  98 */             it.remove();
/*     */           }
/*     */         } 
/*     */       } else {
/* 102 */         desmarcaTudo();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/* 107 */     desenha();
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Arrastado(CoordenadaCartesiana cart, EstadoTeclado e) {
/* 112 */     if (getEstadoInteracao() == EstadoInteracao.BOTAO_PRESSIONADO) {
/* 113 */       if (this.selecao0.isEmpty()) {
/* 114 */         this.p1 = cart;
/* 115 */         desenha();
/* 116 */         setEstadoInteracao(EstadoInteracao.BOTAO_ARRASTADO);
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 122 */       boolean clicouEmMarcado = false;
/* 123 */       for (ObjetoVisual ov : this.selecao0) {
/* 124 */         if (isMarcado(ov)) {
/* 125 */           clicouEmMarcado = true;
/*     */           break;
/*     */         } 
/*     */       } 
/* 129 */       if (!clicouEmMarcado) {
/* 130 */         if (!e.isShiftDown()) {
/* 131 */           setListaMarcados(this.selecao0);
/*     */         } else {
/* 133 */           for (ObjetoVisual ov : this.selecao0) {
/* 134 */             if (!isMarcado(ov)) {
/* 135 */               marcaObjeto(ov); continue;
/*     */             } 
/* 137 */             desmarcaObjeto(ov);
/*     */           } 
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 143 */       this.transformando = true;
/*     */     } 
/* 145 */     setEstadoInteracao(EstadoInteracao.BOTAO_ARRASTADO);
/*     */     
/* 147 */     if (cart != null && this.p1 != null && 
/* 148 */       !this.listaObjetosMarcados.isEmpty()) {
/* 149 */       double dX = cart.getX() - this.p1.getX();
/* 150 */       double dY = cart.getY() - this.p1.getY();
/* 151 */       transformarObjetos(dX, dY);
/*     */     } 
/*     */ 
/*     */     
/* 155 */     this.p1 = cart;
/*     */     
/* 157 */     desenha();
/* 158 */     if (this.transformando && !this.selecao0.isEmpty() && getCanvasEspacial() instanceof CanvasCenarioTatico) {
/* 159 */       ((CanvasCenarioTatico)getCanvasEspacial()).atualizarCamadaTatica();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void botao1Solto(final CoordenadaCartesiana p, EstadoTeclado e) {
/* 165 */     if (getEstadoInteracao() == EstadoInteracao.BOTAO_PRESSIONADO) {
/* 166 */       if (!this.selecao0.isEmpty()) {
/* 167 */         if (!e.isShiftDown()) {
/* 168 */           desmarcaTudo();
/*     */         }
/* 170 */         if (this.selecao0.size() > 1) {
/*     */           
/* 172 */           List<Object> listaNomes = new ArrayList(this.selecao0.size());
/* 173 */           for (ObjetoVisual ov : this.selecao0) {
/* 174 */             listaNomes.add(ov);
/*     */           }
/*     */ 
/*     */           
/* 178 */           new DialogoSelecaoMultipla("Selecione um objeto", "", getCanvasEspacial().getControlador(), listaNomes, false)
/*     */             {
/*     */               public void acao(AcaoDialogo acao, Object itemSelecionado)
/*     */               {
/* 182 */                 if (acao == AcaoDialogo.OK) {
/* 183 */                   TarefaSelecao.this.marcaObjeto((ObjetoVisual)itemSelecionado);
/* 184 */                   TarefaSelecao.this.setEstadoInteracao(TarefaSelecao.EstadoInteracao.BOTAO_SOLTO);
/* 185 */                   TarefaSelecao.this.botao1Solto(p, new EstadoTeclado());
/*     */                 } 
/*     */               }
/*     */             };
/*     */           return;
/*     */         } 
/* 191 */         if (this.selecao0.isEmpty()) {
/* 192 */           desmarcaTudo();
/* 193 */           this.p0 = this.p1 = null;
/* 194 */           desenha();
/*     */           return;
/*     */         } 
/* 197 */         for (ObjetoVisual obj : this.selecao0) {
/* 198 */           if (!isMarcado(obj)) {
/*     */             
/* 200 */             marcaObjeto(obj); continue;
/*     */           } 
/* 202 */           desmarcaObjeto(obj);
/*     */         }
/*     */       
/*     */       } 
/* 206 */     } else if (getEstadoInteracao() == EstadoInteracao.BOTAO_ARRASTADO && 
/* 207 */       this.selecao0.isEmpty()) {
/* 208 */       if (!e.isShiftDown()) {
/* 209 */         desmarcaTudo();
/*     */       }
/* 211 */       this.p1 = p;
/* 212 */       if (this.p0 != null && this.p1 != null) {
/*     */ 
/*     */         
/* 215 */         JanelaCartesiana janela = new JanelaCartesiana(new CoordenadaCartesiana(Math.min(this.p0.getX(), this.p1.getX()), Math.min(this.p0.getY(), this.p1.getY())), new CoordenadaCartesiana(Math.max(this.p0.getX(), this.p1.getX()), Math.max(this.p0.getY(), this.p1.getY())));
/* 216 */         List<ObjetoVisual> selecao = testaSelecao(janela);
/* 217 */         if (!selecao.isEmpty()) {
/* 218 */           for (ObjetoVisual obj : selecao) {
/* 219 */             if (!isMarcado(obj)) {
/* 220 */               marcaObjeto(obj); continue;
/* 221 */             }  if (isMarcado(obj) && e.isShiftDown()) {
/* 222 */               desmarcaObjeto(obj);
/*     */             }
/*     */           } 
/*     */         }
/*     */       } 
/* 227 */       this.p0 = this.p1 = null;
/*     */     } 
/*     */     
/* 230 */     setEstadoInteracao(EstadoInteracao.BOTAO_SOLTO);
/* 231 */     if (this.transformando) {
/* 232 */       finalizaTransformacao(e);
/*     */     }
/* 234 */     desenha();
/* 235 */     this.p0 = this.p1 = null;
/* 236 */     if (getCanvasEspacial() instanceof CanvasCenarioTatico) {
/* 237 */       ((CanvasCenarioTatico)getCanvasEspacial()).atualizarCamadaTatica();
/*     */     }
/*     */   }
/*     */   
/*     */   public synchronized void marcaObjeto(ObjetoVisual obj) {
/* 242 */     if (!isMarcado(obj))
/*     */     {
/* 244 */       this.listaObjetosMarcados.add(obj);
/*     */     }
/*     */   }
/*     */   
/*     */   public synchronized void desmarcaObjeto(ObjetoVisual obj) {
/* 249 */     if (this.listaObjetosMarcados.contains(obj)) {
/* 250 */       this.listaObjetosMarcados.remove(obj);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void desmarcaTudo() {
/* 256 */     this.listaObjetosMarcados.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized boolean isMarcado(ObjetoVisual obj) {
/* 261 */     if (this.listaObjetosMarcados.isEmpty()) {
/* 262 */       return false;
/*     */     }
/*     */     
/* 265 */     if (this.listaObjetosMarcados.contains(obj)) {
/* 266 */       return true;
/*     */     }
/* 268 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized List<ObjetoVisual> ordemMarcacao() {
/* 278 */     return this.listaObjetosMarcados;
/*     */   }
/*     */   
/*     */   public List<ObjetoVisual> getListaObjetosMarcados() {
/* 282 */     return this.listaObjetosMarcados;
/*     */   }
/*     */   
/*     */   public synchronized void setListaMarcados(List<ObjetoVisual> listaMarcados) {
/* 286 */     this.listaObjetosMarcados.addAll(listaMarcados);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void finalizaTransformacao(EstadoTeclado e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void desenha() {
/* 298 */     CanvasCenarioTatico canvasCT = (CanvasCenarioTatico)getCanvasEspacial();
/* 299 */     canvasCT.getCamadaInfo().tornaTransparente();
/* 300 */     Graphics2D g2d = canvasCT.getCamadaInfo().getImagem().createGraphics();
/* 301 */     String msg = null;
/*     */     
/* 303 */     if (this.p0 != null && this.p1 != null && !this.transformando) {
/* 304 */       CoordenadaRaster p0Raster = canvasCT.projeta(this.p0);
/* 305 */       CoordenadaRaster p1Raster = canvasCT.projeta(this.p1);
/* 306 */       CoordenadaRaster pMinRaster = new CoordenadaRaster(Math.min(p0Raster.getX(), p1Raster.getX()), Math.min(p0Raster.getY(), p1Raster.getY()));
/* 307 */       CoordenadaRaster pMaxRaster = new CoordenadaRaster(Math.max(p0Raster.getX(), p1Raster.getX()), Math.max(p0Raster.getY(), p1Raster.getY()));
/*     */       
/* 309 */       int largura = pMaxRaster.getX() - pMinRaster.getX() + 1;
/* 310 */       int altura = pMaxRaster.getY() - pMinRaster.getY() + 1;
/*     */       
/* 312 */       if (this.p0 != null && this.p1 != null) {
/* 313 */         JanelaGeografica janela = getJanela(this.p0, this.p1);
/* 314 */         msg = String.format("%s x %.1f MN x %.1f MN", new Object[] { janela.toString(), Double.valueOf(janela.getLargura()), Double.valueOf(janela.getAltura()) });
/*     */       } 
/*     */       
/* 317 */       if (largura > 1 || altura > 1) {
/* 318 */         g2d.setStroke(new BasicStroke(3.0F));
/* 319 */         g2d.setColor(Color.white);
/* 320 */         g2d.drawRect(pMinRaster.getX(), pMinRaster.getY(), largura, altura);
/*     */       } 
/*     */     } 
/* 323 */     if (getEstadoInteracao() == EstadoInteracao.BOTAO_SOLTO) {
/* 324 */       canvasCT.exibirStatus(g2d, null);
/*     */     } else {
/* 326 */       canvasCT.exibirStatus(g2d, msg);
/*     */     } 
/*     */     
/* 329 */     g2d.dispose();
/* 330 */     canvasCT.getCamadaInfo().geraImagemFX();
/*     */   }
/*     */   
/*     */   private JanelaGeografica getJanela(CoordenadaCartesiana p0, CoordenadaCartesiana p1) {
/* 334 */     CoordenadaCartesiana pMin = new CoordenadaCartesiana(Math.min(p0.getX(), p1.getX()), Math.min(p0.getY(), p1.getY()));
/* 335 */     CoordenadaCartesiana pMax = new CoordenadaCartesiana(Math.max(p0.getX(), p1.getX()), Math.max(p0.getY(), p1.getY()));
/* 336 */     CoordenadaGeografica pMinGeo = CoordenadaGeografica.converterCoordenadaCartesiana(pMin, 0.0D);
/* 337 */     CoordenadaGeografica pMaxGeo = CoordenadaGeografica.converterCoordenadaCartesiana(pMax, 0.0D);
/* 338 */     return new JanelaGeografica(pMinGeo, pMaxGeo);
/*     */   }
/*     */   
/*     */   protected Point getMouseClick(CoordenadaCartesiana p) {
/* 342 */     CoordenadaRaster r = getCanvasEspacial().projeta(p);
/* 343 */     return new Point(r.getX(), r.getY());
/*     */   }
/*     */   
/*     */   protected void transformarObjetos(double dX, double dY) {
/* 347 */     List<ObjetoVisual> lista = ordemMarcacao();
/* 348 */     for (ObjetoVisual ov : lista) {
/* 349 */       ((ObjetoGrafico)ov).transformarPosicao(dX, dY);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void habilitarComandos() {
/* 355 */     desabilitarTodosComandos();
/* 356 */     ordemMarcacao().stream().map(ObjetoGrafico.class::cast).forEach(objetoGrafico -> objetoGrafico.habilitarComandos(ordemMarcacao()));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void desabilitarTodosComandos() {
/* 362 */     obterComandosHabilitados().keySet().forEach(comando -> comando.setHabilitado(false));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void cancelarTarefa() {
/* 369 */     desmarcaTudo();
/* 370 */     this.p0 = this.p1 = null;
/* 371 */     desenha();
/*     */   }
/*     */   
/*     */   public abstract List<ObjetoVisual> testaSelecao(CoordenadaCartesiana paramCoordenadaCartesiana, double paramDouble);
/*     */   
/*     */   public abstract List<ObjetoVisual> testaSelecao(JanelaCartesiana paramJanelaCartesiana);
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaSelecao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */