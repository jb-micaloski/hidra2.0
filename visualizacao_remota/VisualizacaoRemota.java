/*     */ package ipqm.gsd.hidra.ihm.visualizacao_remota;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.Maquina;
/*     */ import ipqm.gsd.componentes.nucleo.ThreadPool;
/*     */ import ipqm.gsd.componentes.nucleo.comunicacao.ConexaoTCPIP;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.notificador.Notificador;
/*     */ import ipqm.gsd.componentes.nucleo.serializacao.AdaptadorSerializacao;
/*     */ import ipqm.gsd.componentes.nucleo.serializacao.Serializador;
/*     */ import ipqm.gsd.componentes.nucleo.util.Compactador;
/*     */ import ipqm.gsd.componentes.nucleo.util.ConversorTipos;
/*     */ import java.awt.AWTException;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.awt.GraphicsDevice;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.Image;
/*     */ import java.awt.MouseInfo;
/*     */ import java.awt.Point;
/*     */ import java.awt.PointerInfo;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Robot;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ImageObserver;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Base64;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.Future;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import javafx.application.Platform;
/*     */ import javafx.scene.image.Image;
/*     */ import javax.imageio.ImageIO;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class VisualizacaoRemota
/*     */ {
/*     */   private IVisualizacaoRemotaCaptura interfaceCaptura;
/*     */   private IVisualizacaoRemotaExibicao interfaceExibicao;
/*     */   private IVisualizacaoRemotaInicia interfaceInicia;
/*     */   private VisualizacaoTCP conexaoEnvia;
/*     */   private VisualizacaoTCP conexaoRequisicao;
/*     */   private VisualizacaoTCP conexaoRequisitado;
/*     */   private VisualizacaoAtivaTCP conexaoRecebeTentativa;
/*     */   private FrameCaptura frameRecebido;
/*     */   private Requisicao requisicao;
/*     */   private boolean instanciado;
/*     */   private Future threadEnvio;
/*     */   private Future threadRequisicao;
/*     */   private int falhaConexao;
/*     */   private BufferedImage ponteiro;
/*     */   private List<Robot> robots;
/*     */   private List<Rectangle> tamanhoTelas;
/*     */   private String ipReceptor;
/*     */   private FrameCaptura frame;
/*     */   private int telaSelecionada;
/*     */   public static final int ENVIAR = 1;
/*     */   public static final int PARAR = 0;
/*  65 */   private final int MAXIMO_FALHA = 5;
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
/*     */   public static final int FULL_HD = 1080;
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
/*     */   public static final int MINIATURA = 816;
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
/*     */   public static final String OCUPADO = "ocupado";
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
/*     */   private final Serializador serializador;
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
/*     */   public VisualizacaoRemota(IVisualizacaoRemotaInicia ivri) {
/* 185 */     List<AdaptadorSerializacao> adaptadores = new ArrayList<>();
/* 186 */     adaptadores.add(new VisualizacaoRemotaSerializer());
/* 187 */     this.serializador = new Serializador();
/* 188 */     this.serializador.construirSerializador(Serializador.Modo.JSON, adaptadores); this.interfaceInicia = ivri; this.conexaoRequisitado = new VisualizacaoTCP(20000); this.conexaoRecebeTentativa = new VisualizacaoAtivaTCP(20020); try { this.conexaoRecebeTentativa.receberDados(); this.conexaoRequisitado.receberDados(); } catch (Exception ex) { Log.gravarLogExcecao("Erro ao iniciar receber dados de requisição", VisualizacaoRemota.class, ex); }  } public VisualizacaoRemota(IVisualizacaoRemotaCaptura ivrc, String ipReceptor) { List<AdaptadorSerializacao> adaptadores = new ArrayList<>(); adaptadores.add(new VisualizacaoRemotaSerializer()); this.serializador = new Serializador(); this.serializador.construirSerializador(Serializador.Modo.JSON, adaptadores); this.interfaceCaptura = ivrc; this.ipReceptor = ipReceptor; try { this.robots = new ArrayList<>(); this.tamanhoTelas = new ArrayList<>(); GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); GraphicsDevice[] telas = ge.getScreenDevices(); for (GraphicsDevice tela : telas) { Robot robotPorTela = new Robot(tela); this.robots.add(robotPorTela); Rectangle tamanhoTela = tela.getDefaultConfiguration().getBounds(); this.tamanhoTelas.add(tamanhoTela); }  this.ponteiro = ImageIO.read(ivrc.getClass().getResource("/ipqm/gsd/hidra/ihm/imagens/ponteiro.png")); } catch (IOException ex) { Log.gravarLogExcecao("Erro ao carregar imagem do ponteiro do mouse para \n a visualizacaoRemota.", VisualizacaoRemota.class, ex); } catch (AWTException e) { Log.gravarLogExcecao("Erro ao instanciar robot.", VisualizacaoRemota.class, e); }  this.conexaoEnvia = new VisualizacaoTCP(20010, ipReceptor); this.frame = new FrameCaptura(); this.threadEnvio = ThreadPool.agendarExecucaoPeriodica(new ThreadCapturaTela(), 1L, TimeUnit.SECONDS, "Captura da tela da visualização remota"); } public VisualizacaoRemota(IVisualizacaoRemotaExibicao ivre, String ipTransmissor) { List<AdaptadorSerializacao> adaptadores = new ArrayList<>(); adaptadores.add(new VisualizacaoRemotaSerializer()); this.serializador = new Serializador(); this.serializador.construirSerializador(Serializador.Modo.JSON, adaptadores); this.interfaceExibicao = ivre; this.conexaoRequisicao = new VisualizacaoTCP(20000, ipTransmissor); this.threadRequisicao = ThreadPool.agendarExecucaoPeriodica(new ThreadEnviaAcaoIp(), 1L, TimeUnit.SECONDS, "Envio de ações da visualização remota"); } public VisualizacaoRemota(IVisualizacaoRemotaExibicao ivre) { List<AdaptadorSerializacao> adaptadores = new ArrayList<>(); adaptadores.add(new VisualizacaoRemotaSerializer()); this.serializador = new Serializador(); this.serializador.construirSerializador(Serializador.Modo.JSON, adaptadores); this.interfaceExibicao = ivre;
/*     */     VisualizacaoTCP conexaoRecebe = new VisualizacaoTCP(20010);
/*     */     VisualizacaoAtivaTCP conexaoRecebeOcupado = new VisualizacaoAtivaTCP(20030);
/*     */     try {
/*     */       conexaoRecebe.receberDados();
/*     */       conexaoRecebeOcupado.receberDados();
/*     */     } catch (Exception ex) {
/*     */       Log.gravarLogExcecao("Erro ao iniciar receber dados das imagens", VisualizacaoRemota.class, ex);
/* 196 */     }  } public BufferedImage capturaFrame() { BufferedImage captura = ((Robot)this.robots.get(this.telaSelecionada)).createScreenCapture(this.tamanhoTelas.get(this.telaSelecionada));
/*     */     
/* 198 */     return captura; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void voltaEnviar() {
/* 203 */     this.threadEnvio = ThreadPool.agendarExecucaoPeriodica(new ThreadCapturaTela(), 1L, TimeUnit.SECONDS, "Captura da tela da visualização remota");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void enviaMensagemVisualizaoOcupada(String ipEnvio) {
/* 213 */     VisualizacaoAtivaTCP conexaoEnviaOcupado = new VisualizacaoAtivaTCP(20030, ipEnvio);
/*     */     
/* 215 */     byte[] daodosEnvio = "ocupado".getBytes();
/* 216 */     byte[] bytes = new byte[4 + daodosEnvio.length];
/* 217 */     byte[] tamanho = ConversorTipos.intParaByte(daodosEnvio.length);
/* 218 */     bytes[0] = tamanho[0];
/* 219 */     bytes[1] = tamanho[1];
/* 220 */     bytes[2] = tamanho[2];
/* 221 */     bytes[3] = tamanho[3];
/* 222 */     System.arraycopy(daodosEnvio, 0, bytes, 4, daodosEnvio.length);
/*     */     try {
/* 224 */       conexaoEnviaOcupado.enviarDados(bytes);
/*     */     }
/* 226 */     catch (Exception ex) {
/* 227 */       Log.gravarLogExcecao("Erro ao enviar mensagem de visualização ocupada.", this, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void verificaVisualizacaoAtiva(String ipEnvio) {
/* 233 */     VisualizacaoAtivaTCP conexaoEnviaTentativa = new VisualizacaoAtivaTCP(20020, ipEnvio);
/*     */     
/* 235 */     String ipLocal = Maquina.getMaquinaLocal().getIp();
/*     */     
/* 237 */     byte[] daodosEnvio = ipLocal.getBytes();
/* 238 */     byte[] bytes = new byte[4 + daodosEnvio.length];
/* 239 */     byte[] tamanho = ConversorTipos.intParaByte(daodosEnvio.length);
/* 240 */     bytes[0] = tamanho[0];
/* 241 */     bytes[1] = tamanho[1];
/* 242 */     bytes[2] = tamanho[2];
/* 243 */     bytes[3] = tamanho[3];
/* 244 */     System.arraycopy(daodosEnvio, 0, bytes, 4, daodosEnvio.length);
/*     */     try {
/* 246 */       conexaoEnviaTentativa.enviarDados(bytes);
/*     */     }
/* 248 */     catch (Exception ex) {
/* 249 */       Log.gravarLogExcecao("Erro ao enviar mensagem de visualização ocupada.", this, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private class ThreadCapturaTela
/*     */     implements Runnable
/*     */   {
/*     */     private BufferedImage imgFrame;
/*     */ 
/*     */     
/*     */     private ThreadCapturaTela() {}
/*     */ 
/*     */     
/*     */     public void run() {
/* 264 */       if (VisualizacaoRemota.this.isDesenharPonteiro()) {
/* 265 */         this.imgFrame = VisualizacaoRemota.this.desenhaPonteiro(VisualizacaoRemota.this.capturaFrame());
/*     */       } else {
/* 267 */         this.imgFrame = VisualizacaoRemota.this.capturaFrame();
/*     */       } 
/*     */       
/*     */       try {
/* 271 */         switch (VisualizacaoRemota.this.interfaceCaptura.obterResolucao()) {
/*     */ 
/*     */           
/*     */           case 816:
/* 275 */             this.imgFrame = VisualizacaoRemota.resizeImage(this.imgFrame, 1450, 816);
/*     */             break;
/*     */         } 
/* 278 */         VisualizacaoRemota.this.frame.setNumeroTelas(VisualizacaoRemota.this.interfaceCaptura.obterNumeroTelas());
/* 279 */         VisualizacaoRemota.this.frame.setFrame(VisualizacaoRemota.imageToString(this.imgFrame));
/*     */         
/* 281 */         byte[] dados = VisualizacaoRemota.this.serializador.serializaSemClassIdentifier(VisualizacaoRemota.this.frame);
/* 282 */         dados = Compactador.compactar(dados);
/* 283 */         byte[] bytes = new byte[4 + dados.length];
/* 284 */         byte[] tamanho = ConversorTipos.intParaByte(dados.length);
/* 285 */         bytes[0] = tamanho[0];
/* 286 */         bytes[1] = tamanho[1];
/* 287 */         bytes[2] = tamanho[2];
/* 288 */         bytes[3] = tamanho[3];
/* 289 */         System.arraycopy(dados, 0, bytes, 4, dados.length);
/* 290 */         VisualizacaoRemota.this.conexaoEnvia.enviarDados(bytes);
/* 291 */         VisualizacaoRemota.this.falhaConexao = 0;
/* 292 */       } catch (IOException ex) {
/* 293 */         if (VisualizacaoRemota.this.falhaConexao < 5) {
/*     */           
/* 295 */           Log.gravarLogExcecao("Erro ao enviar frame.", this, ex);
/*     */           
/* 297 */           VisualizacaoRemota.this.falhaConexao++;
/*     */         } else {
/* 299 */           Notificador.erro("Erro de conexao", "Host não encontrado, conexão da visualização remota encerrada.");
/*     */           
/* 301 */           VisualizacaoRemota.this.threadEnvio.cancel(false);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private class ThreadEnviaAcaoIp
/*     */     implements Runnable
/*     */   {
/*     */     private ThreadEnviaAcaoIp() {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void run() {
/*     */       try {
/* 321 */         Requisicao requisicaoEnvio = new Requisicao();
/* 322 */         requisicaoEnvio.setAcaoConexao(VisualizacaoRemota.this.requisicao.getAcaoConexao());
/* 323 */         requisicaoEnvio.setIpExibicao(VisualizacaoRemota.this.requisicao.getIpExibicao());
/* 324 */         requisicaoEnvio.setResolucao(VisualizacaoRemota.this.requisicao.getResolucao());
/* 325 */         requisicaoEnvio.setTelaSelecionada(VisualizacaoRemota.this.requisicao.getTelaSelecionada());
/*     */         
/* 327 */         byte[] dados = VisualizacaoRemota.this.serializador.serializaSemClassIdentifier(requisicaoEnvio);
/* 328 */         dados = Compactador.compactar(dados);
/* 329 */         byte[] bytes = new byte[4 + dados.length];
/* 330 */         byte[] tamanho = ConversorTipos.intParaByte(dados.length);
/* 331 */         bytes[0] = tamanho[0];
/* 332 */         bytes[1] = tamanho[1];
/* 333 */         bytes[2] = tamanho[2];
/* 334 */         bytes[3] = tamanho[3];
/* 335 */         System.arraycopy(dados, 0, bytes, 4, dados.length);
/* 336 */         VisualizacaoRemota.this.conexaoRequisicao.enviarDados(bytes);
/* 337 */         VisualizacaoRemota.this.falhaConexao = 0;
/*     */         
/* 339 */         if (requisicaoEnvio.getAcaoConexao() == 0) {
/* 340 */           VisualizacaoRemota.this.threadRequisicao.cancel(true);
/*     */         
/*     */         }
/*     */       }
/* 344 */       catch (Exception ex) {
/*     */         
/* 346 */         if (VisualizacaoRemota.this.falhaConexao < 5) {
/*     */           
/* 348 */           Log.gravarLogExcecao("Erro ao enviar requisicao/ip", this, ex);
/*     */           
/* 350 */           VisualizacaoRemota.this.falhaConexao++;
/*     */         } else {
/* 352 */           Notificador.erro("Erro de conexão:", "Host não encontrado, conexão da visualização remota encerrada.");
/* 353 */           VisualizacaoRemota.this.interfaceExibicao.paraRequisicao();
/* 354 */           VisualizacaoRemota.this.threadRequisicao.cancel(true);
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInstanciado() {
/* 362 */     return this.instanciado;
/*     */   }
/*     */   
/*     */   public void setInstanciado(boolean instanciado) {
/* 366 */     this.instanciado = instanciado;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void paraEnvio() {
/* 375 */     this.threadEnvio.cancel(true);
/*     */   }
/*     */   
/*     */   public String getIpReceptor() {
/* 379 */     return this.ipReceptor;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Requisicao getRequisicao() {
/* 386 */     return this.requisicao;
/*     */   }
/*     */   
/*     */   public void setRequisicao(Requisicao requisicao) {
/* 390 */     this.requisicao = requisicao;
/*     */   }
/*     */   
/*     */   public int getTelaSelecionada() {
/* 394 */     return this.telaSelecionada;
/*     */   }
/*     */   
/*     */   public void setTelaSelecionada(int telaSelecionada) {
/* 398 */     this.telaSelecionada = telaSelecionada;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String imageToString(BufferedImage imagemBuffer) {
/* 408 */     String imageString = null;
/*     */     
/* 410 */     try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
/* 411 */       ImageIO.write(imagemBuffer, "png", bos);
/* 412 */       byte[] imageBytes = bos.toByteArray();
/*     */       
/* 414 */       imageString = Base64.getEncoder().encodeToString(imageBytes);
/*     */     }
/* 416 */     catch (IOException e) {
/* 417 */       Log.gravarLogExcecao("Erro ao converter imagem para string", VisualizacaoRemota.class, e);
/*     */     } 
/* 419 */     return imageString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Image stringToImage(String stringImagem) {
/* 429 */     Image image = null;
/*     */     
/* 431 */     byte[] imageByte = Base64.getDecoder().decode(stringImagem);
/* 432 */     try (ByteArrayInputStream bis = new ByteArrayInputStream(imageByte)) {
/* 433 */       image = new Image(bis);
/* 434 */     } catch (Exception e) {
/* 435 */       Log.gravarLogExcecao("Erro ao converter string para imagem", VisualizacaoRemota.class, e);
/*     */     } 
/* 437 */     return image;
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
/*     */   public static BufferedImage resizeImage(BufferedImage imagem, int novaLargura, int novaAltura) {
/* 451 */     Image tmp = imagem.getScaledInstance(novaLargura, novaAltura, 4);
/* 452 */     BufferedImage dimg = new BufferedImage(novaLargura, novaAltura, 2);
/* 453 */     Graphics2D g2d = dimg.createGraphics();
/* 454 */     g2d.drawImage(tmp, 0, 0, (ImageObserver)null);
/* 455 */     g2d.dispose();
/*     */     
/* 457 */     return dimg;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isDesenharPonteiro() {
/* 467 */     String idMonitor = MouseInfo.getPointerInfo().getDevice().getIDstring();
/*     */     
/* 469 */     GraphicsDevice[] dispositivos = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
/*     */     
/* 471 */     if (dispositivos.length == 1) {
/* 472 */       return true;
/*     */     }
/* 474 */     GraphicsDevice dispositivo = dispositivos[this.telaSelecionada];
/*     */     
/* 476 */     return dispositivo.getIDstring().equals(idMonitor);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private BufferedImage desenhaPonteiro(BufferedImage frame) {
/* 483 */     MouseInfo.getPointerInfo().getDevice().getIDstring();
/* 484 */     PointerInfo pi = MouseInfo.getPointerInfo();
/* 485 */     Point mp = pi.getLocation();
/* 486 */     GraphicsConfiguration gc = pi.getDevice().getDefaultConfiguration();
/* 487 */     Rectangle bounds = gc.getBounds();
/* 488 */     Point virtualPoint = new Point(mp);
/* 489 */     virtualPoint.x -= bounds.x;
/* 490 */     virtualPoint.y -= bounds.y;
/*     */     
/* 492 */     Graphics2D g2d = frame.createGraphics();
/* 493 */     g2d.drawImage(this.ponteiro, virtualPoint.x, virtualPoint.y, (ImageObserver)null);
/* 494 */     g2d.dispose();
/*     */     
/* 496 */     return frame;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private class VisualizacaoTCP
/*     */     extends ConexaoTCPIP
/*     */   {
/*     */     public static final int PORTA_REQUISICAO = 20000;
/*     */ 
/*     */ 
/*     */     
/*     */     public static final int PORTA_VISUALIZACAO = 20010;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public VisualizacaoTCP(int portaDestinatario, String ipDestinatario) {
/* 516 */       super(portaDestinatario, ipDestinatario, 4);
/* 517 */       setProtocolo(ConexaoTCPIP.Protocolo.TCP);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public VisualizacaoTCP(int portaRemetente) {
/* 527 */       super(portaRemetente, 4);
/* 528 */       setProtocolo(ConexaoTCPIP.Protocolo.TCP);
/*     */     }
/*     */ 
/*     */     
/*     */     public void receber(byte[] dados) {
/* 533 */       dados = Compactador.descompactar(dados);
/*     */       
/* 535 */       if (VisualizacaoRemota.this.interfaceExibicao != null) {
/* 536 */         VisualizacaoRemota.this.frameRecebido = (FrameCaptura)VisualizacaoRemota.this.serializador.deserializaSemClassIdentifier(dados, FrameCaptura.class);
/* 537 */         Image imagem = VisualizacaoRemota.stringToImage(VisualizacaoRemota.this.frameRecebido.getFrame());
/* 538 */         Platform.runLater(() -> {
/*     */               VisualizacaoRemota.this.interfaceExibicao.inserirFrame(imagem);
/*     */               
/*     */               VisualizacaoRemota.this.interfaceExibicao.setNumeroTelas(VisualizacaoRemota.this.frameRecebido.getNumeroTelas());
/*     */             });
/*     */       } else {
/* 544 */         VisualizacaoRemota.this.requisicao = (Requisicao)VisualizacaoRemota.this.serializador.deserializaSemClassIdentifier(dados, Requisicao.class);
/*     */         
/* 546 */         VisualizacaoRemota.this.interfaceInicia.telaSelecionada(VisualizacaoRemota.this.requisicao.getTelaSelecionada());
/*     */         
/* 548 */         switch (VisualizacaoRemota.this.requisicao.getAcaoConexao()) {
/*     */           
/*     */           case 1:
/* 551 */             if (VisualizacaoRemota.this.isInstanciado()) {
/*     */               break;
/*     */             }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 561 */             VisualizacaoRemota.this.interfaceInicia.instanciaEnvio(VisualizacaoRemota.this.requisicao.getIpExibicao());
/* 562 */             VisualizacaoRemota.this.interfaceInicia.requisicaoResolucao(VisualizacaoRemota.this.requisicao.getResolucao());
/* 563 */             VisualizacaoRemota.this.setInstanciado(true);
/*     */             break;
/*     */ 
/*     */           
/*     */           case 0:
/* 568 */             VisualizacaoRemota.this.setInstanciado(false);
/* 569 */             VisualizacaoRemota.this.interfaceInicia.interrompeEnvio();
/*     */             break;
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private class VisualizacaoAtivaTCP
/*     */     extends ConexaoTCPIP
/*     */   {
/*     */     public static final int PORTA_TENTATIVA_REQUISICAO = 20020;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final int PORTA_TENTATIVA_RESPOSTA = 20030;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public VisualizacaoAtivaTCP(int portaDestinatario, String ipDestinatario) {
/* 594 */       super(portaDestinatario, ipDestinatario, 4);
/* 595 */       setProtocolo(ConexaoTCPIP.Protocolo.TCP);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public VisualizacaoAtivaTCP(int portaRemetente) {
/* 605 */       super(portaRemetente, 4);
/* 606 */       setProtocolo(ConexaoTCPIP.Protocolo.TCP);
/*     */     }
/*     */ 
/*     */     
/*     */     public void receber(byte[] dados) {
/* 611 */       String saida = new String(dados);
/*     */       
/* 613 */       if (!saida.equals("ocupado")) {
/* 614 */         String ipTentativa = saida;
/* 615 */         if (VisualizacaoRemota.this.requisicao.getAcaoConexao() == 1 && 
/* 616 */           !VisualizacaoRemota.this.requisicao.getIpExibicao().equals(ipTentativa)) {
/* 617 */           VisualizacaoRemota.this.enviaMensagemVisualizaoOcupada(ipTentativa);
/*     */         }
/*     */       } else {
/*     */         
/* 621 */         VisualizacaoRemota.this.interfaceExibicao.visualizacaoOcupada();
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/visualizacao_remota/VisualizacaoRemota.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */