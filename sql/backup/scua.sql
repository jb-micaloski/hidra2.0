INSERT INTO dados_ambientais (id_dados_ambientais, declinacao_magnetica, direcao_corrente, direcao_vento, estado_mar,
hemisferio, mdr1, mdr2, nuvens, topo_nuvens, profundidade_camada, profundidade_local, velocidade_corrente,
velocidade_vento, visibilidade, chuva, chance_raios) VALUES (1, 12, 20, 30, 2, 'E', 90, 120, 1000, 1500, 250, 1000, 1, 5, 12, 5, 20);
SELECT setval('dados_ambientais_seq', 1);

INSERT INTO teatro_operacao (id_teatro_operacao,hora_inicio,latitude,longitude,nome,id_dados_ambientais,descricao) VALUES (2, 1420106400444, -22.830094560860065, -43.18222415215477, 'Teatro de operações', 1,'Teatro de operações de testes');
SELECT setval('teatro_operacao_seq', 1);

INSERT INTO condicao_inicial(id_condicao_inicial,id_teatro_operacao,identificador) 
                     VALUES (1, 2,'CON');
SELECT setval('condicao_inicial_seq', 1);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES ((select nextval('objeto_tatico_seq')));
INSERT INTO plano_navegacao (id_objeto_tatico,descricao,plano_corrente,id_rota_alternativa,id_rota_principal,condicao_inicial_dono)
    VALUES ((select currval('objeto_tatico_seq')),'Plano de Operação 1',true,null,null,1);

INSERT INTO bloco VALUES (1, '70', 1);
INSERT INTO bloco VALUES (2, '71', 1);
INSERT INTO bloco VALUES (3, '72', 1);
INSERT INTO bloco VALUES (4, '73', 1);
INSERT INTO bloco VALUES (5, '74', 1);
INSERT INTO bloco VALUES (6, '75', 1);
INSERT INTO bloco VALUES (7, '76', 1);
INSERT INTO bloco VALUES (8, '77', 1);
INSERT INTO bloco VALUES (9, '60', 1);
INSERT INTO bloco VALUES (10, '61', 1);
INSERT INTO bloco VALUES (11, '62', 1);
INSERT INTO bloco VALUES (12, '63', 1);
INSERT INTO bloco VALUES (13, '64', 1);
INSERT INTO bloco VALUES (14, '65', 1);
INSERT INTO bloco VALUES (15, '66', 1);
INSERT INTO bloco VALUES (16, '67', 1);

SELECT setval('bloco_seq', 17);

INSERT INTO guinada_superficie (id_guinada_maritima,zero_a_cinco_nos,cinco_a_dez_nos,dez_a_quinze_nos,quinze_a_vinte_nos,vinte_a_vinte_cinco_nos,vinte_cinco_a_trinta_nos,trinta_a_trinta_cinco_nos,mais_de_trinta_cinco_nos) VALUES (1, 60, 70, 80, 90, 100, 110, 120, 130);

SELECT setval('guinada_maritima_seq', 1);

------------------
INSERT INTO tipo_plataforma VALUES(1,23);
SELECT setval('tipo_plataforma_seq', 2);

-- Plataforma
INSERT INTO plataforma VALUES (1, 'Prédio', 15, 15, 50);
SELECT setval('plataforma_seq', 3);


--------

--Plataformas Superficie, Avião, Helicoptero e Submarino
INSERT INTO plataforma_superficie (
            movimento_in_out, num_eixos, num_pas_eixo, id_tipo_propulsao,
            capacidade_deslocamento,  id_plataforma, id_taxa_guinada, 
            calado, comprimento_proa, comprimento_popa, largura,possui_convoo) 
            VALUES (true, 1, 3, 'MOTOR',
                    1, 1, 1,
                    6, 60, 40, 50,true);

INSERT INTO plataforma_tipo_plataforma VALUES(1,1);

-------------------------------

INSERT INTO info_veiculo (id_informacao_veiculo, nome, id_plataforma) 
    VALUES (1, 'ComOpNav', 1);
-- INSERT INTO info_veiculo (id_informacao_veiculo, nome, id_plataforma) 
--     VALUES (2, 'IPQM002', 1);
-- INSERT INTO info_veiculo (id_informacao_veiculo, nome, id_plataforma) 
--     VALUES (3, 'IPQM003', 1);

INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (1, 7777777, '999999999', 0);
-- INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
--     VALUES (2, 7777777, '999999999', 0);
-- INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
--     VALUES (3, 7777777, '999999999', 0);

INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
    VALUES (1, false, false);
-- INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
--     VALUES (2, false, false);
-- INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
--     VALUES (3, false, false);

INSERT INTO identificador_lora_info_veiculo (id_lora_info_veiculo, conteudo_identificador, id_informacao_veiculo)
    VALUES (1,'ComOpNav', 1);
-- INSERT INTO identificador_lora_info_veiculo (id_lora_info_veiculo, conteudo_identificador, id_informacao_veiculo)
--     VALUES (2,'IPQM002', 2);
-- INSERT INTO identificador_lora_info_veiculo (id_lora_info_veiculo, conteudo_identificador, id_informacao_veiculo)
--     VALUES (3,'IPQM003', 3);


-- Veiculos
INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (9);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (9, 1, true);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (9);

-- Equipamentos

INSERT INTO configuracao_equipamento (id_config_sensor, habilitado, porta_tcp_habilitado, porta_tcpip) VALUES (1, true, true, 4444);
INSERT INTO configuracao_equipamento (id_config_sensor, habilitado, porta_tcp_habilitado, porta_tcpip) VALUES (2, true, true, 4445);
INSERT INTO configuracao_equipamento (id_config_sensor, habilitado, porta_tcp_habilitado, porta_tcpip) VALUES (24, true, true, 50000);
INSERT INTO configuracao_equipamento (id_config_sensor, habilitado, porta_tcp_habilitado, porta_tcpip) VALUES (25, true, true, 50001);
INSERT INTO configuracao_equipamento (id_config_sensor, habilitado, porta_tcp_habilitado, porta_tcpip) VALUES (26, true, true, 50002);
INSERT INTO configuracao_equipamento (id_config_sensor, habilitado, porta_tcp_habilitado, porta_tcpip) VALUES (27, true, true, 50003);
INSERT INTO configuracao_equipamento (id_config_sensor, habilitado, porta_tcp_habilitado, porta_tcpip) VALUES (28, true, true, 50004);
INSERT INTO configuracao_equipamento (id_config_sensor, habilitado, porta_tcp_habilitado, porta_tcpip) VALUES (29, true, true, 50005);
INSERT INTO configuracao_equipamento (id_config_sensor, habilitado, porta_tcp_habilitado, porta_tcpip) VALUES (30, true, true, 50006);
INSERT INTO configuracao_equipamento (id_config_sensor, habilitado, porta_tcp_habilitado, porta_tcpip) VALUES (31, true, true, 50007);
INSERT INTO configuracao_equipamento (id_config_sensor, habilitado, porta_tcp_habilitado, porta_tcpip) VALUES (33, true, true, 50008);
INSERT INTO configuracao_equipamento (id_config_sensor, habilitado, porta_tcp_habilitado, porta_tcpip) VALUES (32, true, true, 50009);
INSERT INTO configuracao_equipamento (id_config_sensor, habilitado, porta_tcp_habilitado, porta_tcpip) VALUES (34, true, true, 4457); -- não alterar essa porta
INSERT INTO configuracao_equipamento (id_config_sensor, habilitado, porta_tcp_habilitado, porta_tcpip) VALUES (35, true, true, 8556); -- não alterar essa porta
INSERT INTO configuracao_equipamento (id_config_sensor, habilitado, porta_tcp_habilitado, porta_tcpip) VALUES (36, true, true, 50010);
INSERT INTO configuracao_equipamento (id_config_sensor, habilitado, porta_tcp_habilitado, porta_tcpip) VALUES (37, true, true, 50011);
INSERT INTO configuracao_equipamento (id_config_sensor, habilitado, porta_tcp_habilitado, porta_tcpip) VALUES (38, true, true, 10112);

INSERT  INTO configuracao_equipamento_maquina(id_config_sensor, id_maquina) VALUES (34 , 1);
INSERT  INTO configuracao_equipamento_maquina(id_config_sensor, id_maquina) VALUES (35 , 1);

INSERT INTO equipamento(id_sensor, tipo, custo_sensor, nome)
    VALUES (1, 'AIS', NULL, 'AIS');
INSERT INTO equipamento(id_sensor, tipo, custo_sensor, nome)
    VALUES (2, 'RADAR_BUSCA', NULL, 'Radar');
INSERT INTO equipamento(id_sensor, tipo, custo_sensor, nome)
    VALUES (6, 'LINK_YB', NULL, 'LinkYb');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (16, 'NOVO_LINK_YB', NULL, 'Novo LinkYb');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (24, 'AIS_NACIONAL', NULL, 'AIS Nacional');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (25, 'GRAFIMAR', NULL, 'GRAFIMAR');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (26, 'LRIT', NULL, 'LRIT');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (27, 'MSSIS', NULL, 'MSSIS');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (28, 'ORBCOMM_COSTEIRO', NULL, 'ORBCOMM Costeiro');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (29, 'ORBCOMM_SATELITAL', NULL, 'ORBCOMM Satelital');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (30, 'PREPS', NULL, 'PREPS');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (31, 'SIMMAP', NULL, 'SIMMAP');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (32, 'SAGBD', NULL, 'SAGBD');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (33, 'VRMTC', NULL, 'VRMTC');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (34, 'SCUA', NULL, 'SCUA');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (35, 'LORA', NULL, 'LORA');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (36, 'DISPOSITIVO_MOVEL', NULL, 'Dispositivo Móvel');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (37, 'DADOS_METEOROLOGICOS', NULL, 'Dados Meteorologicos');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (38, 'SDFDAN', NULL, 'SDFDAN');

SELECT setval('sensor_seq', 39);

INSERT INTO radar(frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, largura_pulso, id_sensor) VALUES (1750, 255, 250, 50, 2);

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 1, 1);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 2, 2);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 24, 24);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 25, 25);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 26, 26);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 27, 27);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 28, 28);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 29, 29);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 30, 30);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 31, 31);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 32, 32);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 33, 33);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 34, 34);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 35, 35);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 36, 36);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 37, 37);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 38, 38);
-- INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 35, 35);
-- INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (3, 35, 35);


INSERT INTO configuracao_ais VALUES (600, 300, 1); -- 10m/5m
INSERT INTO configuracao_radar_busca VALUES (60, 30, 2); -- 1m/30s
INSERT INTO configuracao_ais_nacional VALUES (7200, 300, 24); -- 2h/5m
INSERT INTO configuracao_grafimar VALUES (22400, 1800, 25); -- 6h/30m
INSERT INTO configuracao_lrit VALUES (3600, 600, 26); --1h/10m
INSERT INTO configuracao_mssis VALUES (7200, 300, 27);-- 2h/5m
INSERT INTO configuracao_orbcomm_costeiro VALUES (43200, 3600, 28); --12h/1h
INSERT INTO configuracao_orbcomm_satelital VALUES (43200, 3600, 29);--12h/1h
INSERT INTO configuracao_preps VALUES (22400, 1800, 30);-- 6h/30m
INSERT INTO configuracao_simmap VALUES (18000, 1200, 31); --5h/20m
INSERT INTO configuracao_sagbd VALUES (720000, 3600, 32); --200h/1h
INSERT INTO configuracao_vrmtc VALUES (3600, 600, 33);--1h/10m
INSERT INTO configuracao_scua(
            tempo_eliminado, tempo_perdido, frequencia_envio, id_config_sensor, 
            id_maquina_destino)
    VALUES (120, 60, 10, 34, 
            1); -- 2m/1m
INSERT INTO configuracao_lora (tempo_eliminado, tempo_perdido, endereco_envio, frequencia_envio, porta_envio, 
            id_config_sensor, porta_recepcao_texto) VALUES (300, 150, 'SRV-LORA', 5, 8554,35, 8555); -- 2m/30s
INSERT INTO configuracao_dispositivo_movel VALUES (120, 30, 36); -- 2m/30s
INSERT INTO configuracao_dados_meteorologicos VALUES (500, 37);
INSERT INTO configuracao_sdfdan VALUES (30, 15, 38);

INSERT INTO sensor_ais VALUES (1);
INSERT INTO radar_busca VALUES (false, false, 5, 'CIRCULAR', 2);
INSERT INTO sensor_linkyb VALUES (6);
INSERT INTO sensor_novolinkyb (id_sensor) VALUES (16);
INSERT INTO sensor_ais_nacional (id_sensor) VALUES (24);
INSERT INTO sensor_grafimar (id_sensor) VALUES (25);
INSERT INTO sensor_lrit (id_sensor) VALUES (26);
INSERT INTO sensor_mssis (id_sensor) VALUES (27);
INSERT INTO sensor_orbcomm_costeiro (id_sensor) VALUES (28);
INSERT INTO sensor_orbcomm_satelital (id_sensor) VALUES (29);
INSERT INTO sensor_preps (id_sensor) VALUES (30);
INSERT INTO sensor_simmap (id_sensor) VALUES (31);
INSERT INTO sensor_sagbd (id_sensor) VALUES (32);
INSERT INTO sensor_vrmtc (id_sensor) VALUES (33);
INSERT INTO sensor_scua (id_sensor) VALUES (34);
INSERT INTO sensor_lora (id_sensor) VALUES (35);
INSERT INTO sensor_dispositivo_movel (id_sensor) VALUES (36);
INSERT INTO sensor_dados_meteorologicos (id_sensor) VALUES (37);
INSERT INTO sensor_sdfdan (id_sensor) VALUES (38);

-- FIM Equipamentos

-- Propria maquina associada e com login automatico

INSERT INTO condicao_entidade(altitude, latitude,longitude,na,rumo,velocidade,id_objeto_tatico,
            detect_mage, detect_radar, detect_sonar, detect_mad, detect_iff, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES (0, -22.830094560860065, -43.18222415215477, '1212', 0, 0, 9, 'S', 'S', 'S', 'S', 'S', true, 'A', 1);

INSERT INTO usuario_maquina(id_maquina,id_usuario,login_automatico) VALUES (1,14,true);

INSERT INTO usuario_maquina_condicao(id_condicao,id_maquina,id_usuario) VALUES (1,1,14);

-- Pessoa e usuário web

INSERT INTO pessoa(
            id_pessoa, email, info, nome, telefone)
    VALUES (1, '', '', 'Fulano da Silva', '');

INSERT INTO pessoa_civil(
            cpf, id_pessoa)
    VALUES ('11111111111', 1);

INSERT INTO usuario(
            id_usuario, descricao, id_perfil, usuario, senha)
    VALUES (17, '', 7, 'gsd', '03AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4');

INSERT INTO pessoa_usuario(
            id_pessoa, id_usuario, habilitado)
    VALUES (1, 17, true);

-- Classes dos Classificadores de Objetos
INSERT INTO classificacao (id_classificacao, nome) VALUES (1, 'ClassificadorAcompanhamentosIndentificacao');


INSERT INTO classificacao (id_classificacao, nome) VALUES (2, 'ClassificadorAcompanhamentosRotas');


-- Associação da classificação
INSERT INTO perfil_usuario_classificacao (id_classificacao, id_perfil)  VALUES (1,  7);

-- Classes das Fusoes de Objetos
INSERT INTO fusao (id_fusao, nome) VALUES (1, 'FusaoAcompanhamentosCinematica');
INSERT INTO fusao (id_fusao, nome) VALUES (2, 'FusaoAcompanhamentosIdentificacao');


-- Associação da fusao
INSERT INTO perfil_usuario_fusao (id_fusao, id_perfil)  VALUES (1,  7);
INSERT INTO perfil_usuario_fusao (id_fusao, id_perfil)  VALUES (2,  7);
-- LIT SCUA

-- INSERT INTO maquina (id_maquina,hostname, titulo)  VALUES (2,  'INSPEQ-061', 'MARCELO');
-- INSERT INTO maquina (id_maquina,hostname, titulo)  VALUES (3,  'OPENAV-001', 'CCTON 1');
-- INSERT INTO maquina (id_maquina,hostname, titulo)  VALUES (4,  'OPENAV-002', 'CCTON 2');
-- INSERT INTO maquina (id_maquina,hostname, titulo)  VALUES (5,  'INSPEQ-054', 'PAULO');
-- INSERT INTO maquina (id_maquina,hostname, titulo)  VALUES (6,  'OPENAV-WEB', 'SERVIDOR WEB');
-- INSERT INTO maquina (id_maquina,hostname, titulo)  VALUES (7,  'INSPEQ-011', 'IPqM 1');
-- SELECT setval('maquina_seq', 8);

-- INSERT INTO usuario_maquina(id_maquina,id_usuario,login_automatico) VALUES (2,14,true);
-- INSERT INTO usuario_maquina(id_maquina,id_usuario,login_automatico) VALUES (3,14,true);
-- INSERT INTO usuario_maquina(id_maquina,id_usuario,login_automatico) VALUES (4,14,true);
-- INSERT INTO usuario_maquina(id_maquina,id_usuario,login_automatico) VALUES (5,14,true);
-- INSERT INTO usuario_maquina(id_maquina,id_usuario,login_automatico) VALUES (6,14,true);
-- INSERT INTO usuario_maquina(id_maquina,id_usuario,login_automatico) VALUES (7,14,true);
-- INSERT INTO usuario_maquina(id_maquina,id_usuario,login_automatico) VALUES (7,2,true);
-- INSERT INTO usuario_maquina(id_maquina,id_usuario,login_automatico) VALUES (7,5,true);
-- INSERT INTO usuario_maquina_condicao(id_condicao,id_maquina,id_usuario) VALUES (1,2,14);
-- INSERT INTO usuario_maquina_condicao(id_condicao,id_maquina,id_usuario) VALUES (1,3,14);
-- INSERT INTO usuario_maquina_condicao(id_condicao,id_maquina,id_usuario) VALUES (1,4,14);
-- INSERT INTO usuario_maquina_condicao(id_condicao,id_maquina,id_usuario) VALUES (1,5,14);
-- INSERT INTO usuario_maquina_condicao(id_condicao,id_maquina,id_usuario) VALUES (1,6,14);
-- INSERT INTO usuario_maquina_condicao(id_condicao,id_maquina,id_usuario) VALUES (1,7,14);

-- Inserção na tabela perfil_usuario
INSERT INTO perfil_usuario (id_perfil, nome, classe, id_simbologia, servidor_cartas, historico_navegacao,perfil_instrutor,quantidade_maxima_objetos,necessita_veiculo_associado, id_ambiente_instalacao) VALUES (17, 'SCUA WEB', 'ScuaWeb', 5, 'IPQM', false, false, 10000, true, 1);
INSERT INTO perfil_usuario (id_perfil, nome, classe, id_simbologia, servidor_cartas, historico_navegacao,perfil_instrutor,quantidade_maxima_objetos,necessita_veiculo_associado, id_ambiente_instalacao) VALUES (18, 'SCUA MOBILE', 'ScuaMobile', 5, 'IPQM', false, false, 10000, true,3);
