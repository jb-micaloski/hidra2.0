/*     */ package ipqm.gsd.hidra.ihm.controle;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.componentes.dominio.entidades.Veiculo;
/*     */ import ipqm.gsd.componentes.nucleo.Ambiente;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*     */ import ipqm.gsd.componentes.nucleo.ThreadPool;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.serializacao.Serializador;
/*     */ import ipqm.gsd.hidra.ihm.camadas.filtros.FiltroCartaNautica;
/*     */ import ipqm.gsd.hidra.ihm.camadas.filtros.FiltroMapaOnline;
/*     */ import ipqm.gsd.hidra.ihm.camadas.filtros.FiltroObjetoTatico;
/*     */ import ipqm.gsd.hidra.ihm.camadas.filtros.FiltroVideoRadar;
/*     */ import ipqm.gsd.smaps.view.vector.ENCParameters;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.FileWriter;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PersistirConfigIHM
/*     */   extends ObjetoDOMINIO
/*     */ {
/*  39 */   private List<Object> listaObjetosPersistir = new ArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PersistirConfigIHM(FiltroCartaNautica fcn, FiltroMapaOnline mapaAlternativo, FiltroMapaOnline mapaGoogleEarth, FiltroVideoRadar fvr, FiltroObjetoTatico fot, Aplicacao.ModoOperacao modoOperacao, Double escalaMN, ENCParameters encParameters, CoordenadaGeografica coordenadaGeorafica) {
/*  45 */     this.listaObjetosPersistir.clear();
/*  46 */     this.listaObjetosPersistir.add(fcn);
/*  47 */     this.listaObjetosPersistir.add(mapaAlternativo);
/*  48 */     this.listaObjetosPersistir.add(mapaGoogleEarth);
/*  49 */     this.listaObjetosPersistir.add(fvr);
/*  50 */     this.listaObjetosPersistir.add(fot);
/*  51 */     this.listaObjetosPersistir.add(modoOperacao);
/*  52 */     this.listaObjetosPersistir.add(escalaMN);
/*  53 */     this.listaObjetosPersistir.add(encParameters);
/*  54 */     this.listaObjetosPersistir.add(coordenadaGeorafica);
/*     */     
/*  56 */     inicializaThread();
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
/*     */   public static boolean carregarConfig() {
/* 114 */     boolean carregar = false;
/* 115 */     if (Mediador.getInstancia().getPerfilUsuario().isRecuperarEstado()) {
/* 116 */       File file = new File(Ambiente.getInstance().getPathAplicacao() + "/" + Mediador.getInstancia().getPerfilUsuario().getNome() + ".properties");
/* 117 */       if (file.exists()) {
/* 118 */         carregar = true;
/*     */       }
/*     */     } 
/* 121 */     return carregar;
/*     */   }
/*     */   
/*     */   private void inicializaThread() {
/* 125 */     ThreadPool.agendarExecucaoPeriodica(new VerificaDistanciaVR(), 60L, TimeUnit.SECONDS, "Persistir configuração da IHM");
/*     */   }
/*     */   
/*     */   public List<Object> getListaObjetosPersistir() {
/* 129 */     return this.listaObjetosPersistir;
/*     */   }
/*     */   
/*     */   public void setListaObjetosPersistir(List<Object> listaObjetosPersistir) {
/* 133 */     this.listaObjetosPersistir = listaObjetosPersistir;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private class VerificaDistanciaVR
/*     */     implements Runnable
/*     */   {
/* 142 */     Veiculo veiculo = Veiculo.getVeiculoReferencial();
/* 143 */     CoordenadaGeografica ultimaPosicao = this.veiculo.getPosicao().getCoordenadaGeografica();
/*     */ 
/*     */     
/*     */     public void run() {
/* 147 */       CoordenadaGeografica posicaoAtual = Veiculo.getVeiculoReferencial().getPosicao().getCoordenadaGeografica();
/* 148 */       double distancia = CoordenadaGeografica.calcularDistancia(this.ultimaPosicao, posicaoAtual);
/*     */       
/* 150 */       if (Double.parseDouble(String.format("%-6.2f", new Object[] { Double.valueOf(distancia) })) == 1.0D) {
/* 151 */         this.ultimaPosicao = posicaoAtual;
/*     */       }
/*     */     }
/*     */     
/*     */     private VerificaDistanciaVR() {}
/*     */   }
/*     */   
/*     */   public static void persistirConfigIHM(byte[] dados) throws IOException {
/* 159 */     File file = new File(Ambiente.getInstance().getPathAplicacao() + "/" + Mediador.getInstancia().getPerfilUsuario().getNome() + ".properties");
/* 160 */     if (file.exists()) {
/* 161 */       file.delete();
/*     */     } else {
/* 163 */       file.createNewFile();
/*     */     } 
/* 165 */     try(FileOutputStream arquivoGrav = new FileOutputStream(Ambiente.getInstance().getPathAplicacao() + "/" + 
/* 166 */           Mediador.getInstancia().getPerfilUsuario().getNome() + ".properties"); 
/* 167 */         ObjectOutputStream objGravar = new ObjectOutputStream(arquivoGrav)) {
/* 168 */       objGravar.writeObject(dados);
/* 169 */       objGravar.flush();
/* 170 */       arquivoGrav.flush();
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
/*     */ 
/*     */   
/*     */   public static List<Object> recuperarAlteracoes() {
/* 185 */     List<Object> listaObjs = new ArrayList();
/*     */     
/* 187 */     try(FileInputStream arquivoLeitura = new FileInputStream(Ambiente.getInstance().getPathAplicacao() + "/" + 
/* 188 */           Mediador.getInstancia().getPerfilUsuario().getNome() + ".properties"); 
/*     */         
/* 190 */         ObjectInputStream objLeitura = new ObjectInputStream(arquivoLeitura)) {
/* 191 */       byte[] dadosRecebidos = (byte[])objLeitura.readObject();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 201 */       Serializador serializador = Mediador.getInstancia().getContextualizador().getContexto().getSerializador();
/*     */       
/* 203 */       PersistirConfigIHM persistirConfig = (PersistirConfigIHM)serializador.deserializaSemClassIdentifier(dadosRecebidos, PersistirConfigIHM.class);
/* 204 */       listaObjs = persistirConfig.getListaObjetosPersistir();
/* 205 */     } catch (IOException|ClassNotFoundException e) {
/* 206 */       Log.gravarLogExcecao("Não foi possível recuperar configurações da IHM. Arquivo não encontrado!", PersistirConfigIHM.class, e);
/*     */     } 
/* 208 */     return listaObjs;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static void WriteFile(String json, String caminho) throws IOException {
/* 214 */     File file = new File(Ambiente.getInstance().getPathAplicacao() + "/" + Mediador.getInstancia().getPerfilUsuario().getNome() + ".properties");
/* 215 */     if (!file.exists()) {
/* 216 */       file.createNewFile();
/* 217 */       try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
/* 218 */         bw.write(json);
/*     */       } 
/*     */     } else {
/* 221 */       try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
/* 222 */         bw.newLine();
/* 223 */         bw.write(json);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static List<String> ReadFile(String caminho) {
/* 230 */     List<String> linhas = new ArrayList<>();
/*     */     
/* 232 */     File file = new File(Ambiente.getInstance().getPathAplicacao() + "/" + Mediador.getInstancia().getPerfilUsuario().getNome() + ".properties");
/* 233 */     Path arquivo = Paths.get(caminho, new String[0]);
/* 234 */     if (file.exists()) {
/*     */       try {
/* 236 */         linhas = Files.readAllLines(arquivo, Charset.defaultCharset());
/*     */       }
/* 238 */       catch (IOException ex) {
/* 239 */         Log.gravarLogExcecao("Erro ao ler arquivo de configurações", PersistirConfigIHM.class, ex);
/*     */       } 
/*     */     }
/* 242 */     return linhas;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/controle/PersistirConfigIHM.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */