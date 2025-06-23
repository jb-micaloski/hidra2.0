/*     */ package ipqm.gsd.hidra.ihm.widgets.lora_chat;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.contexto.ContextoTatico;
/*     */ import ipqm.gsd.componentes.dominio.driver.lora.ChatLoRa;
/*     */ import ipqm.gsd.componentes.dominio.driver.lora.MensagemLoRa;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.hidra.ihm.util.FiltroTexto;
/*     */ import java.net.URL;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.ResourceBundle;
/*     */ import javafx.application.Platform;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.fxml.FXML;
/*     */ import javafx.fxml.Initializable;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.ComboBox;
/*     */ import javafx.scene.control.Label;
/*     */ import javafx.scene.control.ProgressIndicator;
/*     */ import javafx.scene.control.TextArea;
/*     */ import javafx.scene.control.TextField;
/*     */ import javafx.scene.input.KeyCode;
/*     */ import javafx.scene.input.KeyEvent;
/*     */ import javafx.scene.input.MouseEvent;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ControladorLoraChat
/*     */   implements Initializable
/*     */ {
/*     */   private WidgetLoraChat widgetLoraChat;
/*     */   @FXML
/*     */   private AnchorPane anchorPaneLoraChat;
/*     */   @FXML
/*     */   private AnchorPane painelChat;
/*     */   @FXML
/*     */   private TextArea textAreaReceberChat;
/*     */   @FXML
/*     */   private TextField textFildEnviarChat;
/*     */   @FXML
/*     */   private Button chatEnviar;
/*     */   @FXML
/*     */   private ComboBox<String> cbDestinatario;
/*     */   private ChatLoRa chatLora;
/*  53 */   private final Map<String, StringBuilder> textoByIdentificador = new HashMap<>();
/*     */   
/*     */   @FXML
/*     */   private Label lbCaracteres;
/*     */   
/*     */   @FXML
/*     */   private ProgressIndicator progressIndicatior;
/*  60 */   private final int TAMANHO_MAXIMO = 120;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void initialize(URL url, ResourceBundle rb) {
/*  67 */     this
/*  68 */       .chatLora = ((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getChatLoRa();
/*     */     
/*  70 */     this.chatLora.getDestinatarios().keySet().stream().forEach(identificador -> {
/*     */           this.cbDestinatario.getItems().add(identificador);
/*     */           
/*     */           this.textoByIdentificador.put(identificador, new StringBuilder());
/*     */         });
/*  75 */     FiltroTexto.limitaCampo(this.textFildEnviarChat, 120);
/*  76 */     this.cbDestinatario.getSelectionModel().select(0);
/*  77 */     atualizarCaracteresRestantes();
/*     */   }
/*     */ 
/*     */   
/*     */   public WidgetLoraChat getWidgetLoRaChat() {
/*  82 */     return this.widgetLoraChat;
/*     */   }
/*     */   
/*     */   public void setWidgetRTD(WidgetLoraChat widgetLoRaChat) {
/*  86 */     this.widgetLoraChat = widgetLoRaChat;
/*     */   }
/*     */   
/*     */   public boolean atualizaChat() {
/*  90 */     boolean novaMensagem = false;
/*     */     
/*  92 */     Map<String, List<MensagemLoRa>> mensagensNovas = this.chatLora.getMensagensNovas();
/*     */     
/*  94 */     for (String identificador : mensagensNovas.keySet()) {
/*  95 */       for (MensagemLoRa mensagem : mensagensNovas.get(identificador)) {
/*  96 */         StringBuilder sb = this.textoByIdentificador.get(identificador);
/*  97 */         sb.append(mensagem.getMensagemFormatada()).append("\n");
/*  98 */         Platform.runLater(new Runnable() {
/*     */               public void run() {
/* 100 */                 ControladorLoraChat.this.cbDestinatario.getSelectionModel().select(identificador);
/*     */               }
/*     */             });
/*     */         
/* 104 */         atualizarDestinatarioSelecionado();
/*     */         
/* 106 */         if (!mensagem.isRecebida()) {
/* 107 */           this.progressIndicatior.setVisible(false);
/*     */         }
/*     */         
/* 110 */         novaMensagem = true;
/*     */       } 
/*     */     } 
/*     */     
/* 114 */     return novaMensagem;
/*     */   }
/*     */ 
/*     */   
/*     */   @FXML
/*     */   private void EnviarMsgChatEnter(KeyEvent event) {
/* 120 */     atualizarCaracteresRestantes();
/* 121 */     if (event.getCode().equals(KeyCode.ENTER)) {
/* 122 */       enviar();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @FXML
/*     */   private void EnviarMsgChat(MouseEvent event) {
/* 129 */     enviar();
/*     */   }
/*     */   
/*     */   private void enviar() {
/* 133 */     String destinatario = (String)this.cbDestinatario.getSelectionModel().getSelectedItem();
/* 134 */     String conteudo = this.textFildEnviarChat.getText();
/* 135 */     this.chatLora.enviarNovaMensagem(destinatario, conteudo);
/*     */     
/* 137 */     this.textFildEnviarChat.clear();
/* 138 */     atualizarCaracteresRestantes();
/* 139 */     this.progressIndicatior.setVisible(true);
/*     */   }
/*     */   
/*     */   public void focaChat() {
/* 143 */     Platform.runLater(() -> this.textFildEnviarChat.requestFocus());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @FXML
/*     */   private void acaoTrocarDestinatario(ActionEvent event) {
/* 150 */     atualizarDestinatarioSelecionado();
/*     */   }
/*     */   
/*     */   private void acaoDigitar(KeyEvent event) {
/* 154 */     atualizarCaracteresRestantes();
/*     */   }
/*     */   
/*     */   private void atualizarDestinatarioSelecionado() {
/* 158 */     String idSelecionado = (String)this.cbDestinatario.getSelectionModel().getSelectedItem();
/* 159 */     this.textAreaReceberChat.setText(((StringBuilder)this.textoByIdentificador.get(idSelecionado)).toString());
/* 160 */     this.textAreaReceberChat.positionCaret(this.textAreaReceberChat.getLength());
/*     */   }
/*     */   
/*     */   private void atualizarCaracteresRestantes() {
/* 164 */     int tamanhoAtual = this.textFildEnviarChat.getLength();
/* 165 */     int restantes = 120 - tamanhoAtual;
/* 166 */     this.lbCaracteres.setText("Caracteres restantes (" + String.valueOf(restantes) + ")");
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/lora_chat/ControladorLoraChat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */