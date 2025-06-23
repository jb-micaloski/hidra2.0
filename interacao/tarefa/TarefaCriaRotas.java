/*     */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaRaster;
/*     */ import ipqm.gsd.componentes.dominio.entidades.Veiculo;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.Pernada;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.PlanoNavegacao;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.Rota;
/*     */ import ipqm.gsd.componentes.dominio.navegacao.WayPoint;
/*     */ import ipqm.gsd.componentes.dominio.teatro_operacao.CondicaoInicial;
/*     */ import ipqm.gsd.componentes.nucleo.Ambiente;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.NucleoAmbiente;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.logon.perfis.PerfilUsuario;
/*     */ import ipqm.gsd.componentes.nucleo.util.UtilData;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasEspacial;
/*     */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*     */ import ipqm.gsd.hidra.ihm.util.FusoHorarioStringConverter;
/*     */ import ipqm.gsd.hidra.ihm.util.desenho.UtilJava2D;
/*     */ import ipqm.gsd.smaps.model.coord.Geodesics;
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import javafx.application.Platform;
/*     */ import javafx.scene.Cursor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TarefaCriaRotas
/*     */   extends TarefaNP<CanvasCenarioTatico>
/*     */ {
/*  46 */   private static int numRota = 1;
/*     */   
/*     */   private Cursor cursor;
/*     */   private boolean statusMoveCarta = false;
/*     */   private TarefaSelecaoObjetos tarefaSelecao;
/*     */   
/*     */   public TarefaCriaRotas(CanvasCenarioTatico canvas) {
/*  53 */     super(canvas);
/*     */   }
/*     */ 
/*     */   
/*     */   public void inicializa() {
/*  58 */     this.cursor = getCanvasEspacial().getCursor();
/*  59 */     getCanvasEspacial().setCursor(Cursor.CROSSHAIR);
/*  60 */     this.tarefaSelecao = (TarefaSelecaoObjetos)getCanvasEspacial().getTarefa(CanvasEspacial.Tarefas.SELECAO_OBJETOS);
/*  61 */     Platform.runLater(() -> this.tarefaSelecao.adicionaRegiaoLimiteSuperior());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void finaliza() {
/*  68 */     getCanvasEspacial().setCursor(this.cursor);
/*  69 */     Platform.runLater(() -> this.tarefaSelecao.removeRegiaoLimiteSuperior());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseSaiu(CoordenadaCartesiana p) {
/*  76 */     if (this.statusMoveCarta) {
/*  77 */       this.tarefaSelecao.moveCarta(p);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void mouseMovido(CoordenadaCartesiana p, EstadoTeclado e) {
/*  83 */     super.mouseMovido(p, e);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void desenha() {
/*  89 */     CanvasCenarioTatico canvas = getCanvasEspacial();
/*  90 */     synchronized (canvas.getCamadaInfo()) {
/*  91 */       canvas.getCamadaInfo().tornaTransparente();
/*     */       
/*  93 */       if (this.vertices.isEmpty()) {
/*  94 */         canvas.getCamadaInfo().geraImagemFX();
/*     */         
/*     */         return;
/*     */       } 
/*  98 */       Graphics2D g2d = canvas.getCamadaInfo().getImagem().createGraphics();
/*     */       
/* 100 */       Color cor = Color.yellow;
/*     */       
/* 102 */       g2d.setStroke(new BasicStroke(1.0F));
/* 103 */       GeneralPath caminho = new GeneralPath();
/*     */       
/* 105 */       if (this.p != null) {
/* 106 */         CoordenadaRaster pR = canvas.projeta(this.p);
/* 107 */         g2d.setStroke(new BasicStroke(2.0F));
/*     */         
/* 109 */         Color corCarmim = new Color(141, 58, 47);
/* 110 */         g2d.setColor(corCarmim);
/* 111 */         g2d.drawLine(0, pR.getY(), canvas.getLargura() - 1, pR.getY());
/* 112 */         g2d.drawLine(pR.getX(), 0, pR.getX(), canvas.getAltura() - 1);
/* 113 */         this.statusMoveCarta = true;
/* 114 */         canvas.setRedesenhoAutomatico(true);
/* 115 */         canvas.setEnquadramento(false);
/*     */       } 
/*     */       
/* 118 */       CoordenadaRaster inicio = canvas.projeta(this.vertices.get(0));
/* 119 */       caminho.moveTo(inicio.getX(), inicio.getY());
/* 120 */       for (int i = 1; i < this.vertices.size(); i++) {
/* 121 */         CoordenadaRaster p = canvas.projeta(this.vertices.get(i));
/* 122 */         caminho.lineTo(p.getX(), p.getY());
/*     */       } 
/*     */       
/* 125 */       CoordenadaRaster fim = (this.p != null) ? canvas.projeta(this.p) : null;
/* 126 */       if (fim != null) {
/* 127 */         caminho.lineTo(fim.getX(), fim.getY());
/*     */       }
/*     */       
/* 130 */       int larguraCanalRaster = canvas.distancia(0.26997840172786175D);
/* 131 */       g2d.setColor(cor);
/* 132 */       g2d.setStroke(new BasicStroke((2 * larguraCanalRaster), 0, 1));
/* 133 */       g2d.setComposite(AlphaComposite.getInstance(3, 0.1F));
/* 134 */       g2d.draw(caminho);
/*     */       
/* 136 */       float[] dashPattern = { 5.0F, 5.0F };
/* 137 */       g2d.setStroke(new BasicStroke(2.0F, 0, 0, 10.0F, dashPattern, 0.0F));
/* 138 */       g2d.setColor(cor);
/* 139 */       g2d.setComposite(AlphaComposite.getInstance(3, 1.0F));
/*     */       
/* 141 */       for (int j = 1; j < this.vertices.size(); j++) {
/* 142 */         CoordenadaRaster p1Raster = canvas.projeta(this.vertices.get(j - 1));
/* 143 */         CoordenadaRaster p2Raster = canvas.projeta(this.vertices.get(j));
/* 144 */         UtilJava2D.desenhaTracejado(g2d, p1Raster.getX(), p1Raster.getY(), p2Raster.getX(), p2Raster.getY());
/*     */       } 
/* 146 */       if (fim != null) {
/* 147 */         CoordenadaRaster p2Raster = canvas.projeta(this.vertices.get(this.vertices.size() - 1));
/* 148 */         UtilJava2D.desenhaTracejado(g2d, p2Raster.getX(), p2Raster.getY(), fim.getX(), fim.getY());
/*     */       } 
/*     */ 
/*     */       
/* 152 */       for (CoordenadaCartesiana posicao : this.vertices) {
/* 153 */         CoordenadaRaster posicaoRaster = canvas.projeta(posicao);
/* 154 */         int raioRaster = 8;
/*     */         
/* 156 */         g2d.setColor(cor);
/* 157 */         g2d.setComposite(AlphaComposite.getInstance(3, 1.0F));
/*     */         
/* 159 */         g2d.setStroke(new BasicStroke(2.0F));
/* 160 */         g2d.drawOval(posicaoRaster.getX() - raioRaster, posicaoRaster.getY() - raioRaster, raioRaster * 2, raioRaster * 2);
/*     */       } 
/*     */       
/* 163 */       g2d.dispose();
/* 164 */       canvas.getCamadaInfo().geraImagemFX();
/* 165 */       canvas.setRedesenhoAutomatico(true);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void criaRota(Rota rota) {
/* 170 */     PlanoNavegacao planoCorrente = PlanoNavegacao.obterPlanoCorrente();
/* 171 */     if (planoCorrente == null) {
/*     */       try {
/* 173 */         planoCorrente = CondicaoInicial.getCondicaoInicialLocal().listarPlanoNavegacaoCorrentePorCondicao();
/* 174 */       } catch (Exception ex) {
/* 175 */         Log.gravarLogExcecao("Erro ao listar o plano de navegação corrente", this, ex);
/*     */         return;
/*     */       } 
/*     */     }
/*     */     try {
/* 180 */       if (planoCorrente.getRotaAlternativa() != null) {
/* 181 */         planoCorrente.getRotaAlternativa().excluir(true);
/*     */       }
/*     */       
/* 184 */       rota.setAlternativa(true);
/* 185 */       rota.salvarBDMemoria(true);
/* 186 */     } catch (Exception ex) {
/* 187 */       Log.gravarLogExcecao("Erro ao salvar rota", this, ex);
/*     */     } 
/*     */     
/* 190 */     getCanvasEspacial().getCamadaInfo().tornaTransparente();
/* 191 */     planoCorrente.setRotaAlternativa(rota);
/*     */     
/*     */     try {
/* 194 */       planoCorrente.atualizarBD(true);
/* 195 */     } catch (Exception ex) {
/* 196 */       Log.gravarLogExcecao("Erro ao atualizar plano corrente.", this, ex);
/*     */     } 
/*     */     
/* 199 */     planoCorrente.salvarMemoria(true);
/*     */     
/* 201 */     this.statusMoveCarta = false;
/* 202 */     getCanvasEspacial().setRedesenhoAutomatico(true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void criaObjeto(List<CoordenadaCartesiana> vertices) {
/* 208 */     if (vertices.size() >= 2) {
/*     */       
/* 210 */       double xtdBombordoPadrao = 250.0D;
/* 211 */       double xtdBorestePadrao = 250.0D;
/* 212 */       double velocidadePadrao = 15.0D;
/* 213 */       double tempoPermanenciaPadrao = 0.0D;
/* 214 */       Random random = new Random();
/* 215 */       String timeZone = Ambiente.getInstance().obterValorVariavelAmbiente(NucleoAmbiente.TIMEZONE_INICIAL);
/* 216 */       long timeZoneDefault = FusoHorarioStringConverter.fromString(timeZone).longValue();
/*     */       
/* 218 */       if (!PerfilUsuario.getPerfilUsuarioAtual().isUsuarioInstrutor()) {
/* 219 */         velocidadePadrao = Veiculo.getVeiculoReferencial().getVelocidadeFundo();
/*     */       }
/*     */ 
/*     */       
/* 223 */       Rota rota = new Rota(null, "Rota" + (Math.abs(random.nextInt()) % 100000), "origem", "destino");
/*     */ 
/*     */       
/* 226 */       List<WayPoint> listaWayPoints = new ArrayList<>();
/* 227 */       for (int i = 0; i < vertices.size(); i++) {
/*     */ 
/*     */         
/* 230 */         WayPoint wp = new WayPoint(null, "wp-" + i, vertices.get(i));
/* 231 */         wp.setTempoPrevistoPermanencia(tempoPermanenciaPadrao);
/* 232 */         wp.setFusoHorario(Long.valueOf(timeZoneDefault));
/*     */         
/* 234 */         if (!PerfilUsuario.getPerfilUsuarioAtual().isUsuarioInstrutor())
/*     */         {
/* 236 */           if (velocidadePadrao >= 0.0D) {
/* 237 */             if (i == 0) {
/* 238 */               wp.setHoraEstimadaPartida(Mediador.getRelogio().getHorario());
/*     */             }
/*     */             else {
/*     */               
/* 242 */               WayPoint wpAnterior = listaWayPoints.get(i - 1);
/* 243 */               double comprimento = Geodesics.distance(wpAnterior.getLongitude(), wpAnterior.getLatitude(), wp.getLongitude(), wp.getLatitude());
/* 244 */               double tempoPernada = UtilData.converterHorasMilissegundos(comprimento / Veiculo.getVeiculoReferencial().getVelocidadeFundo());
/* 245 */               wp.setHoraEstimadaChegada(wpAnterior.getHoraEstimadaPartida() + (long)tempoPernada);
/* 246 */               wp.setHoraEstimadaPartida(wp.getHoraEstimadaChegada() + (long)wp.getTempoPrevistoPermanencia());
/*     */             } 
/*     */           }
/*     */         }
/*     */         
/* 251 */         listaWayPoints.add(wp);
/*     */       } 
/*     */ 
/*     */       
/* 255 */       List<Pernada> listaPernadas = new ArrayList<>();
/* 256 */       Pernada p = new Pernada();
/* 257 */       for (int j = 0; j < listaWayPoints.size(); j++) {
/*     */         try {
/* 259 */           if (j == 0) {
/* 260 */             p = new Pernada(j + 1, listaWayPoints.get(j), null, 0.0D, 0.0D, 0.0D);
/* 261 */             p.setVelocidadePlanejada(10.0D);
/*     */           } else {
/* 263 */             p = new Pernada(j + 1, listaWayPoints.get(j), Pernada.TipoPernada.RHUMB_LINE, xtdBombordoPadrao, xtdBorestePadrao, velocidadePadrao);
/* 264 */             p.setVelocidadePlanejada(10.0D);
/*     */           } 
/* 266 */         } catch (Exception e) {
/* 267 */           Log.gravarLogExcecao("Erro ao criar rota", this, e);
/*     */         } 
/* 269 */         listaPernadas.add(p);
/*     */       } 
/*     */ 
/*     */       
/* 273 */       for (Pernada pernada : listaPernadas) {
/* 274 */         rota.getListaPernadas().add(pernada);
/*     */       }
/*     */       
/* 277 */       criaRota(rota);
/* 278 */       numRota++;
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
/*     */ 
/*     */   
/*     */   public void executarComando(Comando comando, CoordenadaCartesiana posicao) {
/* 299 */     if (isHabilitarComando(comando)) {
/* 300 */       switch (comando) {
/*     */         case CRIA_ROTA:
/* 302 */           criaObjeto(this.vertices);
/* 303 */           this.statusMoveCarta = false;
/* 304 */           this.vertices.clear();
/*     */           break;
/*     */         case CANCELA_ROTA:
/* 307 */           this.vertices.clear();
/* 308 */           this.statusMoveCarta = false;
/*     */           break;
/*     */         case REMOVE_ULT_WP:
/* 311 */           if (this.vertices.size() > 0) {
/* 312 */             this.vertices.remove(this.vertices.size() - 1);
/* 313 */             if (this.vertices.isEmpty()) {
/* 314 */               this.statusMoveCarta = false;
/*     */             }
/*     */           } 
/*     */           break;
/*     */       } 
/*     */       
/* 320 */       desenha();
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
/*     */   public void habilitarComandos() {
/* 335 */     if (!this.vertices.isEmpty()) {
/*     */       
/* 337 */       setHabilitarComando(Comando.CRIA_ROTA, true);
/* 338 */       setHabilitarComando(Comando.CANCELA_ROTA, true);
/* 339 */       setHabilitarComando(Comando.REMOVE_ULT_WP, true);
/*     */     }
/*     */     else {
/*     */       
/* 343 */       setHabilitarComando(Comando.CRIA_ROTA, false);
/* 344 */       setHabilitarComando(Comando.CANCELA_ROTA, false);
/* 345 */       setHabilitarComando(Comando.REMOVE_ULT_WP, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<Comando, Boolean> obterComandosHabilitados() {
/* 372 */     Map<Comando, Boolean> comandosHabilitados = new HashMap<>();
/*     */     
/* 374 */     comandosHabilitados.put(Comando.CRIA_ROTA, Boolean.valueOf(isHabilitarComando(Comando.CRIA_ROTA)));
/* 375 */     comandosHabilitados.put(Comando.CANCELA_ROTA, Boolean.valueOf(isHabilitarComando(Comando.CANCELA_ROTA)));
/* 376 */     comandosHabilitados.put(Comando.REMOVE_ULT_WP, Boolean.valueOf(isHabilitarComando(Comando.REMOVE_ULT_WP)));
/*     */     
/* 378 */     return comandosHabilitados;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaCriaRotas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */