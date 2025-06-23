/*     */ package ipqm.gsd.hidra.ihm.widgets.fonia;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.Ambiente;
/*     */ import ipqm.gsd.componentes.nucleo.Maquina;
/*     */ import ipqm.gsd.componentes.nucleo.NucleoAmbiente;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArquivoConfig
/*     */ {
/*     */   private String TipoEstacao;
/*     */   private String NomeEstacao;
/*     */   private String PortaEscutaGeral;
/*     */   private String PortaInicialBlocoLinExternas;
/*     */   private String PortaInicialBlocoLinInternas;
/*  23 */   private final List<String> ListaLinhasExternas = new ArrayList<>();
/*  24 */   private final List<String> ListaLinhasInternas = new ArrayList<>();
/*  25 */   private final List<String> ListaLinhasInternasGrupSemAltoF = new ArrayList<>();
/*  26 */   private final List<String> ListaLinhasInternasGrupComAltoF = new ArrayList<>();
/*  27 */   private final List<Boolean> ListaLinhasInternasConfiguradas = new ArrayList<>();
/*  28 */   private final List<Boolean> ListaLinhasExternasConfiguradas = new ArrayList<>();
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean CarregarArquivoDefault() {
/*  33 */     boolean Ler_Arquivo_Config_Cub = true;
/*  34 */     boolean Lendo_dados = true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  43 */     File arq = new File("/opt/hidra/Default.txt");
/*     */ 
/*     */     
/*  46 */     if (arq.exists())
/*     */     {
/*  48 */       try(FileReader reader = new FileReader(arq.getAbsoluteFile()); 
/*  49 */           BufferedReader leitor = new BufferedReader(reader)) {
/*     */ 
/*     */         
/*  52 */         System.out.println("-------------------------------------------- READER");
/*     */ 
/*     */         
/*  55 */         while (Ler_Arquivo_Config_Cub) {
/*  56 */           String DadoLido, linha = leitor.readLine();
/*     */           
/*  58 */           switch (linha) {
/*     */             
/*     */             case "TIPO DESTA ESTACAO (1 ->INSTRUTOR     2 ->ALUNO):":
/*  61 */               DadoLido = leitor.readLine();
/*  62 */               System.out.printf("Nome da Estação = %s\n", new Object[] { DadoLido });
/*     */ 
/*     */             
/*     */             case "NOME DESTA ESTACAO:":
/*  66 */               while (Lendo_dados) {
/*  67 */                 DadoLido = leitor.readLine();
/*  68 */                 System.out.printf("Nome da Estação = %s\n", new Object[] { DadoLido });
/*  69 */                 if (DadoLido.equals("")) {
/*     */                   break;
/*     */                 }
/*     */                 
/*  73 */                 this.NomeEstacao = DadoLido;
/*     */               } 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             case "LINHAS EXTERNAS":
/*  80 */               while (Lendo_dados) {
/*  81 */                 DadoLido = leitor.readLine();
/*  82 */                 System.out.printf("FreqExt = %s\n", new Object[] { DadoLido });
/*  83 */                 if (DadoLido.equals("")) {
/*     */                   break;
/*     */                 }
/*     */                 
/*  87 */                 this.ListaLinhasExternas.add(DadoLido);
/*     */               } 
/*     */ 
/*     */             
/*     */             case "LINHAS INTERNAS PONTO-A-PONTO:":
/*  92 */               while (Lendo_dados) {
/*  93 */                 DadoLido = leitor.readLine();
/*  94 */                 System.out.printf("FreqInt = %s\n", new Object[] { DadoLido });
/*  95 */                 if (DadoLido.equals("")) {
/*     */                   break;
/*     */                 }
/*     */                 
/*  99 */                 this.ListaLinhasInternas.add(DadoLido);
/*     */               } 
/*     */ 
/*     */             
/*     */             case "LINHAS INTERNAS GRUPADAS SEM ALTO-FALANTE:":
/* 104 */               while (Lendo_dados) {
/* 105 */                 DadoLido = leitor.readLine();
/* 106 */                 if (DadoLido.equals("")) {
/* 107 */                   Ler_Arquivo_Config_Cub = false;
/*     */                   break;
/*     */                 } 
/* 110 */                 this.ListaLinhasInternasGrupComAltoF.add(DadoLido);
/*     */               } 
/*     */ 
/*     */             
/*     */             case "LINHAS INTERNAS GRUPADAS COM ALTO-FALANTE:":
/* 115 */               while (Lendo_dados) {
/* 116 */                 DadoLido = leitor.readLine();
/* 117 */                 if (DadoLido.equals("")) {
/* 118 */                   Ler_Arquivo_Config_Cub = false;
/*     */                   break;
/*     */                 } 
/* 121 */                 this.ListaLinhasInternasGrupComAltoF.add(DadoLido);
/*     */               } 
/*     */           } 
/*     */ 
/*     */ 
/*     */         
/*     */         } 
/* 128 */         this.TipoEstacao = Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.IP_SERVIDOR_FONIA);
/*     */         
/* 130 */         this.NomeEstacao = Maquina.getMaquinaLocal().getHostname();
/* 131 */         return true;
/* 132 */       } catch (IOException ex) {
/* 133 */         Log.gravarLogExcecao("Erro ao ler arquivo de config", this, ex);
/* 134 */         return false;
/*     */       } 
/*     */     }
/* 137 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean CarregarArquivoConfigDics() {
/* 143 */     boolean Ler_Arquivo_Config_Geral = true;
/*     */     
/* 145 */     String nomeArq = "C:\\Projetos\\FoniaSSTT3\\IHM_DIX_VER2\\src\\ihm_dix_ver2\\IHM_DIX_VER2\\ConfigDICS.txt";
/*     */ 
/*     */ 
/*     */     
/* 149 */     File arq = new File(nomeArq);
/*     */ 
/*     */     
/* 152 */     if (arq.exists())
/*     */     {
/*     */       
/* 155 */       try(FileReader reader = new FileReader(nomeArq); 
/*     */           
/* 157 */           BufferedReader leitor = new BufferedReader(reader)) {
/*     */         
/* 159 */         while (Ler_Arquivo_Config_Geral) {
/* 160 */           String linha = leitor.readLine();
/*     */           
/* 162 */           switch (linha) {
/*     */             
/*     */             case "PORTA ESCUTA GERAL:":
/* 165 */               this.PortaEscutaGeral = leitor.readLine();
/* 166 */               System.out.printf("Porta da Escuta Geral = %s\n", new Object[] { this.PortaEscutaGeral });
/*     */ 
/*     */             
/*     */             case "INICIO BLOCO DE PORTAS LINHAS EXTERNAS:":
/* 170 */               this.PortaInicialBlocoLinExternas = leitor.readLine();
/* 171 */               System.out.printf("Inic Bloco de Portas Linhas Externas: = %s\n", new Object[] { this.PortaInicialBlocoLinExternas });
/*     */ 
/*     */             
/*     */             case "INICIO BLOCO DE PORTAS LINHAS INTERNAS:":
/* 175 */               this.PortaInicialBlocoLinInternas = leitor.readLine();
/* 176 */               System.out.printf("Inic Bloco de Portas Linhas Externas = %s\n", new Object[] { this.PortaInicialBlocoLinInternas });
/*     */           } 
/*     */         
/*     */         } 
/* 180 */         return true;
/* 181 */       } catch (IOException ex) {
/* 182 */         Log.gravarLogExcecao("Erro ao ler arquivo de config dics", this, ex);
/* 183 */         return false;
/*     */       } 
/*     */     }
/* 186 */     return false;
/*     */   }
/*     */   
/*     */   public String GetTipoEstacao() {
/* 190 */     return this.TipoEstacao;
/*     */   }
/*     */   
/*     */   public String GetNomeEstacao() {
/* 194 */     return this.NomeEstacao;
/*     */   }
/*     */   
/*     */   public List<String> GetListaLinhasExternas() {
/* 198 */     return this.ListaLinhasExternas;
/*     */   }
/*     */   
/*     */   public List<String> GetListaLinhasInternas() {
/* 202 */     return this.ListaLinhasInternas;
/*     */   }
/*     */   
/*     */   public List<String> GetListaLinhasInternasGrupSemAltoF() {
/* 206 */     return this.ListaLinhasInternasGrupSemAltoF;
/*     */   }
/*     */   
/*     */   public List<String> GetListaLinhasInternasGrupComAltoF() {
/* 210 */     return this.ListaLinhasInternasGrupComAltoF;
/*     */   }
/*     */   
/*     */   public String GetPortaEscutaGeral() {
/* 214 */     return this.PortaEscutaGeral;
/*     */   }
/*     */   
/*     */   public String GetPortaInicBlocoLinExternas() {
/* 218 */     return this.PortaInicialBlocoLinExternas;
/*     */   }
/*     */   
/*     */   public String GetPortaInicBlocoLinInternas() {
/* 222 */     return this.PortaInicialBlocoLinInternas;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/fonia/ArquivoConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */