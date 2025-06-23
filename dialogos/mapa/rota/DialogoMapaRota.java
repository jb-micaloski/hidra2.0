/*    */ package ipqm.gsd.hidra.ihm.dialogos.mapa.rota;
/*    */ 
/*    */ import com.vividsolutions.jts.geom.Geometry;
/*    */ import ipqm.gsd.componentes.dominio.navegacao.Pernada;
/*    */ import ipqm.gsd.componentes.dominio.navegacao.Rota;
/*    */ import ipqm.gsd.componentes.dominio.navegacao.WayPoint;
/*    */ import ipqm.gsd.componentes.nucleo.Mediador;
/*    */ import ipqm.gsd.hidra.ihm.controle.Controlador;
/*    */ import ipqm.gsd.hidra.ihm.dialogos.mapa.DialogoMapa;
/*    */ import ipqm.gsd.javafx.widget.route.RouteViewerWidget;
/*    */ import ipqm.gsd.javafx.widget.route.Waypoint;
/*    */ import ipqm.gsd.smaps.view.vector.ENCParameters;
/*    */ import ipqm.gsd.smaps.view.vector.S52Parameters;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javafx.scene.Node;
/*    */ import javafx.scene.control.Button;
/*    */ import javafx.scene.layout.Pane;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class DialogoMapaRota
/*    */   extends DialogoMapa
/*    */ {
/*    */   private static final double NM2Y = 2025.37183D;
/*    */   
/*    */   public DialogoMapaRota(Rota rota, String titulo, String descricao, Controlador controladorPai, Map<String, List<Map<String, Object>>> resultAlarmesAtivos, Geometry alarmeArea) throws Exception {
/* 31 */     super(titulo, descricao, controladorPai);
/* 32 */     getControladorDialogoMapa().getFundoMapa().setLayoutX(100.0D);
/* 33 */     getControladorDialogoMapa().getFundoMapa().setLayoutY(50.0D);
/* 34 */     getControladorDialogoMapa().getContainer().setPrefSize(1200.0D, 700.0D);
/* 35 */     getControladorDialogoMapa().getFundoMapa().lookupAll("#botaoJanela").forEach(node -> {
/*    */           node.setLayoutY(node.getLayoutY() + 250.0D);
/*    */           
/*    */           node.setLayoutX(570.0D);
/*    */           
/*    */           if (node instanceof Button && !((Button)node).getText().equals("OK")) {
/*    */             node.setVisible(false);
/*    */           }
/*    */         });
/*    */     
/* 45 */     List<Waypoint> route = new ArrayList<>();
/* 46 */     route.add(new Waypoint(((WayPoint)rota.getListaWayPoints().get(0)).getLongitude(), ((WayPoint)rota.getListaWayPoints().get(0)).getLatitude()));
/* 47 */     for (int i = 1; i < rota.getListaWayPoints().size(); i++) {
/* 48 */       WayPoint wp = rota.getListaWayPoints().get(i);
/* 49 */       route.add(new Waypoint(wp.getLongitude(), wp.getLatitude(), ((Pernada)rota.getListaPernadas().get(i)).getXTDBombordo() / 2025.37183D, ((Pernada)rota.getListaPernadas().get(i)).getXTDBoreste() / 2025.37183D));
/*    */     } 
/*    */     
/* 52 */     ENCParameters params = new ENCParameters();
/* 53 */     params.setDisplayCategory(S52Parameters.DisplayCategory.ALL);
/* 54 */     RouteViewerWidget routeWidget = new RouteViewerWidget(Mediador.getInstancia().getGestor().getGestorCartas().getChartsDB(), params, route);
/*    */     
/* 56 */     getControladorDialogoMapa().getContainer().getChildren().add(routeWidget);
/* 57 */     routeWidget.bind((Pane)getControladorDialogoMapa().getContainer());
/* 58 */     routeWidget.view(alarmeArea, resultAlarmesAtivos);
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/dialogos/mapa/rota/DialogoMapaRota.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */