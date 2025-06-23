INSERT INTO dados_ambientais (id_dados_ambientais, declinacao_magnetica, direcao_corrente, direcao_vento, estado_mar,
hemisferio, mdr1, mdr2, nuvens, topo_nuvens, profundidade_camada, profundidade_local, velocidade_corrente,
velocidade_vento, visibilidade, chuva, chance_raios) VALUES (1, 12, 20, 30, 2, 'E', 90, 120, 1000, 1500, 250, 1000, 1, 5, 12, 5, 20);
SELECT setval('dados_ambientais_seq', 1);

INSERT INTO teatro_operacao (id_teatro_operacao,hora_inicio,latitude,longitude,nome,id_dados_ambientais,descricao) VALUES (2, 1420106400444, -22.8633999999999986, -43.1751999999999967, 'EXERCICIO1', 1,'Exercício de teste');
SELECT setval('teatro_operacao_seq', 2);

INSERT INTO condicao_inicial(id_condicao_inicial,id_teatro_operacao,identificador) 
                     VALUES (1, 2,'Cubículo 1');
SELECT setval('condicao_inicial_seq', 16);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES ((select nextval('objeto_tatico_seq')));
INSERT INTO plano_navegacao (id_objeto_tatico,descricao,plano_corrente,id_rota_alternativa,id_rota_principal,condicao_inicial_dono)
    VALUES ((select currval('objeto_tatico_seq')),'Plano de Navegação 1',true,null,null,1);

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
INSERT INTO plataforma VALUES (1, 'PLAT1', 15, 15, 50);
INSERT INTO plataforma VALUES (2, 'PLATAVIAO', 60, 100, 750);
INSERT INTO plataforma VALUES (3, 'PLATHELICOPTERO', 40, 80, 180);
INSERT INTO plataforma VALUES (4, 'PLATSUBMARINO', 10, 25, 40);
SELECT setval('plataforma_seq', 4);


--Bateria (Plataforma Submarino)

INSERT INTO carga(
            id_carga, cinco_a_oito_nos, mais_de_dez_nos, oito_a_dez_nos, 
            zero_a_cinco_nos)
    VALUES (1, 25, 26, 27, 28);
SELECT setval('carga_seq', 1);

INSERT INTO descarga(
            id_descarga, cinco_a_oito_nos, dez_a_doze_nos, mais_de_doze_nos, 
            oito_a_dez_nos, zero_a_cinco_nos)
    VALUES (1, 25, 29, 28, 
            222, 12);
SELECT setval('descarga_seq', 1);

INSERT INTO bateria(
            id_bateria, duracao, id_taxa_carga, id_taxa_descarga)
    VALUES (1, 25, 1, 1);
SELECT setval('bateria_seq', 1);

-----------------------------------------------

--Plataformas Superficie, Avião, Helicoptero e Submarino
INSERT INTO plataforma_superficie (
            movimento_in_out, num_eixos, num_pas_eixo, id_tipo_propulsao,
            capacidade_deslocamento, id_plataforma, id_taxa_guinada, 
            calado, comprimento_proa, comprimento_popa, largura) 
            VALUES (true, 1, 3, 'MOTOR',
                    1, 1, 1,
                    6, 60, 40, 50);
INSERT INTO plataforma_aviao(
            altitude_maxima, capacidade_armamentos, id_tamanho, taxa_subida_descida, 
            velocidade_minima, id_plataforma, id_taxa_guinada)
    VALUES (30000, 1, 'GRANDE', 300, 
            30, 2, 1);
INSERT INTO plataforma_helicoptero(
            altitude_maxima, capacidade_armamentos, id_tamanho, taxa_subida_descida, 
            id_plataforma, id_taxa_guinada)
    VALUES (10000, 1, 'GRANDE', 100, 
            3, 1);

INSERT INTO plataforma_submarino(
            movimento_in_out, num_eixos, num_pas_eixo, id_tipo_propulsao, 
            capacidade_deslocamento, profundidade_maxima, taxa_mergulho, 
            tipo_submarino, velocidade_max_com_esnorquel, velocidade_max_submerso, 
            id_plataforma, id_bateria, id_taxa_guinada)
    VALUES (true, 2, 4, 'MOTOR', 
            600, 2200, 10, 
            'NUCLEAR', 18, 10, 
            4, 1, 2); 
----------------------------------------------------------------------------------

INSERT INTO modelo_3d(id_modelo_3d, modelo_veiculo_primario, modelo_veiculo_secundario) VALUES (1, 'F46OwnShip', 'F46 - NPCShip');
INSERT INTO modelo_3d(id_modelo_3d, modelo_veiculo_primario, modelo_veiculo_secundario) VALUES (2, '', 'V34 - NPCShip');

INSERT INTO info_veiculo (id_informacao_veiculo, nome, id_plataforma) 
    VALUES (1, 'F46', 1);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (1, 7777777, '710429000', 100);
INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
    VALUES (1, true, false);

INSERT INTO info_veiculo (id_informacao_veiculo, nome, id_plataforma) 
    VALUES (2, 'V34', 1);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (2, 7777777, '710482000', 111);
INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
    VALUES (2, true, false);

INSERT INTO info_veiculo (id_informacao_veiculo, nome, id_plataforma) 
    VALUES (3, 'AVIAO1', 2);
INSERT INTO info_veiculo_aereo (id_informacao_veiculo, autonomia) 
    VALUES (3, '10:00');
INSERT INTO info_veiculo_aviao (id_informacao_veiculo)
    VALUES (3);

INSERT INTO info_veiculo (id_informacao_veiculo, nome, id_plataforma) 
    VALUES (4, 'HELICOPTERO1', 3);
INSERT INTO info_veiculo_aereo (id_informacao_veiculo, autonomia) 
    VALUES (4, '10:00');
INSERT INTO info_veiculo_helicoptero (id_informacao_veiculo)
    VALUES (4);

INSERT INTO info_veiculo (id_informacao_veiculo, nome, id_plataforma) 
    VALUES (5, 'SUBMARINO1', 4);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (5, 1000, 1000, 100);
INSERT INTO info_veiculo_submarino (id_informacao_veiculo)
    VALUES (5);



--Canhao
INSERT INTO arma(id_arma, nome, tempo_reacao,alcance_max, alcance_min, velocidade)    VALUES (1, 'CANHAO1', 10, 3000, 7, 1400);
INSERT INTO canhao(alcance_eficaz_aer, alcance_eficaz_sup, arco_fogo, cadencia_tiro, calibre, polegada, id_arma)
    VALUES (20, 30, 90, 1, 20, false, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (1, 1, 5000);

 --DCN - Naval
INSERT INTO arma(id_arma, nome, tempo_reacao, alcance_max, alcance_min, velocidade)     VALUES (2, 'Creusot-Loire Mod. 1968', 30, 19140, 100, 1946);

INSERT INTO canhao(alcance_eficaz_aer, alcance_eficaz_sup, arco_fogo, cadencia_tiro, calibre, polegada, id_arma)
    VALUES (20, 30, 280, 1, 20, false, 2);

--         --Denel
-- INSERT INTO arma(id_arma, nome, tempo_reacao, alcance_max, alcance_min, velocidade)     VALUES (4, '35mm 35DPG', 15,  6562, 20,  1175);
-- 
--     INSERT INTO canhao(alcance_eficaz_aer, alcance_eficaz_sup, arco_fogo, cadencia_tiro, calibre, polegada, id_arma)
--         VALUES (20, 30, 45, 1, 35, false, 4);
-- 
--         --FMC-United Defense / BAE Systems
-- INSERT INTO arma(id_arma, nome, tempo_reacao,  alcance_max, alcance_min, velocidade)     VALUES (5, 'FMC 127mm L/56 Mk.45 Mod2', 20,  14218, 70,  2292);
-- INSERT INTO arma(id_arma, nome, tempo_reacao, alcance_max, alcance_min, velocidade)     VALUES (6, 'FMC 76mm Mk.75', 30, 20111, 80,  2800);
-- 
--     INSERT INTO canhao(alcance_eficaz_aer, alcance_eficaz_sup, arco_fogo, cadencia_tiro, calibre, polegada, id_arma)
--         VALUES (20, 30, 280, 1, 127, false, 5);
--     INSERT INTO canhao(alcance_eficaz_aer, alcance_eficaz_sup, arco_fogo, cadencia_tiro, calibre, polegada, id_arma)
--         VALUES (20, 30, 180, 1, 76, false, 6);


INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (2, 1, 100);
-- INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (5, 1, 100);
-- INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (6, 1, 100);
-- 
-- INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (3, 2, 100);
-- INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (4, 2, 100);
-- INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (5, 2, 100);
-- INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (6, 2, 100);

SELECT setval('arma_seq', 2);

-- Veiculos
INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (9);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (9, 1, true);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (9);

INSERT INTO configuracao_equipamento VALUES (1, true, false, 4444);
INSERT INTO configuracao_equipamento VALUES (2, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (3, true, false, 4446);
INSERT INTO configuracao_equipamento VALUES (4, true, false, 4447);
INSERT INTO configuracao_equipamento VALUES (5, true, false, 4448);
INSERT INTO configuracao_equipamento VALUES (6, true, false, 4449);
INSERT INTO configuracao_equipamento VALUES (8, true, false, 4451);
INSERT INTO configuracao_equipamento VALUES (9, true, false, 4452);
INSERT INTO configuracao_equipamento VALUES (10, true, false, 4453);
INSERT INTO configuracao_equipamento VALUES (11, true, false, 4454);
INSERT INTO configuracao_equipamento VALUES (12, true, false, 4455);
INSERT INTO configuracao_equipamento VALUES (14, true, false, 4456);
INSERT INTO configuracao_equipamento VALUES (15, true, false, 4457);
INSERT INTO configuracao_equipamento VALUES (16, true, false, 4458);
INSERT INTO configuracao_equipamento VALUES (18, true, false, 31012);
INSERT INTO configuracao_equipamento VALUES (19, true, false, 4459);
INSERT INTO configuracao_equipamento VALUES (20, true, false, 4444);
INSERT INTO configuracao_equipamento VALUES (21, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (22, true, false, 4446);
INSERT INTO configuracao_equipamento VALUES (23, true, false, 4447);
INSERT INTO configuracao_equipamento VALUES (24, true, false, 4448);
INSERT INTO configuracao_equipamento VALUES (25, true, false, 4449);
INSERT INTO configuracao_equipamento VALUES (26, true, false, 4451);
INSERT INTO configuracao_equipamento VALUES (27, true, false, 4452);
INSERT INTO configuracao_equipamento VALUES (28, true, false, 4453);
INSERT INTO configuracao_equipamento VALUES (29, true, false, 4454);
INSERT INTO configuracao_equipamento VALUES (30, true, false, 4455);
INSERT INTO configuracao_equipamento VALUES (31, true, false, 4456);
INSERT INTO configuracao_equipamento VALUES (32, true, false, 4457);
INSERT INTO configuracao_equipamento VALUES (33, true, false, 4458);
INSERT INTO configuracao_equipamento VALUES (34, true, false, 31012);
INSERT INTO configuracao_equipamento VALUES (35, true, false, 4459);
INSERT INTO configuracao_equipamento VALUES (36, true, false, 4459);
INSERT INTO configuracao_equipamento VALUES (37, true, false, 4459);
INSERT INTO configuracao_equipamento VALUES (38, true, false, 4459);
INSERT INTO configuracao_equipamento VALUES (39, true, false, 4459);
INSERT INTO configuracao_equipamento VALUES (40, true, false, 4446);
INSERT INTO configuracao_equipamento VALUES (41, true, false, 4446);
INSERT INTO configuracao_equipamento VALUES (42, true, false, 4447);
INSERT INTO configuracao_equipamento VALUES (43, true, false, 4447);
INSERT INTO configuracao_equipamento VALUES (44, true, false, 4448);
INSERT INTO configuracao_equipamento VALUES (45, true, false, 4448);
INSERT INTO configuracao_equipamento VALUES (46, true, false, 4449);
INSERT INTO configuracao_equipamento VALUES (47, true, false, 4451);
INSERT INTO configuracao_equipamento VALUES (48, true, false, 4451);
INSERT INTO configuracao_equipamento VALUES (49, true, false, 4452);
INSERT INTO configuracao_equipamento VALUES (50, true, false, 4452);
INSERT INTO configuracao_equipamento VALUES (51, true, false, 4453);
INSERT INTO configuracao_equipamento VALUES (52, true, false, 4453);
INSERT INTO configuracao_equipamento VALUES (53, true, false, 4454);
INSERT INTO configuracao_equipamento VALUES (54, true, false, 4454);
INSERT INTO configuracao_equipamento VALUES (55, true, false, 4456);
INSERT INTO configuracao_equipamento VALUES (56, true, false, 4460);
INSERT INTO configuracao_equipamento VALUES (57, true, false, 4461);
INSERT INTO configuracao_equipamento VALUES (58, true, false, 4462);
INSERT INTO configuracao_equipamento VALUES (59, true, false, 4463);
INSERT INTO configuracao_equipamento VALUES (60, true, false, 4464);---------------------------------------
INSERT INTO configuracao_equipamento VALUES (61, true, false, 4465);---------------------------------------
INSERT INTO configuracao_equipamento VALUES (62, true, false, 4466);---------------------------------------
INSERT INTO configuracao_equipamento VALUES (63, true, false, 4467);---------------------------------------
INSERT INTO configuracao_equipamento VALUES (64, true, false, 4468);---------------------------------------
INSERT INTO configuracao_equipamento VALUES (65, true, false, 4469);---------------------------------------
INSERT INTO configuracao_equipamento VALUES (66, true, false, 4470);---------------------------------------
INSERT INTO configuracao_equipamento VALUES (67, true, false, 4471);---------------------------------------
INSERT INTO configuracao_equipamento VALUES (68, true, false, 4472);---------------------------------------
INSERT INTO configuracao_equipamento VALUES (69, true, false, 4473);---------------------------------------

INSERT INTO equipamento(id_sensor, tipo, custo_sensor, nome)
    VALUES (1, 'AIS', NULL, 'AIS');
INSERT INTO equipamento(id_sensor, tipo, custo_sensor, nome)
    VALUES (2, 'ANEMOMETRO', NULL, 'Anemômetro');
INSERT INTO equipamento(id_sensor, tipo, custo_sensor, nome)
    VALUES (3, 'GIRO', NULL, 'Giro');
INSERT INTO equipamento(id_sensor, tipo, custo_sensor, nome)
    VALUES (4, 'GPS', NULL, 'GPS');
INSERT INTO equipamento(id_sensor, tipo, custo_sensor, nome)
    VALUES (5, 'HODOMETRO', NULL, 'Hodômetro');
INSERT INTO equipamento(id_sensor, tipo, custo_sensor, nome)
    VALUES (6, 'LINK_YB', NULL, 'LinkYb');
INSERT INTO equipamento(id_sensor, tipo, custo_sensor, nome)
    VALUES (8, 'RADAR_BUSCA', NULL, 'Radar de busca');
INSERT INTO equipamento(id_sensor, tipo, custo_sensor, nome)
    VALUES (9, 'SONAR_CASCO', NULL, 'Sonar de casco');
INSERT INTO equipamento(id_sensor, tipo, custo_sensor, nome)
    VALUES (10, 'MAGE', NULL, 'MAGE');
INSERT INTO equipamento(id_sensor, tipo, custo_sensor, nome)
    VALUES (11, 'IFF', NULL, 'IFF');
INSERT INTO equipamento(id_sensor, tipo, custo_sensor, nome)
    VALUES (13, 'SONAR_VDS', NULL, 'Sonar VDS');
INSERT INTO equipamento(id_sensor, tipo, custo_sensor, nome)
    VALUES (14, 'DETECCAO_VISUAL', NULL, 'Detecção Visual');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (15, 'WEB_SERVICE', NULL, 'Web Service');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (16, 'NOVO_LINK_YB', NULL, 'Novo LinkYb');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (18, 'ADSB', NULL, 'ADS-B');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (19, 'ALTIMETRO', NULL, 'Altímetro');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (20, 'ECOBATIMETRO', NULL, 'Ecobatímetro');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (21, 'NAVEGACAO_INERCIAL', NULL, 'Navegação Inercial');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (22, 'MAD', NULL, 'MAD');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (23, 'MANOMETRO', NULL, 'Manômetro');
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
    VALUES (34, 'PERISCOPIO', NULL, 'Periscopio');
SELECT setval('sensor_seq', 35);----------------------------------------

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 1, 1);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 2, 2);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 3, 3);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 4, 4);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 5, 5);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 6, 6);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 8, 8);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 9, 9);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 10, 10);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 11, 11);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 14, 14);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 18, 18);

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 1, 20);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 2, 38);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 3, 40);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 4, 42);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 5, 44);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 6, 46);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 8, 47);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 9, 49);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 10, 51);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 11, 53);

-- SUBMARINO
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 2, 39);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 3, 41);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 4, 43);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 5, 45);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 8, 48);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 9, 50);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 10, 52);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 11, 54);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 14, 55);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 20, 56);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 21, 57);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 23, 59);

-- AIS EM TODOS OS VEICULOS SECUNDARIOS PARA FINS DE TESTE DE OPERACAO AUTOMATICA DE EQUIPAMENTO --
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (3, 1, 36);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (4, 1, 37);

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (3, 22, 58);


INSERT INTO configuracao_ais VALUES (100, 150, 1);
INSERT INTO configuracao_ais VALUES (100, 150, 20);
INSERT INTO configuracao_ais VALUES (100, 150, 36);
INSERT INTO configuracao_ais VALUES (100, 150, 37);

INSERT INTO configuracao_anemometro VALUES (10, 2);
INSERT INTO configuracao_anemometro VALUES (10, 38);
INSERT INTO configuracao_anemometro VALUES (10, 39);

INSERT INTO configuracao_giro VALUES (10, true, 3);
INSERT INTO configuracao_giro VALUES (10, true, 40);
INSERT INTO configuracao_giro VALUES (10, true, 41);

INSERT INTO configuracao_gps VALUES (10, 4);
INSERT INTO configuracao_gps VALUES (10, 42);
INSERT INTO configuracao_gps VALUES (10, 43);

INSERT INTO configuracao_hodometro VALUES (10, 5);
INSERT INTO configuracao_hodometro VALUES (10, 44);
INSERT INTO configuracao_hodometro VALUES (10, 45);

INSERT INTO configuracao_linkyb VALUES (300, 600, '127.0.0.1', 2, true, 5008, 5200, 5208, 5000, 6);
INSERT INTO configuracao_linkyb VALUES (300, 600, '127.0.0.1', 2, true, 5008, 5200, 5208, 5000, 46);
    
INSERT INTO configuracao_sonar_casco VALUES (120, 60, 9);
INSERT INTO configuracao_sonar_casco VALUES (120, 60, 49);
INSERT INTO configuracao_sonar_casco VALUES (120, 60, 50);

INSERT INTO configuracao_mage VALUES (0, 60, false, 10);
INSERT INTO configuracao_mage VALUES (0, 60, false, 51);
INSERT INTO configuracao_mage VALUES (0, 60, false, 52);

INSERT INTO configuracao_iff VALUES (21, 20, false, false, 11);
INSERT INTO configuracao_iff VALUES (21, 20, false, false, 53);
INSERT INTO configuracao_iff VALUES (21, 20, false, false, 54);

INSERT INTO configuracao_radar_busca VALUES (30, 60, 12);
INSERT INTO configuracao_radar_busca VALUES (30, 60, 8);
INSERT INTO configuracao_radar_busca VALUES (30, 60, 47);
INSERT INTO configuracao_radar_busca VALUES (30, 60, 48);

INSERT INTO configuracao_dv VALUES (30, 60, 14);
INSERT INTO configuracao_dv VALUES (30, 60, 55);

INSERT INTO configuracao_novolinkyb VALUES (300, 600, '127.0.0.1', 5708, 5808, 16);
INSERT INTO configuracao_adsb VALUES (60,30,18);
INSERT INTO configuracao_altimetro VALUES (10,19);
INSERT INTO configuracao_ecobatimetro VALUES (10,56);
INSERT INTO configuracao_navegacao_inercial VALUES (10,57);

INSERT INTO configuracao_mad (tempo_eliminado, tempo_perdido, id_config_sensor)
    VALUES (60, 30, 58);
INSERT INTO configuracao_manometro VALUES (10,59);

INSERT INTO configuracao_ais_nacional VALUES (1000, 500, 24);--------------------------------------------
INSERT INTO configuracao_grafimar VALUES (1000, 500, 25);--------------------------------------------
INSERT INTO configuracao_lrit VALUES (1000, 500, 26);--------------------------------------------
INSERT INTO configuracao_mssis VALUES (1000, 500, 27);--------------------------------------------
INSERT INTO configuracao_orbcomm_costeiro VALUES (1000, 500, 28);--------------------------------------------
INSERT INTO configuracao_orbcomm_satelital VALUES (1000, 500, 29);--------------------------------------------
INSERT INTO configuracao_preps VALUES (1000, 500, 30);--------------------------------------------
INSERT INTO configuracao_simmap VALUES (1000, 500, 31);--------------------------------------------
INSERT INTO configuracao_simmap VALUES (1000, 500, 32);--------------------------------------------
INSERT INTO configuracao_vrmtc VALUES (1000, 500, 33);--------------------------------------------

INSERT INTO mage VALUES (3, 10);

INSERT INTO radar(
            frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (1750, 255, 250, 50, 8);

INSERT INTO sonar VALUES (15000, 30000, 7, 'ONIDIRECIONAL',9);
INSERT INTO sonar VALUES (30, 30, 8, 'ONIDIRECIONAL',13);


INSERT INTO sonar_casco VALUES (9);

INSERT INTO sonar_prs VALUES (9);

INSERT INTO sonar_vds VALUES (13);


INSERT INTO sensor_ais VALUES (1);
INSERT INTO sensor_anemometro VALUES (2);
INSERT INTO sensor_giro VALUES (3);
INSERT INTO sensor_gps VALUES (4);
INSERT INTO sensor_hodometro VALUES (5);
INSERT INTO sensor_linkyb VALUES (6);
INSERT INTO sensor_iff VALUES (11);
INSERT INTO sensor_adsb VALUES (18);
INSERT INTO sensor_altimetro VALUES (19);
INSERT INTO sensor_ecobatimetro VALUES (20);
INSERT INTO sensor_navegacao_inercial VALUES (21);

INSERT INTO radar_busca VALUES (false, false, 5, 'CIRCULAR', 8);

INSERT INTO sensor_dv VALUES (14);
INSERT INTO sensor_novolinkyb (id_sensor) VALUES (16);

INSERT INTO sensor_mad (id_sensor) VALUES (22);

INSERT INTO sensor_manometro VALUES (23);

INSERT INTO sensor_ais_nacional (id_sensor) VALUES (24);---------------------------------------------------
INSERT INTO sensor_grafimar (id_sensor) VALUES (25);---------------------------------------------------
INSERT INTO sensor_lrit (id_sensor) VALUES (26);---------------------------------------------------
INSERT INTO sensor_mssis (id_sensor) VALUES (27);---------------------------------------------------
INSERT INTO sensor_orbcomm_costeiro (id_sensor) VALUES (28);---------------------------------------------------
INSERT INTO sensor_orbcomm_satelital (id_sensor) VALUES (29);---------------------------------------------------
INSERT INTO sensor_preps (id_sensor) VALUES (30);---------------------------------------------------
INSERT INTO sensor_simmap (id_sensor) VALUES (31);---------------------------------------------------
INSERT INTO sensor_sagbd (id_sensor) VALUES (32);---------------------------------------------------
INSERT INTO sensor_vrmtc (id_sensor) VALUES (33);---------------------------------------------------

INSERT INTO sensor_periscopio VALUES (34);

INSERT INTO faixa_freq_mage VALUES (1,600,250,1,10);
INSERT INTO faixa_freq_mage VALUES (2,18000,601,2,10);
INSERT INTO faixa_freq_mage VALUES (3,25000,18001,3,10);
SELECT setval('faixa_freq_mage_seq', 3);

--CHAFFs------------------------------------------------------------------------------------------------------------------------------------

-- Chaff Superficie --
INSERT INTO chaff_superficie (id_chaff,alcance,duracao_nuvem,tempo_gerar_eco,tipo_chaff,nome) VALUES (1,2.5,600000,5000,'CHARLIE','CHAFF1');
INSERT INTO chaff_superficie (id_chaff,alcance,duracao_nuvem,tempo_gerar_eco,tipo_chaff,nome) VALUES (2,1,420000,2500,'DELTA','CHAFF2');
INSERT INTO chaff_superficie (id_chaff,alcance,duracao_nuvem,tempo_gerar_eco,tipo_chaff,nome) VALUES (3,2.5,600000,5000,'CHARLIE','CHAFF3');
INSERT INTO chaff_superficie (id_chaff,alcance,duracao_nuvem,tempo_gerar_eco,tipo_chaff,nome) VALUES (4,1,420000,2500,'DELTA','CHAFF4');
INSERT INTO chaff_superficie (id_chaff,alcance,duracao_nuvem,tempo_gerar_eco,tipo_chaff,nome) VALUES (5,2.5,600000,5000,'CHARLIE','CHAFF5');
INSERT INTO chaff_superficie (id_chaff,alcance,duracao_nuvem,tempo_gerar_eco,tipo_chaff,nome) VALUES (6,1,420000,2500,'DELTA','CHAFF6');
INSERT INTO chaff_superficie (id_chaff,alcance,duracao_nuvem,tempo_gerar_eco,tipo_chaff,nome) VALUES (7,2.5,600000,5000,'CHARLIE','CHAFF7');
INSERT INTO chaff_superficie (id_chaff,alcance,duracao_nuvem,tempo_gerar_eco,tipo_chaff,nome) VALUES (8,1,420000,2500,'DELTA','CHAFF8');

-- Chaff Aeronave --
INSERT INTO chaff_aeronave (id_chaff,alcance,duracao_nuvem,tempo_gerar_eco,nome) VALUES (9,1,220000,500,'CHAFF1');
INSERT INTO chaff_aeronave (id_chaff,alcance,duracao_nuvem,tempo_gerar_eco,nome) VALUES (10,1,420000,1000,'CHAFF2');
INSERT INTO chaff_aeronave (id_chaff,alcance,duracao_nuvem,tempo_gerar_eco,nome) VALUES (11,1,520000,1500,'CHAFF3');
INSERT INTO chaff_aeronave (id_chaff,alcance,duracao_nuvem,tempo_gerar_eco,nome) VALUES (12,1,620000,2500,'CHAFF4');
SELECT setval('chaff_seq', 12);

-- Associação Chaff Sup com Informação Veículo Superfície --
INSERT INTO info_veiculo_chaff_superficie (id_chaff,id_informacao_veiculo,numero_lancador,quantidade) VALUES(1,1,1,2);
INSERT INTO info_veiculo_chaff_superficie (id_chaff,id_informacao_veiculo,numero_lancador,quantidade) VALUES(2,1,2,4);
INSERT INTO info_veiculo_chaff_superficie (id_chaff,id_informacao_veiculo,numero_lancador,quantidade) VALUES(5,2,3,10);
INSERT INTO info_veiculo_chaff_superficie (id_chaff,id_informacao_veiculo,numero_lancador,quantidade) VALUES(6,2,4,12);

--------------------------------------------------------------------------------------------------------------------------------------------

--MAE--
INSERT INTO mae (id_mae,distancia_limite,frequencia_minima,frequencia_maxima,nome) VALUES (1, 2, 250, 350, 'MAE1');
INSERT INTO mae (id_mae,distancia_limite,frequencia_minima,frequencia_maxima,nome) VALUES (2, 2, 350, 450, 'MAE2');
INSERT INTO mae (id_mae,distancia_limite,frequencia_minima,frequencia_maxima,nome) VALUES (3, 2, 450, 550, 'MAE3');
SELECT setval('mae_seq', 3);

--Associacao MAE com Informacao Veiculo Superficie
INSERT INTO info_veiculo_superficie_mae(id_mae, id_informacao_veiculo) VALUES (1,1);
INSERT INTO info_veiculo_superficie_mae(id_mae, id_informacao_veiculo) VALUES (2,2);

-- Fonia INÍCIO -------------------------------------------------------------------
INSERT INTO configuracao_fonia (id_configuracao_fonia, nome, configuracao_padrao) VALUES(1, 'Configuracao Padrao', true);
SELECT setval('configuracao_fonia_seq', 1);


-- Cubículo Instrutor
-- INSERT INTO linha_interna (id_linha_interna, cubiculo, instrutor, id_maquina) VALUES (1, 4, false, 7);
-- INSERT INTO linha_interna (id_linha_interna, cubiculo, instrutor, id_maquina) VALUES (2, 4, false, 9);
-- INSERT INTO linha_interna (id_linha_interna, cubiculo, instrutor, id_maquina) VALUES (3, 4, false, 15);
-- INSERT INTO linha_interna (id_linha_interna, cubiculo, instrutor, id_maquina) VALUES (4, 4, false, 14);

INSERT INTO linha_externa (id_linha_externa, nome) VALUES (1, '100A');
INSERT INTO linha_externa (id_linha_externa, nome) VALUES (2, '10A');
INSERT INTO linha_externa (id_linha_externa, nome) VALUES (3, '12A');
INSERT INTO linha_externa (id_linha_externa, nome) VALUES (4, '13A');
INSERT INTO linha_externa (id_linha_externa, nome) VALUES (5, '13F');
INSERT INTO linha_externa (id_linha_externa, nome) VALUES (6, '17A');
INSERT INTO linha_externa (id_linha_externa, nome) VALUES (7, '17C');
INSERT INTO linha_externa (id_linha_externa, nome) VALUES (8, '18A');
INSERT INTO linha_externa (id_linha_externa, nome) VALUES (9, '18B');
INSERT INTO linha_externa (id_linha_externa, nome) VALUES (10,'VHF 14');
INSERT INTO linha_externa (id_linha_externa, nome) VALUES (11, 'VHF 16');
INSERT INTO linha_externa (id_linha_externa, nome) VALUES (12, 'M3');
SELECT setval('linha_externa_seq', 12);

-- Teste para a fonia do SIMNAVE --
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (1, '01', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (2, '02', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (3, '03', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (4, '04', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (5, '06', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (6, '07', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (7, '08', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (8, '09', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (9, '10', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (10, '11', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (11, '12', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (12, '13', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (13, '14', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (14, '15', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (15, '16', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (16, '17', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (17, '18', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (18, '19', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (19, '20', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (20, '21', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (21, '22', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (22, '23', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (23, '24', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (24, '25', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (25, '26', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (26, '27', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (27, '28', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (28, '60', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (29, '61', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (30, '62', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (31, '63', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (32, '64', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (33, '65', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (34, '66', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (35, '67', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (36, '68', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (37, '69', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (38, '71', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (39, '72', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (40, '73', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (41, '74', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (42, '77', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (43, '78', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (44, '79', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (45, '80', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (46, '81', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (47, '82', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (48, '83', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (49, '84', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (50, '85', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (51, '86', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (52, '87', 'VHF_HALF');
INSERT INTO linha_vhf (id_linha_vhf, nome, tipo_vhf) VALUES (53, '88', 'VHF_HALF');
SELECT setval('linha_vhf_seq', 53);

INSERT INTO configuracao_fonia_linha_externa (configuracoesfonia_id_configuracao_fonia, linhasexternas_id_linha_externa) VALUES (1, 1);
INSERT INTO configuracao_fonia_linha_externa (configuracoesfonia_id_configuracao_fonia, linhasexternas_id_linha_externa) VALUES (1, 2);
INSERT INTO configuracao_fonia_linha_externa (configuracoesfonia_id_configuracao_fonia, linhasexternas_id_linha_externa) VALUES (1, 3);
INSERT INTO configuracao_fonia_linha_externa (configuracoesfonia_id_configuracao_fonia, linhasexternas_id_linha_externa) VALUES (1, 4);
INSERT INTO configuracao_fonia_linha_externa (configuracoesfonia_id_configuracao_fonia, linhasexternas_id_linha_externa) VALUES (1, 5);
INSERT INTO configuracao_fonia_linha_externa (configuracoesfonia_id_configuracao_fonia, linhasexternas_id_linha_externa) VALUES (1, 6);
INSERT INTO configuracao_fonia_linha_externa (configuracoesfonia_id_configuracao_fonia, linhasexternas_id_linha_externa) VALUES (1, 7);
INSERT INTO configuracao_fonia_linha_externa (configuracoesfonia_id_configuracao_fonia, linhasexternas_id_linha_externa) VALUES (1, 8);
INSERT INTO configuracao_fonia_linha_externa (configuracoesfonia_id_configuracao_fonia, linhasexternas_id_linha_externa) VALUES (1, 9);
INSERT INTO configuracao_fonia_linha_externa (configuracoesfonia_id_configuracao_fonia, linhasexternas_id_linha_externa) VALUES (1, 10);
INSERT INTO configuracao_fonia_linha_externa (configuracoesfonia_id_configuracao_fonia, linhasexternas_id_linha_externa) VALUES (1, 11);
INSERT INTO configuracao_fonia_linha_externa (configuracoesfonia_id_configuracao_fonia, linhasexternas_id_linha_externa) VALUES (1, 12);

-- INSERT INTO configuracao_fonia_linha_interna (configuracoesfonia_id_configuracao_fonia, linhasinternas_id_linha_interna) VALUES (1, 1);
-- INSERT INTO configuracao_fonia_linha_interna (configuracoesfonia_id_configuracao_fonia, linhasinternas_id_linha_interna) VALUES (1, 2);
-- INSERT INTO configuracao_fonia_linha_interna (configuracoesfonia_id_configuracao_fonia, linhasinternas_id_linha_interna) VALUES (1, 3);
-- INSERT INTO configuracao_fonia_linha_interna (configuracoesfonia_id_configuracao_fonia, linhasinternas_id_linha_interna) VALUES (1, 4);

-- -- Fonia do SIMNAV
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 1);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 2);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 3);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 4);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 5);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 6);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 7);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 8);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 9);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 10);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 11);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 12);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 13);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 14);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 15);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 16);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 17);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 18);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 19);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 20);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 21);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 22);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 23);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 24);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 25);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 26);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 27);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 28);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 29);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 30);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 31);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 32);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 33);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 34);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 35);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 36);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 37);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 38);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 39);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 40);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 41);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 42);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 43);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 44);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 45);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 46);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 47);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 48);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 49);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 50);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 51);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 52);
INSERT INTO configuracao_fonia_linha_vhf (configuracoesfonia_id_configuracao_fonia, linhasvhf_id_linha_vhf) VALUES (1, 53);

-- Fonia FIM -------------------------------------------------------------------


-- DAV --
-- INSERT INTO dav (id_dav,altitude_profundidade,bordo,distancia,marcacao,nome,velocidade_manobra,verdadeira_relativa)
--                         values (1,0,'Boreste',0.5,50,'PERSEGUIR 0.5', 20,true);

-- 
-- INSERT INTO dav_perseguir_alvo (altitude_profundidade_final,rumo_fuga,velocidade_fuga,id_dav) 
--                         values (0,0,35,1);

-- DAV CIRCULAR --
-- INSERT INTO dav (id_dav,altitude_profundidade,bordo,distancia,marcacao,nome,velocidade_manobra,verdadeira_relativa)
--                         values (4,0,'Boreste',0.5,50,'Circular 5', 50,true);

-- 
-- INSERT INTO dav_circular (raio_circulo, id_dav) 
--                         values (1,4);

-- DAV ZIG ZAG --
-- INSERT INTO dav (id_dav,altitude_profundidade,bordo,distancia,marcacao,nome,velocidade_manobra,verdadeira_relativa)
--                         values (7,0,'Boreste',0.5,50,'ZIG ZAG 60', 15,true);

-- 
-- INSERT INTO dav_zig_zag (delta, rumo_base, id_dav) 
--                         values (60,180,7);

-- OPERACAO AUTOMATICA DE SENSORES --
-- insert into dav_op_auto_equip (id_op_auto_equip,distancia_perfil,tempo_desligado,tempo_ligado,id_dav,id_equip_associado) 
--                         values(1,1500,60000,50000,1,1);

-- OPERACAO AUTOMATICA DE ARMAS (DAV PERSEGUIR ALVO) --
-- INSERT INTO dav_op_auto_arma(id_op_auto_arma, distancia_perfil, id_arma_associada, intervalo_tempo_disparo, rajada, id_dav)
--     VALUES (1,1500,1,30,5,1);

-- ROTA --

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES ((select nextval('objeto_tatico_seq')));
INSERT INTO rota (id_objeto_tatico, destino, nome, origem, status) 
    VALUES ((SELECT currval('objeto_tatico_seq')),'destino','ROTA X', 'origem', 'N');
INSERT INTO rota_condicao_inicial (id_rota, id_condicao_inicial)
    VALUES ((SELECT currval('objeto_tatico_seq')), 1);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES ((select nextval('objeto_tatico_seq')));
INSERT INTO waypoint (id_objeto_tatico, fuso_horario, hora_chegada, hora_partida, latitude,longitude, nome, tempo_permanencia) 
    VALUES ((SELECT currval('objeto_tatico_seq')),0,0,0,-22.8419669722332,-43.1461867169056,'wp1-1',0);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES ((select nextval('objeto_tatico_seq')));
INSERT INTO waypoint (id_objeto_tatico, fuso_horario, hora_chegada, hora_partida, latitude,longitude, nome, tempo_permanencia) 
    VALUES ((SELECT currval('objeto_tatico_seq')),0,0,0,-22.8730687434074,-43.1547023866036,'wp1-2',0);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES ((select nextval('objeto_tatico_seq')));
INSERT INTO waypoint (id_objeto_tatico, fuso_horario, hora_chegada, hora_partida, latitude,longitude, nome, tempo_permanencia) 
    VALUES ((SELECT currval('objeto_tatico_seq')),0,0,0,-22.9118242054654,-43.1476472554861,'wp1-3',0);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES ((select nextval('objeto_tatico_seq')));
INSERT INTO waypoint (id_objeto_tatico, fuso_horario, hora_chegada, hora_partida, latitude,longitude, nome, tempo_permanencia) 
    VALUES ((SELECT currval('objeto_tatico_seq')),0,0,0,-23.0033194373043,-43.117668420931,'wp1-4',0);

insert into pernada (id_pernada, xtd_bombordo, xtd_boreste, distancia_guinar, numero_pernada,tipo_pernada, velocidade, id_rota, id_waypoint)
                        values (1,250,250,0,1,'RHUMB_LINE',15,(select max(id_objeto_tatico) from rota), 
                        (select min(id_objeto_tatico) from waypoint));
insert into pernada (id_pernada, xtd_bombordo, xtd_boreste, distancia_guinar, numero_pernada,tipo_pernada, velocidade, id_rota, id_waypoint)
                        values (2,250,250,0,2,'RHUMB_LINE',15,(select max(id_objeto_tatico) from rota), 
                        (select min(id_objeto_tatico) + 1 from waypoint));
insert into pernada (id_pernada, xtd_bombordo, xtd_boreste, distancia_guinar, numero_pernada,tipo_pernada, velocidade, id_rota, id_waypoint)
                        values (3,250,250,0,3,'RHUMB_LINE',15,(select max(id_objeto_tatico) from rota), 
                        (select min(id_objeto_tatico) + 2 from waypoint));
insert into pernada (id_pernada, xtd_bombordo, xtd_boreste, distancia_guinar, numero_pernada,tipo_pernada, velocidade, id_rota, id_waypoint)
                        values (4,250,250,0,4,'RHUMB_LINE',15,(select max(id_objeto_tatico) from rota), 
                        (select min(id_objeto_tatico) + 3 from waypoint));

-- INSERT INTO dav (id_dav,altitude_profundidade,bordo,distancia,marcacao,nome,velocidade_manobra,verdadeira_relativa)
--                         values (10,0,'Boreste',0.5,50,'DAV ROTA X', 20,true);
-- 
-- INSERT INTO dav_rota (id_dav)
--                         values (10);

insert into curva_giro (
id_curva_giro, angulo_leme, velocidade, id_plataforma)
values (1, 15, 15, 1);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (1, 15, 185, 40, 1);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (2, 15, 185, 40, 1);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (3, 30, 275, 85, 1);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (4, 45, 345, 115, 1);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (5, 60, 390, 190, 1);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (6, 75, 445, 270, 1);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (7, 90, 500, 375, 1);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (8, 105, 450, 445, 1);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (9, 120, 405, 520, 1);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (10, 135, 360, 590, 1);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (11, 150, 315, 655, 1);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (12, 165, 265, 725, 1);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (13, 180, 205, 800, 1);



insert into curva_giro (
id_curva_giro, angulo_leme, velocidade, id_plataforma)
values (2, 15, 12, null);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (14, 15, 220, 15, 2);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (15, 30, 320, 25, 2);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (16, 45, 400, 60, 2);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (17, 60, 460, 140, 2);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (18, 75, 500, 240, 2);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (19, 90, 520, 300, 2);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (20, 105, 510, 395, 2);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (21, 120, 380, 600, 2);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (22, 150, 380, 600, 2);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (23, 180, 220, 660, 2);



insert into curva_giro (
id_curva_giro, angulo_leme, velocidade, id_plataforma)
values (3, 25, 12, null);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (24, 15, 200, 10, 3);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (25, 30, 260, 23, 3);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (26, 45, 320, 40, 3);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (27, 60, 360, 100, 3);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (28, 75, 380, 140, 3);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (29, 90, 400, 200, 3);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (31, 105, 395, 260, 3);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (32, 120, 380, 300, 3);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (33, 150, 320, 400, 3);

insert into dados_taticos(
id_dados_taticos, angulo_guinada, avanco, afastamento, id_curva_giro)
values (34, 180, 200, 450, 3);

-- INSERT INTO hidra.camera_fixa(
--             id_objeto_tatico, controle_direcional, ip, latitude, longitude, 
--             nome, senha, usuario)
--     VALUES ((select nextval('objeto_tatico_seq')), true, '10.7.40.205:81',-22.50000, -43.10000, 
-- 'Camera Genérica', 'cisne1234', 'cisne');

INSERT INTO condicao_entidade(altitude, latitude,longitude,na,rumo,velocidade,id_objeto_tatico,
            detect_mage, detect_radar, detect_sonar, detect_mad, detect_iff, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES (0, -22.862810980383113, -43.15362489151045, '1212', 0, 0, 9, 'S', 'S', 'S', 'S', 'S', true, 'A', 1);
