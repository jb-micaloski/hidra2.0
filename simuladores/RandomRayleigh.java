/*    */ package ipqm.gsd.hidra.ihm.simuladores;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RandomRayleigh
/*    */ {
/* 16 */   private final Random fRandom = new Random();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double nextRayleigh(double b) {
/* 25 */     double x = nextGaussian(0.0D, b);
/* 26 */     double y = nextGaussian(0.0D, b);
/* 27 */     return Math.sqrt(x * x + y * y);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double[] nextRayleigh(double b, int n) {
/* 37 */     double[] r = new double[n];
/* 38 */     for (int i = 0; i < n; i++) {
/* 39 */       r[i] = nextRayleigh(b);
/*    */     }
/* 41 */     return r;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private double nextGaussian(double mean, double variance) {
/* 51 */     return mean + this.fRandom.nextGaussian() * variance;
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/simuladores/RandomRayleigh.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */