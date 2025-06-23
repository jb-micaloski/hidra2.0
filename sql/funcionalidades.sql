INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 1, 'projetos/cisne/cenas/CenarioTatico.fxml', 'A funcionalidade é a responsável por exibir a maioria das informações existentes.', 'cenarioTatico.png', 'Cenário Tático CISNE'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 1
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 2, 'projetos/instrutor/cenas/CenarioTatico.fxml', 'A funcionalidade é a responsável por exibir a maioria das informações existentes.', 'cenarioTatico.png', 'Cenário Tático Instrutor'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 2
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 3, 'projetos/cisne/cenas/Sitrep.fxml', 'A funcionalidade tem por característica a visualização de acompanhamentos automáticos ou manuais.', 'sitrep.png', 'Sitrep'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 3
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 4, 'projetos/geral/cenas/PlanoNavegacao.fxml', 'A funcionalidade permite a associação de rotas e pontos fixos ao plano de navegação.', 'planoNavegacao.png', 'Plano de Navegação'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 4
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 5, 'projetos/cisne/cenas/Configuracoes.fxml', 'A funcionalidade permite ao usuário alterar as configurações do sistema.', 'configuracoes.png', 'Configurações'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 5
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 6, 'projetos/instrutor/cenas/Configuracoes.fxml', 'A funcionalidade permite ao usuário alterar as configurações do sistema.', 'configuracoes.png', 'Configurações Instrutor'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 6
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 7, 'projetos/administracao/cenas/Usuario.fxml', 'A funcionalidade permite gerenciar usuários.', 'usuario.png', 'Cadastrar Usuario'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 7
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 8, 'projetos/administracao/cenas/Funcionalidade.fxml', 'A funcionalidade permite gerenciar as funcionalidades.', 'funcionalidade.png', 'Cadastrar Funcionalidade'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 8
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 9, 'projetos/administracao/cenas/Maquina.fxml', 'A funcionalidade permite gerenciar as máquinas .', 'maquina.png', 'Cadastrar Máquina'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 9
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 10, 'projetos/sonar/cenas/Sonar.fxml', 'Funcionalidade Aluno Sonar.', 'sonar.png', 'Sonar'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 10
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 11, 'projetos/governo/cenas/Governo.fxml', 'Funcionalidade Aluno Governo.', 'governo.png', 'Governo'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 11
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 12, 'projetos/cisne/cenas/Sensor.fxml', 'Funcionalidade Aluno Sensor.', 'sensores.png', 'Sensores'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 12
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 13, 'projetos/geral/cenas/Ajuda.fxml', 'Funcionalidade Ajuda', 'ajuda.png', 'Ajuda'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 13
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 14, 'projetos/mage/cenas/Mage.fxml', 'Funcionalidade Aluno Mage.', 'mage1.png', 'Mage'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 14
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 15, 'projetos/instrutor/cenas/Monitoracao.fxml', 'A funcionalidade é responsável pela monitaração das máquinas.', 'monitoracao.png', 'Monitoração'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 15
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 16, 'projetos/preparacao/cenas/Armas.fxml', 'A funcionalidade permite gerenciar armas.', 'armas.png', 'Armas'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 16
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 17, 'projetos/preparacao/cenas/Plataformas.fxml', 'A funcionalidade permite gerenciar plataformas.', 'plataformas.png', 'Plataformas'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 17
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 18, 'projetos/reproducao/cenas/Reproducao.fxml', 'Funcionalidade Reprodução', 'reproducao.png', 'Reprodução'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 18
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 19, 'projetos/radar/generico/cenas/RadarGenerico.fxml', 'Funcionalidade Aluno Radar.', 'radar.png', 'Radar'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 19
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 20, 'projetos/cisne/cenas/LinkYb.fxml', 'Funcionalidade do LinkYb', 'linkyb.png', 'LinkYb'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 20
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 21, 'projetos/reproducao/cenas/TelaHistoricoExercicios.fxml', 'Funcionalidade Tela para remover Exercicios do histórico.', 'sitrep.png', 'Tela Exercicios'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 21
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 22, 'projetos/estrategia/cenas/CenarioEstrategico.fxml', 'Funcionalidade Aluno Estratégia.', 'estrategia.png', 'Estratégia'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 22
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 23, 'projetos/estrategia/cenas/Definicoes.fxml', 'Definições do Exercício.', 'configuracoes.png', 'Definições'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 23
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 24, 'projetos/sar/cenas/CenarioTaticoSAR.fxml', 'Funcionalidade de cenário tático do projeto SAR', 'cenarioTatico.png', 'Cenário Tático SAR'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 24
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 25, 'projetos/sar/cenas/ConfiguracoesSAR.fxml', 'Funcionalidade de configurações do projeto SAR', 'configuracoes.png', 'Configurações SAR'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 25
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 26, 'projetos/sar/cenas/PreparacaoSAR.fxml', 'Funcionalidade de preparação do projeto SAR', 'planoNavegacao.png', 'Preparação SAR'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 26
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 27, 'projetos/sonar/cenas/SonarSDAC.fxml', 'Funcionalidade aluno Sonar SDAC', 'sonar.png', 'Sonar SDAC'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 27
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 28, 'projetos/preparacao/cenas/Veiculos.fxml', 'A funcionalidade permite gerenciar veiculos.', 'veiculos.png', 'Veículos'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 28
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 29, 'projetos/cisne/cenas/IFF.fxml', 'Funcionalidade do sensor IFF.', 'iff.png', 'IFF'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 29
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 30, 'projetos/preparacao/cenas/Sensores.fxml', 'Funcionalidade sensor do preparação.', 'sensores.png', 'Sensores Preparação'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 30
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 31, 'projetos/suporte/cenas/Suporte.fxml', 'Funcionalidade suporte.', 'suporte.png', 'Suporte'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 31
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 32, 'projetos/preparacao/cenas/Exercicio.fxml', 'Funcionalidade Exercicio do preparação.', 'exercicio.png', 'Exercicio'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 32
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 33, 'projetos/preparacao/cenas/Fonia.fxml', 'Funcionalidade Fonia do preparação.', 'fonia.png', 'Fonia'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 33
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 34, 'projetos/suporte/cenas/Database.fxml', 'Funcionalidade Banco de Dados.', 'database.png', 'Banco de Dados'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 34
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 35, 'projetos/preparacao/cenas/Despistadores.fxml', 'Funcionalidade Despistadores.', 'interferidor.png', 'Despistadores'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 35
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 36, 'projetos/scua/cenas/CenarioTatico.fxml', 'A funcionalidade é a responsável por exibir a maioria das informações existentes.', 'cenarioTatico.png', 'Cenário Tático SCUA'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 36
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 37, 'projetos/mapa3d/cenas/CenarioTatico.fxml', 'A funcionalidade é a responsável por exibir o mapa 3D.', 'cenarioTatico3D.png', 'Cenário Tático 3D'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 37
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 38, 'projetos/scua/cenas/Configuracoes.fxml', 'A funcionalidade permite ao usuário alterar as configurações do sistema.', 'configuracoes.png', 'Configurações SCUA'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 38
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 39, 'projetos/administracao/cenas/Cartas.fxml', 'A funcionalidade é a responsável por configurar cartas náuticas.', 'configuracaoCartas.png', 'Cartas'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 39
    );


INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 40, 'projetos/preparacao/cenas/DAV.fxml', 'Funcionalidade para configuração do DAV.', 'iconeDAV.png', 'DAV'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 40
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 41, 'projetos/cisne/cenas/CenarioTaticoAluno.fxml', 'A funcionalidade é a responsável por exibir a maioria das informações existentes.', 'cenarioTatico.png', 'Cenário Tático ALUNO'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 41
    );


INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 42, 'projetos/cisne/cenas/VeiculoCisne.fxml', 'A funcionalidade é a responsável por exibir os formulários de cadastro de veículo.', 'veiculos.png', 'Configuração de Veículo'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 42
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome, ativo)
SELECT 43, '', 'A funcionalidade é a responsável por enviar a posição do próprio veículo.', 'veiculos.png', 'Enviar Posição',false
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 43
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome, ativo)
SELECT 44, '', 'A funcionalidade é a responsável por exibir a maioria das informações existentes.', 'cenarioTatico.png', 'Cenário Tático WEB', false
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 44
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 45, 'projetos/cisne/cenas/MensagemAIS.fxml', 'A funcionalidade é a responsável por exibir as mensagens do ais.', 'msgAIS.png', 'Mensagens AIS'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 45
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 46, 'projetos/geral/cenas/ConfiguracaoSCUA.fxml', 'A funcionalidade permite ao usuário alterar as configurações do SCUA.', 'configuracoes.png', 'Configuração SCUA ADMIN'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 46
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 47, 'projetos/geral/cenas/ConfiguracaoLora.fxml', 'A funcionalidade permite ao usuário realizar as configurações do Lora.', 'lora.png', 'Configuração LORA'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 47
    );

INSERT INTO funcionalidade
    (id_funcionalidade, caminho_projeto, descricao, imagem_func, nome)
SELECT 48, 'projetos/cisne/cenas/CenarioTaticoSimPer.fxml', 'A funcionalidade é a responsável por exibir a maioria das informações existentes.', 'cenarioTatico.png', 'Cenário Tático SIMPER'
WHERE
    NOT EXISTS (
        SELECT id_funcionalidade FROM funcionalidade WHERE id_funcionalidade = 48
    );


SELECT setval('func_seq', 48);
