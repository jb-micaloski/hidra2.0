/*     */ package ipqm.gsd.hidra.ihm.dialogos.mapa.preparacao;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaPolar;
/*     */ import ipqm.gsd.componentes.dominio.entidades.VeiculoSubmarino;
/*     */ import ipqm.gsd.componentes.dominio.teatro_operacao.CondicaoEntidade;
/*     */ import ipqm.gsd.componentes.dominio.teatro_operacao.TeatroOperacao;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.util.ConversorUnidades;
/*     */ import ipqm.gsd.hidra.ihm.controle.Controlador;
/*     */ import ipqm.gsd.hidra.ihm.dialogos.AcaoDialogo;
/*     */ import ipqm.gsd.hidra.ihm.dialogos.mapa.DialogoMapa;
/*     */ import ipqm.gsd.hidra.ihm.projetos.preparacao.janelas.controladores.ControladorExercicioVeiculoSimPerForm;
/*     */ import ipqm.gsd.javafx.widget.ShipInfo;
/*     */ import ipqm.gsd.javafx.widget.ShipWidget;
/*     */ import ipqm.gsd.smaps.model.charts.ChartsDB;
/*     */ import ipqm.gsd.smaps.view.vector.ENCParameters;
/*     */ import ipqm.gsd.smaps.view.vector.S52Parameters;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.layout.Pane;
/*     */ 
/*     */ 
/*     */ public class DialogoMapaExercicioSimPerForm
/*     */   extends DialogoMapa
/*     */ {
/*     */   private CoordenadaGeografica coordenadaGeografica;
/*     */   private CondicaoEntidade ce;
/*  33 */   private final int escala = 3;
/*     */   
/*     */   private int velocidade;
/*  36 */   DecimalFormat fRange = new DecimalFormat(".######"); private int rumo; private TeatroOperacao teatro;
/*  37 */   DecimalFormat fMarcacao = new DecimalFormat(".#");
/*     */ 
/*     */   
/*     */   public DialogoMapaExercicioSimPerForm(String titulo, String descricao, Controlador controladorPai, CondicaoEntidade condicaoEntidade, TeatroOperacao exercicio, Boolean estado, List<CondicaoEntidade> lista) throws Exception {
/*  41 */     super(titulo, descricao, controladorPai);
/*     */     
/*  43 */     getControladorDialogoMapa().getFundoMapa().setLayoutX(100.0D);
/*  44 */     getControladorDialogoMapa().getFundoMapa().setLayoutY(50.0D);
/*  45 */     getControladorDialogoMapa().getContainer().setPrefSize(1200.0D, 700.0D);
/*     */     
/*  47 */     getControladorDialogoMapa().getFundoMapa().lookupAll("#botaoJanela").forEach(node -> {
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
/*  60 */     ENCParameters parameters = new ENCParameters();
/*  61 */     parameters.setShowScaleBar(true);
/*  62 */     parameters.setShowNorthArrow(false);
/*  63 */     parameters.setShowUnderScaleIndicator(false);
/*  64 */     parameters.setShowOverScaleIndicator(false);
/*  65 */     parameters.setDisplayCategory(S52Parameters.DisplayCategory.BASE);
/*     */     
/*  67 */     this.ce = condicaoEntidade;
/*  68 */     this.teatro = exercicio;
/*     */     
/*  70 */     ShipInfo shipInfo = null;
/*  71 */     List<ShipInfo> ships = null;
/*     */     
/*  73 */     if (!estado.booleanValue()) {
/*  74 */       ships = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  80 */       shipInfo = new ShipInfo(condicaoEntidade.getIdentificador(), condicaoEntidade.getLongitude(), condicaoEntidade.getLatitude(), condicaoEntidade.getRumo(), condicaoEntidade.getVelocidade());
/*  81 */       ships.add(shipInfo);
/*     */       
/*  83 */       if (!lista.isEmpty()) {
/*  84 */         for (CondicaoEntidade veiculo : lista) {
/*  85 */           if (!veiculo.getIdentificador().equals(shipInfo.getName())) {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*  90 */             ShipInfo ship = new ShipInfo(veiculo.getIdentificador(), veiculo.getLongitude(), veiculo.getLatitude(), veiculo.getRumo(), veiculo.getVelocidade());
/*  91 */             ships.add(ship);
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } else {
/*  96 */       ships = new ArrayList<>();
/*  97 */       if (!lista.isEmpty()) {
/*  98 */         for (CondicaoEntidade veiculo : lista) {
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 103 */           ShipInfo ship = new ShipInfo(veiculo.getIdentificador(), veiculo.getLongitude(), veiculo.getLatitude(), veiculo.getRumo(), veiculo.getVelocidade());
/* 104 */           ships.add(ship);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 109 */     ShipWidget shipWidget = new ShipWidget(Mediador.getInstancia().getGestor().getGestorCartas().getChartsDB(), parameters, shipInfo, ships)
/*     */       {
/*     */         public void finish(ShipInfo si)
/*     */         {
/* 113 */           DialogoMapaExercicioSimPerForm.this.coordenadaGeografica = new CoordenadaGeografica(si.getLatitude(), si.getLongitude());
/* 114 */           DialogoMapaExercicioSimPerForm.this.velocidade = (int)si.getSpeed();
/* 115 */           DialogoMapaExercicioSimPerForm.this.rumo = (int)si.getCourse();
/*     */         }
/*     */       };
/*     */     
/* 119 */     if (this.ce.getLatitude() == 0.0D || this.ce.getLongitude() == 0.0D) {
/* 120 */       shipWidget.view(this.teatro.getLongitude(), this.teatro.getLatitude(), 3.0D);
/*     */     } else {
/* 122 */       shipWidget.view(this.ce.getLongitude(), this.ce.getLatitude(), 3.0D);
/*     */     } 
/*     */     
/* 125 */     shipWidget.bind((Pane)getControladorDialogoMapa().getContainer());
/* 126 */     getControladorDialogoMapa().getContainer().getChildren().add(shipWidget);
/*     */   }
/*     */ 
/*     */   
/*     */   public void acao(AcaoDialogo acao) {
/* 131 */     if (acao.equals(AcaoDialogo.OK)) {
/* 132 */       ((ControladorExercicioVeiculoSimPerForm)this.controladorPai).setLongitude(Double.valueOf(this.coordenadaGeografica.getLongitude()));
/* 133 */       ((ControladorExercicioVeiculoSimPerForm)this.controladorPai).setLatitude(Double.valueOf(this.coordenadaGeografica.getLatitude()));
/*     */       
/* 135 */       this.ce.setRumo(this.rumo);
/* 136 */       ((ControladorExercicioVeiculoSimPerForm)this.controladorPai).getTxHeading().setText(String.valueOf(this.rumo));
/*     */       
/* 138 */       this.ce.setVelocidade(this.velocidade);
/* 139 */       ((ControladorExercicioVeiculoSimPerForm)this.controladorPai).getTxSpeed().setText(String.valueOf(this.velocidade));
/*     */       
/* 141 */       if (!((ControladorExercicioVeiculoSimPerForm)this.controladorPai).getCkPrimario().isSelected() && ((ControladorExercicioVeiculoSimPerForm)this.controladorPai)
/* 142 */         .getTabelaMaqVeic().getItems().isEmpty())
/*     */         try {
/* 144 */           VeiculoSubmarino primario = (new VeiculoSubmarino()).obtemVeiculoPelaCondicaoVeiculo(this.teatro.obtemIdCondicaoByTeatroOperacao(this.teatro.getIdTeatroOperacao()));
/* 145 */           double rumoPrimario = (new VeiculoSubmarino()).obtemRumoDoVeiculoPelaCondicaoVeiculo(this.teatro.obtemIdCondicaoByTeatroOperacao(this.teatro.getIdTeatroOperacao()));
/* 146 */           CoordenadaPolar cp = CoordenadaPolar.converterCoordenadaGeografica(primario.getPosicao().getCoordenadaGeografica(), this.coordenadaGeografica);
/*     */           
/* 148 */           if (rumoPrimario < cp.getMarcacao()) {
/* 149 */             rumoPrimario += 360.0D;
/*     */           }
/*     */           
/* 152 */           double marcacao = cp.getMarcacao() - rumoPrimario;
/*     */           
/* 154 */           if (marcacao < 0.0D) {
/* 155 */             marcacao += 360.0D;
/*     */           }
/*     */           
/* 158 */           ((ControladorExercicioVeiculoSimPerForm)this.controladorPai).getTxBearing().setText(this.fMarcacao.format(marcacao));
/* 159 */           ((ControladorExercicioVeiculoSimPerForm)this.controladorPai).setBearing(Double.valueOf(this.fMarcacao.format(marcacao)).doubleValue());
/* 160 */           double kiloJardas = ConversorUnidades.milhasNauticasParaJardas(cp.getDistancia()) / 1000.0D;
/* 161 */           ((ControladorExercicioVeiculoSimPerForm)this.controladorPai).getTxRange().setText(this.fRange.format(kiloJardas));
/* 162 */           ((ControladorExercicioVeiculoSimPerForm)this.controladorPai).setRange(Double.valueOf(this.fRange.format(kiloJardas)).doubleValue());
/* 163 */         } catch (Exception ex) {
/* 164 */           Log.gravarLogDebug("Erro ao apresentar veículo referencial.", ex);
/*     */         }  
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/dialogos/mapa/preparacao/DialogoMapaExercicioSimPerForm.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */