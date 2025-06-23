/*    */ package ipqm.gsd.hidra.ihm.util;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum Argumentos
/*    */ {
/* 10 */   LOGIN("Login", "login", "Força carregar a tela de login"),
/* 11 */   VERBOSE("Verbose", "v", "Exibe no terminal a saida do log"),
/* 12 */   UPDATE("Atualizar esquema", "update", "Atualiza o esquema do hibernate"),
/* 13 */   CONFIGURACAO("Configuração", "config", "Exibe a tela de configuração"),
/* 14 */   ATUALIZAR_CARTAS("Atualizar cartas náuticas", "atualiza_cartas", "Atualiza as cartas náuticas locais utilizando um banco de dados PostGIS remoto"),
/* 15 */   EXIBIR_SQL("Exibir SQL", "exibir_sql", "Exibe o SQL do hibernate"),
/* 16 */   TERMINAL("Modo terminal", "terminal", "Executa o Hidra no modo terminal"),
/* 17 */   HELP("Ajuda", "help", "Exibe esta ajuda"),
/* 18 */   LOG("Nível do Log", "log=", "Inicia o hidra no nivel de log especificado (DEBUG, INTERFACE, INSTRUCAO, SISTEMA, EXCECAO, DESATIVADO). ex: log=INTERFACE"),
/* 19 */   DEBUG("Modo de depuração", "debug", "Inicia o sistema em modo de depuração");
/*    */   
/*    */   private final String nome;
/*    */   private final String argumento;
/*    */   private final String ajuda;
/*    */   
/*    */   Argumentos(String nome, String argumento, String ajuda) {
/* 26 */     this.nome = nome;
/* 27 */     this.argumento = argumento;
/* 28 */     this.ajuda = ajuda;
/*    */   }
/*    */   
/*    */   public String getNome() {
/* 32 */     return this.nome;
/*    */   }
/*    */   
/*    */   public String getArgumento() {
/* 36 */     return this.argumento;
/*    */   }
/*    */   
/*    */   public String getAjuda() {
/* 40 */     return this.ajuda;
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/util/Argumentos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */