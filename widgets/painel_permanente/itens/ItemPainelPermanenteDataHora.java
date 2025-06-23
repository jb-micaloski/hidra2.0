/*    */ package ipqm.gsd.hidra.ihm.widgets.painel_permanente.itens;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.Mediador;
/*    */ import ipqm.gsd.componentes.nucleo.ObjetoDOMINIO;
/*    */ import ipqm.gsd.componentes.nucleo.util.UtilData;
/*    */ import java.text.SimpleDateFormat;
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemPainelPermanenteDataHora
/*    */   extends ItemPainelPermanente
/*    */ {
/*    */   private final SimpleDateFormat sdf;
/*    */   private boolean pisca = false;
/*    */   
/*    */   public ItemPainelPermanenteDataHora() {
/* 19 */     super("Data/Hora", 28);
/* 20 */     this.sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String geraValor(List<ObjetoDOMINIO> listaObjetos) {
/* 26 */     String horario = this.sdf.format(Long.valueOf(Mediador.getRelogio().getHorario()));
/*    */     
/* 28 */     String fatorAceleracao = UtilData.getFatorAceleracao();
/*    */     
/* 30 */     if (!fatorAceleracao.equals("1x")) {
/*    */       
/* 32 */       if (fatorAceleracao.equalsIgnoreCase("Parado")) {
/* 33 */         this.pisca = !this.pisca;
/*    */       } else {
/* 35 */         this.pisca = false;
/*    */       } 
/*    */       
/* 38 */       if (!this.pisca) {
/* 39 */         horario = "(" + fatorAceleracao + ") " + horario;
/*    */       } else {
/* 41 */         horario = "";
/*    */       } 
/*    */     } 
/*    */     
/* 45 */     return horario;
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/widgets/painel_permanente/itens/ItemPainelPermanenteDataHora.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */