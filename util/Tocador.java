/*     */ package ipqm.gsd.hidra.ihm.util;
/*     */ 
/*     */ import com.sun.javafx.util.WeakReferenceQueue;
/*     */ import ipqm.gsd.componentes.nucleo.configuracao.Diretorios;
/*     */ import ipqm.gsd.componentes.nucleo.log.Log;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.net.MalformedURLException;
/*     */ import javafx.scene.media.Media;
/*     */ import javafx.scene.media.MediaPlayer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Tocador
/*     */ {
/*     */   private MediaPlayer mediaPlayer;
/*     */   public static boolean mute;
/*  23 */   public static final WeakReferenceQueue<MediaPlayer> listaPlayers = new WeakReferenceQueue();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tocador(String arquivo) {
/*  32 */     fabricar(arquivo);
/*  33 */     if (this.mediaPlayer != null) {
/*  34 */       listaPlayers.add(this.mediaPlayer);
/*  35 */       this.mediaPlayer.setMute(mute);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tocador(String arquivo, boolean reutilizar) {
/*  47 */     fabricar(arquivo);
/*  48 */     if (this.mediaPlayer != null) {
/*  49 */       this.mediaPlayer.setOnEndOfMedia(() -> this.mediaPlayer.stop());
/*     */     }
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
/*     */   public Tocador(String arquivo, double balanco) {
/*  62 */     this(arquivo);
/*  63 */     if (this.mediaPlayer != null) {
/*  64 */       this.mediaPlayer.setBalance(balanco);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Tocador(String arquivo, double balanco, double rate) {
/*  76 */     this(arquivo);
/*  77 */     if (this.mediaPlayer != null) {
/*  78 */       this.mediaPlayer.setBalance(balanco);
/*  79 */       this.mediaPlayer.setRate(rate);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reproduzir() {
/*  88 */     if (getMediaPlayer() != null) {
/*  89 */       getMediaPlayer().stop();
/*  90 */       getMediaPlayer().play();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Boolean reproduzindo() {
/*  98 */     if (getMediaPlayer().getStatus() == MediaPlayer.Status.PLAYING) {
/*  99 */       return Boolean.valueOf(true);
/*     */     }
/* 101 */     return Boolean.valueOf(false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void pararReproducao() {
/* 109 */     if (getMediaPlayer() != null) {
/* 110 */       getMediaPlayer().stop();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reproduzirUmaVez() {
/* 118 */     if (getMediaPlayer() != null) {
/* 119 */       getMediaPlayer().stop();
/* 120 */       getMediaPlayer().setOnEndOfMedia(() -> encerrar());
/*     */ 
/*     */       
/* 123 */       getMediaPlayer().play();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reproduzirIndefinidamente() {
/* 132 */     if (getMediaPlayer() != null) {
/* 133 */       getMediaPlayer().stop();
/* 134 */       getMediaPlayer().setCycleCount(-1);
/* 135 */       getMediaPlayer().play();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void encerrar() {
/* 143 */     if (getMediaPlayer() != null) {
/* 144 */       getMediaPlayer().stop();
/* 145 */       getMediaPlayer().dispose();
/* 146 */       listaPlayers.remove(getMediaPlayer());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void fabricar(String arquivo) {
/* 156 */     String caminho = Diretorios.getDiretorioPadrao(Diretorios.Diretorio.SONS) + arquivo;
/*     */     
/* 158 */     File file = new File(caminho);
/* 159 */     if (file.exists()) {
/*     */       try {
/* 161 */         Media hit = new Media(file.toURI().toURL().toString());
/* 162 */         this.mediaPlayer = new MediaPlayer(hit);
/* 163 */       } catch (MalformedURLException ex) {
/* 164 */         Log.gravarLogExcecao("Erro ao formar URL da mídia", this, ex);
/*     */       } 
/*     */     } else {
/* 167 */       Log.gravarLogExcecao("Arquivo não encontrado: " + caminho, this, new FileNotFoundException(caminho));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MediaPlayer getMediaPlayer() {
/* 175 */     return this.mediaPlayer;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/util/Tocador.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */