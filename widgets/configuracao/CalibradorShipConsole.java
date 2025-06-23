/*     */ package ipqm.gsd.hidra.ihm.widgets.configuracao;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.Ambiente;
/*     */ import ipqm.gsd.componentes.nucleo.VariaveisEstado;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.notificador.Notificador;
/*     */ import ipqm.gsd.hidra.ihm.dispositivos.controladores_jogo.ship_console.ShipConsoleController;
/*     */ import javafx.application.Application;
/*     */ import javafx.application.Platform;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.Parent;
/*     */ import javafx.scene.Scene;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.Label;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ import javafx.scene.layout.VBox;
/*     */ import javafx.stage.Stage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CalibradorShipConsole
/*     */   extends Application
/*     */ {
/*     */   public void start(Stage primaryStage) {
/*  27 */     Label label = new Label("Coloque o telegrafo na posição desejada e aperte o botão adequado");
/*  28 */     Label labelDescricaoValorAtual = new Label("ValorAtual:   ");
/*  29 */     Label labelValor = new Label("");
/*     */     
/*  31 */     Button btnCalibrarMinimoTelegrafoBB = new Button();
/*  32 */     btnCalibrarMinimoTelegrafoBB.setText("Valor Mínimo Telegrafo BB");
/*  33 */     btnCalibrarMinimoTelegrafoBB.setOnAction(event -> {
/*     */           ShipConsoleController.setValorMinimoBB(ShipConsoleController.getValorAtualTelegrafo());
/*     */           
/*     */           Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.TELEGRAFO_BB_MIN, String.valueOf(ShipConsoleController.getValorMinimoBB()));
/*     */         });
/*  38 */     Button btnCalibrarMaximoTelegrafoBB = new Button();
/*  39 */     btnCalibrarMaximoTelegrafoBB.setText("Valor Máximo Telegrafo BB");
/*  40 */     btnCalibrarMaximoTelegrafoBB.setOnAction(event -> {
/*     */           ShipConsoleController.setValorMaximoBB(ShipConsoleController.getValorAtualTelegrafo());
/*     */           
/*     */           Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.TELEGRAFO_BB_MAX, String.valueOf(ShipConsoleController.getValorMaximoBB()));
/*     */         });
/*     */     
/*  46 */     Button btnCalibrarMeioMinTelegrafoBB = new Button();
/*  47 */     btnCalibrarMeioMinTelegrafoBB.setText("Valor min centro telegrafo BB");
/*  48 */     btnCalibrarMeioMinTelegrafoBB.setOnAction(event -> {
/*     */           ShipConsoleController.setValorMinimoMeioBB(ShipConsoleController.getValorAtualTelegrafo());
/*     */           
/*     */           Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.TELEGRAFO_BB_MEIO_MIN, String.valueOf(ShipConsoleController.getValorMinimoMeioBB()));
/*     */         });
/*  53 */     Button btnCalibrarMeioMaxTelegrafoBB = new Button();
/*  54 */     btnCalibrarMeioMaxTelegrafoBB.setText("Valor max centro telegrafo BB");
/*  55 */     btnCalibrarMeioMaxTelegrafoBB.setOnAction(event -> {
/*     */           ShipConsoleController.setValorMaximoMeioBB(ShipConsoleController.getValorAtualTelegrafo());
/*     */           
/*     */           Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.TELEGRAFO_BB_MEIO_MAX, String.valueOf(ShipConsoleController.getValorMaximoMeioBB()));
/*     */         });
/*  60 */     Button btnCalibrarMinimoTelegrafoBE = new Button();
/*  61 */     btnCalibrarMinimoTelegrafoBE.setText("Valor mínimo telegrafo BE");
/*  62 */     btnCalibrarMinimoTelegrafoBE.setOnAction(event -> {
/*     */           ShipConsoleController.setValorMinimoBE(ShipConsoleController.getValorAtualTelegrafo());
/*     */           
/*     */           Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.TELEGRAFO_BE_MIN, String.valueOf(ShipConsoleController.getValorMinimoBE()));
/*     */         });
/*  67 */     Button btnCalibrarMaximoTelegrafoBE = new Button();
/*  68 */     btnCalibrarMaximoTelegrafoBE.setText("Valor máximo telegrafo BE");
/*  69 */     btnCalibrarMaximoTelegrafoBE.setOnAction(event -> {
/*     */           ShipConsoleController.setValorMaximoBE(ShipConsoleController.getValorAtualTelegrafo());
/*     */           
/*     */           Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.TELEGRAFO_BE_MAX, String.valueOf(ShipConsoleController.getValorMaximoBE()));
/*     */         });
/*  74 */     Button btnCalibrarMeioMinTelegrafoBE = new Button();
/*  75 */     btnCalibrarMeioMinTelegrafoBE.setText("Valor min centro telegrafo BE");
/*  76 */     btnCalibrarMeioMinTelegrafoBE.setOnAction(event -> {
/*     */           ShipConsoleController.setValorMinimoMeioBE(ShipConsoleController.getValorAtualTelegrafo());
/*     */           
/*     */           Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.TELEGRAFO_BE_MEIO_MIN, String.valueOf(ShipConsoleController.getValorMinimoMeioBE()));
/*     */         });
/*  81 */     Button btnCalibrarMeioMaxTelegrafoBE = new Button();
/*  82 */     btnCalibrarMeioMaxTelegrafoBE.setText("Valor max centro telegrafo BE");
/*  83 */     btnCalibrarMeioMaxTelegrafoBE.setOnAction(event -> {
/*     */           ShipConsoleController.setValorMaximoMeioBE(ShipConsoleController.getValorAtualTelegrafo());
/*     */           
/*     */           Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.TELEGRAFO_BE_MEIO_MAX, String.valueOf(ShipConsoleController.getValorMaximoMeioBE()));
/*     */         });
/*  88 */     Button btnSalvar = new Button("Salvar");
/*  89 */     btnSalvar.setOnAction(event -> {
/*     */           Ambiente.getInstance().persisteVariavelAmbiente("estado.properties");
/*     */           
/*     */           Notificador.informacao("Calibração salva com sucesso", "");
/*     */         });
/*  94 */     VBox vboxBB = new VBox(20.0D);
/*  95 */     vboxBB.getChildren().addAll((Object[])new Node[] { (Node)btnCalibrarMaximoTelegrafoBB, (Node)btnCalibrarMinimoTelegrafoBB, (Node)btnCalibrarMeioMinTelegrafoBB, (Node)btnCalibrarMeioMaxTelegrafoBB });
/*     */     
/*  97 */     VBox vboxBE = new VBox(20.0D);
/*  98 */     vboxBE.getChildren().addAll((Object[])new Node[] { (Node)btnCalibrarMaximoTelegrafoBE, (Node)btnCalibrarMinimoTelegrafoBE, (Node)btnCalibrarMeioMinTelegrafoBE, (Node)btnCalibrarMeioMaxTelegrafoBE });
/*     */     
/* 100 */     AnchorPane root = new AnchorPane();
/* 101 */     root.getStylesheets().clear();
/* 102 */     root.getStylesheets().setAll((Object[])new String[] { "ipqm/gsd/hidra/ihm/css/widgets/configuracao.css" });
/* 103 */     root.getChildren().addAll((Object[])new Node[] { (Node)label, (Node)vboxBB, (Node)vboxBE, (Node)labelDescricaoValorAtual, (Node)labelValor, (Node)btnSalvar });
/*     */     
/* 105 */     Scene scene = new Scene((Parent)root, 600.0D, 350.0D);
/*     */     
/* 107 */     label.setLayoutX(20.0D);
/* 108 */     label.setLayoutY(20.0D);
/* 109 */     labelDescricaoValorAtual.setLayoutX(20.0D);
/* 110 */     labelDescricaoValorAtual.setLayoutY(300.0D);
/* 111 */     labelValor.setLayoutX(100.0D);
/* 112 */     labelValor.setLayoutY(300.0D);
/*     */     
/* 114 */     vboxBB.setLayoutX(20.0D);
/* 115 */     vboxBB.setLayoutY(80.0D);
/*     */     
/* 117 */     vboxBE.setLayoutX(300.0D);
/* 118 */     vboxBE.setLayoutY(80.0D);
/*     */     
/* 120 */     btnSalvar.setLayoutX(250.0D);
/* 121 */     btnSalvar.setLayoutY(300.0D);
/*     */     
/* 123 */     primaryStage.setTitle("Calibrar Telegrafo");
/* 124 */     primaryStage.setScene(scene);
/* 125 */     primaryStage.show();
/*     */     
/* 127 */     (new Thread(() -> {
/*     */           while (primaryStage.isShowing()) {
/*     */             Platform.runLater(());
/*     */ 
/*     */             
/*     */             try {
/*     */               Thread.sleep(100L);
/* 134 */             } catch (Exception ex) {
/*     */               Log.gravarLogExcecao("Erro no sleep", this, ex);
/*     */             } 
/*     */           } 
/* 138 */         })).start();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/* 146 */     launch(args);
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/configuracao/CalibradorShipConsole.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */