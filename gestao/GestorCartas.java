/*     */ package ipqm.gsd.hidra.ihm.gestao;
/*     */ 
/*     */ import com.vividsolutions.jts.geom.Geometry;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.Posicao;
/*     */ import ipqm.gsd.componentes.dominio.contexto.ContextoTatico;
/*     */ import ipqm.gsd.componentes.nucleo.Ambiente;
/*     */ import ipqm.gsd.componentes.nucleo.IHM;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.NucleoAmbiente;
/*     */ import ipqm.gsd.componentes.nucleo.VariaveisEstado;
/*     */ import ipqm.gsd.componentes.nucleo.configuracao.Diretorios;
/*     */ import ipqm.gsd.componentes.nucleo.contexto.GravarEstado;
/*     */ import ipqm.gsd.componentes.nucleo.gestao.GestorCartasGenerico;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.logon.perfis.PerfilUsuario;
/*     */ import ipqm.gsd.componentes.nucleo.notificador.Notificador;
/*     */ import ipqm.gsd.smaps.model.charts.ChartInfo;
/*     */ import ipqm.gsd.smaps.model.charts.ChartsDB;
/*     */ import ipqm.gsd.smaps.model.charts.DAO;
/*     */ import ipqm.gsd.smaps.model.charts.PickFeature;
/*     */ import ipqm.gsd.smaps.model.charts.S57Layer;
/*     */ import ipqm.gsd.smaps.model.charts.SENCDAO;
/*     */ import ipqm.gsd.smaps.model.charts.SENCIndex;
/*     */ import ipqm.gsd.smaps.model.conversion.UserPermit;
/*     */ import ipqm.gsd.smaps.model.coord.BBox;
/*     */ import ipqm.gsd.smaps.model.coord.GeoCoord;
/*     */ import ipqm.gsd.smaps.util.FTPSync;
/*     */ import ipqm.gsd.smaps.util.MapsLoggerApp;
/*     */ import ipqm.gsd.smaps.util.license.License;
/*     */ import ipqm.gsd.smaps.util.license.LicenseInfo;
/*     */ import ipqm.gsd.smaps.view.MapsParameters;
/*     */ import ipqm.gsd.smaps.view.vector.ENCParameters;
/*     */ import ipqm.gsd.smaps.view.vector.ENCViewer;
/*     */ import ipqm.gsd.smaps.view.vector.RadarMapsViewer;
/*     */ import ipqm.gsd.smaps.view.vector.S52Parameters;
/*     */ import ipqm.gsd.smaps.view.vector.SSTTMapsViewer;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.File;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
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
/*     */ public class GestorCartas
/*     */   implements GestorCartasGenerico, MapsLoggerApp, GravarEstado
/*     */ {
/*     */   private static final String M_ID = "F8";
/*     */   private static final String M_KEY = "74198";
/*     */   private ChartsDB chartsDB;
/*     */   private SSTTMapsViewer ssttMapsViewer;
/*     */   private RadarMapsViewer radarMapsViewer;
/*     */   private ENCViewer encViewer;
/*     */   private ENCParameters parameters;
/*     */   private final String ftpHost;
/*     */   private final String ftpUser;
/*     */   private final String ftpPass;
/*     */   private final int numDiasAtualizar;
/*     */   private final boolean atualizarCartas;
/*     */   private int cacheSize;
/*     */   
/*     */   public GestorCartas(boolean atualizarCartas) {
/*  78 */     this.atualizarCartas = atualizarCartas;
/*  79 */     this.parameters = new ENCParameters();
/*     */     
/*  81 */     this.ftpHost = Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.IP_FTP_CARTAS_NAUTICAS);
/*  82 */     this.ftpUser = Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.USUARIO_FTP_CARTAS_NAUTICAS);
/*  83 */     this.ftpPass = Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.SENHA_FTP_CARTAS_NAUTICAS);
/*  84 */     String nd = Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.NUM_DIAS_ATUALIZAR_CARTAS);
/*  85 */     this.numDiasAtualizar = !nd.equalsIgnoreCase("NAO") ? Integer.parseInt(nd) : Integer.MAX_VALUE;
/*     */     try {
/*  87 */       this.cacheSize = Integer.valueOf(Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.TAMANHO_CACHE_CARTAS)).intValue();
/*  88 */     } catch (NumberFormatException ex) {
/*  89 */       Log.gravarLogExcecao("Erro ao obter tamanho do cache, valor padrão definido", this, ex);
/*  90 */       this.cacheSize = 50;
/*     */     } 
/*  92 */     UserPermit.setManufacturer("F8", "74198");
/*     */   }
/*     */ 
/*     */   
/*     */   public void iniciar(IHM ihm) {
/*  97 */     ihm.notificarCarregamento(0, "Iniciando o Gestor de Cartas");
/*     */     
/*  99 */     if (this.chartsDB == null) {
/* 100 */       this.chartsDB = iniciaChartsDB(ihm);
/*     */       
/* 102 */       if (this.chartsDB != null && !this.ftpHost.equals("NAO") && PerfilUsuario.getPerfilUsuarioAtual().isGerenciarCartas() && (this.atualizarCartas || 
/* 103 */         numDaysLastFTPSync() >= this.numDiasAtualizar)) {
/* 104 */         atualizaCartas(ihm);
/*     */       }
/* 106 */       ihm.notificarCarregamento(-1, "Carregando cartas náuticas");
/* 107 */       preCarregaCartas(this.chartsDB);
/* 108 */       ihm.notificarCarregamento(-1, "");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String getHardwareID() {
/* 114 */     LicenseInfo info = License.getInstance().getLicenseInfo();
/* 115 */     return (info != null) ? info.getLogicalID() : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getUserPermit() {
/* 120 */     String hwID = getHardwareID();
/* 121 */     return (hwID != null) ? UserPermit.make(hwID) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setChartsDB(ChartsDB chartsDB) {
/* 126 */     this.chartsDB = chartsDB;
/*     */   }
/*     */ 
/*     */   
/*     */   public ChartsDB getChartsDB() {
/* 131 */     return this.chartsDB;
/*     */   }
/*     */   
/*     */   private ChartsDB iniciaChartsDB(IHM ihm) {
/* 135 */     ChartsDB charts = null;
/*     */     
/*     */     try {
/* 138 */       ihm.notificarCarregamento(-1, "Iniciando banco de cartas naúticas");
/* 139 */       charts = new ChartsDB((DAO)new SENCDAO(new File(Diretorios.getDiretorioPadrao(Diretorios.Diretorio.CARTAS_NAUTICAS)), getHardwareID()), this.cacheSize);
/* 140 */       ihm.notificarCarregamento(-1, "");
/* 141 */     } catch (Exception ex) {
/* 142 */       Log.gravarLogExcecao("Erro ao acessar o banco de cartas náuticas!", this, ex);
/*     */     } 
/* 144 */     return charts;
/*     */   }
/*     */   
/*     */   private void preCarregaCartas(ChartsDB charts) {
/*     */     try {
/* 149 */       if (((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getTeatroOperacao() != null) {
/*     */         
/* 151 */         Posicao posRef = ((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getTeatroOperacao().getPosicaoReferencial();
/* 152 */         CoordenadaGeografica coord = posRef.getCoordenadaGeografica();
/* 153 */         charts.loadCharts(coord.getLongitude(), coord.getLatitude(), 48.0D);
/*     */       } 
/* 155 */     } catch (Exception ex) {
/* 156 */       Log.gravarLogExcecao("Erro ao carregar as cartas!", this, ex);
/*     */     } 
/*     */   }
/*     */   private void atualizaCartas(IHM ihm) {
/*     */     FTPSync ftpSync;
/* 161 */     if (this.chartsDB == null) {
/* 162 */       Log.gravarLogInstrucao("Não é possível atualizar as cartas náuticas. Cliente não está pronto.", this);
/*     */       return;
/*     */     } 
/* 165 */     ihm.notificarCarregamento(-1, "Atualizando cartas náuticas de ftp://" + this.ftpUser + "@" + this.ftpHost + "...");
/*     */     
/*     */     try {
/* 168 */       ftpSync = new FTPSync(this.ftpHost, this.ftpUser, this.ftpPass, "/", getHardwareID(), (SENCDAO)this.chartsDB.getDAO());
/* 169 */     } catch (Exception ex) {
/* 170 */       Notificador.erro("Atualização de cartas", "Não foi possível acessar o servidor de cartas!");
/* 171 */       Log.gravarLogExcecao("Erro ao acessar o servidor de cartas náuticas!", this, ex);
/*     */       return;
/*     */     } 
/* 174 */     if (ftpSync.isChartsSynched()) {
/* 175 */       Log.gravarLogInstrucao("Todas as cartas náuticas estão atualizadas.", this);
/*     */       
/*     */       return;
/*     */     } 
/* 179 */     List<String> chartsToInstall = ftpSync.getChartsToInstall();
/* 180 */     List<String> chartsToUpdate = ftpSync.getChartsToUpdate();
/* 181 */     SENCIndex remoteIndex = ftpSync.getRemoteIndex();
/* 182 */     int numCartasProcessar = chartsToInstall.size() + chartsToUpdate.size();
/* 183 */     int num = 0;
/*     */ 
/*     */     
/* 186 */     long tempo = System.currentTimeMillis();
/* 187 */     if (chartsToInstall.size() > 0) {
/* 188 */       for (String nomeCarta : chartsToInstall) {
/*     */         try {
/* 190 */           int remoteEdition = remoteIndex.getEditionNumber(nomeCarta);
/* 191 */           int remoteUpdate = remoteIndex.getUpdateNumber(nomeCarta);
/* 192 */           String mensagem = String.format("Instalando carta %S (%d/%d): edição %d.%03d", new Object[] { nomeCarta, 
/*     */                 
/* 194 */                 Integer.valueOf(++num), Integer.valueOf(numCartasProcessar), Integer.valueOf(remoteEdition), Integer.valueOf(remoteUpdate) });
/*     */           
/* 196 */           ihm.notificarCarregamento(-1, mensagem);
/* 197 */           Log.gravarLogInstrucao(mensagem, this);
/* 198 */           ftpSync.processChart(nomeCarta);
/* 199 */           Log.gravarLogInstrucao("Carta " + nomeCarta.toUpperCase() + " instalada com sucesso!", this);
/* 200 */         } catch (Exception ex) {
/* 201 */           Log.gravarLogExcecao("Erro ao instalar carta " + nomeCarta.toUpperCase(), this, ex);
/*     */         } 
/*     */       } 
/*     */     }
/* 205 */     if (chartsToUpdate.size() > 0) {
/* 206 */       for (String nomeCarta : chartsToUpdate) {
/*     */         try {
/* 208 */           ChartInfo infoCliente = this.chartsDB.getChartInfo(nomeCarta);
/* 209 */           int localEdition = infoCliente.getEditionNumber();
/* 210 */           int localUpdate = infoCliente.getUpdateNumber();
/* 211 */           int remoteEdition = remoteIndex.getEditionNumber(nomeCarta);
/* 212 */           int remoteUpdate = remoteIndex.getUpdateNumber(nomeCarta);
/* 213 */           String mensagem = String.format("Atualizando carta %S (%d/%d): edição %d.%03d ==> Edição %d.%03d", new Object[] { nomeCarta, 
/*     */                 
/* 215 */                 Integer.valueOf(++num), Integer.valueOf(numCartasProcessar), 
/* 216 */                 Integer.valueOf(localEdition), Integer.valueOf(localUpdate), Integer.valueOf(remoteEdition), Integer.valueOf(remoteUpdate) });
/*     */           
/* 218 */           ihm.notificarCarregamento(-1, mensagem);
/* 219 */           Log.gravarLogInstrucao(mensagem, this);
/* 220 */           ftpSync.processChart(nomeCarta);
/* 221 */           Log.gravarLogInstrucao("Carta " + nomeCarta.toUpperCase() + " atualizada com sucesso!", this);
/* 222 */         } catch (Exception ex) {
/* 223 */           Log.gravarLogExcecao("Erro ao atualizar carta " + nomeCarta.toUpperCase(), this, ex);
/*     */         } 
/*     */       } 
/*     */     }
/*     */     try {
/* 228 */       ftpSync.dispose();
/* 229 */     } catch (Exception ex) {
/* 230 */       Log.gravarLogExcecao("Erro ao fechar a conexão com o servidor de cartas", this, ex);
/*     */     } 
/* 232 */     int deltaTempo = (int)(System.currentTimeMillis() - tempo) / 1000;
/* 233 */     String msg = chartsToInstall.size() + " cartas instaladas, " + chartsToUpdate.size() + " cartas atualizadas, " + deltaTempo + " s. Recarregando...";
/* 234 */     ihm.notificarCarregamento(-1, msg);
/* 235 */     Log.gravarLogInstrucao(msg, this);
/*     */     
/* 237 */     msg = "Recarregando cartas náuticas";
/* 238 */     Log.gravarLogInstrucao(msg, this);
/*     */     try {
/* 240 */       this.chartsDB.setDAO((DAO)new SENCDAO(new File(Diretorios.getDiretorioPadrao(Diretorios.Diretorio.CARTAS_NAUTICAS)), getHardwareID()));
/* 241 */     } catch (Exception ex) {
/* 242 */       Log.gravarLogExcecao("Erro ao recarregar cartas após atualização", this, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private int numDaysLastFTPSync() {
/* 249 */     File indexFile = new File(Diretorios.getDiretorioPadrao(Diretorios.Diretorio.CARTAS_NAUTICAS) + "/INDEX.TXT");
/* 250 */     if (indexFile.isFile()) {
/* 251 */       long lastModified = indexFile.lastModified();
/* 252 */       if (lastModified > 0L) {
/* 253 */         return (int)((System.currentTimeMillis() - lastModified) / 8.64E7D);
/*     */       }
/*     */     } 
/* 256 */     return Integer.MAX_VALUE;
/*     */   }
/*     */   
/*     */   private SSTTMapsViewer getSSTTMapsViewer() {
/* 260 */     if (this.ssttMapsViewer == null) {
/*     */       try {
/* 262 */         this.ssttMapsViewer = new SSTTMapsViewer(this.chartsDB);
/* 263 */       } catch (Exception ex) {
/* 264 */         Log.gravarLogExcecao("Erro ao iniciar o serviço de cartas!", this, ex);
/*     */       } 
/*     */     }
/* 267 */     return this.ssttMapsViewer;
/*     */   }
/*     */   
/*     */   public ENCViewer getENCViewer() {
/* 271 */     if (this.encViewer == null) {
/*     */       try {
/* 273 */         this.encViewer = new ENCViewer(this.chartsDB);
/* 274 */       } catch (Exception ex) {
/* 275 */         Log.gravarLogExcecao("Erro ao iniciar o serviço de cartas!", this, ex);
/*     */       } 
/*     */     }
/* 278 */     return this.encViewer;
/*     */   }
/*     */   
/*     */   private RadarMapsViewer getRadarMapsViewer() {
/* 282 */     if (this.radarMapsViewer == null) {
/*     */       try {
/* 284 */         this.radarMapsViewer = new RadarMapsViewer(this.chartsDB);
/* 285 */       } catch (Exception ex) {
/* 286 */         Log.gravarLogExcecao("Erro ao iniciar o serviço de cartas!", this, ex);
/*     */       } 
/*     */     }
/* 289 */     return this.radarMapsViewer;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<String> getChartNames() {
/* 294 */     return this.chartsDB.getChartNames();
/*     */   }
/*     */ 
/*     */   
/*     */   public double getChartScale(String chartName) {
/* 299 */     String s = String.valueOf(chartName.charAt(2));
/* 300 */     Double escala = Double.valueOf(s);
/* 301 */     return escala.doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public String buildGeometryText(List<GeoCoord> listCoord) {
/* 306 */     int defalutSRID = 4326;
/* 307 */     String stPolygon = "";
/*     */     
/* 309 */     for (GeoCoord coord : listCoord) {
/* 310 */       stPolygon = stPolygon.concat(String.valueOf(coord.getLongitude()));
/* 311 */       stPolygon = stPolygon.concat(" ");
/* 312 */       stPolygon = stPolygon.concat(String.valueOf(coord.getLatitude()));
/* 313 */       stPolygon = stPolygon.concat(", ");
/*     */     } 
/*     */     
/* 316 */     GeoCoord primeiraCoord = listCoord.get(0);
/* 317 */     stPolygon = stPolygon.concat(String.valueOf(primeiraCoord.getLongitude()));
/* 318 */     stPolygon = stPolygon.concat(" ");
/* 319 */     stPolygon = stPolygon.concat(String.valueOf(primeiraCoord.getLatitude()));
/* 320 */     stPolygon = stPolygon.concat("))'");
/* 321 */     stPolygon = stPolygon.concat(", ");
/*     */     
/* 323 */     return "ST_POLYGONFROMTEXT('POLYGON((" + stPolygon + defalutSRID + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BBox getChartBBox(String chartName) {
/* 329 */     ChartInfo chartInfo = getSSTTMapsViewer().getChartsDB().getChartInfo(chartName);
/* 330 */     return (chartInfo != null) ? chartInfo.getBBox() : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, List<Map<String, Object>>> getLayersData(List<GeoCoord> listaCoord) throws Exception {
/* 335 */     double[] longs = new double[4];
/* 336 */     double[] lats = new double[4];
/* 337 */     for (int i = 0; i < 4; i++) {
/* 338 */       longs[i] = ((GeoCoord)listaCoord.get(i)).getLongitude();
/* 339 */       lats[i] = ((GeoCoord)listaCoord.get(i)).getLatitude();
/*     */     } 
/* 341 */     Geometry geometria = ChartsDB.build4SidedPolygon(longs[0], lats[0], longs[1], lats[1], longs[2], lats[2], longs[3], lats[3]);
/*     */     
/* 343 */     Set<String> layerNames = this.chartsDB.getLayerNames(this.chartsDB.getChartNames(geometria));
/* 344 */     return this.chartsDB.getLayersData(geometria, layerNames);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getDepth(double longitude, double latitude) throws Exception {
/* 349 */     return this.chartsDB.getDepth(longitude, latitude);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage draw(double longitudeMin, double latitudeMin, double longitudeMax, double latitudeMax, int imageWidth, int imageHeight, ENCViewer.OverlayMode overlayMode, MapsParameters parameters, GestorCartasGenerico.ServidorCartas servidor) throws Exception {
/* 356 */     switch (servidor) {
/*     */       case IPQM:
/* 358 */         return getENCViewer().draw(longitudeMin, latitudeMin, longitudeMax, latitudeMax, imageWidth, imageHeight, overlayMode, (ENCParameters)parameters);
/*     */       
/*     */       case SSTT:
/* 361 */         return getSSTTMapsViewer().draw(longitudeMin, latitudeMin, longitudeMax, latitudeMax, imageWidth, imageHeight, null);
/*     */       
/*     */       case RADAR:
/* 364 */         return getRadarMapsViewer().draw(longitudeMin, latitudeMin, longitudeMax, latitudeMax, imageWidth, imageHeight, null);
/*     */     } 
/*     */     
/* 367 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage draw(double longitudeMin, double latitudeMin, double longitudeMax, double latitudeMax, int imageWidth, int imageHeight, MapsParameters parameters, GestorCartasGenerico.ServidorCartas servidor) throws Exception {
/* 373 */     return draw(longitudeMin, latitudeMin, longitudeMax, latitudeMax, imageWidth, imageHeight, ENCViewer.OverlayMode.ALL, parameters, servidor);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ENCParameters getParameters() {
/* 379 */     return this.parameters;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setParameters(ENCParameters parameters) {
/* 384 */     this.parameters = parameters;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ChartInfo getChartInfo(BBox bbox, double longitude, double latitude) {
/* 390 */     ChartInfo chartInfo = null;
/*     */     try {
/* 392 */       List<String> charts = this.chartsDB.getBestCharts(bbox);
/* 393 */       String chartName = this.chartsDB.getChartOnTop(longitude, latitude, charts);
/* 394 */       chartInfo = getSSTTMapsViewer().getChartsDB().getChartInfo(chartName);
/* 395 */       return chartInfo;
/* 396 */     } catch (Exception ex) {
/* 397 */       Log.gravarLogExcecao("Erro ao obter informações da carta", this, ex);
/*     */ 
/*     */       
/* 400 */       return chartInfo;
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getPickInfo(BBox pickBox, BBox visualBox) throws Exception {
/* 405 */     String dadosFeicoes = "";
/*     */     
/* 407 */     List<String> cartas = this.chartsDB.getBestCharts(visualBox);
/* 408 */     String cartaTopo = this.chartsDB.getChartOnTop(pickBox.getCenter().getLongitude(), pickBox.getCenter().getLatitude(), cartas);
/* 409 */     List<PickFeature> pickFeatures = this.chartsDB.getPickInfo(pickBox, cartaTopo);
/* 410 */     if (pickFeatures != null && cartas != null) {
/* 411 */       StringBuilder informacoes = new StringBuilder("");
/*     */       
/* 413 */       GeoCoord centro = pickBox.getCenter();
/* 414 */       StringBuilder chartName = new StringBuilder(cartaTopo.toUpperCase());
/* 415 */       chartName.append(" @ (");
/* 416 */       chartName.append((new CoordenadaGeografica(centro.getLatitude(), centro.getLongitude())).toString());
/* 417 */       chartName.append(")\n");
/*     */       
/* 419 */       informacoes.append(chartName.toString());
/* 420 */       informacoes.append("\n");
/* 421 */       for (PickFeature feature : pickFeatures) {
/* 422 */         informacoes.append(feature.toString()).append("\n");
/*     */       }
/* 424 */       dadosFeicoes = informacoes.toString();
/*     */     } 
/*     */     
/* 427 */     return dadosFeicoes;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<String> getChartNames(BBox bbox) {
/* 433 */     return this.chartsDB.getChartNames(bbox);
/*     */   }
/*     */ 
/*     */   
/*     */   public double getBestIsoline(String charName, double desiredDepth) {
/* 438 */     return this.chartsDB.getBestIsoline(charName, desiredDepth);
/*     */   }
/*     */ 
/*     */   
/*     */   public void log(String string) {
/* 443 */     Log.gravarLogInstrucao("Gestor de cartas: " + string, this);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<String> getChartNames(Geometry area) {
/* 448 */     return this.chartsDB.getChartNames(area);
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<String> getLayerNames(String chartName) throws Exception {
/* 453 */     return this.chartsDB.getLayerNames(chartName);
/*     */   }
/*     */ 
/*     */   
/*     */   public Map<String, List<Map<String, Object>>> getLayersData(Geometry area, Set<String> layerNames) throws Exception {
/* 458 */     return this.chartsDB.getLayersData(area, layerNames);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getChartOnTop(double longitude, double latitude, List<String> charts) {
/* 463 */     return this.chartsDB.getChartOnTop(longitude, latitude, charts);
/*     */   }
/*     */ 
/*     */   
/*     */   public void sortCharts(List<String> charts) {
/* 468 */     ChartsDB.sortCharts(charts);
/*     */   }
/*     */ 
/*     */   
/*     */   public void gravarEstado() {
/* 473 */     if (verificarCondicoesGravacao()) {
/* 474 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.DISPLAY, this.parameters.getDisplayCategory().name());
/* 475 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.PALETTE, this.parameters.getColorPalette().name());
/* 476 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.TWO_SHADES, String.valueOf(this.parameters.isShowTwoShades()));
/* 477 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.SHALLOW_PATTERNS, String.valueOf(this.parameters.isShowShallowPattern()));
/* 478 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.CHARTS_BOUNDARIES, String.valueOf(this.parameters.isShowUnderScaleIndicator()));
/* 479 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.DANGERS_IN_SHALLOW_WATER, String.valueOf(this.parameters.isShowDangersInShallowWaters()));
/* 480 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.TEXTS, String.valueOf(this.parameters.isShowText()));
/* 481 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.USE_SCAMIN, String.valueOf(this.parameters.isUseScaleMinMax()));
/* 482 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.SHALLOW_CONTOUR, String.valueOf(this.parameters.getShallowContour()));
/* 483 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.SAFETY_CONTOUR, String.valueOf(this.parameters.getSafetyContour()));
/* 484 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.SAFETY_DEPTH, String.valueOf(this.parameters.getSafetyDepth()));
/* 485 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.DEEP_CONTOUR, String.valueOf(this.parameters.getDeepContour()));
/*     */       
/* 487 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.SPOT_SOUNDING_TO, String.valueOf(this.parameters.getSpotSoundings()));
/*     */       
/* 489 */       Ambiente.getInstance().persisteVariavelAmbiente("estado.properties");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void obterEstado() {
/* 495 */     if (GravarEstado.verificarRecuperacao()) {
/* 496 */       String display = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.DISPLAY);
/* 497 */       if (!display.equals("")) {
/* 498 */         for (S52Parameters.DisplayCategory item : S52Parameters.DisplayCategory.values()) {
/* 499 */           if (item.toString().equals(display.toUpperCase())) {
/* 500 */             this.parameters.setDisplayCategory(item);
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/* 505 */       String palette = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.PALETTE);
/* 506 */       if (!palette.equals("")) {
/* 507 */         for (S52Parameters.ColorPalette item : S52Parameters.ColorPalette.values()) {
/* 508 */           if (item.toString().equals(palette.toUpperCase())) {
/* 509 */             this.parameters.setColorPalette(item);
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/* 514 */       String twoShades = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.TWO_SHADES);
/* 515 */       if (!twoShades.equals("")) {
/* 516 */         this.parameters.setShowTwoShades(Boolean.valueOf(twoShades).booleanValue());
/*     */       }
/*     */       
/* 519 */       String shallowPattens = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.SHALLOW_PATTERNS);
/* 520 */       if (!shallowPattens.equals("")) {
/* 521 */         this.parameters.setShowShallowPattern(Boolean.valueOf(shallowPattens).booleanValue());
/*     */       }
/*     */       
/* 524 */       String chartsBoundaries = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.CHARTS_BOUNDARIES);
/* 525 */       if (!chartsBoundaries.equals("")) {
/* 526 */         this.parameters.setShowUnderScaleIndicator(Boolean.valueOf(chartsBoundaries).booleanValue());
/*     */       }
/*     */       
/* 529 */       String dangersInShallowWater = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.DANGERS_IN_SHALLOW_WATER);
/* 530 */       if (!dangersInShallowWater.equals("")) {
/* 531 */         this.parameters.setShowDangersInShallowWaters(Boolean.valueOf(dangersInShallowWater).booleanValue());
/*     */       }
/*     */       
/* 534 */       String allDepthContours = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.ALL_DEPTH_CONTOURS);
/* 535 */       if (!allDepthContours.equals("")) {
/* 536 */         if (Boolean.valueOf(allDepthContours).booleanValue()) {
/* 537 */           this.parameters.getWhiteLayers().add(S57Layer.DEPCNT);
/*     */         } else {
/* 539 */           this.parameters.getWhiteLayers().remove(S57Layer.DEPCNT);
/*     */         } 
/*     */       }
/*     */       
/* 543 */       String cablesAndPipelines = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.CABLES_AND_PIPELINES);
/* 544 */       if (!cablesAndPipelines.equals("")) {
/* 545 */         if (Boolean.valueOf(cablesAndPipelines).booleanValue()) {
/* 546 */           this.parameters.getWhiteLayers().add(S57Layer.PIPARE);
/* 547 */           this.parameters.getWhiteLayers().add(S57Layer.PIPOHD);
/* 548 */           this.parameters.getWhiteLayers().add(S57Layer.PIPSOL);
/* 549 */           this.parameters.getWhiteLayers().add(S57Layer.CBLARE);
/* 550 */           this.parameters.getWhiteLayers().add(S57Layer.CBLOHD);
/* 551 */           this.parameters.getWhiteLayers().add(S57Layer.CBLSUB);
/*     */         } else {
/* 553 */           this.parameters.getWhiteLayers().remove(S57Layer.PIPARE);
/* 554 */           this.parameters.getWhiteLayers().remove(S57Layer.PIPOHD);
/* 555 */           this.parameters.getWhiteLayers().remove(S57Layer.PIPSOL);
/* 556 */           this.parameters.getWhiteLayers().remove(S57Layer.CBLARE);
/* 557 */           this.parameters.getWhiteLayers().remove(S57Layer.CBLOHD);
/* 558 */           this.parameters.getWhiteLayers().remove(S57Layer.CBLSUB);
/*     */         } 
/*     */       }
/*     */       
/* 562 */       String sounding = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.SOUNDING);
/* 563 */       if (!sounding.equals("")) {
/* 564 */         if (Boolean.valueOf(sounding).booleanValue()) {
/* 565 */           this.parameters.getWhiteLayers().add(S57Layer.SOUNDG);
/*     */         } else {
/* 567 */           this.parameters.getWhiteLayers().remove(S57Layer.SOUNDG);
/*     */         } 
/*     */       }
/*     */       
/* 571 */       String texts = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.TEXTS);
/* 572 */       if (!texts.equals("")) {
/* 573 */         this.parameters.setShowText(Boolean.valueOf(texts).booleanValue());
/*     */       }
/*     */       
/* 576 */       String useSCAMIN = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.USE_SCAMIN);
/* 577 */       if (!useSCAMIN.equals("")) {
/* 578 */         this.parameters.setUseScaleMinMax(Boolean.valueOf(useSCAMIN).booleanValue());
/*     */       }
/*     */       
/* 581 */       String shallowContour = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.SHALLOW_CONTOUR);
/* 582 */       if (!shallowContour.equals("")) {
/* 583 */         this.parameters.setShallowContour(Double.parseDouble(shallowContour));
/*     */       }
/*     */       
/* 586 */       String safetyContour = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.SAFETY_CONTOUR);
/* 587 */       if (!safetyContour.equals("")) {
/* 588 */         this.parameters.setSafetyContour(Double.parseDouble(safetyContour));
/*     */       }
/*     */       
/* 591 */       String safetyDepth = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.SAFETY_DEPTH);
/* 592 */       if (!safetyDepth.equals("")) {
/* 593 */         this.parameters.setSafetyDepth(Double.parseDouble(safetyDepth));
/*     */       }
/*     */       
/* 596 */       String deepContour = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.DEEP_CONTOUR);
/* 597 */       if (!deepContour.equals("")) {
/* 598 */         this.parameters.setDeepContour(Double.parseDouble(deepContour));
/*     */       }
/*     */       
/* 601 */       String spotSounding = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.SPOT_SOUNDING_TO);
/* 602 */       if (!spotSounding.equals(""))
/* 603 */         this.parameters.setSpotSoundings(Double.parseDouble(spotSounding)); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/gestao/GestorCartas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */