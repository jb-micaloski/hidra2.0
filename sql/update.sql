-- 29-09-2016 [Jonata Regis] - inserção de valor default da coluna 'velocidade_maxima'
UPDATE torpedo_submarino SET velocidade_maxima = 1 WHERE velocidade_maxima IS NULL;

-- 09-05-2015 [Paulo Collares] - Remoção da coluna 'link'
alter table info_veiculo_superficie drop column if exists link;
alter table info_veiculo_aviao drop column if exists link;
alter table info_veiculo_helicoptero drop column if exists link;

-- 09-05-2015 [Jonata Regis] - Alteração da coluna 'rumo_magnético', adicionando o valor TRUE onde estiver nulo.
UPDATE info_veiculo_aereo SET rumo_magnetico = true WHERE rumo_magnetico IS NULL;

-- 12-05-2016 [Fernanda Ferreira] - Remoção da coluna 'numeroLancadores'
alter table info_veiculo_superficie drop column if exists numeroLancadores;

-- 12-05-2016 [Camila Siqueira] - Inserção do valor 'S' para a coluna 'detect_mad'
UPDATE condicao_entidade SET detect_mad = 'S' WHERE detect_mad IS NULL;

-- 16-05-2016 [Fernanda Ferreira] - Alteração da coluna 'numeroLancador'
alter table info_veiculo_chaff_aeronave drop CONSTRAINT if exists info_veiculo_chaff_aeronave_pkey ;
alter table info_veiculo_chaff_aeronave ALTER COLUMN id_chaff SET NOT NULL;
alter table info_veiculo_chaff_aeronave ALTER COLUMN id_informacao_veiculo SET NOT NULL;
alter table info_veiculo_chaff_aeronave ALTER COLUMN numero_lancador SET NOT NULL;
ALTER TABLE info_veiculo_chaff_aeronave ADD CONSTRAINT info_veiculo_chaff_aeronave_pkey PRIMARY KEY(id_chaff , id_informacao_veiculo , numero_lancador );

alter table info_veiculo_chaff_superficie drop CONSTRAINT if exists info_veiculo_chaff_superficie_pkey;
alter table info_veiculo_chaff_superficie ALTER COLUMN id_chaff SET NOT NULL;
alter table info_veiculo_chaff_superficie ALTER COLUMN id_informacao_veiculo SET NOT NULL;
alter table info_veiculo_chaff_superficie ALTER COLUMN numero_lancador SET NOT NULL;
ALTER TABLE info_veiculo_chaff_superficie ADD CONSTRAINT info_veiculo_chaff_superficie_pkey PRIMARY KEY(id_chaff , id_informacao_veiculo , numero_lancador );

-- 19-05-2015 [Paulo Collares] - Remoção da coluna 'amarrarareaaodono'
alter table area_circular drop column if exists amarrarareaaodono;
alter table area_poligonal drop column if exists amarrarareaaodono;

-- 25-05-2016 [Fernanda Ferreira] - Remoção da coluna 'numeroLancadores' dos veiculos aereos e remoção da tabela lancador_chaff
alter table info_veiculo_aviao drop column if exists numeroLancadores;
alter table info_veiculo_helicoptero drop column if exists numeroLancadores;
drop table if exists lancador_chaff;
drop table if exists info_veiculo_aereo_boia;
drop table if exists configuracao_boia;
drop table if exists boia;
delete from equipamento exists where tipo = 'BOIA';

-- 30-05-2016 [Camila Siqueira] - Inserção do valor 'S' para a coluna 'detect_iff'
UPDATE condicao_entidade SET detect_iff = 'D' WHERE detect_iff IS NULL;

-- 09-05-2016 [Jonata Regis] - Exclusão da tabela configuracao_radar_nmea.
DROP TABLE IF EXISTS configuracao_radar_nmea;

--07-06-2016 [Luís Henrique] - Criação e preenchimento da coluna id_config_fonia na tabela Linha Interna e exclusão da tabela configuracao_fonia_linha_interna
-- ALTER TABLE linha_interna ADD COLUMN id_config_fonia integer;
-- 
-- UPDATE linha_interna li SET id_config_fonia = c.configuracoesfonia_id_configuracao_fonia FROM
-- configuracao_fonia_linha_interna as c
-- WHERE c.linhasinternas_id_linha_interna = li.id_linha_interna;
-- 
-- DROP TABLE IF EXISTS configuracao_fonia_linha_interna;

-- 08-06-2016 [Paulo Collares] - Remoção da tabela usuario3g
DROP TABLE IF EXISTS usuario3g;
DROP SEQUENCE IF EXISTS usuario3g_id_usuario3g_seq;

--15-06-2016 [Luís Henrique] - Insert na coluna tipo na tabela configuracai_fonia
UPDATE configuracao_fonia cf set tipo='SSTT' WHERE cf.id_configuracao_fonia = 1;

-- 17-06-2016 [Fernanda Ferreira] - Alteracoes no mapeamento das tabelas brs.
ALTER TABLE brs DROP COLUMN IF EXISTS id_sensor;

ALTER TABLE brs DROP COLUMN IF EXISTS id_brs CASCADE;
ALTER TABLE info_veiculo_aereo_brs DROP COLUMN IF EXISTS id_brs;
DROP TABLE IF EXISTS sonar_brs;
DROP TABLE IF EXISTS configuracao_sonar_brs;
ALTER TABLE info_veiculo_aereo_brs DROP CONSTRAINT IF EXISTS fk_7b0tlhs2jof2w60do8dnco76m;

-- 07-07-2016 [Paulo Collares] - Remoção da tabela validacao_externa
DROP TABLE IF EXISTS validacao_externa;
DROP SEQUENCE IF EXISTS validacao_externa_seq;

-- 24-06-2016 [Fernanda Ferreira] - Remoção da coluna 'detect_sonarbrs'
alter table condicao_entidade drop column if exists detect_sonarbrs;

-- 27-06-2016 [Paulo Collares] - Remoção das tabelas de feições
DROP TABLE IF EXISTS valor_atributo_feicao;
DROP TABLE IF EXISTS atributo_feicao;
DROP TABLE IF EXISTS feicao;


-- 28-06-2016 [Camila Siqueira] - Remoção da chave estrangeira de 'id_maquina' e 'id_usuario'
ALTER TABLE usuario_maquina DROP CONSTRAINT IF EXISTS fk_4gi8eau05h8x7g06bdc2ao9ej;
ALTER TABLE usuario_maquina DROP CONSTRAINT IF EXISTS fk_ra3ll0r4ilg04j2adjsj5xcgb;

ALTER TABLE usuario_maquina DROP CONSTRAINT IF EXISTS id_maquina_fk;
ALTER TABLE usuario_maquina ADD CONSTRAINT id_maquina_fk FOREIGN KEY (id_maquina)
      REFERENCES maquina (id_maquina) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE usuario_maquina DROP CONSTRAINT IF EXISTS id_usuario_fk;
ALTER TABLE usuario_maquina ADD CONSTRAINT id_usuario_fk FOREIGN KEY (id_usuario)
      REFERENCES usuario (id_usuario) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

-- 05-07-2016 [Camila Siqueira] - Alteração da chave estrangeira de 'id_informacao_veiculo'
ALTER TABLE veiculo DROP CONSTRAINT IF EXISTS id_informacao_veiculo_fk;
ALTER TABLE veiculo ADD CONSTRAINT id_informacao_veiculo_fk FOREIGN KEY (id_informacao_veiculo)
      REFERENCES info_veiculo (id_informacao_veiculo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE info_veiculo_aereo DROP CONSTRAINT IF EXISTS fk_9b35nl6q2jxubesd5ip3yk4ly;
ALTER TABLE info_veiculo_aereo DROP CONSTRAINT IF EXISTS id_informacao_veiculo_fk;
ALTER TABLE info_veiculo_aereo ADD CONSTRAINT id_informacao_veiculo_fk FOREIGN KEY (id_informacao_veiculo)
      REFERENCES info_veiculo (id_informacao_veiculo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE info_veiculo_aviao DROP CONSTRAINT IF EXISTS fk_n0t56ombgvby9jdjpr8ghp0oe;
ALTER TABLE info_veiculo_aviao DROP CONSTRAINT IF EXISTS id_informacao_veiculo_fk;
ALTER TABLE info_veiculo_aviao ADD CONSTRAINT id_informacao_veiculo_fk FOREIGN KEY (id_informacao_veiculo)
      REFERENCES info_veiculo_aereo (id_informacao_veiculo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE info_veiculo_helicoptero DROP CONSTRAINT IF EXISTS fk_7rgnuj2731bn4uiduwq0pgrt9;
ALTER TABLE info_veiculo_helicoptero DROP CONSTRAINT IF EXISTS id_informacao_veiculo_fk;
ALTER TABLE info_veiculo_helicoptero ADD CONSTRAINT id_informacao_veiculo_fk FOREIGN KEY (id_informacao_veiculo)
      REFERENCES info_veiculo_aereo (id_informacao_veiculo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE info_veiculo_helicoptero DROP CONSTRAINT IF EXISTS fk_jebppla67g1kg9ldt0y754rx7;

ALTER TABLE info_veiculo_maritimo DROP CONSTRAINT IF EXISTS fk_am0373hrmmmtyt4wkt3f730rc;
ALTER TABLE info_veiculo_maritimo DROP CONSTRAINT IF EXISTS id_informacao_veiculo_fk;
ALTER TABLE info_veiculo_maritimo ADD CONSTRAINT id_informacao_veiculo_fk FOREIGN KEY (id_informacao_veiculo)
      REFERENCES info_veiculo (id_informacao_veiculo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE info_veiculo_submarino DROP CONSTRAINT IF EXISTS fk_d7rcslfiunnuqe6ysi7njkwt1;
ALTER TABLE info_veiculo_submarino DROP CONSTRAINT IF EXISTS id_informacao_veiculo_fk;
ALTER TABLE info_veiculo_submarino ADD CONSTRAINT id_informacao_veiculo_fk FOREIGN KEY (id_informacao_veiculo)
      REFERENCES info_veiculo_maritimo (id_informacao_veiculo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE info_veiculo_superficie DROP CONSTRAINT IF EXISTS fk_8lbghoa052104n4h5unvmrj24;
ALTER TABLE info_veiculo_superficie DROP CONSTRAINT IF EXISTS id_informacao_veiculo_fk;
ALTER TABLE info_veiculo_superficie ADD CONSTRAINT id_informacao_veiculo_fk FOREIGN KEY (id_informacao_veiculo)
      REFERENCES info_veiculo_maritimo (id_informacao_veiculo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

-- 07-07-2016 [Camila Siqueira] - Alteração das chaves estrangeiras referentes a objeto_tatico
ALTER TABLE area_exercicio DROP CONSTRAINT IF EXISTS fk_6w54tcv07xqyc7q5d5cjewr5;
ALTER TABLE area_exercicio DROP CONSTRAINT IF EXISTS id_objeto_tatico_fk;
ALTER TABLE area_exercicio ADD CONSTRAINT id_objeto_tatico_fk FOREIGN KEY (id_objeto_tatico)
      REFERENCES objeto_tatico (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE area_exercicio DROP CONSTRAINT IF EXISTS fk_tb4psox8yjjhwokv0e6ha55sj;
ALTER TABLE area_exercicio DROP CONSTRAINT IF EXISTS condicao_inicial_dono_fk;

ALTER TABLE area_circular DROP CONSTRAINT IF EXISTS fk_hfphv9kp1f9fagdn7hsuj1ld1;
ALTER TABLE area_circular DROP CONSTRAINT IF EXISTS id_objeto_tatico_fk;
ALTER TABLE area_circular ADD CONSTRAINT id_objeto_tatico_fk FOREIGN KEY (id_objeto_tatico)
      REFERENCES area_exercicio (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE area_poligonal DROP CONSTRAINT IF EXISTS fk_cbyb57t9c0p1raeyvrmh22wv0;
ALTER TABLE area_poligonal DROP CONSTRAINT IF EXISTS id_objeto_tatico_fk;
ALTER TABLE area_poligonal ADD CONSTRAINT id_objeto_tatico_fk FOREIGN KEY (id_objeto_tatico)
      REFERENCES area_exercicio (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE brs DROP CONSTRAINT IF EXISTS fk_9dje7ml4hx8gmc1lrcfohhaao;
ALTER TABLE brs DROP CONSTRAINT IF EXISTS id_objeto_tatico_fk;
ALTER TABLE brs ADD CONSTRAINT id_objeto_tatico_fk FOREIGN KEY (id_objeto_tatico)
      REFERENCES objeto_tatico (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE camera_fixa DROP CONSTRAINT IF EXISTS fk_gwtilbo1hy9s714pfwxf6lmsm;
ALTER TABLE camera_fixa DROP CONSTRAINT IF EXISTS id_objeto_tatico_fk;
ALTER TABLE camera_fixa ADD CONSTRAINT id_objeto_tatico_fk FOREIGN KEY (id_objeto_tatico)
      REFERENCES objeto_tatico (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE elemento_navegacao DROP CONSTRAINT IF EXISTS fk_4s9gppb7fxqldyyw9poa04221;
ALTER TABLE elemento_navegacao DROP CONSTRAINT IF EXISTS id_objeto_tatico_fk;
ALTER TABLE elemento_navegacao ADD CONSTRAINT id_objeto_tatico_fk FOREIGN KEY (id_objeto_tatico)
      REFERENCES objeto_tatico (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE plano_navegacao DROP CONSTRAINT IF EXISTS fk_6w48qvmt3dj9rhstr3wibc5ux;
ALTER TABLE plano_navegacao DROP CONSTRAINT IF EXISTS id_objeto_tatico_fk;
ALTER TABLE plano_navegacao ADD CONSTRAINT id_objeto_tatico_fk FOREIGN KEY (id_objeto_tatico)
      REFERENCES objeto_tatico (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE ponto_fixo DROP CONSTRAINT IF EXISTS fk_lqu772cm42ejb1xgjf2bkicwa;
ALTER TABLE ponto_fixo DROP CONSTRAINT IF EXISTS id_objeto_tatico_fk;
ALTER TABLE ponto_fixo ADD CONSTRAINT id_objeto_tatico_fk FOREIGN KEY (id_objeto_tatico)
      REFERENCES objeto_tatico (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE rota DROP CONSTRAINT IF EXISTS fk_cbrfidjm32cu7lt3ekh5p50kr;
ALTER TABLE rota DROP CONSTRAINT IF EXISTS id_objeto_tatico_fk;
ALTER TABLE rota ADD CONSTRAINT id_objeto_tatico_fk FOREIGN KEY (id_objeto_tatico)
      REFERENCES objeto_tatico (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sar_area_sar DROP CONSTRAINT IF EXISTS fk_47l5bpj0cgkbhkqy7o5ekles5;
ALTER TABLE sar_area_sar DROP CONSTRAINT IF EXISTS id_objeto_tatico_fk;
ALTER TABLE sar_area_sar ADD CONSTRAINT id_objeto_tatico_fk FOREIGN KEY (id_objeto_tatico)
      REFERENCES area_poligonal (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sar_incidente_homem_ao_mar DROP CONSTRAINT IF EXISTS fk_aewer566o9jx6eep0awb0pfs4;
ALTER TABLE sar_incidente_homem_ao_mar DROP CONSTRAINT IF EXISTS id_objeto_tatico_fk;
ALTER TABLE sar_incidente_homem_ao_mar ADD CONSTRAINT id_objeto_tatico_fk FOREIGN KEY (id_objeto_tatico)
      REFERENCES sar_incidente_sar (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sar_incidente_sar DROP CONSTRAINT IF EXISTS fk_3kc73db5ssc0eqjl6pck7dio2;
ALTER TABLE sar_incidente_sar DROP CONSTRAINT IF EXISTS id_objeto_tatico_fk;
ALTER TABLE sar_incidente_sar ADD CONSTRAINT id_objeto_tatico_fk FOREIGN KEY (id_objeto_tatico)
      REFERENCES objeto_tatico (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sar_salva_mar DROP CONSTRAINT IF EXISTS fk_7sn1ath9kjm3nvsdp6mo5d48k;
ALTER TABLE sar_salva_mar DROP CONSTRAINT IF EXISTS id_objeto_tatico_fk;
ALTER TABLE sar_salva_mar ADD CONSTRAINT id_objeto_tatico_fk FOREIGN KEY (id_objeto_tatico)
      REFERENCES objeto_tatico (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sar_salva_mar_sar_sru DROP CONSTRAINT IF EXISTS fk_aay7flkyjtlg165mp7tsq781f;
ALTER TABLE sar_salva_mar_sar_sru DROP CONSTRAINT IF EXISTS id_objeto_tatico_salva_mar_fk;
ALTER TABLE sar_salva_mar_sar_sru ADD CONSTRAINT id_objeto_tatico_salva_mar_fk FOREIGN KEY (sar_salva_mar_id_objeto_tatico)
      REFERENCES sar_salva_mar (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sar_salva_mar_sar_sru DROP CONSTRAINT IF EXISTS fk_qygbutcq7txg6knss65eec2ut;
ALTER TABLE sar_salva_mar_sar_sru DROP CONSTRAINT IF EXISTS id_objeto_tatico_veiculos_fk;
ALTER TABLE sar_salva_mar_sar_sru ADD CONSTRAINT id_objeto_tatico_veiculos_fk FOREIGN KEY (veiculos_id_objeto_tatico)
      REFERENCES sar_sru (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sar_sru DROP CONSTRAINT IF EXISTS fk_7v03sppiw1sqpo46p6ivxre98;
ALTER TABLE sar_sru DROP CONSTRAINT IF EXISTS id_objeto_tatico_fk;
ALTER TABLE sar_sru ADD CONSTRAINT id_objeto_tatico_fk FOREIGN KEY (id_objeto_tatico)
      REFERENCES objeto_tatico (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sar_sru DROP CONSTRAINT IF EXISTS fk_qygbutcq7txg6knss65eec2ut;
ALTER TABLE sar_sru DROP CONSTRAINT IF EXISTS fk_apb2rexsy4fbiwcjthf0mhxix;
ALTER TABLE sar_sru DROP CONSTRAINT IF EXISTS id_objeto_tatico_veiculo_fk;
ALTER TABLE sar_sru ADD CONSTRAINT id_objeto_tatico_veiculo_fk FOREIGN KEY (id_veiculo)
      REFERENCES veiculo (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sar_sru_aereo DROP CONSTRAINT IF EXISTS fk_js1qtjyo7tkfkx06n2ky2p5in;
ALTER TABLE sar_sru_aereo DROP CONSTRAINT IF EXISTS id_objeto_tatico_fk;
ALTER TABLE sar_sru_aereo ADD CONSTRAINT id_objeto_tatico_fk FOREIGN KEY (id_objeto_tatico)
      REFERENCES sar_sru (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sar_ocorrencia_incidente_sar DROP CONSTRAINT IF EXISTS fk_5lc7pwuqik2ldja7b6op4nfo7;
ALTER TABLE sar_ocorrencia_incidente_sar DROP CONSTRAINT IF EXISTS id_incidente_fk;
ALTER TABLE sar_ocorrencia_incidente_sar ADD CONSTRAINT id_incidente_fk FOREIGN KEY (id_incidente)
      REFERENCES sar_incidente_sar (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sar_ocorrencia_incidente_sar DROP CONSTRAINT IF EXISTS fk_aso9dw0c0eq00ct6upcj1e7fs;
ALTER TABLE sar_ocorrencia_incidente_sar DROP CONSTRAINT IF EXISTS id_salva_mar_fk;
ALTER TABLE sar_ocorrencia_incidente_sar ADD CONSTRAINT id_salva_mar_fk FOREIGN KEY (id_salva_mar)
      REFERENCES sar_salva_mar (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sar_distrito DROP CONSTRAINT IF EXISTS fk_cu0pcg0nl08tetf7e8w909b1a;
ALTER TABLE sar_distrito DROP CONSTRAINT IF EXISTS id_area_fk;
ALTER TABLE sar_distrito ADD CONSTRAINT id_area_fk FOREIGN KEY (id_area)
      REFERENCES sar_area_sar (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sar_distrito DROP CONSTRAINT IF EXISTS fk_jlq7que84i6m5r1p2p6n4ry9f;
ALTER TABLE sar_distrito DROP CONSTRAINT IF EXISTS id_salva_mar_fk;
ALTER TABLE sar_distrito ADD CONSTRAINT id_salva_mar_fk FOREIGN KEY (id_salva_mar)
      REFERENCES sar_salva_mar (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sar_distrito DROP CONSTRAINT IF EXISTS fk_r10pb8b7mk0w7sq74yo4eq6c4;
ALTER TABLE sar_distrito DROP CONSTRAINT IF EXISTS id_condicao_ambiental_fk;
ALTER TABLE sar_distrito ADD CONSTRAINT id_condicao_ambiental_fk FOREIGN KEY (id_condicao_ambiental)
      REFERENCES sar_condicao_ambiental (id_condicao_ambiental) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE veiculo DROP CONSTRAINT IF EXISTS fk_d3himohen6j9io86ce77xvaai;
ALTER TABLE veiculo DROP CONSTRAINT IF EXISTS id_objeto_tatico_fk;
ALTER TABLE veiculo ADD CONSTRAINT id_objeto_tatico_fk FOREIGN KEY (id_objeto_tatico)
      REFERENCES objeto_tatico (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE veiculo_aviao DROP CONSTRAINT IF EXISTS fk_d1osxm9s8saxor3kwitgx7u06;
ALTER TABLE veiculo_aviao DROP CONSTRAINT IF EXISTS id_objeto_tatico_fk;
ALTER TABLE veiculo_aviao ADD CONSTRAINT id_objeto_tatico_fk FOREIGN KEY (id_objeto_tatico)
      REFERENCES veiculo (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE veiculo_helicoptero DROP CONSTRAINT IF EXISTS fk_sdhfyqcqlheo4c5qe4vorkbhs;
ALTER TABLE veiculo_helicoptero DROP CONSTRAINT IF EXISTS id_objeto_tatico_fk;
ALTER TABLE veiculo_helicoptero ADD CONSTRAINT id_objeto_tatico_fk FOREIGN KEY (id_objeto_tatico)
      REFERENCES veiculo (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE veiculo_submarino DROP CONSTRAINT IF EXISTS fk_qhy9bxophetbr1pqmu0x8a5k2;
ALTER TABLE veiculo_submarino DROP CONSTRAINT IF EXISTS id_objeto_tatico_fk;
ALTER TABLE veiculo_submarino ADD CONSTRAINT id_objeto_tatico_fk FOREIGN KEY (id_objeto_tatico)
      REFERENCES veiculo (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE veiculo_superficie DROP CONSTRAINT IF EXISTS fk_pxjwp3iqvdmnec529xuiwbqo7;
ALTER TABLE veiculo_superficie DROP CONSTRAINT IF EXISTS id_objeto_tatico_fk;
ALTER TABLE veiculo_superficie ADD CONSTRAINT id_objeto_tatico_fk FOREIGN KEY (id_objeto_tatico)
      REFERENCES veiculo (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE waypoint DROP CONSTRAINT IF EXISTS fk_t7qakxkwc7uk05gfk5qriu38k;
ALTER TABLE waypoint DROP CONSTRAINT IF EXISTS id_objeto_tatico_fk;
ALTER TABLE waypoint ADD CONSTRAINT id_objeto_tatico_fk FOREIGN KEY (id_objeto_tatico)
      REFERENCES objeto_tatico (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE condicao_entidade DROP CONSTRAINT IF EXISTS fk_el53wvv7iilv91jqawd8o6dx6;

-- 07-07-2016 [Camila Siqueira] - Alteração das chaves estrangeiras referentes a plano de navegação
ALTER TABLE plano_navegacao DROP CONSTRAINT IF EXISTS fk_7xr962o3w1trtd90bejy2h39a;
ALTER TABLE plano_navegacao DROP CONSTRAINT IF EXISTS condicao_inicial_dono_fk;
ALTER TABLE plano_navegacao ADD CONSTRAINT condicao_inicial_dono_fk FOREIGN KEY (condicao_inicial_dono)
      REFERENCES condicao_inicial (id_condicao_inicial) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE plano_navegacao DROP CONSTRAINT IF EXISTS fk_92dmsxpbjuyulhior8ip0x3y1;
ALTER TABLE plano_navegacao DROP CONSTRAINT IF EXISTS id_rota_alternativa_fk;
ALTER TABLE plano_navegacao ADD CONSTRAINT id_rota_alternativa_fk FOREIGN KEY (id_rota_alternativa)
      REFERENCES rota (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE plano_navegacao DROP CONSTRAINT IF EXISTS fk_dxkhig26a26lo75gdm77irril;
ALTER TABLE plano_navegacao DROP CONSTRAINT IF EXISTS id_rota_principal_fk;
ALTER TABLE plano_navegacao ADD CONSTRAINT id_rota_principal_fk FOREIGN KEY (id_rota_principal)
      REFERENCES rota (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE plano_navegacao_area_exercicio DROP CONSTRAINT IF EXISTS fk_avhq1otp33ct3vj6xobkxuo0f;
ALTER TABLE plano_navegacao_area_exercicio DROP CONSTRAINT IF EXISTS id_area_exercicio_fk;
ALTER TABLE plano_navegacao_area_exercicio ADD CONSTRAINT id_area_exercicio_fk FOREIGN KEY (id_area_exercicio)
      REFERENCES area_exercicio (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE plano_navegacao_area_exercicio DROP CONSTRAINT IF EXISTS fk_jdxn8xk89cx33ie8wttdfoosx;
ALTER TABLE plano_navegacao_area_exercicio DROP CONSTRAINT IF EXISTS id_plano_navegacao_fk;
ALTER TABLE plano_navegacao_area_exercicio ADD CONSTRAINT id_plano_navegacao_fk FOREIGN KEY (id_plano_navegacao)
      REFERENCES plano_navegacao (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE plano_navegacao_ponto_fixo DROP CONSTRAINT IF EXISTS fk_9vvrl9ehft1woj73j9p94k2km;
ALTER TABLE plano_navegacao_ponto_fixo DROP CONSTRAINT IF EXISTS id_plano_navegacao_fk;
ALTER TABLE plano_navegacao_ponto_fixo ADD CONSTRAINT id_plano_navegacao_fk FOREIGN KEY (id_plano_navegacao)
      REFERENCES plano_navegacao (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE plano_navegacao_ponto_fixo DROP CONSTRAINT IF EXISTS fk_sxgt99mspr7lyjwc1e2ukyr0m;
ALTER TABLE plano_navegacao_ponto_fixo DROP CONSTRAINT IF EXISTS id_ponto_fixo_fk;
ALTER TABLE plano_navegacao_ponto_fixo ADD CONSTRAINT id_ponto_fixo_fk FOREIGN KEY (id_ponto_fixo)
      REFERENCES ponto_fixo (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

-- 11-07-2016 [Camila Siqueira] - Alteração das chaves estrangeiras referentes a info_veiculo_equip_config
ALTER TABLE info_veiculo_equip_config DROP CONSTRAINT IF EXISTS fk_1chtjg0q5kgh9vck2ncpk91h;
ALTER TABLE info_veiculo_equip_config DROP CONSTRAINT IF EXISTS id_config_equip_fk;
ALTER TABLE info_veiculo_equip_config ADD CONSTRAINT id_config_equip_fk FOREIGN KEY (id_config_equip)
      REFERENCES configuracao_equipamento (id_config_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE info_veiculo_equip_config DROP CONSTRAINT IF EXISTS fk_2rx02c75acu3ayenqpif3rlus;
ALTER TABLE info_veiculo_equip_config DROP CONSTRAINT IF EXISTS id_info_veiculo_fk;
ALTER TABLE info_veiculo_equip_config ADD CONSTRAINT id_info_veiculo_fk FOREIGN KEY (id_info_veiculo)
      REFERENCES info_veiculo (id_informacao_veiculo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE info_veiculo_equip_config DROP CONSTRAINT IF EXISTS fk_ioq3wrtty83pa6625c2330rj8;
ALTER TABLE info_veiculo_equip_config DROP CONSTRAINT IF EXISTS id_equipamento_fk;
ALTER TABLE info_veiculo_equip_config ADD CONSTRAINT id_equipamento_fk FOREIGN KEY (id_equipamento)
      REFERENCES equipamento (id_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

-- 12-07-2016 [Camila Siqueira] - Alteração das chaves estrangeiras 
ALTER TABLE condicao_inicial DROP CONSTRAINT IF EXISTS fk_h2uvttirjy5egkw731vwdvcd5;
ALTER TABLE condicao_inicial DROP CONSTRAINT IF EXISTS id_teatro_operacao_fk;
ALTER TABLE condicao_inicial ADD CONSTRAINT id_teatro_operacao_fk FOREIGN KEY (id_teatro_operacao)
      REFERENCES teatro_operacao (id_teatro_operacao) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE bloco DROP CONSTRAINT IF EXISTS fk_mmhp9i8lsggnodg1fvorv701l;
ALTER TABLE bloco DROP CONSTRAINT IF EXISTS id_condicao_inicial_fk;
ALTER TABLE bloco ADD CONSTRAINT id_condicao_inicial_fk FOREIGN KEY (id_condicao_inicial)
      REFERENCES condicao_inicial (id_condicao_inicial) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE condicao_entidade DROP CONSTRAINT IF EXISTS fk_qo4thsgaa5n10j0c32lnw0rie;
ALTER TABLE condicao_entidade DROP CONSTRAINT IF EXISTS id_condicao_inicial_fk;
ALTER TABLE condicao_entidade ADD CONSTRAINT id_condicao_inicial_fk FOREIGN KEY (id_condicao_inicial)
      REFERENCES condicao_inicial (id_condicao_inicial) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE usuario_maquina_condicao DROP CONSTRAINT IF EXISTS fk_45vmfjx1ohvc1c562rv11075e;
ALTER TABLE usuario_maquina_condicao DROP CONSTRAINT IF EXISTS id_maquina_fk;
ALTER TABLE usuario_maquina_condicao ADD CONSTRAINT id_maquina_fk FOREIGN KEY (id_maquina)
      REFERENCES maquina (id_maquina) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE usuario_maquina_condicao DROP CONSTRAINT IF EXISTS fk_gmdfig2ib9aoafhsybvbnaene;
ALTER TABLE usuario_maquina_condicao DROP CONSTRAINT IF EXISTS id_condicao_fk;
ALTER TABLE usuario_maquina_condicao ADD CONSTRAINT id_condicao_fk FOREIGN KEY (id_condicao)
      REFERENCES condicao_inicial (id_condicao_inicial) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE usuario_maquina_condicao DROP CONSTRAINT IF EXISTS fk_qynbwfr6nha71q86q0uokvas;
ALTER TABLE usuario_maquina_condicao DROP CONSTRAINT IF EXISTS id_usuario_fk;
ALTER TABLE usuario_maquina_condicao ADD CONSTRAINT id_usuario_fk FOREIGN KEY (id_usuario)
      REFERENCES usuario (id_usuario) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE rota DROP CONSTRAINT IF EXISTS fk_lbxxpqojxwmrwhejonjrllr1n;
ALTER TABLE rota DROP CONSTRAINT IF EXISTS id_condicao_inicial_fk;
-- ALTER TABLE rota ADD CONSTRAINT id_condicao_inicial_fk FOREIGN KEY (condicao_inicial_dono)
--       REFERENCES condicao_inicial (id_condicao_inicial) MATCH SIMPLE
--       ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE pernada DROP CONSTRAINT IF EXISTS fk_8vot9x0bpnykytqdh0gdjy6oj;
ALTER TABLE pernada DROP CONSTRAINT IF EXISTS id_waypoint_fk;
ALTER TABLE pernada ADD CONSTRAINT id_waypoint_fk FOREIGN KEY (id_waypoint)
      REFERENCES objeto_tatico (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE pernada DROP CONSTRAINT IF EXISTS fk_litv5xr6r70jyg6w1j3m6ai7f;
ALTER TABLE pernada DROP CONSTRAINT IF EXISTS id_rota_fk;
ALTER TABLE pernada ADD CONSTRAINT id_rota_fk FOREIGN KEY (id_rota)
      REFERENCES rota (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE usuario_func DROP CONSTRAINT IF EXISTS fk_f35huw87248yptbfs2dx6o3mh;
ALTER TABLE usuario_func DROP CONSTRAINT IF EXISTS id_usuario_fk;
ALTER TABLE usuario_func ADD CONSTRAINT id_usuario_fk FOREIGN KEY (id_usuario)
      REFERENCES usuario (id_usuario) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE usuario_func DROP CONSTRAINT IF EXISTS fk_hwkoblwwv095oc9x4wdukgfu6;
ALTER TABLE usuario_func DROP CONSTRAINT IF EXISTS id_funcionalidade_fk;
ALTER TABLE usuario_func ADD CONSTRAINT id_funcionalidade_fk FOREIGN KEY (id_funcionalidade)
      REFERENCES funcionalidade (id_funcionalidade) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE vertice DROP CONSTRAINT IF EXISTS fk_5vcxnswysohpn57yx85v1ewxa;
ALTER TABLE vertice DROP CONSTRAINT IF EXISTS id_area_exercicio_fk;
ALTER TABLE vertice ADD CONSTRAINT id_area_exercicio_fk FOREIGN KEY (id_area_exercicio)
      REFERENCES area_poligonal (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

-- 21-07-2016 [Camila Siqueira] - Alteração das chaves estrangeiras de veiculo escravo
ALTER TABLE veiculo_escravo DROP CONSTRAINT IF EXISTS fk_f1jvkbg9y9si9rtnn4ha8dog9;
ALTER TABLE veiculo_escravo DROP CONSTRAINT IF EXISTS id_condicao_inicial_fk;
ALTER TABLE veiculo_escravo ADD CONSTRAINT id_condicao_inicial_fk FOREIGN KEY (id_condicao_inicial)
      REFERENCES condicao_entidade (id_condicao_inicial) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE veiculo_escravo DROP CONSTRAINT IF EXISTS fk_rta5e0x73n07my5x00i1bkvsi;
ALTER TABLE veiculo_escravo DROP CONSTRAINT IF EXISTS id_veiculo_mestre_fk;
ALTER TABLE veiculo_escravo ADD CONSTRAINT id_veiculo_mestre_fk FOREIGN KEY (id_veiculo_mestre)
      REFERENCES condicao_entidade (id_condicao_inicial) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

-- 25-07-2016 [Camila Siqueira] - Alteração das chaves estrangeiras
ALTER TABLE linha_interna DROP CONSTRAINT IF EXISTS fk_bitvb582mhkwyqbobua409ar0;
ALTER TABLE linha_interna DROP CONSTRAINT IF EXISTS id_maquina_fk;
ALTER TABLE linha_interna ADD CONSTRAINT id_maquina_fk FOREIGN KEY (id_maquina)
      REFERENCES maquina (id_maquina) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE comunicacao_ponto_ponto DROP CONSTRAINT IF EXISTS fk_rrydd05e5vrr48oinec71a7w7;
ALTER TABLE comunicacao_ponto_ponto DROP CONSTRAINT IF EXISTS id_linha_interna_origem_fk;
ALTER TABLE comunicacao_ponto_ponto ADD CONSTRAINT id_linha_interna_origem_fk FOREIGN KEY (id_linha_interna_origem)
      REFERENCES linha_interna (id_linha_interna) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE comunicacao_ponto_ponto DROP CONSTRAINT IF EXISTS fk_t088dg4he99n9pwa4mkrcj1ip;
ALTER TABLE comunicacao_ponto_ponto DROP CONSTRAINT IF EXISTS id_linha_interna_destino_fk;
ALTER TABLE comunicacao_ponto_ponto ADD CONSTRAINT id_linha_interna_destino_fk FOREIGN KEY (id_linha_interna_destino)
      REFERENCES linha_interna (id_linha_interna) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_fonia_linha_interna DROP CONSTRAINT IF EXISTS fk_cx72f1dvbh1eyd49ixih4dhbs;
ALTER TABLE configuracao_fonia_linha_interna DROP CONSTRAINT IF EXISTS id_linha_interna_fk;
ALTER TABLE configuracao_fonia_linha_interna ADD CONSTRAINT id_linha_interna_fk FOREIGN KEY (linhasinternas_id_linha_interna)
      REFERENCES linha_interna (id_linha_interna) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_fonia_linha_interna DROP CONSTRAINT IF EXISTS fk_rmcl32bhdhooni1moysxcix8g;
ALTER TABLE configuracao_fonia_linha_interna DROP CONSTRAINT IF EXISTS id_configuracao_fonia_fk;
ALTER TABLE configuracao_fonia_linha_interna ADD CONSTRAINT id_configuracao_fonia_fk FOREIGN KEY (configuracoesfonia_id_configuracao_fonia)
      REFERENCES configuracao_fonia (id_configuracao_fonia) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

-- 26-07-2016 [Camila Siqueira] - Alteração das chaves estrangeiras referentes a mage
ALTER TABLE faixa_freq_mage DROP CONSTRAINT IF EXISTS fk_pu800rcbqwyr1jin67pn117r4;
ALTER TABLE faixa_freq_mage DROP CONSTRAINT IF EXISTS id_mage_fk;
ALTER TABLE faixa_freq_mage ADD CONSTRAINT id_mage_fk FOREIGN KEY (id_mage)
      REFERENCES mage (id_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE mage DROP CONSTRAINT IF EXISTS fk_rna9sm6hadk5evb4ei4so3nlm;
ALTER TABLE mage DROP CONSTRAINT IF EXISTS id_sensor_fk;
ALTER TABLE mage ADD CONSTRAINT id_sensor_fk FOREIGN KEY (id_sensor)
      REFERENCES equipamento (id_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

-- Alteração das chaves estrangeiras referentes a equipamento
ALTER TABLE radar DROP CONSTRAINT IF EXISTS fk_py6v9bdjdy0ih0x461kn131u0;
ALTER TABLE radar DROP CONSTRAINT IF EXISTS id_sensor_fk;
ALTER TABLE radar ADD CONSTRAINT id_sensor_fk FOREIGN KEY (id_sensor)
      REFERENCES equipamento (id_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE radar_busca DROP CONSTRAINT IF EXISTS fk_r1xe93iacgbt1euqag6isrrih;
ALTER TABLE radar_busca DROP CONSTRAINT IF EXISTS id_sensor_fk;
ALTER TABLE radar_busca ADD CONSTRAINT id_sensor_fk FOREIGN KEY (id_sensor)
      REFERENCES radar (id_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE radar_dt DROP CONSTRAINT IF EXISTS fk_8afh5bio4ho0d0d7e271jud6k;
ALTER TABLE radar_dt DROP CONSTRAINT IF EXISTS id_sensor_fk;
ALTER TABLE radar_dt ADD CONSTRAINT id_sensor_fk FOREIGN KEY (id_sensor)
      REFERENCES radar (id_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sonar DROP CONSTRAINT IF EXISTS fk_j6rbgcik3j9f0kke2r07u2xfd;
ALTER TABLE sonar DROP CONSTRAINT IF EXISTS id_sensor_fk;
ALTER TABLE sonar ADD CONSTRAINT id_sensor_fk FOREIGN KEY (id_sensor)
      REFERENCES equipamento (id_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sonar_casco DROP CONSTRAINT IF EXISTS fk_7ndlppwig5tg3bn76aon1ao0p;
ALTER TABLE sonar_casco DROP CONSTRAINT IF EXISTS id_sensor_fk;
ALTER TABLE sonar_casco ADD CONSTRAINT id_sensor_fk FOREIGN KEY (id_sensor)
      REFERENCES sonar (id_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sonar_prs DROP CONSTRAINT IF EXISTS fk_m4ctajlhs5qhs8qhtepac6kvj;
ALTER TABLE sonar_prs DROP CONSTRAINT IF EXISTS id_sensor_fk;
ALTER TABLE sonar_prs ADD CONSTRAINT id_sensor_fk FOREIGN KEY (id_sensor)
      REFERENCES sonar (id_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sonar_vds DROP CONSTRAINT IF EXISTS fk_ffxy82m4aw4y73y4fi27v3odm;
ALTER TABLE sonar_vds DROP CONSTRAINT IF EXISTS id_sensor_fk;
ALTER TABLE sonar_vds ADD CONSTRAINT id_sensor_fk FOREIGN KEY (id_sensor)
      REFERENCES sonar (id_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

-- Alteração das chaves estrangeiras referentes a arma
ALTER TABLE torpedo_submarino DROP CONSTRAINT IF EXISTS fk_9tun43lfrxcf2si0kca6iiq2p;
ALTER TABLE torpedo_submarino DROP CONSTRAINT IF EXISTS id_arma_fk;
ALTER TABLE torpedo_submarino ADD CONSTRAINT id_arma_fk FOREIGN KEY (id_arma)
      REFERENCES torpedo (id_arma) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE torpedo_as DROP CONSTRAINT IF EXISTS fk_j0u935nff4kqp04gggw130o61;
ALTER TABLE torpedo_as DROP CONSTRAINT IF EXISTS id_arma_fk;
ALTER TABLE torpedo_as ADD CONSTRAINT id_arma_fk FOREIGN KEY (id_arma)
      REFERENCES torpedo (id_arma) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE torpedo DROP CONSTRAINT IF EXISTS fk_gvm0d886lpydqvulh7omwv5nv;
ALTER TABLE torpedo DROP CONSTRAINT IF EXISTS id_arma_fk;
ALTER TABLE torpedo ADD CONSTRAINT id_arma_fk FOREIGN KEY (id_arma)
      REFERENCES arma (id_arma) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

-- Alteração das chaves estrangeiras referentes a dav
ALTER TABLE dav_circular DROP CONSTRAINT IF EXISTS fk_rlsni4h4clf1d03den0x3k6ot;
ALTER TABLE dav_circular DROP CONSTRAINT IF EXISTS id_dav_fk;
ALTER TABLE dav_circular ADD CONSTRAINT id_dav_fk FOREIGN KEY (id_dav)
      REFERENCES dav (id_dav) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE dav_op_auto_arma DROP CONSTRAINT IF EXISTS fk_cy8tkb75fkiuh5o74ayybe16b;
ALTER TABLE dav_op_auto_arma DROP CONSTRAINT IF EXISTS id_arma_fk;
ALTER TABLE dav_op_auto_arma ADD CONSTRAINT id_arma_fk FOREIGN KEY (id_arma_associada)
      REFERENCES arma (id_arma) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE dav_op_auto_arma DROP CONSTRAINT IF EXISTS fk_ew0sx6yacekwcofoq3ttjwxhy;
ALTER TABLE dav_op_auto_arma DROP CONSTRAINT IF EXISTS id_dav_fk;
ALTER TABLE dav_op_auto_arma ADD CONSTRAINT id_dav_fk FOREIGN KEY (id_dav)
      REFERENCES dav (id_dav) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

-- 27-07-2016 [Camila Siqueira] - Alteração das chaves estrangeiras
ALTER TABLE teatro_operacao DROP CONSTRAINT IF EXISTS fk_8o1tb2yaqr4cmrmleha8q8609;
ALTER TABLE teatro_operacao DROP CONSTRAINT IF EXISTS id_dados_ambientais_fk;
ALTER TABLE teatro_operacao ADD CONSTRAINT id_dados_ambientais_fk FOREIGN KEY (id_dados_ambientais)
      REFERENCES dados_ambientais (id_dados_ambientais) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE teatro_operacao DROP CONSTRAINT IF EXISTS fk_qxa1iqgmi61dao9sip2luc2q1;
ALTER TABLE teatro_operacao DROP CONSTRAINT IF EXISTS id_configuracao_fonia_fk;
ALTER TABLE teatro_operacao ADD CONSTRAINT id_configuracao_fonia_fk FOREIGN KEY (id_configuracao_fonia)
      REFERENCES configuracao_fonia (id_configuracao_fonia) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;


-- Alteração das chaves estrangeiras referentes a equipamento
ALTER TABLE sonar_interceptacao DROP CONSTRAINT IF EXISTS fk_3h13hx1ns7vnmw6wj5fnhvrw3;
ALTER TABLE sonar_interceptacao DROP CONSTRAINT IF EXISTS id_sensor_fk;
ALTER TABLE sonar_interceptacao ADD CONSTRAINT id_sensor_fk FOREIGN KEY (id_sensor)
      REFERENCES sonar (id_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sensor_rtd DROP CONSTRAINT IF EXISTS fk_bvntex6tflp4sithysa199sld;
ALTER TABLE sensor_rtd DROP CONSTRAINT IF EXISTS id_sensor_fk;
ALTER TABLE sensor_rtd ADD CONSTRAINT id_sensor_fk FOREIGN KEY (id_sensor)
      REFERENCES equipamento (id_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sensor_novolinkyb DROP CONSTRAINT IF EXISTS fk_3ewluthb4c9fatyaookcxi294;
ALTER TABLE sensor_novolinkyb DROP CONSTRAINT IF EXISTS id_sensor_fk;
ALTER TABLE sensor_novolinkyb ADD CONSTRAINT id_sensor_fk FOREIGN KEY (id_sensor)
      REFERENCES equipamento (id_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

-- 03-08-2016 [Camila Siqueira] - Alteração das chaves estrangeiras
ALTER TABLE info_veiculo_superficie_mae DROP CONSTRAINT IF EXISTS fk_1tdwfwru2og8f06ia8ctkmu8u;
ALTER TABLE info_veiculo_superficie_mae DROP CONSTRAINT IF EXISTS id_informacao_veiculo_fk;
ALTER TABLE info_veiculo_superficie_mae ADD CONSTRAINT id_informacao_veiculo_fk FOREIGN KEY (id_informacao_veiculo)
      REFERENCES info_veiculo_superficie (id_informacao_veiculo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE info_veiculo_superficie_mae DROP CONSTRAINT IF EXISTS fk_dpeagngnbqqo33i7451igf6rk;
ALTER TABLE info_veiculo_superficie_mae DROP CONSTRAINT IF EXISTS id_mae_fk;
ALTER TABLE info_veiculo_superficie_mae ADD CONSTRAINT id_mae_fk FOREIGN KEY (id_mae)
      REFERENCES mae (id_mae)  MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

-- 03-08-2016 [Luis Henrique] - Alteração das chaves estrangeiras referentes a DAV
ALTER TABLE dav_op_auto_equip DROP CONSTRAINT IF EXISTS fk_jjn0bncq1l3udx5ky5su7ovk8;
ALTER TABLE dav_op_auto_equip DROP CONSTRAINT IF EXISTS id_dav_fk;
ALTER TABLE dav_op_auto_equip ADD CONSTRAINT id_dav_fk FOREIGN KEY (id_dav)
      REFERENCES dav (id_dav) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE dav_op_auto_equip DROP CONSTRAINT IF EXISTS fk_p9dpwj7sgjl12gsrc0h4inbx3;
ALTER TABLE dav_op_auto_equip DROP CONSTRAINT IF EXISTS id_sensor_fk;
ALTER TABLE dav_op_auto_equip ADD CONSTRAINT id_sensor_fk FOREIGN KEY (id_equip_associado)
      REFERENCES equipamento (id_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE dav_perseguir_alvo DROP CONSTRAINT IF EXISTS fk_8rqrp3gmt64v4r12nfq7c9s8k;
ALTER TABLE dav_perseguir_alvo DROP CONSTRAINT IF EXISTS id_dav_fk;
ALTER TABLE dav_perseguir_alvo ADD CONSTRAINT id_dav_fk FOREIGN KEY (id_dav)
      REFERENCES dav (id_dav) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE dav_rota DROP CONSTRAINT IF EXISTS fk_hokgybydxvq9bjmc3bujjaruf;
ALTER TABLE dav_rota DROP CONSTRAINT IF EXISTS id_dav_fk;
ALTER TABLE dav_rota ADD CONSTRAINT id_dav_fk FOREIGN KEY (id_dav)
      REFERENCES dav (id_dav) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE dav_zig_zag DROP CONSTRAINT IF EXISTS fk_it7u701s5oqhdixq5f9if4f1;
ALTER TABLE dav_zig_zag DROP CONSTRAINT IF EXISTS id_dav_fk;
ALTER TABLE dav_zig_zag ADD CONSTRAINT id_dav_fk FOREIGN KEY (id_dav)
      REFERENCES dav (id_dav) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

-- 03-08-2016 [Luis Henrique] - Alteração das chaves estrangeiras referentes as Configurações Equipamento

ALTER TABLE configuracao_adsb DROP CONSTRAINT IF EXISTS fk_c4ktwdc0gnr94nhir1yp2kxg;
ALTER TABLE configuracao_adsb DROP CONSTRAINT IF EXISTS id_config_sensor_fk;
ALTER TABLE configuracao_adsb ADD CONSTRAINT id_config_sensor_fk FOREIGN KEY (id_config_sensor)
      REFERENCES configuracao_equipamento (id_config_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_ais DROP CONSTRAINT IF EXISTS fk_rptuyd28g58gp48taotj9dqlu;
ALTER TABLE configuracao_ais DROP CONSTRAINT IF EXISTS id_config_sensor_fk;
ALTER TABLE configuracao_ais ADD CONSTRAINT id_config_sensor_fk FOREIGN KEY (id_config_sensor)
      REFERENCES configuracao_equipamento (id_config_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_altimetro DROP CONSTRAINT IF EXISTS fk_m4j0tf1mj274n4o4uv527kbe5;
ALTER TABLE configuracao_altimetro DROP CONSTRAINT IF EXISTS id_config_sensor_fk;
ALTER TABLE configuracao_altimetro ADD CONSTRAINT id_config_sensor_fk FOREIGN KEY (id_config_sensor)
      REFERENCES configuracao_equipamento (id_config_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_anemometro DROP CONSTRAINT IF EXISTS fk_sxt67b8qa4fya4xpjm0m1c2p0;
ALTER TABLE configuracao_anemometro DROP CONSTRAINT IF EXISTS configuracao_equipamento_fk;
ALTER TABLE configuracao_anemometro ADD CONSTRAINT configuracao_equipamento_fk FOREIGN KEY (id_config_sensor)
      REFERENCES configuracao_equipamento (id_config_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_dv DROP CONSTRAINT IF EXISTS fk_shy38xc0hooulejp98rmuwuf5;
ALTER TABLE configuracao_dv DROP CONSTRAINT IF EXISTS id_config_sensor_fk;
ALTER TABLE configuracao_dv ADD CONSTRAINT id_config_sensor_fk FOREIGN KEY (id_config_sensor)
      REFERENCES configuracao_equipamento (id_config_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_ecobatimetro DROP CONSTRAINT IF EXISTS fk_k50y2wu57mo5ihbhoy5bgessq;
ALTER TABLE configuracao_ecobatimetro DROP CONSTRAINT IF EXISTS id_config_sensor_fk;
ALTER TABLE configuracao_ecobatimetro ADD CONSTRAINT id_config_sensor_fk FOREIGN KEY (id_config_sensor)
      REFERENCES configuracao_equipamento (id_config_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_fonia_linha_externa DROP CONSTRAINT IF EXISTS fk_5g6r0pgxuul11owf6eil060ls;
ALTER TABLE configuracao_fonia_linha_externa DROP CONSTRAINT IF EXISTS id_linha_externa_fk;
ALTER TABLE configuracao_fonia_linha_externa ADD CONSTRAINT id_linha_externa_fk FOREIGN KEY (linhasexternas_id_linha_externa)
      REFERENCES linha_externa (id_linha_externa) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_fonia_linha_externa DROP CONSTRAINT IF EXISTS fk_ty6499nldwxbf423whhhsyxl;
ALTER TABLE configuracao_fonia_linha_externa DROP CONSTRAINT IF EXISTS id_configuracao_fonia_fk;
ALTER TABLE configuracao_fonia_linha_externa ADD CONSTRAINT id_configuracao_fonia_fk FOREIGN KEY (configuracoesfonia_id_configuracao_fonia)
      REFERENCES configuracao_fonia (id_configuracao_fonia) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_fonia_linha_vhf DROP CONSTRAINT IF EXISTS fk_j7vxw7b7ysxia6uqm98u7w9dl;
ALTER TABLE configuracao_fonia_linha_vhf DROP CONSTRAINT IF EXISTS id_linha_vhf_fk;
ALTER TABLE configuracao_fonia_linha_vhf ADD CONSTRAINT id_linha_vhf_fk FOREIGN KEY (linhasvhf_id_linha_vhf)
      REFERENCES linha_vhf (id_linha_vhf) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_fonia_linha_vhf DROP CONSTRAINT IF EXISTS fk_qt5q7il93iw7dbjkjhbxqya9i;
ALTER TABLE configuracao_fonia_linha_vhf DROP CONSTRAINT IF EXISTS id_configuracao_fonia_fk;
ALTER TABLE configuracao_fonia_linha_vhf ADD CONSTRAINT id_configuracao_fonia_fk FOREIGN KEY (configuracoesfonia_id_configuracao_fonia)
      REFERENCES configuracao_fonia (id_configuracao_fonia) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_giro DROP CONSTRAINT IF EXISTS fk_3pjgfmfnmkskte6ei26npr7ls;
ALTER TABLE configuracao_giro DROP CONSTRAINT IF EXISTS id_config_sensor_fk;
ALTER TABLE configuracao_giro ADD CONSTRAINT id_config_sensor_fk FOREIGN KEY (id_config_sensor)
      REFERENCES configuracao_equipamento (id_config_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_gps DROP CONSTRAINT IF EXISTS fk_swsplsd7fujqw6wr0ai8xenxn;
ALTER TABLE configuracao_gps DROP CONSTRAINT IF EXISTS id_config_sensor_fk;
ALTER TABLE configuracao_gps ADD CONSTRAINT id_config_sensor_fk FOREIGN KEY (id_config_sensor)
      REFERENCES configuracao_equipamento (id_config_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_hodometro DROP CONSTRAINT IF EXISTS fk_t81c8cxcodoo6lw40nc7kjaxf;
ALTER TABLE configuracao_hodometro DROP CONSTRAINT IF EXISTS id_config_sensor_fk;
ALTER TABLE configuracao_hodometro ADD CONSTRAINT id_config_sensor_fk FOREIGN KEY (id_config_sensor)
      REFERENCES configuracao_equipamento (id_config_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_iff DROP CONSTRAINT IF EXISTS fk_exgw4ntaagp8jmjwq1a1ixkg3;
ALTER TABLE configuracao_iff DROP CONSTRAINT IF EXISTS id_config_sensor_fk;
ALTER TABLE configuracao_iff ADD CONSTRAINT id_config_sensor_fk FOREIGN KEY (id_config_sensor)
      REFERENCES configuracao_equipamento (id_config_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_linkyb DROP CONSTRAINT IF EXISTS fk_l3wa1gga8aobpvgnwh3m3spwn;
ALTER TABLE configuracao_linkyb DROP CONSTRAINT IF EXISTS id_config_sensor_fk;
ALTER TABLE configuracao_linkyb ADD CONSTRAINT id_config_sensor_fk FOREIGN KEY (id_config_sensor)
      REFERENCES configuracao_equipamento (id_config_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_mad DROP CONSTRAINT IF EXISTS fk_cc4rgt98nmuvo4sropsthur14;
ALTER TABLE configuracao_mad DROP CONSTRAINT IF EXISTS id_config_sensor_fk;
ALTER TABLE configuracao_mad ADD CONSTRAINT id_config_sensor_fk FOREIGN KEY (id_config_sensor)
      REFERENCES configuracao_equipamento (id_config_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_mage DROP CONSTRAINT IF EXISTS fk_i31tn420hdj8kl22d4vrwa97d;
ALTER TABLE configuracao_mage DROP CONSTRAINT IF EXISTS id_config_sensor_fk;
ALTER TABLE configuracao_mage ADD CONSTRAINT id_config_sensor_fk FOREIGN KEY (id_config_sensor)
      REFERENCES configuracao_equipamento (id_config_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_navegacao_inercial DROP CONSTRAINT IF EXISTS fk_ltih2i1idjl0ookejee5oy5rf;
ALTER TABLE configuracao_navegacao_inercial DROP CONSTRAINT IF EXISTS id_config_sensor_fk;
ALTER TABLE configuracao_navegacao_inercial ADD CONSTRAINT id_config_sensor_fk FOREIGN KEY (id_config_sensor)
      REFERENCES configuracao_equipamento (id_config_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_novolinkyb DROP CONSTRAINT IF EXISTS fk_8fmyo6nsyciw7ht4gwn7bausq;
ALTER TABLE configuracao_novolinkyb DROP CONSTRAINT IF EXISTS id_config_sensor_fk;
ALTER TABLE configuracao_novolinkyb ADD CONSTRAINT id_config_sensor_fk FOREIGN KEY (id_config_sensor)
      REFERENCES configuracao_equipamento (id_config_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_radar_busca DROP CONSTRAINT IF EXISTS fk_6isr84t1ufvqkggytioohlcd6;
ALTER TABLE configuracao_radar_busca DROP CONSTRAINT IF EXISTS id_config_sensor_fk;
ALTER TABLE configuracao_radar_busca ADD CONSTRAINT id_config_sensor_fk FOREIGN KEY (id_config_sensor)
      REFERENCES configuracao_equipamento (id_config_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_radar_dt DROP CONSTRAINT IF EXISTS fk_gkkggrkv619pyo2t3pmeif346;
ALTER TABLE configuracao_radar_dt DROP CONSTRAINT IF EXISTS id_config_sensor_fk;
ALTER TABLE configuracao_radar_dt ADD CONSTRAINT id_config_sensor_fk FOREIGN KEY (id_config_sensor)
      REFERENCES configuracao_equipamento (id_config_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_rtd DROP CONSTRAINT IF EXISTS fk_8k2raw40l48tcs8fdwid4o76;
ALTER TABLE configuracao_rtd DROP CONSTRAINT IF EXISTS id_config_sensor_fk;
ALTER TABLE configuracao_rtd ADD CONSTRAINT id_config_sensor_fk FOREIGN KEY (id_config_sensor)
      REFERENCES configuracao_equipamento (id_config_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_sonar_casco DROP CONSTRAINT IF EXISTS fk_9u5hlwq2ec03jgb8nba8y2swj;
ALTER TABLE configuracao_sonar_casco DROP CONSTRAINT IF EXISTS id_config_sensor_fk;
ALTER TABLE configuracao_sonar_casco ADD CONSTRAINT id_config_sensor_fk FOREIGN KEY (id_config_sensor)
      REFERENCES configuracao_equipamento (id_config_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_sonar_interceptacao DROP CONSTRAINT IF EXISTS fk_3j3cvds4l59yu9gtfgny4lm6r;
ALTER TABLE configuracao_sonar_interceptacao DROP CONSTRAINT IF EXISTS id_config_sensor_fk;
ALTER TABLE configuracao_sonar_interceptacao ADD CONSTRAINT id_config_sensor_fk FOREIGN KEY (id_config_sensor)
      REFERENCES configuracao_equipamento (id_config_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_sonar_prs DROP CONSTRAINT IF EXISTS fk_cifqpnqr7emgsg0m720rk71o1;
ALTER TABLE configuracao_sonar_prs DROP CONSTRAINT IF EXISTS id_config_sensor_fk;
ALTER TABLE configuracao_sonar_prs ADD CONSTRAINT id_config_sensor_fk FOREIGN KEY (id_config_sensor)
      REFERENCES configuracao_equipamento (id_config_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_sonar_vds DROP CONSTRAINT IF EXISTS fk_sjxx1wpq0epi80di2dk7idr8s;
ALTER TABLE configuracao_sonar_vds DROP CONSTRAINT IF EXISTS id_config_sensor_fk;
ALTER TABLE configuracao_sonar_vds ADD CONSTRAINT id_config_sensor_fk FOREIGN KEY (id_config_sensor)
      REFERENCES configuracao_equipamento (id_config_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;


-- 03-08-2016 [Luis Henrique] - Alteração das chaves estrangeiras referentes as Coordenada Diagrama
ALTER TABLE coordenada_diagrama DROP CONSTRAINT IF EXISTS fk_6wlqjk7wrxmdnhfrcgskyve28;
ALTER TABLE coordenada_diagrama DROP CONSTRAINT IF EXISTS id_diagrama_grafico_fk;
ALTER TABLE coordenada_diagrama ADD CONSTRAINT id_diagrama_grafico_fk FOREIGN KEY (id_diagrama_grafico)
      REFERENCES grafico (id_grafico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

-- 09-08-2016 [Camila Siqueira] - Alteração das chaves estrangeiras

-- Alteração das chaves estrangeiras referentes a ais
ALTER TABLE ais_navstatus DROP CONSTRAINT IF EXISTS fk_rba5u5ed4aaanqj5ps28qx540;
ALTER TABLE ais_navstatus DROP CONSTRAINT IF EXISTS id_nav_status_fk;
ALTER TABLE ais_navstatus ADD CONSTRAINT id_nav_status_fk FOREIGN KEY (id_nav_status)
      REFERENCES status_navio (id_nav_status) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE ais_paises DROP CONSTRAINT IF EXISTS fk_gixs9ks5botyrqfqvbma4wlow;
ALTER TABLE ais_paises DROP CONSTRAINT IF EXISTS id_paises_fk;
ALTER TABLE ais_paises ADD CONSTRAINT id_paises_fk FOREIGN KEY (id_paises)
      REFERENCES paises (id_paises) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE ais_tiponavio DROP CONSTRAINT IF EXISTS fk_7lxfqucmhxcg2nwag989sn2qi;
ALTER TABLE ais_tiponavio DROP CONSTRAINT IF EXISTS id_tipo_navio_fk;
ALTER TABLE ais_tiponavio ADD CONSTRAINT id_tipo_navio_fk FOREIGN KEY (id_tipo_navio)
      REFERENCES tipo_navio (id_tipo_navio) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

-- Alteração das chaves estrangeiras referentes a bateria
ALTER TABLE bateria DROP CONSTRAINT IF EXISTS fk_auy6f9yu1u4x5lpk49h1ryb1x;
ALTER TABLE bateria DROP CONSTRAINT IF EXISTS id_descarga_fk;
ALTER TABLE bateria ADD CONSTRAINT id_descarga_fk FOREIGN KEY (id_taxa_descarga)
      REFERENCES descarga (id_descarga) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE bateria DROP CONSTRAINT IF EXISTS fk_o7kcisnc7aemk3m0taui12ab8;
ALTER TABLE bateria DROP CONSTRAINT IF EXISTS id_carga_fk;
ALTER TABLE bateria ADD CONSTRAINT id_carga_fk FOREIGN KEY (id_taxa_carga)
      REFERENCES carga (id_carga) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

--
ALTER TABLE condicao_instrutor DROP CONSTRAINT IF EXISTS fk_lhct9sm3w6vwls3ltpyd71uey;
ALTER TABLE condicao_instrutor DROP CONSTRAINT IF EXISTS id_condicao_inicial_fk;
ALTER TABLE condicao_instrutor ADD CONSTRAINT id_condicao_inicial_fk FOREIGN KEY (id_condicao_inicial)
      REFERENCES condicao_inicial (id_condicao_inicial) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

--
ALTER TABLE curva_giro DROP CONSTRAINT IF EXISTS fk_fqyp18kjlyo6rrykyjy5s1bid;
ALTER TABLE curva_giro DROP CONSTRAINT IF EXISTS id_plataforma_fk;
ALTER TABLE curva_giro ADD CONSTRAINT id_plataforma_fk FOREIGN KEY (id_plataforma)
      REFERENCES plataforma_superficie (id_plataforma) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

--
ALTER TABLE dados_taticos DROP CONSTRAINT IF EXISTS fk_jjl9pkhjk6ga12qtj68h3te9a;
ALTER TABLE dados_taticos DROP CONSTRAINT IF EXISTS id_curva_giro_fk;
ALTER TABLE dados_taticos ADD CONSTRAINT id_curva_giro_fk FOREIGN KEY (id_curva_giro)
      REFERENCES curva_giro (id_curva_giro)  MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

--
ALTER TABLE diagrama_grafico DROP CONSTRAINT IF EXISTS fk_isscste1s9fhl60opq3umgckl;
ALTER TABLE diagrama_grafico DROP CONSTRAINT IF EXISTS id_grafico_fk;
ALTER TABLE diagrama_grafico ADD CONSTRAINT id_grafico_fk FOREIGN KEY (id_grafico)
      REFERENCES grafico (id_grafico)  MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

--
ALTER TABLE iff_codigo DROP CONSTRAINT IF EXISTS fk_kuq7ic5q02v3qa2ne991bltvm;
ALTER TABLE iff_codigo DROP CONSTRAINT IF EXISTS id_teatro_fk;
ALTER TABLE iff_codigo ADD CONSTRAINT id_teatro_fk FOREIGN KEY (id_teatro)
      REFERENCES teatro_operacao (id_teatro_operacao)  MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

-- Alteração das chaves estrangeiras referentes a informação veículo
ALTER TABLE info_veiculo_aereo_brs DROP CONSTRAINT IF EXISTS fk_sij4r274vot57sdqrguxxka5d;
ALTER TABLE info_veiculo_aereo_brs DROP CONSTRAINT IF EXISTS id_informacao_veiculo_fk;
ALTER TABLE info_veiculo_aereo_brs ADD CONSTRAINT id_informacao_veiculo_fk FOREIGN KEY (id_informacao_veiculo)
      REFERENCES info_veiculo_aereo (id_informacao_veiculo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE info_veiculo_arma DROP CONSTRAINT IF EXISTS fk_jjsgnqp1uslgxn2kt0429pbi6;
ALTER TABLE info_veiculo_arma DROP CONSTRAINT IF EXISTS id_arma_fk;
ALTER TABLE info_veiculo_arma ADD CONSTRAINT id_arma_fk FOREIGN KEY (id_arma)
      REFERENCES arma (id_arma) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE info_veiculo_arma DROP CONSTRAINT IF EXISTS fk_poep9e49ev5wfpwys5t3ocf5d;
ALTER TABLE info_veiculo_arma DROP CONSTRAINT IF EXISTS id_informacao_veiculo_fk;
ALTER TABLE info_veiculo_arma ADD CONSTRAINT id_informacao_veiculo_fk FOREIGN KEY (id_informacao_veiculo)
      REFERENCES info_veiculo (id_informacao_veiculo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE info_veiculo_chaff_aeronave DROP CONSTRAINT IF EXISTS fk_lh8giw6kt8sdcq6dh515kg0xk;
ALTER TABLE info_veiculo_chaff_aeronave DROP CONSTRAINT IF EXISTS id_informacao_veiculo_fk;
ALTER TABLE info_veiculo_chaff_aeronave ADD CONSTRAINT id_informacao_veiculo_fk FOREIGN KEY (id_informacao_veiculo)
      REFERENCES info_veiculo_aereo (id_informacao_veiculo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE info_veiculo_chaff_aeronave DROP CONSTRAINT IF EXISTS fk_mqdlgo3406ifa6r131qp41w4n;
ALTER TABLE info_veiculo_chaff_aeronave DROP CONSTRAINT IF EXISTS id_chaff_fk;
ALTER TABLE info_veiculo_chaff_aeronave ADD CONSTRAINT id_chaff_fk FOREIGN KEY (id_chaff)
      REFERENCES chaff_aeronave (id_chaff) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE info_veiculo_chaff_superficie DROP CONSTRAINT IF EXISTS fk_dwtso0exnwsdftlw8t46en722;
ALTER TABLE info_veiculo_chaff_superficie DROP CONSTRAINT IF EXISTS id_chaff_fk;
ALTER TABLE info_veiculo_chaff_superficie ADD CONSTRAINT id_chaff_fk FOREIGN KEY (id_chaff)
      REFERENCES chaff_superficie (id_chaff) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE info_veiculo_chaff_superficie DROP CONSTRAINT IF EXISTS fk_esjqq335q7m8fk45wtuioywqq;
ALTER TABLE info_veiculo_chaff_superficie DROP CONSTRAINT IF EXISTS id_informacao_veiculo_fk;
ALTER TABLE info_veiculo_chaff_superficie ADD CONSTRAINT id_informacao_veiculo_fk FOREIGN KEY (id_informacao_veiculo)
      REFERENCES info_veiculo_superficie (id_informacao_veiculo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

-- Alteração das chaves estrangeiras referentes a luz navegação
ALTER TABLE luzes_navegacao_veiculo DROP CONSTRAINT IF EXISTS fk_8tnh0drag7lj0heiudlcf3244;
ALTER TABLE luzes_navegacao_veiculo DROP CONSTRAINT IF EXISTS id_condicao_inicial_fk;
ALTER TABLE luzes_navegacao_veiculo ADD CONSTRAINT id_condicao_inicial_fk FOREIGN KEY (id_luz_navegacao)
      REFERENCES condicao_entidade (id_condicao_inicial) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE luzes_navegacao_veiculo DROP CONSTRAINT IF EXISTS fk_fyp17e8vi1qsoouxfwwjfbxr2;
ALTER TABLE luzes_navegacao_veiculo DROP CONSTRAINT IF EXISTS id_condicao_veiculo_fk;
ALTER TABLE luzes_navegacao_veiculo ADD CONSTRAINT id_condicao_veiculo_fk FOREIGN KEY (id_condicao_veiculo)
      REFERENCES luz_navegacao (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

-- Alteração das chaves estrangeiras referentes a arma
ALTER TABLE bomba DROP CONSTRAINT IF EXISTS fk_dsgunyhdij4vesuskcj1w3owr;
ALTER TABLE bomba DROP CONSTRAINT IF EXISTS id_arma_fk;
ALTER TABLE bomba ADD CONSTRAINT id_arma_fk FOREIGN KEY (id_arma)
      REFERENCES arma (id_arma) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE bomba_profundidade DROP CONSTRAINT IF EXISTS fk_bkjbxujstteql37l08b2wdh08;
ALTER TABLE bomba_profundidade DROP CONSTRAINT IF EXISTS id_arma_fk;
ALTER TABLE bomba_profundidade ADD CONSTRAINT id_arma_fk FOREIGN KEY (id_arma)
      REFERENCES arma (id_arma) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE canhao DROP CONSTRAINT IF EXISTS fk_sw2f4qn0btm9ctr6qpup8b09i;
ALTER TABLE canhao DROP CONSTRAINT IF EXISTS id_arma_fk;
ALTER TABLE canhao ADD CONSTRAINT id_arma_fk FOREIGN KEY (id_arma)
      REFERENCES arma (id_arma) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE foguete_ar_sup DROP CONSTRAINT IF EXISTS fk_q0l0ww5xudeiv0dfurlmq51qu;
ALTER TABLE foguete_ar_sup DROP CONSTRAINT IF EXISTS id_arma_fk;
ALTER TABLE foguete_ar_sup ADD CONSTRAINT id_arma_fk FOREIGN KEY (id_arma)
      REFERENCES arma (id_arma)  MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE foguete_asroc DROP CONSTRAINT IF EXISTS fk_g25a7vel87u0832jplg8egw0o;
ALTER TABLE foguete_asroc DROP CONSTRAINT IF EXISTS id_arma_fk;
ALTER TABLE foguete_asroc ADD CONSTRAINT id_arma_fk FOREIGN KEY (id_arma)
      REFERENCES arma (id_arma)  MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE foguete_asroc DROP CONSTRAINT IF EXISTS fk_orrt9kmddkcf5255etnmciber;
ALTER TABLE foguete_asroc DROP CONSTRAINT IF EXISTS id_torpedo_fk;
ALTER TABLE foguete_asroc ADD CONSTRAINT id_torpedo_fk FOREIGN KEY (id_torpedo)
      REFERENCES torpedo_as (id_arma)  MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE foguete_boroc DROP CONSTRAINT IF EXISTS fk_qlfkpnk2a4wiswanfg4kklgfr;
ALTER TABLE foguete_boroc DROP CONSTRAINT IF EXISTS id_arma_fk;
ALTER TABLE foguete_boroc ADD CONSTRAINT id_arma_fk FOREIGN KEY (id_arma)
      REFERENCES arma (id_arma)  MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE foguete_boroc DROP CONSTRAINT IF EXISTS fk_re56u6efnggu26gbpt79yk7uf;
ALTER TABLE foguete_boroc DROP CONSTRAINT IF EXISTS id_bomba_fk;
ALTER TABLE foguete_boroc ADD CONSTRAINT id_bomba_fk FOREIGN KEY (id_bomba)
      REFERENCES bomba (id_arma)  MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE missil_ar_ar DROP CONSTRAINT IF EXISTS fk_6c8b938ib65ekqda2vnrdwxu6;
ALTER TABLE missil_ar_ar DROP CONSTRAINT IF EXISTS id_arma_fk;
ALTER TABLE missil_ar_ar ADD CONSTRAINT id_arma_fk FOREIGN KEY (id_arma)
      REFERENCES arma (id_arma) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE missil_ar_sup DROP CONSTRAINT IF EXISTS fk_s0gbhewanhtwksfqio7m4xgo3;
ALTER TABLE missil_ar_sup DROP CONSTRAINT IF EXISTS id_arma_fk;
ALTER TABLE missil_ar_sup ADD CONSTRAINT id_arma_fk FOREIGN KEY (id_arma)
      REFERENCES arma (id_arma) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE missil_sup_ar DROP CONSTRAINT IF EXISTS fk_ss55vl62t90twfidhw9fvnoi6;
ALTER TABLE missil_sup_ar DROP CONSTRAINT IF EXISTS id_arma_fk;
ALTER TABLE missil_sup_ar ADD CONSTRAINT id_arma_fk FOREIGN KEY (id_arma)
      REFERENCES arma (id_arma) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE missil_sup_sup DROP CONSTRAINT IF EXISTS fk_shh7iw0d3frx1010oxm72piho;
ALTER TABLE missil_sup_sup DROP CONSTRAINT IF EXISTS id_arma_fk;
ALTER TABLE missil_sup_sup ADD CONSTRAINT id_arma_fk FOREIGN KEY (id_arma)
      REFERENCES arma (id_arma) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE perfil_usuario DROP CONSTRAINT IF EXISTS fk_idaeeb4bqa677ln5pqs4vavop;
ALTER TABLE perfil_usuario DROP CONSTRAINT IF EXISTS id_simbologia_fk;
ALTER TABLE perfil_usuario ADD CONSTRAINT id_simbologia_fk FOREIGN KEY (id_simbologia)
      REFERENCES simbologia (id_simbologia) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

-- Alteração das chaves estrangeiras referentes a pessoa
ALTER TABLE pessoa_civil DROP CONSTRAINT IF EXISTS fk_5c25onqanf5r5g6ub718in5t6;
ALTER TABLE pessoa_civil DROP CONSTRAINT IF EXISTS id_pessoa_fk;
ALTER TABLE pessoa_civil ADD CONSTRAINT id_pessoa_fk FOREIGN KEY (id_pessoa)
      REFERENCES pessoa (id_pessoa) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE pessoa_militar DROP CONSTRAINT IF EXISTS fk_8aqn4d7aokbqtaxowsxfe2i3u;
ALTER TABLE pessoa_militar DROP CONSTRAINT IF EXISTS id_pessoa_fk;
ALTER TABLE pessoa_militar ADD CONSTRAINT id_pessoa_fk FOREIGN KEY (id_pessoa)
      REFERENCES pessoa (id_pessoa) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

-- Alteração das chaves estrangeiras referentes a plataforma
ALTER TABLE plataforma_aviao DROP CONSTRAINT IF EXISTS fk_kqhpckxtaitgftmtxi6kausdf;
ALTER TABLE plataforma_aviao DROP CONSTRAINT IF EXISTS id_taxa_guinada_fk;
ALTER TABLE plataforma_aviao ADD CONSTRAINT id_taxa_guinada_fk FOREIGN KEY (id_taxa_guinada)
      REFERENCES guinada_aviao (id_guinada_aviao) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE plataforma_aviao DROP CONSTRAINT IF EXISTS fk_su1a7k49u75xafwbu4uyeyhj6;
ALTER TABLE plataforma_aviao DROP CONSTRAINT IF EXISTS id_plataforma_fk;
ALTER TABLE plataforma_aviao ADD CONSTRAINT id_plataforma_fk FOREIGN KEY (id_plataforma)
      REFERENCES plataforma (id_plataforma) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE plataforma_helicoptero DROP CONSTRAINT IF EXISTS fk_4txshaosx0v8bdb9scx6rb7um;
ALTER TABLE plataforma_helicoptero DROP CONSTRAINT IF EXISTS id_taxa_guinada_fk;
ALTER TABLE plataforma_helicoptero ADD CONSTRAINT id_taxa_guinada_fk FOREIGN KEY (id_taxa_guinada)
      REFERENCES guinada_helicoptero (id_guinada_helicoptero) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE plataforma_helicoptero DROP CONSTRAINT IF EXISTS fk_bilcts6kqal5jrv83c4at4ymk;
ALTER TABLE plataforma_helicoptero DROP CONSTRAINT IF EXISTS id_plataforma_fk;
ALTER TABLE plataforma_helicoptero ADD CONSTRAINT id_plataforma_fk FOREIGN KEY (id_plataforma)
      REFERENCES plataforma (id_plataforma) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE plataforma_submarino DROP CONSTRAINT IF EXISTS fk_ftenmqwkudxxqum7kg1wryi0t;
ALTER TABLE plataforma_submarino DROP CONSTRAINT IF EXISTS id_bateria_fk;
ALTER TABLE plataforma_submarino ADD CONSTRAINT id_bateria_fk FOREIGN KEY (id_bateria)
      REFERENCES bateria (id_bateria) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE plataforma_submarino DROP CONSTRAINT IF EXISTS fk_i2kj8tp6n2ycd9ympbwww78ju;
ALTER TABLE plataforma_submarino DROP CONSTRAINT IF EXISTS id_taxa_guinada_fk;
ALTER TABLE plataforma_submarino ADD CONSTRAINT id_taxa_guinada_fk FOREIGN KEY (id_taxa_guinada)
      REFERENCES guinada_submarino (id_guinada_maritima) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE plataforma_submarino DROP CONSTRAINT IF EXISTS fk_5bvtlcda3guaoi5rhlfld5dmc;
ALTER TABLE plataforma_submarino DROP CONSTRAINT IF EXISTS id_plataforma_fk;
ALTER TABLE plataforma_submarino ADD CONSTRAINT id_plataforma_fk FOREIGN KEY (id_plataforma)
      REFERENCES plataforma (id_plataforma) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE plataforma_superficie DROP CONSTRAINT IF EXISTS fk_jubeefgfvw27xvw3p4p3avohn;
ALTER TABLE plataforma_superficie DROP CONSTRAINT IF EXISTS id_taxa_guinada_fk;
ALTER TABLE plataforma_superficie ADD CONSTRAINT id_taxa_guinada_fk FOREIGN KEY (id_taxa_guinada)
      REFERENCES guinada_superficie (id_guinada_maritima) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE plataforma_superficie DROP CONSTRAINT IF EXISTS fk_fo8g1ai9l6d3s5kmlokg6nq1q;
ALTER TABLE plataforma_superficie DROP CONSTRAINT IF EXISTS id_plataforma_fk;
ALTER TABLE plataforma_superficie ADD CONSTRAINT id_plataforma_fk FOREIGN KEY (id_plataforma)
      REFERENCES plataforma (id_plataforma) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

--
ALTER TABLE ponto_fixo DROP CONSTRAINT IF EXISTS fk_kl98v5v90j4dqt95ecbrlmx3c;
ALTER TABLE ponto_fixo DROP CONSTRAINT IF EXISTS id_condicao_inicial_fk;
ALTER TABLE ponto_fixo ADD CONSTRAINT id_condicao_inicial_fk FOREIGN KEY (condicao_inicial_dono)
      REFERENCES condicao_inicial (id_condicao_inicial) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

-- Alteração das chaves estrangeiras referentes a sensor
ALTER TABLE sensor_adsb DROP CONSTRAINT IF EXISTS fk_bgo22lvs1hbqnady5efd7o6j7;
ALTER TABLE sensor_adsb DROP CONSTRAINT IF EXISTS id_sensor_fk;
ALTER TABLE sensor_adsb ADD CONSTRAINT id_sensor_fk FOREIGN KEY (id_sensor)
      REFERENCES equipamento (id_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sensor_ais DROP CONSTRAINT IF EXISTS fk_i6tfq4f4l3m94bydl25k1poc9;
ALTER TABLE sensor_ais DROP CONSTRAINT IF EXISTS id_sensor_fk;
ALTER TABLE sensor_ais ADD CONSTRAINT id_sensor_fk FOREIGN KEY (id_sensor)
      REFERENCES equipamento (id_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sensor_altimetro DROP CONSTRAINT IF EXISTS fk_dd9x3v7c2ha9un5pesa82vvdi;
ALTER TABLE sensor_altimetro DROP CONSTRAINT IF EXISTS id_sensor_fk;
ALTER TABLE sensor_altimetro ADD CONSTRAINT id_sensor_fk FOREIGN KEY (id_sensor)
      REFERENCES equipamento (id_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sensor_anemometro DROP CONSTRAINT IF EXISTS fk_lgmbanwy75o2wyhimffx8vlqt;
ALTER TABLE sensor_anemometro DROP CONSTRAINT IF EXISTS id_sensor_fk;
ALTER TABLE sensor_anemometro ADD CONSTRAINT id_sensor_fk FOREIGN KEY (id_sensor)
      REFERENCES equipamento (id_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sensor_dv DROP CONSTRAINT IF EXISTS fk_13so5uo0tw0kkkgkmub2f0ws8;
ALTER TABLE sensor_dv DROP CONSTRAINT IF EXISTS id_sensor_fk;
ALTER TABLE sensor_dv ADD CONSTRAINT id_sensor_fk FOREIGN KEY (id_sensor)
      REFERENCES equipamento (id_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sensor_ecobatimetro DROP CONSTRAINT IF EXISTS fk_3j4ewq6xifms84s39of52ntny;
ALTER TABLE sensor_ecobatimetro DROP CONSTRAINT IF EXISTS id_sensor_fk;
ALTER TABLE sensor_ecobatimetro ADD CONSTRAINT id_sensor_fk FOREIGN KEY (id_sensor)
      REFERENCES equipamento (id_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sensor_giro DROP CONSTRAINT IF EXISTS fk_aejds2lqq19v0k9ti9d4uxwdi;
ALTER TABLE sensor_giro DROP CONSTRAINT IF EXISTS id_sensor_fk;
ALTER TABLE sensor_giro ADD CONSTRAINT id_sensor_fk FOREIGN KEY (id_sensor)
      REFERENCES equipamento (id_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sensor_gps DROP CONSTRAINT IF EXISTS fk_uehbpxgx3tenylkeitcydsec;
ALTER TABLE sensor_gps DROP CONSTRAINT IF EXISTS id_sensor_fk;
ALTER TABLE sensor_gps ADD CONSTRAINT id_sensor_fk FOREIGN KEY (id_sensor)
      REFERENCES equipamento (id_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sensor_hodometro DROP CONSTRAINT IF EXISTS fk_blq6hgipkl1qo1ojpdu8jx2ju;
ALTER TABLE sensor_hodometro DROP CONSTRAINT IF EXISTS id_sensor_fk;
ALTER TABLE sensor_hodometro ADD CONSTRAINT id_sensor_fk FOREIGN KEY (id_sensor)
      REFERENCES equipamento (id_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sensor_iff DROP CONSTRAINT IF EXISTS fk_rtlsbfr2dnhlja3m1n2ls3mbn;
ALTER TABLE sensor_iff DROP CONSTRAINT IF EXISTS id_sensor_fk;
ALTER TABLE sensor_iff ADD CONSTRAINT id_sensor_fk FOREIGN KEY (id_sensor)
      REFERENCES equipamento (id_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sensor_linkyb DROP CONSTRAINT IF EXISTS fk_337bua9yx38fuj0u83s9ouac4;
ALTER TABLE sensor_linkyb DROP CONSTRAINT IF EXISTS id_sensor_fk;
ALTER TABLE sensor_linkyb ADD CONSTRAINT id_sensor_fk FOREIGN KEY (id_sensor)
      REFERENCES equipamento (id_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sensor_mad DROP CONSTRAINT IF EXISTS fk_213id8bmgir3uwudop8xqt7sc;
ALTER TABLE sensor_mad DROP CONSTRAINT IF EXISTS id_sensor_fk;
ALTER TABLE sensor_mad ADD CONSTRAINT id_sensor_fk FOREIGN KEY (id_sensor)
      REFERENCES equipamento (id_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE sensor_navegacao_inercial DROP CONSTRAINT IF EXISTS fk_mgmo3hhmmps4miltsnspxy7dc;
ALTER TABLE sensor_navegacao_inercial DROP CONSTRAINT IF EXISTS id_sensor_fk;
ALTER TABLE sensor_navegacao_inercial ADD CONSTRAINT id_sensor_fk FOREIGN KEY (id_sensor)
      REFERENCES equipamento (id_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

-- 23-08-2016 [Camila Siqueira] - Alteração de constraint
ALTER TABLE info_veiculo_aereo_brs DROP CONSTRAINT IF EXISTS fkbrs;
ALTER TABLE info_veiculo_aereo_brs ADD CONSTRAINT fkbrs FOREIGN KEY (id_objeto_tatico)
      REFERENCES brs (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

-- ALTER TABLE info_veiculo DROP CONSTRAINT IF EXISTS id_modelo_3d_fk;
-- ALTER TABLE info_veiculo ADD CONSTRAINT id_modelo_3d_fk FOREIGN KEY (id_modelo_3d)
--       REFERENCES modelo_3d (id_modelo_3d) MATCH SIMPLE
--       ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE info_veiculo DROP CONSTRAINT IF EXISTS id_plataforma_fk;
ALTER TABLE info_veiculo ADD CONSTRAINT id_plataforma_fk FOREIGN KEY (id_plataforma)
      REFERENCES plataforma (id_plataforma) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

-- 25-08-2016 [Camila Siqueira] - Alteração do campo 'recepcaoaudio'
ALTER TABLE sonar ALTER COLUMN recepcaoaudio SET DEFAULT 'ONIDIRECIONAL';

-- 15-09-2016 [Camila Siqueira] - Remoção de colunas não utilizadas
alter table info_veiculo_aviao drop column if exists nad_dam;
alter table info_veiculo_helicoptero drop column if exists mad_dam;

--15-09-2016 [Luís Henrique] - Remoção da coluna modelo3D no informação veiculo
alter table info_veiculo drop column if exists id_modelo_3d;

-- 16-09-2016 [Davi Lessa] - Remoção da coluna 'setor' e 'buscasetorial' na tabela 'radar_busca'
ALTER TABLE radar_busca DROP COLUMN IF EXISTS setor;
ALTER TABLE radar_busca DROP COLUMN IF EXISTS buscasetorial;

-- 11-10-2016 [Camila Siqueira] - Remoção da coluna pais
ALTER TABLE info_veiculo DROP COLUMN IF EXISTS pais;

-- 31-10-2016 [Camila Siqueira] - Remoção da coluna 'modooperacao'
ALTER TABLE sonar DROP COLUMN IF EXISTS modooperacao;

-- 16-11-2016 [Camila Siqueira] - Alteração de constraint para manometro
ALTER TABLE sensor_manometro DROP CONSTRAINT IF EXISTS fk_5el345x8cl45r1bn8g0b7u70j;
ALTER TABLE sensor_manometro DROP CONSTRAINT IF EXISTS id_sensor_fk;
ALTER TABLE sensor_manometro ADD CONSTRAINT id_sensor_fk FOREIGN KEY (id_sensor)
      REFERENCES equipamento (id_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_manometro DROP CONSTRAINT IF EXISTS fk_o55g1tdxe79nhav1wtyxg5rcn;
ALTER TABLE configuracao_manometro DROP CONSTRAINT IF EXISTS id_config_sensor_fk;
ALTER TABLE configuracao_manometro ADD CONSTRAINT id_config_sensor_fk FOREIGN KEY (id_config_sensor)
      REFERENCES configuracao_equipamento (id_config_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE rota DROP COLUMN IF EXISTS condicao_inicial_dono; 

ALTER TABLE rota_condicao_inicial DROP CONSTRAINT IF EXISTS fk_ai5gnh6ncsjak61u4w77q9ujr;
ALTER TABLE rota_condicao_inicial DROP CONSTRAINT IF EXISTS id_rota_fk;
ALTER TABLE rota_condicao_inicial ADD CONSTRAINT id_rota_fk FOREIGN KEY (id_rota)
      REFERENCES rota (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE rota_condicao_inicial DROP CONSTRAINT IF EXISTS fk_eb3hoa3hhcd4bda7pr66xfalr;
ALTER TABLE rota_condicao_inicial DROP CONSTRAINT IF EXISTS id_condicao_inicial_fk;
ALTER TABLE rota_condicao_inicial ADD CONSTRAINT id_condicao_inicial_fk FOREIGN KEY (id_condicao_inicial)
      REFERENCES condicao_inicial (id_condicao_inicial) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

-- 06-02-2017 [Camila Siqueira] - Exclusão de coluna não utilizada em area_exercicio
ALTER TABLE area_exercicio DROP COLUMN IF EXISTS condicao_inicial_dono;

ALTER TABLE condicao_inicial_area_exercicio DROP CONSTRAINT IF EXISTS fk_njxoy4gj7gjb86tgrp4hl83s1;
ALTER TABLE condicao_inicial_area_exercicio DROP CONSTRAINT IF EXISTS id_area_exercicio_fk;
ALTER TABLE condicao_inicial_area_exercicio ADD CONSTRAINT id_area_exercicio_fk FOREIGN KEY (id_area_exercicio)
      REFERENCES area_exercicio (id_objeto_tatico) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE condicao_inicial_area_exercicio DROP CONSTRAINT IF EXISTS fk_qvkdo5xnpcjedtn0yqo4ubn2y;
ALTER TABLE condicao_inicial_area_exercicio DROP CONSTRAINT IF EXISTS id_condicao_inicial_fk;
ALTER TABLE condicao_inicial_area_exercicio ADD CONSTRAINT id_condicao_inicial_fk FOREIGN KEY (id_condicao_inicial)
      REFERENCES condicao_inicial (id_condicao_inicial) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE condicao_inicial_dav DROP CONSTRAINT IF EXISTS fk_f80sm2rd3of0nc4ojjfe7yh42;
ALTER TABLE condicao_inicial_dav DROP CONSTRAINT IF EXISTS id_condicao_inicial_fk;
ALTER TABLE condicao_inicial_dav ADD CONSTRAINT id_condicao_inicial_fk FOREIGN KEY (id_condicao_inicial)
      REFERENCES condicao_inicial (id_condicao_inicial) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE condicao_inicial_dav DROP CONSTRAINT IF EXISTS fk_t9dokh03pkoudjit5pjbxos16;
ALTER TABLE condicao_inicial_dav DROP CONSTRAINT IF EXISTS id_dav_fk;
ALTER TABLE condicao_inicial_dav ADD CONSTRAINT id_dav_fk FOREIGN KEY (id_dav)
      REFERENCES dav (id_dav) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE info_veiculo_modelo_3d DROP CONSTRAINT IF EXISTS fk_1f95ackk6dgtberw97y6hea2v;
ALTER TABLE info_veiculo_modelo_3d DROP CONSTRAINT IF EXISTS modelo3d_fk;
ALTER TABLE info_veiculo_modelo_3d ADD CONSTRAINT modelo3d_fk FOREIGN KEY (modelo3d)
      REFERENCES modelo_3d (id_modelo_3d) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE info_veiculo_modelo_3d DROP CONSTRAINT IF EXISTS fk_jynahfxpad6h4npo5hsudwher;
ALTER TABLE info_veiculo_modelo_3d DROP CONSTRAINT IF EXISTS veiculo_fk;
ALTER TABLE info_veiculo_modelo_3d ADD CONSTRAINT veiculo_fk FOREIGN KEY (veiculo)
      REFERENCES info_veiculo (id_informacao_veiculo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE pessoa_usuario DROP CONSTRAINT IF EXISTS fk_h5k7jly4p1bds6or7vej712y9;
ALTER TABLE pessoa_usuario DROP CONSTRAINT IF EXISTS id_pessoa_fk;
ALTER TABLE pessoa_usuario ADD CONSTRAINT id_pessoa_fk FOREIGN KEY (id_pessoa)
      REFERENCES pessoa (id_pessoa) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE pessoa_usuario DROP CONSTRAINT IF EXISTS fk_mm48oktgltph6cr08obnvq0mm;
ALTER TABLE pessoa_usuario DROP CONSTRAINT IF EXISTS id_usuario_fk;
ALTER TABLE pessoa_usuario ADD CONSTRAINT id_usuario_fk FOREIGN KEY (id_usuario)
      REFERENCES usuario (id_usuario) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE demon DROP CONSTRAINT IF EXISTS fk_fccrsop3ivi6k36l5efdktjs1;
ALTER TABLE demon DROP CONSTRAINT IF EXISTS id_condicao_inicial_fk;
ALTER TABLE demon ADD CONSTRAINT id_condicao_inicial_fk FOREIGN KEY (id_condicao_inicial)
      REFERENCES condicao_inicial (id_condicao_inicial) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE narrowband DROP CONSTRAINT IF EXISTS fk_4l7pmqem5tajjncbo46rmi7of;
ALTER TABLE narrowband DROP CONSTRAINT IF EXISTS id_condicao_inicial_fk;
ALTER TABLE narrowband ADD CONSTRAINT id_condicao_inicial_fk FOREIGN KEY (id_condicao_inicial)
      REFERENCES condicao_inicial (id_condicao_inicial) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

-- 24-02-2017[Luis Henrique] - Inserção dos valores na tabela dados_ambientais
alter table dados_ambientais drop column if exists progacao_fixa;
alter table dados_ambientais drop column if exists progacao;

UPDATE dados_ambientais SET velocidade_som = '0' WHERE velocidade_som IS NULL;
UPDATE dados_ambientais SET propagacao = 'false' WHERE propagacao IS NULL;
UPDATE dados_ambientais SET propagacao_fixa = '0' WHERE propagacao_fixa IS NULL;
UPDATE dados_ambientais SET perda_absorcao = 'false' WHERE perda_absorcao IS NULL;
UPDATE dados_ambientais SET perda_superficie = '0' WHERE perda_superficie IS NULL;
UPDATE dados_ambientais SET perda_peso = '0' WHERE perda_peso IS NULL;


-- 17-03-2017[Marcelo Santos] - Remoção dos DAVs antigos na tabela dav
DELETE  FROM dav EXISTS WHERE id_dav = '1' AND nome = 'PERSEGUIR 0.5';
DELETE  FROM dav EXISTS WHERE id_dav = '2' AND nome = 'PERSEGUIR 1.0';
DELETE  FROM dav EXISTS WHERE id_dav = '3' AND nome = 'PERSEGUIR 1.5';
DELETE  FROM dav EXISTS WHERE id_dav = '4' AND nome = 'Circular 5';
DELETE  FROM dav EXISTS WHERE id_dav = '5' AND nome = 'Circular 15';
DELETE  FROM dav EXISTS WHERE id_dav = '6' AND nome = 'Circular 30';
DELETE  FROM dav EXISTS WHERE id_dav = '7' AND nome = 'ZIG ZAG 60';
DELETE  FROM dav EXISTS WHERE id_dav = '8' AND nome = 'ZIG ZAG 90';
DELETE  FROM dav EXISTS WHERE id_dav = '9' AND nome = 'ZIG ZAG 150';
DELETE  FROM dav EXISTS WHERE id_dav = '10' AND nome = 'DAV ROTA X';
DELETE  FROM dav EXISTS WHERE id_dav = '11' AND nome = 'DAV ROTA X SIMNAV TESTE ';

-- 17-03-2017[Marcelo Santos] - Remoção da tabela dav_pernada
DROP TABLE IF EXISTS dav_pernada;
DROP SEQUENCE IF EXISTS dav_pernada_seq;

-- 17-03-2017[Marcelo Santos] - Remoção da tabela dav_rota_simnav
DROP TABLE IF EXISTS dav_rota_simnav;

-- 07-04-2017 [Paulo Collares] - Remoção da tabela proxy
drop table if exists proxy;

-- 12-04-2017 [Luis Henrique] - Inserção do valor 'BEAUFORT' para a coluna 'estado_mar' na tabela perfil_usuario
UPDATE perfil_usuario SET estado_mar = 'BEAUFORT' WHERE estado_mar IS NULL;
drop table if exists proxy;

-- 12-04-2017 [Davi Lessa] - Remoção das informações da carta na tabela historico_navegacao
ALTER TABLE historico_navegacao DROP COLUMN IF EXISTS carta;
ALTER TABLE historico_navegacao DROP COLUMN IF EXISTS data_atualz_carta;
ALTER TABLE historico_navegacao DROP COLUMN IF EXISTS edicao_carta;
ALTER TABLE historico_navegacao DROP COLUMN IF EXISTS fonte_carta;
ALTER TABLE historico_navegacao DROP COLUMN IF EXISTS nro_atualz_carta;
ALTER TABLE historico_navegacao DROP COLUMN IF EXISTS rumo;

-- 29-05-2017 [Luis Henrique] - Remoção das colunas 'esnorquel' e 'periscopio' da Informação Veículo Submarino
ALTER TABLE info_veiculo_submarino DROP COLUMN IF EXISTS esnorquel;
ALTER TABLE info_veiculo_submarino DROP COLUMN IF EXISTS periscopio;

-- 30-10-2017 [Luis Henrique] = Remoção das colunas speed, heading, depth
ALTER TABLE condicao_simper DROP COLUMN IF EXISTS speed;
ALTER TABLE condicao_simper DROP COLUMN IF EXISTS heading;
ALTER TABLE condicao_simper DROP COLUMN IF EXISTS depth;

-- 19-05-2017 [Fernanda Ferreira] - Exclusão das tabelas acesso_externo, acesso_externo_navio e acesso_externo_celular.
DROP TABLE IF EXISTS acesso_externo_navio;
DROP TABLE IF EXISTS acesso_externo_celular;
DROP TABLE IF EXISTS acesso_externo;

-- 27-04-2017 [Ana Carolina] - inserção de valor default da coluna 'calado_aereo'
-- 27-04-2017 [Ana Carolina Sim Moraes] - inserção de valor default da coluna 'calado_aereo'
UPDATE plataforma_superficie SET calado_aereo = 0 WHERE calado_aereo IS NULL;

-- 15-05-2017 [Luis Henrique] - Remoção da coluna 'sistema_navegação'
ALTER TABLE perfil_usuario DROP COLUMN IF EXISTS sistema_navegacao;

-- 31-05-2017 [Paulo Collares] - Remoção da tabela simbolo
DROP TABLE IF EXISTS simbolo;

-- 19-06-2017 [Paulo Collares] - Remoção das tabelas sensor_web_service e configuracao_web_service
DROP TABLE IF EXISTS sensor_web_service;
DROP TABLE IF EXISTS configuracao_web_service;

-- 27-06-2017 [Fernanda Ferreira] - Remoção da coluna 'tipo_navio'
alter table plataforma_superficie drop column if exists tipo_navio;

ALTER TABLE historico_navegacao ALTER COLUMN id_equipamento DROP NOT NULL; 

-- 26-07-2017 [Fernanda Ferreira] - Remoção da coluna 'tipo_navio_ais'
alter table info_veiculo_maritimo drop column if exists tipo_navio_ais;

-- 17-10-2017 [Beatriz Sauer] - Remoção da constraint 'id_sonar_vds_fk'  e das colunas 'ros' e 'id_sonar_vds'
ALTER TABLE info_veiculo_helicoptero DROP CONSTRAINT IF EXISTS id_sonar_vds_fk ;
ALTER TABLE info_veiculo_helicoptero DROP COLUMN IF EXISTS id_sonar_vds;
ALTER TABLE info_veiculo_helicoptero DROP COLUMN IF EXISTS ros;

-- 18-09-2017 [Jonata] - Adição de chave estrangeira do objeto tático na tabela Condição Entidade.
--ALTER TABLE condicao_entidade DROP CONSTRAINT IF EXISTS id_objeto_tatico_fk;
--ALTER TABLE condicao_entidade ADD CONSTRAINT id_objeto_tatico_fk FOREIGN KEY (id_objeto_tatico)
--        REFERENCES objeto_tatico (id_objeto_tatico);

alter table info_veiculo_maritimo drop column if exists tipo_navio_ais;

-- 23-10-2017 [Davi Lessa] - Migração dos dados da tabela 'configuracao_linkyb_maquina' para 'configuracao_equipamento_maquina'
-- INSERT INTO configuracao_equipamento_maquina
-- (
--     id_config_sensor,
--     id_maquina
-- )
-- SELECT * FROM configuracao_linkyb_maquina WHERE (
--     SELECT EXISTS (
--         SELECT 1
--         FROM   information_schema.tables 
--         WHERE  table_name = 'configuracao_linkyb_maquina'
--     )
-- );

-- 23-10-2017 [Davi Lessa] - Remoção da tabela 'configuracao_linkyb_maquina'
-- DROP TABLE IF EXISTS configuracao_linkyb_maquina;

-- 08-11-2017 [Fernanda Ferreira] - Remoção da tabela 'foto_veiculo'
DROP TABLE IF EXISTS foto_veiculo;

-- 15-05-2017 [Ana Carolina Sim Moraes] - Remoção de colunas do AreaExercicio.
ALTER TABLE area_exercicio DROP COLUMN IF EXISTS cor;
ALTER TABLE area_exercicio DROP COLUMN IF EXISTS correcao_manual;
ALTER TABLE area_exercicio DROP COLUMN IF EXISTS espessura;
ALTER TABLE area_exercicio DROP COLUMN IF EXISTS estilolinha;
ALTER TABLE area_exercicio DROP COLUMN IF EXISTS estilo_linha;
ALTER TABLE area_exercicio DROP COLUMN IF EXISTS data_hora_inicial;
ALTER TABLE area_exercicio DROP COLUMN IF EXISTS data_hora_final;
ALTER TABLE area_exercicio DROP COLUMN IF EXISTS preenchido;

-- 23-05-2018 [Beatriz Sauer] - Exclusão das tabelas data_historico, marcador_historico e historico.
-- DROP TABLE IF EXISTS historico;
-- DROP TABLE IF EXISTS marcador_historico;
-- DROP TABLE IF EXISTS data_historico; 

-- 13-03-2018 [Paulo Collares] - Remoção das tabelas associadas ao sensor 'camera ip'
DROP TABLE IF EXISTS sensor_camera_ip;
DROP TABLE IF EXISTS configuracao_camera_ip;

-- 23-03-2018 [Fernanda Ferreira] - Remoção de coluna 'permitirFusaoDados'.
ALTER TABLE perfil_usuario DROP COLUMN IF EXISTS permitir_fusao_dados;

-- 27-03-2018 [Luis Henrique] - Criação da tabela ambiente_instalacao e FK id_ambiente_instalacao na tabela perfil_usuario, caso não exista
INSERT INTO ambiente_instalacao (id_ambiente_instalacao, nome) 
    SELECT 2, 'DESKTOP' WHERE NOT EXISTS (SELECT id_ambiente_instalacao FROM ambiente_instalacao WHERE id_ambiente_instalacao = 2);


UPDATE perfil_usuario SET id_ambiente_instalacao = '2' WHERE id_ambiente_instalacao IS NULL;

-- 12-04-2018 [Luis Henrique] - Constraint da tabela configuracao_equipamento_maquina
ALTER TABLE configuracao_equipamento_maquina DROP CONSTRAINT IF EXISTS fk_irdxt0s6ml02jd06akqk3n41t;
ALTER TABLE configuracao_equipamento_maquina DROP CONSTRAINT IF EXISTS id_config_sensor_fk;
ALTER TABLE configuracao_equipamento_maquina ADD CONSTRAINT id_config_sensor_fk FOREIGN KEY (id_config_sensor)
      REFERENCES configuracao_equipamento (id_config_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

ALTER TABLE configuracao_equipamento_maquina DROP CONSTRAINT IF EXISTS fk_rjbaagi5mbm6vc2hs8r90o5ah;
ALTER TABLE configuracao_equipamento_maquina DROP CONSTRAINT IF EXISTS id_maquina_fk;
ALTER TABLE configuracao_equipamento_maquina ADD CONSTRAINT id_maquina_fk FOREIGN KEY (id_maquina)
      REFERENCES maquina (id_maquina) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

    ALTER TABLE configuracao_vrmtc DROP CONSTRAINT IF EXISTS fk_p5hd1ewovfamt94a2hb49x937;
ALTER TABLE configuracao_vrmtc DROP CONSTRAINT IF EXISTS id_config_sensor_fk;
ALTER TABLE configuracao_vrmtc ADD CONSTRAINT id_config_sensor_fk FOREIGN KEY (id_config_sensor)
      REFERENCES configuracao_equipamento (id_config_sensor) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE;

-- 27-04-2018 [Davi Lessa] - Remoção da tabela identificador_lora_informacao_veiculo
DROP TABLE IF EXISTS identificador_lora_informacao_veiculo;