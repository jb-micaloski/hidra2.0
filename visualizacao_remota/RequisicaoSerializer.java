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
/*    */ public class RequisicaoSerializer
/*    */   implements AdaptadorSerializacao<Requisicao>
/*    */ {
/*    */   public static final String IP = "ipExibicao";
/*    */   public static final String ACAO = "acaoConexeao";
/*    */   public static final String RESOLUCAO = "resolucao";
/*    */   public static final String TELA = "telaSelecionada";
/*    */   
/*    */   public Class<Requisicao> getClasse() {
/* 30 */     return Requisicao.class;
/*    */   }
/*    */ 
/*    */   
/*    */   public JsonElement serialize(Requisicao t, Type type, JsonSerializationContext jsc) {
/* 35 */     JsonObject jsonObject = new JsonObject();
/* 36 */     jsonObject.addProperty("ipExibicao", t.getIpExibicao());
/* 37 */     jsonObject.addProperty("acaoConexeao", Integer.valueOf(t.getAcaoConexao()));
/* 38 */     jsonObject.addProperty("resolucao", Integer.valueOf(t.getResolucao()));
/* 39 */     jsonObject.addProperty("telaSelecionada", Integer.valueOf(t.getTelaSelecionada()));
/* 40 */     return (JsonElement)jsonObject;
/*    */   }
/*    */ 
/*    */   
/*    */   public Requisicao deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
/* 45 */     JsonObject jo = je.getAsJsonObject();
/* 46 */     JsonElement jsonIp = jo.get("ipExibicao");
/* 47 */     JsonElement jsonAcao = jo.get("acaoConexeao");
/* 48 */     JsonElement jsonResol = jo.get("resolucao");
/* 49 */     JsonElement jsonTela = jo.get("telaSelecionada");
/*    */ 
/*    */     
/* 52 */     Requisicao req = new Requisicao();
/* 53 */     req.setIpExibicao(jsonIp.getAsString());
/* 54 */     req.setAcaoConexao(jsonAcao.getAsInt());
/* 55 */     req.setResolucao(jsonResol.getAsInt());
/* 56 */     req.setTelaSelecionada(jsonTela.getAsInt());
/*    */     
/* 58 */     return req;
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/visualizacao_remota/RequisicaoSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */