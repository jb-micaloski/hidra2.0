/*     */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*     */ import ipqm.gsd.hidra.ihm.interacao.CanvasEspacial;
/*     */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*     */ import ipqm.gsd.hidra.ihm.interacao.ModeloEventos;
/*     */ import java.util.Map;
/*     */ import javafx.scene.input.KeyEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Tarefa<E extends CanvasEspacial>
/*     */   implements ModeloTarefa, ModeloEventos
/*     */ {
/*     */   private E canvasEspacial;
/*     */   
/*     */   public Tarefa() {}
/*     */   
/*     */   public Tarefa(E canvasEspacial) {
/*  25 */     this.canvasEspacial = canvasEspacial;
/*     */   }
/*     */   
/*     */   public E getCanvasEspacial() {
/*  29 */     return this.canvasEspacial;
/*     */   }
/*     */   
/*     */   public void setCanvasEspacial(E canvasEspacial) {
/*  33 */     this.canvasEspacial = canvasEspacial;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void botao1Arrastado(CoordenadaCartesiana p, EstadoTeclado e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void botao1Solto(CoordenadaCartesiana p, EstadoTeclado e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void botao2Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void botao2Arrastado(CoordenadaCartesiana p, EstadoTeclado e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void botao2Solto(CoordenadaCartesiana p, EstadoTeclado e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void botao3Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void botao3Arrastado(CoordenadaCartesiana p, EstadoTeclado e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void botao3Solto(CoordenadaCartesiana p, EstadoTeclado e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void duploClique(CoordenadaCartesiana p, EstadoTeclado e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseMovido(CoordenadaCartesiana p, EstadoTeclado e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseSaiu(CoordenadaCartesiana p) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseEntrou(CoordenadaCartesiana p) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void mouseRodou(int rotacao) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void teclaPressionada(KeyEvent event) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void desenha() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void criaMenuContexto() {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void executaMenuContexto(Comando comando, CoordenadaCartesiana pos) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract void cancelarTarefa();
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHabilitarComando(Comando comando, boolean habilitaComando) {
/* 125 */     comando.setHabilitado(habilitaComando);
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
/*     */   public boolean isHabilitarComando(Comando comando) {
/* 140 */     return comando.isHabilitado();
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
/* 154 */     throw new UnsupportedOperationException("Operacao nao suportada para esta tarefa.");
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
/*     */   public Map<Comando, Boolean> obterComandosHabilitados() {
/* 177 */     throw new UnsupportedOperationException("Operacao nao suportada para esta tarefa.");
/*     */   }
/*     */   
/*     */   public void executarComando(Comando comando, CoordenadaCartesiana posicao) {}
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/Tarefa.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */