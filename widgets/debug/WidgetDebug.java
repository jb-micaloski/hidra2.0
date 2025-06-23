/*     */ package ipqm.gsd.hidra.ihm.widgets.debug;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.banco_dados.DAOUsuarioMaquina;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.ObjetoCinematica;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.Posicao;
/*     */ import ipqm.gsd.componentes.dominio.entidades.Veiculo;
/*     */ import ipqm.gsd.componentes.nucleo.DistribuidorObjetos;
/*     */ import ipqm.gsd.componentes.nucleo.IHM;
/*     */ import ipqm.gsd.componentes.nucleo.Maquina;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*     */ import ipqm.gsd.componentes.nucleo.comando_remoto.AcaoComandoRemotoDebug;
/*     */ import ipqm.gsd.componentes.nucleo.comando_remoto.ComandoRemoto;
/*     */ import ipqm.gsd.componentes.nucleo.comando_remoto.GestorComandoRemoto;
/*     */ import ipqm.gsd.componentes.nucleo.comando_remoto.comandos.LoginRemoto;
/*     */ import ipqm.gsd.componentes.nucleo.comunicacao.ObjetoObservavel;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.logon.UsuarioMaquina;
/*     */ import ipqm.gsd.componentes.nucleo.notificador.Notificador;
/*     */ import ipqm.gsd.componentes.nucleo.util.ComandosSO;
/*     */ import ipqm.gsd.hidra.ihm.controle.Controlador;
/*     */ import ipqm.gsd.hidra.ihm.controle.Tela;
/*     */ import ipqm.gsd.hidra.ihm.dialogos.AcaoDialogo;
/*     */ import ipqm.gsd.hidra.ihm.dialogos.selecaoMultipla.DialogoSelecaoMultipla;
/*     */ import ipqm.gsd.hidra.ihm.projetos.geral.cenas.controladores.ControladorLogin;
/*     */ import ipqm.gsd.hidra.ihm.visao.Cena;
/*     */ import ipqm.gsd.hidra.ihm.widgets.Widget;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import javafx.application.Platform;
/*     */ import javafx.fxml.FXMLLoader;
/*     */ import javafx.scene.control.TextInputDialog;
/*     */ import javafx.scene.input.KeyCode;
/*     */ import javafx.scene.input.KeyEvent;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WidgetDebug
/*     */   extends Widget
/*     */   implements AcaoComandoRemotoDebug, IHM
/*     */ {
/*     */   private AnchorPane root;
/*     */   private AnchorPane anchorPane;
/*     */   private ControladorPainelDebug controladorPainelDebug;
/*     */   private Controlador controladorExibido;
/*     */   private DialogoSelecaoMultipla selecao;
/*     */   private Modo modo;
/*     */   private List<Object> listaNomesUsuarios;
/*     */   
/*     */   public enum Modo
/*     */   {
/*  59 */     FECHADO,
/*  60 */     MINIMIZADO,
/*  61 */     MAXIMIZADO;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WidgetDebug(Tela tela) {
/*  69 */     super(Widget.Tipo.DEBUG, tela);
/*  70 */     setSempreVisivel(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public void construir() {
/*  75 */     DAOUsuarioMaquina dao = new DAOUsuarioMaquina();
/*  76 */     List<UsuarioMaquina> listaUsuariosMaquina = new ArrayList<>();
/*     */     
/*     */     try {
/*  79 */       listaUsuariosMaquina = dao.listarTodos();
/*  80 */     } catch (Exception ex) {
/*  81 */       Log.gravarLogExcecao("Erro ao listar usuários", this, ex);
/*     */     } 
/*     */     
/*  84 */     this.listaNomesUsuarios = new ArrayList();
/*     */     
/*  86 */     for (UsuarioMaquina um : listaUsuariosMaquina) {
/*  87 */       if (um.getMaquina().equals(Maquina.getMaquinaLocal())) {
/*  88 */         this.listaNomesUsuarios.add(um.getUsuario());
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  93 */     Log.gravarLogInstrucao("Construindo widget debug", this);
/*     */     
/*  95 */     FXMLLoader fxmlLoader = new FXMLLoader(Widget.class.getResource("debug/PainelDebug.fxml"));
/*     */     
/*     */     try {
/*  98 */       this.root = (AnchorPane)fxmlLoader.load();
/*  99 */     } catch (IOException ex) {
/* 100 */       Log.gravarLogExcecao("Erro ao carregar FXML do widget", this, ex);
/*     */     } 
/*     */     
/* 103 */     this.controladorPainelDebug = (ControladorPainelDebug)fxmlLoader.getController();
/*     */     
/* 105 */     this.anchorPane = new AnchorPane();
/* 106 */     this.anchorPane.setLayoutX(0.0D);
/* 107 */     this.anchorPane.setLayoutY(0.0D);
/* 108 */     this.anchorPane.setMaxHeight(20.0D);
/*     */     
/* 110 */     Platform.runLater(() -> {
/*     */           this.anchorPane.getStylesheets().clear();
/*     */           
/*     */           this.anchorPane.getStylesheets().setAll((Object[])new String[] { "ipqm/gsd/hidra/ihm/css/widgets/debug.css" });
/*     */         });
/* 115 */     this.anchorPane.getChildren().addAll((Collection)this.root.getChildren());
/*     */     
/* 117 */     this.controladorPainelDebug.setWidgetDebug(this);
/* 118 */     GestorComandoRemoto.getInstancia().setAcaoComandoRemotoDebug(this);
/* 119 */     minimizar();
/*     */     
/* 121 */     Log.getInstancia().setOuvinte(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void fechar() {
/* 126 */     setModo(Modo.FECHADO);
/* 127 */     this.controladorPainelDebug.ocultarTudo();
/*     */   }
/*     */   
/*     */   public void minimizar() {
/* 131 */     setModo(Modo.MINIMIZADO);
/* 132 */     this.controladorPainelDebug.mostrarBarra();
/*     */   }
/*     */   
/*     */   public void maximizar() {
/* 136 */     setModo(Modo.MAXIMIZADO);
/* 137 */     this.controladorPainelDebug.mostrarPainel();
/*     */   }
/*     */   
/*     */   public Modo getModo() {
/* 141 */     return this.modo;
/*     */   }
/*     */   
/*     */   public void setModo(Modo modo) {
/* 145 */     this.modo = modo;
/*     */   }
/*     */ 
/*     */   
/*     */   public void exibir(Cena cena) {
/* 150 */     this.anchorPane.setVisible(true);
/* 151 */     if (cena.getControlador().getAnchorPanePai() != null) {
/* 152 */       cena.getControlador().getAnchorPanePai().getChildren().add(this.anchorPane);
/* 153 */       this.controladorExibido = cena.getControlador();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void ocultar(Cena cena) {
/* 159 */     this.anchorPane.setVisible(false);
/* 160 */     if (cena.getControlador().getAnchorPanePai() != null) {
/* 161 */       cena.getControlador().getAnchorPanePai().getChildren().remove(this.anchorPane);
/*     */     }
/* 163 */     this.controladorExibido = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isVisivel() {
/* 168 */     return this.anchorPane.isVisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public void desenharObjetos(List<ObjetoDOMINIO> listaObjetos) {
/* 173 */     this.controladorPainelDebug.atualiza();
/*     */   }
/*     */ 
/*     */   
/*     */   public void teclaPressionada(KeyEvent event) {
/* 178 */     super.teclaPressionada(event);
/* 179 */     switch (event.getCode()) {
/*     */       case TAB:
/* 181 */         if (this.controladorExibido instanceof ControladorLogin) {
/* 182 */           if (this.selecao == null) {
/* 183 */             this.selecao = new DialogoSelecaoMultipla("Usuários disponíveis", "Selecione um usuário para fazer login", this.controladorExibido, this.listaNomesUsuarios, false)
/*     */               {
/*     */                 
/*     */                 public void acao(AcaoDialogo acao, Object itemSelecionado)
/*     */                 {
/* 188 */                   if (acao == AcaoDialogo.OK) {
/* 189 */                     ((ControladorLogin)WidgetDebug.this.controladorExibido).login(itemSelecionado.toString(), "1234");
/*     */                   }
/* 191 */                   WidgetDebug.this.selecao = null; } };
/*     */             break;
/*     */           } 
/* 194 */           if (!this.controladorExibido.getAnchorPanePai().getChildren().contains(this.selecao.getControladorDialogoSelecaoMultipla().getAnchorPaneSelecao())) {
/* 195 */             this.selecao.exibir();
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case D:
/* 200 */         if (event.isControlDown()) {
/* 201 */           if (event.isAltDown()) {
/*     */             try {
/* 203 */               Runtime.getRuntime().exec("/opt/jdk" + System.getProperty("java.version") + "/bin/jconsole");
/* 204 */             } catch (IOException ex) {
/* 205 */               Log.gravarLogExcecao("Erro ao abrir o jconsole", this, ex);
/*     */             }  break;
/* 207 */           }  if (event.isShiftDown()) {
/* 208 */             ComandosSO.enviarComando(ComandosSO.Comando.EXIBIR_LOG, 
/* 209 */                 Log.getInstancia().getArquivoLogAtual().getAbsolutePath()); break;
/*     */           } 
/* 211 */           switch (getModo()) {
/*     */             case TAB:
/* 213 */               minimizar();
/*     */               break;
/*     */             case D:
/* 216 */               maximizar();
/*     */               break;
/*     */             case L:
/* 219 */               fechar();
/*     */               break;
/*     */           } 
/*     */         
/*     */         } 
/*     */         break;
/*     */       
/*     */       case L:
/* 227 */         if (event.isAltDown() && !event.isControlDown()) {
/*     */           try {
/* 229 */             Runtime.getRuntime().exec("gedit " + Log.getInstancia().getArquivoLogAtual().getAbsolutePath());
/* 230 */           } catch (IOException ex) {
/* 231 */             Log.gravarLogExcecao("Erro ao abrir o log", this, ex);
/*     */           } 
/*     */         }
/*     */         break;
/*     */       case R:
/* 236 */         if (event.isControlDown()) {
/* 237 */           Veiculo ppv = Veiculo.getVeiculoReferencial();
/* 238 */           if (ppv != null) {
/*     */             
/*     */             try {
/* 241 */               ObjetoCinematica cAuto = ppv.getCondicaoAssociada().getCinematicaAutomatica();
/* 242 */               ObjetoCinematica cEstimada = ppv.getCondicaoAssociada().getCinematicaEstimada();
/* 243 */               ObjetoCinematica cSimulada = ppv.getCondicaoAssociada().getCinematicaSimulada();
/* 244 */               ObjetoCinematica cInicial = ppv.getCondicaoAssociada().getCinematicaInicial();
/*     */               
/* 246 */               cAuto.setPosicao((Posicao)cInicial.getPosicao().clone());
/* 247 */               cAuto.getRumo().setRumoFundo(cInicial.getRumo().getRumoFundo());
/* 248 */               cAuto.getRumo().setRumoSuperficie(cInicial.getRumo().getRumoSuperficie());
/* 249 */               cAuto.getVelocidade().setVelocidadeFundo(cInicial.getVelocidade().getVelocidadeFundo());
/* 250 */               cAuto.getVelocidade().setVelocidadeSuperficie(cInicial.getVelocidade().getVelocidadeSuperficie());
/*     */               
/* 252 */               cEstimada.setPosicao((Posicao)cInicial.getPosicao().clone());
/* 253 */               cEstimada.getRumo().setRumoFundo(cInicial.getRumo().getRumoFundo());
/* 254 */               cEstimada.getRumo().setRumoSuperficie(cInicial.getRumo().getRumoSuperficie());
/* 255 */               cEstimada.getVelocidade().setVelocidadeFundo(cInicial.getVelocidade().getVelocidadeFundo());
/* 256 */               cEstimada.getVelocidade().setVelocidadeSuperficie(cInicial.getVelocidade().getVelocidadeSuperficie());
/*     */               
/* 258 */               cSimulada.setPosicao((Posicao)cInicial.getPosicao().clone());
/* 259 */               cSimulada.getRumo().setRumoFundo(cInicial.getRumo().getRumoFundo());
/* 260 */               cSimulada.getRumo().setRumoSuperficie(cInicial.getRumo().getRumoSuperficie());
/* 261 */               cSimulada.getVelocidade().setVelocidadeFundo(cInicial.getVelocidade().getVelocidadeFundo());
/* 262 */               cSimulada.getVelocidade().setVelocidadeSuperficie(cInicial.getVelocidade().getVelocidadeSuperficie());
/*     */               
/* 264 */               Notificador.notificacao("Posição do navio referencial redefinida para:", ppv
/* 265 */                   .getCondicaoAssociada().getCinematicaInicial().getPosicao().getCoordenadaGeografica().toString() + "\n" + cInicial
/* 266 */                   .getRumo().getRumoFundo() + " / " + cInicial
/* 267 */                   .getVelocidade().getVelocidadeFundo());
/*     */               
/* 269 */               ppv.salvarMemoria(false);
/*     */             }
/* 271 */             catch (Exception ex) {
/* 272 */               Log.gravarLogExcecao("Não foi possível clonar posição inicial", this, ex);
/*     */             } 
/*     */           }
/*     */         } 
/*     */         break;
/*     */       case E:
/* 278 */         if (event.isControlDown()) {
/*     */           
/* 280 */           int idCondicaoInicial = 0;
/*     */           
/* 282 */           String ip = null;
/*     */           
/* 284 */           TextInputDialog dialog = new TextInputDialog();
/* 285 */           dialog.setTitle("Login remoto");
/* 286 */           dialog.setHeaderText("Login remoto");
/* 287 */           dialog.setContentText("Id da condição inicial: ");
/* 288 */           Optional<String> result = dialog.showAndWait();
/* 289 */           if (result.isPresent()) {
/* 290 */             idCondicaoInicial = Integer.parseInt(result.get());
/*     */           }
/*     */           
/* 293 */           dialog = new TextInputDialog("127.0.0.1");
/* 294 */           dialog.setTitle("Login remoto");
/* 295 */           dialog.setHeaderText("Login remoto");
/* 296 */           dialog.setContentText("IP: ");
/* 297 */           result = dialog.showAndWait();
/* 298 */           if (result.isPresent()) {
/* 299 */             ip = result.get();
/*     */           }
/*     */           
/* 302 */           LoginRemoto login = new LoginRemoto(idCondicaoInicial, ip);
/* 303 */           login.setAbrirFuncionalidadeDestaque(true);
/* 304 */           GestorComandoRemoto.getInstancia().enviar((ComandoRemoto)login);
/*     */         } 
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public AnchorPane getAnchorPane() {
/* 312 */     return this.anchorPane;
/*     */   }
/*     */ 
/*     */   
/*     */   public void maximizarPainelDebug() {
/* 317 */     Platform.runLater(() -> maximizar());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void minimizarPainelDebug() {
/* 324 */     Platform.runLater(() -> minimizar());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void exibirIHM() {
/* 331 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void ocultarIHM() {
/* 336 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void configurar() {
/* 341 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void configurarObjetos() {
/* 346 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void configurarComportamentoJanela() {
/* 351 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void configurarEstadoPadrao() {
/* 356 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void configurarTopicoAjuda() {
/* 361 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ 
/*     */   
/*     */   public void notificarCarregamento(int passoAtual, String descricaoPasso) {
/* 366 */     this.controladorPainelDebug.setUltimoLog(descricaoPasso);
/*     */   }
/*     */ 
/*     */   
/*     */   public void notificaEstadoSincronismo(boolean estadoSincronismo) {
/* 371 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */   
/*     */   public void solicitarDumper() {
/* 375 */     List<ObjetoDOMINIO> comandos = new ArrayList<>();
/* 376 */     List<ObjetoObservavel> dumpers = new ArrayList<>();
/*     */     
/* 378 */     DistribuidorObjetos distribuidorObjetos = Mediador.getInstancia().getDistribuidor().getDistribuidorObjetos();
/*     */     
/* 380 */     if (Mediador.isSistemaIniciado()) {
/* 381 */       for (ObjetoObservavel o : distribuidorObjetos.getObjetoObservador().getObservaveis()) {
/* 382 */         if (o != null) {
/*     */           
/* 384 */           if (o.isOnline()) {
/* 385 */             Log.gravarLogInstrucao("Transicao OK para " + o.getIp(), this);
/*     */             
/* 387 */             Map<Integer, ObjetoDOMINIO> objetos = Mediador.getInstancia().getContextualizador().getContexto().getObjetos();
/* 388 */             Iterator<ObjetoDOMINIO> iterator = objetos.values().iterator();
/* 389 */             while (iterator.hasNext()) {
/* 390 */               ObjetoDOMINIO obj = iterator.next();
/* 391 */               if (obj.getDono().getNumeroMaquina() != o.getId()) {
/*     */                 continue;
/*     */               }
/* 394 */               obj.setAcao((byte)1);
/* 395 */               obj.setDistribuir(false);
/* 396 */               Log.gravarLogDebug("Existe objeto a excluir de " + o.getIp() + ": " + obj, this);
/* 397 */               comandos.add(obj);
/*     */             } 
/* 399 */             if (!dumpers.contains(o)) {
/* 400 */               Log.gravarLogDebug("Preciso de dumper de " + o.getIp(), this);
/* 401 */               dumpers.add(o);
/*     */             } 
/*     */           } 
/* 404 */           o.setTransicaoEstado(false);
/*     */         } 
/*     */       } 
/* 407 */       if (comandos.size() > 0) {
/* 408 */         Mediador.getInstancia().processarObjetos(comandos);
/* 409 */         Log.gravarLogInstrucao("Excluindo " + comandos.size() + " antes requisitar o dumper", this);
/*     */         try {
/* 411 */           Thread.sleep(1000L);
/* 412 */         } catch (InterruptedException ex) {
/* 413 */           Log.gravarLogExcecao("Thread interrompida, ocorreu algum erro no sleep", this, ex);
/*     */         } 
/*     */       } 
/* 416 */       Iterator<ObjetoObservavel> i = dumpers.iterator();
/* 417 */       while (i.hasNext()) {
/* 418 */         ObjetoObservavel o = i.next();
/* 419 */         Log.gravarLogInstrucao("Solicitar dumper para: " + o.getIp(), this);
/* 420 */         distribuidorObjetos.getObjetoObservador().solicitarDumper(o);
/*     */       } 
/* 422 */       dumpers.clear();
/* 423 */       comandos.clear();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/debug/WidgetDebug.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */