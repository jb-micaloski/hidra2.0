/*     */ package ipqm.gsd.hidra.ihm.interacao;
/*     */ 
/*     */ import javafx.scene.Group;
/*     */ import javafx.scene.transform.Rotate;
/*     */ import javafx.scene.transform.Scale;
/*     */ import javafx.scene.transform.Transform;
/*     */ import javafx.scene.transform.Translate;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Xform
/*     */   extends Group
/*     */ {
/*     */   public enum RotateOrder
/*     */   {
/*  43 */     XYZ, XZY, YXZ, YZX, ZXY, ZYX;
/*     */   }
/*     */   
/*  46 */   public Translate t = new Translate();
/*  47 */   public Translate p = new Translate();
/*  48 */   public Translate ip = new Translate(); public Rotate ry; public Rotate rz;
/*  49 */   public Rotate rx = new Rotate(); public Scale s; public Xform() {
/*  50 */     this.rx.setAxis(Rotate.X_AXIS);
/*  51 */     this.ry = new Rotate();
/*  52 */     this.ry.setAxis(Rotate.Y_AXIS);
/*  53 */     this.rz = new Rotate();
/*  54 */     this.rz.setAxis(Rotate.Z_AXIS);
/*  55 */     this.s = new Scale();
/*     */ 
/*     */ 
/*     */     
/*  59 */     getTransforms().addAll((Object[])new Transform[] { (Transform)this.t, (Transform)this.rz, (Transform)this.ry, (Transform)this.rx, (Transform)this.s }); } public Xform(RotateOrder rotateOrder) { this.rx.setAxis(Rotate.X_AXIS);
/*     */     this.ry = new Rotate();
/*     */     this.ry.setAxis(Rotate.Y_AXIS);
/*     */     this.rz = new Rotate();
/*     */     this.rz.setAxis(Rotate.Z_AXIS);
/*     */     this.s = new Scale();
/*  65 */     switch (rotateOrder) {
/*     */       case XYZ:
/*  67 */         getTransforms().addAll((Object[])new Transform[] { (Transform)this.t, (Transform)this.p, (Transform)this.rz, (Transform)this.ry, (Transform)this.rx, (Transform)this.s, (Transform)this.ip });
/*     */         break;
/*     */       case XZY:
/*  70 */         getTransforms().addAll((Object[])new Transform[] { (Transform)this.t, (Transform)this.p, (Transform)this.ry, (Transform)this.rz, (Transform)this.rx, (Transform)this.s, (Transform)this.ip });
/*     */         break;
/*     */       case YXZ:
/*  73 */         getTransforms().addAll((Object[])new Transform[] { (Transform)this.t, (Transform)this.p, (Transform)this.rz, (Transform)this.rx, (Transform)this.ry, (Transform)this.s, (Transform)this.ip });
/*     */         break;
/*     */       case YZX:
/*  76 */         getTransforms().addAll((Object[])new Transform[] { (Transform)this.t, (Transform)this.p, (Transform)this.rx, (Transform)this.rz, (Transform)this.ry, (Transform)this.s, (Transform)this.ip });
/*     */         break;
/*     */       case ZXY:
/*  79 */         getTransforms().addAll((Object[])new Transform[] { (Transform)this.t, (Transform)this.p, (Transform)this.ry, (Transform)this.rx, (Transform)this.rz, (Transform)this.s, (Transform)this.ip });
/*     */         break;
/*     */       case ZYX:
/*  82 */         getTransforms().addAll((Object[])new Transform[] { (Transform)this.t, (Transform)this.p, (Transform)this.rx, (Transform)this.ry, (Transform)this.rz, (Transform)this.s, (Transform)this.ip });
/*     */         break;
/*     */     }  }
/*     */ 
/*     */   
/*     */   public void setTranslate(double x, double y, double z) {
/*  88 */     this.t.setX(x);
/*  89 */     this.t.setY(y);
/*  90 */     this.t.setZ(z);
/*     */   }
/*     */   
/*     */   public void setTranslate(double x, double y) {
/*  94 */     this.t.setX(x);
/*  95 */     this.t.setY(y);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTx(double x) {
/* 103 */     this.t.setX(x);
/* 104 */   } public void setTy(double y) { this.t.setY(y); } public void setTz(double z) {
/* 105 */     this.t.setZ(z);
/*     */   }
/*     */   public void setRotate(double x, double y, double z) {
/* 108 */     this.rx.setAngle(x);
/* 109 */     this.ry.setAngle(y);
/* 110 */     this.rz.setAngle(z);
/*     */   }
/*     */   
/* 113 */   public void setRotateX(double x) { this.rx.setAngle(x); }
/* 114 */   public void setRotateY(double y) { this.ry.setAngle(y); }
/* 115 */   public void setRotateZ(double z) { this.rz.setAngle(z); }
/* 116 */   public void setRx(double x) { this.rx.setAngle(x); }
/* 117 */   public void setRy(double y) { this.ry.setAngle(y); } public void setRz(double z) {
/* 118 */     this.rz.setAngle(z);
/*     */   }
/*     */   public void setScale(double scaleFactor) {
/* 121 */     this.s.setX(scaleFactor);
/* 122 */     this.s.setY(scaleFactor);
/* 123 */     this.s.setZ(scaleFactor);
/*     */   }
/*     */   
/*     */   public void setScale(double x, double y, double z) {
/* 127 */     this.s.setX(x);
/* 128 */     this.s.setY(y);
/* 129 */     this.s.setZ(z);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSx(double x) {
/* 137 */     this.s.setX(x);
/* 138 */   } public void setSy(double y) { this.s.setY(y); } public void setSz(double z) {
/* 139 */     this.s.setZ(z);
/*     */   }
/*     */   public void setPivot(double x, double y, double z) {
/* 142 */     this.p.setX(x);
/* 143 */     this.p.setY(y);
/* 144 */     this.p.setZ(z);
/* 145 */     this.ip.setX(-x);
/* 146 */     this.ip.setY(-y);
/* 147 */     this.ip.setZ(-z);
/*     */   }
/*     */   
/*     */   public void reset() {
/* 151 */     this.t.setX(0.0D);
/* 152 */     this.t.setY(0.0D);
/* 153 */     this.t.setZ(0.0D);
/* 154 */     this.rx.setAngle(0.0D);
/* 155 */     this.ry.setAngle(0.0D);
/* 156 */     this.rz.setAngle(0.0D);
/* 157 */     this.s.setX(1.0D);
/* 158 */     this.s.setY(1.0D);
/* 159 */     this.s.setZ(1.0D);
/* 160 */     this.p.setX(0.0D);
/* 161 */     this.p.setY(0.0D);
/* 162 */     this.p.setZ(0.0D);
/* 163 */     this.ip.setX(0.0D);
/* 164 */     this.ip.setY(0.0D);
/* 165 */     this.ip.setZ(0.0D);
/*     */   }
/*     */   
/*     */   public void resetTSP() {
/* 169 */     this.t.setX(0.0D);
/* 170 */     this.t.setY(0.0D);
/* 171 */     this.t.setZ(0.0D);
/* 172 */     this.s.setX(1.0D);
/* 173 */     this.s.setY(1.0D);
/* 174 */     this.s.setZ(1.0D);
/* 175 */     this.p.setX(0.0D);
/* 176 */     this.p.setY(0.0D);
/* 177 */     this.p.setZ(0.0D);
/* 178 */     this.ip.setX(0.0D);
/* 179 */     this.ip.setY(0.0D);
/* 180 */     this.ip.setZ(0.0D);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 184 */     return "Xform[t = (" + this.t
/* 185 */       .getX() + ", " + this.t
/* 186 */       .getY() + ", " + this.t
/* 187 */       .getZ() + ")  " + "r = (" + this.rx
/*     */       
/* 189 */       .getAngle() + ", " + this.ry
/* 190 */       .getAngle() + ", " + this.rz
/* 191 */       .getAngle() + ")  " + "s = (" + this.s
/*     */       
/* 193 */       .getX() + ", " + this.s
/* 194 */       .getY() + ", " + this.s
/* 195 */       .getZ() + ")  " + "p = (" + this.p
/*     */       
/* 197 */       .getX() + ", " + this.p
/* 198 */       .getY() + ", " + this.p
/* 199 */       .getZ() + ")  " + "ip = (" + this.ip
/*     */       
/* 201 */       .getX() + ", " + this.ip
/* 202 */       .getY() + ", " + this.ip
/* 203 */       .getZ() + ")]";
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/Xform.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */