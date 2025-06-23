/*    */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*    */ 
/*    */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
/*    */ import ipqm.gsd.hidra.ihm.interacao.CanvasCenarioTatico;
/*    */ import ipqm.gsd.hidra.ihm.interacao.CanvasEspacial;
/*    */ import ipqm.gsd.hidra.ihm.interacao.EstadoTeclado;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import javafx.scene.input.KeyCode;
/*    */ import javafx.scene.input.KeyEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class TarefaNP<E extends CanvasEspacial>
/*    */   extends Tarefa<E>
/*    */ {
/* 19 */   protected List<CoordenadaCartesiana> vertices = new ArrayList<>();
/*    */   protected CoordenadaCartesiana p;
/*    */   
/*    */   public TarefaNP(E canvas) {
/* 23 */     super(canvas);
/*    */   }
/*    */ 
/*    */   
/*    */   public void botao1Pressionado(CoordenadaCartesiana p, EstadoTeclado e) {
/* 28 */     this.vertices.add(p);
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseMovido(CoordenadaCartesiana p, EstadoTeclado e) {
/* 33 */     this.p = p;
/* 34 */     desenha();
/* 35 */     ((CanvasCenarioTatico)getCanvasEspacial()).atualizarCamadaTatica();
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseSaiu(CoordenadaCartesiana p) {
/* 40 */     this.p = null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void teclaPressionada(KeyEvent event) {
/* 52 */     switch (event.getCode()) {
/*    */       case ENTER:
/* 54 */         criaObjeto(this.vertices);
/* 55 */         this.vertices.clear();
/*    */         break;
/*    */       case BACK_SPACE:
/*    */       case DELETE:
/* 59 */         if (this.vertices.size() > 0) {
/* 60 */           this.vertices.remove(this.vertices.size() - 1);
/* 61 */           desenha();
/*    */         } 
/*    */         break;
/*    */       case ESCAPE:
/* 65 */         this.vertices.clear();
/* 66 */         desenha();
/*    */         break;
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract void criaObjeto(List<CoordenadaCartesiana> paramList);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void cancelarTarefa() {
/* 81 */     this.vertices.clear();
/*    */     
/* 83 */     getCanvasEspacial().getCamadaInfo().tornaTransparente();
/* 84 */     getCanvasEspacial().getCamadaInfo().geraImagemFX();
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/TarefaNP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */