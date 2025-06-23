/*     */ package ipqm.gsd.hidra.ihm.dispositivos.controladores_jogo;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.dispositivos.controladores_jogo.ship_console.ControleTelegrafo;
/*     */ import ipqm.gsd.componentes.nucleo.Ambiente;
/*     */ import ipqm.gsd.componentes.nucleo.NucleoAmbiente;
/*     */ import ipqm.gsd.componentes.nucleo.ThreadPool;
/*     */ import ipqm.gsd.componentes.nucleo.dispositivos.controladores_jogo.ControladorJogo;
/*     */ import ipqm.gsd.componentes.nucleo.dispositivos.controladores_jogo.ship_console.ShipConsoleControllerUser;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.notificador.Notificador;
/*     */ import ipqm.gsd.componentes.nucleo.util.ComandosSO;
/*     */ import ipqm.gsd.hidra.ihm.controle.Controlador;
/*     */ import ipqm.gsd.hidra.ihm.dispositivos.controladores_jogo.pedaleira_fonia.PedaleiraFoniaController;
/*     */ import ipqm.gsd.hidra.ihm.dispositivos.controladores_jogo.pedaleira_fonia.PedaleiraFoniaUser;
/*     */ import ipqm.gsd.hidra.ihm.dispositivos.controladores_jogo.ship_console.ShipConsoleController;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.concurrent.ScheduledFuture;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GestorDispositivos
/*     */   implements Runnable
/*     */ {
/*     */   private static final String SHIP_CONSOLE = "Symbios Logic";
/*     */   private static final String NOME_SHIP_CONSOLE = "usb pad";
/*     */   private static final String NOME_PEDALEIRA = "USB Gamepad";
/*     */   private static boolean conectadoShipConsole;
/*     */   private static boolean conectadoPedaleira;
/*     */   
/*     */   public static boolean isConectadoShipConsole() {
/*  35 */     return conectadoShipConsole;
/*     */   }
/*     */   private static GestorDispositivos instancia; private ControladorJogo controle; private ControladorPadrao controladorPedaleira; private ControleTelegrafo controleTelegrafo; private PedaleiraFoniaUser widgetFonia; private ScheduledFuture scheduledFuture;
/*     */   public static boolean isConectadoPedaleira() {
/*  39 */     return conectadoPedaleira;
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
/*     */   public static GestorDispositivos getInstancia() {
/*  53 */     if (instancia == null) {
/*  54 */       instancia = new GestorDispositivos();
/*     */     }
/*  56 */     return instancia;
/*     */   }
/*     */   
/*     */   public ControleTelegrafo getControleTelegrafo() {
/*  60 */     return this.controleTelegrafo;
/*     */   }
/*     */   
/*     */   public void setControleTelegrafo(ControleTelegrafo controleTelegrafo) {
/*  64 */     this.controleTelegrafo = controleTelegrafo;
/*     */   }
/*     */   
/*     */   public PedaleiraFoniaUser getWidgetFonia() {
/*  68 */     return this.widgetFonia;
/*     */   }
/*     */   
/*     */   public void setWidgetFonia(PedaleiraFoniaUser widgetFonia) {
/*  72 */     this.widgetFonia = widgetFonia;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void run() {
/*  78 */     if (getControleTelegrafo() != null || getWidgetFonia() != null) {
/*     */       
/*     */       try {
/*  81 */         String listaDispositivos = listarUSB();
/*     */         
/*  83 */         if (getControleTelegrafo() != null)
/*     */         {
/*  85 */           if (listaDispositivos.contains("Symbios Logic")) {
/*  86 */             if (!isConectadoShipConsole()) {
/*  87 */               this.controle = (ControladorJogo)new ShipConsoleController("usb pad", (ShipConsoleControllerUser)getControleTelegrafo());
/*  88 */               getControleTelegrafo().setShipConsole(this.controle);
/*  89 */               ComandosSO.enviarComando(ComandosSO.Comando.CALIBRA_SHIPCONSOLE);
/*  90 */               conectadoShipConsole = true;
/*  91 */               if (getControleTelegrafo().getControle() != null) {
/*  92 */                 ((Controlador)getControleTelegrafo().getControle()).exibirIHM();
/*     */               }
/*  94 */               Notificador.alerta("Ship Console Conectado", null);
/*     */             }
/*     */           
/*  97 */           } else if (isConectadoShipConsole()) {
/*  98 */             conectadoShipConsole = false;
/*  99 */             if (getControleTelegrafo().getControle() != null) {
/* 100 */               ((Controlador)getControleTelegrafo().getControle()).exibirIHM();
/*     */             }
/* 102 */             Notificador.alerta("Ship Console foi desconectado", null);
/*     */           } 
/*     */         }
/*     */         
/* 106 */         if (getWidgetFonia() != null)
/*     */         {
/* 108 */           if (listaDispositivos.contains(Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.PEDALEIRA_FONIA))) {
/* 109 */             if (!isConectadoPedaleira()) {
/* 110 */               this.controladorPedaleira = (ControladorPadrao)new PedaleiraFoniaController("USB Gamepad", getWidgetFonia());
/* 111 */               conectadoPedaleira = true;
/* 112 */               Notificador.alerta("Pedaleira foi conectada", null);
/*     */             }
/*     */           
/* 115 */           } else if (isConectadoPedaleira()) {
/*     */             
/* 117 */             conectadoPedaleira = false;
/* 118 */             Notificador.alerta("Pedaleira foi desconectada", null);
/*     */           }
/*     */         
/*     */         }
/* 122 */       } catch (IOException ex) {
/* 123 */         Log.gravarLogExcecao("Não foi possível listar USB", this, ex);
/* 124 */       } catch (Exception ex) {
/* 125 */         Log.gravarLogExcecao("Erro ao iniciar dispositivo. ", this, ex);
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
/*     */ 
/*     */   
/*     */   public String listarUSB() throws IOException, InterruptedException {
/* 139 */     StringBuilder output = new StringBuilder();
/*     */ 
/*     */     
/* 142 */     Process p = Runtime.getRuntime().exec("lsusb");
/* 143 */     p.waitFor();
/* 144 */     try (BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
/*     */       String line;
/* 146 */       while ((line = reader.readLine()) != null) {
/* 147 */         output.append(line.substring(33)).append("\n");
/*     */       }
/*     */     } 
/* 150 */     p.destroy();
/* 151 */     return output.toString();
/*     */   }
/*     */   
/*     */   public void iniciar() {
/* 155 */     if (this.scheduledFuture == null) {
/* 156 */       this.scheduledFuture = ThreadPool.agendarExecucaoPeriodica(this, 5L, 2L, TimeUnit.SECONDS, "Gestor de dispositivos");
/*     */     }
/*     */   }
/*     */   
/*     */   public void encerrar() {
/* 161 */     if (this.scheduledFuture != null) {
/* 162 */       this.scheduledFuture.cancel(false);
/* 163 */       this.scheduledFuture = null;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/dispositivos/controladores_jogo/GestorDispositivos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */