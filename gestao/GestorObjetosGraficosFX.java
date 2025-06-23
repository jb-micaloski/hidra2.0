/*     */ package ipqm.gsd.hidra.ihm.gestao;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.gestao.GestorObjetosTatico;
/*     */ import ipqm.gsd.componentes.nucleo.IHM;
/*     */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*     */ import ipqm.gsd.componentes.nucleo.alarmes.Alarme;
/*     */ import ipqm.gsd.componentes.nucleo.logon.perfis.PerfilUsuario;
/*     */ import ipqm.gsd.componentes.nucleo.objeto_visual.Camada;
/*     */ import ipqm.gsd.componentes.nucleo.objeto_visual.ObjetoVisual;
/*     */ import ipqm.gsd.componentes.nucleo.objeto_visual.PadraoTextual;
/*     */ import ipqm.gsd.hidra.ihm.objetos_visuais.ObjetoVisualVazio;
/*     */ import ipqm.gsd.hidra.ihm.objetos_visuais.objetos_graficos.ObjetoGrafico;
/*     */ import ipqm.gsd.hidra.ihm.objetos_visuais.objetos_tabulares.ObjetoTabular;
/*     */ import ipqm.gsd.hidra.ihm.objetos_visuais.objetos_textuais.ObjetoTextual;
/*     */ import java.util.Iterator;
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
/*     */ public class GestorObjetosGraficosFX
/*     */   extends GestorObjetosTatico
/*     */ {
/*  30 */   private static final String CLASSE_OBJETO_GRAFICO = ObjetoGrafico.class.getCanonicalName();
/*  31 */   private static final String CLASSE_OBJETO_TEXTUAL = ObjetoTextual.class.getCanonicalName();
/*  32 */   private static final String CLASSE_OBJETO_TABULAR = ObjetoTabular.class.getCanonicalName();
/*     */   private static String CLASSE_PADRAO_TEXTUAL;
/*     */   private static final String SUFIXO_OBJETO_GRAFICO_ALTERNATIVO = "Alternativo";
/*     */   
/*     */   public void iniciar(IHM ihm) {
/*  37 */     super.iniciar(ihm);
/*     */ 
/*     */     
/*  40 */     PadraoTextual padraoTextual = PerfilUsuario.getPerfilUsuarioAtual().getPadraoTextual();
/*  41 */     if (padraoTextual != null) {
/*  42 */       CLASSE_PADRAO_TEXTUAL = "ipqm.gsd.hidra.ihm.objetos_visuais.objetos_textuais.padrao_texto." + padraoTextual.getBase() + ".ObjetoTextual";
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public ObjetoDOMINIO gerir(ObjetoDOMINIO objetoDOMINIO) {
/*  48 */     criarObjetosVisuais(objetoDOMINIO);
/*  49 */     Iterator<Alarme> it = objetoDOMINIO.getAlarmes().iterator();
/*  50 */     while (it.hasNext()) {
/*  51 */       criarObjetosVisuais((ObjetoDOMINIO)it.next());
/*     */     }
/*  53 */     return super.gerir(objetoDOMINIO);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void desenharObjeto(ObjetoDOMINIO objeto, Camada camada) {
/*  64 */     ObjetoGrafico objetoGrafico = (ObjetoGrafico)objeto.getObjetosVisuais().get(ObjetoVisual.Tipo.GRAFICO);
/*  65 */     if (objetoGrafico != null && objetoGrafico.efetuarPoliticasDesenho(camada.getFiltro())) {
/*  66 */       objetoGrafico.desenhar(camada);
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
/*     */   public void desenharObjetoAlternativo(ObjetoDOMINIO objeto, Camada camada) {
/*  79 */     if (objeto.getObjetosVisuais().containsKey(ObjetoVisual.Tipo.GRAFICO_ALTERNATIVO)) {
/*  80 */       ObjetoVisual objetoVisual = (ObjetoVisual)objeto.getObjetosVisuais().get(ObjetoVisual.Tipo.GRAFICO_ALTERNATIVO);
/*  81 */       if (objetoVisual != null && objetoVisual.efetuarPoliticasDesenho(camada.getFiltro())) {
/*  82 */         objetoVisual.desenhar(camada);
/*     */       }
/*     */     } else {
/*  85 */       desenharObjeto(objeto, camada);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void criarObjetosVisuais(ObjetoDOMINIO objetoDOMINIO) {
/* 104 */     if (objetoDOMINIO != null) {
/*     */       
/* 106 */       if (!objetoDOMINIO.getObjetosVisuais().containsKey(ObjetoVisual.Tipo.GRAFICO)) {
/* 107 */         ObjetoVisual objetoVisual = criaObjetoVisual(CLASSE_OBJETO_GRAFICO, objetoDOMINIO);
/* 108 */         objetoDOMINIO.getObjetosVisuais().put(ObjetoVisual.Tipo.GRAFICO, (objetoVisual == null) ? new ObjetoVisualVazio() : objetoVisual);
/*     */       } 
/*     */ 
/*     */       
/* 112 */       if (!objetoDOMINIO.getObjetosVisuais().containsKey(ObjetoVisual.Tipo.GRAFICO_ALTERNATIVO)) {
/* 113 */         ObjetoVisual objetoVisual = criaObjetoVisual(CLASSE_OBJETO_GRAFICO, "Alternativo", objetoDOMINIO);
/* 114 */         objetoDOMINIO.getObjetosVisuais().put(ObjetoVisual.Tipo.GRAFICO_ALTERNATIVO, (objetoVisual == null) ? new ObjetoVisualVazio() : objetoVisual);
/*     */       } 
/*     */ 
/*     */       
/* 118 */       if (!objetoDOMINIO.getObjetosVisuais().containsKey(ObjetoVisual.Tipo.TEXTUAL)) {
/* 119 */         ObjetoVisual objetoVisual = null;
/* 120 */         if (CLASSE_PADRAO_TEXTUAL != null) {
/* 121 */           objetoVisual = criaObjetoVisual(CLASSE_PADRAO_TEXTUAL, objetoDOMINIO);
/*     */         }
/* 123 */         if (objetoVisual == null) {
/* 124 */           objetoVisual = criaObjetoVisual(CLASSE_OBJETO_TEXTUAL, objetoDOMINIO);
/*     */         }
/* 126 */         objetoDOMINIO.getObjetosVisuais().put(ObjetoVisual.Tipo.TEXTUAL, (objetoVisual == null) ? new ObjetoVisualVazio() : objetoVisual);
/*     */       } 
/*     */ 
/*     */       
/* 130 */       if (!objetoDOMINIO.getObjetosVisuais().containsKey(ObjetoVisual.Tipo.TABULAR)) {
/* 131 */         ObjetoVisual objetoVisual = criaObjetoVisual(CLASSE_OBJETO_TABULAR, objetoDOMINIO);
/* 132 */         objetoDOMINIO.getObjetosVisuais().put(ObjetoVisual.Tipo.TABULAR, (objetoVisual == null) ? new ObjetoVisualVazio() : objetoVisual);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private ObjetoVisual criaObjetoVisual(String prefixoClasse, ObjetoDOMINIO objetoDOMINIO) {
/* 139 */     return criaObjetoVisual(prefixoClasse, "", objetoDOMINIO);
/*     */   }
/*     */   
/*     */   private ObjetoVisual criaObjetoVisual(String prefixoClasse, String sufixoClasse, ObjetoDOMINIO objetoDOMINIO) {
/* 143 */     ObjetoVisual objetoVisual = null;
/*     */     
/* 145 */     Class classeDesenho = objetoDOMINIO.classeReferenciaObjetoVisual();
/*     */     
/*     */     try {
/* 148 */       Class<?> classeTextual = Class.forName(prefixoClasse + classeDesenho.getSimpleName() + sufixoClasse);
/* 149 */       objetoVisual = (ObjetoVisual)classeTextual.newInstance();
/* 150 */       objetoVisual.setObjetoAssociado(objetoDOMINIO);
/* 151 */     } catch (ClassNotFoundException|InstantiationException|IllegalAccessException classNotFoundException) {}
/*     */ 
/*     */ 
/*     */     
/* 155 */     return objetoVisual;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/gestao/GestorObjetosGraficosFX.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */