/*    */ package ipqm.gsd.hidra.ihm.util;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.ThreadPool;
/*    */ import ipqm.gsd.componentes.nucleo.configuracao.Diretorios;
/*    */ import ipqm.gsd.componentes.nucleo.driver.extrator_nmea.CodificadorVideoRadar;
/*    */ import ipqm.gsd.componentes.nucleo.log.Log;
/*    */ import ipqm.gsd.radar.model.RadarBeam;
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GravadorDisparos
/*    */ {
/*    */   public static void gravarDisparos(List<List<RadarBeam>> disparos, String nomeArquivo, int numDisparosBloco) {
/* 33 */     ThreadPool.executar(() -> {
/*    */           long tempoGravacao = System.currentTimeMillis();
/*    */           String path = Diretorios.getDiretorioPadrao(Diretorios.Diretorio.GRAVACAO_RADAR);
/*    */           File file = new File(path + nomeArquivo);
/*    */           try (FileOutputStream fos = new FileOutputStream(file)) {
/*    */             for (List<RadarBeam> disparo : (Iterable<List<RadarBeam>>)disparos) {
/*    */               int i;
/*    */               for (i = 0; i < disparo.size(); i += numDisparosBloco) {
/*    */                 List<RadarBeam> disparosAtuais = disparo.subList(i, i + numDisparosBloco);
/*    */                 byte[] dados = CodificadorVideoRadar.codificar(disparosAtuais);
/*    */                 fos.write(dados);
/*    */                 fos.flush();
/*    */               } 
/*    */             } 
/*    */             tempoGravacao = System.currentTimeMillis() - tempoGravacao;
/*    */             Log.gravarLogInstrucao(String.format("Disparos gravados! %d voltas radar gravadas no arquivo %s. Tempo gasto para a gravação : %dms.", new Object[] { Integer.valueOf(disparos.size()), nomeArquivo, Long.valueOf(tempoGravacao) }), GravadorDisparos.class);
/* 49 */           } catch (IOException ex) {
/*    */             Log.gravarLogExcecao("Erro ao gravar disparos radar em arquivo", GravadorDisparos.class, ex);
/*    */           } 
/*    */         });
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/util/GravadorDisparos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */