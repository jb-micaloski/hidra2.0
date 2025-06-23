package ipqm.gsd.hidra.ihm.visualizacao_remota;

import java.awt.image.BufferedImage;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

public interface IVisualizacaoRemotaCaptura {
  Image obterFrame();
  
  BufferedImage converterFrame(WritableImage paramWritableImage);
  
  int obterResolucao();
  
  int obterNumeroTelas();
}


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/visualizacao_remota/IVisualizacaoRemotaCaptura.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */