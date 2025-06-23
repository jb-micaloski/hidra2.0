/*     */ package ipqm.gsd.hidra.ihm.util;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.anotacao_interface.EnumFiltros;
/*     */ import ipqm.gsd.componentes.nucleo.configuracao.ConfiguracaoIHM;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.HashSet;
/*     */ import java.util.Set;
/*     */ import javafx.beans.value.ChangeListener;
/*     */ import javafx.beans.value.ObservableValue;
/*     */ import javafx.event.Event;
/*     */ import javafx.event.EventHandler;
/*     */ import javafx.scene.control.TextArea;
/*     */ import javafx.scene.control.TextField;
/*     */ import javafx.scene.control.TextFormatter;
/*     */ import javafx.scene.input.KeyCode;
/*     */ import javafx.scene.input.KeyEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FiltroTexto
/*     */ {
/*  29 */   private static final DecimalFormat DECIMAL = new DecimalFormat("0.0");
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
/*     */   public static void filtrarTexto(TextField campo, EnumFiltros.TipoCampo tipo) {
/*  41 */     EnumFiltros.Regex regex = null;
/*  42 */     boolean decimal = false;
/*  43 */     int tamanho = 0;
/*  44 */     switch (tipo) {
/*     */       case GDM:
/*  46 */         regex = EnumFiltros.Regex.ALFANUMERICO_ESPECIAL;
/*  47 */         tamanho = 15;
/*     */         break;
/*     */       case GMS:
/*  50 */         regex = EnumFiltros.Regex.ALFANUMERICO_ESPECIAL;
/*  51 */         tamanho = 20;
/*     */         break;
/*     */       case null:
/*  54 */         regex = EnumFiltros.Regex.CAMINHO;
/*  55 */         tamanho = 50;
/*     */         break;
/*     */       case null:
/*  58 */         regex = EnumFiltros.Regex.CAT;
/*  59 */         tamanho = 2;
/*     */         break;
/*     */       case null:
/*  62 */         regex = EnumFiltros.Regex.DEFAULT;
/*  63 */         tamanho = 50;
/*     */         break;
/*     */       case null:
/*  66 */         regex = EnumFiltros.Regex.EMAIL_SENHA;
/*  67 */         tamanho = 50;
/*     */         break;
/*     */       case null:
/*  70 */         regex = EnumFiltros.Regex.FLOAT;
/*  71 */         tamanho = 6;
/*  72 */         decimal = true;
/*     */         break;
/*     */       case null:
/*  75 */         regex = EnumFiltros.Regex.FLOAT;
/*  76 */         tamanho = 8;
/*     */         break;
/*     */       case null:
/*  79 */         regex = EnumFiltros.Regex.FLOAT_POSITIVO;
/*  80 */         tamanho = 4;
/*  81 */         decimal = true;
/*     */         break;
/*     */       case null:
/*  84 */         regex = EnumFiltros.Regex.FLOAT_POSITIVO;
/*  85 */         tamanho = 5;
/*  86 */         decimal = true;
/*     */         break;
/*     */       case null:
/*  89 */         regex = EnumFiltros.Regex.FLOAT_POSITIVO;
/*  90 */         tamanho = 6;
/*  91 */         decimal = true;
/*     */         break;
/*     */       case null:
/*  94 */         regex = EnumFiltros.Regex.FLOAT_POSITIVO;
/*  95 */         tamanho = 7;
/*  96 */         decimal = true;
/*     */         break;
/*     */       case null:
/*  99 */         regex = EnumFiltros.Regex.FLOAT_POSITIVO;
/* 100 */         tamanho = 8;
/*     */         break;
/*     */       case null:
/* 103 */         regex = EnumFiltros.Regex.FLOAT_POSITIVO;
/* 104 */         tamanho = 10;
/* 105 */         decimal = true;
/*     */         break;
/*     */       case null:
/* 108 */         tamanho = 14;
/* 109 */         regex = EnumFiltros.Regex.FLOAT_POSITIVO;
/* 110 */         decimal = true;
/*     */         break;
/*     */       case null:
/* 113 */         regex = EnumFiltros.Regex.INTEIRO;
/* 114 */         tamanho = 5;
/*     */         break;
/*     */       case null:
/* 117 */         regex = EnumFiltros.Regex.INTEIRO_POSITIVO;
/* 118 */         tamanho = 2;
/*     */         break;
/*     */       case null:
/* 121 */         regex = EnumFiltros.Regex.INTEIRO_POSITIVO;
/* 122 */         tamanho = 3;
/*     */         break;
/*     */       case null:
/* 125 */         regex = EnumFiltros.Regex.INTEIRO_POSITIVO;
/* 126 */         tamanho = 4;
/*     */         break;
/*     */       case null:
/* 129 */         regex = EnumFiltros.Regex.INTEIRO_POSITIVO;
/* 130 */         tamanho = 5;
/*     */         break;
/*     */       case null:
/* 133 */         regex = EnumFiltros.Regex.INTEIRO_POSITIVO;
/* 134 */         tamanho = 6;
/*     */         break;
/*     */       case null:
/* 137 */         regex = EnumFiltros.Regex.INTEIRO_POSITIVO;
/* 138 */         tamanho = 7;
/*     */         break;
/*     */       case null:
/* 141 */         regex = EnumFiltros.Regex.INTEIRO_POSITIVO;
/* 142 */         tamanho = 9;
/*     */         break;
/*     */       case null:
/* 145 */         regex = EnumFiltros.Regex.INTEIRO_POSITIVO;
/* 146 */         tamanho = 10;
/*     */         break;
/*     */       case null:
/* 149 */         regex = EnumFiltros.Regex.INTEIRO_POSITIVO;
/* 150 */         tamanho = 11;
/*     */         break;
/*     */       case null:
/* 153 */         regex = EnumFiltros.Regex.INTEIRO_UNICO;
/* 154 */         tamanho = 1;
/*     */         break;
/*     */       case null:
/* 157 */         regex = EnumFiltros.Regex.INTEIRO_POSITIVO;
/* 158 */         tamanho = 200;
/*     */         break;
/*     */       case null:
/* 161 */         regex = EnumFiltros.Regex.OCTAL;
/* 162 */         tamanho = 2;
/*     */         break;
/*     */       case null:
/* 165 */         regex = EnumFiltros.Regex.OCTAL;
/* 166 */         tamanho = 4;
/*     */         break;
/*     */       case null:
/* 169 */         regex = EnumFiltros.Regex.EMAIL_SENHA;
/* 170 */         tamanho = 20;
/*     */         break;
/*     */       case null:
/* 173 */         regex = EnumFiltros.Regex.TEXTO;
/* 174 */         tamanho = 5;
/*     */         break;
/*     */       case null:
/* 177 */         regex = EnumFiltros.Regex.TEXTO;
/* 178 */         tamanho = 1;
/*     */         break;
/*     */       case null:
/* 181 */         regex = EnumFiltros.Regex.TEXTO_NA_COMPLETO;
/* 182 */         tamanho = 6;
/*     */         break;
/*     */       case null:
/* 185 */         regex = EnumFiltros.Regex.TEXTO;
/* 186 */         tamanho = 20;
/*     */         break;
/*     */       case null:
/* 189 */         regex = EnumFiltros.Regex.TEXTO;
/* 190 */         tamanho = 50;
/*     */         break;
/*     */       case null:
/* 193 */         regex = EnumFiltros.Regex.TEXTO;
/* 194 */         tamanho = 500;
/*     */         break;
/*     */     } 
/*     */     
/* 198 */     if (decimal) {
/* 199 */       formataDecimal(campo);
/*     */     }
/*     */     
/* 202 */     filtraEntrada(campo, (regex == null) ? null : regex.toString());
/* 203 */     limitaCampo(campo, tamanho);
/*     */   }
/*     */   
/*     */   public static void filtrarTexto(TextArea campo, EnumFiltros.TipoCampo tipo) {
/* 207 */     EnumFiltros.Regex regex = null;
/* 208 */     int tamanho = 0;
/* 209 */     switch (tipo) {
/*     */       case null:
/* 211 */         regex = EnumFiltros.Regex.ALFANUMERICO_ESPECIAL;
/* 212 */         tamanho = 200;
/*     */         break;
/*     */       case null:
/* 215 */         regex = EnumFiltros.Regex.ALFANUMERICO_ESPECIAL;
/* 216 */         tamanho = 82;
/*     */         break;
/*     */     } 
/*     */     
/* 220 */     filtraEntrada(campo, regex);
/* 221 */     limitaCampo(campo, tamanho);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void filtraEntrada(TextField campo, final String entrada) {
/* 232 */     campo.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>()
/*     */         {
/*     */           public void handle(KeyEvent inputEvent) {
/* 235 */             if (!inputEvent.getCharacter().matches(entrada)) {
/* 236 */               inputEvent.consume();
/*     */             }
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   private static void filtraEntrada(TextArea campo, final EnumFiltros.Regex entrada) {
/* 243 */     campo.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>()
/*     */         {
/*     */           public void handle(KeyEvent inputEvent) {
/* 246 */             if (!inputEvent.getCharacter().matches(entrada.toString())) {
/* 247 */               inputEvent.consume();
/*     */             }
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void limitaCampo(TextField campo, int tamanhoMaximo) {
/* 260 */     campo.textProperty().addListener((ov, oldValue, newValue) -> {
/*     */           if (campo.getText().length() > tamanhoMaximo) {
/*     */             campo.setText(oldValue);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void limitaCampo(TextField campo, EnumFiltros.Mascara mascara) {
/* 274 */     campo.textProperty().addListener((ov, oldValue, newValue) -> {
/*     */           EnumFiltros.Mascara mascara_ = substituiMascara(mascara);
/*     */           if (campo.getText().length() > mascara_.toString().length()) {
/*     */             campo.setText(oldValue);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public static void limitaCampo(TextArea campo, int tamanhoMaximo) {
/* 283 */     campo.textProperty().addListener((ov, oldValue, newValue) -> {
/*     */           if (campo.getText().length() > tamanhoMaximo) {
/*     */             campo.setText(oldValue);
/*     */           }
/*     */         });
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
/*     */   public static void controlaQuantidade(final TextArea campo, final int quantidadeMaxima) {
/* 299 */     campo.textProperty().addListener(new ChangeListener<String>()
/*     */         {
/*     */           public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue)
/*     */           {
/* 303 */             if (!campo.getText().isEmpty() && 
/* 304 */               Integer.parseInt(campo.getText()) > quantidadeMaxima) {
/* 305 */               campo.setText(String.valueOf(quantidadeMaxima));
/*     */             }
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */   
/*     */   public static void controlaQuantidade(final TextField campo, final int quantidadeMaxima) {
/* 313 */     campo.textProperty().addListener(new ChangeListener<String>()
/*     */         {
/*     */           public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue)
/*     */           {
/* 317 */             if (!campo.getText().isEmpty() && 
/* 318 */               Integer.parseInt(campo.getText()) > quantidadeMaxima) {
/* 319 */               campo.setText(String.valueOf(quantidadeMaxima));
/*     */             }
/*     */           }
/*     */         });
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
/*     */   
/*     */   public static void adicionarMascara(TextField campo, EnumFiltros.Mascara mascara, EnumFiltros.Valores filtro) {
/* 337 */     limitaCampo(campo, mascara);
/* 338 */     campo.textProperty().addListener((ov, oldValue, newValue) -> campo.setText(campo.getText().toUpperCase()));
/*     */ 
/*     */ 
/*     */     
/* 342 */     campo.setOnKeyPressed(e -> {
/*     */           EnumFiltros.Mascara mascara_ = substituiMascara(mascara);
/*     */           int caretPosition = campo.getCaretPosition();
/*     */           if (caretPosition < mascara_.toString().length() - 1 && mascara_.toString().charAt(caretPosition) != ' ' && e.getCode() != KeyCode.BACK_SPACE && e.getCode() != KeyCode.LEFT) {
/*     */             campo.setText(campo.getText() + mascara_.toString().charAt(caretPosition));
/*     */             campo.positionCaret(caretPosition + 1);
/*     */           } 
/*     */         });
/* 350 */     filtraEntrada(campo, filtro.toString());
/*     */   }
/*     */   
/*     */   private static EnumFiltros.Mascara substituiMascara(EnumFiltros.Mascara mascara) {
/* 354 */     if (mascara == EnumFiltros.Mascara.LATITUDE) {
/* 355 */       switch (Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoIHM().getFormatoExibicaoCoordGeoPadrao()) {
/*     */         case GDM:
/* 357 */           return EnumFiltros.Mascara.LATITUDE_GDM;
/*     */         case GMS:
/* 359 */           return EnumFiltros.Mascara.LATITUDE_GMS;
/*     */       } 
/* 361 */     } else if (mascara == EnumFiltros.Mascara.LONGITUDE) {
/* 362 */       switch (Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoIHM().getFormatoExibicaoCoordGeoPadrao()) {
/*     */         case GDM:
/* 364 */           return EnumFiltros.Mascara.LONGITUDE_GDM;
/*     */         case GMS:
/* 366 */           return EnumFiltros.Mascara.LONGITUDE_GMS;
/*     */       } 
/*     */     } 
/* 369 */     return mascara;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void limitaNumero(final TextField campo, final int valorMin, final int valorMax) {
/* 380 */     campo.textProperty().addListener(new ChangeListener<String>()
/*     */         {
/*     */           public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) {
/* 383 */             if (!campo.getText().equals("")) {
/* 384 */               int numero = Integer.valueOf(campo.getText()).intValue();
/*     */               
/* 386 */               if (numero > valorMax) {
/* 387 */                 campo.setText(String.valueOf(valorMax));
/*     */               }
/* 389 */               if (numero < valorMin) {
/* 390 */                 campo.setText(String.valueOf(valorMin));
/*     */               }
/*     */             } 
/*     */           }
/*     */         });
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
/*     */   public static String misturar(String valor, EnumFiltros.Mascara mascara) {
/* 407 */     StringBuilder sb = new StringBuilder(mascara.toString());
/* 408 */     int k = 0;
/* 409 */     for (int i = 0; i < mascara.toString().length(); i++) {
/* 410 */       if (mascara.toString().charAt(i) == ' ' && k < valor.length()) {
/* 411 */         sb.setCharAt(i, valor.toUpperCase().charAt(k));
/* 412 */         k++;
/*     */       } 
/*     */     } 
/* 415 */     return sb.toString();
/*     */   }
/*     */   
/*     */   public static String tirarMascara(String texto, EnumFiltros.Mascara mascara) {
/* 419 */     Set<String> caracteresDaMascara = new HashSet<>();
/* 420 */     for (int i = 0; i < mascara.toString().length(); i++) {
/* 421 */       char c = mascara.toString().charAt(i);
/* 422 */       if (c != ' ') {
/* 423 */         caracteresDaMascara.add(String.valueOf(c));
/*     */       }
/*     */     } 
/* 426 */     for (String c : caracteresDaMascara) {
/* 427 */       texto = texto.replace(c, "");
/*     */     }
/* 429 */     return texto;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void formataDecimal(TextField campo) {
/* 434 */     campo.setTextFormatter(new TextFormatter(c -> {
/*     */             if (c.getControlNewText().isEmpty()) {
/*     */               return c;
/*     */             }
/*     */             
/*     */             ParsePosition parsePosition = new ParsePosition(0);
/*     */             
/*     */             Object object = DECIMAL.parse(c.getControlNewText(), parsePosition);
/* 442 */             return (object == null || parsePosition.getIndex() < c.getControlNewText().length()) ? null : c;
/*     */           }));
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/util/FiltroTexto.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */