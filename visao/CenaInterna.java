/*     */ package ipqm.gsd.hidra.ihm.visao;
/*     */ 
/*     */ import ipqm.gsd.componentes.nucleo.IHM;
/*     */ import ipqm.gsd.hidra.ihm.controle.Controlador;
/*     */ import ipqm.gsd.hidra.ihm.controle.ControladorFuncionalidadesInternas;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CenaInterna
/*     */   extends Cena
/*     */ {
/*     */   private final ControladorFuncionalidadesInternas controladorFuncionalidadesInternas;
/*     */   private final boolean exibirMenu;
/*     */   
/*     */   public CenaInterna(String nome, String fxml, ControladorFuncionalidadesInternas controladorFuncionalidadesInternas) {
/*  20 */     super(nome, fxml, (IHM)controladorFuncionalidadesInternas, controladorFuncionalidadesInternas.getTela());
/*  21 */     this.controladorFuncionalidadesInternas = controladorFuncionalidadesInternas;
/*  22 */     this.exibirMenu = true;
/*     */   }
/*     */   
/*     */   public CenaInterna(String nome, String fxml, ControladorFuncionalidadesInternas controladorFuncionalidadesInternas, boolean exibirMenu) {
/*  26 */     super(nome, fxml, (IHM)controladorFuncionalidadesInternas, controladorFuncionalidadesInternas.getTela());
/*  27 */     this.controladorFuncionalidadesInternas = controladorFuncionalidadesInternas;
/*  28 */     this.exibirMenu = exibirMenu;
/*     */   }
/*     */   
/*     */   public CenaInterna(String nome, String fxml, ControladorFuncionalidadesInternas controladorFuncionalidadesInternas, boolean exibirMenu, Map<String, Object> parematros) {
/*  32 */     super(nome, fxml, (IHM)controladorFuncionalidadesInternas, controladorFuncionalidadesInternas.getTela(), parematros);
/*  33 */     this.controladorFuncionalidadesInternas = controladorFuncionalidadesInternas;
/*  34 */     this.exibirMenu = exibirMenu;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ControladorFuncionalidadesInternas getControladorFuncionalidadesInternas() {
/*  43 */     return this.controladorFuncionalidadesInternas;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isExibirMenu() {
/*  52 */     return this.exibirMenu;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Object> getParametrosCenaInterna(String fxml) {
/*  62 */     return this.controladorFuncionalidadesInternas.getParametrosCenaInterna(fxml);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void exibirObjeto(String fxml, Object objeto) {
/*  73 */     this.controladorFuncionalidadesInternas.exibirFormulario(fxml, objeto);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void exibirObjeto(String fxml, Controlador pai, Object objeto) {
/*  85 */     this.controladorFuncionalidadesInternas.exibirFormulario(fxml, pai, objeto);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  90 */     int hash = 7;
/*  91 */     hash = 71 * hash + Objects.hashCode(this.nome);
/*  92 */     return hash;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  97 */     if (obj == null) {
/*  98 */       return false;
/*     */     }
/* 100 */     if (getClass() != obj.getClass()) {
/* 101 */       return false;
/*     */     }
/* 103 */     Cena other = (Cena)obj;
/* 104 */     if (!Objects.equals(this.nome, other.nome)) {
/* 105 */       return false;
/*     */     }
/* 107 */     return true;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/visao/CenaInterna.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */