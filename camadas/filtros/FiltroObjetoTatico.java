/*     */ package ipqm.gsd.hidra.ihm.camadas.filtros;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.Ambiente;
/*     */ import ipqm.gsd.componentes.nucleo.VariaveisEstado;
/*     */ import ipqm.gsd.componentes.nucleo.contexto.GravarEstado;
/*     */ import ipqm.gsd.componentes.nucleo.logon.perfis.PerfilUsuario;
/*     */ import ipqm.gsd.componentes.nucleo.objeto_visual.Filtro;
/*     */ import java.io.Serializable;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FiltroObjetoTatico
/*     */   extends Filtro
/*     */   implements Serializable, GravarEstado
/*     */ {
/*  18 */   private int ampliacao = 75;
/*     */   
/*  20 */   private int tamanhoVetorVeloc = 6;
/*     */   
/*     */   private boolean exibirAcompanhamentoRTD = false;
/*     */   
/*     */   private boolean exibirPosicaoGPS = true;
/*     */   private boolean exibirTextos = true;
/*     */   private boolean exibirGiro = true;
/*  27 */   private EnumFiltroPontosHistoricos tipoExibicaoPontosHistoricos = EnumFiltroPontosHistoricos.AO_SELECIONAR;
/*     */   
/*     */   private boolean exibirPontosHistoricos = true;
/*     */   private boolean exibirPontosHistoricosEstendido = false;
/*  31 */   private int intervaloTempoPontosEstendidos = 1;
/*     */   
/*     */   private boolean exibirApenasLiberado = true;
/*     */   
/*     */   private boolean exibirArcoFogo = false;
/*     */   
/*     */   private boolean exibirLinhaFomatura = true;
/*     */   
/*     */   private boolean exibirProjetil = false;
/*     */   
/*     */   protected boolean exibirTrackRadarDT = false;
/*     */   
/*     */   private boolean exibirElementoNavegacao = false;
/*     */   
/*     */   private boolean exibirCameraFixa = true;
/*     */   
/*     */   private boolean exibirProprioVeiculo = true;
/*     */   
/*     */   private boolean exibirCorrecoesManuaisEncarnadas = false;
/*     */   
/*     */   private boolean exibirCorrecoesManuaisDeletadas = false;
/*     */   
/*     */   private boolean exibirCorrecoesManuaisTemporarias = false;
/*     */   
/*     */   private boolean naoExibirCorrecoesManuais = false;
/*     */   
/*     */   private boolean exibirPlotIff = true;
/*     */   
/*     */   private boolean exibirPlotMAD = true;
/*     */   protected boolean exibirPlotRadar = false;
/*     */   private boolean exibirVeiculos = false;
/*     */   private boolean exibirVeiculosFantasmas = false;
/*     */   private boolean exibirObjDeteccaoVisual = true;
/*     */   private boolean exibirObjBloqueioEletronico = false;
/*     */   private boolean exibirObjChaffSuperficie = false;
/*     */   private boolean exibirObjChaffAeronave = false;
/*     */   private boolean exibirAmbAer = true;
/*     */   private boolean exibirAmbSup = true;
/*     */   private boolean exibirAmbSub = true;
/*     */   private boolean exibirAmbDesc = true;
/*     */   private boolean exibirHostilAmigo = true;
/*     */   private boolean exibirHostilDesc = true;
/*     */   private boolean exibirHostilInimigo = true;
/*     */   private boolean exibirHostilNeutro = true;
/*     */   private boolean exibirAreaCircular = true;
/*     */   private boolean exibirAreaPoligonal = true;
/*     */   private boolean exibirCobertura = true;
/*     */   private boolean exibirSetor = true;
/*     */   private boolean exibirRota = true;
/*     */   private boolean exibirGrade = true;
/*  81 */   private Map<PerfilUsuario.ExibirAcompanhamento, Boolean> mapaAcompanhamentos = new HashMap<>();
/*     */   
/*     */   public FiltroObjetoTatico() {
/*  84 */     preencherAcompanhamentos();
/*     */   }
/*     */   
/*     */   public Map<PerfilUsuario.ExibirAcompanhamento, Boolean> getMapaAcompanhamentos() {
/*  88 */     return this.mapaAcompanhamentos;
/*     */   }
/*     */ 
/*     */   
/*     */   public void todosFiltros(boolean habilitar) {
/*  93 */     this.mapaAcompanhamentos.put(PerfilUsuario.ExibirAcompanhamento.EXIBIR_TODOS_ACOMPANHAMENTOS, Boolean.valueOf(habilitar));
/*  94 */     setAcompanhamentos(PerfilUsuario.ExibirAcompanhamento.EXIBIR_TODOS_ACOMPANHAMENTOS, habilitar);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExibirAmbAer() {
/*  99 */     return this.exibirAmbAer;
/*     */   }
/*     */   
/*     */   public void setExibirAmbAer(boolean exibirAmbAer) {
/* 103 */     this.exibirAmbAer = exibirAmbAer;
/*     */   }
/*     */   
/*     */   public boolean isExibirAmbDesc() {
/* 107 */     return this.exibirAmbDesc;
/*     */   }
/*     */   
/*     */   public void setExibirAmbDesc(boolean exibirAmbDesc) {
/* 111 */     this.exibirAmbDesc = exibirAmbDesc;
/*     */   }
/*     */   
/*     */   public boolean isExibirAmbSup() {
/* 115 */     return this.exibirAmbSup;
/*     */   }
/*     */   
/*     */   public void setExibirAmbSup(boolean exibirAmbSup) {
/* 119 */     this.exibirAmbSup = exibirAmbSup;
/*     */   }
/*     */   
/*     */   public boolean isExibirAmbSub() {
/* 123 */     return this.exibirAmbSub;
/*     */   }
/*     */   
/*     */   public void setExibirAmbSub(boolean exibirAmbSub) {
/* 127 */     this.exibirAmbSub = exibirAmbSub;
/*     */   }
/*     */   
/*     */   public void exibirTodosAmbientes(boolean habilitar) {
/* 131 */     this.exibirAmbAer = habilitar;
/* 132 */     this.exibirAmbDesc = habilitar;
/* 133 */     this.exibirAmbSup = habilitar;
/* 134 */     this.exibirAmbSub = habilitar;
/*     */   }
/*     */   
/*     */   public boolean isExibirTodosAmbientes() {
/* 138 */     return (this.exibirAmbAer && this.exibirAmbDesc && this.exibirAmbSup && this.exibirAmbSub);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExibirHostilAmigo() {
/* 143 */     return this.exibirHostilAmigo;
/*     */   }
/*     */   
/*     */   public void setExibirHostilAmigo(boolean exibirHostilAmigo) {
/* 147 */     this.exibirHostilAmigo = exibirHostilAmigo;
/*     */   }
/*     */   
/*     */   public boolean isExibirHostilDesc() {
/* 151 */     return this.exibirHostilDesc;
/*     */   }
/*     */   
/*     */   public void setExibirHostilDesc(boolean exibirHostilDesc) {
/* 155 */     this.exibirHostilDesc = exibirHostilDesc;
/*     */   }
/*     */   
/*     */   public boolean isExibirHostilInimigo() {
/* 159 */     return this.exibirHostilInimigo;
/*     */   }
/*     */   
/*     */   public void setExibirHostilInimigo(boolean exibirHostilInimigo) {
/* 163 */     this.exibirHostilInimigo = exibirHostilInimigo;
/*     */   }
/*     */   
/*     */   public boolean isExibirHostilNeutro() {
/* 167 */     return this.exibirHostilNeutro;
/*     */   }
/*     */   
/*     */   public void setExibirHostilNeutro(boolean exibirHostilNeutro) {
/* 171 */     this.exibirHostilNeutro = exibirHostilNeutro;
/*     */   }
/*     */   
/*     */   public void exibirTodasHostilidades(boolean habilitar) {
/* 175 */     this.exibirHostilAmigo = habilitar;
/* 176 */     this.exibirHostilDesc = habilitar;
/* 177 */     this.exibirHostilInimigo = habilitar;
/* 178 */     this.exibirHostilNeutro = habilitar;
/*     */   }
/*     */   
/*     */   public boolean isExibirTodasHostilidades() {
/* 182 */     return (this.exibirHostilAmigo && this.exibirHostilDesc && this.exibirHostilInimigo && this.exibirHostilNeutro);
/*     */   }
/*     */   
/*     */   public int getAmpliacao() {
/* 186 */     return this.ampliacao;
/*     */   }
/*     */   
/*     */   public void setAmpliacao(int ampliacao) {
/* 190 */     this.ampliacao = ampliacao;
/*     */   }
/*     */   
/*     */   public boolean isExibirRota() {
/* 194 */     return this.exibirRota;
/*     */   }
/*     */   
/*     */   public void setExibirRota(boolean exibirRota) {
/* 198 */     this.exibirRota = exibirRota;
/*     */   }
/*     */   
/*     */   public int getTamanhoVetorVeloc() {
/* 202 */     return this.tamanhoVetorVeloc;
/*     */   }
/*     */   
/*     */   public void setTamanhoVetorVeloc(int tamanhoVetorVeloc) {
/* 206 */     this.tamanhoVetorVeloc = tamanhoVetorVeloc;
/*     */   }
/*     */   
/*     */   public boolean isExibirPosicaoGPS() {
/* 210 */     return this.exibirPosicaoGPS;
/*     */   }
/*     */   
/*     */   public void setExibirPosicaoGPS(boolean exibirPosicaoGPS) {
/* 214 */     this.exibirPosicaoGPS = exibirPosicaoGPS;
/*     */   }
/*     */   
/*     */   public boolean isExibirTextos() {
/* 218 */     return this.exibirTextos;
/*     */   }
/*     */   
/*     */   public void setExibirTextos(boolean exibirTextos) {
/* 222 */     this.exibirTextos = exibirTextos;
/*     */   }
/*     */   
/*     */   public EnumFiltroPontosHistoricos tipoExibicaoPontosHistoricos() {
/* 226 */     return this.tipoExibicaoPontosHistoricos;
/*     */   }
/*     */   
/*     */   public void setTipoExibicaoPontosHistoricos(EnumFiltroPontosHistoricos exibirPontosHistoricos) {
/* 230 */     this.tipoExibicaoPontosHistoricos = exibirPontosHistoricos;
/*     */   }
/*     */   
/*     */   public boolean isExibirGiro() {
/* 234 */     return this.exibirGiro;
/*     */   }
/*     */   
/*     */   public boolean isExibirLinhaFomatura() {
/* 238 */     return this.exibirLinhaFomatura;
/*     */   }
/*     */   
/*     */   public void setExibirLinhaFomatura(boolean exibirLinhaFomatura) {
/* 242 */     this.exibirLinhaFomatura = exibirLinhaFomatura;
/*     */   }
/*     */   
/*     */   public boolean isExibirProjetil() {
/* 246 */     return this.exibirProjetil;
/*     */   }
/*     */   
/*     */   public void setExibirProjetil(boolean exibirProjetil) {
/* 250 */     this.exibirProjetil = exibirProjetil;
/*     */   }
/*     */   
/*     */   public boolean isExibirCameraFixa() {
/* 254 */     return this.exibirCameraFixa;
/*     */   }
/*     */   
/*     */   public void setExibirCameraFixa(boolean exibirCameraFixa) {
/* 258 */     this.exibirCameraFixa = exibirCameraFixa;
/*     */   }
/*     */   
/*     */   public void setExibirGiro(boolean exibirGiro) {
/* 262 */     this.exibirGiro = exibirGiro;
/*     */   }
/*     */   
/*     */   public boolean isExibirApenasLiberado() {
/* 266 */     return this.exibirApenasLiberado;
/*     */   }
/*     */   
/*     */   public void setExibirApenasLiberado(boolean exibirApenasLiberado) {
/* 270 */     this.exibirApenasLiberado = exibirApenasLiberado;
/*     */   }
/*     */   
/*     */   public boolean isExibirArcoFogo() {
/* 274 */     return this.exibirArcoFogo;
/*     */   }
/*     */   
/*     */   public void setExibirArcoFogo(boolean exibirArcoFogo) {
/* 278 */     this.exibirArcoFogo = exibirArcoFogo;
/*     */   }
/*     */   
/*     */   public boolean isExibirTrackRadarDT() {
/* 282 */     return this.exibirTrackRadarDT;
/*     */   }
/*     */   
/*     */   public void setExibirTrackRadarDT(boolean exibirTrackRadarDT) {
/* 286 */     this.exibirTrackRadarDT = exibirTrackRadarDT;
/*     */   }
/*     */   
/*     */   public boolean isExibirElementoNavegacao() {
/* 290 */     return this.exibirElementoNavegacao;
/*     */   }
/*     */   
/*     */   public void setExibirElementoNavegacao(boolean exibirElementoNavegacao) {
/* 294 */     this.exibirElementoNavegacao = exibirElementoNavegacao;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExibirObjDeteccaoVisual() {
/* 299 */     return this.exibirObjDeteccaoVisual;
/*     */   }
/*     */   
/*     */   public void setExibirObjDeteccaoVisual(boolean exibirObjDeteccaoVisual) {
/* 303 */     this.exibirObjDeteccaoVisual = exibirObjDeteccaoVisual;
/*     */   }
/*     */   
/*     */   public boolean isExibirObjBloqueioEletronico() {
/* 307 */     return this.exibirObjBloqueioEletronico;
/*     */   }
/*     */   
/*     */   public void setExibirObjBloqueioEletronico(boolean exibirObjBloqueioEletronico) {
/* 311 */     this.exibirObjBloqueioEletronico = exibirObjBloqueioEletronico;
/*     */   }
/*     */   
/*     */   public boolean isExibirObjChaffSuperficie() {
/* 315 */     return this.exibirObjChaffSuperficie;
/*     */   }
/*     */   
/*     */   public void setExibirObjChaffSuperficie(boolean exibirObjChaffSuperficie) {
/* 319 */     this.exibirObjChaffSuperficie = exibirObjChaffSuperficie;
/*     */   }
/*     */   
/*     */   public boolean isExibirObjChaffAeronave() {
/* 323 */     return this.exibirObjChaffAeronave;
/*     */   }
/*     */   
/*     */   public void setExibirObjChaffAeronave(boolean exibirObjChaffAeronave) {
/* 327 */     this.exibirObjChaffAeronave = exibirObjChaffAeronave;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExibirVeiculoSuperficie() {
/* 332 */     return isExibirAmbSup();
/*     */   }
/*     */   
/*     */   public boolean isExibirVeiculoAereo() {
/* 336 */     return isExibirAmbAer();
/*     */   }
/*     */   
/*     */   public boolean isExibirVeiculoSubmarino() {
/* 340 */     return isExibirAmbSub();
/*     */   }
/*     */   
/*     */   public boolean isExibirVeiculos() {
/* 344 */     return this.exibirVeiculos;
/*     */   }
/*     */   
/*     */   public void setExibirVeiculos(boolean exibeVeiculos) {
/* 348 */     this.exibirVeiculos = exibeVeiculos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isExibirVeiculosFantasmas() {
/* 357 */     return this.exibirVeiculosFantasmas;
/*     */   }
/*     */   
/*     */   public void setExibirVeiculosFantasmas(boolean exibirVeiculosFantasmas) {
/* 361 */     this.exibirVeiculosFantasmas = exibirVeiculosFantasmas;
/*     */   }
/*     */   
/*     */   public boolean isExibirProprioVeiculo() {
/* 365 */     return this.exibirProprioVeiculo;
/*     */   }
/*     */   
/*     */   public void setExibirProprioVeiculo(boolean exibirProprioVeiculo) {
/* 369 */     this.exibirProprioVeiculo = exibirProprioVeiculo;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExibirPlotMAD() {
/* 374 */     return this.exibirPlotMAD;
/*     */   }
/*     */   
/*     */   public void setExibirPlotMAD(boolean exibirPlotMAD) {
/* 378 */     this.exibirPlotMAD = exibirPlotMAD;
/*     */   }
/*     */   
/*     */   public boolean isExibirPlotRadar() {
/* 382 */     return this.exibirPlotRadar;
/*     */   }
/*     */   
/*     */   public void setExibirPlotRadar(boolean exibirPlotRadar) {
/* 386 */     this.exibirPlotRadar = exibirPlotRadar;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isExibirAcompanhamentoRTD() {
/* 391 */     return this.exibirAcompanhamentoRTD;
/*     */   }
/*     */   
/*     */   public void setExibirAcompanhamentoRTD(boolean exibirAcompanhamentoRTD) {
/* 395 */     this.exibirAcompanhamentoRTD = exibirAcompanhamentoRTD;
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
/*     */   public void setAcompanhamentos(PerfilUsuario.ExibirAcompanhamento tipo, boolean condicao) {
/* 408 */     for (PerfilUsuario.ExibirAcompanhamento exibirA : PerfilUsuario.ExibirAcompanhamento.values()) {
/* 409 */       if (exibirA.getDescricao().equals(tipo.getDescricao())) {
/* 410 */         if (exibirA.equals(PerfilUsuario.ExibirAcompanhamento.EXIBIR_TODOS_ACOMPANHAMENTOS)) {
/* 411 */           for (PerfilUsuario.ExibirAcompanhamento exibir : getMapaAcompanhamentos().keySet()) {
/* 412 */             this.mapaAcompanhamentos.put(exibir, Boolean.valueOf(condicao));
/*     */           }
/*     */         } else {
/* 415 */           this.mapaAcompanhamentos.put(exibirA, Boolean.valueOf(condicao));
/*     */         } 
/*     */         
/* 418 */         if (getMapaAcompanhamentos().keySet().stream()
/* 419 */           .filter(acomp -> !acomp.getDescricao().equals(PerfilUsuario.ExibirAcompanhamento.EXIBIR_TODOS_ACOMPANHAMENTOS.getDescricao()))
/* 420 */           .allMatch(cond -> (((Boolean)this.mapaAcompanhamentos.get(cond)).booleanValue() == true))) {
/* 421 */           getMapaAcompanhamentos().put(PerfilUsuario.ExibirAcompanhamento.EXIBIR_TODOS_ACOMPANHAMENTOS, Boolean.valueOf(true)); break;
/*     */         } 
/* 423 */         getMapaAcompanhamentos().put(PerfilUsuario.ExibirAcompanhamento.EXIBIR_TODOS_ACOMPANHAMENTOS, Boolean.valueOf(false));
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void preencherAcompanhamentos() {
/* 436 */     for (PerfilUsuario.ExibirAcompanhamento acomp : PerfilUsuario.getPerfilUsuarioAtual().listarAcompanhamentos()) {
/* 437 */       this.mapaAcompanhamentos.put(acomp, Boolean.valueOf(true));
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean isExibirAreaCircular() {
/* 442 */     return this.exibirAreaCircular;
/*     */   }
/*     */   
/*     */   public void setExibirAreaCircular(boolean exibirAreaCircular) {
/* 446 */     this.exibirAreaCircular = exibirAreaCircular;
/*     */   }
/*     */   
/*     */   public boolean isExibirAreaPoligonal() {
/* 450 */     return this.exibirAreaPoligonal;
/*     */   }
/*     */   
/*     */   public void setExibirAreaPoligonal(boolean exibirAreaPoligonal) {
/* 454 */     this.exibirAreaPoligonal = exibirAreaPoligonal;
/*     */   }
/*     */   
/*     */   public boolean isExibirSetor() {
/* 458 */     return this.exibirSetor;
/*     */   }
/*     */   
/*     */   public void setExibirSetor(boolean exibirSetor) {
/* 462 */     this.exibirSetor = exibirSetor;
/*     */   }
/*     */   
/*     */   public void exibirTodasAreas(boolean habilitar) {
/* 466 */     this.exibirAreaCircular = habilitar;
/* 467 */     this.exibirAreaPoligonal = habilitar;
/* 468 */     this.exibirSetor = habilitar;
/*     */   }
/*     */   
/*     */   public boolean isExibirTodasAreas() {
/* 472 */     return (this.exibirAreaCircular && this.exibirAreaPoligonal && this.exibirSetor);
/*     */   }
/*     */   
/*     */   public boolean isExibirCobertura() {
/* 476 */     return this.exibirCobertura;
/*     */   }
/*     */   
/*     */   public void setExibirCobertura(boolean exibirCobertura) {
/* 480 */     this.exibirCobertura = exibirCobertura;
/*     */   }
/*     */   
/*     */   public boolean isExibirPontosHistoricos() {
/* 484 */     return this.exibirPontosHistoricos;
/*     */   }
/*     */   
/*     */   public void setExibirPontosHistoricos(boolean exibirPontosHistoricos) {
/* 488 */     this.exibirPontosHistoricos = exibirPontosHistoricos;
/*     */   }
/*     */   
/*     */   public boolean isExibirPontosHistoricosEstendido() {
/* 492 */     return this.exibirPontosHistoricosEstendido;
/*     */   }
/*     */   
/*     */   public void setExibirPontosHistoricosEstendido(boolean exibirPontosHistoricosEstendido) {
/* 496 */     this.exibirPontosHistoricosEstendido = exibirPontosHistoricosEstendido;
/*     */   }
/*     */   
/*     */   public boolean isExibirGrade() {
/* 500 */     return this.exibirGrade;
/*     */   }
/*     */   
/*     */   public void setExibirGrade(boolean exibirGrade) {
/* 504 */     this.exibirGrade = exibirGrade;
/*     */   }
/*     */ 
/*     */   
/*     */   public void gravarEstado() {
/* 509 */     if (verificarCondicoesGravacao()) {
/* 510 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.OPACIDADE_OBJETOS_TATICOS, String.valueOf(getOpacidade()));
/* 511 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.TAMANHO_OBJETOS_TATICOS, String.valueOf(getAmpliacao()));
/* 512 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.VETOR_VELOC, String.valueOf(getTamanhoVetorVeloc()));
/*     */       
/* 514 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_HISTORICO_FT, String.valueOf(isExibirPontosHistoricos()));
/* 515 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_HISTORICO_ESTENDIDO_FT, String.valueOf(isExibirPontosHistoricosEstendido()));
/* 516 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_HISTORICO_OBJETOS, this.tipoExibicaoPontosHistoricos.name());
/* 517 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_DETECCAO_VISUAL, String.valueOf(isExibirObjDeteccaoVisual()));
/* 518 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_GRADE, String.valueOf(isExibirGrade()));
/* 519 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_ROTAS, String.valueOf(isExibirRota()));
/* 520 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_TEXTO_FT, String.valueOf(isExibirTextos()));
/*     */       
/* 522 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_AREA_CIRCULAR, String.valueOf(isExibirAreaCircular()));
/* 523 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_AREA_POLIGONAL, String.valueOf(isExibirAreaPoligonal()));
/* 524 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_SETOR, String.valueOf(isExibirSetor()));
/*     */       
/* 526 */       getMapaAcompanhamentos().keySet().forEach(chave -> Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", chave.name(), String.valueOf(getMapaAcompanhamentos().get(chave))));
/*     */ 
/*     */ 
/*     */       
/* 530 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_AMBIENTE_AEREO, String.valueOf(isExibirAmbAer()));
/* 531 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_AMBIENTE_SUPERFICIE, String.valueOf(isExibirAmbSup()));
/* 532 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_AMBIENTE_SUBMARINO, String.valueOf(isExibirAmbSub()));
/* 533 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_AMBIENTE_DESCONHECIDO, String.valueOf(isExibirAmbDesc()));
/*     */       
/* 535 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_HOSTILIDADE_AMIGO, String.valueOf(isExibirHostilAmigo()));
/* 536 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_HOSTILIDADE_INIMIGO, String.valueOf(isExibirHostilInimigo()));
/* 537 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_HOSTILIDADE_NEUTRO, String.valueOf(isExibirHostilNeutro()));
/* 538 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_HOSTILIDADE_DESCONHECIDO, String.valueOf(isExibirHostilDesc()));
/*     */       
/* 540 */       Ambiente.getInstance().alterarValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_OBJETO_TATICO_FT, String.valueOf(isExibir()));
/*     */       
/* 542 */       Ambiente.getInstance().persisteVariavelAmbiente("estado.properties");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void obterEstado() {
/* 548 */     if (GravarEstado.verificarRecuperacao()) {
/* 549 */       String opacidadeObjetosTatico = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.OPACIDADE_OBJETOS_TATICOS);
/* 550 */       if (!opacidadeObjetosTatico.equals("")) {
/* 551 */         setOpacidade(Float.parseFloat(opacidadeObjetosTatico));
/*     */       }
/*     */       
/* 554 */       String tamanhoObjetos = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.TAMANHO_OBJETOS_TATICOS);
/* 555 */       if (!tamanhoObjetos.equals("")) {
/* 556 */         setAmpliacao(Integer.parseInt(tamanhoObjetos));
/*     */       }
/*     */       
/* 559 */       String tamanhovetorVeloc = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.VETOR_VELOC);
/* 560 */       if (!tamanhovetorVeloc.equals("")) {
/* 561 */         setTamanhoVetorVeloc(Integer.parseInt(tamanhovetorVeloc));
/*     */       }
/*     */       
/* 564 */       String objTatico = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_OBJETO_TATICO_FT);
/* 565 */       if (!objTatico.equals("")) {
/* 566 */         setExibir(Boolean.parseBoolean(objTatico));
/*     */       }
/*     */       
/* 569 */       if (isExibir()) {
/* 570 */         String historico = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_HISTORICO_FT);
/* 571 */         if (!historico.equals("")) {
/* 572 */           setExibirPontosHistoricos(Boolean.parseBoolean(historico));
/*     */         }
/*     */         
/* 575 */         String historicoEstendido = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_HISTORICO_ESTENDIDO_FT);
/* 576 */         if (!historicoEstendido.equals("")) {
/* 577 */           setExibirPontosHistoricosEstendido(Boolean.parseBoolean(historicoEstendido));
/*     */         }
/*     */         
/* 580 */         String historicoEnum = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_HISTORICO_OBJETOS);
/* 581 */         if (!historicoEnum.equals("")) {
/* 582 */           setTipoExibicaoPontosHistoricos(EnumFiltroPontosHistoricos.valueOf(historicoEnum));
/*     */         }
/*     */         
/* 585 */         String deteccaoVisual = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_DETECCAO_VISUAL);
/* 586 */         if (!deteccaoVisual.equals("")) {
/* 587 */           setExibirObjDeteccaoVisual(Boolean.parseBoolean(deteccaoVisual));
/*     */         }
/*     */         
/* 590 */         String grade = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_GRADE);
/* 591 */         if (!grade.equals("")) {
/* 592 */           setExibirGrade(Boolean.parseBoolean(grade));
/*     */         }
/*     */         
/* 595 */         String mapa = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_MAPA_FT);
/* 596 */         if (!mapa.equals("")) {
/* 597 */           setExibir(Boolean.parseBoolean(mapa));
/*     */         }
/*     */         
/* 600 */         String exibirRotas = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_ROTAS);
/* 601 */         if (!exibirRotas.equals("")) {
/* 602 */           setExibirRota(Boolean.parseBoolean(exibirRotas));
/*     */         }
/*     */         
/* 605 */         String texto = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_TEXTO_FT);
/* 606 */         if (!texto.equals("")) {
/* 607 */           setExibirTextos(Boolean.parseBoolean(texto));
/*     */         }
/*     */         
/* 610 */         for (PerfilUsuario.ExibirAcompanhamento chave : getMapaAcompanhamentos().keySet()) {
/* 611 */           if (chave != PerfilUsuario.ExibirAcompanhamento.EXIBIR_TODOS_ACOMPANHAMENTOS) {
/* 612 */             String acomp = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", chave.name());
/* 613 */             if (!acomp.equals("")) {
/* 614 */               setAcompanhamentos(chave, Boolean.parseBoolean(acomp));
/*     */             }
/*     */           } 
/*     */         } 
/*     */         
/* 619 */         String areaCircular = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_AREA_CIRCULAR);
/* 620 */         if (!areaCircular.equals("")) {
/* 621 */           setExibirAreaCircular(Boolean.parseBoolean(areaCircular));
/*     */         }
/*     */         
/* 624 */         String areapoligonal = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_AREA_POLIGONAL);
/* 625 */         if (!areapoligonal.equals("")) {
/* 626 */           setExibirAreaPoligonal(Boolean.parseBoolean(areapoligonal));
/*     */         }
/*     */         
/* 629 */         String setor = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_SETOR);
/* 630 */         if (!setor.equals("")) {
/* 631 */           setExibirSetor(Boolean.parseBoolean(setor));
/*     */         }
/*     */         
/* 634 */         String hostilidadeAmigo = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_HOSTILIDADE_AMIGO);
/* 635 */         if (!hostilidadeAmigo.equals("")) {
/* 636 */           setExibirHostilAmigo(Boolean.parseBoolean(hostilidadeAmigo));
/*     */         }
/*     */         
/* 639 */         String hostilidadeInimigo = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_HOSTILIDADE_INIMIGO);
/* 640 */         if (!hostilidadeInimigo.equals("")) {
/* 641 */           setExibirHostilInimigo(Boolean.parseBoolean(hostilidadeInimigo));
/*     */         }
/*     */         
/* 644 */         String hostilidadeNeutro = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_HOSTILIDADE_NEUTRO);
/* 645 */         if (!hostilidadeNeutro.equals("")) {
/* 646 */           setExibirHostilNeutro(Boolean.parseBoolean(hostilidadeNeutro));
/*     */         }
/* 648 */         String hostilidadeDesc = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_HOSTILIDADE_DESCONHECIDO);
/* 649 */         if (!hostilidadeDesc.equals("")) {
/* 650 */           setExibirHostilDesc(Boolean.parseBoolean(hostilidadeDesc));
/*     */         }
/*     */         
/* 653 */         String ambienteAereo = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_AMBIENTE_AEREO);
/* 654 */         if (!ambienteAereo.equals("")) {
/* 655 */           setExibirAmbAer(Boolean.parseBoolean(ambienteAereo));
/*     */         }
/*     */         
/* 658 */         String ambienteSuperficie = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_AMBIENTE_SUPERFICIE);
/* 659 */         if (!ambienteSuperficie.equals("")) {
/* 660 */           setExibirAmbSup(Boolean.parseBoolean(ambienteSuperficie));
/*     */         }
/*     */         
/* 663 */         String ambienteSubmarino = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_AMBIENTE_SUBMARINO);
/* 664 */         if (!ambienteSubmarino.equals("")) {
/* 665 */           setExibirAmbSub(Boolean.parseBoolean(ambienteSubmarino));
/*     */         }
/*     */         
/* 668 */         String ambienteDesc = Ambiente.getInstance().obterValorVariavelAmbiente("estado.properties", VariaveisEstado.EXIBIR_AMBIENTE_DESCONHECIDO);
/* 669 */         if (!ambienteDesc.equals("")) {
/* 670 */           setExibirAmbDesc(Boolean.parseBoolean(ambienteDesc));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean isExibirCorrecoesManuaisEncarnadas() {
/* 677 */     return this.exibirCorrecoesManuaisEncarnadas;
/*     */   }
/*     */   
/*     */   public void setExibirCorrecoesManuaisEncarnadas(boolean exibirCorrecoesManuaisEncarnadas) {
/* 681 */     this.exibirCorrecoesManuaisEncarnadas = exibirCorrecoesManuaisEncarnadas;
/*     */   }
/*     */   
/*     */   public boolean isExibirCorrecoesManuaisDeletadas() {
/* 685 */     return this.exibirCorrecoesManuaisDeletadas;
/*     */   }
/*     */   
/*     */   public void setExibirCorrecoesManuaisDeletadas(boolean exibirCorrecoesManuaisDeletadas) {
/* 689 */     this.exibirCorrecoesManuaisDeletadas = exibirCorrecoesManuaisDeletadas;
/*     */   }
/*     */   
/*     */   public boolean isExibirCorrecoesManuaisTemporarias() {
/* 693 */     return this.exibirCorrecoesManuaisTemporarias;
/*     */   }
/*     */   
/*     */   public void setExibirCorrecoesManuaisTemporarias(boolean exibirCorrecoesManuaisTemporarias) {
/* 697 */     this.exibirCorrecoesManuaisTemporarias = exibirCorrecoesManuaisTemporarias;
/*     */   }
/*     */   
/*     */   public boolean isNaoExibirCorrecoesManuais() {
/* 701 */     return this.naoExibirCorrecoesManuais;
/*     */   }
/*     */   
/*     */   public void setNaoExibirCorrecoesManuais(boolean naoExibirCorrecoesManuais) {
/* 705 */     this.naoExibirCorrecoesManuais = naoExibirCorrecoesManuais;
/*     */   }
/*     */   
/*     */   public int getIntervaloTempoPontosEstendidos() {
/* 709 */     return this.intervaloTempoPontosEstendidos;
/*     */   }
/*     */   
/*     */   public void setIntervaloTempoPontosEstendidos(int intervaloTempoPontosEstendidos) {
/* 713 */     this.intervaloTempoPontosEstendidos = intervaloTempoPontosEstendidos;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/camadas/filtros/FiltroObjetoTatico.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */