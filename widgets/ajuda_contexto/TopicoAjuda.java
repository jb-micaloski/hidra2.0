/*    */ package ipqm.gsd.hidra.ihm.widgets.ajuda_contexto;
/*    */ 
/*    */ import java.util.Objects;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TopicoAjuda
/*    */ {
/*    */   private final String titulo;
/*    */   private final String url;
/*    */   
/*    */   public TopicoAjuda(String titulo, String url) {
/* 17 */     this.titulo = titulo;
/* 18 */     this.url = url;
/*    */   }
/*    */   
/*    */   public String getTitulo() {
/* 22 */     return this.titulo;
/*    */   }
/*    */   
/*    */   public String getUrl() {
/* 26 */     return this.url;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 31 */     return this.titulo;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 36 */     int hash = 7;
/* 37 */     hash = 89 * hash + Objects.hashCode(this.url);
/* 38 */     return hash;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 43 */     if (obj == null) {
/* 44 */       return false;
/*    */     }
/* 46 */     if (getClass() != obj.getClass()) {
/* 47 */       return false;
/*    */     }
/* 49 */     TopicoAjuda other = (TopicoAjuda)obj;
/* 50 */     if (!Objects.equals(this.url, other.url)) {
/* 51 */       return false;
/*    */     }
/* 53 */     return true;
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/ajuda_contexto/TopicoAjuda.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */