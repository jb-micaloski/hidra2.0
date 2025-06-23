-- Remove os valores padrões inseridos no defaul.sql
DELETE FROM usuario_maquina WHERE id_maquina=1;
DELETE FROM maquina WHERE id_maquina=1;



INSERT INTO maquina (id_maquina,hostname) VALUES (1, 'GSD-055');

INSERT INTO maquina (id_maquina,hostname) VALUES (2, 'GSD-068');

SELECT setval('maquina_seq', 2);

INSERT INTO usuario VALUES (1, 9, 'estrategia', '03AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4');

SELECT setval('usuario_seq', 1);

INSERT INTO usuario_func (id_funcionalidade,id_usuario, interagir,posicao) VALUES (22, 1,true,1);
INSERT INTO usuario_func (id_funcionalidade,id_usuario, interagir,posicao) VALUES (23, 1,true,2);



INSERT INTO usuario_maquina VALUES (1, 1, true);

INSERT INTO usuario_maquina VALUES (2, 1, true);

INSERT INTO dados_ambientais (id_dados_ambientais, declinacao_magnetica, direcao_corrente, direcao_vento, estado_mar, hemisferio, mdr1, mdr2, nuvens, topo_nuvens, profundidade_camada, profundidade_local, velocidade_corrente, velocidade_vento, visibilidade, chuva, chance_raios) VALUES (1, 12, 20, 30, 2, 'E', 90, 120, 1000, 1500, 250, 1000, 1, 5, 12, 5, 20);

INSERT INTO teatro_operacao VALUES (1, 1420106400444, -26.38893921489483, -43.65716347228855, 'Exercício 1', 1);

-- Grupo A
INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao) 
    VALUES (1, 0, 0, 0, '1111', 0, 0, 9, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao) 
    VALUES (2, 0, 0, 0, '1212', 0, 0, 10, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao) 
    VALUES (3, 0, 0, 0, '2222', 0, 0, 11, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao) 
    VALUES (4, 0, 0, 0, '3333', 0, 0, 12, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao) 
    VALUES (5, 0, 0, 0, '4444', 0, 0, 13, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao) 
    VALUES (6, 0, 0, 0, '5555', 0, 0, 14, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao) 
    VALUES (7, 0, 0, 0, '6666', 0, 0, 15, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao) 
    VALUES (8, 0, 0, 0, '7777', 0, 0, 16, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao)
    VALUES (9, 0, 0, 0, '7788', 0, 0, 17, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao)   
    VALUES (10, 0, 0, 0, '9999', 0, 0, 18, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao) 
    VALUES (11, 0, 0, 0, '1010', 0, 0, 19, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao) 
    VALUES (12, 0, 0, 0, '1414', 0, 0, 20, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao)  
    VALUES (13, 0, 0, 0, '1515', 0, 0, 21, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao) 
    VALUES (14, 0, 0, 0, '1616', 0, 0, 22, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao) 
    VALUES (15, 0, 0, 0, '1717', 0, 0, 23, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao) 
    VALUES (16, 0, 0, 0, '1818', 0, 0, 24, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao) 
    VALUES (17, 0, 0, 0, '9898', 0, 0, 25, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao) 
    VALUES (18, 0, 0, 0, '5852', 0, 0, 26, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao) 
    VALUES (19, 0, 0, 0, '3355', 0, 0, 27, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao) 
    VALUES (20,  0, 0, 0, '7412', 0, 0, 28, 1 );


--Grupo B
INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao) 
    VALUES (21, 0, 0, 0, '8881', 0, 0, 29, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao)  
    VALUES (22, 0, 0, 0, '8882', 0, 0, 30, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao)  
    VALUES (23, 0, 0, 0, '8883', 0, 0, 31, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao)  
    VALUES (24, 0, 0, 0, '8884', 0, 0, 32, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao)  
    VALUES (25, 0, 0, 0, '8885', 0, 0, 33, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao)  
    VALUES (26, 0, 0, 0, '8886', 0, 0, 34, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao)  
    VALUES (27, 0, 0, 0, '8887', 0, 0, 35, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao)  
    VALUES (28, 0, 0, 0, '8888', 0, 0, 36, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao)  
    VALUES (29, 0, 0, 0, '8889', 0, 0, 37, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao)  
    VALUES (30, 0, 0, 0, '8894', 0, 0, 38, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao)  
    VALUES (31, 0, 0, 0, '8895', 0, 0, 39, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao)  
    VALUES (32, 0, 0, 0, '8896', 0, 0, 40, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao)  
    VALUES (33, 0, 0, 0, '8897', 0, 0, 41, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao)  
    VALUES (34, 0, 0, 0, '8898', 0, 0, 42, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao)  
    VALUES (35, 0, 0, 0, '8899', 0, 0, 43, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao)  
    VALUES (36, 0, 0, 0, '8900', 0, 0, 44, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao)  
    VALUES (37, 0, 0, 0, '8901', 0, 0, 45, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao)  
    VALUES (38, 0, 0, 0, '8902', 0, 0, 46, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao)  
    VALUES (39, 0, 0, 0, '8903', 0, 0, 47, 1 );

INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao)  
    VALUES (40, 0, 0, 0, '8901', 0, 0, 48, 1 );





INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'A', 1);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'A', 2);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'A', 3);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'A', 4);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'A', 5);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'A', 6);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'A', 7);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'A', 8);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'A', 9);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'A', 10);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'A', 11);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'A', 12);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'A', 13);


INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'A', 14);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'A', 15);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'A', 16);


INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'A', 17);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'A', 18);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'A', 19);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'A', 20);

--Grupo B
INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'B', 21);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'B', 22);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'B', 23);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'B', 24);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'B', 25);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'B', 26);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'B', 27);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'B', 28);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'B', 29);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'B', 30);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'B', 31);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'B', 32);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'B', 33);


INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'B', 34);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'B', 35);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'B', 36);


INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'B', 37);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'B', 38);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'B', 39);

INSERT INTO condicao_veiculo(
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES ('S', 'S', true, 'B', 40);



INSERT INTO bloco VALUES (1, '25', 1);

INSERT INTO plataforma VALUES (1, 'PLAT_DEFAULT', 15, 15, 50);

INSERT INTO guinada_superficie (id_guinada_maritima,zero_a_cinco_nos,cinco_a_dez_nos, dez_a_quinze_nos,quinze_a_vinte_nos,vinte_a_vinte_cinco_nos,vinte_cinco_a_trinta_nos, trinta_a_trinta_cinco_nos,mais_de_trinta_cinco_nos) VALUES (1, 60, 70, 80, 90, 100, 110, 120, 130);

INSERT INTO plataforma_superficie ( movimento_in_out, num_eixos, num_pas_eixo, id_tipo_propulsao, capacidade_deslocamento, tipo_navio, vms, id_plataforma, id_taxa_guinada,  calado, comprimento_proa, comprimento_popa, largura) 
            VALUES (true, 1, 3, 'MOTOR', 1, 'FRG', 1, 1, 1, 0, 60, 40, 50);

INSERT INTO modelo_3d(id_modelo_3d, modelo_veiculo_primario, modelo_veiculo_secundario) VALUES (1, 'F46OwnShip', 'F46 - NPCShip'); --TESTAR NULL

--Navios
INSERT INTO info_veiculo (id_informacao_veiculo, custo_veiculo, nome, id_modelo_3d, id_plataforma) 
    VALUES (1, 20000000, 'Contra-Torpedeiros Pará', 1, 1);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (1, 0, '710429000', 100);
INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
    VALUES (1, true, false);

INSERT INTO info_veiculo (id_informacao_veiculo, custo_veiculo, nome, id_modelo_3d, id_plataforma) 
    VALUES (2, 263000000, 'Corveta Barroso', 1, 1);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (2, 0, '710429000', 100);
INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
    VALUES (2, true, false);

INSERT INTO info_veiculo (id_informacao_veiculo, custo_veiculo, nome, id_modelo_3d, id_plataforma) 
    VALUES (3, 25000000, 'Corveta Drummond', 1, 1);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (3, 0, '710429000', 100);
INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
    VALUES (3, true, false);

INSERT INTO info_veiculo (id_informacao_veiculo, custo_veiculo, nome, id_modelo_3d, id_plataforma) 
    VALUES (4, 75000000, 'Corveta Espora', 1, 1);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (4, 0, '710429000', 100);
INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
    VALUES (4, true, false);

INSERT INTO info_veiculo (id_informacao_veiculo, custo_veiculo, nome, id_modelo_3d, id_plataforma) 
    VALUES (5, 300000000, 'Corveta Inhaúma', 1, 1);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (5, 0, '710429000', 100);
INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
    VALUES (5, true, false);

INSERT INTO info_veiculo (id_informacao_veiculo, custo_veiculo, nome, id_modelo_3d, id_plataforma) 
    VALUES (6, 306000000, 'Cruzador Leander', 1, 1);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (6, 0, '710429000', 100);
INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
    VALUES (6, true, false);

INSERT INTO info_veiculo (id_informacao_veiculo, custo_veiculo, nome, id_modelo_3d, id_plataforma) 
    VALUES (7, 323000000, 'Cruzador Ticonderoga', 1, 1);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (7, 0, '710429000', 100);
INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
    VALUES (7, true, false);

INSERT INTO info_veiculo (id_informacao_veiculo, custo_veiculo, nome, id_modelo_3d, id_plataforma) 
    VALUES (8, 265000000, 'Fragata Amatola', 1, 1);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (8, 0, '710429000', 100);
INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
    VALUES (8, true, false);

INSERT INTO info_veiculo (id_informacao_veiculo, custo_veiculo, nome, id_modelo_3d, id_plataforma) 
    VALUES (9, 271633442, 'Fragata Bartolomeu Dias', 1, 1);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (9, 0, '710429000', 100);
INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
    VALUES (9, true, false);

INSERT INTO info_veiculo (id_informacao_veiculo, custo_veiculo, nome, id_modelo_3d, id_plataforma) 
    VALUES (10, 103000000, 'Fragata Condell', 1, 1);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (10, 0, '710429000', 100);
INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
    VALUES (10, true, false);

INSERT INTO info_veiculo (id_informacao_veiculo, custo_veiculo, nome, id_modelo_3d, id_plataforma) 
    VALUES (11, 106573572, 'Fragata Cornwall', 1, 1);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (11, 0, '710429000', 100);
INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
    VALUES (11, true, false);

INSERT INTO info_veiculo (id_informacao_veiculo, custo_veiculo, nome, id_modelo_3d, id_plataforma) 
    VALUES (12, 170000000, 'Fragata Greenhalgh', 1, 1);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (12, 0, '710429000', 100);
INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
    VALUES (12, true, false);

INSERT INTO info_veiculo (id_informacao_veiculo, custo_veiculo, nome, id_modelo_3d, id_plataforma) 
    VALUES (13, 170000000, 'Fragata NE Brasil', 1, 1);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (13, 0, '710429000', 100);
INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
    VALUES (13, true, false);

INSERT INTO info_veiculo (id_informacao_veiculo, custo_veiculo, nome, id_modelo_3d, id_plataforma) 
    VALUES (14, 300000000, 'Fragata Niterói', 1, 1);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (14, 0, '710429000', 100);
INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
    VALUES (14, true, false);

INSERT INTO info_veiculo (id_informacao_veiculo, custo_veiculo, nome, id_modelo_3d, id_plataforma) 
    VALUES (15, 320000000, 'Fragata Simpson', 1, 1);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (15, 0, '710429000', 100);
INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
    VALUES (15, true, false);

INSERT INTO info_veiculo (id_informacao_veiculo, custo_veiculo, nome, id_modelo_3d, id_plataforma) 
    VALUES (16, 1012000000, 'NAE São Paulo', 1, 1);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (16, 0, '710429000', 100);
INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
    VALUES (16, true, false);

INSERT INTO info_veiculo (id_informacao_veiculo, custo_veiculo, nome, id_modelo_3d, id_plataforma) 
    VALUES (17, 10000000, 'NDD Ceará', 1, 1);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (17, 0, '710429000', 100);
INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
    VALUES (17, true, false);

INSERT INTO info_veiculo (id_informacao_veiculo, custo_veiculo, nome, id_modelo_3d, id_plataforma) 
    VALUES (18, 28000000, 'NPA Grajaú', 1, 1);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (18, 0, '710429000', 100);
INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
    VALUES (18, true, false);

INSERT INTO info_veiculo (id_informacao_veiculo, custo_veiculo, nome, id_modelo_3d, id_plataforma) 
    VALUES (19, 40000000, 'NPA Macaé', 1, 1);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (19, 0, '710429000', 100);
INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
    VALUES (19, true, false);

INSERT INTO info_veiculo (id_informacao_veiculo, custo_veiculo, nome, id_modelo_3d, id_plataforma) 
    VALUES (20, 6000000000, 'Porta Helicópteros Wasp', 1, 1);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (20, 0, '710429000', 100);
INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
    VALUES (20, true, false);



--Arma
    --Canhões.

        --Bofors/BAE Systems 
INSERT INTO arma(id_arma, nome, alcance_max, raio_letalidade)     VALUES (1, '40mm OtoMelara 40L70', 6.48, 0.0022);
INSERT INTO arma(id_arma, nome, alcance_max, raio_letalidade)     VALUES (2, '40mm Trinity Mk 3', 5.39, 0.0022);

    INSERT INTO canhao(alcance_eficaz_aer, alcance_eficaz_sup, arco_fogo, cadencia_tiro, calibre, polegada, id_arma)
        VALUES (20, 30, 90, 1, 40, false, 1);

    INSERT INTO canhao(alcance_eficaz_aer, alcance_eficaz_sup, arco_fogo, cadencia_tiro, calibre, polegada, id_arma)
        VALUES (20, 30, 90, 1, 40, false, 2);

        --DCN - Naval
INSERT INTO arma(id_arma, nome, alcance_max, raio_letalidade)     VALUES (3, 'Creusot-Loire Mod. 1968', 9.45, 0.0011);

    INSERT INTO canhao(alcance_eficaz_aer, alcance_eficaz_sup, arco_fogo, cadencia_tiro, calibre, polegada, id_arma)
        VALUES (20, 30, 90, 1, 20, false, 3);

        --Denel
INSERT INTO arma(id_arma, nome, alcance_max, raio_letalidade)     VALUES (4, '35mm 35DPG', 3.24, 0.002);

    INSERT INTO canhao(alcance_eficaz_aer, alcance_eficaz_sup, arco_fogo, cadencia_tiro, calibre, polegada, id_arma)
        VALUES (20, 30, 90, 1, 35, false, 4);

        --FMC-United Defense / BAE Systems
INSERT INTO arma(id_arma, nome, alcance_max, raio_letalidade)     VALUES (5, 'FMC 127mm L/56 Mk.45 Mod2', 7.02, 0.0039);
INSERT INTO arma(id_arma, nome, alcance_max, raio_letalidade)     VALUES (6, 'FMC 76mm Mk.75', 9.93, 0.0027);

    INSERT INTO canhao(alcance_eficaz_aer, alcance_eficaz_sup, arco_fogo, cadencia_tiro, calibre, polegada, id_arma)
        VALUES (20, 30, 90, 1, 127, false, 5);
    INSERT INTO canhao(alcance_eficaz_aer, alcance_eficaz_sup, arco_fogo, cadencia_tiro, calibre, polegada, id_arma)
        VALUES (20, 30, 90, 1, 76, false, 6);
        
        --MSI Defence 
INSERT INTO arma(id_arma, nome, alcance_max, raio_letalidade)     VALUES (7, '30mm DS-30B', 1.62, 0.0019);
    
    INSERT INTO canhao(alcance_eficaz_aer, alcance_eficaz_sup, arco_fogo, cadencia_tiro, calibre, polegada, id_arma)
        VALUES (20, 30, 90, 1, 30, false, 7);

        --Oto-Melara
INSERT INTO arma(id_arma, nome, alcance_max, raio_letalidade)     VALUES (8, '76mm Super Rapid / Compact', 8.69, 0.0027);
    
    INSERT INTO canhao(alcance_eficaz_aer, alcance_eficaz_sup, arco_fogo, cadencia_tiro, calibre, polegada, id_arma)
        VALUES (20, 30, 90, 1, 76, false, 8);
        
        --Raytheon Systems
INSERT INTO arma(id_arma, nome, alcance_max, raio_letalidade)     VALUES (9, '20mm Phalanx Mk15 Block 1', 1.24, 0.0011);
    
    INSERT INTO canhao(alcance_eficaz_aer, alcance_eficaz_sup, arco_fogo, cadencia_tiro, calibre, polegada, id_arma)
        VALUES (20, 30, 90, 1, 20, false, 9);

        --Rheinmetal Defense
INSERT INTO arma(id_arma, nome, alcance_max, raio_letalidade)     VALUES (10, '20mm Oerlikon/BMARC GAM-BO1', 1.08, 0.0011);
    
    INSERT INTO canhao(alcance_eficaz_aer, alcance_eficaz_sup, arco_fogo, cadencia_tiro, calibre, polegada, id_arma)
        VALUES (20, 30, 90, 1, 20, false, 10);

        --Thales Nederland
INSERT INTO arma(id_arma, nome, alcance_max, raio_letalidade)     VALUES (11, '30mm SGE-30 «Goalkeeper»', 1.74, 0.0019);
    
    INSERT INTO canhao(alcance_eficaz_aer, alcance_eficaz_sup, arco_fogo, cadencia_tiro, calibre, polegada, id_arma)
        VALUES (20, 30, 90, 1, 30, false, 11);

        --US Naval Gun Factory
INSERT INTO arma(id_arma, nome, alcance_max, raio_letalidade)     VALUES (12, '127mm /38 Mk.30 m.1932', 9.3, 0.0039); 
INSERT INTO arma(id_arma, nome, alcance_max, raio_letalidade)     VALUES (13, '25mm Mk.38 MGS', 3.48, 0.008); 
INSERT INTO arma(id_arma, nome, alcance_max, raio_letalidade)     VALUES (14, '76mm / 50 (US) Mk33', 6.96, 0.0027);

    INSERT INTO canhao(alcance_eficaz_aer, alcance_eficaz_sup, arco_fogo, cadencia_tiro, calibre, polegada, id_arma)
        VALUES (20, 30, 90, 1, 127, false, 12);
    INSERT INTO canhao(alcance_eficaz_aer, alcance_eficaz_sup, arco_fogo, cadencia_tiro, calibre, polegada, id_arma)
        VALUES (20, 30, 90, 1, 25, false, 13);
    INSERT INTO canhao(alcance_eficaz_aer, alcance_eficaz_sup, arco_fogo, cadencia_tiro, calibre, polegada, id_arma)
        VALUES (20, 30, 90, 1, 76, false, 14);

        --Vickers Defence
INSERT INTO arma(id_arma, nome, alcance_max, raio_letalidade)     VALUES (15, '102mm L/45 Mk.V - XV (UK)', 8.072, 0.0032);
INSERT INTO arma(id_arma, nome, alcance_max, raio_letalidade)     VALUES (16, '114mm Vickers Mk 8 mod.1', 12.17, 0.0042);
INSERT INTO arma(id_arma, nome, alcance_max, raio_letalidade)     VALUES (17, '152mm /50 MK.XXIII (UK)', 12.6, 0.0043);

    INSERT INTO canhao(alcance_eficaz_aer, alcance_eficaz_sup, arco_fogo, cadencia_tiro, calibre, polegada, id_arma)
        VALUES (20, 30, 90, 1, 102, false, 15);
    INSERT INTO canhao(alcance_eficaz_aer, alcance_eficaz_sup, arco_fogo, cadencia_tiro, calibre, polegada, id_arma)
        VALUES (20, 30, 90, 1, 114, false, 16);
    INSERT INTO canhao(alcance_eficaz_aer, alcance_eficaz_sup, arco_fogo, cadencia_tiro, calibre, polegada, id_arma)
        VALUES (20, 30, 90, 1, 152, false, 17);


    --Mísseis
        
        --ALENIA-Marconi
INSERT INTO arma(id_arma, nome, alcance_max, raio_letalidade)     VALUES (18, 'Aspide-2000', 11.88, 0.0262);

    INSERT INTO hidra.missil_sup_sup(altitude_voo, tipo_guiagem, arco_fogo, id_arma)
        VALUES (20, 'ATV', 90,  18);


        --Boeing
INSERT INTO arma(id_arma, nome, alcance_max, raio_letalidade)     VALUES (19, 'Harpoon RGM 84D', 43.19, 0.0162);
    
    INSERT INTO hidra.missil_sup_sup(altitude_voo, tipo_guiagem, arco_fogo, id_arma)
        VALUES (20, 'ATV', 90,  19);

        --Denel
INSERT INTO arma(id_arma, nome, alcance_max, raio_letalidade)     VALUES (20, 'Umkhonto-IR', 6.48, 0.0262);

    INSERT INTO hidra.missil_sup_sup(altitude_voo, tipo_guiagem, arco_fogo, id_arma)
        VALUES (20, 'ATV', 90,  20);

        --MDBA
INSERT INTO arma(id_arma, nome, alcance_max, raio_letalidade)     VALUES (21, 'EXOCET MM-38', 20.52, 0.0216);
INSERT INTO arma(id_arma, nome, alcance_max, raio_letalidade)     VALUES (22, 'EXOCET MM-40', 37.8, 0.027);
INSERT INTO arma(id_arma, nome, alcance_max, raio_letalidade)     VALUES (23, 'GWS-25 Mod.0/3 «Seawolf»', 2.7, 0.0047);
INSERT INTO arma(id_arma, nome, alcance_max, raio_letalidade)     VALUES (24, 'GWS-26 «Seawolf»', 5.4, 0.0057);

    INSERT INTO hidra.missil_sup_sup(altitude_voo, tipo_guiagem, arco_fogo, id_arma)
        VALUES (20, 'ATV', 90,  21);    
    INSERT INTO hidra.missil_sup_sup(altitude_voo, tipo_guiagem, arco_fogo, id_arma)
        VALUES (20, 'ATV', 90,  22);
    INSERT INTO hidra.missil_sup_sup(altitude_voo, tipo_guiagem, arco_fogo, id_arma)
        VALUES (20, 'ATV', 90,  23);
    INSERT INTO hidra.missil_sup_sup(altitude_voo, tipo_guiagem, arco_fogo, id_arma)
        VALUES (20, 'ATV', 90,  24);

        --Raytheon Systems
INSERT INTO arma(id_arma, nome, alcance_max, raio_letalidade)     VALUES (25, 'RIM 116 Rolling Airframe Missile', 4.05,  0.01);
INSERT INTO arma(id_arma, nome, alcance_max, raio_letalidade)     VALUES (26, 'RIM-7M Seasparrow', 8.1, 0.0135);
INSERT INTO arma(id_arma, nome, alcance_max, raio_letalidade)     VALUES (27, 'SM-2MR (RIM-66B)', 86.39, 0.0257);

    INSERT INTO hidra.missil_sup_sup(altitude_voo, tipo_guiagem, arco_fogo, id_arma)
        VALUES (20, 'ATV', 90,  25);
    INSERT INTO hidra.missil_sup_sup(altitude_voo, tipo_guiagem, arco_fogo, id_arma)
        VALUES (20, 'ATV', 90,  26);
    INSERT INTO hidra.missil_sup_sup(altitude_voo, tipo_guiagem, arco_fogo, id_arma)
        VALUES (20, 'ATV', 90,  27);

   


--InformacaoVeiculoArma
    --Contra-Torpedeiros Pará
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (12, 1, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (1, 1, 1);

    --Corveta Barroso
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (22, 2, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (16, 2, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (2, 2, 1);

    --Corveta Drummond
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (21, 3, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (24, 3, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (3, 3, 1);

    --Corveta Espora
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (21, 4, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (6, 4, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (1, 4, 1);

    --Corveta Inhaúma
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (22, 5, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (16, 5, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (1, 5, 1);

    --Cruzador Leander
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (15, 6, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (17, 6, 1);


    --Cruzador Ticonderoga
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (19, 7, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (27, 7, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (5, 7, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (9, 7, 1);

    --Fragata Amatola
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (22, 8, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (20, 8, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (8, 8, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (4, 8, 1);

    --Fragata Bartolomeu Dias
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (19, 9, 2);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (8, 9, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (11, 9, 1);

    --Fragata Condell
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (19, 10, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (23, 10, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (16, 10, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (7, 10, 1);

    --Fragata Cornwall
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (19, 11, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (23, 11, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (16, 11, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (11, 11, 1);

    --Fragata Greenhalgh
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (20, 12, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (21, 12, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (1, 12, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (10, 12, 1);

    --Fragata NE Brasil
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (1, 13, 1);

    --Fragata Niterói
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (22, 14, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (18, 14, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (16, 14, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (2, 14, 1);

    --Fragata Simpson
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (19, 15, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (1, 15, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (9, 15, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (10, 15, 1);

    --NAE São Paulo
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (2, 16, 1);

    --NDD Ceará
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (14, 17, 1);

    --NPA Grajaú
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (1, 18, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (10, 18, 1);

    --NPA Macaé
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (1, 19, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (10, 19, 1);

    --Porta Helicópteros Wasp
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (26, 20, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (25, 20, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (9, 20, 1);
INSERT INTO info_veiculo_arma(id_arma, id_informacao_veiculo, quantidade)    VALUES (13, 20, 1);


--Veículos de Superfície Grupo A
INSERT INTO objeto_tatico(id_objeto_tatico)
    VALUES (9);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (9, 1, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (9);

INSERT INTO objeto_tatico(id_objeto_tatico)
    VALUES (10);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (10, 2, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (10);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (11);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (11, 3, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (11);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (12);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (12, 4, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (12);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (13);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (13, 5, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (13);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (14);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (14, 6, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (14);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (15);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (15, 7, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (15);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (16);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (16, 8, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (16);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (17);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (17, 9, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (17);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (18);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (18, 10, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (18);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (19);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (19, 11, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (19);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (20);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (20, 12, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (20);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (21);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (21, 13, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (21);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (22);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (22, 14, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (22);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (23);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (23, 15, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (23);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (24);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (24, 16, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (24);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (25);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (25, 17, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (25);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (26);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (26, 18, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (26);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (27);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (27, 19, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (27);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (28);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (28, 20, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (28);

-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (10, 2, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (11, 3, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (12, 4, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (13, 5, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (14, 6, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (15, 7, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (16, 8, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (17, 9, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (18, 10, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (19, 11, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (20, 12, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (21, 13, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (22, 14, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (23, 15, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (24, 16, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (25, 17, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (26, 18, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (27, 19, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (28, 20, false);

--Veículos de Superfície Grupo B
INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (29);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (29, 1, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (29);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (30);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (30, 2, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (30);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (31);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (31, 3, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (31);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (32);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (32, 4, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (32);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (33);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (33, 5, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (33);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (34);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (34, 6, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (34);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (35);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (35, 7, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (35);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (36);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (36, 8, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (36);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (37);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (37, 9, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (37);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (38);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (38, 10, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (38);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (39);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (39, 11, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (39);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (40);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (40, 12, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (40);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (41);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (41, 13, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (41);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (42);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (42, 14, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (42);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (43);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (43, 15, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (43);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (44);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (44, 16, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (44);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (45);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (45, 17, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (45);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (46);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (46, 18, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (46);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (47);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (47, 19, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (47);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (48);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (48, 20, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (48);

-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (29, 1, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (30, 2, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (31, 3, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (32, 4, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (33, 5, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (34, 6, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (35, 7, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (36, 8, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (37, 9, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (38, 10, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (39, 11, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (40, 12, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (41, 13, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (42, 14, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (43, 15, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (44, 16, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (45, 17, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (46, 18, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (47, 19, false);
-- INSERT INTO veiculo_superficie (id_objeto_tatico, id_informacao_veiculo, primario) VALUES (48, 20, false);


-- INSERT INTO maquina_associada_veiculo (id_veiculo,id_maquina,id_usuario) VALUES (1, 1, 1);
-- INSERT INTO maquina_associada_veiculo (id_veiculo,id_maquina,id_usuario) VALUES (1, 2, 1);

INSERT INTO configuracao_equipamento VALUES (1, true, false, 4444);
INSERT INTO configuracao_equipamento VALUES (2, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (3, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (4, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (5, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (6, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (7, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (8, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (9, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (10, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (11, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (12, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (13, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (14, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (15, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (16, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (17, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (18, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (19, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (20, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (21, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (22, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (23, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (24, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (25, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (26, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (27, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (28, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (29, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (30, true, false, 4445);
INSERT INTO configuracao_equipamento VALUES (31, true, false, 4445);

--Equipamento:

    --SELEX Sistemi
    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (1, 'RADAR_DT', 'AN/SPG-35');

    --Thomson-CSF / Thales
    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (2, 'RADAR_DT', 'DRBC-32');

    --Lockeed Martin    
    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (3, 'RADAR_DT', 'AN/SPY-1A');

    --SELEX Sistemi
    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (4, 'RADAR_DT', 'RTN-10 X');

    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (5, 'RADAR_DT', 'RTN-30 X');

    --Thales Nederland     
    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (6, 'RADAR_DT', 'STIR MK92');

    --BAE Systems    
    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (7, 'RADAR_DT', 'Type 910');

    --Thales Nederland 
    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (8, 'RADAR_DT', 'WM 28');
    
    --Thomson-CSF    
    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (9, 'RADAR_DT', 'Agave');

    --Northrop Grumman     
    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (10, 'RADAR_DT', 'AN/SPQ-9');
    
    --Raytheon Systems 
    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (11, 'RADAR_DT', 'AN/SPS-10 F');

    --Northrop-Grumman    
    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (12, 'RADAR_DT', 'AN/SPS-40 D');
    
    --ITT Radar Systems / Gilfillan    
    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (13, 'RADAR_DT', 'AN/SPS-48');
    
    --Raytheon Systems     
    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (14, 'RADAR_DT', 'AN/SPS-49');

    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (15, 'RADAR_DT', 'AN/SPS-55');

    --Siemens-Plessey
    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (16, 'RADAR_DT', 'AWS-2');

    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (17, 'RADAR_DT', 'AWS-4');

    --Thales Nederland     
    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (18, 'RADAR_DT', 'DA-05');

    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (19, 'RADAR_DT', 'DA-08');

    --RACAL-DECCA 
    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (20, 'RADAR_DT', '1230 C');

    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (21, 'RADAR_DT', 'RM 1290 A');

    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (22, 'RADAR_DT', 'TM 1226 C');

        
    --Thomson-CSF / Thales    
    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (23, 'RADAR_DT', 'DRBV-15 <<Sea Tiger>>');

    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (24, 'RADAR_DT', 'DRBV-23');

    --Thales     
    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (25, 'RADAR_DT', 'DRBV 51 A <<Triton>>');

    --FURUNO    
    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (26, 'RADAR_DT', 'FR-1941');

    --Thales Nederland     
    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (27, 'RADAR_DT', 'Smart-S');
    
    --Kelvin Hughes    
    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (28, 'RADAR_DT', 'KH-1006');

    --ALENIA-Marconi     
    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (29, 'RADAR_DT', 'Type 967');

    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (30, 'RADAR_DT', 'Type 968');

    --Hollandse Signaalapparaten 
    INSERT INTO equipamento (id_sensor, tipo , nome)
        VALUES (31, 'RADAR_DT', 'ZWO-6');
   

--Grupo A

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 11, 11);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 12, 12);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (1, 1, 1);

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 22, 22);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (2, 5, 5);

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (3, 25, 25);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (3, 22, 22);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (3, 2, 2);

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (4, 22, 22);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (4, 18, 18);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (4, 8, 8);

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 22, 22);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 17, 17);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (5, 4, 4);

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (6, 12, 12);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (6, 11, 11);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (6, 26, 26);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (6, 1, 1);

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (7, 10, 10);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (7, 14, 14);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (7, 15, 15);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (7, 3, 3);

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (8, 22, 22);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (8, 31, 31);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (8, 19, 19);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (8, 6, 6);

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (9, 22, 22);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (9, 27, 27);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (9, 6, 6);

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (10, 28, 28);

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (11, 29, 29);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (11, 30, 30);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (11, 28, 28);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (11, 7, 7);

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (12 , 29, 29);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (12 , 30, 30);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (12, 28, 28);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (12, 7, 7);

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (13, 22, 22);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (13, 20, 20);

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (14, 16, 16);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (14, 31, 31);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (14, 26, 26);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (14, 9, 9);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (14, 4, 4);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (14, 5, 5);

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (15, 14, 14);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (15, 15, 15);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (15, 6, 6);

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (16, 23, 23);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (16, 24, 24);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (16, 28, 28);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (16, 5, 5);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (16, 22, 22);

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (17, 26, 26);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (17, 11, 11);

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (18, 18, 18);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (18, 21, 21);

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (19, 28, 28);

INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (20,13,13);
INSERT INTO info_veiculo_equip_config(id_info_veiculo, id_equipamento, id_config_equip) VALUES (20, 14, 14);



INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (8.639312, 1310, 1265, 8000, 6.5, 1);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (11.879054, 10000, 8000, 1500, 10, 2);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (120.950368, 5890, 1530, 235, 0.7, 3);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (12.958968, 9400, 8600, 1520, 0.5, 4);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (21.058323, 9500, 8500, 1520, 0.5, 5);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (75.59398, 10000, 9220, 2500, 30, 6);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (19.749467232, 8800, 8700, 1417, 1.9, 7);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (12.958968, 9150, 8950, 1710, 0.5, 8);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (23.758108, 9350, 9300, 490, 1, 9);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (19.978409, 6900, 6200, 3145, 1.4, 10);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (56.155528, 5825, 5450, 625, 1.3, 11);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (105.831572, 448, 422, 300, 60, 12);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (109.611271, 3100, 2900, 2000, 3, 13);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (143.088605, 1260, 851, 833, 2, 14);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (19.978409, 10000, 9000, 2252, 1.3, 15);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (72.894195, 3100, 2700, 760, 1.3, 16);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (143.088605, 3100, 2700, 680, 1, 17);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (72.354238, 3000, 2940, 490, 2.15, 18);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (90.712776, 3400, 3300, 500, 4.5, 19);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (120, 3060, 3040, 850, 1, 20);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (20.518366, 9440, 9380, 650, 1, 21);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (14.578839, 9440, 9380, 850, 1, 22);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (50.755958, 3150, 2850, 800, 16, 23);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (87.473034, 1350, 1215, 211, 4, 24);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (15.118796, 5825, 5450, 1000, 0.45, 25);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (16, 9440, 9380, 600, 0.8, 26);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (37.79699, 1000, 2000, 3800, 0.6, 27);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (19.978409, 10000, 8000, 800, 0.75, 28);
   

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (48.59613, 1310, 1265, 8000, 6.5, 29);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (48.59613, 3050, 2950, 740, 2,30);

INSERT INTO radar(
            alcance_medio, frequencia_maxima, frequencia_minima, frequencia_repeticao_pulso, 
            largura_pulso, id_sensor)
    VALUES (11.879054, 9600, 8600, 1520, 0.6, 31);


INSERT INTO radar_dt(id_sensor) VALUES (1);
INSERT INTO radar_dt(id_sensor) VALUES (2);
INSERT INTO radar_dt(id_sensor) VALUES (3);
INSERT INTO radar_dt(id_sensor) VALUES (4);
INSERT INTO radar_dt(id_sensor) VALUES (5);
INSERT INTO radar_dt(id_sensor) VALUES (6);
INSERT INTO radar_dt(id_sensor) VALUES (7);
INSERT INTO radar_dt(id_sensor) VALUES (8);
INSERT INTO radar_dt(id_sensor) VALUES (9);
INSERT INTO radar_dt(id_sensor) VALUES (10);
INSERT INTO radar_dt(id_sensor) VALUES (11);
INSERT INTO radar_dt(id_sensor) VALUES (12);
INSERT INTO radar_dt(id_sensor) VALUES (13);
INSERT INTO radar_dt(id_sensor) VALUES (14);
INSERT INTO radar_dt(id_sensor) VALUES (15);
INSERT INTO radar_dt(id_sensor) VALUES (16);
INSERT INTO radar_dt(id_sensor) VALUES (17);
INSERT INTO radar_dt(id_sensor) VALUES (18);
INSERT INTO radar_dt(id_sensor) VALUES (19);
INSERT INTO radar_dt(id_sensor) VALUES (20);
INSERT INTO radar_dt(id_sensor) VALUES (21);
INSERT INTO radar_dt(id_sensor) VALUES (22);
INSERT INTO radar_dt(id_sensor) VALUES (23);
INSERT INTO radar_dt(id_sensor) VALUES (24);
INSERT INTO radar_dt(id_sensor) VALUES (25);
INSERT INTO radar_dt(id_sensor) VALUES (26);
INSERT INTO radar_dt(id_sensor) VALUES (27);
INSERT INTO radar_dt(id_sensor) VALUES (28);
INSERT INTO radar_dt(id_sensor) VALUES (29);
INSERT INTO radar_dt(id_sensor) VALUES (30);
INSERT INTO radar_dt(id_sensor) VALUES (31);















