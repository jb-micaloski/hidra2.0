/*     */ package ipqm.gsd.hidra.ihm.dialogos.latlong;
/*     */ 
/*     */ import ipqm.gsd.componentes.dominio.cinematica.CoordenadaGeografica;
/*     */ import ipqm.gsd.componentes.nucleo.ThreadPool;
/*     */ import ipqm.gsd.componentes.nucleo.anotacao_interface.EnumFiltros;
/*     */ import ipqm.gsd.hidra.ihm.dialogos.AcaoDialogo;
/*     */ import ipqm.gsd.hidra.ihm.projetos.geral.Validacao;
/*     */ import ipqm.gsd.hidra.ihm.util.FiltroTexto;
/*     */ import java.net.URL;
/*     */ import java.util.ResourceBundle;
/*     */ import javafx.event.ActionEvent;
/*     */ import javafx.fxml.FXML;
/*     */ import javafx.fxml.Initializable;
/*     */ import javafx.scene.control.Label;
/*     */ import javafx.scene.control.TextField;
/*     */ import javafx.scene.layout.AnchorPane;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ControladorDialogoLatLong
/*     */   implements Initializable
/*     */ {
/*     */   @FXML
/*     */   private AnchorPane anchorPaneDialogo;
/*     */   @FXML
/*     */   private AnchorPane fundoPreto;
/*     */   @FXML
/*     */   private AnchorPane dialogoLatLong;
/*     */   @FXML
/*     */   private Label tituloConfirma;
/*     */   @FXML
/*     */   private Label descricaoConfirma;
/*     */   private DialogoLatLong dialogo;
/*     */   @FXML
/*     */   private TextField campoLat;
/*     */   @FXML
/*     */   private TextField campoLong;
/*     */   
/*     */   public void initialize(URL url, ResourceBundle rb) {
/*  43 */     FiltroTexto.adicionarMascara(this.campoLat, EnumFiltros.Mascara.LATITUDE, EnumFiltros.Valores.LATITUDE);
/*  44 */     FiltroTexto.adicionarMascara(this.campoLong, EnumFiltros.Mascara.LONGITUDE, EnumFiltros.Valores.LONGITUDE);
/*     */   }
/*     */   
/*     */   public void exibirIHM() {
/*  48 */     this.campoLat.requestFocus();
/*     */   }
/*     */   
/*     */   public AnchorPane getAnchorPaneDialogo() {
/*  52 */     return this.anchorPaneDialogo;
/*     */   }
/*     */   
/*     */   public AnchorPane getFundoPreto() {
/*  56 */     return this.fundoPreto;
/*     */   }
/*     */   
/*     */   public AnchorPane getDialogo() {
/*  60 */     return this.dialogoLatLong;
/*     */   }
/*     */   
/*     */   public void setTituloConfirma(String tituloConfirma) {
/*  64 */     this.tituloConfirma.setText(tituloConfirma);
/*     */   }
/*     */   
/*     */   public void setDescricaoConfirma(String descricaoConfirma) {
/*  68 */     this.descricaoConfirma.setText(descricaoConfirma);
/*     */   }
/*     */   
/*     */   public boolean validarFormulario() {
/*  72 */     if (!Validacao.validaCampo(this.campoLat.getText(), Validacao.Erro.LATITUDE))
/*  73 */       return false; 
/*  74 */     if (!Validacao.validaCampo(this.campoLong.getText(), Validacao.Erro.LONGITUDE)) {
/*  75 */       return false;
/*     */     }
/*  77 */     return true;
/*     */   }
/*     */   
/*     */   @FXML
/*     */   private void acaoBotaoOk(ActionEvent event) {
/*  82 */     ThreadPool.executar(() -> this.dialogo.acao(AcaoDialogo.OK));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @FXML
/*     */   private void acaoBotaoCancelar(ActionEvent event) {
/*  89 */     ThreadPool.executar(() -> this.dialogo.acao(AcaoDialogo.CANCELAR));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDialogo(DialogoLatLong dialogo) {
/*  95 */     this.dialogo = dialogo;
/*     */   }
/*     */   
/*     */   public CoordenadaGeografica getCoordenadaGeografica() {
/*  99 */     CoordenadaGeografica posicaoAtual = new CoordenadaGeografica();
/* 100 */     posicaoAtual.setLatitude(CoordenadaGeografica.latitudeStringtoDG(this.campoLat.getText()));
/* 101 */     posicaoAtual.setLongitude(CoordenadaGeografica.longitudeStringtoDG(this.campoLong.getText()));
/*     */     
/* 103 */     return posicaoAtual;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/dialogos/latlong/ControladorDialogoLatLong.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */