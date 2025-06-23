/*     */ package ipqm.gsd.hidra.ihm.simuladores;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.ObjetoTatico;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaPolar;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.Posicao;
/*     */ import ipqm.gsd.componentes.dominio.contexto.ContextoTatico;
/*     */ import ipqm.gsd.componentes.dominio.driver.extrator_nmea.DriverVideoRadar;
/*     */ import ipqm.gsd.componentes.dominio.entidades.DadosAmbientais;
/*     */ import ipqm.gsd.componentes.dominio.entidades.Veiculo;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.JanelaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.JanelaGeografica;
/*     */ import ipqm.gsd.componentes.dominio.plot.PlotIff;
/*     */ import ipqm.gsd.componentes.dominio.plot.PlotRadar;
/*     */ import ipqm.gsd.componentes.dominio.sensores.IFF;
/*     */ import ipqm.gsd.componentes.dominio.sensores.ObjetoDetectavel;
/*     */ import ipqm.gsd.componentes.dominio.sensores.Radar;
/*     */ import ipqm.gsd.componentes.dominio.sensores.RadarBusca;
/*     */ import ipqm.gsd.componentes.dominio.simuladores.radar.SimuladorVideoBruto;
/*     */ import ipqm.gsd.componentes.nucleo.Ambiente;
/*     */ import ipqm.gsd.componentes.nucleo.Maquina;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.NucleoAmbiente;
/*     */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*     */ import ipqm.gsd.componentes.nucleo.ThreadPool;
/*     */ import ipqm.gsd.componentes.nucleo.driver.Sensor;
/*     */ import ipqm.gsd.componentes.nucleo.driver.extrator_nmea.CodificadorVideoRadar;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.logon.perfis.PerfilUsuario;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*     */ import ipqm.gsd.radar.model.RadarBeam;
/*     */ import ipqm.gsd.radar.model.Range;
/*     */ import ipqm.gsd.radar.simulation.BeamSimulator;
/*     */ import ipqm.gsd.radar.simulation.BeamSimulatorParameters;
/*     */ import ipqm.gsd.radar.simulation.SeaClutterGenerator;
/*     */ import ipqm.gsd.radar.simulation.mae.MAESector;
/*     */ import ipqm.gsd.radar.simulation.target.RadarTarget;
/*     */ import ipqm.gsd.radar.simulation.target.VesselTarget;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.locks.Condition;
/*     */ import java.util.concurrent.locks.Lock;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SimuladorVideoBrutoFX
/*     */   extends SimuladorVideoBruto
/*     */ {
/*     */   private BeamSimulator simuladorRadar;
/*     */   private BeamSimulatorParameters parametrosSimuladorRadar;
/*     */   private int largura;
/*     */   private int altura;
/*     */   private CoordenadaRaster posReferencialRaster;
/*     */   private JanelaGeografica janelaGeo;
/*     */   private JanelaCartesiana janelaCart;
/*     */   private double escalaMN;
/*  61 */   private ArrayList<RadarBeam> disparos = new ArrayList<>();
/*     */   private RadarBusca sensorRadar;
/*     */   public static int idRadarAtual;
/*     */   private CanvasCenarioTatico canvasCenarioTatico;
/*  65 */   private int marcacaoAtual = 0;
/*  66 */   private int numPassosDisparo = Integer.valueOf(Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.NUMERO_PASSOS_RADAR)).intValue();
/*  67 */   private int velocidadeRotacao = Integer.valueOf(Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.VELOCIDADE_ROTACAO_RADAR)).intValue();
/*     */   private boolean exibir;
/*     */   
/*     */   public SimuladorVideoBrutoFX(Maquina dono, Veiculo vref, DriverVideoRadar driverVideoRadar, SimuladorVideoBruto.AlcanceMN alcance) {
/*  71 */     super(dono, driverVideoRadar, vref, alcance);
/*  72 */     this.largura = 1920;
/*  73 */     this.altura = 1025;
/*  74 */     this.escalaMN = 3.0D;
/*     */     try {
/*  76 */       this.parametrosSimuladorRadar = new BeamSimulatorParameters();
/*  77 */       this.parametrosSimuladorRadar.setShowBlindArea(true);
/*  78 */       this.simuladorRadar = new BeamSimulator(Mediador.getInstancia().getGestor().getGestorCartas().getChartsDB(), Range.RANGE_3NM);
/*  79 */     } catch (Exception ex) {
/*  80 */       Log.gravarLogExcecao("Erro ao criar novo simulador Radar", this, ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   public SimuladorVideoBrutoFX(Maquina dono, Veiculo vref, DriverVideoRadar driverVideoRadar, SimuladorVideoBruto.AlcanceMN alcance, int velocidadeRotacao, int idRadarAssociado) {
/*  85 */     this(dono, vref, driverVideoRadar, alcance);
/*  86 */     this.velocidadeRotacao = velocidadeRotacao;
/*  87 */     this.idRadarAssociado = idRadarAssociado;
/*     */   }
/*     */   
/*     */   public void iniciar() {
/*  91 */     long taxaSimulacao = Math.round(0.5D + 60.0D * this.numDisparosBloco * this.numPassosDisparo / 4.096D * this.velocidadeRotacao);
/*  92 */     ThreadPool.agendarExecucaoPeriodica(new SimulacaoDisparos(), taxaSimulacao, TimeUnit.MILLISECONDS, "Simulador de video bruto");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isSetorBusca(int marcacaoAtual, boolean pixel) {
/* 102 */     int marcacao = marcacaoAtual;
/* 103 */     if (pixel) {
/* 104 */       for (RadarBeam d : this.disparos) {
/* 105 */         marcacao = 360 * marcacaoAtual / d.getBearing();
/*     */       }
/*     */     }
/* 108 */     return this.sensorRadar.isSetorBusca(marcacao);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void executarPadrao() {
/* 114 */     this.simulacaoRadar.lock();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 120 */       while (!this.sinalIniciarDisparos) {
/* 121 */         this.iniciarDisparos.awaitUninterruptibly();
/*     */       }
/* 123 */       this.sinalIniciarDisparos = false;
/*     */     } finally {
/* 125 */       this.simulacaoRadar.unlock();
/*     */     } 
/*     */   }
/*     */   
/*     */   private class SimulacaoDisparos implements Runnable {
/*     */     private SimulacaoDisparos() {}
/*     */     
/*     */     public void run() {
/*     */       try {
/* 134 */         if (Mediador.isSistemaIniciado() && Mediador.getRelogio().getParado() == 0) {
/* 135 */           Posicao posicaoReferencial; if (SimuladorVideoBrutoFX.this.canvasCenarioTatico == null) {
/*     */             return;
/*     */           }
/*     */           
/* 139 */           if (!SimuladorVideoBrutoFX.this.isExibir()) {
/*     */             return;
/*     */           }
/*     */           
/*     */           try {
/* 144 */             SimuladorVideoBrutoFX.this.simulacaoRadar.lock();
/* 145 */             SimuladorVideoBrutoFX.this.sinalIniciarDisparos = true;
/* 146 */             SimuladorVideoBrutoFX.this.iniciarDisparos.signal();
/*     */           } finally {
/* 148 */             SimuladorVideoBrutoFX.this.simulacaoRadar.unlock();
/*     */           } 
/*     */           
/* 151 */           Sensor sensorIFF = null;
/* 152 */           Veiculo veiculo = Veiculo.getVeiculoReferencial();
/*     */           
/* 154 */           if (veiculo.getMapaSensores().containsKey(Sensor.Tipo.IFF)) {
/* 155 */             sensorIFF = ((List<Sensor>)veiculo.getMapaSensores().get(Sensor.Tipo.IFF)).get(0);
/*     */           }
/*     */           
/* 158 */           SimuladorVideoBrutoFX.this.sensorRadar = (RadarBusca)veiculo.getSensor(Sensor.Tipo.RADAR_BUSCA, SimuladorVideoBrutoFX.this.idRadarAssociado);
/*     */           
/* 160 */           if (SimuladorVideoBrutoFX.this.sensorRadar == null) {
/*     */             return;
/*     */           }
/*     */ 
/*     */           
/* 165 */           if (PerfilUsuario.getPerfilUsuarioAtual().isAmbienteSimulado()) {
/* 166 */             posicaoReferencial = Posicao.gerarPosicao(veiculo.getCinematicaSimulada().getPosicao().getCoordenadaGeografica());
/*     */           } else {
/* 168 */             posicaoReferencial = Posicao.gerarPosicao(veiculo.getCinematica().getPosicao().getCoordenadaGeografica());
/*     */           } 
/*     */           
/* 171 */           SimuladorVideoBrutoFX.this.setJanela(posicaoReferencial.getCoordenadaGeografica(), SimuladorVideoBrutoFX.this.escalaMN);
/*     */           
/* 173 */           SimuladorVideoBrutoFX.this.posReferencialRaster = SimuladorVideoBrutoFX.this.projeta(posicaoReferencial.getCoordenadaCartesiana());
/*     */           
/* 175 */           boolean simulaDisparosRadar = false;
/* 176 */           boolean simulaDisparosIff = false;
/*     */           
/* 178 */           verificaPlotsRadar();
/*     */           
/* 180 */           if (SimuladorVideoBrutoFX.this.sensorRadar.isHabilitado() && !SimuladorVideoBrutoFX.this.sensorRadar.isAvariado()) {
/* 181 */             simulaDisparosRadar = true;
/*     */           } else {
/*     */             return;
/*     */           } 
/*     */           
/* 186 */           if (sensorIFF != null && sensorIFF.isHabilitado() && !sensorIFF.isAvariado()) {
/* 187 */             if (PerfilUsuario.getPerfilUsuarioAtual().isAmbienteSimulado()) {
/* 188 */               simulaDisparosIff = true;
/*     */             } else {
/* 190 */               simulaDisparosIff = false;
/*     */             } 
/*     */           }
/*     */           
/* 194 */           if (simulaDisparosRadar || simulaDisparosIff) {
/* 195 */             simularDisparos();
/* 196 */           } else if (SimuladorVideoBrutoFX.this.disparos != null) {
/* 197 */             SimuladorVideoBrutoFX.this.disparos.clear();
/*     */           } 
/* 199 */         } else if (SimuladorVideoBrutoFX.this.disparos != null) {
/* 200 */           SimuladorVideoBrutoFX.this.disparos.clear();
/*     */         } 
/* 202 */       } catch (Exception e) {
/* 203 */         Log.gravarLogExcecao("Erro ao executar Simulação do vídeo bruto fx", this, e);
/*     */       } 
/*     */     }
/*     */     
/*     */     private void verificaPlotsRadar() {
/*     */       try {
/* 209 */         for (ObjetoDOMINIO objeto : Mediador.getInstancia().getGestor().getGestorObjetos().getObjetos()) {
/* 210 */           if (objeto.getId() != Veiculo.getVeiculoReferencial().getId()) {
/*     */             
/* 212 */             Radar.ChaveObjetoSensor chaveObjetoSensor = null;
/*     */             
/* 214 */             if (objeto instanceof ObjetoDetectavel) {
/*     */               
/* 216 */               ObjetoTatico objetoTatico = (ObjetoTatico)objeto;
/*     */               
/* 218 */               boolean mantemPlot = false;
/*     */               
/* 220 */               ObjetoDetectavel objetoDetectavel = (ObjetoDetectavel)objetoTatico;
/*     */               
/* 222 */               CoordenadaCartesiana cgVeiculo = Veiculo.getVeiculoReferencial().getPosicao().getCoordenadaCartesiana();
/*     */               
/* 224 */               CoordenadaCartesiana cgObjeto = objetoTatico.getCinematica().getPosicao().getCoordenadaCartesiana();
/*     */               
/* 226 */               int marcacao = (int)CoordenadaPolar.calcularMarcacao(cgVeiculo, cgObjeto);
/*     */               
/* 228 */               if (SimuladorVideoBrutoFX.this.sensorRadar.regrasDeteccao(objetoDetectavel) && SimuladorVideoBrutoFX.this.isSetorBusca(marcacao, false)) {
/* 229 */                 mantemPlot = true;
/*     */               }
/*     */               
/* 232 */               chaveObjetoSensor = new Radar.ChaveObjetoSensor(objetoTatico.getId(), SimuladorVideoBrutoFX.this.sensorRadar.getId());
/*     */               
/* 234 */               if (mantemPlot) {
/* 235 */                 if (!Radar.MAPA_PLOTS.containsKey(chaveObjetoSensor)) {
/* 236 */                   PlotRadar plot = new PlotRadar(objetoTatico, Maquina.getMaquinaLocal(), SimuladorVideoBrutoFX.this.idRadarAssociado);
/* 237 */                   Radar.MAPA_PLOTS.put(chaveObjetoSensor, plot);
/*     */                 }  continue;
/*     */               } 
/* 240 */               if (Radar.MAPA_PLOTS.containsKey(chaveObjetoSensor)) {
/* 241 */                 PlotRadar plot = (PlotRadar)Radar.MAPA_PLOTS.get(chaveObjetoSensor);
/* 242 */                 if (plot.isMarcado()) {
/* 243 */                   plot.setLiberado(false);
/* 244 */                   plot.setMarcado(false);
/*     */                 } 
/*     */                 
/* 247 */                 Radar.MAPA_PLOTS.remove(chaveObjetoSensor);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/* 252 */       } catch (Exception ex) {
/* 253 */         Log.gravarLogExcecao("Erro ao verificar plots radar", this, ex);
/*     */       } 
/*     */     }
/*     */     
/*     */     public void simularDisparos() {
/*     */       try {
/* 259 */         Veiculo veiculo = Veiculo.getVeiculoReferencial();
/*     */         
/* 261 */         DadosAmbientais dadosAmbientais = ((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getTeatroOperacao().getAmbienteTeatroOperacao();
/* 262 */         List<RadarTarget> targets = new ArrayList<>();
/* 263 */         List<MAESector> maeSectors = new ArrayList<>();
/* 264 */         double marcMin = 0.0D;
/* 265 */         double marcMax = 0.0D;
/* 266 */         for (PlotRadar obj : Radar.MAPA_PLOTS.values()) {
/* 267 */           ObjetoDetectavel objDetect = (ObjetoDetectavel)obj.getObjetoTaticoAssociado();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 274 */           VesselTarget vesselTarget = new VesselTarget(objDetect.getNome(), obj.getCinematicaObjAssociado().getLongitude(), obj.getCinematicaObjAssociado().getLatitude(), objDetect.getComprimento(), objDetect.getLargura(), obj.getCinematicaObjAssociado().getRumo().getRumoSuperficie().getRumo());
/*     */           
/* 276 */           targets.add(vesselTarget);
/*     */           
/* 278 */           if (objDetect.isIrradiandoMAE(SimuladorVideoBrutoFX.this.sensorRadar)) {
/*     */             
/* 280 */             marcMin = obj.getCinematicaObjAssociado().getPosicao().getCoordenadaPolar().getMarcacao() - 5.0D;
/* 281 */             marcMax = obj.getCinematicaObjAssociado().getPosicao().getCoordenadaPolar().getMarcacao() + 5.0D;
/*     */             
/* 283 */             if (marcMin < 0.0D) {
/* 284 */               maeSectors.add(new MAESector(marcMin, marcMax));
/* 285 */               marcMin -= 360.0D;
/* 286 */               maeSectors.add(new MAESector(marcMin, marcMax)); continue;
/* 287 */             }  if (marcMax >= 360.0D) {
/* 288 */               maeSectors.add(new MAESector(marcMin, marcMax));
/* 289 */               marcMax -= 360.0D;
/* 290 */               marcMin -= 360.0D;
/* 291 */               maeSectors.add(new MAESector(marcMin, marcMax)); continue;
/*     */             } 
/* 293 */             maeSectors.add(new MAESector(marcMin, marcMax));
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 299 */         if (veiculo.getMapaSensores().get(Sensor.Tipo.IFF) != null && SimuladorVideoBrutoFX.this.canvasCenarioTatico.getFiltroVideoRadar().isExibirVideoSecundario()) {
/* 300 */           IFF iff = ((List<IFF>)veiculo.getMapaSensores().get(Sensor.Tipo.IFF)).get(0);
/*     */           
/* 302 */           if (!iff.isAvariado() && iff.isHabilitado() && (iff
/* 303 */             .getInterrrogador().isInterrogacaoIndividual() || iff.isInterrogadorAutoAtivo())) {
/* 304 */             Mediador.getInstancia().getGestor().getGestorObjetos().getObjetos().forEach(obj -> {
/*     */                   if (obj instanceof PlotIff) {
/*     */                     Veiculo v = (Veiculo)((PlotIff)obj).getObjetoTaticoAssociado();
/*     */ 
/*     */                     
/*     */                     if (v.getMapaSensores().get(Sensor.Tipo.IFF) != null) {
/*     */                       IFF iffAlvo = ((List<IFF>)v.getMapaSensores().get(Sensor.Tipo.IFF)).get(0);
/*     */ 
/*     */                       
/*     */                       if (iff.regrasDeteccao((ObjetoDetectavel)v) && !iffAlvo.isAvariado() && iffAlvo.isHabilitado() && iffAlvo.isTransponderAtivo()) {
/*     */                         VesselTarget vesselTarget = new VesselTarget(v.getNome(), v.getLongitude(), v.getLatitude(), 0.0D, 0.0D, v.getRumo().getRumoSuperficie().getRumo());
/*     */ 
/*     */                         
/*     */                         vesselTarget.setIFFResponses(new String[] { "111" });
/*     */                         
/*     */                         targets.add(vesselTarget);
/*     */                       } 
/*     */                     } 
/*     */                   } 
/*     */                 });
/*     */           }
/*     */         } 
/*     */         
/* 327 */         SimuladorVideoBrutoFX.this.parametrosSimuladorRadar.setMAESectors(maeSectors);
/* 328 */         SimuladorVideoBrutoFX.this.parametrosSimuladorRadar.setSeaState(preencherEstadoMar(dadosAmbientais.getEstadoMar()));
/* 329 */         SimuladorVideoBrutoFX.this.parametrosSimuladorRadar.setWindSpeed(dadosAmbientais.getVelocidadeVento());
/* 330 */         SimuladorVideoBrutoFX.this.parametrosSimuladorRadar.setWindDirection(dadosAmbientais.getDirecaoVento());
/* 331 */         double escala = ((SimuladorVideoBrutoFX.this.canvasCenarioTatico == null) ? SimuladorVideoBrutoFX.this.escalaMN : SimuladorVideoBrutoFX.this.canvasCenarioTatico.getEscalaMN()) / 40.0D;
/*     */         
/* 333 */         SimuladorVideoBrutoFX.this.parametrosSimuladorRadar.setIFFMarksGap(escala);
/* 334 */         SimuladorVideoBrutoFX.this.parametrosSimuladorRadar.setIFFMarkstDistance(escala * 2.0D);
/* 335 */         SimuladorVideoBrutoFX.this.parametrosSimuladorRadar.setTargets(targets);
/*     */         
/* 337 */         SimuladorVideoBrutoFX.this.disparos.clear();
/*     */         
/* 339 */         while (SimuladorVideoBrutoFX.this.disparos.size() < SimuladorVideoBrutoFX.this.numDisparosBloco) {
/* 340 */           SimuladorVideoBrutoFX.this.disparos.add(SimuladorVideoBrutoFX.this.simuladorRadar.simulate(veiculo.getCinematicaSimulada().getLongitude(), veiculo
/* 341 */                 .getCinematicaSimulada().getLatitude(), 
/* 342 */                 Veiculo.getVeiculoReferencial().getCinematicaSimulada().getRumo().getRumoSuperficie().getRumo(), SimuladorVideoBrutoFX.this
/* 343 */                 .marcacaoAtual, SimuladorVideoBrutoFX.this.parametrosSimuladorRadar));
/* 344 */           SimuladorVideoBrutoFX.this.marcacaoAtual = SimuladorVideoBrutoFX.this.marcacaoAtual + SimuladorVideoBrutoFX.this.numPassosDisparo;
/*     */           
/* 346 */           if (SimuladorVideoBrutoFX.this.marcacaoAtual >= 4096) {
/* 347 */             SimuladorVideoBrutoFX.this.marcacaoAtual = SimuladorVideoBrutoFX.this.marcacaoAtual - 4096;
/*     */           }
/*     */         } 
/*     */         
/* 351 */         byte[] dados = CodificadorVideoRadar.codificar(SimuladorVideoBrutoFX.this.disparos);
/* 352 */         SimuladorVideoBrutoFX.this.getFilaDisparos().offer(dados);
/*     */       }
/* 354 */       catch (Exception ex) {
/* 355 */         Log.gravarLogExcecao("Erro ao simular disparos.", this, ex);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     private SeaClutterGenerator.BeaufortNumber preencherEstadoMar(int estadoMar) {
/* 361 */       SeaClutterGenerator.BeaufortNumber estado = null;
/* 362 */       switch (estadoMar) {
/*     */         case 0:
/* 364 */           estado = SeaClutterGenerator.BeaufortNumber.BEAUFORT_0;
/*     */           break;
/*     */         case 1:
/* 367 */           estado = SeaClutterGenerator.BeaufortNumber.BEAUFORT_1;
/*     */           break;
/*     */         case 2:
/* 370 */           estado = SeaClutterGenerator.BeaufortNumber.BEAUFORT_2;
/*     */           break;
/*     */         case 3:
/* 373 */           estado = SeaClutterGenerator.BeaufortNumber.BEAUFORT_3;
/*     */           break;
/*     */         case 4:
/* 376 */           estado = SeaClutterGenerator.BeaufortNumber.BEAUFORT_4;
/*     */           break;
/*     */         case 5:
/* 379 */           estado = SeaClutterGenerator.BeaufortNumber.BEAUFORT_5;
/*     */           break;
/*     */         case 6:
/* 382 */           estado = SeaClutterGenerator.BeaufortNumber.BEAUFORT_6;
/*     */           break;
/*     */         case 7:
/* 385 */           estado = SeaClutterGenerator.BeaufortNumber.BEAUFORT_7;
/*     */           break;
/*     */         case 8:
/* 388 */           estado = SeaClutterGenerator.BeaufortNumber.BEAUFORT_8;
/*     */           break;
/*     */         case 9:
/* 391 */           estado = SeaClutterGenerator.BeaufortNumber.BEAUFORT_9;
/*     */           break;
/*     */         case 10:
/* 394 */           estado = SeaClutterGenerator.BeaufortNumber.BEAUFORT_10;
/*     */           break;
/*     */         case 11:
/* 397 */           estado = SeaClutterGenerator.BeaufortNumber.BEAUFORT_11;
/*     */           break;
/*     */         case 12:
/* 400 */           estado = SeaClutterGenerator.BeaufortNumber.BEAUFORT_12;
/*     */           break;
/*     */       } 
/* 403 */       return estado;
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
/*     */   public void setJanela(CoordenadaGeografica centro, double escalaMN) {
/* 415 */     if (PerfilUsuario.getPerfilUsuarioAtual() instanceof ipqm.gsd.componentes.nucleo.logon.perfis.AlunoSimNav || this.canvasCenarioTatico == null) {
/* 416 */       this.janelaGeo = new JanelaGeografica(centro, escalaMN);
/* 417 */       this.janelaCart = JanelaCartesiana.converterJanelaGeografica(this.janelaGeo, 0.0D);
/*     */     } 
/* 419 */     if (this.canvasCenarioTatico != null) {
/* 420 */       this.janelaGeo = this.canvasCenarioTatico.getJanelaGeografica();
/* 421 */       this.janelaCart = this.canvasCenarioTatico.getJanelaCartesiana();
/*     */     } 
/*     */     
/* 424 */     this.escalaMN = escalaMN;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CoordenadaRaster projeta(CoordenadaCartesiana p) {
/* 434 */     double iX = (p.getX() - this.janelaCart.getMin().getX()) / this.janelaCart.getLargura();
/* 435 */     double iY = (this.janelaCart.getMax().getY() - p.getY()) / this.janelaCart.getAltura();
/* 436 */     return new CoordenadaRaster((int)(this.largura * iX), (int)(this.altura * iY));
/*     */   }
/*     */   
/*     */   public int converterDistanciaCartesianaParaRaster(double distanciaCartesiana) {
/* 440 */     CoordenadaCartesiana p1 = CoordenadaCartesiana.converterDistanciaXY(this.janelaCart.getCentro(), distanciaCartesiana, 0.0D);
/*     */     
/* 442 */     double dx = Math.abs(p1.getX() - this.janelaCart.getCentro().getX());
/* 443 */     return (int)(dx / this.janelaCart.getLargura() * this.largura + 0.5D);
/*     */   }
/*     */   
/*     */   public CoordenadaRaster converterCoordenadaCartesianaParaRaster(CoordenadaCartesiana p) {
/* 447 */     double iX = (p.getX() - this.janelaCart.getMin().getX()) / this.janelaCart.getLargura();
/* 448 */     double iY = (this.janelaCart.getMax().getY() - p.getY()) / this.janelaCart.getAltura();
/* 449 */     return new CoordenadaRaster((int)(this.largura * iX), (int)(this.altura * iY));
/*     */   }
/*     */   
/*     */   public void setDimensao(int altura, int largura) {
/* 453 */     this.altura = altura;
/* 454 */     this.largura = largura;
/*     */   }
/*     */   
/*     */   public int getLargura() {
/* 458 */     return this.largura;
/*     */   }
/*     */   
/*     */   public int getAltura() {
/* 462 */     return this.altura;
/*     */   }
/*     */   
/*     */   public static int getIdRadarAtual() {
/* 466 */     return idRadarAtual;
/*     */   }
/*     */   
/*     */   public static void setIdRadarAtual(int idRadarAtual) {
/* 470 */     SimuladorVideoBrutoFX.idRadarAtual = idRadarAtual;
/*     */   }
/*     */   
/*     */   public void setCanvasCenarioTatico(CanvasCenarioTatico canvasCenarioTatico) {
/* 474 */     this.canvasCenarioTatico = canvasCenarioTatico;
/*     */   }
/*     */   
/*     */   public CanvasCenarioTatico getCanvasCenarioTatico() {
/* 478 */     return this.canvasCenarioTatico;
/*     */   }
/*     */   
/*     */   public BeamSimulator getSimuladorRadar() {
/* 482 */     return this.simuladorRadar;
/*     */   }
/*     */   
/*     */   public void setSimuladorRadar(BeamSimulator simuladorRadar) {
/* 486 */     this.simuladorRadar = simuladorRadar;
/*     */   }
/*     */   
/*     */   public boolean verificaCanvas(CanvasCenarioTatico canvas) {
/* 490 */     return (this.canvasCenarioTatico == canvas);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isExibir() {
/* 497 */     return this.exibir;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setExibir(boolean exibir) {
/* 504 */     this.exibir = exibir;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/simuladores/SimuladorVideoBrutoFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */