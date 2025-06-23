-- 15-05-2017 [Camila Siqueira] - Refatoração Plataforma Superfície
UPDATE  info_veiculo_superficie SET vms = (SELECT plataforma_superficie.vms
    FROM plataforma_superficie, info_veiculo
        WHERE  info_veiculo.id_plataforma = plataforma_superficie.id_plataforma 
            AND info_veiculo.id_informacao_veiculo = info_veiculo_superficie.id_informacao_veiculo);

UPDATE  info_veiculo_superficie SET vos = (SELECT plataforma_superficie.vos
    FROM plataforma_superficie, info_veiculo
        WHERE  info_veiculo.id_plataforma = plataforma_superficie.id_plataforma 
            AND info_veiculo.id_informacao_veiculo = info_veiculo_superficie.id_informacao_veiculo);

ALTER TABLE plataforma_superficie DROP COLUMN IF EXISTS vos; 
ALTER TABLE plataforma_superficie DROP COLUMN IF EXISTS vms;
