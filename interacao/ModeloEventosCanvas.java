package ipqm.gsd.hidra.ihm.interacao;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.input.TouchEvent;

public interface ModeloEventosCanvas {
  void tratarMouseClickedCanvas(MouseEvent paramMouseEvent);
  
  void tratarMousePressedCanvas(MouseEvent paramMouseEvent);
  
  void tratarMouseDraggedCanvas(MouseEvent paramMouseEvent);
  
  void tratarMouseReleasedCanvas(MouseEvent paramMouseEvent);
  
  void tratarMouseMovedCanvas(MouseEvent paramMouseEvent);
  
  void tratarMouseExitedCanvas(MouseEvent paramMouseEvent);
  
  void tratarMouseEnteredCanvas(MouseEvent paramMouseEvent);
  
  void tratarScrollCanvas(ScrollEvent paramScrollEvent);
  
  void tratarKeyPressedCanvas(KeyEvent paramKeyEvent);
  
  void tratarKeyReleasedCanvas(KeyEvent paramKeyEvent);
  
  void tratarEventoTouchMoved(TouchEvent paramTouchEvent);
  
  void tratarEventoTouchReleased(TouchEvent paramTouchEvent);
  
  void tratarEventoTouchPressed(TouchEvent paramTouchEvent);
}


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/ModeloEventosCanvas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */