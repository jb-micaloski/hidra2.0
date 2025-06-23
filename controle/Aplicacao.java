/*     */ package ipqm.gsd.hidra.ihm.controle;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.gestao.integracao.GestorIntegracaoSimPass;
/*     */ import ipqm.gsd.componentes.dominio.teatro_operacao.UsuarioMaquinaCondicao;
/*     */ import ipqm.gsd.componentes.nucleo.Ambiente;
/*     */ import ipqm.gsd.componentes.nucleo.IHM;
/*     */ import ipqm.gsd.componentes.nucleo.Maquina;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.NucleoAmbiente;
/*     */ import ipqm.gsd.componentes.nucleo.ThreadPool;
/*     */ import ipqm.gsd.componentes.nucleo.comando_remoto.AcaoComandoRemoto;
/*     */ import ipqm.gsd.componentes.nucleo.comando_remoto.GestorComandoRemoto;
/*     */ import ipqm.gsd.componentes.nucleo.contexto.BloquearTela;
/*     */ import ipqm.gsd.componentes.nucleo.gestao.GestorIntegracao;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.logon.Funcionalidade;
/*     */ import ipqm.gsd.componentes.nucleo.notificador.Notificacao;
/*     */ import ipqm.gsd.componentes.nucleo.notificador.Notificador;
/*     */ import ipqm.gsd.componentes.nucleo.util.ComandosSO;
/*     */ import ipqm.gsd.componentes.nucleo.util.depuracao.Debug;
/*     */ import ipqm.gsd.hidra.ihm.HidraFX;
/*     */ import ipqm.gsd.hidra.ihm.dialogos.AcaoDialogo;
/*     */ import ipqm.gsd.hidra.ihm.dialogos.alerta.DialogoAlerta;
/*     */ import ipqm.gsd.hidra.ihm.dispositivos.controladores_jogo.GestorDispositivos;
/*     */ import ipqm.gsd.hidra.ihm.visao.Cena;
/*     */ import ipqm.gsd.hidra.ihm.visualizacao_remota.IVisualizacaoRemotaCaptura;
/*     */ import ipqm.gsd.hidra.ihm.visualizacao_remota.IVisualizacaoRemotaInicia;
/*     */ import ipqm.gsd.hidra.ihm.visualizacao_remota.Requisicao;
/*     */ import ipqm.gsd.hidra.ihm.visualizacao_remota.VisualizacaoRemota;
/*     */ import ipqm.gsd.hidra.ihm.widgets.Widget;
/*     */ import ipqm.gsd.hidra.ihm.widgets.ajuda_contexto.TopicoAjuda;
/*     */ import ipqm.gsd.hidra.ihm.widgets.bloqueio_tela.WidgetBloqueioTela;
/*     */ import ipqm.gsd.hidra.ihm.widgets.easter_egg.WidgetEasterEgg;
/*     */ import ipqm.gsd.hidra.ihm.widgets.pesquisa.itens.ItemPesquisa;
/*     */ import ipqm.gsd.hidra.ihm.widgets.pesquisa.itens.ItemPesquisaCena;
/*     */ import ipqm.gsd.smaps.util.license.License;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import javafx.application.Application;
/*     */ import javafx.application.Platform;
/*     */ import javafx.scene.text.Font;
/*     */ import javafx.stage.Screen;
/*     */ import javafx.stage.Stage;
/*     */ 
/*     */ public abstract class Aplicacao
/*     */   extends Application
/*     */   implements IHM, IVisualizacaoRemotaCaptura, IVisualizacaoRemotaInicia, AcaoComandoRemoto, BloquearTela
/*     */ {
/*     */   public static final int TOTAL_PASSOS_FIXOS = 9;
/*     */   private static Aplicacao instancia;
/*     */   private String cssPadrao;
/*     */   protected IHM ihmCarregamento;
/*     */   private ModoOperacao modoOperacao;
/*     */   private int porcentagemCarregada;
/*     */   private int passoAtual;
/*     */   
/*     */   public enum ModoOperacao
/*     */   {
/*  64 */     DIURNO, NOTURNO;
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
/*  77 */   protected final Map<Integer, Tela> telas = new HashMap<>();
/*     */   
/*  79 */   private final List<TopicoAjuda> topicosDeAjuda = new ArrayList<>();
/*  80 */   private final List<ItemPesquisa> itensPesquisa = new ArrayList<>();
/*     */   
/*     */   protected Requisicao requisicao;
/*     */   
/*     */   private VisualizacaoRemota vrCaptura;
/*     */   
/*     */   private VisualizacaoRemota vrRequisicao;
/*     */   private static Stage stage;
/*     */   private Encerrar encerrar;
/*     */   
/*     */   public void start(Stage stage) throws Exception {
/*  91 */     this; Aplicacao.stage = stage;
/*     */     
/*  93 */     Locale.setDefault(new Locale("pt", "BR"));
/*  94 */     Locale.setDefault(Locale.Category.FORMAT, Locale.ENGLISH);
/*     */     
/*  96 */     if (instancia == null) {
/*  97 */       instancia = this;
/*     */     } else {
/*  99 */       throw new Exception("Aplicação deve ser única");
/*     */     } 
/*     */ 
/*     */     
/* 103 */     if (Ambiente.getInstance().isUnix()) {
/* 104 */       GestorDispositivos.getInstancia().iniciar();
/*     */     }
/*     */     
/* 107 */     this.cssPadrao = "ipqm/gsd/hidra/ihm/css/modoDiurno.css";
/*     */     
/* 109 */     int num = 0;
/* 110 */     int quantidadeTelas = Integer.parseInt(Ambiente.getInstance()
/* 111 */         .obterValorVariavelAmbiente(NucleoAmbiente.QUANTIDADE_TELAS));
/* 112 */     int telaPrincipal = Integer.parseInt(Ambiente.getInstance()
/* 113 */         .obterValorVariavelAmbiente(NucleoAmbiente.TELA_PRINCIPAL)) - 1;
/*     */ 
/*     */     
/* 116 */     if (telaPrincipal > Screen.getScreens().size() - 1) {
/* 117 */       telaPrincipal = 0;
/*     */     }
/*     */ 
/*     */     
/* 121 */     if (quantidadeTelas > Screen.getScreens().size()) {
/* 122 */       quantidadeTelas = Screen.getScreens().size();
/*     */     }
/*     */ 
/*     */     
/* 126 */     this.telas.put(Integer.valueOf(num), new Tela(stage, (Screen)Screen.getScreens().get(telaPrincipal), num));
/*     */ 
/*     */     
/* 129 */     for (Screen src : Screen.getScreens()) {
/* 130 */       if (!src.equals(Screen.getScreens().get(telaPrincipal))) {
/* 131 */         num++;
/* 132 */         if (num >= quantidadeTelas) {
/*     */           break;
/*     */         }
/* 135 */         this.telas.put(Integer.valueOf(num), new Tela(src, num));
/*     */       } 
/*     */     } 
/*     */     
/* 139 */     this.modoOperacao = ModoOperacao.DIURNO;
/*     */ 
/*     */     
/* 142 */     Font.loadFont(HidraFX.class.getResource("css/fontes/Roboto-Thin.ttf").toExternalForm(), 10.0D);
/* 143 */     Font.loadFont(HidraFX.class.getResource("css/fontes/ocr-aregular.ttf").toExternalForm(), 10.0D);
/* 144 */     Font.loadFont(HidraFX.class.getResource("css/fontes/fontawesome-webfont.ttf").toExternalForm(), 10.0D);
/*     */     
/* 146 */     configurar();
/*     */   }
/*     */   
/*     */   public Tela getTela(int pos) {
/* 150 */     return this.telas.get(Integer.valueOf(pos));
/*     */   }
/*     */   
/*     */   public Tela getTelaPrincipal() {
/* 154 */     return getTela(0);
/*     */   }
/*     */   
/*     */   public Map<Integer, Tela> getTelas() {
/* 158 */     return this.telas;
/*     */   }
/*     */   
/*     */   public VisualizacaoRemota getVrRequisicao() {
/* 162 */     return this.vrRequisicao;
/*     */   }
/*     */   
/*     */   public void setVrRequisicao(VisualizacaoRemota vrRequisicao) {
/* 166 */     this.vrRequisicao = vrRequisicao;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void trocarTudo(String cenaNome) {
/* 175 */     Iterator<Tela> it = this.telas.values().iterator();
/* 176 */     while (it.hasNext()) {
/* 177 */       Tela tela = it.next();
/* 178 */       tela.trocar(cenaNome);
/*     */     } 
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
/*     */   public boolean trocar(Cena cena, int tela) {
/* 191 */     return getTela(tela).trocar(cena);
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
/*     */   public boolean trocar(String cenaNome, int tela) {
/* 203 */     return getTela(tela).trocar(cenaNome);
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
/*     */   public boolean trocar(int quadro, int tela) {
/* 215 */     return getTela(tela).trocar(quadro);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void fechaTudo() {
/* 223 */     Iterator<Tela> it = this.telas.values().iterator();
/* 224 */     while (it.hasNext()) {
/* 225 */       Tela tela = it.next();
/* 226 */       tela.getStage().close();
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setPorcentagemCarregada(int porcentagemCarregada) {
/* 231 */     this.porcentagemCarregada = porcentagemCarregada;
/*     */   }
/*     */   
/*     */   public int getPorcentagemCarregada() {
/* 235 */     return this.porcentagemCarregada;
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
/*     */   private GestorIntegracao obtemGestorIntegracaoAtivo() {
/*     */     GestorIntegracaoSimPass gestorIntegracaoSimPass;
/* 257 */     GestorIntegracao gestorIntegracao = null;
/*     */     
/* 259 */     if (Mediador.isSistemaIniciado()) {
/* 260 */       gestorIntegracao = Mediador.getInstancia().getGestor().getGestorIntegracao();
/* 261 */     } else if (Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.INTEGRADOR_POPEYE_HABILITADO).equals("SIM")) {
/* 262 */       gestorIntegracaoSimPass = new GestorIntegracaoSimPass();
/*     */     } 
/*     */     
/* 265 */     return (GestorIntegracao)gestorIntegracaoSimPass;
/*     */   }
/*     */ 
/*     */   
/*     */   public void logoff(String parametros) {
/* 270 */     GestorIntegracao gi = obtemGestorIntegracaoAtivo();
/* 271 */     if (gi != null) {
/* 272 */       gi.encerrar();
/*     */     }
/* 274 */     Log.gravarLogInstrucao("Comando de logoff requisitado. Parâmetros: " + parametros, this);
/* 275 */     encerrar(ComandosSO.Comando.LOGOFF, parametros);
/*     */   }
/*     */ 
/*     */   
/*     */   public void reiniciarClienteFonia() {
/* 280 */     Log.gravarLogInstrucao("Comando reiniciar cliente fonia requisitado.", this);
/*     */ 
/*     */     
/* 283 */     String params = Maquina.getMaquinaLocal().getHostname() + " " + Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.IP_SERVIDOR_FONIA) + " " + Fonia.getTipoFonia() + " " + Fonia.getInternaEstereo();
/* 284 */     ComandosSO.enviarComando(ComandosSO.Comando.REINICIAR_CLIENTE_FONIA, params);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reiniciarAplicacao() {
/* 292 */     reiniciarAplicacao("");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reiniciarAplicacao(String parametros) {
/* 300 */     GestorIntegracao gi = obtemGestorIntegracaoAtivo();
/* 301 */     if (gi != null) {
/* 302 */       gi.encerrar();
/*     */     }
/* 304 */     Log.gravarLogInstrucao("Comando de reiniciar aplicação requisitado. Parâmetros: " + parametros, this);
/* 305 */     encerrar(ComandosSO.Comando.REINICIAR_APLICACAO, parametros);
/*     */   }
/*     */ 
/*     */   
/*     */   public void desligar() {
/* 310 */     GestorIntegracao gi = obtemGestorIntegracaoAtivo();
/* 311 */     if (gi != null) {
/* 312 */       gi.desligar(false);
/*     */     }
/* 314 */     Log.gravarLogInstrucao("Comando de desligar requisitado", this);
/* 315 */     encerrar(ComandosSO.Comando.DESLIGAR);
/*     */   }
/*     */ 
/*     */   
/*     */   public void reiniciar() {
/* 320 */     GestorIntegracao gi = obtemGestorIntegracaoAtivo();
/* 321 */     if (gi != null) {
/* 322 */       gi.desligar(true);
/*     */     }
/* 324 */     Log.gravarLogInstrucao("Comando de reiniciar requisitado", this);
/* 325 */     encerrar(ComandosSO.Comando.REINICIAR);
/*     */   }
/*     */   
/*     */   private void encerrar(ComandosSO.Comando comando) {
/* 329 */     encerrar(comando, "");
/*     */   }
/*     */   
/*     */   private void encerrar(ComandosSO.Comando comando, String parametros) {
/* 333 */     if (this.encerrar == null) {
/* 334 */       this.encerrar = new Encerrar(comando, parametros);
/* 335 */       Log.gravarLogInstrucao("Encerrando o sistema", this);
/*     */       
/* 337 */       trocarTudo("projetos/geral/cenas/Vazio.fxml");
/* 338 */       getTelaPrincipal().trocar("projetos/geral/cenas/Desligando.fxml");
/* 339 */       ThreadPool.executar(this.encerrar, "Encerrar");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Aplicacao getInstancia() {
/* 349 */     return instancia;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Cena getCenaAtual() {
/* 358 */     return getTelaPrincipal().getCenaAtual();
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
/*     */   public void construirCenas(List<Funcionalidade> listaFuncionalidades, List<String> listaWidgets, List<String> itensPainelPermanente) {
/* 371 */     Iterator<Tela> it = this.telas.values().iterator();
/* 372 */     while (it.hasNext()) {
/* 373 */       Tela tela = it.next();
/* 374 */       tela.construir(listaFuncionalidades, listaWidgets, itensPainelPermanente, this);
/* 375 */       tela.aplicarCss();
/*     */     } 
/*     */ 
/*     */     
/* 379 */     Iterator<Funcionalidade> iteratorListaFuncionalidade = listaFuncionalidades.iterator();
/* 380 */     while (iteratorListaFuncionalidade.hasNext()) {
/* 381 */       Funcionalidade funcionalidade = iteratorListaFuncionalidade.next();
/* 382 */       addItemPesquisa((ItemPesquisa)new ItemPesquisaCena(funcionalidade.getNome(), funcionalidade.getCaminhoProjeto()));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void exibirIHM() {
/* 388 */     Platform.runLater(() -> getTelaPrincipal().getStage().show());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ocultarIHM() {
/* 395 */     desligar();
/*     */   }
/*     */ 
/*     */   
/*     */   public void configurar() {
/* 400 */     configurarObjetos();
/* 401 */     configurarEstadoPadrao();
/* 402 */     configurarComportamentoJanela();
/*     */   }
/*     */ 
/*     */   
/*     */   public void notificarCarregamento(int passoAtual, String descricaoPasso) {
/* 407 */     if (passoAtual == 0) {
/* 408 */       this.passoAtual++;
/* 409 */       Log.gravarLogInstrucao(this.passoAtual + ". " + descricaoPasso, this);
/* 410 */       if (this.ihmCarregamento != null) {
/* 411 */         this.ihmCarregamento.notificarCarregamento(this.passoAtual, descricaoPasso);
/*     */       }
/* 413 */     } else if (passoAtual == -1) {
/* 414 */       if (!descricaoPasso.isEmpty()) {
/* 415 */         Log.gravarLogInterface("Exibindo aviso de splash secundário [" + descricaoPasso + "]", this);
/*     */       }
/* 417 */       if (this.ihmCarregamento != null) {
/* 418 */         this.ihmCarregamento.notificarCarregamento(-1, descricaoPasso);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCssPadrao() {
/* 429 */     return this.cssPadrao;
/*     */   }
/*     */   
/*     */   public void setModoDiurno() {
/* 433 */     this.modoOperacao = ModoOperacao.DIURNO;
/* 434 */     this.cssPadrao = "ipqm/gsd/hidra/ihm/css/modoDiurno.css";
/* 435 */     aplicarCss();
/*     */   }
/*     */   
/*     */   public void setModoNoturno() {
/* 439 */     this.modoOperacao = ModoOperacao.NOTURNO;
/* 440 */     this.cssPadrao = "ipqm/gsd/hidra/ihm/css/modoNoturno.css";
/* 441 */     aplicarCss();
/*     */   }
/*     */   
/*     */   public ModoOperacao getModoOperacao() {
/* 445 */     return this.modoOperacao;
/*     */   }
/*     */   
/*     */   public void aplicarCss() {
/* 449 */     Iterator<Tela> it = this.telas.values().iterator();
/* 450 */     while (it.hasNext()) {
/* 451 */       Tela tela = it.next();
/* 452 */       tela.aplicarCss();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void notificaEstadoSincronismo(boolean estadoSincronismo) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTitulo(String nome) {
/* 467 */     Iterator<Tela> it = this.telas.values().iterator();
/* 468 */     while (it.hasNext()) {
/* 469 */       Tela tela = it.next();
/* 470 */       tela.setTitulo(nome);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void addCena(Funcionalidade funcionalidade) {
/* 475 */     Iterator<Tela> it = this.telas.values().iterator();
/* 476 */     while (it.hasNext()) {
/* 477 */       Tela tela = it.next();
/* 478 */       tela.fabricarCena(funcionalidade, this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<TopicoAjuda> getTopicosDeAjuda() {
/* 488 */     return this.topicosDeAjuda;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTopicoAjuda(TopicoAjuda topicoAjuda) {
/* 497 */     if (!this.topicosDeAjuda.contains(topicoAjuda)) {
/* 498 */       this.topicosDeAjuda.add(topicoAjuda);
/*     */     }
/*     */   }
/*     */   
/*     */   public List<ItemPesquisa> getItensPesquisa() {
/* 503 */     return this.itensPesquisa;
/*     */   }
/*     */   
/*     */   public void addItemPesquisa(ItemPesquisa itemPesquisa) {
/* 507 */     if (!this.itensPesquisa.contains(itemPesquisa)) {
/* 508 */       this.itensPesquisa.add(itemPesquisa);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void modoAvaliacao() {
/* 513 */     if (!Mediador.getInstancia().getGerenciadorLicenca().isRegistroValido()) {
/* 514 */       Log.gravarLogInstrucao("Modo de avaliação", this);
/* 515 */       ThreadPool.agendarExecucaoPeriodica(new Avaliacao(), 1L, TimeUnit.SECONDS, "Verificador do modo de avaliação");
/*     */     } 
/*     */   }
/*     */   
/*     */   private class Avaliacao
/*     */     implements Runnable {
/* 521 */     private int minutosRestantes = 10;
/*     */     
/*     */     private int contSeg;
/*     */     
/*     */     private boolean avisou;
/*     */     private Notificacao notificacao;
/*     */     
/*     */     public void run() {
/* 529 */       if (!this.avisou)
/* 530 */         if (this.minutosRestantes == 0 && this.contSeg == 0) {
/* 531 */           this.avisou = true;
/*     */           
/* 533 */           new DialogoAlerta("Versão de Avaliação - Request Key: " + License.getInstance().getRequestKey(), "A versão de avaliação é limitado a 10 minutos por execução do programa.\nPara saber como adquirir entre em contato com o\nGSD (Grupo de Sistemas Digitais) no IPqM (Instituto de Pesquisas da Marinha)", Aplicacao.this.getCenaAtual().getControlador())
/*     */             {
/*     */               public void acao(AcaoDialogo acao)
/*     */               {
/* 537 */                 Platform.runLater(() -> Aplicacao.this.fechaTudo());
/*     */ 
/*     */                 
/* 540 */                 System.exit(0);
/*     */               }
/*     */             };
/*     */         } else {
/* 544 */           String tempo = String.format("%02d:%02d", new Object[] { Integer.valueOf(this.minutosRestantes), Integer.valueOf(this.contSeg) });
/*     */           
/* 546 */           if (this.notificacao == null) {
/*     */             
/* 548 */             this.notificacao = new Notificacao("Versão de avaliação (" + License.getInstance().getRequestKey() + ")", Notificacao.Tipo.ALERTA);
/* 549 */             this.notificacao.setPermanente(true);
/* 550 */             this.notificacao.setExibirBotaoFechar(false);
/* 551 */             Notificador.getInstancia().addNotificacao(this.notificacao);
/*     */           } 
/*     */           
/* 554 */           this.notificacao.setDescricao("Tempo restante de utilização: " + tempo);
/*     */           
/* 556 */           this.contSeg--;
/* 557 */           if (this.contSeg < 0) {
/* 558 */             this.minutosRestantes--;
/* 559 */             this.contSeg = 59;
/*     */           } 
/*     */         }  
/*     */     }
/*     */     
/*     */     private Avaliacao() {}
/*     */   }
/*     */   
/*     */   private class Encerrar
/*     */     implements Runnable {
/*     */     private final ComandosSO.Comando comando;
/*     */     private final String parametros;
/*     */     
/*     */     public Encerrar(ComandosSO.Comando comando, String parametros) {
/* 573 */       this.comando = comando;
/* 574 */       if (parametros == null) {
/* 575 */         this.parametros = "";
/*     */       } else {
/* 577 */         this.parametros = parametros;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void run() {
/* 584 */       IHM IHMnotifica = Aplicacao.this.getCenaAtual().getControlador();
/*     */       
/* 586 */       if (Mediador.isSistemaIniciado()) {
/* 587 */         IHMnotifica.notificarCarregamento(0, "Encerrando Mediador");
/* 588 */         Mediador.getInstancia().encerrar();
/*     */       } 
/*     */       
/* 591 */       IHMnotifica.notificarCarregamento(0, "Encerrando gestor de comando remoto");
/* 592 */       GestorComandoRemoto.getInstancia().encerrar();
/*     */       
/* 594 */       IHMnotifica.notificarCarregamento(0, "Encerrando serviço de log");
/* 595 */       Log.getInstancia().encerrar();
/*     */       
/* 597 */       Platform.runLater(() -> Aplicacao.this.fechaTudo());
/*     */ 
/*     */ 
/*     */       
/* 601 */       ComandosSO.enviarComando(this.comando, this.parametros);
/*     */       
/*     */       try {
/* 604 */         Thread.sleep(500L);
/* 605 */       } catch (InterruptedException ex) {
/* 606 */         Log.gravarLogExcecao("Thread interrompida, ocorreu algum erro no sleep", this, ex);
/*     */       } 
/*     */       
/* 609 */       System.exit(0);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void inserirResolucao(int resolucao) {
/* 620 */     this.requisicao = new Requisicao();
/* 621 */     this.requisicao.setResolucao(resolucao);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void easterEgg() {
/* 627 */     WidgetEasterEgg widgetEasterEgg = (WidgetEasterEgg)getTelaPrincipal().getWidget(Widget.Tipo.EASTER_EGG);
/* 628 */     if (widgetEasterEgg != null && Debug.isDebug()) {
/* 629 */       Platform.runLater(() -> widgetEasterEgg.exibir(getTelaPrincipal().getCenaAtual()));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void alerta(String titulo, String descricao) {
/* 637 */     new DialogoAlerta(titulo, descricao, getTelaPrincipal().getCenaAtual().getControlador())
/*     */       {
/*     */         public void acao(AcaoDialogo acao) {}
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void notificarInformacao(String titulo, String descricao) {
/* 648 */     Notificador.informacao(titulo, descricao);
/*     */   }
/*     */ 
/*     */   
/*     */   public void instanciaEnvio(String ipEnvio) {
/* 653 */     if (this.vrCaptura == null) {
/* 654 */       this.vrCaptura = new VisualizacaoRemota(this, ipEnvio);
/*     */     } else {
/* 656 */       this.vrCaptura.voltaEnviar();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void interrompeEnvio() {
/* 663 */     this.vrCaptura.paraEnvio();
/* 664 */     this.vrCaptura = null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void telaSelecionada(int telaSelecionada) {
/* 669 */     if (this.vrCaptura != null) {
/* 670 */       this.vrCaptura.setTelaSelecionada(telaSelecionada);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void requisicaoResolucao(int resolucao) {
/* 676 */     inserirResolucao(resolucao);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void bloquearTela() {
/* 682 */     bloquearTela("SISTEMA INOPERANTE!");
/*     */   }
/*     */ 
/*     */   
/*     */   public void bloquearTela(String texto) {
/* 687 */     Iterator<Tela> it = this.telas.values().iterator();
/* 688 */     while (it.hasNext()) {
/* 689 */       Tela tela = it.next();
/* 690 */       WidgetBloqueioTela widget = (WidgetBloqueioTela)tela.getWidget(Widget.Tipo.BLOQUEIO_TELA);
/* 691 */       if (widget != null) {
/* 692 */         widget.setTexto(texto);
/* 693 */         widget.setAtivo(true);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void desbloquearTela() {
/* 700 */     Iterator<Tela> it = this.telas.values().iterator();
/* 701 */     while (it.hasNext()) {
/* 702 */       Tela tela = it.next();
/* 703 */       WidgetBloqueioTela widget = (WidgetBloqueioTela)tela.getWidget(Widget.Tipo.BLOQUEIO_TELA);
/* 704 */       if (widget != null) {
/* 705 */         widget.setAtivo(false);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static Stage getStage() {
/* 711 */     return stage;
/*     */   }
/*     */   
/*     */   public abstract boolean login(UsuarioMaquinaCondicao paramUsuarioMaquinaCondicao, IHM paramIHM, boolean paramBoolean);
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/controle/Aplicacao.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */