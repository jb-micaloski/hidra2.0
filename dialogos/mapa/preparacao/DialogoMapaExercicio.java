/*    */ package ipqm.gsd.hidra.ihm.dialogos.mapa.preparacao;
/*    */ 
/*    */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*    */ import ipqm.gsd.componentes.dominio.teatro_operacao.TeatroOperacao;
/*    */ import ipqm.gsd.componentes.nucleo.Mediador;
/*    */ import ipqm.gsd.hidra.ihm.controle.Controlador;
/*    */ import ipqm.gsd.hidra.ihm.dialogos.AcaoDialogo;
/*    */ import ipqm.gsd.hidra.ihm.dialogos.mapa.DialogoMapa;
/*    */ import ipqm.gsd.hidra.ihm.projetos.preparacao.janelas.controladores.ControladorExercicioForm;
/*    */ import ipqm.gsd.javafx.widget.PositionWidget;
/*    */ import ipqm.gsd.smaps.model.charts.ChartsDB;
/*    */ import ipqm.gsd.smaps.model.coord.GeoCoord;
/*    */ import ipqm.gsd.smaps.view.vector.ENCParameters;
/*    */ import ipqm.gsd.smaps.view.vector.S52Parameters;
/*    */ import javafx.scene.Node;
/*    */ import javafx.scene.control.Button;
/*    */ import javafx.scene.layout.Pane;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DialogoMapaExercicio
/*    */   extends DialogoMapa
/*    */ {
/*    */   private CoordenadaGeografica coordenadaGeografica;
/* 25 */   private final int escala = 3;
/*    */   
/*    */   public DialogoMapaExercicio(String titulo, String descricao, final Controlador controlador, final TeatroOperacao exercicio) throws Exception {
/* 28 */     super(titulo, descricao, controlador);
/*    */     
/* 30 */     getControladorDialogoMapa().getFundoMapa().setLayoutX(100.0D);
/* 31 */     getControladorDialogoMapa().getFundoMapa().setLayoutY(50.0D);
/* 32 */     getControladorDialogoMapa().getContainer().setPrefSize(1200.0D, 700.0D);
/* 33 */     getControladorDialogoMapa().getFundoMapa().lookupAll("#botaoJanela").forEach(node -> {
/*    */           if (node instanceof Button) {
/*    */             if (!((Button)node).getText().equals("OK")) {
/*    */               ((Button)node).setMaxWidth(200.0D);
/*    */               
/*    */               node.setLayoutY(node.getLayoutY() + 250.0D);
/*    */               node.setLayoutX(570.0D);
/*    */             } else {
/*    */               ((Button)node).setVisible(false);
/*    */             } 
/*    */           }
/*    */         });
/* 45 */     ENCParameters parameters = new ENCParameters();
/* 46 */     parameters.setShowScaleBar(true);
/* 47 */     parameters.setShowNorthArrow(false);
/* 48 */     parameters.setShowUnderScaleIndicator(false);
/* 49 */     parameters.setShowOverScaleIndicator(false);
/* 50 */     parameters.setDisplayCategory(S52Parameters.DisplayCategory.BASE);
/*    */     
/* 52 */     PositionWidget positionWidget = new PositionWidget(Mediador.getInstancia().getGestor().getGestorCartas().getChartsDB(), parameters)
/*    */       {
/*    */         public void finish(GeoCoord gc) {
/* 55 */           DialogoMapaExercicio.this.coordenadaGeografica = new CoordenadaGeografica(gc.getLatitude(), gc.getLongitude());
/* 56 */           exercicio.setLongitude(DialogoMapaExercicio.this.coordenadaGeografica.getLongitude());
/* 57 */           exercicio.setLatitude(DialogoMapaExercicio.this.coordenadaGeografica.getLatitude());
/* 58 */           String longitude = CoordenadaGeografica.longitudeToString(DialogoMapaExercicio.this.coordenadaGeografica.getLongitude());
/* 59 */           String latitude = CoordenadaGeografica.latitudeToString(DialogoMapaExercicio.this.coordenadaGeografica.getLatitude());
/* 60 */           ((ControladorExercicioForm)controlador).getTxLongitude().setText(longitude);
/* 61 */           ((ControladorExercicioForm)controlador).getTxLatitude().setText(latitude);
/* 62 */           DialogoMapaExercicio.this.ocultar();
/*    */         }
/*    */       };
/* 65 */     positionWidget.bind((Pane)getControladorDialogoMapa().getContainer());
/* 66 */     if (exercicio.getLatitude() == 0.0D || exercicio.getLongitude() == 0.0D) {
/* 67 */       positionWidget.view(-43.154D, -22.86D, 3.0D);
/*    */     } else {
/* 69 */       positionWidget.view(exercicio.getLongitude(), exercicio.getLatitude(), 3.0D);
/*    */     } 
/* 71 */     getControladorDialogoMapa().getContainer().getChildren().add(positionWidget);
/*    */   }
/*    */   
/*    */   public void acao(AcaoDialogo acao) {}
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/dialogos/mapa/preparacao/DialogoMapaExercicio.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */