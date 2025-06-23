/*     */ package ipqm.gsd.hidra.ihm.dialogos.mapa.rota;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.componentes.dominio.contexto.ContextoTatico;
/*     */ import ipqm.gsd.componentes.dominio.entidades.Veiculo;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.Pernada;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.Rota;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.WayPoint;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.hidra.ihm.controle.Controlador;
/*     */ import ipqm.gsd.hidra.ihm.dialogos.AcaoDialogo;
/*     */ import ipqm.gsd.hidra.ihm.dialogos.mapa.DialogoMapa;
/*     */ import ipqm.gsd.hidra.ihm.projetos.geral.janelas.controladores.ControladorRotas;
/*     */ import ipqm.gsd.hidra.ihm.projetos.geral.modeloTabelas.ControladorTabelaRotas;
/*     */ import ipqm.gsd.javafx.widget.route.RouteEditorWidget;
/*     */ import ipqm.gsd.javafx.widget.route.Waypoint;
/*     */ import ipqm.gsd.smaps.model.charts.ChartsDB;
/*     */ import ipqm.gsd.smaps.view.vector.ENCParameters;
/*     */ import ipqm.gsd.smaps.view.vector.S52Parameters;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.layout.Pane;
/*     */ 
/*     */ 
/*     */ public class DialogoMapaEditarRota
/*     */   extends DialogoMapa
/*     */ {
/*  31 */   private final int escala = 3;
/*     */   private List<WayPoint> listaWayPoints;
/*     */   private List<Pernada> listaPernadas;
/*  34 */   private double velocidadePadrao = 0.0D;
/*     */   private static final double NM2Y = 2025.37183D;
/*     */   
/*     */   public DialogoMapaEditarRota(String titulo, String descricao, final Controlador controladorPai, final Rota rota) throws Exception {
/*  38 */     super(titulo, descricao, controladorPai);
/*     */     
/*  40 */     getControladorDialogoMapa().getFundoMapa().setLayoutX(100.0D);
/*  41 */     getControladorDialogoMapa().getFundoMapa().setLayoutY(50.0D);
/*  42 */     getControladorDialogoMapa().getContainer().setPrefSize(1200.0D, 700.0D);
/*  43 */     getControladorDialogoMapa().getFundoMapa().lookupAll("#botaoJanela").forEach(node -> {
/*     */           if (node instanceof Button) {
/*     */             if (!((Button)node).getText().equals("OK")) {
/*     */               ((Button)node).setMaxWidth(200.0D);
/*     */               
/*     */               node.setLayoutY(node.getLayoutY() + 250.0D);
/*     */               
/*     */               node.setLayoutX(520.0D);
/*     */             } else {
/*     */               ((Button)node).setVisible(false);
/*     */             } 
/*     */           }
/*     */         });
/*  56 */     if (Veiculo.getVeiculoReferencial() != null) {
/*  57 */       this.velocidadePadrao = Veiculo.getVeiculoReferencial().getRumoFundo();
/*     */     }
/*     */     
/*  60 */     ENCParameters parameters = new ENCParameters();
/*  61 */     parameters.setShowScaleBar(true);
/*  62 */     parameters.setShowNorthArrow(false);
/*  63 */     parameters.setShowUnderScaleIndicator(false);
/*  64 */     parameters.setShowOverScaleIndicator(false);
/*  65 */     parameters.setDisplayCategory(S52Parameters.DisplayCategory.BASE);
/*     */     
/*  67 */     List<Waypoint> listaWayPointControladorRotas = null;
/*  68 */     if (rota != null) {
/*  69 */       listaWayPointControladorRotas = new ArrayList<>();
/*  70 */       for (int i = 0; i < rota.getListaWayPoints().size(); i++) {
/*  71 */         listaWayPointControladorRotas.add(new Waypoint(((WayPoint)rota
/*  72 */               .getListaWayPoints().get(i)).getLongitude(), ((WayPoint)rota
/*  73 */               .getListaWayPoints().get(i)).getLatitude(), ((Pernada)rota
/*  74 */               .getListaPernadas().get(i)).getXTDBombordo() / 2025.37183D, ((Pernada)rota
/*  75 */               .getListaPernadas().get(i)).getXTDBoreste() / 2025.37183D));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  80 */     RouteEditorWidget routeEditorWidget = new RouteEditorWidget(Mediador.getInstancia().getGestor().getGestorCartas().getChartsDB(), parameters, listaWayPointControladorRotas)
/*     */       {
/*     */         public void finish(List<Waypoint> list)
/*     */         {
/*  84 */           if (list != null) {
/*  85 */             Rota novaRota = null;
/*  86 */             DialogoMapaEditarRota.this.listaWayPoints = new ArrayList();
/*  87 */             for (int i = 0; i < list.size(); i++) {
/*     */               
/*  89 */               CoordenadaGeografica cg = new CoordenadaGeografica(((Waypoint)list.get(i)).getLatitude(), ((Waypoint)list.get(i)).getLongitude());
/*  90 */               DialogoMapaEditarRota.this.listaWayPoints.add(new WayPoint(null, "wp-" + i, cg));
/*     */             } 
/*     */             
/*  93 */             if (!DialogoMapaEditarRota.this.listaWayPoints.isEmpty()) {
/*  94 */               DialogoMapaEditarRota.this.listaPernadas = new ArrayList();
/*  95 */               Pernada pernada = null;
/*  96 */               for (int j = 0; j < DialogoMapaEditarRota.this.listaWayPoints.size(); j++) {
/*  97 */                 if (j == 0) {
/*  98 */                   pernada = new Pernada(j + 1, DialogoMapaEditarRota.this.listaWayPoints.get(j), null, 0.0D, 0.0D, 0.0D);
/*  99 */                   pernada.setVelocidadePlanejada(10.0D);
/*     */                 
/*     */                 }
/*     */                 else {
/*     */ 
/*     */                   
/* 105 */                   pernada = new Pernada(j + 1, DialogoMapaEditarRota.this.listaWayPoints.get(j), Pernada.TipoPernada.RHUMB_LINE, ((Waypoint)list.get(j)).getXtdPort() * 2025.37183D, ((Waypoint)list.get(j)).getXtdStarboard() * 2025.37183D, DialogoMapaEditarRota.this.velocidadePadrao);
/* 106 */                   pernada.setVelocidadePlanejada(10.0D);
/*     */                 } 
/* 108 */                 DialogoMapaEditarRota.this.listaPernadas.add(pernada);
/*     */               } 
/*     */             } 
/*     */             
/* 112 */             if (!DialogoMapaEditarRota.this.listaPernadas.isEmpty())
/*     */             {
/*     */ 
/*     */ 
/*     */               
/* 117 */               novaRota = new Rota(Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoRede().getMaquinaLocal(), (rota == null) ? ("rota" + (Math.abs((new Random()).nextInt()) % 100000)) : rota.getNome(), (rota == null) ? "origem" : rota.getOrigem(), (rota == null) ? "destino" : rota.getDestino());
/* 118 */               for (Pernada pernada : DialogoMapaEditarRota.this.listaPernadas) {
/* 119 */                 novaRota.getListaPernadas().add(pernada);
/*     */               }
/*     */               
/* 122 */               if (rota != null) {
/* 123 */                 rota.excluirBD(true);
/* 124 */                 ((ControladorRotas)controladorPai).getTabelaRotas().getItems();
/*     */               } 
/*     */               
/* 127 */               novaRota.salvarBD(true);
/* 128 */               ControladorTabelaRotas ctr = new ControladorTabelaRotas(novaRota);
/* 129 */               ((ControladorRotas)controladorPai).getTabelaRotas().getItems().add(ctr);
/* 130 */               ((ControladorRotas)controladorPai).atualizaJanela();
/* 131 */               ((ControladorRotas)controladorPai).getTabelaRotas().getSelectionModel().clearSelection(-1);
/*     */             }
/*     */           
/*     */           }
/*     */           else {
/*     */             
/* 137 */             System.out.println("route cancelled!");
/*     */           } 
/* 139 */           DialogoMapaEditarRota.this.ocultar();
/*     */         }
/*     */       };
/*     */ 
/*     */     
/* 144 */     getControladorDialogoMapa().getContainer().getChildren().add(routeEditorWidget);
/* 145 */     routeEditorWidget.bind((Pane)getControladorDialogoMapa().getContainer());
/* 146 */     if (listaWayPointControladorRotas != null) {
/* 147 */       routeEditorWidget.view();
/*     */     } else {
/* 149 */       double lat = ((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getConfiguracaoTatica().getLatitudeInicial();
/* 150 */       double lon = ((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getConfiguracaoTatica().getLongitudeInicial();
/* 151 */       if (lat == 0.0D && lon == 0.0D) {
/* 152 */         lat = -22.8595D;
/* 153 */         lon = -43.1536D;
/*     */       } 
/* 155 */       routeEditorWidget.view(lon, lat, 3.0D);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void acao(AcaoDialogo acao) {
/* 162 */     if (acao == AcaoDialogo.OK);
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/dialogos/mapa/rota/DialogoMapaEditarRota.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */