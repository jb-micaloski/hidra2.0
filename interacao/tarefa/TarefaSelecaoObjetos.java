/*      */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*      */ 
/*      */ import ipqm.gsd.componentes.dominio.ObjetoTatico;
/*      */ import ipqm.gsd.componentes.dominio.acompanhamentos.Acompanhamento;
/*      */ import ipqm.gsd.componentes.dominio.acompanhamentos.AcompanhamentoLinhaMarcacao;
/*      */ import ipqm.gsd.componentes.dominio.acompanhamentos.AcompanhamentoLinhaMarcacaoManual;
/*      */ import ipqm.gsd.componentes.dominio.area_exercicio.AreaExercicio;
/*      */ import ipqm.gsd.componentes.dominio.calculos_taticos.PMA;
/*      */ import ipqm.gsd.componentes.dominio.cinematica.Cinematica;
/*      */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*      */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*      */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*      */ import ipqm.gsd.componentes.dominio.cinematica.ObjetoCinematica;
/*      */ import ipqm.gsd.componentes.dominio.contexto.ContextoTatico;
/*      */ import ipqm.gsd.componentes.dominio.correcao_manual.AreaCircularCorrecaoManual;
/*      */ import ipqm.gsd.componentes.dominio.entidades.Veiculo;
/*      */ import ipqm.gsd.componentes.dominio.navegacao.GrupoSetor;
/*      */ import ipqm.gsd.componentes.dominio.navegacao.JanelaCartesiana;
/*      */ import ipqm.gsd.componentes.dominio.navegacao.JanelaGeografica;
/*      */ import ipqm.gsd.componentes.dominio.navegacao.Pernada;
/*      */ import ipqm.gsd.componentes.dominio.navegacao.PlanoNavegacao;
/*      */ import ipqm.gsd.componentes.dominio.navegacao.Rota;
/*      */ import ipqm.gsd.componentes.dominio.navegacao.WayPoint;
/*      */ import ipqm.gsd.componentes.nucleo.Maquina;
/*      */ import ipqm.gsd.componentes.nucleo.Mediador;
/*      */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*      */ import ipqm.gsd.componentes.nucleo.gestao.GestorObjetos;
/*      */ import ipqm.gsd.componentes.nucleo.log.Log;
/*      */ import ipqm.gsd.componentes.nucleo.logon.perfis.PerfilUsuario;
/*      */ import ipqm.gsd.componentes.nucleo.notificador.Notificador;
/*      */ import ipqm.gsd.componentes.nucleo.objeto_visual.ObjetoVisual;
/*      */ import ipqm.gsd.componentes.nucleo.util.depuracao.Debug;
/*      */ import ipqm.gsd.hidra.ihm.camadas.filtros.FiltroObjetoTatico;
/*      */ import ipqm.gsd.hidra.ihm.controle.Controlador;
/*      */ import ipqm.gsd.hidra.ihm.dialogos.AcaoDialogo;
/*      */ import ipqm.gsd.hidra.ihm.dialogos.mapa.rota.DialogoMapaEditaRota;
/*      */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*      */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*      */ import ipqm.gsd.hidra.ihm.objetos_visuais.objetos_graficos.ObjetoGrafico;
/*      */ import ipqm.gsd.hidra.ihm.objetos_visuais.objetos_graficos.ObjetoGraficoAreaCircularCorrecaoManual;
/*      */ import ipqm.gsd.hidra.ihm.objetos_visuais.objetos_graficos.ObjetoGraficoVeiculo;
/*      */ import ipqm.gsd.hidra.ihm.objetos_visuais.objetos_textuais.ObjetoTextual;
/*      */ import ipqm.gsd.hidra.ihm.projetos.cisne.cenas.controladores.ControladorCenarioTaticoCisne;
/*      */ import ipqm.gsd.hidra.ihm.projetos.geral.cenas.controladores.ControladorCenarioTatico;
/*      */ import ipqm.gsd.hidra.ihm.projetos.geral.formularios.controladores.ControladorEtanaApt;
/*      */ import ipqm.gsd.hidra.ihm.projetos.geral.formularios.controladores.ControladorManobraInterceptacaoForm;
/*      */ import ipqm.gsd.hidra.ihm.projetos.geral.formularios.controladores.ControladorManobraPassarSafoForm;
/*      */ import ipqm.gsd.hidra.ihm.projetos.geral.formularios.controladores.ControladorManobraTempoForm;
/*      */ import ipqm.gsd.hidra.ihm.projetos.geral.formularios.controladores.ControladorManobraVelocForm;
/*      */ import ipqm.gsd.hidra.ihm.projetos.instrutor.formularios.controladores.ControladorEngajamentoInstrutorForm;
/*      */ import ipqm.gsd.hidra.ihm.simuladores.SimuladorVideoBrutoFX;
/*      */ import ipqm.gsd.javafx.widget.route.Waypoint;
/*      */ import java.awt.AWTException;
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Point;
/*      */ import java.awt.Robot;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import javafx.scene.Cursor;
/*      */ import javafx.scene.control.TextInputDialog;
/*      */ import javafx.scene.input.KeyCode;
/*      */ import javafx.scene.input.KeyEvent;
/*      */ import javafx.scene.layout.Region;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class TarefaSelecaoObjetos<E extends CanvasCenarioTatico>
/*      */   extends TarefaSelecao<E>
/*      */ {
/*      */   protected Point mouseClick;
/*      */   protected CoordenadaCartesiana clickScreenLocation;
/*      */   protected Region regiaoCanvasSuperiorModoView;
/*   89 */   private List<ObjetoGrafico> objetosGraficosMouseOver = new ArrayList<>();
/*      */   
/*      */   private ControladorEngajamentoInstrutorForm controladorEngajamento;
/*      */   
/*      */   private boolean statusView = false;
/*      */   
/*      */   private boolean simplifica = false;
/*      */   
/*      */   public TarefaSelecaoObjetos(E canvas) {
/*   98 */     super(canvas);
/*   99 */     this.mouseClick = new Point();
/*      */   }
/*      */ 
/*      */   
/*      */   protected List<ObjetoVisual> filtraSelecao(List<ObjetoVisual> lista) {
/*  104 */     CanvasCenarioTatico canvasCT = (CanvasCenarioTatico)getCanvasEspacial();
/*      */     
/*  106 */     FiltroObjetoTatico filtroObjetoTatico = canvasCT.getFiltroObjetoTatico();
/*  107 */     Iterator<ObjetoVisual> iterator = lista.iterator();
/*      */     
/*  109 */     while (iterator.hasNext()) {
/*  110 */       ObjetoVisual objetoVisual = iterator.next();
/*  111 */       boolean remove = ((ObjetoGrafico)objetoVisual).filtraSelecao(filtroObjetoTatico);
/*  112 */       if (remove) {
/*  113 */         lista.remove(objetoVisual);
/*      */       }
/*      */     } 
/*      */     
/*  117 */     return lista;
/*      */   }
/*      */ 
/*      */   
/*      */   public List<ObjetoVisual> testaSelecao(CoordenadaCartesiana p, double tolerancia) {
/*  122 */     CanvasCenarioTatico<ControladorCenarioTatico> canvasCT = (CanvasCenarioTatico<ControladorCenarioTatico>)getCanvasEspacial();
/*      */     
/*  124 */     List<ObjetoTatico> listaObjetos = canvasCT.getObjetosTaticos();
/*      */     
/*  126 */     if (listaObjetos == null) {
/*  127 */       return new ArrayList<>();
/*      */     }
/*  129 */     List<ObjetoVisual> objetosProximos = new ArrayList<>();
/*  130 */     for (ObjetoTatico objetoTatico : listaObjetos) {
/*  131 */       ObjetoGrafico objetoGrafico = null;
/*      */       try {
/*  133 */         objetoGrafico = (ObjetoGrafico)objetoTatico.getObjetosVisuais().get(ObjetoVisual.Tipo.GRAFICO);
/*      */         
/*  135 */         if (objetoGrafico != null) {
/*  136 */           if (objetoTatico instanceof AreaCircularCorrecaoManual) {
/*  137 */             ObjetoGraficoAreaCircularCorrecaoManual temp = (ObjetoGraficoAreaCircularCorrecaoManual)objetoGrafico;
/*  138 */             if (this.clickScreenLocation == ((AreaCircularCorrecaoManual)temp.getObjetoAssociado()).getPontoCentral() && (
/*  139 */               (AreaCircularCorrecaoManual)temp.getObjetoAssociado()).getPontoCentral().getX() == this.clickScreenLocation
/*  140 */               .getX() && ((AreaCircularCorrecaoManual)temp.getObjetoAssociado()).getPontoCentral().getY() == this.clickScreenLocation.getY()) {
/*  141 */               objetosProximos.remove(temp);
/*      */             }
/*      */           } 
/*      */ 
/*      */           
/*  146 */           Set<ObjetoVisual> lista = new HashSet<>(filtraSelecao(objetoGrafico.testaSelecao(p, tolerancia)));
/*      */           
/*  148 */           objetosProximos.addAll(lista);
/*      */         } 
/*  150 */       } catch (ClassCastException ex) {
/*  151 */         Log.gravarLogExcecao("Erro ao selecionar objeto", this, ex);
/*      */       } 
/*      */     } 
/*      */     
/*  155 */     return objetosProximos;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public List<ObjetoVisual> testaSelecao(JanelaCartesiana janela) {
/*  161 */     CanvasCenarioTatico canvasCT = (CanvasCenarioTatico)getCanvasEspacial();
/*      */     
/*  163 */     List<ObjetoTatico> listaObjetos = canvasCT.getObjetosTaticos();
/*  164 */     if (listaObjetos == null) {
/*  165 */       return new ArrayList<>();
/*      */     }
/*  167 */     List<ObjetoVisual> objetosProximos = new ArrayList<>();
/*  168 */     for (ObjetoTatico objetoTatico : listaObjetos) {
/*  169 */       ObjetoVisual objetoGrafico = (ObjetoVisual)objetoTatico.getObjetosVisuais().get(ObjetoVisual.Tipo.GRAFICO);
/*      */       
/*  171 */       if (objetoGrafico != null && objetoGrafico instanceof ObjetoGrafico) {
/*  172 */         List<ObjetoVisual> lista = filtraSelecao(((ObjetoGrafico)objetoGrafico).testaSelecao(janela));
/*  173 */         objetosProximos.addAll(lista);
/*      */       } 
/*      */     } 
/*  176 */     return objetosProximos;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void finalizaTransformacao(EstadoTeclado e) {
/*  182 */     List<ObjetoVisual> listaMarcados = ordemMarcacao();
/*  183 */     if (listaMarcados != null) {
/*  184 */       for (ObjetoVisual objetoVisual : listaMarcados) {
/*  185 */         ObjetoTatico objetoTatico = ((ObjetoGrafico)objetoVisual).finalizaTransformacao(e);
/*  186 */         if (objetoTatico != null) {
/*  187 */           objetoTatico.salvarMemoria(false);
/*      */         }
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  195 */     this.mouseClick = getMouseClick(p);
/*  196 */     this.clickScreenLocation = p;
/*  197 */     ((CanvasCenarioTatico)getCanvasEspacial()).setRedesenhoAutomatico(false);
/*  198 */     SimuladorVideoBrutoFX.setIdRadarAtual(((CanvasCenarioTatico)getCanvasEspacial()).getIdRadarSelecionado());
/*  199 */     super.botao1Pressionado(p, e);
/*      */   }
/*      */ 
/*      */   
/*      */   public void duploClique(CoordenadaCartesiana p, EstadoTeclado e) {
/*  204 */     if (e.isControlDown()) {
/*  205 */       List<ObjetoVisual> listaMarcados = ordemMarcacao();
/*  206 */       if (listaMarcados.size() == 1) {
/*  207 */         ((ObjetoGrafico)listaMarcados.get(0)).transformarCinematica(0.0D, 0.0D);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void botao1Arrastado(CoordenadaCartesiana cart, EstadoTeclado e) {
/*  214 */     ((CanvasCenarioTatico)getCanvasEspacial()).setCursor(Cursor.NONE);
/*  215 */     super.botao1Arrastado(cart, e);
/*  216 */     this.mouseClick = getMouseClick(cart);
/*  217 */     CanvasCenarioTatico canvasCT = (CanvasCenarioTatico)getCanvasEspacial();
/*  218 */     canvasCT.atualizarCamadaTatica();
/*      */   }
/*      */ 
/*      */   
/*      */   public void botao1Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/*  223 */     ((CanvasCenarioTatico)getCanvasEspacial()).setCursor(Cursor.DEFAULT);
/*  224 */     CanvasCenarioTatico canvasCT = (CanvasCenarioTatico)getCanvasEspacial();
/*      */     
/*  226 */     super.botao1Solto(p, e);
/*  227 */     List<ObjetoVisual> selecao = ordemMarcacao();
/*      */     
/*  229 */     if (selecao.size() == 1) {
/*  230 */       ObjetoTatico objetoTatico = (ObjetoTatico)((ObjetoVisual)selecao.get(0)).getObjetoAssociado();
/*      */       
/*  232 */       if (objetoTatico.getObjetosVisuais().get(ObjetoVisual.Tipo.TEXTUAL) != null && objetoTatico.getObjetosVisuais().get(ObjetoVisual.Tipo.TEXTUAL) instanceof ObjetoTextual) {
/*      */         
/*  234 */         ObjetoTextual objetoTextual = (ObjetoTextual)objetoTatico.getObjetosVisuais().get(ObjetoVisual.Tipo.TEXTUAL);
/*  235 */         canvasCT.getControlador().desenha(objetoTextual);
/*      */       } else {
/*  237 */         canvasCT.getControlador().desenha(null);
/*      */       }
/*      */     
/*  240 */     } else if (selecao.isEmpty()) {
/*  241 */       canvasCT.getControlador().desenha(null);
/*      */     } 
/*      */     
/*  244 */     canvasCT.exibirStatus("");
/*  245 */     canvasCT.atualizarCamadaTatica();
/*  246 */     canvasCT.setRedesenhoAutomatico(true);
/*  247 */     this.mouseClick = getMouseClick(p);
/*      */   }
/*      */ 
/*      */   
/*      */   public void botao3Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/*  252 */     if (Debug.isDebug() && e.isShiftDown()) {
/*  253 */       CoordenadaGeografica cg = CoordenadaGeografica.converterCoordenadaCartesiana(p, 0.0D);
/*  254 */       TextInputDialog dialogo = new TextInputDialog(cg.getLatitude() + " " + cg.getLongitude());
/*  255 */       dialogo.setTitle("Posição");
/*  256 */       dialogo.setHeaderText("Posição");
/*  257 */       dialogo.show();
/*      */       
/*      */       return;
/*      */     } 
/*  261 */     if (this.selecao0 == null) {
/*  262 */       double tolerancia = ((CanvasCenarioTatico)getCanvasEspacial()).distanciaInv(16);
/*  263 */       this.selecao0 = testaSelecao(p, tolerancia);
/*      */     } 
/*      */     
/*  266 */     if (this.selecao0.isEmpty() && ordemMarcacao() != null && ordemMarcacao().size() < 1) {
/*  267 */       botao1Pressionado(p, e);
/*  268 */       botao1Solto(p, e);
/*      */     } 
/*  270 */     super.botao3Pressionado(p, e);
/*      */   }
/*      */   
/*      */   protected void removeObjetos(List<ObjetoVisual> lista) {
/*  274 */     if (lista == null) {
/*      */       return;
/*      */     }
/*  277 */     List<ObjetoVisual> objetosRemovidos = new ArrayList<>();
/*  278 */     for (ObjetoVisual visual : lista) {
/*  279 */       if (visual.efetuarPoliticasExclusaoManual()) {
/*  280 */         objetosRemovidos.add(visual);
/*      */       }
/*      */     } 
/*      */     
/*  284 */     if (objetosRemovidos.size() == 1) {
/*  285 */       Notificador.informacao((new StringBuilder()).append(objetosRemovidos.get(0)).append(" excluído").toString(), "Objeto removido com sucesso");
/*  286 */     } else if (objetosRemovidos.size() > 1) {
/*  287 */       Notificador.informacao("Objetos excluídos", objetosRemovidos.size() + " objetos removidos com sucesso");
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void novoPMA() {
/*  292 */     List<ObjetoVisual> listaMarcados = ordemMarcacao();
/*  293 */     GestorObjetos gestorObjetos = Mediador.getInstancia().getGestor().getGestorObjetos();
/*  294 */     int idManobrador = 0;
/*  295 */     int idReferencia = 0;
/*      */     
/*  297 */     if (listaMarcados.size() == 1) {
/*  298 */       idReferencia = ((ObjetoVisual)listaMarcados.get(0)).getObjetoAssociado().getId();
/*  299 */       idManobrador = Veiculo.getVeiculoReferencial().getId();
/*      */     }
/*  301 */     else if (listaMarcados.size() >= 2) {
/*  302 */       idReferencia = ((ObjetoVisual)listaMarcados.get(0)).getObjetoAssociado().getId();
/*  303 */       idManobrador = ((ObjetoVisual)listaMarcados.get(1)).getObjetoAssociado().getId();
/*      */     } 
/*      */ 
/*      */     
/*  307 */     Cinematica referencia = (idReferencia == 0) ? (Cinematica)Veiculo.getVeiculoReferencial().getCinematica() : (Cinematica)gestorObjetos.getCopia(idReferencia);
/*      */     
/*  309 */     Cinematica manobrador = (idManobrador == 0) ? (Cinematica)Veiculo.getVeiculoReferencial().getCinematica() : (Cinematica)gestorObjetos.getCopia(idManobrador);
/*      */     
/*  311 */     PMA pma = new PMA(Maquina.getMaquinaLocal());
/*  312 */     pma.setCinematicaReferencial(referencia);
/*  313 */     pma.setCinematicaManobrador(manobrador);
/*  314 */     pma.setIdRef(idReferencia);
/*  315 */     pma.setIdMan(idManobrador);
/*      */     
/*  317 */     pma.salvarMemoria(true);
/*      */     try {
/*  319 */       pma.executar();
/*  320 */     } catch (Exception ex) {
/*  321 */       Log.gravarLogExcecao("Erro ao executar PMA", TarefaSelecaoObjetos.class, ex);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void novaManobraTempo() {
/*  326 */     List<ObjetoVisual> listaMarcados = ordemMarcacao();
/*      */     
/*  328 */     if (!listaMarcados.isEmpty()) {
/*      */       
/*  330 */       int idReferencia = 0;
/*  331 */       int idManobrador = 0;
/*      */       
/*  333 */       if (listaMarcados.size() == 1) {
/*  334 */         idManobrador = Veiculo.getVeiculoReferencial().getId();
/*  335 */         idReferencia = ((ObjetoVisual)listaMarcados.get(0)).getObjetoAssociado().getId();
/*      */       } else {
/*  337 */         idReferencia = ((ObjetoVisual)listaMarcados.get(0)).getObjetoAssociado().getId();
/*  338 */         idManobrador = ((ObjetoVisual)listaMarcados.get(1)).getObjetoAssociado().getId();
/*      */       } 
/*      */       
/*  341 */       ControladorManobraTempoForm.setManobrador(idManobrador);
/*  342 */       ControladorManobraTempoForm.setReferencial(idReferencia);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void novaManobraVeloc() {
/*  347 */     List<ObjetoVisual> listaMarcados = ordemMarcacao();
/*      */     
/*  349 */     if (!listaMarcados.isEmpty()) {
/*      */       int idReferencia, idManobrador;
/*      */ 
/*      */ 
/*      */       
/*  354 */       if (listaMarcados.size() == 1) {
/*  355 */         idManobrador = Veiculo.getVeiculoReferencial().getId();
/*  356 */         idReferencia = ((ObjetoVisual)listaMarcados.get(0)).getObjetoAssociado().getId();
/*      */       } else {
/*  358 */         idReferencia = ((ObjetoVisual)listaMarcados.get(0)).getObjetoAssociado().getId();
/*  359 */         idManobrador = ((ObjetoVisual)listaMarcados.get(1)).getObjetoAssociado().getId();
/*      */       } 
/*      */       
/*  362 */       ControladorManobraVelocForm.setManobrador(idManobrador);
/*  363 */       ControladorManobraVelocForm.setReferencial(idReferencia);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void novaManobraPassarSafo() {
/*  368 */     List<ObjetoVisual> listaMarcados = ordemMarcacao();
/*      */     
/*  370 */     if (!listaMarcados.isEmpty()) {
/*      */       int idReferencia, idManobrador;
/*      */ 
/*      */ 
/*      */       
/*  375 */       if (listaMarcados.size() == 1) {
/*  376 */         idManobrador = Veiculo.getVeiculoReferencial().getId();
/*  377 */         idReferencia = ((ObjetoVisual)listaMarcados.get(0)).getObjetoAssociado().getId();
/*      */       } else {
/*  379 */         idReferencia = ((ObjetoVisual)listaMarcados.get(0)).getObjetoAssociado().getId();
/*  380 */         idManobrador = ((ObjetoVisual)listaMarcados.get(1)).getObjetoAssociado().getId();
/*      */       } 
/*      */       
/*  383 */       ControladorManobraPassarSafoForm.setManobrador(idManobrador);
/*  384 */       ControladorManobraPassarSafoForm.setReferencial(idReferencia);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void novaManobraInterceptacao() {
/*  389 */     List<ObjetoVisual> listaMarcados = ordemMarcacao();
/*      */     
/*  391 */     if (!listaMarcados.isEmpty()) {
/*      */       int idReferencia, idManobrador;
/*      */ 
/*      */ 
/*      */       
/*  396 */       if (listaMarcados.size() == 1) {
/*  397 */         idManobrador = Veiculo.getVeiculoReferencial().getId();
/*  398 */         idReferencia = ((ObjetoVisual)listaMarcados.get(0)).getObjetoAssociado().getId();
/*      */       } else {
/*  400 */         idReferencia = ((ObjetoVisual)listaMarcados.get(0)).getObjetoAssociado().getId();
/*  401 */         idManobrador = ((ObjetoVisual)listaMarcados.get(1)).getObjetoAssociado().getId();
/*      */       } 
/*      */       
/*  404 */       ControladorManobraInterceptacaoForm.setManobrador(idManobrador);
/*  405 */       ControladorManobraInterceptacaoForm.setReferencial(idReferencia);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void teclaPressionada(KeyEvent event) {
/*  411 */     CanvasCenarioTatico canvasCT = (CanvasCenarioTatico)getCanvasEspacial();
/*      */     
/*  413 */     if (ordemMarcacao().size() == 2) {
/*  414 */       ObjetoTatico obj1 = (ObjetoTatico)((ObjetoVisual)ordemMarcacao().get(0)).getObjetoAssociado();
/*  415 */       ObjetoTatico obj2 = (ObjetoTatico)((ObjetoVisual)ordemMarcacao().get(1)).getObjetoAssociado();
/*  416 */       if (obj1 instanceof Cinematica && obj2 instanceof Cinematica && !(obj1 instanceof ipqm.gsd.componentes.dominio.navegacao.PontoFixo) && !(obj2 instanceof ipqm.gsd.componentes.dominio.navegacao.PontoFixo) && !(obj1 instanceof Rota) && !(obj2 instanceof Rota))
/*      */       {
/*  418 */         switch (event.getCode()) {
/*      */           case COBERTURA_ESQUADRA:
/*  420 */             novoPMA();
/*      */             break;
/*      */         } 
/*      */       
/*      */       }
/*      */     } 
/*  426 */     switch (event.getCode()) {
/*      */       case GATE_OP:
/*      */       case GATE_BV:
/*  429 */         removeObjetos(ordemMarcacao());
/*  430 */         desmarcaTudo();
/*  431 */         canvasCT.atualizarCamadaTatica();
/*      */         break;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void executarComando(Comando comando, CoordenadaCartesiana posicao) {
/*  454 */     if (isHabilitarComando(comando))
/*      */     {
/*  456 */       switch (comando) {
/*      */         case COBERTURA_ESQUADRA:
/*  458 */           removerAssociacaoPlano();
/*      */           break;
/*      */         case GATE_OP:
/*  461 */           removerObjetos(getCanvasEspacial());
/*      */           break;
/*      */         case GATE_BV:
/*  464 */           adicionarSetor();
/*      */           break;
/*      */         case GATE_BV_MOD:
/*  467 */           criarSetorPreDefinido(GrupoSetor.TipoSetor.COBERTURA_ESQUADRA);
/*      */           break;
/*      */         case PLANO_CORDON:
/*  470 */           criarSetorPreDefinido(GrupoSetor.TipoSetor.GATE_OP);
/*      */           break;
/*      */         case null:
/*  473 */           criarSetorPreDefinido(GrupoSetor.TipoSetor.GATE_BV);
/*      */           break;
/*      */         case null:
/*  476 */           criarSetorPreDefinido(GrupoSetor.TipoSetor.GATE_BV_MOD);
/*      */           break;
/*      */         case null:
/*  479 */           criarSetorPreDefinido(GrupoSetor.TipoSetor.PLANO_CORDON);
/*      */           break;
/*      */         case null:
/*  482 */           removerSetores();
/*      */           break;
/*      */         case null:
/*  485 */           rotacionarSetor();
/*      */           break;
/*      */         case null:
/*  488 */           modificarCalculo();
/*      */           break;
/*      */         case null:
/*  491 */           novoPMA();
/*      */           break;
/*      */         case null:
/*  494 */           novaManobraTempo();
/*      */           break;
/*      */         case null:
/*  497 */           novaManobraVeloc();
/*      */           break;
/*      */         case null:
/*  500 */           novaManobraPassarSafo();
/*      */           break;
/*      */ 
/*      */         
/*      */         case null:
/*      */         case null:
/*  506 */           alteraMasterCheckFire();
/*      */           break;
/*      */ 
/*      */         
/*      */         case null:
/*      */         case null:
/*      */         case null:
/*  513 */           carregaEngajamentoIntrutor(comando);
/*      */           break;
/*      */         case null:
/*  516 */           trocaEscala();
/*      */           break;
/*      */         case null:
/*  519 */           simplifica();
/*      */           break;
/*      */         case null:
/*  522 */           simplifica();
/*      */           break;
/*      */ 
/*      */         
/*      */         case null:
/*  527 */           desamarrarArea();
/*      */           break;
/*      */         case null:
/*  530 */           amarrarLM();
/*      */           break;
/*      */         case null:
/*  533 */           desamarrarLM();
/*      */           break;
/*      */         case null:
/*  536 */           definirPosicaoReferencial();
/*      */           break;
/*      */         case null:
/*  539 */           novaManobraInterceptacao();
/*      */           break;
/*      */         case null:
/*  542 */           exibirPainelEtaNaApt();
/*      */           break;
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private void trocaEscala() {
/*  551 */     List<ObjetoVisual> listaObjVisual = ordemMarcacao();
/*      */     
/*  553 */     for (ObjetoVisual ov : listaObjVisual) {
/*  554 */       ObjetoTatico objTatico = (ObjetoTatico)ov.getObjetoAssociado();
/*      */       
/*  556 */       Veiculo v = (Veiculo)objTatico;
/*      */       
/*  558 */       v.trocaEscala();
/*      */     } 
/*      */   }
/*      */   
/*      */   private void simplifica() {
/*  563 */     List<ObjetoVisual> listaObjVisual = ordemMarcacao();
/*      */     
/*  565 */     for (ObjetoVisual ov : listaObjVisual) {
/*      */       
/*  567 */       ObjetoGraficoVeiculo v = (ObjetoGraficoVeiculo)ov;
/*      */       
/*  569 */       v.simplifica();
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void removerObjetos(E canvas) {
/*  574 */     removeObjetos(ordemMarcacao());
/*  575 */     desmarcaTudo();
/*  576 */     canvas.atualizarCamadaTatica();
/*      */   }
/*      */   
/*      */   private void adicionarSetor() {
/*  580 */     List<ObjetoVisual> listaMarcados = ordemMarcacao();
/*  581 */     if (listaMarcados.size() == 1) {
/*  582 */       ObjetoDOMINIO obj = ((ObjetoVisual)listaMarcados.get(0)).getObjetoAssociado();
/*      */ 
/*      */       
/*  585 */       ObjetoCinematica objetoCinematica = (obj instanceof Acompanhamento) ? ((Acompanhamento)obj).getCinematica() : ((Veiculo)obj).getCondicaoAssociada().getCinematicaAutomatica();
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void criarSetorPreDefinido(GrupoSetor.TipoSetor tpSetor) {
/*  598 */     List<ObjetoVisual> listaMarcados = ordemMarcacao();
/*  599 */     if (listaMarcados.size() == 1) {
/*  600 */       Veiculo veiculo; double escala; ObjetoDOMINIO obj = ((ObjetoVisual)listaMarcados.get(0)).getObjetoAssociado();
/*  601 */       ObjetoTatico objetoTatico = null;
/*  602 */       if (obj instanceof Acompanhamento) {
/*  603 */         Acompanhamento acompanhamento = (Acompanhamento)obj;
/*  604 */       } else if (obj instanceof Veiculo) {
/*  605 */         veiculo = (Veiculo)obj;
/*      */       } 
/*      */       
/*  608 */       switch (tpSetor) {
/*      */         case COBERTURA_ESQUADRA:
/*  610 */           GrupoSetor.criaCoberturaEsquadra((ObjetoTatico)veiculo);
/*      */           break;
/*      */         case GATE_OP:
/*  613 */           GrupoSetor.criaGateOP((ObjetoTatico)veiculo);
/*      */           break;
/*      */         case GATE_BV:
/*  616 */           GrupoSetor.criaGateBV((ObjetoTatico)veiculo);
/*      */           break;
/*      */         case GATE_BV_MOD:
/*  619 */           GrupoSetor.criaGateBVMod((ObjetoTatico)veiculo);
/*      */           break;
/*      */         case PLANO_CORDON:
/*  622 */           escala = ((CanvasCenarioTatico)getCanvasEspacial()).getEscalaMN();
/*  623 */           GrupoSetor.criaPlanoCordon((ObjetoTatico)veiculo, 1.5D, escala);
/*      */           break;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void removerSetores() {
/*  631 */     List<ObjetoVisual> listaMarcados = ordemMarcacao();
/*  632 */     if (listaMarcados.size() == 1) {
/*  633 */       ObjetoGrafico objGrafico = (ObjetoGrafico)listaMarcados.get(0);
/*  634 */       ObjetoDOMINIO obj = ((ObjetoVisual)listaMarcados.get(0)).getObjetoAssociado();
/*  635 */       if (obj instanceof Acompanhamento) {
/*  636 */         objGrafico.removerSetores((ObjetoTatico)obj);
/*  637 */       } else if (obj instanceof Veiculo) {
/*  638 */         objGrafico.removerSetores((ObjetoTatico)obj);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private void rotacionarSetor() {
/*  644 */     List<ObjetoTatico> listaSetores = new ArrayList<>();
/*  645 */     List<ObjetoVisual> listaMarcados = ordemMarcacao();
/*  646 */     Iterator<ObjetoVisual> iterator = listaMarcados.iterator();
/*  647 */     while (iterator.hasNext()) {
/*  648 */       ObjetoVisual obj = iterator.next();
/*  649 */       if (obj.getObjetoAssociado() instanceof ipqm.gsd.componentes.dominio.navegacao.Setor || obj.getObjetoAssociado() instanceof GrupoSetor) {
/*  650 */         listaSetores.add((ObjetoTatico)obj.getObjetoAssociado());
/*      */       }
/*      */     } 
/*  653 */     ((ControladorCenarioTatico)((CanvasCenarioTatico)getCanvasEspacial()).getControlador()).exibirPainelFormulario("projetos/geral/formularios/RotacionarSetorForm.fxml", listaSetores, "Rotacionar Setor(es)");
/*      */   }
/*      */ 
/*      */   
/*      */   private void modificarCalculo() {
/*  658 */     List<ObjetoVisual> listaMarcados = ordemMarcacao();
/*  659 */     if (listaMarcados.size() == 1) {
/*  660 */       ObjetoGrafico objG = (ObjetoGrafico)listaMarcados.get(0);
/*  661 */       if (objG instanceof ipqm.gsd.hidra.ihm.objetos_visuais.objetos_graficos.ObjetoGraficoCalculoTatico) {
/*  662 */         objG.modificarCalculo();
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void mouseMovido(CoordenadaCartesiana p, EstadoTeclado e) {
/*  669 */     super.mouseMovido(p, e);
/*      */     
/*  671 */     List<ObjetoGrafico> objetosGraficos = buscaObjetosGraficos(p);
/*      */     
/*  673 */     if (objetosGraficos == null || objetosGraficos.isEmpty()) {
/*  674 */       if (this.objetosGraficosMouseOver != null && !this.objetosGraficosMouseOver.isEmpty()) {
/*  675 */         this.objetosGraficosMouseOver.forEach(objeto -> objeto.setExibeLegendaAlternativa(false));
/*      */       }
/*  677 */     } else if (this.objetosGraficosMouseOver != null) {
/*      */       
/*  679 */       if (!this.objetosGraficosMouseOver.isEmpty()) {
/*  680 */         for (int i = 0; i < this.objetosGraficosMouseOver.size(); ) {
/*  681 */           if (objetosGraficos.contains(this.objetosGraficosMouseOver.get(i))) {
/*      */             i++;
/*      */             continue;
/*      */           } 
/*  685 */           this.objetosGraficosMouseOver.forEach(objeto -> objeto.setExibeLegendaAlternativa(false));
/*      */         } 
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  691 */       if (PerfilUsuario.getPerfilUsuarioAtual().getPadraoSimbolos().equals("IHO")) {
/*  692 */         for (int i = 0; i < objetosGraficos.size(); i++) {
/*  693 */           if (((ObjetoGrafico)objetosGraficos.get(i)).getObjetoAssociado() instanceof Acompanhamento) {
/*  694 */             Acompanhamento a = (Acompanhamento)((ObjetoGrafico)objetosGraficos.get(i)).getObjetoAssociado();
/*  695 */             if (!a.isDormindo()) {
/*  696 */               ((ObjetoGrafico)this.objetosGraficosMouseOver.get(i)).setExibeLegendaAlternativa(false);
/*      */             }
/*      */           } 
/*      */         } 
/*      */       }
/*  701 */       objetosGraficos.forEach(objeto -> objeto.setExibeLegendaAlternativa(true));
/*      */     } 
/*  703 */     this.objetosGraficosMouseOver = objetosGraficos;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void desenhaGradeCursor(CoordenadaCartesiana p) {
/*  709 */     CanvasCenarioTatico canvasCT = (CanvasCenarioTatico)getCanvasEspacial();
/*  710 */     canvasCT.getCamadaInfo().tornaTransparente();
/*  711 */     Graphics2D g2d = canvasCT.getCamadaInfo().getImagem().createGraphics();
/*      */     
/*  713 */     if (p != null) {
/*  714 */       CoordenadaRaster pR = canvasCT.projeta(p);
/*  715 */       g2d.setStroke(new BasicStroke(2.0F));
/*      */       
/*  717 */       g2d.setColor(new Color(141, 58, 47));
/*  718 */       g2d.drawLine(0, pR.getY(), canvasCT.getLargura() - 1, pR.getY());
/*  719 */       g2d.drawLine(pR.getX(), 0, pR.getX(), canvasCT.getAltura() - 1);
/*      */     } 
/*  721 */     canvasCT.setRedesenhoAutomatico(true);
/*  722 */     canvasCT.getCamadaInfo().geraImagemFX();
/*  723 */     g2d.dispose();
/*      */   }
/*      */ 
/*      */   
/*      */   private void atualizarCamadasModoView() {
/*  728 */     CanvasCenarioTatico canvasCT = (CanvasCenarioTatico)getCanvasEspacial();
/*      */     
/*  730 */     canvasCT.atualizarCamadas();
/*  731 */     if (canvasCT.getControlador() instanceof ControladorCenarioTaticoCisne) {
/*  732 */       ((ControladorCenarioTaticoCisne)canvasCT.getControlador()).desligaPPN();
/*      */     }
/*  734 */     canvasCT.setEnquadramento(false);
/*  735 */     canvasCT.setRedesenhoAutomatico(true);
/*      */   }
/*      */   
/*      */   public void moveCarta(CoordenadaCartesiana p) {
/*  739 */     CanvasCenarioTatico canvasCT = (CanvasCenarioTatico)getCanvasEspacial();
/*  740 */     canvasCT.setRedesenhoAutomatico(false);
/*  741 */     CoordenadaRaster pRMouse = canvasCT.projeta(p);
/*  742 */     canvasCT.setModoNavegacao(CanvasCenarioTatico.ModoNavegacao.LIVRE);
/*  743 */     int posicaoInicialTelaX = (int)canvasCT.getControlador().getTela().getStage().getX();
/*  744 */     int posicaoInicialTelaY = (int)canvasCT.getControlador().getTela().getStage().getY();
/*  745 */     Robot rmouse = null;
/*      */     
/*      */     try {
/*  748 */       rmouse = new Robot();
/*  749 */     } catch (AWTException ex) {
/*  750 */       Log.gravarLogExcecao("Erro ao mover o mouse para o centro do canvas", rmouse, ex);
/*      */     } 
/*      */     
/*  753 */     if (pRMouse.getY() <= canvasCT.getAltura() * 0.07D) {
/*      */       
/*  755 */       if (canvasCT.getLargura() == 1480) {
/*  756 */         rmouse.mouseMove(pRMouse.getX() + posicaoInicialTelaX, 130 + posicaoInicialTelaY);
/*      */       } else {
/*  758 */         rmouse.mouseMove(pRMouse.getX() + 440 + posicaoInicialTelaX, 130 + posicaoInicialTelaY);
/*      */       } 
/*  760 */       canvasCT.moveCentroJanela(0.0D, canvasCT.getEscalaMN() / 2.0D);
/*  761 */       atualizarCamadasModoView();
/*      */       
/*      */       return;
/*      */     } 
/*  765 */     if (pRMouse.getY() >= canvasCT.getAltura() * 0.92D) {
/*  766 */       if (canvasCT.getLargura() == 1480) {
/*  767 */         rmouse.mouseMove(pRMouse.getX() + posicaoInicialTelaX, canvasCT.getAltura() - 130 + posicaoInicialTelaY);
/*      */       } else {
/*  769 */         rmouse.mouseMove(pRMouse.getX() + 440 + posicaoInicialTelaX, canvasCT.getAltura() - 130 + posicaoInicialTelaY);
/*      */       } 
/*  771 */       canvasCT.moveCentroJanela(0.0D, -canvasCT.getEscalaMN() / 2.0D);
/*  772 */       atualizarCamadasModoView();
/*      */       
/*      */       return;
/*      */     } 
/*  776 */     if (pRMouse.getX() >= canvasCT.getLargura() * 0.99D) {
/*  777 */       if (canvasCT.getLargura() == 1480) {
/*  778 */         rmouse.mouseMove(canvasCT.getLargura() - 130 + posicaoInicialTelaX, pRMouse.getY() + posicaoInicialTelaY);
/*      */       } else {
/*  780 */         rmouse.mouseMove(canvasCT.getLargura() + 440 - 130 + posicaoInicialTelaX, pRMouse.getY() + posicaoInicialTelaY);
/*      */       } 
/*  782 */       canvasCT.moveCentroJanela(canvasCT.getEscalaMN() / 2.0D, 0.0D);
/*  783 */       atualizarCamadasModoView();
/*      */       
/*      */       return;
/*      */     } 
/*  787 */     if (pRMouse.getX() <= canvasCT.getLargura() * 0.01D) {
/*  788 */       if (canvasCT.getLargura() == 1480) {
/*  789 */         rmouse.mouseMove(130 + posicaoInicialTelaX, pRMouse.getY() + posicaoInicialTelaY);
/*      */       } else {
/*  791 */         rmouse.mouseMove(130 + 440 + posicaoInicialTelaX, pRMouse.getY() + posicaoInicialTelaY);
/*      */       } 
/*  793 */       canvasCT.moveCentroJanela(-canvasCT.getEscalaMN() / 2.0D, 0.0D);
/*  794 */       atualizarCamadasModoView();
/*      */       return;
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void mouseSaiu(CoordenadaCartesiana p) {
/*  801 */     super.mouseSaiu(p);
/*  802 */     if (this.objetosGraficosMouseOver != null && !this.objetosGraficosMouseOver.isEmpty()) {
/*  803 */       this.objetosGraficosMouseOver.forEach(objeto -> objeto.setExibeLegendaAlternativa(false));
/*      */     }
/*      */   }
/*      */   
/*      */   private List<ObjetoGrafico> buscaObjetosGraficos(CoordenadaCartesiana p) {
/*  808 */     double tolerancia = ((CanvasCenarioTatico)getCanvasEspacial()).distanciaInv(16);
/*  809 */     List<ObjetoVisual> selecao = testaSelecao(p, tolerancia);
/*  810 */     List<ObjetoGrafico> objetosGraficos = new ArrayList<>();
/*  811 */     if (selecao != null && selecao.size() > 0)
/*      */     {
/*      */ 
/*      */       
/*  815 */       for (ObjetoVisual ov : selecao) {
/*  816 */         objetosGraficos.add((ObjetoGrafico)ov);
/*      */       }
/*      */     }
/*      */     
/*  820 */     return objetosGraficos;
/*      */   }
/*      */   
/*      */   private void alteraMasterCheckFire() {
/*  824 */     Veiculo veiculo = (Veiculo)((ObjetoVisual)ordemMarcacao().get(0)).getObjetoAssociado();
/*  825 */     boolean mcf = veiculo.isMasterCheckFire();
/*      */     
/*  827 */     veiculo.setMasterCheckFire(!mcf);
/*  828 */     boolean distribuir = (Veiculo.getVeiculoReferencial() != null);
/*  829 */     veiculo.setDistribuirSisArmas(distribuir);
/*  830 */     veiculo.salvarMemoria(false);
/*      */     
/*  832 */     if (veiculo.isMasterCheckFire()) {
/*  833 */       Notificador.informacao("Master Check Fire", "Master Check Fire ativo para o veículo " + veiculo.toString());
/*      */     } else {
/*  835 */       Notificador.informacao("Removido o Master Check Fire", "Master Check Fire removido do veículo " + veiculo.toString());
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private void carregaEngajamentoIntrutor(Comando comando) {
/*  841 */     ControladorCenarioTatico cct = (ControladorCenarioTatico)((CanvasCenarioTatico)getCanvasEspacial()).getControlador();
/*      */     
/*  843 */     if (cct != null) {
/*  844 */       Veiculo veiculo1 = (Veiculo)((ObjetoVisual)ordemMarcacao().get(0)).getObjetoAssociado();
/*      */       
/*  846 */       if (comando.equals(Comando.ENGAJAMENTO_INSTRUTOR_DUPLO)) {
/*  847 */         Veiculo veiculo2 = (Veiculo)((ObjetoVisual)ordemMarcacao().get(1)).getObjetoAssociado();
/*  848 */         cct.exibirPainelFormulario("projetos/instrutor/formularios/EngajamentoInstrutorForm.fxml", comando.getTexto(), (ObjetoTatico)veiculo1, (ObjetoTatico)veiculo2);
/*      */       } else {
/*  850 */         Map mapVeiculos = ((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getMapaVeiculos();
/*      */         
/*  852 */         boolean temSecundario = false;
/*      */         
/*  854 */         for (Object obj : mapVeiculos.values()) {
/*  855 */           if (obj instanceof Veiculo && (
/*  856 */             (Veiculo)obj).isSecundario()) {
/*  857 */             temSecundario = true;
/*      */             
/*      */             break;
/*      */           } 
/*      */         } 
/*      */         
/*  863 */         if (temSecundario) {
/*  864 */           cct.exibirPainelFormulario("projetos/instrutor/formularios/EngajamentoInstrutorForm.fxml", comando.getTexto(), (ObjetoTatico)veiculo1);
/*      */         } else {
/*  866 */           Notificador.erro("Não há veiculos para engajar:", "É necessário possuir outro veículo no exercício para efetuar um engajamento.");
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   protected void desamarrarArea() {
/*  874 */     List<ObjetoVisual> listaMarcados = ordemMarcacao();
/*  875 */     if (listaMarcados.size() == 1) {
/*  876 */       ObjetoDOMINIO obj = ((ObjetoVisual)listaMarcados.get(0)).getObjetoAssociado();
/*      */       
/*  878 */       if (obj != null && obj instanceof AreaExercicio) {
/*  879 */         AreaExercicio area = (AreaExercicio)obj;
/*  880 */         area.setAmarrarAreaAoDono(false);
/*  881 */         area.salvarMemoria(false);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void amarrarLM() {
/*  887 */     List<ObjetoVisual> listaMarcados = ordemMarcacao();
/*  888 */     if (listaMarcados.size() == 1) {
/*  889 */       ObjetoDOMINIO obj = ((ObjetoVisual)listaMarcados.get(0)).getObjetoAssociado();
/*  890 */       if (obj instanceof AcompanhamentoLinhaMarcacao) {
/*  891 */         AcompanhamentoLinhaMarcacao linha = (AcompanhamentoLinhaMarcacao)obj;
/*  892 */         ((ControladorCenarioTatico)((CanvasCenarioTatico)getCanvasEspacial()).getControlador()).exibirPainelFormulario("projetos/geral/formularios/AmarrarLM.fxml", "Amarrar Linha de Marcação", (ObjetoTatico)linha);
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   protected void desamarrarLM() {
/*  898 */     List<ObjetoVisual> listaMarcados = ordemMarcacao();
/*  899 */     if (listaMarcados.size() == 1) {
/*  900 */       ObjetoDOMINIO obj = ((ObjetoVisual)listaMarcados.get(0)).getObjetoAssociado();
/*      */       
/*  902 */       if (obj != null && obj instanceof AcompanhamentoLinhaMarcacaoManual) {
/*  903 */         AcompanhamentoLinhaMarcacaoManual linha = (AcompanhamentoLinhaMarcacaoManual)obj;
/*  904 */         linha.setAmarrarAoDono(false);
/*  905 */         linha.salvarMemoria(false);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void habilitarComandos() {
/*  912 */     super.habilitarComandos();
/*      */     
/*  914 */     if (Mediador.getInstancia().getGestor().getGestorIntegracao() != null && Mediador.getInstancia().getGestor().getGestorIntegracao() instanceof ipqm.gsd.componentes.dominio.gestao.integracao.GestorIntegracaoExterno) {
/*  915 */       setHabilitarComando(Comando.LIBERAR_SERVICO_EXTERNO, true);
/*      */     }
/*      */     
/*  918 */     setHabilitarComando(Comando.INFO_CARTA, ((CanvasCenarioTatico)getCanvasEspacial()).getFiltroCartaNautica().isExibir());
/*  919 */     setHabilitarComando(Comando.POSICAO_REFERENCIAL, true);
/*      */   }
/*      */   
/*      */   public void definirPosicaoReferencial() {
/*  923 */     ((ControladorCenarioTatico)((CanvasCenarioTatico)getCanvasEspacial()).getControlador()).exibirPainelFormulario("projetos/geral/formularios/AmarrarPolar.fxml", "Amarrar Polar", null);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public Map<Comando, Boolean> obterComandosHabilitados() {
/*  929 */     Map<Comando, Boolean> comandosHabilitados = new HashMap<>();
/*      */     
/*  931 */     comandosHabilitados.put(Comando.POSICAO_REFERENCIAL, Boolean.valueOf(isHabilitarComando(Comando.POSICAO_REFERENCIAL)));
/*      */     
/*  933 */     return comandosHabilitados;
/*      */   }
/*      */   
/*      */   public void editarRota() {
/*  937 */     double NM2Y = 2025.37183D;
/*  938 */     List<ObjetoVisual> listaObjVisual = ordemMarcacao();
/*  939 */     Rota rota = null;
/*  940 */     for (ObjetoVisual ov : listaObjVisual) {
/*  941 */       ObjetoTatico objTatico = (ObjetoTatico)ov.getObjetoAssociado();
/*  942 */       rota = (Rota)objTatico;
/*      */     } 
/*  944 */     if (rota != null) {
/*  945 */       CanvasCenarioTatico canvasCT = (CanvasCenarioTatico)getCanvasEspacial();
/*      */       
/*  947 */       final List<Waypoint> route = new ArrayList<>();
/*  948 */       route.add(new Waypoint(((WayPoint)rota.getListaWayPoints().get(0)).getLongitude(), ((WayPoint)rota.getListaWayPoints().get(0)).getLatitude()));
/*  949 */       for (int i = 1; i < rota.getListaWayPoints().size(); i++) {
/*  950 */         WayPoint wp = rota.getListaWayPoints().get(i);
/*  951 */         route.add(new Waypoint(wp.getLongitude(), wp.getLatitude(), ((Pernada)rota.getListaPernadas().get(i)).getXTDBombordo() / NM2Y, ((Pernada)rota.getListaPernadas().get(i)).getXTDBoreste() / NM2Y));
/*      */       } 
/*      */       
/*      */       try {
/*  955 */         DialogoMapaEditaRota dialogoMapaEditaRota = new DialogoMapaEditaRota("Editar Rota", rota.getNome(), canvasCT.getControlador(), route, rota)
/*      */           {
/*      */             public void acao(AcaoDialogo acao) {
/*  958 */               if (acao == AcaoDialogo.OK) {
/*  959 */                 getRouteEditor().finish(route);
/*      */               }
/*  961 */               ocultar();
/*      */             }
/*      */           };
/*  964 */       } catch (Exception e) {
/*  965 */         Log.gravarLogExcecao("Erro ao abrir DialogoMapaRota.", this, e);
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public void enquadrarRota() {
/*  972 */     JanelaGeografica janela = null;
/*  973 */     double escala = 0.0D;
/*  974 */     List<ObjetoVisual> listaObjVisual = ordemMarcacao();
/*      */     
/*  976 */     CanvasCenarioTatico canvasCT = (CanvasCenarioTatico)getCanvasEspacial();
/*  977 */     ((CanvasCenarioTatico)getCanvasEspacial()).setModoNavegacao(CanvasCenarioTatico.ModoNavegacao.LIVRE);
/*  978 */     canvasCT.setEnquadramento(false);
/*      */     
/*  980 */     for (ObjetoVisual ov : listaObjVisual) {
/*  981 */       ObjetoTatico objTatico = (ObjetoTatico)ov.getObjetoAssociado();
/*      */       
/*  983 */       Rota rota = (Rota)objTatico;
/*  984 */       janela = new JanelaGeografica(rota.obterMenorCoordenada(rota.getListaPernadas()), rota.obterMaiorCoordenada(rota.getListaPernadas()));
/*      */       
/*  986 */       if (janela.getLargura() > janela.getAltura()) {
/*  987 */         escala = janela.getLargura() / 2.0D;
/*      */         continue;
/*      */       } 
/*  990 */       escala = janela.getAltura() / 2.0D;
/*      */     } 
/*      */ 
/*      */     
/*  994 */     if (janela != null) {
/*  995 */       canvasCT.setCentroJanela(janela.getCentro());
/*  996 */       canvasCT.setEscalaMN(escala * 1.1D);
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean modoView() {
/* 1001 */     return this.statusView;
/*      */   }
/*      */   
/*      */   public void setStatusView(boolean statusView) {
/* 1005 */     this.statusView = statusView;
/*      */   }
/*      */   
/*      */   public void adicionaRegiaoLimiteSuperior() {
/* 1009 */     this.regiaoCanvasSuperiorModoView = new Region();
/* 1010 */     (CanvasCenarioTatico)getCanvasEspacial(); this.regiaoCanvasSuperiorModoView.setPrefSize(1920.0D, 3.0D);
/* 1011 */     this.regiaoCanvasSuperiorModoView.setId("regiao");
/* 1012 */     this.regiaoCanvasSuperiorModoView.setLayoutY(0.0D);
/* 1013 */     this.regiaoCanvasSuperiorModoView.setLayoutX(0.0D);
/* 1014 */     this.regiaoCanvasSuperiorModoView.setStyle("-fx-background-color: transparent");
/* 1015 */     ((CanvasCenarioTatico)getCanvasEspacial()).getControlador().getCenarioTaticoAnchorPane().getChildren().add(this.regiaoCanvasSuperiorModoView);
/*      */   }
/*      */   
/*      */   public void removeRegiaoLimiteSuperior() {
/* 1019 */     ((CanvasCenarioTatico)getCanvasEspacial()).getControlador().getCenarioTaticoAnchorPane().getChildren().remove(this.regiaoCanvasSuperiorModoView);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isSimplifica() {
/* 1026 */     return this.simplifica;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setSimplifica(boolean simplifica) {
/* 1033 */     this.simplifica = simplifica;
/*      */   }
/*      */   
/*      */   private void exibirPainelEtaNaApt() {
/* 1037 */     ObjetoTatico objetoAssociado = (ObjetoTatico)((ObjetoVisual)ordemMarcacao().get(0)).getObjetoAssociado();
/* 1038 */     if (ordemMarcacao().size() == 1) {
/* 1039 */       if (!(objetoAssociado instanceof ipqm.gsd.componentes.dominio.calculos_taticos.EtaNaApt)) {
/* 1040 */         ControladorEtanaApt.setObjetoReferencial((Cinematica)((ObjetoVisual)ordemMarcacao().get(0)).getObjetoAssociado());
/* 1041 */         ControladorEtanaApt.setObjetoManobrador((Cinematica)Veiculo.getVeiculoReferencial());
/*      */       } 
/*      */     } else {
/* 1044 */       ControladorEtanaApt.setObjetoReferencial((Cinematica)((ObjetoVisual)ordemMarcacao().get(1)).getObjetoAssociado());
/* 1045 */       ControladorEtanaApt.setObjetoManobrador((Cinematica)((ObjetoVisual)ordemMarcacao().get(0)).getObjetoAssociado());
/*      */     } 
/*      */     
/* 1048 */     ((CanvasCenarioTatico)getCanvasEspacial()).getControlador().exibirPainelFormulario("projetos/geral/formularios/EtaNaAptForm.fxml", Comando.ETA_NA_APT
/* 1049 */         .getTexto(), (ObjetoTatico)((ObjetoVisual)ordemMarcacao().get(0)).getObjetoAssociado());
/*      */   }
/*      */   
/*      */   private void removerAssociacaoPlano() {
/* 1053 */     List<ObjetoVisual> listaObjVisual = ordemMarcacao();
/* 1054 */     Iterator<ObjetoVisual> iterator = listaObjVisual.iterator();
/*      */     
/* 1056 */     PlanoNavegacao planoCorrente = PlanoNavegacao.obterPlanoCorrente();
/*      */     
/* 1058 */     while (iterator.hasNext()) {
/* 1059 */       ObjetoVisual obj = iterator.next();
/* 1060 */       ObjetoDOMINIO objetoAssociado = obj.getObjetoAssociado();
/*      */       
/* 1062 */       if (objetoAssociado instanceof Rota) {
/* 1063 */         if (objetoAssociado.equals(planoCorrente.getRotaAlternativa())) {
/* 1064 */           planoCorrente.setRotaAlternativa(null);
/* 1065 */         } else if (objetoAssociado.equals(planoCorrente.getRotaPrincipal())) {
/* 1066 */           planoCorrente.setRotaPrincipal(null);
/*      */         } 
/*      */         
/* 1069 */         ((Rota)objetoAssociado).excluir(false); continue;
/* 1070 */       }  if (objetoAssociado instanceof AreaExercicio) {
/* 1071 */         planoCorrente.getListaAreaExercicio().remove(objetoAssociado);
/* 1072 */         ((AreaExercicio)objetoAssociado).excluir(false); continue;
/* 1073 */       }  if (objetoAssociado instanceof ipqm.gsd.componentes.dominio.navegacao.PontoFixo) {
/* 1074 */         planoCorrente.getListaPontosFixos().remove(objetoAssociado);
/* 1075 */         objetoAssociado.excluir(false);
/*      */       } 
/*      */     } 
/*      */     
/*      */     try {
/* 1080 */       planoCorrente.atualizarBDMemoria(true);
/* 1081 */     } catch (Exception ex) {
/* 1082 */       Log.gravarLogExcecao("Erro ao remover objetos do plano de navegação.", this, ex);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaSelecaoObjetos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */