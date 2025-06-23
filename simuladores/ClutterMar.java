/*    */ package ipqm.gsd.hidra.ihm.simuladores;
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
/*    */ 
/*    */ public class ClutterMar
/*    */ {
/*    */   private static final double VELOCIDADE_LUZ = 2.99792458E8D;
/*    */   private static final double MAX_DISTANCIA_CLUTTER = 14816.0D;
/* 17 */   private final RandomRayleigh rnd = new RandomRayleigh();
/*    */   
/*    */   private int estadoMar;
/*    */   
/*    */   private float filtro;
/*    */   private int larguraPulso;
/*    */   
/*    */   public int getEstadoMar() {
/* 25 */     return this.estadoMar;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setEstadoMar(int estadoMar) {
/* 33 */     this.estadoMar = estadoMar;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public float getFiltro() {
/* 41 */     return this.filtro;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setFiltro(float filtro) {
/* 49 */     this.filtro = filtro;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public double getLarguraPulso() {
/* 57 */     return this.larguraPulso;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setLarguraPulso(int larguraPulso) {
/* 65 */     this.larguraPulso = larguraPulso;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean[] geraClutterDisparo(int numAmostrasDisparo, int distanciaAmostras) {
/* 75 */     boolean[] clutter = new boolean[numAmostrasDisparo];
/* 76 */     double discriminacaoDistancia = 2.99792458E8D * this.larguraPulso * 1.0E-9D / 2.0D;
/* 77 */     int numFaixasDistancia = (int)(14816.0D / discriminacaoDistancia);
/* 78 */     int b = 17 + this.estadoMar;
/* 79 */     int threshold = 75 + (int)(13.0F * this.filtro);
/*    */     
/* 81 */     for (int faixa = 0; faixa < numFaixasDistancia; faixa++) {
/* 82 */       int amplitude = (int)this.rnd.nextRayleigh(b);
/* 83 */       if (amplitude >= threshold) {
/* 84 */         int r = (int)this.rnd.nextRayleigh(23.0D);
/* 85 */         double distancia = (r + 0.5D) * discriminacaoDistancia;
/*    */         
/* 87 */         int posClutter = (int)(distancia / distanciaAmostras / 100.0D);
/* 88 */         if (posClutter < numAmostrasDisparo && !clutter[posClutter]) {
/* 89 */           clutter[posClutter] = true;
/*    */         }
/*    */       } 
/*    */     } 
/*    */ 
/*    */     
/* 95 */     return clutter;
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/simuladores/ClutterMar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */