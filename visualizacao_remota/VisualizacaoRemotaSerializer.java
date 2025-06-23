/*    */ package ipqm.gsd.hidra.ihm.visualizacao_remota;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import ipqm.gsd.componentes.nucleo.serializacao.AdaptadorSerializacao;
/*    */ import java.lang.reflect.Type;
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
/*    */ 
/*    */ public class VisualizacaoRemotaSerializer
/*    */   implements AdaptadorSerializacao<FrameCaptura>
/*    */ {
/*    */   public static final String FRAME = "frame";
/*    */   public static final String TELA = "numero_telas";
/*    */   
/*    */   public Class<FrameCaptura> getClasse() {
/* 29 */     return FrameCaptura.class;
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonElement serialize(FrameCaptura t, Type type, JsonSerializationContext jsc) {
/* 34 */     JsonObject jsonObject = new JsonObject();
/* 35 */     jsonObject.addProperty("frame", t.getFrame());
/* 36 */     jsonObject.addProperty("numero_telas", Integer.valueOf(t.getNumeroTelas()));
/* 37 */     return (JsonElement)jsonObject;
/*    */   }
/*    */ 
/*    */   
/*    */   public FrameCaptura deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
/* 42 */     JsonObject jo = je.getAsJsonObject();
/* 43 */     JsonElement jsonFrame = jo.get("frame");
/* 44 */     JsonElement jsonTela = jo.get("numero_telas");
/*    */     
/* 46 */     FrameCaptura fc = new FrameCaptura();
/* 47 */     fc.setFrame(jsonFrame.getAsString());
/* 48 */     fc.setNumeroTelas(jsonTela.getAsInt());
/*    */     
/* 50 */     return fc;
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/visualizacao_remota/VisualizacaoRemotaSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */