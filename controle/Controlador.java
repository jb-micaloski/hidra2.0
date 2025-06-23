/*     */ package ipqm.gsd.hidra.ihm.controle;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.ObjetoTatico;
/*     */ import ipqm.gsd.componentes.nucleo.IHM;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*     */ import ipqm.gsd.componentes.nucleo.ThreadPool;
/*     */ import ipqm.gsd.componentes.nucleo.configuracao.Diretorios;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.logon.perfis.PerfilUsuario;
/*     */ import ipqm.gsd.componentes.nucleo.notificador.Notificador;
/*     */ import ipqm.gsd.hidra.ihm.interacao.tarefa.Comando;
/*     */ import ipqm.gsd.hidra.ihm.objetos_visuais.objetos_textuais.ObjetoTextual;
/*     */ import ipqm.gsd.hidra.ihm.util.UtilDesempenho;
/*     */ import ipqm.gsd.hidra.ihm.visao.Cena;
/*     */ import ipqm.gsd.hidra.ihm.widgets.ajuda_contexto.TopicoAjuda;
/*     */ import ipqm.gsd.hidra.ihm.widgets.pesquisa.itens.ItemPesquisa;
/*     */ import ipqm.gsd.hidra.ihm.widgets.pesquisa.itens.ItemPesquisaAjuda;
/*     */ import java.io.File;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javafx.animation.FadeTransition;
/*     */ import javafx.application.Platform;
/*     */ import javafx.collections.ObservableList;
/*     */ import javafx.embed.swing.SwingFXUtils;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.control.Control;
/*     */ import javafx.scene.control.Menu;
/*     */ import javafx.scene.control.MenuItem;
/*     */ import javafx.scene.image.Image;
/*     */ import javafx.scene.image.WritableImage;
/*     */ import javafx.scene.input.KeyCode;
/*     */ import javafx.scene.input.KeyEvent;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ import javafx.scene.layout.Pane;
/*     */ import javax.imageio.ImageIO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Controlador
/*     */   extends Control
/*     */   implements IHM
/*     */ {
/*     */   protected Cena cena;
/*     */   private AnchorPane anchorPaneAnimar;
/*     */   private AnchorPane anchorPanePai;
/*  52 */   private final List<String> listaCss = new ArrayList<>();
/*     */   
/*     */   public Controlador pai;
/*     */   
/*     */   private Tela tela;
/*     */   
/*     */   protected TopicoAjuda topicoAjuda;
/*     */   
/*     */   private boolean primeiraExibicao = true;
/*     */   
/*     */   private String nome;
/*     */ 
/*     */   
/*     */   public void notificaEstadoSincronismo(boolean estadoSincronismo) {}
/*     */ 
/*     */   
/*     */   public void teclaSolta(KeyEvent event) {
/*  69 */     switch (event.getCode()) {
/*     */       case WINDOWS:
/*  71 */         getTela().trocar("projetos/geral/cenas/TelaPrincipal.fxml");
/*     */         break;
/*     */       case F2:
/*     */       case F3:
/*     */       case F4:
/*     */       case F5:
/*     */       case F6:
/*     */       case F7:
/*     */       case F8:
/*     */       case F9:
/*     */       case F10:
/*  82 */         if (!event.isControlDown() && !event.isAltDown() && !event.isShiftDown()) {
/*  83 */           getTela().trocar(Integer.valueOf(event.getCode().getName().substring(1)).intValue() - 1);
/*     */         }
/*     */         break;
/*     */       case F11:
/*  87 */         getTela().getCenaAtual().getControlador().capturarTela();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void teclaPressionada(KeyEvent event) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Cena getCena() {
/* 101 */     return this.cena;
/*     */   }
/*     */   
/*     */   public void setCena(Cena cena) {
/* 105 */     this.cena = cena;
/*     */   }
/*     */   
/*     */   public void setTela(Tela tela) {
/* 109 */     this.tela = tela;
/*     */   }
/*     */   
/*     */   public Tela getTela() {
/* 113 */     return this.tela;
/*     */   }
/*     */   
/*     */   protected void setAnchorPaneAnimar(AnchorPane painel) {
/* 117 */     this.anchorPaneAnimar = painel;
/*     */   }
/*     */   
/*     */   public void animarEntrada() {
/* 121 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)getAnchorPaneAnimar());
/* 122 */     fadeTransition.setFromValue(0.0D);
/* 123 */     fadeTransition.setToValue(1.0D);
/* 124 */     fadeTransition.play();
/*     */   }
/*     */   
/*     */   public void animarSaida() {
/* 128 */     FadeTransition fadeTransition = new FadeTransition(UtilDesempenho.duracaoAnimacao(300.0D), (Node)getAnchorPaneAnimar());
/* 129 */     fadeTransition.setFromValue(1.0D);
/* 130 */     fadeTransition.setToValue(0.0D);
/* 131 */     fadeTransition.play();
/*     */   }
/*     */ 
/*     */   
/*     */   public void exibirIHM() {
/* 136 */     Log.gravarLogInterface("Exibindo '" + this.cena.getNome() + "' em tela " + (this.tela.getNumTela() + 1), this);
/*     */     
/* 138 */     if (getAnchorPaneAnimar() != null) {
/* 139 */       animarEntrada();
/*     */     }
/*     */     
/* 142 */     if (this.primeiraExibicao) {
/* 143 */       primeiraExibicao();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void ocultarIHM() {
/* 150 */     if (getAnchorPaneAnimar() != null) {
/* 151 */       animarSaida();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void configurar() {
/* 157 */     this.listaCss.add(Aplicacao.getInstancia().getCssPadrao());
/* 158 */     configurarTopicoAjuda();
/* 159 */     configurarObjetos();
/* 160 */     configurarEstadoPadrao();
/* 161 */     configurarComportamentoJanela();
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
/*     */   public void exibirObjeto(Controlador pai, Object objeto) {
/* 173 */     exibirIHM();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void configurarObjetos() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void configurarComportamentoJanela() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void configurarEstadoPadrao() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void desenharObjetos(List<ObjetoDOMINIO> listaObjetos) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void notificarCarregamento(int passoAtual, String descricaoPasso) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void primeiraExibicao() {
/* 202 */     this.primeiraExibicao = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean verificaCondicoesExibicao(Cena cena) {
/* 213 */     return true;
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
/*     */ 
/*     */   
/*     */   public boolean verificaCondicoesOcultacao(Cena cena) {
/* 230 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCSS(String... css) {
/* 240 */     this.listaCss.clear();
/* 241 */     this.listaCss.addAll(Arrays.asList(css));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void aplicarCss() {
/* 249 */     Platform.runLater(() -> {
/*     */           getCena().getScene().getStylesheets().clear();
/*     */           getCena().getScene().getStylesheets().addAll(this.listaCss);
/*     */           getCena().getScene().getStylesheets().add(Aplicacao.getInstancia().getCssPadrao());
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnchorPane getAnchorPaneAnimar() {
/* 260 */     return this.anchorPaneAnimar;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void desenha(ObjetoTextual objetoTextual) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Controlador getPai() {
/* 275 */     return this.pai;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPai(Controlador pai) {
/* 282 */     this.pai = pai;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void carregarJanela(Controlador pai, ObjetoTatico obj) {
/* 293 */     setPai(pai);
/* 294 */     configurar();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void carregarJanela(Controlador pai, ObjetoTatico obj1, ObjetoTatico obj2) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void carregarJanela(List<ObjetoTatico> lista, Controlador pai) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAnchorPanePai(AnchorPane anchorPane) {
/* 310 */     this.anchorPanePai = anchorPane;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnchorPane getAnchorPanePai() {
/* 319 */     return this.anchorPanePai;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void configurarTopicoAjuda() {
/* 329 */     if (getCena() == null && !(this instanceof ipqm.gsd.hidra.ihm.projetos.geral.formularios.controladores.ControladorFormularioPadrao)) {
/*     */       return;
/*     */     }
/*     */     
/* 333 */     StringBuilder pathStr = new StringBuilder(Diretorios.getDiretorioPadrao(Diretorios.Diretorio.AJUDA));
/*     */     
/* 335 */     if (Mediador.getInstancia() != null) {
/* 336 */       pathStr.append(PerfilUsuario.getPerfilUsuarioAtual().getSimpleName().toLowerCase()).append("/");
/*     */     }
/*     */     
/* 339 */     pathStr.append(getClass().getSimpleName().replaceAll("Controlador", "").toLowerCase());
/*     */     
/* 341 */     pathStr.append(".html");
/*     */     
/* 343 */     File arqPadrao = new File(pathStr.toString());
/*     */     
/* 345 */     if (arqPadrao.exists()) {
/* 346 */       String titulo = (getCena() == null) ? getNome() : getCena().getNome();
/* 347 */       TopicoAjuda ta = new TopicoAjuda(titulo, "file:" + arqPadrao.toString());
/* 348 */       Aplicacao.getInstancia().addTopicoAjuda(ta);
/* 349 */       this.topicoAjuda = ta;
/*     */       
/* 351 */       Aplicacao.getInstancia().addItemPesquisa((ItemPesquisa)new ItemPesquisaAjuda(this.topicoAjuda, "Ajuda > Tópicos > " + titulo));
/* 352 */       Log.gravarLogInstrucao("Tópico de ajuda adicionado em '" + titulo + "'. Caminho: " + pathStr.toString(), this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TopicoAjuda getTopicoAjuda() {
/* 362 */     return this.topicoAjuda;
/*     */   }
/*     */   
/*     */   public void setTopicoAjuda(TopicoAjuda topicoAjuda) {
/* 366 */     this.topicoAjuda = topicoAjuda;
/*     */   }
/*     */   
/*     */   public void capturarTela() {
/* 370 */     Platform.runLater(() -> {
/*     */           WritableImage snapshot = getTela().getStage().getScene().snapshot(new WritableImage(1920, 1080));
/*     */           if (snapshot != null) {
/*     */             ThreadPool.executar((), "Captura de tela");
/*     */           } else {
/*     */             Log.gravarLogExcecao("Não foi possível capturar a tela", this, null);
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
/*     */   public void habilitarItensMenu(Map<Comando, Boolean> comandosHabilitados, ObservableList<MenuItem> itens) {
/*     */     try {
/* 443 */       for (MenuItem item : itens)
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 451 */         if (!(item instanceof Menu)) {
/* 452 */           item.setDisable(!((Boolean)comandosHabilitados.get(Comando.valueOf(item.getId()))).booleanValue());
/*     */ 
/*     */           
/*     */           continue;
/*     */         } 
/*     */ 
/*     */         
/* 459 */         Menu subMenu = (Menu)item;
/* 460 */         habilitarItensMenu(comandosHabilitados, subMenu.getItems());
/*     */         
/* 462 */         if (!subMenu.getItems().isEmpty()) {
/*     */           int i;
/*     */ 
/*     */ 
/*     */           
/* 467 */           ObservableList<MenuItem> itensSubMenu = subMenu.getItems();
/* 468 */           boolean habilitado = false;
/*     */           
/* 470 */           for (MenuItem itemSubMenu : itensSubMenu)
/*     */           {
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 476 */             i = habilitado | (!itemSubMenu.isDisable() ? 1 : 0);
/*     */           }
/*     */ 
/*     */           
/* 480 */           subMenu.setDisable((i == 0));
/*     */         }
/*     */       
/*     */       }
/*     */     
/* 485 */     } catch (NullPointerException ex) {
/* 486 */       Log.gravarLogExcecao("Erro! Um menu de contexto ou um de seus submenus esta vazio. ", this, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Pane getPainelModal() {
/* 494 */     return null;
/*     */   }
/*     */   
/*     */   public String getNome() {
/* 498 */     return this.nome;
/*     */   }
/*     */   
/*     */   public void setNome(String nome) {
/* 502 */     this.nome = nome;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnchorPane getCenarioTaticoAnchorPane() {
/* 509 */     return null;
/*     */   }
/*     */   
/*     */   public void setCenarioTaticoAnchorPane(AnchorPane cenarioTaticoAnchorPane) {}
/*     */   
/*     */   public void exibirPainelFormulario(String fxml, String titulo, ObjetoTatico obj) {}
/*     */   
/*     */   public void exibirPainelFormulario(String fxml, String titulo, ObjetoTatico obj1, ObjetoTatico obj2) {}
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/controle/Controlador.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */