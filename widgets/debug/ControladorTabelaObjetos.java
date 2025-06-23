/*    */ package ipqm.gsd.hidra.ihm.widgets.debug;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.Mediador;
/*    */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*    */ import ipqm.gsd.componentes.nucleo.util.ConversorTipos;
/*    */ import java.util.Objects;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ControladorTabelaObjetos
/*    */ {
/*    */   private final ObjetoDOMINIO objetoDOMINIO;
/*    */   
/*    */   public ControladorTabelaObjetos(ObjetoDOMINIO objetoDOMINIO) {
/* 17 */     this.objetoDOMINIO = objetoDOMINIO;
/*    */   }
/*    */   
/*    */   public int getID() {
/* 21 */     return this.objetoDOMINIO.getId();
/*    */   }
/*    */   
/*    */   public String getObjeto() {
/* 25 */     return String.valueOf(this.objetoDOMINIO);
/*    */   }
/*    */   
/*    */   public String getDono() {
/* 29 */     if (this.objetoDOMINIO.getDono() != null) {
/* 30 */       return String.valueOf(this.objetoDOMINIO.getDono().getHostname());
/*    */     }
/* 32 */     return "---";
/*    */   }
/*    */   
/*    */   public String getTamanho() {
/* 36 */     return ConversorTipos.byteLegivel(this.objetoDOMINIO.getTamanhoObjetoDistribuicao(), true);
/*    */   }
/*    */   
/*    */   public String getAcao() {
/* 40 */     return this.objetoDOMINIO.getNomeAcao();
/*    */   }
/*    */   
/*    */   public String getTimestamp() {
/* 44 */     long t = Mediador.getRelogio().getHorario();
/* 45 */     long dif = t - this.objetoDOMINIO.getTimeStamp();
/* 46 */     if (dif >= 0L) {
/* 47 */       return (dif / 1000L) + "." + (dif % 1000L) + " s";
/*    */     }
/* 49 */     return "-";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getIpOrigem() {
/* 54 */     return String.valueOf(this.objetoDOMINIO.getIpOrigemObjeto());
/*    */   }
/*    */   
/*    */   public String getClasse() {
/* 58 */     return String.valueOf(this.objetoDOMINIO.getClass());
/*    */   }
/*    */   
/*    */   public ObjetoDOMINIO getObjetoDOMINIO() {
/* 62 */     return this.objetoDOMINIO;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 67 */     int hash = 7;
/* 68 */     hash = 79 * hash + Objects.hashCode(Integer.valueOf(this.objetoDOMINIO.getId()));
/* 69 */     return hash;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object obj) {
/* 74 */     if (obj == null) {
/* 75 */       return false;
/*    */     }
/* 77 */     if (getClass() != obj.getClass()) {
/* 78 */       return false;
/*    */     }
/* 80 */     ControladorTabelaObjetos other = (ControladorTabelaObjetos)obj;
/* 81 */     if (!Objects.equals(Integer.valueOf(this.objetoDOMINIO.getId()), Integer.valueOf(other.objetoDOMINIO.getId()))) {
/* 82 */       return false;
/*    */     }
/* 84 */     return true;
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/debug/ControladorTabelaObjetos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */