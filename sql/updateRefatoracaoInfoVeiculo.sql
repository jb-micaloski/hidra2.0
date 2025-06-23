-- 01-07-2016 [Camila Siqueira] - Refatoração Informação Veículos
INSERT INTO info_veiculo(id_informacao_veiculo, custo_veiculo, nome, id_plataforma) 
	SELECT id_informacao_veiculo, custo_veiculo, nome, id_plataforma FROM info_veiculo_superficie
        WHERE (SELECT COUNT(id_informacao_veiculo) FROM info_veiculo) = 0;

INSERT INTO info_veiculo_modelo_3d(modelo3d, veiculo)
        SELECT id_modelo_3d, id_informacao_veiculo FROM info_veiculo_superficie as IVS
WHERE
    NOT EXISTS (
        SELECT modelo3d, veiculo FROM info_veiculo_modelo_3d WHERE modelo3d = IVS.id_modelo_3d 
                AND veiculo = IVS.id_informacao_veiculo
    );

INSERT INTO info_veiculo(id_informacao_veiculo, custo_veiculo, nome, id_plataforma) 
	SELECT id_informacao_veiculo, custo_veiculo, nome, id_plataforma FROM info_veiculo_submarino
        WHERE (SELECT COUNT(id_informacao_veiculo) FROM info_veiculo) <> 
        ((SELECT COUNT(id_informacao_veiculo) FROM info_veiculo_superficie) + 
        (SELECT COUNT(id_informacao_veiculo) FROM info_veiculo_submarino));

INSERT INTO info_veiculo(id_informacao_veiculo, custo_veiculo, nome, id_plataforma) 
	SELECT id_informacao_veiculo, custo_veiculo, nome, id_plataforma FROM info_veiculo_helicoptero
WHERE  (SELECT count(id_informacao_veiculo) from info_veiculo) <> 
       (SELECT COUNT(id_informacao_veiculo) FROM info_veiculo_superficie) + 
       (SELECT COUNT(id_informacao_veiculo) FROM info_veiculo_submarino) + 
       (SELECT COUNT(id_informacao_veiculo) FROM info_veiculo_helicoptero);

INSERT INTO info_veiculo(id_informacao_veiculo, custo_veiculo, nome, id_plataforma) 
	SELECT id_informacao_veiculo, custo_veiculo, nome, id_plataforma FROM info_veiculo_aviao
WHERE (SELECT count(id_informacao_veiculo) from info_veiculo) <> 
        (SELECT COUNT(id_informacao_veiculo) FROM info_veiculo_superficie) + 
        (SELECT COUNT(id_informacao_veiculo) FROM info_veiculo_submarino) + 
        (SELECT COUNT(id_informacao_veiculo) FROM info_veiculo_aviao) + 
        (SELECT COUNT(id_informacao_veiculo) FROM info_veiculo_helicoptero);

INSERT INTO info_veiculo_aereo(id_informacao_veiculo, autonomia, rumo_magnetico) 
	SELECT id_informacao_veiculo, autonomia, rumo_magnetico FROM info_veiculo_helicoptero
WHERE (SELECT COUNT(id_informacao_veiculo) FROM info_veiculo_aereo) = 0;

INSERT INTO info_veiculo_aereo(id_informacao_veiculo, autonomia, rumo_magnetico) 
	SELECT id_informacao_veiculo, autonomia, rumo_magnetico FROM info_veiculo_aviao
    WHERE  (SELECT count(id_informacao_veiculo) from info_veiculo_aereo) <> 
       (SELECT COUNT(id_informacao_veiculo) FROM info_veiculo_helicoptero) + 
       (SELECT COUNT(id_informacao_veiculo) FROM info_veiculo_aviao);

INSERT INTO info_veiculo_maritimo(id_informacao_veiculo, imo, mmsi, ros, tipo_navio_ais) 
	SELECT id_informacao_veiculo, imo, mmsi, ros, tipo_navio_ais FROM info_veiculo_superficie
WHERE (SELECT COUNT(id_informacao_veiculo) FROM info_veiculo_maritimo) = 0;

INSERT INTO info_veiculo_maritimo(id_informacao_veiculo, imo, mmsi, ros, tipo_navio_ais) 
	SELECT id_informacao_veiculo, imo, mmsi, ros, tipo_navio_ais FROM info_veiculo_submarino
WHERE  (SELECT count(id_informacao_veiculo) from info_veiculo_maritimo) <> 
       (SELECT COUNT(id_informacao_veiculo) FROM info_veiculo_superficie) + 
       (SELECT COUNT(id_informacao_veiculo) FROM info_veiculo_submarino);

ALTER TABLE info_veiculo_superficie DROP COLUMN IF EXISTS custo_veiculo;
ALTER TABLE info_veiculo_superficie DROP COLUMN IF EXISTS nome;
ALTER TABLE info_veiculo_superficie DROP COLUMN IF EXISTS pais;
ALTER TABLE info_veiculo_superficie DROP COLUMN IF EXISTS id_modelo_3d;
ALTER TABLE info_veiculo_superficie DROP COLUMN IF EXISTS id_plataforma;
ALTER TABLE info_veiculo_superficie DROP COLUMN IF EXISTS imo;
ALTER TABLE info_veiculo_superficie DROP COLUMN IF EXISTS mmsi;
ALTER TABLE info_veiculo_superficie DROP COLUMN IF EXISTS ros; 
ALTER TABLE info_veiculo_superficie DROP COLUMN IF EXISTS tipo_navio_ais;

ALTER TABLE info_veiculo_submarino DROP COLUMN IF EXISTS custo_veiculo;
ALTER TABLE info_veiculo_submarino DROP COLUMN IF EXISTS  nome;
ALTER TABLE info_veiculo_submarino DROP COLUMN IF EXISTS pais;
ALTER TABLE info_veiculo_submarino DROP COLUMN IF EXISTS id_modelo_3d;
ALTER TABLE info_veiculo_submarino DROP COLUMN IF EXISTS id_plataforma;
ALTER TABLE info_veiculo_submarino DROP COLUMN IF EXISTS imo;
ALTER TABLE info_veiculo_submarino DROP COLUMN IF EXISTS mmsi;
ALTER TABLE info_veiculo_submarino DROP COLUMN IF EXISTS ros;
ALTER TABLE info_veiculo_submarino DROP COLUMN IF EXISTS tipo_navio_ais;

ALTER TABLE info_veiculo_aviao DROP COLUMN IF EXISTS custo_veiculo;
ALTER TABLE info_veiculo_aviao DROP COLUMN IF EXISTS nome;
ALTER TABLE info_veiculo_aviao DROP COLUMN IF EXISTS pais;
ALTER TABLE info_veiculo_aviao DROP COLUMN IF EXISTS id_modelo_3d;
ALTER TABLE info_veiculo_aviao DROP COLUMN IF EXISTS id_plataforma;
ALTER TABLE info_veiculo_aviao DROP COLUMN IF EXISTS autonomia; 
ALTER TABLE info_veiculo_aviao DROP COLUMN IF EXISTS rumo_magnetico;

ALTER TABLE info_veiculo_helicoptero DROP COLUMN IF EXISTS custo_veiculo;
ALTER TABLE info_veiculo_helicoptero DROP COLUMN IF EXISTS nome;
ALTER TABLE info_veiculo_helicoptero DROP COLUMN IF EXISTS pais;
ALTER TABLE info_veiculo_helicoptero DROP COLUMN IF EXISTS id_modelo_3d;
ALTER TABLE info_veiculo_helicoptero DROP COLUMN IF EXISTS id_plataforma;
ALTER TABLE info_veiculo_helicoptero DROP COLUMN IF EXISTS autonomia; 
ALTER TABLE info_veiculo_helicoptero DROP COLUMN IF EXISTS rumo_magnetico;
