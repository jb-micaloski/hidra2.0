/*    */ package ipqm.gsd.hidra.ihm.interacao;
/*    */ 
/*    */ import javafx.scene.Group;
/*    */ import javafx.scene.transform.Rotate;
/*    */ import javafx.scene.transform.Scale;
/*    */ import javafx.scene.transform.Transform;
/*    */ import javafx.scene.transform.Translate;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class GrupoCamera
/*    */   extends Group
/*    */ {
/* 14 */   private final Translate t = new Translate();
/*    */   private final Rotate rx;
/*    */   private final Rotate ry;
/* 17 */   private final Rotate rz = new Rotate();
/* 18 */   private final Scale s = new Scale();
/*    */ 
/*    */   
/*    */   public GrupoCamera(double largura, double altura) {
/* 22 */     this.rx = new Rotate(0.0D, 0.0D, altura / 2.0D);
/* 23 */     this.ry = new Rotate(0.0D, largura / 2.0D, 0.0D);
/* 24 */     this.rx.setAxis(Rotate.X_AXIS);
/* 25 */     this.ry.setAxis(Rotate.Y_AXIS);
/* 26 */     this.rz.setAxis(Rotate.Z_AXIS);
/* 27 */     getTransforms().addAll((Object[])new Transform[] { (Transform)this.t, (Transform)this.rz, (Transform)this.ry, (Transform)this.rx, (Transform)this.s });
/*    */   }
/*    */ 
/*    */   
/*    */   public Translate getT() {
/* 32 */     return this.t;
/*    */   }
/*    */   
/*    */   public Rotate getRx() {
/* 36 */     return this.rx;
/*    */   }
/*    */   
/*    */   public Rotate getRy() {
/* 40 */     return this.ry;
/*    */   }
/*    */   
/*    */   public Rotate getRz() {
/* 44 */     return this.rz;
/*    */   }
/*    */   
/*    */   public Scale getS() {
/* 48 */     return this.s;
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/GrupoCamera.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */