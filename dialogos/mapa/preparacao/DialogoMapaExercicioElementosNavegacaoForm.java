/*     */ package ipqm.gsd.hidra.ihm.dialogos.mapa.preparacao;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaPolar;
/*     */ import ipqm.gsd.componentes.dominio.teatro_operacao.CondicaoEntidade;
/*     */ import ipqm.gsd.componentes.dominio.teatro_operacao.TeatroOperacao;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.hidra.ihm.controle.Controlador;
/*     */ import ipqm.gsd.hidra.ihm.dialogos.AcaoDialogo;
/*     */ import ipqm.gsd.hidra.ihm.dialogos.mapa.DialogoMapa;
/*     */ import ipqm.gsd.hidra.ihm.projetos.preparacao.janelas.controladores.ControladorExercicioElementosNavegacaoForm;
/*     */ import ipqm.gsd.javafx.widget.ShipInfo;
/*     */ import ipqm.gsd.javafx.widget.ShipWidget;
/*     */ import ipqm.gsd.smaps.model.charts.ChartsDB;
/*     */ import ipqm.gsd.smaps.view.vector.ENCParameters;
/*     */ import ipqm.gsd.smaps.view.vector.S52Parameters;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.layout.Pane;
/*     */ 
/*     */ 
/*     */ public class DialogoMapaExercicioElementosNavegacaoForm
/*     */   extends DialogoMapa
/*     */ {
/*     */   private CoordenadaGeografica coordenadaGeografica;
/*     */   private CondicaoEntidade ce;
/*  29 */   private final int escala = 3;
/*     */   private int velocidade;
/*     */   private int rumo;
/*     */   private TeatroOperacao teatro;
/*     */   
/*     */   public DialogoMapaExercicioElementosNavegacaoForm(String titulo, String descricao, Controlador controladorPai, CondicaoEntidade condicaoEntidade, TeatroOperacao exercicio, Boolean estado, List<CondicaoEntidade> lista) throws Exception {
/*  35 */     super(titulo, descricao, controladorPai);
/*     */     
/*  37 */     getControladorDialogoMapa().getFundoMapa().setLayoutX(100.0D);
/*  38 */     getControladorDialogoMapa().getFundoMapa().setLayoutY(50.0D);
/*  39 */     getControladorDialogoMapa().getContainer().setPrefSize(1200.0D, 700.0D);
/*  40 */     getControladorDialogoMapa().getFundoMapa().lookupAll("#botaoJanela").forEach(node -> {
/*     */           if (node instanceof Button) {
/*     */             if (((Button)node).getText().equals("OK")) {
/*     */               node.setLayoutY(node.getLayoutY() + 250.0D);
/*     */               
/*     */               node.setLayoutX(480.0D);
/*     */             } else {
/*     */               ((Button)node).setMaxWidth(200.0D);
/*     */               node.setLayoutY(node.getLayoutY() + 250.0D);
/*     */               node.setLayoutX(620.0D);
/*     */             } 
/*     */           }
/*     */         });
/*  53 */     ENCParameters parameters = new ENCParameters();
/*  54 */     parameters.setShowScaleBar(true);
/*  55 */     parameters.setShowNorthArrow(false);
/*  56 */     parameters.setShowUnderScaleIndicator(false);
/*  57 */     parameters.setShowOverScaleIndicator(false);
/*  58 */     parameters.setDisplayCategory(S52Parameters.DisplayCategory.BASE);
/*     */     
/*  60 */     this.ce = condicaoEntidade;
/*  61 */     this.teatro = exercicio;
/*     */     
/*  63 */     ShipInfo shipInfo = null;
/*  64 */     List<ShipInfo> ships = null;
/*     */     
/*  66 */     if (!estado.booleanValue()) {
/*  67 */       ships = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  73 */       shipInfo = new ShipInfo(condicaoEntidade.getIdentificador(), condicaoEntidade.getLongitude(), condicaoEntidade.getLatitude(), condicaoEntidade.getRumo(), condicaoEntidade.getVelocidade());
/*  74 */       ships.add(shipInfo);
/*     */       
/*  76 */       if (!lista.isEmpty()) {
/*  77 */         for (CondicaoEntidade veiculo : lista) {
/*  78 */           if (!veiculo.getIdentificador().equals(shipInfo.getName())) {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*  83 */             ShipInfo ship = new ShipInfo(veiculo.getIdentificador(), veiculo.getLongitude(), veiculo.getLatitude(), veiculo.getRumo(), veiculo.getVelocidade());
/*  84 */             ships.add(ship);
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } else {
/*  89 */       ships = new ArrayList<>();
/*  90 */       if (!lista.isEmpty()) {
/*  91 */         for (CondicaoEntidade veiculo : lista) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*  96 */           ShipInfo ship = new ShipInfo(veiculo.getIdentificador(), veiculo.getLongitude(), veiculo.getLatitude(), veiculo.getRumo(), veiculo.getVelocidade());
/*  97 */           ships.add(ship);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 102 */     ShipWidget shipWidget = new ShipWidget(Mediador.getInstancia().getGestor().getGestorCartas().getChartsDB(), parameters, shipInfo, ships)
/*     */       {
/*     */         public void finish(ShipInfo si) {
/* 105 */           DialogoMapaExercicioElementosNavegacaoForm.this.coordenadaGeografica = new CoordenadaGeografica(si.getLatitude(), si.getLongitude());
/* 106 */           DialogoMapaExercicioElementosNavegacaoForm.this.velocidade = (int)si.getSpeed();
/* 107 */           DialogoMapaExercicioElementosNavegacaoForm.this.rumo = (int)si.getCourse();
/*     */         }
/*     */       };
/*     */     
/* 111 */     if (this.ce.getLatitude() == 0.0D || this.ce
/* 112 */       .getLongitude() == 0.0D) {
/* 113 */       shipWidget.view(this.teatro.getLongitude(), this.teatro.getLatitude(), 3.0D);
/*     */     } else {
/* 115 */       shipWidget.view(this.ce.getLongitude(), this.ce.getLatitude(), 3.0D);
/*     */     } 
/*     */     
/* 118 */     shipWidget.bind((Pane)getControladorDialogoMapa().getContainer());
/* 119 */     getControladorDialogoMapa()
/* 120 */       .getContainer().getChildren().add(shipWidget);
/*     */   }
/*     */ 
/*     */   
/*     */   public void acao(AcaoDialogo acao) {
/* 125 */     if (acao.equals(AcaoDialogo.OK)) {
/* 126 */       this.ce.setLatitude(this.coordenadaGeografica.getLatitude());
/* 127 */       this.ce.setLongitude(this.coordenadaGeografica.getLongitude());
/*     */       
/* 129 */       String longitude = CoordenadaGeografica.longitudeToString(this.coordenadaGeografica.getLongitude());
/* 130 */       String latitude = CoordenadaGeografica.latitudeToString(this.coordenadaGeografica.getLatitude());
/* 131 */       ((ControladorExercicioElementosNavegacaoForm)this.controladorPai).getTxLongitude().setText(longitude);
/* 132 */       ((ControladorExercicioElementosNavegacaoForm)this.controladorPai).getTxLatitude().setText(latitude);
/*     */       
/* 134 */       CoordenadaPolar cp = CoordenadaPolar.converterCoordenadaGeografica(this.teatro.getPosicaoTeatroOperacao().getCoordenadaGeografica(), this.coordenadaGeografica);
/* 135 */       ((ControladorExercicioElementosNavegacaoForm)this.controladorPai).getTxMarcacao().setText(String.valueOf((int)cp.getMarcacao()));
/* 136 */       ((ControladorExercicioElementosNavegacaoForm)this.controladorPai).getTxDistancia().setText(String.valueOf((int)cp.getDistancia()));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/dialogos/mapa/preparacao/DialogoMapaExercicioElementosNavegacaoForm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */