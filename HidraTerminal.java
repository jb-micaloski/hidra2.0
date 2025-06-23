/*     */ package ipqm.gsd.hidra.ihm;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.banco_dados.DAO;
/*     */ import ipqm.gsd.componentes.dominio.banco_dados.DAOCondicaoInicial;
/*     */ import ipqm.gsd.componentes.dominio.banco_dados.DAOUsuarioMaquina;
/*     */ import ipqm.gsd.componentes.dominio.contexto.ContextualizadorTatico;
/*     */ import ipqm.gsd.componentes.dominio.gestao.GestorClassificacaoTatica;
/*     */ import ipqm.gsd.componentes.dominio.gestao.GestorFusaoAcompanhamento;
/*     */ import ipqm.gsd.componentes.dominio.gestao.GestorObjetosTatico;
/*     */ import ipqm.gsd.componentes.dominio.gestao.GestorSimulacaoTatico;
/*     */ import ipqm.gsd.componentes.dominio.gestao.GestorSinaisTatico;
/*     */ import ipqm.gsd.componentes.dominio.gestao.GestorTatico;
/*     */ import ipqm.gsd.componentes.dominio.teatro_operacao.CondicaoInicial;
/*     */ import ipqm.gsd.componentes.dominio.teatro_operacao.UsuarioMaquinaCondicao;
/*     */ import ipqm.gsd.componentes.nucleo.Ambiente;
/*     */ import ipqm.gsd.componentes.nucleo.IHM;
/*     */ import ipqm.gsd.componentes.nucleo.Maquina;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.NucleoAmbiente;
/*     */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*     */ import ipqm.gsd.componentes.nucleo.classificacao_perfil.PerfilUsuarioClassificacao;
/*     */ import ipqm.gsd.componentes.nucleo.comando_remoto.AcaoComandoRemoto;
/*     */ import ipqm.gsd.componentes.nucleo.comando_remoto.GestorComandoRemoto;
/*     */ import ipqm.gsd.componentes.nucleo.contexto.Contextualizador;
/*     */ import ipqm.gsd.componentes.nucleo.gestao.Gestor;
/*     */ import ipqm.gsd.componentes.nucleo.gestao.GestorCartasGenerico;
/*     */ import ipqm.gsd.componentes.nucleo.gestao.GestorClassificacao;
/*     */ import ipqm.gsd.componentes.nucleo.gestao.GestorFusao;
/*     */ import ipqm.gsd.componentes.nucleo.gestao.GestorIntegracao;
/*     */ import ipqm.gsd.componentes.nucleo.gestao.GestorObjetos;
/*     */ import ipqm.gsd.componentes.nucleo.gestao.GestorSimulacao;
/*     */ import ipqm.gsd.componentes.nucleo.gestao.GestorSinais;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.logon.Usuario;
/*     */ import ipqm.gsd.componentes.nucleo.logon.perfis.ConfiguracaoPerfilUsuario;
/*     */ import ipqm.gsd.componentes.nucleo.logon.perfis.PerfilUsuario;
/*     */ import ipqm.gsd.componentes.nucleo.notificador.Notificador;
/*     */ import ipqm.gsd.componentes.nucleo.util.ComandosSO;
/*     */ import ipqm.gsd.componentes.nucleo.web.ServidorHttp;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.sql.SQLException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Scanner;
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
/*     */ public class HidraTerminal
/*     */   implements AcaoComandoRemoto, IHM
/*     */ {
/*     */   private static HidraTerminal instancia;
/*     */   private boolean loginEfetuado;
/*     */   
/*     */   public static HidraTerminal getInstancia() {
/*  64 */     if (instancia == null) {
/*  65 */       instancia = new HidraTerminal();
/*     */     }
/*  67 */     return instancia;
/*     */   }
/*     */   
/*     */   public void iniciar() {
/*  71 */     Maquina.getMaquinaLocal().obtemValoresBD();
/*     */ 
/*     */     
/*  74 */     GestorComandoRemoto.getInstancia().setAcaoComandoRemoto(this);
/*     */     
/*  76 */     if (Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.SERVIDOR_HTTP_DEBUG).equals("SIM")) {
/*  77 */       ServidorHttp servidorHttp = new ServidorHttp("Hidra", "HIDRA é um produto do Grupo de Sistemas Digitais (GSD) e do Instituto de Pesquisas da Marinha (IPqM). \nMarinha do Brasil©" + ((Hidra.DATA_COMPILACAO == -1L) ? "" : (" " + (new SimpleDateFormat("yyyy")).format(Long.valueOf(Hidra.DATA_COMPILACAO)))) + " - Todos os direitos reservados. Versão: " + "qualidade_v1.1" + ((Hidra.DATA_COMPILACAO == -1L) ? "" : (" - Compilado em " + (new SimpleDateFormat("dd.MM.yy HH:mm")).format(Long.valueOf(Hidra.DATA_COMPILACAO)))) + ".");
/*  78 */       servidorHttp.iniciar();
/*     */       try {
/*  80 */         servidorHttp.setMaquinasExistentes((new Maquina()).listarTodos());
/*  81 */       } catch (Exception ex) {
/*  82 */         Log.gravarLogExcecao("Erro ao listar maquinas para o servidor http", this, ex);
/*     */       } 
/*  84 */       servidorHttp.addPagina("Objetos", "ipqm.gsd.componentes.dominio.web.PaginaObjetos");
/*  85 */       servidorHttp.addPagina("Máquinas", "ipqm.gsd.componentes.dominio.web.PaginaGrupoMaquinas");
/*  86 */       servidorHttp.addPagina("Terminal", "ipqm.gsd.hidra.ihm.web.PaginaTerminal");
/*  87 */       servidorHttp.addPagina("Sensores", "ipqm.gsd.componentes.dominio.web.PaginaSensores");
/*  88 */       servidorHttp.addPagina("Resumo", "ipqm.gsd.hidra.ihm.web.PaginaResumoHidra");
/*     */     } 
/*     */     
/*     */     try {
/*  92 */       Maquina.getMaquinaLocal().obtemValoresBD();
/*     */       
/*  94 */       DAOUsuarioMaquina daoum = new DAOUsuarioMaquina();
/*  95 */       Usuario usuarioAutomatico = daoum.getLoginAutomatico();
/*     */       
/*  97 */       if (usuarioAutomatico == null) {
/*  98 */         Log.gravarLogExcecao("Não é possivel iniciar o sistema, não há nenhum login automatico associado", this, new Exception(""));
/*  99 */         System.exit(0);
/*     */       } 
/*     */       
/* 102 */       Log.gravarLogInstrucao("Login automático para o usuário " + usuarioAutomatico.getNome(), this);
/* 103 */       UsuarioMaquinaCondicao mas = new UsuarioMaquinaCondicao(usuarioAutomatico);
/* 104 */       mas.setTipoLogin(UsuarioMaquinaCondicao.TipoLogin.AUTOMATICO);
/* 105 */       if (!login(mas)) {
/* 106 */         System.exit(0);
/*     */       }
/*     */     }
/* 109 */     catch (Exception ex) {
/* 110 */       Log.gravarLogExcecao("Erro ao iniciar o sistema", this, ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean login(UsuarioMaquinaCondicao usuarioMaquinaCondicao) {
/* 115 */     boolean status = false;
/* 116 */     if (!this.loginEfetuado) {
/*     */       
/* 118 */       DAOUsuarioMaquina daoum = new DAOUsuarioMaquina();
/*     */       
/* 120 */       Usuario userValidado = daoum.verificaUsuario(usuarioMaquinaCondicao.getUsuario());
/* 121 */       if (userValidado != null) {
/* 122 */         usuarioMaquinaCondicao.setUsuario(userValidado);
/*     */         
/* 124 */         if (daoum.verificaUsuarioMaquina(usuarioMaquinaCondicao)) {
/* 125 */           if (usuarioMaquinaCondicao.getUsuario() != null) {
/* 126 */             Log.gravarLogInstrucao("Login efetuado com sucesso, usuário: " + usuarioMaquinaCondicao.getUsuario().getNome(), this);
/*     */             try {
/* 128 */               status = iniciaCarregamentoSistema(usuarioMaquinaCondicao, false);
/* 129 */             } catch (SQLException ex) {
/* 130 */               Log.gravarLogExcecao("Erro ao executar o projeto", this, ex);
/* 131 */               status = false;
/*     */             } 
/*     */           } else {
/* 134 */             status = false;
/*     */           } 
/*     */         } else {
/* 137 */           Notificador.erro("Acesso não autorizado", "Este usuário não possui permissão para logar nesta maquina");
/* 138 */           Log.gravarLogInstrucao("Erro de login: Este usuário (" + usuarioMaquinaCondicao.getUsuario().getNome() + ") não possui permissão para logar nesta maquina", this);
/* 139 */           status = false;
/*     */         } 
/*     */       } else {
/* 142 */         Notificador.erro("Acesso não autorizado", "Usuário ou senha inválidos");
/* 143 */         Log.gravarLogInstrucao("Erro de login: Usuário (" + usuarioMaquinaCondicao.getUsuario().getNome() + ") ou senha inválidos", this);
/* 144 */         status = false;
/*     */       } 
/*     */     } 
/* 147 */     this.loginEfetuado = status;
/* 148 */     return status;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean iniciaCarregamentoSistema(UsuarioMaquinaCondicao usuarioMaquinaCondicao, boolean abrirFuncionalidadeDestaque) throws SQLException {
/*     */     boolean status;
/*     */     ConfiguracaoPerfilUsuario configuracaoPerfilUsuario;
/*     */     try {
/* 156 */       DAO<ConfiguracaoPerfilUsuario> daopbd = new DAO(ConfiguracaoPerfilUsuario.class);
/* 157 */       configuracaoPerfilUsuario = (ConfiguracaoPerfilUsuario)daopbd.listarPorID(Integer.valueOf(usuarioMaquinaCondicao.getUsuario().getIdPerfil()));
/* 158 */       Log.gravarLogInstrucao("Carregando projeto " + configuracaoPerfilUsuario.getNome(), this);
/* 159 */     } catch (Exception ex) {
/* 160 */       Log.gravarLogExcecao("Erro ao obter o perfil " + usuarioMaquinaCondicao.getUsuario().getIdPerfil(), this, ex);
/* 161 */       return false;
/*     */     } 
/*     */     
/*     */     try {
/* 165 */       Class<?> c = Class.forName("ipqm.gsd.componentes.nucleo.logon.perfis." + configuracaoPerfilUsuario.getClasse());
/* 166 */       Constructor<?> cons = c.getConstructor(new Class[] { ConfiguracaoPerfilUsuario.class });
/* 167 */       PerfilUsuario perfil = (PerfilUsuario)cons.newInstance(new Object[] { configuracaoPerfilUsuario });
/* 168 */       perfil.setUsuario(usuarioMaquinaCondicao.getUsuario());
/*     */       
/* 170 */       DAOCondicaoInicial daoci = new DAOCondicaoInicial();
/* 171 */       List<CondicaoInicial> condicoesIniciais = daoci.obtemCondicaoPorUsuarioMaquina(usuarioMaquinaCondicao.getUsuario(), usuarioMaquinaCondicao.getMaquina());
/*     */ 
/*     */       
/* 174 */       if (usuarioMaquinaCondicao.getCondicaoInicial() == null && perfil.necessitaVeiculoAssociado()) {
/* 175 */         if (condicoesIniciais.isEmpty()) {
/* 176 */           Notificador.erro("Não é possível realizar login", "Este usuário deve estar associado a um veículo referencial");
/* 177 */           Log.gravarLogInstrucao("Este usuário (" + usuarioMaquinaCondicao.getUsuario().getNome() + ") deve estar associado a um veículo referencial", this);
/* 178 */           return false;
/* 179 */         }  if (condicoesIniciais.size() > 1) {
/* 180 */           Notificador.erro("Não é possível realizar login", "Este usuário está associado a mais de um veículo referencial");
/* 181 */           Log.gravarLogInstrucao("Este usuário (" + usuarioMaquinaCondicao.getUsuario().getNome() + ") está associado a mais de um veículo referencial", this);
/* 182 */           return false;
/*     */         } 
/* 184 */         usuarioMaquinaCondicao.setCondicaoInicial(condicoesIniciais.get(0));
/*     */       } 
/*     */ 
/*     */       
/* 188 */       perfil.configurar();
/*     */       try {
/*     */         GestorClassificacaoTatica gestorClassificacaoTatica;
/* 191 */         GestorSinaisTatico gestorSinaisTatico = new GestorSinaisTatico();
/* 192 */         GestorObjetosTatico gestorObjetosTatico = new GestorObjetosTatico();
/* 193 */         GestorIntegracao gi = null;
/* 194 */         GestorSimulacaoTatico gst = null;
/* 195 */         GestorCartasGenerico gcg = null;
/* 196 */         GestorClassificacao gcl = null;
/* 197 */         GestorFusaoAcompanhamento gfa = null;
/*     */         
/* 199 */         List<PerfilUsuarioClassificacao> listaClassificadoresAssociados = new ArrayList<>(perfil.configuracaoPerfilUsuario.getListaClassificadoresAssociados());
/* 200 */         if (!listaClassificadoresAssociados.isEmpty() && Maquina.getMaquinaLocal().isServidorLocal()) {
/* 201 */           gestorClassificacaoTatica = new GestorClassificacaoTatica(listaClassificadoresAssociados);
/*     */         }
/*     */         
/* 204 */         if (perfil.isPermitirFusaoDados() && Maquina.getMaquinaLocal().isServidorLocal()) {
/* 205 */           gfa = new GestorFusaoAcompanhamento(perfil.configuracaoPerfilUsuario.getListaFusoesAssociadas());
/*     */         }
/*     */         
/* 208 */         ContextualizadorTatico contextualizadorTatico = ContextualizadorTatico.fabricar(perfil, usuarioMaquinaCondicao);
/*     */         
/* 210 */         new Mediador(this, (Contextualizador)contextualizadorTatico, (Gestor)new GestorTatico((GestorSinais)gestorSinaisTatico, (GestorObjetos)gestorObjetosTatico, (GestorSimulacao)gst, gi, gcg, (GestorClassificacao)gestorClassificacaoTatica, (GestorFusao)gfa), perfil, null);
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 215 */       catch (Exception ex) {
/* 216 */         Log.gravarLogExcecao("Erro ao iniciar o Mediador", this, ex);
/*     */       } 
/*     */       
/* 219 */       status = true;
/*     */     }
/* 221 */     catch (ClassNotFoundException|InstantiationException|IllegalAccessException|NoSuchMethodException|SecurityException|IllegalArgumentException|java.lang.reflect.InvocationTargetException ex) {
/* 222 */       Log.gravarLogExcecao("Erro ao executar projeto", this, ex);
/* 223 */       status = false;
/*     */     } 
/*     */     
/* 226 */     return status;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void logoff(String parametros) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void desligar() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void reiniciar() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void reiniciarClienteFonia() {}
/*     */ 
/*     */   
/*     */   public void reiniciarAplicacao() {
/* 247 */     Log.gravarLogInstrucao("Comando de reiniciar aplicação requisitado.", this);
/* 248 */     ComandosSO.enviarComando(ComandosSO.Comando.REINICIAR_APLICACAO, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean loginRemoto(int idCondicaoInicial, boolean abrirFuncionalidadeDestaque) {
/* 253 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void easterEgg() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void alerta(String titulo, String descricao) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void notificarInformacao(String titulo, String descricao) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void exibirIHM() {
/* 271 */     Log.getInstancia().setVerbose(false);
/*     */ 
/*     */     
/*     */     while (true) {
/* 275 */       Scanner scanner = new Scanner(System.in);
/* 276 */       System.out.print("hidra@" + Maquina.getMaquinaLocal().getHostname() + ": ");
/* 277 */       String comando = scanner.next();
/*     */       
/* 279 */       System.out.println(comando);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void ocultarIHM() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void configurar() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void configurarObjetos() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void configurarComportamentoJanela() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void configurarEstadoPadrao() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void configurarTopicoAjuda() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void desenharObjetos(List<ObjetoDOMINIO> listaObjetos) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void notificarCarregamento(int passoAtual, String descricaoPasso) {
/* 314 */     if (passoAtual == 0) {
/* 315 */       Log.gravarLogInstrucao(passoAtual + ". " + descricaoPasso, this);
/* 316 */     } else if (passoAtual == -1 && 
/* 317 */       !descricaoPasso.isEmpty()) {
/* 318 */       Log.gravarLogInterface("Exibindo aviso de splash secundário [" + descricaoPasso + "]", this);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void notificaEstadoSincronismo(boolean estadoSincronismo) {}
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/HidraTerminal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */