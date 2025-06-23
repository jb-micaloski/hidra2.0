/*     */ package ipqm.gsd.hidra.ihm.util;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.anotacao_interface.EnumFiltros;
/*     */ import ipqm.gsd.hidra.ihm.widgets.pesquisa.itens.ItemPesquisa;
/*     */ import java.text.Normalizer;
/*     */ import javafx.beans.value.ObservableValue;
/*     */ import javafx.collections.FXCollections;
/*     */ import javafx.collections.ObservableList;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.control.ListView;
/*     */ import javafx.scene.control.TableCell;
/*     */ import javafx.scene.control.TextField;
/*     */ import javafx.scene.input.KeyCode;
/*     */ import javafx.scene.input.KeyEvent;
/*     */ import javafx.scene.input.MouseButton;
/*     */ import javafx.scene.input.MouseEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FiltroTextField<S>
/*     */   extends TableCell<S, String>
/*     */ {
/*     */   private TextField textField;
/*     */   
/*     */   public FiltroTextField(EnumFiltros.TipoCampo tipo, EnumFiltros.Valores valor) {
/*  28 */     this.textField = new TextField();
/*  29 */     FiltroTexto.filtrarTexto(this.textField, tipo);
/*  30 */     this.textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
/*     */           if (!isNowFocused.booleanValue()) {
/*     */             processEdit();
/*     */           }
/*     */         });
/*  35 */     this.textField.setOnAction(event -> processEdit());
/*     */   }
/*     */   
/*     */   public FiltroTextField(EnumFiltros.TipoCampo tipo, EnumFiltros.Valores valor, int quantidadeMaxima) {
/*  39 */     this.textField = new TextField();
/*  40 */     FiltroTexto.filtrarTexto(this.textField, tipo);
/*  41 */     this.textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) -> {
/*     */           if (!isNowFocused.booleanValue()) {
/*     */             processEdit();
/*     */           }
/*     */         });
/*  46 */     this.textField.setOnAction(event -> processEdit());
/*  47 */     FiltroTexto.controlaQuantidade(this.textField, quantidadeMaxima);
/*     */   }
/*     */   
/*     */   private void processEdit() {
/*  51 */     String text = this.textField.getText();
/*  52 */     commitEdit(text);
/*     */   }
/*     */ 
/*     */   
/*     */   public void updateItem(String value, boolean empty) {
/*  57 */     super.updateItem(value, empty);
/*  58 */     if (empty) {
/*  59 */       setText(null);
/*  60 */       setGraphic(null);
/*  61 */     } else if (isEditing()) {
/*  62 */       setText(null);
/*  63 */       this.textField.setText(value);
/*  64 */       setGraphic((Node)this.textField);
/*     */     } else {
/*  66 */       setText(value);
/*  67 */       setGraphic(null);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void startEdit() {
/*  73 */     super.startEdit();
/*  74 */     String value = (String)getItem();
/*  75 */     if (value != null) {
/*  76 */       this.textField.setText(value);
/*  77 */       setGraphic((Node)this.textField);
/*  78 */       setText(null);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void cancelEdit() {
/*  84 */     super.cancelEdit();
/*  85 */     setText((String)getItem());
/*  86 */     setGraphic(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void commitEdit(String value) {
/*  91 */     super.commitEdit(value);
/*     */   }
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
/*     */   public static void pesquisar(String valorAntigo, String novoValor, ListView<ItemPesquisa> listaPesquisa, ObservableList<ItemPesquisa> listaValoresPesquisa) {
/* 105 */     if ((valorAntigo != null && novoValor.length() < valorAntigo.length()) || valorAntigo.equals("")) {
/* 106 */       listaPesquisa.setItems(listaValoresPesquisa);
/* 107 */       listaPesquisa.setVisible(false);
/*     */     } 
/*     */     
/* 110 */     String valor = removerAcentos(novoValor.toUpperCase());
/* 111 */     ObservableList<ItemPesquisa> subEntradas = FXCollections.observableArrayList();
/*     */     
/* 113 */     for (ItemPesquisa itemPesquisa : listaPesquisa.getItems()) {
/* 114 */       boolean encontrou = true;
/* 115 */       String nome = removerAcentos(itemPesquisa.getNome().toUpperCase());
/*     */       
/* 117 */       if (!nome.contains(valor)) {
/* 118 */         encontrou = false;
/*     */       }
/*     */       
/* 121 */       if (encontrou) {
/* 122 */         subEntradas.add(itemPesquisa);
/*     */       }
/*     */     } 
/*     */     
/* 126 */     listaPesquisa.setItems(subEntradas);
/*     */     
/* 128 */     if (listaPesquisa.getItems().size() > 0) {
/* 129 */       listaPesquisa.setVisible(true);
/* 130 */       listaPesquisa.getSelectionModel().select(0);
/*     */     } else {
/* 132 */       listaPesquisa.setVisible(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String removerAcentos(String str) {
/* 143 */     return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
/*     */   }
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
/*     */   public static boolean tratarEventoMouse(MouseEvent mouseEvent, ListView<ItemPesquisa> listaPesquisa, TextField textField) {
/* 157 */     if (mouseEvent.getButton().equals(MouseButton.PRIMARY) && mouseEvent.getClickCount() == 2 && listaPesquisa
/* 158 */       .getItems().size() > 0) {
/* 159 */       ItemPesquisa itemPesquisa = (ItemPesquisa)listaPesquisa.getSelectionModel().getSelectedItems().get(0);
/* 160 */       textField.clear();
/* 161 */       textField.setText(itemPesquisa.getNome());
/* 162 */       listaPesquisa.setVisible(false);
/* 163 */       return true;
/*     */     } 
/*     */     
/* 166 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean tratarEventoTecladoText(TextField tx, ListView<ItemPesquisa> listaPesquisa, KeyEvent event) {
/* 178 */     if (tx.getText().length() == 0) {
/* 179 */       listaPesquisa.setVisible(false);
/*     */     }
/*     */     
/* 182 */     switch (event.getCode()) {
/*     */       case ENTER:
/* 184 */         if (listaPesquisa.getItems().size() > 0) {
/* 185 */           ItemPesquisa itemPesquisa = (ItemPesquisa)listaPesquisa.getSelectionModel().getSelectedItems().get(0);
/* 186 */           tx.clear();
/* 187 */           tx.setText(itemPesquisa.getNome());
/* 188 */           listaPesquisa.setVisible(false);
/* 189 */           return true;
/*     */         } 
/*     */         break;
/*     */       case DOWN:
/* 193 */         if (listaPesquisa.getItems().size() > 0) {
/* 194 */           listaPesquisa.requestFocus();
/*     */         }
/*     */         break;
/*     */     } 
/*     */     
/* 199 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean tratarEventoTecladoLista(TextField tx, ListView<ItemPesquisa> listaPesquisa, KeyEvent event) {
/* 211 */     if (event.getCode().equals(KeyCode.ENTER)) {
/* 212 */       if (listaPesquisa.getItems().size() > 0) {
/* 213 */         ItemPesquisa itemPesquisa = (ItemPesquisa)listaPesquisa.getSelectionModel().getSelectedItems().get(0);
/* 214 */         tx.clear();
/* 215 */         tx.setText(itemPesquisa.getNome());
/* 216 */         listaPesquisa.setVisible(false);
/*     */         
/* 218 */         return true;
/*     */       } 
/* 220 */     } else if (!event.getCode().equals(KeyCode.DOWN) && !event.getCode().equals(KeyCode.UP)) {
/* 221 */       tx.requestFocus();
/*     */     } 
/*     */     
/* 224 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/util/FiltroTextField.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */