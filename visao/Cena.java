/*     */ package ipqm.gsd.hidra.ihm.visao;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.IHM;
/*     */ import ipqm.gsd.componentes.nucleo.Mediador;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import ipqm.gsd.componentes.nucleo.logon.perfis.PerfilUsuario;
/*     */ import ipqm.gsd.hidra.ihm.HidraFX;
/*     */ import ipqm.gsd.hidra.ihm.controle.Controlador;
/*     */ import ipqm.gsd.hidra.ihm.controle.Tela;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javafx.application.Platform;
/*     */ import javafx.fxml.FXMLLoader;
/*     */ import javafx.scene.Parent;
/*     */ import javafx.scene.Scene;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ import javafx.util.Callback;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Cena
/*     */ {
/*     */   private Scene scene;
/*     */   private final Controlador controlador;
/*     */   private final String fxml;
/*     */   private final IHM ihm;
/*     */   private final List<String> listaJanelas;
/*     */   protected String nome;
/*     */   private String descricao;
/*     */   private String imagem;
/*     */   private final Map<String, Object> parametros;
/*     */   
/*     */   public Cena(String nome, String fxml, IHM ihm, Tela tela) {
/*  39 */     this(nome, fxml, ihm, tela, new HashMap<>());
/*     */   }
/*     */   
/*     */   public Cena(String nome, String fxml, IHM ihm, Tela tela, Map<String, Object> parametros) {
/*  43 */     this.listaJanelas = new ArrayList<>();
/*  44 */     this.nome = nome;
/*  45 */     this.fxml = fxml;
/*  46 */     this.ihm = ihm;
/*  47 */     this.parametros = parametros;
/*  48 */     boolean notificaIHM = (tela != null && tela.getCenaAtual() != null);
/*     */     
/*  50 */     if (notificaIHM) {
/*  51 */       tela.getCenaAtual().getIhm().notificarCarregamento(-1, "Carregando cena: " + nome);
/*     */     }
/*     */     
/*  54 */     FXMLLoader fxmlLoader = new FXMLLoader(HidraFX.class.getResource(fxml));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  61 */     fxmlLoader.setControllerFactory(new Callback<Class<?>, Object>() {
/*     */           public Object call(Class<?> param) {
/*     */             Controlador controladorFXML;
/*     */             Class<?> classeControladorFXML;
/*  65 */             Controlador controladorPerfil = null;
/*     */ 
/*     */ 
/*     */             
/*     */             try {
/*  70 */               classeControladorFXML = Class.forName(param.getName());
/*  71 */               Constructor<?> cons = classeControladorFXML.getConstructor(new Class[0]);
/*  72 */               controladorFXML = (Controlador)cons.newInstance(new Object[0]);
/*  73 */             } catch (ClassNotFoundException|InstantiationException|IllegalAccessException|NoSuchMethodException|SecurityException|IllegalArgumentException|java.lang.reflect.InvocationTargetException e) {
/*  74 */               Log.gravarLogExcecao("Erro ao obter o controlador de " + param.getName(), Cena.class, e);
/*  75 */               return null;
/*     */             } 
/*     */ 
/*     */             
/*  79 */             if (Mediador.isSistemaIniciado()) {
/*     */               try {
/*  81 */                 Class<?> c = Class.forName(param.getName() + PerfilUsuario.getPerfilUsuarioAtual().getClass().getSimpleName());
/*     */                 
/*  83 */                 Constructor<?> cons = c.getConstructor(new Class[0]);
/*  84 */                 controladorPerfil = (Controlador)cons.newInstance(new Object[0]);
/*     */ 
/*     */                 
/*  87 */                 if (!classeControladorFXML.isAssignableFrom(c)) {
/*  88 */                   controladorPerfil = null;
/*  89 */                   Log.gravarLogExcecao(c.getSimpleName() + " deve herdar de " + classeControladorFXML.getSimpleName(), this, new Exception(param.getName() + PerfilUsuario.getPerfilUsuarioAtual().getClass().getSimpleName() + " deve herdar de " + param.getName()));
/*     */                 }
/*     */               
/*  92 */               } catch (ClassNotFoundException|InstantiationException|IllegalAccessException|NoSuchMethodException|SecurityException|IllegalArgumentException|java.lang.reflect.InvocationTargetException ex) {
/*  93 */                 controladorPerfil = null;
/*     */               } 
/*     */             }
/*     */             
/*  97 */             if (controladorPerfil == null) {
/*  98 */               return controladorFXML;
/*     */             }
/* 100 */             return controladorPerfil;
/*     */           }
/*     */         });
/*     */ 
/*     */     
/*     */     try {
/* 106 */       AnchorPane root = (AnchorPane)fxmlLoader.load();
/* 107 */       Platform.runLater(() -> this.scene = new Scene((Parent)root));
/*     */     
/*     */     }
/* 110 */     catch (Exception ex) {
/* 111 */       Log.gravarLogExcecao("Erro ao carregar FXML " + fxml, Cena.class, ex);
/*     */     } 
/* 113 */     this.controlador = (Controlador)fxmlLoader.getController();
/*     */     
/* 115 */     this.controlador.setCena(this);
/* 116 */     this.controlador.setTela(tela);
/*     */     
/* 118 */     if (notificaIHM) {
/* 119 */       tela.getCenaAtual().getIhm().notificarCarregamento(-1, "Configurando cena: " + nome);
/*     */     }
/*     */     
/* 122 */     Log.gravarLogInstrucao("Configurando controlador para " + nome, this.controlador);
/* 123 */     this.controlador.configurar();
/*     */     
/* 125 */     if (notificaIHM) {
/* 126 */       tela.getCenaAtual().getIhm().notificarCarregamento(-1, "");
/*     */     }
/*     */   }
/*     */   
/*     */   public String getNome() {
/* 131 */     return this.nome;
/*     */   }
/*     */   
/*     */   public String getDescricao() {
/* 135 */     return this.descricao;
/*     */   }
/*     */   
/*     */   public void setDescricao(String descricao) {
/* 139 */     this.descricao = descricao;
/*     */   }
/*     */   
/*     */   public String getImagem() {
/* 143 */     return this.imagem;
/*     */   }
/*     */   
/*     */   public void setImagem(String imagem) {
/* 147 */     this.imagem = "ipqm/gsd/hidra/ihm/imagens/funcionalidades/destaque/" + imagem;
/*     */   }
/*     */   
/*     */   public Scene getScene() {
/* 151 */     return this.scene;
/*     */   }
/*     */   
/*     */   public Controlador getControlador() {
/* 155 */     return this.controlador;
/*     */   }
/*     */   
/*     */   public String getFxml() {
/* 159 */     return this.fxml;
/*     */   }
/*     */   
/*     */   public IHM getIhm() {
/* 163 */     return this.ihm;
/*     */   }
/*     */   
/*     */   public List<String> getListaJanelas() {
/* 167 */     return this.listaJanelas;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Object> getParametros() {
/* 176 */     return this.parametros;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 181 */     return this.nome;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/visao/Cena.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */