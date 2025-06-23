package ipqm.gsd.hidra.ihm.interacao.tarefa;

import ipqm.gsd.componentes.dominio.cinematica.CoordenadaCartesiana;

public interface ModeloTarefa {
  void inicializa();
  
  void finaliza();
  
  void desenha();
  
  void criaMenuContexto();
  
  void executaMenuContexto(Comando paramComando, CoordenadaCartesiana paramCoordenadaCartesiana);
}


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/ModeloTarefa.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */