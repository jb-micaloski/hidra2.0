-- 07-07-2016 [Camila Siqueira] - Refatoração Objeto Tático
INSERT INTO objeto_tatico(id_objeto_tatico)
        SELECT id_objeto_tatico FROM area_circular;

INSERT INTO objeto_tatico(id_objeto_tatico)
        SELECT id_objeto_tatico FROM area_poligonal;

-- Refatoração de areas
INSERT INTO area_exercicio(id_objeto_tatico, nome)
        SELECT id_objeto_tatico, nome FROM area_circular;  
INSERT INTO area_exercicio(id_objeto_tatico, nome)
        SELECT id_objeto_tatico, nome FROM area_poligonal;
-- Fim Refatoração de areas 

INSERT INTO objeto_tatico(id_objeto_tatico)
        SELECT id_objeto_tatico FROM brs;

INSERT INTO objeto_tatico(id_objeto_tatico)
        SELECT id_objeto_tatico FROM camera_fixa;

INSERT INTO objeto_tatico(id_objeto_tatico)
        SELECT id_objeto_tatico FROM elemento_navegacao;

INSERT INTO objeto_tatico(id_objeto_tatico)
        SELECT id_objeto_tatico FROM plano_navegacao;

INSERT INTO objeto_tatico(id_objeto_tatico)
        SELECT id_objeto_tatico FROM ponto_fixo;

INSERT INTO objeto_tatico(id_objeto_tatico)
        SELECT id_objeto_tatico FROM rota;

INSERT INTO objeto_tatico(id_objeto_tatico)
        SELECT id_objeto_tatico FROM sar_incidente_sar;

INSERT INTO objeto_tatico(id_objeto_tatico)
        SELECT id_objeto_tatico FROM sar_salva_mar;

INSERT INTO objeto_tatico(id_objeto_tatico)
        SELECT id_objeto_tatico FROM sar_sru;

INSERT INTO objeto_tatico(id_objeto_tatico)
        SELECT id_objeto_tatico FROM waypoint;


