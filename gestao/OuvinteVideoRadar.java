package ipqm.gsd.hidra.ihm.gestao;

import ipqm.gsd.radar.model.RadarBeam;
import java.util.List;

public interface OuvinteVideoRadar {
  void atualizarCamadaRadar(List<RadarBeam> paramList);
  
  int getIdRadarSelecionado();
}


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/gestao/OuvinteVideoRadar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */