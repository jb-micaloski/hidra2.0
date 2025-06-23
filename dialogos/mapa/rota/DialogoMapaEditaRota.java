/*     */ package ipqm.gsd.hidra.ihm.dialogos.mapa.rota;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.componentes.dominio.entidades.Veiculo;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.Pernada;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.Rota;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.WayPoint;
/*     */ import ipqm.gsd.componentes.nucleo.Ambiente;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.NucleoAmbiente;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.logon.perfis.PerfilUsuario;
/*     */ import ipqm.gsd.componentes.nucleo.util.UtilData;
/*     */ import ipqm.gsd.hidra.ihm.controle.Controlador;
/*     */ import ipqm.gsd.hidra.ihm.dialogos.mapa.DialogoMapa;
/*     */ import ipqm.gsd.hidra.ihm.util.FusoHorarioStringConverter;
/*     */ import ipqm.gsd.javafx.widget.route.RouteEditorWidget;
/*     */ import ipqm.gsd.javafx.widget.route.Waypoint;
/*     */ import ipqm.gsd.smaps.model.charts.ChartsDB;
/*     */ import ipqm.gsd.smaps.model.coord.Geodesics;
/*     */ import ipqm.gsd.smaps.view.vector.ENCParameters;
/*     */ import ipqm.gsd.smaps.view.vector.S52Parameters;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javafx.application.Platform;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.layout.Pane;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class DialogoMapaEditaRota
/*     */   extends DialogoMapa
/*     */ {
/*     */   private static final double NM2Y = 2025.37183D;
/*     */   private final RouteEditorWidget routeEditor;
/*     */   
/*     */   public DialogoMapaEditaRota(String titulo, String descricao, Controlador controladorPai, List<Waypoint> route, final Rota rota) throws Exception {
/*  42 */     super(titulo, descricao, controladorPai);
/*  43 */     getControladorDialogoMapa().getFundoMapa().setLayoutX(310.0D);
/*  44 */     getControladorDialogoMapa().getFundoMapa().setLayoutY(50.0D);
/*  45 */     getControladorDialogoMapa().getContainer().setPrefSize(1200.0D, 700.0D);
/*  46 */     Platform.runLater(() -> getControladorDialogoMapa().getFundoMapa().lookupAll("#botaoJanela").forEach(()));
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
/*  65 */     ENCParameters params = new ENCParameters();
/*  66 */     params.setShowScaleBar(true);
/*  67 */     params.setShowNorthArrow(false);
/*  68 */     params.setShowUnderScaleIndicator(false);
/*  69 */     params.setShowOverScaleIndicator(false);
/*  70 */     params.setDisplayCategory(S52Parameters.DisplayCategory.BASE);
/*     */     
/*  72 */     this.routeEditor = new RouteEditorWidget(Mediador.getInstancia().getGestor().getGestorCartas().getChartsDB(), params, route)
/*     */       {
/*     */         public void finish(List<Waypoint> waypoints) {
/*  75 */           if (waypoints != null) {
/*     */ 
/*     */             
/*     */             try {
/*  79 */               DialogoMapaEditaRota.this.editarRotaSelecionada(rota, waypoints);
/*     */             }
/*  81 */             catch (CloneNotSupportedException ex) {
/*  82 */               Log.gravarLogDebug("Erro ao editar rota.", this);
/*     */             } 
/*     */           } else {
/*  85 */             System.out.println("route cancelled!");
/*     */           } 
/*  87 */           DialogoMapaEditaRota.this.ocultar();
/*     */         }
/*     */       };
/*     */     
/*  91 */     getControladorDialogoMapa().getContainer().getChildren().add(this.routeEditor);
/*  92 */     this.routeEditor.bind((Pane)getControladorDialogoMapa().getContainer());
/*  93 */     this.routeEditor.view();
/*     */   }
/*     */ 
/*     */   
/*     */   public void constroiRotaModificada(Rota rota, List<Waypoint> route) {
/*  98 */     Rota novaRota = criaObjeto(route);
/*  99 */     if (novaRota != null) {
/*     */       
/* 101 */       rota = novaRota;
/* 102 */       novaRota.setIdObjetoTatico(null);
/* 103 */       rota.atualizarBDMemoria(true);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Rota criaObjeto(List<Waypoint> route) {
/* 110 */     List<CoordenadaCartesiana> vertices = new ArrayList<>();
/*     */     
/* 112 */     for (Waypoint wp : route) {
/* 113 */       CoordenadaGeografica cg = new CoordenadaGeografica(wp.getLatitude(), wp.getLongitude());
/* 114 */       CoordenadaCartesiana cc = CoordenadaCartesiana.converterCoordenadaGeografica(cg, 0.0D);
/* 115 */       vertices.add(cc);
/*     */     } 
/*     */     
/* 118 */     if (vertices.size() >= 2) {
/*     */ 
/*     */ 
/*     */       
/* 122 */       double velocidadePadrao = 15.0D;
/* 123 */       double tempoPermanenciaPadrao = 0.0D;
/* 124 */       Random random = new Random();
/* 125 */       String timeZone = Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.TIMEZONE_INICIAL);
/* 126 */       long timeZoneDefault = FusoHorarioStringConverter.fromString(timeZone).longValue();
/*     */       
/* 128 */       if (!PerfilUsuario.getPerfilUsuarioAtual().isUsuarioInstrutor()) {
/* 129 */         velocidadePadrao = Veiculo.getVeiculoReferencial().getVelocidadeFundo();
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 134 */       Rota rota = new Rota(Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoRede().getMaquinaLocal(), "Rota" + (Math.abs(random.nextInt()) % 100000), "origem", "destino");
/*     */ 
/*     */       
/* 137 */       List<WayPoint> listaWayPoints = new ArrayList<>();
/* 138 */       for (int i = 0; i < vertices.size(); i++) {
/*     */ 
/*     */         
/* 141 */         WayPoint wp = new WayPoint(null, "wp-" + i, vertices.get(i));
/* 142 */         wp.setTempoPrevistoPermanencia(tempoPermanenciaPadrao);
/* 143 */         wp.setFusoHorario(Long.valueOf(timeZoneDefault));
/*     */         
/* 145 */         if (!PerfilUsuario.getPerfilUsuarioAtual().isUsuarioInstrutor())
/*     */         {
/* 147 */           if (velocidadePadrao >= 0.0D) {
/* 148 */             if (i == 0) {
/* 149 */               wp.setHoraEstimadaPartida(Mediador.getRelogio().getHorario());
/*     */             }
/*     */             else {
/*     */               
/* 153 */               WayPoint wpAnterior = listaWayPoints.get(i - 1);
/* 154 */               double comprimento = Geodesics.distance(wpAnterior.getLongitude(), wpAnterior.getLatitude(), wp.getLongitude(), wp.getLatitude());
/* 155 */               double tempoPernada = UtilData.converterHorasMilissegundos(comprimento / Veiculo.getVeiculoReferencial().getVelocidadeFundo());
/* 156 */               wp.setHoraEstimadaChegada(wpAnterior.getHoraEstimadaPartida() + (long)tempoPernada);
/* 157 */               wp.setHoraEstimadaPartida(wp.getHoraEstimadaChegada() + (long)wp.getTempoPrevistoPermanencia());
/*     */             } 
/*     */           }
/*     */         }
/*     */         
/* 162 */         listaWayPoints.add(wp);
/*     */       } 
/*     */ 
/*     */       
/* 166 */       List<Pernada> listaPernadas = new ArrayList<>();
/* 167 */       Pernada p = new Pernada();
/* 168 */       for (int j = 0; j < listaWayPoints.size(); j++) {
/*     */         try {
/* 170 */           if (j == 0) {
/* 171 */             p = new Pernada(j + 1, listaWayPoints.get(j), null, 0.0D, 0.0D, 0.0D);
/* 172 */             p.setVelocidadePlanejada(10.0D);
/*     */           } else {
/* 174 */             p = new Pernada(j + 1, listaWayPoints.get(j), Pernada.TipoPernada.RHUMB_LINE, ((Waypoint)route.get(j)).getXtdPort() * 2025.37183D, ((Waypoint)route.get(j)).getXtdStarboard() * 2025.37183D, velocidadePadrao);
/* 175 */             p.setVelocidadePlanejada(10.0D);
/*     */           } 
/* 177 */         } catch (Exception e) {
/* 178 */           Log.gravarLogExcecao("Erro ao criar rota", this, e);
/*     */         } 
/* 180 */         listaPernadas.add(p);
/*     */       } 
/*     */ 
/*     */       
/* 184 */       for (Pernada pernada : listaPernadas) {
/* 185 */         rota.getListaPernadas().add(pernada);
/*     */       }
/* 187 */       return rota;
/*     */     } 
/* 189 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void editarRotaSelecionada(Rota rota, List<Waypoint> route) throws CloneNotSupportedException {
/* 194 */     if (route.size() <= rota.getListaWayPoints().size()) {
/* 195 */       for (int index = 0; index < route.size(); index++) {
/* 196 */         ((WayPoint)rota.getListaWayPoints().get(index)).setLongitude(((Waypoint)route.get(index)).getLongitude());
/* 197 */         ((WayPoint)rota.getListaWayPoints().get(index)).setLatitude(((Waypoint)route.get(index)).getLatitude());
/* 198 */         ((Pernada)rota.getListaPernadas().get(index)).setXTDBombordo(((Waypoint)route.get(index)).getXtdPort() * 2025.37183D);
/* 199 */         ((Pernada)rota.getListaPernadas().get(index)).setXTDBoreste(((Waypoint)route.get(index)).getXtdStarboard() * 2025.37183D);
/*     */       } 
/*     */     }
/*     */     
/* 203 */     if (route.size() < rota.getListaWayPoints().size()) {
/* 204 */       int qtdWayPointsRemovidos = rota.getListaWayPoints().size() - route.size();
/* 205 */       for (int j = rota.getListaPernadas().size() - 1; j > 0 && 
/* 206 */         qtdWayPointsRemovidos > 0; j--) {
/* 207 */         rota.getListaPernadas().remove(j);
/* 208 */         qtdWayPointsRemovidos--;
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 215 */     if (route.size() > rota.getListaWayPoints().size()) {
/*     */       
/* 217 */       for (int index = 0; index < rota.getListaPernadas().size(); index++) {
/* 218 */         ((WayPoint)rota.getListaWayPoints().get(index)).setLongitude(((Waypoint)route.get(index)).getLongitude());
/* 219 */         ((WayPoint)rota.getListaWayPoints().get(index)).setLatitude(((Waypoint)route.get(index)).getLatitude());
/* 220 */         ((Pernada)rota.getListaPernadas().get(index)).setXTDBombordo(((Waypoint)route.get(index)).getXtdPort() * 2025.37183D);
/* 221 */         ((Pernada)rota.getListaPernadas().get(index)).setXTDBoreste(((Waypoint)route.get(index)).getXtdStarboard() * 2025.37183D);
/*     */       } 
/*     */       
/* 224 */       int qtdWayPointsAdicionados = route.size() - rota.getListaWayPoints().size();
/*     */ 
/*     */       
/* 227 */       for (int j = 0; j < qtdWayPointsAdicionados; j++) {
/*     */         
/* 229 */         Pernada novaPernada = (Pernada)((Pernada)rota.getListaPernadas().get(rota.getListaPernadas().size() - 1)).clone();
/* 230 */         novaPernada.setIdPernada(null);
/* 231 */         novaPernada.setNumeroPernada(novaPernada.getNumeroPernada() + 1);
/*     */         
/* 233 */         novaPernada.setXTDBombordo(((Waypoint)route.get(rota.getListaPernadas().size())).getXtdPort() * 2025.37183D);
/* 234 */         novaPernada.setXTDBoreste(((Waypoint)route.get(rota.getListaPernadas().size())).getXtdStarboard() * 2025.37183D);
/*     */         
/* 236 */         WayPoint novoWayPoint = (WayPoint)((WayPoint)rota.getListaWayPoints().get(rota.getListaWayPoints().size() - 1)).clone();
/* 237 */         novoWayPoint.setIdObjetoTatico(null);
/*     */         
/* 239 */         novoWayPoint.setLongitude(((Waypoint)route.get(rota.getListaWayPoints().size())).getLongitude());
/* 240 */         novoWayPoint.setLatitude(((Waypoint)route.get(rota.getListaWayPoints().size())).getLatitude());
/*     */         
/* 242 */         novaPernada.setWayPoint(novoWayPoint);
/* 243 */         rota.getListaPernadas().add(novaPernada);
/*     */       } 
/*     */     } 
/*     */     
/* 247 */     for (int i = 0; i < rota.getListaWayPoints().size(); i++) {
/* 248 */       ((WayPoint)rota.getListaWayPoints().get(i)).setNome("wp-" + i);
/*     */     }
/*     */     
/* 251 */     rota.atualizarBDMemoria(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RouteEditorWidget getRouteEditor() {
/* 258 */     return this.routeEditor;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/dialogos/mapa/rota/DialogoMapaEditaRota.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */