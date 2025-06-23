-- Remove os valores padrões inseridos no defaul.sql
DELETE FROM usuario_maquina WHERE id_maquina=1;
DELETE FROM maquina WHERE id_maquina=1;



INSERT INTO maquina (id_maquina,hostname,mac_address, titulo) VALUES (1, 'SIMNAV-100','','INST');

INSERT INTO maquina (id_maquina,hostname,mac_address, titulo) VALUES (2, 'SIMNAV-102','','SIMNAV1');

SELECT setval('maquina_seq', 3);


INSERT INTO usuario_maquina(id_maquina, id_usuario, login_automatico) VALUES (1, 3, true);
INSERT INTO usuario_maquina(id_maquina, id_usuario, login_automatico) VALUES (1, 2, false);
INSERT INTO usuario_maquina(id_maquina, id_usuario, login_automatico) VALUES (1, 4, false);
INSERT INTO usuario_maquina(id_maquina, id_usuario, login_automatico) VALUES (2, 1, false);


INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES ((select nextval('objeto_tatico_seq')));
INSERT INTO plano_navegacao (id_objeto_tatico,descricao,plano_corrente,id_rota_alternativa,id_rota_principal)
    VALUES ((select currval('objeto_tatico_seq')),'Plano de Navegação 1',true,null,null);

INSERT INTO dados_ambientais (id_dados_ambientais, declinacao_magnetica, direcao_corrente, direcao_vento, estado_mar,
hemisferio, mdr1, mdr2, nuvens, topo_nuvens, profundidade_camada, profundidade_local, velocidade_corrente,
velocidade_vento, visibilidade, chuva, chance_raios) VALUES (1, 12, 20, 30, 2, 'E', 90, 120, 1000, 1500, 250, 1000, 1, 5, 12, 5, 20);
SELECT setval('dados_ambientais_seq', 1);

INSERT INTO teatro_operacao (id_teatro_operacao,hora_inicio,latitude,longitude,nome,id_dados_ambientais,descricao) VALUES (2, 1420106400444, -22.8633999999999986, -43.1751999999999967, 'EXERCICIO1', 1,'Exercício de teste');
SELECT setval('teatro_operacao_seq', 2);

INSERT INTO condicao_inicial(id_condicao_inicial, id_teatro_operacao,identificador) VALUES (1, 2,'Cubiculo 1');
INSERT INTO condicao_inicial(id_condicao_inicial, id_teatro_operacao,identificador) VALUES (2, 2,'Cubiculo Instrutor');
SELECT setval('condicao_inicial_seq', 3);

INSERT INTO condicao_veiculo(detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial,altitude, latitude,longitude,na,rumo,velocidade,id_objeto_tatico,)
    VALUES ('S', 'S', true, 'A', 1,0, -22.8233999999999986, -43.1551999999999967, '3322', 0, 0, 9);

INSERT INTO condicao_instrutor (id_condicao_inicial) VALUES (2);

INSERT INTO bloco VALUES (1, '25', 1);
INSERT INTO bloco VALUES (2, '26', 1);
INSERT INTO bloco VALUES (3, '27', 1);

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

INSERT INTO mae (id_mae,distancia_limite,frequencia_minima,frequencia_maxima,nome) VALUES (1, 0.5, 250, 350, 'mae1');
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
            capacidade_deslocamento, tipo_navio, vms, id_plataforma, id_taxa_guinada, 
            calado, comprimento_proa, comprimento_popa, largura) 
            VALUES (true, 1, 3, 'MOTOR',
                    1, 'FRG', 20, 1, 1,
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
            1, 2200, 10, 
            'NUCLEAR', 18, 10, 
            4, 1, 2); 
----------------------------------------------------------------------------------

INSERT INTO modelo_3d(id_modelo_3d, modelo_veiculo_primario, modelo_veiculo_secundario) VALUES (1, 'F46OwnShip', 'F46 - NPCShip');
INSERT INTO modelo_3d(id_modelo_3d, modelo_veiculo_primario, modelo_veiculo_secundario) VALUES (2, 'F46OwnShip', 'V34 - NPCShip');

INSERT INTO info_veiculo (id_informacao_veiculo, nome, id_modelo_3d, id_plataforma) 
    VALUES (1, 'F46', 1, 1);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (1, 0, '710429000', 100);
INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
    VALUES (1, true, false);

INSERT INTO info_veiculo (id_informacao_veiculo, nome, id_modelo_3d, id_plataforma) 
    VALUES (2, 'V34', 2, 1);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (2, 0, '710482000', 111);
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
INSERT INTO info_veiculo_submarino (id_informacao_veiculo, esnorquel, periscopio)
    VALUES (5, true, true);

-- --Bomba
-- INSERT INTO arma(id_arma, nome, tempo_reacao)    VALUES (1, 'BOMBA1', 30);
-- INSERT INTO bomba(altitude_min, peso, id_arma)   VALUES (100, 10, 1);
-- INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (1, 1, 100);

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

INSERT INTO usuario_maquina_condicao (id_condicao,id_maquina,id_usuario) VALUES (1, 2, 1);

--Veiculos
INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (9);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (9, 1, true);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (9);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (10);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (10, 2, true);
INSERT INTO veiculo_aviao (id_objeto_tatico) 
    VALUES (10);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (11);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (11, 3, true);
INSERT INTO veiculo_aviao (id_objeto_tatico) 
    VALUES (11);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (12);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (12, 4, true);
INSERT INTO veiculo_helicoptero (id_objeto_tatico) 
    VALUES (12);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (13);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (13, 5, true);
INSERT INTO veiculo_submarino (id_objeto_tatico) 
    VALUES (13);

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
INSERT INTO configuracao_equipamento VALUES (20, true, false, 4460);


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
    VALUES (17, 'BOIA', NULL, 'Bóia');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (18, 'ADSB', NULL, 'ADS-B');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (19, 'ALTIMETRO', NULL, 'Altímetro');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (20, 'ECOBATIMETRO', NULL, 'Ecobatímetro');


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
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 15, 15);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 16, 16);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 18, 18);

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 1, 1);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 2, 2);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 3, 3);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 4, 4);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 5, 5);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 6, 6);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 8, 8);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 9, 9);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 10, 10);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 11, 11);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 15, 15);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 16, 16);

-- SUBMARINO
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 2, 2);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 3, 3);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 4, 4);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 5, 5);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 8, 8);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 9, 9);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 10, 10);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 11, 11);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 14, 14);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 20, 20);


-- AIS EM TODOS OS VEICULOS SECUNDARIOS PARA FINS DE TESTE DE OPERACAO AUTOMATICA DE EQUIPAMENTO --
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (3, 1, 1);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (4, 1, 1);


INSERT INTO configuracao_ais VALUES (100, 150, 1);
INSERT INTO configuracao_anemometro VALUES (10, 2);
INSERT INTO configuracao_giro VALUES (10, true, 3);
INSERT INTO configuracao_gps VALUES (10, 4);
INSERT INTO configuracao_hodometro VALUES (10, 5);
INSERT INTO configuracao_linkyb VALUES (300, 600, '127.0.0.1', 2, true, 5008, 5200, 5208, 5000, 6);
INSERT INTO configuracao_radar_busca(tempo_eliminado, tempo_perdido, id_config_sensor)
    VALUES (30, 60, 8);
INSERT INTO configuracao_sonar_casco VALUES (120, 60, 9);
INSERT INTO configuracao_mage VALUES (0, 60, 10);
INSERT INTO configuracao_iff VALUES (21, 20, false, false, 11);
INSERT INTO configuracao_radar_busca VALUES (30, 60, 12);
INSERT INTO configuracao_dv VALUES (30, 60, 14);
INSERT INTO configuracao_web_service VALUES (1000, 500, 15);
INSERT INTO configuracao_novolinkyb VALUES (300, 600, '127.0.0.1', 5708, 5808, 16);
INSERT INTO configuracao_adsb VALUES (60,30,18);
INSERT INTO configuracao_altimetro VALUES (10,19);
INSERT INTO configuracao_ecobatimetro VALUES (10,20);

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

INSERT INTO radar_busca VALUES (false, false, false, 5, 359, 'CIRCULAR', 8);

INSERT INTO sensor_dv VALUES (14);
INSERT INTO sensor_web_service VALUES (15);
INSERT INTO sensor_novolinkyb (id_sensor) VALUES (16);

INSERT INTO faixa_freq_mage VALUES (1,600,250,1,10);
INSERT INTO faixa_freq_mage VALUES (2,18000,601,2,10);
INSERT INTO faixa_freq_mage VALUES (3,25000,18001,3,10);
SELECT setval('faixa_freq_mage_seq', 3);

--LançadorChaff
INSERT INTO lancador_chaff (id_lancador_chaff,nome,numero_lancador)
VALUES (1,'Lançador 1',1);
INSERT INTO lancador_chaff (id_lancador_chaff,nome,numero_lancador)
VALUES (2,'Lançador 2',2);
INSERT INTO lancador_chaff (id_lancador_chaff,nome,numero_lancador)
VALUES (3,'Lançador 3',3);
INSERT INTO lancador_chaff (id_lancador_chaff,nome,numero_lancador)
VALUES (4,'Lançador 4',4);

INSERT INTO info_veiculo_sup_lancador_chaff (id_lancador_chaff,id_informacao_veiculo) VALUES(1,1);
INSERT INTO info_veiculo_sup_lancador_chaff (id_lancador_chaff,id_informacao_veiculo) VALUES(2,1);
INSERT INTO info_veiculo_sup_lancador_chaff (id_lancador_chaff,id_informacao_veiculo) VALUES(3,1);
INSERT INTO info_veiculo_sup_lancador_chaff (id_lancador_chaff,id_informacao_veiculo) VALUES(4,1);

-- Chaff Charlie lançador1
INSERT INTO chaff_superficie (id_chaff,alcance,duracao_nuvem,marc_relativa,tempo_gerar_eco,tipo_chaff)
VALUES (1,2.5,600000,30,5000,'CHARLIE');
-- Chaff Delta lançador1
INSERT INTO chaff_superficie (id_chaff,alcance,duracao_nuvem,marc_relativa,tempo_gerar_eco,tipo_chaff)
VALUES (2,1,420000,30,5000,'DELTA');
-- Chaff Charlie lançador2
INSERT INTO chaff_superficie (id_chaff,alcance,duracao_nuvem,marc_relativa,tempo_gerar_eco,tipo_chaff)
VALUES (3,2.5,600000,330,5000,'CHARLIE');
-- Chaff Delta lançador2
INSERT INTO chaff_superficie (id_chaff,alcance,duracao_nuvem,marc_relativa,tempo_gerar_eco,tipo_chaff)
VALUES (4,1,420000,330,5000,'DELTA');
-- Chaff Charlie lançador3
INSERT INTO chaff_superficie (id_chaff,alcance,duracao_nuvem,marc_relativa,tempo_gerar_eco,tipo_chaff)
VALUES (5,2.5,600000,110,5000,'CHARLIE');
-- Chaff Delta lançador3
INSERT INTO chaff_superficie (id_chaff,alcance,duracao_nuvem,marc_relativa,tempo_gerar_eco,tipo_chaff)
VALUES (6,1,420000,110,5000,'DELTA');
-- Chaff Charlie lançador4
INSERT INTO chaff_superficie (id_chaff,alcance,duracao_nuvem,marc_relativa,tempo_gerar_eco,tipo_chaff)
VALUES (7,2.5,600000,250,5000,'CHARLIE');
-- Chaff Delta lançador4
INSERT INTO chaff_superficie (id_chaff,alcance,duracao_nuvem,marc_relativa,tempo_gerar_eco,tipo_chaff)
VALUES (8,1,420000,250,5000,'DELTA');

INSERT INTO info_veiculo_chaff_superficie (id_chaff,id_informacao_veiculo,numero_lancador,quantidade) VALUES(1,1,2,4);
INSERT INTO info_veiculo_chaff_superficie (id_chaff,id_informacao_veiculo,numero_lancador,quantidade) VALUES(2,1,2,4);
INSERT INTO info_veiculo_chaff_superficie (id_chaff,id_informacao_veiculo,numero_lancador,quantidade) VALUES(3,1,2,4);
INSERT INTO info_veiculo_chaff_superficie (id_chaff,id_informacao_veiculo,numero_lancador,quantidade) VALUES(4,1,2,4);
INSERT INTO info_veiculo_chaff_superficie (id_chaff,id_informacao_veiculo,numero_lancador,quantidade) VALUES(5,1,2,4);
INSERT INTO info_veiculo_chaff_superficie (id_chaff,id_informacao_veiculo,numero_lancador,quantidade) VALUES(6,1,2,4);
INSERT INTO info_veiculo_chaff_superficie (id_chaff,id_informacao_veiculo,numero_lancador,quantidade) VALUES(7,1,2,4);
INSERT INTO info_veiculo_chaff_superficie (id_chaff,id_informacao_veiculo,numero_lancador,quantidade) VALUES(8,1,2,4);

-- Fonia INÍCIO -------------------------------------------------------------------
INSERT INTO configuracao_fonia (id_configuracao_fonia, nome, configuracao_padrao) VALUES(1, 'Configuracao Padrao', true);

-- -- Teste para a fonia do SIMNAVE
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

-- -- Fonia do SIMNAVE
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
INSERT INTO dav (id_dav,altitude_profundidade,bordo,distancia,marcacao,nome,velocidade_manobra,verdadeira_relativa)
                        values (1,0,'Boreste',0.5,50,'PERSEGUIR 0.5', 20,true);
INSERT INTO dav (id_dav,altitude_profundidade,bordo,distancia,marcacao,nome,velocidade_manobra,verdadeira_relativa)
                        values (2,0,'Bombordo',1.0,100,'PERSEGUIR 1.0', 25,true);
INSERT INTO dav (id_dav,altitude_profundidade,bordo,distancia,marcacao,nome,velocidade_manobra,verdadeira_relativa)
                        values (3,0,'Boreste',0.5,150,'PERSEGUIR 1.5', 30,true);

INSERT INTO dav_perseguir_alvo (altitude_profundidade_final,rumo_fuga,velocidade_fuga,id_dav) 
                        values (0,0,35,1);
INSERT INTO dav_perseguir_alvo (altitude_profundidade_final,rumo_fuga,velocidade_fuga,id_dav) 
                        values (0,150,35,2);
INSERT INTO dav_perseguir_alvo (altitude_profundidade_final,rumo_fuga,velocidade_fuga,id_dav) 
                        values (0,45,35,3);

-- DAV CIRCULAR --
INSERT INTO dav (id_dav,altitude_profundidade,bordo,distancia,marcacao,nome,velocidade_manobra,verdadeira_relativa)
                        values (4,0,'Boreste',0.5,50,'Circular 5', 50,true);
INSERT INTO dav (id_dav,altitude_profundidade,bordo,distancia,marcacao,nome,velocidade_manobra,verdadeira_relativa)
                        values (5,0,'Bombordo',1.0,100,'Circular 15', 80,true);
INSERT INTO dav (id_dav,altitude_profundidade,bordo,distancia,marcacao,nome,velocidade_manobra,verdadeira_relativa)
                        values (6,0,'Boreste',0.5,150,'Circular 30', 30,true);

INSERT INTO dav_circular (raio_circulo, id_dav) 
                        values (1,4);
INSERT INTO dav_circular (raio_circulo, id_dav) 
                        values (2,5);
INSERT INTO dav_circular (raio_circulo, id_dav) 
                        values (3,6);

INSERT INTO dav_pernada(id_pernada,angulo_leme,bordo,rumo_relativo,velocidade,id_dav) 
                        values (4,5,'Boreste',0,20,4);
INSERT INTO dav_pernada(id_pernada,angulo_leme,bordo,rumo_relativo,velocidade,id_dav) 
                        values (5,15,'Bombordo',90,30,5);
INSERT INTO dav_pernada(id_pernada,angulo_leme,bordo,rumo_relativo,velocidade,id_dav) 
                        values (6,30,'Boreste',180,35,6);

-- DAV ZIG ZAG --
INSERT INTO dav (id_dav,altitude_profundidade,bordo,distancia,marcacao,nome,velocidade_manobra,verdadeira_relativa)
                        values (7,0,'Boreste',0.5,50,'ZIG ZAG 60', 15,true);
INSERT INTO dav (id_dav,altitude_profundidade,bordo,distancia,marcacao,nome,velocidade_manobra,verdadeira_relativa)
                        values (8,0,'Bombordo',1.0,100,'ZIG ZAG  90', 30,true);
INSERT INTO dav (id_dav,altitude_profundidade,bordo,distancia,marcacao,nome,velocidade_manobra,verdadeira_relativa)
                        values (9,0,'Boreste',0.5,150,'ZIG ZAG  150', 23,true);

INSERT INTO dav_zig_zag (delta, rumo_base, id_dav) 
                        values (60,180,7);
INSERT INTO dav_zig_zag (delta, rumo_base, id_dav) 
                        values (90,100,8);
INSERT INTO dav_zig_zag (delta, rumo_base, id_dav) 
                        values (150,0,9);

INSERT INTO dav_pernada(id_pernada,angulo_leme,bordo,rumo_relativo,velocidade,id_dav) 
                        values (7,25,'Boreste',60,24,7);
INSERT INTO dav_pernada(id_pernada,angulo_leme,bordo,rumo_relativo,velocidade,id_dav) 
                        values (8,30,'Boreste',90,22,8);
INSERT INTO dav_pernada(id_pernada,angulo_leme,bordo,rumo_relativo,velocidade,id_dav) 
                        values (9,40,'Boreste',180,15,9);

-- OPERACAO AUTOMATICA DE SENSORES --
insert into dav_op_auto_equip (id_op_auto_equip,distancia_perfil,tempo_desligado,tempo_ligado,id_dav,id_equip_associado) 
                        values(1,1500,60000,50000,1,1);
insert into dav_op_auto_equip (id_op_auto_equip,distancia_perfil,tempo_desligado,tempo_ligado,id_dav,id_equip_associado) 
                        values(2,2000,30000,30000,2,1);
insert into dav_op_auto_equip (id_op_auto_equip,distancia_perfil,tempo_desligado,tempo_ligado,id_dav,id_equip_associado) 
                        values(3,1000,100000,100000,3,1);

insert into dav_op_auto_equip (id_op_auto_equip,distancia_perfil,tempo_desligado,tempo_ligado,id_dav,id_equip_associado)
                        values(4,10000,60000,50000,4,1);
insert into dav_op_auto_equip (id_op_auto_equip,distancia_perfil,tempo_desligado,tempo_ligado,id_dav,id_equip_associado) 
                        values(5,10000,30000,30000,5,1);
insert into dav_op_auto_equip (id_op_auto_equip,distancia_perfil,tempo_desligado,tempo_ligado,id_dav,id_equip_associado) 
                        values(6,10000,100000,100000,6,1);

insert into dav_op_auto_equip (id_op_auto_equip,distancia_perfil,tempo_desligado,tempo_ligado,id_dav,id_equip_associado) 
                        values(7,1,60000,50000,7,1);
insert into dav_op_auto_equip (id_op_auto_equip,distancia_perfil,tempo_desligado,tempo_ligado,id_dav,id_equip_associado) 
                        values(8,1,30000,30000,8,1);
insert into dav_op_auto_equip (id_op_auto_equip,distancia_perfil,tempo_desligado,tempo_ligado,id_dav,id_equip_associado) 
                        values(9,1,100000,100000,9,1);

-- OPERACAO AUTOMATICA DE ARMAS (DAV PERSEGUIR ALVO) --
INSERT INTO dav_op_auto_arma(id_op_auto_arma, distancia_perfil, id_arma_associada, intervalo_tempo_disparo, rajada, id_dav)
    VALUES (1,1500,1,30,5,1);
INSERT INTO dav_op_auto_arma(id_op_auto_arma, distancia_perfil, id_arma_associada, intervalo_tempo_disparo, rajada, id_dav)
    VALUES (2,2000,1,30,10,2);
INSERT INTO dav_op_auto_arma(id_op_auto_arma, distancia_perfil, id_arma_associada, intervalo_tempo_disparo, rajada, id_dav)
    VALUES (3,8000,2,20,20,3);

-- ROTA --

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES ((select nextval('objeto_tatico_seq')));
INSERT INTO rota (id_objeto_tatico, destino, nome, origem, status, condicao_inicial_dono) 
    VALUES ((select currval('objeto_tatico_seq')),'destino','ROTA X', 'origem', 'N', 1);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES ((select nextval('objeto_tatico_seq')));
INSERT INTO waypoint (id_objeto_tatico, fuso_horario, hora_chegada, hora_partida, latitude,longitude, nome, tempo_permanencia) 
    VALUES ((select currval('objeto_tatico_seq')),0,0,0,-22.8419669722332,-43.1461867169056,'wp1-1',0);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES ((select nextval('objeto_tatico_seq')));
INSERT INTO waypoint (id_objeto_tatico, fuso_horario, hora_chegada, hora_partida, latitude,longitude, nome, tempo_permanencia) 
    VALUES ((select currval('objeto_tatico_seq')),0,0,0,-22.8730687434074,-43.1547023866036,'wp1-2',0);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES ((select nextval('objeto_tatico_seq')));
INSERT INTO waypoint (id_objeto_tatico, fuso_horario, hora_chegada, hora_partida, latitude,longitude, nome, tempo_permanencia) 
    VALUES ((select currval('objeto_tatico_seq')),0,0,0,-22.9118242054654,-43.1476472554861,'wp1-3',0);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES ((select nextval('objeto_tatico_seq')));
INSERT INTO waypoint (id_objeto_tatico, fuso_horario, hora_chegada, hora_partida, latitude,longitude, nome, tempo_permanencia) 
    VALUES ((select currval('objeto_tatico_seq')),0,0,0,-23.0033194373043,-43.117668420931,'wp1-4',0);

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

INSERT INTO dav (id_dav,altitude_profundidade,bordo,distancia,marcacao,nome,velocidade_manobra,verdadeira_relativa)
                        values (10,0,'Boreste',0.5,50,'DAV ROTA X', 20,true);

INSERT INTO dav_rota (id_dav)
                        values (10);

INSERT INTO dav (id_dav,altitude_profundidade,bordo,distancia,marcacao,nome,velocidade_manobra,verdadeira_relativa)
                        values (11,0,'Boreste',0.5,50,'DAV ROTA X SIMNAV TESTE ', 20,true);

INSERT INTO dav_rota_simnav (id_dav)
                        values (11);

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
