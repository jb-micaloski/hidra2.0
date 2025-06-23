/*     */ package ipqm.gsd.hidra.ihm.gerador_formulario_fx;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.banco_dados.DAO;
/*     */ import ipqm.gsd.componentes.nucleo.anotacao_interface.AcaoFormulario;
/*     */ import ipqm.gsd.componentes.nucleo.anotacao_interface.DadosExtraidos;
/*     */ import ipqm.gsd.componentes.nucleo.anotacao_interface.EnumFiltros;
/*     */ import ipqm.gsd.componentes.nucleo.anotacao_interface.ExtratorDados;
/*     */ import ipqm.gsd.componentes.nucleo.anotacao_interface.TipoCampo;
/*     */ import ipqm.gsd.componentes.nucleo.anotacao_interface.Validacao;
/*     */ import ipqm.gsd.componentes.nucleo.gestao.AmbienteInstalacao;
/*     */ import ipqm.gsd.componentes.nucleo.gestao.GestorCartasGenerico;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.logon.perfis.PerfilUsuario;
/*     */ import ipqm.gsd.componentes.nucleo.objeto_visual.simbologia.Simbologia;
/*     */ import ipqm.gsd.hidra.ihm.util.FiltroTexto;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javafx.collections.FXCollections;
/*     */ import javafx.collections.ObservableList;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.event.Event;
/*     */ import javafx.event.EventHandler;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.CheckBox;
/*     */ import javafx.scene.control.ComboBox;
/*     */ import javafx.scene.control.Label;
/*     */ import javafx.scene.control.TextField;
/*     */ import javafx.scene.layout.HBox;
/*     */ import javafx.scene.layout.VBox;
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
/*     */ 
/*     */ public class FormularioJavaFX
/*     */ {
/*     */   private final HBox pai;
/*     */   private final HBox botoes;
/*     */   private HBox linhasCampos;
/*     */   public final VBox colunasCampos;
/*     */   private final Map<String, DadosExtraidos> mapaForm;
/*     */   private final Map<Node, String> mapaCampos;
/*     */   private final String[] ordenaKeys;
/*     */   private final Button botaoCancelar;
/*     */   private Object novoObjeto;
/*     */   private Class classObjeto;
/*     */   private AcaoFormulario acaoForm;
/*     */   private boolean validaFormulario;
/*     */   private Map<Integer, String> mapaColunaLabels;
/*     */   private Map<Integer, Node> mapaColunaCampos;
/*     */   private Map<Integer, String> mapaColunaMedidas;
/*     */   private boolean alterarObjeto;
/*  65 */   private final Button botaoOk = new Button("OK"); public FormularioJavaFX(double w, double h, Object o) {
/*  66 */     this.botaoOk.setMaxHeight(30.0D);
/*  67 */     this.botaoOk.setMaxWidth(80.0D);
/*  68 */     this.botaoCancelar = new Button("Cancelar");
/*  69 */     this.botaoCancelar.setMaxHeight(30.0D);
/*  70 */     this.botaoCancelar.setMaxWidth(150.0D);
/*  71 */     this.botaoOk.setOnAction(new AcaoBotao());
/*  72 */     this.botaoCancelar.setOnAction(new AcaoCancelarBotao());
/*  73 */     this.botaoOk.setId("botaoJanela");
/*  74 */     this.botaoCancelar.setId("botaoJanela");
/*  75 */     this.botoes = new HBox();
/*  76 */     this.botoes.setId("HBOX");
/*  77 */     this.botoes.setSpacing(10.0D);
/*  78 */     this.botoes.getChildren().addAll((Object[])new Node[] { (Node)this.botaoOk, (Node)this.botaoCancelar });
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  99 */     this.mapaColunaCampos = new HashMap<>();
/* 100 */     this.mapaColunaLabels = new HashMap<>();
/* 101 */     this.mapaColunaMedidas = new HashMap<>();
/* 102 */     this.mapaCampos = new HashMap<>();
/* 103 */     this.mapaForm = ExtratorDados.extratorDadosToFormularioObjeto(o);
/* 104 */     this.ordenaKeys = new String[this.mapaForm.keySet().size()];
/* 105 */     this.colunasCampos = new VBox(8.0D);
/* 106 */     this.pai = new HBox();
/* 107 */     this.pai.setId("HBOX");
/* 108 */     this.pai.setSpacing(12.0D);
/*     */     
/* 110 */     ordena();
/* 111 */     preencherInfo(); } public FormularioJavaFX(double w, double h, Class c) {
/*     */     this.botaoOk.setMaxHeight(30.0D);
/*     */     this.botaoOk.setMaxWidth(80.0D);
/*     */     this.botaoCancelar = new Button("Cancelar");
/*     */     this.botaoCancelar.setMaxHeight(30.0D);
/*     */     this.botaoCancelar.setMaxWidth(150.0D);
/*     */     this.botaoOk.setOnAction(new AcaoBotao());
/*     */     this.botaoCancelar.setOnAction(new AcaoCancelarBotao());
/*     */     this.botaoOk.setId("botaoJanela");
/*     */     this.botaoCancelar.setId("botaoJanela");
/*     */     this.botoes = new HBox();
/*     */     this.botoes.setId("HBOX");
/*     */     this.botoes.setSpacing(10.0D);
/*     */     this.botoes.getChildren().addAll((Object[])new Node[] { (Node)this.botaoOk, (Node)this.botaoCancelar });
/* 125 */     this.mapaColunaCampos = new HashMap<>();
/* 126 */     this.mapaColunaLabels = new HashMap<>();
/* 127 */     this.mapaColunaMedidas = new HashMap<>();
/* 128 */     this.mapaCampos = new HashMap<>();
/* 129 */     this.mapaForm = ExtratorDados.extratorDadosToFormularioClass(c);
/* 130 */     this.ordenaKeys = new String[this.mapaForm.keySet().size()];
/* 131 */     this.colunasCampos = new VBox(8.0D);
/* 132 */     this.pai = new HBox();
/* 133 */     this.pai.setId("HBOX");
/* 134 */     this.pai.setSpacing(12.0D);
/*     */     
/* 136 */     ordena();
/* 137 */     preencherInfo();
/*     */   }
/*     */   
/*     */   public AcaoFormulario getAcaoForm() {
/*     */     return this.acaoForm;
/*     */   }
/*     */   
/*     */   public void setAcaoForm(AcaoFormulario acaoForm) {
/*     */     this.acaoForm = acaoForm;
/*     */   }
/*     */   
/*     */   private void ordena() throws ArrayIndexOutOfBoundsException {
/* 149 */     List<Integer> listaIdDisponivel = new ArrayList<>();
/* 150 */     List<String> ordemZero = new ArrayList<>();
/* 151 */     for (String key : this.mapaForm.keySet()) {
/* 152 */       int index = ((DadosExtraidos)this.mapaForm.get(key)).getOrdem();
/* 153 */       if (index > this.mapaForm.keySet().size()) {
/* 154 */         throw new ArrayIndexOutOfBoundsException("Ordem " + index + " : tamanho do array: " + this.mapaForm.keySet().size());
/*     */       }
/* 156 */       if (index > 0) {
/* 157 */         index--;
/*     */       } else {
/* 159 */         ordemZero.add(key);
/*     */         continue;
/*     */       } 
/* 162 */       if (this.ordenaKeys[index] == null) {
/* 163 */         this.ordenaKeys[index] = key; continue;
/*     */       } 
/* 165 */       ((DadosExtraidos)this.mapaForm.get(key)).setOrdem(0);
/* 166 */       ordemZero.add(key);
/*     */     } 
/*     */     
/* 169 */     if (!ordemZero.isEmpty()) {
/* 170 */       int max = 0;
/* 171 */       for (int i = 0; i < this.ordenaKeys.length; i++) {
/* 172 */         if (this.ordenaKeys[i] != null) {
/* 173 */           max = Math.max(max, i);
/*     */         } else {
/* 175 */           listaIdDisponivel.add(Integer.valueOf(i));
/*     */         } 
/*     */       } 
/*     */       
/* 179 */       if (listaIdDisponivel.size() == ordemZero.size()) {
/* 180 */         int ordemZeroIndex = 0;
/* 181 */         for (Integer idDisp : listaIdDisponivel) {
/* 182 */           this.ordenaKeys[idDisp.intValue()] = ordemZero.get(ordemZeroIndex);
/* 183 */           ordemZeroIndex++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void preencherInfo() {
/* 190 */     int i = 1;
/* 191 */     for (String key : this.ordenaKeys) {
/* 192 */       this.mapaColunaLabels.put(Integer.valueOf(i), key);
/* 193 */       i++;
/*     */     } 
/*     */     
/* 196 */     int y = 1;
/* 197 */     for (String key : this.ordenaKeys) {
/* 198 */       DadosExtraidos tc = this.mapaForm.get(key);
/* 199 */       this.mapaColunaCampos.put(Integer.valueOf(y), tratarTipoCampo(tc, key));
/* 200 */       y++;
/*     */     } 
/*     */     
/* 203 */     int z = 1;
/* 204 */     for (String key : this.ordenaKeys) {
/* 205 */       DadosExtraidos medida = this.mapaForm.get(key);
/* 206 */       this.mapaColunaMedidas.put(Integer.valueOf(z), medida.getNomeMedida());
/* 207 */       z++;
/*     */     } 
/* 209 */     double largura = 0.0D;
/*     */     
/* 211 */     Set<Integer> labels = this.mapaColunaLabels.keySet();
/* 212 */     Set<Integer> campos = this.mapaColunaCampos.keySet();
/* 213 */     for (Integer chaveLabel : labels) {
/* 214 */       for (Integer chaveCampos : campos) {
/* 215 */         if (((String)this.mapaColunaLabels.get(chaveLabel)).equals(this.mapaColunaLabels.get(chaveCampos))) {
/* 216 */           this.linhasCampos = new HBox();
/*     */           
/* 218 */           DadosExtraidos de = this.mapaForm.get(this.mapaColunaLabels.get(chaveLabel));
/*     */           
/* 220 */           if (!de.isVisivel()) {
/*     */             continue;
/*     */           }
/*     */           
/* 224 */           Label label = new Label(this.mapaColunaLabels.get(chaveLabel));
/* 225 */           Label labelM = new Label(this.mapaColunaMedidas.get(chaveLabel));
/*     */           
/* 227 */           this.linhasCampos.getChildren().add(label);
/*     */           
/* 229 */           Node tc = this.mapaColunaCampos.get(chaveCampos);
/* 230 */           this.linhasCampos.getChildren().add(tc);
/*     */           
/* 232 */           this.linhasCampos.getChildren().add(labelM);
/*     */           
/* 234 */           this.colunasCampos.getChildren().add(this.linhasCampos);
/*     */           
/* 236 */           double tamanhoTexto = (((String)this.mapaColunaLabels.get(chaveLabel)).length() * 12);
/* 237 */           if (tamanhoTexto > largura) {
/* 238 */             largura = tamanhoTexto;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 244 */     for (Node hbox : this.colunasCampos.getChildren()) {
/* 245 */       Node label = (Node)((HBox)hbox).getChildren().get(0);
/* 246 */       ((Label)label).setMinWidth(largura);
/*     */     } 
/*     */     
/* 249 */     if (!((String)this.mapaColunaLabels.get(Integer.valueOf(1))).equals("Habilitado:")) {
/* 250 */       this.colunasCampos.getChildren().add(this.botoes);
/*     */     }
/* 252 */     this.pai.getChildren().add(this.colunasCampos);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Node tratarTipoCampo(DadosExtraidos dados, String nome) {
/*     */     CheckBox chk;
/*     */     TextField tf;
/*     */     ComboBox<Object> cb;
/*     */     List<AmbienteInstalacao.Ambiente> listaAmbienteInstalacao;
/*     */     List<String> listaServidorcartas;
/*     */     List<String> listaEstadoMar;
/* 264 */     switch (dados.getTipo()) {
/*     */       case CheckBox:
/* 266 */         chk = new CheckBox(dados.getNomeConteudo());
/* 267 */         if (dados.getValue() != null) {
/* 268 */           Boolean checked = (Boolean)dados.getValue();
/* 269 */           chk.setSelected(checked.booleanValue());
/*     */         } 
/*     */         
/* 272 */         this.mapaCampos.put(chk, nome);
/* 273 */         return (Node)chk;
/*     */       case TextField:
/* 275 */         tf = new TextField();
/* 276 */         tf.setId("campoTexto");
/* 277 */         if (dados.getValidacao() != null) {
/* 278 */           EnumFiltros.Mascara mask = dados.getValidacao().getMascara();
/* 279 */           EnumFiltros.Valores valor = dados.getValidacao().getValores();
/* 280 */           EnumFiltros.TipoCampo tipo = dados.getValidacao().getTipoCampo();
/*     */           
/* 282 */           if (!tipo.equals(EnumFiltros.TipoCampo.NENHUM)) {
/* 283 */             FiltroTexto.filtrarTexto(tf, tipo);
/*     */           }
/*     */           
/* 286 */           if (!mask.equals(EnumFiltros.Mascara.NENHUMA) && !valor.equals(EnumFiltros.Valores.NENHUMA)) {
/* 287 */             FiltroTexto.adicionarMascara(tf, mask, valor);
/*     */           }
/*     */         } 
/* 290 */         if (dados.getValue() != null) {
/* 291 */           tf.setText(dados.getValue().toString());
/*     */         }
/* 293 */         this.mapaCampos.put(tf, nome);
/* 294 */         return (Node)tf;
/*     */       case ComboBox:
/* 296 */         cb = new ComboBox();
/* 297 */         cb.setId("comboBox");
/* 298 */         cb.setPrefWidth(265.0D);
/* 299 */         listaAmbienteInstalacao = new ArrayList<>();
/* 300 */         listaAmbienteInstalacao.add(AmbienteInstalacao.Ambiente.WEB);
/* 301 */         listaAmbienteInstalacao.add(AmbienteInstalacao.Ambiente.DESKTOP);
/* 302 */         listaAmbienteInstalacao.add(AmbienteInstalacao.Ambiente.MOBILE);
/* 303 */         listaServidorcartas = new ArrayList<>();
/* 304 */         listaServidorcartas.add(GestorCartasGenerico.ServidorCartas.K2.getDescricao());
/* 305 */         listaServidorcartas.add(GestorCartasGenerico.ServidorCartas.IPQM.getDescricao());
/* 306 */         listaServidorcartas.add(GestorCartasGenerico.ServidorCartas.RADAR.getDescricao());
/* 307 */         listaServidorcartas.add(GestorCartasGenerico.ServidorCartas.SSTT.getDescricao());
/* 308 */         listaEstadoMar = new ArrayList<>();
/* 309 */         listaEstadoMar.add(PerfilUsuario.GestorEstadoMar.BEAUFORT.getDescricao());
/* 310 */         listaEstadoMar.add(PerfilUsuario.GestorEstadoMar.SIMPER.getDescricao());
/*     */         
/* 312 */         if (dados.getValue() != null) {
/* 313 */           if (dados.getValue() instanceof String) {
/* 314 */             if (listaServidorcartas.contains(dados.getValue().toString())) {
/* 315 */               for (GestorCartasGenerico.ServidorCartas tipo : GestorCartasGenerico.ServidorCartas.values()) {
/* 316 */                 cb.getItems().add(tipo);
/*     */               }
/*     */             }
/*     */             
/* 320 */             if (listaEstadoMar.contains(dados.getValue().toString())) {
/* 321 */               for (PerfilUsuario.GestorEstadoMar estado : PerfilUsuario.GestorEstadoMar.values()) {
/* 322 */                 cb.getItems().add(estado);
/*     */               }
/*     */             }
/*     */             
/* 326 */             cb.getSelectionModel().select(dados.getValue());
/*     */           } 
/* 328 */           if (dados.getValue() instanceof Simbologia) {
/* 329 */             List list; DAO<Simbologia> daosimbologia = new DAO(Simbologia.class);
/*     */ 
/*     */             
/* 332 */             ObservableList observableList = FXCollections.observableArrayList();
/*     */             try {
/* 334 */               list = daosimbologia.listarTodos();
/* 335 */             } catch (Exception ex) {
/* 336 */               Log.gravarLogExcecao("Erro ao listar simbologia", this, ex);
/*     */             } 
/* 338 */             for (Simbologia simbologia : list) {
/* 339 */               cb.getItems().add(simbologia.toString());
/*     */             }
/* 341 */             cb.getSelectionModel().select(dados.getValue().toString());
/*     */           } 
/*     */ 
/*     */           
/* 345 */           if (dados.getValue() instanceof AmbienteInstalacao) {
/* 346 */             for (AmbienteInstalacao.Ambiente a : listaAmbienteInstalacao) {
/* 347 */               if (dados.getValue().toString().equals(a.name().toString())) {
/* 348 */                 cb.getSelectionModel().select(a);
/*     */               }
/* 350 */               cb.getItems().add(a);
/*     */             } 
/*     */           }
/*     */         } 
/* 354 */         this.mapaCampos.put(cb, nome);
/* 355 */         return (Node)cb;
/*     */     } 
/* 357 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public HBox getPai() {
/* 366 */     return this.pai;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public VBox getColunasCampos() {
/* 375 */     return this.colunasCampos;
/*     */   }
/*     */   
/*     */   public HBox getLinhasCampos() {
/* 379 */     return this.linhasCampos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Button getBotaoOk() {
/* 388 */     return this.botaoOk;
/*     */   }
/*     */   
/*     */   public Button getBotaoCancelar() {
/* 392 */     return this.botaoCancelar;
/*     */   }
/*     */   
/*     */   public void setCss(String id) {
/* 396 */     this.pai.setId(id);
/* 397 */     this.linhasCampos.setId(id);
/* 398 */     this.colunasCampos.setId(id);
/*     */   }
/*     */ 
/*     */   
/*     */   private String getValorNode(Node n) {
/* 403 */     if (n instanceof TextField) {
/* 404 */       return ((TextField)n).getText();
/*     */     }
/* 406 */     if (n instanceof CheckBox) {
/* 407 */       return String.valueOf(((CheckBox)n).isSelected());
/*     */     }
/* 409 */     if (n instanceof ComboBox) {
/* 410 */       return String.valueOf(((ComboBox)n).getSelectionModel().getSelectedItem());
/*     */     }
/* 412 */     return null;
/*     */   }
/*     */   
/*     */   public Map<Node, String> getMapaCampos() {
/* 416 */     return this.mapaCampos;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapaColunaLabels() {
/* 420 */     return this.mapaColunaLabels;
/*     */   }
/*     */   
/*     */   public Map<Integer, Node> getMapaColunaCampos() {
/* 424 */     return this.mapaColunaCampos;
/*     */   }
/*     */   
/*     */   public Map<Integer, String> getMapaColunaMedidas() {
/* 428 */     return this.mapaColunaMedidas;
/*     */   }
/*     */   
/*     */   public void limparTodos() {
/* 432 */     for (Node n : this.colunasCampos.getChildren()) {
/* 433 */       if (n instanceof TextField) {
/* 434 */         ((TextField)n).clear(); continue;
/* 435 */       }  if (n instanceof CheckBox) {
/* 436 */         ((CheckBox)n).setSelected(false);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object extrairDadosFormulario() {
/* 448 */     this.validaFormulario = true;
/* 449 */     for (Node n : this.mapaCampos.keySet()) {
/*     */       
/* 451 */       String id = this.mapaCampos.get(n);
/* 452 */       String param = getValorNode(n);
/*     */       
/* 454 */       if (this.classObjeto == null) {
/* 455 */         this.classObjeto = ((DadosExtraidos)this.mapaForm.get(id)).getClassObjeto();
/*     */         try {
/* 457 */           this.novoObjeto = this.classObjeto.newInstance();
/* 458 */         } catch (InstantiationException|IllegalAccessException ex) {
/* 459 */           Log.gravarLogExcecao("Erro ao extrair dados do formulário", this, ex);
/* 460 */           this.validaFormulario = false;
/*     */         } 
/*     */       } 
/*     */       
/* 464 */       Object temp = ExtratorDados.extratorDadosFromFormulario(getNovoObjeto().getClass(), getNovoObjeto(), id, param);
/* 465 */       if (temp == null) {
/* 466 */         this.validaFormulario = false;
/*     */       }
/*     */     } 
/* 469 */     return getNovoObjeto();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object extrairDadosFormulario(Object o) {
/* 480 */     this.validaFormulario = true;
/* 481 */     for (Node n : this.mapaCampos.keySet()) {
/*     */       
/* 483 */       String id = this.mapaCampos.get(n);
/* 484 */       String param = getValorNode(n);
/*     */       
/* 486 */       if (this.classObjeto == null) {
/* 487 */         this.classObjeto = ((DadosExtraidos)this.mapaForm.get(id)).getClassObjeto();
/*     */         try {
/* 489 */           this.novoObjeto = this.classObjeto.newInstance();
/* 490 */         } catch (InstantiationException|IllegalAccessException ex) {
/* 491 */           Log.gravarLogExcecao("Erro ao extrair dados do formulário", this, ex);
/* 492 */           this.validaFormulario = false;
/*     */         } 
/*     */       } 
/*     */       
/* 496 */       Object temp = ExtratorDados.extratorDadosFromFormulario(o.getClass(), o, id, param);
/* 497 */       if (temp == null) {
/* 498 */         this.validaFormulario = false;
/*     */       }
/*     */     } 
/* 501 */     return getNovoObjeto();
/*     */   }
/*     */   
/*     */   public boolean isValidaFormulario() {
/* 505 */     return this.validaFormulario;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getNovoObjeto() {
/* 512 */     return this.novoObjeto;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String validarDadosFormulario() {
/* 521 */     for (Node n : this.mapaCampos.keySet()) {
/*     */       
/* 523 */       String id = this.mapaCampos.get(n);
/* 524 */       String param = getValorNode(n);
/*     */       
/* 526 */       DadosExtraidos de = this.mapaForm.get(id);
/* 527 */       if (de.getOrdem() == 3 && param.equals(""))
/*     */         continue; 
/* 529 */       Validacao validacao = de.getValidacao();
/* 530 */       if (validacao != null) {
/*     */         
/* 532 */         if (validacao.isDigitOnly() && !isNumber(param)) {
/* 533 */           return id.concat(" Valor de entrada inválido");
/*     */         }
/*     */         
/* 536 */         if (validacao.isTextOnly() && isNumber(param)) {
/* 537 */           return id.concat("Valor de entrada inválido");
/*     */         }
/*     */         
/* 540 */         if (param.length() > de.getValidacao().getMaxChar()) {
/* 541 */           return id.concat("Número de caracteres é maior que o permitido");
/*     */         }
/* 543 */         if (validacao.isDigitOnly() && isNumber(param)) {
/* 544 */           Number number = null;
/* 545 */           if (isInteger(param)) {
/* 546 */             number = new Integer(param);
/*     */           }
/* 548 */           else if (isFloat(param)) {
/* 549 */             number = new Float(param);
/*     */           } 
/* 551 */           if (number != null) {
/* 552 */             if (number.intValue() > validacao.getMaxValor()) {
/* 553 */               return id.concat("O valor de entrada é maior que o valor permitido");
/*     */             }
/*     */           } else {
/* 556 */             return "";
/*     */           } 
/*     */           
/* 559 */           if (param.length() > de.getValidacao().getMaxChar()) {
/* 560 */             return id.concat("Número de caracteres é maior que o permitido");
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 566 */     return "ok";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isInteger(String str) {
/* 576 */     return str.matches("^[0-9]+$");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isFloat(String str) {
/* 586 */     return str.matches("[\\.\\-0-9]*");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isNumber(String str) {
/* 596 */     return (isInteger(str) || isFloat("[\\.\\-0-9]*"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAlterarObjeto(boolean alterarObjeto) {
/* 601 */     this.alterarObjeto = alterarObjeto;
/*     */   }
/*     */   
/*     */   private class AcaoBotao implements EventHandler<ActionEvent> {
/*     */     private AcaoBotao() {}
/*     */     
/*     */     public void handle(ActionEvent event) {
/* 608 */       if (!FormularioJavaFX.this.alterarObjeto) {
/* 609 */         FormularioJavaFX.this.extrairDadosFormulario();
/*     */       }
/* 611 */       String ok = FormularioJavaFX.this.validarDadosFormulario();
/* 612 */       if (ok.equals("ok")) {
/* 613 */         FormularioJavaFX.this.acaoForm.acao();
/* 614 */         FormularioJavaFX.this.novoObjeto = null;
/* 615 */         FormularioJavaFX.this.classObjeto = null;
/*     */       } else {
/* 617 */         FormularioJavaFX.this.acaoForm.error(ok);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   private class AcaoCancelarBotao implements EventHandler<ActionEvent> {
/*     */     private AcaoCancelarBotao() {}
/*     */     
/*     */     public void handle(ActionEvent event) {
/* 626 */       String cancelar = FormularioJavaFX.this.validarDadosFormulario();
/* 627 */       if (!cancelar.equals("Cancelar"))
/* 628 */         FormularioJavaFX.this.acaoForm.cancelar(); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/gerador_formulario_fx/FormularioJavaFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */