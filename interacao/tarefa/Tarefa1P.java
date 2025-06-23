/*    */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*    */ 
/*    */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*    */ import ipqm.gsd.hidra.ihm.interacao.CanvasEspacial;
/*    */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class Tarefa1P<E extends CanvasEspacial>
/*    */   extends Tarefa<E>
/*    */ {
/*    */   protected CoordenadaCartesiana p;
/*    */   
/*    */   public Tarefa1P(E canvas) {
/* 18 */     super(canvas);
/*    */   }
/*    */ 
/*    */   
/*    */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/* 23 */     this.p = p;
/*    */   }
/*    */ 
/*    */   
/*    */   public void botao1Solto(CoordenadaCartesiana p2, EstadoTeclado e) {
/* 28 */     if (this.p.getX() == p2.getX() && this.p.getY() == p2.getY()) {
/* 29 */       criaObjeto(this.p);
/*    */     }
/* 31 */     this.p = null;
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseSaiu(CoordenadaCartesiana p) {
/* 36 */     p = null;
/*    */   }
/*    */   
/*    */   public abstract void criaObjeto(CoordenadaCartesiana paramCoordenadaCartesiana);
/*    */   
/*    */   public void cancelarTarefa() {}
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/Tarefa1P.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */