/*     */ package ipqm.gsd.hidra.ihm.configuracao;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.Ambiente;
/*     */ import ipqm.gsd.componentes.nucleo.NucleoAmbiente;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.util.ComandosSO;
/*     */ import ipqm.gsd.hidra.ihm.util.Argumentos;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import javafx.application.Application;
/*     */ import javafx.collections.FXCollections;
/*     */ import javafx.collections.ObservableList;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.event.Event;
/*     */ import javafx.event.EventHandler;
/*     */ import javafx.geometry.Insets;
/*     */ import javafx.geometry.Orientation;
/*     */ import javafx.geometry.Pos;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.Parent;
/*     */ import javafx.scene.Scene;
/*     */ import javafx.scene.control.Alert;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.ButtonType;
/*     */ import javafx.scene.control.CheckBox;
/*     */ import javafx.scene.control.ComboBox;
/*     */ import javafx.scene.control.Label;
/*     */ import javafx.scene.control.PasswordField;
/*     */ import javafx.scene.control.Separator;
/*     */ import javafx.scene.control.Tab;
/*     */ import javafx.scene.control.TabPane;
/*     */ import javafx.scene.control.TextField;
/*     */ import javafx.scene.control.Tooltip;
/*     */ import javafx.scene.image.Image;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ import javafx.scene.layout.HBox;
/*     */ import javafx.scene.layout.VBox;
/*     */ import javafx.stage.Stage;
/*     */ import javafx.stage.WindowEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Configurador
/*     */   extends Application
/*     */ {
/*     */   private static final int WIDTH = 830;
/*     */   private static final int HEIGHT = 650;
/*     */   private Map<NucleoAmbiente, Node> campos;
/*     */   private String argumentos;
/*     */   private AnchorPane root;
/*     */   
/*     */   public void start(Stage primaryStage) {
/*  60 */     this.campos = new HashMap<>();
/*  61 */     this.argumentos = new String();
/*     */     
/*  63 */     this.root = new AnchorPane();
/*     */     
/*  65 */     Node node = criaInterface();
/*  66 */     AnchorPane.setTopAnchor(node, Double.valueOf(0.0D));
/*  67 */     AnchorPane.setLeftAnchor(node, Double.valueOf(0.0D));
/*  68 */     AnchorPane.setRightAnchor(node, Double.valueOf(0.0D));
/*  69 */     AnchorPane.setBottomAnchor(node, Double.valueOf(0.0D));
/*  70 */     this.root.getChildren().add(node);
/*  71 */     Scene scene = new Scene((Parent)this.root, 830.0D, 650.0D);
/*     */     
/*  73 */     primaryStage.setTitle("Hidra - Configuração");
/*  74 */     primaryStage.getIcons().add(new Image("ipqm/gsd/hidra/ihm/imagens/iconeHIDRA.png"));
/*  75 */     primaryStage.setScene(scene);
/*  76 */     primaryStage.setResizable(false);
/*  77 */     primaryStage.show();
/*     */     
/*  79 */     primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>()
/*     */         {
/*     */           public void handle(WindowEvent t) {
/*  82 */             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
/*  83 */             alert.setTitle("Sair");
/*  84 */             alert.setHeaderText("Deseja realmente sair?");
/*  85 */             alert.setContentText("As informações não serão salvas");
/*  86 */             ButtonType buttonLogoff = new ButtonType("Sim");
/*  87 */             ButtonType buttonCancelar = new ButtonType("Não");
/*     */             
/*  89 */             alert.getButtonTypes().setAll((Object[])new ButtonType[] { buttonLogoff, buttonCancelar });
/*     */             
/*  91 */             Optional<ButtonType> result = alert.showAndWait();
/*  92 */             if (result.get() == buttonCancelar) {
/*  93 */               alert.close();
/*  94 */               t.consume();
/*     */             } else {
/*  96 */               System.exit(0);
/*     */             } 
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private Node criaInterface() {
/* 103 */     TabPane tabPane = new TabPane();
/*     */     
/* 105 */     for (NucleoAmbiente.Grupos grupo : NucleoAmbiente.Grupos.values()) {
/* 106 */       Tab tab = new Tab(grupo.getNome());
/* 107 */       tab.setClosable(false);
/* 108 */       Node node = criaGrupo(grupo);
/* 109 */       tab.setContent(node);
/* 110 */       tabPane.getTabs().add(tab);
/*     */     } 
/*     */     
/* 113 */     Button botaoOK = new Button("Salvar e iniciar");
/* 114 */     Button botaoCancelar = new Button("Cancelar");
/* 115 */     Button botaoSair = new Button("Sair");
/*     */     
/* 117 */     botaoOK.setDefaultButton(true);
/* 118 */     botaoCancelar.setCancelButton(true);
/*     */     
/* 120 */     botaoOK.setOnAction(new EventHandler<ActionEvent>()
/*     */         {
/*     */           public void handle(ActionEvent e) {
/* 123 */             Configurador.this.acaoOk();
/*     */           }
/*     */         });
/* 126 */     botaoCancelar.setOnAction(new EventHandler<ActionEvent>()
/*     */         {
/*     */           public void handle(ActionEvent e) {
/* 129 */             Configurador.this.acaoCancelar();
/*     */           }
/*     */         });
/* 132 */     botaoSair.setOnAction(new EventHandler<ActionEvent>()
/*     */         {
/*     */           public void handle(ActionEvent e) {
/* 135 */             Configurador.this.acaoSair();
/*     */           }
/*     */         });
/*     */     
/* 139 */     ObservableList<String> args = FXCollections.observableArrayList();
/*     */     
/* 141 */     args.add("log=debug");
/* 142 */     args.add("log=interface");
/*     */     
/* 144 */     for (Argumentos argumento : Argumentos.values()) {
/* 145 */       args.add(argumento.getArgumento());
/*     */     }
/*     */     
/* 148 */     args.remove(Argumentos.LOG.getArgumento());
/* 149 */     args.remove(Argumentos.HELP.getArgumento());
/* 150 */     args.remove(Argumentos.CONFIGURACAO.getArgumento());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 159 */     HBox hBox = new HBox(new Node[] { (Node)new Label("Argumentos:"), (Node)botaoOK, (Node)botaoCancelar, (Node)botaoSair });
/*     */ 
/*     */     
/* 162 */     hBox.setSpacing(10.0D);
/* 163 */     hBox.setAlignment(Pos.CENTER);
/* 164 */     hBox.setPadding(new Insets(20.0D, 0.0D, 20.0D, 0.0D));
/* 165 */     VBox vbox = new VBox(new Node[] { (Node)tabPane, (Node)new Separator(Orientation.HORIZONTAL), (Node)hBox });
/* 166 */     return (Node)vbox;
/*     */   }
/*     */   
/*     */   private Node criaGrupo(NucleoAmbiente.Grupos grupo) {
/* 170 */     VBox vbox = new VBox(15.0D);
/* 171 */     vbox.setPadding(new Insets(30.0D));
/*     */     
/* 173 */     for (NucleoAmbiente na : NucleoAmbiente.values()) {
/* 174 */       if (na.getNome() != null && na.getGrupo() == grupo) {
/*     */         ComboBox comboBox;
/* 176 */         Label label = new Label(na.getNome() + ":");
/* 177 */         label.setMinWidth(300.0D);
/* 178 */         label.setMaxWidth(300.0D);
/*     */         
/* 180 */         label.setPadding(new Insets(0.0D, 50.0D, 0.0D, 0.0D));
/*     */         
/* 182 */         Node campo = null;
/* 183 */         List<String> valoresPermitidos = na.getValoresPermitidos();
/* 184 */         String valor = Ambiente.getInstance().obterValorVariavelAmbiente(na);
/* 185 */         if (valor.isEmpty() && !na.getValorPadrao().isEmpty()) {
/* 186 */           valor = na.getValorPadrao();
/*     */         }
/* 188 */         if (valoresPermitidos.isEmpty()) {
/*     */           
/* 190 */           if (na.isSenha()) {
/* 191 */             PasswordField passwordField = new PasswordField();
/* 192 */             passwordField.setText(valor);
/* 193 */             passwordField.setMinWidth(250.0D);
/*     */           } else {
/* 195 */             TextField textField = new TextField(valor);
/* 196 */             textField.setMinWidth(250.0D);
/*     */           }
/*     */         
/* 199 */         } else if (valoresPermitidos.size() == 2 && valoresPermitidos.contains("SIM") && valoresPermitidos.contains("NAO")) {
/* 200 */           CheckBox checkBox = new CheckBox("Habilitado(a)");
/* 201 */           checkBox.setSelected(valor.equals("SIM"));
/*     */         } else {
/* 203 */           ObservableList<String> options = FXCollections.observableArrayList(valoresPermitidos);
/* 204 */           comboBox = new ComboBox(options);
/* 205 */           if (valoresPermitidos.contains(valor)) {
/* 206 */             comboBox.getSelectionModel().select(valor);
/*     */           } else {
/* 208 */             comboBox.getSelectionModel().select(na.getValorPadrao());
/*     */           } 
/*     */         } 
/*     */         
/* 212 */         HBox hbox = new HBox(new Node[] { (Node)label, (Node)comboBox });
/* 213 */         if (!na.getDica().isEmpty()) {
/* 214 */           Label labelDica = new Label("  ?  ");
/* 215 */           labelDica.setTooltip(new Tooltip(na.getDica()));
/* 216 */           hbox.getChildren().add(labelDica);
/*     */         } 
/* 218 */         vbox.getChildren().add(hbox);
/* 219 */         this.campos.put(na, comboBox);
/*     */       } 
/*     */     } 
/*     */     
/* 223 */     return (Node)vbox;
/*     */   }
/*     */   
/*     */   private void acaoOk() {
/* 227 */     this.root.setDisable(true);
/* 228 */     (new Thread(new ExecutarOk())).start();
/*     */   }
/*     */   
/*     */   private void acaoCancelar() {
/* 232 */     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
/* 233 */     alert.setTitle("Cancelar");
/* 234 */     alert.setHeaderText("Deseja realmente cancelar?");
/* 235 */     alert.setContentText("As informações não serão salvas");
/* 236 */     ButtonType buttonLogoff = new ButtonType("Sim");
/* 237 */     ButtonType buttonCancelar = new ButtonType("Não");
/*     */     
/* 239 */     alert.getButtonTypes().setAll((Object[])new ButtonType[] { buttonLogoff, buttonCancelar });
/*     */     
/* 241 */     Optional<ButtonType> result = alert.showAndWait();
/* 242 */     if (result.get() == buttonCancelar) {
/* 243 */       alert.close();
/*     */     } else {
/* 245 */       ComandosSO.enviarComando(ComandosSO.Comando.REINICIAR_APLICACAO);
/*     */       try {
/* 247 */         Thread.sleep(1000L);
/* 248 */       } catch (InterruptedException ex) {
/* 249 */         Log.gravarLogExcecao("Erro no sleep.", this, ex);
/*     */       } 
/* 251 */       System.exit(0);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void acaoSair() {
/* 256 */     Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
/* 257 */     alert.setTitle("Sair");
/* 258 */     alert.setHeaderText("Deseja realmente sair?");
/* 259 */     alert.setContentText("As informações não serão salvas");
/* 260 */     ButtonType buttonLogoff = new ButtonType("Sim");
/* 261 */     ButtonType buttonCancelar = new ButtonType("Não");
/*     */     
/* 263 */     alert.getButtonTypes().setAll((Object[])new ButtonType[] { buttonLogoff, buttonCancelar });
/*     */     
/* 265 */     Optional<ButtonType> result = alert.showAndWait();
/* 266 */     if (result.get() == buttonCancelar) {
/* 267 */       alert.close();
/*     */     } else {
/* 269 */       System.exit(0);
/*     */     } 
/*     */   }
/*     */   
/*     */   private class ExecutarOk implements Runnable {
/*     */     private ExecutarOk() {}
/*     */     
/*     */     public void run() {
/* 277 */       Iterator<NucleoAmbiente> it = Configurador.this.campos.keySet().iterator();
/* 278 */       while (it.hasNext()) {
/* 279 */         NucleoAmbiente na = it.next();
/* 280 */         Node node = (Node)Configurador.this.campos.get(na);
/* 281 */         String valor = null;
/*     */         
/* 283 */         if (node instanceof TextField) {
/* 284 */           valor = ((TextField)node).getText();
/* 285 */         } else if (node instanceof PasswordField) {
/* 286 */           valor = ((PasswordField)node).getText();
/* 287 */         } else if (node instanceof ComboBox) {
/* 288 */           valor = ((ComboBox)node).getSelectionModel().getSelectedItem().toString();
/* 289 */         } else if (node instanceof CheckBox) {
/* 290 */           valor = ((CheckBox)node).isSelected() ? "SIM" : "NAO";
/*     */         } 
/*     */         
/* 293 */         String valorFinal = valor;
/* 294 */         if ((valor == null || valor.isEmpty()) && !na.getValorPadrao().isEmpty()) {
/* 295 */           valorFinal = na.getValorPadrao();
/*     */         }
/* 297 */         Ambiente.getInstance().alterarValorVariavelAmbiente(na, valorFinal);
/*     */       } 
/*     */       
/* 300 */       Ambiente.getInstance().persisteVariavelAmbiente(Ambiente.getPropriedadePadrao());
/* 301 */       ComandosSO.enviarComando(ComandosSO.Comando.REINICIAR_APLICACAO, Configurador.this.argumentos);
/*     */       try {
/* 303 */         Thread.sleep(1000L);
/* 304 */       } catch (InterruptedException ex) {
/* 305 */         Log.gravarLogExcecao("Erro no sleep.", this, ex);
/*     */       } 
/* 307 */       System.exit(0);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/configuracao/Configurador.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */