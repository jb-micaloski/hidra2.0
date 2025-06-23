/*    */ package ipqm.gsd.hidra.ihm.gestao;
/*    */ 
/*    */ import ipqm.gsd.componentes.dominio.driver.extrator_nmea.DecodificadorVideoRadar;
/*    */ import ipqm.gsd.componentes.dominio.gestao.GestorSinaisTatico;
/*    */ import ipqm.gsd.componentes.nucleo.driver.Sensor;
/*    */ import ipqm.gsd.componentes.nucleo.driver.Sinal;
/*    */ import ipqm.gsd.radar.model.RadarBeam;
/*    */ import java.util.Deque;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.concurrent.ConcurrentLinkedDeque;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GestorSinaisFX
/*    */   extends GestorSinaisTatico
/*    */ {
/* 24 */   private final Deque<OuvinteVideoRadar> ouvintes = new ConcurrentLinkedDeque<>();
/*    */ 
/*    */   
/*    */   public void notificar(Sinal sinal) {
/* 28 */     if (sinal.getOrigem() == Sensor.Tipo.VIDEO_RADAR) {
/* 29 */       if (!this.ouvintes.isEmpty()) {
/* 30 */         List<RadarBeam> disparos = DecodificadorVideoRadar.decodificar(sinal.getDados(), 10);
/* 31 */         for (Iterator<OuvinteVideoRadar> iterator = this.ouvintes.iterator(); iterator.hasNext();) {
/* 32 */           ((OuvinteVideoRadar)iterator.next()).atualizarCamadaRadar(disparos);
/*    */         }
/*    */       } 
/*    */     } else {
/* 36 */       super.notificar(sinal);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void notificar(Sinal sinal, int idSensor) {
/* 42 */     if (sinal.getOrigem() == Sensor.Tipo.VIDEO_RADAR) {
/* 43 */       if (!this.ouvintes.isEmpty()) {
/* 44 */         List<RadarBeam> disparos = DecodificadorVideoRadar.decodificar(sinal.getDados(), 10);
/* 45 */         this.ouvintes.stream().filter(ouvinte -> (ouvinte.getIdRadarSelecionado() == idSensor)).forEachOrdered(ouvinte -> ouvinte.atualizarCamadaRadar(disparos));
/*    */       }
/*    */     
/*    */     } else {
/*    */       
/* 50 */       super.notificar(sinal);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void adicionaOuvinteVideoRadar(OuvinteVideoRadar ouvinte) {
/* 55 */     this.ouvintes.add(ouvinte);
/*    */   }
/*    */   
/*    */   public void removeOuvinteVideoRadar(OuvinteVideoRadar ouvinte) {
/* 59 */     this.ouvintes.remove(ouvinte);
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/gestao/GestorSinaisFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */