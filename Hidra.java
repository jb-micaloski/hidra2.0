/*     */ package ipqm.gsd.hidra.ihm;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.banco_dados.GestorBancoDeDados;
/*     */ import ipqm.gsd.componentes.nucleo.Ambiente;
/*     */ import ipqm.gsd.componentes.nucleo.IHM;
/*     */ import ipqm.gsd.componentes.nucleo.Maquina;
/*     */ import ipqm.gsd.componentes.nucleo.NucleoAmbiente;
/*     */ import ipqm.gsd.componentes.nucleo.configuracao.Diretorios;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.notificador.Notificador;
/*     */ import ipqm.gsd.componentes.nucleo.util.depuracao.Debug;
/*     */ import ipqm.gsd.hidra.ihm.banco_dados.GestorBancoDeDadosIHM;
/*     */ import ipqm.gsd.hidra.ihm.configuracao.CarregamentoFX;
/*     */ import ipqm.gsd.hidra.ihm.configuracao.CarregamentoTerminal;
/*     */ import ipqm.gsd.hidra.ihm.configuracao.IHMCarregamento;
/*     */ import ipqm.gsd.hidra.ihm.util.Argumentos;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.NetworkInterface;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Enumeration;
/*     */ import java.util.List;
/*     */ import java.util.jar.JarFile;
/*     */ import java.util.zip.ZipEntry;
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
/*     */ public class Hidra
/*     */ {
/*     */   public static final String VERSAO = "qualidade_v1.1";
/*     */   public static long DATA_COMPILACAO;
/*     */   public static long tempoIniciado;
/*     */   private static boolean verbose;
/*     */   private static boolean atualizaEsquema;
/*     */   protected static boolean configurar;
/*     */   private static boolean exibirSQL;
/*  48 */   private static String nivelLogArgumento = null;
/*     */   
/*     */   private static boolean modoTerminal;
/*     */   
/*     */   protected static boolean forcaLogin;
/*     */   
/*     */   protected static boolean atualizarCartas;
/*     */   
/*     */   public static GestorBancoDeDadosIHM gbdd;
/*     */   
/*     */   public static List<String> argumentos;
/*     */   protected static IHMCarregamento carregamento;
/*     */   
/*     */   public static void main(String[] args) {
/*  62 */     tempoIniciado = System.currentTimeMillis();
/*     */     
/*  64 */     argumentos = new ArrayList<>();
/*     */     
/*  66 */     for (String arg : args) {
/*  67 */       arg = arg.toLowerCase().trim();
/*  68 */       if (arg.equals(Argumentos.TERMINAL.getArgumento())) {
/*  69 */         modoTerminal = true;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/*     */     try {
/*  75 */       JarFile jf = new JarFile("hidra.jar");
/*  76 */       ZipEntry manifest = jf.getEntry("META-INF/MANIFEST.MF");
/*  77 */       DATA_COMPILACAO = manifest.getTime();
/*  78 */     } catch (IOException ex) {
/*  79 */       DATA_COMPILACAO = -1L;
/*     */     } 
/*     */     
/*  82 */     if (modoTerminal) {
/*  83 */       carregamento = (IHMCarregamento)new CarregamentoTerminal();
/*     */     } else {
/*  85 */       carregamento = (IHMCarregamento)new CarregamentoFX();
/*     */     } 
/*     */     
/*  88 */     carregamento.iniciar();
/*     */     
/*  90 */     carregamento.notificarCarregamento(0, "Iniciando o carregamento do sistema");
/*     */     
/*     */     try {
/*  93 */       carregamento.notificarCarregamento(0, "Obtendo argumentos");
/*  94 */       configuraArgumentos(args);
/*     */       
/*  96 */       carregamento.notificarCarregamento(0, "Abrindo arquivo de configurações");
/*  97 */       if (configuraArquivoConfiguracoes()) {
/*  98 */         carregamento.ocultarIHM();
/*     */       } else {
/* 100 */         String interfaceRede = Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.INTERFACE_REDE);
/* 101 */         if (Maquina.getInterfaceRedePorNome(interfaceRede) == null) {
/* 102 */           throw new Exception("Rede não detectada");
/*     */         }
/*     */         
/* 105 */         carregamento.notificarCarregamento(0, "Iniciando serviço de log");
/* 106 */         configuraServicoLog();
/*     */         
/* 108 */         carregamento.notificarCarregamento(0, "Iniciando Gestor de Banco de dados");
/* 109 */         configuraBancoDeDados();
/*     */         
/* 111 */         carregamento.notificarCarregamento(0, "Iniciando a aplicação");
/* 112 */         carregamento.finalizaCarregamento();
/*     */       } 
/*     */       
/* 115 */       Maquina.getMaquinaLocal().iniciaMonitoracaoAplicacao();
/*     */       
/* 117 */       if (modoTerminal) {
/* 118 */         HidraTerminal.getInstancia().iniciar();
/*     */       } else {
/* 120 */         HidraFX.iniciar(args);
/*     */       }
/*     */     
/* 123 */     } catch (Exception ex) {
/* 124 */       carregamento.erro(ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void configuraArgumentos(String[] args) throws Exception {
/* 129 */     for (String arg : args) {
/* 130 */       arg = arg.toLowerCase().trim();
/* 131 */       argumentos.add(arg);
/*     */       
/* 133 */       if (arg.equals(Argumentos.LOGIN.getArgumento())) {
/* 134 */         forcaLogin = true;
/* 135 */       } else if (arg.equals(Argumentos.VERBOSE.getArgumento())) {
/* 136 */         verbose = true;
/* 137 */       } else if (arg.equals(Argumentos.DEBUG.getArgumento())) {
/* 138 */         Debug.getInstancia().setDepuracao(true);
/* 139 */       } else if (arg.equals(Argumentos.UPDATE.getArgumento())) {
/* 140 */         atualizaEsquema = true;
/* 141 */       } else if (arg.equals(Argumentos.CONFIGURACAO.getArgumento())) {
/* 142 */         configurar = true;
/* 143 */       } else if (arg.equals(Argumentos.EXIBIR_SQL.getArgumento())) {
/* 144 */         exibirSQL = true;
/* 145 */       } else if (arg.equals(Argumentos.ATUALIZAR_CARTAS.getArgumento())) {
/* 146 */         atualizarCartas = true;
/* 147 */       } else if (arg.equals(Argumentos.TERMINAL.getArgumento())) {
/* 148 */         modoTerminal = true;
/* 149 */       } else if (arg.contains(Argumentos.LOG.getArgumento())) {
/* 150 */         String[] s = arg.split("=");
/* 151 */         if (s[1] != null && !s[1].isEmpty()) {
/* 152 */           nivelLogArgumento = s[1];
/*     */         }
/*     */       } else {
/* 155 */         StringBuilder ex = new StringBuilder();
/* 156 */         ex.append("Argumento inválido, argumentos disponíveis:\n");
/* 157 */         for (Argumentos argumento : Argumentos.values()) {
/* 158 */           ex.append("- ").append(argumento.getArgumento()).append(" - ").append(argumento.getAjuda()).append("\n");
/*     */         }
/* 160 */         throw new Exception(ex.toString());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean configuraArquivoConfiguracoes() throws Exception {
/* 167 */     File arquivoConfiguracao = new File(new File(".") + "/" + Ambiente.getPropriedadePadrao());
/*     */     
/* 169 */     if (!arquivoConfiguracao.exists()) {
/* 170 */       arquivoConfiguracao.createNewFile();
/*     */     }
/*     */ 
/*     */     
/* 174 */     if (Ambiente.getInstance().atualizaPropriedadePadrao((IHM)carregamento)) {
/* 175 */       Notificador.informacao("Arquivo de configuração alterado", "Alterações automáticas em " + Ambiente.getPropriedadePadrao());
/*     */     }
/*     */     
/* 178 */     File diretorioSQL = new File(Diretorios.getDiretorioPadrao(Diretorios.Diretorio.SQL_BACKUP));
/* 179 */     if (diretorioSQL.exists()) {
/* 180 */       carregamento.notificarCarregamento(0, "Obtendo os arquivos sql de instalação para o configurador");
/* 181 */       NucleoAmbiente.SQL_INSTALACAO.getValoresPermitidos().clear();
/* 182 */       NucleoAmbiente.SQL_INSTALACAO.getValoresPermitidos().add("NENHUM");
/* 183 */       for (File file : diretorioSQL.listFiles()) {
/* 184 */         if (file.isFile() && !file.isHidden() && !file.getName().endsWith("~")) {
/* 185 */           NucleoAmbiente.SQL_INSTALACAO.getValoresPermitidos().add(file.getName());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 190 */     if (NucleoAmbiente.VELOCIDADE_ROTACAO_RADAR.getValoresPermitidos().isEmpty()) {
/* 191 */       carregamento.notificarCarregamento(0, "Obtendo os valores de velocidade de Rotação do Radar para o configurador");
/* 192 */       NucleoAmbiente.VELOCIDADE_ROTACAO_RADAR.getValoresPermitidos().clear();
/* 193 */       for (int i = 12; i <= 48; i++) {
/* 194 */         NucleoAmbiente.VELOCIDADE_ROTACAO_RADAR.getValoresPermitidos().add(String.valueOf(i));
/*     */       }
/*     */     } 
/*     */     
/* 198 */     File diretorioRadar = new File(Diretorios.getDiretorioPadrao(Diretorios.Diretorio.GRAVACAO_RADAR));
/* 199 */     if (diretorioRadar.exists()) {
/* 200 */       carregamento.notificarCarregamento(0, "Obtendo os arquivos de reprodução radar para o configurador");
/* 201 */       NucleoAmbiente.ARQUIVO_GRAVACAO_RADAR.getValoresPermitidos().clear();
/* 202 */       for (File file : diretorioRadar.listFiles()) {
/* 203 */         if (file.isFile() && !file.isHidden() && !file.getName().endsWith("~")) {
/* 204 */           NucleoAmbiente.ARQUIVO_GRAVACAO_RADAR.getValoresPermitidos().add(file.getName());
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 209 */     carregamento.notificarCarregamento(0, "Obtendo as interfaces de rede para o configurador");
/* 210 */     NucleoAmbiente.INTERFACE_REDE.getValoresPermitidos().clear();
/* 211 */     for (Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces(); nets.hasMoreElements(); ) {
/* 212 */       NetworkInterface netInt = nets.nextElement();
/* 213 */       NucleoAmbiente.INTERFACE_REDE.getValoresPermitidos().add(netInt.getDisplayName());
/*     */     } 
/*     */     
/* 216 */     return configurar;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void configuraServicoLog() throws Exception {
/* 221 */     if (nivelLogArgumento == null) {
/* 222 */       Log.getInstancia().setNivel(Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.NIVEL_LOG));
/*     */     } else {
/* 224 */       Log.getInstancia().setNivel(nivelLogArgumento);
/*     */     } 
/*     */     
/* 227 */     Ambiente ambiente = Ambiente.getInstance();
/* 228 */     String hostR = ambiente.obterValorVariavelAmbiente(NucleoAmbiente.HOST_BD_LEITURA);
/* 229 */     String portaR = ambiente.obterValorVariavelAmbiente(NucleoAmbiente.PORTA_BD_LEITURA);
/* 230 */     String bd = ambiente.obterValorVariavelAmbiente(NucleoAmbiente.NOME_BD);
/* 231 */     String schema = ambiente.obterValorVariavelAmbiente(NucleoAmbiente.SCHEMA_BD);
/*     */     
/* 233 */     String hostW = ambiente.obterValorVariavelAmbiente(NucleoAmbiente.HOST_BD_ESCRITA);
/* 234 */     String portaW = ambiente.obterValorVariavelAmbiente(NucleoAmbiente.PORTA_BD_ESCRITA);
/*     */     
/* 236 */     StringBuilder info = new StringBuilder();
/* 237 */     info.append("Nivel do Log: ").append(Log.getInstancia().getNivel()).append("\n")
/* 238 */       .append("Versão atual:  ").append("qualidade_v1.1").append("\n")
/* 239 */       .append("Data de compilação: ").append((DATA_COMPILACAO != -1L) ? (new SimpleDateFormat("dd.MM.yy HH:mm")).format(Long.valueOf(DATA_COMPILACAO)) : "n/d").append("\n")
/* 240 */       .append("Argumentos: ").append(argumentos.toString()).append("\n\n")
/* 241 */       .append("Banco de dados: ").append(bd).append("\n")
/* 242 */       .append("Host (leitura): ").append(hostR).append(":").append(portaR).append("\n")
/* 243 */       .append("Host (escrita): ").append(hostW).append(":").append(portaW).append("\n")
/* 244 */       .append("Esquema: ").append(schema).append("\n\n");
/*     */     
/* 246 */     Log.getInstancia().inicia(info.toString(), "qualidade_v1.1", Diretorios.getDiretorioPadrao(Diretorios.Diretorio.LOG));
/*     */     
/* 248 */     Log.gravarLogInstrucao("Serviço de log iniciado", HidraFX.class);
/*     */     
/* 250 */     if (Debug.isDebug()) {
/* 251 */       verbose = true;
/*     */     }
/*     */     
/* 254 */     Log.getInstancia().setVerbose(verbose);
/*     */   }
/*     */   
/*     */   private static void configuraBancoDeDados() throws Exception {
/*     */     try {
/* 259 */       gbdd = new GestorBancoDeDadosIHM(exibirSQL);
/* 260 */       GestorBancoDeDados.setInstanciaLeitura(gbdd.getGestorBancoDeDadosLeitura());
/* 261 */       GestorBancoDeDados.setInstanciaEscrita(gbdd.getGestorBancoDeDadosEscrita());
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 266 */       if (Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.SINCRONIA_POSTGRES).equals("SIM")) {
/* 267 */         PreparedStatement stmt = gbdd.getGestorBancoDeDadosEscrita().getConnection().prepareStatement("SELECT client_addr FROM pg_stat_replication;");
/* 268 */         ResultSet rs = stmt.executeQuery();
/* 269 */         List<String> listaMaqSincronizadas = new ArrayList<>();
/* 270 */         while (rs.next()) {
/* 271 */           String ip = rs.getString(1);
/* 272 */           listaMaqSincronizadas.add(ip);
/*     */         } 
/* 274 */         if (!listaMaqSincronizadas.contains(Maquina.getMaquinaLocal().getIp())) {
/* 275 */           throw new Exception("Favor sincronizar máquina local no módulo suporte.");
/*     */         }
/*     */       }
/*     */     
/* 279 */     } catch (Exception ex) {
/* 280 */       throw new Exception("Erro de conexão com o banco de dados: " + ex.getMessage());
/*     */     } 
/*     */     
/*     */     try {
/* 284 */       gbdd.getGestorBancoDeDadosEscrita().exportar((IHM)carregamento);
/* 285 */     } catch (Exception ex) {
/* 286 */       throw new Exception("Ocorreu algum erro na criação/exportação do banco de dados: " + ex.getMessage());
/*     */     } 
/*     */     
/* 289 */     if (atualizaEsquema) {
/*     */       try {
/* 291 */         gbdd.getGestorBancoDeDadosEscrita().atualiza((IHM)carregamento);
/* 292 */       } catch (Exception ex) {
/* 293 */         throw new Exception("Ocorreu algum erro na atualização do banco de dados: " + ex.getMessage());
/*     */       } 
/*     */     }
/*     */     
/* 297 */     gbdd.getGestorBancoDeDadosEscrita().obterConfiguracoes((IHM)carregamento);
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/Hidra.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */