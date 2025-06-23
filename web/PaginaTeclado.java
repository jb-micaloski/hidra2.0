/*    */ package ipqm.gsd.hidra.ihm.web;
/*    */ 
/*    */ import ipqm.gsd.componentes.nucleo.log.Log;
/*    */ import ipqm.gsd.componentes.nucleo.web.Pagina;
/*    */ import ipqm.gsd.componentes.nucleo.web.ServidorHttp;
/*    */ import ipqm.gsd.hidra.ihm.controle.Aplicacao;
/*    */ import java.util.Map;
/*    */ import javafx.application.Platform;
/*    */ import javafx.scene.input.KeyCode;
/*    */ import javafx.scene.input.KeyEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PaginaTeclado
/*    */   extends Pagina
/*    */ {
/*    */   private boolean ctrlPressionado;
/*    */   private boolean altPressionado;
/*    */   private boolean shiftPressionado;
/*    */   
/*    */   public PaginaTeclado(ServidorHttp servidorHttp) {
/* 24 */     super(servidorHttp, "Teclado");
/*    */   }
/*    */ 
/*    */   
/*    */   public String getConteudo(Map<String, String> params) {
/* 29 */     StringBuilder out = new StringBuilder();
/*    */     
/* 31 */     if (params.containsKey("ctrl")) {
/* 32 */       this.ctrlPressionado = ((String)params.get("ctrl")).equals("true");
/*    */     }
/* 34 */     if (params.containsKey("alt")) {
/* 35 */       this.altPressionado = ((String)params.get("alt")).equals("true");
/*    */     }
/* 37 */     if (params.containsKey("shift")) {
/* 38 */       this.shiftPressionado = ((String)params.get("shift")).equals("true");
/*    */     }
/*    */     
/* 41 */     if (params.containsKey("key")) {
/* 42 */       KeyCode key = KeyCode.valueOf(params.get("key"));
/*    */       
/* 44 */       Log.gravarLogInstrucao("Tecla pressionada pelo operador web: " + key, this);
/*    */       
/* 46 */       if (key == KeyCode.CONTROL || key == KeyCode.ALT || key == KeyCode.SHIFT) {
/*    */         
/* 48 */         if (key == KeyCode.CONTROL) {
/* 49 */           this.ctrlPressionado = !this.ctrlPressionado;
/*    */         }
/* 51 */         if (key == KeyCode.SHIFT) {
/* 52 */           this.shiftPressionado = !this.shiftPressionado;
/*    */         }
/* 54 */         if (key == KeyCode.ALT) {
/* 55 */           this.altPressionado = !this.altPressionado;
/*    */         }
/*    */       } else {
/*    */         
/* 59 */         KeyEvent keyEvent = new KeyEvent(null, this.titulo, this.titulo, key, this.shiftPressionado, this.ctrlPressionado, this.altPressionado, false);
/* 60 */         Platform.runLater(() -> {
/*    */               Aplicacao.getInstancia().getTelaPrincipal().teclaPressionada(keyEvent);
/*    */               
/*    */               Aplicacao.getInstancia().getTelaPrincipal().teclaSolta(keyEvent);
/*    */             });
/*    */       } 
/*    */     } 
/* 67 */     out.append("<h2>Teclado</h2>");
/* 68 */     out.append("<iframe id='tela' style='width:600px; height:337px; border:none' frameborder=0 border=0  allowtransparency='no' src='/?p=ipqm.gsd.hidra.ihm.web.PaginaImagemTela'></iframe><br/><a href='javascript:;' onclick=\"document.getElementById('tela').contentWindow.location.reload();\">atualizar tela</a>");
/* 69 */     out.append("<br/>");
/* 70 */     out.append("<hr/><br/>");
/* 71 */     out.append("CTRL: ").append(this.ctrlPressionado).append(" | ALT: ").append(this.altPressionado).append(" | SHIFT: ").append(this.shiftPressionado).append("<br/><br/>");
/* 72 */     out.append(desenhaTecla(KeyCode.ESCAPE)).append(" &nbsp;").append(desenhaTecla(KeyCode.F1)).append(desenhaTecla(KeyCode.F2)).append(desenhaTecla(KeyCode.F3)).append(desenhaTecla(KeyCode.F4)).append(desenhaTecla(KeyCode.F5)).append(desenhaTecla(KeyCode.F6)).append(desenhaTecla(KeyCode.F7)).append(desenhaTecla(KeyCode.F8)).append(desenhaTecla(KeyCode.F9)).append(desenhaTecla(KeyCode.F10)).append(desenhaTecla(KeyCode.F11));
/* 73 */     out.append("<br/>");
/* 74 */     out.append("<br/>");
/* 75 */     out.append(desenhaTecla(KeyCode.DIGIT1, "1")).append(desenhaTecla(KeyCode.DIGIT2, "2")).append(desenhaTecla(KeyCode.DIGIT3, "3")).append(desenhaTecla(KeyCode.DIGIT4, "4")).append(desenhaTecla(KeyCode.DIGIT5, "5")).append(desenhaTecla(KeyCode.DIGIT6, "6")).append(desenhaTecla(KeyCode.DIGIT7, "7")).append(desenhaTecla(KeyCode.DIGIT8, "8")).append(desenhaTecla(KeyCode.DIGIT9, "9")).append(desenhaTecla(KeyCode.DIGIT0, "0")).append(desenhaTecla(KeyCode.MINUS, "-")).append(desenhaTecla(KeyCode.PLUS, "+")).append(desenhaTecla(KeyCode.BACK_SPACE));
/* 76 */     out.append("<br/>");
/* 77 */     out.append(desenhaTecla(KeyCode.TAB)).append(desenhaTecla(KeyCode.Q)).append(desenhaTecla(KeyCode.W)).append(desenhaTecla(KeyCode.E)).append(desenhaTecla(KeyCode.R)).append(desenhaTecla(KeyCode.T)).append(desenhaTecla(KeyCode.Y)).append(desenhaTecla(KeyCode.U)).append(desenhaTecla(KeyCode.I)).append(desenhaTecla(KeyCode.O)).append(desenhaTecla(KeyCode.P)).append(desenhaTecla(KeyCode.ENTER));
/* 78 */     out.append("<br/>");
/* 79 */     out.append(desenhaTecla(KeyCode.CAPS, "Caps")).append(desenhaTecla(KeyCode.A)).append(desenhaTecla(KeyCode.S)).append(desenhaTecla(KeyCode.D)).append(desenhaTecla(KeyCode.F)).append(desenhaTecla(KeyCode.G)).append(desenhaTecla(KeyCode.H)).append(desenhaTecla(KeyCode.J)).append(desenhaTecla(KeyCode.K)).append(desenhaTecla(KeyCode.L));
/* 80 */     out.append("<br/>");
/* 81 */     out.append(desenhaTecla(KeyCode.SHIFT)).append(desenhaTecla(KeyCode.Z)).append(desenhaTecla(KeyCode.X)).append(desenhaTecla(KeyCode.C)).append(desenhaTecla(KeyCode.V)).append(desenhaTecla(KeyCode.B)).append(desenhaTecla(KeyCode.N)).append(desenhaTecla(KeyCode.M));
/* 82 */     out.append("<br/>");
/* 83 */     out.append(desenhaTecla(KeyCode.CONTROL)).append(desenhaTecla(KeyCode.WINDOWS, "Win")).append(desenhaTecla(KeyCode.ALT)).append(desenhaTecla(KeyCode.SPACE, "&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; "));
/* 84 */     return out.toString();
/*    */   }
/*    */   
/*    */   private String desenhaTecla(KeyCode keyCode) {
/* 88 */     return desenhaTecla(keyCode, keyCode.getName());
/*    */   }
/*    */   
/*    */   private String desenhaTecla(KeyCode keyCode, String key) {
/* 92 */     StringBuilder out = new StringBuilder();
/* 93 */     String modificadores = "&ctrl=" + this.ctrlPressionado + "&alt=" + this.altPressionado + "&shift=" + this.shiftPressionado;
/* 94 */     out.append("<button type='button' onclick=\"location.href='?p=ipqm.gsd.hidra.ihm.web.PaginaTeclado&key=").append(keyCode).append(modificadores).append("'\">").append(key).append("</button>");
/* 95 */     return out.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/web/PaginaTeclado.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */