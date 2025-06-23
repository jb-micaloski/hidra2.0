package ipqm.gsd.hidra.ihm.interacao;

import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;
import javafx.scene.input.KeyEvent;

public interface ModeloEventos {
  void botao1Pressionado(CoordenadaCartesiana paramCoordenadaCartesiana, EstadoTeclado paramEstadoTeclado);
  
  void botao1Arrastado(CoordenadaCartesiana paramCoordenadaCartesiana, EstadoTeclado paramEstadoTeclado);
  
  void botao1Solto(CoordenadaCartesiana paramCoordenadaCartesiana, EstadoTeclado paramEstadoTeclado);
  
  void botao2Pressionado(CoordenadaCartesiana paramCoordenadaCartesiana, EstadoTeclado paramEstadoTeclado);
  
  void botao2Arrastado(CoordenadaCartesiana paramCoordenadaCartesiana, EstadoTeclado paramEstadoTeclado);
  
  void botao2Solto(CoordenadaCartesiana paramCoordenadaCartesiana, EstadoTeclado paramEstadoTeclado);
  
  void botao3Pressionado(CoordenadaCartesiana paramCoordenadaCartesiana, EstadoTeclado paramEstadoTeclado);
  
  void botao3Arrastado(CoordenadaCartesiana paramCoordenadaCartesiana, EstadoTeclado paramEstadoTeclado);
  
  void botao3Solto(CoordenadaCartesiana paramCoordenadaCartesiana, EstadoTeclado paramEstadoTeclado);
  
  void duploClique(CoordenadaCartesiana paramCoordenadaCartesiana, EstadoTeclado paramEstadoTeclado);
  
  void mouseMovido(CoordenadaCartesiana paramCoordenadaCartesiana, EstadoTeclado paramEstadoTeclado);
  
  void mouseSaiu(CoordenadaCartesiana paramCoordenadaCartesiana);
  
  void mouseEntrou(CoordenadaCartesiana paramCoordenadaCartesiana);
  
  void mouseRodou(int paramInt);
  
  void teclaPressionada(KeyEvent paramKeyEvent);
}


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/ModeloEventos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */