/*     */ package ipqm.gsd.hidra.ihm.dispositivos.controladores_jogo.ship_console;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.Ambiente;
/*     */ import ipqm.gsd.componentes.nucleo.VariaveisEstado;
/*     */ import ipqm.gsd.componentes.nucleo.dispositivos.controladores_jogo.ship_console.ShipConsoleControllerUser;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.hidra.ihm.dispositivos.controladores_jogo.ControladorPadrao;
/*     */ import java.text.DecimalFormat;
/*     */ import net.java.games.input.Event;
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
/*     */ public class ShipConsoleController
/*     */   extends ControladorPadrao
/*     */ {
/*     */   private static final String LEME;
/*     */   private static final String TELEGRAFO_BOMBORDO;
/*     */   private static final String TELEGRAFO_BORESTE;
/*     */   private static final String ESCALA_MAIS;
/*     */   private static final String ESCALA_MENOS;
/*     */   private static final String BOTAO_SUPERIOR_ESQUERDO;
/*     */   private static final String BOTAO_SUPERIOR_DIREITO;
/*     */   private static final String BOTAO_LEME_ESQUERDO;
/*     */   private static final String BOTAO_LEME_DIREITO;
/*     */   private static final String BOTAO_STANDARD;
/*     */   private static final String BOTAO_UM_TRES;
/*     */   private static final String BOTAO_UM_QUATRO;
/*     */   private static final String BOTAO_UM_CINCO;
/*     */   private static final String BOTAO_DOIS_UM;
/*     */   private static final String BOTAO_DOIS_DOIS;
/*     */   private static final String BOTAO_DOIS_TRES;
/*     */   private static final String BOTAO_TRES_UM;
/*     */   private static final String BOTAO_TRES_DOIS;
/*     */   private static final String BOTAO_TRES_TRES;
/*     */   private static final String BOTAO_TRES_CINCO;
/*     */   private static final String BOTAO_QUATRO_UM;
/*     */   private static final String BOTAO_QUATRO_DOIS;
/*     */   private static final String BOTAO_QUATRO_TRES;
/*     */   private static final String BOTAO_QUATRO_QUATRO;
/*     */   private static final String BOTAO_QUATRO_CINCO;
/*     */   private static final String BOTAO_QUATRO_SEIS;
/*     */   private static final String BOTAO_CINCO_QUATRO;
/*     */   private static final String BOTAO_CINCO_CINCO;
/*     */   private static final String BOTAO_CINCO_SEIS;
/*     */   private static final String BOTAO_DEMANDA_ESQUERDO;
/*     */   private static final String BOTAO_DEMANDA_DIREITO;
/*     */   private final ShipConsoleControllerUser shipConsoleControllerUser;
/*  55 */   private static double valorAtualTelegrafo = 0.0D;
/*     */   
/*  57 */   private static double valorMinimoBB = 0.0D;
/*  58 */   private static double valorMaximoBB = 0.0D;
/*     */   
/*  60 */   private static double valorMinimoMeioBB = 0.0D;
/*  61 */   private static double valorMaximoMeioBB = 0.0D;
/*     */   
/*  63 */   private static double valorMinimoBE = 0.0D;
/*  64 */   private static double valorMaximoBE = 0.0D;
/*     */   
/*  66 */   private static double valorMinimoMeioBE = 0.0D;
/*  67 */   private static double valorMaximoMeioBE = 0.0D;
/*     */   
/*     */   private static boolean CALIBRAR = false;
/*  70 */   private static DecimalFormat dF = new DecimalFormat("0.0");
/*     */   
/*     */   static {
/*  73 */     if (Ambiente.getInstance().isWindows()) {
/*  74 */       LEME = "Rotação X";
/*  75 */       TELEGRAFO_BOMBORDO = "Leme";
/*  76 */       TELEGRAFO_BORESTE = "Acelerador";
/*  77 */       ESCALA_MAIS = "Botão 3";
/*  78 */       ESCALA_MENOS = "Botão 2";
/*     */       
/*  80 */       BOTAO_SUPERIOR_ESQUERDO = "";
/*  81 */       BOTAO_SUPERIOR_DIREITO = "";
/*     */       
/*  83 */       BOTAO_LEME_ESQUERDO = "";
/*  84 */       BOTAO_LEME_DIREITO = "";
/*     */       
/*  86 */       BOTAO_STANDARD = "";
/*  87 */       BOTAO_UM_TRES = "";
/*  88 */       BOTAO_UM_QUATRO = "";
/*  89 */       BOTAO_UM_CINCO = "";
/*     */       
/*  91 */       BOTAO_DOIS_UM = "";
/*  92 */       BOTAO_DOIS_DOIS = "";
/*  93 */       BOTAO_DOIS_TRES = "";
/*     */       
/*  95 */       BOTAO_TRES_UM = "";
/*  96 */       BOTAO_TRES_DOIS = "";
/*  97 */       BOTAO_TRES_TRES = "";
/*  98 */       BOTAO_TRES_CINCO = "";
/*     */       
/* 100 */       BOTAO_QUATRO_UM = "";
/* 101 */       BOTAO_QUATRO_DOIS = "";
/* 102 */       BOTAO_QUATRO_TRES = "";
/* 103 */       BOTAO_QUATRO_QUATRO = "";
/* 104 */       BOTAO_QUATRO_CINCO = "";
/* 105 */       BOTAO_QUATRO_SEIS = "";
/*     */       
/* 107 */       BOTAO_CINCO_QUATRO = "";
/* 108 */       BOTAO_CINCO_CINCO = "";
/* 109 */       BOTAO_CINCO_SEIS = "";
/*     */       
/* 111 */       BOTAO_DEMANDA_ESQUERDO = "";
/* 112 */       BOTAO_DEMANDA_DIREITO = "";
/*     */     } else {
/*     */       
/* 115 */       LEME = "rx";
/* 116 */       TELEGRAFO_BOMBORDO = "rz";
/* 117 */       TELEGRAFO_BORESTE = "slider";
/* 118 */       ESCALA_MAIS = "Top";
/* 119 */       ESCALA_MENOS = "Thumb 2";
/*     */       
/* 121 */       BOTAO_SUPERIOR_ESQUERDO = "Base 6";
/* 122 */       BOTAO_SUPERIOR_DIREITO = "Unknown";
/*     */       
/* 124 */       BOTAO_LEME_ESQUERDO = "Unknown";
/* 125 */       BOTAO_LEME_DIREITO = "Unknown";
/*     */       
/* 127 */       BOTAO_STANDARD = "Dead";
/* 128 */       BOTAO_UM_TRES = "Unknown";
/* 129 */       BOTAO_UM_QUATRO = "Unknown";
/* 130 */       BOTAO_UM_CINCO = "Unknown";
/*     */       
/* 132 */       BOTAO_DOIS_UM = "Top";
/* 133 */       BOTAO_DOIS_DOIS = "Pinkie";
/* 134 */       BOTAO_DOIS_TRES = "Base 4";
/*     */       
/* 136 */       BOTAO_TRES_UM = "Thumb 2";
/* 137 */       BOTAO_TRES_DOIS = "Top 2";
/* 138 */       BOTAO_TRES_TRES = "Base 5";
/* 139 */       BOTAO_TRES_CINCO = "pov";
/*     */       
/* 141 */       BOTAO_QUATRO_UM = "Unknown";
/* 142 */       BOTAO_QUATRO_DOIS = "Unknown";
/* 143 */       BOTAO_QUATRO_TRES = "Unknown";
/* 144 */       BOTAO_QUATRO_QUATRO = "pov";
/* 145 */       BOTAO_QUATRO_CINCO = "Thumb";
/* 146 */       BOTAO_QUATRO_SEIS = "pov";
/*     */       
/* 148 */       BOTAO_CINCO_QUATRO = "Trigger";
/* 149 */       BOTAO_CINCO_CINCO = "pov";
/* 150 */       BOTAO_CINCO_SEIS = "Base 3";
/*     */       
/* 152 */       BOTAO_DEMANDA_ESQUERDO = "Base";
/* 153 */       BOTAO_DEMANDA_DIREITO = "Base 2";
/*     */     } 
/*     */   }
/*     */   
/*     */   public ShipConsoleController(String nomeDispositivo, ShipConsoleControllerUser shipConsoleControllerUser) throws Exception {
/* 158 */     super(nomeDispositivo);
/* 159 */     this.shipConsoleControllerUser = shipConsoleControllerUser;
/*     */     
/*     */     try {
/* 162 */       valorMaximoBB = Double.parseDouble(Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.TELEGRAFO_BB_MAX));
/* 163 */       valorMinimoBB = Double.parseDouble(Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.TELEGRAFO_BB_MIN));
/* 164 */       valorMaximoBE = Double.parseDouble(Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.TELEGRAFO_BE_MAX));
/* 165 */       valorMinimoBE = Double.parseDouble(Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.TELEGRAFO_BE_MIN));
/*     */       
/* 167 */       valorMinimoMeioBB = Double.parseDouble(Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.TELEGRAFO_BB_MEIO_MIN));
/* 168 */       valorMaximoMeioBB = Double.parseDouble(Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.TELEGRAFO_BB_MEIO_MAX));
/* 169 */       valorMinimoMeioBE = Double.parseDouble(Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.TELEGRAFO_BE_MEIO_MIN));
/* 170 */       valorMaximoMeioBE = Double.parseDouble(Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.TELEGRAFO_BE_MEIO_MAX));
/*     */     }
/* 172 */     catch (Exception ex) {
/* 173 */       Log.gravarLogExcecao("erro carregar parametro de calibragem do telegrafo", this, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void notificar(Event event) {
/* 180 */     String nomeEvento = event.getComponent().getName();
/* 181 */     double valorEvento = Double.parseDouble(dF.format(event.getValue()));
/*     */ 
/*     */     
/* 184 */     if (CALIBRAR) {
/* 185 */       setValorAtualTelegrafo(valorEvento);
/*     */       
/*     */       return;
/*     */     } 
/* 189 */     if (nomeEvento.equals(TELEGRAFO_BORESTE)) {
/* 190 */       if (valorEvento >= getValorMinimoMeioBE() && valorEvento <= getValorMaximoMeioBE()) {
/* 191 */         valorEvento = 0.0D;
/* 192 */       } else if (valorEvento < 0.0D) {
/* 193 */         valorEvento = (getValorMinimoBE() == 0.0D) ? valorEvento : (-valorEvento / getValorMinimoBE());
/*     */       } else {
/* 195 */         valorEvento = (getValorMaximoBE() == 0.0D) ? valorEvento : (valorEvento / getValorMaximoBE());
/*     */       } 
/* 197 */       this.shipConsoleControllerUser.acaoControleTelegrafoBE(valorEvento);
/*     */       
/*     */       return;
/*     */     } 
/* 201 */     if (nomeEvento.equals(TELEGRAFO_BOMBORDO)) {
/* 202 */       if (valorEvento >= getValorMinimoMeioBB() && valorEvento <= getValorMaximoMeioBB()) {
/* 203 */         valorEvento = 0.0D;
/* 204 */       } else if (valorEvento < 0.0D) {
/* 205 */         valorEvento = (getValorMinimoBB() == 0.0D) ? getValorMinimoBB() : (-valorEvento / getValorMinimoBB());
/*     */       } else {
/* 207 */         valorEvento = (getValorMaximoBB() == 0.0D) ? getValorMaximoBB() : (valorEvento / getValorMaximoBB());
/*     */       } 
/* 209 */       this.shipConsoleControllerUser.acaoControleTelegrafoBB(valorEvento);
/*     */       
/*     */       return;
/*     */     } 
/* 213 */     if (nomeEvento.equals(LEME)) {
/* 214 */       this.shipConsoleControllerUser.acaoControleLeme(valorEvento);
/*     */       return;
/*     */     } 
/* 217 */     if (nomeEvento.equals(ESCALA_MAIS)) {
/* 218 */       if (valorEvento == 0.0D) {
/* 219 */         this.shipConsoleControllerUser.acaoControleEscalaMais();
/*     */       }
/*     */       return;
/*     */     } 
/* 223 */     if (nomeEvento.equals(ESCALA_MENOS) && 
/* 224 */       valorEvento == 0.0D) {
/* 225 */       this.shipConsoleControllerUser.acaoControleEscalaMenos();
/*     */     }
/*     */     
/* 228 */     if (nomeEvento.equals(BOTAO_DEMANDA_ESQUERDO) && 
/* 229 */       valorEvento != 0.0D) {
/* 230 */       this.shipConsoleControllerUser.acaoControleEscalaMenos();
/*     */     }
/*     */     
/* 233 */     if (nomeEvento.equals(BOTAO_DEMANDA_DIREITO) && 
/* 234 */       valorEvento != 0.0D) {
/* 235 */       this.shipConsoleControllerUser.acaoControleEscalaMais();
/*     */     }
/*     */     
/* 238 */     if (nomeEvento.equals(BOTAO_STANDARD) && 
/* 239 */       valorEvento != 0.0D) {
/* 240 */       this.shipConsoleControllerUser.acaoControleModoStandard();
/*     */     }
/*     */     
/* 243 */     if (nomeEvento.equals(BOTAO_SUPERIOR_ESQUERDO) && 
/* 244 */       valorEvento != 0.0D) {
/* 245 */       this.shipConsoleControllerUser.acaoControleChangeCamera();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isCALIBRAR() {
/* 254 */     return CALIBRAR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setCALIBRAR(boolean aCALIBRAR) {
/* 261 */     CALIBRAR = aCALIBRAR;
/*     */   }
/*     */   
/*     */   public static double getValorAtualTelegrafo() {
/* 265 */     return valorAtualTelegrafo;
/*     */   }
/*     */   
/*     */   public static void setValorAtualTelegrafo(double aValorAtualTelegrafo) {
/* 269 */     valorAtualTelegrafo = aValorAtualTelegrafo;
/*     */   }
/*     */   
/*     */   public static double getValorMaximoBB() {
/* 273 */     return valorMaximoBB;
/*     */   }
/*     */   
/*     */   public static void setValorMaximoBB(double aValorMaximoBB) {
/* 277 */     valorMaximoBB = aValorMaximoBB;
/*     */   }
/*     */   
/*     */   public static double getValorMinimoBB() {
/* 281 */     return valorMinimoBB;
/*     */   }
/*     */   
/*     */   public static void setValorMinimoBB(double aValorMinimoBB) {
/* 285 */     valorMinimoBB = aValorMinimoBB;
/*     */   }
/*     */   
/*     */   public static double getValorMaximoBE() {
/* 289 */     return valorMaximoBE;
/*     */   }
/*     */   
/*     */   public static void setValorMaximoBE(double aValorMaximoBE) {
/* 293 */     valorMaximoBE = aValorMaximoBE;
/*     */   }
/*     */   
/*     */   public static double getValorMinimoBE() {
/* 297 */     return valorMinimoBE;
/*     */   }
/*     */   
/*     */   public static void setValorMinimoBE(double aValorMinimoBE) {
/* 301 */     valorMinimoBE = aValorMinimoBE;
/*     */   }
/*     */   
/*     */   public static double getValorMinimoMeioBB() {
/* 305 */     return valorMinimoMeioBB;
/*     */   }
/*     */   
/*     */   public static void setValorMinimoMeioBB(double aValorMinimoMeioBB) {
/* 309 */     valorMinimoMeioBB = aValorMinimoMeioBB;
/*     */   }
/*     */   
/*     */   public static double getValorMaximoMeioBB() {
/* 313 */     return valorMaximoMeioBB;
/*     */   }
/*     */   
/*     */   public static void setValorMaximoMeioBB(double aValorMaximoMeioBB) {
/* 317 */     valorMaximoMeioBB = aValorMaximoMeioBB;
/*     */   }
/*     */   
/*     */   public static double getValorMinimoMeioBE() {
/* 321 */     return valorMinimoMeioBE;
/*     */   }
/*     */   
/*     */   public static void setValorMinimoMeioBE(double aValorMinimoMeioBE) {
/* 325 */     valorMinimoMeioBE = aValorMinimoMeioBE;
/*     */   }
/*     */   
/*     */   public static double getValorMaximoMeioBE() {
/* 329 */     return valorMaximoMeioBE;
/*     */   }
/*     */   
/*     */   public static void setValorMaximoMeioBE(double aValorMaximoMeioBE) {
/* 333 */     valorMaximoMeioBE = aValorMaximoMeioBE;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/dispositivos/controladores_jogo/ship_console/ShipConsoleController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */