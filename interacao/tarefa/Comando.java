/*     */ package ipqm.gsd.hidra.ihm.interacao.tarefa;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum Comando
/*     */ {
/*  10 */   CRIAR_ACOMPANHAMENTO("Criar acompanhamento aqui"),
/*  11 */   CRIAR_ACOMPANHAMENTO_RAD("Criar acompanhamento radar aqui"),
/*  12 */   CRIAR_ACOMPANHAMENTO_MAD("Criar Acompanhamento MAD aqui"),
/*  13 */   ALTERAR_ACOMPANHAMENTO("Alterar acompanhamento"),
/*  14 */   EXCLUIR_ACOMPANHAMENTO("Excluir acompanhamento"),
/*     */   
/*  16 */   CENTRAR_MAPA("Centrar mapa aqui"),
/*  17 */   IR_PARA("Ir para uma coordenada"),
/*     */   
/*  19 */   EDITAR_GRADE("Editar grade"),
/*  20 */   REDEFINIR_GRADE("Redefinir grade"),
/*  21 */   GRADE_CENTRO_EXERCICIO("Definir grade no centro do exercício"),
/*     */   
/*  23 */   CRIA_LINHA_ACOMPANHAMENTO("Linha de Marcação"),
/*  24 */   CANCELA_LINHA_ACOMPANHAMENTO("Cancela Linha Marcação"),
/*     */   
/*  26 */   LINHA_FORMATURA("Linha de Formatura"),
/*     */   
/*  28 */   CRIA_AREA("Cria área aberta"),
/*  29 */   CRIA_AREA_FECHADA("Cria área fechada"),
/*  30 */   CANCELA_AREA("Cancela área"),
/*     */   
/*  32 */   CRIA_PONTO("Criar um ponto fixo"),
/*     */   
/*  34 */   CRIA_SIMBOLOS("Criar um Símbolo"),
/*  35 */   CRIA_TEXTO("Criar um Texto"),
/*  36 */   CRIA_PROFUNDIDADE("Criar uma Profundidade"),
/*     */   
/*  38 */   CRIA_ROTA("Cria rota"),
/*  39 */   CANCELA_ROTA("Cancela rota"),
/*  40 */   REMOVE_ULT_WP("Remove último waypoint"),
/*     */   
/*  42 */   RELATIVE_MOTION_CENTERED("Relative motion centered"),
/*  43 */   RELATIVE_MOTION_LOOK_AHEAD("Relative motion look ahead"),
/*     */   
/*  45 */   CRIA_VEICULO("Criar veículo aqui"),
/*  46 */   VEICULO_AMARELO("Amarelo"),
/*  47 */   VEICULO_AZUL("Azul"),
/*  48 */   VEICULO_BRANCO("Branco"),
/*  49 */   VEICULO_CINZA("Cinza"),
/*  50 */   VEICULO_LARANJA("Laranja"),
/*  51 */   VEICULO_MAGENTA("Magenta"),
/*  52 */   VEICULO_ROSA("Rosa"),
/*  53 */   VEICULO_VERDE("Verde"),
/*  54 */   VEICULO_VERMELHO("Vermelho"),
/*     */   
/*  56 */   ALOCAR_VEICULO("Alocar um veículo nesta posição"),
/*     */   
/*  58 */   LIBERAR_LINHA_MARCACAO("Liberar linha de marcação"),
/*  59 */   CANCELAR_LIBERACAO_LINHA_MARCACAO("Cancelar liberação da linha de marcação"),
/*     */   
/*  61 */   EXPORTAR_ACOMPANHAMENTO("Exportar acompanhamento"),
/*  62 */   CANCELAR_EXPORTACAO("Cancelar exportação de acompanhamento"),
/*     */ 
/*     */   
/*  65 */   DORMIR_AIS("AIS Dormindo"),
/*  66 */   ESCALA_REAL("Escala real"),
/*  67 */   HEADING_LINE("Heading Line"),
/*  68 */   ESCALA_REAL_VEICULO("Escala real veiculo"),
/*  69 */   SIMPLIFICADO("Veiculo simplificado"),
/*  70 */   MINIMIZADO("Veiculo Minimizado"),
/*  71 */   REGISTRAR_OCORRENCIA("Registrar ocorrência"),
/*     */ 
/*     */ 
/*     */   
/*  75 */   EXCLUI_OBJETOS("Excluir objeto(s)"),
/*  76 */   ALTERA_OBJETO("Alterar objeto"),
/*  77 */   CONFIGURA_OBJETO("Configura objeto"),
/*  78 */   ADD_SETOR("Setores"),
/*  79 */   ADD_COBERTURA_ESQUADRA("Adicionar Cobertura Esquadra"),
/*  80 */   ADD_GATE_OP("Adicionar Gate OP"),
/*  81 */   ADD_GATE_BV("Adicionar Gate BV"),
/*  82 */   ADD_GATE_BV_MOD("Adicionar Gate BV Mod"),
/*  83 */   ADD_ACORN("Adicionar Acorn"),
/*  84 */   ADD_PLANO_CORDON("Adicionar Plano Cordon"),
/*  85 */   ADD_SETOR_GEOGRAFICO("Adicionar Setor Geográfico"),
/*  86 */   REMOVE_SETORES("Remover Setores"),
/*  87 */   ROTACIONAR_SETOR("Rotacionar Setor(es)"),
/*  88 */   MODO_VIEW("Ativar View"),
/*  89 */   AIS_FORM("Editar AIS"),
/*     */   
/*  91 */   CANCELA_MODO_VIEW("Desativar View"),
/*     */ 
/*     */   
/*  94 */   EDITAR_ROTA("Editar Rota"),
/*  95 */   MODIFICA_CALCULO("Modificar Cálculo Tático"),
/*  96 */   ENQUADRAR_ROTA("Enquadrar rota"),
/*  97 */   CALCULA_PMA("Calcular PMA"),
/*  98 */   MANOBRA_TEMPO("Manobra dado tempo"),
/*  99 */   MANOBRA_VELOC("Manobra dado veloc"),
/* 100 */   PASSAR_SAFO("Passar safo"),
/* 101 */   LIBERAR_CT("Liberar para cenário tático"),
/* 102 */   LIBERAR_ACOMP("Liberar Acompanhamento"),
/* 103 */   LIBERAR_SERVICO_EXTERNO("Liberar para o SCUA"),
/* 104 */   CANCELA_LIBERAR_ACOMP("Cancelar Liberação Acompanhamento"),
/* 105 */   CANCELA_LIBERAR_CT("Cancelar Liberação para o cenário tático"),
/* 106 */   DEFINIR_DLRP("Definir como DLRP"),
/* 107 */   ADD_ACOMPANHAMENTO_SITREP("Adicionar Acompanhamento(s) ao SITREP"),
/* 108 */   EXIBIR_ARMAMENTO("Armamento"),
/* 109 */   ALTERAR_MUNICAO("Alterar Munição"),
/* 110 */   ESTADO_SENSORES("Estado Sensores"),
/* 111 */   CONFIG_TRANSPONDER("Configurar Transponder"),
/* 112 */   ALTERAR_ROS("Alterar ROS"),
/* 113 */   ALTERAR_ROR("Alterar ROR"),
/* 114 */   ADD_VEICULO_SITREP("Adicionar Veículo ao SITREP (S)"),
/* 115 */   INTERROGADOR_IFF("Interrogador IFF"),
/* 116 */   ENGAJAR("Passar TI"),
/* 117 */   MANOBRAR_VEICULO("Manobrar este veículo"),
/* 118 */   REMOVE_OBJETOS_PLANO("Remover Associação com plano"),
/*     */   
/* 120 */   MASTER_CHECK_FIRE("Master Check Fire"),
/* 121 */   REMOVER_MASTER_CHECK_FIRE("Remover Master Check Fire"),
/* 122 */   EXIBIR_ARCO_FOGO("Arco de Fogo"),
/* 123 */   ENGAJAMENTO_INSTRUTOR_VEICULO("Engajar com veículo"),
/* 124 */   ENGAJAMENTO_INSTRUTOR_ALVO("Engajar veículo alvo"),
/* 125 */   ENGAJAMENTO_INSTRUTOR_DUPLO("Efetuar engajamento"),
/*     */   
/* 127 */   BLOQUEIO_ELETRONICO("Bloqueio Eletrônico"),
/* 128 */   MAE_INSTRUTOR("Bloqueio Eletrônico(MAE)"),
/* 129 */   LANCAR_CHAFF_SUPERFICIE("Lançar Chaff Superfície"),
/* 130 */   LANCAR_CHAFF_AERONAVE("Lançar Chaff Aeronave"),
/* 131 */   LANCAR_CHAFF_SUPERFICIE_INSTRUTOR("Lançar Chaff Superfície"),
/* 132 */   LANCAR_CHAFF_AERONAVE_INSTRUTOR("Lançar Chaff Aeronave"),
/*     */ 
/*     */   
/* 135 */   DIP("DIP"),
/* 136 */   INFO_CARTA("Informações da Carta Náutica"),
/* 137 */   MANOBRA_GUIA_GOLF_ICAR("Içar Bandeira Golf"),
/* 138 */   MANOBRA_GUIA_GOLF_ARRIAR("Arriar Bandeira Golf"),
/*     */   
/* 140 */   CRIA_PONTOREFERENCIA("Criar um ponto de referencia"),
/* 141 */   IMPOR_AVARIA("Impor Avarias"),
/*     */   
/* 143 */   ESCRAVIZACAO_VERDADEIRA("Verdadeira"),
/* 144 */   ESCRAVIZACAO_RELATIVA("Relativa"),
/* 145 */   DESESCRAVIZAR("Desescravizar veículo"),
/* 146 */   SIMPLIFICAR_ROTA("Simplificação"),
/* 147 */   FORMULARIO_ROTA("Formulario Rota"),
/* 148 */   TRACK_RADAR_DT("Trackear com RadarDT"),
/* 149 */   CANCELAR_TRACK_RADAR_DT("Cancelar Track do RadarDT"),
/* 150 */   DESAMARRAR_AREA("Desamarrar área"),
/* 151 */   AMARRAR_LM("Amarrar Linha de Marcação a um contato"),
/* 152 */   DESAMARRAR_LM("Desamarrar Linha de Marcação"),
/* 153 */   ELEMENTO_NAVEGACAO("Criar Elemento de Navegação"),
/*     */ 
/*     */   
/* 156 */   ASSOCIAR_DAV("Associar Dav"),
/* 157 */   DESASSOCIAR_DAV("Desassociar DAV"),
/*     */   
/* 159 */   OPERAR_PERISCOPIO("Operar periscópio"),
/*     */ 
/*     */   
/* 162 */   LANCAR_BRS("Lançar BRS"),
/* 163 */   LANCAR_BRS_AUTO("Lançamento automático"),
/* 164 */   GERENCIADOR_BRS_AUTO("Gerenciador BRS Auto"),
/* 165 */   FALHA_BRS("Aplicar Falha BRS"),
/* 166 */   CANCELAR_FALHA_BRS("Cancelar Falha BRS"),
/* 167 */   MONITORAR_BRS("Monitorar BRS"),
/* 168 */   CANCELAR_MONITORAR_BRS("Cancelar Monitoração BRS"),
/* 169 */   ALTERAR_QUANT_BRS("Alterar Quantidade BRS"),
/* 170 */   ALTERAR_CAT("Alterar CAT"),
/*     */   
/* 172 */   POSICAO_REFERENCIAL("Amarrar Polar"),
/* 173 */   ACIONAR_RONCADOR("Acionar Roncador"),
/* 174 */   DESLIGAR_RONCADOR("Desligar Roncador"),
/* 175 */   CONFIGURAR_ROTACAO_GATE("Configurar Rotação Gate"),
/* 176 */   EXIBIR_LEGENDA_SETOR("Exibir Legenda do Setor"),
/* 177 */   OCULTAR_LEGENDA_SETOR("Ocultar Legenda do Setor"),
/* 178 */   ROTACIONAR_GATE("Rotacionar GATE"),
/* 179 */   CRIAR_AREA_EXERCICIO("Criar Área"),
/* 180 */   MANOBRA_INTERCEPTACAO("Manobra Interceptação"),
/* 181 */   CRIAR_MOB("Criar MOB"),
/* 182 */   SELECIONAR_HISTORICO_NAVEGACAO("Selecionar Histórico"),
/* 183 */   EXIBIR_AREAS_COBERTURA_MANOBRA_INTERCEPTACAO("Exibir Áreas de Cobertura"),
/* 184 */   ENVIAR_POINTER("Enviar Pointer"),
/* 185 */   ETA_NA_APT("ETA na APT"),
/* 186 */   CONTROLE_AEREO("CAINT"),
/* 187 */   CONTROLE_AEREO_DUPLO("CAINT"),
/* 188 */   LIBERAR_OBJETO_SERVICO_EXTERNO("Liberar Objeto para Serviço Externo"),
/* 189 */   HABILITAR_AIS_MODO_ATIVO("Habilitar AIS em modo Ativo"),
/* 190 */   DESABILITAR_AIS_MODO_ATIVO("Desabiitar AIS em modo Ativo"),
/* 191 */   ADICIONAR_MARCADOR("Adicionar marcador na gravação"),
/* 192 */   PLOTAR_LINHAS_MARCACAO("Plotar Linhas de Marcação"),
/* 193 */   REMOVE_OBJETO_CORRECAO_MANUAL("Remover Objeto");
/*     */   
/*     */   private boolean habilitado = true;
/*     */   private final String texto;
/*     */   
/*     */   Comando(String texto) {
/* 199 */     this.texto = texto;
/*     */   }
/*     */   
/*     */   public String getTexto() {
/* 203 */     return this.texto;
/*     */   }
/*     */   
/*     */   public boolean isHabilitado() {
/* 207 */     return this.habilitado;
/*     */   }
/*     */   
/*     */   public void setHabilitado(boolean habilitado) {
/* 211 */     this.habilitado = habilitado;
/*     */   }
/*     */ }


/* Location:              /mnt/c/Users/jbmic/OneDrive/Área de Trabalho/hidra/hidra.jar!/ipqm/gsd/hidra/ihm/interacao/tarefa/Comando.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */