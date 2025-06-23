/*    */ package ipqm.gsd.hidra.ihm.gestao;
/*    */ 
/*    */ import ipqm.gsd.componentes.dominio.driver.extrator_nmea.DriverExtratorNMEA;
/*    */ import ipqm.gsd.componentes.dominio.driver.extrator_nmea.DriverVideoRadar;
/*    */ import ipqm.gsd.componentes.dominio.entidades.Veiculo;
/*    */ import ipqm.gsd.componentes.dominio.gestao.GestorSimulacaoTatico;
/*    */ import ipqm.gsd.componentes.dominio.sensores.RadarBusca;
/*    */ import ipqm.gsd.componentes.dominio.simuladores.radar.SimuladorVideoBruto;
/*    */ import ipqm.gsd.componentes.nucleo.Ambiente;
/*    */ import ipqm.gsd.componentes.nucleo.Maquina;
/*    */ import ipqm.gsd.componentes.nucleo.Mediador;
/*    */ import ipqm.gsd.componentes.nucleo.NucleoAmbiente;
/*    */ import ipqm.gsd.componentes.nucleo.configuracao.Diretorios;
/*    */ import ipqm.gsd.componentes.nucleo.driver.Driver;
/*    */ import ipqm.gsd.componentes.nucleo.driver.Sensor;
/*    */ import ipqm.gsd.componentes.nucleo.logon.perfis.PerfilUsuario;
/*    */ import ipqm.gsd.componentes.nucleo.util.ConversorUnidades;
/*    */ import ipqm.gsd.hidra.ihm.simuladores.SimuladorVideoBrutoFX;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.stream.Collectors;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GestorSimulacaoFX
/*    */   extends GestorSimulacaoTatico
/*    */ {
/*    */   private SimuladorVideoBrutoFX simuladorVideoRadar;
/* 33 */   public static Map<Integer, SimuladorVideoBrutoFX> MAPA_SIMULADORES = new HashMap<>();
/*    */   
/*    */   public synchronized void simularVideoRadar() {
/* 36 */     if (this.simuladorVideoRadar != null || Veiculo.getVeiculoReferencial() == null) {
/*    */       return;
/*    */     }
/* 39 */     Maquina dono = Mediador.getInstancia().getContextualizador().getContexto().getConfiguracaoRede().getMaquinaLocal();
/* 40 */     Veiculo vref = Veiculo.getVeiculoReferencial();
/*    */     
/* 42 */     Map<Sensor.Tipo, Driver> drivers = Mediador.getInstancia().getGestor().getGestorSinais().getDrivers();
/* 43 */     if (drivers.containsKey(Sensor.Tipo.RADAR_BUSCA)) {
/* 44 */       DriverVideoRadar dvr = ((DriverExtratorNMEA)drivers.get(Sensor.Tipo.RADAR_BUSCA)).getDriverVideoRadar();
/* 45 */       List<RadarBusca> listaRadares = (List<RadarBusca>)((List)vref.getMapaSensores().get(Sensor.Tipo.RADAR_BUSCA)).stream().map(RadarBusca.class::cast).collect(Collectors.toList());
/*    */       
/* 47 */       for (RadarBusca radarBusca : listaRadares) {
/* 48 */         String arquivo; if (PerfilUsuario.getPerfilUsuarioAtual() instanceof ipqm.gsd.componentes.nucleo.logon.perfis.AlunoSimNav) {
/* 49 */           this.simuladorVideoRadar = new SimuladorVideoBrutoFX(dono, vref, dvr, SimuladorVideoBruto.AlcanceMN.SESSENTA_E_QUATRO);
/*    */         } else {
/* 51 */           this
/* 52 */             .simuladorVideoRadar = new SimuladorVideoBrutoFX(dono, vref, dvr, SimuladorVideoBruto.AlcanceMN.SESSENTA_E_QUATRO, (int)ConversorUnidades.sprParaRpm(radarBusca.getRotacao()), radarBusca.getId());
/*    */         } 
/* 54 */         String radar = Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.RADAR);
/*    */         
/* 56 */         switch (radar) {
/*    */           case "NAO":
/* 58 */             this.simuladorVideoRadar.setVideoSimulado(false);
/*    */             break;
/*    */           case "SIMULAÇAO":
/* 61 */             this.simuladorVideoRadar.setVideoSimulado(true);
/*    */             break;
/*    */           case "ARQUIVO":
/* 64 */             this.simuladorVideoRadar.setVideoSimulado(false);
/*    */             
/* 66 */             arquivo = Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.ARQUIVO_GRAVACAO_RADAR);
/*    */             
/* 68 */             this.simuladorVideoRadar.setNomeArquivoVideo(Diretorios.getDiretorioPadrao(Diretorios.Diretorio.GRAVACAO_RADAR) + arquivo);
/*    */             break;
/*    */         } 
/* 71 */         MAPA_SIMULADORES.put(Integer.valueOf(radarBusca.getId()), this.simuladorVideoRadar);
/* 72 */         if (PerfilUsuario.getPerfilUsuarioAtual().isAmbienteSimulado()) {
/* 73 */           this.simuladorVideoRadar.setVideoSimulado(true);
/*    */         }
/*    */         
/* 76 */         if (this.simuladorVideoRadar.isVideoSimulado()) {
/* 77 */           this.simuladorVideoRadar.simular();
/* 78 */           this.simuladorVideoRadar.iniciar();
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */   
/*    */   public SimuladorVideoBrutoFX getSimuladorVideoRadar() {
/* 85 */     if (this.simuladorVideoRadar == null) {
/* 86 */       simularVideoRadar();
/*    */     }
/* 88 */     return this.simuladorVideoRadar;
/*    */   }
/*    */   
/*    */   public void setSimuladorVideoRadar(SimuladorVideoBrutoFX simuladorVideoRadar) {
/* 92 */     this.simuladorVideoRadar = simuladorVideoRadar;
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/gestao/GestorSimulacaoFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */