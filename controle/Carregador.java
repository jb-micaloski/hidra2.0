/*     */ package ipqm.gsd.hidra.ihm.controle;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.contexto.ContextualizadorTatico;
/*     */ import ipqm.gsd.componentes.dominio.gestao.GestorClassificacaoTatica;
/*     */ import ipqm.gsd.componentes.dominio.gestao.GestorFusaoAcompanhamento;
/*     */ import ipqm.gsd.componentes.dominio.gestao.GestorTatico;
/*     */ import ipqm.gsd.componentes.dominio.gestao.integracao.GestorIntegracaoAcompanhamentos;
/*     */ import ipqm.gsd.componentes.dominio.gestao.integracao.GestorIntegracaoSimPass;
/*     */ import ipqm.gsd.componentes.dominio.teatro_operacao.UsuarioMaquinaCondicao;
/*     */ import ipqm.gsd.componentes.nucleo.Ambiente;
/*     */ import ipqm.gsd.componentes.nucleo.Maquina;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.NucleoAmbiente;
/*     */ import ipqm.gsd.componentes.nucleo.classificacao_perfil.PerfilUsuarioClassificacao;
/*     */ import ipqm.gsd.componentes.nucleo.contexto.Contextualizador;
/*     */ import ipqm.gsd.componentes.nucleo.gestao.Gestor;
/*     */ import ipqm.gsd.componentes.nucleo.gestao.GestorCartasGenerico;
/*     */ import ipqm.gsd.componentes.nucleo.gestao.GestorClassificacao;
/*     */ import ipqm.gsd.componentes.nucleo.gestao.GestorFusao;
/*     */ import ipqm.gsd.componentes.nucleo.gestao.GestorIntegracao;
/*     */ import ipqm.gsd.componentes.nucleo.gestao.GestorObjetos;
/*     */ import ipqm.gsd.componentes.nucleo.gestao.GestorSimulacao;
/*     */ import ipqm.gsd.componentes.nucleo.gestao.GestorSinais;
/*     */ import ipqm.gsd.componentes.nucleo.licenca.GerenciadorLicenca;
/*     */ import ipqm.gsd.componentes.nucleo.licenca.GerenciadorLicencaSMAPS;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.logon.Funcionalidade;
/*     */ import ipqm.gsd.componentes.nucleo.logon.perfis.PerfilUsuario;
/*     */ import ipqm.gsd.componentes.nucleo.notificador.Notificador;
/*     */ import ipqm.gsd.componentes.nucleo.util.depuracao.Debug;
/*     */ import ipqm.gsd.hidra.ihm.gestao.GestorCartas;
/*     */ import ipqm.gsd.hidra.ihm.gestao.GestorObjetosGraficosFX;
/*     */ import ipqm.gsd.hidra.ihm.gestao.GestorSimulacaoFX;
/*     */ import ipqm.gsd.hidra.ihm.gestao.GestorSinaisFX;
/*     */ import ipqm.gsd.hidra.ihm.simuladores.Governo;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
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
/*     */ public class Carregador
/*     */   implements Runnable
/*     */ {
/*     */   private final Aplicacao aplicacao;
/*     */   private final PerfilUsuario perfil;
/*     */   private final UsuarioMaquinaCondicao usuarioMaquinaCondicao;
/*     */   private final List<Funcionalidade> listaFuncionalidades;
/*     */   private List<String> itensPainelPermanente;
/*     */   private List<String> listaWidgets;
/*     */   private boolean abrirFuncionalidadeDestaque;
/*     */   private final boolean atualizarCartas;
/*     */   
/*     */   public Carregador(PerfilUsuario perfil, UsuarioMaquinaCondicao usuarioMaquinaCondicao, List<Funcionalidade> listaFuncionalidades, Aplicacao aplicacao, boolean atualizarCartas) {
/*  62 */     this.perfil = perfil;
/*  63 */     this.usuarioMaquinaCondicao = usuarioMaquinaCondicao;
/*  64 */     this.aplicacao = aplicacao;
/*  65 */     this.listaFuncionalidades = listaFuncionalidades;
/*  66 */     this.atualizarCartas = atualizarCartas;
/*     */   }
/*     */ 
/*     */   
/*     */   private void configurar(UsuarioMaquinaCondicao usuarioMaquinaCondicao) {
/*  71 */     this.itensPainelPermanente = this.perfil.getItensPainelPermanente();
/*  72 */     this.listaWidgets = this.perfil.getListaWidgets();
/*     */     
/*  74 */     addWidget("painel_permanente.WidgetPainelPermanente");
/*  75 */     addWidget("pesquisa.WidgetPesquisa");
/*  76 */     addWidget("alarme.WidgetAlarme");
/*     */     
/*  78 */     Log.gravarLogInstrucao("Configurando " + this.perfil.getNome(), this);
/*  79 */     this.perfil.configurar();
/*     */     
/*  81 */     this.aplicacao.notificarCarregamento(0, "Carregando o sistema");
/*  82 */     if (configurarMediador(usuarioMaquinaCondicao)) {
/*     */       
/*  84 */       Log.gravarLogInstrucao("Configurando " + this.perfil.getNome(), this);
/*  85 */       this.perfil.configurar();
/*     */       
/*  87 */       if (PerfilUsuario.getPerfilUsuarioAtual() instanceof ipqm.gsd.componentes.nucleo.logon.perfis.AlunoSimNav) {
/*  88 */         Governo.getInstancia();
/*     */       }
/*     */       
/*  91 */       Log.gravarLogInstrucao("Carregando Funcionalidades", this);
/*  92 */       configurarCenas();
/*     */       
/*  94 */       if ((!Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.ARQUIVO_GRAVACAO_RADAR).equals("NAO") || this.perfil.isAmbienteSimulado()) && 
/*  95 */         (GestorSimulacaoFX)Mediador.getInstancia().getGestor().getGestorSimulacao() != null) {
/*  96 */         ((GestorSimulacaoFX)Mediador.getInstancia().getGestor().getGestorSimulacao()).simularVideoRadar();
/*     */       }
/*     */ 
/*     */       
/* 100 */       Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoIHM().setBloquearTela(Aplicacao.getInstancia());
/*     */       
/* 102 */       double tempoCarregamento = ((System.currentTimeMillis() - Mediador.getInstancia().getDataInicio()) / 1000L);
/*     */       
/* 104 */       this.aplicacao.notificarCarregamento(0, "Sistema iniciado");
/*     */       
/* 106 */       Log.gravarLogInstrucao("Sistema carregado em " + tempoCarregamento + " segundos", this);
/*     */       
/* 108 */       if (Debug.isDebug()) {
/* 109 */         Notificador.informacao("Sistema iniciado", "Carregado em " + tempoCarregamento + " segundos");
/*     */       } else {
/* 111 */         Notificador.ajuda("Bem vindo", "Pressione F1 para ajuda");
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean configurarMediador(UsuarioMaquinaCondicao usuarioMaquinaCondicao) {
/*     */     try {
/*     */       GestorCartas gestorCartas;
/* 121 */       ContextualizadorTatico contextualizadorTatico = ContextualizadorTatico.fabricar(this.perfil, usuarioMaquinaCondicao);
/*     */ 
/*     */ 
/*     */       
/* 125 */       GestorObjetosGraficosFX gestorObjetosGraficosFX = new GestorObjetosGraficosFX();
/* 126 */       GerenciadorLicencaSMAPS gerenciadorLicencaSMAPS = new GerenciadorLicencaSMAPS();
/* 127 */       GestorCartasGenerico gc = null;
/*     */       
/* 129 */       if (this.perfil.isIniciarGestores()) {
/* 130 */         GestorIntegracaoAcompanhamentos gestorIntegracaoAcompanhamentos; GestorClassificacaoTatica gestorClassificacaoTatica; GestorSinaisFX gestorSinaisFX = new GestorSinaisFX();
/* 131 */         GestorIntegracao gi = null;
/* 132 */         GestorSimulacaoFX gestorSimulacaoFX = new GestorSimulacaoFX();
/* 133 */         GestorClassificacao gcl = null;
/* 134 */         GestorFusaoAcompanhamento gfa = null;
/*     */         
/* 136 */         if (this.perfil.getServidorCartas() != null) {
/* 137 */           gestorCartas = new GestorCartas(this.atualizarCartas);
/*     */         }
/* 139 */         if (Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.INTEGRADOR_POPEYE_HABILITADO).equals("SIM")) {
/*     */           
/* 141 */           GestorIntegracaoSimPass gestorIntegracaoSimPass = new GestorIntegracaoSimPass();
/* 142 */         } else if (!Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.INTEGRADOR_ACOMPANHAMENTO_REDE_HABILITADO).equals("NAO")) {
/*     */           
/* 144 */           gestorIntegracaoAcompanhamentos = new GestorIntegracaoAcompanhamentos();
/*     */         } 
/*     */         
/* 147 */         List<PerfilUsuarioClassificacao> listaClassificadoresAssociados = new ArrayList<>(this.perfil.configuracaoPerfilUsuario.getListaClassificadoresAssociados());
/*     */         
/* 149 */         if (!listaClassificadoresAssociados.isEmpty() && Maquina.getMaquinaLocal().isServidorLocal())
/*     */         {
/* 151 */           gestorClassificacaoTatica = new GestorClassificacaoTatica(listaClassificadoresAssociados);
/*     */         }
/*     */         
/* 154 */         if (this.perfil.isPermitirFusaoDados() && Maquina.getMaquinaLocal().isServidorLocal()) {
/* 155 */           gfa = new GestorFusaoAcompanhamento(this.perfil.configuracaoPerfilUsuario.getListaFusoesAssociadas());
/*     */         }
/*     */         
/* 158 */         Mediador mediador = new Mediador(this.aplicacao, (Contextualizador)contextualizadorTatico, (Gestor)new GestorTatico((GestorSinais)gestorSinaisFX, (GestorObjetos)gestorObjetosGraficosFX, (GestorSimulacao)gestorSimulacaoFX, (GestorIntegracao)gestorIntegracaoAcompanhamentos, (GestorCartasGenerico)gestorCartas, (GestorClassificacao)gestorClassificacaoTatica, (GestorFusao)gfa), this.perfil, (GerenciadorLicenca)gerenciadorLicencaSMAPS);
/*     */       } else {
/*     */         
/* 161 */         if (this.perfil.getServidorCartas() != null) {
/* 162 */           gestorCartas = new GestorCartas(this.atualizarCartas);
/*     */         }
/* 164 */         Mediador mediador = new Mediador(this.aplicacao, (Contextualizador)contextualizadorTatico, (Gestor)new GestorTatico(null, (GestorObjetos)gestorObjetosGraficosFX, null, null, (GestorCartasGenerico)gestorCartas, null, null), this.perfil, (GerenciadorLicenca)gerenciadorLicencaSMAPS);
/*     */       } 
/*     */       
/* 167 */       if (this.perfil.getServidorCartas() != null && gestorCartas != null) {
/* 168 */         gestorCartas.obterEstado();
/*     */       }
/*     */       
/* 171 */       Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoIHM().obterEstado();
/*     */       
/* 173 */       return Mediador.isSistemaIniciado();
/* 174 */     } catch (Exception ex) {
/* 175 */       Log.gravarLogExcecao("Erro ao iniciar o Mediador", this, ex);
/*     */       
/* 177 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void configurarCenas() {
/* 186 */     this.aplicacao.setTitulo(this.perfil.getNome());
/* 187 */     this.aplicacao.construirCenas(this.listaFuncionalidades, this.listaWidgets, this.itensPainelPermanente);
/*     */   }
/*     */ 
/*     */   
/*     */   public void exibirTelaPrincipal() {
/* 192 */     int cont = 1;
/* 193 */     Iterator<Tela> it = this.aplicacao.getTelas().values().iterator();
/* 194 */     while (it.hasNext()) {
/* 195 */       Tela tela = it.next();
/* 196 */       if (isAbrirFuncionalidadeDestaque()) {
/* 197 */         boolean r = tela.trocar(cont);
/* 198 */         if (!r) {
/* 199 */           tela.trocar("projetos/geral/cenas/TelaPrincipal.fxml");
/*     */         }
/* 201 */         cont++; continue;
/*     */       } 
/* 203 */       tela.trocar("projetos/geral/cenas/TelaPrincipal.fxml");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addItemPainelPermanente(String item) {
/* 209 */     this.itensPainelPermanente.add(item);
/*     */   }
/*     */   
/*     */   public void addWidget(String item) {
/* 213 */     this.listaWidgets.add(item);
/*     */   }
/*     */   
/*     */   public boolean isAbrirFuncionalidadeDestaque() {
/* 217 */     return this.abrirFuncionalidadeDestaque;
/*     */   }
/*     */   
/*     */   public void setAbrirFuncionalidadeDestaque(boolean abrirFuncionalidadeDestaque) {
/* 221 */     this.abrirFuncionalidadeDestaque = abrirFuncionalidadeDestaque;
/*     */   }
/*     */ 
/*     */   
/*     */   public void run() {
/* 226 */     configurar(this.usuarioMaquinaCondicao);
/*     */     
/* 228 */     while (Aplicacao.getInstancia().getPorcentagemCarregada() < 100) {
/*     */       try {
/* 230 */         Thread.sleep(500L);
/* 231 */       } catch (InterruptedException ex) {
/* 232 */         Log.gravarLogExcecao("Thread interrompida, ocorreu algum erro no sleep", this, ex);
/*     */       } 
/*     */     } 
/*     */     
/* 236 */     exibirTelaPrincipal();
/*     */     
/* 238 */     this.aplicacao.modoAvaliacao();
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/controle/Carregador.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */