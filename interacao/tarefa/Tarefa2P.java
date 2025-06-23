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
/*    */ public abstract class Tarefa2P<E extends CanvasEspacial>
/*    */   extends Tarefa<E>
/*    */ {
/*    */   protected CoordenadaCartesiana p1;
/*    */   protected CoordenadaCartesiana p2;
/*    */   protected boolean cancelado;
/*    */   
/*    */   public Tarefa2P(E canvas) {
/* 19 */     super(canvas);
/*    */   }
/*    */ 
/*    */   
/*    */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/* 24 */     this.p1 = p;
/* 25 */     this.p2 = p;
/* 26 */     this.cancelado = false;
/*    */   }
/*    */ 
/*    */   
/*    */   public void botao1Arrastado(CoordenadaCartesiana p, EstadoTeclado e) {
/* 31 */     if (this.p1 == null || p == null || e == null) {
/*    */       return;
/*    */     }
/* 34 */     this.p2 = p;
/* 35 */     if (e.isAltDown()) {
/* 36 */       if (Math.abs(p.getX() - this.p1.getX()) > Math.abs(p.getY() - this.p1.getY())) {
/* 37 */         this.p2.setY(this.p1.getY());
/*    */       } else {
/* 39 */         this.p2.setX(this.p1.getX());
/*    */       } 
/*    */     }
/* 42 */     desenha();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void botao1Solto(CoordenadaCartesiana p, EstadoTeclado e) {
/* 48 */     if (this.p1 == null || p == null || e == null) {
/*    */       return;
/*    */     }
/*    */     
/* 52 */     if (this.p1.getX() == this.p2.getX() && this.p1.getY() == this.p2.getY()) {
/* 53 */       if (!this.cancelado) {
/* 54 */         criaObjeto(this.p1);
/*    */       }
/* 56 */     } else if (!this.cancelado) {
/* 57 */       criaObjeto(this.p1, this.p2);
/*    */     } 
/* 59 */     this.p1 = this.p2 = null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract void criaObjeto(CoordenadaCartesiana paramCoordenadaCartesiana);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract void criaObjeto(CoordenadaCartesiana paramCoordenadaCartesiana1, CoordenadaCartesiana paramCoordenadaCartesiana2);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void cancelarTarefa() {
/* 83 */     this.cancelado = true;
/*    */     
/* 85 */     getCanvasEspacial().getCamadaInfo().tornaTransparente();
/* 86 */     getCanvasEspacial().getCamadaInfo().geraImagemFX();
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/Tarefa2P.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */