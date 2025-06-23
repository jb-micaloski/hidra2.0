INSERT INTO maquina (id_maquina,hostname)  VALUES (11,  'GSD-055');
-- INSERT INTO usuario VALUES (13, 8, 'sar', '03AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4');
-- INSERT INTO usuario_func (id_funcionalidade,id_usuario, interagir,posicao) VALUES (24, 13,true,1);
-- INSERT INTO usuario_func (id_funcionalidade,id_usuario, interagir,posicao) VALUES (25, 13,true,2);
-- INSERT INTO usuario_func (id_funcionalidade,id_usuario, interagir,posicao) VALUES (26, 13,true,3);
INSERT INTO usuario_maquina VALUES (11, 13, true);

INSERT INTO dados_ambientais (id_dados_ambientais, declinacao_magnetica, direcao_corrente, direcao_vento, estado_mar, hemisferio, mdr1, mdr2, nuvens, topo_nuvens, profundidade_camada, profundidade_local, velocidade_corrente, velocidade_vento, visibilidade, chuva, chance_raios) VALUES (1, 12, 20, 30, 2, 'E', 90, 120, 1000, 1500, 250, 1000, 1, 5, 12, 5, 20);

INSERT INTO teatro_operacao (id_teatro_operacao,hora_inicio,latitude,longitude,nome,id_dados_ambientais,descricao) VALUES (1, 1420106400444, -26.38893921489483, -43.65716347228855, 'SAR_DINÂMICO', 1, 'Sar Dinâmico');

-- AREA SAR --
INSERT INTO area_poligonal (id_objeto_tatico, nome, aberto) 
    VALUES (51,'AREA SAR SUESTE', false);
INSERT INTO sar_area_sar (id_objeto_tatico) 
    VALUES (51);

insert into vertice values (1,-19.0163700747716,-31.6670988488185,16,51);
insert into vertice values (2,-23.0112742909912,-43.587965202798,29,51);
insert into vertice values (3,-18.4406888730616,-25.0013785240405,9,51);
insert into vertice values (4,-22.5200929361772,-24.4504925467861,5,51);
insert into vertice values (5,-22.7626469858639,-43.3707452393921,27,51);
insert into vertice values (6,-20.0665277674326,-40.2058314962615,20,51);
insert into vertice values (7,-34.0758614216857,-34.8622375168939,2,51);
insert into vertice values (8,-18.7767442700626,-39.9303885076343,19,51);
insert into vertice values (9,-27.5186363762781,-5.50001492923528,4,51);
insert into vertice values (10,-17.6700763427564,-30.0695295147808,14,51);
insert into vertice values (11,-17.2351236360775,-28.967757560272,13,51);
insert into vertice values (12,-21.9602915372649,-41.0300772141561,22,51);
insert into vertice values (13,-19.685453758261,-32.0527190328966,17,51);
insert into vertice values (14,-24.1364115539492,-46.9497769668965,31,51);
insert into vertice values (15,-25.3428928987161,-48.0835009709992,1,51);
insert into vertice values (16,-20.5415544765214,-32.3281620215238,18,51);
insert into vertice values (17,-17.4285656190657,-26.6540364558036,11,51);
insert into vertice values (18,-21.2040851232222,-41.3076034507702,21,51);
insert into vertice values (19,-22.9584134723208,-43.2335536835568,28,51);
insert into vertice values (20,-22.8632113492234,-44.4282634822893,30,51);
insert into vertice values (21,-22.8367543251942,-43.0220500349773,25,51);
insert into vertice values (22,-21.0150927910025,-23.7343407763554,6,51);
insert into vertice values (23,-22.5416793880615,-42.103784021137,23,51);
insert into vertice values (24,-20.1140966134409,-23.8445179718063,7,51);
insert into vertice values (25,-22.656708782492,-43.1192273870273,26,51);
insert into vertice values (26,-17.2835034571878,-27.7558084103124,12,51);
insert into vertice values (27,-18.2483573157437,-31.1162128715641,15,51);
insert into vertice values (28,-22.8414326678953,-42.0599592535051,24,51);
insert into vertice values (29,-34.0758614216857,-5.55510352696072,3,51);
insert into vertice values (30,-19.1121228771877,-24.2301381558843,8,51);

-- AREA SAR --
INSERT INTO area_poligonal (id_objeto_tatico, nome, aberto) 
    VALUES (52,'AREA SAR 02', false);
INSERT INTO sar_area_sar (id_objeto_tatico) 
    VALUES (52);

insert into vertice values (31,-15.6661218819954,-38.9770537994079,3,52);
insert into vertice values (32,-18.1668234535782,-30.6805175770453,21,52);
insert into vertice values (33,-19.1668295262753,-31.6630021296935,23,52);
insert into vertice values (34,-18.786579755255,-40.0141208272033,26,52);
insert into vertice values (35,-17.4009860844597,-39.1408012248493,2,52);
insert into vertice values (36,-11.8084739592403,-37.4487444952885,6,52);
insert into vertice values (37,-10.3279912682489,-36.2479300420518,7,52);
insert into vertice values (38,-22.6402946800115,-24.2397855096849,10,52);
insert into vertice values (39,-18.5008201924548,-25.1131051120388,15,52);
insert into vertice values (40,-17.7364485445523,-25.9864247143928,16,52);
insert into vertice values (41,-21.7096292617112,-5.51799653422184,8,52);
insert into vertice values (42,-19.9719016588818,-23.7485432333608,12,52);
insert into vertice values (43,-17.3530116750533,-28.6063835214547,18,52);
insert into vertice values (44,-19.8773994152898,-32.2634093563119,24,52);
insert into vertice values (45,-19.0243388473705,-24.4581154102734,14,52);
insert into vertice values (46,-17.7364485445523,-30.1346928255741,20,52);
insert into vertice values (47,-13.7697575322606,-39.0316362745551,4,52);
insert into vertice values (48,-18.7389870041886,-40.0687033023504,1,52);
insert into vertice values (49,-21.6162222083142,-23.8031257085079,11,52);
insert into vertice values (50,-17.3530116750533,-29.2613732232201,19,52);
insert into vertice values (51,-12.8890946230672,-39.0316362745551,5,52);
insert into vertice values (52,-19.5461937203106,-24.0214556090964,13,52);
insert into vertice values (53,-17.4009860844597,-26.968909267041,17,52);
insert into vertice values (54,-27.5961530374711,-5.46341405907472,9,52);
insert into vertice values (55,-20.4906341233636,-32.5363217320475,25,52);
insert into vertice values (56,-18.5008201924548,-31.2263423285165,22,52);

-- AREA SAR --
INSERT INTO area_poligonal (id_objeto_tatico, nome, aberto) 
    VALUES (53,'AREA SAR 03', false);
INSERT INTO sar_area_sar (id_objeto_tatico) 
    VALUES (53);

insert into vertice values (57,-6.2909832327237,-5.43750471847055,4,53);
insert into vertice values (58,-5.10166554973427,-36.0055925387319,19,53);
insert into vertice values (59,-10.3238920168599,-36.2491390692066,2,53);
insert into vertice values (60,-21.7350368592437,-5.54533508539128,3,53);
insert into vertice values (61,-6.2909832327237,-12.0151571006345,5,53);
insert into vertice values (62,-2.91003388387708,-40.8728876472284,9,53);
insert into vertice values (63,-10.2933803116288,-36.2909599639555,1,53);
insert into vertice values (64,-8.94698403252387,-35.2153086934736,24,53);
insert into vertice values (65,-3.45514841370195,-39.11323288834,12,53);
insert into vertice values (66,-10.1547743714439,-36.2047385545499,26,53);
insert into vertice values (67,9.26857161464283,-34.664006013539,7,53);
insert into vertice values (68,-8.0213276954934,-34.8893755526189,23,53);
insert into vertice values (69,-5.44847858539794,-35.3309161151394,21,53);
insert into vertice values (70,-2.87061705685475,-39.9374577939903,10,53);
insert into vertice values (71,-4.75522121091612,-37.3188303337049,16,53);
insert into vertice values (72,-6.48083123566726,-35.0786072222706,22,53);
insert into vertice values (73,9.26857161464283,-36.9992529850088,8,53);
insert into vertice values (74,-5.19611803360696,-35.5337705764662,20,53);
insert into vertice values (75,-5.15676456058005,-36.7133254821305,18,53);
insert into vertice values (76,-4.45913040274127,-37.8311052573284,14,53);
insert into vertice values (77,-4.67645876500925,-37.5783324129511,15,53);
insert into vertice values (78,-5.00719897819639,-37.2166022418807,17,53);
insert into vertice values (79,-3.31068906858544,-39.2309793034329,11,53);
insert into vertice values (80,8.94142224470508,-34.1742975028427,6,53);
insert into vertice values (81,-9.59063407182969,-35.7377276601219,25,53);
insert into vertice values (82,-3.77681733444468,-38.4787105403393,13,53);

-- AREA SAR --
INSERT INTO area_poligonal (id_objeto_tatico, nome, aberto) 
    VALUES (54,'AREA SAR 04', false);
INSERT INTO sar_area_sar (id_objeto_tatico) 
    VALUES (54);

insert into vertice values (83,7.85551410152003,-47.9615352362121,2,54);
insert into vertice values (84,3.7654639290862,-51.5021591820235,1,54);
insert into vertice values (85,-1.48629662216678,-50.9686405052574,12,54);
insert into vertice values (86,2.25975530613372,-50.8231354115939,15,54);
insert into vertice values (87,-1.80601247677867,-52.0356778587896,13,54);
insert into vertice values (88,-2.30830407574366,-49.7560980580617,10,54);
insert into vertice values (89,-0.938108735793876,-47.8645318404364,9,54);
insert into vertice values (90,3.40064573241157,-50.9686405052574,16,54);
insert into vertice values (91,9.52641947695067,-36.9516498156752,4,54);
insert into vertice values (92,-1.98868230722494,-50.8231354115939,11,54);
insert into vertice values (93,-2.90168655661205,-40.8802873445892,5,54);
insert into vertice values (94,9.43629853128017,-47.9615352362121,3,54);
insert into vertice values (95,-2.71913794868226,-43.3538739368684,6,54);
insert into vertice values (96,-2.44526312974752,-43.8873926136345,7,54);
insert into vertice values (97,-1.89734983585061,-44.5664163840641,8,54);
insert into vertice values (98,1.39204601975683,-49.8045997559495,14,54);

-- AREA SAR --
INSERT INTO area_poligonal (id_objeto_tatico, nome, aberto) 
    VALUES (55,'AREA SAR 05', false);
INSERT INTO sar_area_sar (id_objeto_tatico) 
    VALUES (55);

insert into vertice values (99,-25.8604467366629,-48.5988665916096,6,55);
insert into vertice values (100,-33.1627633903312,-52.6599162016614,12,55);
insert into vertice values (101,-29.7826470544058,-50.1641091605578,8,55);
insert into vertice values (102,-32.5191187241841,-52.4304346405881,11,55);
insert into vertice values (103,-33.7802898838991,-53.4172162715884,1,55);
insert into vertice values (104,-30.4826009245493,-50.3192042787137,9,55);
insert into vertice values (105,-28.4338957138691,-48.6870492715503,7,55);
insert into vertice values (106,-33.7829504569971,-53.394257197096,13,55);
insert into vertice values (107,-25.3289191081532,-48.2664733345058,5,55);
insert into vertice values (108,-32.2146021801636,-52.2239012356222,10,55);
insert into vertice values (109,-35.7870918735548,-50.2725521626327,2,55);
insert into vertice values (110,-33.9873152530616,-47.9953816009751,3,55);
insert into vertice values (111,-33.9873152530616,-35.0914150849156,4,55);

-- SALVAMAR -- 
INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (56);
INSERT INTO sar_salva_mar (id_objeto_tatico, latitude, longitude, nome, qtdsru) 
    VALUES (56, -22.3747565246263, -41.765757841076,'SALVAMAR SUESTE',5);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (57);
INSERT INTO sar_salva_mar (id_objeto_tatico, latitude, longitude, nome, qtdsru) 
    VALUES (57,-13.0077,-38.5453666666667,'SALVAMAR 02',5);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (58);
INSERT INTO sar_salva_mar (id_objeto_tatico, latitude, longitude, nome, qtdsru) 
    VALUES (58,-5.76191666666667,-35.1933,'SALVAMAR 03',5);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (59);
INSERT INTO sar_salva_mar (id_objeto_tatico, latitude, longitude, nome, qtdsru) 
    VALUES (59,-1.21765,-48.5703833333333,'SALVAMAR 04',5);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (60);
INSERT INTO sar_salva_mar (id_objeto_tatico, latitude, longitude, nome, qtdsru) 
    VALUES (60,-32.1490166666667,-52.0919833333333,'SALVAMAR 05',5);

-- DISTRITO --
insert into sar_condicao_ambiental values (1,144,15,1,1,1,1,1);

insert into sar_condicao_ambiental values (2,144,15,1,1,1,1,1);

insert into sar_condicao_ambiental values (3,144,15,1,1,1,1,1);

insert into sar_condicao_ambiental values (4,144,15,1,1,1,1,1);

insert into sar_condicao_ambiental values (5,144,15,1,1,1,1,1);


insert into sar_distrito values (1,'DISTRITO SUESTE',51,1,56);

insert into sar_distrito values (2,'DISTRITO 02',52,2,57);

insert into sar_distrito values (3,'DISTRITO 03',53,3,58);

insert into sar_distrito values (4,'DISTRITO 04',54,4,59);

insert into sar_distrito values (5,'DISTRITO 05',55,5,60);
SELECT setval('sar_distrito_seq', 5);


--SRU--
INSERT INTO condicao_inicial(id_condicao_inicial,id_teatro_operacao,identificador) 
                     VALUES (1, 1,'SRU1');

INSERT INTO condicao_entidade(altitude, latitude,longitude,na,rumo,velocidade,id_objeto_tatico,
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES (0, 0, 0, '1111', 0, 0, 9, 'S', 'S', true, 'A', 1);

INSERT INTO condicao_inicial(id_condicao_inicial,id_teatro_operacao,identificador) 
                     VALUES (2, 1,'SRU1');

INSERT INTO condicao_entidade(altitude, latitude,longitude,na,rumo,velocidade,id_objeto_tatico,
            detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
    VALUES (0, 0, 0, '1212', 0, 0, 10, 'S', 'S', true, 'A', 2);

-- INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao) 
--     VALUES (1, 0, 0, 0, '1111', 0, 0, 9, 1 );
-- 
-- INSERT INTO condicao_inicial(id_condicao_inicial, altitude, latitude,longitude, na, rumo, velocidade, id_objeto_tatico, id_teatro_operacao) 
--     VALUES (2, 0, 0, 0, '1212', 0, 0, 10, 1 );
-- 
-- INSERT INTO condicao_veiculo(
--             detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
--     VALUES ('S', 'S', true, 'A', 1);
-- 
-- INSERT INTO condicao_veiculo(
--             detect_mage, detect_radar, detectabilidade_visual, grupo, id_condicao_inicial)
--     VALUES ('S', 'S', true, 'A', 2);

INSERT INTO bloco VALUES (1, '25', 1);

INSERT INTO plataforma VALUES (1, 'NAVIO', 15, 15, 40);
INSERT INTO plataforma VALUES (2, 'HELICOPTERO', 40, 80, 180);

INSERT INTO guinada_superficie (id_guinada_maritima,zero_a_cinco_nos,cinco_a_dez_nos, dez_a_quinze_nos,quinze_a_vinte_nos,vinte_a_vinte_cinco_nos,vinte_cinco_a_trinta_nos, trinta_a_trinta_cinco_nos,mais_de_trinta_cinco_nos) VALUES (1, 60, 70, 80, 90, 100, 110, 120, 130);
SELECT setval('guinada_maritima_seq', 1);

INSERT INTO guinada_helicoptero(id_guinada_helicoptero, mais_de_cem_nos, zero_a_cem_nos)    VALUES (1, 200, 250);
SELECT setval('guinada_helicoptero_seq', 1);

INSERT INTO plataforma_superficie ( movimento_in_out, num_eixos, num_pas_eixo, id_tipo_propulsao, capacidade_deslocamento, tipo_navio, vms, id_plataforma, id_taxa_guinada,  calado, comprimento_proa, comprimento_popa, largura) 
            VALUES (true, 1, 3, 'MOTOR', 1, 'FRG', 1, 1, 1, 0, 60, 40, 50);

INSERT INTO plataforma_helicoptero(altitude_maxima, capacidade_armamentos, id_tamanho, taxa_subida_descida, id_plataforma, id_taxa_guinada)
            VALUES (10000, 1, 'GRANDE', 100, 
            2, 1);

INSERT INTO modelo_3d(id_modelo_3d, modelo_veiculo_primario, modelo_veiculo_secundario) VALUES (1, 'F46OwnShip', 'F46 - NPCShip'); --TESTAR NULL

INSERT INTO info_veiculo (id_informacao_veiculo, custo_veiculo, nome, id_modelo_3d, id_plataforma) 
    VALUES (1, 20000000, 'Navio', 1, 1);
INSERT INTO info_veiculo_maritimo (id_informacao_veiculo, imo, mmsi, ros) 
    VALUES (1, 0, '710429000', 100);
INSERT INTO info_veiculo_superficie (id_informacao_veiculo, roncador, supressor_ruido)
    VALUES (1, true, false);

INSERT INTO info_veiculo (id_informacao_veiculo, nome, id_plataforma) 
    VALUES (2, 'Helicóptero',  2);
INSERT INTO info_veiculo_aereo (id_informacao_veiculo, autonomia) 
    VALUES (2, '10:00');
INSERT INTO info_veiculo_helicoptero (id_informacao_veiculo)
    VALUES (2);

--Veiculos
INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (9);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (9, 1, false);
INSERT INTO veiculo_superficie (id_objeto_tatico) 
    VALUES (9);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (10);
INSERT INTO veiculo (id_objeto_tatico, id_informacao_veiculo, primario)
    VALUES (10, 2, false);
INSERT INTO veiculo_helicoptero (id_objeto_tatico) 
    VALUES (10);

 --SAR_SRU
INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (61);
INSERT INTO sar_sru(id_objeto_tatico, nome, velocidademaxima, txaceleracao, txdesaceleracao, id_veiculo, preco_combustivel, autonomia) 
    VALUES (61, 'LB Achernar - CPSP 02', 10.2, 25, 35, 9, 2.88, 22.254458454545);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (62);
INSERT INTO sar_sru(id_objeto_tatico, nome, velocidademaxima, txaceleracao, txdesaceleracao, id_veiculo, preco_combustivel, autonomia) 
    VALUES (62, 'NPa Guajará (P-44)', 26, 25, 35, 9, 2.88, 108.901515152);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (63);
INSERT INTO sar_sru(id_objeto_tatico, nome, velocidademaxima, txaceleracao, txdesaceleracao, id_veiculo, preco_combustivel, autonomia) 
    VALUES (63, 'RbAM Almirante Guillobel (R-25)', 14, 25, 35, 9, 2.88, 67.19367588930);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (64);
INSERT INTO sar_sru(id_objeto_tatico, nome, velocidademaxima, txaceleracao, txdesaceleracao, id_veiculo, preco_combustivel, autonomia) 
    VALUES (64, 'RbAM Triunfo (R-23)', 12, 25, 35, 9, 2.88, 79.5454545455);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (65);
INSERT INTO sar_sru(id_objeto_tatico, nome, velocidademaxima, txaceleracao, txdesaceleracao, id_veiculo, preco_combustivel, autonomia) 
    VALUES (65, 'SRU 05', 23, 85, 135, 10, 2.88, 100);
INSERT INTO sar_sru_aereo(id_objeto_tatico, taxa_subida_descida) 
    VALUES (65, 100);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (66);
INSERT INTO sar_sru(id_objeto_tatico, nome, velocidademaxima, txaceleracao, txdesaceleracao, id_veiculo, preco_combustivel, autonomia) 
    VALUES (66, 'NPa Benevente (P-61)', 14, 25, 35, 9, 2.88, 108.901515152);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (67);
INSERT INTO sar_sru(id_objeto_tatico, nome, velocidademaxima, txaceleracao, txdesaceleracao, id_veiculo, preco_combustivel, autonomia) 
    VALUES (67, 'NPa Graúna (P-42)', 12, 25, 35, 9, 2.88, 61.158459596);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (68);
INSERT INTO sar_sru(id_objeto_tatico, nome, velocidademaxima, txaceleracao, txdesaceleracao, id_veiculo, preco_combustivel, autonomia) 
    VALUES (68, 'NPa Bracuí (P-60)', 14, 25, 35, 9, 2.88, 198.412698413);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (69);
INSERT INTO sar_sru(id_objeto_tatico, nome, velocidademaxima, txaceleracao, txdesaceleracao, id_veiculo, preco_combustivel, autonomia) 
    VALUES (69, 'NPa Parati (P-13)', 19, 25, 35, 9, 2.88, 40.5471564785);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (70);
INSERT INTO sar_sru_aereo(id_objeto_tatico, nome, velocidademaxima, txaceleracao, txdesaceleracao, id_veiculo,autonomia, preco_combustivel) 
    VALUES (70, 'SRU 10', 23, 85, 135, 10, 100, 2.88);
INSERT INTO sar_sru_aereo(id_objeto_tatico, taxa_subida_descida) 
    VALUES (70, 100);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (71);
INSERT INTO sar_sru(id_objeto_tatico, nome, velocidademaxima, txaceleracao, txdesaceleracao, id_veiculo, preco_combustivel, autonomia) 
    VALUES (71, 'NPaFlu Roraima (P-30)', 17.5, 25, 35, 9, 2.88, 45.58422155);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (72);
INSERT INTO sar_sru(id_objeto_tatico, nome, velocidademaxima, txaceleracao, txdesaceleracao, id_veiculo, preco_combustivel, autonomia) 
    VALUES (72, 'NPaFlu Pedro Teixeira (P-20)', 16, 25, 35, 9, 2.88, 61.158459596);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (73);
INSERT INTO sar_sru(id_objeto_tatico, nome, velocidademaxima, txaceleracao, txdesaceleracao, id_veiculo, preco_combustivel, autonomia) 
    VALUES (73, 'SRU 13', 30, 25, 35, 9, 2.88, 10);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (74);
INSERT INTO sar_sru(id_objeto_tatico, nome, velocidademaxima, txaceleracao, txdesaceleracao, id_veiculo, preco_combustivel, autonomia) 
    VALUES (74, 'SRU 14', 35, 25, 35, 9, 2.88, 10);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (75);
INSERT INTO sar_sru(id_objeto_tatico, nome, velocidademaxima, txaceleracao, txdesaceleracao, id_veiculo,autonomia, preco_combustivel) 
    VALUES (75, 'SRU 15', 23, 85, 135, 10, 100, 100, 2.88);
INSERT INTO sar_sru_aereo(id_objeto_tatico, nome, taxa_subida_descida)
    VALUES (75, 100);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (76);
INSERT INTO sar_sru(id_objeto_tatico, nome, velocidademaxima, txaceleracao, txdesaceleracao, id_veiculo, preco_combustivel, autonomia)  
    VALUES (76, 'SRU 16', 25, 25, 35, 9, 2.88, 10);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (77);
INSERT INTO sar_sru(id_objeto_tatico, nome, velocidademaxima, txaceleracao, txdesaceleracao, id_veiculo, preco_combustivel, autonomia) 
    VALUES (77, 'SRU 17', 30, 25, 35, 9, 2.88, 10);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (78);
INSERT INTO sar_sru(id_objeto_tatico, nome, velocidademaxima, txaceleracao, txdesaceleracao, id_veiculo, preco_combustivel, autonomia) 
    VALUES (78, 'SRU 18', 30, 25, 35, 9, 2.88, 10);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (79);
INSERT INTO sar_sru(id_objeto_tatico, nome, velocidademaxima, txaceleracao, txdesaceleracao, id_veiculo, preco_combustivel, autonomia)  
    VALUES (79, 'SRU 19', 35, 25, 35, 9, 2.88, 10);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (80);
INSERT INTO sar_sru(id_objeto_tatico, nome, velocidademaxima, txaceleracao, txdesaceleracao, id_veiculo,autonomia, preco_combustivel) 
    VALUES (80, 'SRU 20', 23, 80, 135, 10, 100, 100, 2.88);
INSERT INTO sar_sru_aereo(id_objeto_tatico, taxa_subida_descida) 
    VALUES (80, 100);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (81);
INSERT INTO sar_sru(id_objeto_tatico, nome, velocidademaxima, txaceleracao, txdesaceleracao, id_veiculo, preco_combustivel, autonomia) 
    VALUES (81, 'SRU 21', 25, 25, 35, 9, 2.88, 10);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (82);
INSERT INTO sar_sru(id_objeto_tatico, nome, velocidademaxima, txaceleracao, txdesaceleracao, id_veiculo, preco_combustivel, autonomia) 
    VALUES (82, 'SRU 22', 30, 25, 35, 9, 2.88, 10);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (83);
INSERT INTO sar_sru(id_objeto_tatico, nome, velocidademaxima, txaceleracao, txdesaceleracao, id_veiculo, preco_combustivel, autonomia) 
    VALUES (83, 'SRU 23', 30, 25, 35, 9, 2.88, 10);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (84);
INSERT INTO sar_sru(id_objeto_tatico, nome, velocidademaxima, txaceleracao, txdesaceleracao, id_veiculo, preco_combustivel, autonomia) 
    VALUES (84, 'SRU 24', 35, 25, 35, 9, 2.88, 10);

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (85);
INSERT INTO sar_sru_aereo(id_objeto_tatico, nome, velocidademaxima, txaceleracao, txdesaceleracao, id_veiculo,autonomia, preco_combustivel) 
    VALUES (85, 'SRU 25', 23, 80, 130, 10, 100, 100, 2.88);
INSERT INTO sar_sru_aereo(id_objeto_tatico, taxa_subida_descida) 
    VALUES (85, 100);

-- SRUS ASSOCIACOS AO SALVAMAR 01--
INSERT INTO sar_salva_mar_sar_sru(sar_salva_mar_id_objeto_tatico, veiculos_id_objeto_tatico) VALUES (56, 61);
INSERT INTO sar_salva_mar_sar_sru(sar_salva_mar_id_objeto_tatico, veiculos_id_objeto_tatico) VALUES (56, 62);
INSERT INTO sar_salva_mar_sar_sru(sar_salva_mar_id_objeto_tatico, veiculos_id_objeto_tatico) VALUES (56, 63);
INSERT INTO sar_salva_mar_sar_sru(sar_salva_mar_id_objeto_tatico, veiculos_id_objeto_tatico) VALUES (56, 64);
INSERT INTO sar_salva_mar_sar_sru(sar_salva_mar_id_objeto_tatico, veiculos_id_objeto_tatico) VALUES (56, 66);

-- SRUS ASSOCIACOS AO SALVAMAR 02--
INSERT INTO sar_salva_mar_sar_sru(sar_salva_mar_id_objeto_tatico, veiculos_id_objeto_tatico) VALUES (57, 65);
INSERT INTO sar_salva_mar_sar_sru(sar_salva_mar_id_objeto_tatico, veiculos_id_objeto_tatico) VALUES (57, 67);
INSERT INTO sar_salva_mar_sar_sru(sar_salva_mar_id_objeto_tatico, veiculos_id_objeto_tatico) VALUES (57, 68);
INSERT INTO sar_salva_mar_sar_sru(sar_salva_mar_id_objeto_tatico, veiculos_id_objeto_tatico) VALUES (57, 69);
INSERT INTO sar_salva_mar_sar_sru(sar_salva_mar_id_objeto_tatico, veiculos_id_objeto_tatico) VALUES (57, 70);

-- SRUS ASSOCIACOS AO SALVAMAR 03--
INSERT INTO sar_salva_mar_sar_sru(sar_salva_mar_id_objeto_tatico, veiculos_id_objeto_tatico) VALUES (58, 71);
INSERT INTO sar_salva_mar_sar_sru(sar_salva_mar_id_objeto_tatico, veiculos_id_objeto_tatico) VALUES (58, 72);
INSERT INTO sar_salva_mar_sar_sru(sar_salva_mar_id_objeto_tatico, veiculos_id_objeto_tatico) VALUES (58, 73);
INSERT INTO sar_salva_mar_sar_sru(sar_salva_mar_id_objeto_tatico, veiculos_id_objeto_tatico) VALUES (58, 74);
INSERT INTO sar_salva_mar_sar_sru(sar_salva_mar_id_objeto_tatico, veiculos_id_objeto_tatico) VALUES (58, 75);

-- SRUS ASSOCIACOS AO SALVAMAR 04--
INSERT INTO sar_salva_mar_sar_sru(sar_salva_mar_id_objeto_tatico, veiculos_id_objeto_tatico) VALUES (59, 76);
INSERT INTO sar_salva_mar_sar_sru(sar_salva_mar_id_objeto_tatico, veiculos_id_objeto_tatico) VALUES (59, 77);
INSERT INTO sar_salva_mar_sar_sru(sar_salva_mar_id_objeto_tatico, veiculos_id_objeto_tatico) VALUES (59, 78);
INSERT INTO sar_salva_mar_sar_sru(sar_salva_mar_id_objeto_tatico, veiculos_id_objeto_tatico) VALUES (59, 79);
INSERT INTO sar_salva_mar_sar_sru(sar_salva_mar_id_objeto_tatico, veiculos_id_objeto_tatico) VALUES (59, 80);

-- SRUS ASSOCIACOS AO SALVAMAR 05--
INSERT INTO sar_salva_mar_sar_sru(sar_salva_mar_id_objeto_tatico, veiculos_id_objeto_tatico) VALUES (60, 81);
INSERT INTO sar_salva_mar_sar_sru(sar_salva_mar_id_objeto_tatico, veiculos_id_objeto_tatico) VALUES (60, 82);
INSERT INTO sar_salva_mar_sar_sru(sar_salva_mar_id_objeto_tatico, veiculos_id_objeto_tatico) VALUES (60, 83);
INSERT INTO sar_salva_mar_sar_sru(sar_salva_mar_id_objeto_tatico, veiculos_id_objeto_tatico) VALUES (60, 84);
INSERT INTO sar_salva_mar_sar_sru(sar_salva_mar_id_objeto_tatico, veiculos_id_objeto_tatico) VALUES (60, 85);


-- INCIDENTES --
--DISTRITO 1--
INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (86);
INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
    VALUES (86, -20.9249727, -40.7613298, 'Média de incidentes 1');

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (87);
INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
    VALUES (87, -22.9223094, -43.1361063, 'Média de incidentes 2');

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (88);
INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
    VALUES (88, -22.7696170, -43.1558223, 'Média de incidentes 3');

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (89);
INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
    VALUES (89,-23.1100940, -44.1554320, 'Média de incidentes 4');

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (90);
INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
    VALUES (90, -24.5552760, -42.9848830, 'Média de incidentes 5');

-- INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
--     VALUES (86, -21.5575111122555, -38.7304682160882, 'Afundamento');
-- INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
--     VALUES (87, -22.8741853920071, -39.0964777954498, 'Incidente desconhecido');
-- INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
--     VALUES (88, -23.5967756921866, -38.5308266273454, 'Desaparecido');
-- INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
--     VALUES (89, -24.0406380545861, -41.0762568838151, 'Desaparecido');
-- INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
--     VALUES (90, -23.7346901992058, -42.8563943834378, 'Afundamento');
-- INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
--     VALUES (91, -23.4587143856303, -43.4220455515421, 'Desaparecido');
--OCORRENCIA--
INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
    VALUES (1, '28/10/2015 13:34:20', 86, 56);
INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
    VALUES (2, '28/9/2015 08:30:20', 87, 56);
INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
    VALUES (3, '28/8/2015 04:20:00', 88, 56);
INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
    VALUES (4, '28/10/2015 12:11:50', 89, 56);
INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
    VALUES (5, '28/10/2015 06:67:26', 90, 56);
-- INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
--     VALUES (6, '28/10/2015 08:34:20', 91, 56);



--DISTRITO 2--
INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (92);
INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
    VALUES (92, -20.2422710, -39.5812623, 'Média de incidentes 6');

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (93);
INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
    VALUES (93, -23.0213540, -44.4652520, 'Média de incidentes 7');

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (94);
INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
    VALUES (94, -19.6595760, -39.8165270, 'Média de incidentes 8');

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (95);
INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
    VALUES (95, -21.6231020, -40.9689510, 'Média de incidentes 9');

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (96);
INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
    VALUES (96, -18.4947930, -39.5566740, 'Média de incidentes 10');
-- INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
--     VALUES (92, -15.1595926416304, -36.3347691511755, 'Mancha de óleo');
-- INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
--     VALUES (93, -13.5376765294923, -35.2533772121524, 'Afundamento');
-- INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
--     VALUES (94, -17.7600586486783, -35.4862923990189, 'Incidente desconhecido');
-- INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
--     VALUES (95, -14.8199866004866, -33.7727020956439, 'Avaria');
-- INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
--     VALUES (96, -12.1665044684529, -35.469655599957, 'Homem ao Mar');
-- INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
--     VALUES (97, -15.1595926416304, -33.7727020956439, 'Homem ao Mar');

--OCORRENCIA--
INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
    VALUES (7, '28/10/2015 13:34:20', 92, 57);
INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
    VALUES (8, '28/9/2015 08:30:20', 93, 57);
INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
    VALUES (9, '28/8/2015 04:20:00', 94, 57);
INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
    VALUES (10, '28/10/2015 12:11:50', 95, 57);
INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
    VALUES (11, '28/10/2015 06:67:26', 96, 57);
-- INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
--     VALUES (12, '28/10/2015 08:34:20', 97, 57);

--DISTRITO 3--
INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (98);
INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
    VALUES (98, -2.10988087001475, -39.6787657626161, 'Desaparecido');

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (99);
INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
    VALUES (99, -1.84205983485462, -39.5456713701209, 'Afundamento');

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (100);
INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
    VALUES (100, -4.75116663656694, -36.6508683333515, 'Mancha de óleo');

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (101);
INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
    VALUES (101, -7.31580137874897, -32.4916685678781, 'Homem ao Mar');

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (102);
INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
    VALUES (102, -8.72531252635457, -30.3954318860795, 'Desaparecido');

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (103);
INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
    VALUES (103, -8.39411491671829, -33.1904141284776, 'Mancha de óleo');

--OCORRENCIA--
INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
    VALUES (13, '28/10/2015 13:34:20', 98, 58);
INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
    VALUES (14, '28/9/2015 08:30:20', 99, 58);
INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
    VALUES (15, '28/8/2015 04:20:00', 100, 58);
INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
    VALUES (16, '28/10/2015 12:11:50', 101, 58);
INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
    VALUES (17, '28/10/2015 06:67:26', 102, 58);
INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
    VALUES (18, '28/10/2015 08:34:20', 103, 58);

--DISTRITO 4--

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (104);
INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
    VALUES (104, -0.887656917278664, -43.0061255749948, 'Incêndio');

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (105);
INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
    VALUES (105, -1.42350999871822, -43.5884135421611, 'Mancha de óleo');

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (106);
INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
    VALUES (106, 0.200986654859574, -46.8658629573541, 'Desaparecido');

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (107);
INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
    VALUES (107, 1.17234168377755, -45.2188198502266, 'Incidente desconhecido');

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (108);
INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
    VALUES (108, 3.21408357797417, -49.3613828166381, 'Afundamento');

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (109);
INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
    VALUES (109, 2.41112338357798, -50.0601283772377, 'Homem ao Mar');
--OCORRENCIA--
INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
    VALUES (19, '28/10/2015 13:34:20', 104, 59);
INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
    VALUES (20, '28/9/2015 08:30:20', 105, 59);
INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
    VALUES (21, '28/8/2015 04:20:00', 106, 59);
INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
    VALUES (22, '28/10/2015 12:11:50', 107, 59);
INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
    VALUES (23, '28/10/2015 06:67:26', 108, 59);
INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
    VALUES (24, '28/10/2015 08:34:20', 109, 59);

--DISTRITO 5--
INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (110);
INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
    VALUES (110, -33.6183947331392, -52.7053794280787, 'Mancha de óleo');

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (111);
INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
    VALUES (111, -34.1456719819942, -50.8753315312704, 'Homem ao Mar');

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (112);
INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
    VALUES (112, -32.4271356139974, -49.3114724194524, 'Desaparecido');

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (113);
INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
    VALUES (113, -30.7467685265563, -49.8604867884949, 'Afundamento');

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (114);
INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
    VALUES (114, -32.0736875275495, -48.5628164616672, 'Incidente desconhecido');

INSERT INTO objeto_tatico (id_objeto_tatico) 
    VALUES (115);
INSERT INTO sar_incidente_sar(id_objeto_tatico, latitudeinicial, longitudeinicial, tipo)
    VALUES (115, -31.647722468472, -47.814160503882, 'Desaparecido');
SELECT setval('objeto_tatico_seq', 115);

--OCORRENCIA--
INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
    VALUES (25, '28/10/2015 13:34:20', 110, 60);
INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
    VALUES (26, '28/9/2015 08:30:20', 111, 60);
INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
    VALUES (27, '28/8/2015 04:20:00', 112, 60);
INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
    VALUES (28, '28/10/2015 12:11:50', 113, 60);
INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
    VALUES (29, '28/10/2015 06:67:26', 114, 60);
INSERT INTO sar_ocorrencia_incidente_sar(id_ocorrencia, data_ocorrencia, id_incidente, id_salva_mar)
    VALUES (30, '28/10/2015 08:34:20', 115, 60);

SELECT setval('sar_ocorrencia_seq', 30);
