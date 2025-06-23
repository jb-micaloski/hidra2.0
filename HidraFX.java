/*     */ package ipqm.gsd.hidra.ihm;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.banco_dados.DAO;
/*     */ import ipqm.gsd.componentes.dominio.banco_dados.DAOUsuarioFuncionalidade;
/*     */ import ipqm.gsd.componentes.dominio.banco_dados.DAOUsuarioMaquina;
/*     */ import ipqm.gsd.componentes.dominio.contexto.ContextoTatico;
/*     */ import ipqm.gsd.componentes.dominio.teatro_operacao.CondicaoInicial;
/*     */ import ipqm.gsd.componentes.dominio.teatro_operacao.GrupoMaquinas;
/*     */ import ipqm.gsd.componentes.dominio.teatro_operacao.UsuarioMaquinaCondicao;
/*     */ import ipqm.gsd.componentes.nucleo.Ambiente;
/*     */ import ipqm.gsd.componentes.nucleo.IHM;
/*     */ import ipqm.gsd.componentes.nucleo.Maquina;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.NucleoAmbiente;
/*     */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*     */ import ipqm.gsd.componentes.nucleo.ThreadPool;
/*     */ import ipqm.gsd.componentes.nucleo.comando_remoto.AcaoComandoRemoto;
/*     */ import ipqm.gsd.componentes.nucleo.comando_remoto.GestorComandoRemoto;
/*     */ import ipqm.gsd.componentes.nucleo.configuracao.Diretorios;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.logon.Funcionalidade;
/*     */ import ipqm.gsd.componentes.nucleo.logon.Usuario;
/*     */ import ipqm.gsd.componentes.nucleo.logon.perfis.ConfiguracaoPerfilUsuario;
/*     */ import ipqm.gsd.componentes.nucleo.logon.perfis.PerfilUsuario;
/*     */ import ipqm.gsd.componentes.nucleo.notificador.Notificacao;
/*     */ import ipqm.gsd.componentes.nucleo.notificador.Notificador;
/*     */ import ipqm.gsd.componentes.nucleo.util.depuracao.Debug;
/*     */ import ipqm.gsd.componentes.nucleo.web.ServidorHttp;
/*     */ import ipqm.gsd.hidra.ihm.configuracao.Configurador;
/*     */ import ipqm.gsd.hidra.ihm.controle.Aplicacao;
/*     */ import ipqm.gsd.hidra.ihm.controle.Carregador;
/*     */ import ipqm.gsd.hidra.ihm.controle.Fonia;
/*     */ import ipqm.gsd.hidra.ihm.controle.Tela;
/*     */ import ipqm.gsd.hidra.ihm.projetos.geral.cenas.controladores.ControladorLogin;
/*     */ import ipqm.gsd.hidra.ihm.projetos.geral.cenas.controladores.ControladorSplash;
/*     */ import ipqm.gsd.hidra.ihm.visao.Cena;
/*     */ import ipqm.gsd.hidra.ihm.visualizacao_remota.IVisualizacaoRemotaInicia;
/*     */ import ipqm.gsd.hidra.ihm.visualizacao_remota.VisualizacaoRemota;
/*     */ import ipqm.gsd.hidra.ihm.widgets.Widget;
/*     */ import ipqm.gsd.hidra.ihm.widgets.pesquisa.itens.ItemPesquisaCena;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.sql.SQLException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.jar.JarFile;
/*     */ import java.util.zip.ZipEntry;
/*     */ import javafx.application.Platform;
/*     */ import javafx.embed.swing.SwingFXUtils;
/*     */ import javafx.scene.image.Image;
/*     */ import javafx.scene.image.WritableImage;
/*     */ import javafx.stage.Screen;
/*     */ import javafx.stage.Stage;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HidraFX
/*     */   extends Aplicacao
/*     */ {
/*     */   public static boolean aplicacaoDesatualizada;
/*     */   private UsuarioMaquinaCondicao.TipoLogin tipoLogin;
/*     */   private static boolean loginEfetuado;
/*     */   
/*     */   public static void iniciar(String[] args) {
/*  74 */     if (Screen.getScreens() != null) {
/*  75 */       NucleoAmbiente.QUANTIDADE_TELAS.getValoresPermitidos().clear();
/*  76 */       NucleoAmbiente.TELA_PRINCIPAL.getValoresPermitidos().clear();
/*  77 */       for (int i = 1; i <= Screen.getScreens().size(); i++) {
/*  78 */         NucleoAmbiente.QUANTIDADE_TELAS.getValoresPermitidos().add(String.valueOf(i));
/*  79 */         NucleoAmbiente.TELA_PRINCIPAL.getValoresPermitidos().add(String.valueOf(i));
/*     */       } 
/*     */     } 
/*     */     
/*  83 */     if (Hidra.configurar) {
/*  84 */       Configurador configurador = new Configurador();
/*  85 */       Platform.runLater(() -> configurador.start(new Stage()));
/*     */     }
/*     */     else {
/*     */       
/*  89 */       launch(args);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void configurarObjetos() {
/*  96 */     Maquina.getMaquinaLocal().obtemValoresBD();
/*     */ 
/*     */     
/*  99 */     List<String> widgetsDefaults = new ArrayList<>();
/*     */     
/* 101 */     widgetsDefaults.add("system_tray.WidgetSystemTray");
/* 102 */     widgetsDefaults.add("menu_desligar.WidgetMenuDesligar");
/* 103 */     widgetsDefaults.add("ajuda_contexto.WidgetAjuda");
/* 104 */     widgetsDefaults.add("notificador.WidgetNotificador");
/* 105 */     widgetsDefaults.add("fundo_preto.WidgetFundoPreto");
/* 106 */     widgetsDefaults.add("configuracao.WidgetConfiguracao");
/*     */     
/* 108 */     if (Fonia.isFoniaHabilitada()) {
/* 109 */       widgetsDefaults.add(Fonia.getWidgetFonia());
/*     */     }
/*     */     
/* 112 */     if (Debug.isDebug()) {
/* 113 */       widgetsDefaults.add("debug.WidgetDebug");
/* 114 */       widgetsDefaults.add("easter_egg.WidgetEasterEgg");
/*     */     } 
/*     */     
/* 117 */     Iterator<Tela> it = this.telas.values().iterator();
/* 118 */     while (it.hasNext()) {
/* 119 */       Tela tela = it.next();
/* 120 */       tela.construirWidgets(widgetsDefaults);
/* 121 */       tela.iniciaAnimacao(1.0D);
/*     */     } 
/*     */     
/* 124 */     addCena(new Funcionalidade("Login", "projetos/geral/cenas/Login.fxml"));
/* 125 */     addCena(new Funcionalidade("Splash", "projetos/geral/cenas/Splash.fxml"));
/* 126 */     addCena(new Funcionalidade("Vazio", "projetos/geral/cenas/Vazio.fxml"));
/* 127 */     addCena(new Funcionalidade("Desligando", "projetos/geral/cenas/Desligando.fxml"));
/*     */     
/* 129 */     setTitulo("Carregando");
/* 130 */     aplicarCss();
/*     */ 
/*     */     
/* 133 */     trocarTudo("projetos/geral/cenas/Vazio.fxml");
/*     */ 
/*     */     
/* 136 */     GestorComandoRemoto.getInstancia().setAcaoComandoRemoto((AcaoComandoRemoto)this);
/*     */ 
/*     */     
/* 139 */     if (getVrRequisicao() == null) {
/* 140 */       setVrRequisicao(new VisualizacaoRemota((IVisualizacaoRemotaInicia)this));
/*     */     }
/*     */ 
/*     */     
/* 144 */     if (Hidra.DATA_COMPILACAO != -1L) {
/* 145 */       ThreadPool.agendarExecucaoPeriodica(new VerificadorAtualizacoes(), 60L, TimeUnit.SECONDS, "Verificador de atualizações");
/*     */     }
/*     */ 
/*     */     
/* 149 */     if (Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.EXIBIR_NOTIFICACAO_QUEDA_SERVIDOR_LOCAL).equals("SIM")) {
/* 150 */       ThreadPool.agendarExecucaoPeriodica(new NotificadorQuedaDoServidorLocal(), 20L, 5L, TimeUnit.SECONDS, "Notificador de queda do servidor local");
/*     */     }
/*     */     
/* 153 */     if (Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.SERVIDOR_HTTP_DEBUG).equals("SIM")) {
/* 154 */       ServidorHttp servidorHttp = new ServidorHttp("Hidra", "HIDRA é um produto do Grupo de Sistemas Digitais (GSD) e do Instituto de Pesquisas da Marinha (IPqM). \nMarinha do Brasil©" + ((Hidra.DATA_COMPILACAO == -1L) ? "" : (" " + (new SimpleDateFormat("yyyy")).format(Long.valueOf(Hidra.DATA_COMPILACAO)))) + " - Todos os direitos reservados. Versão: " + "qualidade_v1.1" + ((Hidra.DATA_COMPILACAO == -1L) ? "" : (" - Compilado em " + (new SimpleDateFormat("dd.MM.yy HH:mm")).format(Long.valueOf(Hidra.DATA_COMPILACAO)))) + ".");
/* 155 */       servidorHttp.iniciar();
/*     */       try {
/* 157 */         servidorHttp.setMaquinasExistentes((new Maquina()).listarTodos());
/* 158 */       } catch (Exception ex) {
/* 159 */         Log.gravarLogExcecao("Erro ao listar maquinas para o servidor http", this, ex);
/*     */       } 
/* 161 */       servidorHttp.addPagina("Objetos", "ipqm.gsd.componentes.dominio.web.PaginaObjetos");
/* 162 */       servidorHttp.addPagina("Máquinas", "ipqm.gsd.componentes.dominio.web.PaginaGrupoMaquinas");
/* 163 */       servidorHttp.addPagina("IHM", "ipqm.gsd.hidra.ihm.web.PaginaIHM");
/* 164 */       servidorHttp.addPagina("Notificações", "ipqm.gsd.hidra.ihm.web.PaginaNotificacoes");
/* 165 */       servidorHttp.addPagina("Controle", "ipqm.gsd.hidra.ihm.web.PaginaControle");
/* 166 */       servidorHttp.addPagina("Teclado", "ipqm.gsd.hidra.ihm.web.PaginaTeclado");
/* 167 */       servidorHttp.addPagina("Sensores", "ipqm.gsd.componentes.dominio.web.PaginaSensores");
/* 168 */       servidorHttp.addPagina("Resumo", "ipqm.gsd.hidra.ihm.web.PaginaResumoHidra");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void configurarEstadoPadrao() {
/* 175 */     Hidra.carregamento.ocultarIHM();
/* 176 */     trocarTudo("projetos/geral/cenas/Vazio.fxml");
/* 177 */     getItensPesquisa().add(new ItemPesquisaCena("Tela Principal", "projetos/geral/cenas/TelaPrincipal.fxml"));
/* 178 */     getItensPesquisa().add(new ItemPesquisaCena("Ajuda", "projetos/geral/cenas/Ajuda.fxml"));
/*     */   }
/*     */   
/*     */   public void configurarComportamentoJanela() {
/*     */     UsuarioMaquinaCondicao mas;
/*     */     boolean t;
/* 184 */     DAOUsuarioMaquina daoum = new DAOUsuarioMaquina();
/* 185 */     Usuario usuarioAutomatico = daoum.getLoginAutomatico();
/*     */     
/* 187 */     if (Hidra.forcaLogin || usuarioAutomatico == null) {
/* 188 */       this.tipoLogin = UsuarioMaquinaCondicao.TipoLogin.MANUAL;
/*     */     } else {
/* 190 */       this.tipoLogin = UsuarioMaquinaCondicao.TipoLogin.AUTOMATICO;
/*     */     } 
/*     */     
/* 193 */     switch (this.tipoLogin) {
/*     */       case AUTOMATICO:
/* 195 */         Log.gravarLogInstrucao("Login automático para o usuário " + usuarioAutomatico.getNome(), this);
/* 196 */         mas = new UsuarioMaquinaCondicao(usuarioAutomatico);
/* 197 */         mas.setTipoLogin(this.tipoLogin);
/* 198 */         t = login(mas, (IHM)this, !Debug.isDebug());
/* 199 */         if (!t) {
/* 200 */           trocar("projetos/geral/cenas/Login.fxml", 0);
/* 201 */           exibirIHM();
/*     */         } 
/*     */         break;
/*     */       case MANUAL:
/* 205 */         Log.gravarLogInstrucao("Login manual, aguardando usuário e senha", this);
/* 206 */         setTitulo("Hidra");
/* 207 */         trocar("projetos/geral/cenas/Login.fxml", 0);
/* 208 */         exibirIHM();
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean login(UsuarioMaquinaCondicao usuarioMaquinaCondicao, IHM ihmCarregamento, boolean abrirFuncionalidadeDestaque) {
/* 215 */     boolean status = false;
/* 216 */     if (!loginEfetuado) {
/* 217 */       this.ihmCarregamento = ihmCarregamento;
/*     */       
/* 219 */       DAOUsuarioMaquina daoum = new DAOUsuarioMaquina();
/*     */       
/* 221 */       Usuario userValidado = daoum.verificaUsuario(usuarioMaquinaCondicao.getUsuario());
/* 222 */       if (userValidado != null) {
/* 223 */         usuarioMaquinaCondicao.setUsuario(userValidado);
/*     */         
/* 225 */         if (daoum.verificaUsuarioMaquina(usuarioMaquinaCondicao)) {
/* 226 */           if (usuarioMaquinaCondicao.getUsuario() != null) {
/* 227 */             Log.gravarLogInstrucao("Login efetuado com sucesso, usuário: " + usuarioMaquinaCondicao.getUsuario().getNome(), this);
/*     */             try {
/* 229 */               status = iniciaCarregamentoSistema(usuarioMaquinaCondicao, abrirFuncionalidadeDestaque);
/* 230 */             } catch (SQLException ex) {
/* 231 */               Log.gravarLogExcecao("Erro ao executar o projeto", HidraFX.class, ex);
/* 232 */               status = false;
/*     */             } 
/*     */           } else {
/* 235 */             status = false;
/*     */           } 
/*     */         } else {
/* 238 */           Notificador.erro("Acesso não autorizado", "Este usuário não possui permissão para logar nesta maquina");
/* 239 */           Log.gravarLogInstrucao("Erro de login: Este usuário (" + usuarioMaquinaCondicao.getUsuario().getNome() + ") não possui permissão para logar nesta maquina", this);
/* 240 */           status = false;
/*     */         } 
/*     */       } else {
/* 243 */         Notificador.erro("Acesso não autorizado", "Usuário ou senha inválidos");
/* 244 */         Log.gravarLogInstrucao("Erro de login: Usuário (" + usuarioMaquinaCondicao.getUsuario().getNome() + ") ou senha inválidos", this);
/* 245 */         status = false;
/*     */       } 
/*     */     } 
/* 248 */     loginEfetuado = status;
/* 249 */     return status;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean iniciaCarregamentoSistema(UsuarioMaquinaCondicao maquinaAssociadaCondicao, boolean abrirFuncionalidadeDestaque) throws SQLException {
/*     */     boolean status;
/*     */     ConfiguracaoPerfilUsuario configuracaoPerfilUsuario;
/* 262 */     setTitulo("Carregando");
/*     */ 
/*     */     
/*     */     try {
/* 266 */       DAO<ConfiguracaoPerfilUsuario> daopbd = new DAO(ConfiguracaoPerfilUsuario.class);
/* 267 */       configuracaoPerfilUsuario = (ConfiguracaoPerfilUsuario)daopbd.listarPorID(Integer.valueOf(maquinaAssociadaCondicao.getUsuario().getIdPerfil()));
/* 268 */       Log.gravarLogInstrucao("Carregando projeto " + configuracaoPerfilUsuario.getNome(), this);
/* 269 */     } catch (Exception ex) {
/* 270 */       Log.gravarLogExcecao("Erro ao obter o perfil " + maquinaAssociadaCondicao.getUsuario().getIdPerfil(), this, ex);
/* 271 */       return false;
/*     */     } 
/*     */     
/*     */     try {
/* 275 */       Class<?> c = Class.forName("ipqm.gsd.componentes.nucleo.logon.perfis." + configuracaoPerfilUsuario.getClasse());
/* 276 */       Constructor<?> cons = c.getConstructor(new Class[] { ConfiguracaoPerfilUsuario.class });
/* 277 */       PerfilUsuario perfil = (PerfilUsuario)cons.newInstance(new Object[] { configuracaoPerfilUsuario });
/* 278 */       perfil.setUsuario(maquinaAssociadaCondicao.getUsuario());
/*     */       
/* 280 */       CondicaoInicial cI = new CondicaoInicial();
/* 281 */       List<CondicaoInicial> condicoesIniciais = cI.obtemCondicaoPorUsuarioMaquina(maquinaAssociadaCondicao.getUsuario(), maquinaAssociadaCondicao.getMaquina());
/*     */ 
/*     */       
/* 284 */       if (maquinaAssociadaCondicao.getCondicaoInicial() == null && perfil.necessitaVeiculoAssociado()) {
/* 285 */         if (condicoesIniciais.isEmpty()) {
/* 286 */           Notificador.erro("Não é possível realizar login", "Este usuário deve estar associado a um veículo referencial");
/* 287 */           Log.gravarLogInstrucao("Este usuário (" + maquinaAssociadaCondicao.getUsuario().getNome() + ") deve estar associado a um veículo referencial", this);
/* 288 */           return false;
/* 289 */         }  if (condicoesIniciais.size() > 1) {
/* 290 */           Notificador.erro("Não é possível realizar login", "Este usuário está associado a mais de um veículo referencial");
/* 291 */           Log.gravarLogInstrucao("Este usuário (" + maquinaAssociadaCondicao.getUsuario().getNome() + ") está associado a mais de um veículo referencial", this);
/* 292 */           return false;
/*     */         } 
/* 294 */         maquinaAssociadaCondicao.setCondicaoInicial(condicoesIniciais.get(0));
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 299 */       if (perfil.isUsuarioInstrutor() && condicoesIniciais.isEmpty()) {
/* 300 */         Notificador.erro("Não é possível realizar login", "Este usuário deve possuir uma associação com algum teatro de operação");
/* 301 */         Log.gravarLogInstrucao("Este usuário (" + maquinaAssociadaCondicao.getUsuario().getNome() + ") deve possuir pelo menos uma associação com algum teatro de operação", this);
/* 302 */         return false;
/*     */       } 
/*     */ 
/*     */       
/* 306 */       if (!Debug.isDebug() && 
/* 307 */         perfil.isDesabilitarMenuDesligar()) {
/* 308 */         Iterator<Tela> iterator = Aplicacao.getInstancia().getTelas().values().iterator();
/* 309 */         while (iterator.hasNext()) {
/* 310 */           Tela tela = iterator.next();
/* 311 */           tela.removeWidget(Widget.Tipo.MENU_DESLIGAR);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 316 */       DAOUsuarioFuncionalidade dAOUsuarioFuncionalidade = new DAOUsuarioFuncionalidade();
/* 317 */       List<Funcionalidade> listaFuncionalidades = dAOUsuarioFuncionalidade.obtemFuncionalidades(maquinaAssociadaCondicao.getUsuario().getIdUsuario());
/*     */       
/* 319 */       listaFuncionalidades.add(0, new Funcionalidade("Tela Principal", "projetos/geral/cenas/TelaPrincipal.fxml"));
/* 320 */       listaFuncionalidades.add(1, new Funcionalidade("Ajuda", "projetos/geral/cenas/Ajuda.fxml"));
/*     */       
/* 322 */       ControladorSplash splash = (ControladorSplash)getTelaPrincipal().getCena("projetos/geral/cenas/Splash.fxml").getControlador();
/* 323 */       splash.setTextoBoasVindas(perfil.getMensagemBoasVindas());
/* 324 */       splash.setImagem(perfil.getImagem());
/*     */       
/* 326 */       int passosExtras = listaFuncionalidades.size() * this.telas.size();
/*     */       
/* 328 */       if (perfil.necessitaVeiculoAssociado()) {
/* 329 */         passosExtras++;
/*     */       }
/* 331 */       if (perfil.getServidorCartas() != null) {
/* 332 */         passosExtras++;
/*     */       }
/* 334 */       if (perfil.isIniciarGestores()) {
/* 335 */         passosExtras += 4;
/*     */       }
/*     */       
/* 338 */       splash.setTotalPassos(passosExtras);
/*     */       
/* 340 */       trocar("projetos/geral/cenas/Splash.fxml", 0);
/*     */       
/* 342 */       this.ihmCarregamento = (IHM)getTelaPrincipal().getCena("projetos/geral/cenas/Splash.fxml").getControlador();
/*     */       
/* 344 */       Carregador carregador = new Carregador(perfil, maquinaAssociadaCondicao, listaFuncionalidades, this, Hidra.atualizarCartas);
/* 345 */       carregador.setAbrirFuncionalidadeDestaque(abrirFuncionalidadeDestaque);
/* 346 */       ThreadPool.executar((Runnable)carregador, "Carregador do hidra");
/*     */       
/* 348 */       status = true;
/*     */     }
/* 350 */     catch (ClassNotFoundException|InstantiationException|IllegalAccessException|NoSuchMethodException|SecurityException|IllegalArgumentException|java.lang.reflect.InvocationTargetException ex) {
/* 351 */       Log.gravarLogExcecao("Erro ao executar projeto", this, ex);
/* 352 */       status = false;
/*     */     } 
/* 354 */     return status;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void desenharObjetos(List<ObjetoDOMINIO> listaObjetos) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WritableImage obterFrame() {
/* 369 */     return getTelaPrincipal().getStage().getScene().snapshot(new WritableImage(1920, 1080));
/*     */   }
/*     */ 
/*     */   
/*     */   public BufferedImage converterFrame(WritableImage imagem) {
/* 374 */     return SwingFXUtils.fromFXImage((Image)imagem, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public int obterResolucao() {
/* 379 */     return this.requisicao.getResolucao();
/*     */   }
/*     */ 
/*     */   
/*     */   public int obterNumeroTelas() {
/* 384 */     return Aplicacao.getInstancia().getTelas().size();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean loginRemoto(int idCondicaoInicial, boolean abrirFuncionalidadeDestaque) {
/* 389 */     Ambiente.getInstance().verificarArquivosRecuperacao();
/*     */     
/* 391 */     ((ControladorLogin)((Cena)getTelaPrincipal().getCenas().get("projetos/geral/cenas/Login.fxml")).getControlador()).desabilitaCampos();
/*     */     
/* 393 */     CondicaoInicial condicaoInicial = new CondicaoInicial();
/* 394 */     condicaoInicial.setIdCondicaoInicial(idCondicaoInicial);
/*     */     
/* 396 */     Usuario usuario = condicaoInicial.obtemUsuarioPorCondicaoMaquina(Maquina.getMaquinaLocal());
/*     */     
/* 398 */     if (usuario == null) {
/* 399 */       Notificador.erro("Não é possível realizar login remoto", "Não foi possível obter o usuário pela condição inicial");
/* 400 */       ((ControladorLogin)((Cena)getTelaPrincipal().getCenas().get("projetos/geral/cenas/Login.fxml")).getControlador()).habilitaCampos();
/* 401 */       return false;
/*     */     } 
/*     */     
/* 404 */     UsuarioMaquinaCondicao mas = new UsuarioMaquinaCondicao(condicaoInicial, usuario);
/*     */     
/* 406 */     if (!login(mas, (IHM)this, abrirFuncionalidadeDestaque)) {
/* 407 */       ((ControladorLogin)((Cena)getTelaPrincipal().getCenas().get("projetos/geral/cenas/Login.fxml")).getControlador()).habilitaCampos();
/* 408 */       return false;
/*     */     } 
/*     */     
/* 411 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private class NotificadorQuedaDoServidorLocal
/*     */     implements Runnable
/*     */   {
/*     */     private Notificacao notificacao;
/*     */ 
/*     */     
/* 422 */     private int ciclosDesconectados = 0;
/*     */ 
/*     */     
/*     */     public void run() {
/* 426 */       if (Mediador.isSistemaIniciado() && (
/* 427 */         (ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getGrupoMaquinas() != null) {
/* 428 */         GrupoMaquinas gm = ((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getGrupoMaquinas();
/* 429 */         if (gm.getMaquinaServidoraLocal() != null && gm.getMaquinaServidoraLocal().getIdMaquina() != Maquina.getMaquinaLocal().getIdMaquina()) {
/* 430 */           Maquina maq = gm.getMaquinaServidoraLocal();
/* 431 */           if (maq.getEstadoConexao() != Maquina.EnumEstadosConexao.EXECUTANDO) {
/* 432 */             this.ciclosDesconectados++;
/* 433 */             if (this.notificacao == null && this.ciclosDesconectados >= 2) {
/* 434 */               this.notificacao = Notificador.excecaoPermanenteSemFechar("Não foi possível se conectar com o servidor", "Verifique a sua conexão com a rede");
/*     */             }
/* 436 */             if (this.notificacao != null) {
/* 437 */               if (this.ciclosDesconectados < 100) {
/* 438 */                 this.notificacao.setDescricao("Tentativa " + (this.ciclosDesconectados - 1) + " de reconexão. Verifique a sua conexão com a rede.");
/*     */               } else {
/* 440 */                 this.notificacao.setDescricao("Verifique a sua conexão com a rede ou entre em contato com o suporte técnico.");
/*     */               } 
/*     */             }
/*     */           } else {
/* 444 */             this.ciclosDesconectados = 0;
/* 445 */             if (this.notificacao != null) {
/* 446 */               Notificador.getInstancia().removeNotificacao(this.notificacao);
/* 447 */               this.notificacao = null;
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     private NotificadorQuedaDoServidorLocal() {}
/*     */   }
/*     */   
/*     */   private class VerificadorAtualizacoes implements Runnable {
/*     */     private boolean exibiuNotificacao;
/*     */     
/*     */     private VerificadorAtualizacoes() {}
/*     */     
/*     */     public void run() {
/* 463 */       File file = new File(Diretorios.getDiretorioPadrao(Diretorios.Diretorio.ATUALIZACAO));
/*     */       
/* 465 */       if (!this.exibiuNotificacao && file.exists()) {
/*     */         long dataNovo;
/*     */ 
/*     */         
/*     */         try {
/* 470 */           JarFile jf = new JarFile(Diretorios.getDiretorioPadrao(Diretorios.Diretorio.ATUALIZACAO) + "hidra.jar");
/* 471 */           ZipEntry manifest = jf.getEntry("META-INF/MANIFEST.MF");
/* 472 */           dataNovo = manifest.getTime();
/* 473 */         } catch (IOException ex) {
/* 474 */           dataNovo = -1L;
/*     */         } 
/*     */         
/* 477 */         if (dataNovo != -1L && 
/* 478 */           Hidra.DATA_COMPILACAO != dataNovo) {
/* 479 */           Notificacao notificacao = new Notificacao("Atualização disponível", Notificacao.Tipo.INFORMACAO);
/* 480 */           notificacao.setDescricao("Reinicie a aplicação (CTRL+SHIFT+L) para aplicar");
/* 481 */           notificacao.setPermanente(true);
/* 482 */           Notificador.getInstancia().addNotificacao(notificacao);
/* 483 */           this.exibiuNotificacao = true;
/* 484 */           HidraFX.aplicacaoDesatualizada = true;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void configurarTopicoAjuda() {
/* 496 */     throw new UnsupportedOperationException("Not supported yet.");
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/HidraFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */