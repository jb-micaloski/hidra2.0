-- Remove os valores padrões inseridos no defaul.sql
DELETE FROM usuario_maquina WHERE id_maquina=1;
DELETE FROM maquina WHERE id_maquina=1;

--instrutores
INSERT INTO maquina (id_maquina,hostname,mac_address, titulo) VALUES (1, 'inspeq-030', '', 'INST1');
INSERT INTO maquina (id_maquina,hostname,mac_address, titulo) VALUES (20, 'inspeq-031', '', 'INST2');
INSERT INTO maquina (id_maquina,hostname,mac_address, titulo) VALUES (24, 'inspeq-032', '', 'INST3');

--cubiculo 1
INSERT INTO maquina (id_maquina,hostname,mac_address, titulo) VALUES (2, 'inspeq-001', '', 'COMB');
INSERT INTO maquina (id_maquina,hostname,mac_address, titulo) VALUES (3, 'inspeq-002', '', 'SUPER');
INSERT INTO maquina (id_maquina,hostname,mac_address, titulo) VALUES (4, 'inspeq-003', '', 'CAT1');
INSERT INTO maquina (id_maquina,hostname,mac_address, titulo) VALUES (5, 'inspeq-004', '', 'COC');
INSERT INTO maquina (id_maquina,hostname,mac_address, titulo) VALUES (6, 'inspeq-005', '', 'AS');
INSERT INTO maquina (id_maquina,hostname,mac_address, titulo) VALUES (7, 'inspeq-006', '', 'EOS');

--cubiculo 2
INSERT INTO maquina (id_maquina,hostname,mac_address, titulo) VALUES (8, 'inspeq-007', '', 'COMB');
INSERT INTO maquina (id_maquina,hostname,mac_address, titulo) VALUES (9, 'inspeq-008', '', 'SUPER');
INSERT INTO maquina (id_maquina,hostname,mac_address, titulo) VALUES (10, 'inspeq-009', '', 'CAT1');
INSERT INTO maquina (id_maquina,hostname,mac_address, titulo) VALUES (11, 'inspeq-010', '', 'COC');
INSERT INTO maquina (id_maquina,hostname,mac_address, titulo) VALUES (12, 'inspeq-011', '', 'AS');
INSERT INTO maquina (id_maquina,hostname,mac_address, titulo) VALUES (13, 'inspeq-012', '', 'EOS');

--cubiculo 3
INSERT INTO maquina (id_maquina,hostname,mac_address, titulo) VALUES (14, 'inspeq-013', '', 'COMB');
INSERT INTO maquina (id_maquina,hostname,mac_address, titulo) VALUES (15, 'inspeq-014', '', 'SUPER');
INSERT INTO maquina (id_maquina,hostname,mac_address, titulo) VALUES (16, 'inspeq-015', '', 'CAT1');
INSERT INTO maquina (id_maquina,hostname,mac_address, titulo) VALUES (17, 'inspeq-016', '', 'COC');
INSERT INTO maquina (id_maquina,hostname,mac_address, titulo) VALUES (18, 'inspeq-017', '', 'AS');
INSERT INTO maquina (id_maquina,hostname,mac_address, titulo) VALUES (19, 'inspeq-018', '', 'EOS');

--cubiculo 4
INSERT INTO maquina (id_maquina,hostname,mac_address, titulo) VALUES (21, 'inspeq-019', '', 'COC');
--cubiculo 5
INSERT INTO maquina (id_maquina,hostname,mac_address, titulo) VALUES (22, 'inspeq-020', '', 'COC');
--cubiculo 6
INSERT INTO maquina (id_maquina,hostname,mac_address, titulo) VALUES (23, 'inspeq-021', '', 'COC');
INSERT INTO maquina (id_maquina,hostname,mac_address, titulo) VALUES (25, 'inspeq-249', '', 'MASTER');
SELECT setval('maquina_seq', 25);

INSERT INTO usuario_maquina VALUES (1, 3, true);
INSERT INTO usuario_maquina VALUES (20, 3, false);
INSERT INTO usuario_maquina VALUES (24, 3, false);
INSERT INTO usuario_maquina VALUES (2, 1, false);
INSERT INTO usuario_maquina VALUES (3, 6, false);
INSERT INTO usuario_maquina VALUES (4, 1, false);
INSERT INTO usuario_maquina VALUES (5, 4, false);
INSERT INTO usuario_maquina VALUES (6, 9, false);
INSERT INTO usuario_maquina VALUES (7, 7, false);
INSERT INTO usuario_maquina VALUES (8, 1, false);
INSERT INTO usuario_maquina VALUES (9, 6, false);
INSERT INTO usuario_maquina VALUES (10, 1, false);
INSERT INTO usuario_maquina VALUES (11, 4, false);
INSERT INTO usuario_maquina VALUES (12, 9, false);
INSERT INTO usuario_maquina VALUES (13, 7, false);
INSERT INTO usuario_maquina VALUES (14, 1, false);
INSERT INTO usuario_maquina VALUES (15, 6, false);
INSERT INTO usuario_maquina VALUES (16, 1, false);
INSERT INTO usuario_maquina VALUES (17, 4, false);
INSERT INTO usuario_maquina VALUES (18, 9, false);
INSERT INTO usuario_maquina VALUES (19, 7, false);
INSERT INTO usuario_maquina VALUES (21, 6, false);
INSERT INTO usuario_maquina VALUES (22, 6, false);
INSERT INTO usuario_maquina VALUES (23, 6, false);
INSERT INTO usuario_maquina VALUES (25, 2, false);
INSERT INTO usuario_maquina VALUES (25, 5, false);
INSERT INTO usuario_maquina VALUES (25, 15, true);
INSERT INTO usuario_maquina VALUES (5, 1, false);
INSERT INTO usuario_maquina VALUES (3, 1, false);
INSERT INTO usuario_maquina VALUES (2, 6, false);
INSERT INTO usuario_maquina VALUES (6, 1, false);
INSERT INTO usuario_maquina VALUES (9, 1, false);
INSERT INTO usuario_maquina VALUES (11, 1, false);
INSERT INTO usuario_maquina VALUES (13, 6, false);
INSERT INTO usuario_maquina VALUES (12, 7, false);
INSERT INTO usuario_maquina VALUES (15, 1, false);
INSERT INTO usuario_maquina VALUES (19, 1, false);
INSERT INTO usuario_maquina VALUES (18, 6, false);
INSERT INTO usuario_maquina VALUES (17, 7, false);
INSERT INTO usuario_maquina VALUES (22, 1, false);
INSERT INTO usuario_maquina VALUES (23, 1, false);


INSERT INTO dados_ambientais (id_dados_ambientais, declinacao_magnetica, direcao_corrente, direcao_vento, estado_mar,
hemisferio, mdr1, mdr2, nuvens, topo_nuvens, profundidade_camada, profundidade_local, velocidade_corrente,
velocidade_vento, visibilidade, chuva, chance_raios) VALUES (1, 12, 20, 30, 2, 'E', 90, 120, 1000, 1500, 250, 1000, 1, 5, 12, 0, 0);

INSERT INTO teatro_operacao VALUES (2, 'Exercício de teste', 1420106400880, -23.25, -43.1000000000000014, 'EXERCICIO1', 1, NULL);


INSERT INTO condicao_inicial VALUES (8, 'Bancada Instrutores', 2);
INSERT INTO condicao_inicial VALUES (9, 'Bancada 1', 2);
INSERT INTO condicao_inicial VALUES (10, 'Bancada 2', 2);
INSERT INTO condicao_inicial VALUES (11, 'Bancada 3', 2);
INSERT INTO condicao_inicial VALUES (12, 'Bancada 4', 2);

SELECT setval('condicao_inicial_seq', 12);

INSERT INTO condicao_entidade(altitude, latitude,longitude,na,rumo,velocidade,id_objeto_tatico,
            detect_mage, detect_radar, detect_sonar, detect_mad, detect_iff, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES (0, -23.2500000000000036, -43.0999999999999659, '1111', 0, 0, 57, 'S', 'S', 'S', 'S', 'S', true, 'A', 9);
INSERT INTO condicao_entidade(altitude, latitude,longitude,na,rumo,velocidade,id_objeto_tatico,
            detect_mage, detect_radar, detect_sonar, detect_mad, detect_iff, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES (0, -23.2332772598020441, -43.0999999999999659, '2222', 0, 0, 59, 'S', 'S', 'S',  'S', 'S', true, 'B', 10);
INSERT INTO condicao_entidade(altitude, latitude,longitude,na,rumo,velocidade,id_objeto_tatico,
            detect_mage, detect_radar, detect_sonar, detect_mad, detect_iff, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES (0, -23.2499989574520605, -43.0819021783158433, '3333', 0, 0, 61, 'S', 'S', 'S',  'S', 'S', true, 'A', 11);
INSERT INTO condicao_entidade(altitude, latitude,longitude,na,rumo,velocidade,id_objeto_tatico,
            detect_mage, detect_radar, detect_sonar, detect_mad, detect_iff, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES (10, -23.2667227046096414, -43.0999999999999659, '4444', 0, 0, 63, 'S', 'S', 'S',  'S', 'S', true, 'B', 12);

-- INSERT INTO condicao_entidade VALUES (0, 'D', 'D', true, 'A', -23.2500000000000036, -43.0999999999999659, '1111', 0, 0, 9, 57);
-- INSERT INTO condicao_entidade VALUES (0, 'D', 'D', true, 'B', -23.2332772598020441, -43.0999999999999659, '2222', 0, 0, 10, 59);
-- INSERT INTO condicao_entidade VALUES (0, 'D', 'D', true, 'A', -23.2499989574520605, -43.0819021783158433, '3333', 0, 0, 11, 61);
-- INSERT INTO condicao_entidade VALUES (10, 'D', 'D', true, 'B', -23.2667227046096414, -43.0999999999999659, '4444', 0, 0, 12, 63);

INSERT INTO condicao_instrutor (id_condicao_inicial) VALUES (8);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES ((select nextval('objeto_tatico_seq')));
INSERT INTO plano_navegacao (id_objeto_tatico,descricao,plano_corrente,id_rota_alternativa,id_rota_principal,condicao_inicial_dono)
    VALUES ((select currval('objeto_tatico_seq')),'Plano de Navegação 1',true,null,null,9);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES ((select nextval('objeto_tatico_seq')));
INSERT INTO plano_navegacao (id_objeto_tatico,descricao,plano_corrente,id_rota_alternativa,id_rota_principal,condicao_inicial_dono)
    VALUES ((select currval('objeto_tatico_seq')),'Plano de Navegação 2',true,null,null,10);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES ((select nextval('objeto_tatico_seq')));
INSERT INTO plano_navegacao (id_objeto_tatico,descricao,plano_corrente,id_rota_alternativa,id_rota_principal,condicao_inicial_dono)
            VALUES ((select currval('objeto_tatico_seq')),'Plano de Navegação 3',true,null,null,11);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES ((select nextval('objeto_tatico_seq')));
INSERT INTO plano_navegacao (id_objeto_tatico,descricao,plano_corrente,id_rota_alternativa,id_rota_principal,condicao_inicial_dono)
            VALUES ((select currval('objeto_tatico_seq')),'Plano de Navegação 4',true,null,null,12);

INSERT INTO bloco VALUES (22, '11', 9);
INSERT INTO bloco VALUES (23, '22', 10);
INSERT INTO bloco VALUES (24, '33', 11);
INSERT INTO bloco VALUES (25, '44', 12);
SELECT setval('bloco_seq', 25);


--Taxa de Guinada Plataformas

INSERT INTO guinada_aviao(id_guinada_aviao, mais_de_seiscentos_nos, trezentos_um_a_seiscentos_nos, zero_a_trezentos_nos)    VALUES (1, 300, 360, 360);
SELECT setval('guinada_aviao_seq', 1);

INSERT INTO guinada_helicoptero(id_guinada_helicoptero, mais_de_cem_nos, zero_a_cem_nos)    VALUES (1, 360, 360);
SELECT setval('guinada_helicoptero_seq', 1);

INSERT INTO guinada_superficie (id_guinada_maritima,zero_a_cinco_nos,cinco_a_dez_nos,dez_a_quinze_nos,quinze_a_vinte_nos,vinte_a_vinte_cinco_nos,vinte_cinco_a_trinta_nos,trinta_a_trinta_cinco_nos,mais_de_trinta_cinco_nos)
    VALUES (1, 60, 70, 80, 90, 100, 110, 120, 130);

INSERT INTO guinada_submarino(id_guinada_maritima, cinco_a_dez_nos, dez_a_quinze_nos, quinze_a_vinte_nos, 
            vinte_a_vinte_cinco_nos, vinte_cinco_a_trinta_nos, zero_a_cinco_nos, mais_de_trinta_nos)
    VALUES (2, 45, 60, 75, 90, 115, 30, 130);


SELECT setval('guinada_maritima_seq', 2);
---------------------------------------------------------------------

-- Plataforma
INSERT INTO plataforma VALUES (1, 'PLAT1', 15, 15, 50);
INSERT INTO plataforma VALUES (2, 'PLATAVIAO', 150, 200, 600);
INSERT INTO plataforma VALUES (3, 'PLATHELICOPTERO', 160, 240, 150);
INSERT INTO plataforma VALUES (4, 'PLATSUBMARINO', 15, 15, 40);
SELECT setval('plataforma_seq', 4);


--Bateria (Plataforma Submarino)

INSERT INTO carga(
            id_carga, cinco_a_oito_nos, mais_de_dez_nos, oito_a_dez_nos, 
            zero_a_cinco_nos)
    VALUES (1, 25, 26, 27, 
            28);
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

INSERT INTO mae (id_mae,distancia_limite,frequencia_minima,frequencia_maxima,nome) VALUES (1, 2, 250, 350, 'mae1');
-----------------------------------------------

--Plataformas Superficie, Avião, Helicoptero e Submarino
INSERT INTO plataforma_superficie (movimento_in_out, num_eixos, num_pas_eixo, id_tipo_propulsao, 
                                   capacidade_deslocamento, tipo_navio, vms, id_plataforma, 
                                   id_taxa_guinada, calado, comprimento_proa, comprimento_popa, largura) 
                                   VALUES (true, 1, 3, 'MOTOR', 2000, 'FRG', 20, 1, 1, 0,  60, 40, 50);
INSERT INTO plataforma_aviao(
            altitude_maxima, capacidade_armamentos, id_tamanho, taxa_subida_descida, 
            velocidade_minima, id_plataforma, id_taxa_guinada)
    VALUES (30000, 20000, 'GRANDE', 1000, 
            100, 2, 1);
INSERT INTO plataforma_helicoptero(
            altitude_maxima, capacidade_armamentos, id_tamanho, taxa_subida_descida, 
            id_plataforma, id_taxa_guinada)
    VALUES (10000, 3500, 'GRANDE', 1000, 
            3, 1);

INSERT INTO plataforma_submarino(
            movimento_in_out, num_eixos, num_pas_eixo, id_tipo_propulsao, 
            capacidade_deslocamento, profundidade_maxima, taxa_mergulho, 
            tipo_submarino, velocidade_max_com_esnorquel, velocidade_max_submerso, 
            id_plataforma, id_bateria, id_taxa_guinada)
    VALUES (true, 2, 4, 'MOTOR', 
            600, 2200, 10, 
            'NUCLEAR', 16, 10, 
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

INSERT INTO info_veiculo (id_informacao_veiculo, nome, id_modelo_3d, id_plataforma) 
    VALUES (4, 'V34', 2, 1);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (4, 0, '710482000', 111);
INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
    VALUES (4, true, false);

INSERT INTO info_veiculo (id_informacao_veiculo, nome, id_plataforma) 
    VALUES (3, 'AVIAO1', 2);
INSERT INTO info_veiculo_aereo (id_informacao_veiculo, autonomia) 
    VALUES (3, '10:00');
INSERT INTO info_veiculo_aviao (id_informacao_veiculo)
    VALUES (3);

INSERT INTO info_veiculo (id_informacao_veiculo, nome, id_plataforma) 
    VALUES (5, 'SUBMARINO1', 4);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (5, 1000, 1000, 100);
INSERT INTO info_veiculo_submarino (id_informacao_veiculo, esnorquel, periscopio)
    VALUES (5, true, true);

INSERT INTO info_veiculo (id_informacao_veiculo, nome, id_plataforma) 
    VALUES (6, 'HELICOPTERO1', 3);
INSERT INTO info_veiculo_aereo (id_informacao_veiculo, autonomia) 
    VALUES (6, '10:00');
INSERT INTO info_veiculo_helicoptero (id_informacao_veiculo)
    VALUES (6);

INSERT INTO info_veiculo (id_informacao_veiculo, nome, id_plataforma) 
    VALUES (7, 'HELICOPTERO1', 3);
INSERT INTO info_veiculo_aereo (id_informacao_veiculo, autonomia) 
    VALUES (7, '10:00');
INSERT INTO info_veiculo_helicoptero (id_informacao_veiculo)
    VALUES (7);

--Associacao MAE com Informacao Veiculo Superficie
INSERT INTO info_veiculo_superficie_mae(id_mae, id_informacao_veiculo) VALUES (1,1);
INSERT INTO info_veiculo_superficie_mae(id_mae, id_informacao_veiculo) VALUES (1,2);

--Bomba
INSERT INTO arma(id_arma, nome, tempo_reacao)    VALUES (1, 'BOMBA1', 30);
INSERT INTO bomba(altitude_min, peso, id_arma)   VALUES (100, 10, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (1, 3, 10);



--Canhao
INSERT INTO arma(id_arma, nome, tempo_reacao,alcance_max, alcance_min, velocidade)    VALUES (2, 'CANHAO1', 30, 3000, 7, 1400);
INSERT INTO canhao(alcance_eficaz_aer, alcance_eficaz_sup, arco_fogo, cadencia_tiro, calibre, polegada, id_arma)
    VALUES (20, 30, 90, 1, 20, false, 2);

-- --DCN - Naval
-- INSERT INTO arma(id_arma, nome, tempo_reacao, alcance_max, alcance_min, velocidade)     VALUES (3, 'Creusot-Loire Mod. 1968', 30, 19140, 100, 1946);
-- 
--     INSERT INTO canhao(alcance_eficaz_aer, alcance_eficaz_sup, arco_fogo, cadencia_tiro, calibre, polegada, id_arma)
--         VALUES (20, 30, 280, 1, 20, false, 3);
-- 
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
-- 
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (2, 1, 2000);
-- INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (3, 1, 100);
-- INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (4, 1, 100);
-- INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (5, 1, 100);
-- INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (6, 1, 100);

INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (2, 2, 5000);
-- INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (3, 2, 100);
-- INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (4, 2, 100);
-- INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (5, 2, 100);
-- INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (6, 2, 100);

INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (2, 4, 1500);
-- INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (3, 4, 100);
-- INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (4, 4, 100);
-- INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (5, 4, 100);
-- INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (6, 4, 100);

SELECT setval('arma_seq', 2);


-- Veiculos
INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (57);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (57, 1, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (57);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (59);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (59, 1, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (59);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (61);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (61, 1, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (61);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (63);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (63, 5, true);
INSERT INTO veiculo_submarino (id_objeto_tatico) 
    VALUES (63);


INSERT INTO usuario_maquina_condicao VALUES (8, 1, 3);
INSERT INTO usuario_maquina_condicao VALUES (8, 20, 3);
INSERT INTO usuario_maquina_condicao VALUES (8, 24, 3);
INSERT INTO usuario_maquina_condicao VALUES (9, 5, 1);
INSERT INTO usuario_maquina_condicao VALUES (9, 4, 1);
INSERT INTO usuario_maquina_condicao VALUES (9, 3, 1);
INSERT INTO usuario_maquina_condicao VALUES (9, 2, 6);
INSERT INTO usuario_maquina_condicao VALUES (9, 7, 7);
INSERT INTO usuario_maquina_condicao VALUES (9, 6, 1);
INSERT INTO usuario_maquina_condicao VALUES (10, 10, 1);
INSERT INTO usuario_maquina_condicao VALUES (10, 9, 1);
INSERT INTO usuario_maquina_condicao VALUES (10, 8, 1);
INSERT INTO usuario_maquina_condicao VALUES (10, 11, 1);
INSERT INTO usuario_maquina_condicao VALUES (10, 13, 6);
INSERT INTO usuario_maquina_condicao VALUES (10, 12, 7);
INSERT INTO usuario_maquina_condicao VALUES (11, 15, 1);
INSERT INTO usuario_maquina_condicao VALUES (11, 16, 1);
INSERT INTO usuario_maquina_condicao VALUES (11, 14, 1);
INSERT INTO usuario_maquina_condicao VALUES (11, 19, 1);
INSERT INTO usuario_maquina_condicao VALUES (11, 18, 6);
INSERT INTO usuario_maquina_condicao VALUES (11, 17, 7);
INSERT INTO usuario_maquina_condicao VALUES (12, 22, 1);
INSERT INTO usuario_maquina_condicao VALUES (12, 23, 1);
INSERT INTO usuario_maquina_condicao VALUES (12, 21, 6);


INSERT INTO configuracao_equipamento VALUES (1, false, false, 4444);
INSERT INTO configuracao_equipamento VALUES (2, false, false, 4445);
INSERT INTO configuracao_equipamento VALUES (3, false, false, 4446);
INSERT INTO configuracao_equipamento VALUES (4, false, false, 4447);
INSERT INTO configuracao_equipamento VALUES (5, false, false, 4448);
INSERT INTO configuracao_equipamento VALUES (6, false, false, 4449);
INSERT INTO configuracao_equipamento VALUES (8, false, false, 4451);
INSERT INTO configuracao_equipamento VALUES (9, false, false, 4452);
INSERT INTO configuracao_equipamento VALUES (10, false, false, 4453);
INSERT INTO configuracao_equipamento VALUES (11, false, false, 4454);
INSERT INTO configuracao_equipamento VALUES (12, false, false, 4455);
INSERT INTO configuracao_equipamento VALUES (14, false, false, 4456);
INSERT INTO configuracao_equipamento VALUES (15, false, false, 4459);
INSERT INTO configuracao_equipamento VALUES (13, false, false, 4460);
INSERT INTO configuracao_equipamento VALUES (18, false, false, 4444);
INSERT INTO configuracao_equipamento VALUES (19, false, false, 4444);
INSERT INTO configuracao_equipamento VALUES (20, false, false, 4444);
INSERT INTO configuracao_equipamento VALUES (21, false, false, 4445);
INSERT INTO configuracao_equipamento VALUES (22, false, false, 4445);
INSERT INTO configuracao_equipamento VALUES (23, false, false, 4445);
INSERT INTO configuracao_equipamento VALUES (24, false, false, 4445);
INSERT INTO configuracao_equipamento VALUES (25, false, false, 4445);
INSERT INTO configuracao_equipamento VALUES (26, false, false, 4445);
INSERT INTO configuracao_equipamento VALUES (27, false, false, 4446);
INSERT INTO configuracao_equipamento VALUES (28, false, false, 4446);
INSERT INTO configuracao_equipamento VALUES (29, false, false, 4446);
INSERT INTO configuracao_equipamento VALUES (30, false, false, 4446);
INSERT INTO configuracao_equipamento VALUES (31, false, false, 4446);
INSERT INTO configuracao_equipamento VALUES (32, false, false, 4446);
INSERT INTO configuracao_equipamento VALUES (33, false, false, 4447);
INSERT INTO configuracao_equipamento VALUES (34, false, false, 4447);
INSERT INTO configuracao_equipamento VALUES (35, false, false, 4447);
INSERT INTO configuracao_equipamento VALUES (36, false, false, 4447);
INSERT INTO configuracao_equipamento VALUES (37, false, false, 4447);
INSERT INTO configuracao_equipamento VALUES (38, false, false, 4447);
INSERT INTO configuracao_equipamento VALUES (39, false, false, 4448);
INSERT INTO configuracao_equipamento VALUES (40, false, false, 4448);
INSERT INTO configuracao_equipamento VALUES (41, false, false, 4448);
INSERT INTO configuracao_equipamento VALUES (42, false, false, 4448);
INSERT INTO configuracao_equipamento VALUES (43, false, false, 4448);
INSERT INTO configuracao_equipamento VALUES (44, false, false, 4448);
INSERT INTO configuracao_equipamento VALUES (45, false, false, 4449);
INSERT INTO configuracao_equipamento VALUES (46, false, false, 4449);
INSERT INTO configuracao_equipamento VALUES (47, false, false, 4449);
INSERT INTO configuracao_equipamento VALUES (48, false, false, 4449);
INSERT INTO configuracao_equipamento VALUES (49, false, false, 4449);
INSERT INTO configuracao_equipamento VALUES (50, false, false, 4449);
INSERT INTO configuracao_equipamento VALUES (51, false, false, 4451);
INSERT INTO configuracao_equipamento VALUES (52, false, false, 4451);
INSERT INTO configuracao_equipamento VALUES (53, false, false, 4451);
INSERT INTO configuracao_equipamento VALUES (54, false, false, 4451);
INSERT INTO configuracao_equipamento VALUES (55, false, false, 4451);
INSERT INTO configuracao_equipamento VALUES (56, false, false, 4451);
INSERT INTO configuracao_equipamento VALUES (57, false, false, 4452);
INSERT INTO configuracao_equipamento VALUES (58, false, false, 4452);
INSERT INTO configuracao_equipamento VALUES (59, false, false, 4452);
INSERT INTO configuracao_equipamento VALUES (60, false, false, 4453);
INSERT INTO configuracao_equipamento VALUES (61, false, false, 4453);
INSERT INTO configuracao_equipamento VALUES (62, false, false, 4453);
INSERT INTO configuracao_equipamento VALUES (63, false, false, 4453);
INSERT INTO configuracao_equipamento VALUES (64, false, false, 4453);
INSERT INTO configuracao_equipamento VALUES (65, false, false, 4453);
INSERT INTO configuracao_equipamento VALUES (66, false, false, 4454);
INSERT INTO configuracao_equipamento VALUES (67, false, false, 4454);
INSERT INTO configuracao_equipamento VALUES (68, false, false, 4454);
INSERT INTO configuracao_equipamento VALUES (69, false, false, 4454);
INSERT INTO configuracao_equipamento VALUES (70, false, false, 4454);
INSERT INTO configuracao_equipamento VALUES (71, false, false, 4454);
INSERT INTO configuracao_equipamento VALUES (72, false, false, 4460);
INSERT INTO configuracao_equipamento VALUES (73, false, false, 4456);
INSERT INTO configuracao_equipamento VALUES (74, false, false, 4456);
INSERT INTO configuracao_equipamento VALUES (75, false, false, 4456);
INSERT INTO configuracao_equipamento VALUES (76, false, false, 4456);
INSERT INTO configuracao_equipamento VALUES (77, false, false, 4456);
INSERT INTO configuracao_equipamento VALUES (78, false, false, 4456);
INSERT INTO configuracao_equipamento VALUES (79, false, false, 4459);
INSERT INTO configuracao_equipamento VALUES (80, false, false, 4459);
INSERT INTO configuracao_equipamento VALUES (81, false, false, 4459);
INSERT INTO configuracao_equipamento VALUES (82, false, false, 4460);
INSERT INTO configuracao_equipamento VALUES (83, false, false, 4461);
INSERT INTO configuracao_equipamento VALUES (84, false, false, 4462);

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
    VALUES (15, 'ALTIMETRO', NULL, 'Altímetro');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (16, 'ECOBATIMETRO', NULL, 'Ecobatímetro');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (18, 'NAVEGACAO_INERCIAL', NULL, 'Navegação Inercial');
INSERT INTO equipamento(id_sensor, tipo , custo_sensor, nome)
    VALUES (19, 'MAD', NULL, 'MAD');

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

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 1, 18);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 2, 21);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 3, 27);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 4, 33);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 5, 39);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 6, 45);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 8, 51);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 9, 57);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 10, 60);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 11, 66);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 14, 73);

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (4, 1, 19);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (4, 2, 22);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (4, 3, 28);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (4, 4, 34);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (4, 5, 40);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (4, 6, 46);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (4, 8, 52);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (4, 9, 58);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (4, 10, 61);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (4, 11, 67);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (4, 14, 74);

-- SUBMARINO
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 1, 20);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 2, 23);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 3, 29);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 4, 35);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 5, 41);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 6, 47);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 8, 53);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 9, 59);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 10, 62);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 11, 68);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 14, 75);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 16, 82);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 18, 83);

-- AVIÂO
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (3, 2, 24);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (3, 3, 30);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (3, 4, 36);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (3, 5, 42);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (3, 6, 48);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (3, 8, 54);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (3, 10, 63);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (3, 11, 69);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (3, 14, 76);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (3, 15, 79);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (3, 19, 84);

--HELICÓPTERO
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (6, 2, 25);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (6, 3, 31);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (6, 4, 37);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (6, 5, 43);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (6, 6, 49);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (6, 8, 55);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (6, 10, 64);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (6, 11, 70);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (6, 13, 13);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (6, 14, 77);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (6, 15, 80);

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (7, 2, 26);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (7, 3, 32);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (7, 4, 38);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (7, 5, 44);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (7, 6, 50);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (7, 8, 56);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (7, 10, 65);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (7, 11, 71);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (7, 13, 72);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (7, 14, 78);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (7, 15, 81);


INSERT INTO configuracao_ais VALUES (100, 150, 1);
INSERT INTO configuracao_ais VALUES (100, 150, 18);
INSERT INTO configuracao_ais VALUES (100, 150, 19);
INSERT INTO configuracao_ais VALUES (100, 150, 20);

INSERT INTO configuracao_anemometro VALUES (10, 2);
INSERT INTO configuracao_anemometro VALUES (10, 21);
INSERT INTO configuracao_anemometro VALUES (10, 22);
INSERT INTO configuracao_anemometro VALUES (10, 23);
INSERT INTO configuracao_anemometro VALUES (10, 24);
INSERT INTO configuracao_anemometro VALUES (10, 25);
INSERT INTO configuracao_anemometro VALUES (10, 26);

INSERT INTO configuracao_giro VALUES (10, true, 3);
INSERT INTO configuracao_giro VALUES (10, true, 27);
INSERT INTO configuracao_giro VALUES (10, true, 28);
INSERT INTO configuracao_giro VALUES (10, true, 29);
INSERT INTO configuracao_giro VALUES (10, true, 30);
INSERT INTO configuracao_giro VALUES (10, true, 31);
INSERT INTO configuracao_giro VALUES (10, true, 32);

INSERT INTO configuracao_gps VALUES (10, 4);
INSERT INTO configuracao_gps VALUES (10, 33);
INSERT INTO configuracao_gps VALUES (10, 34);
INSERT INTO configuracao_gps VALUES (10, 35);
INSERT INTO configuracao_gps VALUES (10, 36);
INSERT INTO configuracao_gps VALUES (10, 37);
INSERT INTO configuracao_gps VALUES (10, 38);

INSERT INTO configuracao_hodometro VALUES (10, 5);
INSERT INTO configuracao_hodometro VALUES (10, 39);
INSERT INTO configuracao_hodometro VALUES (10, 40);
INSERT INTO configuracao_hodometro VALUES (10, 41);
INSERT INTO configuracao_hodometro VALUES (10, 42);
INSERT INTO configuracao_hodometro VALUES (10, 43);
INSERT INTO configuracao_hodometro VALUES (10, 44);

INSERT INTO configuracao_linkyb VALUES (300, 600, '127.0.0.1', 2, true, 5008, 5200, 5208, 5000, 6);
INSERT INTO configuracao_linkyb VALUES (300, 600, '127.0.0.1', 2, true, 5008, 5200, 5208, 5000, 45);
INSERT INTO configuracao_linkyb VALUES (300, 600, '127.0.0.1', 2, true, 5008, 5200, 5208, 5000, 46);
INSERT INTO configuracao_linkyb VALUES (300, 600, '127.0.0.1', 2, true, 5008, 5200, 5208, 5000, 47);
INSERT INTO configuracao_linkyb VALUES (300, 600, '127.0.0.1', 2, true, 5008, 5200, 5208, 5000, 48);
INSERT INTO configuracao_linkyb VALUES (300, 600, '127.0.0.1', 2, true, 5008, 5200, 5208, 5000, 49);
INSERT INTO configuracao_linkyb VALUES (300, 600, '127.0.0.1', 2, true, 5008, 5200, 5208, 5000, 50);

INSERT INTO configuracao_radar_busca(tempo_eliminado, tempo_perdido, id_config_sensor)
    VALUES (30, 60, 8);
INSERT INTO configuracao_radar_busca(tempo_eliminado, tempo_perdido, id_config_sensor)
    VALUES (30, 60, 51);
INSERT INTO configuracao_radar_busca(tempo_eliminado, tempo_perdido, id_config_sensor)
    VALUES (30, 60, 52);
INSERT INTO configuracao_radar_busca(tempo_eliminado, tempo_perdido, id_config_sensor)
    VALUES (30, 60, 53);
INSERT INTO configuracao_radar_busca(tempo_eliminado, tempo_perdido, id_config_sensor)
    VALUES (30, 60, 54);
INSERT INTO configuracao_radar_busca(tempo_eliminado, tempo_perdido, id_config_sensor)
    VALUES (30, 60, 55);
INSERT INTO configuracao_radar_busca(tempo_eliminado, tempo_perdido, id_config_sensor)
    VALUES (30, 60, 56);

INSERT INTO configuracao_sonar_casco VALUES (120, 60, 9);
INSERT INTO configuracao_sonar_casco VALUES (120, 60, 57);
INSERT INTO configuracao_sonar_casco VALUES (120, 60, 58);
INSERT INTO configuracao_sonar_casco VALUES (120, 60, 59);

INSERT INTO configuracao_mage VALUES (0, 60, 10);
INSERT INTO configuracao_mage VALUES (0, 60, 60);
INSERT INTO configuracao_mage VALUES (0, 60, 61);
INSERT INTO configuracao_mage VALUES (0, 60, 62);
INSERT INTO configuracao_mage VALUES (0, 60, 63);
INSERT INTO configuracao_mage VALUES (0, 60, 64);
INSERT INTO configuracao_mage VALUES (0, 60, 65);

INSERT INTO configuracao_iff VALUES (21, 20, false, false, 11);
INSERT INTO configuracao_iff VALUES (21, 20, false, false, 66);
INSERT INTO configuracao_iff VALUES (21, 20, false, false, 67);
INSERT INTO configuracao_iff VALUES (21, 20, false, false, 68);
INSERT INTO configuracao_iff VALUES (21, 20, false, false, 69);
INSERT INTO configuracao_iff VALUES (21, 20, false, false, 70);
INSERT INTO configuracao_iff VALUES (21, 20, false, false, 71);

INSERT INTO configuracao_radar_busca VALUES (30, 60, 12);

INSERT INTO configuracao_sonar_vds VALUES (120, 60, 13);
INSERT INTO configuracao_sonar_vds VALUES (120, 60, 72);

INSERT INTO configuracao_dv VALUES (30, 60, 14);
INSERT INTO configuracao_dv VALUES (30, 60, 73);
INSERT INTO configuracao_dv VALUES (30, 60, 74);
INSERT INTO configuracao_dv VALUES (30, 60, 75);
INSERT INTO configuracao_dv VALUES (30, 60, 76);
INSERT INTO configuracao_dv VALUES (30, 60, 77);
INSERT INTO configuracao_dv VALUES (30, 60, 78);

INSERT INTO configuracao_altimetro VALUES (10,15);
INSERT INTO configuracao_altimetro VALUES (10,79);
INSERT INTO configuracao_altimetro VALUES (10,80);
INSERT INTO configuracao_altimetro VALUES (10,81);

INSERT INTO configuracao_ecobatimetro VALUES (10,82);

INSERT INTO configuracao_navegacao_inercial VALUES (10,83);

INSERT INTO configuracao_mad (tempo_perdido, tempo_eliminado, id_config_sensor)
    VALUES (30, 60, 84);

INSERT INTO mage VALUES (3, 10);

INSERT INTO radar(
            frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (1750, 255, 250, 50, 8);

INSERT INTO sonar VALUES (15000, 30000, 7, 'ONIDIRECIONAL',9);
INSERT INTO sonar VALUES (15000, 30000, 7, 'ONIDIRECIONAL',13);

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
INSERT INTO radar_busca VALUES (false, false, false, 5, 359, 'CIRCULAR', 8);
INSERT INTO sensor_dv VALUES (14);
INSERT INTO sensor_altimetro VALUES (15);
INSERT INTO sensor_ecobatimetro VALUES (16);
INSERT INTO faixa_freq_mage VALUES (1,600,250,1, 10);
INSERT INTO faixa_freq_mage VALUES (2,18000,601,2, 10);
INSERT INTO faixa_freq_mage VALUES (3,25000,18001,3, 10);
INSERT INTO sensor_navegacao_inercial VALUES (18);

INSERT INTO sensor_mad (id_sensor) VALUES (19);

-- Chaff Charlie lançador1
INSERT INTO chaff_superficie (id_chaff,alcance,duracao_nuvem,tempo_gerar_eco,tipo_chaff)
VALUES (1,2.5,600000,5000,'CHARLIE');
-- Chaff Charlie lançador2
INSERT INTO chaff_superficie (id_chaff,alcance,duracao_nuvem,tempo_gerar_eco,tipo_chaff)
VALUES (2,2.5,600000,5000,'CHARLIE');
-- Chaff Delta lançador1
INSERT INTO chaff_superficie (id_chaff,alcance,duracao_nuvem,tempo_gerar_eco,tipo_chaff)
VALUES (3,1,420000,5000,'DELTA');
-- Chaff Delta lançador2
INSERT INTO chaff_superficie (id_chaff,alcance,duracao_nuvem,tempo_gerar_eco,tipo_chaff)
VALUES (4,1,420000,5000,'DELTA');
-- Chaff Sierra lançador4
INSERT INTO chaff_superficie (id_chaff,alcance,duracao_nuvem,tempo_gerar_eco,tipo_chaff)
VALUES (5,1,420000,5000,'SIERRA');

INSERT INTO info_veiculo_chaff_superficie (id_chaff,id_informacao_veiculo,numero_lancador,quantidade) VALUES(1,1,1,2);
INSERT INTO info_veiculo_chaff_superficie (id_chaff,id_informacao_veiculo,numero_lancador,quantidade) VALUES(2,1,2,2);
INSERT INTO info_veiculo_chaff_superficie (id_chaff,id_informacao_veiculo,numero_lancador,quantidade) VALUES(3,1,3,2);
INSERT INTO info_veiculo_chaff_superficie (id_chaff,id_informacao_veiculo,numero_lancador,quantidade) VALUES(4,1,4,2);
INSERT INTO info_veiculo_chaff_superficie (id_chaff,id_informacao_veiculo,numero_lancador,quantidade) VALUES(5,1,4,2);

-- Chaff Aeronave lançador1
INSERT INTO chaff_aeronave (id_chaff,alcance,duracao_nuvem,tempo_gerar_eco)
VALUES (6,2.5,600000,5000);
-- Chaff Aeronave lançador2
INSERT INTO chaff_aeronave (id_chaff,alcance,duracao_nuvem,tempo_gerar_eco)
VALUES (7,2.5,600000,5000);

INSERT INTO info_veiculo_chaff_aeronave (id_chaff,id_informacao_veiculo,quantidade,numero_lancador) VALUES(6,6,4,2);
INSERT INTO info_veiculo_chaff_aeronave (id_chaff,id_informacao_veiculo,quantidade,numero_lancador) VALUES(7,6,4,2);

-- Fonia INÍCIO -------------------------------------------------------------------
INSERT INTO configuracao_fonia (id_configuracao_fonia, nome, configuracao_padrao) VALUES(1, 'Configuracao Padrao', true);
SELECT setval('configuracao_fonia_seq', 1);

INSERT INTO linha_interna (id_linha_interna, cubiculo, instrutor, id_maquina) VALUES (1, 1, false, 2);
INSERT INTO linha_interna (id_linha_interna, cubiculo, instrutor, id_maquina) VALUES (2, 1, false, 3);
INSERT INTO linha_interna (id_linha_interna, cubiculo, instrutor, id_maquina) VALUES (3, 1, false, 4);
INSERT INTO linha_interna (id_linha_interna, cubiculo, instrutor, id_maquina) VALUES (4, 1, false, 5);
INSERT INTO linha_interna (id_linha_interna, cubiculo, instrutor, id_maquina) VALUES (5, 1, false, 6);
INSERT INTO linha_interna (id_linha_interna, cubiculo, instrutor, id_maquina) VALUES (6, 1, false, 7);

INSERT INTO linha_interna (id_linha_interna, cubiculo, instrutor, id_maquina) VALUES (7, 2, false, 8);
INSERT INTO linha_interna (id_linha_interna, cubiculo, instrutor, id_maquina) VALUES (8, 2, false, 9);
INSERT INTO linha_interna (id_linha_interna, cubiculo, instrutor, id_maquina) VALUES (9, 2, false, 10);
INSERT INTO linha_interna (id_linha_interna, cubiculo, instrutor, id_maquina) VALUES (10, 2, false, 11);
INSERT INTO linha_interna (id_linha_interna, cubiculo, instrutor, id_maquina) VALUES (11, 2, false, 12);
INSERT INTO linha_interna (id_linha_interna, cubiculo, instrutor, id_maquina) VALUES (12, 2, false, 13);

INSERT INTO linha_interna (id_linha_interna, cubiculo, instrutor, id_maquina) VALUES (13, 3, false, 14);
INSERT INTO linha_interna (id_linha_interna, cubiculo, instrutor, id_maquina) VALUES (14, 3, false, 15);
INSERT INTO linha_interna (id_linha_interna, cubiculo, instrutor, id_maquina) VALUES (15, 3, false, 16);
INSERT INTO linha_interna (id_linha_interna, cubiculo, instrutor, id_maquina) VALUES (16, 3, false, 17);
INSERT INTO linha_interna (id_linha_interna, cubiculo, instrutor, id_maquina) VALUES (17, 3, false, 18);
INSERT INTO linha_interna (id_linha_interna, cubiculo, instrutor, id_maquina) VALUES (18, 3, false, 19);

-- Cubículo Instrutor
INSERT INTO linha_interna (id_linha_interna, cubiculo, instrutor, id_maquina) VALUES (22, 4, true, 1);
INSERT INTO linha_interna (id_linha_interna, cubiculo, instrutor, id_maquina) VALUES (24, 4, true, 20);
INSERT INTO linha_interna (id_linha_interna, cubiculo, instrutor, id_maquina) VALUES (25, 4, true, 24);


-- Inserts para as linhas de comunicação ponto a ponto entre instrutor e aluno (conceito do VIGIA)
-- Deve haver um par para cada comunicação requerida, para que os dois consoles saibam se configurarar corretamente
INSERT INTO Comunicacao_ponto_ponto (id_linha_interna_origem, id_linha_interna_destino) VALUES (24, 1);
INSERT INTO Comunicacao_ponto_ponto (id_linha_interna_origem, id_linha_interna_destino) VALUES (1, 24);
INSERT INTO Comunicacao_ponto_ponto (id_linha_interna_origem, id_linha_interna_destino) VALUES (24, 7);
INSERT INTO Comunicacao_ponto_ponto (id_linha_interna_origem, id_linha_interna_destino) VALUES (7, 24);
INSERT INTO Comunicacao_ponto_ponto (id_linha_interna_origem, id_linha_interna_destino) VALUES (24, 13);
INSERT INTO Comunicacao_ponto_ponto (id_linha_interna_origem, id_linha_interna_destino) VALUES (13, 24);

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

INSERT INTO configuracao_fonia_linha_interna (configuracoesfonia_id_configuracao_fonia, linhasinternas_id_linha_interna) VALUES (1, 1);
INSERT INTO configuracao_fonia_linha_interna (configuracoesfonia_id_configuracao_fonia, linhasinternas_id_linha_interna) VALUES (1, 2);
INSERT INTO configuracao_fonia_linha_interna (configuracoesfonia_id_configuracao_fonia, linhasinternas_id_linha_interna) VALUES (1, 3);
INSERT INTO configuracao_fonia_linha_interna (configuracoesfonia_id_configuracao_fonia, linhasinternas_id_linha_interna) VALUES (1, 4);
INSERT INTO configuracao_fonia_linha_interna (configuracoesfonia_id_configuracao_fonia, linhasinternas_id_linha_interna) VALUES (1, 5);
INSERT INTO configuracao_fonia_linha_interna (configuracoesfonia_id_configuracao_fonia, linhasinternas_id_linha_interna) VALUES (1, 6);
INSERT INTO configuracao_fonia_linha_interna (configuracoesfonia_id_configuracao_fonia, linhasinternas_id_linha_interna) VALUES (1, 7);
INSERT INTO configuracao_fonia_linha_interna (configuracoesfonia_id_configuracao_fonia, linhasinternas_id_linha_interna) VALUES (1, 8);
INSERT INTO configuracao_fonia_linha_interna (configuracoesfonia_id_configuracao_fonia, linhasinternas_id_linha_interna) VALUES (1, 9);
INSERT INTO configuracao_fonia_linha_interna (configuracoesfonia_id_configuracao_fonia, linhasinternas_id_linha_interna) VALUES (1, 10);
INSERT INTO configuracao_fonia_linha_interna (configuracoesfonia_id_configuracao_fonia, linhasinternas_id_linha_interna) VALUES (1, 11);
INSERT INTO configuracao_fonia_linha_interna (configuracoesfonia_id_configuracao_fonia, linhasinternas_id_linha_interna) VALUES (1, 12);
INSERT INTO configuracao_fonia_linha_interna (configuracoesfonia_id_configuracao_fonia, linhasinternas_id_linha_interna) VALUES (1, 13);
INSERT INTO configuracao_fonia_linha_interna (configuracoesfonia_id_configuracao_fonia, linhasinternas_id_linha_interna) VALUES (1, 14);
INSERT INTO configuracao_fonia_linha_interna (configuracoesfonia_id_configuracao_fonia, linhasinternas_id_linha_interna) VALUES (1, 15);
INSERT INTO configuracao_fonia_linha_interna (configuracoesfonia_id_configuracao_fonia, linhasinternas_id_linha_interna) VALUES (1, 16);
INSERT INTO configuracao_fonia_linha_interna (configuracoesfonia_id_configuracao_fonia, linhasinternas_id_linha_interna) VALUES (1, 17);
INSERT INTO configuracao_fonia_linha_interna (configuracoesfonia_id_configuracao_fonia, linhasinternas_id_linha_interna) VALUES (1, 18);
INSERT INTO configuracao_fonia_linha_interna (configuracoesfonia_id_configuracao_fonia, linhasinternas_id_linha_interna) VALUES (1, 22);
INSERT INTO configuracao_fonia_linha_interna (configuracoesfonia_id_configuracao_fonia, linhasinternas_id_linha_interna) VALUES (1, 24);

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
                        values (9,0,'Boreste',0.5,150,'ZIG ZAG  180', 23,true);

INSERT INTO dav_zig_zag (delta, rumo_base, id_dav) 
                        values (60,180,7);
INSERT INTO dav_zig_zag (delta, rumo_base, id_dav) 
                        values (90,100,8);
INSERT INTO dav_zig_zag (delta, rumo_base, id_dav) 
                        values (180,0,9);

INSERT INTO dav_pernada(id_pernada,angulo_leme,bordo,rumo_relativo,velocidade,id_dav) 
                        values (7,25,'Boreste',60,24,7);
INSERT INTO dav_pernada(id_pernada,angulo_leme,bordo,rumo_relativo,velocidade,id_dav) 
                        values (8,30,'Boreste',90,22,8);
INSERT INTO dav_pernada(id_pernada,angulo_leme,bordo,rumo_relativo,velocidade,id_dav) 
                        values (9,40,'Boreste',180,15,9);

-- OPERACAO AUTOMATICA DE SENSORES --
insert into dav_op_auto_equip (id_op_auto_equip,distancia_perfil,tempo_desligado,tempo_ligado,id_dav,id_equip_associado) 
                        values(1,1,60000,50000,1,1);
insert into dav_op_auto_equip (id_op_auto_equip,distancia_perfil,tempo_desligado,tempo_ligado,id_dav,id_equip_associado) 
                        values(2,1,30000,30000,2,1);
insert into dav_op_auto_equip (id_op_auto_equip,distancia_perfil,tempo_desligado,tempo_ligado,id_dav,id_equip_associado) 
                        values(3,1,100000,100000,3,1);

insert into dav_op_auto_equip (id_op_auto_equip,distancia_perfil,tempo_desligado,tempo_ligado,id_dav,id_equip_associado) 
                        values(4,1,60000,50000,4,1);
insert into dav_op_auto_equip (id_op_auto_equip,distancia_perfil,tempo_desligado,tempo_ligado,id_dav,id_equip_associado) 
                        values(5,1,30000,30000,5,1);
insert into dav_op_auto_equip (id_op_auto_equip,distancia_perfil,tempo_desligado,tempo_ligado,id_dav,id_equip_associado) 
                        values(6,1,100000,100000,6,1);

insert into dav_op_auto_equip (id_op_auto_equip,distancia_perfil,tempo_desligado,tempo_ligado,id_dav,id_equip_associado) 
                        values(7,1,60000,50000,7,1);
insert into dav_op_auto_equip (id_op_auto_equip,distancia_perfil,tempo_desligado,tempo_ligado,id_dav,id_equip_associado) 
                        values(8,1,30000,30000,8,1);
insert into dav_op_auto_equip (id_op_auto_equip,distancia_perfil,tempo_desligado,tempo_ligado,id_dav,id_equip_associado) 
                        values(9,1,100000,100000,9,1);

-- OPERACAO AUTOMATICA DE ARMAS (DAV PERSEGUIR ALVO) --
INSERT INTO dav_op_auto_arma(id_op_auto_arma, distancia_perfil, id_arma_associada, intervalo_tempo_disparo, rajada, id_dav)
    VALUES (1,1500,2,30,5,1);
INSERT INTO dav_op_auto_arma(id_op_auto_arma, distancia_perfil, id_arma_associada, intervalo_tempo_disparo, rajada, id_dav)
    VALUES (2,2000,2,30,10,2);
INSERT INTO dav_op_auto_arma(id_op_auto_arma, distancia_perfil, id_arma_associada, intervalo_tempo_disparo, rajada, id_dav)
    VALUES (3,8000,2,20,20,3);

-- ROTA --
INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES ((select nextval('objeto_tatico_seq')));
INSERT INTO rota (id_objeto_tatico, destino, nome, origem, status, condicao_inicial_dono) 
    VALUES ((select currval('objeto_tatico_seq')),'destino','ROTA X', 'origem', 'N', null);

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
