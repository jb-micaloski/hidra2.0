/*      */ package ipqm.gsd.hidra.ihm.util;
/*      */ 
/*      */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*      */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*      */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaPolar;
/*      */ import ipqm.gsd.componentes.dominio.contexto.ContextoTatico;
/*      */ import ipqm.gsd.componentes.dominio.entidades.IReferencial;
/*      */ import ipqm.gsd.componentes.nucleo.Mediador;
/*      */ import java.util.HashMap;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public enum InformacoesExibicaoUtil
/*      */ {
/*   23 */   COORDENADA_POLAR,
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   30 */   COORDENADA_GRADE,
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*   36 */   COORDENADA_GEOGRAFICA,
/*      */ 
/*      */ 
/*      */   
/*   40 */   LATITUDE,
/*      */ 
/*      */ 
/*      */   
/*   44 */   LONGITUDE;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String obterInformacaoPosicaoCursor(CoordenadaCartesiana posicaoCursor, InformacoesExibicaoUtil tipoCoordenada) {
/*   61 */     String informacaoPosicaoCursor = "";
/*      */     
/*      */     try { IReferencial objetoReferencial;
/*      */       CoordenadaGeografica coordLat, coordLong;
/*   65 */       switch (tipoCoordenada)
/*      */       
/*      */       { 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*      */         case COORDENADA_POLAR:
/*   74 */           objetoReferencial = ((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getTeatroOperacao().getIReferencial();
/*   75 */           if (objetoReferencial != null) {
/*   76 */             CoordenadaCartesiana pReferencial = objetoReferencial.getPosicaoReferencial().getCoordenadaCartesiana();
/*   77 */             CoordenadaPolar marcacaoDistancia = CoordenadaPolar.converterCoordenadaCartesiana(pReferencial, posicaoCursor);
/*   78 */             informacaoPosicaoCursor = marcacaoDistancia.toString();
/*      */           } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  126 */           return informacaoPosicaoCursor;case COORDENADA_GRADE: informacaoPosicaoCursor = ((ContextoTatico)Mediador.getInstancia().getContextualizador().getContexto()).getGrade().getTextoGrade(posicaoCursor); return informacaoPosicaoCursor;case COORDENADA_GEOGRAFICA: informacaoPosicaoCursor = CoordenadaGeografica.converterCoordenadaCartesiana(posicaoCursor, 0.0D).toString(); return informacaoPosicaoCursor;case LATITUDE: coordLat = CoordenadaGeografica.converterCoordenadaCartesiana(posicaoCursor, 0.0D); informacaoPosicaoCursor = CoordenadaGeografica.latitudeToString(coordLat.getLatitude()); return informacaoPosicaoCursor;case LONGITUDE: coordLong = CoordenadaGeografica.converterCoordenadaCartesiana(posicaoCursor, 0.0D); informacaoPosicaoCursor = CoordenadaGeografica.longitudeToString(coordLong.getLongitude()); return informacaoPosicaoCursor; }  informacaoPosicaoCursor = CoordenadaGeografica.converterCoordenadaCartesiana(posicaoCursor, 0.0D).toString(); } catch (NullPointerException nex) { informacaoPosicaoCursor = ""; throw nex; }  return informacaoPosicaoCursor;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String obterNomeCompletoAtributoFeicao(String nome) {
/*  151 */     HashMap<String, String> atributos = new HashMap<>();
/*  152 */     String nomeComletoAtributoFeicao = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  159 */     atributos.put("AGENCY", "Agency responsible for production");
/*  160 */     atributos.put("BCNSHP", "Beacon shape");
/*  161 */     atributos.put("BUISHP", "Building shape");
/*  162 */     atributos.put("BOYSHP", "Buoy shape");
/*  163 */     atributos.put("BURDEP", "Buried depth");
/*  164 */     atributos.put("CALSGN", "Call sign");
/*  165 */     atributos.put("CAT_TS", "Category of Tidal stream");
/*  166 */     atributos.put("CATAIR", "Category of airport/airfield");
/*  167 */     atributos.put("CATACH", "Category of anchorage");
/*  168 */     atributos.put("CATBRG", "Category of bridge");
/*  169 */     atributos.put("CATBUA", "Category of built-up area");
/*  170 */     atributos.put("CATCBL", "Category of cable");
/*  171 */     atributos.put("CATCAN", "Category of canal");
/*  172 */     atributos.put("CATCAM", "Category of cardinal mark");
/*  173 */     atributos.put("CATCHP", "Category of checkpoint");
/*  174 */     atributos.put("CATCOA", "Category of coastline");
/*  175 */     atributos.put("CATCTR", "Category of control point");
/*  176 */     atributos.put("CATCON", "Category of conveyor");
/*  177 */     atributos.put("CATCOV", "Category of coverage");
/*  178 */     atributos.put("CATCRN", "Category of crane");
/*  179 */     atributos.put("CATDAM", "Category of dam");
/*  180 */     atributos.put("CATDIS", "Category of distance mark");
/*  181 */     atributos.put("CATDOC", "Category of dock");
/*  182 */     atributos.put("CATDPG", "Category of dumping ground");
/*  183 */     atributos.put("CATFNC", "Category of fence/wall");
/*  184 */     atributos.put("CATFRY", "Category of ferry");
/*  185 */     atributos.put("CATFIF", "Category of fishing facility");
/*  186 */     atributos.put("CATFOG", "Category of fog signal");
/*  187 */     atributos.put("CATFOR", "Category of fortified structure");
/*  188 */     atributos.put("CATGAT", "Category of gate");
/*  189 */     atributos.put("CATHAF", "Category of harbour facility");
/*  190 */     atributos.put("CATHLK", "Category of hulk");
/*  191 */     atributos.put("CATICE", "Category of ice");
/*  192 */     atributos.put("CATINB", "Category of installation buoy");
/*  193 */     atributos.put("CATLND", "Category of land region");
/*  194 */     atributos.put("CATLMK", "Category of landmark");
/*  195 */     atributos.put("CATLAM", "Category of lateral mark");
/*  196 */     atributos.put("CATLIT", "Category of light");
/*  197 */     atributos.put("CATMFA", "Category of marine farm/culture");
/*  198 */     atributos.put("CATMPA", "Category of military practice area");
/*  199 */     atributos.put("CATMOR", "Category of mooring/warping facility");
/*  200 */     atributos.put("CATNAV", "Category of navigation line");
/*  201 */     atributos.put("CATOBS", "Category of obstruction");
/*  202 */     atributos.put("CATOFP", "Category of offshore platform");
/*  203 */     atributos.put("CATOLB", "Category of oil barrier");
/*  204 */     atributos.put("CATPLE", "Category of pile");
/*  205 */     atributos.put("CATPIL", "Category of pilot boarding place");
/*  206 */     atributos.put("CATPIP", "Category of pipeline / pipe");
/*  207 */     atributos.put("CATPRA", "Category of production area");
/*  208 */     atributos.put("CATPYL", "Category of pylon");
/*  209 */     atributos.put("CATRAS", "Category of radar station");
/*  210 */     atributos.put("CATRTB", "Category of radar transponder beacon");
/*  211 */     atributos.put("CATROS", "Category of radio station");
/*  212 */     atributos.put("CATTRK", "Category of recommended track");
/*  213 */     atributos.put("CATRSC", "Category of rescue station");
/*  214 */     atributos.put("CATREA", "Category of restricted area");
/*  215 */     atributos.put("CATROD", "Category of road");
/*  216 */     atributos.put("CATRUN", "Category of runway");
/*  217 */     atributos.put("CATSEA", "Category of sea area");
/*  218 */     atributos.put("CATSLC", "Category of shoreline construction");
/*  219 */     atributos.put("CATSIT", "Category of signal station - traffic");
/*  220 */     atributos.put("CATSIW", "Category of signal station - warning");
/*  221 */     atributos.put("CATSIL", "Category of silo/tank");
/*  222 */     atributos.put("CATSLO", "Category of slope");
/*  223 */     atributos.put("CATSCF", "Category of small craft facility");
/*  224 */     atributos.put("CATSPM", "Category of special purpose mark");
/*  225 */     atributos.put("CATTSS", "Category of Traffic Separation Scheme");
/*  226 */     atributos.put("CATVEG", "Category of vegetation");
/*  227 */     atributos.put("CATWAT", "Category of water turbulence");
/*  228 */     atributos.put("CATWED", "Category of weed/kelp");
/*  229 */     atributos.put("CATWRK", "Category of wreck");
/*  230 */     atributos.put("CATZOC", "Category of zone of confidence data");
/*  231 */     atributos.put("COLOUR", "Colour");
/*  232 */     atributos.put("COLPAT", "Colour pattern");
/*  233 */     atributos.put("COMCHA", "Communication channel");
/*  234 */     atributos.put("CPDATE", "Compilation date");
/*  235 */     atributos.put("CSCALE", "Compilation scale");
/*  236 */     atributos.put("CONDTN", "Condition");
/*  237 */     atributos.put("CONRAD", "Conspicuous - Radar");
/*  238 */     atributos.put("CONVIS", "Conspicuous - Visual");
/*  239 */     atributos.put("CURVEL", "Current velocity");
/*  240 */     atributos.put("DATEND", "Date end");
/*  241 */     atributos.put("DATSTA", "Date start");
/*  242 */     atributos.put("DRVAL1", "Depth range value 1");
/*  243 */     atributos.put("DRVAL2", "Depth range value 2");
/*  244 */     atributos.put("ELEVAT", "Elevation");
/*  245 */     atributos.put("ESTRNG", "Estimated range of transmission");
/*  246 */     atributos.put("EXCLIT", "Exhibition condition of light");
/*  247 */     atributos.put("EXPSOU", "Exposition of sounding");
/*  248 */     atributos.put("FUNCTN", "Function");
/*  249 */     atributos.put("HEIGHT", "Height");
/*  250 */     atributos.put("HORACC", "Horizontal accuracy");
/*  251 */     atributos.put("HORCLR", "Horizontal clearance");
/*  252 */     atributos.put("HORDAT", "Horizontal datum");
/*  253 */     atributos.put("HORLEN", "Horizontal length");
/*  254 */     atributos.put("HORWID", "Horizontal width");
/*  255 */     atributos.put("ICEFAC", "Ice factor");
/*  256 */     atributos.put("INFORM", "Information");
/*  257 */     atributos.put("JRSDTN", "Jurisdiction");
/*  258 */     atributos.put("LIFCAP", "Lifting capacity");
/*  259 */     atributos.put("LITCHR", "Light characteristic");
/*  260 */     atributos.put("LITVIS", "Light visibility");
/*  261 */     atributos.put("MARSYS", "Marks navigational - System of");
/*  262 */     atributos.put("MLTYLT", "Multiplicity of lights");
/*  263 */     atributos.put("NATION", "Nationality");
/*  264 */     atributos.put("NATCON", "Nature of construction");
/*  265 */     atributos.put("NATSUR", "Nature of surface");
/*  266 */     atributos.put("NATQUA", "Nature of surface - qualifying terms");
/*  267 */     atributos.put("NINFOM", "Information in national language");
/*  268 */     atributos.put("NMDATE", "Notice to Mariners date");
/*  269 */     atributos.put("NOBJNM", "Object name in national language");
/*  270 */     atributos.put("NPLDST", "Pilot district in national language");
/*  271 */     atributos.put("NTXTDS", "Textual description in national language");
/*  272 */     atributos.put("OBJNAM", "Object name");
/*  273 */     atributos.put("ORIENT", "Orientation");
/*  274 */     atributos.put("PEREND", "Periodic date end");
/*  275 */     atributos.put("PERSTA", "Periodic date start");
/*  276 */     atributos.put("PICREP", "Pictorial representation");
/*  277 */     atributos.put("PILDST", "Pilot district");
/*  278 */     atributos.put("POSACC", "Positional Accuracy");
/*  279 */     atributos.put("PRCTRY", "Producing country");
/*  280 */     atributos.put("PRODCT", "Product");
/*  281 */     atributos.put("PUBREF", "Publication reference");
/*  282 */     atributos.put("QUAPOS", "Quality of position");
/*  283 */     atributos.put("QUASOU", "Quality of sounding measurement");
/*  284 */     atributos.put("RADWAL", "Radar wave length");
/*  285 */     atributos.put("RADIUS", "Radius");
/*  286 */     atributos.put("RYRMGV", "Reference year for magnetic variation");
/*  287 */     atributos.put("RESTRN", "Restriction");
/*  288 */     atributos.put("SCAMIN", "Scale minimum");
/*  289 */     atributos.put("SCVAL1", "Scale value one");
/*  290 */     atributos.put("SCVAL2", "Scale value two");
/*  291 */     atributos.put("SECTR1", "Sector limit one");
/*  292 */     atributos.put("SECTR2", "Sector limit two");
/*  293 */     atributos.put("SHIPAM", "Shift parameters");
/*  294 */     atributos.put("SIGFRQ", "Signal frequency");
/*  295 */     atributos.put("SIGGEN", "Signal generation");
/*  296 */     atributos.put("SIGGRP", "Signal group");
/*  297 */     atributos.put("SIGPER", "Signal period");
/*  298 */     atributos.put("SIGSEQ", "Signal sequence");
/*  299 */     atributos.put("SOUACC", "Sounding accuracy");
/*  300 */     atributos.put("SDISMX", "Sounding distance - maximum");
/*  301 */     atributos.put("SDISMN", "Sounding distance - minimum");
/*  302 */     atributos.put("SORDAT", "Source date");
/*  303 */     atributos.put("SORIND", "Source indication");
/*  304 */     atributos.put("STATUS", "Status");
/*  305 */     atributos.put("SURATH", "Survey authority");
/*  306 */     atributos.put("SUREND", "Survey date - end");
/*  307 */     atributos.put("SURSTA", "Survey date - start");
/*  308 */     atributos.put("SURTYP", "Survey type");
/*  309 */     atributos.put("TECSOU", "Technique of sounding measurement");
/*  310 */     atributos.put("TXTDSC", "Textual description");
/*  311 */     atributos.put("TS_TSP", "Tidal stream - panel values");
/*  312 */     atributos.put("TS_TSV", "Tidal stream current - time series values");
/*  313 */     atributos.put("T_ACWL", "Tide - accuracy of water level");
/*  314 */     atributos.put("T_HWLW", "Tide - high and low water values");
/*  315 */     atributos.put("T_MTOD", "Tide - method of tidal prediction");
/*  316 */     atributos.put("T_THDF", "Tide - time and height differences");
/*  317 */     atributos.put("T_TINT", "Tide current - time interval of values");
/*  318 */     atributos.put("T_TSVL", "Tide - time series values");
/*  319 */     atributos.put("T_VAHC", "Tide - value of harmonic constituents");
/*  320 */     atributos.put("TIMEND", "Time end");
/*  321 */     atributos.put("TIMSTA", "Time start");
/*  322 */     atributos.put("TOPSHP", "Topmark/daymark shape");
/*  323 */     atributos.put("TRAFIC", "Traffic flow");
/*  324 */     atributos.put("VALACM", "Value of annual change in magnetic variation");
/*  325 */     atributos.put("VALDCO", "Value of depth contour");
/*  326 */     atributos.put("VALLMA", "Value of local magnetic anomaly");
/*  327 */     atributos.put("VALMAG", "Value of magnetic variation");
/*  328 */     atributos.put("VALMXR", "Value of maximum range");
/*  329 */     atributos.put("VALNMR", "Value of nominal range");
/*  330 */     atributos.put("VALSOU", "Value of sounding");
/*  331 */     atributos.put("VERACC", "Vertical accuracy");
/*  332 */     atributos.put("VERCLR", "Vertical clearance");
/*  333 */     atributos.put("VERCCL", "Vertical clearance - closed");
/*  334 */     atributos.put("VERCOP", "Vertical clearance - open");
/*  335 */     atributos.put("VERCSA", "Vertical clearance - safe");
/*  336 */     atributos.put("VERDAT", "Vertical datum");
/*  337 */     atributos.put("VERLEN", "Vertical length");
/*  338 */     atributos.put("WATLEV", "Water level effect");
/*      */ 
/*      */     
/*      */     try {
/*  342 */       nomeComletoAtributoFeicao = atributos.get(nome.toUpperCase());
/*      */     }
/*  344 */     catch (NullPointerException ex) {
/*      */       
/*  346 */       if (nome == null) {
/*      */         
/*  348 */         nomeComletoAtributoFeicao = null;
/*      */       }
/*      */       else {
/*      */         
/*  352 */         throw ex;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  358 */     return nomeComletoAtributoFeicao;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String obterDescricaoValorAtributoFeicao(String nomeAtributoFeicao, String valorAtributoFeicao) {
/*  381 */     HashMap<String, HashMap<String, String>> valoresAtributos = new HashMap<>();
/*  382 */     String descricaoAtributo = null;
/*      */ 
/*      */     
/*  385 */     valoresAtributos.put("AGENCY", new HashMap<>());
/*  386 */     valoresAtributos.put("BCNSHP", new HashMap<>());
/*  387 */     valoresAtributos.put("BUISHP", new HashMap<>());
/*  388 */     valoresAtributos.put("BOYSHP", new HashMap<>());
/*  389 */     valoresAtributos.put("BURDEP", new HashMap<>());
/*  390 */     valoresAtributos.put("CALSGN", new HashMap<>());
/*  391 */     valoresAtributos.put("CAT_TS", new HashMap<>());
/*  392 */     valoresAtributos.put("CATAIR", new HashMap<>());
/*  393 */     valoresAtributos.put("CATACH", new HashMap<>());
/*  394 */     valoresAtributos.put("CATBRG", new HashMap<>());
/*  395 */     valoresAtributos.put("CATBUA", new HashMap<>());
/*  396 */     valoresAtributos.put("CATCBL", new HashMap<>());
/*  397 */     valoresAtributos.put("CATCAN", new HashMap<>());
/*  398 */     valoresAtributos.put("CATCAM", new HashMap<>());
/*  399 */     valoresAtributos.put("CATCHP", new HashMap<>());
/*  400 */     valoresAtributos.put("CATCOA", new HashMap<>());
/*  401 */     valoresAtributos.put("CATCTR", new HashMap<>());
/*  402 */     valoresAtributos.put("CATCON", new HashMap<>());
/*  403 */     valoresAtributos.put("CATCOV", new HashMap<>());
/*  404 */     valoresAtributos.put("CATCRN", new HashMap<>());
/*  405 */     valoresAtributos.put("CATDAM", new HashMap<>());
/*  406 */     valoresAtributos.put("CATDIS", new HashMap<>());
/*  407 */     valoresAtributos.put("CATDOC", new HashMap<>());
/*  408 */     valoresAtributos.put("CATDPG", new HashMap<>());
/*  409 */     valoresAtributos.put("CATFNC", new HashMap<>());
/*  410 */     valoresAtributos.put("CATFRY", new HashMap<>());
/*  411 */     valoresAtributos.put("CATFIF", new HashMap<>());
/*  412 */     valoresAtributos.put("CATFOG", new HashMap<>());
/*  413 */     valoresAtributos.put("CATFOR", new HashMap<>());
/*  414 */     valoresAtributos.put("CATGAT", new HashMap<>());
/*  415 */     valoresAtributos.put("CATHAF", new HashMap<>());
/*  416 */     valoresAtributos.put("CATHLK", new HashMap<>());
/*  417 */     valoresAtributos.put("CATICE", new HashMap<>());
/*  418 */     valoresAtributos.put("CATINB", new HashMap<>());
/*  419 */     valoresAtributos.put("CATLND", new HashMap<>());
/*  420 */     valoresAtributos.put("CATLMK", new HashMap<>());
/*  421 */     valoresAtributos.put("CATLAM", new HashMap<>());
/*  422 */     valoresAtributos.put("CATLIT", new HashMap<>());
/*  423 */     valoresAtributos.put("CATMFA", new HashMap<>());
/*  424 */     valoresAtributos.put("CATMPA", new HashMap<>());
/*  425 */     valoresAtributos.put("CATMOR", new HashMap<>());
/*  426 */     valoresAtributos.put("CATNAV", new HashMap<>());
/*  427 */     valoresAtributos.put("CATOBS", new HashMap<>());
/*  428 */     valoresAtributos.put("CATOFP", new HashMap<>());
/*  429 */     valoresAtributos.put("CATOLB", new HashMap<>());
/*  430 */     valoresAtributos.put("CATPLE", new HashMap<>());
/*  431 */     valoresAtributos.put("CATPIL", new HashMap<>());
/*  432 */     valoresAtributos.put("CATPIP", new HashMap<>());
/*  433 */     valoresAtributos.put("CATPRA", new HashMap<>());
/*  434 */     valoresAtributos.put("CATPYL", new HashMap<>());
/*  435 */     valoresAtributos.put("CATRAS", new HashMap<>());
/*  436 */     valoresAtributos.put("CATRTB", new HashMap<>());
/*  437 */     valoresAtributos.put("CATROS", new HashMap<>());
/*  438 */     valoresAtributos.put("CATTRK", new HashMap<>());
/*  439 */     valoresAtributos.put("CATRSC", new HashMap<>());
/*  440 */     valoresAtributos.put("CATREA", new HashMap<>());
/*  441 */     valoresAtributos.put("CATROD", new HashMap<>());
/*  442 */     valoresAtributos.put("CATRUN", new HashMap<>());
/*  443 */     valoresAtributos.put("CATSEA", new HashMap<>());
/*  444 */     valoresAtributos.put("CATSLC", new HashMap<>());
/*  445 */     valoresAtributos.put("CATSIT", new HashMap<>());
/*  446 */     valoresAtributos.put("CATSIW", new HashMap<>());
/*  447 */     valoresAtributos.put("CATSIL", new HashMap<>());
/*  448 */     valoresAtributos.put("CATSLO", new HashMap<>());
/*  449 */     valoresAtributos.put("CATSCF", new HashMap<>());
/*  450 */     valoresAtributos.put("CATSPM", new HashMap<>());
/*  451 */     valoresAtributos.put("CATTSS", new HashMap<>());
/*  452 */     valoresAtributos.put("CATVEG", new HashMap<>());
/*  453 */     valoresAtributos.put("CATWAT", new HashMap<>());
/*  454 */     valoresAtributos.put("CATWED", new HashMap<>());
/*  455 */     valoresAtributos.put("CATWRK", new HashMap<>());
/*  456 */     valoresAtributos.put("CATZOC", new HashMap<>());
/*  457 */     valoresAtributos.put("COLOUR", new HashMap<>());
/*  458 */     valoresAtributos.put("COLPAT", new HashMap<>());
/*  459 */     valoresAtributos.put("COMCHA", new HashMap<>());
/*  460 */     valoresAtributos.put("CPDATE", new HashMap<>());
/*  461 */     valoresAtributos.put("CSCALE", new HashMap<>());
/*  462 */     valoresAtributos.put("CONDTN", new HashMap<>());
/*  463 */     valoresAtributos.put("CONRAD", new HashMap<>());
/*  464 */     valoresAtributos.put("CONVIS", new HashMap<>());
/*  465 */     valoresAtributos.put("CURVEL", new HashMap<>());
/*  466 */     valoresAtributos.put("DATEND", new HashMap<>());
/*  467 */     valoresAtributos.put("DATSTA", new HashMap<>());
/*  468 */     valoresAtributos.put("DRVAL1", new HashMap<>());
/*  469 */     valoresAtributos.put("DRVAL2", new HashMap<>());
/*  470 */     valoresAtributos.put("ELEVAT", new HashMap<>());
/*  471 */     valoresAtributos.put("ESTRNG", new HashMap<>());
/*  472 */     valoresAtributos.put("EXCLIT", new HashMap<>());
/*  473 */     valoresAtributos.put("EXPSOU", new HashMap<>());
/*  474 */     valoresAtributos.put("FUNCTN", new HashMap<>());
/*  475 */     valoresAtributos.put("HEIGHT", new HashMap<>());
/*  476 */     valoresAtributos.put("HORACC", new HashMap<>());
/*  477 */     valoresAtributos.put("HORCLR", new HashMap<>());
/*  478 */     valoresAtributos.put("HORDAT", new HashMap<>());
/*  479 */     valoresAtributos.put("HORLEN", new HashMap<>());
/*  480 */     valoresAtributos.put("HORWID", new HashMap<>());
/*  481 */     valoresAtributos.put("ICEFAC", new HashMap<>());
/*  482 */     valoresAtributos.put("INFORM", new HashMap<>());
/*  483 */     valoresAtributos.put("JRSDTN", new HashMap<>());
/*  484 */     valoresAtributos.put("LIFCAP", new HashMap<>());
/*  485 */     valoresAtributos.put("LITCHR", new HashMap<>());
/*  486 */     valoresAtributos.put("LITVIS", new HashMap<>());
/*  487 */     valoresAtributos.put("MARSYS", new HashMap<>());
/*  488 */     valoresAtributos.put("MLTYLT", new HashMap<>());
/*  489 */     valoresAtributos.put("NATION", new HashMap<>());
/*  490 */     valoresAtributos.put("NATCON", new HashMap<>());
/*  491 */     valoresAtributos.put("NATSUR", new HashMap<>());
/*  492 */     valoresAtributos.put("NATQUA", new HashMap<>());
/*  493 */     valoresAtributos.put("NINFOM", new HashMap<>());
/*  494 */     valoresAtributos.put("NMDATE", new HashMap<>());
/*  495 */     valoresAtributos.put("NOBJNM", new HashMap<>());
/*  496 */     valoresAtributos.put("NPLDST", new HashMap<>());
/*  497 */     valoresAtributos.put("NTXTDS", new HashMap<>());
/*  498 */     valoresAtributos.put("OBJNAM", new HashMap<>());
/*  499 */     valoresAtributos.put("ORIENT", new HashMap<>());
/*  500 */     valoresAtributos.put("PEREND", new HashMap<>());
/*  501 */     valoresAtributos.put("PERSTA", new HashMap<>());
/*  502 */     valoresAtributos.put("PICREP", new HashMap<>());
/*  503 */     valoresAtributos.put("PILDST", new HashMap<>());
/*  504 */     valoresAtributos.put("POSACC", new HashMap<>());
/*  505 */     valoresAtributos.put("PRCTRY", new HashMap<>());
/*  506 */     valoresAtributos.put("PRODCT", new HashMap<>());
/*  507 */     valoresAtributos.put("PUBREF", new HashMap<>());
/*  508 */     valoresAtributos.put("QUAPOS", new HashMap<>());
/*  509 */     valoresAtributos.put("QUASOU", new HashMap<>());
/*  510 */     valoresAtributos.put("RADWAL", new HashMap<>());
/*  511 */     valoresAtributos.put("RADIUS", new HashMap<>());
/*  512 */     valoresAtributos.put("RYRMGV", new HashMap<>());
/*  513 */     valoresAtributos.put("RESTRN", new HashMap<>());
/*  514 */     valoresAtributos.put("SCAMIN", new HashMap<>());
/*  515 */     valoresAtributos.put("SCVAL1", new HashMap<>());
/*  516 */     valoresAtributos.put("SCVAL2", new HashMap<>());
/*  517 */     valoresAtributos.put("SECTR1", new HashMap<>());
/*  518 */     valoresAtributos.put("SECTR2", new HashMap<>());
/*  519 */     valoresAtributos.put("SHIPAM", new HashMap<>());
/*  520 */     valoresAtributos.put("SIGFRQ", new HashMap<>());
/*  521 */     valoresAtributos.put("SIGGEN", new HashMap<>());
/*  522 */     valoresAtributos.put("SIGGRP", new HashMap<>());
/*  523 */     valoresAtributos.put("SIGPER", new HashMap<>());
/*  524 */     valoresAtributos.put("SIGSEQ", new HashMap<>());
/*  525 */     valoresAtributos.put("SOUACC", new HashMap<>());
/*  526 */     valoresAtributos.put("SDISMX", new HashMap<>());
/*  527 */     valoresAtributos.put("SDISMN", new HashMap<>());
/*  528 */     valoresAtributos.put("SORDAT", new HashMap<>());
/*  529 */     valoresAtributos.put("SORIND", new HashMap<>());
/*  530 */     valoresAtributos.put("STATUS", new HashMap<>());
/*  531 */     valoresAtributos.put("SURATH", new HashMap<>());
/*  532 */     valoresAtributos.put("SUREND", new HashMap<>());
/*  533 */     valoresAtributos.put("SURSTA", new HashMap<>());
/*  534 */     valoresAtributos.put("SURTYP", new HashMap<>());
/*  535 */     valoresAtributos.put("TXTDSC", new HashMap<>());
/*  536 */     valoresAtributos.put("TECSOU", new HashMap<>());
/*  537 */     valoresAtributos.put("TS_TSP", new HashMap<>());
/*  538 */     valoresAtributos.put("TS_TSV", new HashMap<>());
/*  539 */     valoresAtributos.put("T_ACWL", new HashMap<>());
/*  540 */     valoresAtributos.put("T_HWLW", new HashMap<>());
/*  541 */     valoresAtributos.put("T_MTOD", new HashMap<>());
/*  542 */     valoresAtributos.put("T_THDF", new HashMap<>());
/*  543 */     valoresAtributos.put("T_TINT", new HashMap<>());
/*  544 */     valoresAtributos.put("T_TSVL", new HashMap<>());
/*  545 */     valoresAtributos.put("T_VAHC", new HashMap<>());
/*  546 */     valoresAtributos.put("TIMEND", new HashMap<>());
/*  547 */     valoresAtributos.put("TIMSTA", new HashMap<>());
/*  548 */     valoresAtributos.put("TOPSHP", new HashMap<>());
/*  549 */     valoresAtributos.put("TRAFIC", new HashMap<>());
/*  550 */     valoresAtributos.put("VALACM", new HashMap<>());
/*  551 */     valoresAtributos.put("VALDCO", new HashMap<>());
/*  552 */     valoresAtributos.put("VALLMA", new HashMap<>());
/*  553 */     valoresAtributos.put("VALMAG", new HashMap<>());
/*  554 */     valoresAtributos.put("VALMXR", new HashMap<>());
/*  555 */     valoresAtributos.put("VALNMR", new HashMap<>());
/*  556 */     valoresAtributos.put("VALSOU", new HashMap<>());
/*  557 */     valoresAtributos.put("VERACC", new HashMap<>());
/*  558 */     valoresAtributos.put("VERCLR", new HashMap<>());
/*  559 */     valoresAtributos.put("VERCCL", new HashMap<>());
/*  560 */     valoresAtributos.put("VERCOP", new HashMap<>());
/*  561 */     valoresAtributos.put("VERCSA", new HashMap<>());
/*  562 */     valoresAtributos.put("VERDAT", new HashMap<>());
/*  563 */     valoresAtributos.put("VERLEN", new HashMap<>());
/*  564 */     valoresAtributos.put("WATLEV", new HashMap<>());
/*  565 */     valoresAtributos.put("SDRLEV", new HashMap<>());
/*      */ 
/*      */     
/*  568 */     ((HashMap<String, String>)valoresAtributos.get("BCNSHP")).put("1", "stake, pole, perch, post");
/*  569 */     ((HashMap<String, String>)valoresAtributos.get("BCNSHP")).put("2", "whity");
/*  570 */     ((HashMap<String, String>)valoresAtributos.get("BCNSHP")).put("3", "beacon tower");
/*  571 */     ((HashMap<String, String>)valoresAtributos.get("BCNSHP")).put("4", "lattice beacon");
/*  572 */     ((HashMap<String, String>)valoresAtributos.get("BCNSHP")).put("5", "pile beacon");
/*  573 */     ((HashMap<String, String>)valoresAtributos.get("BCNSHP")).put("6", "cairn");
/*  574 */     ((HashMap<String, String>)valoresAtributos.get("BCNSHP")).put("7", "buoyant beacon");
/*  575 */     ((HashMap<String, String>)valoresAtributos.get("BUISHP")).put("5", "high-rise building");
/*  576 */     ((HashMap<String, String>)valoresAtributos.get("BUISHP")).put("6", "pyramid");
/*  577 */     ((HashMap<String, String>)valoresAtributos.get("BUISHP")).put("7", "cylindrical");
/*  578 */     ((HashMap<String, String>)valoresAtributos.get("BUISHP")).put("8", "spherical");
/*  579 */     ((HashMap<String, String>)valoresAtributos.get("BUISHP")).put("9", "cubic");
/*  580 */     ((HashMap<String, String>)valoresAtributos.get("BOYSHP")).put("1", "conical (nun, ogival)");
/*  581 */     ((HashMap<String, String>)valoresAtributos.get("BOYSHP")).put("2", "can (cylindrical)");
/*  582 */     ((HashMap<String, String>)valoresAtributos.get("BOYSHP")).put("3", "spherical");
/*  583 */     ((HashMap<String, String>)valoresAtributos.get("BOYSHP")).put("4", "pillar");
/*  584 */     ((HashMap<String, String>)valoresAtributos.get("BOYSHP")).put("5", "spar (spindle)");
/*  585 */     ((HashMap<String, String>)valoresAtributos.get("BOYSHP")).put("6", "barrel (tun)");
/*  586 */     ((HashMap<String, String>)valoresAtributos.get("BOYSHP")).put("7", "super-buoy");
/*  587 */     ((HashMap<String, String>)valoresAtributos.get("BOYSHP")).put("8", "ice buoy");
/*  588 */     ((HashMap<String, String>)valoresAtributos.get("BOYSHP")).put("1", "military aeroplane airport");
/*  589 */     ((HashMap<String, String>)valoresAtributos.get("CATAIR")).put("2", "civil aeroplane airport");
/*  590 */     ((HashMap<String, String>)valoresAtributos.get("CATAIR")).put("3", "military heliport");
/*  591 */     ((HashMap<String, String>)valoresAtributos.get("CATAIR")).put("4", "civil heliport");
/*  592 */     ((HashMap<String, String>)valoresAtributos.get("CATAIR")).put("5", "glider airfield");
/*  593 */     ((HashMap<String, String>)valoresAtributos.get("CATAIR")).put("6", "small planes airfield");
/*  594 */     ((HashMap<String, String>)valoresAtributos.get("CATAIR")).put("8", "emergency airfield");
/*  595 */     ((HashMap<String, String>)valoresAtributos.get("CATACH")).put("1", "unrestricted anchorage");
/*  596 */     ((HashMap<String, String>)valoresAtributos.get("CATACH")).put("2", "deep water anchorage");
/*  597 */     ((HashMap<String, String>)valoresAtributos.get("CATACH")).put("3", "tanker anchorage");
/*  598 */     ((HashMap<String, String>)valoresAtributos.get("CATACH")).put("4", "explosives anchorage");
/*  599 */     ((HashMap<String, String>)valoresAtributos.get("CATACH")).put("5", "quarantine anchorage");
/*  600 */     ((HashMap<String, String>)valoresAtributos.get("CATACH")).put("6", "sea-plane anchorage");
/*  601 */     ((HashMap<String, String>)valoresAtributos.get("CATACH")).put("7", "small craft anchorage");
/*  602 */     ((HashMap<String, String>)valoresAtributos.get("CATACH")).put("8", "small craft mooring area");
/*  603 */     ((HashMap<String, String>)valoresAtributos.get("CATACH")).put("9", "anchorage for periods up to 24 hours");
/*  604 */     ((HashMap<String, String>)valoresAtributos.get("CATACH")).put("10", "anchorage for pushing-navigation vessels");
/*  605 */     ((HashMap<String, String>)valoresAtributos.get("CATACH")).put("11", "anchorage for other vessels than pushing-navigation vessels");
/*  606 */     ((HashMap<String, String>)valoresAtributos.get("CATBRG")).put("1", "fixed bridge");
/*  607 */     ((HashMap<String, String>)valoresAtributos.get("CATBRG")).put("2", "opening bridge");
/*  608 */     ((HashMap<String, String>)valoresAtributos.get("CATBRG")).put("3", "swing bridge");
/*  609 */     ((HashMap<String, String>)valoresAtributos.get("CATBRG")).put("4", "lifting bridge");
/*  610 */     ((HashMap<String, String>)valoresAtributos.get("CATBRG")).put("5", "bascule bridge");
/*  611 */     ((HashMap<String, String>)valoresAtributos.get("CATBRG")).put("6", "pontoon bridge");
/*  612 */     ((HashMap<String, String>)valoresAtributos.get("CATBRG")).put("7", "draw bridge");
/*  613 */     ((HashMap<String, String>)valoresAtributos.get("CATBRG")).put("8", "transporter bridge");
/*  614 */     ((HashMap<String, String>)valoresAtributos.get("CATBRG")).put("9", "footbridge");
/*  615 */     ((HashMap<String, String>)valoresAtributos.get("CATBRG")).put("10", "viaduct");
/*  616 */     ((HashMap<String, String>)valoresAtributos.get("CATBRG")).put("11", "aqueduct");
/*  617 */     ((HashMap<String, String>)valoresAtributos.get("CATBRG")).put("12", "suspension bridge");
/*  618 */     ((HashMap<String, String>)valoresAtributos.get("CATBUA")).put("1", "urban area");
/*  619 */     ((HashMap<String, String>)valoresAtributos.get("CATBUA")).put("2", "settlement");
/*  620 */     ((HashMap<String, String>)valoresAtributos.get("CATBUA")).put("3", "village");
/*  621 */     ((HashMap<String, String>)valoresAtributos.get("CATBUA")).put("4", "town");
/*  622 */     ((HashMap<String, String>)valoresAtributos.get("CATBUA")).put("5", "city");
/*  623 */     ((HashMap<String, String>)valoresAtributos.get("CATBUA")).put("6", "holiday village");
/*  624 */     ((HashMap<String, String>)valoresAtributos.get("CATCBL")).put("1", "power line");
/*  625 */     ((HashMap<String, String>)valoresAtributos.get("CATCBL")).put("3", "transmission line");
/*  626 */     ((HashMap<String, String>)valoresAtributos.get("CATCBL")).put("4", "telephone");
/*  627 */     ((HashMap<String, String>)valoresAtributos.get("CATCBL")).put("5", "telegraph");
/*  628 */     ((HashMap<String, String>)valoresAtributos.get("CATCBL")).put("6", "mooring cable/chain");
/*  629 */     ((HashMap<String, String>)valoresAtributos.get("CATCAN")).put("1", "transportation");
/*  630 */     ((HashMap<String, String>)valoresAtributos.get("CATCAN")).put("2", "drainage");
/*  631 */     ((HashMap<String, String>)valoresAtributos.get("CATCAN")).put("3", "irrigation");
/*  632 */     ((HashMap<String, String>)valoresAtributos.get("CATCAM")).put("1", "north cardinal mark");
/*  633 */     ((HashMap<String, String>)valoresAtributos.get("CATCAM")).put("2", "east cardinal mark");
/*  634 */     ((HashMap<String, String>)valoresAtributos.get("CATCAM")).put("3", "south cardinal mark");
/*  635 */     ((HashMap<String, String>)valoresAtributos.get("CATCAM")).put("4", "west cardinal mark");
/*  636 */     ((HashMap<String, String>)valoresAtributos.get("CATCHP")).put("1", "custom");
/*  637 */     ((HashMap<String, String>)valoresAtributos.get("CATCOA")).put("1", "steep coast");
/*  638 */     ((HashMap<String, String>)valoresAtributos.get("CATCOA")).put("2", "flat coast");
/*  639 */     ((HashMap<String, String>)valoresAtributos.get("CATCOA")).put("3", "sandy shore");
/*  640 */     ((HashMap<String, String>)valoresAtributos.get("CATCOA")).put("4", "stony shore");
/*  641 */     ((HashMap<String, String>)valoresAtributos.get("CATCOA")).put("5", "shingly shore");
/*  642 */     ((HashMap<String, String>)valoresAtributos.get("CATCOA")).put("6", "glacier (seaward end)");
/*  643 */     ((HashMap<String, String>)valoresAtributos.get("CATCOA")).put("7", "mangrove");
/*  644 */     ((HashMap<String, String>)valoresAtributos.get("CATCOA")).put("8", "marshy shore");
/*  645 */     ((HashMap<String, String>)valoresAtributos.get("CATCOA")).put("9", "coral reef");
/*  646 */     ((HashMap<String, String>)valoresAtributos.get("CATCOA")).put("10", "ice coast");
/*  647 */     ((HashMap<String, String>)valoresAtributos.get("CATCTR")).put("1", "triangulation point");
/*  648 */     ((HashMap<String, String>)valoresAtributos.get("CATCTR")).put("2", "observation spot");
/*  649 */     ((HashMap<String, String>)valoresAtributos.get("CATCTR")).put("3", "fixed point");
/*  650 */     ((HashMap<String, String>)valoresAtributos.get("CATCTR")).put("4", "bench-mark");
/*  651 */     ((HashMap<String, String>)valoresAtributos.get("CATCTR")).put("5", "boundary mark");
/*  652 */     ((HashMap<String, String>)valoresAtributos.get("CATCTR")).put("6", "horizontal control, main station");
/*  653 */     ((HashMap<String, String>)valoresAtributos.get("CATCTR")).put("7", "horizontal control, secondary station");
/*  654 */     ((HashMap<String, String>)valoresAtributos.get("CATCON")).put("1", "aerial cableway (telepheric)");
/*  655 */     ((HashMap<String, String>)valoresAtributos.get("CATCON")).put("2", "belt conveyor");
/*  656 */     ((HashMap<String, String>)valoresAtributos.get("CATCOV")).put("1", "coverage available");
/*  657 */     ((HashMap<String, String>)valoresAtributos.get("CATCOV")).put("2", "no coverage available");
/*  658 */     ((HashMap<String, String>)valoresAtributos.get("CATCRN")).put("2", "container crane/gantry");
/*  659 */     ((HashMap<String, String>)valoresAtributos.get("CATCRN")).put("3", "sheerlegs");
/*  660 */     ((HashMap<String, String>)valoresAtributos.get("CATCRN")).put("4", "travelling crane");
/*  661 */     ((HashMap<String, String>)valoresAtributos.get("CATCRN")).put("5", "A-frame");
/*  662 */     ((HashMap<String, String>)valoresAtributos.get("CATDAM")).put("1", "weir");
/*  663 */     ((HashMap<String, String>)valoresAtributos.get("CATDAM")).put("2", "dam");
/*  664 */     ((HashMap<String, String>)valoresAtributos.get("CATDAM")).put("3", "flood barrage");
/*  665 */     ((HashMap<String, String>)valoresAtributos.get("CATDIS")).put("1", "distance mark not physically installed");
/*  666 */     ((HashMap<String, String>)valoresAtributos.get("CATDIS")).put("2", "visible mark, pole");
/*  667 */     ((HashMap<String, String>)valoresAtributos.get("CATDIS")).put("3", "visible mark, board");
/*  668 */     ((HashMap<String, String>)valoresAtributos.get("CATDIS")).put("4", "visible mark, unknown shape");
/*  669 */     ((HashMap<String, String>)valoresAtributos.get("CATDOC")).put("1", "tidal");
/*  670 */     ((HashMap<String, String>)valoresAtributos.get("CATDOC")).put("2", "non-tidal (wet dock)");
/*  671 */     ((HashMap<String, String>)valoresAtributos.get("CATDPG")).put("2", "chemical waste dumping ground");
/*  672 */     ((HashMap<String, String>)valoresAtributos.get("CATDPG")).put("3", "nuclear waste dumping ground");
/*  673 */     ((HashMap<String, String>)valoresAtributos.get("CATDPG")).put("4", "explosives dumping ground");
/*  674 */     ((HashMap<String, String>)valoresAtributos.get("CATDPG")).put("5", "spoil ground");
/*  675 */     ((HashMap<String, String>)valoresAtributos.get("CATDPG")).put("6", "vessel dumping ground");
/*  676 */     ((HashMap<String, String>)valoresAtributos.get("CATFNC")).put("1", "fence");
/*  677 */     ((HashMap<String, String>)valoresAtributos.get("CATFNC")).put("3", "hedge");
/*  678 */     ((HashMap<String, String>)valoresAtributos.get("CATFNC")).put("4", "wall");
/*  679 */     ((HashMap<String, String>)valoresAtributos.get("CATFRY")).put("1", "free-moving' ferry");
/*  680 */     ((HashMap<String, String>)valoresAtributos.get("CATFRY")).put("2", "cable ferry");
/*  681 */     ((HashMap<String, String>)valoresAtributos.get("CATFRY")).put("3", "ice ferry");
/*  682 */     ((HashMap<String, String>)valoresAtributos.get("CATFIF")).put("1", "fishing stake");
/*  683 */     ((HashMap<String, String>)valoresAtributos.get("CATFIF")).put("2", "fish trap");
/*  684 */     ((HashMap<String, String>)valoresAtributos.get("CATFIF")).put("3", "fish weir");
/*  685 */     ((HashMap<String, String>)valoresAtributos.get("CATFIF")).put("4", "tunny net");
/*  686 */     ((HashMap<String, String>)valoresAtributos.get("CATFOG")).put("1", "explosive");
/*  687 */     ((HashMap<String, String>)valoresAtributos.get("CATFOG")).put("2", "diaphone");
/*  688 */     ((HashMap<String, String>)valoresAtributos.get("CATFOG")).put("3", "siren");
/*  689 */     ((HashMap<String, String>)valoresAtributos.get("CATFOG")).put("4", "nautophone");
/*  690 */     ((HashMap<String, String>)valoresAtributos.get("CATFOG")).put("5", "reed");
/*  691 */     ((HashMap<String, String>)valoresAtributos.get("CATFOG")).put("6", "tyfon");
/*  692 */     ((HashMap<String, String>)valoresAtributos.get("CATFOG")).put("7", "bell");
/*  693 */     ((HashMap<String, String>)valoresAtributos.get("CATFOG")).put("8", "whistle");
/*  694 */     ((HashMap<String, String>)valoresAtributos.get("CATFOG")).put("9", "gong");
/*  695 */     ((HashMap<String, String>)valoresAtributos.get("CATFOG")).put("10", "horn");
/*  696 */     ((HashMap<String, String>)valoresAtributos.get("CATFOR")).put("1", "castle");
/*  697 */     ((HashMap<String, String>)valoresAtributos.get("CATFOR")).put("2", "fort");
/*  698 */     ((HashMap<String, String>)valoresAtributos.get("CATFOR")).put("3", "battery");
/*  699 */     ((HashMap<String, String>)valoresAtributos.get("CATFOR")).put("4", "blockhouse");
/*  700 */     ((HashMap<String, String>)valoresAtributos.get("CATFOR")).put("5", "Martello tower");
/*  701 */     ((HashMap<String, String>)valoresAtributos.get("CATGAT")).put("2", "flood barrage gate");
/*  702 */     ((HashMap<String, String>)valoresAtributos.get("CATGAT")).put("3", "caisson");
/*  703 */     ((HashMap<String, String>)valoresAtributos.get("CATGAT")).put("4", "lock gate");
/*  704 */     ((HashMap<String, String>)valoresAtributos.get("CATGAT")).put("5", "dyke gate");
/*  705 */     ((HashMap<String, String>)valoresAtributos.get("CATHAF")).put("1", "RoRo-terminal");
/*  706 */     ((HashMap<String, String>)valoresAtributos.get("CATHAF")).put("2", "timber yard");
/*  707 */     ((HashMap<String, String>)valoresAtributos.get("CATHAF")).put("3", "ferry terminal");
/*  708 */     ((HashMap<String, String>)valoresAtributos.get("CATHAF")).put("4", "fishing harbour");
/*  709 */     ((HashMap<String, String>)valoresAtributos.get("CATHAF")).put("5", "yacht harbour/marina");
/*  710 */     ((HashMap<String, String>)valoresAtributos.get("CATHAF")).put("6", "naval base");
/*  711 */     ((HashMap<String, String>)valoresAtributos.get("CATHAF")).put("7", "tanker terminal");
/*  712 */     ((HashMap<String, String>)valoresAtributos.get("CATHAF")).put("8", "passenger terminal");
/*  713 */     ((HashMap<String, String>)valoresAtributos.get("CATHAF")).put("9", "shipyard");
/*  714 */     ((HashMap<String, String>)valoresAtributos.get("CATHAF")).put("10", "container terminal");
/*  715 */     ((HashMap<String, String>)valoresAtributos.get("CATHAF")).put("11", "bulk terminal");
/*  716 */     ((HashMap<String, String>)valoresAtributos.get("CATHAF")).put("12", "syncrolift");
/*  717 */     ((HashMap<String, String>)valoresAtributos.get("CATHAF")).put("13", "straddle carrier");
/*  718 */     ((HashMap<String, String>)valoresAtributos.get("CATHLK")).put("1", "floating restaurant");
/*  719 */     ((HashMap<String, String>)valoresAtributos.get("CATHLK")).put("2", "historic ship");
/*  720 */     ((HashMap<String, String>)valoresAtributos.get("CATHLK")).put("3", "museum");
/*  721 */     ((HashMap<String, String>)valoresAtributos.get("CATHLK")).put("4", "accomodation");
/*  722 */     ((HashMap<String, String>)valoresAtributos.get("CATHLK")).put("5", "floating breakwater");
/*  723 */     ((HashMap<String, String>)valoresAtributos.get("CATICE")).put("1", "fast ice");
/*  724 */     ((HashMap<String, String>)valoresAtributos.get("CATICE")).put("5", "glacier");
/*  725 */     ((HashMap<String, String>)valoresAtributos.get("CATICE")).put("8", "polar ice");
/*  726 */     ((HashMap<String, String>)valoresAtributos.get("CATINB")).put("1", "catenary anchor leg mooring (CALM)");
/*  727 */     ((HashMap<String, String>)valoresAtributos.get("CATINB")).put("2", "single buoy mooring (SBM or SPM)");
/*  728 */     ((HashMap<String, String>)valoresAtributos.get("CATLND")).put("1", "fen");
/*  729 */     ((HashMap<String, String>)valoresAtributos.get("CATLND")).put("2", "marsh");
/*  730 */     ((HashMap<String, String>)valoresAtributos.get("CATLND")).put("3", "moor/bog");
/*  731 */     ((HashMap<String, String>)valoresAtributos.get("CATLND")).put("4", "heathland");
/*  732 */     ((HashMap<String, String>)valoresAtributos.get("CATLND")).put("5", "mountain range");
/*  733 */     ((HashMap<String, String>)valoresAtributos.get("CATLND")).put("6", "lowlands");
/*  734 */     ((HashMap<String, String>)valoresAtributos.get("CATLND")).put("7", "canyon lands");
/*  735 */     ((HashMap<String, String>)valoresAtributos.get("CATLND")).put("8", "paddy field");
/*  736 */     ((HashMap<String, String>)valoresAtributos.get("CATLND")).put("9", "agricultural land");
/*  737 */     ((HashMap<String, String>)valoresAtributos.get("CATLND")).put("10", "savanna/grassland");
/*  738 */     ((HashMap<String, String>)valoresAtributos.get("CATLND")).put("11", "parkland");
/*  739 */     ((HashMap<String, String>)valoresAtributos.get("CATLND")).put("12", "swamp");
/*  740 */     ((HashMap<String, String>)valoresAtributos.get("CATLND")).put("13", "landslide");
/*  741 */     ((HashMap<String, String>)valoresAtributos.get("CATLND")).put("14", "lava flow");
/*  742 */     ((HashMap<String, String>)valoresAtributos.get("CATLND")).put("15", "salt pan");
/*  743 */     ((HashMap<String, String>)valoresAtributos.get("CATLND")).put("16", "moraine");
/*  744 */     ((HashMap<String, String>)valoresAtributos.get("CATLND")).put("17", "crater");
/*  745 */     ((HashMap<String, String>)valoresAtributos.get("CATLND")).put("18", "cave");
/*  746 */     ((HashMap<String, String>)valoresAtributos.get("CATLND")).put("19", "rock column or pinnacle");
/*  747 */     ((HashMap<String, String>)valoresAtributos.get("CATLMK")).put("1", "cairn");
/*  748 */     ((HashMap<String, String>)valoresAtributos.get("CATLMK")).put("2", "cemetery");
/*  749 */     ((HashMap<String, String>)valoresAtributos.get("CATLMK")).put("3", "chimney");
/*  750 */     ((HashMap<String, String>)valoresAtributos.get("CATLMK")).put("4", "dish aerial");
/*  751 */     ((HashMap<String, String>)valoresAtributos.get("CATLMK")).put("5", "flagstaff (flagpole)");
/*  752 */     ((HashMap<String, String>)valoresAtributos.get("CATLMK")).put("6", "flare stack");
/*  753 */     ((HashMap<String, String>)valoresAtributos.get("CATLMK")).put("7", "mast");
/*  754 */     ((HashMap<String, String>)valoresAtributos.get("CATLMK")).put("8", "windsock");
/*  755 */     ((HashMap<String, String>)valoresAtributos.get("CATLMK")).put("9", "monument");
/*  756 */     ((HashMap<String, String>)valoresAtributos.get("CATLMK")).put("10", "column (pillar)");
/*  757 */     ((HashMap<String, String>)valoresAtributos.get("CATLMK")).put("11", "memorial plaque");
/*  758 */     ((HashMap<String, String>)valoresAtributos.get("CATLMK")).put("12", "obelisk");
/*  759 */     ((HashMap<String, String>)valoresAtributos.get("CATLMK")).put("13", "statue");
/*  760 */     ((HashMap<String, String>)valoresAtributos.get("CATLMK")).put("14", "cross");
/*  761 */     ((HashMap<String, String>)valoresAtributos.get("CATLMK")).put("15", "dome");
/*  762 */     ((HashMap<String, String>)valoresAtributos.get("CATLMK")).put("16", "radar scanner");
/*  763 */     ((HashMap<String, String>)valoresAtributos.get("CATLMK")).put("17", "tower");
/*  764 */     ((HashMap<String, String>)valoresAtributos.get("CATLMK")).put("18", "windmill");
/*  765 */     ((HashMap<String, String>)valoresAtributos.get("CATLMK")).put("19", "windmotor");
/*  766 */     ((HashMap<String, String>)valoresAtributos.get("CATLMK")).put("20", "spire/minaret");
/*  767 */     ((HashMap<String, String>)valoresAtributos.get("CATLMK")).put("21", "large rock or boulder on land");
/*  768 */     ((HashMap<String, String>)valoresAtributos.get("CATLMK")).put("22", "large rock or boulder on land");
/*  769 */     ((HashMap<String, String>)valoresAtributos.get("CATLAM")).put("1", "port-hand lateral mark");
/*  770 */     ((HashMap<String, String>)valoresAtributos.get("CATLAM")).put("2", "starboard-hand lateral mark");
/*  771 */     ((HashMap<String, String>)valoresAtributos.get("CATLAM")).put("3", "preferred channel to starboard lateral mark");
/*  772 */     ((HashMap<String, String>)valoresAtributos.get("CATLAM")).put("4", "preferred channel to port lateral mark");
/*  773 */     ((HashMap<String, String>)valoresAtributos.get("CATLIT")).put("1", "directional function");
/*  774 */     ((HashMap<String, String>)valoresAtributos.get("CATLIT")).put("4", "leading light");
/*  775 */     ((HashMap<String, String>)valoresAtributos.get("CATLIT")).put("5", "aero light");
/*  776 */     ((HashMap<String, String>)valoresAtributos.get("CATLIT")).put("6", "air obstruction light");
/*  777 */     ((HashMap<String, String>)valoresAtributos.get("CATLIT")).put("7", "fog detector light");
/*  778 */     ((HashMap<String, String>)valoresAtributos.get("CATLIT")).put("8", "flood light");
/*  779 */     ((HashMap<String, String>)valoresAtributos.get("CATLIT")).put("9", "strip light");
/*  780 */     ((HashMap<String, String>)valoresAtributos.get("CATLIT")).put("10", "subsidiary light");
/*  781 */     ((HashMap<String, String>)valoresAtributos.get("CATLIT")).put("11", "spotlight");
/*  782 */     ((HashMap<String, String>)valoresAtributos.get("CATLIT")).put("12", "front");
/*  783 */     ((HashMap<String, String>)valoresAtributos.get("CATLIT")).put("13", "rear");
/*  784 */     ((HashMap<String, String>)valoresAtributos.get("CATLIT")).put("14", "lower");
/*  785 */     ((HashMap<String, String>)valoresAtributos.get("CATLIT")).put("15", "upper");
/*  786 */     ((HashMap<String, String>)valoresAtributos.get("CATLIT")).put("16", "moir� effect");
/*  787 */     ((HashMap<String, String>)valoresAtributos.get("CATLIT")).put("17", "emergency");
/*  788 */     ((HashMap<String, String>)valoresAtributos.get("CATLIT")).put("18", "bearing light");
/*  789 */     ((HashMap<String, String>)valoresAtributos.get("CATLIT")).put("19", "horizontally disposed");
/*  790 */     ((HashMap<String, String>)valoresAtributos.get("CATLIT")).put("20", "vertically disposed");
/*  791 */     ((HashMap<String, String>)valoresAtributos.get("CATMFA")).put("1", "crustaceans");
/*  792 */     ((HashMap<String, String>)valoresAtributos.get("CATMFA")).put("2", "oyster/mussels");
/*  793 */     ((HashMap<String, String>)valoresAtributos.get("CATMFA")).put("3", "fish");
/*  794 */     ((HashMap<String, String>)valoresAtributos.get("CATMFA")).put("4", "seaweed");
/*  795 */     ((HashMap<String, String>)valoresAtributos.get("CATMPA")).put("2", "torpedo exercise area");
/*  796 */     ((HashMap<String, String>)valoresAtributos.get("CATMPA")).put("3", "submarine exercise area");
/*  797 */     ((HashMap<String, String>)valoresAtributos.get("CATMPA")).put("4", "firing danger area");
/*  798 */     ((HashMap<String, String>)valoresAtributos.get("CATMPA")).put("5", "mine-laying practice area");
/*  799 */     ((HashMap<String, String>)valoresAtributos.get("CATMPA")).put("6", "small arms firing range");
/*  800 */     ((HashMap<String, String>)valoresAtributos.get("CATMOR")).put("1", "dolphin");
/*  801 */     ((HashMap<String, String>)valoresAtributos.get("CATMOR")).put("2", "deviation dolphin");
/*  802 */     ((HashMap<String, String>)valoresAtributos.get("CATMOR")).put("3", "bollard");
/*  803 */     ((HashMap<String, String>)valoresAtributos.get("CATMOR")).put("4", "tie-up wall");
/*  804 */     ((HashMap<String, String>)valoresAtributos.get("CATMOR")).put("5", "post or pile");
/*  805 */     ((HashMap<String, String>)valoresAtributos.get("CATMOR")).put("6", "chain/wire/cable");
/*  806 */     ((HashMap<String, String>)valoresAtributos.get("CATMOR")).put("7", "mooring buoy");
/*  807 */     ((HashMap<String, String>)valoresAtributos.get("CATNAV")).put("1", "clearing line");
/*  808 */     ((HashMap<String, String>)valoresAtributos.get("CATNAV")).put("2", "transit line");
/*  809 */     ((HashMap<String, String>)valoresAtributos.get("CATNAV")).put("3", "leading line bearing a recommended track");
/*  810 */     ((HashMap<String, String>)valoresAtributos.get("CATOBS")).put("1", "snag / stump");
/*  811 */     ((HashMap<String, String>)valoresAtributos.get("CATOBS")).put("2", "wellhead");
/*  812 */     ((HashMap<String, String>)valoresAtributos.get("CATOBS")).put("3", "diffuser");
/*  813 */     ((HashMap<String, String>)valoresAtributos.get("CATOBS")).put("4", "crib");
/*  814 */     ((HashMap<String, String>)valoresAtributos.get("CATOBS")).put("5", "fish haven");
/*  815 */     ((HashMap<String, String>)valoresAtributos.get("CATOBS")).put("6", "foul area");
/*  816 */     ((HashMap<String, String>)valoresAtributos.get("CATOBS")).put("7", "foul ground");
/*  817 */     ((HashMap<String, String>)valoresAtributos.get("CATOBS")).put("8", "ice boom");
/*  818 */     ((HashMap<String, String>)valoresAtributos.get("CATOBS")).put("9", "ground tackle");
/*  819 */     ((HashMap<String, String>)valoresAtributos.get("CATOBS")).put("10", "boom");
/*  820 */     ((HashMap<String, String>)valoresAtributos.get("CATOFP")).put("1", "oil derrick / rig");
/*  821 */     ((HashMap<String, String>)valoresAtributos.get("CATOFP")).put("2", "production platform");
/*  822 */     ((HashMap<String, String>)valoresAtributos.get("CATOFP")).put("3", "observation / research platform");
/*  823 */     ((HashMap<String, String>)valoresAtributos.get("CATOFP")).put("4", "articulated loading platform (ALP)");
/*  824 */     ((HashMap<String, String>)valoresAtributos.get("CATOFP")).put("5", "single anchor leg mooring (SALM)");
/*  825 */     ((HashMap<String, String>)valoresAtributos.get("CATOFP")).put("6", "mooring tower");
/*  826 */     ((HashMap<String, String>)valoresAtributos.get("CATOFP")).put("7", "artificial island");
/*  827 */     ((HashMap<String, String>)valoresAtributos.get("CATOFP")).put("8", "floating production, storage and off-loading vessel (FPSO)");
/*  828 */     ((HashMap<String, String>)valoresAtributos.get("CATOFP")).put("9", "accomodation platform");
/*  829 */     ((HashMap<String, String>)valoresAtributos.get("CATOFP")).put("10", "navigation, communication and control buoy (NCCB)");
/*  830 */     ((HashMap<String, String>)valoresAtributos.get("CATOLB")).put("1", "oil retention (high pressure pipe)");
/*  831 */     ((HashMap<String, String>)valoresAtributos.get("CATOLB")).put("2", "floating oil barrier");
/*  832 */     ((HashMap<String, String>)valoresAtributos.get("CATPLE")).put("1", "stake");
/*  833 */     ((HashMap<String, String>)valoresAtributos.get("CATPLE")).put("3", "post");
/*  834 */     ((HashMap<String, String>)valoresAtributos.get("CATPLE")).put("4", "tripodal");
/*  835 */     ((HashMap<String, String>)valoresAtributos.get("CATPIL")).put("1", "boarding by pilot-cruising vessel");
/*  836 */     ((HashMap<String, String>)valoresAtributos.get("CATPIL")).put("2", "boarding by helicopter");
/*  837 */     ((HashMap<String, String>)valoresAtributos.get("CATPIL")).put("3", "pilot comes out from shore");
/*  838 */     ((HashMap<String, String>)valoresAtributos.get("CATPIP")).put("2", "outfall pipe");
/*  839 */     ((HashMap<String, String>)valoresAtributos.get("CATPIP")).put("3", "intake pipe");
/*  840 */     ((HashMap<String, String>)valoresAtributos.get("CATPIP")).put("4", "sewer");
/*  841 */     ((HashMap<String, String>)valoresAtributos.get("CATPIP")).put("5", "bubbler system");
/*  842 */     ((HashMap<String, String>)valoresAtributos.get("CATPIP")).put("6", "supply pipe");
/*  843 */     ((HashMap<String, String>)valoresAtributos.get("CATPRA")).put("1", "quarry");
/*  844 */     ((HashMap<String, String>)valoresAtributos.get("CATPRA")).put("2", "mine");
/*  845 */     ((HashMap<String, String>)valoresAtributos.get("CATPRA")).put("3", "stockpile");
/*  846 */     ((HashMap<String, String>)valoresAtributos.get("CATPRA")).put("4", "power station area");
/*  847 */     ((HashMap<String, String>)valoresAtributos.get("CATPRA")).put("5", "refinery area");
/*  848 */     ((HashMap<String, String>)valoresAtributos.get("CATPRA")).put("6", "timber yard");
/*  849 */     ((HashMap<String, String>)valoresAtributos.get("CATPRA")).put("7", "factory area");
/*  850 */     ((HashMap<String, String>)valoresAtributos.get("CATPRA")).put("8", "tank farm");
/*  851 */     ((HashMap<String, String>)valoresAtributos.get("CATPRA")).put("9", "wind farm");
/*  852 */     ((HashMap<String, String>)valoresAtributos.get("CATPYL")).put("1", "power transmission pylon/pole");
/*  853 */     ((HashMap<String, String>)valoresAtributos.get("CATPYL")).put("2", "telephone/telegraph pylon/pole");
/*  854 */     ((HashMap<String, String>)valoresAtributos.get("CATPYL")).put("3", "aerial cableway/sky pylon");
/*  855 */     ((HashMap<String, String>)valoresAtributos.get("CATPYL")).put("4", "bridge pylon/tower");
/*  856 */     ((HashMap<String, String>)valoresAtributos.get("CATPYL")).put("5", "bridge pier");
/*  857 */     ((HashMap<String, String>)valoresAtributos.get("CATRAS")).put("1", "radar surveillance station");
/*  858 */     ((HashMap<String, String>)valoresAtributos.get("CATRAS")).put("2", "coast radar station");
/*  859 */     ((HashMap<String, String>)valoresAtributos.get("CATRTB")).put("1", "ramark, radar beacon transmitting continuously");
/*  860 */     ((HashMap<String, String>)valoresAtributos.get("CATRTB")).put("2", "racon, radar transponder beacon");
/*  861 */     ((HashMap<String, String>)valoresAtributos.get("CATRTB")).put("3", "leading racon/radar transponder beacon");
/*  862 */     ((HashMap<String, String>)valoresAtributos.get("CATROS")).put("1", "circular (non-directional) marine or aero-marine radiobeacon");
/*  863 */     ((HashMap<String, String>)valoresAtributos.get("CATROS")).put("2", "directional radiobeacon");
/*  864 */     ((HashMap<String, String>)valoresAtributos.get("CATROS")).put("3", "rotating-pattern radiobeacon");
/*  865 */     ((HashMap<String, String>)valoresAtributos.get("CATROS")).put("4", "Consol beacon");
/*  866 */     ((HashMap<String, String>)valoresAtributos.get("CATROS")).put("5", "radio direction-finding station");
/*  867 */     ((HashMap<String, String>)valoresAtributos.get("CATROS")).put("6", "coast radio station providing QTG service");
/*  868 */     ((HashMap<String, String>)valoresAtributos.get("CATROS")).put("7", "aeronautical radiobeacon");
/*  869 */     ((HashMap<String, String>)valoresAtributos.get("CATROS")).put("8", "Decca");
/*  870 */     ((HashMap<String, String>)valoresAtributos.get("CATROS")).put("9", "Loran C");
/*  871 */     ((HashMap<String, String>)valoresAtributos.get("CATROS")).put("10", "Differential GPS");
/*  872 */     ((HashMap<String, String>)valoresAtributos.get("CATROS")).put("11", "Toran");
/*  873 */     ((HashMap<String, String>)valoresAtributos.get("CATROS")).put("12", "Omega");
/*  874 */     ((HashMap<String, String>)valoresAtributos.get("CATROS")).put("13", "Syledis");
/*  875 */     ((HashMap<String, String>)valoresAtributos.get("CATROS")).put("14", "Chaika (Chayka)");
/*  876 */     ((HashMap<String, String>)valoresAtributos.get("CATTRK")).put("1", "based on a system of fixed marks");
/*  877 */     ((HashMap<String, String>)valoresAtributos.get("CATTRK")).put("2", "not based on a system of fixed marks");
/*  878 */     ((HashMap<String, String>)valoresAtributos.get("CATRSC")).put("1", "rescue station with lifeboat");
/*  879 */     ((HashMap<String, String>)valoresAtributos.get("CATRSC")).put("2", "rescue station with rocket");
/*  880 */     ((HashMap<String, String>)valoresAtributos.get("CATRSC")).put("4", "refuge for shipwrecked mariners");
/*  881 */     ((HashMap<String, String>)valoresAtributos.get("CATRSC")).put("5", "refuge for intertidal area walkers");
/*  882 */     ((HashMap<String, String>)valoresAtributos.get("CATRSC")).put("6", "lifeboat lying at a mooring");
/*  883 */     ((HashMap<String, String>)valoresAtributos.get("CATREA")).put("1", "offshore safety zone");
/*  884 */     ((HashMap<String, String>)valoresAtributos.get("CATREA")).put("4", "nature reserve");
/*  885 */     ((HashMap<String, String>)valoresAtributos.get("CATREA")).put("5", "bird sanctuary");
/*  886 */     ((HashMap<String, String>)valoresAtributos.get("CATREA")).put("6", "game preserve");
/*  887 */     ((HashMap<String, String>)valoresAtributos.get("CATREA")).put("7", "seal sanctuary");
/*  888 */     ((HashMap<String, String>)valoresAtributos.get("CATREA")).put("8", "degaussing range");
/*  889 */     ((HashMap<String, String>)valoresAtributos.get("CATREA")).put("9", "military area");
/*  890 */     ((HashMap<String, String>)valoresAtributos.get("CATREA")).put("10", "historic wreck area");
/*  891 */     ((HashMap<String, String>)valoresAtributos.get("CATREA")).put("12", "navigational aid safety zone");
/*  892 */     ((HashMap<String, String>)valoresAtributos.get("CATREA")).put("14", "minefield");
/*  893 */     ((HashMap<String, String>)valoresAtributos.get("CATREA")).put("18", "swimming area");
/*  894 */     ((HashMap<String, String>)valoresAtributos.get("CATREA")).put("19", "waiting area");
/*  895 */     ((HashMap<String, String>)valoresAtributos.get("CATREA")).put("20", "research area");
/*  896 */     ((HashMap<String, String>)valoresAtributos.get("CATREA")).put("21", "dredging area");
/*  897 */     ((HashMap<String, String>)valoresAtributos.get("CATREA")).put("22", "fish sanctuary");
/*  898 */     ((HashMap<String, String>)valoresAtributos.get("CATREA")).put("23", "ecological reserve");
/*  899 */     ((HashMap<String, String>)valoresAtributos.get("CATREA")).put("24", "no wake area");
/*  900 */     ((HashMap<String, String>)valoresAtributos.get("CATREA")).put("25", "swinging area");
/*  901 */     ((HashMap<String, String>)valoresAtributos.get("CATREA")).put("26", "water skiing area");
/*  902 */     ((HashMap<String, String>)valoresAtributos.get("CATROD")).put("1", "motorway");
/*  903 */     ((HashMap<String, String>)valoresAtributos.get("CATROD")).put("2", "major road");
/*  904 */     ((HashMap<String, String>)valoresAtributos.get("CATROD")).put("3", "minor road");
/*  905 */     ((HashMap<String, String>)valoresAtributos.get("CATROD")).put("4", "track / path");
/*  906 */     ((HashMap<String, String>)valoresAtributos.get("CATROD")).put("5", "major street");
/*  907 */     ((HashMap<String, String>)valoresAtributos.get("CATROD")).put("6", "minor street");
/*  908 */     ((HashMap<String, String>)valoresAtributos.get("CATROD")).put("7", "crossing");
/*  909 */     ((HashMap<String, String>)valoresAtributos.get("CATRUN")).put("1", "aeroplane");
/*  910 */     ((HashMap<String, String>)valoresAtributos.get("CATRUN")).put("2", "helicopter landing pad");
/*  911 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("2", "gat");
/*  912 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("3", "bank");
/*  913 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("4", "deep");
/*  914 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("5", "bay");
/*  915 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("6", "trench");
/*  916 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("7", "basin");
/*  917 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("8", "mud flats");
/*  918 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("9", "reef");
/*  919 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("10", "ledge");
/*  920 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("11", "canyon");
/*  921 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("12", "narrows");
/*  922 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("13", "shoal");
/*  923 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("14", "knoll");
/*  924 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("15", "ridge");
/*  925 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("16", "seamount");
/*  926 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("17", "pinnacle");
/*  927 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("18", "abyssal plain");
/*  928 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("19", "plateau");
/*  929 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("20", "spur");
/*  930 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("21", "shelf");
/*  931 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("22", "trough");
/*  932 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("23", "saddle");
/*  933 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("24", "abyssal hills");
/*  934 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("25", "apron");
/*  935 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("26", "archipelagic apron");
/*  936 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("27", "borderland");
/*  937 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("28", "continental margin");
/*  938 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("29", "continental rise");
/*  939 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("30", "escarpment");
/*  940 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("31", "fan");
/*  941 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("32", "fracture zone");
/*  942 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("33", "gap");
/*  943 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("34", "guyot");
/*  944 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("35", "hill");
/*  945 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("36", "hole");
/*  946 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("37", "levee");
/*  947 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("38", "median valley");
/*  948 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("39", "moat");
/*  949 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("40", "mountains");
/*  950 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("41", "peak");
/*  951 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("42", "province");
/*  952 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("43", "rise");
/*  953 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("44", "seachannel");
/*  954 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("45", "seamount chain");
/*  955 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("46", "shelf edge");
/*  956 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("47", "sill");
/*  957 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("48", "slope");
/*  958 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("49", "terrace");
/*  959 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("50", "valley");
/*  960 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("51", "canal");
/*  961 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("52", "lake");
/*  962 */     ((HashMap<String, String>)valoresAtributos.get("CATSEA")).put("53", "river");
/*  963 */     ((HashMap<String, String>)valoresAtributos.get("CATSLC")).put("1", "breakwater");
/*  964 */     ((HashMap<String, String>)valoresAtributos.get("CATSLC")).put("2", "groyne (groin)");
/*  965 */     ((HashMap<String, String>)valoresAtributos.get("CATSLC")).put("3", "mole");
/*  966 */     ((HashMap<String, String>)valoresAtributos.get("CATSLC")).put("4", "pier ( jetty)");
/*  967 */     ((HashMap<String, String>)valoresAtributos.get("CATSLC")).put("5", "promenadepier");
/*  968 */     ((HashMap<String, String>)valoresAtributos.get("CATSLC")).put("6", "wharf (quay)");
/*  969 */     ((HashMap<String, String>)valoresAtributos.get("CATSLC")).put("7", "training wall");
/*  970 */     ((HashMap<String, String>)valoresAtributos.get("CATSLC")).put("8", "rip rap");
/*  971 */     ((HashMap<String, String>)valoresAtributos.get("CATSLC")).put("9", "revetment");
/*  972 */     ((HashMap<String, String>)valoresAtributos.get("CATSLC")).put("10", "sea wall");
/*  973 */     ((HashMap<String, String>)valoresAtributos.get("CATSLC")).put("11", "landing steps");
/*  974 */     ((HashMap<String, String>)valoresAtributos.get("CATSLC")).put("12", "ramp");
/*  975 */     ((HashMap<String, String>)valoresAtributos.get("CATSLC")).put("13", "slipway");
/*  976 */     ((HashMap<String, String>)valoresAtributos.get("CATSLC")).put("14", "fender");
/*  977 */     ((HashMap<String, String>)valoresAtributos.get("CATSLC")).put("15", "solid face wharf");
/*  978 */     ((HashMap<String, String>)valoresAtributos.get("CATSLC")).put("16", "open face wharf");
/*  979 */     ((HashMap<String, String>)valoresAtributos.get("CATSLC")).put("17", "log ramp");
/*  980 */     ((HashMap<String, String>)valoresAtributos.get("CATSIT")).put("1", "port control");
/*  981 */     ((HashMap<String, String>)valoresAtributos.get("CATSIT")).put("2", "port entry and departure");
/*  982 */     ((HashMap<String, String>)valoresAtributos.get("CATSIT")).put("3", "International Port Traffic");
/*  983 */     ((HashMap<String, String>)valoresAtributos.get("CATSIT")).put("4", "berthing");
/*  984 */     ((HashMap<String, String>)valoresAtributos.get("CATSIT")).put("5", "dock");
/*  985 */     ((HashMap<String, String>)valoresAtributos.get("CATSIT")).put("6", "lock");
/*  986 */     ((HashMap<String, String>)valoresAtributos.get("CATSIT")).put("7", "flood barrage");
/*  987 */     ((HashMap<String, String>)valoresAtributos.get("CATSIT")).put("8", "bridge passage");
/*  988 */     ((HashMap<String, String>)valoresAtributos.get("CATSIT")).put("9", "dredging");
/*  989 */     ((HashMap<String, String>)valoresAtributos.get("CATSIT")).put("10", "oncomig traffic indication");
/*  990 */     ((HashMap<String, String>)valoresAtributos.get("CATSIW")).put("1", "danger");
/*  991 */     ((HashMap<String, String>)valoresAtributos.get("CATSIW")).put("2", "maritime obstruction");
/*  992 */     ((HashMap<String, String>)valoresAtributos.get("CATSIW")).put("3", "cable");
/*  993 */     ((HashMap<String, String>)valoresAtributos.get("CATSIW")).put("4", "military practice");
/*  994 */     ((HashMap<String, String>)valoresAtributos.get("CATSIW")).put("5", "distress");
/*  995 */     ((HashMap<String, String>)valoresAtributos.get("CATSIW")).put("6", "weather");
/*  996 */     ((HashMap<String, String>)valoresAtributos.get("CATSIW")).put("7", "storm");
/*  997 */     ((HashMap<String, String>)valoresAtributos.get("CATSIW")).put("8", "ice");
/*  998 */     ((HashMap<String, String>)valoresAtributos.get("CATSIW")).put("9", "time");
/*  999 */     ((HashMap<String, String>)valoresAtributos.get("CATSIW")).put("10", "tide");
/* 1000 */     ((HashMap<String, String>)valoresAtributos.get("CATSIW")).put("11", "tidal stream");
/* 1001 */     ((HashMap<String, String>)valoresAtributos.get("CATSIW")).put("12", "tide gauge");
/* 1002 */     ((HashMap<String, String>)valoresAtributos.get("CATSIW")).put("13", "tide scale");
/* 1003 */     ((HashMap<String, String>)valoresAtributos.get("CATSIW")).put("14", "diving");
/* 1004 */     ((HashMap<String, String>)valoresAtributos.get("CATSIL")).put("1", "silo in general");
/* 1005 */     ((HashMap<String, String>)valoresAtributos.get("CATSIL")).put("2", "tank in general");
/* 1006 */     ((HashMap<String, String>)valoresAtributos.get("CATSIL")).put("3", "grain elevator");
/* 1007 */     ((HashMap<String, String>)valoresAtributos.get("CATSIL")).put("4", "water tower");
/* 1008 */     ((HashMap<String, String>)valoresAtributos.get("CATSLO")).put("1", "cutting");
/* 1009 */     ((HashMap<String, String>)valoresAtributos.get("CATSLO")).put("2", "embankment");
/* 1010 */     ((HashMap<String, String>)valoresAtributos.get("CATSLO")).put("3", "dune");
/* 1011 */     ((HashMap<String, String>)valoresAtributos.get("CATSLO")).put("4", "hill");
/* 1012 */     ((HashMap<String, String>)valoresAtributos.get("CATSLO")).put("5", "pingo");
/* 1013 */     ((HashMap<String, String>)valoresAtributos.get("CATSLO")).put("6", "cliff");
/* 1014 */     ((HashMap<String, String>)valoresAtributos.get("CATSLO")).put("7", "scree");
/* 1015 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("1", "visitor`s berth");
/* 1016 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("2", "nautical club");
/* 1017 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("3", "boat hoist");
/* 1018 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("4", "sailmaker");
/* 1019 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("5", "boatyard");
/* 1020 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("6", "public inn");
/* 1021 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("7", "restaurant");
/* 1022 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("8", "chandler");
/* 1023 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("9", "provisions");
/* 1024 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("10", "doctor");
/* 1025 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("11", "pharmacy");
/* 1026 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("12", "water tap");
/* 1027 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("13", "fuel station");
/* 1028 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("14", "electricity");
/* 1029 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("15", "bottle gas");
/* 1030 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("16", "showers");
/* 1031 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("17", "launderette");
/* 1032 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("18", "public toilets");
/* 1033 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("19", "post box");
/* 1034 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("20", "public telephone");
/* 1035 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("21", "refuse bin");
/* 1036 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("22", "car park");
/* 1037 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("23", "parking for boats and trailers");
/* 1038 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("24", "caravan site");
/* 1039 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("25", "camping site");
/* 1040 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("26", "sewerage pump-out station");
/* 1041 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("27", "emergency telephone");
/* 1042 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("28", "landing / launching place for boats");
/* 1043 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("29", "visitors mooring");
/* 1044 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("30", "scrubbing berth");
/* 1045 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("31", "picnic area");
/* 1046 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("32", "mechanics workshop");
/* 1047 */     ((HashMap<String, String>)valoresAtributos.get("CATSCF")).put("33", "guard and/or security service");
/* 1048 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("1", "firing danger area mark");
/* 1049 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("2", "target mark");
/* 1050 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("3", "marker ship mark");
/* 1051 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("4", "degaussing range mark");
/* 1052 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("5", "barge mark");
/* 1053 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("6", "cable mark");
/* 1054 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("7", "spoil ground mark");
/* 1055 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("8", "outfall mark");
/* 1056 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("9", "ODAS (Ocean-Data-Acquisition-System)");
/* 1057 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("10", "recording mark");
/* 1058 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("11", "seaplane anchorage mark");
/* 1059 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("12", "recreation zone mark");
/* 1060 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("13", "private mark");
/* 1061 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("14", "mooring mark");
/* 1062 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("15", "LANBY (Large Automatic Navigational Buoy)");
/* 1063 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("16", "leading mark");
/* 1064 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("17", "measured distance mark");
/* 1065 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("18", "notice mark");
/* 1066 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("19", "TSS mark (Traffic Separation Scheme)");
/* 1067 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("20", "anchoring prohibited mark");
/* 1068 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("21", "berthing prohibited mark");
/* 1069 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("22", "overtaking prohibited mark");
/* 1070 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("23", "two-way traffic prohibited mark");
/* 1071 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("24", "reduced wake' mark");
/* 1072 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("25", "speed limit mark");
/* 1073 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("26", "stop mark");
/* 1074 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("27", "general warning mark");
/* 1075 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("28", "sound ship's siren' mark");
/* 1076 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("29", "restricted vertical clearence mark");
/* 1077 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("30", "maximum vessel's draught mark");
/* 1078 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("31", "restricted horizontal clearance mark");
/* 1079 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("32", "strong current warning mark");
/* 1080 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("33", "berthing permitted mark");
/* 1081 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("34", "overhead power cable mark");
/* 1082 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("35", "channel edge gradient' mark");
/* 1083 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("36", "telephone mark");
/* 1084 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("37", "ferry crossing mark");
/* 1085 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("39", "pipline mark");
/* 1086 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("40", "anchorage mark");
/* 1087 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("41", "clearing mark");
/* 1088 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("42", "control mark");
/* 1089 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("43", "diving mark");
/* 1090 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("44", "refuge beacon");
/* 1091 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("45", "foul ground mark");
/* 1092 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("46", "yachting mark");
/* 1093 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("47", "heliport mark");
/* 1094 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("48", "GPS mark");
/* 1095 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("49", "seaplane landing mark");
/* 1096 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("50", "entry prohibited mark");
/* 1097 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("51", "work in progress mark");
/* 1098 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("52", "mark with unknown purpose");
/* 1099 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("54", "channel separation mark");
/* 1100 */     ((HashMap<String, String>)valoresAtributos.get("CATSPM")).put("55", "marine farm mark");
/* 1101 */     ((HashMap<String, String>)valoresAtributos.get("CATTSS")).put("1", "IMO - adopted");
/* 1102 */     ((HashMap<String, String>)valoresAtributos.get("CATTSS")).put("2", "not IMO - adopted");
/* 1103 */     ((HashMap<String, String>)valoresAtributos.get("CATVEG")).put("1", "grassland");
/* 1104 */     ((HashMap<String, String>)valoresAtributos.get("CATVEG")).put("3", "bush");
/* 1105 */     ((HashMap<String, String>)valoresAtributos.get("CATVEG")).put("4", "deciduous wood");
/* 1106 */     ((HashMap<String, String>)valoresAtributos.get("CATVEG")).put("5", "coniferous  wood");
/* 1107 */     ((HashMap<String, String>)valoresAtributos.get("CATVEG")).put("6", "wood in general (inc mixed wood)");
/* 1108 */     ((HashMap<String, String>)valoresAtributos.get("CATVEG")).put("7", "mangroves");
/* 1109 */     ((HashMap<String, String>)valoresAtributos.get("CATVEG")).put("10", "mixed crops");
/* 1110 */     ((HashMap<String, String>)valoresAtributos.get("CATVEG")).put("11", "reed");
/* 1111 */     ((HashMap<String, String>)valoresAtributos.get("CATVEG")).put("12", "moos");
/* 1112 */     ((HashMap<String, String>)valoresAtributos.get("CATVEG")).put("13", "tree in general");
/* 1113 */     ((HashMap<String, String>)valoresAtributos.get("CATVEG")).put("14", "evergreen tree");
/* 1114 */     ((HashMap<String, String>)valoresAtributos.get("CATVEG")).put("15", "coniferous tree");
/* 1115 */     ((HashMap<String, String>)valoresAtributos.get("CATVEG")).put("16", "palm tree");
/* 1116 */     ((HashMap<String, String>)valoresAtributos.get("CATVEG")).put("17", "nipa palm tree");
/* 1117 */     ((HashMap<String, String>)valoresAtributos.get("CATVEG")).put("18", "casuarina tree");
/* 1118 */     ((HashMap<String, String>)valoresAtributos.get("CATVEG")).put("19", "eucalypt tree");
/* 1119 */     ((HashMap<String, String>)valoresAtributos.get("CATVEG")).put("20", "deciduous tree");
/* 1120 */     ((HashMap<String, String>)valoresAtributos.get("CATVEG")).put("21", "mangrove tree");
/* 1121 */     ((HashMap<String, String>)valoresAtributos.get("CATVEG")).put("22", "filao tree");
/* 1122 */     ((HashMap<String, String>)valoresAtributos.get("CATWAT")).put("1", "breakers");
/* 1123 */     ((HashMap<String, String>)valoresAtributos.get("CATWAT")).put("2", "eddies");
/* 1124 */     ((HashMap<String, String>)valoresAtributos.get("CATWAT")).put("3", "overfalls");
/* 1125 */     ((HashMap<String, String>)valoresAtributos.get("CATWAT")).put("4", "tide rips");
/* 1126 */     ((HashMap<String, String>)valoresAtributos.get("CATWAT")).put("5", "bombora");
/* 1127 */     ((HashMap<String, String>)valoresAtributos.get("CATWED")).put("1", "kelp");
/* 1128 */     ((HashMap<String, String>)valoresAtributos.get("CATWED")).put("2", "sea weed");
/* 1129 */     ((HashMap<String, String>)valoresAtributos.get("CATWED")).put("3", "sea grass");
/* 1130 */     ((HashMap<String, String>)valoresAtributos.get("CATWED")).put("4", "saragasso");
/* 1131 */     ((HashMap<String, String>)valoresAtributos.get("CATWRK")).put("1", "non-dangerous wreck");
/* 1132 */     ((HashMap<String, String>)valoresAtributos.get("CATWRK")).put("2", "dangerous wreck");
/* 1133 */     ((HashMap<String, String>)valoresAtributos.get("CATWRK")).put("3", "distributed remains of wreck");
/* 1134 */     ((HashMap<String, String>)valoresAtributos.get("CATWRK")).put("4", "wreck showing mast/masts");
/* 1135 */     ((HashMap<String, String>)valoresAtributos.get("CATWRK")).put("5", "wreck showing any portion of hull or superstructure");
/* 1136 */     ((HashMap<String, String>)valoresAtributos.get("CATZOC")).put("1", "zone of confidence A1");
/* 1137 */     ((HashMap<String, String>)valoresAtributos.get("CATZOC")).put("2", "zone of confidence A2");
/* 1138 */     ((HashMap<String, String>)valoresAtributos.get("CATZOC")).put("3", "zone of confidence B");
/* 1139 */     ((HashMap<String, String>)valoresAtributos.get("CATZOC")).put("4", "zone of confidence C");
/* 1140 */     ((HashMap<String, String>)valoresAtributos.get("CATZOC")).put("5", "zone of confidence D");
/* 1141 */     ((HashMap<String, String>)valoresAtributos.get("CATZOC")).put("6", "zone of confidence U (data not assessed)");
/* 1142 */     ((HashMap<String, String>)valoresAtributos.get("COLOUR")).put("1", "white");
/* 1143 */     ((HashMap<String, String>)valoresAtributos.get("COLOUR")).put("2", "black");
/* 1144 */     ((HashMap<String, String>)valoresAtributos.get("COLOUR")).put("3", "red");
/* 1145 */     ((HashMap<String, String>)valoresAtributos.get("COLOUR")).put("4", "green");
/* 1146 */     ((HashMap<String, String>)valoresAtributos.get("COLOUR")).put("5", "blue");
/* 1147 */     ((HashMap<String, String>)valoresAtributos.get("COLOUR")).put("6", "yellow");
/* 1148 */     ((HashMap<String, String>)valoresAtributos.get("COLOUR")).put("7", "grey");
/* 1149 */     ((HashMap<String, String>)valoresAtributos.get("COLOUR")).put("8", "brown");
/* 1150 */     ((HashMap<String, String>)valoresAtributos.get("COLOUR")).put("9", "amber");
/* 1151 */     ((HashMap<String, String>)valoresAtributos.get("COLOUR")).put("10", "violet");
/* 1152 */     ((HashMap<String, String>)valoresAtributos.get("COLOUR")).put("11", "orange");
/* 1153 */     ((HashMap<String, String>)valoresAtributos.get("COLOUR")).put("12", "magenta");
/* 1154 */     ((HashMap<String, String>)valoresAtributos.get("COLOUR")).put("13", "pink");
/* 1155 */     ((HashMap<String, String>)valoresAtributos.get("COLPAT")).put("1", "horizontal stripes");
/* 1156 */     ((HashMap<String, String>)valoresAtributos.get("COLPAT")).put("2", "vertical stripes");
/* 1157 */     ((HashMap<String, String>)valoresAtributos.get("COLPAT")).put("3", "diagonal stripes");
/* 1158 */     ((HashMap<String, String>)valoresAtributos.get("COLPAT")).put("4", "squared");
/* 1159 */     ((HashMap<String, String>)valoresAtributos.get("COLPAT")).put("5", "stripes (direction unknown)");
/* 1160 */     ((HashMap<String, String>)valoresAtributos.get("COLPAT")).put("6", "border stripes");
/* 1161 */     ((HashMap<String, String>)valoresAtributos.get("CONDTN")).put("1", "under construction");
/* 1162 */     ((HashMap<String, String>)valoresAtributos.get("CONDTN")).put("2", "ruined");
/* 1163 */     ((HashMap<String, String>)valoresAtributos.get("CONDTN")).put("3", "under reclamation");
/* 1164 */     ((HashMap<String, String>)valoresAtributos.get("CONDTN")).put("4", "wingless");
/* 1165 */     ((HashMap<String, String>)valoresAtributos.get("CONDTN")).put("5", "planned construction");
/* 1166 */     ((HashMap<String, String>)valoresAtributos.get("CONRAD")).put("1", "radar conspicuous");
/* 1167 */     ((HashMap<String, String>)valoresAtributos.get("CONRAD")).put("2", "not radar conspicuous");
/* 1168 */     ((HashMap<String, String>)valoresAtributos.get("CONRAD")).put("3", "radar conspicuous (has radar reflector)");
/* 1169 */     ((HashMap<String, String>)valoresAtributos.get("CONVIS")).put("1", "visual conspicuous");
/* 1170 */     ((HashMap<String, String>)valoresAtributos.get("CONVIS")).put("2", "not visual conspicuous");
/* 1171 */     ((HashMap<String, String>)valoresAtributos.get("EXCLIT")).put("1", "light shown without change of character");
/* 1172 */     ((HashMap<String, String>)valoresAtributos.get("EXCLIT")).put("2", "daytime light");
/* 1173 */     ((HashMap<String, String>)valoresAtributos.get("EXCLIT")).put("3", "fog light");
/* 1174 */     ((HashMap<String, String>)valoresAtributos.get("EXCLIT")).put("4", "night light");
/* 1175 */     ((HashMap<String, String>)valoresAtributos.get("EXPSOU")).put("1", "within the range of depth of the surrounding depth area");
/* 1176 */     ((HashMap<String, String>)valoresAtributos.get("EXPSOU")).put("2", "shoaler than range of depth of the surrounding depth area");
/* 1177 */     ((HashMap<String, String>)valoresAtributos.get("EXPSOU")).put("3", "deeper than range of depth of the surrounding depth area");
/* 1178 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("2", "harbour-master's office");
/* 1179 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("3", "custom office");
/* 1180 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("4", "health office");
/* 1181 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("5", "hospital");
/* 1182 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("6", "post office");
/* 1183 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("7", "hotel");
/* 1184 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("8", "railway station");
/* 1185 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("9", "police station");
/* 1186 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("10", "water-police station");
/* 1187 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("11", "pilot office");
/* 1188 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("12", "pilot lookout");
/* 1189 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("13", "bank office");
/* 1190 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("14", "headquarters for district control");
/* 1191 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("15", "transit shed/warehouse");
/* 1192 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("16", "factory");
/* 1193 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("17", "power station");
/* 1194 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("18", "administrative");
/* 1195 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("19", "educational facility");
/* 1196 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("20", "church");
/* 1197 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("21", "chapel");
/* 1198 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("22", "temple");
/* 1199 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("23", "pagoda");
/* 1200 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("24", "shinto shrine");
/* 1201 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("25", "buddhist temple");
/* 1202 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("26", "mosque");
/* 1203 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("27", "marabout");
/* 1204 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("28", "lookout");
/* 1205 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("29", "communication");
/* 1206 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("30", "television");
/* 1207 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("31", "radio");
/* 1208 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("32", "radar");
/* 1209 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("33", "light support");
/* 1210 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("34", "microwave");
/* 1211 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("35", "cooling");
/* 1212 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("36", "observation");
/* 1213 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("37", "timeball");
/* 1214 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("38", "clock");
/* 1215 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("39", "control");
/* 1216 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("40", "airship mooring");
/* 1217 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("41", "stadium");
/* 1218 */     ((HashMap<String, String>)valoresAtributos.get("FUNCTN")).put("42", "bus station");
/* 1219 */     ((HashMap<String, String>)valoresAtributos.get("JRSDTN")).put("1", "international");
/* 1220 */     ((HashMap<String, String>)valoresAtributos.get("JRSDTN")).put("2", "national");
/* 1221 */     ((HashMap<String, String>)valoresAtributos.get("JRSDTN")).put("3", "national sub-division");
/* 1222 */     ((HashMap<String, String>)valoresAtributos.get("LITCHR")).put("1", "fixed");
/* 1223 */     ((HashMap<String, String>)valoresAtributos.get("LITCHR")).put("2", "flashing");
/* 1224 */     ((HashMap<String, String>)valoresAtributos.get("LITCHR")).put("3", "long-flashing");
/* 1225 */     ((HashMap<String, String>)valoresAtributos.get("LITCHR")).put("4", "quick-flashing");
/* 1226 */     ((HashMap<String, String>)valoresAtributos.get("LITCHR")).put("5", "very quick-flashing");
/* 1227 */     ((HashMap<String, String>)valoresAtributos.get("LITCHR")).put("6", "ultra quick-flashing");
/* 1228 */     ((HashMap<String, String>)valoresAtributos.get("LITCHR")).put("7", "isophased");
/* 1229 */     ((HashMap<String, String>)valoresAtributos.get("LITCHR")).put("8", "occulting");
/* 1230 */     ((HashMap<String, String>)valoresAtributos.get("LITCHR")).put("9", "interrupted quick-flashing");
/* 1231 */     ((HashMap<String, String>)valoresAtributos.get("LITCHR")).put("10", "interrupted very quick-flashing");
/* 1232 */     ((HashMap<String, String>)valoresAtributos.get("LITCHR")).put("11", "interrupted ultra quick-flashing");
/* 1233 */     ((HashMap<String, String>)valoresAtributos.get("LITCHR")).put("12", "morse");
/* 1234 */     ((HashMap<String, String>)valoresAtributos.get("LITCHR")).put("13", "fixed / flash");
/* 1235 */     ((HashMap<String, String>)valoresAtributos.get("LITCHR")).put("14", "flash / long-flash");
/* 1236 */     ((HashMap<String, String>)valoresAtributos.get("LITCHR")).put("15", "occulting / flash");
/* 1237 */     ((HashMap<String, String>)valoresAtributos.get("LITCHR")).put("16", "fixed / long-flash");
/* 1238 */     ((HashMap<String, String>)valoresAtributos.get("LITCHR")).put("17", "occulting alternating");
/* 1239 */     ((HashMap<String, String>)valoresAtributos.get("LITCHR")).put("18", "long-flash alternating");
/* 1240 */     ((HashMap<String, String>)valoresAtributos.get("LITCHR")).put("19", "flash alternating");
/* 1241 */     ((HashMap<String, String>)valoresAtributos.get("LITCHR")).put("20", "group alternating");
/* 1242 */     ((HashMap<String, String>)valoresAtributos.get("LITCHR")).put("25", "quick-flash plus long-flash");
/* 1243 */     ((HashMap<String, String>)valoresAtributos.get("LITCHR")).put("26", "very quick-flash plus long-flash");
/* 1244 */     ((HashMap<String, String>)valoresAtributos.get("LITCHR")).put("27", "ultra quick-flash plus long-flash");
/* 1245 */     ((HashMap<String, String>)valoresAtributos.get("LITCHR")).put("28", "alternating");
/* 1246 */     ((HashMap<String, String>)valoresAtributos.get("LITCHR")).put("29", "fixed and alternating flashing");
/* 1247 */     ((HashMap<String, String>)valoresAtributos.get("LITVIS")).put("1", "high intensity");
/* 1248 */     ((HashMap<String, String>)valoresAtributos.get("LITVIS")).put("2", "low intensity");
/* 1249 */     ((HashMap<String, String>)valoresAtributos.get("LITVIS")).put("3", "faint");
/* 1250 */     ((HashMap<String, String>)valoresAtributos.get("LITVIS")).put("4", "intensified");
/* 1251 */     ((HashMap<String, String>)valoresAtributos.get("LITVIS")).put("5", "unintensified");
/* 1252 */     ((HashMap<String, String>)valoresAtributos.get("LITVIS")).put("6", "visibility deliberately restricted");
/* 1253 */     ((HashMap<String, String>)valoresAtributos.get("LITVIS")).put("7", "obscured");
/* 1254 */     ((HashMap<String, String>)valoresAtributos.get("LITVIS")).put("8", "partially obscured");
/* 1255 */     ((HashMap<String, String>)valoresAtributos.get("MARSYS")).put("1", "IALA A");
/* 1256 */     ((HashMap<String, String>)valoresAtributos.get("MARSYS")).put("2", "IALA B");
/* 1257 */     ((HashMap<String, String>)valoresAtributos.get("MARSYS")).put("3", "modified US");
/* 1258 */     ((HashMap<String, String>)valoresAtributos.get("MARSYS")).put("4", "old US");
/* 1259 */     ((HashMap<String, String>)valoresAtributos.get("MARSYS")).put("5", "US intracoastal waterway");
/* 1260 */     ((HashMap<String, String>)valoresAtributos.get("MARSYS")).put("6", "US uniform state");
/* 1261 */     ((HashMap<String, String>)valoresAtributos.get("MARSYS")).put("7", "US western rivers");
/* 1262 */     ((HashMap<String, String>)valoresAtributos.get("MARSYS")).put("8", "SIGNI");
/* 1263 */     ((HashMap<String, String>)valoresAtributos.get("MARSYS")).put("9", "no system");
/* 1264 */     ((HashMap<String, String>)valoresAtributos.get("MARSYS")).put("10", "other sytem");
/* 1265 */     ((HashMap<String, String>)valoresAtributos.get("NATCON")).put("1", "masonry");
/* 1266 */     ((HashMap<String, String>)valoresAtributos.get("NATCON")).put("2", "concreted");
/* 1267 */     ((HashMap<String, String>)valoresAtributos.get("NATCON")).put("3", "loose boulders");
/* 1268 */     ((HashMap<String, String>)valoresAtributos.get("NATCON")).put("4", "hard surfaced");
/* 1269 */     ((HashMap<String, String>)valoresAtributos.get("NATCON")).put("5", "unsurfaced");
/* 1270 */     ((HashMap<String, String>)valoresAtributos.get("NATCON")).put("6", "wooden");
/* 1271 */     ((HashMap<String, String>)valoresAtributos.get("NATCON")).put("7", "metal");
/* 1272 */     ((HashMap<String, String>)valoresAtributos.get("NATCON")).put("8", "glass reinforced plastic (GRP)");
/* 1273 */     ((HashMap<String, String>)valoresAtributos.get("NATCON")).put("9", "painted");
/* 1274 */     ((HashMap<String, String>)valoresAtributos.get("NATSUR")).put("1", "mud");
/* 1275 */     ((HashMap<String, String>)valoresAtributos.get("NATSUR")).put("2", "clay");
/* 1276 */     ((HashMap<String, String>)valoresAtributos.get("NATSUR")).put("3", "silt");
/* 1277 */     ((HashMap<String, String>)valoresAtributos.get("NATSUR")).put("4", "sand");
/* 1278 */     ((HashMap<String, String>)valoresAtributos.get("NATSUR")).put("5", "stone");
/* 1279 */     ((HashMap<String, String>)valoresAtributos.get("NATSUR")).put("6", "gravel");
/* 1280 */     ((HashMap<String, String>)valoresAtributos.get("NATSUR")).put("7", "pebbles");
/* 1281 */     ((HashMap<String, String>)valoresAtributos.get("NATSUR")).put("8", "cobbles");
/* 1282 */     ((HashMap<String, String>)valoresAtributos.get("NATSUR")).put("9", "rock");
/* 1283 */     ((HashMap<String, String>)valoresAtributos.get("NATSUR")).put("11", "lava");
/* 1284 */     ((HashMap<String, String>)valoresAtributos.get("NATSUR")).put("14", "coral");
/* 1285 */     ((HashMap<String, String>)valoresAtributos.get("NATSUR")).put("17", "shells");
/* 1286 */     ((HashMap<String, String>)valoresAtributos.get("NATSUR")).put("18", "boulder");
/* 1287 */     ((HashMap<String, String>)valoresAtributos.get("NATQUA")).put("1", "fine");
/* 1288 */     ((HashMap<String, String>)valoresAtributos.get("NATQUA")).put("2", "medium");
/* 1289 */     ((HashMap<String, String>)valoresAtributos.get("NATQUA")).put("3", "coarse");
/* 1290 */     ((HashMap<String, String>)valoresAtributos.get("NATQUA")).put("4", "broken");
/* 1291 */     ((HashMap<String, String>)valoresAtributos.get("NATQUA")).put("5", "sticky");
/* 1292 */     ((HashMap<String, String>)valoresAtributos.get("NATQUA")).put("6", "soft");
/* 1293 */     ((HashMap<String, String>)valoresAtributos.get("NATQUA")).put("7", "stiff");
/* 1294 */     ((HashMap<String, String>)valoresAtributos.get("NATQUA")).put("8", "volcanic");
/* 1295 */     ((HashMap<String, String>)valoresAtributos.get("NATQUA")).put("9", "calcareous");
/* 1296 */     ((HashMap<String, String>)valoresAtributos.get("NATQUA")).put("10", "hard");
/* 1297 */     ((HashMap<String, String>)valoresAtributos.get("PRODCT")).put("1", "oil");
/* 1298 */     ((HashMap<String, String>)valoresAtributos.get("PRODCT")).put("2", "gas");
/* 1299 */     ((HashMap<String, String>)valoresAtributos.get("PRODCT")).put("3", "water");
/* 1300 */     ((HashMap<String, String>)valoresAtributos.get("PRODCT")).put("4", "stone");
/* 1301 */     ((HashMap<String, String>)valoresAtributos.get("PRODCT")).put("5", "coal");
/* 1302 */     ((HashMap<String, String>)valoresAtributos.get("PRODCT")).put("6", "ore");
/* 1303 */     ((HashMap<String, String>)valoresAtributos.get("PRODCT")).put("7", "chemicals");
/* 1304 */     ((HashMap<String, String>)valoresAtributos.get("PRODCT")).put("8", "drinking water");
/* 1305 */     ((HashMap<String, String>)valoresAtributos.get("PRODCT")).put("9", "milk");
/* 1306 */     ((HashMap<String, String>)valoresAtributos.get("PRODCT")).put("10", "bauxite");
/* 1307 */     ((HashMap<String, String>)valoresAtributos.get("PRODCT")).put("11", "coke");
/* 1308 */     ((HashMap<String, String>)valoresAtributos.get("PRODCT")).put("12", "iron ingots");
/* 1309 */     ((HashMap<String, String>)valoresAtributos.get("PRODCT")).put("13", "salt");
/* 1310 */     ((HashMap<String, String>)valoresAtributos.get("PRODCT")).put("14", "sand");
/* 1311 */     ((HashMap<String, String>)valoresAtributos.get("PRODCT")).put("15", "timber");
/* 1312 */     ((HashMap<String, String>)valoresAtributos.get("PRODCT")).put("16", "sawdust / wood chips");
/* 1313 */     ((HashMap<String, String>)valoresAtributos.get("PRODCT")).put("17", "scrap metal");
/* 1314 */     ((HashMap<String, String>)valoresAtributos.get("PRODCT")).put("18", "liquified natural gas (LNG)");
/* 1315 */     ((HashMap<String, String>)valoresAtributos.get("PRODCT")).put("19", "liquified petroleum gas (LPG)");
/* 1316 */     ((HashMap<String, String>)valoresAtributos.get("PRODCT")).put("20", "wine");
/* 1317 */     ((HashMap<String, String>)valoresAtributos.get("PRODCT")).put("21", "cement");
/* 1318 */     ((HashMap<String, String>)valoresAtributos.get("PRODCT")).put("22", "grain");
/* 1319 */     ((HashMap<String, String>)valoresAtributos.get("QUASOU")).put("1", "depth known");
/* 1320 */     ((HashMap<String, String>)valoresAtributos.get("QUASOU")).put("2", "depth unknown");
/* 1321 */     ((HashMap<String, String>)valoresAtributos.get("QUASOU")).put("3", "doubtful sounding");
/* 1322 */     ((HashMap<String, String>)valoresAtributos.get("QUASOU")).put("4", "unreliable sounding");
/* 1323 */     ((HashMap<String, String>)valoresAtributos.get("QUASOU")).put("5", "no bottom found at value shown");
/* 1324 */     ((HashMap<String, String>)valoresAtributos.get("QUASOU")).put("6", "least depth known");
/* 1325 */     ((HashMap<String, String>)valoresAtributos.get("QUASOU")).put("7", "least depth unknown, safe clearance at value shown");
/* 1326 */     ((HashMap<String, String>)valoresAtributos.get("QUASOU")).put("8", "value reported (not surveyed)");
/* 1327 */     ((HashMap<String, String>)valoresAtributos.get("QUASOU")).put("9", "value reported (not confirmed)");
/* 1328 */     ((HashMap<String, String>)valoresAtributos.get("QUASOU")).put("10", "maintained depth");
/* 1329 */     ((HashMap<String, String>)valoresAtributos.get("QUASOU")).put("11", "not reguraly maintained");
/* 1330 */     ((HashMap<String, String>)valoresAtributos.get("RESTRN")).put("1", "anchoring prohibited");
/* 1331 */     ((HashMap<String, String>)valoresAtributos.get("RESTRN")).put("2", "anchoring restricted");
/* 1332 */     ((HashMap<String, String>)valoresAtributos.get("RESTRN")).put("3", "fishing prohibited");
/* 1333 */     ((HashMap<String, String>)valoresAtributos.get("RESTRN")).put("4", "fishing restricted");
/* 1334 */     ((HashMap<String, String>)valoresAtributos.get("RESTRN")).put("5", "trawling prohibited");
/* 1335 */     ((HashMap<String, String>)valoresAtributos.get("RESTRN")).put("6", "trawling restricted");
/* 1336 */     ((HashMap<String, String>)valoresAtributos.get("RESTRN")).put("7", "entry prohibited");
/* 1337 */     ((HashMap<String, String>)valoresAtributos.get("RESTRN")).put("8", "entry restricted");
/* 1338 */     ((HashMap<String, String>)valoresAtributos.get("RESTRN")).put("9", "dredging prohibited");
/* 1339 */     ((HashMap<String, String>)valoresAtributos.get("RESTRN")).put("10", "dredging restricted");
/* 1340 */     ((HashMap<String, String>)valoresAtributos.get("RESTRN")).put("11", "diving prohibited");
/* 1341 */     ((HashMap<String, String>)valoresAtributos.get("RESTRN")).put("12", "diving restricted");
/* 1342 */     ((HashMap<String, String>)valoresAtributos.get("RESTRN")).put("13", "no wake");
/* 1343 */     ((HashMap<String, String>)valoresAtributos.get("RESTRN")).put("14", "area to be avoided");
/* 1344 */     ((HashMap<String, String>)valoresAtributos.get("RESTRN")).put("15", "construction prohibited");
/* 1345 */     ((HashMap<String, String>)valoresAtributos.get("RESTRN")).put("16", "discharging prohibited");
/* 1346 */     ((HashMap<String, String>)valoresAtributos.get("RESTRN")).put("17", "discharging restricted");
/* 1347 */     ((HashMap<String, String>)valoresAtributos.get("RESTRN")).put("18", "industrial or mineral exploration/development prohibited");
/* 1348 */     ((HashMap<String, String>)valoresAtributos.get("RESTRN")).put("19", "industrial or mineral exploration/development restricted");
/* 1349 */     ((HashMap<String, String>)valoresAtributos.get("RESTRN")).put("20", "drilling prohibited");
/* 1350 */     ((HashMap<String, String>)valoresAtributos.get("RESTRN")).put("21", "drilling restricted");
/* 1351 */     ((HashMap<String, String>)valoresAtributos.get("RESTRN")).put("22", "removal of historical artifacts prohibited");
/* 1352 */     ((HashMap<String, String>)valoresAtributos.get("RESTRN")).put("23", "cargo transhipment (lightering) prohibited");
/* 1353 */     ((HashMap<String, String>)valoresAtributos.get("RESTRN")).put("24", "dragging prohibited");
/* 1354 */     ((HashMap<String, String>)valoresAtributos.get("RESTRN")).put("25", "stopping prohibited");
/* 1355 */     ((HashMap<String, String>)valoresAtributos.get("RESTRN")).put("26", "landing prohibited");
/* 1356 */     ((HashMap<String, String>)valoresAtributos.get("RESTRN")).put("27", "speed restricted");
/* 1357 */     ((HashMap<String, String>)valoresAtributos.get("SIGGEN")).put("1", "automatically");
/* 1358 */     ((HashMap<String, String>)valoresAtributos.get("SIGGEN")).put("2", "by wave action");
/* 1359 */     ((HashMap<String, String>)valoresAtributos.get("SIGGEN")).put("3", "by hand");
/* 1360 */     ((HashMap<String, String>)valoresAtributos.get("SIGGEN")).put("4", "by wind");
/* 1361 */     ((HashMap<String, String>)valoresAtributos.get("STATUS")).put("1", "permanent");
/* 1362 */     ((HashMap<String, String>)valoresAtributos.get("STATUS")).put("2", "occasional");
/* 1363 */     ((HashMap<String, String>)valoresAtributos.get("STATUS")).put("3", "recommended");
/* 1364 */     ((HashMap<String, String>)valoresAtributos.get("STATUS")).put("4", "disused");
/* 1365 */     ((HashMap<String, String>)valoresAtributos.get("STATUS")).put("5", "periodically/intermittent");
/* 1366 */     ((HashMap<String, String>)valoresAtributos.get("STATUS")).put("6", "reserved");
/* 1367 */     ((HashMap<String, String>)valoresAtributos.get("STATUS")).put("7", "temporary");
/* 1368 */     ((HashMap<String, String>)valoresAtributos.get("STATUS")).put("8", "private");
/* 1369 */     ((HashMap<String, String>)valoresAtributos.get("STATUS")).put("9", "mandatory");
/* 1370 */     ((HashMap<String, String>)valoresAtributos.get("STATUS")).put("11", "extinguished");
/* 1371 */     ((HashMap<String, String>)valoresAtributos.get("STATUS")).put("12", "illuminated");
/* 1372 */     ((HashMap<String, String>)valoresAtributos.get("STATUS")).put("13", "historic");
/* 1373 */     ((HashMap<String, String>)valoresAtributos.get("STATUS")).put("14", "public");
/* 1374 */     ((HashMap<String, String>)valoresAtributos.get("STATUS")).put("15", "synchronized");
/* 1375 */     ((HashMap<String, String>)valoresAtributos.get("STATUS")).put("16", "watched");
/* 1376 */     ((HashMap<String, String>)valoresAtributos.get("STATUS")).put("17", "un-watched");
/* 1377 */     ((HashMap<String, String>)valoresAtributos.get("STATUS")).put("18", "existence doubtful");
/* 1378 */     ((HashMap<String, String>)valoresAtributos.get("SURTYP")).put("1", "reconnaissance/sketch survey");
/* 1379 */     ((HashMap<String, String>)valoresAtributos.get("SURTYP")).put("2", "controlled survey");
/* 1380 */     ((HashMap<String, String>)valoresAtributos.get("SURTYP")).put("4", "examintion survey");
/* 1381 */     ((HashMap<String, String>)valoresAtributos.get("SURTYP")).put("5", "passage survey");
/* 1382 */     ((HashMap<String, String>)valoresAtributos.get("SURTYP")).put("6", "remotely sensed");
/* 1383 */     ((HashMap<String, String>)valoresAtributos.get("TECSOU")).put("1", "found by echo-sounder");
/* 1384 */     ((HashMap<String, String>)valoresAtributos.get("TECSOU")).put("2", "found by side scan sonar");
/* 1385 */     ((HashMap<String, String>)valoresAtributos.get("TECSOU")).put("3", "found by multi-beam");
/* 1386 */     ((HashMap<String, String>)valoresAtributos.get("TECSOU")).put("4", "found by diver");
/* 1387 */     ((HashMap<String, String>)valoresAtributos.get("TECSOU")).put("5", "found by lead-line");
/* 1388 */     ((HashMap<String, String>)valoresAtributos.get("TECSOU")).put("6", "swept by wire-drag");
/* 1389 */     ((HashMap<String, String>)valoresAtributos.get("TECSOU")).put("7", "found by laser");
/* 1390 */     ((HashMap<String, String>)valoresAtributos.get("TECSOU")).put("8", "swept by vertical acoustic system");
/* 1391 */     ((HashMap<String, String>)valoresAtributos.get("TECSOU")).put("9", "found by electromagnetic sensor");
/* 1392 */     ((HashMap<String, String>)valoresAtributos.get("TECSOU")).put("10", "photogrammetry");
/* 1393 */     ((HashMap<String, String>)valoresAtributos.get("TECSOU")).put("11", "satelite imagery");
/* 1394 */     ((HashMap<String, String>)valoresAtributos.get("TECSOU")).put("12", "found by levelling");
/* 1395 */     ((HashMap<String, String>)valoresAtributos.get("TECSOU")).put("13", "swept by side-scan sonar");
/* 1396 */     ((HashMap<String, String>)valoresAtributos.get("TECSOU")).put("14", "computer generated");
/* 1397 */     ((HashMap<String, String>)valoresAtributos.get("T_ACWL")).put("1", "better than 0.1m and 10 minutes");
/* 1398 */     ((HashMap<String, String>)valoresAtributos.get("T_ACWL")).put("2", "worse than 0.1m or 10 minutes");
/* 1399 */     ((HashMap<String, String>)valoresAtributos.get("T_MTOD")).put("1", "simplified harmonic method of tidal prediction");
/* 1400 */     ((HashMap<String, String>)valoresAtributos.get("T_MTOD")).put("2", "full harmonic method of tidal prediction");
/* 1401 */     ((HashMap<String, String>)valoresAtributos.get("T_MTOD")).put("3", "height and time difference non-harmonic method");
/* 1402 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("1", "cone, point up");
/* 1403 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("2", "cone, point down");
/* 1404 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("3", "sphere");
/* 1405 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("4", "2 sphere");
/* 1406 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("5", "cylinder (can)");
/* 1407 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("6", "board");
/* 1408 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("7", "x-shape (St. Andrew's cross)");
/* 1409 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("8", "upright cross (St. George cross)");
/* 1410 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("9", "cube, point up");
/* 1411 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("10", "2 cones, point to point");
/* 1412 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("11", "2 cones, base to base");
/* 1413 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("12", "rhombus (diamond)");
/* 1414 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("13", "2 cones (points upward)");
/* 1415 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("14", "2 cones (points downward)");
/* 1416 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("15", "besom, point up (broom or perch)");
/* 1417 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("16", "besom, point down (broom or perch)");
/* 1418 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("17", "flag");
/* 1419 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("18", "sphere over rhombus");
/* 1420 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("19", "square");
/* 1421 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("20", "rectangle, horizontal");
/* 1422 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("21", "rectangle, vertical");
/* 1423 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("22", "trapezium, up");
/* 1424 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("23", "trapezium, down");
/* 1425 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("24", "triangle, point up");
/* 1426 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("25", "triangle, point down");
/* 1427 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("26", "circle");
/* 1428 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("27", "two upright crosses (one over the other)");
/* 1429 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("28", "T-shape");
/* 1430 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("29", "triangle pointing up over a circle");
/* 1431 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("30", "upright cross over a circle");
/* 1432 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("31", "rhombus over a circle");
/* 1433 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("32", "circle over a triangle pointing up");
/* 1434 */     ((HashMap<String, String>)valoresAtributos.get("TOPSHP")).put("33", "other shape (see INFORM)");
/* 1435 */     ((HashMap<String, String>)valoresAtributos.get("TRAFIC")).put("1", "inbound");
/* 1436 */     ((HashMap<String, String>)valoresAtributos.get("TRAFIC")).put("2", "outbound");
/* 1437 */     ((HashMap<String, String>)valoresAtributos.get("TRAFIC")).put("3", "one-way");
/* 1438 */     ((HashMap<String, String>)valoresAtributos.get("TRAFIC")).put("4", "two-way");
/* 1439 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("1", "Mean low water springs");
/* 1440 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("2", "Mean lower low water springs");
/* 1441 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("3", "Mean sea level");
/* 1442 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("4", "Lowest low water");
/* 1443 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("5", "Mean low water");
/* 1444 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("6", "Lowest low water springs");
/* 1445 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("7", "Approximate mean low water springs");
/* 1446 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("8", "Indian spring low water");
/* 1447 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("9", "Low water springs");
/* 1448 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("10", "Approximate lowest astronomical tide");
/* 1449 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("11", "Nearly lowest low water");
/* 1450 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("12", "Mean lower low water");
/* 1451 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("13", "Low water");
/* 1452 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("14", "Approximate mean low water");
/* 1453 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("15", "Approximate mean lower low water");
/* 1454 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("16", "Mean high water");
/* 1455 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("17", "Mean high water springs");
/* 1456 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("18", "High water");
/* 1457 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("19", "Approximate mean sea level");
/* 1458 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("20", "High water springs");
/* 1459 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("21", "Mean higher high water");
/* 1460 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("22", "Equinoctial spring low water");
/* 1461 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("23", "Lowest astronomical tide");
/* 1462 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("24", "Local datum");
/* 1463 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("25", "International Great Lakes Datum 1985");
/* 1464 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("26", "Mean water level");
/* 1465 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("27", "Lower low water large tide");
/* 1466 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("28", "Higher high water lage tide");
/* 1467 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("29", "Nearly highest high water");
/* 1468 */     ((HashMap<String, String>)valoresAtributos.get("VERDAT")).put("30", "Highest astronomical tide (HAT)");
/* 1469 */     ((HashMap<String, String>)valoresAtributos.get("WATLEV")).put("1", "partly submerged at high water");
/* 1470 */     ((HashMap<String, String>)valoresAtributos.get("WATLEV")).put("2", "always dry");
/* 1471 */     ((HashMap<String, String>)valoresAtributos.get("WATLEV")).put("3", "always under water/submerged");
/* 1472 */     ((HashMap<String, String>)valoresAtributos.get("WATLEV")).put("4", "covers and uncovers");
/* 1473 */     ((HashMap<String, String>)valoresAtributos.get("WATLEV")).put("5", "awash");
/* 1474 */     ((HashMap<String, String>)valoresAtributos.get("WATLEV")).put("6", "subject to inundation or flooding");
/* 1475 */     ((HashMap<String, String>)valoresAtributos.get("WATLEV")).put("7", "floating");
/* 1476 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("1", "WGS 72");
/* 1477 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("2", "WGS 84");
/* 1478 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("3", "European 1950");
/* 1479 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("4", "Potsdam Datum");
/* 1480 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("5", "Adindan");
/* 1481 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("6", "Afgooye");
/* 1482 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("7", "Ain el Abd 1970");
/* 1483 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("8", "Anna 1 Astro 1965");
/* 1484 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("9", "Antigua Island Astro 1943");
/* 1485 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("10", "Arc 1950");
/* 1486 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("11", "Arc 1960");
/* 1487 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("12", "Ascension Island 1958");
/* 1488 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("13", "Astro beacon 'E' 1945");
/* 1489 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("14", "Astro DOS 71/4");
/* 1490 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("15", "Astro Tern Island (FRIG) 1961");
/* 1491 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("16", "Astronimical Station 1952");
/* 1492 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("17", "Australian Geodetic 1966");
/* 1493 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("18", "Australian Geodetic 1984");
/* 1494 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("19", "Ayabelle Lighthouse");
/* 1495 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("20", "Bellevue (IGN)");
/* 1496 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("21", "Bermuda 1957");
/* 1497 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("22", "Bissau");
/* 1498 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("23", "Bogota Observatory");
/* 1499 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("24", "Bukit Rimpah");
/* 1500 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("25", "Camp Area Astro");
/* 1501 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("26", "Campo Inchauspe 1969");
/* 1502 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("27", "Canton Astro 1966");
/* 1503 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("28", "Cape");
/* 1504 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("29", "Cape Canaveral");
/* 1505 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("30", "Carthage");
/* 1506 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("31", "Chatam Island Astro 1971");
/* 1507 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("32", "Chua Astro");
/* 1508 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("33", "Corrego Alegre");
/* 1509 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("34", "Dabola");
/* 1510 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("35", "Djakarta (Batavia)");
/* 1511 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("36", "DOS 1968");
/* 1512 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("37", "Easter Island 1967");
/* 1513 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("38", "European 1979");
/* 1514 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("39", "Fort Thomas 1955");
/* 1515 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("40", "Gan 1970");
/* 1516 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("41", "Geodetic Datum 1949");
/* 1517 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("42", "Graciosa Base SW 1948");
/* 1518 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("43", "Guam 1963");
/* 1519 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("44", "Ganung Segara");
/* 1520 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("45", "GUX 1 Astro");
/* 1521 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("46", "Herat North");
/* 1522 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("47", "Hjorsey 1955");
/* 1523 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("48", "Hong Kong 1963");
/* 1524 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("49", "Hu-Tzu-Shan");
/* 1525 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("50", "Indian");
/* 1526 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("51", "Indian 1954");
/* 1527 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("52", "Indian 1975");
/* 1528 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("53", "Ireland 1965");
/* 1529 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("54", "ISTS 061 Astro 1968");
/* 1530 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("55", "ISTS 073 Astro 1969");
/* 1531 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("56", "Johnston Island 1961");
/* 1532 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("57", "Kandawala");
/* 1533 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("58", "Kerguelen Island 1949");
/* 1534 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("59", "Kertau 1948");
/* 1535 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("60", "Kusaie Astro 1951");
/* 1536 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("61", "L. C. 5 Astro 1961");
/* 1537 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("62", "Leigon");
/* 1538 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("63", "Liberia 1964");
/* 1539 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("64", "Luzon");
/* 1540 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("65", "Mahe 1971");
/* 1541 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("66", "Massawa");
/* 1542 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("67", "Merchich");
/* 1543 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("68", "Midway Astro 1961");
/* 1544 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("69", "Minna");
/* 1545 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("70", "Montserrat Island Astro 1958");
/* 1546 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("71", "M'Poraloko");
/* 1547 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("72", "Nahrwan");
/* 1548 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("73", "Naparima, BWI");
/* 1549 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("74", "North American 1927");
/* 1550 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("75", "North American 1983");
/* 1551 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("76", "Observatorio Meteorologico 1939");
/* 1552 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("77", "Old Egyptian 1907");
/* 1553 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("78", "Old Hawaiian");
/* 1554 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("79", "Oman");
/* 1555 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("80", "Ordnance Survey of Great Britain 1936");
/* 1556 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("81", "Pico de las Nieves");
/* 1557 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("82", "Pitcairn Astro 1967");
/* 1558 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("83", "Point 58");
/* 1559 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("84", "Pointe Noire 1948");
/* 1560 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("85", "Porto Santo 1936");
/* 1561 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("86", "Provisional South American 1956");
/* 1562 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("87", "Provisional South Chilean 1963 (also known as Hito XVIII 1963)");
/* 1563 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("88", "Puerto Rico");
/* 1564 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("89", "Qatar national");
/* 1565 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("90", "Qornoq");
/* 1566 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("91", "Reunion");
/* 1567 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("92", "Rome 1940");
/* 1568 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("93", "Santo (DOS) 1965");
/* 1569 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("94", "Sao Braz");
/* 1570 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("95", "Sapper Hill 1943");
/* 1571 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("96", "Schwarzeck");
/* 1572 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("97", "Selvagem Grande 1938");
/* 1573 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("98", "South American 1969");
/* 1574 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("99", "South Asia");
/* 1575 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("100", "Tananarive Observatory 1925");
/* 1576 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("101", "Timbalai 1948");
/* 1577 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("102", "Tokyo");
/* 1578 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("103", "Tristan Astro 1968");
/* 1579 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("104", "Viti Levu 1916");
/* 1580 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("105", "Wake-Eniwetok 1960");
/* 1581 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("106", "Wake Island Astro 1952");
/* 1582 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("107", "Yacare");
/* 1583 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("108", "Zanderij");
/* 1584 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("109", "American Samoa 1962");
/* 1585 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("110", "Deception Island");
/* 1586 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("111", "Indian 1960");
/* 1587 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("112", "Indonesian 1974");
/* 1588 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("113", "North Sahara 1959");
/* 1589 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("114", "Pulkovo 1942");
/* 1590 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("115", "S-42 (Pulkovo 1942)");
/* 1591 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("116", "S-JYSK");
/* 1592 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("117", "Voirol 1950");
/* 1593 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("118", "Average Terrestrial System 1977");
/* 1594 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("119", "Compensation Geodesique du Quebec 1977");
/* 1595 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("120", "Finnish (KKJ)");
/* 1596 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("121", "Ordnance Survey of Ireland");
/* 1597 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("122", "Revised Kertau");
/* 1598 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("123", "Revised Nahrwan");
/* 1599 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("124", "GGRS 76 (Greece)");
/* 1600 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("125", "Nouvelle Triangulation de France");
/* 1601 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("126", "RT 90 (Sweden)");
/* 1602 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("127", "Geocentric Datum of Australia (GDA)");
/* 1603 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("128", "BJZ54 (A954 Beijing Coordinates)");
/* 1604 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("129", "Modified BJZ54");
/* 1605 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("130", "GDZ80");
/* 1606 */     ((HashMap<String, String>)valoresAtributos.get("HORDAT")).put("131", "Local datum");
/* 1607 */     ((HashMap<String, String>)valoresAtributos.get("QUAPOS")).put("1", "surveyed");
/* 1608 */     ((HashMap<String, String>)valoresAtributos.get("QUAPOS")).put("2", "unsurveyed");
/* 1609 */     ((HashMap<String, String>)valoresAtributos.get("QUAPOS")).put("3", "inadequately surveyed");
/* 1610 */     ((HashMap<String, String>)valoresAtributos.get("QUAPOS")).put("4", "approximated");
/* 1611 */     ((HashMap<String, String>)valoresAtributos.get("QUAPOS")).put("5", "position doubtful");
/* 1612 */     ((HashMap<String, String>)valoresAtributos.get("QUAPOS")).put("6", "unreliable");
/* 1613 */     ((HashMap<String, String>)valoresAtributos.get("QUAPOS")).put("7", "reported (not surveyed)");
/* 1614 */     ((HashMap<String, String>)valoresAtributos.get("QUAPOS")).put("8", "reported (not confirmed)");
/* 1615 */     ((HashMap<String, String>)valoresAtributos.get("QUAPOS")).put("9", "estimated");
/* 1616 */     ((HashMap<String, String>)valoresAtributos.get("QUAPOS")).put("10", "precisely known");
/* 1617 */     ((HashMap<String, String>)valoresAtributos.get("QUAPOS")).put("11", "calculated");
/*      */ 
/*      */     
/*      */     try {
/* 1621 */       descricaoAtributo = (String)((HashMap)valoresAtributos.get(nomeAtributoFeicao)).get(valorAtributoFeicao);
/*      */     }
/* 1623 */     catch (NullPointerException ex) {
/*      */       
/* 1625 */       if (nomeAtributoFeicao == null || valorAtributoFeicao == null || valoresAtributos.get(nomeAtributoFeicao) == null) {
/*      */         
/* 1627 */         descricaoAtributo = null;
/*      */       }
/*      */       else {
/*      */         
/* 1631 */         throw ex;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1637 */     return descricaoAtributo;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String obterNomeCompletoFeicao(String nome) {
/* 1668 */     HashMap<String, String> feicoes = new HashMap<>();
/* 1669 */     String nomeCompletoFeicao = null;
/*      */     
/* 1671 */     feicoes.put("ADMARE", "Administration area (Named)");
/* 1672 */     feicoes.put("AIRARE", "Airport / airfield");
/* 1673 */     feicoes.put("ACHBRT", "Anchor berth");
/* 1674 */     feicoes.put("ACHARE", "Anchorage area");
/* 1675 */     feicoes.put("BCNCAR", "Beacon, cardinal");
/* 1676 */     feicoes.put("BCNISD", "Beacon, isolated danger");
/* 1677 */     feicoes.put("BCNLAT", "Beacon, lateral");
/* 1678 */     feicoes.put("BCNSAW", "Beacon, safe water");
/* 1679 */     feicoes.put("BCNSPP", "Beacon, special purpose/general");
/* 1680 */     feicoes.put("BERTHS", "Berth");
/* 1681 */     feicoes.put("BRIDGE", "Bridge");
/* 1682 */     feicoes.put("BUISGL", "Building, single");
/* 1683 */     feicoes.put("BUAARE", "Built-up area");
/* 1684 */     feicoes.put("BOYCAR", "Buoy, cardinal");
/* 1685 */     feicoes.put("BOYINB", "Buoy, installation");
/* 1686 */     feicoes.put("BOYISD", "Buoy, isolated danger");
/* 1687 */     feicoes.put("BOYLAT", "Buoy, lateral");
/* 1688 */     feicoes.put("BOYSAW", "Buoy, safe water");
/* 1689 */     feicoes.put("BOYSPP", "Buoy, special purpose/general");
/* 1690 */     feicoes.put("CBLARE", "Cable area");
/* 1691 */     feicoes.put("CBLOHD", "Cable, overhead");
/* 1692 */     feicoes.put("CBLSUB", "Cable, submarine");
/* 1693 */     feicoes.put("CANALS", "Canal");
/* 1694 */     feicoes.put("CTSARE", "Cargo transshipment area");
/* 1695 */     feicoes.put("CAUSWY", "Causeway");
/* 1696 */     feicoes.put("CTNARE", "Caution area");
/* 1697 */     feicoes.put("CHKPNT", "Checkpoint");
/* 1698 */     feicoes.put("CGUSTA", "Coastguard station");
/* 1699 */     feicoes.put("COALNE", "Coastline");
/* 1700 */     feicoes.put("CONZNE", "Contiguous zone");
/* 1701 */     feicoes.put("COSARE", "Continental shelf area");
/* 1702 */     feicoes.put("CTRPNT", "Control point");
/* 1703 */     feicoes.put("CONVYR", "Conveyor");
/* 1704 */     feicoes.put("CRANES", "Crane");
/* 1705 */     feicoes.put("CURENT", "Current - non - gravitational");
/* 1706 */     feicoes.put("CUSZNE", "Custom zone");
/* 1707 */     feicoes.put("DAMCON", "Dam");
/* 1708 */     feicoes.put("DAYMAR", "Daymark");
/* 1709 */     feicoes.put("DWRTCL", "Deep water route centerline");
/* 1710 */     feicoes.put("DWRTPT", "Deep water route part");
/* 1711 */     feicoes.put("DEPARE", "Depth area");
/* 1712 */     feicoes.put("DEPCNT", "Depth contour");
/* 1713 */     feicoes.put("DISMAR", "Distance mark");
/* 1714 */     feicoes.put("DOCARE", "Dock area");
/* 1715 */     feicoes.put("DRGARE", "Dredged area");
/* 1716 */     feicoes.put("DRYDOC", "Dry dock");
/* 1717 */     feicoes.put("DMPGRD", "Dumping ground");
/* 1718 */     feicoes.put("DYKCON", "Dyke");
/* 1719 */     feicoes.put("EXEZNE", "Exclusive Economic Zone");
/* 1720 */     feicoes.put("FAIRWY", "Fairway");
/* 1721 */     feicoes.put("FNCLNE", "Fence/wall");
/* 1722 */     feicoes.put("FERYRT", "Ferry route");
/* 1723 */     feicoes.put("FSHZNE", "Fishery zone");
/* 1724 */     feicoes.put("FSHFAC", "Fishing facility");
/* 1725 */     feicoes.put("FSHGRD", "Fishing ground");
/* 1726 */     feicoes.put("FLODOC", "Floating dock");
/* 1727 */     feicoes.put("FOGSIG", "Fog signal");
/* 1728 */     feicoes.put("FORSTC", "Fortified structure");
/* 1729 */     feicoes.put("FRPARE", "Free port area");
/* 1730 */     feicoes.put("GATCON", "Gate");
/* 1731 */     feicoes.put("GRIDRN", "Gridiron");
/* 1732 */     feicoes.put("HRBARE", "Harbour area (administrative)");
/* 1733 */     feicoes.put("HRBFAC", "Harbour facility");
/* 1734 */     feicoes.put("HULKES", "Hulk");
/* 1735 */     feicoes.put("ICEARE", "Ice area");
/* 1736 */     feicoes.put("ICNARE", "Incineration area");
/* 1737 */     feicoes.put("ISTZNE", "Inshore traffic zone");
/* 1738 */     feicoes.put("LAKARE", "Lake");
/* 1739 */     feicoes.put("LNDARE", "Land area");
/* 1740 */     feicoes.put("LNDELV", "Land elevation");
/* 1741 */     feicoes.put("LNDRGN", "Land region");
/* 1742 */     feicoes.put("LNDMRK", "Landmark");
/* 1743 */     feicoes.put("LIGHTS", "Light");
/* 1744 */     feicoes.put("LITFLT", "Light float");
/* 1745 */     feicoes.put("LITVES", "Light vessel");
/* 1746 */     feicoes.put("LOCMAG", "Local magnetic anomaly");
/* 1747 */     feicoes.put("LOKBSN", "Lock basin");
/* 1748 */     feicoes.put("LOGPON", "Log pond");
/* 1749 */     feicoes.put("MAGVAR", "Magnetic variation");
/* 1750 */     feicoes.put("MARCUL", "Marine farm/culture");
/* 1751 */     feicoes.put("MIPARE", "Military practice area");
/* 1752 */     feicoes.put("MORFAC", "Mooring/warping facility");
/* 1753 */     feicoes.put("NAVLNE", "Navigation line");
/* 1754 */     feicoes.put("OBSTRN", "Obstruction");
/* 1755 */     feicoes.put("OFSPLF", "Offshore platform");
/* 1756 */     feicoes.put("OSPARE", "Offshore production area");
/* 1757 */     feicoes.put("OILBAR", "Oil barrier");
/* 1758 */     feicoes.put("PILPNT", "Pile");
/* 1759 */     feicoes.put("PILBOP", "Pilot boarding place");
/* 1760 */     feicoes.put("PIPARE", "Pipeline area");
/* 1761 */     feicoes.put("PIPOHD", "Pipeline, overhead");
/* 1762 */     feicoes.put("PIPSOL", "Pipeline, submarine/on land");
/* 1763 */     feicoes.put("PONTON", "Pontoon");
/* 1764 */     feicoes.put("PRCARE", "Precautionary area");
/* 1765 */     feicoes.put("PRDARE", "Production / storage area");
/* 1766 */     feicoes.put("PYLONS", "Pylon/bridge support");
/* 1767 */     feicoes.put("RADLNE", "Radar line");
/* 1768 */     feicoes.put("RADRNG", "Radar range");
/* 1769 */     feicoes.put("RADRFL", "Radar reflector");
/* 1770 */     feicoes.put("RADSTA", "Radar station");
/* 1771 */     feicoes.put("RTPBCN", "Radar transponder beacon");
/* 1772 */     feicoes.put("RDOCAL", "Radio calling-in point");
/* 1773 */     feicoes.put("RDOSTA", "Radio station");
/* 1774 */     feicoes.put("RAILWY", "Railway");
/* 1775 */     feicoes.put("RAPIDS", "Rapids");
/* 1776 */     feicoes.put("RCRTCL", "Recommended route centerline");
/* 1777 */     feicoes.put("RECTRC", "Recommended track");
/* 1778 */     feicoes.put("RCTLPT", "Recommended Traffic Lane Part");
/* 1779 */     feicoes.put("RSCSTA", "Rescue station");
/* 1780 */     feicoes.put("RESARE", "Restricted area");
/* 1781 */     feicoes.put("RETRFL", "Retro-reflector");
/* 1782 */     feicoes.put("RIVERS", "River");
/* 1783 */     feicoes.put("ROADWY", "Road");
/* 1784 */     feicoes.put("RUNWAY", "Runway");
/* 1785 */     feicoes.put("SNDWAV", "Sand waves");
/* 1786 */     feicoes.put("SEAARE", "Sea area / named water area");
/* 1787 */     feicoes.put("SPLARE", "Sea-plane landing area");
/* 1788 */     feicoes.put("SBDARE", "Seabed area");
/* 1789 */     feicoes.put("SLCONS", "Shoreline Construction");
/* 1790 */     feicoes.put("SISTAT", "Signal station, traffic");
/* 1791 */     feicoes.put("SISTAW", "Signal station, warning");
/* 1792 */     feicoes.put("SILTNK", "Silo / tank");
/* 1793 */     feicoes.put("SLOTOP", "Slope topline");
/* 1794 */     feicoes.put("SLOGRD", "Sloping ground");
/* 1795 */     feicoes.put("SMCFAC", "Small craft facility");
/* 1796 */     feicoes.put("SOUNDG", "Sounding");
/* 1797 */     feicoes.put("SPRING", "Spring");
/* 1798 */     feicoes.put("STSLNE", "Straight territorial sea baseline");
/* 1799 */     feicoes.put("SUBTLN", "Submarine transit lane");
/* 1800 */     feicoes.put("SWPARE", "Swept Area");
/* 1801 */     feicoes.put("TESARE", "Territorial sea area");
/* 1802 */     feicoes.put("TS_PRH", "Tidal stream - harmonic prediction");
/* 1803 */     feicoes.put("TS_PNH", "Tidal stream - non-harmonic prediction");
/* 1804 */     feicoes.put("TS_PAD", "Tidal stream panel data");
/* 1805 */     feicoes.put("TS_TIS", "Tidal stream - time series");
/* 1806 */     feicoes.put("T_HMON", "Tide - harmonic prediction");
/* 1807 */     feicoes.put("T_NHMN", "Tide - non-harmonic prediction");
/* 1808 */     feicoes.put("T_TIMS", "Tidal stream - time series");
/* 1809 */     feicoes.put("TIDEWY", "Tideway");
/* 1810 */     feicoes.put("TOPMAR", "Top mark");
/* 1811 */     feicoes.put("TSELNE", "Traffic Separation Line");
/* 1812 */     feicoes.put("TSSBND", "Traffic Separation Scheme  Boundary");
/* 1813 */     feicoes.put("TSSCRS", "Traffic Separation Scheme Crossing");
/* 1814 */     feicoes.put("TSSLPT", "Traffic Separation Scheme  Lane part");
/* 1815 */     feicoes.put("TSSRON", "Traffic Separation Scheme  Roundabout");
/* 1816 */     feicoes.put("TSEZNE", "Traffic Separation Zone");
/* 1817 */     feicoes.put("TUNNEL", "Tunnel");
/* 1818 */     feicoes.put("TWRTPT", "Two-way route  part");
/* 1819 */     feicoes.put("UWTROC", "Underwater rock / awash rock");
/* 1820 */     feicoes.put("UNSARE", "Unsurveyed area");
/* 1821 */     feicoes.put("VEGATN", "Vegetation");
/* 1822 */     feicoes.put("WATTUR", "Water turbulence");
/* 1823 */     feicoes.put("WATFAL", "Waterfall");
/* 1824 */     feicoes.put("WEDKLP", "Weed/Kelp");
/* 1825 */     feicoes.put("WRECKS", "Wreck");
/* 1826 */     feicoes.put("TS_FEB", "Tidal stream - flood/ebb");
/* 1827 */     feicoes.put("M_ACCY", "Accuracy of data");
/* 1828 */     feicoes.put("M_CSCL", "Compilation scale of data");
/* 1829 */     feicoes.put("M_COVR", "Coverage");
/* 1830 */     feicoes.put("M_HOPA", "Horizontal datum shift parameters");
/* 1831 */     feicoes.put("M_NPUB", "Nautical publication information");
/* 1832 */     feicoes.put("M_NSYS", "Navigational system of marks");
/* 1833 */     feicoes.put("M_QUAL", "Quality of data");
/* 1834 */     feicoes.put("M_SDAT", "Sounding datum");
/* 1835 */     feicoes.put("M_SREL", "Survey reliability");
/* 1836 */     feicoes.put("M_VDAT", "Vertical datum of data");
/* 1837 */     feicoes.put("C_AGGR", "Aggregation");
/* 1838 */     feicoes.put("C_ASSO", "Association");
/*      */ 
/*      */     
/*      */     try {
/* 1842 */       nomeCompletoFeicao = feicoes.get(nome.toUpperCase());
/*      */     }
/* 1844 */     catch (NullPointerException ex) {
/*      */       
/* 1846 */       if (nome == null) {
/*      */         
/* 1848 */         nomeCompletoFeicao = null;
/*      */       }
/*      */       else {
/*      */         
/* 1852 */         throw ex;
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1858 */     return nomeCompletoFeicao;
/*      */   }
/*      */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/util/InformacoesExibicaoUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */