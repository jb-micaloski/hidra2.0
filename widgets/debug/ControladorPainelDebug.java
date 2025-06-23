/*     */ package ipqm.gsd.hidra.ihm.widgets.debug;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.banco_dados.GestorBancoDeDados;
/*     */ import ipqm.gsd.componentes.dominio.contexto.ContextoTatico;
/*     */ import ipqm.gsd.componentes.dominio.entidades.Veiculo;
/*     */ import ipqm.gsd.componentes.dominio.gestao.GestorClassificacaoTatica;
/*     */ import ipqm.gsd.componentes.dominio.teatro_operacao.GrupoMaquinas;
/*     */ import ipqm.gsd.componentes.dominio.teatro_operacao.TeatroOperacao;
/*     */ import ipqm.gsd.componentes.nucleo.Ambiente;
/*     */ import ipqm.gsd.componentes.nucleo.Maquina;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.NucleoAmbiente;
/*     */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*     */ import ipqm.gsd.componentes.nucleo.ThreadPool;
/*     */ import ipqm.gsd.componentes.nucleo.comando_remoto.GestorComandoRemoto;
/*     */ import ipqm.gsd.componentes.nucleo.configuracao.Proxy;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.relogio_simulado.RelogioSimulado;
/*     */ import ipqm.gsd.componentes.nucleo.util.ConversorTipos;
/*     */ import ipqm.gsd.hidra.ihm.Hidra;
/*     */ import ipqm.gsd.hidra.ihm.controle.Aplicacao;
/*     */ import ipqm.gsd.hidra.ihm.controle.Tela;
/*     */ import ipqm.gsd.hidra.ihm.util.UtilDesempenho;
/*     */ import java.lang.management.ManagementFactory;
/*     */ import java.net.URL;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.ResourceBundle;
/*     */ import java.util.Set;
/*     */ import javafx.animation.TranslateTransition;
/*     */ import javafx.application.Platform;
/*     */ import javafx.collections.FXCollections;
/*     */ import javafx.collections.ObservableList;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.fxml.FXML;
/*     */ import javafx.fxml.Initializable;
/*     */ import javafx.scene.Node;
/*     */ import javafx.scene.chart.LineChart;
/*     */ import javafx.scene.chart.XYChart;
/*     */ import javafx.scene.control.Button;
/*     */ import javafx.scene.control.Label;
/*     */ import javafx.scene.control.TableColumn;
/*     */ import javafx.scene.control.TableView;
/*     */ import javafx.scene.control.TextArea;
/*     */ import javafx.scene.control.TitledPane;
/*     */ import javafx.scene.control.cell.PropertyValueFactory;
/*     */ import javafx.scene.input.MouseEvent;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ import javafx.util.Callback;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ControladorPainelDebug
/*     */   implements Initializable
/*     */ {
/*  58 */   private final DecimalFormat df = new DecimalFormat("0.##");
/*     */   
/*     */   private WidgetDebug widgetDebug;
/*     */   
/*     */   @FXML
/*     */   private AnchorPane anchorPaneDebug;
/*     */   
/*     */   @FXML
/*     */   private AnchorPane barraDebug;
/*     */   
/*     */   @FXML
/*     */   private AnchorPane painelDebug;
/*     */   
/*     */   @FXML
/*     */   private Label labelTexto;
/*     */   
/*     */   @FXML
/*     */   private TableView<ControladorTabelaObjetos> tabelaObjetos;
/*     */   
/*     */   private ObservableList<ControladorTabelaObjetos> listaObjetos;
/*     */   
/*     */   @FXML
/*     */   private TableColumn<ControladorTabelaObjetos, Integer> colunaID;
/*     */   
/*     */   @FXML
/*     */   private TableColumn<ControladorTabelaObjetos, String> colunaObjeto;
/*     */   
/*     */   @FXML
/*     */   private TableColumn<ControladorTabelaObjetos, String> colunaDono;
/*     */   
/*     */   @FXML
/*     */   private TableColumn<ControladorTabelaObjetos, String> colunaClasse;
/*     */   
/*     */   @FXML
/*     */   private TableColumn<ControladorTabelaObjetos, String> colunaTimeStamp;
/*     */   
/*     */   @FXML
/*     */   private TableColumn<ControladorTabelaObjetos, String> colunaIpOrigem;
/*     */   @FXML
/*     */   private TableColumn<ControladorTabelaObjetos, String> colunaTamanho;
/*     */   @FXML
/*     */   private TableColumn<ControladorTabelaObjetos, String> colunaAcao;
/*     */   @FXML
/*     */   private TableView<ItemTabela> tabelaItens;
/*     */   @FXML
/*     */   private TableColumn<ItemTabela, String> colunaTabelaIntensChave;
/*     */   @FXML
/*     */   private TableColumn<ItemTabela, String> colunaTabelaIntensValor;
/*     */   private ObservableList<ItemTabela> listaItens;
/*     */   @FXML
/*     */   private Label labelMaquinaLocal;
/*     */   @FXML
/*     */   private Label labelIpMaquinaLocal;
/*     */   @FXML
/*     */   private Label labelTempoExecução;
/*     */   @FXML
/*     */   private LineChart<?, ?> graficoThreads;
/*     */   private XYChart.Series serieThreads;
/*     */   @FXML
/*     */   private TitledPane painelThreads;
/*     */   @FXML
/*     */   private LineChart<?, ?> graficoMemoria;
/*     */   private XYChart.Series serieMemoriaUso;
/*     */   private XYChart.Series serieMemoriaMaxima;
/*     */   @FXML
/*     */   private TitledPane painelMemoria;
/*     */   @FXML
/*     */   private Label labelCompilado;
/*     */   @FXML
/*     */   private TitledPane painelObjetos;
/*     */   @FXML
/*     */   private Label labelGrupoMaquina;
/*     */   @FXML
/*     */   private Label labelMaquinasExistentes;
/*     */   @FXML
/*     */   private Label labelSistema;
/*     */   @FXML
/*     */   private TextArea areaInfoObjeto;
/*     */   private ControladorTabelaObjetos objetoSelecionado;
/*     */   boolean mouseAreaInfoObjeto = false;
/*     */   private String ultimoLog;
/*     */   @FXML
/*     */   private Button botaoSolicitarDumper;
/*     */   
/*     */   public void initialize(URL url, ResourceBundle rb) {
/* 143 */     this.barraDebug.setVisible(false);
/* 144 */     this.painelDebug.setVisible(false);
/*     */ 
/*     */     
/* 147 */     this.listaItens = FXCollections.observableArrayList();
/*     */     
/* 149 */     this.colunaTabelaIntensChave.setCellValueFactory((Callback)new PropertyValueFactory("item"));
/* 150 */     this.colunaTabelaIntensValor.setCellValueFactory((Callback)new PropertyValueFactory("valor"));
/*     */ 
/*     */     
/* 153 */     this.listaObjetos = FXCollections.observableArrayList();
/* 154 */     this.colunaID.setCellValueFactory((Callback)new PropertyValueFactory("ID"));
/* 155 */     this.colunaObjeto.setCellValueFactory((Callback)new PropertyValueFactory("objeto"));
/* 156 */     this.colunaDono.setCellValueFactory((Callback)new PropertyValueFactory("dono"));
/* 157 */     this.colunaAcao.setCellValueFactory((Callback)new PropertyValueFactory("acao"));
/* 158 */     this.colunaTimeStamp.setCellValueFactory((Callback)new PropertyValueFactory("timestamp"));
/* 159 */     this.colunaIpOrigem.setCellValueFactory((Callback)new PropertyValueFactory("ipOrigem"));
/* 160 */     this.colunaTamanho.setCellValueFactory((Callback)new PropertyValueFactory("tamanho"));
/* 161 */     this.colunaClasse.setCellValueFactory((Callback)new PropertyValueFactory("classe"));
/*     */ 
/*     */     
/* 164 */     this.serieThreads = new XYChart.Series();
/* 165 */     this.serieThreads.setName("Threads");
/*     */     
/* 167 */     this.graficoThreads.getData().add(this.serieThreads);
/* 168 */     this.graficoThreads.setLegendVisible(false);
/* 169 */     this.graficoThreads.setAnimated(false);
/*     */     
/* 171 */     this.serieMemoriaUso = new XYChart.Series();
/* 172 */     this.serieMemoriaUso.setName("Memória livre");
/* 173 */     this.serieMemoriaMaxima = new XYChart.Series();
/* 174 */     this.serieMemoriaMaxima.setName("Memória total");
/*     */     
/* 176 */     this.graficoMemoria.getData().addAll((Object[])new XYChart.Series[] { this.serieMemoriaMaxima, this.serieMemoriaUso });
/* 177 */     this.graficoMemoria.setLegendVisible(false);
/* 178 */     this.graficoMemoria.setAnimated(false);
/*     */   }
/*     */   
/*     */   public WidgetDebug getWidgetDebug() {
/* 182 */     return this.widgetDebug;
/*     */   }
/*     */   
/*     */   public void setWidgetDebug(WidgetDebug widgetDebug) {
/* 186 */     this.widgetDebug = widgetDebug;
/*     */   }
/*     */ 
/*     */   
/*     */   public void atualiza() {
/* 191 */     long tempoExecucao = System.currentTimeMillis() - Hidra.tempoIniciado;
/* 192 */     long segundos = tempoExecucao / 1000L;
/* 193 */     long minutos = segundos / 60L;
/* 194 */     long horas = minutos / 60L;
/* 195 */     minutos %= 60L;
/* 196 */     segundos %= 60L;
/*     */     
/* 198 */     String tempoExecucaoTexto = String.format("%02d:%02d:%02d", new Object[] { Long.valueOf(horas), Long.valueOf(minutos), Long.valueOf(segundos) });
/*     */ 
/*     */     
/* 201 */     Platform.runLater(() -> {
/*     */           int t = ThreadPool.getInstancia().getTamanhoFila();
/*     */           
/*     */           if (this.serieThreads.getData().size() == 0 || ((Integer)((XYChart.Data)this.serieThreads.getData().get(this.serieThreads.getData().size() - 1)).getYValue()).intValue() != t) {
/*     */             this.serieThreads.getData().add(new XYChart.Data(tempoExecucaoTexto, Integer.valueOf(t)));
/*     */           }
/*     */           
/*     */           if (this.serieThreads.getData().size() >= 20) {
/*     */             this.serieThreads.getData().remove(0);
/*     */           }
/*     */           
/*     */           long mJVM = Maquina.getMaquinaLocal().getMonitoracaoDaAplicacao().getMemoriaJava() / 1048576L;
/*     */           
/*     */           long mSO = Maquina.getMaquinaLocal().getMonitoracaoDaAplicacao().getMemoriaSistema() / 1048576L;
/*     */           
/*     */           if (this.serieMemoriaUso.getData().size() == 0 || ((Long)((XYChart.Data)this.serieMemoriaUso.getData().get(this.serieMemoriaUso.getData().size() - 1)).getYValue()).longValue() != mJVM) {
/*     */             this.serieMemoriaUso.getData().add(new XYChart.Data(tempoExecucaoTexto, Long.valueOf(mJVM)));
/*     */             this.serieMemoriaMaxima.getData().add(new XYChart.Data(tempoExecucaoTexto, Long.valueOf(mSO)));
/*     */           } 
/*     */           if (this.serieMemoriaUso.getData().size() >= 20) {
/*     */             this.serieMemoriaUso.getData().remove(0);
/*     */             this.serieMemoriaMaxima.getData().remove(0);
/*     */           } 
/*     */         });
/* 225 */     switch (this.widgetDebug.getModo()) {
/*     */       case MINIMIZADO:
/* 227 */         atualizaBarraDebug(tempoExecucaoTexto);
/*     */         break;
/*     */       case MAXIMIZADO:
/* 230 */         atualizaPainelDebug(tempoExecucaoTexto);
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void atualizaBarraDebug(String tempoExecucaoTexto) {
/* 238 */     StringBuilder texto = new StringBuilder();
/*     */     
/* 240 */     texto.append(" tempo: ").append(ConversorTipos.milisegundosParaString(System.currentTimeMillis() - Hidra.tempoIniciado));
/*     */     
/* 242 */     texto.append(" | maq. local: ").append(Maquina.getMaquinaLocal().getHostname()).append(" (").append(Maquina.getMaquinaLocal().getIp()).append(")");
/*     */     
/* 244 */     texto.append(" | versão: ").append("qualidade_v1.1");
/*     */     
/* 246 */     if (Hidra.DATA_COMPILACAO != -1L) {
/* 247 */       texto.append(" | compilado: ").append((new SimpleDateFormat("dd.MM.yy HH:mm")).format(Long.valueOf(Hidra.DATA_COMPILACAO)));
/*     */     }
/*     */     
/* 250 */     Iterator<Tela> it = Aplicacao.getInstancia().getTelas().values().iterator();
/* 251 */     while (it.hasNext()) {
/* 252 */       Tela tela = it.next();
/* 253 */       String nome = " | fps: ";
/* 254 */       if (tela.getNumTela() > 0) {
/* 255 */         nome = " | fps (t" + (tela.getNumTela() + 1) + "): ";
/*     */       }
/* 257 */       texto.append(nome).append(String.format("%.2f", new Object[] { Float.valueOf(tela.getFPS()) }));
/*     */     } 
/*     */     
/* 260 */     texto.append(" | threads (jvm): ").append(ManagementFactory.getThreadMXBean().getThreadCount());
/*     */     
/* 262 */     texto.append(" | mem.: ").append(ConversorTipos.byteLegivel(Runtime.getRuntime()
/* 263 */           .freeMemory(), true)).append("/").append(ConversorTipos.byteLegivel(Runtime.getRuntime().totalMemory(), true));
/*     */     
/* 265 */     if (Mediador.isSistemaIniciado()) {
/* 266 */       if (Mediador.getInstancia().getGestor().getGestorObjetos() != null) {
/* 267 */         texto.append(" | objetos: ").append(Mediador.getInstancia().getGestor().getGestorObjetos().getObjetos().size()).append("/").append(Mediador.getInstancia().getPerfilUsuario().getQuantidadeMaximaObjetos());
/*     */       }
/* 269 */       if (((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto())
/* 270 */         .getTeatroOperacao() != null) {
/*     */         
/* 272 */         if (((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getGrupoMaquinas() != null) {
/* 273 */           if (((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto())
/* 274 */             .getGrupoMaquinas().getMaquinaServidoraLocal() != null) {
/* 275 */             texto.append(" | SL: ").append(((ContextoTatico)Mediador.getInstancia()
/* 276 */                 .getContextualizador().getContexto()).getGrupoMaquinas().getMaquinaServidoraLocal().getHostname());
/*     */           } else {
/* 278 */             texto.append(" | SL: ").append("---");
/*     */           } 
/*     */         }
/*     */         
/* 282 */         if (((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getGrupoMaquinas() != null) {
/* 283 */           String maquinasExistentes = "";
/* 284 */           Set<Maquina> setMaquinasExistentes = ((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getGrupoMaquinas().getMaquinasExistentes();
/* 285 */           for (Maquina maq : setMaquinasExistentes) {
/* 286 */             maquinasExistentes = maquinasExistentes.concat(maq.getHostname() + " ");
/*     */           }
/* 288 */           texto.append(" | maqs.: ").append(maquinasExistentes);
/*     */         } 
/*     */       } 
/*     */       
/* 292 */       if (Veiculo.getVeiculoReferencial() != null) {
/* 293 */         Veiculo veiculoReferencial = Veiculo.getVeiculoReferencial();
/* 294 */         texto.append(" | v.ref.: ").append(veiculoReferencial.getNome()).append(" (").append(veiculoReferencial.getCondicaoAssociada().getGrupo()).append(")");
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 299 */     texto.append(" | maximizar/fechar: CTRL+D ");
/*     */     
/* 301 */     if (this.ultimoLog != null) {
/* 302 */       texto.append("| Log: ").append(this.ultimoLog);
/*     */     }
/*     */ 
/*     */     
/* 306 */     String textoFinal = texto.toString();
/* 307 */     Platform.runLater(() -> this.labelTexto.setText(textoFinal));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void atualizaPainelDebug(String tempoExecucaoTexto) {
/* 313 */     this.listaItens.clear();
/*     */     
/* 315 */     Platform.runLater(() -> {
/*     */           this.labelMaquinaLocal.setText(Maquina.getMaquinaLocal().getHostname());
/*     */           
/*     */           this.labelIpMaquinaLocal.setText("(" + Maquina.getMaquinaLocal().getIdMaquina() + ") " + ((Maquina.getMaquinaLocal().getTitulo() != null) ? (Maquina.getMaquinaLocal().getTitulo() + " - ") : "") + Maquina.getMaquinaLocal().getIp() + " - " + ((Maquina.getMaquinaLocal().getMac() != null) ? Maquina.getMaquinaLocal().getMac() : ""));
/*     */           
/*     */           this.labelTempoExecução.setText(tempoExecucaoTexto);
/*     */           
/*     */           this.labelCompilado.setText("v: qualidade_v1.1 | Compilado em: " + ((Hidra.DATA_COMPILACAO != -1L) ? (new SimpleDateFormat("dd.MM.yy HH:mm")).format(Long.valueOf(Hidra.DATA_COMPILACAO)) : "n/d"));
/*     */           
/*     */           this.labelGrupoMaquina.setText("---");
/*     */           
/*     */           this.labelMaquinasExistentes.setText("");
/*     */           
/*     */           if (Mediador.isSistemaIniciado() && ((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getGrupoMaquinas() != null) {
/*     */             GrupoMaquinas gm = ((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getGrupoMaquinas();
/*     */             
/*     */             this.labelGrupoMaquina.setText(gm.toString());
/*     */             
/*     */             if (gm.getMaquinaServidoraLocal() != null) {
/*     */               this.labelMaquinasExistentes.setText("Servidor local: " + gm.getMaquinaServidoraLocal().toString());
/*     */             }
/*     */           } 
/*     */           
/*     */           this.painelThreads.setText("Threads (" + ThreadPool.getInstancia().getTamanhoFila() + ")");
/*     */           
/*     */           this.painelMemoria.setText("Memória JVM (" + ConversorTipos.byteLegivel(Runtime.getRuntime().freeMemory(), true) + "/" + ConversorTipos.byteLegivel(Runtime.getRuntime().totalMemory(), true) + " max: " + ConversorTipos.byteLegivel(Runtime.getRuntime().maxMemory(), true) + ") - Amarelo - JVM / Vermelho - S.O. | eixo Y em MB");
/*     */           
/*     */           if (Mediador.isSistemaIniciado()) {
/*     */             if (Mediador.getInstancia().getGestor().getGestorObjetos() != null) {
/*     */               this.painelObjetos.setText("Objetos em memória (" + Mediador.getInstancia().getGestor().getGestorObjetos().getObjetos().size() + "/" + Mediador.getInstancia().getPerfilUsuario().getQuantidadeMaximaObjetos() + ")");
/*     */             } else {
/*     */               this.painelObjetos.setText("Gestor de Objetos não Iniciado");
/*     */             } 
/*     */           }
/*     */           
/*     */           if (this.objetoSelecionado != null && Mediador.getInstancia().getGestor().getGestorObjetos().buscar(this.objetoSelecionado.getObjetoDOMINIO().getId()) == null) {
/*     */             this.objetoSelecionado = null;
/*     */           }
/*     */           
/*     */           if (!this.mouseAreaInfoObjeto) {
/*     */             this.areaInfoObjeto.clear();
/*     */             if (this.objetoSelecionado != null) {
/*     */               this.areaInfoObjeto.setText(this.objetoSelecionado.getObjetoDOMINIO().getInfo().toString());
/*     */             }
/*     */           } 
/*     */           if (Maquina.getMaquinaLocal().getMonitoracaoDaAplicacao() != null) {
/*     */             String monitoracaoAplicacao = "Java: " + Maquina.getMaquinaLocal().getMonitoracaoDaAplicacao().getJavaInfo() + "\n";
/*     */             monitoracaoAplicacao = monitoracaoAplicacao + "Uso CPU (JVM / SO): " + this.df.format(Maquina.getMaquinaLocal().getMonitoracaoDaAplicacao().getCargaCPUJava() * 100.0D) + "% / " + this.df.format(Maquina.getMaquinaLocal().getMonitoracaoDaAplicacao().getCargaCPUSistema() * 100.0D) + "%\n";
/*     */             monitoracaoAplicacao = monitoracaoAplicacao + "Memória (SO): " + ConversorTipos.byteLegivel(Maquina.getMaquinaLocal().getMonitoracaoDaAplicacao().getTotalMemoriaSistema() - Maquina.getMaquinaLocal().getMonitoracaoDaAplicacao().getMemoriaSistema(), true) + "/" + ConversorTipos.byteLegivel(Maquina.getMaquinaLocal().getMonitoracaoDaAplicacao().getTotalMemoriaSistema(), true) + "\n";
/*     */             monitoracaoAplicacao = monitoracaoAplicacao + "Swap: " + ConversorTipos.byteLegivel(Maquina.getMaquinaLocal().getMonitoracaoDaAplicacao().getSwapUso(), true) + "/" + ConversorTipos.byteLegivel(Maquina.getMaquinaLocal().getMonitoracaoDaAplicacao().getTotalSwapDisponivel(), true) + "\n";
/*     */             this.labelSistema.setText(monitoracaoAplicacao);
/*     */           } 
/*     */         });
/* 368 */     Iterator<Tela> it = Aplicacao.getInstancia().getTelas().values().iterator();
/* 369 */     while (it.hasNext()) {
/* 370 */       Tela tela = it.next();
/* 371 */       String nome = "FPS";
/* 372 */       if (tela.getNumTela() > 0) {
/* 373 */         nome = "FPS (t" + (tela.getNumTela() + 1) + ")";
/*     */       }
/* 375 */       this.listaItens.add(new ItemTabela(nome, String.format("%.2f", new Object[] { Float.valueOf(tela.getFPS()) })));
/*     */     } 
/*     */     
/* 378 */     this.listaItens.add(new ItemTabela("Veloc. animação", UtilDesempenho.getVelocidadeAnimacao().name()));
/* 379 */     this.listaItens.add(new ItemTabela("Freq. CT", UtilDesempenho.getFrequenciaAtualizacaoCenarioTatico().name()));
/*     */     
/* 381 */     this.listaItens.add(new ItemTabela("", ""));
/*     */     
/* 383 */     this.listaItens.add(new ItemTabela("DB escrita", GestorBancoDeDados.getInstanciaEscrita().toString()));
/* 384 */     this.listaItens.add(new ItemTabela("DB leitura", GestorBancoDeDados.getInstanciaLeitura().toString()));
/* 385 */     this.listaItens.add(new ItemTabela("Schema", GestorBancoDeDados.getInstanciaEscrita().getSchema()));
/* 386 */     this.listaItens.add(new ItemTabela("Sinc. postgres", Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.SINCRONIA_POSTGRES)));
/*     */     
/* 388 */     this.listaItens.add(new ItemTabela("", ""));
/*     */     
/* 390 */     this.listaItens.add(new ItemTabela("Cmd remoto (fila)", String.valueOf(GestorComandoRemoto.getInstancia().getTamFila())));
/* 391 */     this.listaItens.add(new ItemTabela("Cmd remoto (proc)", String.valueOf(GestorComandoRemoto.getInstancia().getComandosEmProcessamentos())));
/*     */     
/* 393 */     this.listaItens.add(new ItemTabela("", ""));
/* 394 */     this.listaItens.add(new ItemTabela("Thread Pool:", ""));
/* 395 */     String[] p = ThreadPool.getInstancia().getInfo().split("\\.");
/* 396 */     for (String s : p) {
/* 397 */       String[] v = s.split(": ");
/* 398 */       this.listaItens.add(new ItemTabela(v[0], v[1]));
/*     */     } 
/*     */     
/* 401 */     if (Mediador.isSistemaIniciado()) {
/* 402 */       this.listaItens.add(new ItemTabela("", ""));
/* 403 */       this.listaItens.add(new ItemTabela("Threads proc.", Mediador.getInstancia().getThreadsProcessadorPresas() + ""));
/* 404 */       this.listaItens.add(new ItemTabela("Contextualizador", Mediador.getInstancia().getContextualizador().getTempoInteracao() + "ms"));
/* 405 */       this.listaItens.add(new ItemTabela("Usuário", Mediador.getInstancia().getPerfilUsuario().getUsuario().getNome()));
/* 406 */       this.listaItens.add(new ItemTabela("Perfil", Mediador.getInstancia().getPerfilUsuario().getNome()));
/*     */       
/* 408 */       if (((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto())
/* 409 */         .getTeatroOperacao() != null) {
/*     */         
/* 411 */         TeatroOperacao teatro = ((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getTeatroOperacao();
/* 412 */         if (teatro != null) {
/* 413 */           this.listaItens.add(new ItemTabela("Teatro", teatro.getNome()));
/*     */         }
/*     */         
/* 416 */         this.listaItens.add(new ItemTabela("", ""));
/*     */         
/* 418 */         if (Mediador.getInstancia().getGestor().getGestorSimulacao() != null && 
/* 419 */           Mediador.getInstancia().getGestor().getGestorSimulacao().isSimulando()) {
/* 420 */           this.listaItens.add(new ItemTabela("Sim. habilitada", "SIM"));
/*     */         }
/*     */ 
/*     */         
/* 424 */         if (Veiculo.getVeiculoReferencial() != null) {
/* 425 */           Veiculo veiculoReferencial = Veiculo.getVeiculoReferencial();
/* 426 */           this.listaItens.add(new ItemTabela("V. ref.", veiculoReferencial.getNome() + "(" + veiculoReferencial.getCondicaoAssociada().getGrupo() + ")"));
/*     */         } 
/*     */         
/* 429 */         if (Mediador.getInstancia().getGestor().getGestorClassificacao() != null) {
/* 430 */           this.listaItens.add(new ItemTabela("", ""));
/* 431 */           this.listaItens.add(new ItemTabela("Classificação", "SIM"));
/* 432 */           int tamFilaGestorClassificacao = ((GestorClassificacaoTatica)Mediador.getInstancia().getGestor().getGestorClassificacao()).getTamanhoFila();
/* 433 */           this.listaItens.add(new ItemTabela("Fila", String.valueOf(tamFilaGestorClassificacao)));
/*     */         } 
/*     */ 
/*     */         
/* 437 */         if (RelogioSimulado.getInstance() != null) {
/* 438 */           String relogio = Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.SERVIDOR_RELOGIO);
/* 439 */           this.listaItens.add(new ItemTabela("Srv. relógio", relogio));
/*     */         } 
/*     */         
/* 442 */         if (Mediador.getInstancia().getGestor().getGestorIntegracao() != null && 
/* 443 */           Mediador.getInstancia().getGestor().getGestorIntegracao() instanceof ipqm.gsd.componentes.dominio.gestao.integracao.GestorIntegracaoSimPass) {
/* 444 */           this.listaItens.add(new ItemTabela("Simpass", "SIM"));
/*     */         }
/*     */ 
/*     */         
/* 448 */         this.listaItens.add(new ItemTabela("", ""));
/* 449 */         if (((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getGrupoMaquinas() != null) {
/* 450 */           if (((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto())
/* 451 */             .getGrupoMaquinas().getMaquinaServidoraLocal() != null) {
/* 452 */             this.listaItens.add(new ItemTabela("Servidor local", ((ContextoTatico)Mediador.getInstancia()
/* 453 */                   .getContextualizador().getContexto()).getGrupoMaquinas().getMaquinaServidoraLocal().getHostname()));
/*     */           } else {
/* 455 */             this.listaItens.add(new ItemTabela("Servidor local", "---"));
/*     */           } 
/*     */         }
/*     */         
/* 459 */         if (((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getGrupoMaquinas() != null) {
/* 460 */           GrupoMaquinas gm = ((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getGrupoMaquinas();
/* 461 */           this.listaItens.add(new ItemTabela("Grupo Máquina", gm.toString()));
/* 462 */           this.listaItens.add(new ItemTabela("Maqs. existentes:", ""));
/* 463 */           Set<Maquina> setMaquinasExistentes = ((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getGrupoMaquinas().getMaquinasExistentes();
/* 464 */           for (Maquina maq : setMaquinasExistentes) {
/* 465 */             this.listaItens.add(new ItemTabela(maq.getHostname(), maq.getEstadoConexao().name()));
/*     */           }
/*     */         } 
/*     */         
/* 469 */         this.listaItens.add(new ItemTabela("", ""));
/*     */         
/* 471 */         Proxy proxy = Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoRede().getProxy();
/* 472 */         if (proxy != null) {
/* 473 */           this.listaItens.add(new ItemTabela("Proxy habilitado", proxy.isHabilitado() ? "SIM" : "NÃO"));
/* 474 */           this.listaItens.add(new ItemTabela("Host", proxy.getHost() + ":" + proxy.getPorta()));
/* 475 */           this.listaItens.add(new ItemTabela("Usuário", proxy.getUsuario()));
/* 476 */           this.listaItens.add(new ItemTabela("Senha", proxy.getSenha(true)));
/* 477 */           this.listaItens.add(new ItemTabela("Exceções", proxy.getExcecoes()));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 484 */     Platform.runLater(() -> {
/*     */           this.tabelaItens.getItems().clear();
/*     */           
/*     */           this.tabelaItens.getItems().addAll((Collection)this.listaItens);
/*     */         });
/*     */     
/* 490 */     if (Mediador.isSistemaIniciado()) {
/* 491 */       this.listaObjetos.clear();
/* 492 */       if (Mediador.getInstancia().getGestor().getGestorObjetos() != null) {
/* 493 */         Mediador.getInstancia().getGestor().getGestorObjetos().getObjetos().stream().forEach(objetoDOMINIO -> this.listaObjetos.add(new ControladorTabelaObjetos(objetoDOMINIO)));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 498 */       Platform.runLater(() -> {
/*     */             this.painelObjetos.setDisable(false);
/*     */             this.botaoSolicitarDumper.setDisable(false);
/*     */             this.tabelaObjetos.getItems().clear();
/*     */             this.tabelaObjetos.getItems().addAll((Collection)this.listaObjetos);
/*     */             if (this.objetoSelecionado != null) {
/*     */               this.tabelaObjetos.getSelectionModel().select(this.objetoSelecionado);
/*     */             }
/*     */           });
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUltimoLog(String ultimoLog) {
/* 512 */     this.ultimoLog = ultimoLog;
/*     */   }
/*     */   
/*     */   public void mostrarBarra() {
/* 516 */     ocultarPainel();
/* 517 */     this.barraDebug.setVisible(true);
/* 518 */     this.widgetDebug.getAnchorPane().setPrefHeight(30.0D);
/* 519 */     this.widgetDebug.getAnchorPane().setMaxHeight(30.0D);
/* 520 */     this.widgetDebug.getAnchorPane().setMinHeight(30.0D);
/* 521 */     TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(100.0D), (Node)this.barraDebug);
/* 522 */     tt.setFromX(1780.0D);
/* 523 */     tt.setToX(0.0D);
/* 524 */     tt.setAutoReverse(false);
/* 525 */     tt.play();
/*     */   }
/*     */   
/*     */   private void ocultarBarra() {
/* 529 */     TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(100.0D), (Node)this.barraDebug);
/* 530 */     tt.setFromX(0.0D);
/* 531 */     tt.setToX(1780.0D);
/* 532 */     tt.setAutoReverse(false);
/* 533 */     tt.setOnFinished(arg0 -> this.barraDebug.setVisible(false));
/*     */ 
/*     */     
/* 536 */     tt.play();
/*     */   }
/*     */   
/*     */   public void mostrarPainel() {
/* 540 */     ocultarBarra();
/* 541 */     this.painelDebug.setVisible(true);
/* 542 */     this.widgetDebug.getAnchorPane().setPrefWidth(1080.0D);
/* 543 */     this.widgetDebug.getAnchorPane().setMaxWidth(1080.0D);
/* 544 */     this.widgetDebug.getAnchorPane().setMinWidth(1080.0D);
/* 545 */     TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(100.0D), (Node)this.painelDebug);
/* 546 */     tt.setFromY(-2000.0D);
/* 547 */     tt.setToY(0.0D);
/* 548 */     tt.setAutoReverse(false);
/* 549 */     tt.play();
/*     */   }
/*     */   
/*     */   private void ocultarPainel() {
/* 553 */     TranslateTransition tt = new TranslateTransition(UtilDesempenho.duracaoAnimacao(100.0D), (Node)this.painelDebug);
/* 554 */     tt.setFromY(0.0D);
/* 555 */     tt.setToY(-2000.0D);
/* 556 */     tt.setAutoReverse(false);
/* 557 */     tt.setOnFinished(arg0 -> this.painelDebug.setVisible(false));
/*     */ 
/*     */     
/* 560 */     tt.play();
/*     */   }
/*     */   
/*     */   public void ocultarTudo() {
/* 564 */     this.widgetDebug.getAnchorPane().setPrefWidth(0.0D);
/* 565 */     this.widgetDebug.getAnchorPane().setMaxWidth(0.0D);
/* 566 */     this.widgetDebug.getAnchorPane().setMinWidth(0.0D);
/* 567 */     ocultarBarra();
/* 568 */     ocultarPainel();
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void acaoSelecionaTabela(MouseEvent event) {
/* 573 */     if (this.tabelaObjetos.getSelectionModel().getSelectedItem() != null) {
/* 574 */       this.objetoSelecionado = (ControladorTabelaObjetos)this.tabelaObjetos.getSelectionModel().getSelectedItem();
/*     */     }
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void acaoFecharPainel(ActionEvent event) {
/* 580 */     this.widgetDebug.fechar();
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void acaoCliqueBarra(MouseEvent event) {
/* 585 */     if (event.getClickCount() == 2) {
/* 586 */       this.widgetDebug.fechar();
/*     */     }
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void mouseSaiuAreaInfoObjeto(MouseEvent event) {
/* 592 */     this.mouseAreaInfoObjeto = false;
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void mouseEntrouAreaInfoObjeto(MouseEvent event) {
/* 597 */     this.mouseAreaInfoObjeto = true;
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void acaoSolicitarDumper(ActionEvent event) {
/* 602 */     Log.gravarLogDebug("Usuário solicitou dumper!", this);
/* 603 */     ThreadPool.executar(() -> this.widgetDebug.solicitarDumper(), "Dumper manual");
/*     */   }
/*     */ 
/*     */   
/*     */   public class ItemTabela
/*     */   {
/*     */     private final String item;
/*     */     
/*     */     private final String valor;
/*     */     
/*     */     public ItemTabela(String item, String valor) {
/* 614 */       this.item = item;
/* 615 */       this.valor = valor;
/*     */     }
/*     */     
/*     */     public String getItem() {
/* 619 */       return this.item;
/*     */     }
/*     */     
/*     */     public String getValor() {
/* 623 */       return this.valor;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/debug/ControladorPainelDebug.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */