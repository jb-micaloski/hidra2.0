INSERT INTO dados_ambientais (id_dados_ambientais, declinacao_magnetica, direcao_corrente, direcao_vento, estado_mar,
hemisferio, mdr1, mdr2, nuvens, topo_nuvens, profundidade_camada, profundidade_local, velocidade_corrente,
velocidade_vento, visibilidade, chuva, chance_raios) VALUES (1, 12, 20, 30, 2, 'E', 90, 120, 1000, 1500, 250, 1000, 1, 5, 12, 5, 20);
SELECT setval('dados_ambientais_seq', 1);

INSERT INTO teatro_operacao (id_teatro_operacao,hora_inicio,latitude,longitude,nome,id_dados_ambientais,descricao) VALUES (2, 1420106400444, -22.8633999999999986, -43.1751999999999967, 'EXERCICIO1', 1,'Exercício de teste');
SELECT setval('teatro_operacao_seq', 2);

INSERT INTO condicao_inicial(id_condicao_inicial,id_teatro_operacao,identificador) 
                     VALUES (1, 2,'NPOCAP');
SELECT setval('condicao_inicial_seq', 16);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES ((select nextval('objeto_tatico_seq')));
INSERT INTO plano_navegacao (id_objeto_tatico,descricao,plano_corrente,id_rota_alternativa,id_rota_principal,condicao_inicial_dono)
    VALUES ((select currval('objeto_tatico_seq')),'Plano de Navegação',true,null,null,1);

INSERT INTO bloco VALUES (1, '25', 1);
INSERT INTO bloco VALUES (2, '26', 1);
INSERT INTO bloco VALUES (3, '27', 1);
SELECT setval('bloco_seq', 7);

--Taxa de Guinada Plataformas

INSERT INTO guinada_aviao(id_guinada_aviao, mais_de_seiscentos_nos, trezentos_um_a_seiscentos_nos, zero_a_trezentos_nos)    VALUES (1, 300, 170, 60);
SELECT setval('guinada_aviao_seq', 1);

INSERT INTO guinada_helicoptero(id_guinada_helicoptero, mais_de_cem_nos, zero_a_cem_nos)    VALUES (1, 200, 250);
SELECT setval('guinada_helicoptero_seq', 1);

INSERT INTO guinada_superficie (id_guinada_maritima,zero_a_cinco_nos,cinco_a_dez_nos,dez_a_quinze_nos,quinze_a_vinte_nos,vinte_a_vinte_cinco_nos,vinte_cinco_a_trinta_nos,trinta_a_trinta_cinco_nos,mais_de_trinta_cinco_nos) VALUES (1, 60, 70, 80, 90, 100, 110, 120, 130);

INSERT INTO guinada_submarino(id_guinada_maritima, cinco_a_dez_nos, dez_a_quinze_nos, quinze_a_vinte_nos, 
            vinte_a_vinte_cinco_nos, vinte_cinco_a_trinta_nos, zero_a_cinco_nos, mais_de_trinta_nos)
    VALUES (2, 45, 60, 75, 90, 115, 30, 130);

SELECT setval('guinada_maritima_seq', 2);

---------------------------------------------------------------------

-- Plataforma
INSERT INTO plataforma VALUES (1, 'NPOCAP', 15, 15, 50);
SELECT setval('plataforma_seq', 1);


--Plataformas Superficie, Avião, Helicoptero e Submarino
INSERT INTO plataforma_superficie (
            movimento_in_out, num_eixos, num_pas_eixo, id_tipo_propulsao,
            capacidade_deslocamento, id_plataforma, id_taxa_guinada, 
            calado, comprimento_proa, comprimento_popa, largura) 
            VALUES (true, 1, 3, 'MOTOR',
                    1, 1, 1,
                    6, 60, 40, 50);

INSERT INTO info_veiculo (id_informacao_veiculo, nome, id_plataforma) 
    VALUES (1, 'P121', 1);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (1, 9526409, '710494000', 100);
INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
    VALUES (1, true, false);

INSERT INTO info_veiculo (id_informacao_veiculo, nome, id_plataforma) 
    VALUES (2, 'ComOpNav', 1);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (2, 7777777, '999999999', 0);
INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
    VALUES (2, false, false);

----- Servidor
INSERT INTO maquina (id_maquina,hostname, titulo)  VALUES (2,  'OPENAV-003', 'OPENAV-SRV');
SELECT setval('maquina_seq', 2);

-- Veiculos
INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (2);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (2, 1, true);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (2);

INSERT INTO configuracao_equipamento VALUES (1, true, true, 4444);
INSERT INTO configuracao_equipamento VALUES (2, true, true, 4445);
INSERT INTO configuracao_equipamento VALUES (3, true, true, 4446);
INSERT INTO configuracao_equipamento VALUES (4, true, true, 4447);
INSERT INTO configuracao_equipamento VALUES (5, true, true, 4457);
INSERT INTO configuracao_equipamento VALUES (6, true, true, 4457);

INSERT INTO configuracao_equipamento_maquina(id_config_sensor, id_maquina) VALUES (5, 1);

INSERT INTO equipamento(id_sensor, tipo, custo_sensor, nome)
    VALUES (1, 'AIS', NULL, 'AIS');
INSERT INTO equipamento(id_sensor, tipo, custo_sensor, nome)
    VALUES (2, 'GIRO', NULL, 'Giro');
INSERT INTO equipamento(id_sensor, tipo, custo_sensor, nome)
    VALUES (3, 'GPS', NULL, 'GPS');
INSERT INTO equipamento(id_sensor, tipo, custo_sensor, nome)
    VALUES (4, 'RADAR_BUSCA', NULL, 'Radar de busca');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (5, 'SCUA', NULL, 'SCUA');

SELECT setval('sensor_seq', 5);----------------------------------------

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 1, 1);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 2, 2);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 3, 3);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 4, 4);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 5, 5);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 5, 6);

INSERT INTO configuracao_ais VALUES (100, 150, 1);

INSERT INTO configuracao_giro VALUES (10, true, 2);

INSERT INTO configuracao_gps VALUES (10, 3);

INSERT INTO configuracao_radar_busca VALUES (30, 60, 4);

INSERT INTO configuracao_scua(
            tempo_eliminado, tempo_perdido, frequencia_envio, id_config_sensor, 
            id_maquina_destino)
    VALUES (120, 60, 10, 5, 
            1); -- 2m/1m
INSERT INTO configuracao_scua(
            tempo_eliminado, tempo_perdido, frequencia_envio, id_config_sensor, 
            id_maquina_destino)
    VALUES (120, 60, 10, 6, 
            2); -- 2m/1m



INSERT INTO sensor_ais VALUES (1);
INSERT INTO sensor_giro VALUES (2);
INSERT INTO sensor_gps VALUES (3);
INSERT INTO radar(
            frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (1750, 255, 250, 50, 4);
INSERT INTO radar_busca VALUES (false, false, 5, 'CIRCULAR', 4);
INSERT INTO sensor_scua VALUES (5);

-- Fonia INÍCIO -------------------------------------------------------------------
INSERT INTO configuracao_fonia (id_configuracao_fonia, nome, configuracao_padrao) VALUES(1, 'Configuracao Padrao', true);
SELECT setval('configuracao_fonia_seq', 1);

INSERT INTO perfil_usuario (id_perfil, nome, classe, id_simbologia, servidor_cartas, historico_navegacao,perfil_instrutor,necessita_veiculo_associado) VALUES (17, 'SCUA Embarcado', 'SCUAEmbarcado', 5, 'IPQM', false, false,true);
INSERT INTO usuario (id_usuario, id_perfil, usuario, senha, descricao) VALUES (17, 17, 'scua', '03AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4','');
SELECT setval('usuario_seq', 17);

UPDATE funcionalidade SET nome='Cenário Tático' WHERE id_funcionalidade=1;

INSERT INTO usuario_func (id_funcionalidade,id_usuario, interagir,posicao) VALUES (1, 17,true,1);
INSERT INTO usuario_func (id_funcionalidade,id_usuario, interagir,posicao) VALUES (3, 17,true,2);
INSERT INTO usuario_func (id_funcionalidade,id_usuario, interagir,posicao) VALUES (4, 17,true,3);
INSERT INTO usuario_func (id_funcionalidade,id_usuario, interagir,posicao) VALUES (12, 17,true,4);


INSERT INTO condicao_entidade(altitude, latitude,longitude,na,rumo,velocidade,id_objeto_tatico,
            detect_mage, detect_radar, detect_sonar, detect_mad, detect_iff, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES (0, -22.862810980383113, -43.15362489151045, '2121', 0, 0, 2, 'S', 'S', 'S', 'S', 'S', true, 'A', 1);

INSERT INTO usuario_maquina(id_maquina,id_usuario,login_automatico) VALUES (1,17,true);

INSERT INTO usuario_maquina_condicao(id_condicao,id_maquina,id_usuario) VALUES (1,1,17);

-- Classes das Fusoes de Objetos
INSERT INTO fusao (id_fusao, nome) VALUES (1, 'FusaoAcompanhamentosCinematica');

-- Associação da fusao
INSERT INTO perfil_usuario_fusao (id_fusao, id_perfil)  VALUES (1,  17);