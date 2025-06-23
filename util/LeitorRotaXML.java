/*     */ package ipqm.gsd.hidra.ihm.util;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.navegacao.Pernada;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.Rota;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.RotaCondicaoInicial;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.WayPoint;
/*     */ import ipqm.gsd.componentes.dominio.teatro_operacao.CondicaoInicial;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import javax.xml.parsers.DocumentBuilder;
/*     */ import javax.xml.parsers.DocumentBuilderFactory;
/*     */ import org.w3c.dom.Document;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
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
/*     */ public class LeitorRotaXML
/*     */ {
/*     */   private static WayPoint wayPoint;
/*     */   
/*     */   public static void lerArquivoRotaXML(String caminho) {
/*     */     try {
/*  34 */       File fXmlFile = new File(caminho);
/*  35 */       DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
/*  36 */       DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
/*  37 */       Document doc = dBuilder.parse(fXmlFile);
/*     */       
/*  39 */       doc.getDocumentElement().normalize();
/*     */       
/*  41 */       NodeList nList = doc.getElementsByTagName("WayPoint");
/*  42 */       NodeList nodeListRota = doc.getElementsByTagName("TSH_Route");
/*  43 */       Node nodeRota = nodeListRota.item(0);
/*  44 */       Element elementRota = (Element)nodeRota;
/*  45 */       String nomeRota = elementRota.getAttribute("RtName");
/*     */       
/*  47 */       Rota rota = new Rota();
/*  48 */       rota.setListaPernadas(new ArrayList());
/*  49 */       int num = 1;
/*     */       
/*  51 */       for (int i = 0; i < nList.getLength() - 1; i++) {
/*     */         
/*  53 */         Node nNode = nList.item(i);
/*     */         
/*  55 */         if (nNode.getNodeType() == 1) {
/*     */           Pernada pernada;
/*  57 */           Element wp = (Element)nNode;
/*     */ 
/*     */ 
/*     */           
/*  61 */           double latWp = Double.valueOf(wp.getAttribute("Lat")).doubleValue() / 60.0D;
/*  62 */           double longWp = Double.valueOf(wp.getAttribute("Lon")).doubleValue() / 60.0D;
/*     */           
/*  64 */           if (Math.abs(latWp) > 360.0D) {
/*  65 */             latWp %= 360.0D;
/*     */           }
/*     */           
/*  68 */           if (Math.abs(longWp) > 360.0D) {
/*  69 */             longWp %= 360.0D;
/*     */           }
/*     */           
/*  72 */           wayPoint = WayPoint.fabricar(wp.getAttribute("WPName"), latWp, longWp);
/*     */ 
/*     */ 
/*     */           
/*  76 */           if (i == 0) {
/*  77 */             pernada = new Pernada(num++, wayPoint, null, 0.0D, 0.0D, 0.0D);
/*     */           } else {
/*  79 */             pernada = new Pernada(num++, wayPoint, Pernada.TipoPernada.RHUMB_LINE, 250.0D, 250.0D, 15.0D);
/*     */           } 
/*     */ 
/*     */           
/*  83 */           rota.getListaPernadas().add(pernada);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/*  89 */       rota.setNome(nomeRota);
/*  90 */       rota.setOrigem("origem");
/*  91 */       rota.setDestino("destino");
/*  92 */       rota.setStatus("N");
/*     */       
/*  94 */       RotaCondicaoInicial rci = new RotaCondicaoInicial();
/*  95 */       rci.setCondicaoInicial(CondicaoInicial.getCondicaoInicialLocal());
/*  96 */       rci.setRota(rota);
/*  97 */       rota.getCondicoesIniciais().add(rci);
/*     */       
/*  99 */       rota.salvarBD(true);
/*     */     }
/* 101 */     catch (Exception ex) {
/*     */       
/* 103 */       Log.gravarLogExcecao("Erro no leitor de rotas. ", LeitorRotaXML.class, ex);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/util/LeitorRotaXML.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */